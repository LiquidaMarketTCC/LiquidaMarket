$(document)
		.ready(
				function() {
					var senha = '';
					// Eventos
					$('#Conteudo').on('click', '#BTN_Salvar', function() {
						console.log("Clicou em salvar");
						salvar();
					});

					$(window).on('load', function() {
						try {
							recuperarDados();
						} catch (err) {

						}
					});
					preencherPagina();
					// Funções
					function preencherPagina() {
						/*
						 * cnpj email razaoSocial nomeFantasia endereco telefone
						 * horaIncial horaFinal dinheiro credito debito cheque
						 * refeicao alimentacao
						 */

						$
								.ajax({
									type : "POST",
									url : "/webservices/VerificarLoginServlet",
									data : null,
									dataType : "json",
									success : function(resultado) {
										console.log(resultado);

										if (resultado['idConsumidor'] != null) {

										} else if (resultado['idEstabelecimento'] != null) {
											var formas = resultado['formasPagamento']
													.split(',');
											console.log(formas);

											document.getElementById('cnpj').value = resultado['cnpj'];
											document.getElementById('email').value = resultado['usuario']['login'];
											document
													.getElementById('razaoSocial').value = resultado['razaoSocial'];
											document
													.getElementById('nomeFantasia').value = resultado['nomeFantasia'];
											document.getElementById('endereco').value = resultado['endereco'];
											document.getElementById('telefone').value = resultado['telefone'];
											document
													.getElementById('horaInicial').value = resultado['horaFuncionamentoInicial'];
											document
													.getElementById('horaFinal').value = resultado['horaFuncionamentoFinal'];

											for (var i = 0; i < formas.length; i++) {
												if (formas[i].trim() == "Dinheiro") {
													document
															.getElementById('dinheiro').checked = true;
												} else if (formas[i].trim() == "Cartão de Crédito") {
													document
															.getElementById('credito').checked = true;
												} else if (formas[i].trim() == "Cartão de Débito") {
													document
															.getElementById('debito').checked = true;
												} else if (formas[i].trim() == "Cheque") {
													document
															.getElementById('cheque').checked = true;
												} else if (formas[i].trim() == "Vale Refeição") {
													document
															.getElementById('refeicao').checked = true;
												} else if (formas[i].trim() == "Vale Alimentação") {
													document
															.getElementById('alimentacao').checked = true;

												}
											}

										}

									}
								});
					}

					function recuperarDados() {
						var url = window.location.href;
						var parametrosDaUrl = url.split("?")[1];
						var listaDeParametros = parametrosDaUrl.split("&");

						var hash = {}
						for (var i = 0; i < listaDeParametros.length; i++) {
							var parametro = listaDeParametros[i].split("=");
							var chave = parametro[0];
							var valor = parametro[1];
							hash[chave] = valor;
						}

						var cpfCad = hash.cnpj;
						var emailCad = hash.email;
						senha = hash.senha;

						document.getElementById('cnpj').value = cpfCad;
						document.getElementById('email').value = emailCad;
					}

					function salvar() {
						var cnpj = document.getElementById('cnpj').value;
						var email = document.getElementById('email').value;
						var razaoSocial = document
								.getElementById('razaoSocial').value;
						var nomeFantasia = document
								.getElementById('nomeFantasia').value;
						var endereco = document.getElementById('endereco').value;
						var telefone = document.getElementById('telefone').value;
						var horaInicial = document.getElementById('horaInicial').value;
						var horaFinal = document.getElementById('horaFinal').value;
						var formasPgto = "";

						if (document.getElementById('dinheiro').checked) {
							formasPgto += document.getElementById('dinheiro').value;
						}
						if (document.getElementById('credito').checked) {
							formasPgto += ("," + document
									.getElementById('credito').value);
						}
						if (document.getElementById('debito').checked) {
							formasPgto += ("," + document
									.getElementById('debito').value);
						}
						if (document.getElementById('cheque').checked) {
							formasPgto += ","
									+ document.getElementById('cheque').value;
						}
						if (document.getElementById('refeicao').checked) {
							formasPgto += ("," + document
									.getElementById('refeicao').value);
						}
						if (document.getElementById('alimentacao').checked) {
							formasPgto += ("," + document
									.getElementById('alimentacao').value);
						}

						var dados = "&cnpj=" + cnpj;
						dados += "&razaoSocial=" + razaoSocial;
						dados += "&nomeFantasia=" + nomeFantasia;
						dados += "&endereco=" + endereco;
						dados += "&telefone=" + telefone;
						dados += "&email=" + email;
						dados += "&horaInicial=" + horaInicial;
						dados += "&horaFinal=" + horaFinal;
						dados += "&formasPgto=" + formasPgto;
						dados += "&senha=" + senha;

						$.ajax({
							type : "POST",
							url : "/webservices/SalvarEstabelecimentoServlet",
							data : dados,
							dataType : "json",
							success : function(resultado) {

								console.log(resultado);
								alert("Cadastro realizado com sucesso");
							}
						});
					}

				});
