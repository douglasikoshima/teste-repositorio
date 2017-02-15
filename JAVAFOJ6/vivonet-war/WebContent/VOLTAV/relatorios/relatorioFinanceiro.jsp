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

<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioAcessosForm"/>
<bean:define id="recargaVO"		name="relatorioForm" property="recargas" />

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
                    document["formFiltro"].action = "gerarRelatorioFinanceiro.do";
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

             function submitRegionais() {
                document["formFiltro"].method = "POST";
                document["formFiltro"].action = "obterRecargas.do";
                document["formFiltro"].target = "ifrmCombos";
                document["formFiltro"].submit();
            }

            function regionalChange() {
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
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <!--APLICACAO-->
            <vivo:sessao id="qdMain" height="475" width="790" label="Financeiro" operacoes="Gerar Relatório">
                <form name="formFiltro" action="/VOLTAV/relatorios/gerarRelatorioAcessos.do" method="post">
                    <html:hidden name="relatorioForm" property="ipTerminalSelecionado" styleId="ipTerminalSelecionado"/>
                    <input type="hidden" name="canalSelecionado" value="<%= ConstantesCRM.COD_CANAL_TAV %>">
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tr>
                            <td colspan="2"><br><b>Filtros para Geração do Relatório</b></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
						 <tr>
                            <td align="left">
                                <b>Operadora:</b>
                            </td>
                            <td align="left">
                                 <html:select styleId="operadoraSelecionada" name="relatorioForm" property="operadoraSelecionada" style="width:200px" onchange="submitOperadoras();">
                                     <html:optionsCollection name="relatorioForm" property="operadoras" label="value" value="key" />
                                 </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <b>Regional:</b>
                            </td>
                            <td align="left">
                                 <html:select styleId="regionalSelecionada" name="relatorioForm" property="regionalSelecionada" style="width:200px" onChange="submitRegionais();">
                                 </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
						<tr>
                            <td align="left">
                                <b>Lojas:</b>
                            </td>
                            <td align="left">
                                 <html:select styleId="lojaSelecionada" name="relatorioForm" property="lojaSelecionada" style="width:380px" onchange="lojaChange();">
									<html:optionsCollection name="relatorioForm" property="lojas" label="value" value="key" />
                                 </html:select>
                            </td>
                        </tr>
                        <tr id="trTerminal">
                            <td align="left">
                                <b>Terminal:</b>
                            </td>
                            <td align="left">
                                 <html:select styleId="terminalSelecionado" name="relatorioForm" property="terminalSelecionado" style="width:200px" onchange="setIp(this.options[this.selectedIndex].text);">
                                 </html:select>
                            </td>
                        </tr>
						<tr>
                            <td align="left">
                                <b>Tipo Serviço:</b>
                            </td>
                            <!-- <td align="left"> -->
                                <!-- <netui : select tagId="tipoServico" dataSource="tipoServicoSelecionado" optionsDataSource="tipoServico" style="width:200px"/> -->
                            <!-- </td> -->
                            <td align="left">
                               <html:select styleId="servicoSelecionado" name="relatorioForm" property="servicoSelecionado" style="width:200px">
                                     <html:optionsCollection name="relatorioForm" property="tipoServico" label="value" value="key" />
                               </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">
                                <b>Recarga:</b>
                            </td>
                            <!-- <td align="left"> -->
                                <!-- <netui : select tagId="recargas" dataSource="recargaSelecionada" style="width:200px"/> -->
                            <!-- </td> -->
							<td align="left">
								 <html:select styleId="recargaSelecionada" name="relatorioForm" property="recargaSelecionada" style="width:200px">
									 <html:option value="-1" key="recargaSelecionada">Todos</html:option>
									 <html:options name="relatorioForm" collection="recargaVO" property="idTipoRecarga" labelProperty="dsTipoRecarga"/>
								 </html:select>
							</td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
						<tr>
                            <td align="left">
                                <b>Status da Venda:</b>
                            </td>
                            <!-- <td align="left"> -->
                                <!-- <netui : select tagId="statusVenda" dataSource="statusVendaSelecionado" optionsDataSource="statusVenda" style="width:200px"/> -->
                            <!-- </td> -->
                            <td align="left">
                                <html:select styleId="statusVendaSelecionado" name="relatorioForm" property="statusVendaSelecionado" style="width:200px">
                               		 <html:optionsCollection name="relatorioForm" property="statusVenda" label="value" value="key" />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td><b>Data de Início do Período:</b></td>
                            <td><html:text name="relatorioForm" property="dataInicio" styleId="dataInicio" size="10" maxlength="10" onblur="validaData(this);" onkeyup="Formatar(this.value, this.relatorioForm.name, this.name, 'data');" onchange="Formatar(this.value, this.relatorioForm.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dataInicio', '%d/%m/%Y');">&nbsp;
                                <b>até&nbsp;</b>
                                <html:text name="relatorioForm" property="dataFim" styleId="dataFim" size="10" maxlength="10" onblur="validaData(this);" onkeyup="Formatar(this.value, this.relatorioForm.name, this.name, 'data');" onchange="Formatar(this.value, this.relatorioForm.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dataFim', '%d/%m/%Y');">&nbsp;
                                <b>(Máximo 30 dias)</b></td>
                        </tr>
                    </table>
                    <br>
                    <table width="100%">
                        <tr>
                            <td align="center">
                                <vivo:botao id="btGerar" width="60px" height="10px" value="Gerar" styleClass="btTemplate" onclick="submitGerar();"/>
                            </td>
                        </tr>
                    </table>
                    <iframe scrolling="yes" style="visibility:hidden;" name="ifrmCombos" height="0" width="0"></iframe>
                    <input type="hidden" name="somaDias">
                </form>
            </vivo:sessao>
    </netui-template:section>
</netui-template:template>