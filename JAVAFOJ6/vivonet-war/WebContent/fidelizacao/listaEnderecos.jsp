<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="ErroForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.pesquisaEndereco"/>
<logic:empty name="ErroForm" property="erro">
	<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.pesquisaEndereco.listaEnderecos.enderecoVOArray"/>
</logic:empty>

<acesso:controlInitEnv/>
<html>
    <head>
        <title></title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
        <script>            
            <!--
                function get_radio_value(){
                    if(document.forms[0].enderecoSelecionado){
                        if(document.forms[0].enderecoSelecionado.length){
                            for(var i=0; i < document.forms[0].enderecoSelecionado.length; i++){                   
                                if(document.forms[0].enderecoSelecionado[i].checked){
                                    var rad_val = document.forms[0].enderecoSelecionado[i].value;
                                    return rad_val;
                                }
                            }
                        }else{
                            if(document.forms[0].enderecoSelecionado.checked){
                                var rad_val = document.forms[0].enderecoSelecionado.value;
                                return rad_val;
                            }
                        }
                    }else{
                        return false;
                    }
                }

                function selEndereco(idArray){                
                    if(idArray){
                        if (parent.inAnalise != null && parent.inAnalise != undefined && parent.inAnalise == true) {
                            var xmlhttp = new ActiveXObject("microsoft.xmlhttp");
                            xmlhttp.open("GET","buscaEnderecoCD.do?selecionado=TRUE&idArrayEndereco="+idArray+"&inAnalise=TRUE", true);
                            xmlhttp.setRequestHeader("Content-Type","text/xml");
                            xmlhttp.setRequestHeader("chartset","ISO-8859-1");
                            xmlhttp.send();
                            
                            xmlhttp.onreadystatechange = function() {
                    
                                if (xmlhttp.readyState==4){
                                    
                                    var texto = xmlhttp.responseText;
                                    texto = unescape(texto);
                                    oXml = new ActiveXObject("microsoft.xmldom");
                                    
                                    if ( !oXml.loadXML(texto) || oXml.selectSingleNode("/EnderecoVO")==null ) {
                                        alert('Erro durante carregamento de endereço. Tente novamente.');
                                    } else {
                                        
                                        var objSelect = document.getElementById("idMotivoAlteracaoEndereco");
                                        var objForm = document.forms[0];
                                        var doc = window.top.frameApp.ti_frameAbas.frames["frmQuestionario"].frames["ifrmAnalisesBKO"].document;
                                        
                                        var nmTituloLogradouro    = oXml.selectSingleNode("/EnderecoVO/nmTituloLogradouro").text;
                                        var nmLogradouro          = oXml.selectSingleNode("/EnderecoVO/nmLogradouro").text;
                                        var nmBairro              = oXml.selectSingleNode("/EnderecoVO/nmBairro").text;
                                        var nmMunicipio           = oXml.selectSingleNode("/EnderecoVO/nmMunicipio").text;
                                        var nrCEP                 = oXml.selectSingleNode("/EnderecoVO/nrCEP").text;
                                        var sgUF                  = oXml.selectSingleNode("/EnderecoVO/UFVO/sgUF").text;
                                        
                                        doc.getElementById("rua").value = nmTituloLogradouro+" "+nmLogradouro;
                                        doc.getElementById("numero").value = "";
                                        doc.getElementById("bairro").value = nmBairro;
                                        doc.getElementById("cidade").value = nmMunicipio;
                                        doc.getElementById("cep").value = nrCEP;
                                        
                                        for (i=0; i < doc.getElementById("estado").length; i++) {
                                            if (doc.getElementById("estado").options[i].text == sgUF) {
                                                doc.getElementById("estado").options[i].selected = true;
                                            }
                                        }
                                        
                                        doc.getElementById("numero").focus();
                                        top.frameApp.$('divPopupTI').hide();
                                    }
                                }
                            }
                        
                        } else {
    
                            document.forms[0].action="buscaEnderecoCD.do?selecionado=TRUE&idArrayEndereco="+idArray+"&idMotivoAlteracaoSelecionado="+parent.idMotivoAlteracaoSelecionado;
                            document.forms[0].target="frmQuestionario";
                            top.frameApp.mostrar_div();
                            document.forms[0].submit();                
                            top.frameApp.$('divPopupTI').hide();
                        }
                    }else{
                        alert('Execute uma pesquisa e/ou selecione um endereço!');
                        return false;
                    }
                }
            //-->
        </script>
		<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
			top.frameApp.oculta_div();
            document.body.style.backgroundColor = '#ededed';
        -->
		</SCRIPT>	
    </head>
    <body>
		<acesso:controlHiddenItem nomeIdentificador="fid_liend_verpagina">
        <html:form action="/fidelizacao/buscaEnderecoCD.do">
            <logic:empty name="ErroForm" property="erro">
    			<vivo:tbTable selectable="true" onRowClick="" layoutWidth="683" layoutHeight="126" tableWidth="683" styleId="tableTitle" sortable="true">
    				<vivo:tbHeader>
    						<vivo:tbHeaderColumn align="left" width="5%" type=""></vivo:tbHeaderColumn>
    						<vivo:tbHeaderColumn align="left" width="10%" type="string">CEP</vivo:tbHeaderColumn>
    						<vivo:tbHeaderColumn align="left" width="25%" type="string">Localidade</vivo:tbHeaderColumn>
    						<vivo:tbHeaderColumn align="left" width="25%" type="string">Logradouro</vivo:tbHeaderColumn>
    						<vivo:tbHeaderColumn align="left" width="10%" type="string">Lado</vivo:tbHeaderColumn>
    						<vivo:tbHeaderColumn align="left" width="20%" type="string">Bairro</vivo:tbHeaderColumn>   
    						<vivo:tbHeaderColumn align="center" width="5%" type="string">UF</vivo:tbHeaderColumn>                                                             
    					</vivo:tbHeader>
    					<vivo:tbRows scroll="true">                        
    						<logic:iterate id="resultados" name="Form" indexId="idx">
    							<vivo:tbRow key="Linha">
    								<vivo:tbRowColumn><input name="enderecoSelecionado" type="radio" class="radio" value="<%=idx%>"></vivo:tbRowColumn>
    								<vivo:tbRowColumn><bean:write name="resultados" property="nrCEP"/></vivo:tbRowColumn>
    								<vivo:tbRowColumn><bean:write name="resultados" property="nmMunicipio"/></vivo:tbRowColumn>
    								<vivo:tbRowColumn><bean:write name="resultados" property="nmLogradouro"/></vivo:tbRowColumn>
    								<vivo:tbRowColumn><bean:write name="resultados" property="dsLado"/></vivo:tbRowColumn>
    								<vivo:tbRowColumn><bean:write name="resultados" property="nmBairro"/></vivo:tbRowColumn>
    								<vivo:tbRowColumn><bean:write name="resultados" property="UFVO.sgUF"/></vivo:tbRowColumn>
    							</vivo:tbRow>
    						</logic:iterate>    
    					</vivo:tbRows>
    			</vivo:tbTable>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            </logic:empty>
            <div align="right">
                <acesso:controlHiddenItem nomeIdentificador="fid_liend_selecionar">
                    <img hspace="22" onClick="selEndereco(get_radio_value()); return false;" src="/FrontOfficeWeb/resources/images/bt_selecionar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_selecionar_over.gif" style="border:none;"/>
                </acesso:controlHiddenItem>
                <logic:notEmpty name="ErroForm" property="erro">
                    <table width="100%" align="center" height="100%" border="0" bgcolor="#E3ECF4">
                        <tr>
                            <td align="center"><b>Foram encontrados mais de 50 registros para os dados de pesquisa selecionados. Por favor refine a pesquisa!<b></td>
                        </tr>
                    </table>
                </logic:notEmpty>
            </div>
        </html:form>
        </acesso:controlHiddenItem>
    </body>
</html>