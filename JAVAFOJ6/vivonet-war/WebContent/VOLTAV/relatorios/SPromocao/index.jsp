<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Super Promoção"/>
    <netui-template:setAttribute name="modulo" value="Relatórios"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript" type="text/javascript">
            function validaForm(){
                if($F('dtAtivacao').strip().empty()){
                    alert("Favor informar a Data de Ativação");
                    return false;
                }
                return true;
            }

            function pesquisar(){
                if(validaForm()){
                    if (self.$('dvAnimarAguarde')) self.startAnimation();
                    var params = $H();
                    params.set('dtAtivacao', $F('dtAtivacao'));
                    params.set('idRegional', $F('idRegional'));
                    params.set('idTpLinha', $F('idTpLinha'));

                    $('dtAtivExp').value = $F('dtAtivacao');
                    $('idRegExp').value = $F('idRegional');
                    $('idTipLin').value = $F('idTpLinha');

                    new Ajax.Updater('dataTable', 'pesquisar.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onSuccess: function() {
                            if (self.$('dvAnimarAguarde')) self.stopAnimation();

                        }, onFailure: function(e) {
                            if (self.$('dvAnimarAguarde')) self.stopAnimation();
                            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                            top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                        }
                    });
                }
            }

            function limpar(){
                self.location.href = 'begin.do';
            }

            function exportar(){
                var f = document.forms[0];
                if(validaForm()){
                    if($F('dtAtivExp')!=""){
                        $('dtAtivacao').value = $F('dtAtivExp');
                        $('idRegional').value = $F('idRegExp');
                        $('idTpLinha').value = $F('idTipLin');
                        f.action = "exportar.do";
                        f.target = "frmDown";
                        f.submit();
                    }else{
                        alert("Favor realizar a Pesquisa.");
                    }
                }
            }
        </script>
        <script language="javascript" for="window" event="onload">
            <logic:notEmpty name="msgStatus" scope="request">
            alert('<bean:write name="msgStatus" scope="request"/>');
            </logic:notEmpty>
            oculta_div();
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="460" width="790" label="Relatório" operacoes="Torpedo Premiado">
            <form  name="formRel"  action="pesquisar.do" enctype="multipart/form-data" method="post" onsubmit="return false;">
            <input type="hidden" name="dtAtivExp" value=""/>
            <input type="hidden" name="idRegExp" value=""/>
            <input type="hidden" name="idTipLin" value=""/>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
            <fieldset>
                <table cellpadding="3" cellspacing="2" width="100%">
                    <tr>
                        <td align="right" width="25%">*&nbsp;Data de Ativação:</td>
                        <td align="left" width="25%">
                            <html:text styleId="dtAtivacao" name="Form" property="dtAtivacao" size="11" maxlength="10" onblur="validaData(this);" onkeyup="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>&nbsp;
                            <img src="/FrontOfficeWeb/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtAtivacao', '%d/%m/%Y');">&nbsp;
                        </td>
                        <td align="right" width="10%">&nbsp;Regional:</td>
                        <td align="left" width="25%">
                            <html:select name="Form" property="idRegional" styleId="idRegional" style="width:150px;" styleClass="SELECT">
                                <option value="" selected>-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaRegional.itArray" value="id" label="ds"/>
                            </html:select>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right" width="25%">&nbsp;Tipo de Linha:</td>
                        <td align="left" width="25%">
                            <html:select name="Form" property="idTpLinha" styleId="idTpLinha" style="width:150px;" styleClass="SELECT">
                                <option value="" selected>-- Selecione --</option>
                                <html:optionsCollection name="Form" property="listaTpLinhas.itArray" value="id" label="ds"/>
                            </html:select>
                        </td>
                        <td align="right" width="10%"></td>
                        <td align="left" width="25%"></td>
                        <td><img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onclick="pesquisar();" style="cursor:pointer;"></td>
                        <td><img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onclick="limpar();" style="cursor:pointer;"></td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </fieldset>
            <div id="dataTable" style="with:740;height:300px;margin-top:10px;">
                <vivo:tbTable selectable="true" styleId="tbRelBlindagem" layoutHeight="300" layoutWidth="740" tableWidth="740">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn width="25%" type="String">Data Ativação</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="25%" type="String">Regional</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="25%" type="String">Tipo de Linha</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="25%" type="String">Total Ativações c/ Sucesso</vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows>
                        <vivo:tbRow key="linha1">
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </vivo:tbRows>
                </vivo:tbTable>
            </div>
            <table cellpadding="3" cellspacing="2" width="100%">
                <tr>
                    <td align="left"><img src="/FrontOfficeWeb/resources/images/bt_exportar_nrml.gif" onclick="exportar();" style="border:none;cursor:pointer;"></td>
                </tr>
            </table>
            </form>
        </vivo:sessao>
        <iframe id="frmDown" name="frmDown" src="about:blank" width="1" height="1" style="display:none"></iframe>
    </netui-template:section>
</netui-template:template>