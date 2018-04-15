//package com.cj.witcommon.utils.excle;
//
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.activemq.leveldb.SubAckRecord;
//import org.apache.commons.validator.Var;
//import org.apache.log4j.Logger;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import com.google.gson.Gson;
//
//import cn.mos.framework.annotation.RestfulAnnotation;
//import cn.mos.framework.base.controller.BaseController;
//import cn.mos.framework.base.entity.SessionBean;
//import cn.mos.framework.common.OfficeComponentsUtils;
//import cn.mos.framework.common.OrgUtils;
//import cn.mos.framework.components.AppConst;
//import cn.mos.framework.exception.BussinessException;
//import cn.mos.framework.organization.department.bean.OrgOrganization;
//import cn.mos.framework.organization.department.service.OrgOrganizationService;
//import cn.mos.framework.utils.EasyUIPage;
//import cn.mos.framework.utils.StringUtils;
//import cn.mos.tobacco.jxkh.bean.AddRecord;
//import cn.mos.tobacco.zcqgh.bean.CtbPlan;
//import cn.mos.tobacco.zrs.bean.ZrsBean;
//import cn.mos.tobacco.zrs.bean.ZrsqdrBean;
//import cn.mos.tobacco.zrs.service.ZrsService;
//
///******************************************************************************
// * @Package:
// * @ClassName:
// * @Description: [责任书相关页面]
// * @Author:zhengyouquan
// * @CreateDate:
// * @UpdateUser:
// * @UpdateDate:
// * @UpdateRemark:
// * @Version: [v1.0]
// */
//@Controller
//@RequestMapping(value="/ctbZrs")
//public class ZrsController extends BaseController{
//
//	private Logger logger = Logger.getLogger(this.getClass());
//
//	@Resource(name = "zrsService")
//	ZrsService zrsService;
//	@Resource(name = "orgOrganizationService")
//	OrgOrganizationService orgOrganizationService;
//
//	@RequestMapping("/Page")
//	public  @ResponseBody ModelAndView  page(String id, SessionBean sessionBean){
//		ModelAndView modelAndView = new ModelAndView("tobacco/zrs/zrsQueryList");
//		sessionBean = getSessionBean(sessionBean);
//		OrgOrganization org = OrgUtils.getSsdw(sessionBean);
//		if (org == null) {
//			try {
//				throw new BussinessException("当前用户所属单位不存在！");
//			} catch (BussinessException e) {
//				e.printStackTrace();
//			}
//		}
//		modelAndView.addObject("treeJson", zrsService.getManagerOrgJson(org.getId()));
//		modelAndView.addObject("userOrgCode", sessionBean.getUserOrgCode());
//		modelAndView.addObject("orgCode", org.getOrgcode());
//		modelAndView.addObject("orgLevel",org.getOrglevel());
//		modelAndView.addObject("orgType",sessionBean.getUserOrgBiztype());
//		if( null == id || "".equals(id)){
//			modelAndView.addObject("queryUrl", "/ctbZrs/queryList?fqdwid="+org.getOrgcode());
//			modelAndView.addObject("review", false);
//		}else{
//			modelAndView.addObject("queryUrl", "/ctbZrs/querySingle?id=" + id);
//			modelAndView.addObject("review", true);
//		}
//		return modelAndView;
//	}
//
//	@RequestMapping("/querySingle")
//	public @ResponseBody EasyUIPage query(String id, SessionBean sessionBean){
//		sessionBean = getSessionBean(sessionBean);
//		EasyUIPage page = new EasyUIPage();
//		List<ZrsBean> zrsBeans = new ArrayList<ZrsBean>();
//		zrsBeans.add(zrsService.querySingle(id));
//		page.setRownum(1);
//		page.setPage(1);
//		page.setTotal(1);
//		page.setRows(zrsBeans);
//		return page;
//	}
//
//	@RequestMapping(value="/queryQdrList")
//	public @ResponseBody EasyUIPage  queryList(SessionBean sessionBean, EasyUIPage page,String id,
//			@RequestParam(value = "rows", required = false) Integer rows) {
//		sessionBean = getSessionBean(sessionBean);
//		page.setPagePara(rows);
//		page.setRows(zrsService.getZrsqdrByBeans(id));
//		return page;
//	}
//
//	@RequestMapping(value="/queryList")
//	public @ResponseBody EasyUIPage  queryList(SessionBean sessionBean, EasyUIPage page,ZrsBean entity,
//			@RequestParam(value = "rows", required = false) Integer rows) {
//		sessionBean = getSessionBean(sessionBean);
//		page.setPagePara(rows);
//		OrgOrganization org = null;
//		String orgCode=entity.getFqdwid();
//		boolean  city = false;
//		if (StringUtils.isBlank(orgCode)) {
//			org = OrgUtils.getSsdw(sessionBean);
//		} else {
//			org = orgOrganizationService.queryByOrgcode(orgCode);
//		}
//		OrgOrganization orgfor = OrgUtils.getSsdw(sessionBean);
//		if (orgfor==null){
//			return new EasyUIPage();
//		}
//		if (org != null) {
//			String orglevel = org.getOrglevel();
//			if (orgfor!=null&&OrgUtils.ORGLEVEL_SGS.equals(orgfor.getOrglevel())){
//				//市级数据以下所有数据
//				if ( OrgUtils.ORGLEVEL_SZGS.equals(orglevel)){
//					city = true;
//					entity.setBqddwbmid(org.getOrgcode());
//					entity.setXt_lrrszgsid(org.getOrgcode());
//					entity.setFqdwid(null);
//				//县级及以下所有数据 单根据setXt_lrrxgsid 可以查询
//				}else if(OrgUtils.ORGLEVEL_XGS.equals(orglevel)){
//					entity.setXt_lrrxgsid(org.getOrgcode());
//					entity.setZrslx("01");
//					entity.setBqddwbmid(org.getOrgcode());
//					entity.setFqdwid(null);
//				//省级数据以下所有数据 只需要 Xt_lrrdwid 即可
//				}else if(OrgUtils.ORGLEVEL_SGS.equals(orglevel)){
//					entity.setXt_lrrdwid(org.getOrgcode());
//					entity.setFqdwid(null);
//				}else if(OrgUtils.ORGLEVEL_SGSBM.endsWith(orglevel)){
//					entity.setFqbmid(org.getOrgcode());
//					entity.setFqdwid(null);
//				}
//				else if(OrgUtils.ORGLEVEL_SZGSBM.endsWith(orglevel)){
//					city = true;
//					entity.setQdrbmdmArray(org.getOrgcode());
//					entity.setFqbmid(org.getOrgcode());
//					entity.setFqdwid(null);
//				}else{
//					entity.setQdrbmdmArray(entity.getQdrbmdmArray());
//					entity.setZrslx("01");
//					entity.setFqbmid(org.getOrgcode());
//					entity.setFqdwid(null);
//				}
//		}else{
//			if ( OrgUtils.ORGLEVEL_SZGS.equals(orglevel)){
//				city = true;
//				entity.setBqddwbmid(entity.getFqdwid());
//			}else if(OrgUtils.ORGLEVEL_XGS.equals(orglevel)){
//				entity.setXt_lrrxgsid(org.getOrgcode());
//				entity.setZrslx("01");
//				entity.setBqddwbmid(org.getOrgcode());
//				entity.setFqdwid(null);
//			}else if(OrgUtils.ORGLEVEL_SGSBM.endsWith(orglevel) ){
//				entity.setFqbmid(org.getOrgcode());
//				entity.setFqdwid(null);
//			}
//			else if(OrgUtils.ORGLEVEL_SZGSBM.endsWith(orglevel)){
//				city = true;
//				entity.setQdrbmdmArray(org.getOrgcode());
//				entity.setFqbmid(org.getOrgcode());
//				entity.setFqdwid(null);
//			}else{
//				entity.setQdrbmdmArray(org.getOrgcode());
//				entity.setZrslx("01");
//				entity.setFqbmid(org.getOrgcode());
//				entity.setFqdwid(null);
//			}
//		}
//		}
//		else{
//			return new EasyUIPage();
//		}
//		page = zrsService.queryList(entity,page,city);
//
//		@SuppressWarnings("unchecked")
//		List<ZrsBean> listzrs =(List<ZrsBean>)page.getRows();
//		StringBuffer sb = new StringBuffer();
//		for (ZrsBean zrsBean : listzrs) {
//			sb.append("'"+zrsBean.getId()+"',");
//		}
//		if(sb.length()>0){
//			String zrsid= sb.substring(0,sb.length()-1).toString();
//			List<ZrsqdrBean> listzrsqdr = zrsService.getZrsqdrByzrses(zrsid);
//			for (int i = 0; i < listzrs.size(); i++) {
//				for (int j = 0; j < listzrsqdr.size(); j++) {
//					if(listzrs.get(i).getId().equals(listzrsqdr.get(j).getZrsid())){
//						listzrs.get(i).setQdzt(listzrsqdr.get(j).getQdzt());
//						break;
//					}
//				}
//			}
//		}
//		page.setRows(listzrs);
//		return page;
//	}
//
//	@RequestMapping(value = "/del", method = RequestMethod.POST)
//	public @ResponseBody Map<String,Object> delete(ZrsBean entity,SessionBean sessionBean) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		sessionBean = getSessionBean(sessionBean);
//		try {
//			String resuleses = zrsService.del(entity,sessionBean);
//			if("del".equals(resuleses)){
//				map.put(AppConst.STATUS, AppConst.SUCCESS);
//				map.put(AppConst.MESSAGES, getAddSuccess());
//			}else{
//				map.put(AppConst.STATUS, AppConst.FAIL);
//				map.put(AppConst.MESSAGES, getAddFail());
//			}
//
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//			map.put(AppConst.STATUS, AppConst.FAIL);
//			map.put(AppConst.MESSAGES, getAddFail());
//		}
//		return map;
//	}
//
//	@RequestMapping(value="/view")
//	public @ResponseBody ModelAndView view(String id) {
//		ModelAndView modelAndView = new ModelAndView("tobacco/zrs/viewZrs");
//		modelAndView.addObject("entity", zrsService.querySingle(id));
//		return modelAndView;
//	}
//
//	@RequestMapping(value="/review")
//	public @ResponseBody ModelAndView  review(String id,String messageid) {
//		ModelAndView modelAndView = new ModelAndView("tobacco/zrs/reviewZrs");
//		modelAndView.addObject("entity", zrsService.querySingle(id));
//		modelAndView.addObject("messageid", messageid);
//		return modelAndView;
//	}
//
//	@RequestMapping(value="/reviewStatus")
//	public ModelAndView  reviewStatus(String id,int state,String ldspyj, String messageid,SessionBean sessionBean){
//		sessionBean = getSessionBean(sessionBean);
//		ModelAndView mv = new ModelAndView(getViewName(sessionBean));
//		Map<String, Object> model = new HashMap<String, Object>();
//		try {
//			if( null != id){
//				zrsService.reviewStatus(id, state, ldspyj, messageid,sessionBean);
//			}
//			model.put(AppConst.STATUS, AppConst.SUCCESS);
//			model.put(AppConst.MESSAGES, getAddSuccess());
//		} catch (RuntimeException e) {
//			logger.error(e.getLocalizedMessage(), e);
//			model.put(AppConst.STATUS, AppConst.FAIL);
//			model.put(AppConst.MESSAGES, e.getMessage());
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//			model.put(AppConst.STATUS, AppConst.FAIL);
//			model.put(AppConst.MESSAGES, getAddFail());
//		}
//		mv.addObject(AppConst.MESSAGES, new Gson().toJson(model));
//		return mv;
//	}
//
//	@RequestMapping(value="/isprocessed")
//	@ResponseBody
//	public boolean  isprocessed(String id) {
//		if(zrsService.isprocessed(id) == 0 ){
//			return false;
//		}else{
//			return true;
//		}
//	}
//
//	@RequestMapping("/addZrs")
//	public ModelAndView addZrs(){
//		ModelAndView modelAndView = new ModelAndView("tobacco/zrs/addZrs");
//		SessionBean sessionBean = getSessionBean();
//		OrgOrganization org = OrgUtils.getSsdw(sessionBean);
//		ZrsBean zrsBean = new ZrsBean();
//		if (org == null) {
//			try {
//				throw new BussinessException("当前用户所属单位不存在！");
//			} catch (BussinessException e) {
//				e.printStackTrace();
//			}
//		}
//
//		OrgOrganization org2 = OrgUtils.getSzgsAjbm();
//		OrgOrganization org3 = OrgUtils.getSgsAjbm();
//		String type = "";
//		if(sessionBean.getUserOrgCode().equals(org2.getOrgcode())) {//市州安监科人员
//			type = "1";
//			zrsBean.setZrslx("01");
//		}else if (sessionBean.getUserOrgBiztype().equals(OrgUtils.ORGBIZTYPE_AJBM)) {//安全部门人员
//			type = "2";
//			zrsBean.setZrslx("02");
//		}
//		if(org3 != null) {
//			if(sessionBean.getUserOrgCode().equals(org3.getOrgcode())) {//省安监科人员
//				type = "1";
//				zrsBean.setZrslx("01");
//			}else if (sessionBean.getUserOrgBiztype().equals(OrgUtils.ORGBIZTYPE_AJBM)) {//安全部门人员
//				type = "2";
//				zrsBean.setZrslx("02");
//			}
//		}
//		modelAndView.addObject("type", type);
//		modelAndView.addObject("entity", zrsBean);
//		modelAndView.addObject("orgInfo", org);
//		modelAndView.addObject("sessionInfo", sessionBean);
//		modelAndView.addObject("level", org.getOrglevel());
//		return modelAndView;
//	}
//
//	@RequestMapping("/save")
//	public ModelAndView save(ZrsBean entity, SessionBean sessionBean){
//		sessionBean = getSessionBean(sessionBean);
//		ModelAndView mv = new ModelAndView(getViewName(sessionBean));
//		Map<String, Object> model = new HashMap<String, Object>();
//		try {
//			//发起人是省级，被签订单位是市州，将省数据传到市州
//			//当领导审批后同样在传递数据到市州服务器
//			sessionBean = getSessionBean(sessionBean);
//			if( null != entity ){
//					  StringBuffer sbu = new StringBuffer();
//					    char[] chars = entity.getQdnr().toString().toCharArray();
//					    for (int i = 0; i < chars.length; i++) {
//					    	if((int)chars[i]> 31 || (int)chars[i] < 14){
//							            sbu.append(chars[i]);
//					    	}
//					    }
//					entity.setQdnr(sbu.toString());
//					zrsService.save(entity, sessionBean);
//			}
//			model.put(AppConst.STATUS, AppConst.SUCCESS);
//			model.put(AppConst.MESSAGES, getAddSuccess());
//		} catch (RuntimeException e) {
//			logger.error(e.getLocalizedMessage(), e);
//			model.put(AppConst.STATUS, AppConst.FAIL);
//			model.put(AppConst.MESSAGES, e.getMessage());
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//			model.put(AppConst.STATUS, AppConst.FAIL);
//			model.put(AppConst.MESSAGES, getAddFail());
//		}
//		mv.addObject(AppConst.MESSAGES, new Gson().toJson(model));
//		return mv;
//	}
//
//
//	/**
//	 * @Title:getDataFromProvice
//	 * @Description: TODO(省下发责任书签订到市州部门)
//	 * @return map 返回类型
//	 * @throws
//	 */
//	@RestfulAnnotation(serverId="3")
//	@RequestMapping(value="/getDataFromProvice", method= RequestMethod.POST)
//	@ResponseBody
//    public String getDataFromProvice(String cipherCode, String entityData, String zrsqdrentityData, String sessionData,String isdel){
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (!"doSendToCity".equals(cipherCode)) {
//			map.put(AppConst.STATUS, AppConst.FAIL);
//			map.put(AppConst.MESSAGES, "系统错误");
//			return new Gson().toJson(map);
//		}
//		SessionBean sessionBean = JSON.parseObject(sessionData, new TypeReference<SessionBean>(){});
//		ZrsBean entity = JSON.parseObject(entityData, new TypeReference<ZrsBean>(){});
//		if	("false".equals(isdel)){
//			List<ZrsqdrBean> zrsqdrBeans = JSON.parseArray(zrsqdrentityData, ZrsqdrBean.class);
//			try {
//				zrsService.saveDataFromProvice(entity, zrsqdrBeans, sessionBean);
//				map.put(AppConst.STATUS, AppConst.SUCCESS);
//				map.put(AppConst.MESSAGES, getAddSuccess());
//			} catch (RuntimeException e) {
//				logger.error(e.getLocalizedMessage(), e);
//				map.put(AppConst.STATUS, AppConst.FAIL);
//				map.put(AppConst.MESSAGES, e.getMessage());
//			} catch (Exception e) {
//				logger.error(e.getLocalizedMessage(), e);
//				map.put(AppConst.STATUS, AppConst.FAIL);
//				map.put(AppConst.MESSAGES, getAddFail());
//			}
//
//		}else if("true".equals(isdel)){
//			try {
//				zrsService.delFromProvince(entity,sessionBean);
//				map.put(AppConst.STATUS, AppConst.SUCCESS);
//				map.put(AppConst.MESSAGES, getAddSuccess());
//			} catch (Exception e) {
//				logger.error(e.getLocalizedMessage(), e);
//				map.put(AppConst.STATUS, AppConst.FAIL);
//				map.put(AppConst.MESSAGES, getAddFail());
//			}
//		}
//		return new Gson().toJson(map);
//	    }
//
//	/**
//	 * @Title:getDataFromProvice
//	 * @Description: TODO(市州签订完成给省发送数据)
//	 * @return map 返回类型
//	 * @throws
//	 */
//	@RestfulAnnotation(serverId="3")
//	@RequestMapping(value="/getDataFromCity", method= RequestMethod.POST)
//	@ResponseBody
//    public String getDataFromCity(String cipherCode, String entityData, String sessionData){
//		Map<String, Object> map = new HashMap<String, Object>();
//		if (!"sendToProvince".equals(cipherCode)) {
//			map.put(AppConst.STATUS, AppConst.FAIL);
//			map.put(AppConst.MESSAGES, "系统错误");
//			return new Gson().toJson(map);
//		}
//
//		ZrsqdrBean entity = JSON.parseObject(entityData, new TypeReference<ZrsqdrBean>(){});
//		SessionBean sessionBean = JSON.parseObject(sessionData, new TypeReference<SessionBean>(){});
//		try {
//			zrsService.saveDataFromCity(entity, sessionBean);
//			map.put(AppConst.STATUS, AppConst.SUCCESS);
//			map.put(AppConst.MESSAGES, getAddSuccess());
//		} catch (RuntimeException e) {
//			logger.error(e.getLocalizedMessage(), e);
//			map.put(AppConst.STATUS, AppConst.FAIL);
//			map.put(AppConst.MESSAGES, e.getMessage());
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//			map.put(AppConst.STATUS, AppConst.FAIL);
//			map.put(AppConst.MESSAGES, getAddFail());
//		}
//
//			return new Gson().toJson(map);
//	    }
//
//	@RequestMapping("/exportExcel")
//	@ResponseBody
//	public Map<String, Object> exportExcel(SessionBean sessionBean,HttpServletResponse response){
//		sessionBean = getSessionBean(sessionBean);
//		Map<String, Object> model = new HashMap<String, Object>();
//		OrgOrganization org = OrgUtils.getSsdw(sessionBean);
//		List<List<?>> exportData = null;
//		if(OrgUtils.ORGLEVEL_SZGS.equals(org.getOrglevel()) && sessionBean.getUserOrgBiztype().equals("04")){
//			List<String> orgCodes = new ArrayList<>();
//			List<OrgOrganization> dwDowmDw = OrgUtils.getDwDowmDw2(sessionBean, true);
//			for(OrgOrganization organization : dwDowmDw){
//				orgCodes.add(organization.getOrgcode());
//			}
//			exportData = zrsService.getExportData(orgCodes);
//		}
//		String docName = "责任书统计表";
//		String[] describes = {"单位名称", "单位对单位下发数", "单位对单位签订数", "单位对部门下发数", "单位对部门签订数","部门对个人下发人数", "部门对个人签订人数","主管领导对分管领导下发数", "主管领导对分管领导签订数"};
//		XSSFWorkbook workBook = OfficeComponentsUtils.createExcelForArray(describes, exportData);
//		OutputStream out = null;
//    	try {
//    		String downLoadFileName = URLEncoder.encode(docName + ".xlsx", "UTF-8");
//    		String fileName = "attachment; filename=\"" + downLoadFileName + "\"";
//    		response.reset();
//    		response.setHeader("Content-Disposition", fileName);
//    		response.setContentType("application/x-msdownload");
//			if (workBook != null) {
//				out = response.getOutputStream();
//				workBook.write(out);
//				out.flush();
//			}
//    	} catch (Exception e) {
//    		logger.error(e.getLocalizedMessage(), e);
//    		model.put(AppConst.STATUS, AppConst.FAIL);
//    		model.put(AppConst.MESSAGES, "导出Excel失败！");
//    	} finally {
//    		if (out != null) {
//    			try {
//    				out.close();
//    			} catch (Exception e) {
//    				e.printStackTrace();
//    			}
//    		}
//    	}
//    	return model;
//	}
//}
