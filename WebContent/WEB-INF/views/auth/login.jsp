<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Login</title>
</head>
<body>
	<h3>Efetue Login</h3>
	<form:form servletRelativeAction="/login">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
		<table>
			<tr>
				<td>Login:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Senha:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Logar" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>