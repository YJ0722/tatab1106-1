<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>kanban-col</title>
    </head>
				
<body>
     <c:choose>
    	<c:when test="${empty colData}">
    	</c:when>
    	<c:otherwise>
    		<c:forEach items="${colData}" var="colList">
    		<c:set var="colno" value="${ colList.col_no }" />
	        <!-- kanban-col section start -->
	                <div class="kanban-col-box" id="${ colList.col_no }">
	                    <div class="kanban-col round-border">
	                        <!-- 이름 start -->
	                        <div class="kanban-head">
	                            <input type="text" class="col-title-input" style="display:none;" autofocus="">
	                            <p class="col-title-show-1" >${ colList.col_name }</p>
	                            <img class="toggle body-up-img" src="resources/img/board/sort-up.png">
	                            <img class="toggle body-down-img" src="resources/img/board/sort-down.png">
	                        </div>
	                        <!-- 이름 end -->
	
	                        <!-- 내용 start -->
	                        <div class="kanban-body tasksortable" id="kanban-body">
	                            <!-- task start -->
	                           	<%-- <jsp:include page="/WEB-INF/views/kanban_task.jsp"/> --%>
	                            <!-- task end -->
	                            
	                            
	                            <c:choose>
							    	<c:when test="${empty taskData}">
							    	</c:when>
							    	<c:otherwise>
							    		<c:forEach items="${taskData}" var="taskList">
		                            		<c:choose>
								    			<c:when test="${ taskList.col_no eq colno }">
											        <!-- task start -->
											        <c:if test="${taskList.status == 'C' }">
											        <div class="task round-border ui-state-default" id="${ taskList.task_no }" style="background-color:rgba(34,139,34,0.5);">
											            <div class="task-inner">
											                <!-- task name start -->
											                <div class="task-label">
											                	<p class='task-content-p' style="text-decoration: line-through;">${ taskList.task_name }</p>
											                </div>
											                <!-- task name end -->
											                <!-- task content start -->
											                <div class="task-content">
											                	<p class='task-content-p task-content-content' style="text-decoration: line-through;">${ taskList.task_content }</p>
											                </div>
											                <!-- task content end -->
											            </div>
											        </div>
											        </c:if>
											        <c:if test="${taskList.status == 'O' }">
											        <div class="task round-border ui-state-default" id="${ taskList.task_no }">
											            <div class="task-inner">
											                <!-- task name start -->
											                <div class="task-label">
											                	<p class='task-content-p'>${ taskList.task_name }</p>
											                </div>
											                <!-- task name end -->
											                <!-- task content start -->
											                <div class="task-content">
											                	<p class='task-content-p task-content-content'>${ taskList.task_content}</p>
											                </div>
											                <!-- task content end -->
											            </div>
											        </div>
											        </c:if>
											        <!-- task end -->
										        </c:when>
										        </c:choose>
							            </c:forEach>
							    	</c:otherwise>
							    </c:choose>
	                            
	                            
	                            <div class="add-task-line"></div>
	                        </div>
	                        <div class="add-task-box">
	                            <a href="#"><i class="fas fa-plus-circle task-add-btn add"></i></a>
	                        </div>
	                    </div>
	                </div>
            </c:forEach>
    	</c:otherwise>
    </c:choose>
	
	<form style="width:0px; float:left;" id="insertCol" action="insertCol.do" method="post">
		<input id="colName" type="hidden" name="ColName">
	</form>	
</body>
    <!-- script 참조 -->
    <!-- <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js'></script> -->
    <script src="resources/js/jquery-ui.js"></script>
    <!-- <script src="resources/js/jquery-ui.min.js"></script> -->
    <!-- <script src="resources/js/board/boardjs.js"></script> -->
    <script type="text/javascript">
    $(function(){
    	$('.task-content-content').each(function(){
    		var content = $(this).html();
	    	content = content.replace(/[\r\n]/g, "<br>");
	    	$(this).html(content);
    	})
    })
    </script>
</html>