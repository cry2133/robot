package com.robot;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.common.CommonSynonymDictionary;
import com.hankcs.hanlp.dictionary.common.CommonSynonymDictionary.SynonymItem;
import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.TraditionalChineseTokenizer;
import com.robot.common.utils.HanLPUtils;

public class HanLPtest {

	public static void main(String[] args) {
		
		HanLPtest hlp = new HanLPtest();
		//hlp.yyjl();
		//hlp.zntj();
		//hlp.gjz();
		//hlp.zd();
		//hlp.dx();
		//hlp.bz();
		//hlp.nlp();
		//hlp.SYFC();
		//hlp.FT();
		//hlp.JSCD();
		//hlp.N_ZDLJ();
		//hlp.ycjf();
		//hlp.vectors();
		
		System.out.println(HanLPUtils.getKeyword("只记得恍惚中跟你相恋", 2));
		
		System.out.println(HanLPUtils.getKeywordByNLPsegment("只记得恍惚中跟你相恋"));
	}
	
	
	
	public void yyjl(){
		/**
		 * 语义距离
		 * 可以方便地计算两个词汇之间y的距离，意义越相近，距离越小
		 */
		String[] wordArray = new String[]
		        {
		                "香蕉",
		                "苹果",
		                "白菜",
		                "水果",
		                "蔬菜",
		                "自行车",
		                "公交车",
		                "飞机",
		                "买",
		                "卖",
		                "购入",
		                "新年",
		                "春节",
		                "丢失",
		                "补办",
		                "办理",
		                "送给",
		                "寻找",
		                "孩子",
		                "教室",
		                "教师",
		                "会计",
		        };
		String[] wordArray2 = new String[]
		        {
		                "用电",
		                "报装",
		           
		        };
		for (String a : wordArray2){
		    for (String b : wordArray2){
		        System.out.println(a + "\t" + b + "\t之间的距离是\t" + CoreSynonymDictionary.distance(a, b));
		    }
		}
	}
	
	
	
	public void zntj(){
		/**
		 * 智能推荐--搜索引擎
		 * 也就是文本推荐(句子级别，从一系列句子中挑出与输入句子最相似的那一个)
		 * 在搜索引擎的输入框中，用户输入一个词，搜索引擎会联想出最合适的搜索词， HanLP 实现了类似的功能。
		 * 可以动态调节每种识别器的权重
		 */
		Suggester suggester = new Suggester();
		String[] titleArray =
		(
		        "威廉王子发表演说 呼吁保护野生动物\n" +
		        "《时代》年度人物最终入围名单出炉 普京马云入选\n" +
		        "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散\n" +
		        "日本保密法将正式生效 日媒指其损害国民知情权\n" +
		        "公司企业客户办理批量新装所需资料\n" +
		        "机关事业单位客户办理批量新装所需资料\n" +
		        "我要用电报装\n" +
		        "英报告说空气污染带来“公共健康危机”"
		).split("\\n");
		for (String title : titleArray)
		{
		    suggester.addSentence(title);
		}

		System.out.println(suggester.suggest("威廉发言", 1));     // 语义
		System.out.println(suggester.suggest("危机公共", 1));   // 字符
		System.out.println(suggester.suggest("我想用电报装", 1));   // 字符
		System.out.println(suggester.suggest("机关事业单位客户办理批量新装", 1));   // 字符
		System.out.println(suggester.suggest("mayun", 1));      // 拼音
	}
	
	
	
	public void gjz(){
		/**
		 * 关键词提取
		 * 句话调用，第一个参数指定文本，第二个参数指定需要提取几个关键字
		 * 内部采用 TextRankKeyword 实现，用户可以直接调用 TextRankKeyword.getKeywordList(document, size)
		 * 算法详解 《TextRank算法提取关键词的Java实现》
		 */
		String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";
		String content2 = "非居民办理用电报装有哪些流程？";
		List<String> keywordList = HanLP.extractKeyword(content2, 9);
		System.out.println("关键字有："+keywordList);
	}
	
	
	
