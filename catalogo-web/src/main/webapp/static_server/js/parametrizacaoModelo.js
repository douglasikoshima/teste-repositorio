var $jq = jQuery.noConflict();

function popularFabricantePesquisa() {
	var tipoProduto = new Object();
	tipoProduto.idTipoProduto = $jq('#tipoProdutoPesquisa').val();
	if (tipoProduto.idTipoProduto == 10) { // ACESSORIO
		$jq('#tecnologiaPesquisa').val('DEFAULT');
		$jq('#tecnologiaPesquisaDiv').hide('fast');
	} else {
		$jq('#tecnologiaPesquisaDiv').show('fast');
	}
	if (tipoProduto.idTipoProduto != 'DEFAULT') {
		$jq.getJSON('getFabricante.do', tipoProduto).done(function(data) {
			$jq('#fabricantePesquisa').find('option:not([value="DEFAULT"])').remove();	
			$jq.each(sortByKey(data, 'nome'), function(index, fabricante) {
				$jq('#fabricantePesquisa').append('<option value="' + fabricante.idFabricante + '" >' + fabricante.nome + '</option>');
			});
		});
	} else {
		limparSelectFabricante();
	}
}

function popularModeloPesquisa() {
	var modelo = new Object();
	modelo.idTipoProduto = $jq('#tipoProdutoPesquisa').val();
	if (modelo.idTipoProduto != 'DEFAULT') {
		modelo.idFabricante = $jq('#fabricantePesquisa').val();
		if (modelo.idFabricante != 'DEFAULT') {
			if ($jq('#tecnologiaPesquisa').val()!= 'DEFAULT') {
				modelo.idTecnologia = $jq('#tecnologiaPesquisa').val();
			}
			$jq.getJSON('getModelo.do', modelo).done(function(data) {
				$jq('#modeloPesquisa').find('option:not([value="DEFAULT"])').remove();	
				$jq.each(sortByKey(data, 'nome'), function(index, modelo) {
					$jq('#modeloPesquisa').append('<option value="' + modelo.id + '" >' + modelo.nome + '</option>');
				});
			});
		} else {
			limparSelectModelo();
		}
	} else {
		limparSelectFabricante();
	}
}

