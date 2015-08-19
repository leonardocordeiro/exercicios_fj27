<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 

	<c:forEach items="${requestScope['org.springframework.validation.BindingResult.aluno'].allErrors}"
			   var="erro">
	${erro.code} - <br>
	${erro.defaultMessage}
			   
   </c:forEach>
 --%>

<%-- 
<spring:hasBindErrors name="aluno">
	<c:forEach items="${errors.allErrors}" var="error">
		${error.code}
	</c:forEach>
</spring:hasBindErrors> 
--%>

<%-- 
<spring:hasBindErrors name="aluno">
	<c:forEach items="${errors.allErrors}" var="error">
		<spring:message code="${error.code}" />
	</c:forEach>
</spring:hasBindErrors> 
--%>

<spring:hasBindErrors name="aluno">
	<c:forEach items="${errors.getFieldErrors('nome')}" var="error">
		<spring:message code="${error.code}" />
	</c:forEach>
</spring:hasBindErrors> 

	<br><br><br><br><br>
	<form:form commandName="aluno" action="${spring:mvcUrl('AC#save').build()}">
		<input type="text" name="nome"></input> <input type="submit"
			value="Cadastrar">
		<form:errors path="nome" />
	</form:form>
</body>
</html>