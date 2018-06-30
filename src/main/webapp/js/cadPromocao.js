$(document)
		.ready(
				function() {
					var idPromocao = 0;
					try {
						var url = window.location.href;
						var parametrosDaUrl = url.split("?")[1];
						var hash = {}
						var parametro = parametrosDaUrl.split("=");
						var chave = parametro[0];
						var valor = parametro[1];
						hash[chave] = valor;
						idPromocao = hash.idPromocao;
						console.log(idPromocao);
					} catch (err) {
						console.log("teste");
						idPromocao = 0;
					}
					preencherPagina();

					// Eventos
					$('#Conteudo').on('click', '#BTN_Salvar', function() {
						console.log("Clicou em salvar");
						salvar();
					});

					// Funções
					function preencherPagina() {
						if (idPromocao > 0) {
							var dados = "idPromocao=" + idPromocao;
							$
									.ajax({
										type : "POST",
										url : "/webservices/RecuperarPromocaoServlet",
										data : dados,
										dataType : "json",
										success : function(resultado) {
											console.log(resultado);
											document.getElementById('nome').value = resultado['nome'];
											document
													.getElementById('dataInicial').value = resultado['dataInicial'];
											document
													.getElementById('dataTermino').value = resultado['dataTermino'];

											if (resultado['situacao']) {
												document
														.getElementById('situacaoAtiva').checked = true;
											} else {
												document
														.getElementById('situacaoInativa').checked = true;
											}

										}
									});
						}
					}

					function salvar() {
						var nome = document.getElementById('nome').value;
						var dataInicial = document
								.getElementById('dataInicial').value;
						var dataTermino = document
								.getElementById('dataTermino').value;
						var situacao;

						if (document.getElementById('situacaoAtiva').checked) {
							situacao = 1;
						} else if (document.getElementById('situacaoInativa').checked) {
							situacao = 0;
						}

						var dados = "&nome=" + nome;
						dados += "&dataInicial=" + dataInicial;
						dados += "&dataTermino=" + dataTermino;
						dados += "&situacao=" + situacao;
						dados += "&idPromocao=" + idPromocao;
						console.log(dados);
						$.ajax({
							type : "POST",
							url : "/webservices/SalvarPromocaoServlet",
							data : dados,
							dataType : "json",
							success : function(resultado) {

								console.log(resultado);
								window.location = "./cadItens.html?idPromocao="
										+ resultado['idPromocao']
										+ "&nomePromocao=" + resultado['nome'];
							}
						});
					}

				});
