			function create() {
				document.bandeiraForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/bandeira/create.do#contentForm";
				document.bandeiraForm.submit();
			}
			
			function editar(id){
				document.bandeiraForm('idBandeira').value = id;
				document.bandeiraForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/bandeira/edit.do#contentForm";
				document.bandeiraForm.submit();
			}
			function clearPage() {
				document.location.href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/bandeira/CadastroBandeiraAction.do";
			}	
			function search() {
				if (validarFormSearch()) {
					document.bandeiraForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/bandeira/search.do";
					document.bandeiraForm.submit();
				}
			}
			function validarFormSearch() {
				var camposErro = "";
				var retorno = true;
				
				if (document.bandeiraForm('nmBandeiraSearch').value == "" && document.bandeiraForm('sgBandeiraSAPSearch').value == "") {
					camposErro += "Favor preencher pelo menos um parâmetro da Pesquisa.";
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
				
				if (document.bandeiraForm("nmBandeira").value == "" ) {
					if (camposErro != "") {
						camposErro += ", ";	
					}
					camposErro += "Nome";
				}
				
				if (document.bandeiraForm("sgBandeiraSAP").value == "" ) {
					if (camposErro != "") {
						camposErro += ", ";	
					}
					camposErro += "Sigla SAP";
				}
				
				if (document.bandeiraForm("cdInstituicaoFinanceira").value == "" ) {
					if (camposErro != "") {
						camposErro += ", ";	
					}
					camposErro += "Cod. Inst. Financeira";
				}
				
				if (camposErro == "") {
					if (validarExtensao()) {
						if (document.getElementById('imgBandeira').value != '') { 
							generateContentErrorForm("Esse tipo de extensão não é permitida para o campo Imagem.");
							retorno = false;
						}
					}
				}
				
				if (camposErro != "") {
					generateContentErrorForm("Favor preencher os campos: " + camposErro);
					retorno = false;
				}
				
				return retorno;
			}
			
			function validarExtensao() {
				
				var retorno = false;
				var fileStr = document.getElementById('imgBandeira').value;
				var fileArray = new Array();
				fileArray = fileStr.split('\\');
				
				var nameFile = fileArray[fileArray.length-1];
				
				fileArray = nameFile.split('.');
				
				var extensao = fileArray[fileArray.length-1]
				
				if (extensao != 'png' && extensao != 'PNG' && extensao != 'gif' && extensao != 'GIF' && extensao != 'JPEG' && extensao != 'jpeg' && extensao != 'JPG' && extensao != 'jpg') {
					retorno = true;
				}
				
				return retorno;
			}
			
			function cancel() {
				document.acaoForm.action = "/br/com/vivo/catalogoPRS/pageflows/param/bandeira/search.do";
				document.acaoForm.submit();
			}
			
			function save() {
				if (validarForm()) {
					document.bandeiraForm('sgBandeiraSAP').disabled = false;
					//alert("to aqui");
					document.getElementById("bandeiraForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/bandeira/save.do";
					//alert("to aqui 2");
					document.getElementById("bandeiraForm").submit();
				}
			}
			
			function remove(id) {
				if (confirm("Tem certeza que deseja excluir essa Bandeira?")) {
					document.bandeiraForm('idBandeira').value = id;
					document.bandeiraForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/bandeira/remove.do";
					document.bandeiraForm.submit();
				}
			}