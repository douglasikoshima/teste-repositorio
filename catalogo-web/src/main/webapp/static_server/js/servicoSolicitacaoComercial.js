			var $jq = jQuery.noConflict();
			
			$jq(document).ready(function() {
			    var altura = "225px";
			    if (document.getElementById('politicaDispSlctComercialTOinAreaConcorrencia').value == 'S' && document.getElementById('politicaDispSlctComercialTOinPlanoMinuto').value == 'S'
				    ||document.getElementById('politicaDispCndcPagamentoTOinAreaConcorrencia').value == 'S' && document.getElementById('politicaDispCndcPagamentoTOinPlanoMinuto').value == 'S') {
				    altura = "390px";
			    }
			  $jq(".solicitacaoComercialDialogs").dialog({
			    autoOpen: true,
			    show: {
			      effect: "blind",
			      duration: 1000
			    },
				height: altura,
			    width: 800,
			    resizable: false,
			    modal: true
			  });
			  checkError();
			  if($jq("#idTipoSolicitacaoComercial option[selected]").text() == "INCLUSAO" && $jq("#nmTipoServico")[0].value == "BANDA LARGA"){
			  	$jq(".divInOfertaClienteInadimplente").css("display","block");
			  	$jq(".divInOfertaClienteInadimplente").each(function(i,e) {
			  		$jq(e).css("display","block");
			  	})
			  }
			});
			
			function criarNovo() {
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/criarNovo.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function searchSolicitacaoComercial() {
			
				if(validarFormSlct()){
					document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/searchSolicitacaoComercial.do";
					document.getElementById('solicitacaoComercialForm').submit();
				} else {
					posicionaDivErrosMidle("pesqSolicitacaoComDiv");
					generateContentErrorForm("Favor preencher ao menos um parâmetro da pesquisa.");
				}
			}
			
			function searchCanalVenda() {
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/searchCanalVenda.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function switchDisponibilidadeSolicitacaoComercial(id,inDisp) {
				var str = "";
				if(inDisp == "S") str = "desativar";
				else  str = "ativar";
			
				if (confirm("Tem certeza que deseja "+str+" essa configuração?")) {
					document.getElementById("inDisponivelNew").value = inDisp;
					document.getElementById("idSolicitacaoComercial").value = id;
					document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/switchDisponibilidadeSolicitacaoComercial.do";
					document.getElementById('solicitacaoComercialForm').submit();
				}
			}
			
			function switchDisponibilidadeCanalVendaSlctCmrl(id,inDisp) {
				var str = "";
				if(inDisp == "S") str = "desativar";
				else  str = "ativar";
				if (confirm("Tem certeza que deseja "+str+" essa configuração?")) {
					document.getElementById("inDisponivelNew").value = inDisp;
					document.getElementById("idCanalVendaSolicitacaoComercial").value = id;
					document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/switchDisponibilidadeCanalVendaSlctCmrl.do";
					document.getElementById('solicitacaoComercialForm').submit();
				}
			}
			
			function findCanalVendaBySolicitacaoComercial(idslct,iddp) {
				document.getElementById("idSolicitacaoComercial").value = idslct;
				document.getElementById("idPoliticaDispSlctComercial").value = iddp;
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/findCanalVendaBySolicitacaoComercial.do";
				document.getElementById('solicitacaoComercialForm').submit();
			
			}
			
			function openSlctc1Dialog1() {
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/openSlctc1Dialog1.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
						
			function openEncargoFormSearch(id) {
				document.getElementById("idSolicitacaoComercial").value = id;
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/searchEncargoBySolicitacaoComercial.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function openAssociacaoFinanciamento() {
				document.getElementById("idCondicaoPagamentoEncargoNew").value = "";
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/openAssociacaoFinanciamento.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function openEditAssociacaoFinanciamento(idcpe,idcp,idcv) {
				document.getElementById("idCondicaoPagamentoEncargoNew").value = idcpe;
				document.getElementById("idCondicaoPagamentoEdit").value = idcp;
				document.getElementById("idCanalVendaEdit").value = idcv;
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/openEditAssociacaoFinanciamento.do#contentForm";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function openFinanciamentoFormSearch(idEncargo) {
				document.getElementById("idEncargo").value = idEncargo;
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/findCanalVendaCndcPgtoByIdEncargo.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function searchEncargoBySolicitacaoComercial() {
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/searchEncargoBySolicitacaoComercial.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function createUpdateCanalVendaSlctCmrlList() {
				
				$jq('#canalVendaSelecionadoForm > option').remove();
			
				$jq("#canais-selecionados > option").each(function (index, element) {
					$jq("#canalVendaSelecionadoForm").append($jq(element).clone());
				});
				
				$jq("#canalVendaSelecionadoForm > option").each(function (index, element) {
					element.selected = true;
				});
				
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/createUpdateCanalVendaSlctCmrlList.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function findDispArConcPlMinByIdCnVendaSlct(id) {
				document.getElementById("idCanalVendaSolicitacaoComercial").value = id;
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/findDispArConcPlMinByIdCnVendaSlct.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function createUpdateDispArConcPlMinByIdCnVendaSlct() {
				if (document.getElementById('politicaDispSlctComercialTOinAreaConcorrencia').value == 'S' && document.getElementById('politicaDispSlctComercialTOinPlanoMinuto').value == 'S'
					&& (($jq("#plMinSlctSelecionados > option").length == 0 && $jq("#arConcSlctSelecionadas > option").length != 0)
						|| ($jq("#plMinSlctSelecionados > option").length != 0 && $jq("#arConcSlctSelecionadas > option").length == 0))) {
					$jq("#errorPoliticaDispSlctComercialTO").css('display','block');
					return;
				}
				$jq('#plMinutoSelecionadoForm > option').remove();
				$jq('#areaConcSelecionadoForm > option').remove();
			
				$jq("#plMinSlctSelecionados > option").each(function (index, element) {
					$jq("#plMinutoSelecionadoForm").append($jq(element).clone());
				});
				$jq("#arConcSlctSelecionadas > option").each(function (index, element) {
					$jq("#areaConcSelecionadoForm").append($jq(element).clone());
				});
				
				$jq("#plMinutoSelecionadoForm > option").each(function (index, element) {
					element.selected = true;
				});
				$jq("#areaConcSelecionadoForm > option").each(function (index, element) {
					element.selected = true;
				});
				
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/createUpdateDispArConcPlMinByIdCnVendaSlct.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function searchCndcPgtoEncargo() {
				if(validarFormCondPgtoEncSearch()){
					document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/searchCndcPgtoEncargo.do";
					document.getElementById('solicitacaoComercialForm').submit();
				} else {
					posicionaDivErrosMidle("pesqFinaciamentoDiv");
					generateContentErrorForm("Favor preencher os campos obrigatórios.");
				}
			}
			
			function findDispArConcPlMinByIdCndcPgtoEnc(id) {
				document.getElementById("idCondicaoPagamentoEncargo").value = id;
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/findDispArConcPlMinByIdCndcPgtoEnc.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function createUpdateDispArConcPlMinByIdCndcPgtoEnc() {
				if (document.getElementById('politicaDispCndcPagamentoTOinAreaConcorrencia').value == 'S' && document.getElementById('politicaDispCndcPagamentoTOinPlanoMinuto').value == 'S'
					&& (($jq("#plMinCndcSelecionados > option").length == 0 && $jq("#arConcCndcSelecionadas > option").length != 0)
							|| ($jq("#plMinCndcSelecionados > option").length != 0 && $jq("#arConcCndcSelecionadas > option").length == 0))) {
					$jq('#errorPoliticaDispCndcPagamentoTO').css('display','block');
					return;
				}
				$jq('#plMinCndcSelecionadosForm > option').remove();
				$jq('#arConcCndcSelecionadasForm > option').remove();
			
				$jq("#plMinCndcSelecionados > option").each(function (index, element) {
					$jq("#plMinCndcSelecionadosForm").append($jq(element).clone());
				});
				$jq("#arConcCndcSelecionadas > option").each(function (index, element) {
					$jq("#arConcCndcSelecionadasForm").append($jq(element).clone());
				});
				
				$jq("#plMinCndcSelecionadosForm > option").each(function (index, element) {
					element.selected = true;
				});
				$jq("#arConcCndcSelecionadasForm > option").each(function (index, element) {
					element.selected = true;
				});
			
				document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/createUpdateDispArConcPlMinByIdCndcPgtoEnc.do";
				document.getElementById('solicitacaoComercialForm').submit();
			}
			
			function createUpdateCndcPgtoEncargo() {
				if(validarFormCondPgtoEnc()){
					document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/createUpdateCndcPgtoEncargo.do";
					document.getElementById('solicitacaoComercialForm').submit();
				} else {
					posicionaDivErrosMidle("pesqFinaciamentoDiv");
				  	if(document.solicitacaoComercialForm("idCondicaoPagamentoEncargoNew").value == ""){
						generateContentErrorForm("Não foi possível criar o Plano de Financiamento. Todos os campos obrigatórios devem ser preenchidos.");
					} else {
						generateContentErrorForm("Não foi possível alterar o Plano de Financiamento. Todos os campos obrigatórios devem ser preenchidos.");						
					}
				}
			}
			
			function saveSolicitacaoInadimplente(){
				if(validarFormSlctInadimplenteChecks()){
					document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/saveSolicitacaoInadimplente.do";
					document.getElementById('solicitacaoComercialForm').submit();
				} else {
					posicionaDivErrosMidle("pesqSolicitacaoComDiv");
					generateContentErrorForm("Favor selecionar ao menos um registro.");
				}
			}
			
			function switchDisponibilidadeCndcPgtoEncargo(id,inDisp){
				var str = "";
				if(inDisp == "S") str = "desativar";
				else  str = "ativar";
			
				if (confirm("Tem certeza que deseja "+str+" essa configuração?")) {
					document.getElementById("inDisponivelNew").value = inDisp;
					document.getElementById("idCondicaoPagamentoEncargo").value = id;
					document.getElementById('solicitacaoComercialForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/switchDisponibilidadeCndcPgtoEncargo.do";
					document.getElementById('solicitacaoComercialForm').submit();
				}
			}
			
			function validarFormSlct() {
				var retorno = true;
				if (document.solicitacaoComercialForm("cdSolicitacaoComercial").value == "" 
					&& document.solicitacaoComercialForm("nmSolicitacaoComercial").value == ""
					&& (
						document.solicitacaoComercialForm("idTipoSolicitacaoComercial").value == ""
						|| document.solicitacaoComercialForm("idTipoSolicitacaoComercial").value == 0
					)
					&& document.solicitacaoComercialForm("pzMaximoVigencia").value == ""
					&& document.solicitacaoComercialForm("pzMaximoAtendimento").value == ""
					&& document.solicitacaoComercialForm("inDisponivel").value == ""
				){
					retorno = false;
				}
			
				return retorno;
			}
			
			function validarFormCondPgtoEnc() {
				var retorno = false;
				if (document.solicitacaoComercialForm("idCanalVendaNew").value != ""
					&& document.solicitacaoComercialForm("idCondicaoPagamentoNew").value != ""){
					retorno = true;
				} 
			
				return retorno;
			}
			
			function validarFormCondPgtoEncSearch() {
				var retorno = false;
				if (document.solicitacaoComercialForm("idCanalVendaEncargo").value != ""){
					retorno = true;
				} 
			
				return retorno;
			}
			
			function validarFormSlctInadimplenteChecks() {
				var retorno = false;
				
				if($jq("#servicoSolicitacaoComercialTO input[CHECKED]").length > 0) {
					retorno = true;
				}
			
				return retorno;
			}
			
			function posicionaDivErrosMidle(idElemento) {
				$jq("#divErrosMidle").prependTo("#" + idElemento);
			}
			
			function posicionaDivSucess(idElemento) {
				$jq("#divSucess").prependTo("#" + idElemento);
			}
			
			function toggleInOfertaInadimplente(elem) {
				//configuracao de ofertas inadimplentes pode ser feita somente para solicitacoes comerciais do tipo inclusao e tipo de servico banda larga
				if(elem.options[elem.selectedIndex].text == "INCLUSAO" && $jq("#nmTipoServico")[0].value == "BANDA LARGA"){
					$jq(".divInOfertaClienteInadimplente").css("display","block");
				} else {
					$jq(".divInOfertaClienteInadimplente").css("display","none");
				}
			}