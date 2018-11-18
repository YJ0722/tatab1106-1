<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Project? You better TATAB.</title>
    <link rel="icon" type="image/x-icon" href="<c:url value='/resources/img/main/favicon.ico' />"/>
    <meta name="msapplication-TileColor" content="#da532c">
	<meta name="theme-color" content="#ffffff">
    <link href="<c:url value="/resources/css/main/UserMain.css?ver=7" />" rel="stylesheet">
    <!-- MyPage 관련 -->	
    <link href="<c:url value="/resources/css/main/MyPageModal.css?ver=3" />" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- MyPage 관련 끝 -->
    <!-- StickyNote 관련 -->
    <link href="<c:url value="/resources/css/main/StickyNoteModal.css?ver=2" />" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Gloria+Hallelujah' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
    <!-- Registration 관련 -->
    <link href="<c:url value="/resources/css/main/RegistrationModal.css?ver=1" />" rel="stylesheet">
    <!-- MainComment 관련 -->
    <link href="<c:url value="/resources/css/main/MainCommentModal.css?ver=1" />" rel="stylesheet">
    <!-- BackgroundImage 관련 -->
    <link href="<c:url value="/resources/css/main/BackgroundImageModal.css?var=1" />" rel="stylesheet">
    <!-- topMenu 관련 -->
    <link rel="stylesheet" href="<c:url value="/resources/css/topMenu/topMenu.css?ver=2" />">
    <!-- 관련  끝 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Cedarville+Cursive|Gothic+A1" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <script src="<c:url value="/resources/js/main/StickyNote.js" />"></script>
    <script src="<c:url value="/resources/js/main/UserMain.js?var=2" />"></script>
    
    
    <script>
	    function goBoard(a,b) {
			var projectName = a;
			var projectNo = b;
			console.log(projectName);
			
			$('.projectName').val(a);
			$('.project_no').val(b);
			$('.hiddenProjectName').submit();
			return false;
		}
    	  
        // 파일 정책위반 테스트 액션.
        function fileCheck(){
            console.log('파일체크 시작!!');
            var fileNm = $('#file').val();
            console.log(fileNm);

               if (fileNm != "") {
                      
                  var ext = fileNm.slice(fileNm.lastIndexOf(".") + 1).toLowerCase();
                      
                  if (!(ext == "gif" || ext == "jpg" || ext == "png")) {
                      alert("이미지파일 (.jpg, .png, .gif ) 만 업로드 가능합니다.");
                      return false;
                  }
               return true;
               }
         }
        
        $(document).ready(function(){
        	$('#deleteBackgroundImage').click(function(){
        		if(confirm('삭제하시겠습니까?')){
        			location.href="${pageContext.request.contextPath}/backgroundDelete.do";
        		}
        	});
        });
    </script>
    
<!--     <meta http-equiv="Cache-Control" content="no-cache"/> -->
<!-- <meta http-equiv="Expires" content="0"/> -->
<!-- <meta http-equiv="Pragma" content="no-cache"/> -->

