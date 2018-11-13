<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<!--     <script src="resources/js/jquery-3.3.1.min.js"></script> -->


<!-- 부트스트랩 기본 css -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!-- Ignite UI Required Combined CSS Files -->
<link
	href="http://cdn-na.infragistics.com/igniteui/2018.1/latest/css/themes/infragistics/infragistics.theme.css"
	rel="stylesheet" />
<link
	href="http://cdn-na.infragistics.com/igniteui/2018.1/latest/css/structure/infragistics.css"
	rel="stylesheet" />


<script src="resources/js/jquery-3.3.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<link rel="stylesheet" type="text/css"
	href="resources/css/board/boardcss.css">
<!-- topMenu.css -->
<link rel="stylesheet" type="text/css"
	href="resources/css/topMenu/topMenu.css?ver=3">
<!-- taskStyle.css -->
<link rel="stylesheet" type="text/css"
	href="resources/css/board/taskStyle.css?ver=2">


<!-- topMenu.js -->
<script src="resources/js/topMenu/topMenu.js?ver=2"></script>


</head>

<body>
	<table border="1px" style="width: 100%; height: 50px;">
		<tr>
			<td style="width: 13%"><i class="far fa-calendar-alt"></i> tatab
			</td>
			<td style="width: 7%" id="projectsBtn"><i class="fas fa-bolt"></i>
				&nbsp; ${projectName } &nbsp; <i class="fas fa-angle-down"></i></td>
			<td style="width: 5%" id="infoBtn"><i class="fas fa-info"></i></td>
			<td style="width: 50%">
				<!-- 테스크 모달 창 테스트 버튼 -->
				<button id="btnShowTaskModal" type="button" class="btn btn-primary"
					data-toggle="modal" data-target="#exampleModalLong">Task
					테스트 버튼</button>
			</td>
			<td style="width: 5%">
				<table class="activeBtn">
					<tr>
						<td align="center">9</td>
						<td rowspan="2"><i class="fas fa-angle-down"></i></td>
					</tr>
					<tr>
						<td>Active</td>
					</tr>
				</table>
			</td>
			<td style="width: 5%"><i class="far fa-clock"></i></td>
			<td style="width: 5%" class="activityBtn"><i class="fas fa-at"></i>
				<div onclick="history.back();" class="page_cover"></div>
				<div id="menu">
					<div onclick="history.back();" class="activityClose"></div>
					<!-- activity.jsp-->
					<jsp:include page="/WEB-INF/views/topMenu/topMenu_activity.jsp" />
				</div></td>
			<td style="width: 5%"><i class="fas fa-cog"></i></td>
			<td style="width: 5%"><i class="fas fa-user-circle"></i></td>
		</tr>
	</table>

	<!-- kanban board -->
	<div class="kanban-container2">

		<!-- kanban-col end -->
		<!-- kanban 시작 -->
		<div class="kanban-board colsortable">

			<!-- kanban-col section start -->
			<jsp:include page="/WEB-INF/views/kanban_col.jsp" />
			<!-- kanban-col end -->

			<a href="#" id="col-add-box"><i
				class="fas fa-plus-circle add col-add-btn"></i></a>
			<div id="endline"></div>
		</div>
		<!-- kanban 끝 -->
	</div>
	<!-- kanban board -->

	<!-- projects Modal -->
	<div id="projectsModal" class="projectsmodal">
		<jsp:include page="/WEB-INF/views/topMenu/topMenu_projects.jsp"></jsp:include>
	</div>
	<!-- projects Modal -->

	<!-- projectManage Modal -->
	<div id="manageModal" class="manageModal">
		<!-- Modal content -->
		<div class="manage-modal-content">
			<jsp:include page="/WEB-INF/views/topMenu/topMenu_projectManage.jsp"></jsp:include>
		</div>
	</div>
	<!-- projectManage Modal -->

	<!-- 모달 화면 -->
	<!-- 		<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true"> -->
	<div class="modal fade" id="exampleModalLong" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">Modal
							title</h5>
						<button type="button" class="close" style="margin : 0;" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				<!--  -->
				<div class="left-box">
					<div class="modal-body">
						<div id="container"> 
							<div id="boxs">
								<span>D-?</span> <img
									src="resources/img/board/bookmarkButton.png" width="30"
									height="30" /> <img src="resources/img/board/mButton.jpg"
									width="30" height="30" />
							</div>
							

							<div id="box1">
								<div class="form-group shadow-textarea">
									<label for="exampleFormControlTextarea6"></label>
									<textarea class="form-control z-depth-1 task_name"
										id="exampleFormControlTextarea6 task_name" rows="3" placeholder="업무명..."></textarea>
								</div>
							</div>

							<div id="boxs">
								<div class="form-group shadow-textarea2">
									<label for="exampleFormControlTextarea6"></label>
									<textarea class="form-control z-depth-1 task_content"
										id="exampleFormControlTextarea6 task_content" rows="3"
										placeholder="업무 내용..."></textarea>
								</div>
							</div>

							<div id="boxs">
								<div>
									<div class="form-group">
										<label>Upload Image</label>
										<div class="input-group">
											<span class="input-group-btn"> <span
												class="btn btn-default btn-file"> Browse… <input
													type="file" id="imgInp">
											</span>
											</span> <input type="text" class="form-control" readonly>
										</div>
										<img id='img-upload' />
									</div>

								</div>
							</div>

							<div id="boxs">
								<div id="myDIV" class="header">
									<span> <input type="text" id="myInput"
										placeholder="Comment..."> <span onclick="newElement()"
										class="addBtn">Add</span>
									</span>
								</div>
								<div>
									<ul id="myUL">
										<li>Created by</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save
							changes</button>
					</div>
				</div>
				<div class="center-line"></div>
				<div class="right-box">
						<!-- 할당 멤버 start-->
						<div class="right-box-item task-assigned-member-box">
							<div class="right-box-item-title task-assigned-member-box-name">할당멤버</div>
							<div class="task-assigned-member">
								<img res="resources/img/board/sort-up.png"
									class="task-assigned-member-img" />
								<p>name</p>
							</div>
						</div>
						<!-- 할당 멤버 end -->
						<!-- d-day start-->
						<div class="right-box-item task-dday-box">
							<div class="right-box-item-title task-dday-name">D-day
								<p>2018.11.12</p>
								<input type="text" id="datepicker1">
							</div>
						</div>
						<!-- d-day end-->
						<!-- startday start-->
						<div class="right-box-item task-start-day-box">
							<div class="right-box-item-title task-start-day-name">시작일</div>
							<p id="startDate"></p>
						</div>
						<!-- startday end-->
						<!-- updateday start-->
						<div class="right-box-item task-update-day-box">
							<div class="right-box-item-title task-update-day-name">수정일</div>
							<p>2018.11.12</p>
						</div>
						<!-- updateday start-->

					</div>

				</div>
				<!-- #$##### -->
			</div>

			<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
		</div>
	
</body>

<script>
	$(function() {
	  $( "#datepicker1" ).datepicker({
	  });
	});
</script>

<!-- task 관련 스크립트  - 부트스트랩 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<!-- script 참조 -->
<script
	src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js'></script>
<script src="resources/js/jquery-ui.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<script src="resources/js/board/boardjs.js?ver=4"></script>
<script src="resources/js/board/taskScript.js?ver=1"></script>

</html>