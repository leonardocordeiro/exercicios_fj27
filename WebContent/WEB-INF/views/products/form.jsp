<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de livros</title>
</head>
<body>
	<form action='<c:url value="/produtos" />' method="post">
		<div>
			<label for="titulo">Titulo</label> <input type="text" name="title"
				id="title" />
		</div>
		<div>
			<label for="descricao">Descrição</label>
			<textarea rows="10" cols="20" name="description" id="descricao"></textarea>
		</div>
		<div>
			<label for="numeroPaginas">Número de paginas</label> <input
				type="text" name="numberOfPages" id="numberOfPages" />
		</div>
		<div>
			<input type="submit" value="Enviar">
		</div>
	</form>
</body>
</html>