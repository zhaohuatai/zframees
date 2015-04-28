package org.zht.framework.data;


public class CopyOfDataSet {

	private Long total;
	private DataTable rows=new DataTable();
	private DataTable footer=new DataTable();//合计列
	private String simpleFooter;//合计列
	
//	public DataSet() {
//	}
	public CopyOfDataSet( DataTable rows) {
		this.rows = rows;
	}
	public CopyOfDataSet(Long total, DataTable rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public CopyOfDataSet(Long total, DataTable rows,DataTable footer) {
		this.total = total;
		this.rows = rows;
		this.footer = footer;
	}
	/**
	 * 查询只有一条记录的时候
	 * @param key
	 * @return
	 */
	public Object getDataIfOneRecord(String key){
		
		if(key==null||this.rows==null|rows.size()==0){
			return null;
		}
		return rows.get(0).get(key);
		
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public DataTable getRows() {
		return rows;
	}
	public void setRows(DataTable rows) {
		this.rows = rows;
	}
	public DataTable getFooter() {
		return footer;
	}
	public void setFooter(DataTable footer) {
		this.footer = footer;
	}

	public String getSimpleFooter() {
		return simpleFooter;
	}

	public void setSimpleFooter(String simpleFooter) {
		this.simpleFooter = simpleFooter;
	}
	


}
