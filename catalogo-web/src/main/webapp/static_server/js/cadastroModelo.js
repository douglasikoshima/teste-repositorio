var modelo = null;

function abrirCadastroModelo() {
	$jq('#cadastroModeloDiv').show();
	window.scrollTo(0, document.body.scrollHeight);
	
	limparCadastroModelo();
}

function limparCadastroModelo() {
	modelo = null;
	
	$jq('#tipoProdutoCadastro').val('DEFAULT');
	limparSelect("fabricanteCadastro");
	
	$jq('#nmGrupoProdutoCadastro').val('');
	$jq('#urlCadastro').val('');
	$jq('#dsNotaCadastro').val('');
	
	$jq('#inFimVidaCadastro').val('N');
	$jq('#inDisponivelCadastro').val('N');
	
	caracteristicasCadastroMap = new Object();
	$jq('#caracteristicasSelecionadasCadastroDiv').html('');
	
	tecnologiasCadastroMap = new Object();
	$jq('#tecnologiasSelecionadasCadastroDiv').html('');
	
	multimidiasCadastroMap = new Object();
	if ($jq.fn.DataTable.fnIsDataTable(document.getElementById('tResultadoMultimidia'))) {
		var dataTableMultimidia = $jq('#tResultadoMultimidia').dataTable();
		dataTableMultimidia.fnClearTable();
		$jq('.dataTables_empty', $jq('#tResultadoMultimidia')).hide();
	}
	$jq('#corCadastro').val('DEFAULT');
	
	$jq('#tipoProdutoCadastro').focus();
}

function popularFabricanteCadastro(idFabricante) {
	var tipoProduto = new Object();
	tipoProduto.idTipoProduto = $jq('#tipoProdutoCadastro').val();
	if (tipoProduto.idTipoProduto == 10) { // ACESSORIO
		tecnologiasCadastroMap = new Object();
		$jq('#tecnologiasSelecionadasCadastroDiv').html('');
		$jq('#tecnologiasCadastroDiv').hide('fast');
	} else {
		$jq('#tecnologiasCadastroDiv').show('fast');
	}
	if (tipoProduto.idTipoProduto != 'DEFAULT') {
		$jq.getJSON('getFabricante.do', tipoProduto).done(function(data) {
			$jq('#fabricanteCadastro').find('option:not([value="DEFAULT"])').remove();	
			$jq.each(sortByKey(data, 'nome'), function(index, fabricante) {
				var selected = (idFabricante && idFabricante == fabricante.idFabricante) ? ' selected' : '';
				$jq('#fabricanteCadastro').append('<option value="' + fabricante.idFabricante + '"' + selected + '>' + fabricante.nome + '</option>');
			});
		});
	} else {
		limparSelect("fabricanteCadastro");
	}
}

function fecharCadastroModelo() {
	$jq('#cadastroModeloDiv').hide('fast');
}

function gravarModelo() {
	validarCampos($jq("#cadastro_modelo"));
	if (staticMaps.camposValidado && validarModelo()) {
		if (modelo && modelo.produtoList && modelo.produtoList.length > 0) {
			abrirPopupConfirmacaoAlteracao();
		} else {
			submeterGravacaoModelo();
		}
	}
}

