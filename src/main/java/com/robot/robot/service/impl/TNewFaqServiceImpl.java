package com.robot.robot.service.impl;

import com.robot.common.domain.TuLingReturn;
import com.robot.common.domain.XFyunReturn;
import com.robot.common.utils.*;
import com.robot.robot.common.HanLPUrl;
import com.robot.robot.controller.app.bean.FaqRequestBean;
import com.robot.robot.dao.*;
import com.robot.robot.domain.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.robot.robot.service.TNewFaqService;

import javax.annotation.Resource;


@Service
public class TNewFaqServiceImpl implements TNewFaqService {

	@Resource
	private TNewFaqDao tNewFaqDao;
	@Resource
	private TEntityDao tEntityDao;
	@Resource
	private TEntranceDao tEntranceDao;
	@Resource
	private TEntranceEntityDao tEntranceEntityDao;
	@Resource
	private TEntranceFaqDao tEntranceFaqDao;
	@Resource
	private TNonexistentFaqDao tNonexistentFaqDao;
	@Resource
	private TRepositoryDao tRepositoryDao;
	@Resource
	private TFaqLogDao tFaqLogDao;
	@Resource
	private TRobotUserDao tRobotUserDao;

	public static Logger log = Logger.getLogger(TNewFaqService.class);

	private final static String DEFAULT_NO_ANSWER = "对不起！本宝宝回答不了这个问题。";    //问答没有找到答案时提示
	private final static String DEFAULT_ANSWER = "本宝宝回答不了您的问题，您可拨打24小时报障电话0750-3438266；日间业务咨询电话18138001831或18138001832，我们将尽快安排专业人员处理，江海供电局将竭诚为您服务，感谢您的理解与支持。";
	private final static String DL_WORD = "电,一户一表,一口受理,用电报装,报装容量,功率因数,城乡结合部,竣工检验,隐蔽工程,峰谷分时,计量装置,阶梯分时,最大需量,事业单位,社会团体,暂拆恢复,暂换恢复,复核单,国土所,国土局,总容量,两部制,大工业,大客户,单车房,通知单,烂尾楼,变压器,抄错表,非居民,表位,报装,合表,拆表,验表,抄表,改压,移表,改类,新装,装表,催费,业扩,增容,减容,分表,暂拆,暂换,暂缓,异议,峰谷,复核,专变,计量,租户,竣工,统建,征收,总段,整流,分户,居民,过户,低压,中压,光伏,高压,功率";


	private final static float NEAR_DOC_SIM = 0.9f;    //文档相似度--闸值
	private final static double SIM = 0.95;    //相似度--闸值
	private final static double DIS = 0.01;    //语义距离--闸值

	private final static int CACHE_TIME = 50;   //缓存时间 秒

	//private final static String MODEL_FILE_NAME = "C:/Users/lenovo/Desktop/HanLP/data/model/word_vector_model.txt";  //模型文件路径
	private final static String MODEL_FILE_NAME = HanLPUrl.getHanLPUrl() + "data/model/word_vector_model.txt";  //模型文件路径

	private final static String[] YES = {"是", "恩", "对", "行", "好", "有"};
	private final static String[] NO = {"不是", "不", "不对", "没", "没有", "不好", "不行"};
	private final static String[] REPLACE = {"的", "啊", "呀", "哦", "呢",",","，","?","？",".","。","请","咦","哎","是","是的","呵呵","哪里","那里","那个"};

	private List<TEntityDO> entityList = null;
	private List<TEntranceDO> entranceList = null;
	private List<TEntranceEntityDO> tEntranceEntityList = null;

	private List<TNewFaqDO> faqList = null;
	private List<TEntranceFaqDO> entranceFaqList = null;

    private String questionMatch = "";

	private Map<String, String> entranceEntityMap = new HashMap<>();
	private Map<String,String> stringMap = new HashMap<>();

	private void getEntityList(String robotNo) {
		Map<String,Object> map = new HashMap<>();
		map.put("robotNo",robotNo);
		entityList = tEntityDao.list(map);
	}

	private void getEntranceList(String robotNo) {
		Map<String,Object> map = new HashMap<>();
		map.put("robotNo",robotNo);
		entranceList =tEntranceDao.list(map);
	}

	private void getEntranceEntityList(String robotNo) {
		Map<String,Object> map = new HashMap<>();
		map.put("robotNo",robotNo);
		tEntranceEntityList = tEntranceEntityDao.list(map);
	}

