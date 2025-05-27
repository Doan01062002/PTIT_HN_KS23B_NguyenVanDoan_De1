<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Quản lý danh mục</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        .error { color: red; }
    </style>
</head>
<body>
<h1>Quản lý danh mục</h1>
<a href="<c:url value='/category/add'/>">Thêm mới</a>
<form action="<c:url value='/category/search'/>" method="get">
    <input type="text" name="name" placeholder="Tìm kiếm danh mục">
    <input type="submit" value="Search">
</form>
<c:if test="${not empty message}">
    <p>${message}</p>
</c:if>
<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>
<table>
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Mô tả</th>
        <th>Trạng thái</th>
        <th>Ngày tạo</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="category" items="${categories}">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td>${category.description}</td>
            <td>${category.status ? 'Hoạt động' : 'Không hoạt động'}</td>
            <td>${category.createdAt}</td>
            <td>
                <a href="<c:url value='/category/edit/${category.id}'/>">Sửa</a>
                <a href="<c:url value='/category/delete/${category.id}'/>" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
