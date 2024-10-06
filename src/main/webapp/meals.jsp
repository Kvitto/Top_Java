<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<section>
    <table class="table">
        <thead>
            <tr>
                <th>Дата/Время</th>
                <th>Описание</th>
                <th>Калории</th>
            </tr>
        </thead>
        <tbody>
            <jsp:useBean id="mealsToStorage" scope="request" type="java.util.List"/>
            <c:forEach var="meal" items="${mealsToStorage}">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" var="dateTime" />
                <tr style=${meal.excess ? "color:Tomato;" : "color:MediumSeaGreen;"}>
                    <td>${dateTime}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
</body>
</html>