	private void getFaqList(String robotNo,Long userId) {
		Map<String,Object> map = new HashMap<>();
		map.put("robotNo",robotNo);
		map.put("userId",userId);
		faqList = tNewFaqDao.list(map);
	}

	private void getEntranceFaqList(String robotNo) {
		Map<String,Object> map = new HashMap<>();
		map.put("robotNo",robotNo);
		entranceFaqList = tEntranceFaqDao.list(map);
	}

	@Override
	public TNewFaqDO get(Long id){
		return tNewFaqDao.get(id);
	}
	
	@Override
	public List<TNewFaqDO> list(Map<String, Object> map){
		return tNewFaqDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tNewFaqDao.count(map);
	}
	
	@Override
	public int save(TNewFaqDO tNewFaq){
		Long userId = ShiroUtils.getUserId();
		Long repositoryId = tRepositoryDao.getIdByUserId(userId);
		tNewFaq.setRepositoryId(repositoryId);
		return tNewFaqDao.save(tNewFaq);
	}
	
	@Override
	public int update(TNewFaqDO tNewFaq){
		return tNewFaqDao.update(tNewFaq);
	}
	
	@Override
	public int remove(Long id){
		return tNewFaqDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tNewFaqDao.batchRemove(ids);
	}

	@Override
	public FaqRequestBean searchAnswer(String question,String robotNo){
        FaqRequestBean faqBean = new FaqRequestBean();
        List<String> questionList = new ArrayList<>();

		entranceEntityMap = RedisUtil.getMap(robotNo + "robot_entrance_entity_cache");
		String entityId = RedisUtil.getValue(robotNo + "robot_up_entity_id");
		if(StringUtils.isNotEmpty(entityId)){
			String answer = doScene(question,robotNo,entityId);
			String cacheQuestion = RedisUtil.getValue(robotNo + "robot_next_question");
			if(StringUtils.isNotEmpty(cacheQuestion)){
				String cacheAnswer = getAnswerByEntrance(cacheQuestion,robotNo);
				if(StringUtils.isNotEmpty(cacheAnswer)){
					answer = cacheAnswer;
					questionList = recommendQuestion(question,robotNo);
                    faqBean.setQuestionList(questionList);
					removeCache(robotNo);
				}
			}
			if(StringUtils.isNotEmpty(answer)){
                faqBean.setAnswer(answer);
				saveFaq(question,answer,2,robotNo);
				return faqBean;
			}
		}
		//没有缓存则认为第一轮对话
		String answer = getClarificationByEntranceId(question,robotNo);
		if(StringUtils.isEmpty(answer)) {
			answer = getAnswerByQuestion(question, robotNo);
			saveFaq(question,answer,1,robotNo);
			if (StringUtils.isEmpty(answer)) {
				answer = getNoAnswer(question,robotNo);
				saveFaq(question,answer,0,robotNo);
				if (StringUtils.isEmpty(answer)) {
					answer = getAnswerByNonexistentFaq(question);
					if (StringUtils.isEmpty(answer)) {
						answer = getAnswerByXunFei(question);
						if (StringUtils.isEmpty(answer)) {
							answer = getAnswerByTuLing(question);
							if (StringUtils.isEmpty(answer)) {
								answer = DEFAULT_NO_ANSWER;
							}
						}
					}
					saveFaq(question,answer,3,robotNo);
				}
			}
		}
		questionList = recommendQuestion(question,robotNo);
        faqBean.setQuestionList(questionList);
        faqBean.setAnswer(answer);
        faqBean.setQuestion(questionMatch);
		return faqBean;
	}


    /**
     * 问题推荐
     */
	private List<String> recommendQuestion(String question,String robotNo){
        List<String> questionList = new ArrayList<>();
	    initQuestionMap(robotNo,null);
        double matchValue = 0.11;
		Map<Long,Map<String, Double>> mapMap = compare(question, stringMap,matchValue);
		Map<String, Double> map = compareMatch(mapMap);
		Map<String,Double> mapSort = MapUtils.doubleMapSort(map);
		Iterator<Map.Entry<String,Double>> it = mapSort.entrySet().iterator();
		int count = 0;
		while (it.hasNext()){
			Map.Entry<String,Double> entry = it.next();
			String ques = entry.getKey();
            if(StringUtils.isNotEmpty(ques)){
				count++;
                questionList.add(ques);
                if(count == 5){
                	break;
				}
            }
		}
		return questionList;
	}

