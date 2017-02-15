<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/CRMTemplateSemCabec.jsp">
<netui-template:setAttribute value="Correspondência Devolvida" name="title"></netui-template:setAttribute>
<netui-template:setAttribute value="Gestão de Clientes" name="modulo"></netui-template:setAttribute>
<netui-template:section name="headerSection">
    <link href="/FrontOfficeWeb/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
</netui-template:section>
<netui-template:section name="bodySection">
<acesso:controlHiddenItem nomeIdentificador="cli_tcti_verpagina">

<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        if(document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="ABERTO"    &&
            document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="ABERTA"    &&
            document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="DESLIGADO" &&
            document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="DESLIGADA" &&
            document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="RETIRADO"  &&
            document.getElementById('comboStatus').options[document.getElementById('comboStatus').selectedIndex].text.toUpperCase()!="RETIRADA"){
            document.getElementById('comboStatus').disabled=true;
            document.getElementById('btAlterarEndereco').style.display='none';
            if(document.getElementById('btGravar')){
                document.getElementById('btGravar').style.display='none';
            }
        }
        parent.oculta_div();
    -->
</SCRIPT>

<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tratarForm"/>
<bean:define id="Status"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tratarForm.tratarCorrespDevolvida.listaStatusArray"/>
<bean:define id="StatusCorresp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tratarForm.tratarCorrespDevolvida.listaStatusCorrespArray"/>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<script>
        function efetuaAlteracoes()
        {                                                                                      
            if (document.forms[0].idStatus.value == document.forms[0].statusAtual.value)
                alert ('É necessário alterar o status para registrar as alterações.');                
            else{
                document.forms[0].action="salvaDadosTratar.do";                
                //parent.dvAtendCorresp.style.display = 'none';                
                parent.mostrar_div();
                document.forms[0].submit();
            }            
        }        
        
        function tratarEndereco()
        {
            dvTratarEnd.style.display = '';            
            document.forms[0].target = "ifrmTratarEnd";            
            document.forms[0].action="/FrontOfficeWeb/cliente/TelaInicial/DetalheCliente/loadEndereco.do?reload=ok&idPessoaCliente="+document.getElementById("idPessoa").value;
            parent.mostrar_div();
            document.forms[0].submit();
        }        
            
    </script>
    <html:form action="/cliente/CDevolvida/telaInicial/salvaDadosTratar.do">
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:sessao id="correpDevolvida" width="751" height="510" label="Correspondência Devolvida" operacoes="Manutenção">
            <table width="741" height="240" class="tblDinamica" border="0" cellspacing="0" cellpadding="0">
                <tr class="tblDinamica_linhapar">
                    <html:hidden name="Form" property="tratarCorrespDevolvida.idCorrespondenciaDevolvida"/>
                    <html:hidden name="Form" property="idStatus"/>
                    <html:hidden name="Form" property="idPessoa" styleId="idPessoa"/>
                    <td width="130">Tipo Correspondência:</td>
                    <td width="240"><html:text name= "Form" property="tratarCorrespDevolvida.dsTipoCorrespondencia" styleClass="textfield" size="39" readonly="true"/></td>
                    <td width="100"></td>
                    <td width="271"></td>
                </tr>
                <tr>
                    <td>&nbsp;Motivo de Devolução:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.dsMotivoDevolucao" styleClass="textfield" size="39" readonly="true"/></td>
                    <td>&nbsp;Data Devolução</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.dtDevolucao" styleClass="textfield" size="18" readonly="true"/></td>
                </tr>
                <tr class="tblDinamica_linhapar">
                    <td>Linha:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.nrLinha" styleClass="textfield" size="15" readonly="true" onkeyup="maskPhoneNumberObj(this)" maxlength="14"/></td>
                    <!-- <script language="Javascript">
                        checaTelefone(document.forms[0].elements['tratarCorrespDevolvida.nrLinha'])
                    </script> -->
                    <td>Conta:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.nrConta" styleClass="textfield" size="18" readonly="true"/></td>
                </tr>
                <tr>
                    <td>&nbsp;Nome:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.nmCliente" styleClass="textfield" size="39" readonly="true"/></td>
                    <td>&nbsp;<bean:write name="Form" property="tratarCorrespDevolvida.dsTipoDocumento"/>:</td>
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
                    <td>Endereço:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.dsEndereco" styleClass="textfield" size="39" styleId="dsEndereco" readonly="true"/></td>
                    <td>Número:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.nrEndereco" styleClass="textfield" size="18" readonly="true"/></td>
                </tr>
                <tr>
                    <td>&nbsp;Complemento:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.dsComplemento" styleClass="textfield" size="39" readonly="true"/></td>
                    <td>&nbsp;Bairro:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.dsBairro" styleClass="textfield" size="39" readonly="true"/></td>
                </tr>
                <tr class="tblDinamica_linhapar">
                    <td>Cidade:</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.dsCidade" styleClass="textfield" size="39" readonly="true"/></td>
                    <td>Estado (UF):</td>
                    <td><html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.UFVO.sgUF" styleClass="textfield" size="6" readonly="true"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CEP:
                        <html:text name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.nrCEP" styleClass="textfield" size="18" readonly="true"/>
                    </td>
                    <script language="Javascript">
                           checaCEP(document.forms[0].elements['tratarCorrespDevolvida.enderecoBaseVO.nrCEP'])
                    </script>
                </tr>
                <tr>
                    <td>&nbsp;Data Telemensagem:</td>
                    <td><html:text style="width:60px;" name="Form" property="tratarCorrespDevolvida.dtTelemensagem" styleClass="textfield" readonly="true"/></td>
                    <td>&nbsp;País:</td>
                    <td><html:text size="12" name="Form" property="tratarCorrespDevolvida.enderecoBaseVO.paisVO.nmPais" styleClass="textfield" readonly="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="/FrontOfficeWeb/resources/images/bt_altendereco_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_altendereco_over.gif" style="border:none;" hspace="5" onClick="tratarEndereco(); return false" id="btAlterarEndereco"/>&nbsp</td>
                </tr>                
                <tr class="tblDinamica_linhapar">                                        
                    <td>Data SMS:</td>
                    <td><html:text style="width:60px;" name="Form" property="tratarCorrespDevolvida.dtSMS" styleClass="textfield" readonly="true"/></td>
                    <td>&nbsp;</td>
                    <td align="center">&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;Status:</td>
                    <td colspan="3">
                        <html:select name="Form" property="statusAtual" style="width:225px;text-indent:3px;" styleId="comboStatus">
                            <html:options collection="Status" property="idStatus" labelProperty="dsStatus"/>
                        </html:select>
                    </td>                    
                </tr>
            </table>
            <table width="741">
                <tr>
                    <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cli_tcti_alterar">
                        <img src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;" hspace="5" onClick="efetuaAlteracoes(); return false" id="btGravar"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>                
            </table>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
            <vivo:tbTable selectable="true" layoutWidth="725" layoutHeight="150" tableWidth="725" styleId="tableTitle" sortable="true">
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
        
        </vivo:sessao>
        </html:form>

    <!--Form e Quadro Flutuante-->    
    <vivo:quadroFlutuante id="dvTratarEnd" idIframe="ifrmTratarEnd" width="758" height="245" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Lista de Endereços" />    
</acesso:controlHiddenItem>
</netui-template:section>
</netui-template:template>