function pesquisarModelos() {
	validarCampos($jq("#pesquisa_simplificada"));
	if (staticMaps.camposValidado) {
		
		if (modelo) {
			fecharCadastroModelo();
		}
		
		$jq('#tResultado').html('');
		var thead = '<thead><tr>';
		thead += '<th style="text-align: left;">' + mensagens.NomeModelo + '</th>';
		thead += '<th style="text-align: left;">' + mensagens.Fabricante + '</th>';
		thead += '<th>' + mensagens.FimDeVida + '</th>';
		thead += '<th>' + mensagens.Disp + '</th>';
		thead += '<th>' + mensagens.Caracteristicas + '</th>';
		thead += '<th>' + mensagens.Multimidia + '</th>';
		thead += '<th>' + mensagens.Copiar + '</th>';
		thead += '<th>' + mensagens.Excluir + '</th>';
		thead += '<th></th>';
		thead += '</tr></thead>';
		$jq('#tResultado').append(thead);
		$jq('#tResultado').append('<tbody></tbody>');
		
		$jq('#resultado').show();
		
		var filtros = new Object();
		filtros.idTipoProduto = $jq('#tipoProdutoPesquisa').val() != 'DEFAULT' ? parseInt($jq('#tipoProdutoPesquisa').val()) : 0;
		filtros.idFabricante = $jq('#fabricantePesquisa').val() != 'DEFAULT' ? parseInt($jq('#fabricantePesquisa').val()) : 0;
		filtros.idModelo = $jq('#modeloPesquisa').val() != 'DEFAULT' ? parseInt($jq('#modeloPesquisa').val()) : 0;
		filtros.idTecnologia = $jq('#tecnologiaPesquisa').val() != 'DEFAULT' && $jq('#tecnologiaPesquisa:visible').val() ? parseInt($jq('#tecnologiaPesquisa').val()) : 0;
		
		filtros.idsCaracteristicas = new Array();
		$jq.each(caracteristicasPesquisaMap, function(i1, caracteristica) {
			if (caracteristica.selecionado) {
				var valores = new Array();
				$jq.each(caracteristica.valoresMap, function(i2, valor) {
					if (valor.selecionado) {
						valores.push(valor.id);
					}
				});
				filtros.idsCaracteristicas.push({"id": caracteristica.id, "valores": valores});
			}
		});
		
		$jq('#tResultado').dataTable({
		    "sAjaxSource": "pesquisarModelos.do",
		    "sDom": '<"top">rt<"bottom"flpi><"clear">',
			"bServerSide": true,
			"bDestroy": true,
			"bSort": false,
			"bLengthChange": true,
			"bFilter": false,
			"bProcessing": true,
			"bPaginate": true,
			"sPaginationType": "full_numbers",
		    "iDisplayLength": 20,
		    "showInfo": false,
	    	"oLanguage": dataTableLanguage,
			"fnInfoCallback": function(oSettings, iStart, iEnd, iMax, iTotal, sPre) {
				if (!iMax) {
					$jq('#tResultado_paginate').remove();
					return '';
				}
				$jq('#tResultado_paginate').find('.paginate_button_disabled').each(function() {
					$jq(this).attr('class', '');
	                $jq(this).attr('style', '');
				});
				return mensagens.Mostrandode + "&nbsp;" + iStart + "&nbsp;" + mensagens.ate + "&nbsp;" + iEnd + "&nbsp;" + mensagens.de + "&nbsp;" + iMax + "&nbsp;" + mensagens.Registros + "&nbsp;";
			},
			"fnServerParams": function(aoData) {
				aoData.push({"name": "form", "value": JSON.stringify(filtros)});
	       	}, 
	       	"fnDrawCallback" : function(oSettings) {
	   			var legenda = $jq('#tResultado_info');
				$jq('.paging_full_numbers').attr('class', '');
				$jq(legenda).attr('class', '').attr('style', 'float:left');
				$jq('#tResultado_paginate').prepend($jq(legenda));
				$jq('#tResultado_paginate').find('.paginate_button:not(".paginate_button_disabled")').each(function() {
	   				$jq(this).attr('style', 'color: #0066CF; cursor: pointer;');
				});
	   		},
	   		"aoColumnDefs": 
	       	[
	       		{
	       			"mRender": function(data, type, row) {
	                   return '<div onclick="alterarModelo(' + row[7] + ');" style="color: #3D3D3D; text-align: left; text-decoration: underline; cursor: pointer;">' + data + '</div>';
	               	},
	               	"aTargets": [ 0 ]
	       		},
	       		{
	       			"sClass": "left truncate",
	       			"aTargets": [ 1 ]
	       		},
				{
	           		"mRender": function(data, type, row) {
	                   return data ? '<img alt="' + mensagens.Sim + '" src="/catalogo/static_server/img/bullets/icon-available.png"/>' : '<img alt="' + mensagens.Nao + '" src="/catalogo/static_server/img/bullets/icon-unavailable.png"/>';
	               	},
	               	"aTargets": [ 2,3,4,5 ]
	        	},
				{
	           		"mRender": function(data, type, row) {
	                   return '<img alt="' + mensagens.Copiar + '" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/Copy16.gif" onclick="copiarModelo(' + data + ')" style="cursor: pointer;"/>';
	               	},
	               	"aTargets": [ 6 ]
	        	},
				{
	           		"mRender": function(data, type, row) {
	                   return '<img alt="' + mensagens.Excluir + '" src="/catalogo/static_server/img/botoes/bt-excluir.gif" onclick="excluirModelo(' + data + ')" style="cursor: pointer;"/>';
	               	},
	               	"aTargets": [ 7 ]
	        	},
				{
	           		"mRender": function(data, type, row) {
	                   return '<img alt="' + mensagens.DetalheModelo + '" src="/catalogo/static_server/img/botoes/bt-detalhes-ico.gif" onclick="obterDetalhesModelo(' + data + ');" style="cursor: pointer;"/>';
	               	},
	               	"aTargets": [ 8 ]
	        	}
			]
		});
	}

}