	/**
	 * 获取多轮问答知识库的答案
	 */
	public String getAnswerByEntrance(String question,String robotNo){
		/*//全文检索
		String answer = getLikeByEntranceFaq(question,robotNo);
		if(StringUtils.isNotEmpty(answer)){
			return answer;
		}*/
		//特征提取匹配
		initEntranceFaqMap(robotNo);
        double matchValue = 0.9;
		Long entranceFaqId = keywordCompare(question,stringMap,matchValue);
		if(entranceFaqId > 0){
			return tEntranceFaqDao.get(entranceFaqId).getAnswer();
		}
		/*
		//相似度匹配
		try {
			entranceFaqId = nearestDocument(question,stringMap,MODEL_FILE_NAME);
		}catch (IOException IOE){
			log.debug(IOE);
		}
		if(entranceFaqId > 0){
			return tEntranceFaqDao.get(entranceFaqId).getAnswer();
		}
		*/
		return null;
	}

	/**
	 * 是否开启闲聊
	 */
	private String getNoAnswer(String question,String robotNo){
		Map<String,Object> map = new HashMap<>();
		map.put("robotNo",robotNo);
		Iterator<TRobotUserDO> it = tRobotUserDao.list(map).iterator();
		while (it.hasNext()){
			TRobotUserDO tRobotUserDO = it.next();
			if(1 == tRobotUserDO.getChat()){
				return tRobotUserDO.getDefaultAnswer();
			}
		}
/*
		//判断是否是专业问题
		boolean no = match(question,DL_WORD.split(","));
		if(no){
			return DEFAULT_ANSWER;
		}*/
		return null;
	}



	/**
	 * 多轮问答问题-全文检索
	 */
	public String getLikeByEntranceFaq(String question,String robotNo){
		String answer = "";
		Map<String,Object> map = new HashMap<>();
		map.put("question",question);
		map.put("robotNo",robotNo);
		List<TEntranceFaqDO> tEntranceFaq = tEntranceFaqDao.getLikeByQuestion(map);
		if(tEntranceFaq.size() == 1){
			for(TEntranceFaqDO entranceFaqDO : tEntranceFaq){
				answer = entranceFaqDO.getAnswer();
				if(StringUtils.isNotEmpty(answer)){
					break;
				}
			}
		}
		return answer;
	}


	/**
	 * 根据问题找到场景ID返回澄清语句
	 */
	public String getClarificationByEntranceId(String question,String robotNo){
		//初始化场景
		initEntranceMap(robotNo);

		//全文检索
		Long entranceId = getEntranceIdByQuestion(question,robotNo);
		if(entranceId > 0){
			saveQuestion(robotNo,entranceId);
			return existEntity(question, robotNo, entranceId);
		}
		//特征提取匹配
        double matchValue = 0.9;
		entranceId = keywordCompareByEntrance(question,stringMap,matchValue);
		if(entranceId > 0){
			saveQuestion(robotNo,entranceId);
			return existEntity(question, robotNo, entranceId);
		}
		//相似度匹配
		entranceId = nearestDocument(question,stringMap,MODEL_FILE_NAME);
		if(entranceId > 0){
			saveQuestion(robotNo,entranceId);
			return existEntity(question, robotNo, entranceId);
		}
		return null;
	}


	/**
	 * 一问一答库匹配答案
	 */
	private String getAnswerByQuestion(String question,String robotNo){
		//全文检索
		/*String answer = getLikeByQuestion(question,robotNo);
		if(StringUtils.isNotEmpty(answer)){
			return answer;
		}*/
		//特征提取匹配
		initQuestionMap(robotNo,null);
        double matchValue = 0.65;
		Long faqId = keywordCompare(question,stringMap,matchValue);
		if(faqId > 0){
			return getAnswerById(faqId);
		}
		//相似度匹配
		faqId = nearestDocument(question,stringMap,MODEL_FILE_NAME);
		if(faqId > 0){
			return getAnswerById(faqId);
		}
		return null;
	}



	/**
	 * 根据ID获取答案
	 */
	private String getAnswerById(Long id){
		for(TNewFaqDO faqDO : faqList){
			if(id.equals(faqDO.getId())){
				return faqDO.getAnswer();
			}
		}
		return null;
	}


