$(document)
		.ready(
				function() {

					// Eventos
					$('#Conteudo').on('click', '#BTN_Salvar', function() {
						salvar();
					});

					$('#Conteudo').on('click', '#BTN_Buscar', function() {
						buscar();
					});

					// Funções
					function buscar() {
						var input_CodBarra = document
								.getElementById('input_CodBarra').value;
						var dados = "&codigoBarras=" + input_CodBarra;

						$
								.ajax({
									type : "POST",
									url : "/webservices/RecuperarProdutoManufaturadoPorCodServlet",
									data : dados,
									dataType : "json",
									success : function(resultado) {
										console.log(resultado);

										if (resultado != null) {
											document
													.getElementById('input_Nome').value = resultado['nome'];
											document
													.getElementById('input_Descricao').value = resultado['descricao'];
											document
													.getElementById('input_Marca').value = resultado['marca']['nome'];
											$("#input_Categoria")
													.val(
															resultado['categoria']['nome']);
											document
													.getElementById('input_Nome').readOnly = true;
											document
													.getElementById('input_Descricao').readOnly = true;
											document
													.getElementById('input_Marca').readOnly = true;
											$('#input_Categoria').attr(
													'readonly', true);
											$('#input_Categoria').prop(
													'disabled', true);

											buscarComercializado();
										}
									}
								});
					}

					function buscarComercializado() {
						var input_CodBarra = document
								.getElementById('input_CodBarra').value;
						var dados = "&codBarras=" + input_CodBarra;

						$
								.ajax({
									type : "POST",
									url : "/webservices/RecuperarProdutoComercializadoCodBarrasServlet",
									data : dados,
									dataType : "json",
									success : function(resultado) {
										console.log(resultado);

										if (resultado != null) {
											document
													.getElementById('input_Valor').value = resultado['valor'];

											if (resultado['situacao']) {
												document
														.getElementById('input_SituacaoAtivo').checked = true;
											} else {
												document
														.getElementById('input_SituacaoInativo').checked = true;
											}
										}
									}
								});
					}

					function salvar() {
						var input_CodBarra = document
								.getElementById('input_CodBarra').value;
						var input_Nome = document.getElementById('input_Nome').value;
						var input_Descricao = document
								.getElementById('input_Descricao').value;
						var input_Marca = document
								.getElementById('input_Marca').value;
						var input_Categoria = document
								.getElementById('input_Categoria').value;
						var input_Valor = document
								.getElementById('input_Valor').value;
						var inputAtivo = 1;

						if (document.getElementById('input_SituacaoAtivo').checked) {
							inputAtivo = 1;
						} else if (document
								.getElementById('input_SituacaoInativo').checked) {
							inputAtivo = 0;
						}

						var dados = "&codigoBarras=" + input_CodBarra;
						dados += "&nome=" + input_Nome;
						dados += "&descricao=" + input_Descricao;
						dados += "&marca=" + input_Marca;
						dados += "&categoria=" + input_Categoria;
						dados += "&valor=" + input_Valor;
						dados += "&situacao=" + inputAtivo;

						$
								.ajax({
									type : "POST",
									url : "/webservices/SalvarProdutoManufaturadoServlet",
									data : dados,
									dataType : "json",
									success : function(resultado) {

										console.log(resultado);
										alert("Cadastro realizado com sucesso");
									}
								});
					}

					function salvarImagem(codigoBarras) {
						var data = new FormData();
						data.append('imagem', $('#input_Imagem')[0].files[0]);
						data.append('codigoBarras', codigoBarras);
						$.ajax({
							url : '/webservices/SalvarImagemServlet',
							data : data,
							processData : false,
							contentType : false,
							type : 'POST',
							success : function(data) {
								alert("Cadastro realizado com sucesso");
							}
						});
					}

				});
