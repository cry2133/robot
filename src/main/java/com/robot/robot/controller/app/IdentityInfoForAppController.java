package com.robot.robot.controller.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.common.utils.StringUtils;
import com.robot.robot.bean.UserModel;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TIdentityInfoDO;
import com.robot.robot.service.TIdentityInfoService;
import org.springframework.web.bind.annotation.RequestBody;


import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/app/authentication")
public class IdentityInfoForAppController {
	public static Logger logger = Logger.getLogger(IdentityInfoForAppController.class); 

	
	@Autowired
	private TIdentityInfoService tIdentityInfoService;
	
	/**
	 * 实名认证信息采集
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/collect", method = POST)
	@ResponseBody
	public ResponseBean collect(@RequestBody TIdentityInfoDO tIdentityInfo) throws Exception{
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("identityID", tIdentityInfo.getIdentityID());
			List<TIdentityInfoDO> getIdentityIdByIdentityId = tIdentityInfoService.list(map);
			if(!getIdentityIdByIdentityId.isEmpty()){
				return ResponseBean.fail("该身份证号已被认证！不能重复采集！");				
			}
			/**
			 * 身份证号码粗略验证
			 */
			String identityID = tIdentityInfo.getIdentityID();
			Pattern pattern = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");
	        Matcher matcher = pattern.matcher(identityID);
			if(identityID.length()!=18 || matcher.matches()==false){
				return ResponseBean.fail("该身份证号不存在！");
			}
			SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
			String birth = tIdentityInfo.getBirth();
			if(StringUtils.isNotEmpty(birth)){
				long lt = new Long(tIdentityInfo.getBirth());
				Date d = new Date(lt);
				tIdentityInfo.setBirth(sf.format(d));
			}
			tIdentityInfoService.save(tIdentityInfo);
        }catch (Exception e) {
        	logger.error("collect异常", e);
        }
        return ResponseBean.success();
		
	}
	

	/**
	 * 
	* @Functionlity  实名认证信息查询
	* @Date  2018年6月21日
	* @param userModel
	* @return ResponseBean
	 */
    @RequestMapping(value = "/searchUserInfo", method = POST)
    @ResponseBody
    public ResponseBean searchUserInfo(@RequestBody UserModel userModel) {
        TIdentityInfoDO tIdentityInfo = null;
        try {
        	tIdentityInfo = tIdentityInfoService.selectByIdentityID(userModel.getIdentityID());
                if (tIdentityInfo==null || "".equals(tIdentityInfo)) {
                    return ResponseBean.success("没有相关用户信息！");
                }
                return ResponseBean.success(tIdentityInfo);

        }catch (Exception e) {
            logger.error("search error", e);
        }
        return ResponseBean.fail();
    }
}
