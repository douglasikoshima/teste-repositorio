var multimidiasCadastroMap = new Object();
var multimidiasCadastroMapCopia = null;
var precisaSalvarPopupMultimidia = false;

var classificacoesList = null;
var coresList = null;

function Multimidia() {}
Multimidia.prototype.nome = null;
Multimidia.prototype.caminho = null;
Multimidia.prototype.classificacao = null;
Multimidia.prototype.cor = null;

function obterMultimidiaCadastro() {
	multimidiasCadastroMapCopia = new Object();
	$jq.each(multimidiasCadastroMap, function(index, multimidia) {
		var multimidiaCopia = new Multimidia();
		multimidiaCopia.nome = multimidia.nome;
		multimidiaCopia.caminho = multimidia.caminho;
		multimidiaCopia.classificacao = multimidia.classificacao;
		multimidiaCopia.cor = multimidia.cor;
		multimidiasCadastroMapCopia[multimidia.nome] = multimidiaCopia;
	});
	precisaSalvarPopupMultimidia = false;
	
	$jq('#uploadForm')[0].reset();
	var dataTableMultimidia = document.getElementById('tResultadoMultimidia');
	if ($jq.fn.DataTable.fnIsDataTable(dataTableMultimidia)) {
		selecionarMultimidia($jq('#tResultadoMultimidia').find('tbody tr:first'));
	}
	abrirPopupMultimidia();
}

function abrirPopupMultimidia() {
	var popupParams = {
		create: function(event, ui) {
			createPopupTitle('popupMultimidia', mensagens.Multimidia, 'fecharPopupMultimidia();');
      	},
      	modal: true,
	    height: 'auto',
	    resizable: false,
	    width: 840
	};
	popupParams.buttons = new Object();
	popupParams.buttons[mensagens.Salvar] = function() {
		salvarPopupMultimidia();
	};
	$jq('#popupMultimidia').dialog(popupParams);
	$jq('#popupMultimidia').dialog('close');
	$jq('#popupMultimidia').dialog(popupParams);
}

function submeterUploadForm() {
	var fileName = $jq('#fileMultimidia').val();
	if (fileName == '') {
		alert(mensagens.Favorselecionarmultimidia);
		$jq('#fileMultimidia').focus();
		return false;
	}
	var fileExtension = fileName.substr(fileName.length - 4).toLowerCase();
	if (fileExtension != '.gif' && fileExtension != '.jpg' && fileExtension != 'jpeg' && fileExtension != '.png' && fileExtension != '.swf') {
		alert(mensagens.Arquivoextensaoinvalidamultimidia);
		$jq('#fileMultimidia').focus();
		return false;
	}
	if (fileExtension == '.swf') {
		var swfCount = 0;
		$jq.each(multimidiasCadastroMap, function(index, multimidia) {
			fileExtension = multimidia.nome.substr(multimidia.nome.length - 4).toLowerCase();
			if (fileExtension == '.swf') {
				swfCount ++;
				return false;
			}
		});
		if (swfCount > 0) {
			alert(mensagens.Permissaocadastrovideomultimidia);
			$jq('#fileMultimidia').focus();
			return false;
		}
	}
	if (len(multimidiasCadastroMap) >= 50) {
		alert(mensagens.Numeromaximomultimidiasmodelo);
		$jq('#fileMultimidia').focus();
		return false;
	}
	$jq("#uploadForm").ajaxSubmit({success: processarRetornoUpload, error: tratarErroUpload});
	return false;
}

function processarRetornoUpload(data) {
	if (data.erro) {
		alert(data.erro);
	} else {
		$jq("#uploadForm")[0].reset();
		
		var item = $jq.parseJSON(data);
		classificacoesList = item.classificacoes;
		coresList = item.cores;
		
		var multimidia = new Multimidia();
		multimidia.nome = item.nome;
		multimidia.caminho = item.caminho_link_imagens_modelo;
		multimidia.classificacao = null;
		multimidia.cor = null;
		multimidiasCadastroMap[multimidia.nome] = multimidia;
		precisaSalvarPopupMultimidia = true;
		
		var dataTableMultimidia = document.getElementById('tResultadoMultimidia');
		if ($jq.fn.DataTable.fnIsDataTable(dataTableMultimidia)) {
			$jq('#tResultadoMultimidia').dataTable().fnAddData([multimidia.nome, multimidia.nome, multimidia.classificacao, multimidia.cor]);
			selecionarMultimidia($jq('#tResultadoMultimidia').find('tbody tr:contains(\'' + multimidia.nome + '\')'));
			
		} else {
			var aaData = new Array();
			aaData.push([multimidia.nome, multimidia.nome, multimidia.classificacao, multimidia.cor]);
			criarDatatableMultimidia(aaData);
		}
	}
}

