<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- <%@ page session="false" %> --%>
<!DOCTYPE html>
<html>
    <head>
    <title>tatab</title>
    <!-- favicon 삽입 -->
    <link rel="icon" type="image/x-icon" href="<c:url value='/resources/img/main/favicon.ico' />"/>
    <meta name="msapplication-TileColor" content="#da532c">
	<meta name="theme-color" content="#ffffff">
    
        <!-- script 참조 -->
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/jquery-ui.js?ver=2"></script>
    <script src="resources/js/jquery-ui.min.js?ver=2"></script>
    
    <!-- css 참조 -->
    <link rel="stylesheet" type="text/css" href="resources/css/board/boardcss.css?ver=3">
    <link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.min.css">
    
    <!-- myPageModal -->
	<link href="<c:url value="/resources/css/board/MyPageModal.css?ver=3" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/main/BackgroundImageModal.css" />" rel="stylesheet">
    
    <!-- Bootstrap -->
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    
    <!-- <script src="resources/js/board/boardjs.js"></script> -->
    
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.5/jquery-ui.min.js'></script>
    
    <!-- font awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    
    <style>
   .board-background {
       background-image: url('resources/img/main/background-sample.jpg');
       background-attachment: fixed;
       background-repeat: no-repeat;
       background-size: 100% 100%;
       padding: 10px;
       height: 100vh;
   }
   .add:hover {
       font-size: 3rem;
       margin: 10px;
       color: darkgreen;
   }
    </style>
    
    </head>
    
    <body style="overflow-y: hidden;">
    <!-- board-background start -->
    	<%-- <div>${pageContext.request.contextPath}/img/${sessionScope.mainBackgroundVO.save_name}</div> --%>
     <c:choose>
    	<c:when test="${empty sessionScope.mainBackgroundVO.save_name}">
		    <div class="board-background" style="width: 100%; background-size: 100% 100%;">
		       <!-- topMenu include start -->
		       <jsp:include page="/WEB-INF/views/topMenu.jsp"/>
		       <!-- topMenu include end -->    
		   </div>          
  		 <!-- board-background end -->
    	</c:when>
    	<c:otherwise>
		    <div class="board-background" style="width: 100%; background-size: 100% 100%; background-image: url('${pageContext.request.contextPath}/img/${sessionScope.mainBackgroundVO.save_name}');">
		       <!-- topMenu include start -->
		       <jsp:include page="/WEB-INF/views/topMenu.jsp"/>
		       <!-- topMenu include end -->    
		   </div>          
  		 <!-- board-background end -->
    	</c:otherwise>
    </c:choose> 
    </body>
</html>