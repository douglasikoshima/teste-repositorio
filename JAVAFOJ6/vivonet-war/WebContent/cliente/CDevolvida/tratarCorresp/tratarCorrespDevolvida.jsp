<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tratarForm"/>
<bean:define id="Paises"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tratarForm.tratarCorrespDevolvida.paisVOArray"/>
<bean:define id="Ufs"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tratarForm.tratarCorrespDevolvida.UFVOArray"/>
<bean:define id="Status"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tratarForm.tratarCorrespDevolvida.listaStatusArray"/>
<bean:define id="StatusCorresp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tratarForm.tratarCorrespDevolvida.listaStatusCorrespArray"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute value="Correspondência Devolvida" name="title"></netui-template:setAttribute>
<netui-template:setAttribute value="Gestão de Clientes" name="modulo"></netui-template:setAttribute>
<netui-template:section name="headerSection">
    <link href="/FrontOfficeWeb/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script>
        function efetuaAlteracoes(){                                                                                      
            if (trim(document.getElementById("dsEndereco").value)=="")
                alert ('Preencha corretamente os dados de endereço');
            else if(trim(document.getElementById("nrEndereco").value)==""){
                alert ('Preencha o número do endereço');
            }else if(trim(document.getElementById("dsBairro").value)==""){
                alert ('Preencha o bairro!');
            }else if(trim(document.getElementById("nrCEP").value)==""){
                alert ('Preencha o CEP!');
            }else if(trim(document.getElementById("dsCidade").value)==""){
                alert ('Preencha a cidade!');
            }else{
                document.forms[0].elements['tratarCorrespDevolvida.enderecoBaseVO.nrCEP'].value = limpaInteiro(document.forms[0].elements['tratarCorrespDevolvida.enderecoBaseVO.nrCEP'].value)
                //parent.dvTratar.style.display = 'none';
                document.forms[0].action="salvaDadosTratar.do";
                //document.forms[0].target="frmPesquisa";
                parent.mostrar_div();
                document.forms[0].submit();
            }            
        }
        
        function voltar(){
            document.forms[0].action="refresh.do";
            parent.mostrar_div();
            document.forms[0].submit();
        }        
    </script>
    <script for="window" event="onload">
        if(document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="ABERTO"    &&
            document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="ABERTA"    &&
            document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="DESLIGADO" &&
            document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="DESLIGADA" &&
            document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="RETIRADO"  &&
            document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="RETIRADA"){
                document.getElementById('comboStatus').disabled=true;
                document.getElementById('dsEndereco').disabled=true;
                document.getElementById('nrEndereco').disabled=true;
                document.getElementById('dsBairro').disabled=true;
                document.getElementById('dsCidade').disabled=true;
                document.getElementById('nrCEP').disabled=true;
                document.getElementById('dsComp').disabled=true;
                document.getElementById('dsUF').disabled=true;
                document.getElementById('dsPais').disabled=true;
            if(document.getElementById('btGravar')){
                document.getElementById('btGravar').style.display='none';
            }
        }
        parent.oculta_div();
    </script>