function submeterGravacaoModelo() {
	var id = (modelo) ? modelo.idGrupoProduto : 0;
	
	modelo = new Object();
	modelo.idGrupoProduto = id;
	modelo.idTipoProduto = $jq('#tipoProdutoCadastro').val() != 'DEFAULT' ? $jq('#tipoProdutoCadastro').val() : 0;
	modelo.idFabricante = $jq('#fabricanteCadastro').val() != 'DEFAULT' ? $jq('#fabricanteCadastro').val() : 0;
	modelo.nmGrupoProduto = $jq('#nmGrupoProdutoCadastro').val();
	modelo.url =  $jq('#urlCadastro').val();
	modelo.dsNota =  $jq('#dsNotaCadastro').val();
	modelo.inFimVida =  $jq('#inFimVidaCadastro').val() != 'DEFAULT' ? $jq('#inFimVidaCadastro').val() : 0;
	modelo.inDisponivel =  $jq('#inDisponivelCadastro').val() != 'DEFAULT' ? $jq('#inDisponivelCadastro').val() : 0;
	modelo.idCor = $jq('#corCadastro').val() != 'DEFAULT' ? $jq('#corCadastro').val() : 0;
	
	modelo.idsCaracteristicas = new Array();
	$jq.each(caracteristicasCadastroMap, function(i1, caracteristica) {
		if (caracteristica.selecionado) {
			var valores = new Array();
			$jq.each(caracteristica.valoresMap, function(i2, valor) {
				if (valor.selecionado) {
					valores.push(valor.id);
				}
			});
			modelo.idsCaracteristicas.push({"id": caracteristica.id, "valores": valores});
		}
	});
	
	modelo.idsTecnologias = new Array();
	$jq.each(tecnologiasCadastroMap, function(i1, tecnologia) {
		if (tecnologia.selecionado) {
			var tiposFrequencia = new Array();
			$jq.each(tecnologia.tiposFrequenciaMap, function(i2, tipoFrequencia) {
				if (tipoFrequencia.selecionado) {
					var valores = new Array();
					$jq.each(tipoFrequencia.valoresMap, function(i3, valor) {
						if (valor.selecionado) {
							valores.push(valor.id);
						}
					});
					tiposFrequencia.push({"id": tipoFrequencia.id, "valores": valores});
				}
			});
			modelo.idsTecnologias.push({"id": tecnologia.id, "tiposFrequencia": tiposFrequencia});
		}
	});
	
	modelo.multimidias = new Array();
	$jq.each(multimidiasCadastroMap, function(i, multimidia) {
		modelo.multimidias.push({
			"nome": multimidia.nome,
			"idClassificacao": (multimidia.classificacao && multimidia.classificacao != 'DEFAULT') ? multimidia.classificacao : 0,
			"idCor": (multimidia.cor && multimidia.cor != 'DEFAULT') ? multimidia.cor : 0
		});
	});

	$jq.getJSON((id == 0) ? 'salvarModelo.do' : 'atualizarModelo.do', {jsonModelo: JSON.stringify(modelo)}).done(function(data) {
		if (data.erro) {
			alert(data.erro);
		} else {
			alert($jq('#mensagemSucessoGravacao').text());
			modelo = data.grupoProduto;
		}
	});
}

function validarModelo() {
	if (!temCaracteristicasCadastroSelecionadas()) {
		alert(mensagens.Favorselecionarcaracteristicasmodelo);
		return false;
	}
	// se o tipo de produto for diferente de acessório, é obrigatório informar tecnologia/frequência
	var idTipoProduto = $jq('#tipoProdutoCadastro').val();
	if (idTipoProduto != 10 && !temTecnologiasCadastroSelecionadas()) {
		alert(mensagens.Favorselecionartecnologiasmodelo);
		return false;
	}
	if ($jq.isEmptyObject(multimidiasCadastroMap)) {
		alert(mensagens.Favordefinirmultimidiasmodelo);
		return false;
	}
	if (modelo && modelo.produtoList && modelo.produtoList.length > 0) {
		var idFabricante = $jq('#fabricanteCadastro').val() != 'DEFAULT' ? $jq('#fabricanteCadastro').val() : 0;
		if (modelo.fabricante.idFabricante != idFabricante) {
			alert(mensagens.Permissaoalteracaofabricante);
			return false;
		}
	}
	return true;
}

function temCaracteristicasCadastroSelecionadas() {
	var tem = false;
	$jq.each(caracteristicasCadastroMap, function(index, caracteristica) {
		if (caracteristica.selecionado) {
			tem = true;
			return false;
		}
	});
	return tem;
}

function temTecnologiasCadastroSelecionadas() {
	var tem = false;
	$jq.each(tecnologiasCadastroMap, function(index, tecnologia) {
		if (tecnologia.selecionado) {
			tem = true;
			return false;
		}
	});
	return tem;
}