	public void zd(){
		/**
		 * 自动摘要
		 * 同样是一句话调用，第一个参数指定文本，第二个参数指定需要提取几个句子
		 * 内部采用 TextRankSentence 实现，用户可以直接调用 TextRankSentence.getTopSentenceList(document, size)
		 */
		String document = "算法可大致分为基本算法、数据结构的算法、数论算法、计算几何的算法、图的算法、动态规划以及数值分析、加密算法、排序算法、检索算法、随机化算法、并行算法、厄米变形模型、随机森林算法。\n" +
			    "算法可以宽泛的分为三类，\n" +
			    "一，有限的确定性算法，这类算法在有限的一段时间内终止。他们可能要花很长时间来执行指定的任务，但仍将在一定的时间内终止。这类算法得出的结果常取决于输入值。\n" +
			    "二，有限的非确定算法，这类算法在有限的时间内终止。然而，对于一个（或一些）给定的数值，算法的结果并不是唯一的或确定的。\n" +
			    "三，无限的算法，是那些由于没有定义终止定义条件，或定义的条件无法由输入的数据满足而不终止运行的算法。通常，无限算法的产生是由于未能确定的定义终止条件。";
			List<String> sentenceList = HanLP.extractSummary(document, 3);
			System.out.println(sentenceList);
	}
	
	
	
	
	public void dx(){
		/**
		 * 短语提取
		 * 同样是一句话调用，第一个参数指定文本，第二个参数指定需要提取几个短语
		 * 内部采用 MutualInformationEntropyPhraseExtractor 实现，用户可以直接调用 MutualInformationEntropyPhraseExtractor.extractPhrase(text, size)
		 */
		String text = "算法工程师\n" +
		            "算法（Algorithm）是一系列解决问题的清晰指令，也就是说，能够对一定规范的输入，在有限时间内获得所要求的输出。如果一个算法有缺陷，或不适合于某个问题，执行这个算法将不会解决这个问题。不同的算法可能用不同的时间、空间或效率来完成同样的任务。一个算法的优劣可以用空间复杂度与时间复杂度来衡量。算法工程师就是利用算法处理事物的人。\n" +
		            "\n" +
		            "1职位简介\n" +
		            "算法工程师是一个非常高端的职位；\n" +
		            "专业要求：计算机、电子、通信、数学等相关专业；\n" +
		            "学历要求：本科及其以上的学历，大多数是硕士学历及其以上；\n" +
		            "语言要求：英语要求是熟练，基本上能阅读国外专业书刊；\n" +
		            "必须掌握计算机相关知识，熟练使用仿真工具MATLAB等，必须会一门编程语言。\n" +
		            "\n" +
		            "2研究方向\n" +
		            "视频算法工程师、图像处理算法工程师、音频算法工程师 通信基带算法工程师\n" +
		            "\n" +
		            "3目前国内外状况\n" +
		            "目前国内从事算法研究的工程师不少，但是高级算法工程师却很少，是一个非常紧缺的专业工程师。算法工程师根据研究领域来分主要有音频/视频算法处理、图像技术方面的二维信息算法处理和通信物理层、雷达信号处理、生物医学信号处理等领域的一维信息算法处理。\n" +
		            "在计算机音视频和图形图像技术等二维信息算法处理方面目前比较先进的视频处理算法：机器视觉成为此类算法研究的核心；另外还有2D转3D算法(2D-to-3D conversion)，去隔行算法(de-interlacing)，运动估计运动补偿算法(Motion estimation/Motion Compensation)，去噪算法(Noise Reduction)，缩放算法(scaling)，锐化处理算法(Sharpness)，超分辨率算法(Super Resolution),手势识别(gesture recognition),人脸识别(face recognition)。\n" +
		            "在通信物理层等一维信息领域目前常用的算法：无线领域的RRM、RTT，传送领域的调制解调、信道均衡、信号检测、网络优化、信号分解等。\n" +
		            "另外数据挖掘、互联网搜索算法也成为当今的热门方向。\n" +
		            "算法工程师逐渐往人工智能方向发展。";
		List<String> phraseList = HanLP.extractPhrase(text, 10);
		System.out.println(phraseList);	
	}
	
	
	public void bz(){
		/**
		 * 标准分词
		 * 标准分词是最常用的分词器，基于HMM-Viterbi实现，开启了中国人名识别和音译人名识别，调用方法如下:
		 * HanLP.segment 其实是对 StandardTokenizer.segment 的包装。
		 * HanLP中有一系列“开箱即用”的静态分词器，以 Tokenizer 结尾，在接下来的例子中会继续介绍。
		 */
		List<Term> termList = HanLP.segment("商品和服务");
		List<Term> termList2 = HanLP.segment("非居民办理用电报装有哪些流程？");
		System.out.println(termList2);
	}
	
	
	public void nlp(){
		/**
		 *NLP分词
		 *NLP分词 NLPTokenizer 会执行全部命名实体识别和词性标注。，调用方法如下:
		 *NLP分词 NLPTokenizer 会执行全部命名实体识别和词性标注。
		 *所以速度比标准分词慢，并且有误识别的情况。
		 */
		System.out.println(CustomDictionary.contains("用电报装"));
		System.out.println(CustomDictionary.contains("一户一表"));
		List<Term> termList2 = NLPTokenizer.segment("中国科学院计算技术研究所的宗成庆教授正在教授自然语言处理课程一户一表规定用电报装有");
		System.out.println(termList2);
	}
	
	
	public void SYFC(){
		/**
		 * 索引分词
		 * 索引分词 IndexTokenizer 是面向搜索引擎的分词器，能够对长词全切分，另外通过 term.offset 可以获取单词在文本中的偏移量。调用方法如下:
		 */
		List<Term> termList3 = IndexTokenizer.segment("主副食品");
		for (Term term : termList3)
		{
		    System.out.println(term + " [" + term.offset + ":" + (term.offset + term.word.length()) + "]");
		}
	}
	
	
	public void FT(){
		/**
		 * 繁体分词
		 * 繁体分词 TraditionalChineseTokenizer 可以直接对繁体进行分词，输出切分后的繁体词语。调用方法如下:
		 * 繁体分词也像标准分词一样支持命名实体识别。
		 */
		List<Term> termList4 = TraditionalChineseTokenizer.segment("大衛貝克漢不僅僅是名著名球員，球場以外，其妻為前辣妹合唱團成員維多利亞·碧咸，亦由於他擁有突出外表、百變髮型及正面的形象，以至自己品牌的男士香水等商品，及長期擔任運動品牌Adidas的代言人，因此對大眾傳播媒介和時尚界等方面都具很大的影響力，在足球圈外所獲得的認受程度可謂前所未見。");
		System.out.println(termList4);
	}
	
	
	public void JSCD(){
		/**
		 * 极速词典分词
		 * 极速分词是词典最长分词，速度极其快，精度一般。调用方法如下:
		 * 在i7上跑出了2000万字每秒的速度。
		 * 使用的算法是 《Aho Corasick自动机结合DoubleArrayTrie极速多模式匹配》
		 * 接下来介绍的分词器是由用户动态创建，使用场景不常见的分词器。
		 */
		String text = "江西鄱阳湖干枯，中国最大淡水湖变成大草原";
		System.out.println(SpeedTokenizer.segment(text));
		long start = System.currentTimeMillis();
		int pressure = 1000000;
		for (int i = 0; i < pressure; ++i)
		{
		    SpeedTokenizer.segment(text);
		}
		double costTime = (System.currentTimeMillis() - start) / (double)1000;
		System.out.printf("分词速度：%.2f字每秒", text.length() * pressure / costTime);
	}
	
	
	public void N_ZDLJ(){
		/**
		 * N-最短路径分词
		 * N最短路分词器 NShortSegment 比最短路分词器( DijkstraSegment )慢，但是效果稍微好一些，对命名实体识别能力更强。调用方法如下:
		 * 一般场景下最短路分词的精度已经足够，而且速度比N最短路分词器快几倍，请酌情选择。
		 */
		Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
		Segment shortestSegment = new ViterbiSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
		String[] testCase = new String[]{
		        "刘喜杰石国祥会见吴亚琴先进事迹报告团成员",
		};
		for (String sentence : testCase)
		{
		    System.out.println("N-最短分词：" + nShortSegment.seg(sentence) + "\n最短路分词：" + shortestSegment.seg(sentence));
		}
	}
	
	
	
