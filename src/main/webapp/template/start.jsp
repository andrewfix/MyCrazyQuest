<%@ include file="/design/header.jsp" %>

<h2>${gameService.getGameDescription()}</h2>

<p>Как тебя зовут, о спаситель!<p>
<form method="POST" action="${contextPath}/start">
    <input type="text" name="userName" />
    <button type="submit">Начать игру</button>
</form>
<p id="errorMessages">${errorMessages}</p>

<%@ include file="/design/footer.jsp" %>
