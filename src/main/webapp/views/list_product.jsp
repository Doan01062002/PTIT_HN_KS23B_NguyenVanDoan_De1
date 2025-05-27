<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Products List</h2>
<a href="product?action=new">Add New</a>
<table border="1">
    <tr><th>ID</th><th>Name</th><th>Description</th><th>Price</th><th>Image</th><th>Status</th><th>Actions</th></tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.product_id}</td>
            <td>${product.product_name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td><img src="${product.image_url}" alt="Product Image" width="50"/></td>
            <td>${product.status}</td>
            <td>
                <a href="product?action=edit&id=${product.product_id}">Edit</a> |
                <a href="product?action=delete&id=${product.product_id}" onclick="return confirm('Bạn có muốn xóa?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>