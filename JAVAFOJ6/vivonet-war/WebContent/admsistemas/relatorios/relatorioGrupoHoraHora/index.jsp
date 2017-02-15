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
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>

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
                
				if (qtDiasDecorridos > 90) {
					alert('O período máximo de busca é retroativo de três (3) meses.');
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
                    params.set('ddd', $F('ddd'));
                    params.set('numeroTelefone', $F('numeroTelefone'));
                    params.set('grupo', $F('grupo'));
                    params.set('fornecedor', $F('fornecedor'));
                    params.set('site', $F('site'));
                    
                    $('dataInicio').value = $F('dataInicio');
                    $('dataTermino').value = $F('dataTermino');
                    $('ddd').value = $F('ddd');
                    $('numeroTelefone').value = $F('numeroTelefone');
                    $('grupo').value = $F('grupo');
                    $('fornecedor').value = $F('fornecedor');
                    $('site').value = $F('site');

                    new Ajax.Updater('dataTable', '<%=request.getContextPath()%>/admsistemas/relatorioGrupoHoraHora/pesquisar.do', {
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
                document.forms[0].action = '<%=request.getContextPath()%>/admsistemas/relatorioGrupoHoraHora/clean.do';
                document.forms[0].submit();  
            }

            function exportar(){
                var f = document.forms[0];
                if(validaForm()){
                    if($F('dataInicio')!=""){
                        $('dataInicio').value = $F('dataInicio');
                        $('dataTermino').value = $F('dataTermino');
                        $('ddd').value = $F('ddd');
                        $('numeroTelefone').value = $F('numeroTelefone');
                        $('grupo').value = $F('grupo');
                        $('fornecedor').value = $F('fornecedor');
                        $('site').value = $F('site');

                        f.action = "<%=request.getContextPath()%>/admsistemas/relatorioGrupoHoraHora/exportar.do";
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
        <vivo:sessao id="qdMain" height="460" width="790" label="Relatório" operacoes="Recontato - Grupo Hora a Hora">
            <form  name="formRel"  action="pesquisar.do" enctype="multipart/form-data" method="post" onsubmit="return false;">

            <input type="hidden" name="idmensagem">
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
            <fieldset>
                <table cellpadding="3" cellspacing="2" width="100%">                  
                
                    <tr>
                        <td align="right" width="25%">Data Inicial:</td>
                        <td align="left" width="100%">
                            <html:text name="Form"
                                       property="dataInicio"
                                       styleId="dtValidadeDe"
                                       onkeyup="checaData(this)"
                                       onblur="checaData(this)"
                                       maxlength="10"
                                       style="width:70px" />
                            <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;margin-right:20px;" title="Calendário" onclick="return showCalendar('dtValidadeDe', '%d/%m/%Y');">
                            Data Final: &nbsp;&nbsp;
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
                        <td></td>
                        <td></td>
                        <td>&nbsp;</td>
                    </tr>    
                                
                    <tr>
                        <td align="right" width="25%">DDD:</td>
                        <td align="left" width="25%">
                            <html:select name="Form" property="ddd" styleId="ddd" style="width:150px;" styleClass="SELECT">
                                <option value="" selected>-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaDDD.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>      
                    
                    <tr>
                        <td align="right" width="25%">N&#250;mero Telefone:</td>
                        <td align="left" width="25%">
                            <html:text styleId="numeroTelefone" name="Form" property="numeroTelefone" size="18" maxlength="11" onblur="formatPhoneNumberObj(this)"/>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>                         
                    
                    <tr>
                        <td align="right" width="25%">Grupo de Atendimento:</td>
                        <td align="left" width="25%">
                            <html:select name="Form" property="grupo" styleId="grupo" style="width:150px;" styleClass="SELECT">
                                <option value="" selected>-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaGrupo.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>        
                    
                    <tr>
                        <td align="right" width="25%">Site:</td>
                        <td align="left" width="25%">
                            <html:select name="Form" property="site" styleId="site  " style="width:150px;" styleClass="SELECT">
                                <option value="" selected>-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaSite.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>       
                    
                    <tr>
                        <td align="right" width="25%">Fornecedor:</td>
                        <td align="left" width="25%">
                            <html:select name="Form" property="fornecedor" styleId="fornecedor" style="width:150px;" styleClass="SELECT">
                                <option value="" selected>-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaFornecedor.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>                                                                       
                
                    <tr>
                        <td align="left" width="25%"></td>
                        <td align="left" width="100%">
                    
                                       
                        </td>
                        <td align="right" width="10%"></td>
                        <td align="left" width="25%"></td>
                        <td><img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onclick="pesquisar();" style="cursor:pointer;"></td>
                        <td><img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onclick="limpar();" style="cursor:pointer;"></td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </fieldset>

            <div class="scroller">
                <div id="dataTable">
                    <div id="divTableTitle2">
                        <table cellspacing="0" cellpadding="0" >
                            <tr>          
                                <td align="center" width="100">Data</td>
                                <td align="center" width="100">Intervalo</td>
                                <td align="center" width="100">Canal Atual</td>
                                <td align="center" width="100">Grupo Anterior</td>
                                <td align="center" width="100">Fornecedor Anterior</td>                                
                                <td align="center" width="100">Site Anterior</td>                                
                                <td align="center" width="100">Motivo do Recontato</td>                                
                                <td align="center" width="100">Sub Motivo do Recontato</td>                                
                                <td align="center" width="100">Quantidade</td>
                            </tr>
                        </table>
                    </div> 
                </div>
            </div>


            </form>

            <table cellpadding="3" cellspacing="2" width="100%">
                <tr>
                    <td align="left"><img src="/FrontOfficeWeb/resources/images/bt_exportar_nrml.gif" onclick="exportar();" style="border:none;cursor:pointer;"></td>
                </tr>
            </table>
        </vivo:sessao>
        
        <iframe id="frmDown" name="frmDown" src="about:blank" width="1" height="1" style="display:none"></iframe>
        
        
    </netui-template:section>
</netui-template:template>