	/**
	 * 根据ID获取问题
	 */
	private String getQuestionById(Long id){
		for(TNewFaqDO faqDO : faqList){
			if(id.equals(faqDO.getId())){
				return faqDO.getQuestion();
			}
		}
		return null;
	}


	/**
	 * QA问题-全文检索
	 */
	@Override
	public String getLikeByQuestion(String question,String robotNo){
		String answer = "";
		Map<String,Object> map = new HashMap<>();
		map.put("question",question);
		map.put("robotNo",robotNo);
		List<TNewFaqDO> tNewFaqDo = tNewFaqDao.getLikeByQuestion(map);
		if(tNewFaqDo.size() > 0){
			for(TNewFaqDO tNewFaq: tNewFaqDo){
                questionMatch = tNewFaq.getQuestion() + " ## 置信度：99.9% ##";
				answer = tNewFaq.getAnswer();
				if(StringUtils.isNotEmpty(answer)){
					break;
				}
			}
		}
		return answer;
	}


	/**
	 * 场景入口-全文检索
	 */
	public long getEntranceIdByQuestion(String question,String robotNo){
		Map<String,Object> map = new HashMap<>();
		map.put("question",question);
		map.put("robotNo",robotNo);
		Long id = tEntranceDao.getEntranceIdByQuestion(map);
		if(id == null){
			return 0;
		}
		return id;
	}



	/**
	 * 讯飞获取问答
	 *
	 * @param question 问题
	 * @return answer 答案
	 */
	@Override
	public String getAnswerByXunFei(String question) {
		String result = null;
		try {
			XFyunReturn xfReturn = XFyunUtils.runChat(question);
			result = xfReturn.getText();
		}catch (Exception e){
			log.debug(e);
		}
		if (StringUtils.isNotEmpty(result)) {
			log.info("------------------通过 < 讯飞 > 寻找 question:" + question + ",result:" + result + "-------------------");

			saveNonexistentFaq(question, result);
		}
		return result;
	}


	/**
	 * 图灵获取问答
	 *
	 * @param question 问题
	 * @return answer 答案
	 */
	@Override
	public String getAnswerByTuLing(String question) {
		String result = null;
		try {
		TuLingReturn tuLingReturn = TuLingUtils.postTalk(question);
		result = tuLingReturn.getText();
		}catch (Exception e){
			log.debug(e);
		}
		if (StringUtils.isNotEmpty(result)) {
			log.info("-------------------通过 < 图灵 >  寻找 question:" + question + ",result:" + result + "-------------------");

			saveNonexistentFaq(question, result);
		}
		return result;
	}

	/**
	 * 保存知识库没有的问答
	 *
	 * @param question 问题
	 * @param answer   答案
	 */
	private void saveNonexistentFaq(String question, String answer) {
		List<TNonexistentFaqDO> list = tNonexistentFaqDao.existQuestion(question);
		if (list.size() <= 0) {
			TNonexistentFaqDO tNonexistentFaq = new TNonexistentFaqDO();
			tNonexistentFaq.setQuestion(question);
			tNonexistentFaq.setAnswer(answer);
			tNonexistentFaq.setCreatetime(new Date());
			tNonexistentFaqDao.save(tNonexistentFaq);
		}
	}

	/**
	 * 遗漏问题库获取问答
	 */
	private String getAnswerByNonexistentFaq(String question){
		String answer = null;
		Map<String, Object> map = new HashMap<>();
		map.put("question", question);
		map.put("offset", 0);
		map.put("limit", 1);
		List<TNonexistentFaqDO> tNonexistentFaqList = tNonexistentFaqDao.list(map);
		Iterator<TNonexistentFaqDO> it = tNonexistentFaqList.iterator();
		while (it.hasNext()) {
			answer = it.next().getAnswer();
		}
		return answer;
	}

	/**
	 * 保存问答记录
	 */
	private void saveFaq(String question,String answer,int way,String robotNo){
		if(com.robot.common.utils.StringUtils.isNotEmpty(answer)){
			TFaqLogDO tFaqLogDO = new TFaqLogDO();
			tFaqLogDO.setQuestion(question);
			tFaqLogDO.setAnswer(answer);
			tFaqLogDO.setWay(way);
			tFaqLogDO.setRobotNo(robotNo);
			tFaqLogDO.setCreatetime(new Date());
			tFaqLogDao.save(tFaqLogDO);
		}
	}


