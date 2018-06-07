package com.robot.robot.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.common.utils.RequestUtil;
import com.robot.common.utils.StringUtils;
import com.robot.robot.controller.app.bean.DfDetailBean;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.controller.app.common.AppConstants;
import com.robot.robot.domain.DhKhdfDO;
import com.robot.robot.domain.DhKhxxDO;
import com.robot.robot.domain.DlCbdlDO;
import com.robot.robot.service.DhKhdfService;
import com.robot.robot.service.DlKhYdkhService;
import com.robot.robot.service.DlLcCbxxService;

/**
 * 电力相关接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/app/dl")
public class DLForAppController {
	public static Logger log = Logger.getLogger(DLForAppController.class); 
	
	@Autowired
	private DhKhdfService dhKhdfService;
	@Autowired
	private DlKhYdkhService dlKhYdkhService;
	@Autowired
	private DlLcCbxxService dlLcCbxxService;
	
	/**
	 * 通过身份证查询信息
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getUserDLMsgForID")
	@ResponseBody
	public ResponseBean getUserDLMsgForID(HttpServletRequest request) throws Exception{
		String idCard=RequestUtil.getString(request, "idCard");
		if(StringUtils.isEmpty(idCard)){
			return ResponseBean.fail("参数格式不对");
		}
		try{
			//查找身份证对应用户
			List<DhKhxxDO> list=dlKhYdkhService.getForZjhm(idCard);
			return ResponseBean.success(list);
		}catch (Exception e) {
			log.error("通过身份证查询信息接口异常:  "+e.getMessage());
			return ResponseBean.fail(e.getMessage());
		}
		
	}
		
	
	/**
	 * 通过电力的用户编号查询电费
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getChargeForYhbh")
	@ResponseBody
	public ResponseBean getChargeForYhbh(HttpServletRequest request) throws Exception{
		String yhbh=RequestUtil.getString(request, "yhbh");
		int page=RequestUtil.getInt(request, "page");
		int limit=AppConstants.df_limit;
		if(StringUtils.isEmpty(yhbh)){
			return ResponseBean.fail("参数格式不对");
		}
		try{
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("yhbh",yhbh);
			map.put("offset", (page-1)*limit);
			map.put("limit", limit);
//			//查找用户电费
			List<DhKhdfDO> list=dhKhdfService.list(map);
			return ResponseBean.success(list);
		}catch (Exception e) {
			log.error("通过电力的用户编号查询电费异常:  "+e.getMessage());
			return ResponseBean.fail(e.getMessage());
		}
		
	}
	
	
	/**
	 * 通过电力的用户编号查询明细
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getDetailForYhbh")
	@ResponseBody
	public ResponseBean getDetailForYhbh(HttpServletRequest request) throws Exception{
		String yhbh=RequestUtil.getString(request, "yhbh");
		String dfny=RequestUtil.getString(request, "dfny");		//电费年月
		if(StringUtils.isEmpty(yhbh) && StringUtils.isEmpty(dfny)){
			return ResponseBean.fail("参数格式不对");
		}
		try{
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("yhbh",yhbh);
			map.put("dfny",dfny);
			
//			//查找用户电费
			List<DhKhdfDO> list=dhKhdfService.list(map);
			
			DhKhdfDO dhkhdf=new DhKhdfDO();
			if(list.size()>0){
				dhkhdf=list.get(0);
			}
			List<DlCbdlDO> dlCbdls=dlLcCbxxService.getlccbxxForAPP(map);
			DfDetailBean bean =new DfDetailBean();
			bean.setDhKhdf(dhkhdf);
			bean.setDlCbdls(dlCbdls);
			
			return ResponseBean.success(bean);
		}catch (Exception e) {
			log.error("通过电力的用户编号查询明细异常:  "+e.getMessage());
			return ResponseBean.fail(e.getMessage());
		}
		
	}
}
