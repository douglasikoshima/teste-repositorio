<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="planosForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" language="javascript">
            function validaForm(){
                var f = document.forms[0];
                if(f.idSelRegional.options.length<1){
                    alert('� necess�rio a sele��o de ao menos um(a) valor de Regional');
                    return false;
                }
                if(f.idSelTpCliente.options.length<1){
                    alert('� necess�rio a sele��o de ao menos um(a) valor de Tipo de Cliente');
                    return false;
                }
                if(f.idTpLinha.value==''){
                    alert('O campo Tipo de Linha � de preenchimento obrigat�rio');
                    return false;
                }
                if(f.dsPlano.value==''){
                    alert('O campo Plano � de preenchimento obrigat�rio');
                    return false;
                }
                if(document.getElementById('trTpSrvc').style.display!='none'){
                    if(f.idTpServico.value==''){
                        alert('O campo Tipo de Servico � de preenchimento obrigat�rio');
                        return false;
                    }
                }
                if(f.cdServico.value==''){
                    alert('O campo C�digo do Servi�o � de preenchimento obrigat�rio');
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
                    if(f.idPlano.value==''){
                        f.action = "incluir.do";
                    }else{
                        f.action = "alterar.do";
                    }
                    f.submit();
                }
            }

            function selTpServico(obj){
                if(obj.value!='2' && obj.value!='6' && obj.value!='' && obj.value!='3'){
                    document.getElementById('trTpSrvc').style.display = 'block';
                }else{
                    document.getElementById('trTpSrvc').style.display = 'none';
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
            parent.fecharPlanos(true);
            </logic:equal>
            </logic:notEmpty>
        </script>
    </netui-template:section>
<netui-template:section name="bodySection">
    <form method="post" action="" id="formPlanos" name="formPlanos" enctype="multipart/form-data" style="margin:0px;">
    <html:hidden name="Form" property="idPlano"/>
    <table width="95%" align="center">
        <tr>
            <td colspan="2">
                <table width="100%">
                    <tr>
                        <td align="center">Regionais Dispon�veis</td>
                        <td>&nbsp;</td>
                        <td align="center">Regionais Selecionadas</td>
                    </tr>
                    <tr>
                        <td width="43%" align="center">
                            <select name="idDispRegional" id="idDispRegional" style="width:225px;" size="6" class="SELECT" multiple>
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
                            <select name="idSelRegional" id="idSelRegional" style="width:225px;" size="6" class="SELECT" multiple>
                                <logic:iterate id="it" name="Form" property="listaSelRegional.itArray" indexId="idx">
                                    <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                </logic:iterate>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <table width="100%">
                    <tr>
                        <td align="center">Tipos de Clientes Dispon�veis</td>
                        <td>&nbsp;</td>
                        <td align="center">Tipos de Clientes Selecionadas</td>
                    </tr>
                    <tr>
                        <td width="43%" align="center">
                            <select name="idDispTpCliente" id="idDispTpCliente" style="width:225px;" size="6" class="SELECT" multiple>
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
                            <select name="idSelTpCliente" id="idSelTpCliente" style="width:225px;" size="6" class="SELECT" multiple>
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
            <td colspan="2">
                <table>
                    <tr>
                        <td>Tipo de Linha</td>
                        <td>
                            <html:select name="Form" property="idTpLinha" style="width:150px;" styleClass="SELECT" onchange="selTpServico(this);">
                                <option value="">-- Selecione --</option>
                                <html:optionsCollection name="Form" property="listaLinhas.itArray" value="id" label="ds"/>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Plano</td>
                        <td><html:text name="Form" property="dsPlano" style="width:308px;" maxlength="255"/></td>
                    </tr>
                    <tr id="trTpSrvc" style="display:none;">
                        <td>Tipo de Servi�o</td>
                        <td>
                            <html:select name="Form" property="idTpServico" style="width:150px;" styleClass="SELECT">
                                <option value="">-- Selecione --</option>
                                <html:optionsCollection name="Form" property="listaServicos.itArray" value="id" label="ds"/>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td>C�digo do Servi�o</td>
                        <td><html:text name="Form" property="cdServico" style="width:158px;" maxlength="60"/></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <img src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif" style="cursor:pointer;" onclick="parent.fecharPlanos(false);"/>
            </td>
            <td align="right">
                <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" style="cursor:pointer;" onclick="gravar();"/>
            </td>
        </tr>
    </table>
    </form>
    <script>
        document.body.style.backgroundColor = '#ededed';
        selTpServico($('idTpLinha'));
    </script>
</netui-template:section>
</netui-template:template>