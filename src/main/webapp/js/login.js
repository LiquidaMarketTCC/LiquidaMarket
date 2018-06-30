$(document).ready(function() {
	var idUsuario = 0;
	var consumidor = true;

	verificarUsuario();
	
	$('#Conteudo').on('click', '#BTN_Connect', function() {
		if(idUsuario > 0){
			logout();
		}
	});
	

	function logout() 
	{

		var dados = "&login=login";

		$.ajax({
			type : "POST",
			url : "/webservices/LogoutServlet",
			data : dados,
			dataType : "json",
			success : function(resultado) {
				idUsuario = 0;
				consumidor = true;
				alert("Sessão encerrada!");
				window.location = "./index.html";
			}
		});
	}
	
	function verificarUsuario() {

		var dados = "&login=login";

		$.ajax({
			type : "POST",
			url : "/webservices/VerificarLoginServlet",
			data : dados,
			dataType : "json",
			success : function(resultado) {
				console.log(resultado);

				if (resultado['idConsumidor'] != null) {
					idUsuario = resultado['idConsumidor'];
					consumidor = true;
				} else if (resultado['idEstabelecimento'] != null) {
					idUsuario = resultado['idEstabelecimento'];
					consumidor = false;
				}
				console.log("IdUsuario : " + idUsuario);
				console.log("Consumidor : " + consumidor);
				atualizarBarrinhaSuperior();
			}
		});
	}

	function atualizarBarrinhaSuperior() {
		if (idUsuario > 0) {
			console.log("Monta barrinha");
			$("#BTN_Connect").text("Sair");
			$("#BTN_Connect").attr("href", "#");
		}
		
		if (consumidor) {
			$("#User_Bar").prepend("<li><a id=\"BTN_MyAccount\" href=\"cadConsumidor.html\">Minha Conta</a></li>");
		} else {
			$("#User_Bar").prepend("<li><a id=\"BTN_MyAccount\" href=\"cadEstabelecimento.html\">Minha Conta</a></li>");
			$("#User_Bar").prepend("<li><a id=\"BTN_AddProduto\" href=\"cadProduto.html\">Cadastrar Produto</a></li>");
			$("#User_Bar").prepend("<li><a id=\"BTN_AddPromocao\" href=\"cadPromocao.html\">Cadastrar Promoção</a></li>");
		}
	}

});
