<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="trackingForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute value="FrontOffice - Atendimento" name="title"/>
<netui-template:setAttribute value="Atendimento" name="modulo"/>
<netui-template:section name="headerSection">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js" ></script>
    <script type="text/javascript" language="javascript">
        <!--
            pesquisar = function(){
                if(validaPesquisa()){
                    document.forms[0].submit();
                }
                return false;
            }

            gravar = function(){
                if(validaForm()){
                    document.forms[1].submit();
                }
                return false;
            }

            validaPesquisa = function(){
                if($F('psqIdSO')=='' && $F('psqSgUF')==''){
                    alert('Favor selecionar um sistema origem ou uma das UF');
                    return false;
                }
                return true;
            }

            validaForm = function(){
                if($F('qtFornecimento')==''){
                    alert('O campo Fornecimento é de preenchimento obrigatório.');
                    return false;
                }
                if($F('qtPicking')==''){
                    alert('O campo Picking é de preenchimento obrigatório.');
                    return false;
                }
                if($F('qtConfPicking')==''){
                    alert('O campo Confirmação Picking é de preenchimento obrigatório.');
                    return false;
                }
                if($F('qtFaturamento')==''){
                    alert('O campo Faturamento é de preenchimento obrigatório.');
                    return false;
                }
                if($F('qtRegSaida')==''){
                    alert('O campo Registro Saída é de preenchimento obrigatório.');
                    return false;
                }
                if($F('qtEntrega')==''){
                    alert('O campo Entrega é de preenchimento obrigatório.');
                    return false;
                }
                return true;
            }

            alterar = function(idSO, sgUF, qtFr, qtPk, qtCP, qtFt, qtRS, qtEt){
                $('idSO').value           = idSO;
                $('sgUF').value           = sgUF;
                $('qtFornecimento').value = qtFr;
                $('qtPicking').value      = qtPk;
                $('qtConfPicking').value  = qtCP;
                $('qtFaturamento').value  = qtFt;
                $('qtRegSaida').value     = qtRS;
                $('qtEntrega').value      = qtEt;
            }
        -->
    </script>
</netui-template:section>
<netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="body" height="470" width="790" label="Tracking" operacoes="Parametrização" scroll="N">
        <table width="100%">
            <tr>
                <td>
                    <fieldset id="psqPrevisao">
                        <legend>Pesquisa</legend>
                        <form method="post" action="pesquisar.do" id="trackingPsqForm" name="trackingPsqForm" style="margin:0px;" onsubmit="return false;">
                            <table width="100%" border="0">
                                <tr>
                                    <td align="left">Sistema Origem:</td>
                                    <td align="left">UF:</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <html:select styleId="psqIdSO" name="Form" property="trackingPEVO.filtro.idSO" style="width:150px;" styleClass="SELECT">
                                            <option value="">-- Selecione --</option>
                                            <html:optionsCollection name="Form" property="comboSO.itArray" value="id" label="ds"/>
                                        </html:select>
                                    </td>
                                    <td align="left">
                                        <html:select styleId="psqSgUF" name="Form" property="trackingPEVO.filtro.sgUF" style="width:150px;" styleClass="SELECT">
                                            <option value="">-- Selecione --</option>
                                            <html:optionsCollection name="Form" property="comboUF.itArray" value="id" label="id"/>
                                        </html:select>
                                    </td>
                                    <td align="right">
                                        <!--acesso:controlHiddenItem nomeIdentificador="fid_cmig_pesquisar"-->
                                            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" onclick="pesquisar();" style="cursor:pointer;margin-right:30px;">
                                        <!--/acesso:controlHiddenItem-->
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td>
                    <vivo:tbTable selectable="true" layoutWidth="750" layoutHeight="200" tableWidth="750" styleId="tableTracking" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="center" width="12%" type="string">Sistema Origem</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="11%" type="string">UF</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="11%" type="string">Fornecimento</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="11%" type="string">Picking</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="11%" type="string">Confirmação Picking</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="11%" type="string">Registro Saída</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="11%" type="string">Faturamento</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="11%" type="string">Entrega</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="11%" type="string">&nbsp;</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate id="it" name="Form" property="trackingPEVO.tabela.camposArray" indexId="idx">
                            <vivo:tbRow key="linhax1">
                                <vivo:tbRowColumn><bean:write name="it" property="nmSO"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="it" property="sgUF"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="it" property="qtFornecimento"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="it" property="qtPicking"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="it" property="qtConfPicking"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="it" property="qtRegSaida"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="it" property="qtFaturamento"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="it" property="qtEntrega"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <!--acesso:controlHiddenItem nomeIdentificador="fid_cmig_alterar"-->
                                        <img src="<%=request.getContextPath()%>/resources/images/bt_icon_alterar.gif" onclick="alterar('<bean:write name="it" property="idSO"/>','<bean:write name="it" property="sgUF"/>','<bean:write name="it" property="qtFornecimento"/>','<bean:write name="it" property="qtPicking"/>','<bean:write name="it" property="qtConfPicking"/>','<bean:write name="it" property="qtFaturamento"/>','<bean:write name="it" property="qtRegSaida"/>','<bean:write name="it" property="qtEntrega"/>');">
                                    <!--/acesso:controlHiddenItem-->
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>
                    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></div>
                </td>
            </tr>
            <tr>
                <td>
                    <fieldset id="manter">
                        <legend>Manutenção</legend>
                        <form method="post" action="gravar.do" id="trackingForm" name="trackingForm" style="margin:0px;" onsubmit="return false;">
                            <html:hidden styleId="idSO" name="Form" property="trackingPEVO.campos.idSO"/>
                            <html:hidden styleId="sgUF" name="Form" property="trackingPEVO.campos.sgUF"/>
                            <table width="100%" border="0">
                                <tr>
                                    <td>Fornecimento:</td>
                                    <td><html:text styleId="qtFornecimento" name="Form" property="trackingPEVO.campos.qtFornecimento" maxlength="2" onkeypress="checaInteiro(this);" onblur="checaInteiro(this);"/></td>
                                    <td>Picking:</td>
                                    <td><html:text styleId="qtPicking" name="Form" property="trackingPEVO.campos.qtPicking" maxlength="2" onkeypress="checaInteiro(this);" onblur="checaInteiro(this);"/></td>
                                    <td>Confirmação Picking:</td>
                                    <td><html:text styleId="qtConfPicking" name="Form" property="trackingPEVO.campos.qtConfPicking" maxlength="2" onkeypress="checaInteiro(this);" onblur="checaInteiro(this);"/></td>
                                </tr>
                                <tr>
                                    <td>Registro Saída:</td>
                                    <td><html:text styleId="qtRegSaida" name="Form" property="trackingPEVO.campos.qtRegSaida" maxlength="2" onkeypress="checaInteiro(this);" onblur="checaInteiro(this);"/></td>
                                    <td>Faturamento:</td>
                                    <td><html:text styleId="qtFaturamento" name="Form" property="trackingPEVO.campos.qtFaturamento" maxlength="2" onkeypress="checaInteiro(this);" onblur="checaInteiro(this);"/></td>
                                    <td>Entrega:</td>
                                    <td><html:text styleId="qtEntrega" name="Form" property="trackingPEVO.campos.qtEntrega" maxlength="2" onkeypress="checaInteiro(this);" onblur="checaInteiro(this);"/></td>
                                </tr>
                            </table>
                        </form>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td colspan="6" align="right">
                    <img src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" onclick="gravar();" style="cursor:pointer;margin-right:30px;">
                </td>
            </tr>
        </table>
    </vivo:sessao>
</netui-template:section>
</netui-template:template>