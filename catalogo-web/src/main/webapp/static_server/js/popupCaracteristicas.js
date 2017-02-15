var caracteristicasPesquisaMap = new Object();
var caracteristicasCadastroMap = new Object();
var caracteristicasMap;

var caracteristicaCorrente = null;

function Caracteristica() {}
Caracteristica.prototype.id = null;
Caracteristica.prototype.nome = null;
Caracteristica.prototype.valoresMap = new Object();
Caracteristica.prototype.selecionado = false;
Caracteristica.prototype.salvo = false;

function Valor() {}
Valor.prototype.id = null;
Valor.prototype.nome = null;
Valor.prototype.caracteristica = null;
Valor.prototype.selecionado = false;
Valor.prototype.salvo = false;

function obterCaracteristicasPesquisa() {
	caracteristicasMap = caracteristicasPesquisaMap;
	obterCaracteristicas();
}

function obterCaracteristicasCadastro() {
	caracteristicasMap = caracteristicasCadastroMap;
	obterCaracteristicas();
}

function obterCaracteristicas() {
	$jq('#resultadoTabelaValores').html('');
	criarDatatableCaracteristicas();
	abrirPopupCaracteristicas();
	habilitaTooltip();
}

function criarDatatableCaracteristicas() {
	$jq('#tResultadoCaracteristica').dataTable({
		"sAjaxSource": "obterCaracteristicas.do",
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
				$jq('#tResultadoCaracteristica_paginate').remove();
				return '';
			}
			$jq('#tResultadoCaracteristica_paginate').find('.paginate_button_disabled').each(function() {
				$jq(this).attr('class', '');
                $jq(this).attr('style', '');
			});
			selecionarCaracteristica($jq('#tResultadoCaracteristica').find('tbody tr:first'));
			return '';
		},
       	"fnDrawCallback" : function(oSettings) {
   			var legenda = $jq('#tResultadoCaracteristica_info');
			$jq('.paging_full_numbers').attr('class', '');
			$jq(legenda).attr('class','').attr('style', 'float: left');
			$jq('#tResultadoCaracteristica_paginate').prepend($jq(legenda));
			$jq('#tResultadoCaracteristica_paginate').find('.paginate_button:not(".paginate_button_disabled")').each(function() {
   				$jq(this).attr('style', 'color: #0066CF; cursor: pointer;');
			});
			$jq('#tResultadoCaracteristica_paginate').find('.paginate_button_disabled').each(function() {
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
           			var caracteristica = caracteristicasMap[id];
           			var checked = (caracteristica && caracteristica.selecionado) ? ' checked' : '';
           			return '<input type="checkbox" id="checkboxCaracteristica' + data + '" name="checkboxCaracteristica" value="' + data + '" onclick="checarCaracteristica(this);"' + checked + '>';
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
           			var idCaracteristica = row[0];
           			var caracteristica = caracteristicasMap[idCaracteristica];
           			if (!caracteristica) {
           				caracteristica = new Caracteristica();
           				caracteristica.id = idCaracteristica;
           				caracteristica.nome = row[1];
           				caracteristica.selecionado = false;
           				caracteristica.salvo = true;
           				caracteristica.valoresMap = new Object();
           				caracteristicasMap[idCaracteristica] = caracteristica;
           			}
           			
           			var valoresJSON = $jq.parseJSON(data);
					$jq.each(valoresJSON, function(index, item) {
						var idValorCaracteristica = item.id;
						var valor = caracteristica.valoresMap[idValorCaracteristica];
						if (!valor) {
							valor = new Valor();
							valor.id = idValorCaracteristica;
							valor.nome = item.nome;
							valor.caracteristica = caracteristica;
							valor.selecionado = false;
							valor.salvo = true;
	           				caracteristica.valoresMap[idValorCaracteristica] = valor;
	           			}
					});
					return '';
               	},
               	"aTargets": [ 2 ]
			}
		]
				
	});
	$jq('#tResultadoCaracteristica tbody tr').live('click', function() {
		selecionarCaracteristica($jq(this));
	});
	$jq('#tResultadoCaracteristica').attr('style', 'width: 300px;');
}

function selecionarCaracteristica(row) {
	if ($jq(row).hasClass('selecionado')) return;
	$jq('.selecionado', $jq('#fieldCaracteristica')).removeClass('selecionado');
	$jq(row).addClass('selecionado');
	
	var tds = $jq('td', row);
	var checkbox = $jq('input', tds[0]);
	var idCaracteristica = $jq(checkbox).attr('value');
	var caracteristica = caracteristicasMap[idCaracteristica];
	if (caracteristica) {
		caracteristicaCorrente = caracteristica;
	} else {
		caracteristicaCorrente = null;
		return;
	}
	
	$jq('#fieldValores').hide();
	if ($jq.isEmptyObject(caracteristica.valoresMap)) {
		$jq('#resultadoTabelaValores').html('<div class="ufLinha">' + mensagens.Naoavalores + '</div>');
	} else {
		criarDatatableValores(caracteristica);
	}
	$jq('#fieldValores').show();
	habilitaTooltip();
}

