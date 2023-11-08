<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article write</title>
</head>
<body>
	<h1>게시물 작성</h1>
	
	<form action="doWrite" method="post">
		<div>
			제목 : <input name="title" type="text" placeholder="제목을 입력해주세요"/>
		</div>
		<div>
			내용 : <textarea name="body" placeholder="내용을 입력해주세요"></textarea>
		</div>
		
		<button>작성</button>
<!-- 		<input type="submit" value="작성" /> -->
	</form>
</body>
</html>