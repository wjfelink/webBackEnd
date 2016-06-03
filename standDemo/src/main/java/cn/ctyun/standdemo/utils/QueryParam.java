package cn.ctyun.standdemo.utils;

import java.util.HashMap;
import java.util.Map;

/** 
 * 查询参数
 * @author  woniu
 * @version V1.0  CreateTime：2014-6-6 下午1:59:05 
 */
public class QueryParam {
	
	/** 检索字段和值 */
	private Map<String, Object> params = new HashMap<String, Object>();
	/** 排序字段和顺序 */
	private Map<String, String> orderBys = new HashMap<String, String>();
	
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public void addParam(String key, Object value) {
		if(this.params == null){
			synchronized (this.params) {
				if(this.params == null){
					this.params = new HashMap<String, Object>();
				}
			}
		}
		this.params.put(key, value);
	}
	public void removeParam(String key) {
		if(this.params != null){
			synchronized (this.params) {
				this.params.remove(key);
			}
		}
	}
	
	public Map<String, String> getOrderBys() {
		return orderBys;
	}
	public void setOrderBys(Map<String, String> orderBys) {
		this.orderBys = orderBys;
	}
	public void addOrderBy(String key, String value) {
		if(this.orderBys == null){
			synchronized (this.orderBys) {
				if(this.orderBys == null){
					this.orderBys = new HashMap<String, String>();
				}
			}
		}
		this.orderBys.put(key, value);
	}
	

}
