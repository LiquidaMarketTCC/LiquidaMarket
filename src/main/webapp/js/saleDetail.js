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
					} catch (err) {
						idPromocao = 0;
					}

					var dados = "&idPromocao=" + idPromocao;
					montarLista();

					var idUsuarioEstabelecimento = 0;
					var idEstabelecimento = 0;
					function mostrarBotao() {
						try {
							if (idUsuarioEstabelecimento > 0) {
								if (idEstabelecimento == idUsuarioEstabelecimento) {
									var botao = "<a href=\"cadPromocao.html?idPromocao="+idPromocao+"\" ><input tabindex=\"3\" id=\"BTN_Editar\" class=\"btn btn-inverse large\" type=\"button\" value=\"Editar\"> </a>";
									console.log(botao);
									$("#Conteudo_Produto").append(botao);

									
								}
							}
						} catch (err) {

						}
					}

					function verificarUsuarioEstabelecimento() {

						var dados = "&login=login";

						$
								.ajax({
									type : "POST",
									url : "/webservices/VerificarLoginServlet",
									data : dados,
									dataType : "json",
									success : function(resultado) {
										console.log(resultado);

										if (resultado['idEstabelecimento'] != null) {
											idUsuarioEstabelecimento = resultado['idEstabelecimento'];
										}
										mostrarBotao();

									}
								});
					}

					function montarLista() {
						$
								.ajax({
									type : "POST",
									url : "/webservices/RecuperarTodosItensPromocaoServlet",
									data : dados,
									dataType : "json",
									success : function(resultado) {

										var htmlOutput = "<div class=\"row\">";
										htmlOutput += "<div class=\"span9\">";
										htmlOutput += "<div class=\"row\">";
										htmlOutput += "<div class=\"span4\">";
										htmlOutput += "<a href=\"themes/images/sale/1.jpg\" class=\"thumbnail\" data-fancybox-group=\"group1\" title=\"Description 1\"><img alt=\"\" src=\"themes/images/sale/1.jpg\"></a>";
										htmlOutput += "</div>";
										htmlOutput += "<div id=\"Conteudo_Produto\" class=\"span5\">";
										htmlOutput += "<address>";
										htmlOutput += "<strong>Promoção:</strong><span> "
												+ resultado[0]['promocao']['nome']
												+ "</span><br>";
										htmlOutput += "<strong>Estabelecimento:</strong> <span> "
												+ resultado[0]['promocao']['estabelecimento']['nomeFantasia']
												+ "</span><br>";
										htmlOutput += "<strong>Data Inicial:</strong> <span> "
												+ resultado[0]['promocao']['dataInicial']
												+ "</span><br>";
										htmlOutput += "<strong>Data Término: </strong> <span> "
												+ resultado[0]['promocao']['dataTermino']
												+ "</span><br>";
										htmlOutput += "</address><br/></div></div>";
										htmlOutput += "<div class=\"row\">";
										htmlOutput += "<div class=\"span9\">";
										htmlOutput += "<ul class=\"nav nav-tabs\" id=\"myTab\">";
										htmlOutput += "<li class=\"active\"><a href=\"#\">Itens da Promoção</a></li>";
										htmlOutput += "</ul>";
										htmlOutput += "<div class=\"tab-content\">";
										htmlOutput += "<div class=\"tab-pane active\" id=\"home\">";

										for (i = 0; i < resultado.length; i++) {
											htmlOutput += "<label class=\"checkbox\"><strong> "
													+ resultado[i]['produtoComercializado']['produtoManufaturado']['nome']
													+ " </strong> R$"
													+ resultado[i]['valorPromocionalProduto']
													+ " </label>";
										}

										htmlOutput += "</div></div></div></div></div></div>";

										$("#MAIN_CONTENT").html(htmlOutput);

										idEstabelecimento = resultado[0]['promocao']['estabelecimento']['idEstabelecimento'];
										verificarUsuarioEstabelecimento();
									}
								});
					}

				});
