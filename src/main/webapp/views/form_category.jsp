<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="category" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="category_name" value="${category.category_name}" required/>
    <br/>
    <label for="description">Description:</label>
    <textarea id="description" name="description" required>${category.description}</textarea>
    <br/>
    <button type="submit">Save</button>
</form>
<a href="">Back</a>
</body>
</html>