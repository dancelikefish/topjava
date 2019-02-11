<%@ page import="ru.javawebinar.topjava.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html" ; charset="UTF-8">
    <title>Meals with excess</title>
</head>
<body>
<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Description</th>
        <th>Calories</th>
        <th>Meal time</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="mealTo" items="${mealsWithExcess}">
        <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
        <c:set var="redColor" value="<tr style=\"color:red\">"/>
        <c:set var="greenColor" value="<tr style=\"color:green\">"/>

        ${mealTo.excess == true ? redColor : greenColor}
        <td>${mealTo.description}</td>
        <td>${mealTo.calories}</td>
        <td><%=HtmlUtil.formatDate(mealTo.getDateTime())%></td>
        <td><a href="meals?action=update&id=<c:out value="${mealTo.id}"/>">Update</a></td>
        <td><a href="meals?action=delete&id=<c:out value="${mealTo.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="meals?action=add">Add Meal</a></p>
<p><a href="index.html">Home</a></p>
</body>
</html>
