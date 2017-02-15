<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="bonusForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript">
            function validaForm(){
                var f = document.forms[0];
                if(f.idSelRegional.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Regional');
                    return false;
                }
                if(f.idSelSegmentacao.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Segmentação');
                    return false;
                }
                if(f.idTpLinha.value==''){
                    alert('O campo Tipo de Linha é de preenchimento obrigatório');
                    return false;
                }
                if(f.idGrpPacote.value==''){
                    alert('O campo Grupo de Pacote é de preenchimento obrigatório');
                    return false;
                }
                if(f.nmBonus.value==''){
                    alert('O campo Nome do Bônus é de preenchimento obrigatório');
                    return false;
                }
                if(f.vdBonus.value==''){
                    alert('O campo Validade de Pacote é de preenchimento obrigatório');
                    return false;
                }
                if(f.cdServico.value==''){
                    alert('O campo Código do Pacote é de preenchimento obrigatório');
                    return false;
                }

                if(document.getElementById('trTpSrvc').style.display!='none'){
                    if(f.idTpServico.value==''){
                        alert('O campo Tipo de Servico é de preenchimento obrigatório');
                        return false;
                    }
                }
                if(f.vlBonus.value==''){
                    alert('O campo Tarifa é de preenchimento obrigatório');
                    return false;
                }
                return true;
            }

            function gravar(){
                if(validaForm()){
                    var f = document.forms[0];
                    f.vlBonus.value = f.vlBonus.value.replace(",",".")
                    for(j = 0; j<f.idSelRegional.options.length; j++) {
                        f.idSelRegional.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelSegmentacao.options.length; j++) {
                        f.idSelSegmentacao.options[j].selected = true;
                    }
                    if(f.idBonus.value==''){
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
            parent.fecharBonus(true);
            </logic:equal>
            </logic:notEmpty>
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <form method="post" action="" id="formBonus" name="formBonus" enctype="multipart/form-data" style="margin:0px;">
        <html:hidden name="Form" property="idBonus"/>
        <html:hidden name="Form" property="tpOperacao"/>
        <table width="95%" align="center" cellpadding="2" cellspacing="2">
            <tr>
                <td colspan="2">
                    Canal de Retenção: <b>URA</b>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table width="100%">
                        <tr>
                            <td align="center">Regionais Disponíveis</td>
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
                            <td align="center">Segmentações Disponíveis</td>
                            <td>&nbsp;</td>
                            <td align="center">Segmentações Selecionadas</td>
                        </tr>
                        <tr>
                            <td width="43%" align="center">
                                <select name="idDispSegmentacao" id="idDispSegmentacao" style="width:225px;" size="6" class="SELECT" multiple>
                                    <logic:iterate id="it" name="Form" property="listaSegmentacao.itArray" indexId="idx">
                                        <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                    </logic:iterate>
                                </select>
                            </td>
                            <td width="14%" align="center">
                                <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispSegmentacao'),$('idSelSegmentacao'),false);">
                                <br><br>
                                <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispSegmentacao'),$('idSelSegmentacao'),true);">
                            </td>
                            <td width="43%" align="center">
                                <select name="idSelSegmentacao" id="idSelSegmentacao" style="width:225px;" size="6" class="SELECT" multiple>
                                    <logic:iterate id="it" name="Form" property="listaSelSegmentacao.itArray" indexId="idx">
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
                            <td>Tipo de Linha:</td>
                            <td>
                                <html:select name="Form" property="idTpLinha" style="width:150px;" styleClass="SELECT" onchange="selTpServico(this);">
                                    <option value="">-- Selecione --</option>
                                    <html:optionsCollection name="Form" property="listaLinhas.itArray" value="id" label="ds"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Grupo de Pacote:</td>
                            <td>
                                <html:select name="Form" property="idGrpPacote" style="width:150px;" styleClass="SELECT">
                                    <option value="">-- Selecione --</option>
                                    <html:optionsCollection name="Form" property="listaGrpPacote.itArray" value="id" label="ds"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Nome do Bônus</td>
                            <td><html:text name="Form" property="nmBonus" style="width:300px;" maxlength="255"/></td>
                        </tr>
                        <tr>
                            <td>Validade de Pacote:</td>
                            <td>
                                <html:select name="Form" property="vdBonus" style="width:150px;">
                                    <option value="">-- Selecione --</option>
                                    <option value="1" <logic:equal name="Form" property="vdBonus" value="1">selected</logic:equal> >05 Dias</option>
                                    <option value="2" <logic:equal name="Form" property="vdBonus" value="2">selected</logic:equal> >15 Dias</option>
                                    <option value="3" <logic:equal name="Form" property="vdBonus" value="3">selected</logic:equal> >30 Dias</option>
                                    <option value="4" <logic:equal name="Form" property="vdBonus" value="4">selected</logic:equal> >45 Dias</option>
                                    <option value="5" <logic:equal name="Form" property="vdBonus" value="5">selected</logic:equal> >60 Dias</option>
                                    <option value="6" <logic:equal name="Form" property="vdBonus" value="6">selected</logic:equal> >Sem Validade</option>
                                </html:select>
                            </td>
                        </tr>
                        <tr id="trTpSrvc" style="display:none;">
                            <td>Tipo de Serviço:</td>
                            <td>
                                <html:select name="Form" property="idTpServico" style="width:150px;" styleClass="SELECT">
                                    <option value="">-- Selecione --</option>
                                    <html:optionsCollection name="Form" property="listaServicos.itArray" value="id" label="ds"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Código do Pacote:</td>
                            <td><html:text name="Form" property="cdServico" style="width:150px;" maxlength="60"/></td>
                        </tr>
                        <tr>
                            <td>Tarifa:</td>
                            <td><html:text name="Form" property="vlBonus" style="width:150px;" maxlength="18" onkeypress="checaReal(this);"/></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <img src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif" style="cursor:pointer;" onclick="parent.fecharBonus(false);"/>
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