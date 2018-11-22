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
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/css/board/boardcss.css"> -->
<!-- topMenu.css -->
<link rel="stylesheet" type="text/css"
	href="resources/css/topMenu/topMenu.css?ver=4">
<!-- topMenu_activity.css -->
<link rel="stylesheet" type="text/css"
	href="resources/css/topMenu/topMenu_activity.css">
<!-- taskStyle.css -->
<link rel="stylesheet" type="text/css"
	href="resources/css/board/taskStyle.css?ver=3">
<!-- <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet"> -->
<!-- myPageModal -->
	<link href="<c:url value="/resources/css/board/MyPageModal.css?ver=3" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/main/BackgroundImageModal.css" />" rel="stylesheet">
<!-- font-awesome -->
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<!-- topMenu.js -->
<script src="resources/js/topMenu/topMenu.js?ver=2"></script>

</head>

<body>
<div class="table-box">
<!-- 원래 태그	<table border="1px" style="width: 100%; height: 50px;"> -->
<table id="topMenu-table">
		<tr>
			<td style="width: 7%">
				<a href="<c:url value="/userMain.do" />"><img class="board-logo" src="resources/img/main/tatabBold5.png" /></a>
			</td>
			<td style="width: 7%" id="projectsBtn" style="font-size: 2rem;">
				<img src="resources/img/topMenu/aircraft.png">
				&nbsp; ${projectName } &nbsp; 
				<i class="fas fa-angle-down "></i>
			</td>
			<td style="width: 1%" id="infoBtn"><img src="resources/img/topMenu/gear.png"></td>
			<td style="width: 34%"></td>
			<td style="width: 23%"></td>
<!-- 			<td style="width: 5%"> -->
<!-- 				<table class="activeBtn"> -->
<!-- 					<tr> -->
<!-- 						<td align="center">9</td> -->
<!-- 						<td rowspan="2"><i class="fas fa-angle-down"></i></td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>Active</td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
<!-- 			</td> -->
			<td style="width: 2%" class="rightIcon"><a><img src="resources/img/topMenu/alarm_bell.png"></a></td>
			<td style="width: 2%" class="rightIcon" id="activityBtn"><a><img src="resources/img/topMenu/world_asia.png"></a>
			<td style="width: 5%;" id="MyPageModalBtn2"><a>
               	<c:choose>
                  	<c:when test="${empty profileImgVO.save_name }">
                         <img src="<c:url value="/resources/img/main/single-01.svg" />">
                        	</c:when>
                        	<c:otherwise>
                        		<img src="${pageContext.request.contextPath}/img/${profileImgVO.save_name}">
                        	</c:otherwise>
                        </c:choose>
                   </a></td>
            	<div class="page_cover"></div>
				<div id="menu">
					<div class="activityClose" id="activityClose"><a></a></div>
					<!-- activity.jsp-->
					<jsp:include page="/WEB-INF/views/topMenu/topMenu_activity.jsp" />
				</div></td>
<!-- 			<td style="width: 5%"><i class="fas fa-cog"></i></td> -->
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
	<!-- projectManage Modal  -->

	<!-- 모달 화면 -->
	<!-- 		<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true"> -->
	<div class="modal fade" id="exampleModalLong" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
					<div class="modal-header" style="display: block;">
						<!-- 액티비티용 컴플리트 버튼 : 스크립트는 아래에! -->
						<img src="resources/img/task/task-complete-check-mark-white.png" id="taskCompleteBtn" onclick="completeTask()" />