function criarDatatableValores(caracteristica) {
	var valoresArray = new Array();
	$jq.each(caracteristica.valoresMap, function(index, valor) {
		valoresArray.push(valor);
	});
	var aaData = new Array();
	$jq.each(sortByKey(valoresArray, "nome"), function(index, valor) {
		aaData.push([valor.id, valor.nome]);
	});
	$jq('#resultadoTabelaValores').html('<div style="width: 300px; height: 215px; overflow-x: hidden; overflow-y: auto;"><table id="tResultadoValores" class="tabela-padrao table_body"></table></div>');
	$jq('#tResultadoValores').dataTable({
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
			selecionarValorCaracteristica($jq('#tResultadoValores').find('tbody tr:first'));
			return '';
		},
		"aaData": aaData,
       	"aoColumnDefs": 
	     [
	     	{
	     		"sWidth": "20px",
				"mRender": function(data, type, row) {
					var id = data;
           			var valor = caracteristica.valoresMap[id];
           			var checked = (valor && valor.selecionado) ? ' checked' : '';
           			return '<input type="checkbox" id="checkboxValorCaracteristica' + data + '" name="checkboxValorCaracteristica" value="' + caracteristica.id + ',' + data + '" onclick="checarValorCaracteristica(this);"' + checked + '>';
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
	$jq('#tResultadoValores tbody tr').live('click', function() {
		selecionarValorCaracteristica($jq(this));
	});
	$jq('#tResultadoValores').attr('style', 'width: 300px;');
}

function selecionarValorCaracteristica(row) {
	if ($jq(row).hasClass('selecionado')) return;
	$jq('.selecionado', $jq('#fieldValores')).removeClass('selecionado');
	$jq(row).addClass('selecionado');
	
	habilitaTooltip();
}

function checarCaracteristica(checkbox) {
	var id = $jq(checkbox).attr('value');
	var caracteristica = caracteristicasMap[id];
	caracteristica.selecionado = checkbox.checked;
	caracteristica.salvo = !caracteristica.salvo;
	$jq.each(caracteristica.valoresMap, function(index, valor) {
		if (valor.selecionado) {
			$jq('#checkboxValorCaracteristica' + valor.id).removeAttr('checked');
			valor.selecionado = false;
			valor.salvo = !valor.salvo;
		}
	});
}

function checarValorCaracteristica(checkbox) {
	var ids = $jq(checkbox).attr('value').split(',');
	var idCaracteristica = ids[0];
	var idValorCaracteristica = ids[1];
	var caracteristica = caracteristicasMap[idCaracteristica];
	var valor = caracteristica.valoresMap[idValorCaracteristica];
	valor.selecionado = checkbox.checked;
	valor.salvo = !valor.salvo;
	if (valor.selecionado && !caracteristica.selecionado) {
		$jq('#checkboxCaracteristica' + idCaracteristica).attr('checked', 'checked');
		caracteristica.selecionado = true;
		caracteristica.salvo = !caracteristica.salvo;
	}
}

function abrirPopupCaracteristicas() {
	var popupParams = {
		create: function(event, ui) {
			createPopupTitle('popupCaracteristica', mensagens.Caracteristicas, 'fecharPopupCaracteristicas();');
      	},
      	modal: true,
	    height: 'auto',
	    resizable: false,
	    width: 670
	};
	popupParams.buttons = new Object();
	if (caracteristicasMap == caracteristicasCadastroMap) {
		popupParams.buttons[mensagens.IncluirNovoValor] = function() {
			incluirNovoValorCaracteristica();
		};
	}
	popupParams.buttons[mensagens.Salvar] = function() {
		salvarPopupCaracteristicas();
	};
	$jq('#popupCaracteristica').dialog(popupParams);
	$jq('#popupCaracteristica').dialog('close');
	$jq('#popupCaracteristica').dialog(popupParams);
}

function precisaSalvarPopupCaracteristicas() {
	var precisaSalvar = false;
	$jq.each(caracteristicasMap, function(i1, caracteristica) {
		if (!caracteristica.salvo) {
			precisaSalvar = true;
		}
		if (!precisaSalvar) {
			$jq.each(caracteristica.valoresMap, function(i2, valor) {
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
	return precisaSalvar;
}

function cancelarAlteracoesPopupCaracteristicas() {
	$jq.each(caracteristicasMap, function(i1, caracteristica) {
		if (!caracteristica.salvo) {
			caracteristica.selecionado = !caracteristica.selecionado;
			caracteristica.salvo = true;
			$jq.each(caracteristica.valoresMap, function(i2, valor) {
				if (!valor.salvo) {
					valor.selecionado = !valor.selecionado;
					valor.salvo = true;
				}
			});
		}
	});
}

function fecharPopupCaracteristicas() {
	if (precisaSalvarPopupCaracteristicas()) {
		if (confirm(mensagens.Asalteracoesseraoperdidas + '?')) {
			cancelarAlteracoesPopupCaracteristicas();
			$jq('#popupCaracteristica').dialog('close');
		}
	} else {
		$jq('#popupCaracteristica').dialog('close');
	}
}

function salvarPopupCaracteristicas() {
	var div = (caracteristicasMap == caracteristicasPesquisaMap)
		? $jq('#caracteristicasSelecionadasPesquisaDiv')
		: $jq('#caracteristicasSelecionadasCadastroDiv');
	div.html('');
	var caracteristicasObtidas = '';
	$jq.each(caracteristicasMap, function(i1, caracteristica) {
		if (!caracteristica.salvo) {
			caracteristica.salvo = true;
		}
		$jq.each(caracteristica.valoresMap, function(i2, valor) {
			if (!valor.salvo) {
				valor.salvo = true;
			}
		});
		if (caracteristica.selecionado) {
			caracteristicasObtidas += '<div class="linhaRegional">';
			caracteristicasObtidas += '<span class="spanNomeRegional">' + caracteristica.nome + '</span>';
			$jq.each(caracteristica.valoresMap, function(i2, valor) {
				if (valor.selecionado) {
					caracteristicasObtidas += '<div class="divUF">';
					caracteristicasObtidas += '<span class="left">' + valor.nome + '</span>';
					caracteristicasObtidas += '</div>';
				}
			});
			caracteristicasObtidas += '</div>';
		}
	});
	if (caracteristicasObtidas.length > 0) {
		div.html(caracteristicasObtidas);
	}
	$jq('#popupCaracteristica').dialog('close');
}

function incluirNovoValorCaracteristica() {
	if (caracteristicaCorrente) {
		abrirPopupIncluirValor();
		habilitaTooltip();
	}
}

function abrirPopupIncluirValor() {
	var popupParams = {
		create: function(event, ui) {
			createPopupTitle('popupIncluirValor', mensagens.Caracteristicas, null);
      	},
      	modal: true,
	    height: 180,
	    resizable: false,
	    width: 250
	};
	popupParams.buttons = new Object();
	popupParams.buttons[mensagens.Salvar] = function() {
		salvarValor();
	};
	$jq('#popupIncluirValor').dialog(popupParams);
	$jq('#popupIncluirValor').dialog('close');
	$jq('#popupIncluirValor').dialog(popupParams);
	
	$jq('#valor').val('');
	$jq('#valor').focus();
}

function salvarValor(){
	var valor = new Object();
	valor.nome = $jq.trim($jq('#valor').val());
	if (valor.nome == '') {
		alert(mensagens.E01);
		$jq('#valor').val('');
		$jq('#valor').focus();
		return;
	}
	valor.idCaracteristica = caracteristicaCorrente.id;
	$jq.getJSON('salvarValor.do', {jsonValor: JSON.stringify(valor)}).done(function(data) {
		if (data.erro) {
			alert(data.erro);
		} else {
			valor = new Valor();
			valor.id = data.valor.id;
			valor.nome = data.valor.nome;
			valor.caracteristica = caracteristicaCorrente;
			valor.selecionado = false;
			valor.salvo = true;
			caracteristicaCorrente.valoresMap[valor.id] = valor;
			
			var dataTableValores = document.getElementById('tResultadoValores');
			if (dataTableValores && $jq.fn.DataTable.fnIsDataTable(dataTableValores)) {
				$jq('#tResultadoValores').dataTable().fnAddData([valor.id, valor.nome]);
				selecionarValorCaracteristica($jq('#tResultadoValores').find('tbody tr:contains(\'' + valor.nome + '\')'));
				
			} else {
				criarDatatableValores(caracteristicaCorrente);
			}
			
			var checkbox = document.getElementById('checkboxValorCaracteristica' + valor.id);
			$jq(checkbox).attr('checked', 'checked');
			checarValorCaracteristica(checkbox);
			
			$jq('#popupIncluirValor').dialog('close');
		}
	});
}