function limparSelect(selectId) {
	var select = $jq('#' + selectId + '');
	select.val('DEFAULT');
	select.find('option:not([value="DEFAULT"])').remove();
}

function limparSelectFabricante() {
	limparSelect("fabricantePesquisa");
	limparSelectModelo();
}

function limparSelectModelo() {
	limparSelect("modeloPesquisa");
}

function limparTela(){
	//cancelarCriacaoModelo();
	
	$jq('#tipoProdutoPesquisa').focus();
	$jq('#tipoProdutoPesquisa').val('DEFAULT');
	
	limparSelectFabricante();
	
	$jq('#tecnologiaPesquisa').val('DEFAULT');
	$jq('#tecnologiaPesquisaDiv').show('fast');
	
	caracteristicasPesquisaMap = new Object();
	$jq('#caracteristicasSelecionadasPesquisaDiv').html('');
	
	$jq('#resultadoTabelaPesquisa').children().remove();
	$jq('#resultadoTabelaPesquisa').append('<table id="tResultado" class="tabela-padrao table_body"></table>');
	$jq('#resultado').hide('fast');
}

function obterDetalhesModelo(idModelo) {
	var params = new Object();
	params.idModelo = idModelo;
	$jq.getJSON('obterDetalhesModelo.do', params).done(function(data) {
		if (data.erro) {
			alert(data.erro);
		} else {
			// tituloPopupDetalhesModelo
			var html = data.grupoProduto.fabricante.nmFabricante + ' ' + data.grupoProduto.nmGrupoProduto;
			if (data.grupoProduto.tecnologiaList) {
				$jq.each(sortByKey(data.grupoProduto.tecnologiaList, 'nmTecnologia'), function(i1, tecnologia) {
					html += ' - ' + tecnologia.nmTecnologia;
					if (tecnologia.tipoFrequenciaList) {
						$jq.each(sortByKey(tecnologia.tipoFrequenciaList, 'nmTipoFrequencia'), function(i2, tipoFrequencia) {
							html += ' : ' + tipoFrequencia.nmTipoFrequencia;
							if (tipoFrequencia.vlFrequenciaList) {
								$jq.each(sortByKey(tipoFrequencia.vlFrequenciaList, 'vlFrequencia'), function(i3, vlFrequencia) {
									html += ((i3 > 0) ? '/' : ' ') + vlFrequencia.vlFrequencia;
								});
							}
						});
					}
				});
			}
			$jq('#tituloPopupDetalhesModelo').html(html);
			
			// multimidiasPopupDetalhesModelo
			html = '<br>';
			if (data.grupoProduto.multimidiaList) {
				var multimidiaList = new Array();
				$jq.each(data.grupoProduto.multimidiaList, function(i, multimidia) {
					if (multimidia.tipoMultimidia && multimidia.tipoMultimidia.idTipoMultimidia == 1) { // Imagem
						multimidiaList.push(multimidia);
					}
				});
				$jq.each(sortByKey(multimidiaList, 'nomeMultimidia'), function(i, multimidia) {
					html += '<div id="multimidia_' + i + '"' + ((i > 0) ? ' style="display: none;"' : '') + ' align="center">';
					html += '<div class="foto-moldura"><img height="165px" width="110px" src="/' + data.caminho_link_imagens_modelo + '/' + multimidia.nomeMultimidia + '"/></div>';
					html += '<div align="center" style="margin-top:5px;">';
					if (i > 0) {
						html += '<a href="#" onclick="$(\'multimidia_' + i + '\').hide();$(\'multimidia_' + (i - 1) + '\').show();return false;" title="' + mensagens.FotoAnterior + '"><img src="/catalogo/static_server/img/botoes/bt-fotos-anterior.gif" alt="' + mensagens.FotoAnterior + '" title="' + mensagens.FotoAnterior + '"/></a>';
					} else {
						html += '<a href="#" style="visibility:hidden;" onclick="" title="' + mensagens.FotoAnterior + '"><img src="/catalogo/static_server/img/botoes/bt-fotos-anterior.gif" alt="' + mensagens.FotoAnterior + '" title="' + mensagens.FotoAnterior + '"/></a>';
					}
					html += '<img src="/catalogo/static_server/img/botoes/bt-fotos-middle.gif"/>';
					if (multimidiaList.length > i + 1) {
						html += '<a href="#" onclick="$(\'multimidia_' + i + '\').hide();$(\'multimidia_' + (i + 1) + '\').show();return false;" title="' + mensagens.ProximaFoto + '"><img src="/catalogo/static_server/img/botoes/bt-fotos-proxima.gif" alt="' + mensagens.ProximaFoto + '" title="' + mensagens.ProximaFoto + '"/></a>';
					} else {
						html += '<a href="#" style="visibility:hidden;" onclick="" title="' + mensagens.ProximaFoto + '"><img src="/catalogo/static_server/img/botoes/bt-fotos-proxima.gif" alt="' + mensagens.ProximaFoto + '" title="' + mensagens.ProximaFoto + '"/></a>';
					}
					html += '</div></div>';
				});
			}
			if (html == '<br>') {
				html += '<div class="foto-moldura"><img height="110px" width="110px" src="/catalogo/static_server/img/transparent.gif"/></div>';
			}
			$jq('#multimidiasPopupDetalhesModelo').html(html);
			
			// caracteristicasPopupDetalhesModelo
			html = '<ul class="dados-list" style="margin-left:0px">';
			if (data.grupoProduto.caracteristicaList) {
				$jq.each(sortByKey(data.grupoProduto.caracteristicaList, 'nmCaracteristica'), function(i1, caracteristica) {
					html += '<li>' + caracteristica.nmCaracteristica;
					if (caracteristica.valorCaracteristicaList) {
						$jq.each(sortByKey(caracteristica.valorCaracteristicaList, 'valor'), function(i2, valorCaracteristica) {
							html += ' / ' + valorCaracteristica.valor;
						});
					}
					html += '</li>';
				});
			}
			html += '</ul>';
			$jq('#caracteristicasPopupDetalhesModelo').html(html);
			
			// produtosPopupDetalhesModelo
			html = '';
			if (data.grupoProduto.produtoList) {
				$jq.each(sortByKey(data.grupoProduto.produtoList, 'sistemaProduto.cdCodigo'), function(i, produto) {
					html += '<div class="dados-list"><li>' + ((produto.sistemaProduto) ? produto.sistemaProduto.cdCodigo : '') + '&nbsp;(' + ((produto.gama) ? produto.gama.nmGama : '') + ')</li></div>';
				});
			}
			$jq('#produtosPopupDetalhesModelo').html(html);
			
			abrirPopupDetalhesModelo();
		}
	});
}

