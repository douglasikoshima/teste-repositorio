			var $jq = jQuery.noConflict();
			
			function mostrarCamposPesquisa(obj) {				
				
				$jq("#resultadoImportacaoList").css('display','none');
				$jq("#preencher_parametros").css('display','none');
				$jq("#resultadoServicoServicoArqItemList").css('display','none');
				$jq("#resultadoSolComGrupoComArqItemList").css('display','none');
				$jq("#resultadoSCEncargoGruComArqItemList").css('display','none');
				$jq("#resultadoOfertaVLArqItemList").css('display','none');
				$jq("#resultadoSCEncargoGCPMACArqItemList").css('display','none');
				$jq("#resultadoOfertaVLFArqItemList").css('display','none');
				
				if (obj.value != 0) {
					document.getElementById('idStatusArquivoImportacao').value = 0;
					document.getElementById('nmArquivo').value = "";
					document.getElementById('userImportacao').value = "";
				    document.getElementById('periodoInicio').value = "";
				    document.getElementById('periodoFim').value = ""; 
					$jq("#pesquisa").css('display','block');
				} else {
					$jq("#pesquisa").css('display','none');
				}
			}
			function limpar() {
				$jq("#idStatusArquivoImportacao").attr('value',0);
				$jq("#nmArquivo").attr('value',"");
				$jq("#userImportacao").attr('value',"");
				$jq("#periodoInicio").attr('value',"");
				$jq("#periodoFim").attr('value',"");
			}
			
			function pesquisar() {
				$jq("#preencher_parametros").css('display','none');
				if (document.getElementById('idStatusArquivoImportacao').value == 0	  					
						&& document.getElementById('nmArquivo').value == ""		      
						&& document.getElementById('userImportacao').value == ""	  
						&& document.getElementById('periodoInicio').value == ""
					    && document.getElementById('periodoFim').value == "") { 	
					$jq("#preencher_parametros").css('display','block');
					return;
				}
				document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/pesquisar.do"; 
				document.getElementById('acaoForm').submit();
			}
			
			function pesquisarCritica(idArquivo, nmArquivoConsulta) {										
				document.getElementById('nmArquivoConsulta').value = nmArquivoConsulta;
				
				if (document.getElementById('idTipoImportacao').value == 1) {		
					document.getElementById('idArquivo').value = idArquivo;					
					document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/pesquisarCriticaServicoServicoArq.do";
					document.getElementById('acaoForm').submit();
					
				}
				if (document.getElementById('idTipoImportacao').value == 2) {
					document.getElementById('idArquivo').value = idArquivo;
					document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/pesquisarCriticaSolComGrupoComArq.do";
					document.getElementById('acaoForm').submit();
				}
				if (document.getElementById('idTipoImportacao').value == 3) {
					document.getElementById('idArquivo').value = idArquivo;
					document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/pesquisarCriticaSCEncargoGruComArq.do";
					document.getElementById('acaoForm').submit();
				}
				if (document.getElementById('idTipoImportacao').value == 4) {
					document.getElementById('idArquivo').value = idArquivo;
					document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/pesquisarCriticaSCEncargoGCPMACArq.do";
					document.getElementById('acaoForm').submit();
				}
                if (document.getElementById('idTipoImportacao').value == 5) {
                	document.getElementById('idArquivo').value = idArquivo;
                    document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/pesquisarCriticaServicoArq.do";
                    document.getElementById('acaoForm').submit();
                }
                if (document.getElementById('idTipoImportacao').value == 6) {
                	document.getElementById('idArquivo').value = idArquivo;
                    document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/pesquisarCriticaOfertaVLArq.do";
                    document.getElementById('acaoForm').submit();
                }
                if (document.getElementById('idTipoImportacao').value == 7) {
                	document.getElementById('idArquivo').value = idArquivo;
                    document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/pesquisarCriticaOfertaVLFArq.do";
                    document.getElementById('acaoForm').submit();
                }				                          
			}
			
			function exportarCritica() {
				document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/consulta/exportarCritica.do";
				document.getElementById('acaoForm').submit();
			}
			
			function init() {								
				if (document.getElementById('idTipoImportacao').value != 0) {
					$jq("#pesquisa").css('display','block');
				}
				
				if (document.getElementById('erro').value != "") {
					document.getElementById('divErros').style.display = "block";
					document.getElementById('conteudo_divErros').innerHTML = document.getElementById('erro').value;
			       	
				}
			}