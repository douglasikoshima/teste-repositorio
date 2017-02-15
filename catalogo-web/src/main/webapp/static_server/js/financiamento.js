
			var $jq = jQuery.noConflict();

			function search() {
				document.acaoForm.action = "search.do";
				document.acaoForm.submit();
			}
		
			function mostrarPesquisa(){
				$('divErros').style.display = "none";
				$jq("#novoProdutos").css('display','none');
				$jq("#editarProdutos").css('display','none');
				$jq('html, body').animate({scrollTop: $jq("#resultadoPesquisaProduto").css('display','block').offset().top}, 2000);
			};
			
			function mostrarErro(msg) {
				document.getElementById('divErros').style.display = "block";
		       	document.getElementById('conteudo_divErros').innerHTML = msg;
			}
			
			function gravarRegistros(){
				if (acaoForm.nomeNew.value == "" 
					|| acaoForm.qtdeParcelaNew.value == ""
					|| acaoForm.taxaJurosNew.value == ""){
					$jq("#divSucess").hide();
					if(acaoForm.idEditado.value == '') {
						document.getElementById('flash').innerHTML = 'Não foi possível criar o Financiamento. Todos os campos obrigatórios devem ser preenchidos';
						$jq('#flash').css('display','block');
				    } else {
						document.getElementById('flash').innerHTML = 'Não foi possível alterar o Financiamento. Todos os campos obrigatórios devem ser preenchidos.';
						$jq('#flash').css('display','block');
				    }
					return;
				} else{
					document.getElementById("taxaJurosNew").disabled = false;
					document.getElementById("qtdeParcelaNew").disabled = false;
					document.acaoForm.action = "gravar.do#acaoForm";
					document.acaoForm.submit();
				}
			}
			
			function remove(id) {
				if (confirm("Deseja realmente excluir esse financiamento?"))  {
					acaoForm.idEditado.value = id;
					document.acaoForm.action = "remove.do#acaoForm";
					document.acaoForm.submit();
				}			
			}
			
			function change(id, nome, qtdeParcela, taxaJuros, inCriacaoCatalogo) {
				if (!inCriacaoCatalogo) {
					document.getElementById("taxaJurosNew").disabled = true;
					document.getElementById("qtdeParcelaNew").disabled = true;
				} else {
					document.getElementById("taxaJurosNew").disabled = false;
					document.getElementById("qtdeParcelaNew").disabled = false;				
				}
				$jq('html, body').animate({scrollTop: $jq("#editarProdutos").css('display','block').offset().top}, 2000);
				acaoForm.idEditado.value = id;
				acaoForm.nomeNew.value = nome;
				acaoForm.qtdeParcelaNew.value = qtdeParcela;
				acaoForm.taxaJurosNew.value = taxaJuros.replace(/\./, ",");
			}
			
			function changeStatus(id, statusAtual) {
			    var msg = "";
				if (statusAtual == "true") {
					acaoForm.statusAtual.value = "ativo";
					msg = "Tem certeza que deseja Desativar essa Configuração?";
				} else {
					acaoForm.statusAtual.value = "inativo";
					msg = "Tem certeza que deseja Ativar essa Configuração?";
				}
				if (confirm(msg)) {
					acaoForm.idEditado.value = id;
					acaoForm.action = "changeStatus.do#acaoForm";
					acaoForm.submit();
				}
			}
			
	
			/* exemplo novos registros */
			function novosRegistros(){
				document.getElementById("taxaJurosNew").disabled = false;
				document.getElementById("qtdeParcelaNew").disabled = false;	
				acaoForm.idEditado.value = "";
				acaoForm.nomeNew.value = "";
				acaoForm.qtdeParcelaNew.value = "";
				acaoForm.taxaJurosNew.value = "";			
				$('divErros').style.display = "none";
				$jq("#resultadoPesquisaProduto").css('display','none');
				$jq('html, body').animate({scrollTop: $jq("#editarProdutos").css('display','block').offset().top}, 2000);
			}