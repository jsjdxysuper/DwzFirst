package com.kedong.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.kedong.bean.Person;
import com.kedong.bean.User;
import com.kedong.tool.DBOperate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AllAction extends BaseAction{

	private Map<String,String>comboxOne = new HashMap<String,String>();
	private Map<String,String>comboxTwo = new HashMap<String,String>();;
	
	private String sheng;
	private String shi;
	private String id;
	
	private Pager pages;
	private String orderField;
	private String orderDirection;
	
	private int pageNum;
	private int numPerPage;
	//select top (目标页值-1)*页大小,页大小 * from tableName;

	public String test(){
		Person p = new Person();
		p.setName("ding");
		String json = JSONObject.fromObject(p).toString();
		return this.renderJson(json);
	}
	
	public String test1(){
		
		return "list";
	}
	
	public String test2(){
		
		return this.renderHtml("sldfjk");
	}
	
	public String oneComboxGet(){
		List<String[]> shandongList = new ArrayList<String[]>();
		shandongList.add(new String[]{"bz","滨州"});
		shandongList.add(new String[]{"jn","济南"});
		shandongList.add(new String[]{"ta","泰安"});
		List<String[]> liaoningList = new ArrayList<String[]>();
		liaoningList.add(new String[]{"as","鞍山"});
		liaoningList.add(new String[]{"dl","大连"});
		liaoningList.add(new String[]{"jz","锦州"});
		
		String shandongStr = JSONArray.fromObject(shandongList).toString();
		String liaoningStr = JSONArray.fromObject(liaoningList).toString();
		comboxOne.put("sd", shandongStr);
		comboxOne.put("ln", liaoningStr);
		
		return renderJson(comboxOne.get(sheng));
	}
	
	public String twoComboxGet(){
		List<String[]>binzhouList = new ArrayList<String[]>();
		binzhouList.add(new String[]{"yx","阳信"});
		binzhouList.add(new String[]{"hm","惠民"});
		binzhouList.add(new String[]{"wd","无棣"});
		
		List<String[]>jinanList = new ArrayList<String[]>();
		jinanList.add(new String[]{"cq","长青"});
		jinanList.add(new String[]{"lx","历下"});
		jinanList.add(new String[]{"btq","趵突泉"});
		
		List<String[]>taianList = new ArrayList<String[]>();
		taianList.add(new String[]{"ts","泰山"});
		taianList.add(new String[]{"dp","东平"});
		
		List<String[]>anshanList = new ArrayList<String[]>();
		anshanList.add(new String[]{"hc","海城"});
		anshanList.add(new String[]{"ta","台安"});
		
		List<String[]>dalianList = new ArrayList<String[]>();
		dalianList.add(new String[]{"dsq","大石桥"});
		dalianList.add(new String[]{"dl","大连市"});
		
		comboxTwo.put("bz",JSONArray.fromObject(binzhouList).toString());
		comboxTwo.put("jn", JSONArray.fromObject(jinanList).toString());
		comboxTwo.put("ta", JSONArray.fromObject(taianList).toString());
		comboxTwo.put("as", JSONArray.fromObject(anshanList).toString());
		comboxTwo.put("dl", JSONArray.fromObject(dalianList).toString());
		return renderJson(comboxTwo.get(shi));
	}
	
	
	public String testOracle(){
		DBOperate db = new DBOperate(true, true);
		Vector<Vector<Object>> ret = null;
		try {
			db.connect("DING");
			String sqlStr = "select ID,NAME,AGE,CORP_ID from t_user t";
			ret = db.executeQuery(sqlStr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<User> users = new ArrayList<User>();
		for(int i=0;i<ret.size();i++){
			User u = new User();
			u.setId(ret.get(i).get(0).toString());
			u.setName(ret.get(i).get(1).toString());
			if(ret.get(i).get(2)!=null)
				u.setAge(Integer.parseInt(ret.get(i).get(2).toString()));
			if(ret.get(i).get(3)!=null)
				u.setCorp_id(ret.get(i).get(3).toString());
			users.add(u);
		}
		request.put("users", users);
		return "user";
		
	}
	
	public String userSearch(){
		pages = new Pager(pageNum,numPerPage);
		DBOperate db = new DBOperate(true, true);
		Vector<Vector<Object>> ret = null;
		try {
			db.connect("DING");
			int startNO = pages.getPageSize()*(pages.getPageNo()-1);
			int endNO = pages.getPageSize()*pages.getPageNo();
			String sqlStr = "select * from( "+
					"select A.*,Rownum RN from "+
					"(select * from t_user where 1=1) a where rownum <= "+endNO+" "+
					") where rn >= "+startNO;
//			String sqlStr = "select top "+
//			(pages.getPageSize()*(pages.getPageNo()-1))+
//			","+pages.getPageNo()+" ID,NAME,AGE,CORP_ID from t_user t";
			ret = db.executeQuery(sqlStr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<User> users = new ArrayList<User>();
		for(int i=0;i<ret.size();i++){
			User u = new User();
			u.setId(ret.get(i).get(0).toString());
			u.setName(ret.get(i).get(1).toString());
			if(ret.get(i).get(2)!=null)
				u.setAge(Integer.parseInt(ret.get(i).get(2).toString()));
			if(ret.get(i).get(3)!=null)
				u.setCorp_id(ret.get(i).get(3).toString());
			users.add(u);
		}
		pages.setPages(users);

		
		String sqlCount = "select count(*) from t_user";
		try {
			ret = db.executeQuery(sqlCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = Integer.parseInt(ret.get(0).get(0).toString());
		pages.setTotalCount(count);
		
		request.put("pages", pages);
		return "user";
	}
	
	public String getSheng() {
		return sheng;
	}

	public void setSheng(String sheng) {
		this.sheng = sheng;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public Pager getPages() {
		return pages;
	}

	public void setPages(Pager pages) {
		this.pages = pages;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	
}
