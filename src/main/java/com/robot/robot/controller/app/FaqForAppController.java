package com.robot.robot.controller.app;


import java.util.Date;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.common.utils.RequestUtil;
import com.robot.common.utils.TuLingUtils;
import com.robot.common.utils.XFyunUtils;
import com.robot.common.domain.TuLingReturn;
import com.robot.common.domain.XFyunReturn;
import com.robot.robot.controller.app.bean.FaqRequestBean;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.controller.app.common.AppConstants;
import com.robot.robot.domain.TFaqDO;
import com.robot.robot.domain.TNonexistentFaqDO;
import com.robot.robot.service.TFaqService;
import com.robot.robot.service.TKeywordService;
import com.robot.robot.service.TNonexistentFaqService;
import com.robot.robot.utils.SessionUtil;


@Controller
@RequestMapping("/app/faq")
public class FaqForAppController {
	public static Logger log = Logger.getLogger(FaqForAppController.class); 
	
	@Autowired
	private TFaqService tFaqService;
	@Autowired
	private TKeywordService tKeywordService;
	@Autowired
	private TNonexistentFaqService tNonexistentFaqService;
	/**
	 * 智能查询问答
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/searchAnswer")
	@ResponseBody
	public ResponseBean searchAnswer(HttpServletRequest request) throws Exception{
		String content=RequestUtil.getString(request, "content");
		String robotNo=RequestUtil.getString(request, "robotNo");

		//退出即清空session缓存
		if(content.equals(AppConstants.OUT)){
			SessionUtil.removeSessionAttribute(robotNo);
		}
		String parentId  = SessionUtil.getAttr(robotNo);		//获取当前机械人语音是否有上级问答
		log.info("语音回答上级 parentId:"+parentId);
		
		log.info("content:"+content +",robotNo:"+robotNo);
		
		if(StringUtils.isEmpty(content)){
			return ResponseBean.fail("参数格式不对");
		}
		
		ResponseBean res =new ResponseBean();
		if(StringUtils.isNotEmpty(parentId)){
			int countParent=tFaqService.countParent(parentId);
			if(countParent>0){
				//大于0即代表有下级问答
				res =parentSearch(content,robotNo,parentId);
			}else{
				//0即代表没有下级问答
				res =commonSearch(content,robotNo);
			}
		}else{
			res =commonSearch(content,robotNo);
		}
		return res;
		
		
		/***********************注释为原回答逻辑*******************************/
//		String content=RequestUtil.getString(request, "content");
//		log.info("content:"+content);
//		
//		if(StringUtils.isEmpty(content)){
//			return ResponseBean.fail("参数格式不对");
//		}
//		//优先通过问答查询答案
//		TFaqDO tFaq=new TFaqDO();
//		tFaq=tFaqService.getLikeByQuestion(content);
//		if(tFaq.getFaqId() != null){
//			FaqRequestBean faqBean=new FaqRequestBean();
//			faqBean.setQuestion(tFaq.getQuestion());
//			faqBean.setAnswer(tFaq.getAnswer());
//			
//			return ResponseBean.success(faqBean);
//		}	
//		
//		//关键字查询
//		String gc=tKeywordService.strReplace(content);
//		long faqId=tFaqService.searchFaqForApp(gc);
//		if(faqId!=0){//通过关键字查询答案
//			tFaq=tFaqService.get(faqId);
//			
//			FaqRequestBean faqBean=new FaqRequestBean();
//			faqBean.setQuestion(content);
//			faqBean.setAnswer(tFaq.getAnswer());
//			
//			return ResponseBean.success(faqBean);
//		}else{
////			tFaq=tFaqService.getLikeByQuestion(content);
////			if(tFaq.getFaqId() != null){
////				FaqRequestBean faqBean=new FaqRequestBean();
////				faqBean.setQuestion(tFaq.getQuestion());
////				faqBean.setAnswer(tFaq.getAnswer());
////				
////				return ResponseBean.success(faqBean);
////				
////			}else{
//				//查询不到答案记录问题
//				TuLingReturn tuLingReturn = TuLingUtils.postTalk(content);
//				String result = tuLingReturn.getText();
//				log.info("未找到答案,通过图灵寻找 question:"+content+",result:"+result);
//				
//				FaqRequestBean faqBean=new FaqRequestBean();
//				faqBean.setQuestion(content);
//				faqBean.setAnswer(result);
//				
//				if(tNonexistentFaqService.existQuestion(gc)){
//					TNonexistentFaqDO tNonexistentFaq=new TNonexistentFaqDO();
//					tNonexistentFaq.setQuestion(gc);
//					tNonexistentFaq.setAnswer(result);
//					tNonexistentFaq.setCreatetime(new Date());
//					tNonexistentFaqService.save(tNonexistentFaq);
//				}
//				
//				return ResponseBean.success(faqBean);
////			}
//			
//		}
		
	}

	
	public ResponseBean commonSearch(String content,String robotNo) throws Exception{
		
		TNonexistentFaqDO tNonexistentFaq=new TNonexistentFaqDO();
		FaqRequestBean faqBean=new FaqRequestBean();
		
		//优先通过问答查询答案
		TFaqDO tFaq=new TFaqDO();
		tFaq=tFaqService.getLikeByQuestion(content);
		if(tFaq.getFaqId() != null){
			faqBean.setQuestion(tFaq.getQuestion());
			faqBean.setAnswer(tFaq.getAnswer());
			
			SessionUtil.setRequestAttribute(robotNo, String.valueOf(tFaq.getFaqId()));
			return ResponseBean.success(faqBean);
		}	
			
		//关键字查询
		String gc=tKeywordService.strReplace(content);
		long faqId=tFaqService.searchFaqForApp(gc);
		if(faqId!=0){//通过关键字查询答案
			tFaq=tFaqService.get(faqId);
			
			faqBean.setQuestion(content);
			faqBean.setAnswer(tFaq.getAnswer());
			
			SessionUtil.setRequestAttribute(robotNo, String.valueOf(tFaq.getFaqId()));
			return ResponseBean.success(faqBean);
		}else{
			//本地知识库查询不到答案转<讯飞>记录问题
			XFyunReturn xfReturn = XFyunUtils.runChat(content);
			String result = xfReturn.getText();
			log.info("==========未找到答案,通过<讯飞>寻找 question:"+content+",result:"+result);
			
			if(result.isEmpty()){
				//<讯飞>查询不到答案转<图灵>记录问题
				TuLingReturn tuLingReturn = TuLingUtils.postTalk(content);
				String TLresult = tuLingReturn.getText();
				log.info("==========未找到答案,通过<图灵>寻找 question:"+content+",result:"+TLresult);
				
				
				faqBean.setQuestion(content);
				faqBean.setAnswer(TLresult);
				
				if(tNonexistentFaqService.existQuestion(gc)){
					tNonexistentFaq.setQuestion(gc);
					tNonexistentFaq.setAnswer(TLresult);
					tNonexistentFaq.setCreatetime(new Date());
					tNonexistentFaqService.save(tNonexistentFaq);
				}
				
				return ResponseBean.success(faqBean);
			}
			
			
			faqBean.setQuestion(content);
			faqBean.setAnswer(result);
			
			if(tNonexistentFaqService.existQuestion(gc)){
				tNonexistentFaq.setQuestion(gc);
				tNonexistentFaq.setAnswer(result);
				tNonexistentFaq.setCreatetime(new Date());
				tNonexistentFaqService.save(tNonexistentFaq);
			}
			
			return ResponseBean.success(faqBean);
		}
	}
	
	public ResponseBean parentSearch(String content,String robotNo,String parentId) throws Exception{
		
		TNonexistentFaqDO tNonexistentFaq=new TNonexistentFaqDO();
		FaqRequestBean faqBean=new FaqRequestBean();
		
		//优先通过问答查询答案
		TFaqDO tFaq=new TFaqDO();
		tFaq=tFaqService.getLikeByQuestionForParent(content, parentId);
		if(tFaq.getFaqId() != null){
			faqBean.setQuestion(tFaq.getQuestion());
			faqBean.setAnswer(tFaq.getAnswer());
			
			SessionUtil.setRequestAttribute(robotNo, String.valueOf(tFaq.getFaqId()));
			return ResponseBean.success(faqBean);
		}	
		
		//关键字查询
		String gc=tKeywordService.strReplace(content);
		long faqId=tFaqService.searchFaqForApp(gc);
		
		if(faqId!=0){//通过关键字查询答案
			tFaq=tFaqService.get(faqId);
			
			faqBean.setQuestion(content);
			faqBean.setAnswer(tFaq.getAnswer());
			
			SessionUtil.setRequestAttribute(robotNo, String.valueOf(tFaq.getFaqId()));
			return ResponseBean.success(faqBean);
		}else{
			//本地知识库查询不到答案转<讯飞>记录问题
			XFyunReturn xfReturn = XFyunUtils.runChat(content);
			String result = xfReturn.getText();
			
			if(result.isEmpty()){
				//<讯飞>查询不到答案转<图灵>记录问题
				TuLingReturn tuLingReturn = TuLingUtils.postTalk(content);
				String TLresult = tuLingReturn.getText();
				log.info("==========未找到答案,通过<图灵>寻找 question:"+content+",result:"+TLresult);
				
				
				faqBean.setQuestion(content);
				faqBean.setAnswer(TLresult);
				
				if(tNonexistentFaqService.existQuestion(gc)){
					tNonexistentFaq.setQuestion(gc);
					tNonexistentFaq.setAnswer(TLresult);
					tNonexistentFaq.setCreatetime(new Date());
					tNonexistentFaqService.save(tNonexistentFaq);
				}
				
				return ResponseBean.success(faqBean);
			}
			log.info("==========未找到答案,通过<讯飞>寻找 question:"+content+",result:"+result);
			
			faqBean.setQuestion(content);
			faqBean.setAnswer(result);
			
			if(tNonexistentFaqService.existQuestion(gc)){
				tNonexistentFaq.setQuestion(gc);
				tNonexistentFaq.setAnswer(result);
				tNonexistentFaq.setCreatetime(new Date());
				tNonexistentFaqService.save(tNonexistentFaq);
			}
			
			return ResponseBean.success(faqBean);
		}
	}
}
