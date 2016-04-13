package com.kedong.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.Action;


@SuppressWarnings("serial")
public class BaseAction implements Action, RequestAware, ServletResponseAware, SessionAware, ApplicationAware, Serializable {
	
	protected static final Logger LOG = Logger.getLogger(BaseAction.class);
	public String version = "v2.0";
	
	//作用域
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
	protected HttpServletResponse response;
	
	//Struts2返回值
	protected final static String INDEX = "index";
	protected final static String LIST = "list";
	protected final static String INIT = "init";
	protected final static String EDIT = "edit";
	protected final static String SAVE = "save";
	protected final static String CHART = "chart";
	protected final static String LOGOUT = "logout";
	
	//操作方式
	protected String operation;
	protected final static String ADD = "add";	//添加
	protected final static String EDT = "edt";	//编辑
	protected final static String DTL = "dtl";	//详情
	protected final static String COP = "cpy";	//拷贝
	
	//分页参数
	protected Integer pageNo = 1;
	protected Integer pageSize = 10;
	
	//主键，如果是联合主键使用 :链接
	protected String primaryKey;
	protected String t;
	
	protected String menuid;
	
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}
	
	public String getApplactionPath() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		return basePath;
	}
	
	public InputStream getResourceAsStream(String resource) {
		return getServletContext().getResourceAsStream(resource);
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	/**
	 * 直接输出流文件
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	protected String renderFile(File file) throws FileNotFoundException {
		if (file == null) {
			LOG.error("下载文件失败, 信息：未找到的文件[file=null]");
			throw new FileNotFoundException();
		}
		return renderFile(file.getName(), file);
	}
	
	/**
	 * 直接输出流文件
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	protected String renderFile(String fileName, File file) {
		try {
			fileName = new String(fileName.getBytes(), "ISO8859-1");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);
			ServletOutputStream os = response.getOutputStream();
			InputStream is = new FileInputStream(file);
			byte[] buffer = new byte[2048];
			int n = -1;
			while ((n = is.read(buffer, 0, buffer.length)) != -1) {
				os.write(buffer, 0, n);
			}
			os.flush();
			os.close();
			is.close();
		} catch (UnsupportedEncodingException e) {
			LOG.error("下载文件失败, 信息：文件名编码转换错误, e:" + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			LOG.error("下载文件失败, 信息：未找到的文件, e:" + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("下载文件失败, 信息：文件输出失败, e:" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	protected String render(String text, String contentType) {
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 直接输出字符串.
	 */
	protected String renderText(String text) {
		return render(text, "text/plain;charset=UTF-8");
	}

	/**
	 * 直接输出字符串GBK编码.
	 */
	protected String renderHtmlGBK(String html) {
		return render(html, "text/html;charset=GBK");
	}
	
	protected String renderHtml(String html) {
		return render(html, "text/html;charset=UTF-8");
	}

	/**
	 * 直接输出XML.
	 */
	protected String renderXML(String xml) {
		return render(xml, "text/xml;charset=UTF-8");
	}
	
	/**
	 * 直接输出Json
	 * @return
	 */
	protected String renderJson(String json){
		return render(json, "text/json;charset=UTF-8");
	}
	
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String index() {
		return INDEX;
	}
	
	public String init() throws Exception {
		return INIT;
	}
	
	public String edit() throws Exception {
		return EDIT;
	}
	

	/////////////////Getters And Setters/////////////////
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}


	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
