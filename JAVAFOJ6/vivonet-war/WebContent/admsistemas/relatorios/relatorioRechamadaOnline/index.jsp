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
    <netui-template:setAttribute name="title" value="Rechamada Online"/>
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
                if (parseInt($F('dataInicio')) == -1 && parseInt($F('dataTermino')) > -1) {                    
                    alert('Você deve selecionar o Horário Inicial');
                    return false;                
                }
                
                if (parseInt($F('dataTermino')) == -1 && parseInt($F('dataInicio')) > -1) {                    
                    alert('Você deve selecionar o Horario Final');
                    return false;                
                }            
                                       
                if (parseInt($F('dataInicio')) > parseInt($F('dataTermino'))) {
                    alert('Horário de Inicio deve ser menor que o Horário de Término.');
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
                    
                    $('dataInicio').value = $F('dataInicio');
                    $('dataTermino').value = $F('dataTermino');
                    
                    new Ajax.Updater('dataTable', '<%=request.getContextPath()%>/admsistemas/relatorioRechamadaOnline/pesquisar.do', {
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
                self.location.href = 'clean.do';
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

        </script>
        <script language="javascript" for="window" event="onload">
            <logic:notEmpty name="msgStatus" scope="request">
            alert('<bean:write name="msgStatus" scope="request"/>');
            </logic:notEmpty>
            oculta_div();
        </script>
        
        <script type="text/javascript">
            function reFresh() {
              document.forms[0].dataInicio.value  = -1;
              document.forms[0].dataTermino.value = -1;
              pesquisar();   
            }
            
            function manterLogado() {
                new Ajax.Updater('manterLogado', 'manterLogado.do', {
                    method: 'post',
                    evalScripts: true,
                    onSuccess: function() {
                        
                    }, onFailure: function(e) {
                        
                    }
                });            
            }
            
            window.setInterval("reFresh()",<%=request.getSession().getAttribute("TIME_REFRESH")%>);
            
            window.setInterval("manterLogado()",60000);
        </script>    
        
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="460" width="790" label="Relatório" operacoes="Rechamada Online">
            <form  name="formRel"  action="pesquisar.do" enctype="multipart/form-data" method="post" onsubmit="return false;">

            <input type="hidden" name="idmensagem">
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
            <fieldset>
                <table cellpadding="3" cellspacing="2" width="100%">                  
                    <tr>
                        <td align="left" width="25%"><small>Relatório Automático</small></td>
                        <td align="left" width="100%">
                                    
                        Hora Inicial: 
                        
                            <html:select name="Form" property="dataInicio" styleId="dataInicio" style="width:150px;" styleClass="SELECT">
                                <option value="-1">--Selecione--</option>
                                <option value="0">00:00</option>
                                <option value="1">01:00</option>
                                <option value="2">02:00</option>
                                <option value="3">03:00</option>
                                <option value="4">04:00</option>
                                <option value="5">05:00</option>
                                <option value="6">06:00</option>
                                <option value="7">07:00</option>
                                <option value="8">08:00</option>
                                <option value="9">09:00</option>
                                <option value="10">10:00</option>
                                <option value="11">11:00</option>
                                <option value="12">12:00</option>
                                <option value="13">13:00</option>
                                <option value="14">14:00</option>
                                <option value="15">15:00</option>
                                <option value="16">16:00</option>
                                <option value="17">17:00</option>
                                <option value="18">18:00</option>
                                <option value="19">19:00</option>
                                <option value="20">20:00</option>
                                <option value="21">21:00</option>
                                <option value="22">22:00</option>
                                <option value="23">23:00</option>
                            </html:select>  
                    
                        Hora Final: 
                        
                            <html:select name="Form" property="dataTermino" styleId="dataTermino" style="width:150px;" styleClass="SELECT">
                                <option value="-1">--Selecione--</option>
                                <option value="0">00:59</option>
                                <option value="1">01:59</option>
                                <option value="2">02:59</option>
                                <option value="3">03:59</option>
                                <option value="4">04:59</option>
                                <option value="5">05:59</option>
                                <option value="6">06:59</option>
                                <option value="7">07:59</option>
                                <option value="8">08:59</option>
                                <option value="9">09:59</option>
                                <option value="10">10:59</option>
                                <option value="11">11:59</option>
                                <option value="12">12:59</option>
                                <option value="13">13:59</option>
                                <option value="14">14:59</option>
                                <option value="15">15:59</option>
                                <option value="16">16:59</option>
                                <option value="17">17:59</option>
                                <option value="18">18:59</option>
                                <option value="19">19:59</option>
                                <option value="20">20:59</option>
                                <option value="21">21:59</option>
                                <option value="22">22:59</option>
                                <option value="23">23:59</option>
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
            <div id="dataTable" style="with:800;height:300px;margin-top:10px;">
            <%
             int i=0;
            %>
            
                <div id="divTableTitle">
                    <table width="760px" cellspacing="0" cellpadding="0" class="tableProtocolo">
                        <tr>          
                            <td align="center" width="120"></td>
                            <td align="center" width="120"></td>
                            <td align="center" width="120"></td>
                            <td align="center" width="200" colspan="2">Período</td>
                            <td align="center" width="200" colspan="2">Consolidado</td>                                
                        </tr>
                        <tr>          
                            <td align="center" width="120">Intervalo</td>
                            <td align="center" width="120">Motivo de Rechamada</td>
                            <td align="center" width="120">Sub Motivo de Rechamada</td>
                            <td align="center" width="100">Quantidade</td>
                            <td align="center" width="100">Percentual</td>
                            <td align="center" width="100">Quantidade</td>
                            <td align="center" width="100">Percentual</td>                
                        </tr>
                    </table>
                </div> 
                <logic:iterate id="itLinha" name="Form" property="resultset.linhas.linhaArray">
                <table width="760px" cellspacing="1" cellpadding="0" class="tableProtocolo" >
                <%i++;
                
                String classLinha=null;
                if (i % 2 == 0)
                    classLinha = "rowTabelaZebradoOff";
                else
                    classLinha = "rowTabelaZebradoOn";    
                
                %>
                
                <tr class="<%=classLinha%>">
                    <td width="110" align="center"><bean:write name="itLinha" property="valorArray[0]"/></td>
                    <td width="115" align="center"><bean:write name="itLinha" property="valorArray[1]"/></td>
                    <td width="115" align="center"><bean:write name="itLinha" property="valorArray[2]"/></td>
                    <td width="100" align="center"><bean:write name="itLinha" property="valorArray[3]"/></td>
                    <td width="100" align="center"><bean:write name="itLinha" property="valorArray[4]"/>%</td>
                    <td width="100" align="center"><bean:write name="itLinha" property="valorArray[5]"/></td>
                    <td width="100" align="center"><bean:write name="itLinha" property="valorArray[6]"/>%</td>
                </tr>            
                </logic:iterate>
               </table>
            </div>
            </form>

        </vivo:sessao>
        
        <div id="manterLogado"></div>
        <iframe id="frmDown" name="frmDown" src="about:blank" width="1" height="1" style="display:none"></iframe>
        
        
    </netui-template:section>
</netui-template:template>