	public void ycjf(){
		/**
		 * 依存句法分析（MaxEnt和神经网络句法模型需要-Xms1g -Xmx1g -Xmn512m）
		 * @author hankcs
		 */
		CoNLLSentence sentence = HanLP.parseDependency("徐先生还具体帮助他确定了把画雄鹰、松鼠和麻雀作为主攻目标。");
        System.out.println(sentence);
        // 可以方便地遍历它
        for (CoNLLWord word : sentence)
        {
            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
        // 也可以直接拿到数组，任意顺序或逆序遍历
        CoNLLWord[] wordArray = sentence.getWordArray();
        for (int i = wordArray.length - 1; i >= 0; i--)
        {
            CoNLLWord word = wordArray[i];
            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
        // 还可以直接遍历子树，从某棵子树的某个节点一路遍历到虚根
        CoNLLWord head = wordArray[12];
        while ((head = head.HEAD) != null)
        {
            if (head == CoNLLWord.ROOT) System.out.println(head.LEMMA);
            else System.out.printf("%s --(%s)--> ", head.LEMMA, head.DEPREL);
        }
	}
	
    public void vectors(){
    	Word2VecTrainer trainerBuilder = new Word2VecTrainer();
    	WordVectorModel wordVectorModel = trainerBuilder.train("C:/Users/lenovo/Desktop/HanLP/train/199801.txt", "C:/Users/lenovo/Desktop/HanLP/train/msr_vectors.txt");
//    	System.out.println(wordVectorModel.nearest("报装"));
//    	System.out.println(wordVectorModel.nearest("你好"));
//    	System.out.println(wordVectorModel.nearest("新闻"));
//    	System.out.println(wordVectorModel.similarity("高压", "用电"));
//    	System.out.println(wordVectorModel.similarity("高一口受理", "一户一表"));
//    	System.out.println(wordVectorModel.similarity("申请", "用电"));
//    	System.out.println(wordVectorModel.similarity("光伏", "办理"));
    	System.out.println(wordVectorModel.analogy("日本", "自民党", "共和党"));
//    	System.out.println(wordVectorModel.analogy("用电报装", "安装", "新装"));
    	
    }
    
	
}
