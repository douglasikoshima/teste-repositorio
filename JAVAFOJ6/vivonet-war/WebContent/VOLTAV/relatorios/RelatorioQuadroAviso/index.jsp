<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.commons.utils.BrowserDetect"%>

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
    <netui-template:setAttribute name="title" value="Quadro de Avisos VOL-E"/>
    <netui-template:setAttribute name="modulo" value="Relatórios"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/TelaInicialAbaRelacionamento.css">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js" ></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/effects.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/accordion.js"></script>
        
        <script language="javascript" type="text/javascript">
        
            function pesquisaConta(){
                if($F('conta').strip().empty()){
                    alert("Favor informar a conta.");
                    return false;
                }
                document.forms[0].action = 'pesquisaConta.do';
                document.forms[0].submit();    
            }
        
            function validaForm(){
                if($F('dataInicio').strip().empty()){
                    alert("Favor informar o periodo.");
                    return false;
                }
                return true;
            }

            function pesquisar(){
                if(validaForm()){
                    if (self.$('dvAnimarAguarde')) self.startAnimation();
                    var params = $H();
                    params.set('dataInicio', $F('dataInicio'));
                    params.set('dataTermino', $F('dataTermino'));
                    params.set('regional', $F('regional'));
                    params.set('segmentacao', $F('segmentacao'));                    
                    params.set('conta', $F('conta'));                    

                    $('dataInicio').value = $F('dataInicio');
                    $('dataTermino').value = $F('dataTermino');
                    $('regional').value = $F('regional');
                    $('segmentacao').value = $F('segmentacao');
                    $('conta').value = $F('conta');

                    new Ajax.Updater('dataTable', 'pesquisar.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onSuccess: function() {
                            if (self.$('dvAnimarAguarde')) self.stopAnimation();
                            
                            var bottomAccordion = new accordion('vertical_container', {});
							if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();                            

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
                    if($F('dataInicio')!=""){
                        $('dataInicio').value = $F('dataInicio');
                        $('dataTermino').value = $F('dataTermino');
                        $('regional').value = $F('regional');
                        $('segmentacao').value = $F('segmentacao');
                        $('conta').value = $F('conta');
                        f.action = "exportar.do";
                        f.target = "frmDown";
                        f.submit();
                    }else{
                        alert("Favor realizar a Pesquisa.");
                    }
                }
            }
            
            
            function exibirMensagem (id) {
                var div = document.getElementById(id);
                
                if (div.style.display == "none") {
                    div.style.display = "block";
                } else {
                    div.style.display = "none";
                }

            }
            
            
            function excluirMensagem(id) {
                if (window.confirm('Confirma exclusão da mensagem ?')) {                    
                    var f = document.forms[0];
                    $('idmensagem').value = id;   
                    f.action = "excluirMensagem.do";
                    f.submit();
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
        <vivo:sessao id="qdMain" height="460" width="790" label="Relatório" operacoes="Relatorio Quadro Aviso VOL-E">
            <form  name="formRel"  action="pesquisar.do" enctype="multipart/form-data" method="post" onsubmit="return false;">

            <input type="hidden" name="idmensagem">
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
            <fieldset>
                <table cellpadding="3" cellspacing="2" width="100%">
                    <tr>
                        <td align="right" width="25%">Conta:</td>
                        <td align="left" width="25%">
                            <html:text styleId="conta" name="Form" property="conta" size="11" maxlength="10" />&nbsp;
                            <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onclick="pesquisaConta();" style="cursor:pointer;">
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right" width="25%">Regional:</td>
                        <td align="left" width="25%">
                            <html:select name="Form" property="regional" styleId="regional" style="width:150px;" styleClass="SELECT">
                                <option value="" selected>-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaRegional.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>          
                    <tr>
                        <td align="right" width="25%">Segmentação:</td>
                        <td align="left" width="25%">
                            <html:select name="Form" property="segmentacao" styleId="segmentacao" style="width:150px;" styleClass="SELECT">
                                <option value="" selected>-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaSegmento.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>                              
                    <tr>
                        <td align="right" width="25%">Periodo Validade:</td>
                        <td align="left" width="100%">
                            <html:text name="Form"
                                       property="dataInicio"
                                       styleId="dtValidadeDe"
                                       onkeyup="checaData(this)"
                                       onblur="checaData(this)"
                                       maxlength="10"
                                       style="width:70px" />
                            <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;margin-right:20px;" title="Calendário" onclick="return showCalendar('dtValidadeDe', '%d/%m/%Y');">
                            At&eacute;: &nbsp;&nbsp;
                            <html:text name="Form"
                                       property="dataTermino"
                                       styleId="dtValidadeAte"
                                       onkeyup="checaData(this)"
                                       onblur="checaData(this)"
                                       maxlength="10"
                                       style="width:70px" />
                            <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtValidadeAte', '%d/%m/%Y');">
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
                  <div id="divTableTitle">
                    <table width="100%" cellspacing="0" cellpadding="0">
                        <tr>
                            <td align="center" width="141">Conta</td>
                            <td align="center" width="101">Regional</td>
                            <td align="center" width="134">Segmentação</td>
                            <td align="center" width="144">Data Envio</td>
                            <td align="center" width="102">Validade</td>
                            <td align="center" width="102">Status</td>
                            <td align="center" width="102"></td>
                        </tr>
                    </table>
                </div> 
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