function abrirPopupConfirmacaoAlteracao() {
	abrirPopupConfirmacao(
		modelo.produtoList,
		mensagens.Alteracaoprodutos,
		mensagens.Alteracaoconfirmacao,
		confirmarAlteracaoModelo,
		cancelarAlteracaoModelo);
}

function confirmarAlteracaoModelo() {
	$jq('#popupConfirmacao').dialog('close');
	submeterGravacaoModelo();
}

function cancelarAlteracaoModelo() {
	$jq('#popupConfirmacao').dialog('close');
}

function alterarModelo(idModelo) {
	var params = new Object();
	params.idModelo = idModelo;
	$jq.getJSON('obterDetalhesModelo.do', params).done(function(data) {
		if (data.erro) {
			alert(data.erro);
		} else {
			abrirCadastroModelo()
			preencherCadastroModelo(data);
		}
	});
}

function copiarModelo(idModelo) {
	var params = new Object();
	params.idModelo = idModelo;
	$jq.getJSON('obterDetalhesModelo.do', params).done(function(data) {
		if (data.erro) {
			alert(data.erro);
		} else {
			abrirCadastroModelo()
			preencherCadastroModelo(data);
			$jq('#nmGrupoProdutoCadastro').val('COPIA DE ' + modelo.nmGrupoProduto);
			modelo = null;
		}
	});
}

