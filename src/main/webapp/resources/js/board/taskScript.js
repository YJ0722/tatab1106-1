//////////////////////////////////////////////////////////////////

// Create a "close" button and append it to each list item
var myNodelist = document.getElementsByTagName("LI");
var i;
for (i = 0; i < myNodelist.length; i++) {
  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  myNodelist[i].appendChild(span);
}



// Click on a close button to hide the current list item
//var close = document.getElementsByClassName("close");
//var i;
//for (i = 0; i < close.length; i++) {
//  close[i].onclick = function() {
//	  alert('');
//    var div = this.parentElement;
//    div.style.display = "none";
//    

//$(function(){
////	alert('when??');
//	$('.close').on('click',function(){
//		alert('yyj');
//	})
//})
    ///////////////////
//    var commentId = this.parents('li').attr('id');
//    var commentId = div.attr('id');
//    console.log('코멘트 id : ' + commentId);
//    
//    var commentNo = commentId; 
//
//    $.ajax({
//  		url : "deleteComment.do",
//  		type : "get",
//  		data : {
//  			'commentNo' : commentNo
//  		},
//  	    cache : false,
//  		success : function() {
//  							
//  			console.log('id로 설정할 comment_no : ' + commentVO.comment_no);
//  			$('li').eq(commentLiIndex).attr('id', commentVO.comment_no);
//  			
//  		}
//  	});
    //////////////////////
//  }

  
//}

// Create a new list item when clicking on the "Add" button
function newElement() {
	var taskNo = $('.modal-content').attr('id');
	console.log('taskNo : ' + taskNo);
	
	
	var nname = $('#nicknameText').text();
  var li = document.createElement("li");
  var nicknamebox = document.createElement("p");
  var contentbox = document.createElement("p");
  var inputValue = document.getElementById("myInput").value;
  var inputCommentVal = document.createTextNode(inputValue);
  var nicknameVal = document.createTextNode(nname);
  
  // 입력 텍스트 contentbox에 추가
  contentbox.appendChild(inputCommentVal);
  // 닉네임 nicknamebox에 추가
  nicknamebox.appendChild(nicknameVal);
  
  // li에 닉네임, 댓글 추가
  li.appendChild(nicknamebox);
  li.appendChild(contentbox);

  
  if (inputValue === '') {
    alert("You must write something!");
  } else {
    document.getElementById("myUL").appendChild(li);
  }
  document.getElementById("myInput").value = "";

  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  li.appendChild(span);

  
  $('#myUL li > p:eq(0)').attr('class', 'nicknamebox');
  $('#myUL li > p:eq(1)').attr('class', 'contentbox');
  

  for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
      var div = this.parentElement;
      div.style.display = "none";

      /*
      ///////////////////
      alert();
      var commentId = this.parents('li').attr('id');
      console.log('코멘트 id : ' + commentId);
      
      var commentNo = commentId; 
	  $.ajax({
			url : "deleteComment.do",
			type : "get",
			data : {
				'commentNo' : commentNo
			},
		    cache : false,
			success : function() {
								
//				console.log('id로 설정할 comment_no : ' + commentVO.comment_no);
//				$('li').eq(commentLiIndex).attr('id', commentVO.comment_no);
				
			}
		});
	*/	
    }
  }
  
  var commentLiIndex = $('li:last').index();
  console.log('$$$$$: ' + commentLiIndex);
  insertComment(taskNo, nname, inputValue, commentLiIndex);

}

// 파일 업로드

$(document).ready( function() {
    	$(document).on('change', '.btn-file :file', function() {
		var input = $(this),
			label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		input.trigger('fileselect', [label]);
		});

		$('.btn-file :file').on('fileselect', function(event, label) {
		    
		    var input = $(this).parents('.input-group').find(':text'),
		        log = label;
		    
		    if( input.length ) {
		        input.val(log);
		    } else {
		        if( log ) alert(log);
		    }
	    
		});
		function readURL(input) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		        
		        reader.onload = function (e) {
		            $('#img-upload').attr('src', e.target.result);
		        }
		        
		        reader.readAsDataURL(input.files[0]);
		    }
		}

		$("#imgInp").change(function(){
		    readURL(this);
		}); 	
	});


// insert comment ajax
function insertComment(taskNo, loginName, comment, commentLiIndex) {

	console.log('taskNo : ' + taskNo);
	console.log('loginName : ' + loginName);
	console.log('comment : ' + comment);
	
	  $.ajax({
			url : "insertComment.do",
			type : "post",
			data : {
				'taskNo' : taskNo,
				'loginName' : loginName,
				'comment' : comment
			},
		    cache : false,
			success : function(commentVO) {
								
				console.log('id로 설정할 comment_no : ' + commentVO.comment_no);
				$('li').eq(commentLiIndex).attr('id', commentVO.comment_no);
				
			}
		})
	
}