<!-- 						<img src="resources/img/task/task-complete-check-mark-yellow.png" id="taskCompleteOKBtn" style="display: none;"/> -->
						<!-- 액티비티용 컴플리트 버튼 끝 -->
						
						<!-- D-DAY(D-0) -->
						<div id="ddayCountView">D</div>
						<div  id="ddayCount">-0</div>
						<!-- d-day -->
						
						<h5 class="modal-title" id="exampleModalLongTitle">
							<!-- Modal title -->
						</h5>
						<div class="close1">
                           <a class="manage-a"><i id="deleteTask" class="close fas fa-trash-alt" onclick="deleteTask()" ></i></a>
                        </div> 
						<button type="button" class="close" style="margin : 0; height: 54px; margin-right:5px;" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				<div class='center-box'> 
				<!--  -->
				<div class="left-box">
					<div class="modal-body">
						<div id="container"> 
							<div id="box1">
								<div class="form-group shadow-textarea">
									<label class="taskSubTitle" for="exampleFormControlTextarea6">Title</label>
									<textarea class="form-control z-depth-1 task_name_input"
										id="exampleFormControlTextarea6 task_name_input" rows="3" placeholder="업무명..."></textarea>
								</div>
							</div>

							<div id="boxs">
								<div class="form-group shadow-textarea2">
									<label class="taskSubTitle" for="exampleFormControlTextarea6">Content</label>
									<textarea class="form-control z-depth-1 task_content_input"
										id="task-content-input" rows="3" onkeyup="this.style.height='26px'; this.style.height = this.scrollHeight + 'px';"
										placeholder="업무 내용..."></textarea>
								</div>
							</div>

							<div id="boxs">
								<div id="myDIV" class="header">
									<div class="nicknameText" id="" style="display:none;"></div>
									<span> <input type="text" id="myInput"
										placeholder="Comment..."> <span onclick="	newElement()"
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

				</div>
				<div class="right-box">
						<!-- 할당 멤버 start-->
						<div class="right-box-item task-assigned-member-box">
							<div class="right-box-item-title task-assigned-member-box-name">할당멤버</div>
							<div class="task-assigned-member">
								<p><i class="fas fa-plus-circle addTaskMemberBtn" id="assignBtn" onclick="assignMember()"></i></p>
							</div>
						</div>
						<!-- 할당 멤버 end -->
						
						<!-- 멤버리스트 보여주기-->
						<div class="assigneeList" style="margin-bottom: 50px;">
							assignee list
						</div>
							<div id="myForm">
								<input type="text" id="assignee" name ="assignee">
								<button id="assigneeBtn" onclick="setAssignee()">할당</button>
							</div>
						<!-- 멤버리스트 보여주기 끝-->
						
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

					<div id="boxs" class="filebox" style="margin:0px">
								<div>
									<div class="form-group">
										<label class="uploadLabel">Upload File</label>
										<div class="input-group">
											<span class="input-group-btn"> 
												<span class="btn btn-default btn-file"> Browse… 
													<input class="file" type="file" id="file" name="file">
												</span>
											</span>
										</div>
										 <input type="text" class="form-control" id="task_ori_name" readonly>
										<!-- <img id='img-upload' /> -->
									</div>

								</div>
							</div>
						<!-- 체크리스트 -->
							<div class="taskChecklist" id="taskChecklist">
								<img src="<c:url value="/resources/img/main/plus.png" />">&nbsp;&nbsp;Add Checklist Item
							</div>
							<div class="checkWrapper"></div>
					</div>
					</div>
					<!-- 버튼! -->
					<div class="modal-footer">
						<button type="button" class="closeBtn" data-dismiss="modal">Close</button>
						<button type="button" class="saveBtn" onclick="my_submit()">Save
							changes</button>
					</div>
							

				</div>
				<!-- #$##### -->
			</div>

			<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
		</div>
		
		<form id="updateTask" action="updateTask.do" method="post" enctype="multipart/form-data">
			<input type="hidden" class="update_task_name" name="task_name">
			<input type="hidden" class="update_task_content" name="task_content">
			<input type="hidden" class="update_d_day" id="datepicker1" name="dday">
			<input type="hidden" class="update_task_no" name="task_no">
			<input type="hidden" class="update_file" id="file" name="file">
		</form>
	<!-- 모달 : MyPage -->
        <div id="MyPageModal" class="MyPageModal">
            <!-- MyPage 해당 컨텐츠 -->
            <div class="MyPageModalContent2">
                <div class="container">
                    <div class="row2">
                        <div class="col-lg-3 col-sm-6">
                            <div class="card hovercard">
                                <div class="cardheader">
                                </div>
                                <div class="contentSection"></div>
                                <div class="avatar">
                                	<c:choose>
                                    	<c:when test="${empty profileImgVO.save_name }">
		                                    <img src="https://i.stack.imgur.com/34AD2.jpg">
                                    	</c:when>
                                    	<c:otherwise>
                                    		<img src="${pageContext.request.contextPath}/img/${profileImgVO.save_name}">
                                    	</c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="info">
                                    <div class="title">
                                        <a><c:out value="${ myPageVO.login_name }" /></a>
                                    </div>
                                    <div class="desc"><c:out value="${ myPageVO.dob }" /></div>
                                    <div class="desc"><c:out value="${ myPageVO.login_email }" /></div>
                                    <h4><c:out value="${ myPageVO.motto }" /></h4>
                                </div>
                                <div class="bottom">
                                    <a href="<c:url value="/myPage.do" />"><img src="<c:url value="/resources/img/main/gear.png" />"/></a>                              
                                </div>
                                <div class="logout">
                                	<a href="googleLogout.do" onClick="signOut();">
                                    	LOGOUT 
                                    	<i class="fas fa-sign-out-alt"></i>
                                    </a>      
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- MyPage 해당 컨텐츠 끝 -->
        </div>
        <!-- 모달 끝 -->
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
    				var tag1 = '<div class="activityIcon" id="activityIcon"><img src="${pageContext.request.contextPath}/img/'+data[i].save_name+'"/>';
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
    				var tag = tag1 + tag2 + diffInfo + tag3 + data[i].alert_message + tag4 + tag5;
    				$(tag).hide().appendTo('.activityContent').show(); 
				}
				// 여기에 "그 후 실행" 코드들이 들어가야 한다!	
					event.stopPropagation();
				if($('#menu').css('right') == '-302px') {
					$('#menu').css('right', '0px');
				} else {
					$('#menu').css('right', '-302px');
				}
			}
			
		});
		$('#activityClose').click(function() {
			event.stopPropagation();
			$('#menu').css('right', '-302px');
		});
	});
});
</script>
<script>
	// complete task
	function completeTask() {
		var taskNo = $('.modal-content').attr('id');
		
		$.ajax({
			url : "taskStatusComplete.do",
			data : {'task_no': taskNo,
					},
			type : "get",
			success : function(data) {
// 					$('#taskCompleteBtn').hide();
// 					$('#taskCompleteOKBtn').show();
					$('.modal-header').css('background-color', 'forestgreen');
					$('.saveBtn, .addBtn').css('border-color', 'forestgreen');
					$('.saveBtn, .addBtn').css('background', 'forestgreen');
					$('.task_name, .task_content').prop('readonly', true);
					$('.saveBtn').prop("disabled", true);
					$('.task_name').css('text-decoration', 'line-through');
					$('.task_content').css('text-decoration', 'line-through');
				}
		})
		
		// ajax로 값 넘기기
		$.ajax({
			url : "completeTask.do",
			data : {'task_no': taskNo,
					},
			type : "get",
			success : function() {
					alert('작업완료 알림 완료!');
				}
		});
	}
	</script>
	<script>
	// set assignee
	function setAssignee() {
		var taskNo = $('.modal-content').attr('id');
		var assignee = $('#assignee').val();
		
		if($('#assignee').val().length == 0) {
			alert('아이디를 입력해주세요');
			false;
		} else {
			// ajax로 값 넘기기 - 유저 추가
			$.ajax({
				url:'addAssignee.do',
				type:'get',
				data:{'assignee':assignee},
				success:function(result){
					if(result == 'true'){
						alert('task member 추가완료');
						
						// 멤버리스트 관련 for문
// 						$('.assigneeList').empty();
						
						// 추가멤버 입력 창 안보이게 하기
						obj = document.getElementById("myForm");
						obj.style.display = "none";
						
						$.ajax({
							url : "selectAllTask.do",
							type : "post",
							data : {
								'task_no' : taskNo
							},
							success : function(data) {
								if(data != null) {
						//console.log('memberList:'+data.memberList);
									for(i=0; i<data.memberList.length; i++) {
										console.log('잘 나오니? : ' + data.memberList[i]);
										var tag1 = '<div class="nameList">';
										var tag2 = '</div>';
		    							var tag = tag1 + data.memberList[i].login_name + tag2;
		    							$(tag).hide().appendTo('.assigneeList').show();
									}
								}
							}
						});
					}else {
						alert('없는 아이디');
					}
				}
			});
		
			// ajax로 액티비티 값 넘기기
			$.ajax({
				url : "setAssignee.do",
				data : {'task_no': taskNo,
						'login_name': assignee
						},
				type : "get",
				success : function() {
						alert('작업완료 알림 완료!');
					}
			});
		}
	};
	</script>
	<script>
		
		
		// 텍스트 입력 후 바깥 클릭 시 text 확정 및 데이터 ajax
       /*  $(document).ready(function(){
			$('#fixedChecklist').click(function() { */
			function setChecklist() {
				var taskNo = $('.modal-content').attr('id');
				var fixedChecklist = $('#checkName').val();
				
				if($('#checkName').val().length == 0) {
					// 입력값 없을 때 - 원래대로 돌리기
					$('.checkMiddleWrapper').first().remove();
					$('.taskChecklist').css('display', 'block');
				} else {
					// ajax로 값 넘기기
					alert('fixedChecklist 값 : ' + fixedChecklist);
					$.ajax({
						url:'addChecklist.do',
						type:'get',
						data:{'fixedChecklist':fixedChecklist},
						success: function() {
							alert('체크리스트 추가 완료!');
							
							// 체크리스트 관련 for문
							$('.taskChecklist').css('display', 'block');
							getTaskCheckList(taskNo);
						}
					});
				}
				return false;
			};
		
	</script>

