<%@ page import="java.time.LocalDateTime" %>
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
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <table class="table">
            <thead>
            <tr>
                <th>Дата/Время</th>
                <th>Описание</th>
                <th>Калории</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
                <fmt:parseDate value="${LocalDateTime.now()}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}" var="dateTime" />
                <input type="hidden" name="id" value="${meal.id}">
                <tr>
                    <td><input type="datetime-local" name="datetime"
                               value="${meal.id == 0 ? dateTime : meal.dateTime}"></td>
                    <td><input type="text" placeholder="description"  name="description"
                               value="${meal.id == 0 ? "" : meal.description}"></td>
                    <td><input type="number" name="calories"
                               value="${meal.id == 0 ? 0 : meal.calories}"></td>
                    <td>
                        <button type="submit"><img src="img/add.png" alt="Save"></button>
                        <button type="reset" onclick="window.history.back()"><img src="img/delete.png" alt="Back"></button>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</section>
</body>
</html>
