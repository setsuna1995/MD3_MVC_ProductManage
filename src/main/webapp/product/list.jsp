<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Product</h1>
<p>
    <a href="${pageContext.request.contextPath}/product?action=create">Create new product</a>
</p>
<table>
    <tr>
        <td>Name</td>
        <td>Email</td>
        <td>Address</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>

    <c:forEach items='${products}' var="product">
        <tr>
            <td><a href="${pageContext.request.contextPath}/product?action=view&id=${product.getId()}">${product.getName()}</a></td>
            <td>${product.getEmail()}</td>
            <td>${product.getAddress()}</td>
            <td><a href="${pageContext.request.contextPath}/product?action=edit&id=${product.getId()}">edit</a></td>
            <td><a href="${pageContext.request.contextPath}/product?action=delete&id=${product.getId()}">delete</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>