function abrirPopupDetalhesModelo() {
	var popupParams = {
		create: function(event, ui) {
			createPopupTitle('popupDetalhesModelo', mensagens.DetalhesDoModelo, null);
      	},
      	modal: true,
	    resizable: false,
	    width: 800,
	    height: 100
	};
	$jq('#popupDetalhesModelo').dialog(popupParams);
}

function excluirModelo(idGrupoProduto) {
	var modelo = new Object();
	modelo.idGrupoProduto = idGrupoProduto;
	$jq.getJSON('obterProdutosModelo.do', modelo).done(function(data) {
		if (data.erro) {
			alert(data.erro);
		} else {
			if (data.produtoList) {
				abrirPopupConfirmacaoExclusao(idGrupoProduto, data.produtoList);
			} else {
				submeterExclusaoModelo(idGrupoProduto);
			}
		}
	});
}

function submeterExclusaoModelo(idGrupoProduto) {
	var modelo = new Object();
	modelo.idGrupoProduto = idGrupoProduto;
	$jq.getJSON('excluirModelo.do', modelo).done(function(data) {
		if (data.erro) {
			alert(data.erro);
		} else {
			pesquisarModelos();
			alert($jq('#mensagemSucessoExclusao').text());
		}
	});
}

function abrirPopupConfirmacaoExclusao(idGrupoProduto, produtoList) {
	abrirPopupConfirmacao(
		produtoList,
		mensagens.Exclusaoprodutos,
		mensagens.Exclusaoconfirmacao,
		function() {
			confirmarExclusaoModelo(idGrupoProduto);
		},
		cancelarExclusaoModelo);
}

