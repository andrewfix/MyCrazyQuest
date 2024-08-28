<%@ include file="/design/header.jsp" %>

<h2>Игрок: ${userName}</h2>

<fieldset>
<c:forEach var="info" items="${еntityInfo}">
<div>
    <b>${info.getKey()}: </b> ${info.getValue()}
</div>
</c:forEach>
</fieldset>

<h2>${stateNodeDescriptions}</h2>

<form method="POST" action="${contextPath}/play">
   <fieldset>
   <c:forEach var="transitions" items="${stateNodeTransitions}">
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