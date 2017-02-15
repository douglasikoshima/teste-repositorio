var tecnologiasCadastroMap = new Object();

function Tecnologia() {}
Tecnologia.prototype.id = null;
Tecnologia.prototype.nome = null;
Tecnologia.prototype.tiposFrequenciaMap = new Object();
Tecnologia.prototype.selecionado = false;
Tecnologia.prototype.salvo = false;

function TipoFrequencia() {}
TipoFrequencia.prototype.id = null;
TipoFrequencia.prototype.nome = null;
TipoFrequencia.prototype.tecnologia = null;
TipoFrequencia.prototype.valoresMap = new Object();
TipoFrequencia.prototype.selecionado = false;
TipoFrequencia.prototype.salvo = false;

function ValorFrequencia() {}
ValorFrequencia.prototype.id = null;
ValorFrequencia.prototype.valor = null;
ValorFrequencia.prototype.tipoFrequencia = null;
ValorFrequencia.prototype.selecionado = false;
ValorFrequencia.prototype.salvo = false;

function obterTecnologiasCadastro() {
	$jq('#resultadoTabelaTiposFrequencia').html('');
	criarDatatableTecnologias();
	abrirPopupTecnologias();
	habilitaTooltip();
}

function criarDatatableTecnologias() {
	$jq('#tResultadoTecnologia').dataTable({
		"sAjaxSource": "obterTecnologias.do",
	    "sDom": '<"top">rt<"bottom"flpi><"clear">',
		"bServerSide": true,
		"bDestroy": true,
		"bSort": false,
		"bLengthChange": true,
		"bFilter": false,
		"bProcessing": true,
		"bPaginate": true,
		"sPaginationType": "full_numbers",
	    "iDisplayLength": 10,
	    "showInfo": false,
    	"oLanguage": popupsDataTableLanguage,
    	"bAutoWidth": false,
		"fnInfoCallback": function(oSettings, iStart, iEnd, iMax, iTotal, sPre) {
			if (!iMax) {
				$jq('#tResultadoTecnologia_paginate').remove();
				return '';
			}
			$jq('#tResultadoTecnologia_paginate').find('.paginate_button_disabled').each(function() {
				$jq(this).attr('class', '');
                $jq(this).attr('style', '');
			});
			selecionarTecnologia($jq('#tResultadoTecnologia').find('tbody tr:first'));
			return '';
		},
       	"fnDrawCallback" : function(oSettings) {
   			var legenda = $jq('#tResultadoTecnologia_info');
			$jq('.paging_full_numbers').attr('class', '');
			$jq(legenda).attr('class','').attr('style', 'float: left');
			$jq('#tResultadoTecnologia_paginate').prepend($jq(legenda));
			$jq('#tResultadoTecnologia_paginate').find('.paginate_button:not(".paginate_button_disabled")').each(function() {
   				$jq(this).attr('style', 'color: #0066CF; cursor: pointer;');
			});
			$jq('#tResultadoTecnologia_paginate').find('.paginate_button_disabled').each(function() {
				$jq(this).attr('class', '');
                $jq(this).attr('style', '');
			});
			habilitaTooltip();
   		},
   		"aoColumnDefs": 
	     [
	     	{
	     		"sWidth": "20px",
				"mRender": function(data, type, row) {
					var id = data;
           			var tecnologia = tecnologiasCadastroMap[id];
           			var checked = (tecnologia && tecnologia.selecionado) ? ' checked' : '';
           			return '<input type="checkbox" id="checkboxTecnologia' + data + '" name="checkboxTecnologia" value="' + data + '" onclick="checarTecnologia(this);"' + checked + '>';
               	},
               	"aTargets": [ 0 ]
			},
			{
				"sWidth": "287px",
				"sClass": "left truncate",
           		"mRender": function(data, type, row) {
           			return data;
               	},
               	"aTargets": [ 1 ]
			},
			{
				"bVisible": false,
				"mRender": function(data, type, row) {
           			var idTecnologia = row[0];
           			var tecnologia = tecnologiasCadastroMap[idTecnologia];
           			if (!tecnologia) {
           				tecnologia = new Tecnologia();
           				tecnologia.id = idTecnologia;
           				tecnologia.nome = row[1];
           				tecnologia.selecionado = false;
           				tecnologia.salvo = true;
           				tecnologia.tiposFrequenciaMap = new Object();
           				tecnologiasCadastroMap[idTecnologia] = tecnologia;
           			}
           			
           			var tiposFrequenciaJSON = $jq.parseJSON(data);
					$jq.each(tiposFrequenciaJSON, function(i1, item) {
						var idTipoFrequencia = item.id;
						var tipoFrequencia = tecnologia.tiposFrequenciaMap[idTipoFrequencia];
						if (!tipoFrequencia) {
							tipoFrequencia = new TipoFrequencia();
							tipoFrequencia.id = idTipoFrequencia;
							tipoFrequencia.nome = item.nome;
							tipoFrequencia.tecnologia = tecnologia;
							tipoFrequencia.selecionado = false;
							tipoFrequencia.salvo = true;
							tipoFrequencia.valoresMap = new Object();
	           				tecnologia.tiposFrequenciaMap[idTipoFrequencia] = tipoFrequencia;
	           			}
           				
           				$jq.each(item.children, function(i2, subitem) {
           					var idValor = subitem.id;
           					var valorFrequencia = tipoFrequencia.valoresMap[idValor];
           					if (!valorFrequencia) {
	           					valorFrequencia = new ValorFrequencia();
	           					valorFrequencia.id = idValor;
	           					valorFrequencia.valor = subitem.nome;
	           					valorFrequencia.tipoFrequencia = tipoFrequencia;
	           					valorFrequencia.selecionado = false;
	           					valorFrequencia.salvo = true;
	           					tipoFrequencia.valoresMap[idValor] = valorFrequencia;
	           				}
           				});
					});
					return '';
               	},
               	"aTargets": [ 2 ]
			}
		]
				
	});
	$jq('#tResultadoTecnologia tbody tr').live('click', function() {
		selecionarTecnologia($jq(this));
	});
	$jq('#tResultadoTecnologia').attr('style', 'width: 200px;');
}