function criarDatatableMultimidia(aaData) {
	$jq('#tResultadoMultimidia').dataTable({
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
			selecionarMultimidia($jq('#tResultadoMultimidia').find('tbody tr:first'));
			return '';
		},
		"aaData": aaData,
       	"aoColumnDefs": 
	     [
	     	{
				"mRender": function(data, type, row) {
					return data;
               	},
               	"sClass": "left truncate",
               	"aTargets": [ 0 ]
			},
			{
           		"mRender": function(data, type, row) {
           			return '<img alt="' + mensagens.Excluir + '" src="/catalogo/static_server/img/botoes/toolbarButtonGraphics/general/Delete24.gif" onclick="excluirMultimidia(\'' + data + '\')"/>';
               	},
               	"aTargets": [ 1 ]
			},
			{
				"mRender": function(data, type, row) {
					var multimidia = multimidiasCadastroMap[row[0]];
					var select = '<select id="classificacaoMultimidiaCadastro-' + row[0] + '" style="width: 100px;" onchange="definirClassificacaoMultimidia(this);">';
					select += '<option value="DEFAULT">--' + mensagens.selecione + '--</option>';
					$jq.each(classificacoesList, function(index, classificacao) {
						var selected = (multimidia && multimidia.classificacao == classificacao.idClassificacao) ? ' selected' : '';
						select += '<option value="' + classificacao.idClassificacao + '"' + selected + '>' + classificacao.nomeClassificacao + '</option>';
					});
					select += '</select>';
					return select;
               	},
               	"aTargets": [ 2 ]
			},
			{
				"mRender": function(data, type, row) {
					var multimidia = multimidiasCadastroMap[row[0]];
					var select = '<select id="corMultimidiaCadastro-' + row[0] + '" style="width: 100px;" onchange="definirCorMultimidia(this);">';
					select += '<option value="DEFAULT">--' + mensagens.selecione + '--</option>';
					$jq.each(coresList, function(index, cor) {
						var selected = (multimidia && multimidia.cor == cor.idCor) ? ' selected' : '';
						select += '<option value="' + cor.idCor + '"' + selected + '>' + cor.nmCor + '</option>';
					});
					select += '</select>';
					return select;
               	},
               	"aTargets": [ 3 ]
			}
		]
	});
	$jq('#tResultadoMultimidia tbody tr').live('click', function() {
		selecionarMultimidia($jq(this));
	});
}

function selecionarMultimidia(row) {
	if ($jq(row).hasClass('selecionado')) return;
	$jq('.selecionado', $jq('#listaImagens')).removeClass('selecionado');
	$jq(row).addClass('selecionado');
	
	var tds = $jq('td', row);
	var nome = $jq(tds[0]).html();
	var multimidia = multimidiasCadastroMap[nome];
	if (multimidia) {
		$jq('#imagem_modelo').attr('src', '/' + multimidia.caminho + '/' + multimidia.nome);
		$jq('#imagem_modelo').attr('title', multimidia.nome);
		$jq('#imagem_modelo').show();
	} else {
		$jq('#imagem_modelo').attr('src', '');
		$jq('#imagem_modelo').attr('title', '');
		$jq('#imagem_modelo').hide();
	}
	
	habilitaTooltip();
}

function excluirMultimidia(nome) {
	var dataTableMultimidia = $jq('#tResultadoMultimidia').dataTable();
	var index = -1;
	$jq.each(dataTableMultimidia.fnGetNodes(), function(i, tr) {
		var firstTd = $jq(tr).children()[0];
		if ($jq(firstTd).html() == nome) {
			index = i;
			return false;
		}
	});
	dataTableMultimidia.fnDeleteRow(index);
	var multimidiasCadastroMapNovo = new Object();
	$jq.each(multimidiasCadastroMap, function(i, multimidia) {
		if (multimidia.nome != nome) {
			multimidiasCadastroMapNovo[multimidia.nome] = multimidia;
		}
	});
	multimidiasCadastroMap = multimidiasCadastroMapNovo;
	if ($jq.isEmptyObject(multimidiasCadastroMap)) {
		$jq('.dataTables_empty', $jq('#tResultadoMultimidia')).hide();
	};
	precisaSalvarPopupMultimidia = true;
}

function definirClassificacaoMultimidia(select) {
	var nome = $jq(select).attr('id').split('-')[1];
	var multimidia = multimidiasCadastroMap[nome];
	multimidia.classificacao = $jq(select).val();
	precisaSalvarPopupMultimidia = true;
}

