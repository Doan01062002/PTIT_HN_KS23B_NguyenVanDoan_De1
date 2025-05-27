<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Sửa danh mục</title>
  <style>
    .error { color: red; }
  </style>
</head>
<body>
<h1>Sửa danh mục</h1>
<form:form modelAttribute="category" method="post" action="${pageContext.request.contextPath}/category/edit">
  <form:hidden path="id"/>
  <div>
    <label>Tên:</label>
    <form:input path="name"/>
    <form:errors path="name" cssClass="error"/>
  </div>
  <div>
    <label>Mô tả:</label>
    <form:textarea path="description"/>
    <form:errors path="description" cssClass="error"/>
  </div>
  <div>
    <label>Trạng thái:</label>
    <form:checkbox path="status" value="true"/>
    <form:errors path="status" cssClass="error"/>
  </div>
  <input type="submit" value="Cập nhật">
  <a href="<c:url value='/category/list'/>">Hủy</a>
</form:form>
</body>
</html>
