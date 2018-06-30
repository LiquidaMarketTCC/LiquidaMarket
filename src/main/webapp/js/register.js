$(document).ready(function()
{	
	
	$('#Conteudo').on('click', '#BTN_Login', function() {
		console.log("Clicou em salvar");
		login();
	});
	
	$('#Conteudo').on('click', '#BTN_CriarConta', function() {
		console.log("Clicou em criar conta");
		registro();
	});
	
	function registro(){

		cpfCnpj = document.getElementById('inputCpfCnpj').value;
		email = document.getElementById('inputEmail').value;
		senha = document.getElementById('inputSenha').value;

		if(cpfCnpj.length == 11){ 
			window.location = "./cadConsumidor.html?"+"cpf="+cpfCnpj+"&email="+email+"&senha="+senha;
						}
		else if(cpfCnpj.length == 14){
			window.location = "./cadEstabelecimento.html?"+"cnpj="+cpfCnpj+"&email="+email+"&senha="+senha;
		}
		else{
			alert("Informe um CPF ou CNPJ válido");
		}
	}
	
	function login() {
		var login = document.getElementById('username').value;
		var senha = document.getElementById('password').value;

		var dados = "&email=" + login;
		dados += "&senha=" + senha;

		$.ajax({
			type : "POST",
			url : "/webservices/LoginServlet",
			data : dados,
			dataType : "json",
			success : function(resultado) {

				console.log(resultado);
				window.location = "./index.html";
			}
		});
	}
	
	

});
