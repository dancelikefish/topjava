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
    </tr>
    <c:forEach var="mealTo" items="${mealsWithExcess}">
        <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
            <c:choose>
            <c:when test="${mealTo.excess == true}">
                <tr style="color:red">
                <td><%=mealTo.getDescription()%></td>
                <td><%=mealTo.getCalories()%></td>
                <td><%=HtmlUtil.formatDate(mealTo.getDateTime())%></td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr style="color:green">
                <td><%=mealTo.getDescription()%></td>
                <td><%=mealTo.getCalories()%></td>
                <td><%=HtmlUtil.formatDate(mealTo.getDateTime())%></td>
                </tr>
            </c:otherwise>
            </c:choose>
    </c:forEach>
</table>
</body>
</html>