function selecionarTecnologia(row) {
	if ($jq(row).hasClass('selecionado')) return;
	$jq('.selecionado', $jq('#fieldTecnologia')).removeClass('selecionado');
	$jq(row).addClass('selecionado');
	
	var tds = $jq('td', row);
	var checkbox = $jq('input', tds[0]);
	var idTecnologia = $jq(checkbox).attr('value');
	var tecnologia = tecnologiasCadastroMap[idTecnologia];
	if (!tecnologia) return;
	
	$jq('#fieldTiposFrequencia').hide();
	if ($jq.isEmptyObject(tecnologia.tiposFrequenciaMap)) {
		$jq('#resultadoTabelaTiposFrequencia').html('<div class="ufLinha">' + mensagens.Naoavalores + '</div>');
		$jq('#fieldFrequencias').hide();
	} else {
		criarDatatableTiposFrequencia(tecnologia);
	}
	$jq('#fieldTiposFrequencia').show();
	habilitaTooltip();
}

function criarDatatableTiposFrequencia(tecnologia) {
	var tiposFrequenciaArray = new Array();
	$jq.each(tecnologia.tiposFrequenciaMap, function(index, tipoFrequencia) {
		tiposFrequenciaArray.push(tipoFrequencia);
	});
	var aaData = new Array();
	$jq.each(sortByKey(tiposFrequenciaArray, "nome"), function(index, tipoFrequencia) {
		aaData.push([tipoFrequencia.id, tipoFrequencia.nome]);
	});
	$jq('#resultadoTabelaTiposFrequencia').html('<div style="width: 200px; height: 215px; overflow-x: hidden; overflow-y: auto;"><table id="tResultadoTiposFrequencia" class="tabela-padrao table_body"></table></div>');
	$jq('#tResultadoTiposFrequencia').dataTable({
		"sDom": '<"top">rt<"bottom"flpi><"clear">',
		"bServerSide": false,
		"bDestroy": true,
		"bSort": false,
		"bLengthChange": true,
		"bFilter": false,
		"bProcessing": false,
		"bPaginate": false,
		"showInfo": false,
    	"bAutoWidth": false,
		"fnInfoCallback": function(oSettings, iStart, iEnd, iMax, iTotal, sPre) {
			selecionarTipoFrequencia($jq('#tResultadoTiposFrequencia').find('tbody tr:first'));
			return '';
		},
		"aaData": aaData,
       	"aoColumnDefs": 
	     [
	     	{
	     		"sWidth": "20px",
				"mRender": function(data, type, row) {
					var id = data;
           			var tipoFrequencia = tecnologia.tiposFrequenciaMap[id];
           			var checked = (tipoFrequencia && tipoFrequencia.selecionado) ? ' checked' : '';
           			return '<input type="checkbox" id="checkboxTipoFrequencia' + data + '" name="checkboxTipoFrequencia" value="' + tecnologia.id + ',' + data + '" onclick="checarTipoFrequencia(this);"' + checked + '>';
               	},
               	"aTargets": [ 0 ]
			},
			{
				"sWidth": "287px",
				"sClass": "left truncate",
           		"mRender": function(data, type, row) {
           			return data;
               	},
               	"aTargets": [ 1 ]
			}
		]
				
	});
	$jq('#tResultadoTiposFrequencia tbody tr').live('click', function() {
		selecionarTipoFrequencia($jq(this));
	});
	$jq('#tResultadoTiposFrequencia').attr('style', 'width: 200px;');
}

