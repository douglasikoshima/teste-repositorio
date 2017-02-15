<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxForm" />
<bean:define id="atdInfVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxForm.atendimentoInformacaoVO" />
<bean:define id="gruposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxForm.atendimentoInformacaoVO.WFGrupoVOArray" />
<bean:define id="estadosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxForm.atendimentoInformacaoVO.WFEstadoVOArray" />
<bean:define id="subEstadosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxForm.atendimentoInformacaoVO.WFSubEstadoVOArray" />
<bean:define id="usuariosVIVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxForm.atendimentoInformacaoVO.usuarioVIVOArray"/>
<bean:define id="atdInBoxPesqVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxForm.atdInBoxPesqVO"/>
<bean:define id="campanhas" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxForm.campanhasVO"/>

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Workflow"/>
    <netui-template:setAttribute name="modulo" value="Processos In-Box"/>
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js"></script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="wor_wb_verpagina">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />

        <!--APLICACAO-->
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
            <vivo:sessao id="qdMain" height="470" width="790" label="Vivo Net >> Workflow >> Processos In-Box" operacoes="Status" scroll="no">

                <form action="begin.do" method="post" name="formInBox" id="formInBox">
                    <html:hidden property="fila" name="form"></html:hidden>
                    <html:hidden property="idUsuario" name="form"></html:hidden>
                    <html:hidden property="abaSelecionada" name="form"></html:hidden>
                    <html:hidden property="atdInBoxPesqVO.idContato" name="form"></html:hidden>
                    <input type="hidden" name="somaDias">
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td>
                                <vivo:abaGrupo id="btAbaPesquisas" width="250px" height="10px" styleClass="abatexto">
                                    <acesso:controlHiddenItem nomeIdentificador="wor_wb_abaestadoatendente">
                                    <vivo:abaItem id="btCTIAtendente" onclick="exibicaoAbaPesquisas(0);" value="Estado Atendente" select="S"/>
                                    </acesso:controlHiddenItem>
                                    <acesso:controlHiddenItem nomeIdentificador="wor_wb_abapesquisa">
                                    <vivo:abaItem id="btPesquisa" onclick="exibicaoAbaPesquisas(1);" value="Pesquisa"/>
                                    </acesso:controlHiddenItem>
                                </vivo:abaGrupo>
                            </td>
                            <td width="20"><img id="btMaxMin" name="btMaxMin" title="Click para minimizar/maximizar os parâmetros de pesquisa" src="<%=request.getContextPath()%>/resources/images/bt_lupa_aba.gif" style="cursor:pointer" onclick="resizeMaxMin();"></td>
                        </tr>
                    </table>
                    <table id="tbConfiguracoes" name="tbConfiguracoes" width="780" cellspacing="0" cellpadding="0" border="0">
                        <!--CTI E STATUS DO ATENDENTE-->
                        <tr id="trEstadoAtendente" name="trEstadoAtendente">
                            <td align="right" width="400px">

                                    <bean:define name="form" property="usuarioVIVO.inDisponivelWF" id="inDisponivelWF"/>
                                    <table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
                                        <tr>
                                            <td height="40" width="30px">Login:</td>
                                            <td width="160px"><bean:write name="form" property="usuarioVIVO.nmLoginUsuario"/></td>
                                            <td>Status:</td>
                                            <td>
                                                <table cellspacing="1" cellpadding="1" border="0">
                                                    <tr>
                                                        <td>
                                                        <acesso:controlHiddenItem nomeIdentificador="wor_wb_disponivel">
                                                        <img id="btDisponivel"  value="Disponível" style='border:none' onClick="submitDisponivel('1');" src='<%="/FrontOfficeWeb/resources/images/"+(inDisponivelWF.equals("1") ? "bt_disponivel_disponivel.gif" : "bt_disponivel_indisponivel.gif")%>' />
                                                        </acesso:controlHiddenItem>
                                                        </td>
                                                        <td>
                                                        <acesso:controlHiddenItem nomeIdentificador="wor_wb_indisponivel">
                                                        <img id="btIndisponivel"  value="Indisponível" style='border:none' onClick="submitDisponivel('2');" src='<%="/FrontOfficeWeb/resources/images/"+(inDisponivelWF.equals("2") ? "bt_indisponivel_disponivel.gif" : "bt_indisponivel_indisponive.gif")%>' />
                                                        </acesso:controlHiddenItem>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <!-- TODO: -->
                                        <tr>
                                            <td>
                                                <iframe scrolling="yes" frameborder="yes" src="test2.html" id="iFrTst1" name="iFrTst1" height="100px" width="300px"></iframe>
                                            </td>
                                        </tr>
                                    </table>
                            </td>
                            <td id="tdBarraCTI" align="right" style="display:none;">
                                    <table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
                                        <tr>
                                            <td height="40" width="15%">Campanha:</td>
                                            <td width="40%">
                                                <html:select name="form" property="numCampanhaSel" title="numCampanhaSel" style="width=150px" onchange="trocarCampanha(this.value)">
                                                    <html:options collection="campanhas" property="idRetornoWFCTI" labelProperty="dsRetornoWFCTI" />
                                                </html:select>
                                            </td>
                                            <td width="45%" align="right"><img id="imgDisponibilidade" src="<%=request.getContextPath()%>/resources/images/icon_atend_disponivel.gif" onclick="mudaDisponibilidade()" style='cursor:pointer' border="0"></td>
                                        </tr>
                                    </table>
                            </td>
                        </tr>

                        <!--PESQUISA-->
                        <tr id="trPesquisa" id="trPesquisa" style="display:none;">
                            <td>

                                    <table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
                                        <tr>
                                            <td>Grupo</td>
                                            <td colspan="3">
                                                <html:select name="form" property="grupoSel" title="grupoSel" style="width=250px">
                                                    <html:option value="-1" key="grupoSel">&nbsp;</html:option>
                                                    <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
                                                </html:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="10%">Estado</td>
                                            <td width="40%">
                                                <html:select name="form" property="estadoSel" title="estadoSel" style="width=250px" onchange="submitEstado();">
                                                    <html:option value="-1" key="estadoSel">&nbsp;</html:option>
                                                    <html:options collection="estadosVO" property="idEstado" labelProperty="dsEstado" />
                                                </html:select>
                                            </td>
                                            <td width="15%">&nbsp;&nbsp;Sub-Estado</td>
                                            <td width="35%">
                                                <html:select name="form" property="subEstadoSel" title="subEstadoSel" style="width=250px">
                                                    <html:option value="-1" key="subEstadoSel">&nbsp;</html:option>
                                                    <html:options collection="subEstadosVO" property="idSubEstado" labelProperty="dsSubEstado" />
                                                </html:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Processo</td>
                                        <td><html:text property="atdInBoxPesqVO.nrProtocolo" name="form" maxlength="30" style="width:100px" onkeyup ="Formatar(this.value, this.form.name, this.name, 'numero');" onchange="Formatar(this.value, this.form.name, this.name, 'numero');"/></td>
                                            <td>
                                                <netui:radioButtonGroup dataSource="{actionForm.optGrpSel}" defaultValue="" tagId="optGrpSel">
                                                    <netui:radioButtonOption value="0" styleClass="radio" onClick="habilitaData(this.value);">Abertura de</netui:radioButtonOption>
                                                </netui:radioButtonGroup>
                                            </td>
                                            <td width="15%">
                                                <html:text property="atdInBoxPesqVO.dtAberturaInicio" name="form" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtAbIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('atdInBoxPesqVO.dtAberturaInicio', '%d/%m/%Y');">&nbsp;Até&nbsp;
                                                <html:text property="atdInBoxPesqVO.dtAberturaFim" name="form" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('atdInBoxPesqVO.dtAberturaFim', '%d/%m/%Y');">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Linha</td>
                                            <td><html:text property="atdInBoxPesqVO.nrLinha" name="form" maxlength="255" style="width:100px" onkeyup ="Formatar(this.value, this.form.name, this.name, 'telefone');" onchange="Formatar(this.value, this.form.name, this.name, 'telefone');"/></td>
                                            <td><netui:radioButtonGroup dataSource="{actionForm.optGrpSel}" defaultValue="" tagId="optGrpSel">
                                                    <netui:radioButtonOption styleClass="radio" value="1" onClick="habilitaData(this.value);">Fechamento de</netui:radioButtonOption>
                                                </netui:radioButtonGroup>
                                            </td>
                                            <td>
                                                <html:text property="atdInBoxPesqVO.dtFechamentoInicio" name="form" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtFecIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('atdInBoxPesqVO.dtFechamentoInicio', '%d/%m/%Y');">&nbsp;Até&nbsp;
                                                <html:text property="atdInBoxPesqVO.dtFechamentoFim" name="form" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtFecFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('atdInBoxPesqVO.dtFechamentoFim', '%d/%m/%Y');">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="12%">Contato</td>
                                            <td colspan="3">
                                                <table width="100%" cellspacing="0" cellpadding="0" border="0">
                                                    <tr>
                                                        <td width="310"><html:text property="textoContato" name="form" maxlength="255" style="width:300px" readonly="true"/></td>
                                                        <td width="30"><img id="imgLupa" src="<%=request.getContextPath()%>/resources/images/lupa_bg_transp.gif" onclick="arvoreContato()" style='cursor:pointer' border="0"></td>
                                                        <td width="110"><img id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" value="Pesquisar" style="border:none" onClick="submitPesquisar();"/></td>
                                                        <td><img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" id="btLimpar" value="Limpar" style="border:none" onClick="flLimpar=1;limparPesquisa();exibicaoAbaPesquisas(1);"/></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4">
                                                <iframe scrolling="yes" frameborder="yes" src="" id="iFrTst2" name="iFrTst2" height="200px" width="200px"></iframe>
                                            </td>
                                        </tr>
                                    </table>
                            </td>
                        </tr>
                    </table>
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                    <vivo:abaGrupo id="btAbaInBox" width="300" height="10px" styleClass="abatexto" >
                        <acesso:controlHiddenItem nomeIdentificador="wor_wb_abaprotrat">
                        <vivo:abaItem id="btTratamento" onclick="exibicaoAbaInBox(0);" value="Processos Em Tratamento" select="S"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="wor_wb_abappauta">
                        <vivo:abaItem id="btPausa" onclick="exibicaoAbaInBox(1);" value="Processos Em Pausa"/>
                        </acesso:controlHiddenItem>
                    </vivo:abaGrupo>
                    <table width="780" cellspacing="0" cellpadding="0" border="0">
                        <!--ABAS PROCESSOS TRATAMENTO-->
                        <tr id="trProcessosTratamento" name="trProcessosTratamento">
                            <td>
                                    <table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
                                        <tr>
                                            <td>Atualização:</td>
                                            <td width="10%"><bean:write name="atdInfVO" property="atualizacao"/></td>
                                            <td width="10%" align="right">Vermelho: </td>
                                            <td width="10%"><div id="lblVermelhoTrat">0</div></td>
                                            <td width="10%" align="right">Amarelo: </td>
                                            <td width="10%"><div id="lblAmareloTrat">0</div></td>
                                            <td width="10%" align="right">Normal: </td>
                                            <td width="10%"><div id="lblNormalTrat">0</div></td>
                                            <td width="20%" align="right">Registros Encontrados: </td>
                                            <td width="10%"><div id="lblTotalTrat">0</div></td>
                                        </tr>
                                    </table>
                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                                <vivo:tbTable selectable="true" layoutWidth="765" layoutHeight="161" tableWidth="765" styleId="tbProcessosTratamento" sortable="true" onRowClick="linhaSel(this);">
                                    <vivo:tbHeader>
                                        <vivo:tbHeaderColumn width="5%"/>
                                        <vivo:tbHeaderColumn align="center" width="6%" type="string">Alerta</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="8%" type="number">N. Processo</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="30%" type="string">Contato</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="12%" type="string">N. Linha</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="19%" type="string">Estado / Sub-Estado</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="10%" type="date">Abertura</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="10%" type="date">Fechamento</vivo:tbHeaderColumn>
                                    </vivo:tbHeader>
                                    <vivo:tbRows>
                                        <% int qtdVermelhoTrat = 0, qtdAmareloTrat = 0, qtdNormalTrat = 0, qtdTotalTrat = 0;%>

                                        <logic:iterate id="atdInBoxVO" name="form" property="atendimentoInformacaoVO.atendimentoInBoxVOArray" indexId="idx">
                                            <bean:define id="atdVO" name="atdInBoxVO" property="atendimentoVO"/>
                                            <%
                                                String inTratamento = ((AtendimentoVO)atdVO).getTabPausa();
                                                if(inTratamento == null || ( inTratamento.equals(ConstantesCRM.SZERO))) {
                                                String idClassRow = ConstantesCRM.SVAZIO;%>

                                                <logic:iterate id="alertaVO" name="atdInBoxVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
                                                    <bean:define name="alertaVO" property="nmCor" id="nmCor"/>
                                                    <logic:notEmpty name="alertaVO" property="nmCor">
                                                        <%idClassRow = nmCor.toString(); %>
                                                    </logic:notEmpty>
                                                </logic:iterate>

                                                <!-- Faz a contagem de registros de acordo com o alerta -->
                                                <%
                                                    qtdVermelhoTrat += idClassRow.equals("rowTabelaAlertaAlto") ? 1 : 0;
                                                    qtdAmareloTrat += idClassRow.equals("rowTabelaAlertaMedio") ? 1 : 0;
                                                    qtdNormalTrat += idClassRow.equals(ConstantesCRM.SVAZIO) ? 1 : 0;
                                                %>

                                                <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
                                                    <vivo:tbRowColumn>
                                                        <html:hidden name="atdInBoxVO" property="atendimentoVO.dtSuspeito"/>
                                                        <html:hidden name="atdInBoxVO" property="atendimentoVO.idAtendimentoBaixaHistorico"/>
                                                        <html:hidden name="atdInBoxVO" property="atendimentoVO.nmURLDados"/>
                                                        <html:hidden name="atdInBoxVO" property="atendimentoVO.dtSolicitacaoCancelamento"/>
                                                        <html:checkbox name="atdInBoxVO" property="operacaoWorkflow" indexed="true" value="true" onclick="controleDetalhe = false;" style="border=0; background-color=transparent;"></html:checkbox>
                                                    </vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>
                                                        <logic:iterate id="alertaVO" name="atdInBoxVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
                                                            <logic:notEmpty name="alertaVO" property="nmCor">
                                                                <input type="image" name="" src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" onclick="submitAlerta('<bean:write name="atdInBoxVO" property="atendimentoVO.idAtendimento" format="###"/>');" style="border=0; background-color=transparent;" title="Ver alertas">
                                                            </logic:notEmpty>
                                                        </logic:iterate>
                                                    </vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>
                                                        <bean:write name="atdInBoxVO" property="atendimentoVO.nrProtocolo" format="###"/>
                                                        <%--html:hidden styleClass="classIdAtendimento" name="atdInBoxVO" property="atendimentoVO.idAtendimento"/--%>
                                                        <span class="classIdAtendimento"><bean:write name="atdInBoxVO" property="atendimentoVO.idAtendimento"/></span>
                                                    </vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="atendimentoVO.arvoreAtendimentoVO.descricaoCompleta"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="atendimentoVO.nrTelefone"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="atendimentoVO.WFEstadoVO.dsEstado"/> / <bean:write name="atdInBoxVO" property="atendimentoVO.WFSubEstadoVO.dsSubEstado"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="atendimentoVO.dtAbertura"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="atendimentoVO.dtFechamento"/></vivo:tbRowColumn>
                                                </vivo:tbRow>
                                            <% } %>
                                        </logic:iterate>

                                        <!-- Atualiza labels com valor dos contadores -->
                                        <%
                                            qtdTotalTrat = qtdVermelhoTrat + qtdAmareloTrat + qtdNormalTrat;
                                            String scriptHtml = "";
                                            scriptHtml += "<script language=\"javascript\">";
                                            scriptHtml += "  lblVermelhoTrat.innerHTML = " + qtdVermelhoTrat + ";";
                                            scriptHtml += "  lblAmareloTrat.innerHTML = " + qtdAmareloTrat + ";";
                                            scriptHtml += "  lblNormalTrat.innerHTML = " + qtdNormalTrat + ";";
                                            scriptHtml += "  lblTotalTrat.innerHTML = " + qtdTotalTrat + ";";
                                            scriptHtml += "</script>";
                                        %>
                                        <%=scriptHtml%>
                                    </vivo:tbRows>
                                </vivo:tbTable>
                            </td>
                        </tr>

                        <!--ABAS PROCESSOS PAUSA-->
                        <tr id="trProcessosPausa" name="trProcessosPausa" style="display:none;">
                            <td>
                                    <table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
                                        <tr>
                                            <td>Atualização: </td>
                                            <td width="10%"><bean:write name="atdInfVO" property="atualizacao"/></td>
                                            <td width="10%" align="right">Vermelho: </td>
                                            <td width="10%"><div id="lblVermelhoPausa">0</div></td>
                                            <td width="10%" align="right">Amarelo: </td>
                                            <td width="10%"><div id="lblAmareloPausa">0</div></td>
                                            <td width="10%" align="right">Normal: </td>
                                            <td width="10%"><div id="lblNormalPausa">0</div></td>
                                            <td width="20%" align="right">Registros Encontrados: </td>
                                            <td width="10%"><div id="lblTotalPausa">0</div></td>
                                        </tr>
                                    </table>
                                 <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                                <vivo:tbTable selectable="true" layoutWidth="765" layoutHeight="161" tableWidth="765" styleId="tbProcessosPausa" sortable="true" onRowClick="linhaSel(this);">
                                    <vivo:tbHeader>
                                        <vivo:tbHeaderColumn width="5%"/>
                                        <vivo:tbHeaderColumn align="center" width="6%" type="string">Alerta</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="8%" type="number">N. Processo</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="30%" type="string">Contato</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="12%" type="string">N. Linha</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="19%" type="string">Estado / Sub-Estado</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="10%" type="date">Fim Pausa</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="10%" type="date">Fechamento</vivo:tbHeaderColumn>
                                    </vivo:tbHeader>
                                    <vivo:tbRows>
                                        <% int qtdVermelhoPausa = 0, qtdAmareloPausa = 0, qtdNormalPausa = 0, qtdTotalPausa = 0;%>

                                        <logic:iterate id="atdInBoxVO" name="form" property="atendimentoInformacaoVO.atendimentoInBoxVOArray" indexId="idx">

                                            <bean:define id="atdVO" name="atdInBoxVO" property="atendimentoVO"/>
                                            <%
                                                String inPausa = ((AtendimentoVO)atdVO).getTabPausa();
                                            if(inPausa != null && inPausa.equals(ConstantesCRM.SONE)) {%>

                                                <% String idClassRow = ConstantesCRM.SVAZIO; %>
                                                <logic:iterate id="alertaVO" name="atdInBoxVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
                                                    <bean:define name="alertaVO" property="nmCor" id="nmCor"/>
                                                    <logic:notEmpty name="alertaVO" property="nmCor">
                                                        <%idClassRow = nmCor.toString(); %>
                                                    </logic:notEmpty>
                                                </logic:iterate>

                                                <!-- Faz a contagem de registros de acordo com o alerta -->
                                                <%
                                                    qtdVermelhoPausa += idClassRow.equals("rowTabelaAlertaAlto") ? 1 : 0;
                                                    qtdAmareloPausa += idClassRow.equals("rowTabelaAlertaMedio") ? 1 : 0;
                                                    qtdNormalPausa += idClassRow.equals(ConstantesCRM.SVAZIO) ? 1 : 0;
                                                %>

                                                <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
                                                    <vivo:tbRowColumn>
                                                        <html:hidden name="atdInBoxVO" property="atendimentoVO.dtSuspeito"/>
                                                        <html:hidden name="atdInBoxVO" property="atendimentoVO.idAtendimentoBaixaHistorico"/>
                                                        <html:hidden name="atdInBoxVO" property="atendimentoVO.nmURLDados"/>
                                                        <html:hidden name="atdInBoxVO" property="atendimentoVO.dtSolicitacaoCancelamento"/>
                                                        <html:checkbox name="atdInBoxVO" property="operacaoWorkflow" indexed="true" value="true" onclick="controleDetalhe = false;" style="border=0; background-color=transparent;"></html:checkbox>
                                                    </vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>
                                                        <logic:iterate id="alertaVO" name="atdInBoxVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
                                                            <logic:notEmpty name="alertaVO" property="nmCor">
                                                                <input type="image" name="" src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" onclick="submitAlerta('<bean:write name="atdInBoxVO" property="atendimentoVO.idAtendimento" format="###"/>');" style="border=0; background-color=transparent;" title="Ver alertas">
                                                            </logic:notEmpty>
                                                        </logic:iterate>
                                                    </vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="atendimentoVO.nrProtocolo" format="###"/><html:hidden name="atdInBoxVO" property="atendimentoVO.idAtendimento"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="atendimentoVO.arvoreAtendimentoVO.descricaoCompleta"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="atendimentoVO.nrTelefone"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="atendimentoVO.WFEstadoVO.dsEstado"/> / <bean:write name="atdInBoxVO" property="atendimentoVO.WFSubEstadoVO.dsSubEstado"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="atendimentoVO.dtFimPausaAtendimento"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="atdInBoxVO" property="atendimentoVO.dtFechamento"/></vivo:tbRowColumn>
                                                </vivo:tbRow>
                                            <% } %>
                                        </logic:iterate>

                                        <!-- Atualiza labels com valor dos contadores -->
                                        <%
                                            qtdTotalPausa = qtdVermelhoPausa + qtdAmareloPausa + qtdNormalPausa;

                                            String scriptHtml = "";
                                            scriptHtml += "<script language=\"javascript\">";
                                            scriptHtml += "  lblVermelhoPausa.innerHTML = " + qtdVermelhoPausa + ";";
                                            scriptHtml += "  lblAmareloPausa.innerHTML = " + qtdAmareloPausa + ";";
                                            scriptHtml += "  lblNormalPausa.innerHTML = " + qtdNormalPausa + ";";
                                            scriptHtml += "  lblTotalPausa.innerHTML = " + qtdTotalPausa + ";";
                                            scriptHtml += "</script>";
                                        %>
                                        <%=scriptHtml%>
                                    </vivo:tbRows>
                                </vivo:tbTable>

                            </td>
                        </tr>
                    </table>
                     <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

                    <!--AÇÕES-->
                    <vivo:quadro id="qdOperacoes" height="35" width="780" label="Ação" scroll="N">
                        <table border="0" width="100%">
                            <tr>
                                <td width="50%" align="left">
                                    <img vspace="5" style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" id="btSuspeito" value="Voltar" onClick="window.location.href='/FrontOfficeWeb/index.jsp';"/>
                                </td>
                                <td width="50%" align="right">
                                    <acesso:controlHiddenItem nomeIdentificador="wor_wb_encaminhar">
                                        <img vspace="5" src="/FrontOfficeWeb/resources/images/bt_encaminhar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_encaminhar_over.gif" id="btEncaminhar" value="Encaminhar"  onClick='submitEncaminhar() ;' style="border:none"/>
                                    </acesso:controlHiddenItem>
                                    <acesso:controlHiddenItem nomeIdentificador="wor_wb_suspeito">
                                        <img  vspace="5" src="/FrontOfficeWeb/resources/images/bt_suspeito_over.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_suspeito_nrml.gif" id="btSuspeito"  value="Suspeito" style="border:none" onClick="submitSuspeito();"/>
                                    </acesso:controlHiddenItem>
                                </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                    <iframe scrolling="yes" style="visibility:hidden;" name="iframeEstado" height="1px" width="1px"></iframe>
                    <iframe scrolling="yes" style="visibility:hidden;" name="iframeUsuario" height="1px" width="1px"></iframe>
                </form>
            </vivo:sessao>

            <!--Quadro Flutuante-->
            <vivo:quadroFlutuante id="dvEncaminhar" idIframe="ifrmEncaminhar" width="780" height="290" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Encaminhar" onclick="submitPesquisar()"/>
            <vivo:quadroFlutuante id="dvSuspeito"   idIframe="ifrmSuspeito"   width="780" height="250" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Suspeito"   onclick="submitPesquisar()"/>
            <vivo:quadroFlutuante id="dvAlerta"     idIframe="ifrmAlerta"     width="720" height="220" spacesTop="190" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Alertas"    onclick="submitPesquisar()"/>
            <vivo:quadroFlutuante id="dvArvore"     idIframe="ifrmArvore"     width="720" height="220" spacesTop="190" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Árvore de Contato" scroll="auto"/>

            <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
            <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>

            <script language="javascript">

                // Array p/ controle de ativa/desativa elementos dos forms
                frmsState = null;

                function desativar_combos() {
                    if (frmsState == null) {
                        forms = document.forms;
                        frmsState = new Array(forms.length);

                        for (i=0;i<forms.length;i++) {
                            el = forms[i].elements;
                            elState = new Array(el.length);
                            frmsState[i] = elState;
                            for(j=0;j<el.length;j++){
                                elState[j]=el[j].disabled;
                                el[j].disabled=true;
                            }
                        }
                    }
                    return;
                }

                function ativar_combos() {
                    if (frmsState != null) {

                        forms = document.forms;

                        for (i=0;i<forms.length;i++) {
                            el = forms[i].elements;
                            elState = frmsState[i];
                            frmsState[i] = elState;
                            for(j=0;j<el.length;j++){
                                el[j].disabled=elState[j];
                            }
                        }
                    }
                    frmsState = null;
                    return;
                }

                function fecharQdFlutuante() {
                    dvEncaminhar.style.display='none';
                    dvSuspeito.style.display='none';
                    ativar_combos();
                }

                mostraBarraCTI();

                //verifica se usuario logado em CTI
                function mostraBarraCTI()
                {
                    tdBarraCTI.style.display = 'none';

                    //verifica se o objeto OCX está carregado
                    if(top.frameCTI.isCarregadoOCX())
                    {
                        //verifica se o agente está logado em CTI
                        if(top.frameCTI.isAgenteLogado())
                        {
                            tdBarraCTI.style.display = '';
                            //obtém a disponibilidade do usuário quanto ao CTI
                            obtemDisponibilidadeCTI();
                        }
                    }
                }


                //exibe a aba correta após a pesquisa básica ou avançada
                //exibicaoAbaPesquisas(<bean:write name="form" property="abaSelecionada"/>);

                //Controle do click no detalhe
                var controleDetalhe = true;

                //Controle max min
                var up = true;

                function resizePesquisa() {
                    //Rezise dos objetos
                }

                function resizeMaxMin() {
                    //Controle da visibilidade
                    tbConfiguracoes.style.display = (up ? "none" : "");

                    //Rezise dos objetos
                    tbProcessosTratamento_body.parentElement.style.height = (up ? "309px" : "161px");
                    tbProcessosPausa_body.parentElement.style.height      = (up ? "309px" : "161px");

                    //Atualiza a imagem de exibição
                    document.forms["formInBox"].elements["btMaxMin"].src = "<%=request.getContextPath()%>/resources/images/" + (up ? "bt_lupa_aba_down.gif" : "bt_lupa_aba.gif");

                    //Atualiza variável de controle
                    up = !up;
                }

                //Controle da exibição
                function exibicaoAbaPesquisas(index) {

                    if( index == 0) {
                        abaSelected(btAbaPesquisas, btCTIAtendente);

                        trPesquisa.style.display = 'none';
                        trEstadoAtendente.style.display = '';
                        document.getElementById("iFrTst1").src="test1.html";

                    } else {
                        abaSelected(btAbaPesquisas, btPesquisa);

                        trPesquisa.style.display = '';
                        document.getElementById("iFrTst2").src="test2.html";
                        trEstadoAtendente.style.display = 'none';
                    }

                    //atualiza o campo da aba que está selecionada para o retorno da pesquisa abrir a aba correta
                    document.forms["formInBox"].elements["abaSelecionada"].value = index;

                    if( ! up ) {
                        up = false;
                        resizeMaxMin();
                    }
                }

                function exibePesquisa(index) {
                    trPesquisa.style.display        = (index == 0 ? "none" : "");
                    trEstadoAtendente.style.display = (index == 1 ? "none" : "");
                }

                function exibicaoAbaInBox(index) {
                    //Exibição da aba
                    if( index == 0 ) abaSelected(btAbaInBox, btTratamento);
                    else if( index == 1 ) abaSelected(btAbaInBox, btPausa);

                    //Exibição do elemento
                    trProcessosTratamento.style.display = (index == 0 ? "" : "none");
                    trProcessosPausa.style.display      = (index == 1 ? "" : "none");
                }

                document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoInicio"].disabled = true;
                document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoFim"].disabled = true;
                document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaInicio"].disabled = true;
                document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaFim"].disabled = true;
                document.forms["formInBox"].elements["imgCalendDtFecIni"].style.display = 'none';
                document.forms["formInBox"].elements["imgCalendDtFecFim"].style.display = 'none';
                document.forms["formInBox"].elements["imgCalendDtAbIni"].style.display = 'none';
                document.forms["formInBox"].elements["imgCalendDtAbFim"].style.display = 'none';

                function habilitaData(valor) {
                    if(valor == 0) {
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoInicio"].value = "";
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoFim"].value = "";
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoInicio"].disabled = true;
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoFim"].disabled = true;
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaInicio"].disabled = false;
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaFim"].disabled = false;
                        document.forms["formInBox"].elements["imgCalendDtFecIni"].style.display = 'none';
                        document.forms["formInBox"].elements["imgCalendDtFecFim"].style.display = 'none';
                        document.forms["formInBox"].elements["imgCalendDtAbIni"].style.display = '';
                        document.forms["formInBox"].elements["imgCalendDtAbFim"].style.display = '';
                    }else{
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaInicio"].value = "";
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaFim"].value = "";
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoInicio"].disabled = false;
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoFim"].disabled = false;
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaInicio"].disabled = true;
                        document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaFim"].disabled = true;
                        document.forms["formInBox"].elements["imgCalendDtAbIni"].style.display = 'none';
                        document.forms["formInBox"].elements["imgCalendDtAbFim"].style.display = 'none';
                        document.forms["formInBox"].elements["imgCalendDtFecIni"].style.display = '';
                        document.forms["formInBox"].elements["imgCalendDtFecFim"].style.display = '';
                    }
                }

                function linhaSel( linha ) {
                    //Verifica os controles de check e lupa
                    if( ! controleDetalhe )  {
                        controleDetalhe = true;
                        return;
                    }
                    //Obtem os dados para obter detalhes
                    linha.cells(2).id = "tdLinhaAtendimentoWFInbox";
                    var idAtd = $('tdLinhaAtendimentoWFInbox').select('[class="classIdAtendimento"]')[0].innerText;
                    //Fim animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    //Obtém tela de detalhe
                    document.forms["formInBox"].target = "frameApp";
                    document.forms["formInBox"].action = linha.all['atendimentoVO.nmURLDados'].value + "?idAtendimento=" + idAtd;
                    document.forms["formInBox"].submit();
                }

                function limparPesquisa() {
                    //Limpa elementos
                    document.forms["formInBox"].elements["grupoSel"].value = "";
                    document.forms["formInBox"].elements["estadoSel"].value = "";
                    document.forms["formInBox"].elements["subEstadoSel"].value = "";
                    document.forms["formInBox"].elements["atdInBoxPesqVO.nrProtocolo"].value = "";
                    document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaInicio"].value = "";
                    document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaFim"].value = "";
                    document.forms["formInBox"].elements["atdInBoxPesqVO.nrLinha"].value = "";
                    document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoInicio"].value = "";
                    document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoFim"].value = "";
                    document.forms["formInBox"].elements["textoContato"].value = "";
                    document.forms["formInBox"].elements["atdInBoxPesqVO.idContato"].value = "";

                    //Limpa combos dinamicos
                    while(document.forms["formInBox"].elements["subEstadoSel"].options.length != 0)
                        document.forms["formInBox"].elements["subEstadoSel"].options.remove(0);
                }

                function submitPesquisar() {
                    ativar_combos();
                    if(verificaDatas()) {
                        //Liga animação
                        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                        dvEncaminhar.style.display='none';
                        dvSuspeito.style.display='none';
                        document.forms["formInBox"].method = "POST";
                        document.forms["formInBox"].action = "pesquisar.do";
                        document.forms["formInBox"].target = "";
                        document.forms["formInBox"].submit();
                    }
                }

                function submitEstado() {
                    //Liga animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

                    document.forms["formInBox"].method = "POST";
                    document.forms["formInBox"].action = "obterSubEstado.do";
                    document.forms["formInBox"].target = "iframeEstado";
                    document.forms["formInBox"].submit();
                }

                function submitEncaminhar() {
                    //Liga animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

                    dvEncaminhar.style.display = '';
                    document.forms["formInBox"].method = "POST";
                    document.forms["formInBox"].action = "encaminhar.do?idAtividade=3";
                    document.forms["formInBox"].target = "ifrmEncaminhar";
                    document.forms["formInBox"].submit();
                }

                function arvoreContato() {
                    //Liga animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

                    dvArvore.style.display = '';
                    document.forms["formInBox"].method = "POST";
                    document.forms["formInBox"].action = "obterArvoreContato.do";
                    document.forms["formInBox"].target = "ifrmArvore";
                    document.forms["formInBox"].submit();
                }

                function submitSuspeito() {
                    //Liga animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    var contSusp = 0;
                    var contNaoSusp = 0;
                    var aSuspeito = new Array();
                    aCheckbox = document.getElementsByTagName('input');
                    for(i=0;i<aCheckbox.length;i++){
                        if(aCheckbox[i].type == 'checkbox' && aCheckbox[i].checked == true){
                            //alert(aCheckbox[i].parentElement.parentElement.childNodes(2).innerText);
                            if(aCheckbox[i].parentElement.childNodes(0).value != ""
                               || aCheckbox[i].parentElement.all['atendimentoVO.idAtendimentoBaixaHistorico'].value != 0){
                                //alert(aCheckbox[i].parentElement.all['atendimentoVO.idAtendimentoBaixaHistorico'].value);
                                aCheckbox[i].checked = false;
                                aSuspeito[contSusp] = aCheckbox[i].parentElement.parentElement.childNodes(2).innerText;
                                contSusp++;
                            }
                            else
                                contNaoSusp++;
                        }
                    }

                    if(aSuspeito.length > 0 && contNaoSusp == 0){
                        top.frameApp.stopAnimation();
                        alert("Nenhum atendimento selecionado pode ser marcado como suspeito!")
                        return false;
                    }

                    var strSusp = '';

                    for(i=0;i<aSuspeito.length;i++){
                        if(i == aSuspeito.length-1)
                            strSusp = strSusp + aSuspeito[i];
                        else
                            strSusp = strSusp + aSuspeito[i] + ",";

                    }

                    if(aSuspeito.length > 0)
                        if(!(confirm("Os seguintes pocessos não podem ser marcados como suspeitos: " + strSusp + " . \nDeseja continuar?"))){
                            top.frameApp.stopAnimation();
                            return false;
                        }
                    dvSuspeito.style.display = '';
                    document.forms["formInBox"].method = "POST";
                    document.forms["formInBox"].action = "suspeito.do?idAtividade=6";
                    document.forms["formInBox"].target = "ifrmSuspeito";
                    document.forms["formInBox"].submit();
                }

                function submitDisponivel(inDisponibilidade) {
                    //Liga animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

                    document.forms["formInBox"].method = "POST";
                    document.forms["formInBox"].action = "analistaDisponivel.do?inDisponivelWF=" + inDisponibilidade;
                    document.forms["formInBox"].target = "";
                    document.forms["formInBox"].submit();
                }

                function obtemDisponibilidadeCTI() {
                    var estadoAgente = top.frameCTI.obtemEstadoAgente();
                    var loginOk = estadoAgente.search('logado');
                    var disponivelOk = estadoAgente.search('disponivel');

                    if(loginOk >= 0) {
                        if(disponivelOk >= 0) {
                            document.getElementById('imgDisponibilidade').src = '<%=request.getContextPath()%>/resources/images/icon_atend_disponivel.gif';
                        }
                        else {
                            document.getElementById('imgDisponibilidade').src = '<%=request.getContextPath()%>/resources/images/icon_atend_indisponivel.gif';
                        }
                    }
                    else {
                        document.getElementById('imgDisponibilidade').src = '<%=request.getContextPath()%>/resources/images/icon_atend_indisponivel.gif';
                    }
                }

                function mudaDisponibilidade() {
                    if(obtemEstadoLigacaoCTI()!=1)
                    {
                        // Executa chamada ao cti para marcar como disponível
                        top.frameCTI.alterarEstado();

                        //obtém a disponibilidade do usuário quanto ao CTI
                        obtemDisponibilidadeCTI();
                    }
                    else
                    {
                        alert('Estado com Ligação');
                    }
                }

                function obtemEstadoLigacaoCTI() {

                    var estadoLigacao = top.frameCTI.obtemEstadoLigacao();

                    var comLigacao = estadoLigacao.search('Com');
                    var semLigacao = estadoLigacao.search('Sem');

                    if(comLigacao >= 0) {
                        return 1;
                    }
                    else{
                        if(semLigacao >= 0){
                            return 2;
                        }
                    }
                }

                function receberChamada(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA) {

                    //Obtém tela de detalhe
                    document.forms["formInBox"].target = "frameApp";
                    document.forms["formInBox"].action = "/FrontOfficeWeb/workflow/AtendimentoDetalhe/detalheBegin.do?idAtendimento=" + NumProc;
                    document.forms["formInBox"].submit();
                }

                /*
                * Efetua algum tratamento quando a ligação for finalizada.
                */
                function semLigacao() {
                    alert('Ligação Finalizada');
                }

                /*
                function receberChamada(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA) {

                    var strAction = "receberChamada.do?";

                    strAction += "parametros.idUra=" + GrupOrigCh;
                    strAction += "&";
                    strAction += "parametros.idCallCenter=" + GrupDest;
                    strAction += "&";
                    strAction += "parametros.inSenhaValidada=";
                    strAction += (CliAut == 'S');
                    strAction += "&";

                    strAction += "parametros.nrLinha=" + NumLinCliUra;
                    strAction += "&";
                    strAction += "parametros.idTipoRelacionamento=";
                    if(IndTitular == 'C') {
                        strAction += '1';
                    }
                    if(IndTitular == 'U') {
                        strAction += '2';
                    }
                    if(IndTitular == 'N') {
                        strAction += '3';
                    }
                    strAction += "&";

                    document.forms["formInBox"].method = "POST";
                    document.forms['formInBox'].target = "";
                    document.forms['formInBox'].action = strAction;
                    document.forms['formInBox'].submit();
                }
                */

                function trocarCampanha(itemValue) {

                    // se tem campanha aberta deve fechar a campanha
                    var campanhas = top.frameCTI.obtemCampanhas();

                    var posHifen = campanhas.search('-');

                    //se há campanha anterior, deve ser fechada
                    if(posHifen >= 0) {
                        var campanhaAnterior = campanhas.substr(0, posHifen);
                        campanhaAnterior = Trim(campanhaAnterior);

                        //marcar inDisponibilidade
                        top.frameCTI.alterarEstadoEspecifico('N');

                        top.frameCTI.fecharCampanha(campanhaAnterior);
                    }

                    if(itemValue != -1) {
                        oOption = document.getElementById('numCampanhaSel');
                        for(i = 0; i < oOption.options.length; i++ ) {
                            if(oOption.options(i).value == itemValue) {
                                // abrir a nova campanha
                                top.frameCTI.abrirCampanha(oOption.options(i).text);

                                //marcar disponibilidade
                                top.frameCTI.alterarEstadoEspecifico('S');

                                break;
                            }
                        }
                    }

                    obtemDisponibilidadeCTI();
                }
                             // Função de Trim, pois o javascript não tem uma função/método correspondente
                function Trim(s) {
                    // Remove leading spaces and carriage returns
                    while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r')) {
                        s = s.substring(1,s.length);
                    }

                    // Remove trailing spaces and carriage returns
                    while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r')) {
                        s = s.substring(0,s.length-1);
                    }
                    return s;
                }

                function submitAlerta(linha) {
                    controleDetalhe = false;

                    //Liga animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

                    //Obtem os dados para obter detalhes
                    //var idAtd = linha.cells(2).innerText;

                    dvAlerta.style.display = '';
                    document.forms["formInBox"].method = "POST";
                    document.forms["formInBox"].action = "obterAlerta.do?idAtendimento=" + linha;
                    document.forms["formInBox"].target = "ifrmAlerta";
                    document.forms["formInBox"].submit();
                }

                function verificaDatas() {
                    dtFecIni = document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoInicio"].value;
                    dtFecFim = document.forms["formInBox"].elements["atdInBoxPesqVO.dtFechamentoFim"].value;
                    dtAbeIni = document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaInicio"].value;
                    dtAbeFim = document.forms["formInBox"].elements["atdInBoxPesqVO.dtAberturaFim"].value;

                    if(dtAbeIni.length > 0) {
                        if(!validaData(dtAbeIni)) {
                            alert('Data de abertura inicial deve estar no formato DD/MM/AAAA.');
                            return false;
                        }
                    }
                    if(dtAbeFim.length > 0) {
                        if(!validaData(dtAbeFim)) {
                            alert('Data de abertura final deve estar no formato DD/MM/AAAA.');
                            return false;
                        }
                    }
                    if(dtFecIni.length > 0) {
                        if(!validaData(dtFecIni)) {
                            alert('Data de fechamento inicial deve estar no formato DD/MM/AAAA.');
                            return false;
                        }
                    }
                    if(dtFecFim.length > 0) {
                        if(!validaData(dtFecFim)) {
                            alert('Data de fechamento final deve estar no formato DD/MM/AAAA.');
                            return false;
                        }
                    }

                    return true;
                }

                /*
                * Função para validar data.
                * Parâmetros : String data no formato DD/MM/YYYY.
                * Retorno    : boolean; true se data válida e false se data inválida.
                */
                function validaData(dataValidar) {
                    var dataOriginal = dataValidar;
                    try {
                        // tipo deve ser string e inicializado
                        if(dataValidar == undefined || typeof(dataValidar) != 'string') {
                            return false;
                        }

                        // verifica se foi digitado somente numero e o caracter /
                        dataValidar = replaceAll(dataValidar, '/', '');
                        if(isNaN(dataValidar)) {
                            return false;
                        }

                        // verifica se tem 8 digitos (sem as /s)
                        if(dataValidar.length != 8) {
                            return false;
                        }

                        // verifica se está no formato DD/MM/YYYY
                        dia = dataOriginal.substring(0, dataOriginal.indexOf('/'));
                        mes = dataOriginal.substring(dataOriginal.indexOf('/') + 1, dataOriginal.lastIndexOf('/'));
                        ano = dataOriginal.substring(dataOriginal.lastIndexOf('/') + 1, dataOriginal.length);

                        if((dia < 1 || dia > 31) || (mes < 1 || mes > 12) || (ano < 1000)) {
                            return false;
                        }

                        if(mes == 2 && dia > 29) {
                            return false;
                        }

                        if((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {
                            return false;
                        }

                        return true;
                    }
                    catch(ex) {
                        alert('erro');
                    }
                }

                /*
                * Função para trocar caracter em toda a string.
                * Parâmetros : String texto que contém o valor; String a encontrar; String substituta.
                * Retorno    : String com os valores alterados.
                */
                function replaceAll(texto, encontrar, substituir) {
                    var ret = texto;

                    do {
                        ret = ret.replace(encontrar, substituir);
                    }
                    while(ret.indexOf(encontrar) != -1);

                    return ret;
                }



                //verifica se há data de cancelamento para cada atendimento que está sendo exibido
                function verificaCancelamento(){
                    var contCancelado = 0;
                    var aCancelado = new Array();


                    aCheckbox = document.getElementsByTagName('input');
                    for(i=0;i<aCheckbox.length;i++){
                        //alert(aCheckbox[i].parentElement.parentElement.childNodes(2).innerText);
                        if(aCheckbox[i].type == 'checkbox'){
                            if(aCheckbox[i].parentElement.all['atendimentoVO.dtSolicitacaoCancelamento'].value != "" ){
                                //alert(aCheckbox[i].parentElement.all['atendimentoVO.dtSolicitacaoCancelamento'].value);
                                aCancelado[contCancelado] = aCheckbox[i].parentElement.parentElement.childNodes(2).innerText;
                                contCancelado++;
                            }
                        }
                    }

                    var strCancelados = "";
                    for(i=0;i<aCancelado.length;i++){
                        if(i == aCancelado.length-1)
                            strCancelados = strCancelados + aCancelado[i];
                        else
                            strCancelados = strCancelados + aCancelado[i] + ", ";
                    }
                    if(aCancelado.length > 0)
                        alert("Os seguintes processos foram cancelados:\n" + strCancelados);
                }

                verificaCancelamento();

                habilitaData( <bean:write name="form" property="optGrpSel"/> );

                //Fim animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            </script>

        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
