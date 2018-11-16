<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	href="resources/css/topMenu/topMenu.css?ver=4">
<!-- taskStyle.css -->
<link rel="stylesheet" type="text/css"
	href="resources/css/board/taskStyle.css?ver=3">
<!-- <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet"> -->

<!-- topMenu.js -->
<script src="resources/js/topMenu/topMenu.js?ver=2"></script>

<script>
	// update task
	function submit() {
		var task_name = $('.task_name').val();
		var task_content = $('.task_content').val();
		var dday = $('#datepicker1').val();
		var task_no = $('.task_no').val();
		
		console.log(task_name);
		console.log(task_content);
		console.log(dday);
		console.log(task_no);
		
		$('.task_name1').val(task_name);
		$('.task_content1').val(task_content);
		$('.dday1').val(dday);
		
		$('#updateTask').submit();
		
		// ajax로 값 넘기기
		$.ajax({
			url : "deadlineInsert.do",
			data : {'task_name': task_name,
					'alert_message' : dday
					},
			type : "get",
			success : function() {
					alert('마감기한 설정 완료!');
				}
		});
	}
	
	// 멤버 할당
	function assignMember() {
		console.log('멤버할당');
		document.getElementById("myForm").style.display = "block";
	}
</script>

</head>

<body>
<div class="table-box">
	<table border="1px" style="width: 100%; height: 50px;">
		<tr>
			<td style="width: 10%">
				<img class="board-logo" src="resources/img/index/tatab-logo1.png" />
			</td>
			<td style="width: 5%" id="infoBtn"><i class="fas fa-info"></i></td>
			
			<td style="width: 25%" id="projectsBtn" style="font-size: 2rem;">
				<i class="fas fa-bolt"></i>
				&nbsp; ${projectName } &nbsp; 
				<i class="fas fa-angle-down"></i>
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
			<td style="width: 5%" id="activityBtn"><i class="fas fa-at"></i>
				<div class="page_cover"></div>
				<div id="menu">
					<div class="activityClose" id="activityClose"></div>
					<!-- activity.jsp-->
					<jsp:include page="/WEB-INF/views/topMenu/topMenu_activity.jsp" />
				</div></td>
			<td style="width: 5%"><i class="fas fa-cog"></i></td>
			<td style="width: 5%"><i class="fas fa-user-circle"></i></td>
		</tr>
	</table>
</div>
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
								<div id="myDIV" class="header">
									<div class="nicknameText" id="" style="display:none;"></div>
									<span> <input type="text" id="myInput"
										placeholder="Comment..."> <span onclick="newElement()"
										class="addBtn">Add</span>
									</span>
								</div>
								<div>
									<ul id="myUL">
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" onclick="submit()">Save
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
								<p><i class="fas fa-plus-circle" onclick="assignMember()"></i></p>
							</div>
						</div>
						<!-- 할당 멤버 end -->
						
						<!-- 멤버리스트 보여주기-->
						<div>
							성수 연주 원석
							<div id="myForm"></div>
						</div>
						<!-- 멤버리스트 보여주기-->
						
						<!-- d-day start-->
						<div class="right-box-item task-dday-box">
							<div>
								<div class="right-box-item-title task-dday-name">D-day</div>
								<input type="text" id="datepicker1" placeholder="-">
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
							<p id="updateDate"></p>
						</div>
						<!-- updateday start-->

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
											</span> <input type="text" class="form-control" id="imgName" readonly>
										</div>
										<img id='img-upload' />
									</div>

								</div>
							</div>

				</div>
				<!-- #$##### -->
			</div>

			<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
		</div>
		
		<form id="updateTask" action="updateTask.do" method="post">
			<input type="hidden" class="task_name1" name="task_name">
			<input type="hidden" class="task_content1" name="task_content">
			<input type="hidden" class="dday1" name="dday">
			<input type="hidden" class="task_no" name="task_no">
		</form>
	
</body>

<script>
	$(function() {
	  $( "#datepicker1" ).datepicker({
		  dateFormat: 'yy.mm.dd'
	  });
	});
</script>
<script>
//activity 관련 업데이트
$(document).ready(function() {
	$("#activityBtn").click(function() {
		// 내용 ajax
		$.ajax({
			url : "topMenuActivity.do",
			type : "get",
			success : function(data) {
				
				console.log(data);
				$('.activityContent').empty();
				  for(i=0; i<data.length; i++) { // 프로필사진, 시간, 이름, 메시지, 프로젝트이름
    				var tag1 = '<div class="activityIcon" id="MyPageModalBtn"><img src="${pageContext.request.contextPath}/img/'+data[i].save_name+'"/>';
    				var tag2 = '</div><div class="activityTime">';
    				var tag3 = '</div><div class="activityDo">';
    				var tag4 = '</div><div class="activityTaskName">';
    				var tag5 = '</div>';
    				
    				var diffInfo = 0;
    				
    				if(data[i].diffMin/60/24 > 1)
    					diffInfo = parseInt(data[i].diffMin/60/24)+" days ago";
    				else if(data[i].diffMin/60 > 1)
    					diffInfo = parseInt(data[i].diffMin/60)+" hours ago";
    				else
    					diffInfo = (data[i].diffMin)+" mins ago";
    				var tag = tag1 + tag2 + diffInfo + tag3 + data[i].login_name + data[i].alert_message + tag4 + tag5;
    				$(tag).hide().appendTo('.activityContent').show(); 
				}
				// 여기에 "그 후 실행" 코드들이 들어가야 한다!	
				$('#menu').css('right', '0px');
				console.log('open done');
			}
			
		});
		$('#activityClose').click(function() {
			event.stopPropagation();
			$('#menu').css('right', '-302px');
			console.log('close done');
		});
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

<script src="resources/js/board/boardjs.js?ver=5"></script>
<script src="resources/js/board/taskScript.js?ver=4"></script>

</html>