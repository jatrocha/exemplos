<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="pt-br">
<head>
<title>jQuery UI</title>

<link href="${pageContext.request.contextPath }/css/estilos.css" rel="stylesheet" type="text/css" /> <!-- CSS para as páginas exemplo -->
<link type="text/css" href="${pageContext.request.contextPath }/css/ui-lightness/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" />	

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-ui-1.9.2.custom.min.js"></script>
<script>

	$(function() { 

		$('.log').ajaxSend(function(e, jqxhr, settings) {
		     $(this).html('<p>Ajax Send executado</p>');
		});

		$('.log').ajaxComplete(function(e, xhr, settings) {
		     $(this).html('<p>Ajax Complete executado.</p>');
		 
		});

		 $('<table/>', {
				'id': 'resultado',
				'border': '1',
				'align': 'center',
				}).insertAfter('#marca');
	
		$("#marca").change(function(){
			
			$.ajax({
				  type: "GET",
				  url: "http://localhost:8090/rest-api/carro/query",
				  dataType: "json",
				  error: function(jqXHR, textStatus, errorThrown){
					  alert(errorThrown);
					  },
				  complete: function(jqXHR, textStatus){
					  alert('complete');
					  },
				  success: function (data, textStatus, jqXHR){
					  
					  
						  $("#resultado tr").each(function() {
						         $(this).remove();
						    });
						  						  	
					  		var thead = $('<tr>');
					  		$("<td>").html("<b>Marca</b>").appendTo(thead);
							$("<td>").html("<b>Modelo</b>").appendTo(thead);
							$("<td>").html("<b>Versao</b>").appendTo(thead);
							$("<td>").html("<b>Motor</b>").appendTo(thead);
							thead.appendTo("#resultado");
					  		
							  for(var i = 0; i < data.length; i++){
								  var row = $('<tr>');		
								  $("<td>").text(data[i].marca).appendTo(row);
								  $("<td>").text(data[i].modelo).appendTo(row);
								  $("<td>").text(data[i].versao).appendTo(row);
								  $("<td>").text(data[i].motor).appendTo(row);
								  row.appendTo("#resultado");
							  }

					  		
					  }
			});	
		});

		
		$('.log').load('${pageContext.request.contextPath }/button.jsp #checkboxes', function(response, status, xhr) {
			//alert(xhr);
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
		
		<div class="log" align="center"></div>
		<br />
		<div align="center">
			<label for="marca">Marca: </label>
			<select id="marca" name="marca">
				<option value="">Selecione</option>
				<option value="FIAT">FIAT</option>
				<option value="FORD">FORD</option>
				<option value="KIA">KIA</option>
				<option value="RENAULT">RENAULT</option>
				<option value="CITROEN">CITROEN</option>
				<option value="HONDA">HONDA</option>
				<option value="HYUNDAI">HYUNDAI</option>
				<option value="PEUGEOT">PEUGEOT</option>
				<option value="TOYOTA">TOYOTA</option>
				<option value="VOLKSWAGEN">VOLKSWAGEN</option>
			</select>
			
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
