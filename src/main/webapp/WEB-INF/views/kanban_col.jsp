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
	        <!-- kanban-col section start -->
	                <div class="kanban-col-box" id="kanban-col-box">
	                    <div class="kanban-col round-border">
	                        <!-- 이름 start -->
	                        <div class="kanban-head">
	<!--                            <input type="text" class="col-title-input" autofocus>-->
	                            <p class="col-title-show-1" >${ colList.col_name }</p>
	                            <img class="toggle body-up-img" src="resources/img/board/sort-up.png">
	                            <img class="toggle body-down-img" src="resources/img/board/sort-down.png">
	                        </div>
	                        <!-- 이름 end -->
	
	                        <!-- 내용 start -->
	                        <div class="kanban-body tasksortable" id="kanban-body">
	                            <!-- task start -->
	                           	<jsp:include page="/WEB-INF/views/kanban_task.jsp"/>
	                            <!-- task end -->
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

</body>
    <!-- script 참조 -->
    <!-- <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js'></script> -->
    <script src="resources/js/jquery-ui.js"></script>
    <!-- <script src="resources/js/jquery-ui.min.js"></script> -->
    <!-- <script src="resources/js/board/boardjs.js"></script> -->
</html>