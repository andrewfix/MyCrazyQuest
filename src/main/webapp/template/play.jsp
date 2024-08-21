<%@ include file="/design/header.jsp" %>

<fieldset>
<c:forEach var="info" items="${gameService.getEntityInfo()}">
<div>
    <b>${info.getKey()}: </b> ${info.getValue()}
</div>
</c:forEach>
</fieldset>

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
   <p id="errorMessages">${errorMessages}</p>
   <button type="submit">Выполнить</button>
</form>

<%@ include file="/design/footer.jsp" %>