	/**
	 * 初始化场景入口数据
	 */
	public void initEntranceMap(String robotNo) {
		stringMap.clear();
		getEntityList(robotNo);
		getEntranceList(robotNo);
		getEntranceEntityList(robotNo);
		if(entranceList.size() > 0){
			for (TEntranceDO list : entranceList) {
				String id = list.getId().toString();
				String string = list.getEntrance();
				String string2 = list.getEntrances();
				initMap(string,string2,id);
			}
		}
	}


	/**
	 * 初始化问题数据
	 */
	private void initQuestionMap(String robotNo,Long userId) {
		stringMap.clear();
		getFaqList(robotNo,userId);
		if(faqList.size() > 0){
			for (TNewFaqDO list : faqList) {
				String id = list.getId().toString();
				String string = list.getQuestion();
				String string2 = list.getQuestions();
				initMap(string,string2,id);
			}
		}
	}


	/**
	 * 初始化多轮问答库数据
	 */
	public void initEntranceFaqMap(String robotNo) {
		stringMap.clear();
		getEntranceFaqList(robotNo);
		if(entranceFaqList.size() > 0){
			for (TEntranceFaqDO list : entranceFaqList) {
				String id = list.getId().toString();
				String string = list.getQuestion();
				String string2 = list.getQuestions();
				initMap(string,string2,id);
			}
		}
	}


	/**
	 * 初始stringMap数据
	 */
	private void initMap(String string,String string2,String id) {
		if(com.robot.common.utils.StringUtils.isNotEmpty(string)){
			stringMap.put(string, id);
		}
		if (com.robot.common.utils.StringUtils.isNotEmpty(string2)) {
			String[] strings = string2.split("\\r\\n");
			for (String s : strings) {
				if (StringUtils.isNotEmpty(s)) {
					stringMap.put(s, id);
				}
			}
		}
	}




	/**
	 * 智能搜索推荐
	 *
	 * @param question 问题
	 * @return 返回问题集推荐
	 */
	@Override
	public List<String> intelligentSearch(String question, Map<String, String> stringMap) {
		if (stringMap.size() <= 0) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		for (String key : stringMap.keySet()) {
			list.add(key);
		}
		return HanLPUtils.intelligentSearch(list, question, 5);
	}



    /**
     * 特征提取匹配
     *
     * @param question 问题
     * @param stringMap 匹配集
     */
    @Override
	public Long keywordCompare(String question, Map<String, String> stringMap,double matchValue){
		Map<Long,Map<String, Double>> mapMap = compare(question, stringMap,matchValue);
		Map<String,Double> map = new HashMap<>();
		for(Long key : mapMap.keySet()){
			Map<String,Double> map2 = mapMap.get(key);
			for(String key2 : map2.keySet()){
				map.put(key.toString(),map2.get(key2));
			}
		}

        Long id = getIdByKeyword(map);
        String ques = getQuestionById(id);
		double match = 0.00;
		if(id > 0){
			match = map.get(id.toString());
		}
		questionMatch = ques + " ## 置信度：" + match * 100 + "% ##";
        return id;
    }


	/**
	 * 多轮问答匹配
	 */
	public Long keywordCompareByEntrance(String question, Map<String, String> stringMap,double matchValue){
		Map<Long,Map<String, Double>> map = compare(question, stringMap,matchValue);
		Map<String, Double> returnMap = new HashMap<>();
		for(Long key : map.keySet()){
			if(key > 0){
				Map<String, Double> map2 = map.get(key);
				for(String key2 : map2.keySet()){
					returnMap.put(key.toString(),map2.get(key2));
				}
			}
		}
		return getIdByKeyword(returnMap);
	}


	/**
	 * 过滤返回问题和置信度
	 */
	public Map<String,Double> compareMatch(Map<Long,Map<String, Double>> mapMap){
		Map<String,Double> map = new HashMap<>();
		for(Long key : mapMap.keySet()){
			map.putAll(mapMap.get(key));
		}
		return map;
	}


