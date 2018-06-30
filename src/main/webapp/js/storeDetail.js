$(document)
		.ready(
				function() {

					var idEstabelecimento = 0;
					try {
						var url = window.location.href;
						var parametrosDaUrl = url.split("?")[1];
						var hash = {}
						var parametro = parametrosDaUrl.split("=");
						var chave = parametro[0];
						var valor = parametro[1];
						hash[chave] = valor;
						idEstabelecimento = hash.idEstabelecimento;
					} catch (err) {
						idEstabelecimento = 0;
					}
					
					var dados = "&idEstabelecimento=" + idEstabelecimento;
					apresentarDetalhes();

					function apresentarDetalhes() {
						$
								.ajax({
									type : "POST",
									url : "/webservices/RecuperarEstabelecimentoServlet",
									data : dados,
									dataType : "json",
									success : function(resultado) {
										
										var htmlOutput = "<div class=\"row\">";
										htmlOutput += "<div class=\"span9\">";
										htmlOutput += "<div class=\"row\">";
										htmlOutput += "<div class=\"span4\">";
										htmlOutput += "<a href=\"themes/images/stores/1.png\" class=\"thumbnail\" data-fancybox-group=\"group1\" title=\"Description 1\"><img alt=\"\" src=\"themes/images/stores/1.png\"></a>";
										htmlOutput += "</div>";
										htmlOutput += "<div class=\"span5\">";
										htmlOutput += "<address>";
										htmlOutput += "<strong>Nome:</strong><span> "+resultado['nomeFantasia']+"</span><br>";
										htmlOutput += "<strong>Endereço:</strong> <span> "+resultado['endereco']+"</span><br>";
										htmlOutput += "<strong>Telefone:</strong> <span> "+resultado['telefone']+"</span><br>";
										console.log(resultado);
										htmlOutput += "<strong>Horário de Funcionamento: </strong> <span>Das "+resultado['horaFuncionamentoInicial']+" às "+resultado['horaFuncionamentoFinal']+"</span><br>";
										htmlOutput += "<strong>Formas de Pagamento Aceitas:</strong> <span> "+resultado['formasPagamento']+"</span><br>";
										htmlOutput += "</address>";
										htmlOutput += "<br/><h4><strong></strong></h4>";
										htmlOutput += "</div></div></div></div>";
										
										$("#MAIN_CONTENT").html(htmlOutput);
									}
								});
					}

				});