function selecionarTipoFrequencia(row) {
	if ($jq(row).hasClass('selecionado')) return;
	$jq('.selecionado', $jq('#fieldTiposFrequencia')).removeClass('selecionado');
	$jq(row).addClass('selecionado');
	
	var tds = $jq('td', row);
	var checkbox = $jq('input', tds[0]);
	var ids = $jq(checkbox).attr('value').split(',');
	var idTecnologia = ids[0];
	var idTipoFrequencia = ids[1];
	var tecnologia = tecnologiasCadastroMap[idTecnologia];
	if (!tecnologia) return;
	var tipoFrequencia = tecnologia.tiposFrequenciaMap[idTipoFrequencia];
	if (!tipoFrequencia) return;
	
	$jq('#fieldFrequencias').hide();
	if ($jq.isEmptyObject(tipoFrequencia.valoresMap)) {
		$jq('#resultadoTabelaFrequencias').html('<div class="ufLinha">' + mensagens.Naoavalores + '</div>');
	} else {
		criarDatatableFrequencias(tipoFrequencia);
	}
	$jq('#fieldFrequencias').show();
	habilitaTooltip();
}

function criarDatatableFrequencias(tipoFrequencia) {
	var valoresArray = new Array();
	$jq.each(tipoFrequencia.valoresMap, function(index, valor) {
		valoresArray.push(valor);
	});
	var aaData = new Array();
	$jq.each(sortByKey(valoresArray, "valor"), function(index, valor) {
		aaData.push([valor.id, valor.valor]);
	});
	$jq('#resultadoTabelaFrequencias').html('<div style="width: 200px; height: 215px; overflow-x: hidden; overflow-y: auto;"><table id="tResultadoFrequencias" class="tabela-padrao table_body"></table></div>');
	$jq('#tResultadoFrequencias').dataTable({
		"sDom": '<"top">rt<"bottom"flpi><"clear">',
		"bServerSide": false,
		"bDestroy": true,
		"bSort": false,
		"bLengthChange": true,
		"bFilter": false,
		"bProcessing": false,
		"bPaginate": false,
		"showInfo": false,
    	"bAutoWidth": false,
		"fnInfoCallback": function(oSettings, iStart, iEnd, iMax, iTotal, sPre) {
			selecionarValorFrequencia($jq('#tResultadoFrequencias').find('tbody tr:first'));
			return '';
		},
		"aaData": aaData,
       	"aoColumnDefs": 
	     [
	     	{
	     		"sWidth": "20px",
				"mRender": function(data, type, row) {
					var id = data;
           			var valor = tipoFrequencia.valoresMap[id];
           			var checked = (valor && valor.selecionado) ? ' checked' : '';
           			return '<input type="checkbox" id="checkboxValorFrequencia' + data + '" name="checkboxValorFrequencia" value="' + tipoFrequencia.tecnologia.id + ',' + tipoFrequencia.id + ',' + data + '" onclick="checarValorFrequencia(this);"' + checked + '>';
               	},
               	"aTargets": [ 0 ]
			},
			{
				"sWidth": "287px",
				"sClass": "left truncate",
           		"mRender": function(data, type, row) {
           			return data;
               	},
               	"aTargets": [ 1 ]
			}
		]
				
	});
	$jq('#tResultadoFrequencias tbody tr').live('click', function() {
		selecionarValorFrequencia($jq(this));
	});
	$jq('#tResultadoFrequencias').attr('style', 'width: 200px;');
}

