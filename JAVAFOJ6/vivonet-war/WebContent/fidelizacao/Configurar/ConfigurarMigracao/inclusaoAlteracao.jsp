<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="migracaoForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/Formatacao.js"></script>
        <script type="text/javascript" language="javascript">
            function validaForm(){
                var f = document.forms[0];
                if(f.idSelRegional.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Regional');
                    return false;
                }
                if(f.idSelTpCliente.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Tipo de Cliente');
                    return false;
                }
                if(f.idSelTpLinha.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Tipo de Linha');
                    return false;
                }
                if(f.dsMigra.value==''){
                    alert('O campo Descrição é de preenchimento obrigatório');
                    return false;
                }
                if(f.vlBonus.value=='' || f.vlBonus.value=='0,00'){
                    alert('O campo Bônus (em Reais) é de preenchimento obrigatório');
                    return false;
                }
                if(f.vdMigra.value==''){
                    alert('O campo Validade do Crédito (em dias) é de preenchimento obrigatório');
                    return false;
                }
                return true;
            }

            function gravar(){
                if(validaForm()){
                    var f = document.forms[0];
                    for(j = 0; j<f.idSelRegional.options.length; j++) {
                        f.idSelRegional.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelTpCliente.options.length; j++) {
                        f.idSelTpCliente.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelTpLinha.options.length; j++) {
                        f.idSelTpLinha.options[j].selected = true;
                    }
                    if(f.idMigracao.value==''){
                        f.action = "incluir.do";
                    }else{
                        f.action = "alterar.do";
                    }
                    f.submit();
                }
            }

            transfereSelecaoLista = function(listaOrigem, listaDestino, inverse, flAll){
                var i;
                if (inverse) {
                    for(i = 0; i<listaDestino.options.length; i++) {
                        if (flAll) listaDestino.options[i].selected = true;
                            if(listaDestino.options[i].selected)
                                listaOrigem.options[listaOrigem.options.length] = new Option(listaDestino.options[i].text, listaDestino.options[i].value);
                    }
                    for(i = listaDestino.options.length-1; i>=0; i--)
                        if(listaDestino.options[i].selected)
                            listaDestino.options[i] = null;
                } else {
                    for(i = 0; i<listaOrigem.options.length; i++) {
                        if (flAll) listaOrigem.options[i].selected = true;
                        if(listaOrigem.options[i].selected)
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
                    for(i = listaOrigem.options.length-1; i>=0; i--)
                        if(listaOrigem.options[i].selected)
                            listaOrigem.options[i] = null;
                }
            }

            <logic:notEmpty name="msgError" scope="request">
            alert('<bean:write name="msgError" scope="request"/>');
            </logic:notEmpty>
            <logic:notEmpty name="status" scope="request">
            <logic:equal name="status" scope="request" value="OK">
            parent.fecharMigra(true);
            </logic:equal>
            </logic:notEmpty>
        </script>
        <script for="window" event="onload" language="jscript">
            <!--
                //document.forms[0].vlBonus = "0,00";
            -->
        </script>
    </netui-template:section>
<netui-template:section name="bodySection">
    <form method="post" action="" id="formMigra" name="formMigra" enctype="multipart/form-data" style="margin:0px;">
    <html:hidden name="Form" property="idMigracao"/>
    <table width="95%" align="center" cellpadding="2" cellspacing="2">
        <tr>
            <td>
                <table width="100%">
                    <tr>
                        <td align="center">Regionais Disponíveis</td>
                        <td>&nbsp;</td>
                        <td align="center">Regionais Selecionadas</td>
                    </tr>
                    <tr>
                        <td width="43%" align="center">
                            <select name="idDispRegional" id="idDispRegional" style="width:150px;" size="6" class="SELECT" multiple>
                                <logic:iterate id="it" name="Form" property="listaRegional.itArray" indexId="idx">
                                    <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                </logic:iterate>
                            </select>
                        </td>
                        <td width="14%" align="center">
                            <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispRegional'),$('idSelRegional'),false);">
                            <br><br>
                            <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispRegional'),$('idSelRegional'),true);">
                        </td>
                        <td width="43%" align="center">
                            <select name="idSelRegional" id="idSelRegional" style="width:150px;" size="6" class="SELECT" multiple>
                                <logic:iterate id="it" name="Form" property="listaSelRegional.itArray" indexId="idx">
                                    <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                </logic:iterate>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
            <td>
                <table width="100%">
                    <tr>
                        <td align="center">Tipos de Clientes Disponíveis</td>
                        <td>&nbsp;</td>
                        <td align="center">Tipos de Clientes Selecionadas</td>
                    </tr>
                    <tr>
                        <td width="43%" align="center">
                            <select name="idDispTpCliente" id="idDispTpCliente" style="width:150px;" size="6" class="SELECT" multiple>
                                <logic:iterate id="it" name="Form" property="listaClientes.itArray" indexId="idx">
                                    <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                </logic:iterate>
                            </select>
                        </td>
                        <td width="14%" align="center">
                            <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpCliente'),$('idSelTpCliente'),false);">
                            <br><br>
                            <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpCliente'),$('idSelTpCliente'),true);">
                        </td>
                        <td width="43%" align="center">
                            <select name="idSelTpCliente" id="idSelTpCliente" style="width:150px;" size="6" class="SELECT" multiple>
                                <logic:iterate id="it" name="Form" property="listaSelClientes.itArray" indexId="idx">
                                    <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                </logic:iterate>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table width="100%">
                    <tr>
                        <td align="center">Tipo de Linha Disponíveis</td>
                        <td>&nbsp;</td>
                        <td align="center">Tipo de Linha Selecionadas</td>
                    </tr>
                    <tr>
                        <td width="43%" align="center">
                            <select name="idDispTpLinha" id="idDispTpLinha" style="width:150px;" size="6" class="SELECT" multiple>
                                <logic:iterate id="it" name="Form" property="listaLinhas.itArray" indexId="idx">
                                    <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                </logic:iterate>
                            </select>
                        </td>
                        <td width="14%" align="center">
                            <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpLinha'),$('idSelTpLinha'),false);">
                            <br><br>
                            <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpLinha'),$('idSelTpLinha'),true);">
                        </td>
                        <td width="43%" align="center">
                            <select name="idSelTpLinha" id="idSelTpLinha" style="width:150px;" size="6" class="SELECT" multiple>
                                <logic:iterate id="it" name="Form" property="listaSelLinhas.itArray" indexId="idx">
                                    <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                </logic:iterate>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
            <td>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <table>
                    <tr>
                        <td>Descrição</td>
                        <td><html:text name="Form" property="dsMigra" style="width:308px;" maxlength="255"/></td>
                    </tr>
                    <tr>
                        <td>Bônus (em Reais)</td>
                        <td><html:text name="Form" property="vlBonus" style="width:100px;text-align:right;" maxlength="8" onkeypress="return exibirValorFormatado(event);" onkeydown="return capturaCodTecla(event);"/></td>
                    </tr>
                    <tr>
                        <td>Validade do Crédito (em dias)</td>
                        <td><html:text name="Form" property="vdMigra" style="width:100px;text-align:right;" maxlength="3" onkeypress="this.value=Format(this.value,'###');" onblur="this.value=Format(this.value,'###');"/></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <img src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif" style="cursor:pointer;" onclick="parent.fecharMigra(false);"/>
            </td>
            <td align="right">
                <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" style="cursor:pointer;" onclick="gravar();"/>
            </td>
        </tr>
    </table>
    </form>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
</netui-template:section>
</netui-template:template>