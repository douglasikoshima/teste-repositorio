var $jq = jQuery.noConflict();
			
			function create() {
				document.acaoForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/create.do#contentForm";
				document.acaoForm.submit();
			}
			
			function editar(id){
				document.acaoForm('idFormaPagamento').value = id;
				document.acaoForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/edit.do#contentForm";
				document.acaoForm.submit();
			}
			function clearPage() {
				document.location.href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/FormaCondicaoPagamentoAction.do";
			}	
			function search() {
				if (validarFormSearch()) {
					document.acaoForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/search.do";
					document.acaoForm.submit();
				}
			}
			function validarFormSearch() {
				var camposErro = "";
				var retorno = true;
				
				if (document.acaoForm('sgMeioPagamentoSearch').value == "" &&
					document.acaoForm('idFormaPagamentoSearch').value == "" &&
					document.acaoForm('idBandeiraSearch').value == "") {
					camposErro += "Favor preencher pelo menos um parâmetro da Pesquisa.<br />";
				}
				
				if (camposErro != "") {
					generateContentError(camposErro);
					retorno = false;
				}
				
				return retorno;
			}
			function init(){
			
			}
			
			function validarForm() {
				var camposErro = "";
				var retorno = true;
                
				if (document.acaoForm("idCanalAtendimento").value == "" ) {
					if (camposErro != "") {
						camposErro += ", ";	
					}
					camposErro += "Canal Atendimento";
				}
				
				if (document.acaoForm("sgMeioPagamento").value == "" ) {
					if (camposErro != "") {
						camposErro += ", ";	
					}
					camposErro += "Meio Pagamento";
				}
				
				if (document.acaoForm("formaPagamento").value == "" ) {
					if (camposErro != "") {
						camposErro += ", ";	
					}
					camposErro += "Forma Pagamento";
				}
                
                if ($jq("input:checkbox:checked").length == 0 ) {
                    if (camposErro != "") {
                        camposErro += ", "; 
                    }
                    camposErro += "Plataforma";
                }                
				
				if (document.acaoForm('idCanalDistribuicao').value == "" ) {
					if (camposErro != "") {
						camposErro += ", ";	
					}
					camposErro += "Canal Distribuição";
				}

				
				if (document.getElementById('sgMeioPagamento').value == '2|J' && document.getElementById('sgMeioPagamento').value != '') {
				
					var isCampoVazio = false;
					var isCampoCheck = false;
					
					for (var i = 0; i < document.all('checkBandeira').length; i++) {
						if (document.all("checkBandeira")[i].checked == true) {
							isCampoCheck = true;
						}
					}
					
					if (!isCampoCheck) {
						if (camposErro != "") {
							camposErro += ", ";	
						}
						camposErro += "Bandeiras";
					}
				} else {
					if (document.getElementById('idCanalDistribuicao').value == '40') {
						if (document.getElementById('sgMeioPagamento').value != "" && document.acaoForm('codInstituicaoFinanceira').value == "" ) {
							if (camposErro != "") {
								camposErro += ", ";	
							}
							camposErro += "Cod. Inst. Financeira";
						}
					}
				}
				
				var isCampoVazio = false;
				
				if (document.acaoForm("condicoesPagto[]").length == null) {
					if (document.acaoForm("condicoesPagto[]").value == "") {
						isCampoVazio = true;
					}
				} else {
					for(var i = 0; i < document.acaoForm("condicoesPagto[]").length; i++) {
						if (document.acaoForm("condicoesPagto[]")[i].value == "") {
							isCampoVazio = true;
						}
					}
				}
				
				if (document.acaoForm("siglasSAP[]").length == null) {
					if (document.acaoForm("siglasSAP[]").value == "") {
						isCampoVazio = true;
					}
				} else {
					for(var i = 0; i < document.acaoForm("siglasSAP[]").length; i++) {
						if (document.acaoForm("siglasSAP[]")[i].value == "") {
							isCampoVazio = true;
						}
					}
				}
				
				if (document.acaoForm("descricoes[]").length == null) {
					if (document.acaoForm("descricoes[]").value == "") {
						isCampoVazio = true;
					}
				} else {
					for(var i = 0; i < document.acaoForm("descricoes[]").length; i++) {
						if (document.acaoForm("descricoes[]")[i].value == "") {
							isCampoVazio = true;
						}
					}
				}
				
				if (isCampoVazio) {
					if (camposErro != "") {
						camposErro += ", ";	
					}
					camposErro += "Condição Pagamento";
				}
				
				if (camposErro != "") {
					generateContentErrorForm("Favor preencher os campos: " + camposErro);
					retorno = false;
				}

				return retorno;
			}
			
			function cancel() {
				document.acaoForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/search.do";
				document.acaoForm.submit();
			}
			
			function save() {
				if (validarForm()) {
					document.acaoForm('idCanalDistribuicao').disabled = false;
					document.acaoForm('idCanalAtendimento').disabled = false;
					document.acaoForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/save.do";
					document.acaoForm.submit();
				}
			}
			
			function remove(id) {
				if (confirm("Tem certeza que deseja excluir essa Forma e Condição de Pagamento?")) {
					document.acaoForm('idFormaPagamento').value = id;
					document.acaoForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/formacondicaopagamento/remove.do";
					document.acaoForm.submit();
				}
			}
			
			function setAllCheckBoxes(FormName, FieldName, CheckValue) {
				
				FormName = "acaoForm";
				FieldName = "checkBandeira";
				
				if (document.acaoForm("checkAllBandeira").checked == false) {
					CheckValue = false;
				} else {
					CheckValue = true;
				}
				
				if(!document.forms[FormName])
					return;
				var objCheckBoxes = document.forms[FormName].elements[FieldName];
				if(!objCheckBoxes)
					return;
				var countCheckBoxes = objCheckBoxes.length;
				if(!countCheckBoxes)
					objCheckBoxes.checked = CheckValue;
				else
					// set the check value for all check boxes
					for(var i = 0; i < countCheckBoxes; i++)
							objCheckBoxes[i].checked = CheckValue;
			}
			
			$jq(function () { 
				function removeCampo() { 
					$jq(".removerCampo").unbind("click"); 
					$jq(".removerCampo").bind("click", 
						function () {
						i=0; 
						$jq(".condicoes p.campoCondicao").each(function () { 
						i++;
						});
						if (i>1) { 
							$jq(this).parent().remove(); 
						} 
					}); 
				} 
				removeCampo(); 
				$jq(".adicionarCampo").click(function () { 
					novoCampo = $jq(".condicoes p.campoCondicao:first").clone(); 
					novoCampo.find("input").val(""); 
					novoCampo.insertAfter(".condicoes p.campoCondicao:last"); 
					removeCampo(); 
				}); 
			}); 
			
			function alterarCadastro(value) {
			
                var sgMeioPagamento = document.getElementById('sgMeioPagamento').value;
                if (sgMeioPagamento.indexOf("T") !=-1){
                    $jq(".checkPlataforma1 :input").attr("checked",false);
                    $jq(".checkPlataforma1").css("display","none");
                } else {
                    $jq(".checkPlataforma1").css("display","inline");
                }
                
				if (document.getElementById('sgMeioPagamento').value == '2|J') {
					document.getElementById('cadastroCredito').style.display = "inline";
					document.getElementById('cadastroDebito').style.display = "none";
				} else {
				 	if (document.getElementById('sgMeioPagamento').value != '') {
				 		document.getElementById('cadastroDebito').style.display = "inline";
				 		document.getElementById('cadastroCredito').style.display = "none";
				 		for(var i = 0; i < document.acaoForm("checkBandeira").length; i++) {
							document.acaoForm("checkBandeira")[i].checked = false;
						}
				 	} else {
				 		document.getElementById('cadastroDebito').style.display = "none";
						document.getElementById('cadastroCredito').style.display = "none";
						for(var i = 0; i < document.acaoForm("checkBandeira").length; i++) {
							document.acaoForm("checkBandeira")[i].checked = false;
						}
				 	}
				}
				
			}
			
			function alterarCanalDistribuicao() {
				// SE FOR IGUAL AO CANAL TELEVENDAS
				if (document.getElementById("idCanalDistribuicao").value == '40'){
					document.getElementById("contentCodInstituicao").style.display = "inline";
				} else {
					document.getElementById("contentCodInstituicao").style.display = "none";
					document.getElementById("codInstituicaoFinanceira").value = "";
				}
			}
