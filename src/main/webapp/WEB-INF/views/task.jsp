
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- Ignite UI Required Combined CSS Files -->
<link
	href="http://cdn-na.infragistics.com/igniteui/2018.1/latest/css/themes/infragistics/infragistics.theme.css"
	rel="stylesheet" />
<link
	href="http://cdn-na.infragistics.com/igniteui/2018.1/latest/css/structure/infragistics.css"
	rel="stylesheet" />

<script
	src="http://ajax.aspnetcdn.com/ajax/modernizr/modernizr-2.8.3.js"></script>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

<!-- Ignite UI Required Combined JavaScript Files -->
<script
	src="http://cdn-na.infragistics.com/igniteui/2018.1/latest/js/infragistics.core.js"></script>
<script
	src="http://cdn-na.infragistics.com/igniteui/2018.1/latest/js/infragistics.lob.js"></script>

<!-- 파일 업로드 -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>

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
									<textarea class="form-control z-depth-1"
										id="exampleFormControlTextarea6" rows="3" placeholder="업무명..."></textarea>
								</div>
							</div>

							<div id="boxs">
								<div class="form-group shadow-textarea2">
									<label for="exampleFormControlTextarea6"></label>
									<textarea class="form-control z-depth-1"
										id="exampleFormControlTextarea6" rows="3"
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
						<!-- updateday end-->
					</div>

				</div>
				<!-- #$##### -->
			</div>
	</div>

	
	<script src="resources/js/board/taskScript.js"></script>
</body>
</html>
