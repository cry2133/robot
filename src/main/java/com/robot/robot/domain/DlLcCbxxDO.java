package com.robot.robot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 根据抄表计划和运行电能表等客户档案信息通过抄表数据开放形成的抄表信息及计费电量的存储 -来源回流库 （抄表信息）
 * 
 * @author yobi
 * @email ***
 * @date 2018-03-29 17:44:46
 */
public class DlLcCbxxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//工作单编号
	private String gzdbh;
	//计量点编号
	private String jldbh;
	//运行电能表标识
	private String yxdnbbs;
	//示数类型代码(正向有功总、正向有功尖峰等)
	private String sslxdm;
	//抄表业务类别代码(0_正常抄表,1_非周期及业扩变更结算抄表,2_补抄,3_政策性退补,4_监抄,5_试算等)
	private String ywlbdm;
	//计量点序号
	private BigDecimal jldxh;
	//时段代码(冗余)
	private String sdlx;
	//抄表计划编号
	private String cbjhbh;
	//抄表区段编号
	private String cbqdbh;
	//计量点抄表顺序号
	private BigDecimal jldcbsxh;
	//抄表顺序号
	private BigDecimal cbsxh;
	//主副表标志(0_副表,1_主表)
	private String zfbbz;
	//电费年月
	private BigDecimal dfny;
	//本月抄表次数
	private BigDecimal bqcbcs;
	//应抄表次数
	private BigDecimal ycbcs;
	//冲正次数
	private BigDecimal czcs;
	//用户编号
	private String yhbh;
	//用户名称
	private String yhmc;
	//用电地址
	private String yddz;
	//计量点用途代码
	private String jldytdm;
	//资产编号
	private String zcbh;
	//出厂编号
	private String ccbh;
	//设备类型代码
	private String sblxdm;
	//上次表示数
	private BigDecimal scbss;
	//本次表示数
	private BigDecimal bcbss;
	//表示数差额
	private BigDecimal bssce;
	//表码位数
	private String bmws;
	//相位
	private String xw;
	//综合倍率
	private BigDecimal zhbl;
	//旧表电量
	private BigDecimal jbdl;
	//加减电量
	private BigDecimal jjdl;
	//预收电量
	private BigDecimal ysdl;
	//退补电量
	private BigDecimal tbdl;
	//上次预收电量
	private BigDecimal scysdl;
	//表计电量
	private BigDecimal bjdl;
	//上次抄表日期
	private Date sccbrq;
	//上次表计电量
	private BigDecimal scbjdl;
	//上上次表计电量
	private BigDecimal pjbjdl;
	//抄表标志代码(0_未抄,1_已抄)
	private String cbbzdm;
	//用户类别代码(用户一种常用的分类方式且方便用户的管理:1_公变用户,2_专变客户,3_线损考核,4_台区考核,5_购电)
	private String yhlbdm;
	//实际抄表方式代码(10_现场单户,11_手工(抄表卡),12_普通抄表器,13_远红外抄表器,20_现场集抄,21_普通抄表器集抄,22_远红外抄表器集抄,23_无线抄表器集抄,24_低压载波集抄,30_遥抄,31_负控遥抄,32_低压遥抄,33_配变遥抄,34_厂站遥抄,99_其它)
	private String sjcbfsdm;
	//抄表状态代码(0_正常,1_翻转,2_倒走等)
	private String cbztdm;
	//抄表异常代码(0_无,10_示数不平,11_箱烂,12_违约,13_无表,14_电表烧表,15_电表故障,16_电表编号不符,18_变比不符,19_估抄,20_漏抄)
	private String cbycdm;
	//供电单位编码
	private String gddwbm;
	//抄表人标识
	private String cbrbs;
	//抄表时间
	private Date cbsj;
	//冻结时间
	private Date djsj;
	//上次工作单编号
	private String scgzdbh;
	//单位编码
	private String dwbm;
	//地区编码
	private String dqbm;
	//数据创建时间
	private Date cjsj;
	//数据最近一次变更时间
	private Date czsj;
	//冲正年月
	private BigDecimal czny;
	//数据类型
	private String sjlx;
	//有效标志
	private String yxbz;
	//下载PDA标志
	private String xzpdabz;
	//原抄表号,用于记录电能表上的抄表号
	private String ycbh;
	//转外委抄表标志
	private String zwwcbbz;
	//相线，包括：单相、三相三线、三相四线
	private String xxdm;
	//计量点抄表方式代码
	private String jldcbfsdm;
	//自动抄表更新时间
	private Date zdcbgxsj;
	//远程抄表标识
	private String yccbbz;
	//计量前日0点表码
	private BigDecimal jlqrldbm;
	//计量当日0点表码
	private BigDecimal jldrldbm;
	//计量当日24点表码
	private BigDecimal jldresdbm;
	//计量次日24点表码
	private BigDecimal jlcresdbm;
	//实际抄表日期
	private Date sjcbrq;
	//远程自动化抄表存在补抄的情况，此字段标识获取的数据是否为计量系统补抄数据
	private String ycbcbz;

	/**
	 * 设置：工作单编号
	 */
	public void setGzdbh(String gzdbh) {
		this.gzdbh = gzdbh;
	}
	/**
	 * 获取：工作单编号
	 */
	public String getGzdbh() {
		return gzdbh;
	}
	/**
	 * 设置：计量点编号
	 */
	public void setJldbh(String jldbh) {
		this.jldbh = jldbh;
	}
	/**
	 * 获取：计量点编号
	 */
	public String getJldbh() {
		return jldbh;
	}
	/**
	 * 设置：运行电能表标识
	 */
	public void setYxdnbbs(String yxdnbbs) {
		this.yxdnbbs = yxdnbbs;
	}
	/**
	 * 获取：运行电能表标识
	 */
	public String getYxdnbbs() {
		return yxdnbbs;
	}
	/**
	 * 设置：示数类型代码(正向有功总、正向有功尖峰等)
	 */
	public void setSslxdm(String sslxdm) {
		this.sslxdm = sslxdm;
	}
	/**
	 * 获取：示数类型代码(正向有功总、正向有功尖峰等)
	 */
	public String getSslxdm() {
		return sslxdm;
	}
	/**
	 * 设置：抄表业务类别代码(0_正常抄表,1_非周期及业扩变更结算抄表,2_补抄,3_政策性退补,4_监抄,5_试算等)
	 */
	public void setYwlbdm(String ywlbdm) {
		this.ywlbdm = ywlbdm;
	}
	/**
	 * 获取：抄表业务类别代码(0_正常抄表,1_非周期及业扩变更结算抄表,2_补抄,3_政策性退补,4_监抄,5_试算等)
	 */
	public String getYwlbdm() {
		return ywlbdm;
	}
	/**
	 * 设置：计量点序号
	 */
	public void setJldxh(BigDecimal jldxh) {
		this.jldxh = jldxh;
	}
	/**
	 * 获取：计量点序号
	 */
	public BigDecimal getJldxh() {
		return jldxh;
	}
	/**
	 * 设置：时段代码(冗余)
	 */
	public void setSdlx(String sdlx) {
		this.sdlx = sdlx;
	}
	/**
	 * 获取：时段代码(冗余)
	 */
	public String getSdlx() {
		return sdlx;
	}
	/**
	 * 设置：抄表计划编号
	 */
	public void setCbjhbh(String cbjhbh) {
		this.cbjhbh = cbjhbh;
	}
	/**
	 * 获取：抄表计划编号
	 */
	public String getCbjhbh() {
		return cbjhbh;
	}
	/**
	 * 设置：抄表区段编号
	 */
	public void setCbqdbh(String cbqdbh) {
		this.cbqdbh = cbqdbh;
	}
	/**
	 * 获取：抄表区段编号
	 */
	public String getCbqdbh() {
		return cbqdbh;
	}
	/**
	 * 设置：计量点抄表顺序号
	 */
	public void setJldcbsxh(BigDecimal jldcbsxh) {
		this.jldcbsxh = jldcbsxh;
	}
	/**
	 * 获取：计量点抄表顺序号
	 */
	public BigDecimal getJldcbsxh() {
		return jldcbsxh;
	}
	/**
	 * 设置：抄表顺序号
	 */
	public void setCbsxh(BigDecimal cbsxh) {
		this.cbsxh = cbsxh;
	}
	/**
	 * 获取：抄表顺序号
	 */
	public BigDecimal getCbsxh() {
		return cbsxh;
	}
	/**
	 * 设置：主副表标志(0_副表,1_主表)
	 */
	public void setZfbbz(String zfbbz) {
		this.zfbbz = zfbbz;
	}
	/**
	 * 获取：主副表标志(0_副表,1_主表)
	 */
	public String getZfbbz() {
		return zfbbz;
	}
	/**
	 * 设置：电费年月
	 */
	public void setDfny(BigDecimal dfny) {
		this.dfny = dfny;
	}
	/**
	 * 获取：电费年月
	 */
	public BigDecimal getDfny() {
		return dfny;
	}
	/**
	 * 设置：本月抄表次数
	 */
	public void setBqcbcs(BigDecimal bqcbcs) {
		this.bqcbcs = bqcbcs;
	}
	/**
	 * 获取：本月抄表次数
	 */
	public BigDecimal getBqcbcs() {
		return bqcbcs;
	}
	/**
	 * 设置：应抄表次数
	 */
	public void setYcbcs(BigDecimal ycbcs) {
		this.ycbcs = ycbcs;
	}
	/**
	 * 获取：应抄表次数
	 */
	public BigDecimal getYcbcs() {
		return ycbcs;
	}
	/**
	 * 设置：冲正次数
	 */
	public void setCzcs(BigDecimal czcs) {
		this.czcs = czcs;
	}
	/**
	 * 获取：冲正次数
	 */
	public BigDecimal getCzcs() {
		return czcs;
	}
	/**
	 * 设置：用户编号
	 */
	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}
	/**
	 * 获取：用户编号
	 */
	public String getYhbh() {
		return yhbh;
	}
	/**
	 * 设置：用户名称
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	/**
	 * 获取：用户名称
	 */
	public String getYhmc() {
		return yhmc;
	}
	/**
	 * 设置：用电地址
	 */
	public void setYddz(String yddz) {
		this.yddz = yddz;
	}
	/**
	 * 获取：用电地址
	 */
	public String getYddz() {
		return yddz;
	}
	/**
	 * 设置：计量点用途代码
	 */
	public void setJldytdm(String jldytdm) {
		this.jldytdm = jldytdm;
	}
	/**
	 * 获取：计量点用途代码
	 */
	public String getJldytdm() {
		return jldytdm;
	}
	/**
	 * 设置：资产编号
	 */
	public void setZcbh(String zcbh) {
		this.zcbh = zcbh;
	}
	/**
	 * 获取：资产编号
	 */
	public String getZcbh() {
		return zcbh;
	}
	/**
	 * 设置：出厂编号
	 */
	public void setCcbh(String ccbh) {
		this.ccbh = ccbh;
	}
	/**
	 * 获取：出厂编号
	 */
	public String getCcbh() {
		return ccbh;
	}
	/**
	 * 设置：设备类型代码
	 */
	public void setSblxdm(String sblxdm) {
		this.sblxdm = sblxdm;
	}
	/**
	 * 获取：设备类型代码
	 */
	public String getSblxdm() {
		return sblxdm;
	}
	/**
	 * 设置：上次表示数
	 */
	public void setScbss(BigDecimal scbss) {
		this.scbss = scbss;
	}
	/**
	 * 获取：上次表示数
	 */
	public BigDecimal getScbss() {
		return scbss;
	}
	/**
	 * 设置：本次表示数
	 */
	public void setBcbss(BigDecimal bcbss) {
		this.bcbss = bcbss;
	}
	/**
	 * 获取：本次表示数
	 */
	public BigDecimal getBcbss() {
		return bcbss;
	}
	/**
	 * 设置：表示数差额
	 */
	public void setBssce(BigDecimal bssce) {
		this.bssce = bssce;
	}
	/**
	 * 获取：表示数差额
	 */
	public BigDecimal getBssce() {
		return bssce;
	}
	/**
	 * 设置：表码位数
	 */
	public void setBmws(String bmws) {
		this.bmws = bmws;
	}
	/**
	 * 获取：表码位数
	 */
	public String getBmws() {
		return bmws;
	}
	/**
	 * 设置：相位
	 */
	public void setXw(String xw) {
		this.xw = xw;
	}
	/**
	 * 获取：相位
	 */
	public String getXw() {
		return xw;
	}
	/**
	 * 设置：综合倍率
	 */
	public void setZhbl(BigDecimal zhbl) {
		this.zhbl = zhbl;
	}
	/**
	 * 获取：综合倍率
	 */
	public BigDecimal getZhbl() {
		return zhbl;
	}
	/**
	 * 设置：旧表电量
	 */
	public void setJbdl(BigDecimal jbdl) {
		this.jbdl = jbdl;
	}
	/**
	 * 获取：旧表电量
	 */
	public BigDecimal getJbdl() {
		return jbdl;
	}
	/**
	 * 设置：加减电量
	 */
	public void setJjdl(BigDecimal jjdl) {
		this.jjdl = jjdl;
	}
	/**
	 * 获取：加减电量
	 */
	public BigDecimal getJjdl() {
		return jjdl;
	}
	/**
	 * 设置：预收电量
	 */
	public void setYsdl(BigDecimal ysdl) {
		this.ysdl = ysdl;
	}
	/**
	 * 获取：预收电量
	 */
	public BigDecimal getYsdl() {
		return ysdl;
	}
	/**
	 * 设置：退补电量
	 */
	public void setTbdl(BigDecimal tbdl) {
		this.tbdl = tbdl;
	}
	/**
	 * 获取：退补电量
	 */
	public BigDecimal getTbdl() {
		return tbdl;
	}
	/**
	 * 设置：上次预收电量
	 */
	public void setScysdl(BigDecimal scysdl) {
		this.scysdl = scysdl;
	}
	/**
	 * 获取：上次预收电量
	 */
	public BigDecimal getScysdl() {
		return scysdl;
	}
	/**
	 * 设置：表计电量
	 */
	public void setBjdl(BigDecimal bjdl) {
		this.bjdl = bjdl;
	}
	/**
	 * 获取：表计电量
	 */
	public BigDecimal getBjdl() {
		return bjdl;
	}
	/**
	 * 设置：上次抄表日期
	 */
	public void setSccbrq(Date sccbrq) {
		this.sccbrq = sccbrq;
	}
	/**
	 * 获取：上次抄表日期
	 */
	public Date getSccbrq() {
		return sccbrq;
	}
	/**
	 * 设置：上次表计电量
	 */
	public void setScbjdl(BigDecimal scbjdl) {
		this.scbjdl = scbjdl;
	}
	/**
	 * 获取：上次表计电量
	 */
	public BigDecimal getScbjdl() {
		return scbjdl;
	}
	/**
	 * 设置：上上次表计电量
	 */
	public void setPjbjdl(BigDecimal pjbjdl) {
		this.pjbjdl = pjbjdl;
	}
	/**
	 * 获取：上上次表计电量
	 */
	public BigDecimal getPjbjdl() {
		return pjbjdl;
	}
	/**
	 * 设置：抄表标志代码(0_未抄,1_已抄)
	 */
	public void setCbbzdm(String cbbzdm) {
		this.cbbzdm = cbbzdm;
	}
	/**
	 * 获取：抄表标志代码(0_未抄,1_已抄)
	 */
	public String getCbbzdm() {
		return cbbzdm;
	}
	/**
	 * 设置：用户类别代码(用户一种常用的分类方式且方便用户的管理:1_公变用户,2_专变客户,3_线损考核,4_台区考核,5_购电)
	 */
	public void setYhlbdm(String yhlbdm) {
		this.yhlbdm = yhlbdm;
	}
	/**
	 * 获取：用户类别代码(用户一种常用的分类方式且方便用户的管理:1_公变用户,2_专变客户,3_线损考核,4_台区考核,5_购电)
	 */
	public String getYhlbdm() {
		return yhlbdm;
	}
	/**
	 * 设置：实际抄表方式代码(10_现场单户,11_手工(抄表卡),12_普通抄表器,13_远红外抄表器,20_现场集抄,21_普通抄表器集抄,22_远红外抄表器集抄,23_无线抄表器集抄,24_低压载波集抄,30_遥抄,31_负控遥抄,32_低压遥抄,33_配变遥抄,34_厂站遥抄,99_其它)
	 */
	public void setSjcbfsdm(String sjcbfsdm) {
		this.sjcbfsdm = sjcbfsdm;
	}
	/**
	 * 获取：实际抄表方式代码(10_现场单户,11_手工(抄表卡),12_普通抄表器,13_远红外抄表器,20_现场集抄,21_普通抄表器集抄,22_远红外抄表器集抄,23_无线抄表器集抄,24_低压载波集抄,30_遥抄,31_负控遥抄,32_低压遥抄,33_配变遥抄,34_厂站遥抄,99_其它)
	 */
	public String getSjcbfsdm() {
		return sjcbfsdm;
	}
	/**
	 * 设置：抄表状态代码(0_正常,1_翻转,2_倒走等)
	 */
	public void setCbztdm(String cbztdm) {
		this.cbztdm = cbztdm;
	}
	/**
	 * 获取：抄表状态代码(0_正常,1_翻转,2_倒走等)
	 */
	public String getCbztdm() {
		return cbztdm;
	}
	/**
	 * 设置：抄表异常代码(0_无,10_示数不平,11_箱烂,12_违约,13_无表,14_电表烧表,15_电表故障,16_电表编号不符,18_变比不符,19_估抄,20_漏抄)
	 */
	public void setCbycdm(String cbycdm) {
		this.cbycdm = cbycdm;
	}
	/**
	 * 获取：抄表异常代码(0_无,10_示数不平,11_箱烂,12_违约,13_无表,14_电表烧表,15_电表故障,16_电表编号不符,18_变比不符,19_估抄,20_漏抄)
	 */
	public String getCbycdm() {
		return cbycdm;
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
	 * 设置：抄表人标识
	 */
	public void setCbrbs(String cbrbs) {
		this.cbrbs = cbrbs;
	}
	/**
	 * 获取：抄表人标识
	 */
	public String getCbrbs() {
		return cbrbs;
	}
	/**
	 * 设置：抄表时间
	 */
	public void setCbsj(Date cbsj) {
		this.cbsj = cbsj;
	}
	/**
	 * 获取：抄表时间
	 */
	public Date getCbsj() {
		return cbsj;
	}
	/**
	 * 设置：冻结时间
	 */
	public void setDjsj(Date djsj) {
		this.djsj = djsj;
	}
	/**
	 * 获取：冻结时间
	 */
	public Date getDjsj() {
		return djsj;
	}
	/**
	 * 设置：上次工作单编号
	 */
	public void setScgzdbh(String scgzdbh) {
		this.scgzdbh = scgzdbh;
	}
	/**
	 * 获取：上次工作单编号
	 */
	public String getScgzdbh() {
		return scgzdbh;
	}
	/**
	 * 设置：单位编码
	 */
	public void setDwbm(String dwbm) {
		this.dwbm = dwbm;
	}
	/**
	 * 获取：单位编码
	 */
	public String getDwbm() {
		return dwbm;
	}
	/**
	 * 设置：地区编码
	 */
	public void setDqbm(String dqbm) {
		this.dqbm = dqbm;
	}
	/**
	 * 获取：地区编码
	 */
	public String getDqbm() {
		return dqbm;
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
	 * 设置：冲正年月
	 */
	public void setCzny(BigDecimal czny) {
		this.czny = czny;
	}
	/**
	 * 获取：冲正年月
	 */
	public BigDecimal getCzny() {
		return czny;
	}
	/**
	 * 设置：数据类型
	 */
	public void setSjlx(String sjlx) {
		this.sjlx = sjlx;
	}
	/**
	 * 获取：数据类型
	 */
	public String getSjlx() {
		return sjlx;
	}
	/**
	 * 设置：有效标志
	 */
	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
	/**
	 * 获取：有效标志
	 */
	public String getYxbz() {
		return yxbz;
	}
	/**
	 * 设置：下载PDA标志
	 */
	public void setXzpdabz(String xzpdabz) {
		this.xzpdabz = xzpdabz;
	}
	/**
	 * 获取：下载PDA标志
	 */
	public String getXzpdabz() {
		return xzpdabz;
	}
	/**
	 * 设置：原抄表号,用于记录电能表上的抄表号
	 */
	public void setYcbh(String ycbh) {
		this.ycbh = ycbh;
	}
	/**
	 * 获取：原抄表号,用于记录电能表上的抄表号
	 */
	public String getYcbh() {
		return ycbh;
	}
	/**
	 * 设置：转外委抄表标志
	 */
	public void setZwwcbbz(String zwwcbbz) {
		this.zwwcbbz = zwwcbbz;
	}
	/**
	 * 获取：转外委抄表标志
	 */
	public String getZwwcbbz() {
		return zwwcbbz;
	}
	/**
	 * 设置：相线，包括：单相、三相三线、三相四线
	 */
	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
	}
	/**
	 * 获取：相线，包括：单相、三相三线、三相四线
	 */
	public String getXxdm() {
		return xxdm;
	}
	/**
	 * 设置：计量点抄表方式代码
	 */
	public void setJldcbfsdm(String jldcbfsdm) {
		this.jldcbfsdm = jldcbfsdm;
	}
	/**
	 * 获取：计量点抄表方式代码
	 */
	public String getJldcbfsdm() {
		return jldcbfsdm;
	}
	/**
	 * 设置：自动抄表更新时间
	 */
	public void setZdcbgxsj(Date zdcbgxsj) {
		this.zdcbgxsj = zdcbgxsj;
	}
	/**
	 * 获取：自动抄表更新时间
	 */
	public Date getZdcbgxsj() {
		return zdcbgxsj;
	}
	/**
	 * 设置：远程抄表标识
	 */
	public void setYccbbz(String yccbbz) {
		this.yccbbz = yccbbz;
	}
	/**
	 * 获取：远程抄表标识
	 */
	public String getYccbbz() {
		return yccbbz;
	}
	/**
	 * 设置：计量前日0点表码
	 */
	public void setJlqrldbm(BigDecimal jlqrldbm) {
		this.jlqrldbm = jlqrldbm;
	}
	/**
	 * 获取：计量前日0点表码
	 */
	public BigDecimal getJlqrldbm() {
		return jlqrldbm;
	}
	/**
	 * 设置：计量当日0点表码
	 */
	public void setJldrldbm(BigDecimal jldrldbm) {
		this.jldrldbm = jldrldbm;
	}
	/**
	 * 获取：计量当日0点表码
	 */
	public BigDecimal getJldrldbm() {
		return jldrldbm;
	}
	/**
	 * 设置：计量当日24点表码
	 */
	public void setJldresdbm(BigDecimal jldresdbm) {
		this.jldresdbm = jldresdbm;
	}
	/**
	 * 获取：计量当日24点表码
	 */
	public BigDecimal getJldresdbm() {
		return jldresdbm;
	}
	/**
	 * 设置：计量次日24点表码
	 */
	public void setJlcresdbm(BigDecimal jlcresdbm) {
		this.jlcresdbm = jlcresdbm;
	}
	/**
	 * 获取：计量次日24点表码
	 */
	public BigDecimal getJlcresdbm() {
		return jlcresdbm;
	}
	/**
	 * 设置：实际抄表日期
	 */
	public void setSjcbrq(Date sjcbrq) {
		this.sjcbrq = sjcbrq;
	}
	/**
	 * 获取：实际抄表日期
	 */
	public Date getSjcbrq() {
		return sjcbrq;
	}
	/**
	 * 设置：远程自动化抄表存在补抄的情况，此字段标识获取的数据是否为计量系统补抄数据
	 */
	public void setYcbcbz(String ycbcbz) {
		this.ycbcbz = ycbcbz;
	}
	/**
	 * 获取：远程自动化抄表存在补抄的情况，此字段标识获取的数据是否为计量系统补抄数据
	 */
	public String getYcbcbz() {
		return ycbcbz;
	}
}
