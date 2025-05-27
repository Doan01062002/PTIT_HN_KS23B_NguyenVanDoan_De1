<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Thêm sản phẩm</title>
    <style>
        .error { color: red; }
    </style>
</head>
<body>
<h1>Thêm sản phẩm</h1>
<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>
<form:form modelAttribute="product" method="post" action="${pageContext.request.contextPath}/product/add">
    <div>
        <label>Tên sản phẩm:</label>
        <form:input path="name"/>
        <form:errors path="name" cssClass="error"/>
    </div>
    <div>
        <label>Mô tả:</label>
        <form:textarea path="description"/>
        <form:errors path="description" cssClass="error"/>
    </div>
    <div>
        <label>Giá:</label>
        <form:input path="price" type="number" step="0.01"/>
        <form:errors path="price" cssClass="error"/>
    </div>
    <div>
        <label>Danh mục:</label>
        <form:select path="categoryId">
            <form:option value="0" label="-- Chọn danh mục --"/>
            <form:options items="${categories}" itemValue="id" itemLabel="name"/>
        </form:select>
        <form:errors path="categoryId" cssClass="error"/>
    </div>
    <div>
        <label>URL ảnh sản phẩm:</label>
        <input type="text" name="imageUrl" placeholder="Nhập URL ảnh (JPEG/PNG)"/>
    </div>
    <div>
        <label>Trạng thái:</label>
        <form:checkbox path="status" value="true" checked="checked"/>
        <form:errors path="status" cssClass="error"/>
    </div>
    <input type="submit" value="Thêm">
    <a href="<c:url value='/product/list'/>">Hủy</a>
</form:form>
</body>
</html>