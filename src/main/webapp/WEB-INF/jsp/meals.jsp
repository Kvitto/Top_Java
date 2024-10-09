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
                <th><a href="meals?id=null&action=edit"><img src="img/add.png"></a></th>
            </tr>
        </thead>
        <tbody>
            <jsp:useBean id="mealsTo" scope="request" type="java.util.List"/>
            <c:forEach var="meal" items="${mealsTo}">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}" var="dateTime" />
                <tr style="color:${meal.excess ? "Tomato" : "MediumSeaGreen"};">
                    <td>${dateTime}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td>
                        <a href="meals?id=${meal.id}&action=edit"><img src="img/pencil.png"></a>
                        <img src="../../img/spacer.png" width="10">
                        <a href="meals?id=${meal.id}&action=delete"><img src="img/delete.png"></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
</body>
</html>