$(document).ready(function () {

	 // task 클릭 이벤트
	   $(document).on("click", '.task', function(e) {
	      
	      var task_no = $(this).attr("id");
	      
	      if(task_no != null) {
		      console.log('i : ' + task_no);
		      // 모달 천천히 띄우기
		      $('#exampleModalLong').modal().fadeIn(300);
		      
		      selectAllTask(task_no);
	    	  
	      }
	      	    
	   });
   
   // 토글 실행
   var state = 0;
   $(document).on("click", '.toggle', function (e) {
	   
       var toggleIndex = parseInt($('.toggle').index(this)/2);
       // console.log('토글 인덱스 : ' + toggleIndex + "\n상태 인덱스 : " + state);
       
       if($('.body-up-img').eq(toggleIndex).css('display') == 'none') {
           state = 1;
       } else {
           state = 0;
       }
       // 토글down img 보이기
       if (state == 0) {
           $('.body-up-img').eq(toggleIndex).css('display', 'none');
           $('.body-down-img').eq(toggleIndex).css('display', 'block');
           state = 1;
       }
       // 토글up img 보이기
       else {
           $('.body-up-img').eq(toggleIndex).css('display', 'block');
           $('.body-down-img').eq(toggleIndex).css('display', 'none');
           state = 0;
       }
       var $panelBody = $('.kanban-head').parent().children('.kanban-body').eq(toggleIndex);
       $panelBody.slideToggle();

       toggleIndex = 0;
   });
    
    // 컬럼 정렬 drag and drop
    // connectWith : 해당 클래스 내의 요소들을 정렬 아이템으로 지정
    // items : 지정한 요소들만 sortable 시킨다
    // update : 정렬 중 순서 변경 시 실행
    $(".colsortable").sortable({
        connectWith: ".colsortable",
        items: ".kanban-col-box:not(.col-add-box)",
        update: function(event, ui) {
            
            var order = $(this).sortable('toArray');
            
            console.log('*****\n' + order + '\n*****');
            
            // col_no 값을 저장할 배열
            var colIndexArr = new Array();
            
            for(var i=0; i<order.length; i++) {
                colIndexArr[i] = order[i];
            }

        	console.log('colIndexArr : ' + colIndexArr + "\n" );
        	
        	// 배열은 'traditinal : true' 로 지정해야 파라미터로 전송 가능
        	$.ajax({
        		url : "updateBoardCol.do",
        		type : "post",
        		traditional : true,
        		data : {
        			'colIndexArr' : colIndexArr
        		}
        	});
        }

    });
    

    // 작업 정렬 drag and drop
    $(".tasksortable").sortable({
        connectWith: ".tasksortable",
        items: ".task:not(.add-task-box)",
        update: function(event, ui) {
            var taskOrder = $(this).sortable('toArray');

            // task_no 값을 저장할 배열
            var taskNoArr = new Array();

            // 해당 작업의 col_no 값 
            var colno = $(this).parents(".kanban-col-box").attr("id");
            console.log('id:::::' + colno);
            
            for(var i=0; i<taskOrder.length; i++) {
            	taskNoArr[i] = taskOrder[i];
            }
            
            console.log(taskNoArr);
            
            // 업데이트 된 후 작업 인덱스 인덱스 배열에 작업이 하나도 존재 하지 않을 경우만 ajax 실행
            if(taskNoArr.length != 0) {

            	$.ajax({
            		url : "updateBoardTask.do",
            		type : "get",
            		traditional : true,
            		data : {
            			'colIndex' : colno, 
            			'taskNoArr' : taskNoArr
            		}
            	});
            }
        }
    });
    
    $('.add-task-box').disableSelection();
    $('.col-add-box').disableSelection();
    
    // 제목 변경할 col의 mouseover, mouseout 설정(mouseover: true, mouseout: false)
    var updateColTitleMouseAction = true;
    // 
    var index;
    // hide 설정했던 input 태그
    var inputTag;
    // update 전 col의 기존 제목
    var originalName;
    // col의 제목 클릭 이벤트(col 제목 업데이트 이벤트)
    $(document).on("click", '.col-title-show', function(e) {
    	
    	// col 제목의 p 태그 숨기기
    	$('.col-title-show').show();
        $(this).hide();
        
        // col 제목의 input 태그 보여주기
        $('.col-title-input').hide();
        console.log('show!');
        $(this).prev().show();
        
        // col의 기존 이름 일시 저장
        originalName = $(this).text();
        
        // $(this) : p태그 
        // prev() : 이전 요소
        inputTag = $(this).prev();

        // mouseover, mouseout 설정
        inputTag.off().on({
        	// input 태그의 over
            'mouseover' : function(e) {
            	updateColTitleMouseAction = true;
                console.log("last in");
                return false;
            },
        	// input 태그의 out
            'mouseout' : function(e) {
            	updateColTitleMouseAction = false;
                console.log("last out");
                return false;
            }
        });

        // 외부 영역 클릭 시 입력 내용 고정
        $(document).on("click", function(e) {
        	
        	// 클릭이벤트 + input 태그 out 된 경우에만 실행
        	if(updateColTitleMouseAction == false) {
        		
        		console.log(inputTag.val());
        		
        		// 변경한 col 제목 저장
        		var updateTitle = inputTag.val();

        		// input 태그 숨기기
        		inputTag.hide();
        		// p 태그 설정 : input 태그의 다음 요소
        		var p = inputTag.next();
        		
        		// 입력된 제목이 없으면 원래 이름으로 다시 돌려놓기
        		if(updateTitle == "") {
            		p.text(originalName);
        		}
        		// 새로 입력된 제목 있으면 p 태그에 다시 업데이트하여 보여주기
        		else{
            		p.text(updateTitle);
            		var colId = p.parents('.kanban-col-box').attr('id');
            		console.log('%%%%%' + colId);
            		
            		
            		updateColName(colId, updateTitle);
        		}
        		// p 태그 보여주기
        		p.show();
    			        		
        		// col mouse 액션 over로 다시 설정 --> 외부 요소 클릭 이벤트 재실행 방지
            	updateColTitleMouseAction = true;
        		
        	}
        });
        
    });
        
    
    // 작업 추가 
    var tagIndex;
    var taskMouseAction = true;
    $(document).on("click", '.task-add-btn', function(e) {
        e.preventDefault();

        // 해당 작업의 col_index 가져오기
        tagIndex = $('.add-task-box .add').index(this);
        console.log('클릭한 버튼 index : ' + tagIndex);  
        
        // 해당 작업의 col_id 가져오기
        task_col_no = $('.kanban-col-box').eq(tagIndex).attr("id");
        
        
        var addTaskTag = '<div class="task round-border ui-state-default">' +
                         '<div class="task-inner">' +
                         '<div class="task-label">' +
                         '<input type="text" autofocus  class="task-title-input task-input-check" style="display:block" placeholder="제목을 입력하세요">' +
                         '<p class="task-title-show" style="display:none"></p>' +
//                         '<p>20181001 TodoList</p>' +
                         '</div>' +
                         '<div class="task-content">' +
                         '<p></p>' +
                         '</div>' +
                         '</div>' +
                         '</div>';
        
        // task 요소 추가
        var addTask = $(addTaskTag).hide()
                            .appendTo($('.kanban-body').eq(tagIndex))
//                        .insertBefore($('.add-task-box').eq(tagIndex))
                            .show("fade", 300);
                
        // task 추가 후 작업 추가 버튼으로 화면 자동 이동
        $('.kanban-body').animate({scrollTop: $('.task-add-btn').offset().top}, 500);
        
        
        // 작업 추가 버튼 숨기기
        $('.task-add-btn').eq(tagIndex).hide();
                    
        // 추가한 작업에서 mouseover
        $('.kanban-col:eq('+tagIndex+') .task:last').off().on({
            'mouseover' : function(e) {
                taskMouseAction = true;
//                console.log("last in"+tagIndex);
                return false;
            },
            'mouseout' : function(e) {
                taskMouseAction = false;
//                console.log("last out");
                return false;
            }
        })
        
        // 해당 컬럼의 가장 마지막 작업(동적 생성하여 생긴 task)
        var lastTag = $('.kanban-col').eq(this).find('.task:last');
        
        var getTaskTitle;
        var setTaskTitle;
        // 외부 영역 클릭 시 입력 내용 고정
        $(document).on("click", lastTag, function(e) {
            var selectTask = $('.kanban-col').eq(tagIndex).find('.task:last');
            
            // 동적 생성한  작업의 input 태그
            getTaskTitle = selectTask.find('.task-title-input').last();
            // 동적 생성한  작업의 p 태그
            setTaskTitle = selectTask.find('.task-title-show').last();            
            
            
            // TODO : 왜 자동 포커스 안되는지 모름... 나중에 수정
            $('.task-title-input:last').focus();
            
            console.log('input-check-확인 : ' + $('.task-title-input').hasClass('task-input-check'));
            // 클릭한 공간이 mouseout 상태인 경우 실행
            if(taskMouseAction == false && $('.task-title-input').hasClass('task-input-check')) {
                console.log('추가된 작업 외부영역 클릭(작업 추가 완료)');

                var taskTitle = getTaskTitle.val();
                
                // input 태그에 입력된 값이 없다면
                if(taskTitle == "") {
                    // 태그 삭제
                    $('.kanban-col').eq(tagIndex).find('.task:last').remove();
                } else {
                	// p 태그에 input 태그에 입력했던 제목 setting
                    setTaskTitle.text(taskTitle);
                    // input 태그 숨기기
                    getTaskTitle.hide();
                    // p 태그 보이기
                    setTaskTitle.show();
                    
                    console.log('작업 제목 : ' + taskTitle);
                    console.log('해당 컬럼 인덱스 : ' + task_col_no);
                    
                    $('.task-title-input').removeClass('task-input-check');
                    
                    // db에 태스크 추가 ajax 실행
                    insertBoardTask($('.kanban-col').eq(tagIndex).find('.task:last'), task_col_no, taskTitle);
                    
	                // ajax로 값 넘기기 - 액티비티 추가
        			$.ajax({
        				url : "createNewTask.do",
        				data : {'task_name': taskTitle
        						},
        				type : "get",
        				success : function() {
        						alert('테스크 추가 알림 완료!');
        						// 새로 추가된 task에 대해서도 클릭 이벤트(채크리스트 받아오기) 처리기 등록하기
        						$('.task').on('click', function(){
        							getTaskCheckList($(this).attr('id'));	
        						})	
        					}
        			});
                    
                }

                // 작업 insert 후 마우스 다시 mouseover 상태로 변경
                taskMouseAction = true;

                // 작업 추가 버튼 보여주기
                $('.task-add-btn').eq(tagIndex).show();

            }

            $(".tasksortable").sortable({
                connectWith: ".tasksortable"
            });
                
            $('.add-task-box').disableSelection();
        });
        
    });

    
    // 컬럼 추가
    var mouseAction = true;    
    $(document).on("click", '.col-add-btn', function(e) {
        
        console.log('col 추가 작업 수행');
        
        var addColTag = '<div class="kanban-col-box">' +
        '<div class="kanban-col add-col round-border">' +
        '<div class="kanban-head">' +
        '<input type="text" class="col-title-input col-input-first" autofocus style="display:block">' +
        '<p class="col-title-show" style="display:none"></p>' +
        '<img class="toggle body-up-img" src="resources/img/board/sort-up.png">' +
        '<img class="toggle body-down-img" src="resources/img/board/sort-down.png">' +
        '</div>' +
        '<div class="kanban-body tasksortable" id="kanban-body">' +
        '<jsp:include page="/WEB-INF/views/kanban_task.jsp"/>' +
        '</div>' +
        '<div class="add-task-line"></div>' +
        '<div class="add-task-box">' +
        '<a href="#"><i class="fas fa-plus-circle task-add-btn add"></i></a>' +
        '</div>' +
        '</div>' +
        '</div>';
        
        // col 입력창 추가
        var addCol = $(addColTag).hide().insertBefore("#col-add-box").show("fade", 300);
        
//        location.hash = '#endline';
        $('.board-background').animate({scrollLeft: $('#endline').offset().left}, 300);
//        $('.board-background').animate({scrollLeft: $('.col-add-btn').offset().left}, 300);
        
        // 컬럼 추가 버튼 숨기기
        $('.col-add-btn').hide();
        
        var lastCol = $('.add-col:last');
        
        // 외부 영역 클릭 시 입력 내용 고정
        
        $(lastCol).off().on({
            'mouseover' : function(e) {
                mouseAction = true;
            },
            'mouseout' : function(e) {
                mouseAction = false;
            }
        });
        // 컬럼 insert 관련 클릭 이벤트 시작
        $(document).on("click", lastCol, function(e) {
        	
        	// 클릭한 위치가 input 내부인지 외부인지 기록
        	
        	// 동적 생성한 컬럼 input 태그
            var getTitle = $('.col-title-input:last');
        	// 동적 생성한 컬럼 p 태그
            var setTitle = $('.col-title-show:last');
            
            // 생성한 col의 외부영역 클릭했을 경우
            console.log($('.col-title-input').hasClass('col-input-first'));
            if(mouseAction == false && $('.col-title-input').hasClass('col-input-first')) {
                console.log('추가된 작업 외부영역 클릭(컬럼 추가 완료)');
                
                // input 태그의 입력값 받아서 변수에 저장
                var title = getTitle.val();
                
                // input 태그에 입력된 값이 없다면
                if(title == "") {
                    // 태그 삭제
                    $('.kanban-col-box:last').remove();
                } else {
                	// input 태그의 입력값 p 태그에 입력
                    setTitle.text(title);
                    // input 태그 숨기기
                    getTitle.hide();
                    // p 태그 보이기
                    setTitle.show();
                    
                    
                    ////// soo 컬럼추가 //////
                    console.log("추가될 col 이름 : " + title);
                    $('#colName').val(title);

                    insertCol($('.kanban-col-box:last'));
                    ////// soo 컬럼추가 //////	
                }
                
                // 작업 추가 후 mouseover 상태로 다시 변경
                mouseAction = true;
                $('.col-title-input').removeClass('col-input-first');
                
                // 컬럼 추가 버튼 보여주기
                $('.col-add-btn').show();
                
                // 컬럼 생성 후 컬럼 생성 버튼으로 화면 자동 이동(추가한 컬럼 위치로!)
                $('.board-background').animate({scrollLeft: $('#endline').offset().left}, 300);
        
            }
        });
        
        $(".colsortable").sortable({
            connectWith: ".colsortable",
            items: ".kanban-col-box:not(.col-add-box)"
        });
        $(".tasksortable").sortable({
            connectWith: ".tasksortable"
        });
        $('.col-add-box').disableSelection();
        $('.add-task-box').disableSelection();
                
        console.log('클릭이벤트 종료');
    });

    return;
});

