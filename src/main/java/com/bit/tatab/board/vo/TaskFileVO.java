package com.bit.tatab.board.vo;

public class TaskFileVO {

	private int task_no;
	private String task_ori_name;
	private String task_save_name;
	public TaskFileVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaskFileVO(int task_no, String task_ori_name, String task_save_name) {
		super();
		this.task_no = task_no;
		this.task_ori_name = task_ori_name;
		this.task_save_name = task_save_name;
	}
	public int getTask_no() {
		return task_no;
	}
	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}
	public String getTask_ori_name() {
		return task_ori_name;
	}
	public void setTask_ori_name(String task_ori_name) {
		this.task_ori_name = task_ori_name;
	}
	public String getTask_save_name() {
		return task_save_name;
	}
	public void setTask_save_name(String task_save_name) {
		this.task_save_name = task_save_name;
	}
	@Override
	public String toString() {
		return "TaskFileVO [task_no=" + task_no + ", task_ori_name=" + task_ori_name + ", task_save_name="
				+ task_save_name + "]";
	}
	
	
	
}
