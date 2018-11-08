package com.robot.robot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 应收电费记录
 *
 */
public class DlZwYsDfJlDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//应收账务流水号
	private String yszwlsh;
	//计量点编号
	private String jldbh;
	//计量点序号
	private BigDecimal jldxh;
	//工作单编号
	private String gzdbh;
	//抄表区段编号
	private String cbqdbh;
	//用户编号
	private String yhbh;
	//结算户号
	private String jshh;
	//抄表计划编号
	private String cbjhbh;
	//电费年月
	private BigDecimal dfny;
	//本期抄表次数
	private BigDecimal bqcbcs;
	//应抄表次数
	private BigDecimal ycbcs;
	//结算类型代码(0_分次,1_结算)
	private String jslxdm;
	//冲正次数(针对原记录:0_未被应收冲正,>1表示被冲正的次数)
	private BigDecimal czcs;
	//冲正年月(针对冲正红字记录)
	private BigDecimal czny;
	//有效性标志(0_否,1_是)
	private String yxxbz;
	//用户名称
	private String yhmc;
	//用电地址
	private String yddz;
	//违约金日期(违约金起征日期)
	private Date wyjrq;
	//行业分类代码(行业类别,目前使用国民经济行业分类与代码(gb/DescartesUtil 4754－2002),例如:01 农业 02林业 03畜牧业 04渔业)
	private String hyfldm;
	//用户类别代码(1_公变用户,2_专变客户,3_线损考核,4_台区考核,5_购电)
	private String yhlbdm;
	//票据类型代码(0_普通发票,1_增值税发票)
	private String pjlxdm;
	//城乡代码(城乡标志:0_城市,1_乡村)
	private String cxdm;
	//用电类别代码
	private String ydlbdm;
	//电价代码
	private String djdm;
	//电价(主电价)
	private BigDecimal dj;
	//分时计费标志(是否执行峰谷标志:0_否,1_是)
	private String fsjfbz;
	//差错退补电量
	private BigDecimal cctbdl;
	//计费电量
	private BigDecimal jfdl;
	//电度电费
	private BigDecimal dddf;
	//基本电费
	private BigDecimal jbdf;
	//力调电费
	private BigDecimal ltdf;
	//应收电费
	private BigDecimal ysdf;
	//附加费合计
	private BigDecimal fjfhj;
	//差错退补电费
	private BigDecimal cctbdf;
	//政策性退补电费
	private BigDecimal zctbdf;
	//缴费优先级，用于同一个结算户下多个计量点缴费的顺序
	private BigDecimal jfyxj;
	//票据流水号
	private String pjdyxxbs;
	//是否生成账单数据的标志（0：否，1：是）
	private String sfsczdbz;
	//账单生成时间
	private Date zDscsj;
	//应收业务类别代码(0_正常电费,1_应收冲正[红字],2_冲正退补[蓝字],3_电量电费退补,4_政策性退补,5_窃电补收,6_窃电违约金,7_非周期性计费,8_卡表预售)
	private String ywlbdm;
	//包括 正常电费、冲正红字、冲正蓝字、差错电费等
	private String sjlx;
	//超过需量定值电费
	private BigDecimal cdzxldf;
	//阶梯类型
	private String jtlx;
	//基本电费计算方式代码
	private String jbDfjsfsdm;
	//免费电量
	private BigDecimal mfdl;
	//峰谷增收
	private BigDecimal fgzs;
	//发行人标识
	private String fxrbs;
	//发行时间
	private Date fxsj;
	//供电单位编码
	private String gddwbm;
	//单位编码
	private String dwbm;
	//地区编码
	private String dqbm;
	//数据创建时间
	private Date cjsj;
	//数据最近一次变更时间
	private Date czsj;
	//计算参数版本的唯一标识，每次电价维护流程产生新版本
	private String djbbbh;
	//结算户名，发票打印抬头
	private String jshmc;
	//结算户地址，发票打印用
	private String jshdz;
	//发票打印方式：不打印、合打、按用户打、按计量点打
	private String fpdyfs;
	//发票获取方式，如派送、自取
	private String fphqfs;
	//线路线段标识
	private String xlxdbs;
	//台区标识
	private String tqbs;
	//年累计电量
	private BigDecimal nljdl;
	//计划账单派送日期
	private Date jhzdpsrq;
	//计划划扣日期
	private Date jhhkrq;

	
	public String getYszwlsh() {
		return yszwlsh;
	}
	public void setYszwlsh(String yszwlsh) {
		this.yszwlsh = yszwlsh;
	}
	public String getJldbh() {
		return jldbh;
	}
	public void setJldbh(String jldbh) {
		this.jldbh = jldbh;
	}
	public BigDecimal getJldxh() {
		return jldxh;
	}
	public void setJldxh(BigDecimal jldxh) {
		this.jldxh = jldxh;
	}
	public String getGzdbh() {
		return gzdbh;
	}
	public void setGzdbh(String gzdbh) {
		this.gzdbh = gzdbh;
	}
	public String getCbqdbh() {
		return cbqdbh;
	}
	public void setCbqdbh(String cbqdbh) {
		this.cbqdbh = cbqdbh;
	}
	public String getYhbh() {
		return yhbh;
	}
	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}
	public String getJshh() {
		return jshh;
	}
	public void setJshh(String jshh) {
		this.jshh = jshh;
	}
	public String getCbjhbh() {
		return cbjhbh;
	}
	public void setCbjhbh(String cbjhbh) {
		this.cbjhbh = cbjhbh;
	}
	public BigDecimal getDfny() {
		return dfny;
	}
	public void setDfny(BigDecimal dfny) {
		this.dfny = dfny;
	}
	public BigDecimal getBqcbcs() {
		return bqcbcs;
	}
	public void setBqcbcs(BigDecimal bqcbcs) {
		this.bqcbcs = bqcbcs;
	}
	public BigDecimal getYcbcs() {
		return ycbcs;
	}
	public void setYcbcs(BigDecimal ycbcs) {
		this.ycbcs = ycbcs;
	}
	public String getJslxdm() {
		return jslxdm;
	}
	public void setJslxdm(String jslxdm) {
		this.jslxdm = jslxdm;
	}
	public BigDecimal getCzcs() {
		return czcs;
	}
	public void setCzcs(BigDecimal czcs) {
		this.czcs = czcs;
	}
	public BigDecimal getCzny() {
		return czny;
	}
	public void setCzny(BigDecimal czny) {
		this.czny = czny;
	}
	public String getYxxbz() {
		return yxxbz;
	}
	public void setYxxbz(String yxxbz) {
		this.yxxbz = yxxbz;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getYddz() {
		return yddz;
	}
	public void setYddz(String yddz) {
		this.yddz = yddz;
	}
	public Date getWyjrq() {
		return wyjrq;
	}
	public void setWyjrq(Date wyjrq) {
		this.wyjrq = wyjrq;
	}
	public String getHyfldm() {
		return hyfldm;
	}
	public void setHyfldm(String hyfldm) {
		this.hyfldm = hyfldm;
	}
	public String getYhlbdm() {
		return yhlbdm;
	}
	public void setYhlbdm(String yhlbdm) {
		this.yhlbdm = yhlbdm;
	}
	public String getPjlxdm() {
		return pjlxdm;
	}
	public void setPjlxdm(String pjlxdm) {
		this.pjlxdm = pjlxdm;
	}
	public String getCxdm() {
		return cxdm;
	}
	public void setCxdm(String cxdm) {
		this.cxdm = cxdm;
	}
	public String getYdlbdm() {
		return ydlbdm;
	}
	public void setYdlbdm(String ydlbdm) {
		this.ydlbdm = ydlbdm;
	}
	public String getDjdm() {
		return djdm;
	}
	public void setDjdm(String djdm) {
		this.djdm = djdm;
	}
	public BigDecimal getDj() {
		return dj;
	}
	public void setDj(BigDecimal dj) {
		this.dj = dj;
	}
	public String getFsjfbz() {
		return fsjfbz;
	}
	public void setFsjfbz(String fsjfbz) {
		this.fsjfbz = fsjfbz;
	}
	public BigDecimal getCctbdl() {
		return cctbdl;
	}
	public void setCctbdl(BigDecimal cctbdl) {
		this.cctbdl = cctbdl;
	}
	public BigDecimal getJfdl() {
		return jfdl;
	}
	public void setJfdl(BigDecimal jfdl) {
		this.jfdl = jfdl;
	}
	public BigDecimal getDddf() {
		return dddf;
	}
	public void setDddf(BigDecimal dddf) {
		this.dddf = dddf;
	}
	public BigDecimal getJbdf() {
		return jbdf;
	}
	public void setJbdf(BigDecimal jbdf) {
		this.jbdf = jbdf;
	}
	public BigDecimal getLtdf() {
		return ltdf;
	}
	public void setLtdf(BigDecimal ltdf) {
		this.ltdf = ltdf;
	}
	public BigDecimal getYsdf() {
		return ysdf;
	}
	public void setYsdf(BigDecimal ysdf) {
		this.ysdf = ysdf;
	}
	public BigDecimal getFjfhj() {
		return fjfhj;
	}
	public void setFjfhj(BigDecimal fjfhj) {
		this.fjfhj = fjfhj;
	}
	public BigDecimal getCctbdf() {
		return cctbdf;
	}
	public void setCctbdf(BigDecimal cctbdf) {
		this.cctbdf = cctbdf;
	}
	public BigDecimal getZctbdf() {
		return zctbdf;
	}
	public void setZctbdf(BigDecimal zctbdf) {
		this.zctbdf = zctbdf;
	}
	public BigDecimal getJfyxj() {
		return jfyxj;
	}
	public void setJfyxj(BigDecimal jfyxj) {
		this.jfyxj = jfyxj;
	}
	public String getPjdyxxbs() {
		return pjdyxxbs;
	}
	public void setPjdyxxbs(String pjdyxxbs) {
		this.pjdyxxbs = pjdyxxbs;
	}
	public String getSfsczdbz() {
		return sfsczdbz;
	}
	public void setSfsczdbz(String sfsczdbz) {
		this.sfsczdbz = sfsczdbz;
	}
	public Date getzDscsj() {
		return zDscsj;
	}
	public void setzDscsj(Date zDscsj) {
		this.zDscsj = zDscsj;
	}
	public String getYwlbdm() {
		return ywlbdm;
	}
	public void setYwlbdm(String ywlbdm) {
		this.ywlbdm = ywlbdm;
	}
	public String getSjlx() {
		return sjlx;
	}
	public void setSjlx(String sjlx) {
		this.sjlx = sjlx;
	}
	public BigDecimal getCdzxldf() {
		return cdzxldf;
	}
	public void setCdzxldf(BigDecimal cdzxldf) {
		this.cdzxldf = cdzxldf;
	}
	public String getJtlx() {
		return jtlx;
	}
	public void setJtlx(String jtlx) {
		this.jtlx = jtlx;
	}
	public String getJbDfjsfsdm() {
		return jbDfjsfsdm;
	}
	public void setJbDfjsfsdm(String jbDfjsfsdm) {
		this.jbDfjsfsdm = jbDfjsfsdm;
	}
	public BigDecimal getMfdl() {
		return mfdl;
	}
	public void setMfdl(BigDecimal mfdl) {
		this.mfdl = mfdl;
	}
	public BigDecimal getFgzs() {
		return fgzs;
	}
	public void setFgzs(BigDecimal fgzs) {
		this.fgzs = fgzs;
	}
	public String getFxrbs() {
		return fxrbs;
	}
	public void setFxrbs(String fxrbs) {
		this.fxrbs = fxrbs;
	}
	public Date getFxsj() {
		return fxsj;
	}
	public void setFxsj(Date fxsj) {
		this.fxsj = fxsj;
	}
	public String getGddwbm() {
		return gddwbm;
	}
	public void setGddwbm(String gddwbm) {
		this.gddwbm = gddwbm;
	}
	public String getDwbm() {
		return dwbm;
	}
	public void setDwbm(String dwbm) {
		this.dwbm = dwbm;
	}
	public String getDqbm() {
		return dqbm;
	}
	public void setDqbm(String dqbm) {
		this.dqbm = dqbm;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public Date getCzsj() {
		return czsj;
	}
	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}
	public String getDjbbbh() {
		return djbbbh;
	}
	public void setDjbbbh(String djbbbh) {
		this.djbbbh = djbbbh;
	}
	public String getJshmc() {
		return jshmc;
	}
	public void setJshmc(String jshmc) {
		this.jshmc = jshmc;
	}
	public String getJshdz() {
		return jshdz;
	}
	public void setJshdz(String jshdz) {
		this.jshdz = jshdz;
	}
	public String getFpdyfs() {
		return fpdyfs;
	}
	public void setFpdyfs(String fpdyfs) {
		this.fpdyfs = fpdyfs;
	}
	public String getFphqfs() {
		return fphqfs;
	}
	public void setFphqfs(String fphqfs) {
		this.fphqfs = fphqfs;
	}
	public String getXlxdbs() {
		return xlxdbs;
	}
	public void setXlxdbs(String xlxdbs) {
		this.xlxdbs = xlxdbs;
	}
	public String getTqbs() {
		return tqbs;
	}
	public void setTqbs(String tqbs) {
		this.tqbs = tqbs;
	}
	public BigDecimal getNljdl() {
		return nljdl;
	}
	public void setNljdl(BigDecimal nljdl) {
		this.nljdl = nljdl;
	}
	public Date getJhzdpsrq() {
		return jhzdpsrq;
	}
	public void setJhzdpsrq(Date jhzdpsrq) {
		this.jhzdpsrq = jhzdpsrq;
	}
	public Date getJhhkrq() {
		return jhhkrq;
	}
	public void setJhhkrq(Date jhhkrq) {
		this.jhhkrq = jhhkrq;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
