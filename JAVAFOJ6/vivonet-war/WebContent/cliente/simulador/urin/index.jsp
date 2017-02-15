<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute value="Cliente" name="title"/>
<netui-template:setAttribute value="Simulador URIN" name="modulo"/>

	<netui-template:section name="headerSection">
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/chili/jquery.chili-2.2.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/chili/recipes.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <script type="text/javascript">
			jQuery.noConflict();
			
			onload = function() {
                $('nrLinha').focus();
                if (top.frameApp.dvAnimarAguarde) top.frameApp.stopAnimation();            
            }
			
			function simular(){
				if ($F('nrLinha').blank()) {
                    alert('Por favor, digite o DDD e o número de telefone para realizar a busca.');
                } else {
                    var params = $H();
                    var linha = $F('nrLinha');
                    var ddd = linha.substring(1, 3);
                    var tel = linha.substring(4).replace("\\D+", "");
                    
                    params.set('ddd', ddd);
                    params.set('tel', tel);
                    
                    new Ajax.Request('simularUrin.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onCreate: function() {
                        	if (top.frameApp.dvAnimarAguarde) top.frameApp.startAnimation(); 
                        }, onSuccess: function(e) {
                        	var response = e.responseText;
                        	$('div-pesquisa').update("<pre>" + response + "</pre>");
                        	if (top.frameApp.dvAnimarAguarde) top.frameApp.stopAnimation();
                        }, onComplete: function() {
                        	if (top.frameApp.dvAnimarAguarde) top.frameApp.stopAnimation();
                        }, onFailure: function(e) {
                        	if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();  
                            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                            createErrorPopUp('erroPortabilidade', e.statusText, stackTrace, false);
                        }
                    });
                }
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
                width: 780px;
                padding: 5px;
            }
            pre{
				overflow: auto;
				height: 427px;
                width: 765px;
				
				font-family: "helvetica";
				font-size: 12px;
				font-weight: bold;
				
				margin: 6px;
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
		<div id="menuPrincipal"><jsp:include page="/resources/menus/MenuPrincipal.jsp" /></div>
		
		<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="470" width="790" label="Cliente" operacoes="Simulador URIN" scroll="no">

            <table id="table-parametros" cellpadding="0" cellspacing="0">
                <thead>
                    <tr>
                        <td colspan="27">
                            Número do telefone:
                            <input type="text"
                                   id="nrLinha"
                                   name="nrLinha"
                                   maxlength="14"
                                   style="width:90px;"
                                   onkeyup="maskPhoneNumberObj(this)" />
                            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" onclick="simular()" class="button" align="absmiddle" />
                        --</td>
                    </tr>
                </thead>
            </table>
            <div id="div-pesquisa">
            </div>
        </vivo:sessao>
        <vivo:alert atributo="msgRetorno" scope="request" />
	</netui-template:section>
	
</netui-template:template>