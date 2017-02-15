<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="camposDinamicosForm"
             type="admsistemas.AdmFormularios.AdmFormulariosController.CamposDinamicosForm" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/admsistemas-formularios.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script>
            <!--
                function criarNovoForm(){
                    self.location.href = "/FrontOfficeWeb/admsistemas/AdmFormularios/loadCfgFormulario.do";
                }

                function showForm(id){
                    var params = $H();
                    params.set('idFormulario', id);
                    createNewPopUp('frmDinamico', '', 550, 300, null, 'visualizaFormulario.do', true, params);
                }

                function alterarForm(id){
                    self.location.href = "/FrontOfficeWeb/admsistemas/AdmFormularios/loadCfgFormularioAlterar.do?idFormulario="+id;
                }
                function duplicarForm(id){
                    self.location.href = "/FrontOfficeWeb/admsistemas/AdmFormularios/loadCfgFormularioAlterar.do?duplicarFormulario=true&idFormulario="+id;
                }

                function excluirForm(id){
                    if(id!='' || id!=undefined){
                        var params = $H();
                        params.set('idFormulario', id);
                        new Ajax.Updater({ success : 'dataFormTable'}, 'excluirForm.do', {
                            method: 'post',
                            parameters: params,
                            evalScripts: true,
                            onSuccess: function() {
                                alert('Operação realizada com sucesso!');
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, onFailure: function(e) {
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                                top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                            }
                        });
                    }
                }

                function verificaExcluirForm(id){
                    if(id!='' || id!=undefined){
                        var params = $H();
                        params.set('idFormulario', id);
                        new Ajax.Request('verificaExcluirForm.do?' + params.toQueryString(), {
                            method: 'post',
                            onSuccess: function(e) {
                                if (e.responseText != '') {
                                    var dom = parseXml(e.responseText);
                                    var jsonString = xml2json(dom, '');
                                    var jsonObj = jsonString.evalJSON();
                                    if(jsonObj.Resultset && jsonObj.Resultset.linhas){
                                        var count = jsonObj.Resultset.linhas.linha.valor;
                                        if(count>0){
                                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                            alert('Não é possível realizar a exclusão deste formulário, pois o mesmo está associado a pelo menos uma funcionalidade e/ou regra.');
                                        }else{
                                            if(confirm('Deseja realmente excluir este formulário?')){
                                                excluirForm(id);
                                            } else {
                                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                                            }
                                        }
                                    }
                                }
                            }, onCreate: function() {
                                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

                            }, onComplete: function() {
                                //if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                            }, onException: function(e) {
                                window.top.frameApp.createErrorPopUp('erro', e.statusText, e.responseText, false);
                                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            }
                        });
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
                    var limpar = false;
                    for(i = 0; i<listaOrigem.options.length; i++) {
                        if (flAll) listaOrigem.options[i].selected = true;
                        if(listaOrigem.options[i].selected)
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                            limpar = true;


                    }
                    for(i = listaOrigem.options.length-1; i>=0; i--)
                        if(listaOrigem.options[i].selected && limpar)
                            listaOrigem.options[i] = null;

                }
            }


            -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:sessao id="manutencao" width="790" height="480" operacoes="Manutenção de Formulários" scroll="no">

        <div id="dataFormTable" style="with:740;height:200px;margin-top:10px;margin-left:5px;">
            <vivo:tbTable selectable="true" layoutWidth="740" layoutHeight="200" tableWidth="740" styleId="tableTitle" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="left" width="40%" type="string">Nome do Formulário</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="40%" type="string">Funcionalidade associada</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>
                    <logic:present name="Form" property="listaFormularios.linhas">
                    <logic:iterate id="it" name="Form" property="listaFormularios.linhas.linhaArray" indexId="idx">
                        <vivo:tbRow key="linha1">
                            <vivo:tbRowColumn><bean:write name="it" property="valorArray[1]"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="it" property="valorArray[2]"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" title="Visualizar formulário" onclick="showForm('<bean:write name="it" property="valorArray[0]"/>');"     style="cursor:pointer;"></vivo:tbRowColumn>
                            <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_duplicar.gif" title="Duplicar formulário"   onclick="duplicarForm('<bean:write name="it" property="valorArray[0]"/>');" style="cursor:pointer;"></vivo:tbRowColumn>
                            <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif"  title="Alterar formulário"    onclick="alterarForm('<bean:write name="it" property="valorArray[0]"/>');"  style="cursor:pointer;"></vivo:tbRowColumn>
                            <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif"  title="Excluir formulário"    onclick="verificaExcluirForm('<bean:write name="it" property="valorArray[0]"/>');" style="cursor:pointer;"></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
                    </logic:present>
                </vivo:tbRows>
            </vivo:tbTable>
        </div>
        <div id="dvLegenda" style="with:740;height:40px;margin-top:10px;margin-left:5px;">
            <table width="740" border="0" cellspacing="5" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                <tr>
                    <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                    <td colspan="4"></td>
                </tr>
                <tr>
                    <td align="center">
                        <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif">&nbsp;Visualizar formulário
                    </td>
                     <td align="center">
                        <img src="/FrontOfficeWeb/resources/images/bt_icon_duplicar.gif">&nbsp;Duplicar formulário
                    </td>
                    <td align="center">
                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif">&nbsp;Alterar formulário
                    </td>
                    <td align="center">
                        <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif">&nbsp;Excluir formulário
                    </td>
                    <td><img src="/FrontOfficeWeb/resources/images/transp.gif" width="10" height="5"></td>
                    <td><img src="/FrontOfficeWeb/resources/images/transp.gif" width="10" height="5"></td>
                </tr>
            </table>
        </div>
        <table width="740" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td align="right">
                    <img src="/FrontOfficeWeb/resources/images/bt_criarnovo_nrml.gif" align="middle" onclick="criarNovoForm();" style="cursor:pointer;">
                </td>
            </tr>
        </table>
        </vivo:sessao>
    </netui-template:section>
</netui-template:template>