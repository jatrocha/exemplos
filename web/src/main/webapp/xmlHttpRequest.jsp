<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", 0); // Proxies.
%>
<html lang="pt-br">
<head>
<title>jQuery UI</title>

<link href="${pageContext.request.contextPath }/css/estilos.css" rel="stylesheet" type="text/css" /> <!-- CSS para as páginas exemplo -->
<link type="text/css" href="${pageContext.request.contextPath }/css/ui-lightness/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" />	

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-ui-1.9.2.custom.min.js"></script>
<script>

	$(function() { 

		$("#botao").click(function(){

			var xmlHttp;

			if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest();
			} 
			else{
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}

			xmlHttp.open('GET', 'livros.xml', true);
			xmlHttp.setRequestHeader('Pragma', 'no-cache');
			xmlHttp.setRequestHeader('Cache-Control', 'no-cache');
			xmlHttp.setRequestHeader('Content-Type', 'text/plain;charset=UTF-8');
			xmlHttp.send(null);

			if(xmlHttp.status == 200){
				var resposta = xmlHttp.responseText;
				var respostaXML = xmlHttp.responseXML;

				 $(respostaXML).find('Livro').each(function () {
                     var sTitulo = $(this).find('Titulo').text();
                     var sAutor = $(this).find('Autor').text();
                     var sGenero = $(this).find('Genero').text();
                     $("<li></li>").html(sTitulo + ", " + sAutor + ", " + sGenero).appendTo("#resp ul");
                 });

				$('.log').html(resposta);
			}
			
		});
	});
	
	
</script>
</head>
<body>
<div id="tudo">
<div id="conteudo">
<header>	
	<h1>Ajax</h1>
	<div class="clear"></div>	
</header>
<section>	
	<form>
		
		<div align="center">
			<button id="botao">Exec AJAX XMLHttpRequest</button>
			
			<br />
			<div class="log" align="center"></div>
			<vr />
			<div id="resp">
				<ul></ul>
			</div>
		</div>
	</form>
</section>
<footer>	
	<div class="clear"></div>	
</footer>
</div> <!-- /conteudo -->
</div> <!-- /tudo -->
</body>
</html>
