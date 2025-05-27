<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Category List</h2>
<a href="category?action=new">Add New</a>
<table border="1">
    <tr><th>ID</th><th>Name</th><th>Description</th><th>Actions</th></tr>
    <c:forEach var="category" items="${categories}">
        <tr>
            <td>${category.category_id}</td>
            <td>${category.category_name}</td>
            <td>${category.description}</td>
            <td>
                <a href="category?action=edit&id=${category.category_id}">Edit</a> |
                <a href="category?action=delete&id=${category.category_id}" onclick="return confirm('Bạn có muốn xóa?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
