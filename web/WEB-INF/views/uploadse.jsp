<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form action="/servlet/upload" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <leabl>文件描述</leabl>
            <input type="text" class="form-control" name="fileStr">
            <leabl>选择文件</leabl>
            <input type="file" class="form-control" name="doc">
            <button class="btn btn-primary">上传</button>
        </div>
    </form>
</div>
</body>
</html>
