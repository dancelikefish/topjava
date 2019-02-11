<%@ page import="ru.javawebinar.topjava.util.HtmlUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add or Edit Meal</title>
</head>
<body>
<form method="POST" action='meals' name="AddMeal">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <input type="hidden" name="id" value="${meal.id}">
        Date : <input type="text" name="dateTime" value="<c:out value="<%=HtmlUtil.formatDate(meal.getDateTime())%>" />" placeholder="dd.MM.yyyy HH:mm"/> <br/>
        Description : <input type="text" name="description" value="<c:out value="${meal.description}" />"/> <br/>
        Calories : <input type="text" name="calories" value="<c:out value="${meal.calories}" />"/> <br/>
    <input type="submit" value="Submit"/>
</form>

<a href="meals">Back to meals</a>
</body>
</html>
