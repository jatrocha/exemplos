<!DOCTYPE html>
<html>
  <head>
    <title>Bootstrap 101 Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>


	<form role="form" action="#"  id="form">

        <div class="form-group">
            <label for="CPF">ID</label> 
            <input type="text" class="form-control" id="id" placeholder="id" name="id">
        </div>

		<div class="form-group">
			<label for="modelo">Titulo</label> <input
				type="text" class="form-control" id="titulo" placeholder="titulo" name="titulo">
		</div>

        <div class="form-group">
            <label for="marca">Autor</label> <input
                type="text" class="form-control" id="autor" placeholder="marca" name="autor" >
        </div>

        <div class="form-group">
            <label for="versao">Genero</label> <input
                type="text" class="form-control" id="genero" placeholder="versao" name="genero">
        </div>


		<button type="submit" class="btn btn-default" id="btnSubmit">Submit</button>
	</form>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.json-2.4.min.js"></script>

<script type="text/javascript">

jQuery('form').bind('submit', function(event){
    event.preventDefault();

    var form = this;
    var json = ConvertFormToJSON(form);
    
    console.log(json);
    
    $.ajaxSetup({   
        contentType: "application/json; charset=ISO-8859-1"
     }); 
    
    $.ajax({
    	
    	beforeSend: function(req) {
    		req.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
    		req.setRequestHeader("Accept", "application/json");
    	},
        type: "POST",
        url: "http://localhost:8090/rest-api/livro/",
        data: json,
        dataType: "json"
    });
    return true;
});


jQuery(document).on('ready', function() {
});

function ConvertFormToJSON(form){
    var array = jQuery(form).serializeArray();
    var json = {};
    
    jQuery.each(array, function() {
        json[this.name] = this.value || '';
    });
    
    return $.toJSON(json);
}

</script>

  </body>
</html>


