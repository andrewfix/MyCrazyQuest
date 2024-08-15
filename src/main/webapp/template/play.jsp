<%@ include file="/design/header.jsp" %>

<h2>${gameService.getStateNodeDescriptions()}</h2>

<form method="POST" action="${contextPath}/play">
   <fieldset>
   <c:forEach var="transitions" items="${gameService.getStateNodeTransitions()}">
    <div>
        <input type="radio" name="transition" value="${transitions.getKey()}" />
        <label>"${transitions.getValue()}"</label>
      </div>
   </c:forEach>
   </fieldset>
   <button type="submit">Выполнить</button>
</form>

<%@ include file="/design/footer.jsp" %>