<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
    <head>
    <title>tatab</title>
    
    
    <!-- script 참조 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    <!-- css 참조 -->
    <link rel="stylesheet" type="text/css" href="resources/css/board/boardcss.css">
    <link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.min.css">
    
    <!-- Bootstrap -->
    <!-- 합쳐지고 최소화된 최신 CSS --> 
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    
    
    <!-- font awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!-- 
    <script src="resources/js/jquery-ui.js"></script>
    <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.5/jquery-ui.min.js'></script>
     -->
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="resources/js/jquery-ui.js"></script>
    <script src="resources/js/jquery-ui.min.js"></script>
    <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.5/jquery-ui.min.js'></script>
    합쳐지고 최소화된 최신 자바스크립트
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
     -->
    
    
    <style>
   .board-background {
       background-image: url('resources/img/board/backgroundImg.jpg');
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
    
    <body>
    <!-- board-background start -->
    <div class="board-background" style="width: 100%; background-size: 100% 100%;">
       <!-- topMenu include start -->
       <jsp:include page="/WEB-INF/views/topMenu.jsp"/>
       <!-- topMenu include end -->    
      
   </div>          
   <!-- board-background end -->
    </body>
</html>