function preencherCadastroModelo(data) {
	modelo = data.grupoProduto;
	
	$jq('#tipoProdutoCadastro').val(modelo.tipoProduto.idTipoProduto);
	popularFabricanteCadastro(modelo.fabricante.idFabricante);
	
	$jq('#nmGrupoProdutoCadastro').val(modelo.nmGrupoProduto);
	
	if (modelo.multimidiaList && modelo.multimidiaList.length > 0) {
		$jq.each(modelo.multimidiaList, function(index, multimidia) {
			if (multimidia.tipoMultimidia.idTipoMultimidia == 3) { // link
				$jq('#urlCadastro').val(multimidia.nomeMultimidia);
				return false;
			}
		});
	}
	
	if (modelo.dsNota) {
		$jq('#dsNotaCadastro').val(modelo.dsNota);
	}
	
	$jq('#inFimVidaCadastro').val(modelo.inFimVida);
	$jq('#inDisponivelCadastro').val(modelo.inDisponivel);
	
	if (modelo.caracteristicaList && modelo.caracteristicaList.length > 0) {
		var caracteristicasDefinidas = '';
		caracteristicasCadastroMap = new Object();
		$jq.each(sortByKey(modelo.caracteristicaList, "nmCaracteristica"), function(i1, c) {
			var caracteristica = new Caracteristica();
			caracteristica.id = c.idCaracteristica;
			caracteristica.nome = c.nmCaracteristica;
			caracteristica.selecionado = true;
			caracteristica.salvo = true;
			
			caracteristicasDefinidas += '<div class="linhaRegional">';
			caracteristicasDefinidas += '<span class="spanNomeRegional">' + caracteristica.nome + '</span>';
			
			caracteristica.valoresMap = new Object();
			if (c.valorCaracteristicaList && c.valorCaracteristicaList.length > 0) {
				$jq.each(sortByKey(c.valorCaracteristicaList, "valor"), function(i2, vc) {
					var valor = new Valor();
					valor.id = vc.idValorCaracteristica;
					valor.nome = vc.valor;
					valor.caracteristica = caracteristica;
					valor.selecionado = true;
					valor.salvo = true;
					
					caracteristicasDefinidas += '<div class="divUF">';
					caracteristicasDefinidas += '<span class="left">' + valor.nome + '</span>';
					caracteristicasDefinidas += '</div>';
					
       				caracteristica.valoresMap[valor.id] = valor;
				});
			}
			caracteristicasDefinidas += '</div>';
			caracteristicasCadastroMap[caracteristica.id] = caracteristica;
		});
		$jq('#caracteristicasSelecionadasCadastroDiv').html(caracteristicasDefinidas);
	}
	
	if (modelo.tecnologiaList && modelo.tecnologiaList.length > 0) {
		var tecnologiasDefinidas = '';
		tecnologiasCadastroMap = new Object();
		$jq.each(sortByKey(modelo.tecnologiaList, "nmTecnologia"), function(i1, t) {
			tecnologia = new Tecnologia();
  			tecnologia.id = t.idTecnologia;
  			tecnologia.nome = t.nmTecnologia;
  			tecnologia.selecionado = true;
  			tecnologia.salvo = true;
  			
  			tecnologiasDefinidas += '<div class="linhaRegional">';
			tecnologiasDefinidas += '<span class="spanNomeRegional">' + tecnologia.nome + '</span>';
  			
  			tecnologia.tiposFrequenciaMap = new Object();
			if (t.tipoFrequenciaList && t.tipoFrequenciaList.length > 0) {
				$jq.each(sortByKey(t.tipoFrequenciaList, "nmTipoFrequencia"), function(i2, tf) {
					var tipoFrequencia = new TipoFrequencia();
					tipoFrequencia.id = tf.idTipoFrequencia;
					tipoFrequencia.nome = tf.nmTipoFrequencia;
					tipoFrequencia.tecnologia = tecnologia;
					tipoFrequencia.selecionado = true;
					tipoFrequencia.salvo = true;
       				tecnologia.tiposFrequenciaMap[tipoFrequencia.id] = tipoFrequencia;
					
					tecnologiasDefinidas += '<div class="divUF">';
					tecnologiasDefinidas += '<span class="left">' + tipoFrequencia.nome;
					
					tipoFrequencia.valoresMap = new Object();
					if (tf.vlFrequenciaList && tf.vlFrequenciaList.length > 0) {
						$jq.each(sortByKey(tf.vlFrequenciaList, "vlFrequencia"), function(i3, vf) {
							var valorFrequencia = new ValorFrequencia();
        					valorFrequencia.id = vf.idVlFrequencia;
         					valorFrequencia.valor = vf.vlFrequencia;
         					valorFrequencia.tipoFrequencia = tipoFrequencia;
         					valorFrequencia.selecionado = true;
         					valorFrequencia.salvo = true;
         					tipoFrequencia.valoresMap[valorFrequencia.id] = valorFrequencia;
							
							tecnologiasDefinidas += ((i3 > 0) ? '/' : ' ') + valorFrequencia.valor;
						});
					}
					
					tecnologiasDefinidas += '</span>';
					tecnologiasDefinidas += '</div>';
				});
			}
			tecnologiasDefinidas += '</div>';
			tecnologiasCadastroMap[tecnologia.id] = tecnologia;
		});
		$jq('#tecnologiasSelecionadasCadastroDiv').html(tecnologiasDefinidas);
	}
	
	if (modelo.multimidiaList && modelo.multimidiaList.length > 0) {
		classificacoesList = data.classificacoes;
		coresList = data.cores;
		
		var aaData = new Array();
		multimidiasCadastroMap = new Object();
		$jq.each(sortByKey(modelo.multimidiaList, "nomeMultimidia"), function(index, m) {
			if (m.tipoMultimidia.idTipoMultimidia != 3) { // imagem ou vídeo
				var multimidia = new Multimidia();
				multimidia.nome = m.nomeMultimidia;
				multimidia.caminho = data.caminho_link_imagens_modelo;
				multimidia.classificacao = (m.classificacao) ? m.classificacao.idClassificacao : null;
				multimidia.cor = (m.cor) ? m.cor.idCor : null;
				multimidiasCadastroMap[multimidia.nome] = multimidia;
				aaData.push([multimidia.nome, multimidia.nome, multimidia.classificacao, multimidia.cor]);
			}
		});
		criarDatatableMultimidia(aaData);
	}
	
	if (modelo.cor) {
		$jq('#corCadastro').val(modelo.cor.idCor);
	}
}