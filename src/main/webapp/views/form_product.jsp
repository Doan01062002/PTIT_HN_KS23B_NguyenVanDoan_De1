<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="product" method="post">
    <input type="hidden" name="id" value="${product.id}"/>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${product.name}" required/>
    <br/>
    <label for="price">Price:</label>
    <input type="number" id="price" name="price" value="${product.price}" required/>
    <br/>
    <label for="description">Description:</label>
    <textarea id="description" name="description" required>${product.description}</textarea>
    <br/>
    <label for="category">Category:</label>
    <select id="category" name="category" required>
        <option value="">Select Category</option>
        <c:forEach var="cat" items="${categories}">
            <option value="${cat.id}" ${cat.id == product.category.id ? 'selected' : ''}>${cat.name}</option>
        </c:forEach>
    </select>
    <br/>
    <label for="image">Image URL:</label>
    <input type="text" id="image" name="image" value="${product.image}" required/>
    <br/>
    <label for="createdAt">Created At:</label>
    <input type="date" id="createdAt" name="createdAt" value="${product.createdAt}" required/>
    <br/>
    <button type="submit">Save</button>
</form>
<a href="">Back</a>
</body>
</html>