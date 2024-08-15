<%@ include file="/design/header.jsp" %>

<h2>${gameService.getStateNodeDescriptions()}</h2>
<button onclick="location.href='${contextPath}/start'">Новая игра</button>

<%@ include file="/design/footer.jsp" %>
