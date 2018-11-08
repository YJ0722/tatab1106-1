$(document).ready(function () {
    
    // 토글 실행
    var state = 0;
    $(document).on("click", '.toggle', function (e) {
        var toggleIndex = parseInt($('.toggle').index(this)/2);
//        console.log('토글 인덱스 : ' + toggleIndex + "\n상태 인덱스 : " + state);
        
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
    
    
    $(".colsortable").sortable({
        connectWith: ".colsortable",
        items: ".kanban-col-box:not(.col-add-box)"
    });
    $(".tasksortable").sortable({
        connectWith: ".tasksortable"
    });
    $('.add-task-box').disableSelection();
    $('.col-add-box').disableSelection();
    
    // 작업 추가 
    var tagIndex;
    var taskMouseAction = true;
    $(document).on("click", '.task-add-btn', function(e) {
        e.preventDefault();
//        console.log('task 추가 작업 수행');
        tagIndex = $('.add-task-box .add').index(this);
//        console.log('클릭한 버튼 index : ' + tagIndex);  
        
        var addTaskTag = '<div class="task round-border ui-state-default">' +
                         '<div class="task-inner">' +
                         '<div class="task-label">' +
                         '<input type="text" autofocus  class="task-title-input" style="display:block" placeholder="제목을 입력하세요">' +
                         '<p class="task-title-show" style="display:none"></p>' +
//                         '<p>20181001 TodoList</p>' +
                         '</div>' +
                         '<div class="task-content">' +
                         '<p></p>' +
                         '</div>' +
                         '</div>' +
                         '</div>';
        
        // task 입력창 추가
        var addTask = $(addTaskTag).hide()
                            .appendTo($('.kanban-body').eq(tagIndex))
//                        .insertBefore($('.add-task-box').eq(tagIndex))
                            .show("fade", 300);
                
        $('.kanban-body').animate({scrollTop: $('.task-add-btn').offset().top}, 500);
        
        
        // 작업 추가 버튼 숨기기
        $('.task-add-btn').eq(tagIndex).hide();
                    
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
        
        var lastTag = $('.kanban-col').eq(this).find('.task:last');
        var getTaskTitle;
        var setTaskTitle;
        // 외부 영역 클릭 시 입력 내용 고정
        $(document).on("click", lastTag, function(e) {
            var selectTask = $('.kanban-col').eq(tagIndex).find('.task:last');
            getTaskTitle = selectTask.find('.task-title-input').last();
            setTaskTitle = selectTask.find('.task-title-show').last();            
            
            $('.task-title-input:last').focus();

            if(taskMouseAction == false) {
                console.log('추가된 작업 외부영역 클릭(작업 추가 완료)');

                var taskTitle = getTaskTitle.val();
                var task_col_index = tagIndex;              
                

                // input 태그에 입력된 값이 없다면
                if(taskTitle == "") {
                    // 태그 삭제
                    $('.kanban-col').eq(tagIndex).find('.task:last').remove();
                } else {
                    setTaskTitle.text(taskTitle);
                    getTaskTitle.hide();
                    setTaskTitle.show();
                    
                    console.log('작업 제목 : ' + taskTitle);
                    console.log('해당 컬럼 인덱스 : ' + task_col_index);
                    
                    // db에 태스크 추가 ajax 실행
                    insertBoardTask(task_col_index, taskTitle);
                    
                }

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
        
        var addColTag = '<div class="kanban-col-box" id="kanban-col-box">' +
        '<div class="kanban-col add-col round-border">' +
        '<div class="kanban-head">' +
        '<input type="text" class="col-title-input" autofocus style="display:block">' +
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
        
        $(document).on("click", lastCol, function(e) {
            
            var getTitle = $('.col-title-input:last');
            var setTitle = $('.col-title-show:last');
            
            if(mouseAction == false) {
                console.log('추가된 작업 외부영역 클릭(컬럼 추가 완료)');
                
                var title = getTitle.val();
                
                // input 태그에 입력된 값이 없다면
                if(title == "") {
                    // 태그 삭제
                    $('.kanban-col-box:last').remove();
                } else {
                    setTitle.text(title);
                    getTitle.hide();
                    setTitle.show();
                    
                    
                    ////// soo 컬럼추가 //////
                    console.log("추가될 col 이름 : " + title);
                    $('#colName').val(title);
//                    $('#insertCol').click(insertCol);
                    insertCol();
                    ////// soo 컬럼추가 //////	
                }
                mouseAction = true;
                
                // 컬럼 추가 버튼 보여주기
                $('.col-add-btn').show();
                
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

function insertCol() {
	$.ajax({
		url:'insertCol.do',
		type:'post',
		data:{'ColName' : $('#colName').val()},
		success:function(){
		}
	})

}

////////// todo
function insertBoardTask(colno, t_name) {
	
	console.log('------------------');
	console.log('colno : ' + colno);
	console.log('t_name : ' + t_name);
	console.log('------------------');
	
//	var data = {
//		'col_no' : colno,
//		'task_name' : t_name
//	};
	
	$.ajax({
		url : "insertBoardTask.do",
		type : "post",
//		dataType: "json",
		data : {
			'task_name' : t_name,
			'col_no' : colno
		},
//		contentType : "application/json; charset=utf-8"
		success:function(){
			alert("ok");
		},
		error:function(){
			alert("error");
		}
	});
	
	return false;
}