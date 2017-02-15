var $jq = jQuery.noConflict();
			
			$jq(document).ready(function () {    
				$jq("#rgb").live("change",function() {
					copyRgb();
				})  
			});
			
						
			function save() {
				if(validarForm()) {
					document.cadastroCorForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/cor/save.do#cadastroCorForm";
					document.cadastroCorForm.submit();
				}
			}

			function validarForm() {

				var camposErro = "";
				var retorno = true;
				
				if (document.cadastroCorForm("idCor").value == '0') {
					camposErro += "Nome da Cor";
				}

				if (!validarRgb()) {
					if (camposErro != "") {
						camposErro += ", ";	
					}
					camposErro += "Código Hexa";
				}

				if (camposErro != "") {
					document.getElementById('conteudo_divErros').innerHTML = "É obrigatório informar o(s) campo(s) " + camposErro;
					document.getElementById('divErros').style.display = "inline";
					$jq("#divSucess").hide();
					retorno = false;
				}
				
				return retorno;
			}
			
			
			function isCharHex(char){

				var lCharHex = new Array('a','b','c','d','e','f','A','B','C','D','E','F');
				
				if ( char == "") { 
					return false;
				}
				for (i1 = 0; i1 < lCharHex.length; i1++) {
					if (char == lCharHex[i1]){
						return true;
					}
				}
				for (i2 = 0; i2 < 10; i2++) {
					if (char == i2) {
						return true;
					}
				}
				return false;
			}


			//verificação se o campo rgb é um rgb hexadecimal valido 
			function validarRgb() {
				val = document.cadastroCorForm("rgbDisplay").value;
				if (val.charAt(0) != '#'){
					return false;
				} else {
					for (i3 = 1; i3 < 7; i3++) {
						if(!isCharHex(val.charAt(i3))) {
							return false;
						}
					}
				}
				return true;
			}
			
			function init() {
					
			}

			function load(id) {
				if (id != 0) {
						document.getElementById("idCor").value = id;
						document.cadastroCorForm.action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/cor/load.do#contentForm";
						document.getElementById("cadastroCorForm").submit();
				} else {
					document.getElementById("divSucess").style.display = "none";
					document.getElementById("divSucess").innerHTML = "hide";
					$jq("#divErros").hide();
				}
				copyRgb();
			}
			
			function formClear() {
				document.getElementById("idCor")[0].selected = '1';
				document.getElementById("rgb").style.backgroundColor = "#ffffff";
				document.getElementById('rgbDisplay').style.backgroundColor = "#ffffff";
				document.getElementById('rgbDisplay').value = '';
				
				$jq("#divErros").hide();
				$jq("#divSucess").hide();
			}
			
			function copyRgb() {
				$jq("#rgbDisplay").val($jq("#rgb").val());
				$jq("#rgbDisplay").css("background-color",$jq("#rgb").css("background-color"));
				$jq("#rgbDisplay").css("color",$jq("#rgb").css("color"));				
			}