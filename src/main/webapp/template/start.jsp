<%@ include file="/design/header.jsp" %>

<h1>${gameService.getGameTitle()}</h1>
<h2>${gameService.getGameDescription()}</h2>

<p>Как тебя зовут, о спаситель!<p>
<form method="POST" action="${contextPath}/start">
    <input type="text" name="userName" />
    <button type="submit">Начать игру</button>
</form>

<%@ include file="/design/footer.jsp" %>
