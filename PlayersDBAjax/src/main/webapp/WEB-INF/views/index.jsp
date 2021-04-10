<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  
  <!-- Ultima versione di bootstrap (minified) -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <!-- Custom styles -->
  <link rel="stylesheet" href="/resources/css/custom.css">
  
</head>
<body>
<div Class="container">
	<h1 id="titolo">Registrazione nuovo giocatore</h1>
	<img src="/resources/img/giocatore.png" alt="pagina caricata" >
	<form action="#" name="modulo" id="modulo">
		<div class="form-group row">
			<label class="col-lg-1 col-form-label" for="id">ID</label>
			<div class="col-lg-3">
				<input name="id" class="form-control" style="width : 80px" type="text" id="id" readonly />
			</div>
		</div>
		 <div class="form-group row">
			<label class="col-lg-1 col-form-label" for="nome">Nome</label>
			 <div class="col-lg-3">
				<input class="form-control" type="text" name="nome" id="nome" />
			 </div>
	    </div>
        <div class="form-group row">	    	
			<label class="col-lg-1 col-form-label" for="cognome">Cognome</label>
			<div class="col-lg-3">
				<input class="form-control" type="text" name="cognome" id="cognome" />
			</div>
		</div>
		
		<div class="form-group row">
		<label class="col-lg-1 col-form-label" for="squadra">Squadra</label>
			<div class="col-lg-3">
				<input class="form-control" type="text" name="squadra" id="squadra" />
			</div>	
		</div>
		<div class="col-lg-2">
			<input class="btn btn-primary btn-lg" width=200 type="submit" class="submit, form-control" id="btnInvio" value="Invia i dati" />
		</div>	
	</form>
	<br/><br/>
	<div class="btn-group btn-group-lg">
		<button class="btn btn-secondary" id="bntLista">Lista</button>
		<button class="btn btn-secondary" id="btnSvuota">Svuota</button>
		<button class="btn btn-danger" id="btnDelete">Elimina</button>
	</div>
	<button class="btn btn-success btn-lg" id="btnUpdate">Aggiorna</button>
	<br/><br/>
	<div id="somediv"></div>
	
	<!-- bootstrap js -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	  <!-- Ultima versione di jquery.validate (minfied) -->
  	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
  	<!-- my codice jQuery -->
  	<script>
  		$('#btnUpdate').hide();
  	</script>
  	<script type="text/javascript" src="/resources/js/app.js"></script>
  	</div>
</body>
</html>

