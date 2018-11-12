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
											            <div class="task-inner">
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
	                            
</body>
</html>