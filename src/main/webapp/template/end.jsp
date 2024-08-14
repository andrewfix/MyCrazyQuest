<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

<h2>${gameService.getStateNodeDescriptions()}</h2>
<button onclick="location.href='/start'">Новая игра</button>
</body>
</html>
