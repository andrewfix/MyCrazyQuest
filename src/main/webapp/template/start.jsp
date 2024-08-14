<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h1>${gameService.getGameTitle()}</h1>
<h2>${gameService.getGameDescription()}</h2>

<p>Как тебя зовут, о спаситель!<p>
<form method="POST" action="/start">
    <input type="text" name="userName" />
    <button type="submit">Начать игру</button>
</form>

</body>
</html>
