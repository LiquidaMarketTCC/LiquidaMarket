$(document)
		.ready(
				function() {

						
					var idCategoria = 0;
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
										
	/* ------------------itens actives more visualized-------------------------------- */				
										htmlOutput += "<div class=\"row\">";
										htmlOutput += "<div class=\"span12\">";
										htmlOutput += "<h4 class=\"title\">";
										htmlOutput += "<span class=\"pull-left\"><span class=\"text\"><span class=\"line\">Mais <strong>Vistos</strong></span></span></span>";
										htmlOutput += "<span class=\"pull-right\">";
										htmlOutput += "<a class=\"left button\" href=\"#myCarousel\" data-slide=\"prev\"></a><a class=\"right button\" href=\"#myCarousel\" data-slide=\"next\"></a>";
										htmlOutput += "</span></h4>";
										htmlOutput += "<div id=\"myCarousel\" class=\"myCarousel carousel slide\">";
										htmlOutput += "<div class=\"carousel-inner\">";
												
										htmlOutput += "<div class=\"active item\">";
										htmlOutput += "<ul class=\"thumbnails\">";
										
										//primeiro for										
										for (i=0; i < resultado.length; i++){
											
											htmlOutput += "<li class=\"span3\">";
											htmlOutput += "<div class=\"product-box\">";
											htmlOutput += "<span class=\"sale_tag\"></span>";
											htmlOutput += "<a href=\"#\"><img alt=\"\" src=\"themes/images/products/4.jpg\"></a><br/>";
											htmlOutput += "<a href=\"#\" class=\"title\">"+resultado[i]['produtoManufaturado']['nome']+"</a><br/>";
											htmlOutput += "<a href=\"storeDetail.html?idEstabelecimento="+resultado[i]['estabelecimento']['idEstabelecimento']+"\" class=\"category\">"+resultado[i]['estabelecimento']['nomeFantasia']+"</a>";
											htmlOutput += "<p class=\"price\">"+resultado[i]['valor']+"</p>";
											htmlOutput += "</div>";
											htmlOutput += "</li>";
											
											if (i == 3){
												break;
											}
										}
										
										
										htmlOutput += "</ul></div>";
										
										if (resultado.length > 4){
											
											htmlOutput += "<div class=\"item\">";
											htmlOutput +="<ul class=\"thumbnails\">";
											
											//segundo for
											for (i=4; i < resultado.length; i++){
												htmlOutput += "<li class=\"span3\">";
												htmlOutput += "<div class=\"product-box\">";
												htmlOutput += "<span class=\"sale_tag\"></span>";
												htmlOutput += "<a href=\"#\"><img alt=\"\" src=\"themes/images/products/4.jpg\"></a><br/>";
												htmlOutput += "<a href=\"#\" class=\"title\">"+resultado[i]['produtoManufaturado']['nome']+"</a><br/>";
												htmlOutput += "<a href=\"storeDetail.html?idEstabelecimento="+resultado[i]['estabelecimento']['idEstabelecimento']+"\" class=\"category\">"+resultado[i]['estabelecimento']['nomeFantasia']+"</a>";
												htmlOutput += "<p class=\"price\">"+resultado[i]['valor']+"</p>";
												htmlOutput += "</div>";
												htmlOutput += "</li>";
												
												if (i == 7){
													break;
												}
											}
											
											htmlOutput += "</ul></div>";
										}
																				
										htmlOutput += "</div></div></div></div>";
										htmlOutput += "<br/>";
										
	/* ------------------itens actives last added-------------------------------- */
										
										htmlOutput += "<div class=\"row\">";
										htmlOutput += "<div class=\"span12\">";
										htmlOutput += "<h4 class=\"title\">";
										htmlOutput += "<span class=\"pull-left\"><span class=\"text\"><span class=\"line\">Últimos <strong>Cadastrados</strong></span></span></span>";
										htmlOutput += "<span class=\"pull-right\">";
										htmlOutput += "<a class=\"left button\" href=\"#myCarousel-2\" data-slide=\"prev\"></a><a class=\"right button\" href=\"#myCarousel-2\" data-slide=\"next\"></a>";
										htmlOutput += "</span></h4>";
										htmlOutput += "<div id=\"myCarousel-2\" class=\"myCarousel carousel slide\">";
										htmlOutput += "<div class=\"carousel-inner\">";
										
										
										htmlOutput += "<div class=\"active item\">";
										htmlOutput += "<ul class=\"thumbnails\">";
										
										for (i=(resultado.length-1); i >= 0; i--){
											
											htmlOutput += "<li class=\"span3\">";
											htmlOutput += "<div class=\"product-box\">";
											htmlOutput += "<span class=\"sale_tag\"></span>";
											htmlOutput += "<a href=\"#\"><img alt=\"\" src=\"themes/images/products/4.jpg\"></a><br/>";
											htmlOutput += "<a href=\"#\" class=\"title\">"+resultado[i]['produtoManufaturado']['nome']+"</a><br/>";
											htmlOutput += "<a href=\"storeDetail.html?idEstabelecimento="+resultado[i]['estabelecimento']['idEstabelecimento']+"\" class=\"category\">"+resultado[i]['estabelecimento']['nomeFantasia']+"</a>";
											htmlOutput += "<p class=\"price\">"+resultado[i]['valor']+"</p>";
											htmlOutput += "</div>";
											htmlOutput += "</li>";
											
											if (i == (resultado.length-4)){
												break;
											}
										}
										
										
										htmlOutput += "</ul></div>";
										
										if((resultado.length-4) >= 1){
											
											
											htmlOutput += "<div class=\"item\">";
											htmlOutput +="<ul class=\"thumbnails\">";
											
											//segundo for
											for(i=(resultado.length-5); i>=0; i--){
												htmlOutput += "<li class=\"span3\">";
												htmlOutput += "<div class=\"product-box\">";
												htmlOutput += "<span class=\"sale_tag\"></span>";
												htmlOutput += "<a href=\"#\"><img alt=\"\" src=\"themes/images/products/4.jpg\"></a><br/>";
												htmlOutput += "<a href=\"#\" class=\"title\">"+resultado[i]['produtoManufaturado']['nome']+"</a><br/>";
												htmlOutput += "<a href=\"storeDetail.html?idEstabelecimento="+resultado[i]['estabelecimento']['idEstabelecimento']+"\" class=\"category\">"+resultado[i]['estabelecimento']['nomeFantasia']+"</a>";
												htmlOutput += "<p class=\"price\">"+resultado[i]['valor']+"</p>";
												htmlOutput += "</div>";
												htmlOutput += "</li>";
												
												if (i == (resultado.length-8)){
													break;
												}
											}
											
											htmlOutput += "</ul></div>";
										}
										
										htmlOutput += "</div></div></div></div>";
										
										
										$("#MAIN_CONTENT").html(htmlOutput);
									}
								});
					}
					
					
				});
