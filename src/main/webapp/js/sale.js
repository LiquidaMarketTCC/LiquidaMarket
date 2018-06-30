$(document)
		.ready(
				function() {

					montarLista();

					function montarLista() {
						$
								.ajax({
									type : "POST",
									url : "/webservices/RecuperarTodasPromocoesServlet",
									data : null,
									dataType : "json",
									success : function(resultado) {
										
										var htmlOutput = "";
										for (i = 0; i < resultado.length; i++) {
											console.log(resultado);
											// Começo
											var comeco = "<div class=\"row\">";
											comeco += "<div class=\"span12\">";								
											comeco += "<div id=\"myCarousel\" class=\"myCarousel carousel slide\">";
											comeco += "<div class=\"carousel-inner\">";
											comeco += "<div class=\"active item\">";
											comeco += "<ul class=\"thumbnails\">";	
															
											var listaPromocao = "";			
											for(x = 0 ; x < 4; x++){
												listaPromocao += "<li class=\"span3\">";
												listaPromocao += "<div class=\"product-box\">";
												listaPromocao += "<span class=\"sale_tag\"></span>";
												listaPromocao += "<p><a href=\"saleDetail.html?idPromocao="+resultado[i]['idPromocao']+"\"><img src=\"themes/images/sale/1.jpg\" alt=\"\"></a></p>";
												listaPromocao += "<a href=\"#\" class=\"title\">"+resultado[i]['nome']+"</a><br>";
												listaPromocao += "<a href=\"storeDetail.html?idEstabelecimento="+resultado[i]['estabelecimento']['idEstabelecimento']+"\" class=\"category\">"+resultado[i]['estabelecimento']['nomeFantasia']+"</a>";
												listaPromocao += "<p class=\"price\">"+resultado[i]['dataInicial']+" a "+resultado[i]['dataTermino']+"</p>";
												listaPromocao += "</div>";
												listaPromocao += "</li>";
												 var myDate = new Date(resultado[i]['dataInicial']);
												 
												 console.log(myDate);
												 console.log(myDate.toLocaleDateString("pt-BR"));
												i++;
												
												if(i >= resultado.length){
													break;
												}
											}
											
											// Fim
											var final = "</ul></div></div></div></div></div>";
											
											htmlOutput += comeco+listaPromocao+final;
										}
										$("#MAIN_CONTENT").html(htmlOutput);
									}
								});
					}

				});