<script>
	// update task/* 
	function my_submit() {
			
		var update_task_no = $('.modal-content').attr('id');
		$(".update_task_no").val(update_task_no);
		var update_task_name = $('#task_name_input').text();
		$(".update_task_name").val(update_task_name);
		var update_task_content = $('#task-content-input').text();
		alert($('#task-content-input').text());
		$(".update_task_content").html(update_task_content);
		var update_d_day = $('#datepicker1').val();
		$(".update_d_day").val(update_d_day);
		//var file = $('.file');
		var update_file = $('#task_ori_name').val();
		$(".update_file").val(update_file);
		
		/*
		var task_name = $('.task_name').text();
		var task_content = $('#task_content-input').text();
		//var dday = $('.dday').val();
		var dday = $('#datepicker1').val();
		var task_no = $('.modal-content').attr('id');
		*/
		
		console.log('.......................');
		console.log('ok.task_no ' + update_task_no);
		console.log('ok.task_name ' + update_task_name);
		console.log('ok.task_content ' + update_task_content);
		console.log('ok.task_dday ' + update_d_day);
		console.log('file 이름 : ' + update_file);
		console.log('.......................');
		
		/* 필요 없을 듯
		$('.task_name').val(task_name);
		$('.task_content').val(task_content);
		$('.dday1').val(dday);
		$('.task_no').val(task_no);
		*/
		
		$('#updateTask').append(file);
		// 잠시 주석
		// $('#updateTask').submit();
		
		
		// ajax로 값 넘기기
		/*
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
		*/
		
		return false;
	} 
	
	// 멤버 할당
	function assignMember() {
		obj = document.getElementById("myForm");
		if(obj.style.display == "none" || obj.style.display == "") // 최초값이 ""이라서 조건문에 반영!
			obj.style.display = "block";
		else
			obj.style.display = "none";
			
	}
	
	// task 삭제
	function deleteTask() {
		console.log('task 삭제');
		if(confirm("Task를 삭제하시겠습니까?") == true) {
    		location.href="deleteTask.do";
		}
		return false;   
	}

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
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> -->

