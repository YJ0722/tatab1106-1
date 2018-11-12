<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="resources/css/board/taskStyle.css"/>

    <!-- Ignite UI Required Combined CSS Files -->
    <link href="http://cdn-na.infragistics.com/igniteui/2018.1/latest/css/themes/infragistics/infragistics.theme.css" rel="stylesheet" />
    <link href="http://cdn-na.infragistics.com/igniteui/2018.1/latest/css/structure/infragistics.css" rel="stylesheet" />

    <script src="http://ajax.aspnetcdn.com/ajax/modernizr/modernizr-2.8.3.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

    <!-- Ignite UI Required Combined JavaScript Files -->
    <script src="http://cdn-na.infragistics.com/igniteui/2018.1/latest/js/infragistics.core.js"></script>
    <script src="http://cdn-na.infragistics.com/igniteui/2018.1/latest/js/infragistics.lob.js"></script>

    <!-- 파일 업로드 -->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
    <div id="container">
<div id="boxs">
    <span>D-?</span>
    <img src="resources/img/board/bookmarkButton.png" width="30" height="30"/>
    <img src="resources/img/board/mButton.jpg" width="30" height="30"/>
    <img src="resources/img/board/closeButton.png" width="30" height="30" class="right"/>
</div>
        
<div id="box1">
<div class="form-group shadow-textarea">
    <label for="exampleFormControlTextarea6"></label>
    <textarea class="form-control z-depth-1" id="exampleFormControlTextarea6" rows="3" placeholder="업무명..."></textarea>
</div></div>

<div id="boxs">
<div class="form-group shadow-textarea2">
    <label for="exampleFormControlTextarea6"></label>
    <textarea class="form-control z-depth-1" id="exampleFormControlTextarea6" rows="3" placeholder="업무 내용..."></textarea>
</div></div>

<div id="boxs">
<div>
        <div class="form-group">
            <label>Upload Image</label>
            <div class="input-group">
                <span class="input-group-btn">
                    <span class="btn btn-default btn-file">
                        Browse… <input type="file" id="imgInp">
                    </span>
                </span>
                <input type="text" class="form-control" readonly>
            </div>
            <img id='img-upload'/>
        </div>
    
</div></div>

<div id="boxs">
<div id="myDIV" class="header">
    <span>
  <input type="text" id="myInput" placeholder="Comment...">
  <span onclick="newElement()" class="addBtn">Add</span>
    </span>
</div>
<div>
    <ul id="myUL">
      <li>Created by</li>
    </ul>
</div></div>
    
<script src="resources/js/board/taskScript.js">
</script>

</body>
</html>