    /**
     * 特征处理
     */
    public Map<Long,Map<String, Double>> compare(String question, Map<String, String> stringMap,double matchValue) {
        Map<Long,Map<String, Double>> mapMap = new HashMap<>();

        Set<String> list = HanLPUtils.getKeywordByHanLP(replace(question));
        if(stringMap.size() > 0 && list.size() > 0){
            for (String key : stringMap.keySet()) {
				Map<String, Double> map = new HashMap<>();
                Set<String> list2 = HanLPUtils.getKeywordByHanLP(replace(key));
                int count = 0;
                for (String word : list) {
                    for (String feature : list2) {
                        if (word.equals(feature)) {
                            count++;
                        }
                    }
                }
				double match2 = (double)count / list.size();
                double match22 = (double)count / list2.size();
				double match = match2 * match22;
                if(match > matchValue){
                    String id = stringMap.get(key);

                    if(mapMap.size() <= 0){
						map.put(key,match);
					}
                    for(Map.Entry<Long,Map<String, Double>> entry : mapMap.entrySet()){
                    	if(entry.getKey().toString().equals(id)){
							Map<String,Double> map2 = entry.getValue();
							for(String key3 : map2.keySet()){
								double value = map2.get(key3);
								if(match > value){
									map.put(key,match);
								}
							}
						}else {
							map.put(key,match);
						}
					}
					mapMap.put(Long.parseLong(id),map);
				}
            }
        }
        return mapMap;
    }

    /**
     * 获取集合中最大值的keyId
     */
	private Long getIdByKeyword(Map<String, Double> map){
        Long id = 0L;
	    String maxId = MapUtils.getKeyByMaxDoubleValue(map);
        if(StringUtils.isNotEmpty(maxId)){
            id = Long.parseLong(maxId);
        }
        return id;
    }


	/**
	 * 文档相似度匹配
	 *
	 * @param question 问题
	 * @param stringMap 匹配集
	 * @param model_file_name 指定模型路径
	 * @return 匹配ID
	 */
	@Override
	public Long nearestDocument(String question, Map<String, String> stringMap, String model_file_name) {
		Map<String, Float> returnMap = new ConcurrentHashMap<>();

		List<String> list = new ArrayList<>();
		for (String key : stringMap.keySet()) {
			list.add(key);
		}
		int size = list.size();
		String[] documents = new String[size];
		list.toArray(documents);
		Map<String, Float> nearMap = Doc2Vec.getDocVectorModel(model_file_name).nearestDocument(question, documents);
		for (String key : stringMap.keySet()) {
			for (String key2 : nearMap.keySet()) {
				if (key2.equals(key)) {
					float value = nearMap.get(key2);
					String id = stringMap.get(key);
					if (returnMap.size() <= 0 && value > NEAR_DOC_SIM) {
						returnMap.put(id, value);
					} else {
						boolean exist = false;
						for (String idKey : returnMap.keySet()) {
							float oldValue = returnMap.get(idKey);
							if (idKey.equals(id)) {
								exist = true;
								if(value > oldValue){
									returnMap.put(id, value);
								}
								break;
							}
						}
						if(!exist){
							returnMap.put(id,value);
						}
					}
				}
			}
		}
		String maxId = MapUtils.getKeyByMaxFloatValue(returnMap);
		for(String id : returnMap.keySet()){
			if(maxId.equals(id)){
				float value = returnMap.get(id);
				if(value > NEAR_DOC_SIM){
                    questionMatch = tNewFaqDao.get(Long.parseLong(maxId)).getQuestion() + " ## 置信度：" + value*100 + "% ##";
					return Long.parseLong(maxId);
				}
			}
		}
		return 0L;
	}