<script src="resources/js/board/boardjs.js?ver=6"></script>
<script src="resources/js/board/taskScript.js?ver=4"></script>
<script type="text/javascript">
function getTaskCheckList(task_no){
	console.log('보낸 태스크번호:'+task_no);
	$.ajax({
		url:'taskCheckList.do',
		method:'get',
		data:{'task_no':task_no},
		success:function(result){
			console.log('check result:'+result);
			$('.checkWrapper').empty();
			// 체크리스트 관련 for문
			$(result).each(function(index, data){
				console.log('체크리스트 잘 나오니?' + data);
				console.log('시퀀스 나와라 : ' + data.checklist_no);
				var tag1 = '<div class="checkInnerWrapper"><input type="checkbox" class="yyj" id="cbx' + data.checklist_no + '" style="display: none;">';
				var tag2 = '<label for="cbx' + data.checklist_no + '" class="check">';
				var tag3 = '<svg width="18px" height="18px" viewBox="0 0 18 18">';
				var tag4 = '<path d="M1,9 L1,3.5 C1,2 2,1 3.5,1 L14.5,1 C16,1 17,2 17,3.5 L17,14.5 C17,16 16,17 14.5,17 L3.5,17 C2,17 1,16 1,14.5 L1,9 Z"></path>';
				var tag5 = '<polyline points="1 9 7 14 15 4"></polyline></svg></label></div>'; 
				var tag6 = '<input type="text" id="checkName" value="';
				var tag7 = '" readonly>&nbsp;<br>';
				var tag = tag1 + tag2 + tag3 + tag4 + tag5 + tag6 + data['task_checklist'] + tag7;
				$('.checkWrapper').prepend(tag);	
			 
			})
			
			$('#cbx').on('click', function(){
				console.log($(this).is(':checked'));	
			})
			
			$('#taskChecklist').show();
		}
	})
}

$(function(){
	
		$('.task').on('click', function(){
			getTaskCheckList($(this).attr('id'));	
			
		})	
		
		// 체크리스트 없을 때 - 클릭 시 체크박스 하나씩 생성 - 체크리스트 추가버튼 클릭했을 때
		$('.taskChecklist').click(function() {
			$('.taskChecklist').css('display', 'none');
			var tag1 = '<div class="checkMiddleWrapper"><div class="checkInnerWrapper"><input type="checkbox" class="yyj" id="cbx" style="display: none;">';
			var tag2 = '<label for="cbx" class="check">';
			var tag3 = '<svg width="18px" height="18px" viewBox="0 0 18 18">';
			var tag4 = '<path d="M1,9 L1,3.5 C1,2 2,1 3.5,1 L14.5,1 C16,1 17,2 17,3.5 L17,14.5 C17,16 16,17 14.5,17 L3.5,17 C2,17 1,16 1,14.5 L1,9 Z"></path>';
			var tag5 = '<polyline points="1 9 7 14 15 4"></polyline></svg></label></div>'; 
			var tag = tag1 + tag2 + tag3 + tag4 + tag5 + '<input type="text" id="checkName">&nbsp;<button id="fixedChecklist" onclick="setChecklist()">완료</button><br></div>';	
			
			$('.checkWrapper').prepend(tag);
			return false;
		});
		
})

</script>

</html>