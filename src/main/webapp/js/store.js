$(document)
		.ready(
				function() {

					montarLista();

					function montarLista() {
						$
								.ajax({
									type : "POST",
									url : "/webservices/RecuperarTodosEstabelecimentosServlet",
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
												listaPromocao += "<p><a href=\"storeDetail.html?idEstabelecimento="+resultado[i]['idEstabelecimento']+"\"><img src=\"themes/images/stores/3.png\" alt=\"\"></a></p>";
												listaPromocao += "<a href=\"#\" class=\"title\"></a><br/>";
												listaPromocao += "<a href=\"#\" class=\"category\">"+resultado[i]['nomeFantasia']+"</a>";
												listaPromocao += "<p class=\"price\">"+resultado[i]['endereco']+"</p>";
												listaPromocao += "</div>";
												listaPromocao += "</li>";
												
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
