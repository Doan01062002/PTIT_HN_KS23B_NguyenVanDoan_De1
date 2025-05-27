<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Quản lý sản phẩm</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        .error { color: red; }
        .pagination a { margin: 0 5px; }
    </style>
</head>
<body>
<h1>Quản lý sản phẩm</h1>
<a href="<c:url value='/product/add'/>">Thêm mới</a>
<form action="<c:url value='/product/search'/>" method="get">
    <input type="text" name="name" placeholder="Tìm kiếm sản phẩm">
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
        <th>Giá</th>
        <th>Ảnh</th>
        <th>Danh mục</th>
        <th>Trạng thái</th>
        <th>Ngày tạo</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td><img src="${product.image}" width="50" alt="Product Image"></td>
            <td>${product.categoryName}</td>
            <td>${product.status ? 'Hoạt động' : 'Không hoạt động'}</td>
            <td>${product.createdAt}</td>
            <td>
                <a href="<c:url value='/product/edit/${product.id}'/>">Sửa</a>
                <a href="<c:url value='/product/delete/${product.id}'/>" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
<div class="pagination">
    <c:forEach begin="1" end="${totalPages}" var="i">
        <a href="<c:url value='/product/list?page=${i}'/>" ${i == currentPage ? 'style="font-weight:bold"' : ''}>${i}</a>
    </c:forEach>
</div>
</body>
</html>
