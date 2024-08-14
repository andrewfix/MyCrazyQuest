<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

<h2>${gameService.getStateNodeDescriptions()}</h2>
<form method="POST" action="/play">
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
</body>
</html>