function selecionarValorFrequencia(row) {
	if ($jq(row).hasClass('selecionado')) return;
	$jq('.selecionado', $jq('#fieldFrequencias')).removeClass('selecionado');
	$jq(row).addClass('selecionado');
	
	habilitaTooltip();
}

function checarTecnologia(checkbox) {
	var id = $jq(checkbox).attr('value');
	var tecnologia = tecnologiasCadastroMap[id];
	tecnologia.selecionado = checkbox.checked;
	tecnologia.salvo = !tecnologia.salvo;
	$jq.each(tecnologia.tiposFrequenciaMap, function(i1, tipoFrequencia) {
		if (tipoFrequencia.selecionado) {
			$jq('#checkboxTipoFrequencia' + tipoFrequencia.id).removeAttr('checked');
			tipoFrequencia.selecionado = false;
			tipoFrequencia.salvo = !tipoFrequencia.salvo;
			$jq.each(tipoFrequencia.valoresMap, function(i2, valor) {
				if (valor.selecionado) {
					$jq('#checkboxValorFrequencia' + valor.id).removeAttr('checked');
					valor.selecionado = false;
					valor.salvo = !valor.salvo;
				}
			});
		}
	});
}

function checarTipoFrequencia(checkbox) {
	var ids = $jq(checkbox).attr('value').split(',');
	var idTecnologia = ids[0];
	var idTipoFrequencia = ids[1];
	var tecnologia = tecnologiasCadastroMap[idTecnologia];
	var tipoFrequencia = tecnologia.tiposFrequenciaMap[idTipoFrequencia];
	tipoFrequencia.selecionado = checkbox.checked;
	tipoFrequencia.salvo = !tipoFrequencia.salvo;
	if (tipoFrequencia.selecionado && !tecnologia.selecionado) {
		$jq('#checkboxTecnologia' + idTecnologia).attr('checked', 'checked');
		tecnologia.selecionado = true;
		tecnologia.salvo = !tecnologia.salvo;
	}
	$jq.each(tipoFrequencia.valoresMap, function(index, valor) {
		if (valor.selecionado) {
			$jq('#checkboxValorFrequencia' + valor.id).removeAttr('checked');
			valor.selecionado = false;
			valor.salvo = !valor.salvo;
		}
	});
}

function checarValorFrequencia(checkbox) {
	var ids = $jq(checkbox).attr('value').split(',');
	var idTecnologia = ids[0];
	var idTipoFrequencia = ids[1];
	var idValorFrequencia = ids[2];
	var tecnologia = tecnologiasCadastroMap[idTecnologia];
	var tipoFrequencia = tecnologia.tiposFrequenciaMap[idTipoFrequencia];
	var valorFrequencia = tipoFrequencia.valoresMap[idValorFrequencia];
	valorFrequencia.selecionado = checkbox.checked;
	valorFrequencia.salvo = !valorFrequencia.salvo;
	if (valorFrequencia.selecionado && !tipoFrequencia.selecionado) {
		$jq('#checkboxTipoFrequencia' + idTipoFrequencia).attr('checked', 'checked');
		tipoFrequencia.selecionado = true;
		tipoFrequencia.salvo = !tipoFrequencia.salvo;
		if (tipoFrequencia.selecionado && !tecnologia.selecionado) {
			$jq('#checkboxTecnologia' + idTecnologia).attr('checked', 'checked');
			tecnologia.selecionado = true;
			tecnologia.salvo = !tecnologia.salvo;
		}
	}
}

function abrirPopupTecnologias() {
	var popupParams = {
		create: function(event, ui) {
			createPopupTitle('popupTecnologia', mensagens.Tecnologias, 'fecharPopupTecnologias();');
      	},
      	modal: true,
	    height: 'auto',
	    resizable: false,
	    width: 650
	};
	popupParams.buttons = new Object();
	popupParams.buttons[mensagens.Salvar] = function() {
		salvarPopupTecnologias();
	};
	$jq('#popupTecnologia').dialog(popupParams);
	$jq('#popupTecnologia').dialog('close');
	$jq('#popupTecnologia').dialog(popupParams);
}

function precisaSalvarPopupTecnologias() {
	var precisaSalvar = false;
	$jq.each(tecnologiasCadastroMap, function(i1, tecnologia) {
		if (!tecnologia.salvo) {
			precisaSalvar = true;
		}
		if (!precisaSalvar) {
			$jq.each(tecnologia.tiposFrequenciaMap, function(i2, tipoFrequencia) {
				if (!tipoFrequencia.salvo) {
					precisaSalvar = true;
				}
				if (!precisaSalvar) {
					$jq.each(tipoFrequencia.valoresMap, function(i3, valor) {
						if (!valor.salvo) {
							precisaSalvar = true;
							return false;
						}
					});
				}
				if (precisaSalvar) {
					return false;
				}
			});
		}
		if (precisaSalvar) {
			return false;
		}
	});
	return precisaSalvar;
}

