package com.bit.tatab.board.vo;

public class BoardColVO {

	private int project_no;
	private int col_no;
	private int col_index;
	private String col_name;
	
	public BoardColVO() {
		super();
	}
	public BoardColVO(int project_no, String col_name) {
		super();
		this.project_no = project_no;
		this.col_name = col_name;
		
	}
	public BoardColVO(int project_no, int col_no, int col_index) {
		super();
		this.project_no = project_no;
		this.col_no = col_no;
		this.col_index = col_index;
	}
	public BoardColVO(int project_no, int col_no, int col_index, String col_name) {
		super();
		this.project_no = project_no;
		this.col_no = col_no;
		this.col_index = col_index;
		this.col_name = col_name;
	}
	
	public int getProject_no() {
		return project_no;
	}
	public void setProject_no(int project_no) {
		this.project_no = project_no;
	}
	public int getCol_no() {
		return col_no;
	}
	public void setCol_no(int col_no) {
		this.col_no = col_no;
	}
	public int getCol_index() {
		return col_index;
	}
	public void setCol_index(int col_index) {
		this.col_index = col_index;
	}
	public String getCol_name() {
		return col_name;
	}
	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}

	@Override
	public String toString() {
		return "BoardColVO [project_no=" + project_no + ", col_no=" + col_no + ", col_index=" + col_index
				+ ", col_name=" + col_name + "]";
	}
	
	
	
	
}
