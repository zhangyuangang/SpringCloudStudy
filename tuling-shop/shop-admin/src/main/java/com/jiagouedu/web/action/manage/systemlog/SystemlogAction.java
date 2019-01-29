package com.jiagouedu.web.action.manage.systemlog;import com.jiagouedu.core.dao.page.PagerModel;import com.jiagouedu.services.manage.systemlog.SystemlogService;import com.jiagouedu.services.manage.systemlog.bean.Systemlog;import com.jiagouedu.web.action.BaseController;import com.jiagouedu.web.util.RequestHolder;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;/** * 系统日志管理 * @author wukong 图灵学院 QQ:245553999 */@Controller@RequestMapping("/manage/systemlog/")public class SystemlogAction extends BaseController<Systemlog> {	private static final long serialVersionUID = 1L;    @Autowired	private SystemlogService systemlogService;    private static final String page_toList = "/manage/systemlog/systemlogList";    private static final String page_toAdd = "/manage/systemlog/systemlogEdit";    private static final String page_toEdit = "/manage/systemlog/systemlogEdit";    private static final String page_systemlogListAndDetail = "/manage/systemlog/systemlogListAndDetail";    public SystemlogAction() {        super.page_toList = page_toList;        super.page_toEdit = page_toAdd;        super.page_toAdd = page_toEdit;    }    @Override	public SystemlogService getService() {		return systemlogService;	}    @Override	protected void selectListAfter(PagerModel pager) {		pager.setPagerUrl("selectList");	}	public void setSystemlogService(SystemlogService systemlogService) {		this.systemlogService = systemlogService;	}    @Override	public void insertAfter(Systemlog e) {		e.clear();	}	@RequestMapping("systemlogListAndDetail")	public String systemlogListAndDetail(@RequestParam(required = false)Integer type, Systemlog e) throws Exception{		if(type == null){			e.setType(Integer.valueOf(type));		}		selectList(RequestHolder.getRequest(), e);		return page_systemlogListAndDetail;	}}