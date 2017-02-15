		function create() {
			document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acao/create.do#contentForm";
			document.getElementById("acaoForm").submit();
		}
		
		function editar(id){
			document.getElementById('idAcao').value = id;
			document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acao/edit.do#contentForm";
			document.getElementById("acaoForm").submit();
		}
		
		function clearPage() {
			document.location.href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acao/AcaoAction.do";
		}	
		
		
		function validarFormSearch() {
			var camposErro = "";
			var retorno = true;
							
			if (document.acaoForm('nmAcaoSearch').value == "" &&
					document.acaoForm('sgAcaoSearch').value == "" &&
					document.acaoForm('inDisponivelSearch')[0].checked == false && document.acaoForm('inDisponivelSearch')[1].checked == false &&
					document.acaoForm('dtInicialSearch').value == "" &&
					document.acaoForm('dtFinalSearch').value == ""
					) {
					camposErro += "Favor preencher pelo menos um parâmetro da Pesquisa.<br />";
				}
			
			if (document.getElementById('nmAcaoSearch').value != "" && document.getElementById('nmAcaoSearch').value.length < 3) {
				camposErro += "Favor informar pelo menos 3 caracteres para o campo Nome.<br />";
			}
			
			if (camposErro != "") {
				generateContentError(camposErro);
				retorno = false;
			}
			
			return retorno;
		}

		function cancel() {
			document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acao/search.do";
			document.getElementById("acaoForm").submit();
		}
		
		function remove(id) {
			if (confirm("Tem certeza que deseja excluir essa Ação de Venda?")) {
				document.getElementById('idAcao').value = id;
				document.getElementById("acaoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/acao/remove.do";
				document.getElementById("acaoForm").submit();
			}
		}
		
		function maxLengthTextArea(textAreaField, limit) {
	 		if (textAreaField.value.length >= limit) {
	 			textAreaField.value = textAreaField.value.substring(0, limit-1);
	 		}
	 	}
		
		function selecionarTodos(FieldName) {
			if (document.getElementById(FieldName).value == "") {
				for (var i = 0; i < document.getElementById(FieldName).length; i++) {
					if (i == 0) {
						document.getElementById(FieldName)[i].selected = false;
					} else {
						document.getElementById(FieldName)[i].selected = true;
					}
				}
			}
		}