/**
 * 
 */
"use strict"

function fornavnSjekk() {
    const fornavn = document.forms["root"]["fornavn"];
    const reg = /^[A-ZÆØÅ]{1}[a-zA-ZæøåÆØÅ -]{1,19}$/;
    if(reg.test(fornavn.value)){
          fornavn.style.border="2px solid green";
      } else {
      fornavn.style.border="2px solid red";
 	}
}

function etternavnSjekk(){
	const etternavn = document.forms["root"]["etternavn"];
	const reg = /^[A-ZÆØÅ]{1}[a-zA-ZæøåÆØÅ-]{1,19}$/;	
	if(reg.test(etternavn.value)){
		etternavn.style.border="2px solid green";
	}else {
		etternavn.style.border="2px solid red";
	}
}

function mobilSjekk(){
	const mobil = document.forms["root"]["mobil"];
	const reg = /^[0-9]{8}$/;
	if(reg.test(mobil.value)){
		mobil.style.border="2px solid green";
	}else {
		mobil.style.border="2px solid red";
	}
}

function passordSjekk(){
	const passord = document.forms["root"]["passord"];
	const reg = /^.{1,7}$/;
	const reg1 = /^.{8,11}$/;
	const reg2 = /^.{12,64}$/;
	if(reg.test(passord.value)){
		passord.style.border="2px solid red";
	}else if(reg1.test(passord.value)){
		passord.style.border="2px solid orange";
	}else if(reg2.test(passord.value)){
		passord.style.border="2px solid green";
	}
}

function passordRepetertSjekk(){
	const passord = document.forms["root"]["passordRepetert"];
	const passordRepetert = document.forms["root"]["passord"];
	if(passord.value == passordRepetert.value){
		passord.style.border="2px solid green";
	}else {
		passord.style.border="2px solid red";
	}
}