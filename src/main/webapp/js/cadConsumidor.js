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

											document.getElementById('cpf').value = resultado['cpf'];
											document.getElementById('email').value = resultado['usuario']['login'];
											document.getElementById('nome').value = resultado['nome'];
											document.getElementById('dataNasc').value = resultado['dataNasc'];
											document.getElementById('endereco').value = resultado['endereco'];
											document.getElementById('telefone').value = resultado['telefone'];

											if (resultado['sexo']) {
												document
														.getElementById('masculino').checked = true;
											} else {
												document
														.getElementById('feminino').checked = true;
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

						var cpfCad = hash.cpf;
						var emailCad = hash.email;
						senha = hash.senha;

						document.getElementById('cpf').value = cpfCad;
						document.getElementById('email').value = emailCad;
					}

					function salvar() {
						var cpf = document.getElementById('cpf').value;
						var email = document.getElementById('email').value;
						var nome = document.getElementById('nome').value;
						var sexo;
						var dataNasc = document.getElementById('dataNasc').value;
						var endereco = document.getElementById('endereco').value;
						var telefone = document.getElementById('telefone').value;

						if (document.getElementById('feminino').checked) {
							sexo = 1;
						} else if (document.getElementById('masculino').checked) {
							sexo = 0;
						}

						var dados = "&cpf=" + cpf;
						dados += "&nome=" + nome;
						dados += "&sexo=" + sexo;
						dados += "&dataNasc=" + dataNasc;
						dados += "&endereco=" + endereco;
						dados += "&telefone=" + telefone;
						dados += "&email=" + email;
						dados += "&senha=" + senha;

						$.ajax({
							type : "POST",
							url : "/webservices/SalvarConsumidorServlet",
							data : dados,
							dataType : "json",
							success : function(resultado) {

								console.log(resultado);
								alert("Cadastro realizado com sucesso");
							}
						});
					}

				});
