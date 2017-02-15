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
            
            function getCheckedValue(radioObj) {
            	if(!radioObj)
            		return "";
            	var radioLength = radioObj.length;
            	if(radioLength == undefined)
            		if(radioObj.checked)
            			return radioObj.value;
            		else
            			return "";
            	for(var i = 0; i < radioLength; i++) {
            		if(radioObj[i].checked) {
            			return radioObj[i].value;
            		}
            	}
            	return "";
            }       
            

            function clearCheckedValue(radioObj) {
            	var radioLength = radioObj.length;
            	for(var i = 0; i < radioLength; i++) {
            		radioObj[i].checked = false; 
            	}
            }                   
            
        
            function validaForm(){  
            	
                if (getCheckedValue(document.forms['formRel'].elements['tipo']) == "") {
                    alert("Campos obrigatórios sem preenchimento.");
                    $('tipo').focus();
                    return false;
                }                  
                
   
                if (getCheckedValue(document.forms['formRel'].elements['relatorio']) == "") {
                    alert("Campos obrigatórios sem preenchimento.");
                    $('relatorio').focus();
                    return false;
                }                  

                if (trim($F('dataInicio'))=="") {
                    alert("Os filtros Data Inicial e Data Final são obrigatórios.");
                    $('dataInicio').focus();
                    return false;
                }              
                 
               if (trim($F('dataTermino'))=="") {
                    alert("Os filtros Data Inicial e Data Final são obrigatórios.");
                    $('dataTermino').focus();
                    return false;
                }                     
                 
                if(!validaDataFinal($F('dataInicio'),$F('dataTermino'))){
                    alert('A Data Inicial não pode ser Maior do que a Data Final.');
                    return false;
                }

				dia = new Date();
				mes = dia.getMonth() + 1;
				hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
                
                if(!validaDataFinal($F('dataTermino'),hoje)){
                    alert('Data de Término maior que o dia de hoje.');
                    return false;
                }

        
                dtInicio  = $F('dataInicio');
                dtTermino = $F('dataTermino');
 
                arD1 = dtInicio.split("/");

				var dt1 = new Date();
                dt1.setFullYear(arD1[2],arD1[1]-1,arD1[0]);

                arD2 = dtTermino.split("/");
				var dt2 = new Date();
                dt2.setFullYear(arD2[2],arD2[1]-1,arD2[0]);

				var hoje = new Date();
				qtDiasDecorridos = diasDecorridos(dt1, hoje);
				
				qtDiasDecorridos2 = diasDecorridos(dt1, dt2); 
                
				//if (qtDiasDecorridos > 90) {
				//	alert('O período máximo de busca é retroativo de três (3) meses.');
				//	return false;
				//}                
 
                
                if (qtDiasDecorridos2 > 30) {
                    alert("Período de consulta superior a 30 dias.");
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
                    params.set('tipo', getCheckedValue(document.forms['formRel'].elements['tipo']) );
                    params.set('relatorio', getCheckedValue(document.forms['formRel'].elements['relatorio']) );
                    
                    $('dataInicio').value = $F('dataInicio');
                    $('dataTermino').value = $F('dataTermino');
                    $('tipo').value = getCheckedValue(document.forms['formRel'].elements['tipo']);
                    $('relatorio').value = getCheckedValue(document.forms['formRel'].elements['relatorio']);

                    new Ajax.Updater('dataTable', '<%=request.getContextPath()%>/VOLE/relatorioMVE/pesquisar.do', {
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
                document.forms[0].action = '<%=request.getContextPath()%>/VOLE/relatorioMVE/clean.do';
                document.forms[0].submit();  
            }
            
            function download (arquivo) {            
                document.forms[0].action = 'download.do?arquivo='+arquivo;
                document.forms[0].submit();    
            }

            function exportar(){
                var f = document.forms[0];
                if(validaForm()){
                    if($F('dataInicio')!=""){
                        $('dataInicio').value = $F('dataInicio');
                        $('dataTermino').value = $F('dataTermino');
                        $('tipo').value = getCheckedValue(document.forms['formRel'].elements['tipo']);
                        $('relatorio').value = getCheckedValue(document.forms['formRel'].elements['relatorio']);

                        f.action = "<%=request.getContextPath()%>/VOLE/relatorioMVE/download.do";
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
            
            
            function selecionaRelatorio (id) {

            	clearCheckedValue(document.forms['formRel'].elements['relatorio']);
            	
                if (id == 1) {
                    $('relatorio0').style.display = "block";
                    $('relatorio1').style.display = "none";
                    $('relatorio2').style.display = "none";
                    $('relatorio3').style.display = "none";
                } else if (id == 2) {
                    $('relatorio0').style.display = "none";
                    $('relatorio1').style.display = "block";
                    $('relatorio2').style.display = "none";
                    $('relatorio3').style.display = "none";
                } else if (id == 3) {              	
                    $('relatorio0').style.display = "none";
                    $('relatorio1').style.display = "none";
                    $('relatorio2').style.display = "block";
                    $('relatorio3').style.display = "none";
                } else if (id == 4) {
                	document.forms['formRel'].elements['relatorio'][6].checked = true;
                    $('relatorio0').style.display = "none";
                    $('relatorio1').style.display = "none";
                    $('relatorio2').style.display = "none";
                    $('relatorio3').style.display = "block";                       
                } else {
                    $('relatorio0').style.display = "none";
                    $('relatorio1').style.display = "none";
                    $('relatorio2').style.display = "none";
                    $('relatorio3').style.display = "none";
                }
            }            

        </script>
        <script language="javascript" for="window" event="onload">
            <logic:notEmpty name="msgStatus" scope="request">
            alert('<bean:write name="msgStatus" scope="request"/>');
            </logic:notEmpty>
            oculta_div();
        </script>
        
        <style type="text/css">
        <!--

            div.scroller {
            height: 155px;
            width: 780px;
            overflow: auto;
            }
            
            div.scroller table {
            height: 55px;
            margin: 0;
            }
                        
            #divTableTitle2 table {width:1300px;}      
            #divTableTitle2 table {
                background:#656565;
                height:25px;
                color:#ffffff;
                font-family:Tahoma;
                font-size:10px;
                font-weight:bold;
                line-height:20px;
                }
            #divTableTitle2 td {
                border-right:1px solid #ffffff;
                padding-left:10px;
                }            
            
        //-->
    </style>
        
      
        
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="460" width="790" label="Relatório" operacoes="Meu Vivo Empresas">
            <form  name="formRel"  action="pesquisar.do" enctype="multipart/form-data" method="post" onsubmit="return false;">

            <input type="hidden" name="idmensagem">
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
            <fieldset>
                <table cellpadding="3" cellspacing="2" width="100%">                  
                
                    <tr>
                        <td align="left" width="25%">
<html:radio name="Form" property="tipo" value="1" styleClass="radio" onclick="selecionaRelatorio('1');"/> Acessos por Gestor<br> 
&nbsp;<html:radio name="Form" property="tipo" value="2" styleClass="radio" onclick="selecionaRelatorio('2');"/> Acessos por Conta<br>                    
&nbsp;<html:radio name="Form" property="tipo" value="3" styleClass="radio" onclick="selecionaRelatorio('3');"/> Acessos por CNPJ<br>
&nbsp;<html:radio name="Form" property="tipo" value="4" styleClass="radio" onclick="selecionaRelatorio('4');"/> Primeiro Acesso
                        </td>
                        <td align="left" width="100%">
                            Periodo:
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
                            Período Máximo de 30 dias
                            <br><br>

                            <div id="relatorio0" style="display:none;">
                                <html:radio name="Form" property="relatorio" value="RELGMGC_PORGESTOR" styleClass="radio"/> Total de acessos únicos no canal por tipo de relacionamento<br>
                                &nbsp;<html:radio name="Form" property="relatorio" value="RELGMGC_TOTAL" styleClass="radio"/> Total de acessos no canal
                            </div>
                          	
                          	<div id="relatorio1" style="display:none;">
                                <html:radio name="Form" property="relatorio" value="RELCONTA_PORCONTA" styleClass="radio"/> Total de acessos únicos no canal<br>
                                &nbsp;<html:radio name="Form" property="relatorio" value="RELCONTA_TOTAL" styleClass="radio"/> Total de acessos no canal                       
                            </div>      
                            
                            <div id="relatorio2" style="display:none;">
                                <html:radio name="Form" property="relatorio" value="RELCNPJ_PORCNPJ" styleClass="radio"/> Total de acessos únicos no canal<br>
                                &nbsp;<html:radio name="Form" property="relatorio" value="RELCNPJ_TOTAL" styleClass="radio"/> Total de acessos no canal                       
                            </div>
                                                                         
                            <div id="relatorio3" style="display:none;">
                            	<html:radio name="Form" property="relatorio" value="RELATORIO_PRIMEIROACESSO" styleClass="radio"/> Primeiro Acesso                      
                            </div>                              
                                                      
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
				
				    <div id="divTableTitle2">
				        <table cellspacing="0" cellpadding="0" style="width:730px;">
				          <tr>          
				                <td align="center" width="100">Status</td>
				                <td align="center" width="200">Tipo Relatorio</td>
				                <td align="center" width="100">Data Inicio</td>
				                <td align="center" width="100">Data Termino</td>
				                <td align="center" width="150">Data Solicitação</td>
				                <td align="center" width="80">Download</td>
				        </tr>
				    </table>
				    </div> 
				    <logic:iterate id="itLinha" name="Form" property="resultset.linhas.linhaArray">
				    <table cellspacing="0" cellpadding="0" style="width:730px;" >
				    <%i++;
				    
				    String classLinha=null;
				    if (i % 2 == 0)
				        classLinha = "rowTabelaZebradoOff";
				    else
				        classLinha = "rowTabelaZebradoOn";    
				    
				    %>
				    
				    <tr class="<%=classLinha%>">
				        <td width="100" align="left" onclick="javascript:exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[1]"/></td>
				        <td width="200" align="left" onclick="javascript:exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[2]"/></td>
				        <td width="100" align="center" onclick="javascript:exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[3]"/></td>
				        <td width="100" align="center" onclick="javascript:exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[4]"/></td>
				        <td width="150" align="center" onclick="javascript:exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[5]"/></td>
				        <td width="80" align="center" onclick="javascript:exibirMensagem('divMensagem<%=i%>');">
				             <logic:equal name="itLinha" property="valorArray[1]" value="Processado">
				                <img src="/FrontOfficeWeb/resources/images/bt_icon_download.gif" title="Download" border="0" style="cursor:pointer;" onclick="download('<bean:write name="itLinha" property="valorArray[0]"/>');">
				            </logic:equal>            
				             <logic:notEqual name="itLinha" property="valorArray[1]" value="Processado">
				                <img src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" title="Download nao disponivel" border="0" >
				            </logic:notEqual>     
				        </td>  
				    </tr>            
				    </logic:iterate>
				   </table>                   
                   
            </div>


            </form>

        </vivo:sessao>
        
        <iframe id="frmDown" name="frmDown" src="about:blank" width="1" height="1" style="display:none"></iframe>
        
        
    </netui-template:section>
</netui-template:template>