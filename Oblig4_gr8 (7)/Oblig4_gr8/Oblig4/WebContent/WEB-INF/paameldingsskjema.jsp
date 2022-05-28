<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link href="css/formcontroller.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<script src="js/formcontroller.js" defer></script>
<title>Påmelding</title>
</head>
<body>
	<h2>Påmelding</h2>
	<form name="root" method="post" class="pure-form pure-form-aligned" action="Paamelding">
		<fieldset>
			<div class="pure-control-group">
				<label for="fornavn">Fornavn:</label> 
				<input type="text" name="fornavn" value="${fornavn}" placeholder="Fornavn" onkeyup="fornavnSjekk();" required/> 
				<font color="red">${fornavnFeilmelding}</font>
			</div>
			<div class="pure-control-group">
				<label for="etternavn">Etternavn:</label> 
				<input type="text" name="etternavn" value="${etternavn}" placeholder="Etternavn" onkeyup="etternavnSjekk();" required /> 
				<font color="red">${etternavnFeilmelding}</font>
			</div>
			<div class="pure-control-group">
				<label for="mobil">Mobil (8 siffer):</label> 
				<input type="text" name="mobil" id="mobil" value="${mobil}" placeholder="Mobilnummer" onkeyup="mobilSjekk();" required/> 
				<font color="red">${mobilFeilmelding}</font>
			</div>
			<div class="pure-control-group">
				<label for="passord">Passord:</label> 
				<input type="password" name="passord" id="passord" value="${passord}" placeholder="Passord" onkeyup="passordSjekk();" required 
					onmouseover="this.parentElement.querySelector('#passordinfo').textContent = 'Passordstyrke regnes ut ifra lengde. 1-7 er ugyldig. 8-15 er svakt. 16-64 er sterkt.'"
					onmouseout="this.parentElement.querySelector('#passordinfo').textContent = '${passordFeilmelding}'"/> 
				<font id="passordinfo" color="red">${passordFeilmelding}</font>
			</div>
			<div class="pure-control-group">
				<label for="passordRepetert">Passord repetert:</label> 
				<input type="password" name="passordRepetert" id="passordRepetert" value="${passordRepetert}" placeholder="Passord" onkeyup="passordRepetertSjekk();" required/> 
				<font color="red">${passordRepetertFeilmelding}</font>
			</div>
			<div class="pure-control-group">
				<label for="kjonn">Kjønn:</label> 
				<input type="radio" name="kjonn" value="mann"/>&ensp;mann&ensp;&ensp;
				<input type="radio" name="kjonn" value="kvinne"/>&ensp;kvinne
				<font color="red">${kjonnFeilmelding}</font>
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Registrer</button>&ensp;
				<button type="submit" form="logginn" class="pure-button pure-button-primary">Logg inn</button>
			</div>
		</fieldset>
	</form>
	<form action="Logginn" method="get" id="logginn"></form>
</body>
</html>