function confirmarExclusaoModelo(idGrupoProduto) {
	$jq('#popupConfirmacao').dialog('close');
	submeterExclusaoModelo(idGrupoProduto);
}

function cancelarExclusaoModelo() {
	$jq('#popupConfirmacao').dialog('close');
}

function sortByKey(array, key) {
	return array.sort(function(a, b) {
		var keys = key.split(".");
		var x = (keys.length == 2) ? a[keys[0]][keys[1]] : a[key];
		var y = (keys.length == 2) ? b[keys[0]][keys[1]] : b[key];
        if (typeof x == "string") {
			x = x.toLowerCase(); 
			y = y.toLowerCase();
		}
		return ((x < y) ? -1 : ((x > y) ? 1 : 0));
	});
}

function abrirPopupConfirmacao(produtoList, msg1, msg2, functionSim, functionNao) {
	$jq('#msg1PopupConfirmacao').text(msg1);
	var html = '';
	$jq.each(sortByKey(produtoList, 'sistemaProduto.cdCodigo'), function(i, produto) {
		html += '<div class="dados-list"><li>' + ((produto.sistemaProduto) ? produto.sistemaProduto.cdCodigo : '') + '&nbsp;(' + ((produto.gama) ? produto.gama.nmGama : '') + ')</li></div>';
	});
	$jq('#produtosPopupConfirmacao').html(html);
	$jq('#msg2PopupConfirmacao').text(msg2);
	
	var popupParams = {
		create: function(event, ui) {
			createPopupTitle('popupConfirmacao', mensagens.Confirmacao, null);
      	},
      	modal: true,
	    resizable: false,
	    width: 400,
	    height: 120
	};
	popupParams.buttons = new Object();
	popupParams.buttons[mensagens.Sim] = functionSim;
	popupParams.buttons[mensagens.Nao] = functionNao;
	$jq('#popupConfirmacao').dialog(popupParams);
	$jq('#popupConfirmacao').dialog('close');
	$jq('#popupConfirmacao').dialog(popupParams);
}

function createPopupTitle(id, title, fechar) {
	var dialog = $jq('#' + id).dialog('widget');
	dialog.attr('id', id + 'Dialog');
	var popup = $jq('#' + id + 'Dialog');
	var titleBar = $jq('.ui-dialog-titlebar-close', popup).parent();
	$jq('[role="button"]', popup).remove();
	if (!fechar) {
		fechar = '$jq(\'#' + id + '\').dialog(\'close\');';
	}
	$jq(titleBar).append('<div style="float: right; width: 60px;"><div class="popup_padrao_box_top_right_close" style="float: right;" onclick="' + fechar + '"></div></div>');
	var titleMessage = dialog.attr('aria-labelledby');
	$jq('#' + titleMessage, popup).append(title);
	$jq('#' + titleMessage, popup).removeClass('ui-widget-header').addClass('titlePopup');
}

function habilitaTooltip() {
	$jq('[title]').qtip({
		content: {
			text: false
		},
		style: {
	        classes: 'qtip-blue qtip-shadow'
	    }
    });
}

function len(obj) {
	var l = 0;
	$jq.each(obj, function(i, elem) {
		l ++;
	});
    return l;
}