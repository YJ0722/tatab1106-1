package com.bit.tatab.board.vo;

public class ChecklistVO {

	private int task_no;
	private String task_checklist;
	private String status;
	public ChecklistVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChecklistVO(int task_no, String task_checklist, String status) {
		super();
		this.task_no = task_no;
		this.task_checklist = task_checklist;
		this.status = status;
	}
	public int getTask_no() {
		return task_no;
	}
	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}
	public String getTask_checklist() {
		return task_checklist;
	}
	public void setTask_checklist(String task_checklist) {
		this.task_checklist = task_checklist;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ChecklistVO [task_no=" + task_no + ", task_checklist=" + task_checklist + ", status=" + status + "]";
	}
	
	
	
}
