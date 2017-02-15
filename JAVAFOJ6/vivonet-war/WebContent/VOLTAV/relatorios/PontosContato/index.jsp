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
    <netui-template:setAttribute name="title" value="Pontos de Contato"/>
    <netui-template:setAttribute name="modulo" value="Relatórios"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript" type="text/javascript">
            function validaForm(){
                if($F('cdCnpjEmpresa').strip().empty()){
                    alert("Favor informar o CNPJ.");
                    return false;
                } else if(!($F('cdCnpjEmpresa').strip().empty()) && !validaCNPJ($F('cdCnpjEmpresa'))){
                    alert("Por favor, digite um CNPJ válido.");
                    return false;
                }
                return true;
            }

            function pesquisar(){
                if(validaForm()){
                    if (self.$('dvAnimarAguarde')) self.startAnimation();
                    var params = $H();
                    var cdCnpjEmpresa = limpa_cnpj($F('cdCnpjEmpresa'));
                    params.set('cdCnpjEmpresa', cdCnpjEmpresa);
                    new Ajax.Updater('dataTable', 'pesquisar.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onSuccess: function() {
                            if (self.$('dvAnimarAguarde')) self.stopAnimation();
                            setTimeout('desabilitarExport()', 600);
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
                var isExportFilter = $F('isExportFilter');
                if ((!isExportFilter) || (isExportFilter && validaForm())) {
                    f.action = "exportar.do";
                    f.target = "frmDown";
                    f.submit();
                }
            }

            function desabilitarExport() {
                if ($('divExportFilter') && $('linha1') == null) {
                    $('isExportFilter').style.display = 'none';
                    $('isExportFilter').checked = false;
                    $('divExportFilter').style.display = 'none';
                } else if ($('divExportFilter')){
                    $('isExportFilter').style.display = 'block';
                    $('divExportFilter').style.display = 'block';
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
        <vivo:sessao id="qdMain" height="460" width="790" label="Relatório" operacoes="Pontos de Contato">
            <html:form styleId="formRel" action="/VOLTAV/relatorios/PontosContato/pesquisar.do" enctype="multipart/form-data" method="post" onsubmit="return false;">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
            <fieldset>
                <table cellpadding="3" cellspacing="2" width="100%" border="0">
                    <tr>
                        <td align="right" width="25%">*&nbsp;CNPJ:</td>
                        <td align="left" width="25%">
                            <html:text styleId="cdCnpjEmpresa" name="Form" property="cdCnpjEmpresa" size="18" maxlength="18" onblur="checaCNPJ(this)" onkeyup="checaCNPJ(this);" />&nbsp;
                        </td>
                        <td width="25%" align="right"><img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onclick="pesquisar();" style="cursor:pointer;"></td>
                         <td>&nbsp;</td>
                        <td width="25%" align="left"><img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onclick="limpar();" style="cursor:pointer;"></td>
                    </tr>
                </table>
            </fieldset>
            <div id="dataTable" style="with:740;height:300px;margin-top:10px;">
                <vivo:tbTable selectable="true" styleId="tbRelBlindagem" layoutHeight="300" layoutWidth="750" tableWidth="750">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn width="120" type="String">Gerente de Contas</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="120" type="String">Gerente de Seção</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="120" type="String">Gerente de Divisão</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="120" type="String">GAM</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="120" type="String">Parceiro Vivo</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="120" type="String">Técnico Residente</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="120" type="String" align="left">Consultor de Relacionamento 1</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn width="120" type="String" align="left">Consultor de Relacionamento 2</vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows>
                        <vivo:tbRow key="linha1">
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                            <vivo:tbRowColumn></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </vivo:tbRows>
                </vivo:tbTable>
            </div>
            <acesso:controlHiddenItem nomeIdentificador="voltav_pontcont_btexp">
            <table cellpadding="3" cellspacing="2" width="100%" border="0">
                <tr>
                    <td align="left" width="15%">
                    <!--
                    <img src="/FrontOfficeWeb/resources/images/bt_exportar_nrml.gif" onclick="exportar();" id="btExport" style="border:none;cursor:pointer;">
                    -->
                    </td>
                    <!--
                    <td align="right" id="divExportFilter"  style="display:none">Aplicar filtro:</td>
                    <td>
                    </td>
                    -->
                    <td align="left" width="70%">&nbsp;</td>
                </tr>
            </table>
            </acesso:controlHiddenItem>
            </html:form>
        </vivo:sessao>
        <iframe id="frmDown" name="frmDown" src="about:blank" width="1" height="1" style="display:none"></iframe>
    </netui-template:section>
</netui-template:template>
