<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>kanban-task</title>
</head>
    
<body>
     
	                            <c:choose>
							    	<c:when test="${empty taskData}">
							    	<script>alert('null');</script>
							    	</c:when>
							    	<c:otherwise>
							    		<c:forEach items="${taskData}" var="taskList">
		                            		<c:choose>
								    			<c:when test="${ taskList.col_index eq colindex }">
											        <!-- task start -->
											        <div class="task round-border ui-state-default">
											            <div class="task-inner btn btn-primary" id="btnShowTaskModal">
											                <!-- task name start -->
											                <div class="task-label">
											                    <p>${ taskList.task_name }</p>
											                </div>
											                <!-- task name end -->
											                <!-- task content start -->
											                <div class="task-content">
											                    <p>${ taskList.task_content }</p>
											                </div>
											                <!-- task content end -->
											            </div>
											        </div>
											        <!-- task end -->
										        </c:when>
										        </c:choose>
							            </c:forEach>
							    	</c:otherwise>
							    </c:choose>
							    
							    <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                    <div id="container">
                        <div id="boxs">
                            <span>D-?</span>
                            <img src="resources/img/board/bookmarkButton.png" width="30" height="30"/>
                            <img src="resources/img/board/mButton.jpg" width="30" height="30"/>
                        </div>

                        <div id="box1">
                        <div class="form-group shadow-textarea">
                            <label for="exampleFormControlTextarea6"></label>
                            <textarea class="form-control z-depth-1" id="exampleFormControlTextarea6" rows="3" placeholder="업무명..."></textarea>
                        </div>
                    </div>

                        <div id="boxs">
                        <div class="form-group shadow-textarea2">
                            <label for="exampleFormControlTextarea6"></label>
                            <textarea class="form-control z-depth-1" id="exampleFormControlTextarea6" rows="3" placeholder="업무 내용..."></textarea>
                        </div></div>

                        <div id="boxs">
                        <div>
                                <div class="form-group">
                                    <label>Upload Image</label>
                                    <div class="input-group">
                                        <span class="input-group-btn">
                                            <span class="btn btn-default btn-file">
                                                Browse… <input type="file" id="imgInp">
                                            </span>
                                        </span>
                                        <input type="text" class="form-control" readonly>
                                    </div>
                                    <img id='img-upload'/>
                                </div>

                        </div></div>

                        <div id="boxs">
                        <div id="myDIV" class="header">
                            <span>
                          <input type="text" id="myInput" placeholder="Comment...">
                          <span onclick="newElement()" class="addBtn">Add</span>
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
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
              </div>
            </div>
          </div>
        </div>
	                            
</body>
</html>