<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="scriptForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="javascript">
            loadScript = function(){
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                var f = document.forms[0];
                f.action = 'alterarBegin.do';
                f.submit();
            }

            validaForm = function(){
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
                if(f.idSelDestinos.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Destinos');
                    return false;
                }
                if(f.idSelDestinos.options.length>20){
                    alert('Selecione apenas 20 (vinte) Destinos possíveis.');
                    return false;
                }
                return true;
            }

            gravar = function(){
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
                    for(j = 0; j<f.idSelDestinos.options.length; j++) {
                        f.idSelDestinos.options[j].selected = true;
                    }
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    f.action = "alterar.do";
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

            numberElements = function(obj){
                $('nrElementos').innerText = obj.length;
            }
            <logic:notEmpty name="msgError" scope="request">
            alert('<bean:write name="msgError" scope="request"/>');
            </logic:notEmpty>
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                numberElements($('idSelDestinos'));
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_ms_verpagina">
    <form method="post" action="" id="scriptForm" name="scriptForm" enctype="multipart/form-data" style="margin:0px;" onsubmit="return false;">
    <html:hidden name="Form" property="idScript"/>
    <table width="95%" align="center" cellpadding="2" cellspacing="2">
        <tr>
            <td colspan="2">Intenções possíveis&nbsp;&nbsp;
                <html:select name="Form" property="idIntencao" style="width:480px;" styleClass="SELECT" onchange="loadScript(this);">
                    <option value="">-- Selecione --</option>
                    <html:optionsCollection name="Form" property="listaIntencao.itArray" value="id" label="ds"/>
                </html:select>
            </td>
        </tr>
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
                <table width="100%" align="left">
                    <tr>
                        <td align="center">Destinos previstos Disponíveis</td>
                        <td>&nbsp;</td>
                        <td align="center">Destinos previstos Selecionados (<span id="nrElementos"></span>)</td>
                    </tr>
                    <tr>
                        <td width="43%" align="center">
                            <select name="idDispDestinos" id="idDispDestinos" style="width:350px;" size="8" class="SELECT" multiple>
                                <logic:iterate id="it" name="Form" property="listaDestinos.itArray" indexId="idx">
                                    <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                </logic:iterate>
                            </select>
                        </td>
                        <td width="14%" align="center">
                            <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispDestinos'),$('idSelDestinos'),false);numberElements($('idSelDestinos'));">
                            <br><br>
                            <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispDestinos'),$('idSelDestinos'),true);numberElements($('idSelDestinos'));">
                        </td>
                        <td width="43%" align="center">
                            <select name="idSelDestinos" id="idSelDestinos" style="width:350px;" size="8" class="SELECT" multiple onchange="numberElements(this);">
                                <logic:iterate id="it" name="Form" property="listaSelDestinos.itArray" indexId="idx">
                                    <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                </logic:iterate>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <div style="float:right;margin-top:3px;">
        <acesso:controlHiddenItem nomeIdentificador="fid_ms_gravar">
            <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onclick="gravar();" style="cursor:pointer;"/>
        </acesso:controlHiddenItem>
    </div>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
    </form>
</acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