</head>
<body>
    <!-- 제일 상위 등급 div -->
    <div name="mainWrapper" id="mainWrapper">
        <!-- 배경이미지 -->
        <div class="background">
        	<c:choose>
        		<c:when test="${empty mainBackgroundVO.save_name}">
        			<img src="http://cfile226.uf.daum.net/image/226ED33553157E4F2F828A">
        		</c:when>
        		<c:otherwise>
        			<img src="${pageContext.request.contextPath}/img/${mainBackgroundVO.save_name}">
        		</c:otherwise>
        	</c:choose>
        </div>
        <div class="mask">
        	<div class="bluredBackground">
        		<c:choose>
        		<c:when test="${empty mainBackgroundVO.save_name}">
        			<img src="http://cfile226.uf.daum.net/image/226ED33553157E4F2F828A">
        		</c:when>
        		<c:otherwise>
        			<img src="${pageContext.request.contextPath}/img/${mainBackgroundVO.save_name}">
        		</c:otherwise>
        	</c:choose>
        	</div>
        </div>
        <!-- 배경이미지 끝 -->
        <!-- 배경이미지 제외 맨 위 div -->
        <div class="subWrapper">
            <!-- 왼쪽 사이드바 -->
            <div class="sidebar">
                <!-- 사이드바 배경 -->
                <div class="sideBack"></div>
                <!--사이드바 배경 끝-->
                <!--사이드 내용물-->
                <div class="sideContent">
                    <!-- 로고 & 제목 -->
                    <div class="logoAndPhrase">
                        <!-- 제목 -->
                        <div class="titlePhrase">
                            <a href="<c:url value="/userMain.do" />"><img src="<c:url value="/resources/img/main/tatab-color.png" />"></a>
                        </div>
                        <!-- 제목 끝 -->
                        <!-- 플젝 표시 -->
                        <div class="projectSign">
                            <h4><img src="<c:url value="/resources/img/main/flash-21.svg" />">&nbsp;Projects</h4>
                        </div>
                        <!-- 플젝 표시 끝 -->
                        <hr> <!-- 수평선 -->
                    </div>
                    <!-- 로고 & 제목 끝 -->
                    <!-- 플젝 리스트 -->
                    <div class="listWrapper">
               		<c:forEach items="${projectList}" var="projectName" varStatus="status">
	                    <div class="list"> 
	                        <!-- 플젝명 -->
	                        <div class="listName">
	                            <a id="projectNames" onclick="goBoard('${projectName.project_name}', '${projectName.project_no}')"><img src="<c:url value="/resources/img/main/spaceship.svg" />">&nbsp;&nbsp;${projectName.project_name}</a>
	                        </div>
	                        <!-- 플잭명 끝  -->
	                    </div>
               		</c:forEach>
               		</div>
               		<!-- 플젝 리스트 끝-->
               		
               		<!-- 프로젝트 list submit -->
		            <form method="post" class="hiddenProjectName" action="board.do">
		            	<input class="projectName" type="hidden" name="projectName">
		            	<input class="project_no" type="hidden" name="project_no">
		            </form>
		            <!-- 프로젝트 list submit -->
                    
                    <!-- 새 플젝 추가 -->
                    <div class="addProject">
                            <a id="RegistrationModalBtn"><img src="<c:url value="/resources/img/main/plus_circle.png" />"> New Project</a>
                    </div>
                </div>
                <!-- 사이드 내용물 끝 -->
            </div>
            <!-- 왼쪽 사이드바 끝 -->
            <!-- 메인 -->
            <div class="main">
                <!-- 위쪽 아이콘들 -->
                <div id="topMenu">
                    <ul>
                        <li id="StickyNoteModalBtn"><a><img src="<c:url value="/resources/img/main/pencil-circle.png" />"></a></li>
		              			<li id="activityBtn"><a><img src="<c:url value="/resources/img/main/world.svg" />"></a></li>
                        <li id="MyPageModalBtn"><a>
                        	<c:choose>
                                    	<c:when test="${empty profileImgVO.save_name }">
		                                    <img src="<c:url value="/resources/img/main/single-01.svg" />">
                                    	</c:when>
                                    	<c:otherwise>
                                    		<img src="${pageContext.request.contextPath}/img/${profileImgVO.save_name}">
                                    	</c:otherwise>
                                    </c:choose>
                        </a></li>
                    </ul>
                </div>
                		<div class="page_cover"></div>
				<div id="menu">
					<div class="activityClose" id="activityClose"></div>
					<!-- activity.jsp-->
					<jsp:include page="/WEB-INF/views/topMenu/topMenu_activity.jsp" />
				</div>
               
                <!-- 위쪽 아이콘들 끝 -->
                <!-- 아래 코멘트 배경 -->
                <!-- <div class="commentBack"></div> -->
                <!-- 아래 코멘트 배경 끝 -->
                <!-- 아래 코멘트 -->
                <div class="commentBelow">
	                    <h1><c:out value="${ commentVO.main_title }" /></h1><br><br>
	                    <h2><c:out value="${ commentVO.sub_title }" /></h2><br>
	                    <p><c:out value="${ commentVO.sub_comment }" /></p><br>
                </div>
                <!-- 아래 코멘트 끝 -->
                <!-- 코멘트 수정 아이콘 -->
                <div class="commentAdjust">
                    <a id="MainCommentModalBtn"><img src="<c:url value="/resources/img/main/gear.png" />"></a>
                </div>
                <!-- 코멘트 수정 아이콘 끝 -->
                <!-- 배경 이미지 변경 아이콘 -->
                <div class="BackgroundImage">
                    <a id="BackgroundImageModalBtn"><img src="<c:url value="/resources/img/main/wrench.png" />"></a>
                </div>
                <!-- 배경 이미지 변경 아이콘 끝 -->
                <!-- 아래 공백 부분 -->
                <div class="emptyFooter">
                    Copyright ⓒ 2018. BeatTheBit. All rights reserved.
                </div>
                <!-- 아래 공백 부분 끝 -->
            </div>
            <!-- 메인 끝 -->
        </div>
        <!-- 배경이미지 제외 맨 위 div 끝 -->
        <!-- 첫번째 모달 : MyPage -->
        <div id="MyPageModal" class="MyPageModal">
            <!-- MyPage 해당 컨텐츠 -->
            <div class="MyPageModalContent">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-sm-6">
                            <div class="card hovercard">
                                <div class="cardheader">
                                	<%-- <img src="<c:url value="/resources/img/main/tatab.png" />"
 											style="background-size: cover; height: 135px; 
  											background-repeat: no-repeat;"/> --%>
                                </div>
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
        <!-- 첫번째 모달 끝 -->
        <!-- 두번째 모달 : StickyNote -->
        <div id="StickyNoteModal" class="StickyNoteModal">
            <!-- StickyNote 해당 컨텐츠 -->
            <div class="StickyNoteModalContent">
                <a href="javascript:;" class="button" id="add_new">ADD NOTE</a>
                <div id="board">
                </div>
            </div>
            <!-- StickyNote 해당 컨텐츠 끝 -->
        </div>
        <!-- 두번째 모달 끝 -->
        <!-- 세번째 모달 : Registration -->
        <div id="RegistrationModal" class="RegistrationModal">
            <!-- Registration 해당 컨텐츠 -->
            <div class="RegistrationModalContent">
                <div class="container">
                    <form id="contact" action="register.do" method="post" commandName="ProjectVO">
                        <h3>새 프로젝트</h3>
                        <h4>새 프로젝트의 이름, 설명 및 구성원을 고르십시오.</h4>
                        <fieldset>
                            <input placeholder="프로젝트명" name="project_name" type="text" tabindex="1" required
                                autofocus >
                        </fieldset>
                        <fieldset>
                            <textarea rows="30" cols="50" name="project_comment" placeholder="설명" tabindex="2" required></textarea>
                        </fieldset>
                        <fieldset id="buttons">
                            <input type="submit" class="btn btn-primary" value="생성">
                            <a class="btn btn-light" href="<c:url value="/userMain.do" />" role="button">취소</a>
                        </fieldset>
                    </form>
                    
                </div>
            </div>
            <!-- Registration 해당 컨텐츠 끝 -->
        </div> 
        <!-- 세번째 모달 끝 -->
        <!-- 네번째 모달 : MainComment -->
        <div id="MainCommentModal" class="MainCommentModal">
            <!-- MainComment 해당 컨텐츠 -->
            <div class="MainCommentModalContent">
                <div class="container">
                    <form id="contact" action="modifyComment.do" method="post"
                        commandName="CommentVO">
                            <h3>코멘트 변경</h3>
                            <input class="commentInput" placeholder="메인 타이틀" name="mainTitle" type="text" tabindex="1" required
                                autofocus>
                            <input class="commentInput" placeholder="서브 타이틀" name="subTitle" type="text" tabindex="1" required
                                autofocus>
                            <textarea class="commentInput" rows="30" cols="50" placeholder="코멘트" name="subComment" tabindex="2" required></textarea>
                        <fieldset id="buttons">
                            <input type="submit" class="btn btn-primary" value="수정">
                            <a class="btn btn-light" href="<c:url value="/userMain.do" />" role="button">취소</a>
                        </fieldset>
                    </form>
                </div>
            </div>
            <!-- MainComment 해당 컨텐츠 끝 -->
        </div>
        <!-- 네번째 모달 끝 -->
        <!-- 다섯번째 모달 : BackgroundImage -->
        <div id="BackgroundImageModal" class="BackgroundImageModal">
        	<!-- BackgroundImage 해당 컨텐츠 -->
            <div class="BackgroundImageModalContent">
                <div class="container">
                    <form id="contact" action="modifyBackgroundImage.do" method="post"
                        enctype="multipart/form-data" onsubmit = "return fileCheck()">
	                     <c:choose>
	                        <c:when test="${empty mainBackgroundVO.save_name }">
		                        <fieldset class="form-group">
		                            <h3>배경이미지 변환</h3>
		                            <input type="file" class="form-control-file" id="file" name="file"
		                            accept="image/gif, image/jpeg, image/png"> <!-- accept : 이미지파일만 할 수 있도록! -->
		                        </fieldset>
		                        <fieldset id="buttons">
		                            <input type="submit" class="btn btn-primary" value="등록">
		                            <a class="btn btn-light" href="<c:url value="/userMain.do" />" role="button">취소</a>
			                    </fieldset>
                       		</c:when>
                       		<c:otherwise>
		                        <fieldset class="form-group">
		                            <h3>배경이미지 삭제</h3>
		                            <p>삭제하시겠습니까?</p>
		                        </fieldset>
		                        <fieldset id="buttons">
		                        	<input type="button" id="deleteBackgroundImage" class="btn btn-primary" value="삭제">	
		                            <a class="btn btn-light" href="<c:url value="/userMain.do" />" role="button">취소</a>
		                        </fieldset>
                       		</c:otherwise>
                      	</c:choose>
                    </form>
                </div>
            </div>
            <!-- BackgroundImage 해당 컨텐츠 끝 -->
        </div>
        <!-- 다섯번째 모달 끝 --> 
    </div>
    <!-- 제일 상위 등급 div 끝 -->
</body>

<script>
//activity 관련 업데이트
$(document).ready(function() {
	$("#activityBtn").click(function() {
		// 내용 ajax
		$.ajax({
			url : "userMainActivity.do",
			type : "post",
			success : function(data) { // data가 컨트롤러에서 넘어오는 리턴값!
				
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
    				var tag = tag1 + tag2 + diffInfo + tag3 + data[i].alert_message + tag4 + data[i].project_name + tag5;
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
	// 구글 로그아웃
	function signOut() {
		alert('!!!');
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	    });
	  };
</script>

</html>