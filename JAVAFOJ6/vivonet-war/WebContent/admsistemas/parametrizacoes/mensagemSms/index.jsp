<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<netui-template:template templatePage='/resources/jsp/CRMTemplate.jsp'>
	<netui-template:setAttribute value="Administração de Sistemas" name="title" />
	<netui-template:setAttribute value="Administração de Sistemas" name="modulo" />
	<netui-template:section name="headerSection">
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
		<script type="text/javascript">
        	var where = 0;
        	
        	window.onload = function(){
        		checaMensagem();
        	};
        	
        	function limparAcentos(text){
        		text = text.replace(new RegExp('[áàâã]','g'), 'a');
        		text = text.replace(new RegExp('[éèê]','g'), 'e');
        		text = text.replace(new RegExp('[íìî]','g'), 'i');
        		text = text.replace(new RegExp('[óòôõ]','g'), 'o');
        		text = text.replace(new RegExp('[úùû]','g'), 'u');
        		text = text.replace(new RegExp('[ç]','g'), 'c');
        		
        		text = text.replace(new RegExp('[ÁÀÂÃ]','g'), 'A');
        		text = text.replace(new RegExp('[ÉÈÊ]','g'), 'E');
        		text = text.replace(new RegExp('[ÍÌÎ]','g'), 'I');
        		text = text.replace(new RegExp('[ÓÒÔÕ]','g'), 'O');
        		text = text.replace(new RegExp('[ÚÙÛ]','g'), 'U');
        		text = text.replace(new RegExp('[Ç]','g'), 'C');
        		return text;
        	}
        	
        	function checaMensagem() {
        		var texto = $F('textArea');
        		var limite = 300;
        		var contagem = limite - texto.length;
        
        		if(texto.length > limite) {
        			$('textArea').value = texto.substring(0, limite);
        		
        		}else{
        			$('contador').innerHTML = contagem;
        		}
        		
        		if(window.event.keyCode == 8){
        			contagem += 1;		
        		}
        	}
        	
        	function caret() {
        		var node = $('textArea');
        		var texto = $F('textArea');
        		
        		if(node.selectionStart) 
        			return node.selectionStart;
        		else if(!document.selection) 
        			return 0;
        		
        		var c = "\001";
        		var sel	= document.selection.createRange();
        		var txt	= sel.text;
        		var dul	= sel.duplicate();
        		var len	= 0;
        		 
        		try{ 
        			dul.moveToElementText(node); 
        		
        		}catch(e) { 
        			return 0; 
        		}
        
        		sel.text = txt + c;
        		len	= (dul.text.indexOf(c));
        		sel.moveStart('character',-1);
        		sel.text = "";
        		
        		if(len > 0){
        			where = len;
        		
        		}else{
        			where = texto.length;	
        		}
        	}
        
        	function limpar() {
        		$('contador').innerHTML = 300;
        		$('textArea').clear();
        	}
        	
        	function validarConstante(tipo){
        		var texto = $F('textArea');
        		var array_no_spaces = texto.slice(" ");
        		var len = array_no_spaces.length;
        		for(var i = 0; i < len; i++){
        			if((tipo == "pre" && array_no_spaces[i] == "_NRLINHAPRE_CTRL_") || (tipo == "pos" && array_no_spaces[i] == "_NRLINHAPOS_")){
        				return false;
        			}
        		}
        		return true;		
        	}
        
        	function add(tipo) {
        		var texto = $F('textArea');
        		if(validarConstante(tipo)){
        			var pre = texto.slice(0, where);
        			var suf = texto.slice(where, texto.lenght);
        			texto = "";
        			texto = texto.concat(pre);
        			if (tipo == "pre") {
        				texto = texto.concat(" _NRLINHAPRE_CTRL_ ");
        			
        			} else if(tipo == "pos"){
        				texto = texto.concat(" _NRLINHAPOS_ ");
        			}
        			
        			texto = texto.concat(suf);
        			$('textArea').value = texto;
        		}
        		
        		checaMensagem();
        
        		return;
        	}
        
        	function atualizar() {
        		var texto = $F('textArea');
        		
        		if(texto.blank())
        			alert("Área de texto vazia!");
        		
        		else{
        			texto = texto.gsub("(_NRLINHAPOS_|_NRLINHAPRE_CTRL_)", "(XX)XXXX-XXXX");
        			texto = limparAcentos(texto);
        
        			if (confirm(texto + "\n\nDeseja atualizar a mensagem conforme a alteração acima?")) {
        				var params = $H();
        	
        				params.set('texto', $F('textArea'));
        	
        				new Ajax.Request('atualizarMensagemSms.do', {
        					method : 'post',
        					parameters : params,
        					evalScripts : true,
        					onCreate : function() {
        						try {
        							if (top.frameApp.dvAnimarAguarde)
        								top.frameApp.startAnimation();
        						} catch (e) {
        						}
        					},
        					onSuccess : function(e) {
        						alert(e.responseText);
        						try {
        							if (top.frameApp.dvAnimarAguarde)
        								top.frameApp.stopAnimation();
        						} catch (e) {
        						}
        					},
        					onComplete : function() {
        						try {
        							if (top.frameApp.dvAnimarAguarde)
        								top.frameApp.stopAnimation();
        						} catch (e) {
        						}
        					},
        					onFailure : function(e) {
        						try {
        							if (window.top.frameApp.$('dvAnimarAguarde'))
        								window.top.frameApp.stopAnimation();
        						} catch (e) {
        						}
        						var stackTrace = e.getHeader("stackTrace") ? e
        								.getHeader("stackTrace") : "";
        						createErrorPopUp('erroPortabilidade', e.statusText,
        								stackTrace, false);
        					}
        				});
        			}
        		}
        
        	}
        
        	function preVisualizar() {
        		if ($F('textArea').blank())
        			alert("Área de texto vazia!");
        		else{
        			var texto = $F('textArea');
        
        			texto = texto.gsub("(_NRLINHAPOS_|_NRLINHAPRE_CTRL_)", "(XX)XXXX-XXXX");
        			alert("Pré-visualização\n\n" + texto);
        		}
        	}
        	
        	function validarSelect(){
        		if($('selecionado').selectedIndex != 0)
        			$('formMensagemSms').submit();
        	}
        </script>
		<style type="text/css">
            #table-parametros {
            	border: 1px solid #c7daea;
            	width: 100%;
            	margin-top: 3px;
            }
            
            #div-pesquisa {
            	border: 1px solid #c7daea;
            	margin-top: 5px;
            	height: 427px;
            	padding: 5px;
            }
            
            #div-form {
            	margin: 5px 0 5px 0;
            }
            
            #div-form form {
            	margin: 0;
            	padding: 0;
            }
        </style>
	</netui-template:section>

	<netui-template:section name="bodySection">
		<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
		<div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
		<vivo:sessao id="qdMain" height="470" width="790" label="Administração"
			operacoes="Parametrização > SMS Vínculo" scroll="no">
			<html:form action="/admsistemas/parametrizacoes/mensagemSms/selecionado.do" method="post" styleId="formMensagemSms">
				<table id="table-parametros" cellpadding="0" cellspacing="0" height="100px">
					<tr>
						<td colspan="7" align="left" height="40px">
							<html:select styleId="selecionado" property="selecionado" onchange="validarSelect()">
								<html:option value="">Selecione o tipo	de parametro de mensagem SMS</html:option>
								<html:option value="precontrole">SMS confirmação - Pré-pago Controle</html:option>
								<html:option value="pos">SMS confirmação - Pós-Pago</html:option>
								<html:option value="pospendente">SMS pendente - Pós-Pago</html:option>
								<html:option value="pospendente24h">SMS pendente 24h - Pós-Pago</html:option>
						    </html:select>
					    </td>
					</tr>
					<%if (!"".equals(request.getAttribute("isPre"))) {%>
					<tr>
						<td colspan="2" rowspan="2" width="35%" align="left" height="50px">
    						<p><%=request.getAttribute("titulo")%></p>
    						<html:textarea rows="5" cols="40" property="textArea" styleId="textArea" onkeyup="checaMensagem();caret();" onclick="caret()"></html:textarea>
    						<p style="width: 100%; height: 10px; margin-left: 230px;">Restam <span id="contador">300</span> caracteres</p>
    					</td>
						<td colspan="5" >
    						<img src="<%=request.getContextPath()%>/resources/images/bt_addconstanteprepagocontrole_nrml.gif"
    							 onclick="add('pre')" class="button" align="absmiddle" /> 
     
     						<img src="<%=request.getContextPath()%>/resources/images/bt_addconstantepospago_nrml.gif"
    							 onclick="add('pos')" class="button" align="absmiddle" /> 
						</td>
					</tr>
					<tr>
						<td width="9%" align="left"><img
							src="<%=request.getContextPath()%>/resources/images/bt_limparcampos_nrml.gif"
							onclick="limpar()" class="button" align="absmiddle" />
                        </td>
						<td width="10%" align="left"><img
							src="<%=request.getContextPath()%>/resources/images/bt_atualizar_nrml.gif"
							onclick="atualizar()" class="button" align="absmiddle" />
                        </td>
						<td align="left"><img
							src="<%=request.getContextPath()%>/resources/images/bt_previsualizar_nrml.gif"
							onclick="preVisualizar()" class="button" align="absmiddle" />
                        </td>
					</tr>
					<%
						}
					%>
				</table>

			</html:form>
			<%if(!"".equals(request.getAttribute(ConstantesCRM.SERROR))){%>
				<script>
         			alert("<%=request.getAttribute(ConstantesCRM.SERROR)%>");
             	</script>
			<%}%>
		</vivo:sessao>
		<vivo:alert atributo="msgRetorno" scope="request" />
	</netui-template:section>
</netui-template:template>