// 컬럼 추가 ajax 실행
function insertCol(lastColBox) {
	$.ajax({
		url:'insertCol.do',
		type:'post',
		data:{'ColName' : $('#colName').val()},
		success:function(colNoId){
			
			// id 값 받아서 생성한 col에 id 입력
			lastColBox.attr("id", colNoId);
		}
	})

}

// 작업 추가 ajax 실행
function insertBoardTask(taskColBox, task_col_no, t_name) {
	alert(t_name);
	$.ajax({
		url : "insertBoardTask.do",
		type : "post",
		data : {
			'task_name' : t_name,
			'col_no' : task_col_no
		},
		success:function(taskNoId){
			// id 값 받아서 생성한 col에 id 입력
			taskColBox.attr("id", taskNoId);
		}
	});
	
	return false;
}

//컬럼 이름 변경
function updateColName(colId, updateTitle) {
	$.ajax({
		url : "updateColName.do",
		type : "post",
		data : {
			'colId' : colId,
			'updateTitle' : updateTitle
		}
	});
}

// 작업 데이터 가져오기
function selectAllTask(task_no) {
	$.ajax({
		url : "selectAllTask.do",
		type : "post",
		data : {
			'task_no' : task_no
		},
		success : function(data) {
			if(data != null) {
					
				if(data.taskVO.status == 'C') {
					console.log('완료 상태');
					$('.modal-header').css('background-color', 'forestgreen');
					$('.saveBtn, .addBtn').css('border-color', 'forestgreen');
					$('.saveBtn, .addBtn').css('background', 'forestgreen');
					$('.saveBtn').prop("disabled", true);
					$('.task_name').css('text-decoration', 'line-through');
					$('.task_content').css('text-decoration', 'line-through');
//					$('.task_name. .task_content').prop('readonly', true);
				} else {
					$('.modal-header').css('background-color', 'darkorange');
					$('.saveBtn, .addBtn').css('border-color', 'darkorange');
					$('.saveBtn, .addBtn').css('background', 'darkorange');
					$('.saveBtn').prop("disabled", false);
//					$('.task_name. .task_content').prop('readonly', false);
					$('.task_name').css('text-decoration', 'none');
					$('.task_content').css('text-decoration', 'none');
				}
				
				console.log("5555555555 data : " + data.loginName);
				console.log('@@@@@@ : ' + data.taskVO.d_day);

				$('.nicknameText').attr('id', data.loginName);
				
				
				$('.modal-content').attr('id', data.taskVO.task_no);
				$('.modal-content').val(task_no);
				
				console.log('@#$^&*()_(*&%@#$)( : ' + data.dday);
				
				
				if(data.dday != null) {

					$('#ddayCount').text(data.dday);

					$('#ddayCountView').show();
					$('#ddayCount').show();
					
				} else {
					$('#ddayCountView').hide();
					$('#ddayCount').hide();
				} 
				
				// 데이터 벨류값 설정
				$('.task_name').val(data.taskVO.task_name);
				$('.task_content').html(data.taskVO.task_content);
				$('#startDate').text(data.taskVO.reg_date);
				$('#datepicker1').attr('placeholder', data.taskVO.d_day);
				$('#task_ori_name').val("");
				if(data.taskFileVO != null){
					$('#task_ori_name').val(data.taskFileVO.task_ori_name);
				}
				$('.task_no').val(data.taskVO.task_no);
				
				
				if(data.taskVO.status == 'C') {
					$('.task_name, .task_content').prop('readonly', true);
				} else {
					$('.task_name, .task_content').prop('readonly', false);
				}
				// comlete 확인
				
				// 작업 완료 상태 img 표시
//				if(data.taskVO.status == 'C') {
//					$('#taskCompleteBtn').hide();
//					$('#taskCompleteOKBtn').show();
//				} 
//				else {
//					$('#taskCompleteBtn').show();
//					$('#taskCompleteOKBtn').hide();
//				}

//				$('#nicknameText').text(data.loginName);

				// form 타입의 input data 설정
//				$('.task_no').val(task_no);
				$('#updateDate').text(data.update_date);
				
				if(data.d_day != '-') {
					$('#datepicker1').val(data.d_day);
//					$('.dday').val(data.d_day);
				} 
				var array = new Array();// = data.memberList.toArray();
				
				for(i=0; i<array.length; i++) {
					console.log('5655'+array[i]);
				}
				// 멤버리스트 관련 for문
				$('.assigneeList').empty();
				console.log('memberList:'+data.memberList);
				for(i=0; i<data.memberList.length; i++) {
					console.log('잘 나오니? : ' + data.memberList[i]);
					var tag1 = '<div class="nameList">';
					var tag2 = '</div>';
    				var tag = tag1 + data.memberList[i].login_name + tag2;
    				$(tag).hide().appendTo('.assigneeList').show();
				}
//				// 체크리스트 관련 for문
//				for(i=0; i<data.checklistList.length; i++) {
//					console.log('체크리스트 잘 나오니?' + data.checklistList[i]);
//					var tag1 = '<div class="checkInnerWrapper"><input type="checkbox" class="yyj" id="cbx" style="display: none;">';
//					var tag2 = '<label for="cbx" class="check">';
//					var tag3 = '<svg width="18px" height="18px" viewBox="0 0 18 18">';
//					var tag4 = '<path d="M1,9 L1,3.5 C1,2 2,1 3.5,1 L14.5,1 C16,1 17,2 17,3.5 L17,14.5 C17,16 16,17 14.5,17 L3.5,17 C2,17 1,16 1,14.5 L1,9 Z"></path>';
//					var tag5 = '<polyline points="1 9 7 14 15 4"></polyline></svg></label></div>'; 
//					var tag6 = '<input type="text" id="checkName" value="';
//					var tag7 = '" readonly>&nbsp;<br>';
//					var tag = tag1 + tag2 + tag3 + tag4 + tag5 + tag6 + data.checklistList[i].task_checklist + tag7;
//					$(tag).hide().appendTo('.checkWrapper').show(); 
//					}
					
				
				
				// 코멘트들 관련 for문
				$('#myUL').empty();

				var comments = data.commentList;
				
				for(var i=0; i<comments.length; i++) {
					console.log("comments 확인 : " + comments[i]);
					
					var nname = comments[i].login_name;
					var date = comments[i].reg_date;
					console.log('namename : ' + nname + "\ndate : " + date);
					
					var li = document.createElement("li");
					var nicknamebox = document.createElement("div");
					var contentbox = document.createElement("div");
					var datebox = document.createElement("div");
					
					var commentValue = comments[i].task_comment;
					var inputCommentVal = document.createTextNode(commentValue);
					var nicknameVal = document.createTextNode(nname);
					var dateVal = document.createTextNode(date);
					/////--------------------------------------------------------------------------
					var login_email = comments[i].login_email;
					var my_email = data.myEmail;
					
					console.log('login_email : ' + login_email + "\nmy_email : " + my_email);
				  
					// 댓글 텍스트 contentbox에 추가
					contentbox.appendChild(inputCommentVal);
					// 닉네임 nicknamebox에 추가
					nicknamebox.appendChild(nicknameVal);
					// 작성 날짜 datebox에 추가
					datebox.appendChild(dateVal);
				  
					// li에 닉네임, 댓글, 작성 날짜 추가
					li.appendChild(nicknamebox);
					li.appendChild(contentbox);
					li.appendChild(datebox);					  
					  
	  		      	document.getElementById("myUL").appendChild(li);
	  		      	$('li').eq(i).attr('id', comments[i].comment_no);
	  		      	
	  		      	// 닉네임box에  로그인 id 설정
	  		      	$('li').eq(i).children().eq(0).attr('id', login_email);
	  		      	$('li').eq(i).children().eq(0).attr('class', 'nicknamebox');
	  		      	$('li').eq(i).children().eq(1).attr('class', 'contentbox');
	  		        $('li').eq(i).children().eq(2).attr('class', 'datebox');
	  		      	
	  		      	// 로그인 id 와 동일한 id 정보를 가지는 댓글만 x 표시
	  		      	if(my_email == login_email) {
	  		      		
		  		      	var span = document.createElement("p");
		  		      	var txt = document.createTextNode("\u00D7");
		  		      	span.className = "commentDelete";
		  		      	span.appendChild(txt);
		  		      	li.appendChild(span);
		  		      	
		  		      	$('.commentDelete').click=function(){
		  		      		alert('쫌!!!');
		  		      		console.log('yyj');
		  		      	}
	  		      		
	  		      	}
	  		      	////////////////////////////////////////////
				}

				// 댓글 삭제 버튼 클릭
  		      	$('.commentDelete').on("click", function() {
  		      		
  		      		// 삭제 선택한 댓글의 id
  		      		var selectCommentId = $(this).parents(li).attr('id');
  		      		
  		      		// 삭제할 댓글 li
  		      		var delLi = $(this).parents('li');
  		      		
  		      		// 댓글 삭제 ajax
	  		      	$.ajax({
	  					url : "deleteComment.do",
	  					type : "get",
	  					data : {
	  						'commentNo' : selectCommentId
	  					},
	  				    cache : false,
	  					success : function(commentVO) {

	  	  		      		// 화면에서 삭제 버튼 클릭 요소 삭제
	  	  		      		delLi.remove();
	  					}
	  				});
  		      		
  		      	});
  		      	
			}
			//////////////////////
			
		}
	})
}