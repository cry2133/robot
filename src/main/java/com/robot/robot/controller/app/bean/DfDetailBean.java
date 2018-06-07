package com.robot.robot.controller.app.bean;

import java.util.List;

import com.robot.robot.domain.DhKhdfDO;
import com.robot.robot.domain.DlCbdlDO;

public class DfDetailBean {
	private DhKhdfDO dhKhdf;
	
	private  List<DlCbdlDO> dlCbdls;

	public DhKhdfDO getDhKhdf() {
		return dhKhdf;
	}

	public void setDhKhdf(DhKhdfDO dhKhdf) {
		this.dhKhdf = dhKhdf;
	}

	public List<DlCbdlDO> getDlCbdls() {
		return dlCbdls;
	}

	public void setDlCbdls(List<DlCbdlDO> dlCbdls) {
		this.dlCbdls = dlCbdls;
	}
	
	
}