function definirCorMultimidia(select) {
	var nome = $jq(select).attr('id').split('-')[1];
	var multimidia = multimidiasCadastroMap[nome];
	multimidia.cor = $jq(select).val();
	precisaSalvarPopupMultimidia = true;
	if (len(multimidiasCadastroMap) == 1 || $jq('#corCadastro').val() == 'DEFAULT') {
		$jq('#corCadastro').val(multimidia.cor);
	}
}

function tratarErroUpload(xhr, textStatus, errorThrown) {
	alert(textStatus);
}

function cancelarAlteracoesPopupMultimidia() {
	multimidiasCadastroMap = multimidiasCadastroMapCopia;
	var aaData = new Array();
	$jq.each(multimidiasCadastroMap, function(index, multimidia) {
		aaData.push([multimidia.nome, multimidia.nome, multimidia.classificacao, multimidia.cor]);
	});
	criarDatatableMultimidia(aaData);
	if ($jq.isEmptyObject(multimidiasCadastroMap)) {
		$jq('.dataTables_empty', $jq('#tResultadoMultimidia')).hide();
	};
}

function fecharPopupMultimidia() {
	if (precisaSalvarPopupMultimidia) {
		if (confirm(mensagens.Asalteracoesseraoperdidas + '?')) {
			cancelarAlteracoesPopupMultimidia();
			$jq('#popupMultimidia').dialog('close');
		}
	} else {
		$jq('#popupMultimidia').dialog('close');
	}
}

function salvarPopupMultimidia() {
	if (!validarPopupMultimidia()) return;
	$jq('#popupMultimidia').dialog('close');
}

function validarPopupMultimidia() {
	var msgErro = '';
	$jq.each(multimidiasCadastroMap, function(index, multimidia) {
		if ((!multimidia.classificacao || multimidia.classificacao == 'DEFAULT') &&
			(!multimidia.cor || multimidia.cor == 'DEFAULT')) {
			msgErro = mensagens.Favorclassificarmultimidias;
			return false;
		}
	});
	if (msgErro.length == 0) {
		$jq.each(multimidiasCadastroMap, function(i1, m1) {
			$jq.each(multimidiasCadastroMap, function(i2, m2) {
				if (i1 != i2) {
					var m1SemClassificacao = (!m1.classificacao || m1.classificacao == 'DEFAULT');
					var m2SemClassificacao = (!m2.classificacao || m2.classificacao == 'DEFAULT');
					var mesmaClassificacao = ((m1SemClassificacao && m2SemClassificacao) || (m1.classificacao == m2.classificacao));
					
					var m1SemCor = (!m1.cor || m1.cor == 'DEFAULT');
					var m2SemCor = (!m2.cor || m2.cor == 'DEFAULT');
					var mesmaCor = ((m1SemCor && m2SemCor) || (m1.cor == m2.cor));
					
					if (mesmaClassificacao && mesmaCor) {
						msgErro = mensagens.Multimidiasdiferentesmesmaclassificacao;
						return false;
					}
				}
			});
			if (msgErro.length > 0) {
				return false;
			}
		});
	}
	if (msgErro.length == 0 && modelo && modelo.multimidiaList && modelo.multimidiaList.length > 0) {
		$jq.each(multimidiasCadastroMap, function(i1, m1) {
			$jq.each(modelo.multimidiaList, function(i2, m2) {
				if (m1.nome == m2.nomeMultimidia) {
					if (m1.classificacao == 'DEFAULT' && m2.classificacao) {
						msgErro = mensagens.Desassociacaoclassificacaomultimidia;
						return false;
					}
					if (m1.cor == 'DEFAULT' && m2.cor) {
						msgErro = mensagens.Desassociacaocormultimidia;
						return false;
					}
					return false;
				}
			});
			if (msgErro.length > 0) {
				return false;
			}
		});
	}
	// a cor padrão é obrigatória
	if (msgErro.length == 0 && $jq('#corCadastro').val() == 'DEFAULT') {
		msgErro = mensagens.E01;
		$jq('#corCadastro').focus();
	}
	if (msgErro.length == 0 && modelo && modelo.multimidiaList && modelo.multimidiaList.length > 0) {
		var idCor = $jq('#corCadastro').val() != 'DEFAULT' ? $jq('#corCadastro').val() : 0;
		if (modelo.cor.idCor != idCor) {
			var corDefinida = false, achouCor = false;
			$jq.each(multimidiasCadastroMap, function(index, multimidia) {
				if (multimidia.cor && multimidia.cor != 'DEFAULT') {
					corDefinida = true;
				}
				if (multimidia.cor == idCor) {
					achouCor = true;
					return false;
				}
			});
			if (corDefinida && !achouCor) {
				msgErro = mensagens.Permissaoalteracaocorpadrao;
			}
		}
	}
	if (msgErro.length > 0) {
		alert(msgErro);
		return false;
	}
	return true;
}