	/**
	 * 词-相似度匹配
	 */
	public boolean wordSim(String str,String str2){
		if(StringUtils.isEmpty(str) || StringUtils.isEmpty(str2)){
			return false;
		}
		List<String> strings = HanLPUtils.getKeyword(str);
		List<String> strings2 = HanLPUtils.getKeyword(str2);
		for(String word : strings){
			for(String word2 : strings2){
				Long distance = HanLPUtils.semanticDistance(word, word2);
				if(distance < DIS){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 文档-相似度匹配
	 */
	public boolean docSim(String doc,String doc2){
		double sim = 0;
		try{
			sim = Doc2Vec.getDocVectorModel(MODEL_FILE_NAME).docSimilarity(doc, doc2);
		}catch (Exception e){
			log.debug(e);
		}

		//正则匹配
		Pattern p = Pattern.compile(doc);
		Matcher m = p.matcher(doc2);

		if (sim > SIM || m.find()) {
			return true;
		}
		return false;
	}


	/**
	 * 根据场景ID获取场景代表问题
	 * @param id
	 * @return
	 */
	public String getQuestionByEntranceId(Long id){
		for(TEntranceDO tEntranceDO : entranceList){
			if(id.equals(tEntranceDO.getId())){
				return tEntranceDO.getEntrance();
			}
		}
		return null;
	}

	/**
	 * 保存场景代表问题缓存
	 * @param robotNo
	 * @param entranceId
	 */
	public void saveQuestion(String robotNo,Long entranceId){
		String ques = getQuestionByEntranceId(entranceId);
		if(StringUtils.isNotEmpty(ques)){
			RedisUtil.setExpire(robotNo + "robot_next_question", CACHE_TIME, ques);
		}
	}



	/**
	 * 判断问题是否存在词槽
	 */
	private String existEntity(String question,String robotNo, Long id){

		String idString = id.toString();

		List<Long> existEntityId = new ArrayList<>();
		Iterator<TEntranceEntityDO> iterator = tEntranceEntityList.iterator();
		while (iterator.hasNext()){
			TEntranceEntityDO tEntranceEntity = iterator.next();
			if (id.equals(tEntranceEntity.getEntranceId())) {
				Long entityID = tEntranceEntity.getEntityId();
				existEntityId.add(entityID);
				entranceEntityMap.put(entityID.toString(), idString);
			}
		}

		for(Long entityId : existEntityId) {
			for (TEntityDO tEntityDO : entityList) {
				if (entityId.equals(tEntityDO.getId())) {
					String[] strings = tEntityDO.getDescription().split(",");
					boolean sim = false;
					for(String s : strings){
						sim = wordSim(question,s);
						if(sim){
							entranceEntityMap.remove(entityId.toString());
							break;
						}
						sim = docSim(question,s);
						if(sim){
							entranceEntityMap.remove(entityId.toString());
							break;
						}
					}

					if (!sim) {
						RedisUtil.setExpire(robotNo + "robot_up_entity_id", CACHE_TIME, tEntityDO.getId().toString());
						RedisUtil.del(robotNo + "robot_entrance_entity_cache");
						RedisUtil.setMap(robotNo + "robot_entrance_entity_cache", entranceEntityMap);
						log.info("第一轮---" + entranceEntityMap);
						return tEntityDO.getClarification();
					}
				}
			}
		}
		if (entranceEntityMap.size() > 0) {
			log.info("第一轮===" + entranceEntityMap);
			RedisUtil.del(robotNo + "robot_entrance_entity_cache");
			RedisUtil.setMap(robotNo + "robot_entrance_entity_cache", entranceEntityMap);
		}
		return null;
	}



	public String doScene(String question,String robotNo, String entityId) {
		question = replace(question);
		boolean match = false;
		String matchString = "";
		for (TEntityDO tEntityDO : entityList) {
			//判断上一次词槽是否正确填充
			if (entityId.equals(tEntityDO.getId().toString())) {
				if (tEntityDO.getType() == 0) {
					if (question.length() > 4) {
						match = match(question,NO);
						if(match) {
							matchString = tEntityDO.getNo();
							break;
						}
					}
					match = sayNo(question);
					if(match) {
						matchString = tEntityDO.getNo();
						break;
					}else {
						match = sayYes(question);
						if(match) {
							matchString = tEntityDO.getYes();
							break;
						}
					}
				} else if (tEntityDO.getType() == 1) {
					String stringReplace = tEntityDO.getDescription();
					stringReplace = stringReplace.replace(",","，");
					String[] words = stringReplace.split("，");
					for(String word : words) {
						Double sim = Doc2Vec.getDocVectorModel(MODEL_FILE_NAME).docSimilarity(word, question);
						if(sim > SIM || word.equals(question)) {
							matchString = word;
							break;
						}
					}
				} else if (tEntityDO.getType() == 2){
					if (question.length() > 4) {
						match = match(question,NO);
						if(match) {
							matchString = tEntityDO.getNo();
							break;
						}
					}
					match = sayNo(question);
					if(match) {
						matchString = tEntityDO.getNo();
						break;
					}else {
						match = sayYes(question);
						if(match) {
							matchString = tEntityDO.getYes();
							break;
						}
					}

					String stringReplace = tEntityDO.getDescription();
					stringReplace = stringReplace.replace(",","，");
					String[] words = stringReplace.split(",");
					for(String word : words) {
						Double sim = Doc2Vec.getDocVectorModel(MODEL_FILE_NAME).docSimilarity(word, question);
						if(sim > SIM) {
							matchString = word;
							break;
						}
					}
				}
			}
		}
		if(StringUtils.isNotEmpty(matchString)){
			doMatch(robotNo,matchString,entityId);
		}

		if (entranceEntityMap.size() > 0) {
			log.info("第二轮====" + entranceEntityMap);

			for (String ee : entranceEntityMap.keySet()) {
				RedisUtil.setExpire(robotNo + "robot_up_entity_id", CACHE_TIME, ee);
				for (TEntityDO tEntityDO : entityList) {
					if (tEntityDO.getId().toString().equals(ee)) {
						RedisUtil.del(robotNo + "robot_entrance_entity_cache");
						RedisUtil.setMap(robotNo + "robot_entrance_entity_cache", entranceEntityMap);
						return tEntityDO.getClarification();
					}
				}
			}
		}
		return null;
	}


	/**
	 * 否定语义计算
	 */
	private boolean sayNo(String question) {
		for (String no : NO) {
			double distance = HanLPUtils.semanticDistance(no, question);
			if (distance < DIS) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 肯定语义计算
	 */
	private boolean sayYes(String question) {
		for (String yes : YES) {
			double distance = HanLPUtils.semanticDistance(yes, question);
			if (distance < DIS) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符去除
	 */
	private String replace(String question) {
		for (String rp : REPLACE) {
			question = question.replace(rp, "");
		}
		return question;
	}


	/**
	 * 正则匹配
	 */
	private boolean match(String doc,String[] words){
		for(String word : words){
			Pattern p = Pattern.compile(word);
			Matcher m = p.matcher(doc);
			if(m.find()){
				return true;
			}
		}
		return false;
	}

	/**
	 * 匹配后处理
	 */
	private void doMatch(String robotNo, String word, String entityId) {
		String string = RedisUtil.getValue(robotNo + "robot_next_question");
		RedisUtil.setExpire(robotNo + "robot_next_question", CACHE_TIME, string+"，"+word);
		RedisUtil.del(robotNo + "robot_up_entity_id");
		entranceEntityMap.remove(entityId);
	}


	/**
	 * 清理缓存
	 */
	private void removeCache(String robotNo) {
		RedisUtil.del(robotNo + "robot_entrance_entity_cache");
		RedisUtil.del(robotNo + "robot_up_entity_id");
		RedisUtil.del(robotNo + "robot_next_question");
	}


	/**
	 * 校验是否有类似问答
	 */
	@Override
	public List<String> verify(TNewFaqDO tNewFaq){
		List<String> repetitionList = new ArrayList<>();

		Long userId = ShiroUtils.getUserId();

		initQuestionMap(null,userId);
		//修改时不对所修改的校验
		Long faqId = tNewFaq.getId();
		String id = null;
		if(faqId != null){
			id = faqId.toString();
		}
		if(StringUtils.isNotEmpty(id)){
			List<String> keyList = new ArrayList<>();
			for(Map.Entry<String,String> entry : stringMap.entrySet()){

				if(id.equals(entry.getValue())){
					keyList.add(entry.getKey());
				}
			}
			if(keyList.size() > 0){
				for(String key : keyList){
					stringMap.remove(key);
				}
			}
		}

		double matchValue = 0.74;
		repetitionList.add(tNewFaq.getQuestion());
		String[] strings = tNewFaq.getQuestions().split("\\r\\n");
		for (String s : strings) {
			if (StringUtils.isNotEmpty(s)) {
				repetitionList.add(s);
			}
		}
		Map<Long,Map<String, Double>> mapMap = new HashMap<>();
		if(repetitionList.size() > 0){
			for(String repetitionQuestion : repetitionList){
				Map<Long,Map<String, Double>> mapMap2 = compare(repetitionQuestion, stringMap,matchValue);
				mapMap.putAll(mapMap2);
			}
		}
		Map<String, Double> map = compareMatch(mapMap);
		Map<String,Double> mapSort = MapUtils.doubleMapSort(map);
		Iterator<Map.Entry<String,Double>> it = mapSort.entrySet().iterator();

		repetitionList.clear();
		int count = 0;
		while (it.hasNext()){
			Map.Entry<String,Double> entry = it.next();
			String ques = entry.getKey();
			if(StringUtils.isNotEmpty(ques)){
				count++;
				repetitionList.add(ques + " ## 相识度："+ entry.getValue() * 100 +"% ##");
				if(count == 5){
					break;
				}
			}
		}
		return repetitionList;
	}



}
