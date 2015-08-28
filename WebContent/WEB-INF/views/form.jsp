<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="alternativa" method="post">

	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	
	<input name="alternativas[0].descricao" type="text" value="${alternativas.alternativas[0].descricao }"/>
	<form:errors path="alternativas.alternativas[0].descricao"/>
	
	<input name="alternativas[1].descricao" type="text" value="${alternativas.alternativas[1].descricao }"/>
	<form:errors path="alternativas.alternativas[1].descricao"/>
	
	<input type="submit" value="ok">
</form>


</body>
</html>