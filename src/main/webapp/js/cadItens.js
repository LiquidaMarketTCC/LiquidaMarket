$(document)
		.ready(
				function() {

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

					var idPromocao = hash.idPromocao;
					var nomePromocao = hash.nomePromocao;
					document.getElementById('nome').value = nomePromocao;

					montarLista();

					// Eventos
					$('#Conteudo').on('click', '#BTN_Salvar', function() {
						console.log("Clicou em salvar");
						salvar();
					});

					// Funções

					function salvar() {

						var listaItens = $(".itensList");
						var listaValores = $(".itensValue");
						var listaPromocao = "&itens="; // ID,VALOR;
						for (i = 0; i < listaItens.length; i++) {
							if (listaItens[i].checked) {

								var id = (listaItens[i].id).replace("item", "");
								var valor = (listaValores[i].value).replace(
										".", "").replace(",", ".");
								listaPromocao += id + "," + valor + ";"
							}
						}
						listaPromocao += "&idPromocao=" + idPromocao;
						console.log(listaPromocao);

						$
								.ajax({
									type : "POST",
									url : "/webservices/AdicionarItemPromocaoServlet",
									data : listaPromocao,
									dataType : "json",
									success : function(resultado) {
										alert("Cadastro de Itens realizado com sucesso!");
									}
								});

					}

					function montarLista() {
						var dados = "idPromocao=" + idPromocao;

						$
								.ajax({
									type : "POST",
									url : "/webservices/RecuperarProdutosEstabelecimentoPromocaoServlet",
									data : dados,
									dataType : "json",
									success : function(resultado) {
										var criarLista = "";
										console.log(resultado);
										for (i = 0; i < resultado.length; i++) {
											var checked = '';
											var value = '';
											
											if( resultado[i]['promocao'] != null){
												checked = 'checked';
												value = resultado[i]['promocao']['valorPromocionalProduto'];
											}
											
											var tag1 = "<input type=\"checkbox\"" + " " + checked + " class=\"itensList\" id=\"item"
													+ resultado[i]['produto']['idProdutoComercializado'] 
													+ "\">";
											var nome = "   "
													+ resultado[i]['produto']['produtoManufaturado']['nome']
													+ "   ";
											var tag2 = "<input type=\"text\" class=\"itensValue\" value=\""+value+"\"  length=\"5\" onKeyUp=\"mascaraMoeda(this, event)\"><br>";
											criarLista += tag1 + nome + tag2;
										}
										$("#LIST_Itens").html(criarLista);
									}
								});
					}

				});
