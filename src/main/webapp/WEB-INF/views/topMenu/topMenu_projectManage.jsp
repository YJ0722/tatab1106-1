<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <!-- topMenu_projectManage.css-->
    <link rel="stylesheet" type="text/css" href="resources/css/topMenu/topMenu_projectManage.css?ver=3">
    
    <!-- web font -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <!-- topMenu_projectMenu.js -->    
    <script src="resources/js/topMenu/topMenu_projectManage.js?var=2"></script>
    <script>
    	$(document).ready(function() {
    		// projectManage overview 데이터 호출 
    		$('#infoBtn').click(function() {
    			$.ajax({
        			url : "boardProjectManage.do",
        			type : "post",
        			 
        			success : function(data) {
        				// console.log(data);
        				
        				$('.projectTitle').html(data.project_name);
        				$('.projectNames').attr("value", data.project_name);
        				$('.projectDescription').attr("value", data.project_comment);
        			}
    			})
    		});
    		
    		// members 호출
    		$('.membersBtn').click(function() {
    			$.ajax({
    				url : "memberList.do",
    				type : "post",
    				
    				success : function(data) {
    					console.log(data);
    					
    				}
    			})
    		})
    		
    	});
    	// done 버튼 클릭시 projectVO update
		function update() {
			$('.updateProjectVO').submit();
			
		}
    	
    	function addUser() {
			$('.addUser').submit();
    	}
    </script>
       
    <body>
        <div class="manageWrapper">
                   <div class="title">
                       <div class="name">
                           <a class="projectTitle manage-a"></a>
                       </div>
                       <div class="close">
                           <a class="manage-a"><i class="fas fa-times "></i></a>
                       </div> 
                       <div class="trash">
                           <a class="manage-a"><i class="fas fa-trash-alt"></i></a>
                       </div>
                   </div>

                   <div class="menu">
                       <div class="overviewBtn"><a class="manage-a">Overview</a></div>
                       <div class="checklistBtn"><a class="manage-a">Checklist</a></div>
                       <div class="membersBtn"><a class="manage-a">Members</a></div>
                   </div>
                    
                   <!-- Overview 메뉴-->
                   <div class="content overview">
						<form method="post" class="updateProjectVO" action="updateProjectVO.do">
	                       <div class="pj">
	                           <div class="pjName">
	                               Project name
	                           </div>
	                           <div class="pjInput">
	                                   <input class="projectNames" name="projectName" type="text" style="width:300px; height:50px;" placeholder="">
	                           </div>
	                       </div>
	                       <div class="dpt">
	                           <div class="dptName">
	                               Project Discription
	                           </div>
	                           <div class="dptdpt">
	                              <input class="projectDescription" name="projectDescription" type="text" style="width:300px; height:200px;" placeholder="">
	                       </div>
	               		</form>	        
                    </div>
          </div>
                   
                   <!-- Checklist 메뉴 -->
                   <div class="content checklist">
                       <div class="pj"> 
                           <div class="pjName">
                               checklist
                           </div>
                           <div class="pjInput">
                               <form>
                                   <input type="text" style="width:300px; height:50px;" placeholder="ABC">
                               </form>	
                           </div>
                       </div>

                       <div class="dpt">
                           <div class="dptName">
                               Project Discription
                           </div>
                           <div class="dptdpt">
                              <input type="text" style="width:300px; height:200px;" placeholder="DB에서 가져올 내용">
                           </div>
                       </div>
                   </div>
                   <!-- Checklist 메뉴 -->
                   
                   <!-- Members 메뉴 -->
                   <div class="content members">
                        <div class="pjName">
	                       Members
                        </div>
					   
					    <div class="memberDiv">
					   		<div class="memberList">
					   			<div class="memberName">
					   				moon
					   			</div>
					   			<div class="memberId">             
					   				sss!@navd ,com
					   			</div>
					   		</div>
			 	   		</div>
			 		   	
			 		   	<form class="addUser" action="addUser.do" method="post">
				 		    <div class="addUser">
					   			<input type="text" class="addMemberInput" name ="user">
							    <div class="addUserBtn" onclick="addUser()">
									Invite
							    </div>
						    </div>
				    	</form> 
                   </div>
				   <!-- Members 메뉴 -->

                   <div class="photo">
                       <div class="image">
                           <i class="far fa-grimace"></i>
                       </div>
                       <div class="record">
                           abcdefghijklmnopqrstuvwxyz
                       </div>
                   </div>

                   <div class="footer">
                       <div>
                           <div class="done">
                               <a onclick="update()">done</a>
                           </div>
                       </div>
                   </div>
    </body>
    
    
</html>