<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioAcessosForm"/>

<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Front-Office >> Workflow"/>
    <netui-template:setAttribute name="modulo" value="Workflow"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <script language="javascript">
        
            function submitGerar() {
            
                erros = "";
             
                var dtInicio = document["formFiltro"]["dataInicio"];
                var dtFim = document["formFiltro"]["dataFim"];
        
                if (dtInicio.value == "") {
                    erros += "Selecione a Data de Início!\n";
                }
                if (dtFim.value == "") {
                    erros += "Selecione a Data Final!\n";
                }
             
                if (!validaDataFinal(dtInicio.value,dtFim.value)) {
                    erros += "Data Inicial maior que Data Final!\n";
                }
             
                if (!valida1mes(dtInicio,dtFim)) {
                    erros += "Intervalo de Datas maior que 30 dias!\n";
                }
                
                if (erros == "") {
                    //Liga animação
                    //if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        
                    document["formFiltro"].method = "POST";
                    document["formFiltro"].action = "gerarRelatorioUtilizacaoServicos.do";
                    document["formFiltro"].target = "";
                    document["formFiltro"].submit();
                } else {
                    alert(erros);
                    return false;
                }
            }
                
            function submitOperadoras() {
                document["formFiltro"].method = "POST";
                document["formFiltro"].action = "obterRegionais.do";
                document["formFiltro"].target = "ifrmCombos";
                document["formFiltro"].submit();     
                           
            }
            
            function submitOperadorasTerminal() {
                document["formFiltro"].method = "POST";
                document["formFiltro"].action = "obterRegionaisTerminal.do";
                document["formFiltro"].target = "ifrmCombos";
                document["formFiltro"].submit();                
            }
        
        
            function regionalTerminalChange() {
                document["formFiltro"].method = "POST";
                document["formFiltro"].action = "obterLojas.do";
                document["formFiltro"].target = "ifrmCombos";
                document["formFiltro"].submit();
            }
        
           
            function lojaChange() {
                document["formFiltro"].method = "POST";
                document["formFiltro"].action = "obterTerminais.do";
                document["formFiltro"].target = "ifrmCombos";
                document["formFiltro"].submit();                
            }
        
            function canalChange() {
                canal = document["formFiltro"]["canalSelecionado"];
                if (canal.value == '<%= ConstantesCRM.COD_CANAL_TAV %>') {
                    trLoja.style.display = '';
                    trTerminal.style.display = '';
                    trOperadora.style.display = '';
                    trRegional.style.display = '';
                } else {
                    trLoja.style.display = 'none';
                    trTerminal.style.display = 'none';
                    trOperadora.style.display = 'none';
                    trRegional.style.display = 'none';
                }
        
                document["formFiltro"].method = "POST";
                document["formFiltro"].action = "obterLojas.do";
                document["formFiltro"].target = "ifrmCombos";
                document["formFiltro"].submit();                
            }
        
            function setIp(valor) {
                document["formFiltro"]["ipTerminalSelecionado"].value = valor;
            }
        
            function valida1mes(objInicial,objFinal){
                objSomaDias = document.forms[0].somaDias;
                somaDiasData(objInicial,objSomaDias,30);
                resposta = validaDataFinal(objFinal.value,objSomaDias.value);
                return resposta;
            }
            
            function validaData(data){
                if(data.value == '')
                    return false;
                if(!validate_date(data.value)){
                    data.value = '';
                    data.focus();
                    alert("Data inválida");
                }
            }
            
            /*
            function trocaServicosDisponiveis()
            {
                canal = document["formFiltro"]["canalSelecionado"];
                if (canal[canal.selectedIndex].value == "15")
                {
                    document.all["servicosVOL"].style.display = "inline";
                    document.all["servicosTAV"].style.display = "none";
                }
                else
                {
                    document.all["servicosTAV"].style.display = "inline";
                    document.all["servicosVOL"].style.display = "none";
                }
            }
            document.onload = "trocaServicosDisponiveis";
            */
        </script>
    </netui-template:section>  
    <netui-template:section name="bodySection">
   
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <!--div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div-->
         <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <!--APLICACAO--> 
            <vivo:sessao id="qdMain" height="480" width="790" label="Utilização de Serviços" operacoes="Gerar Relatório">
 				<html:form styleId="formFiltro" action="/VOLTAV/relatorios/gerarRelatorioUtilizacaoServicos.do" method="post">
 					<html:hidden name="form" property="ipTerminalSelecionado" styleId="ipTerminalSelecionado"/>            
                    <html:hidden name="form" property="nmOperadoraTerminal" styleId="nmOperadoraTerminal" />
                    <html:hidden name="form" property="nmRegionalTerminal" styleId="nmRegionalTerminal" />
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tr>
                            <td align="left" width="160">
                                <b>Agrupado por Linha:</b>
                            </td>
                            <td align="left">
                                <html:radio name="form" property="isAgrupado" value="1" style="border:none;background-color:#E3ECF4;">&nbsp;Sim&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:radio>
                                <html:radio name="form" property="isAgrupado" value="0" style="border:none;background-color:#E3ECF4;">&nbsp;N&atilde;o</html:radio>
                            </td>
                        </tr>                      
                        <tr>
                            <td align="left">
                                <b>Canal:</b>
                            </td>
                            <td align="left">
                                <html:select styleId="canalSelecionado" name="form" property="canalSelecionado" style="width:200px" onchange="canalChange();">
                                    <html:optionsCollection name="form" property="canais" label="value" value="key" />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Data de Início do Período:</b></td>
                            <td>
                                <html:text name="form" property="dataInicio" styleId="dataInicio" size="10" maxlength="10" onblur="validaData(this);" onkeyup="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dataInicio', '%d/%m/%Y');">&nbsp;
                                <B>até&nbsp;</b>
                                <html:text name="form" property="dataFim" styleId="dataFim" size="10" maxlength="10" onblur="validaData(this);" onkeyup="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dataFim', '%d/%m/%Y');">&nbsp;
                                <b>(Máximo 30 dias)</b></td>
                        </tr>
                         <tr>
                            <td align="left"><b>Serviços:</b></td>
                            <td align="left">
                               <html:select styleId="servicoSelecionado" name="form" property="servicoSelecionado" style="width:200px">
                                   <html:optionsCollection name="form" property="servicosDisponiveis" label="value" value="key" />
                               </html:select>
                            </td>
                        </tr>
                    </table>
                <iframe scrolling="yes" style="visibility:hidden;" name="ifrmCombos" height="0" width="0"></iframe>
                <input type="hidden" name="somaDias">
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
              <vivo:quadro height="170" width="780" label="Filtros por Linha" id="FiltrosporLinha">
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                       <tr>
                            <td align="left" width="153">
                                <b>Operadora:</b>
                            </td>
                            <td align="left">
                                <html:select styleId="operadoraSelecionada" name="form" property="operadoraSelecionada" style="width:200px" onchange="submitOperadoras();">
                                    <html:optionsCollection name="form" property="operadoras" label="value" value="key" />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <b>Regional:</b>
                            </td>
                            <td align="left">
                                <html:select styleId="regionalSelecionada" name="form" property="regionalSelecionada" style="width:200px">
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <b>Carteira:</b>
                            </td>
                            <td align="left">
                                <html:select styleId="carteiraSelecionada" name="form" property="carteiraSelecionada" style="width:200px">
                                    <html:optionsCollection name="form" property="carteiras" label="value" value="key" />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left"><b>Segmento:</b></td>
                            <td align="left">
                                <html:select styleId="segmentoSelecionada" name="form" property="segmentoSelecionada" style="width:200px">
                                    <html:optionsCollection name="form" property="segmentos" label="value" value="key" />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <b>Tipo de Linha:</b>
                            </td>
                            <td align="left">
                                <html:select styleId="tipoLinhaSelecionado" name="form" property="tipoLinhaSelecionado" style="width:200px">
                                    <html:optionsCollection name="form" property="tipoLinha" label="value" value="key" />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <b>Tecnologia:</b>
                            </td>
                            <td align="left">
                                <html:select styleId="tecnologiaSelecionada" name="form" property="tecnologiaSelecionada" style="width:200px">
                                    <html:optionsCollection name="form" property="tecnologias" label="value" value="key" />
                                </html:select>
                            </td>
                        </tr>                                                             
						<tr>
                            <td align="left"><b>Número da Linha:</b></td>
                            <td align="left">
                                &nbsp;<html:text name="form" property="codigoArea" styleId="codigoArea" size="2" maxlength="2"></html:text> - <html:text name="form" property="numeroLinha" styleId="numeroLinha" size="9" maxlength="9"/>
                            </td>
                        </tr>	
            </table>					                 
            </vivo:quadro>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
            <vivo:quadro height="100" width="780" label="Filtros por Terminal" id="FiltrosporTerminal">
                 <table cellpadding="0" cellspacing="0" border="0" width="100%">                   
                       <tr id="trOperadora" style="display: none;">
                            <td align="left" width="153">
                                <b>Operadora:</b>
                            </td>
                            <td align="left">
                                <html:select styleId="operadoraTerminalSelecionada" name="form" property="operadoraTerminalSelecionada" style="width:200px" onchange="submitOperadorasTerminal();">
                                    &nbsp;<html:optionsCollection name="form" property="operadoras" label="value" value="key" />
                                </html:select>
                            </td>
                        </tr>                                          
                         <tr id="trRegional" style="display: none;">
                            <td align="left">
                                <b>Regional:</b>
                            </td>
                            <td align="left">
                                &nbsp;<html:select styleId="regionalTerminalSelecionada" name="form" property="regionalTerminalSelecionada" style="width:200px" onchange="regionalTerminalChange();">
                                </html:select>
                            </td>
                        </tr>
                        <tr id="trLoja" style="display: none;">
                            <td align="left">
                                <b>Loja:</b>
                            </td>
                            <td align="left">
                                &nbsp;<html:select styleId="lojaSelecionada" name="form" property="lojaSelecionada" style="width:380px" onchange="lojaChange();">
                                </html:select>
                            </td>
                        </tr>
                        <tr id="trTerminal" style="display: none;">
                            <td align="left">
                                <b>Terminal:</b>
                            </td>
                            <td align="left">
                                &nbsp;<html:select styleId="terminalSelecionado" name="form" property="terminalSelecionado" style="width:200px" onchange="setIp(this.options[this.selectedIndex].text);">
                                </html:select>
                            </td>
                        </tr>
                    </table>                                    
            </vivo:quadro>
            <table width="100%">
                        <tr>
                            <td align="center">
                                <vivo:botao id="btGerar" width="60px" height="10px" value="Gerar" styleClass="btTemplate" onclick="submitGerar();"/>
                            </td>
                        </tr>                
                </table>            
      		</html:form>
            </vivo:sessao>
    </netui-template:section>
</netui-template:template>