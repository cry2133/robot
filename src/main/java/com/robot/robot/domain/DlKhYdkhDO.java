package com.robot.robot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 该表存放用电客户和考核户、违约窃电黑户的基本信息 --来源回流库 （客户用电考核）
1、用电客户定义：依法与供电企业建立供用电关系的组织或个人
 * 
 * @author yobi
 * @email ***
 * @date 2018-03-29 17:44:46
 */
public class DlKhYdkhDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//用电客户的唯一编号，参见南网编码规范
	private String yhbh;
	//客户的唯一标识
	private String khbh;
	//用户的名称，一般等于客户实体中的客户名称。
	private String yhmc;
	//用电客户的用电地址（不可编辑，来源于结构化地址实体）
	private String yddz;
	//通过信誉评估评估出来的用电客户的信誉等级：最高信誉度等级，较高信誉度等级，高信誉度等级，中最高信誉度等级，中较高信誉度等级等
	private String xydjdm;
	//通过信誉评估评估出来的用电客户客户的信誉分数
	private BigDecimal xyfz;
	//通过价值评估评出出来的用电客户客户的价值等级：最高价值等级，较高价值等级，高价值等级，中最高价值等级，中较高价值等级等
	private String jzdjdm;
	//通过风险评估评估出的用电客户的风险等级：最高风险等级，较高风险等级，高风险等级，中最高风险等级，中较高风险等级等
	private String fxdjdm;
	//用电客户的用电类别代码
	private String ydlbdm;
	//用电客户的电压等级代码
	private String dydjdm;
	//用电客户的行业分类代码,引用国标GB/T 4754-2002
	private String hyfldm;
	//主计量方式：1高供高计、2高供低计、3低供低计(是否需展示？)
	private String jlfsdm;
	//用户一种常用的分类方式，方便用户的管理  01 公变用户，02 专变客户，03 线损考核，04 台区考核、05、购电
	private String yhlbdm;
	//供电单位编码
	private String gddwbm;
	//用电客户所属的抄表区段。
	private String cbqdbh;
	//存储客户提供的自己熟悉的一串标识码，客户通过各种服务渠道可以通过这个查询号来查询自己用电的信息，如客户有多个用电地址，可提供不同的查询号
	private String zdycxh;
	//原用户编号，用于系统升级的时候用户编号重新编号可以在一段时间内继续使用原用户编号查询用户信息
	private String yyhbh;
	//合同约定的本用户的容量
	private BigDecimal htrl;
	//用电客户正在使用的合同容量，如暂停客户，在暂停期间其运行容量等于合同容量减去已暂停的容量
	private BigDecimal yxrl;
	//用电客户的生产班次分类：单班，二班，三班，连续生产
            
	private String scbcdm;
	//负荷的重要程度分类：一类，二类，三类
            
	private String fhxzdm;
	//依据国家最新的高耗能行业划分
	private String ghnhylbdm;
	//周休日通过数字连续表示周休哪几天，类似于飞机航班日期表示，如1.2.3,表示星期一星期二和星期三休息。
            
	private String cxr;
	//电子用户档案的首次建立日期
	private Date lhrq;
	//用户的首次送电日期
	private Date sdrq;
	//销户业务信息归档的日期
	private Date xhrq;
	//临时用电客户约定的用电到期日期
	private Date lsyddqrq;
	//表示是否是临时用电的用电客户，且属于哪种临时用电 01 装表临时用电，02 无表临时用电，03 非临时用电
	private String lsydbz;
	//用电客户的状态说明，说明客户是否处于业扩变更中或已销户 ：正常用电客户，当前新装客户，当前变更客户，已销户客户。
	private String yhztdm;
	//检查周期(单位：月)：用于存放客户检查周期信息，便于周期检查计划制定时，获取参数。
	private BigDecimal ydjczq;
	//用电检查上次周期检查日期
	private Date scjcrq;
	//检查区段标识(增加)
	private String jcqdbs;
	//停电标志：0 已停电  1 未停电，反映客户当前是否处于停电状态
	private String tdbz;
	//标识客户是否是转供相关客户，如果涉及转供，是属于转供户还是被转供户：无转供，转供户，被转供户
            
	private String zglxdm;
	//用户所在的地区编码，用于分区。
	private String dqbm;
	//电源类型代码(单电源单回路、单电源双回路、双电源同站双回路、双电源异站双回路)
	private String dylxdm;
	//电源之间的联锁方式 01 电气联锁、02 机械联锁、03 机械和电气联锁
	private String dylsfsdm;
	//电源之间的切换方式  01 手动、02 自动
	private String dyqhfsdm;
	//电源之间联锁装置的位置
	private String dylszzwz;
	//是否自备电源（是、否）
	private String zbdybz;
	//受电点的自备电源的闭锁方式代码：机械，电磁，电子，九线闸，其它
            
	private String zbdybsfsdm;
	//自备电源的总容量
	private BigDecimal zbdyrl;
	//是否谐波用户（是、否）
	private String xbyhbz;
	//是否冲击负荷用户（是、否）
	private String cjfhyhbz;
	//高可靠性标志（1是、0否）
	private String gkkxbz;
	//行政区域(所属村用该字段区分)
	private String xzqydm;
	//城乡标志（城市、乡村）
	private String cxdm;
	//所在楼层
	private BigDecimal szlc;
	//预付费类型（预售电、预付费、预收、非预付）
	private String yfflxdm;
	//方便收费操作，用户间建立的松散的缴费关系的标识，可根据此编号缴费，系统显示该编号的所有用户欠费记录，但用户间不能互相共用余额(是否放在结算户上？)
	private String lsjfgxh;
	//分管检查人员的人员标识，多个人员用';'分开。(是否放用检？删除)
	private String jcrybs;
	//定义用户一个抄表区段的抄表顺序
	private BigDecimal cbsxh;
	//单位通邮地址，业扩的作业知道书表单需要
	private String dwtydz;
	//邮政编码，业务的作业指导书表单需要
	private String yzbm;
	//传真号码，业扩的作业指导书表单需要
	private String czhm;
	//客户身份(业主、租户)，业扩的作业指导书表单需要
	private String khsfdm;
	//客服使用，对该用户进行分群，分为：重要客户、大客户、重点关注客户、居民客户、其它客户等五群客户。
	private String khfqbz;
	//客服使用，记录用户对口服务的客户经理
	private String khjlbs;
	//是否有自备电厂，默认为“否”
	private String sfyzbdc;
	//自备电厂容量，现场勘查时录入
	private BigDecimal zbdcrl;
	//保障房标志，受理时录入
	private String bzfbz;
	//保障房总户数，受理时录入
	private BigDecimal bzfzhs;
	//保障房总面积，受理时录入
	private BigDecimal bzfzmj;
	//保障房总容量，受理时录入
	private BigDecimal bzfzrl;
	//数据创建时间
	private Date cjsj;
	//数据最近一次变更时间
	private Date czsj;
	//抄表周期(0_不抄表,1_每月一次抄表,2_单月抄表,3_双月抄表,4_每月多次抄表,5_不定期抄表)
	private String cbzq;
	//阶梯类型
	private String jtlx;
	//村：农村电费公布榜需要
	private String cdm;
	//三重项目标志（是、否）
	private String szxmbz;
	//数据资源管理平台变更时间
	private Date sjzybgsj;
	//用电检查上次专项检查日期
	private Date ydjcsczxjcrq;
	//规划容量
	private BigDecimal ghrl;
	//补收终点时间
	private Date bszdsj;
	//暂停期满时间点
	private Date ztqmsj;
	//费控模式，包括：远程、本地、卡表
	private String fkmsdm;
	//付费模式，包括：后付，预付
	private String ffmsdm;
	//是否允许停电标志，包括：是、否
	private String sfyxtdbz;
	//停电类型代码，包括：自动、手动
	private String tdlxdm;
	//复电方式代码，包括：远程、复电卡等
	private String fdfsdm;
	//经度
	private String jd;
	//纬度
	private String wd;
	//是否0度户标志
	private String sfldhbz;
	//用电客户所属市场交易用户标志
	private String scjyyhbz;
	//用户报装属性：01统一安装 02零散安装
	private String yhbzsxdm;

	/**
	 * 设置：用电客户的唯一编号，参见南网编码规范
	 */
	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}
	/**
	 * 获取：用电客户的唯一编号，参见南网编码规范
	 */
	public String getYhbh() {
		return yhbh;
	}
	/**
	 * 设置：客户的唯一标识
	 */
	public void setKhbh(String khbh) {
		this.khbh = khbh;
	}
	/**
	 * 获取：客户的唯一标识
	 */
	public String getKhbh() {
		return khbh;
	}
	/**
	 * 设置：用户的名称，一般等于客户实体中的客户名称。
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	/**
	 * 获取：用户的名称，一般等于客户实体中的客户名称。
	 */
	public String getYhmc() {
		return yhmc;
	}
	/**
	 * 设置：用电客户的用电地址（不可编辑，来源于结构化地址实体）
	 */
	public void setYddz(String yddz) {
		this.yddz = yddz;
	}
	/**
	 * 获取：用电客户的用电地址（不可编辑，来源于结构化地址实体）
	 */
	public String getYddz() {
		return yddz;
	}
	/**
	 * 设置：通过信誉评估评估出来的用电客户的信誉等级：最高信誉度等级，较高信誉度等级，高信誉度等级，中最高信誉度等级，中较高信誉度等级等
	 */
	public void setXydjdm(String xydjdm) {
		this.xydjdm = xydjdm;
	}
	/**
	 * 获取：通过信誉评估评估出来的用电客户的信誉等级：最高信誉度等级，较高信誉度等级，高信誉度等级，中最高信誉度等级，中较高信誉度等级等
	 */
	public String getXydjdm() {
		return xydjdm;
	}
	/**
	 * 设置：通过信誉评估评估出来的用电客户客户的信誉分数
	 */
	public void setXyfz(BigDecimal xyfz) {
		this.xyfz = xyfz;
	}
	/**
	 * 获取：通过信誉评估评估出来的用电客户客户的信誉分数
	 */
	public BigDecimal getXyfz() {
		return xyfz;
	}
	/**
	 * 设置：通过价值评估评出出来的用电客户客户的价值等级：最高价值等级，较高价值等级，高价值等级，中最高价值等级，中较高价值等级等
	 */
	public void setJzdjdm(String jzdjdm) {
		this.jzdjdm = jzdjdm;
	}
	/**
	 * 获取：通过价值评估评出出来的用电客户客户的价值等级：最高价值等级，较高价值等级，高价值等级，中最高价值等级，中较高价值等级等
	 */
	public String getJzdjdm() {
		return jzdjdm;
	}
	/**
	 * 设置：通过风险评估评估出的用电客户的风险等级：最高风险等级，较高风险等级，高风险等级，中最高风险等级，中较高风险等级等
	 */
	public void setFxdjdm(String fxdjdm) {
		this.fxdjdm = fxdjdm;
	}
	/**
	 * 获取：通过风险评估评估出的用电客户的风险等级：最高风险等级，较高风险等级，高风险等级，中最高风险等级，中较高风险等级等
	 */
	public String getFxdjdm() {
		return fxdjdm;
	}
	/**
	 * 设置：用电客户的用电类别代码
	 */
	public void setYdlbdm(String ydlbdm) {
		this.ydlbdm = ydlbdm;
	}
	/**
	 * 获取：用电客户的用电类别代码
	 */
	public String getYdlbdm() {
		return ydlbdm;
	}
	/**
	 * 设置：用电客户的电压等级代码
	 */
	public void setDydjdm(String dydjdm) {
		this.dydjdm = dydjdm;
	}
	/**
	 * 获取：用电客户的电压等级代码
	 */
	public String getDydjdm() {
		return dydjdm;
	}
	/**
	 * 设置：用电客户的行业分类代码,引用国标GB/T 4754-2002
	 */
	public void setHyfldm(String hyfldm) {
		this.hyfldm = hyfldm;
	}
	/**
	 * 获取：用电客户的行业分类代码,引用国标GB/T 4754-2002
	 */
	public String getHyfldm() {
		return hyfldm;
	}
	/**
	 * 设置：主计量方式：1高供高计、2高供低计、3低供低计(是否需展示？)
	 */
	public void setJlfsdm(String jlfsdm) {
		this.jlfsdm = jlfsdm;
	}
	/**
	 * 获取：主计量方式：1高供高计、2高供低计、3低供低计(是否需展示？)
	 */
	public String getJlfsdm() {
		return jlfsdm;
	}
	/**
	 * 设置：用户一种常用的分类方式，方便用户的管理
            01 公变用户，02 专变客户，03 线损考核，04 台区考核、05、购电
	 */
	public void setYhlbdm(String yhlbdm) {
		this.yhlbdm = yhlbdm;
	}
	/**
	 * 获取：用户一种常用的分类方式，方便用户的管理
            01 公变用户，02 专变客户，03 线损考核，04 台区考核、05、购电
	 */
	public String getYhlbdm() {
		return yhlbdm;
	}
	/**
	 * 设置：供电单位编码
	 */
	public void setGddwbm(String gddwbm) {
		this.gddwbm = gddwbm;
	}
	/**
	 * 获取：供电单位编码
	 */
	public String getGddwbm() {
		return gddwbm;
	}
	/**
	 * 设置：用电客户所属的抄表区段。
	 */
	public void setCbqdbh(String cbqdbh) {
		this.cbqdbh = cbqdbh;
	}
	/**
	 * 获取：用电客户所属的抄表区段。
	 */
	public String getCbqdbh() {
		return cbqdbh;
	}
	/**
	 * 设置：存储客户提供的自己熟悉的一串标识码，客户通过各种服务渠道可以通过这个查询号来查询自己用电的信息，如客户有多个用电地址，可提供不同的查询号
	 */
	public void setZdycxh(String zdycxh) {
		this.zdycxh = zdycxh;
	}
	/**
	 * 获取：存储客户提供的自己熟悉的一串标识码，客户通过各种服务渠道可以通过这个查询号来查询自己用电的信息，如客户有多个用电地址，可提供不同的查询号
	 */
	public String getZdycxh() {
		return zdycxh;
	}
	/**
	 * 设置：原用户编号，用于系统升级的时候用户编号重新编号可以在一段时间内继续使用原用户编号查询用户信息
	 */
	public void setYyhbh(String yyhbh) {
		this.yyhbh = yyhbh;
	}
	/**
	 * 获取：原用户编号，用于系统升级的时候用户编号重新编号可以在一段时间内继续使用原用户编号查询用户信息
	 */
	public String getYyhbh() {
		return yyhbh;
	}
	/**
	 * 设置：合同约定的本用户的容量
	 */
	public void setHtrl(BigDecimal htrl) {
		this.htrl = htrl;
	}
	/**
	 * 获取：合同约定的本用户的容量
	 */
	public BigDecimal getHtrl() {
		return htrl;
	}
	/**
	 * 设置：用电客户正在使用的合同容量，如暂停客户，在暂停期间其运行容量等于合同容量减去已暂停的容量
	 */
	public void setYxrl(BigDecimal yxrl) {
		this.yxrl = yxrl;
	}
	/**
	 * 获取：用电客户正在使用的合同容量，如暂停客户，在暂停期间其运行容量等于合同容量减去已暂停的容量
	 */
	public BigDecimal getYxrl() {
		return yxrl;
	}
	/**
	 * 设置：用电客户的生产班次分类：单班，二班，三班，连续生产
            
	 */
	public void setScbcdm(String scbcdm) {
		this.scbcdm = scbcdm;
	}
	/**
	 * 获取：用电客户的生产班次分类：单班，二班，三班，连续生产
            
	 */
	public String getScbcdm() {
		return scbcdm;
	}
	/**
	 * 设置：负荷的重要程度分类：一类，二类，三类
            
	 */
	public void setFhxzdm(String fhxzdm) {
		this.fhxzdm = fhxzdm;
	}
	/**
	 * 获取：负荷的重要程度分类：一类，二类，三类
            
	 */
	public String getFhxzdm() {
		return fhxzdm;
	}
	/**
	 * 设置：依据国家最新的高耗能行业划分
	 */
	public void setGhnhylbdm(String ghnhylbdm) {
		this.ghnhylbdm = ghnhylbdm;
	}
	/**
	 * 获取：依据国家最新的高耗能行业划分
	 */
	public String getGhnhylbdm() {
		return ghnhylbdm;
	}
	/**
	 * 设置：周休日通过数字连续表示周休哪几天，类似于飞机航班日期表示，如1.2.3,表示星期一星期二和星期三休息。
            
	 */
	public void setCxr(String cxr) {
		this.cxr = cxr;
	}
	/**
	 * 获取：周休日通过数字连续表示周休哪几天，类似于飞机航班日期表示，如1.2.3,表示星期一星期二和星期三休息。
            
	 */
	public String getCxr() {
		return cxr;
	}
	/**
	 * 设置：电子用户档案的首次建立日期
	 */
	public void setLhrq(Date lhrq) {
		this.lhrq = lhrq;
	}
	/**
	 * 获取：电子用户档案的首次建立日期
	 */
	public Date getLhrq() {
		return lhrq;
	}
	/**
	 * 设置：用户的首次送电日期
	 */
	public void setSdrq(Date sdrq) {
		this.sdrq = sdrq;
	}
	/**
	 * 获取：用户的首次送电日期
	 */
	public Date getSdrq() {
		return sdrq;
	}
	/**
	 * 设置：销户业务信息归档的日期
	 */
	public void setXhrq(Date xhrq) {
		this.xhrq = xhrq;
	}
	/**
	 * 获取：销户业务信息归档的日期
	 */
	public Date getXhrq() {
		return xhrq;
	}
	/**
	 * 设置：临时用电客户约定的用电到期日期
	 */
	public void setLsyddqrq(Date lsyddqrq) {
		this.lsyddqrq = lsyddqrq;
	}
	/**
	 * 获取：临时用电客户约定的用电到期日期
	 */
	public Date getLsyddqrq() {
		return lsyddqrq;
	}
	/**
	 * 设置：表示是否是临时用电的用电客户，且属于哪种临时用电
            01 装表临时用电，02 无表临时用电，03 非临时用电
	 */
	public void setLsydbz(String lsydbz) {
		this.lsydbz = lsydbz;
	}
	/**
	 * 获取：表示是否是临时用电的用电客户，且属于哪种临时用电
            01 装表临时用电，02 无表临时用电，03 非临时用电
	 */
	public String getLsydbz() {
		return lsydbz;
	}
	/**
	 * 设置：用电客户的状态说明，说明客户是否处于业扩变更中或已销户
            ：正常用电客户，当前新装客户，当前变更客户，已销户客户。
            
	 */
	public void setYhztdm(String yhztdm) {
		this.yhztdm = yhztdm;
	}
	/**
	 * 获取：用电客户的状态说明，说明客户是否处于业扩变更中或已销户
            ：正常用电客户，当前新装客户，当前变更客户，已销户客户。
            
	 */
	public String getYhztdm() {
		return yhztdm;
	}
	/**
	 * 设置：检查周期(单位：月)：用于存放客户检查周期信息，便于周期检查计划制定时，获取参数。
	 */
	public void setYdjczq(BigDecimal ydjczq) {
		this.ydjczq = ydjczq;
	}
	/**
	 * 获取：检查周期(单位：月)：用于存放客户检查周期信息，便于周期检查计划制定时，获取参数。
	 */
	public BigDecimal getYdjczq() {
		return ydjczq;
	}
	/**
	 * 设置：用电检查上次周期检查日期
	 */
	public void setScjcrq(Date scjcrq) {
		this.scjcrq = scjcrq;
	}
	/**
	 * 获取：用电检查上次周期检查日期
	 */
	public Date getScjcrq() {
		return scjcrq;
	}
	/**
	 * 设置：检查区段标识(增加)
	 */
	public void setJcqdbs(String jcqdbs) {
		this.jcqdbs = jcqdbs;
	}
	/**
	 * 获取：检查区段标识(增加)
	 */
	public String getJcqdbs() {
		return jcqdbs;
	}
	/**
	 * 设置：停电标志：0 已停电  1 未停电，反映客户当前是否处于停电状态
	 */
	public void setTdbz(String tdbz) {
		this.tdbz = tdbz;
	}
	/**
	 * 获取：停电标志：0 已停电  1 未停电，反映客户当前是否处于停电状态
	 */
	public String getTdbz() {
		return tdbz;
	}
	/**
	 * 设置：标识客户是否是转供相关客户，如果涉及转供，是属于转供户还是被转供户：无转供，转供户，被转供户
            
	 */
	public void setZglxdm(String zglxdm) {
		this.zglxdm = zglxdm;
	}
	/**
	 * 获取：标识客户是否是转供相关客户，如果涉及转供，是属于转供户还是被转供户：无转供，转供户，被转供户
            
	 */
	public String getZglxdm() {
		return zglxdm;
	}
	/**
	 * 设置：用户所在的地区编码，用于分区。
	 */
	public void setDqbm(String dqbm) {
		this.dqbm = dqbm;
	}
	/**
	 * 获取：用户所在的地区编码，用于分区。
	 */
	public String getDqbm() {
		return dqbm;
	}
	/**
	 * 设置：电源类型代码(单电源单回路、单电源双回路、双电源同站双回路、双电源异站双回路)
	 */
	public void setDylxdm(String dylxdm) {
		this.dylxdm = dylxdm;
	}
	/**
	 * 获取：电源类型代码(单电源单回路、单电源双回路、双电源同站双回路、双电源异站双回路)
	 */
	public String getDylxdm() {
		return dylxdm;
	}
	/**
	 * 设置：电源之间的联锁方式
            01 电气联锁、02 机械联锁、03 机械和电气联锁
	 */
	public void setDylsfsdm(String dylsfsdm) {
		this.dylsfsdm = dylsfsdm;
	}
	/**
	 * 获取：电源之间的联锁方式
            01 电气联锁、02 机械联锁、03 机械和电气联锁
	 */
	public String getDylsfsdm() {
		return dylsfsdm;
	}
	/**
	 * 设置：电源之间的切换方式
            01 手动、02 自动
	 */
	public void setDyqhfsdm(String dyqhfsdm) {
		this.dyqhfsdm = dyqhfsdm;
	}
	/**
	 * 获取：电源之间的切换方式
            01 手动、02 自动
	 */
	public String getDyqhfsdm() {
		return dyqhfsdm;
	}
	/**
	 * 设置：电源之间联锁装置的位置
	 */
	public void setDylszzwz(String dylszzwz) {
		this.dylszzwz = dylszzwz;
	}
	/**
	 * 获取：电源之间联锁装置的位置
	 */
	public String getDylszzwz() {
		return dylszzwz;
	}
	/**
	 * 设置：是否自备电源（是、否）
	 */
	public void setZbdybz(String zbdybz) {
		this.zbdybz = zbdybz;
	}
	/**
	 * 获取：是否自备电源（是、否）
	 */
	public String getZbdybz() {
		return zbdybz;
	}
	/**
	 * 设置：受电点的自备电源的闭锁方式代码：机械，电磁，电子，九线闸，其它
            
	 */
	public void setZbdybsfsdm(String zbdybsfsdm) {
		this.zbdybsfsdm = zbdybsfsdm;
	}
	/**
	 * 获取：受电点的自备电源的闭锁方式代码：机械，电磁，电子，九线闸，其它
            
	 */
	public String getZbdybsfsdm() {
		return zbdybsfsdm;
	}
	/**
	 * 设置：自备电源的总容量
	 */
	public void setZbdyrl(BigDecimal zbdyrl) {
		this.zbdyrl = zbdyrl;
	}
	/**
	 * 获取：自备电源的总容量
	 */
	public BigDecimal getZbdyrl() {
		return zbdyrl;
	}
	/**
	 * 设置：是否谐波用户（是、否）
	 */
	public void setXbyhbz(String xbyhbz) {
		this.xbyhbz = xbyhbz;
	}
	/**
	 * 获取：是否谐波用户（是、否）
	 */
	public String getXbyhbz() {
		return xbyhbz;
	}
	/**
	 * 设置：是否冲击负荷用户（是、否）
	 */
	public void setCjfhyhbz(String cjfhyhbz) {
		this.cjfhyhbz = cjfhyhbz;
	}
	/**
	 * 获取：是否冲击负荷用户（是、否）
	 */
	public String getCjfhyhbz() {
		return cjfhyhbz;
	}
	/**
	 * 设置：高可靠性标志（1是、0否）
	 */
	public void setGkkxbz(String gkkxbz) {
		this.gkkxbz = gkkxbz;
	}
	/**
	 * 获取：高可靠性标志（1是、0否）
	 */
	public String getGkkxbz() {
		return gkkxbz;
	}
	/**
	 * 设置：行政区域(所属村用该字段区分)
	 */
	public void setXzqydm(String xzqydm) {
		this.xzqydm = xzqydm;
	}
	/**
	 * 获取：行政区域(所属村用该字段区分)
	 */
	public String getXzqydm() {
		return xzqydm;
	}
	/**
	 * 设置：城乡标志（城市、乡村）
	 */
	public void setCxdm(String cxdm) {
		this.cxdm = cxdm;
	}
	/**
	 * 获取：城乡标志（城市、乡村）
	 */
	public String getCxdm() {
		return cxdm;
	}
	/**
	 * 设置：所在楼层
	 */
	public void setSzlc(BigDecimal szlc) {
		this.szlc = szlc;
	}
	/**
	 * 获取：所在楼层
	 */
	public BigDecimal getSzlc() {
		return szlc;
	}
	/**
	 * 设置：预付费类型（预售电、预付费、预收、非预付）
	 */
	public void setYfflxdm(String yfflxdm) {
		this.yfflxdm = yfflxdm;
	}
	/**
	 * 获取：预付费类型（预售电、预付费、预收、非预付）
	 */
	public String getYfflxdm() {
		return yfflxdm;
	}
	/**
	 * 设置：方便收费操作，用户间建立的松散的缴费关系的标识，可根据此编号缴费，系统显示该编号的所有用户欠费记录，但用户间不能互相共用余额(是否放在结算户上？)
	 */
	public void setLsjfgxh(String lsjfgxh) {
		this.lsjfgxh = lsjfgxh;
	}
	/**
	 * 获取：方便收费操作，用户间建立的松散的缴费关系的标识，可根据此编号缴费，系统显示该编号的所有用户欠费记录，但用户间不能互相共用余额(是否放在结算户上？)
	 */
	public String getLsjfgxh() {
		return lsjfgxh;
	}
	/**
	 * 设置：分管检查人员的人员标识，多个人员用';'分开。(是否放用检？删除)
	 */
	public void setJcrybs(String jcrybs) {
		this.jcrybs = jcrybs;
	}
	/**
	 * 获取：分管检查人员的人员标识，多个人员用';'分开。(是否放用检？删除)
	 */
	public String getJcrybs() {
		return jcrybs;
	}
	/**
	 * 设置：定义用户一个抄表区段的抄表顺序
	 */
	public void setCbsxh(BigDecimal cbsxh) {
		this.cbsxh = cbsxh;
	}
	/**
	 * 获取：定义用户一个抄表区段的抄表顺序
	 */
	public BigDecimal getCbsxh() {
		return cbsxh;
	}
	/**
	 * 设置：单位通邮地址，业扩的作业知道书表单需要
	 */
	public void setDwtydz(String dwtydz) {
		this.dwtydz = dwtydz;
	}
	/**
	 * 获取：单位通邮地址，业扩的作业知道书表单需要
	 */
	public String getDwtydz() {
		return dwtydz;
	}
	/**
	 * 设置：邮政编码，业务的作业指导书表单需要
	 */
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	/**
	 * 获取：邮政编码，业务的作业指导书表单需要
	 */
	public String getYzbm() {
		return yzbm;
	}
	/**
	 * 设置：传真号码，业扩的作业指导书表单需要
	 */
	public void setCzhm(String czhm) {
		this.czhm = czhm;
	}
	/**
	 * 获取：传真号码，业扩的作业指导书表单需要
	 */
	public String getCzhm() {
		return czhm;
	}
	/**
	 * 设置：客户身份(业主、租户)，业扩的作业指导书表单需要
	 */
	public void setKhsfdm(String khsfdm) {
		this.khsfdm = khsfdm;
	}
	/**
	 * 获取：客户身份(业主、租户)，业扩的作业指导书表单需要
	 */
	public String getKhsfdm() {
		return khsfdm;
	}
	/**
	 * 设置：客服使用，对该用户进行分群，分为：重要客户、大客户、重点关注客户、居民客户、其它客户等五群客户。
	 */
	public void setKhfqbz(String khfqbz) {
		this.khfqbz = khfqbz;
	}
	/**
	 * 获取：客服使用，对该用户进行分群，分为：重要客户、大客户、重点关注客户、居民客户、其它客户等五群客户。
	 */
	public String getKhfqbz() {
		return khfqbz;
	}
	/**
	 * 设置：客服使用，记录用户对口服务的客户经理
	 */
	public void setKhjlbs(String khjlbs) {
		this.khjlbs = khjlbs;
	}
	/**
	 * 获取：客服使用，记录用户对口服务的客户经理
	 */
	public String getKhjlbs() {
		return khjlbs;
	}
	/**
	 * 设置：是否有自备电厂，默认为“否”
	 */
	public void setSfyzbdc(String sfyzbdc) {
		this.sfyzbdc = sfyzbdc;
	}
	/**
	 * 获取：是否有自备电厂，默认为“否”
	 */
	public String getSfyzbdc() {
		return sfyzbdc;
	}
	/**
	 * 设置：自备电厂容量，现场勘查时录入
	 */
	public void setZbdcrl(BigDecimal zbdcrl) {
		this.zbdcrl = zbdcrl;
	}
	/**
	 * 获取：自备电厂容量，现场勘查时录入
	 */
	public BigDecimal getZbdcrl() {
		return zbdcrl;
	}
	/**
	 * 设置：保障房标志，受理时录入
	 */
	public void setBzfbz(String bzfbz) {
		this.bzfbz = bzfbz;
	}
	/**
	 * 获取：保障房标志，受理时录入
	 */
	public String getBzfbz() {
		return bzfbz;
	}
	/**
	 * 设置：保障房总户数，受理时录入
	 */
	public void setBzfzhs(BigDecimal bzfzhs) {
		this.bzfzhs = bzfzhs;
	}
	/**
	 * 获取：保障房总户数，受理时录入
	 */
	public BigDecimal getBzfzhs() {
		return bzfzhs;
	}
	/**
	 * 设置：保障房总面积，受理时录入
	 */
	public void setBzfzmj(BigDecimal bzfzmj) {
		this.bzfzmj = bzfzmj;
	}
	/**
	 * 获取：保障房总面积，受理时录入
	 */
	public BigDecimal getBzfzmj() {
		return bzfzmj;
	}
	/**
	 * 设置：保障房总容量，受理时录入
	 */
	public void setBzfzrl(BigDecimal bzfzrl) {
		this.bzfzrl = bzfzrl;
	}
	/**
	 * 获取：保障房总容量，受理时录入
	 */
	public BigDecimal getBzfzrl() {
		return bzfzrl;
	}
	/**
	 * 设置：数据创建时间
	 */
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	/**
	 * 获取：数据创建时间
	 */
	public Date getCjsj() {
		return cjsj;
	}
	/**
	 * 设置：数据最近一次变更时间
	 */
	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}
	/**
	 * 获取：数据最近一次变更时间
	 */
	public Date getCzsj() {
		return czsj;
	}
	/**
	 * 设置：抄表周期(0_不抄表,1_每月一次抄表,2_单月抄表,3_双月抄表,4_每月多次抄表,5_不定期抄表)
	 */
	public void setCbzq(String cbzq) {
		this.cbzq = cbzq;
	}
	/**
	 * 获取：抄表周期(0_不抄表,1_每月一次抄表,2_单月抄表,3_双月抄表,4_每月多次抄表,5_不定期抄表)
	 */
	public String getCbzq() {
		return cbzq;
	}
	/**
	 * 设置：阶梯类型
	 */
	public void setJtlx(String jtlx) {
		this.jtlx = jtlx;
	}
	/**
	 * 获取：阶梯类型
	 */
	public String getJtlx() {
		return jtlx;
	}
	/**
	 * 设置：村：农村电费公布榜需要
	 */
	public void setCdm(String cdm) {
		this.cdm = cdm;
	}
	/**
	 * 获取：村：农村电费公布榜需要
	 */
	public String getCdm() {
		return cdm;
	}
	/**
	 * 设置：三重项目标志（是、否）
	 */
	public void setSzxmbz(String szxmbz) {
		this.szxmbz = szxmbz;
	}
	/**
	 * 获取：三重项目标志（是、否）
	 */
	public String getSzxmbz() {
		return szxmbz;
	}
	/**
	 * 设置：数据资源管理平台变更时间
	 */
	public void setSjzybgsj(Date sjzybgsj) {
		this.sjzybgsj = sjzybgsj;
	}
	/**
	 * 获取：数据资源管理平台变更时间
	 */
	public Date getSjzybgsj() {
		return sjzybgsj;
	}
	/**
	 * 设置：用电检查上次专项检查日期
	 */
	public void setYdjcsczxjcrq(Date ydjcsczxjcrq) {
		this.ydjcsczxjcrq = ydjcsczxjcrq;
	}
	/**
	 * 获取：用电检查上次专项检查日期
	 */
	public Date getYdjcsczxjcrq() {
		return ydjcsczxjcrq;
	}
	/**
	 * 设置：规划容量
	 */
	public void setGhrl(BigDecimal ghrl) {
		this.ghrl = ghrl;
	}
	/**
	 * 获取：规划容量
	 */
	public BigDecimal getGhrl() {
		return ghrl;
	}
	/**
	 * 设置：补收终点时间
	 */
	public void setBszdsj(Date bszdsj) {
		this.bszdsj = bszdsj;
	}
	/**
	 * 获取：补收终点时间
	 */
	public Date getBszdsj() {
		return bszdsj;
	}
	/**
	 * 设置：暂停期满时间点
	 */
	public void setZtqmsj(Date ztqmsj) {
		this.ztqmsj = ztqmsj;
	}
	/**
	 * 获取：暂停期满时间点
	 */
	public Date getZtqmsj() {
		return ztqmsj;
	}
	/**
	 * 设置：费控模式，包括：远程、本地、卡表
	 */
	public void setFkmsdm(String fkmsdm) {
		this.fkmsdm = fkmsdm;
	}
	/**
	 * 获取：费控模式，包括：远程、本地、卡表
	 */
	public String getFkmsdm() {
		return fkmsdm;
	}
	/**
	 * 设置：付费模式，包括：后付，预付
	 */
	public void setFfmsdm(String ffmsdm) {
		this.ffmsdm = ffmsdm;
	}
	/**
	 * 获取：付费模式，包括：后付，预付
	 */
	public String getFfmsdm() {
		return ffmsdm;
	}
	/**
	 * 设置：是否允许停电标志，包括：是、否
	 */
	public void setSfyxtdbz(String sfyxtdbz) {
		this.sfyxtdbz = sfyxtdbz;
	}
	/**
	 * 获取：是否允许停电标志，包括：是、否
	 */
	public String getSfyxtdbz() {
		return sfyxtdbz;
	}
	/**
	 * 设置：停电类型代码，包括：自动、手动
	 */
	public void setTdlxdm(String tdlxdm) {
		this.tdlxdm = tdlxdm;
	}
	/**
	 * 获取：停电类型代码，包括：自动、手动
	 */
	public String getTdlxdm() {
		return tdlxdm;
	}
	/**
	 * 设置：复电方式代码，包括：远程、复电卡等
	 */
	public void setFdfsdm(String fdfsdm) {
		this.fdfsdm = fdfsdm;
	}
	/**
	 * 获取：复电方式代码，包括：远程、复电卡等
	 */
	public String getFdfsdm() {
		return fdfsdm;
	}
	/**
	 * 设置：经度
	 */
	public void setJd(String jd) {
		this.jd = jd;
	}
	/**
	 * 获取：经度
	 */
	public String getJd() {
		return jd;
	}
	/**
	 * 设置：纬度
	 */
	public void setWd(String wd) {
		this.wd = wd;
	}
	/**
	 * 获取：纬度
	 */
	public String getWd() {
		return wd;
	}
	/**
	 * 设置：是否0度户标志
	 */
	public void setSfldhbz(String sfldhbz) {
		this.sfldhbz = sfldhbz;
	}
	/**
	 * 获取：是否0度户标志
	 */
	public String getSfldhbz() {
		return sfldhbz;
	}
	/**
	 * 设置：用电客户所属市场交易用户标志
	 */
	public void setScjyyhbz(String scjyyhbz) {
		this.scjyyhbz = scjyyhbz;
	}
	/**
	 * 获取：用电客户所属市场交易用户标志
	 */
	public String getScjyyhbz() {
		return scjyyhbz;
	}
	/**
	 * 设置：用户报装属性：01统一安装 02零散安装
	 */
	public void setYhbzsxdm(String yhbzsxdm) {
		this.yhbzsxdm = yhbzsxdm;
	}
	/**
	 * 获取：用户报装属性：01统一安装 02零散安装
	 */
	public String getYhbzsxdm() {
		return yhbzsxdm;
	}
}
