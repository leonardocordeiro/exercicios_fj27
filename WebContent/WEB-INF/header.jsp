<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<ul class="container">

	<li class="category"><a href="http://www.casadocodigo.com.br">
			Home </a>
	<li class="category"><a href="/collections/livros-de-agile"> <fmt:message
				key="navigation.category.agile" />
	</a>
	<li class="category"><a href="/collections/livros-de-front-end">
			<fmt:message key="navigation.category.front" />
	</a>
	<li class="category"><a href="/collections/livros-de-games"> <fmt:message
				key="navigation.category.games" />
	</a>
	<li class="category"><a href="/collections/livros-de-java"> <fmt:message
				key="navigation.category.java" />
	</a>
	<li class="category"><a href="/collections/livros-de-mobile">
			<fmt:message key="navigation.category.mobile" />
	</a>
	<li class="category"><a
		href="/collections/livros-desenvolvimento-web"> <fmt:message
				key="navigation.category.web" />
	</a>
	<li class="category"><a href="/collections/outros"> <fmt:message
				key="navigation.category.others" />
	</a></li>
</ul>
<nav id="main-nav"> <ul id="clearfix">
<li>
<a href="<c:url value="/produtos?locale=pt"/>">
Português
</a> </li>
<li>
<a href="<c:url value="/produtos?locale=en_US"/>">
Inglês
</a> </li>
</ul> </nav>