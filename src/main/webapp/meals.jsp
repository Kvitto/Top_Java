<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <th>Дата</th>
                <th>Время</th>
                <th>Описание</th>
                <th>Калории</th>
            </tr>
        </thead>
        <tbody>
            <jsp:useBean id="mealsToStorage" scope="request" type="java.util.List"/>
            <c:forEach var="meal" items="${mealsToStorage}">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
                <tr bgcolor=${meal.excess ? "red" : "green"}>
                    <td>${meal.date}</td>
                    <td>${meal.time}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
</body>
</html>