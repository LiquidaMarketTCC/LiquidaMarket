$(document)
		.ready(
				function() {

						
					var idCategoria = 0;
					try {
						var url = window.location.href;
						var parametrosDaUrl = url.split("?")[1];
						var hash = {}
						var parametro = parametrosDaUrl.split("=");
						var chave = parametro[0];
						var valor = parametro[1];
						hash[chave] = valor;
						idCategoria = hash.idCategoria;
					} catch (err) {
						idCategoria = 0;
					}
					
					var dados = "&idCategoria=" + idCategoria;
					
					montarLista();
					
					function montarLista() {
						$
								.ajax({
									type : "POST",
									url : "/webservices/RecuperarTodosProdutosComercializadosServlet",
									data : dados,
									dataType : "json",
									success : function(resultado) {
										
										var htmlOutput = "";
										for (i = 0; i < resultado.length; i++) {
											console.log(resultado);
											// Começo
											var comeco = "<div class=\"row\">";						
											comeco += "<div class=\"span9\">";							
											comeco += "<ul class=\"thumbnails listing-products\">";
															
											var listaProdutos = "";			
											for(x = 0 ; x < 3; x++){
												listaProdutos += "<li class=\"span3\">";
												listaProdutos += "<div class=\"product-box\">";
												listaProdutos += "<span class=\"sale_tag\"></span>";
												listaProdutos += "<a href=\"#\"><img alt=\"\" src=\"themes/images/products/4.jpg\"></a><br/>";
												listaProdutos += "<a href=\"#\" class=\"title\">"+resultado[i]['produtoManufaturado']['nome']+"</a><br/>";
												listaProdutos += "<a href=\"storeDetail.html?idEstabelecimento="+resultado[i]['estabelecimento']['idEstabelecimento']+"\" class=\"category\">"+resultado[i]['estabelecimento']['nomeFantasia']+"</a>";
												listaProdutos += "<p class=\"price\">"+resultado[i]['valor']+"</p>";
												listaProdutos += "</div>";
												listaProdutos += "</li>";
												i++;
												
												if(i >= resultado.length){
													break;
												}
											}
											
											// Fim
											
											var final = "</ul><hr></div>";
											console.log("valor de i"+i);
											if (i <= 3){
												final += "<div class=\"span3 col\">";
												final += "<div class=\"block\">";
												final += "<ul class=\"nav nav-list\">";
												final += "<li class=\"nav-header\">CATEGORIAS</li>";
												final += "<li><a href=\"products.html?idCategoria=1\">Alimentos</a></li>";
												final += "<li><a href=\"products.html?idCategoria=2\">Bebidas</a></li>";
												final += "<li><a href=\"products.html?idCategoria=3\">Carnes</a></li>";
												final += "<li><a href=\"products.html?idCategoria=4\">Congelados e Resfriados</a></li>";
												final += "<li><a href=\"products.html?idCategoria=5\">Frutas, Verduras e Legumes</a></li>";
												final += "<li><a href=\"products.html?idCategoria=6\">Higiene</a></li>";
												final += "<li><a href=\"products.html?idCategoria=7\">Limpeza</a></li>";
												final += "<li><a href=\"products.html?idCategoria=8\">Padaria</a></li>";
												final += "<li><a href=\"products.html?idCategoria=9\">Outros</a></li>";
												final += "</ul></div></div>";
											}
											
											final += "</div>";
											htmlOutput += comeco+listaProdutos+final;
										}
										$("#MAIN_CONTENT").html(htmlOutput);
									}
								});
					}
					
					
				});