function cancelarAlteracoesPopupTecnologias() {
	$jq.each(tecnologiasCadastroMap, function(i1, tecnologia) {
		if (!tecnologia.salvo) {
			tecnologia.selecionado = !tecnologia.selecionado;
			tecnologia.salvo = true;
			$jq.each(tecnologia.tiposFrequenciaMap, function(i2, tipoFrequencia) {
				if (!tipoFrequencia.salvo) {
					tipoFrequencia.selecionado = !tipoFrequencia.selecionado;
					tipoFrequencia.salvo = true;
					$jq.each(tipoFrequencia.valoresMap, function(i3, valor) {
						if (!valor.salvo) {
							valor.selecionado = !valor.selecionado;
							valor.salvo = true;
						}
					});
				}
			});
		}
	});
}

function fecharPopupTecnologias() {
	if (precisaSalvarPopupTecnologias()) {
		if (confirm(mensagens.Asalteracoesseraoperdidas + '?')) {
			cancelarAlteracoesPopupTecnologias();
			$jq('#popupTecnologia').dialog('close');
		}
	} else {
		$jq('#popupTecnologia').dialog('close');
	}
}

function salvarPopupTecnologias() {
	if (!validarPopupTecnologias()) return;
	
	var div = $jq('#tecnologiasSelecionadasCadastroDiv');
	div.html('');
	var tecnologiasObtidas = '';
	$jq.each(tecnologiasCadastroMap, function(i1, tecnologia) {
		if (!tecnologia.salvo) {
			tecnologia.salvo = true;
		}
		$jq.each(tecnologia.tiposFrequenciaMap, function(i2, tipoFrequencia) {
			if (!tipoFrequencia.salvo) {
				tipoFrequencia.salvo = true;
			}
			$jq.each(tipoFrequencia.valoresMap, function(i3, valor) {
				if (!valor.salvo) {
					valor.salvo = true;
				}
			});
		});
		if (tecnologia.selecionado) {
			tecnologiasObtidas += '<div class="linhaRegional">';
			tecnologiasObtidas += '<span class="spanNomeRegional">' + tecnologia.nome + '</span>';
			$jq.each(tecnologia.tiposFrequenciaMap, function(i2, tipoFrequencia) {
				if (tipoFrequencia.selecionado) {
					tecnologiasObtidas += '<div class="divUF">';
					tecnologiasObtidas += '<span class="left">' + tipoFrequencia.nome;
					var n = 0;
					$jq.each(tipoFrequencia.valoresMap, function(i3, valor) {
						if (valor.selecionado) {
							tecnologiasObtidas += ((n > 0) ? '/' : ' ') + valor.valor;
							n ++;
						}
					});
					tecnologiasObtidas += '</span>';
					tecnologiasObtidas += '</div>';
				}
			});
			tecnologiasObtidas += '</div>';
		}
	});
	if (tecnologiasObtidas.length > 0) {
		div.html(tecnologiasObtidas);
	}
	$jq('#popupTecnologia').dialog('close');
}

function validarPopupTecnologias() {
	var msgsErro = '';
	$jq.each(tecnologiasCadastroMap, function(i1, tecnologia) {
		if (tecnologia.selecionado) {
			$jq.each(tecnologia.tiposFrequenciaMap, function(i2, tipoFrequencia) {
				if (tipoFrequencia.selecionado) {
					var valorSelecionado = false;
					$jq.each(tipoFrequencia.valoresMap, function(i3, valor) {
						if (valor.selecionado) {
							valorSelecionado = true;
							return false;
						}
					});
					if (!valorSelecionado) {
						msgsErro += ((msgsErro.length > 0) ? '\r\n' : '');
						msgsErro += mensagens.Favorselecionarfrequenciatipo + ' ' + tipoFrequencia.nome + ' ' + mensagens.paratecnologia + ' ' + tecnologia.nome + '.';
					}
				}
			});
		}
	});
	if (msgsErro.length > 0) {
		alert(msgsErro);
		return false;
	}
	return true;
}