</netui-template:section>
<netui-template:section name="bodySection">
<acesso:controlHiddenItem nomeIdentificador="cli_tcda_verpagina">
<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<table>
    <tr>
        <td>
        <vivo:sessao id="correspondenciadevolvida" height="470" width="790" label="Correspond&ecirc;ncia Devolvida" operacoes="Alteração" scroll="no">
        <form tagId="fTratarCorresp" name="fTratarCorresp" action="salvaDadosTratar.do" method="POST">
            <div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div>
            <table style="text-indent:3px;" width="100%" class="tblDinamica" border="0" cellspacing="0" cellpadding="0">
                <tr class="tblDinamica_linhapar">
                    <html:hidden name="Form" property="tratarCorrespDevolvida.idCorrespondenciaDevolvida"/>
                    <html:hidden name="Form" property="statusAtual"/>
                    <td nowrap>Tipo Correspondência:</td>
                    <td colspan="1"><html:text name= "Form" property="tratarCorrespDevolvida.dsTipoCorrespondencia" styleClass="textfield" size="39" readonly="true"/></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td nowrap>Motivo de Devolução:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.dsMotivoDevolucao" styleClass="textfield" size="39" readonly="true"/></td>
                    <td nowrap>Data Devolução:</td>
                    <td align=left><html:text name="Form" property="tratarCorrespDevolvida.dtDevolucao" styleClass="textfield" size="18" readonly="true"/></td>
                </tr>
                <tr class="tblDinamica_linhapar">
                    <td nowrap>Linha:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.nrLinha" styleClass="textfield" size="15" readonly="true" onkeyup="maskPhoneNumberObj(this)" maxlength="14"/></td>
                    <!-- <script language="Javascript">
                        checaTelefone(document.forms[0].elements['tratarCorrespDevolvida.nrLinha'])
                    </script> -->
                    <td nowrap>Conta:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.nrConta" styleClass="textfield" size="18" readonly="true"/></td>
                </tr>
                <tr>
                    <td nowrap>Nome:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.nmCliente" styleClass="textfield" size="39" readonly="true"/></td>
                    <td nowrap><bean:write name="Form" property="tratarCorrespDevolvida.dsTipoDocumento"/>:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.nrDocumento" styleClass="textfield" size="18" readonly="true"/></td>
                    <script language="Javascript">
                    <logic:equal value="CPF" name="Form" property="tratarCorrespDevolvida.dsTipoDocumento">
                        checaCPF(document.forms[0].elements['tratarCorrespDevolvida.nrDocumento']);
                    </logic:equal>
                    <logic:equal value="CNPJ" name="Form" property="tratarCorrespDevolvida.dsTipoDocumento">
                        checaCNPJ(document.forms[0].elements['tratarCorrespDevolvida.nrDocumento']);
                    </logic:equal>
                    </script>
                </tr>
                <tr class="tblDinamica_linhapar">
                    <td nowrap>Endereço:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.dsEndereco" maxlength="255" styleClass="textfield" size="39" styleId="dsEndereco" readonly="true"/></td>
                    <td nowrap>Número:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.nrEndereco" maxlength="10" styleClass="textfield" size="18" styleId="nrEndereco" readonly="true"/></td>
                </tr>
                <tr>
                    <td nowrap>Complemento:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.dsComplemento" maxlength="255" styleClass="textfield" size="39"styleId="dsComp" readonly="true"/></td>
                    <td nowrap>Bairro:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.dsBairro" maxlength="255" styleClass="textfield" size="39" styleId="dsBairro" readonly="true"/></td>
                </tr>
                <tr class="tblDinamica_linhapar">
                    <td nowrap>Cidade:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.dsCidade" maxlength="255" styleClass="textfield" size="39" styleId="dsCidade" readonly="true"/></td>
                    <td nowrap>Estado (UF):</td>
                    <td style="padding-left:3px;"><html:select style="width:50px" name="Form" property="idUFSelecionado" styleId="dsUF" disabled="true">
                            <html:options collection="Ufs" property="idUF" labelProperty="sgUF"/>
                        </html:select>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CEP:
                        <html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.nrCEP" onkeyup="checaCEP(this)" maxlength="9" styleClass="textfield" size="18" styleId="nrCEP" readonly="true"/>
                        <html:hidden name="Form" property="idUFSelecionado" />
                    </td>
                    <script language="Javascript">
                        checaCEP(document.forms[0].elements['tratarCorrespDevolvida.enderecoBaseVO.nrCEP'])
                    </script>
                </tr>
                <tr>
                    <td nowrap>Data Telemensagem:</td>
                    <td><html:text size="12" name="Form" property="tratarCorrespDevolvida.dtTelemensagem" styleClass="textfield" readonly="true"/></td>
                    <td nowrap>País:</td>
                    <td style="padding-left:3px;"><html:select style="width:100px" name="Form" property="idPaisSelecionado" styleId="dsPais" disabled="true">
                            <html:options collection="Paises" property="idPais" labelProperty="nmPais"/>
                        </html:select>&nbsp;&nbsp;&nbsp;&nbsp;
                        <html:hidden  name="Form" property="idPaisSelecionado"/>
                        </td>
                </tr>                
                <tr class="tblDinamica_linhapar">                                        
                    <td nowrap>Data SMS:</td>
                    <td><html:text size="12" name="Form" property="tratarCorrespDevolvida.dtSMS" styleClass="textfield" readonly="true"/></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>                                 
                <tr>                    
                    <td width="80" height="25">Status:</td>
                    <td width="282" style="padding-left:3px;">
                        <html:select name="Form" property="idStatus" styleId="comboStatus">
                            <html:options collection="Status" property="idStatus" labelProperty="dsStatus"/>
                        </html:select>
                    </td>                    
                </tr>
            </table>
            <table width="100%">
                <tr>
                    <td align="left">
                        <img onclick="voltar(); return false;" style="border:0px;cursor:hand;" align="middle" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" border="0" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif';" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif';"/>
                    </td>
                    <td align="right">        
                    <acesso:controlHiddenItem nomeIdentificador="cli_tcda_salvar">                
                        <img onclick="efetuaAlteracoes(); return false;" style="border:0px;cursor:hand;" align="middle" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" border="0" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif';" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif';" id="btGravar"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="760" layoutHeight="180" tableWidth="760" styleId="tableTitle" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="80%" type="string">Status</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="20%" type="string">Data</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>
                    <logic:iterate name="Form" property="tratarCorrespDevolvida.listaStatusCorrespArray" id="listaStatusCorrespArray">
                        <vivo:tbRow key="linha1">
                            <vivo:tbRowColumn><bean:write name="listaStatusCorrespArray" property="dsStatus"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="listaStatusCorrespArray" property="dtStatus"/></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
                </vivo:tbRows>
            </vivo:tbTable>
        </form>
    </vivo:sessao>
    </td>
    </tr>
</table> 
</acesso:controlHiddenItem>
</netui-template:section>
</netui-template:template>