<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.AtendimentoFechamentoMassaVODocument.AtendimentoFechamentoMassaVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFechamentoMassaForm" />
<bean:define id="atdInfVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFechamentoMassaForm.atendimentoInformacaoVO" />
<bean:define id="total" name="form" property="atendimentoInformacaoVO.nrRegistros"/>
<bean:define id="total2" name="form" property="atendimentoInformacaoVO.totalRegistros"/>

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Workflow"/>
    <netui-template:setAttribute name="modulo" value="Fechamento Técnico Workflow"/>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="wor_fme_verpagina">
        <!--APLICAÇÃO MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
         <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

        <!--APLICACAO-->
        <vivo:sessao id="qdMain" height="470" width="790" label="Vivo Net >> Workflow" operacoes="Fechamento em Massa" scroll="n">

                <form action="encerrar.do" name="formFechMassa" method="post">
                <html:hidden property="fila" name="form"></html:hidden>
                <%--html:hidden property="idUsuario" name="form"></html:hidden>--%>
                <html:hidden property="idContato" name="form"></html:hidden>
                <html:hidden property="idContatoWF" name="form"></html:hidden>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                <vivo:abaGrupo id="btAbaPesquisas" width="350px" height="10px" styleClass="abatexto">
                    <vivo:abaItem id="btAnalise"  onclick="" value="Análise de Fechamento" select="S"/>
                    <vivo:abaItem id="btExecucao" onclick="" value="Execução"/>
                </vivo:abaGrupo>
                <table width="780" cellpadding="0" cellspacing="0" border="0" >
                    <!--ABA ANALISE-->
                    <tr id="trAnalise" name="trAnalise">
                        <td>
                            <table class="tbl_bggray" cellpadding="2" cellspacing="3" width="780" border="0">
                                <tr>
                                    <td width="45">Contato</td>
                                    <td width=""><html:text property="textoContato" name="form" style="width:250px;" readonly="true"/></td>
                                    <td width=""><img id="imgLupa" src="<%=request.getContextPath()%>/resources/images/lupa_bg_transp.gif" onclick="arvoreContato()" style='cursor:pointer' border="0"></td>
                                    <td width="">&nbsp;</td>
                                    <td width="">&nbsp;</td>
                                    <td align="right"><img vspace="6" id="bt01" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;" onClick="limpar(); return false" /></td>
                                </tr>
                                <tr>
                                    <td>Login</td>
                                    <td colspan="2"><html:text property="login" name="form" style="width:100px" maxlength="255"/></td>
                                    <td>Período de</td>
                                    <td align="left" valign="middle">
                                        <html:text property="dtSuspeitoInicio" name="form" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);"/><img align="middle" id="imgCalendDtAbIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtSuspeitoInicio', '%d/%m/%Y');">
                                        at&eacute;
                                        <html:text property="dtSuspeitoFim" name="form" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);"/><img align="middle" id="imgCalendDtAbFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtSuspeitoFim', '%d/%m/%Y');">
                                    </td>
                                    <td width="50" align="right">
                                    <acesso:controlHiddenItem nomeIdentificador="wor_fme_pesquisar">
                                        <img vspace="6" id="bt01" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" style="border:none;" onClick="submitPesquisar(); return false" />
                                    </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                            </table>
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                            <!-- variáveis que conterão as quantidades para cada alerta -->
                            <% int qtdVermelho = 0, qtdAmarelo = 0, qtdNormal = 0;%>

                                <table width="770" class="tbl_bgGray" align="center">
                                    <tr>
                                        <td width="10%" align="right">Vermelho: </td>
                                        <td width="5%"><div id="lblVermelho">0</div></td>
                                        <td width="10%" align="right">Amarelo: </td>
                                        <td width="5%"><div id="lblAmarelo">0</div></td>
                                        <td width="10%" align="right">Normal: </td>
                                        <td width="5%"><div id="lblNormal">0</div></td>
                                        <td width="20%" align="right">Registros Retornados:</td>
                                        <td width="5%"><div id="total"><bean:write name="form" property="atendimentoInformacaoVO.nrRegistros" format="####"/></div></td>
                                        <td width="20%" align="right">Registros Encontrados:</td>
                                        <td width="5%"><div id="total2"><bean:write name="form" property="atendimentoInformacaoVO.totalRegistros" format="###"/></div></td>
                                    </tr>
                                </table>
                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                            <vivo:tbTable selectable="true" layoutWidth="765" layoutHeight="290" tableWidth="765" styleId="tableTitle3" sortable="true" onRowClick="linhaSel(this);">
                                <vivo:tbHeader>
                                    <vivo:tbHeaderColumn width="5%"/>
                                    <vivo:tbHeaderColumn align="center" width="6%" type="string">Alerta</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="8%" type="number">Nº Processo</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="28%" type="string">Contato</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="12%" type="string">Nº Linha</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="25%" type="string">Estado / Subestado</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Suspeito</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="11%" type="string">Doc.</vivo:tbHeaderColumn>
                                </vivo:tbHeader>
                                <vivo:tbRows>
                                    <logic:iterate id="atdFechamentoMassaVO" name="form" property="atendimentoInformacaoVO.atendimentoFechamentoMassaVOArray" indexId="idx">
                                        <% String idClassRow = ""; %>
                                        <logic:iterate id="alertaVO" name="atdFechamentoMassaVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
                                            <bean:define name="alertaVO" property="nmCor" id="nmCor"/>
                                            <logic:notEmpty name="alertaVO" property="nmCor">
                                                <%idClassRow = nmCor.toString(); %>
                                            </logic:notEmpty>
                                        </logic:iterate>
                                        <!-- Faz a contagem de registros de acordo com o alerta -->
                                        <%
                                            qtdVermelho += idClassRow.equals("rowTabelaAlertaAlto") ? 1 : 0;
                                            qtdAmarelo += idClassRow.equals("rowTabelaAlertaMedio") ? 1 : 0;
                                            qtdNormal += idClassRow.equals("") ? 1 : 0;
                                            String textoContato = ((AtendimentoFechamentoMassaVO)atdFechamentoMassaVO).getAtendimentoVO().getArvoreAtendimentoVO().getDescricaoCompleta().replaceAll("/"," / ");
                                        %>
                                        <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
                                            <vivo:tbRowColumn><html:hidden name="atdFechamentoMassaVO" property="atendimentoVO.arvoreAtendimentoVO.idContato"/><html:checkbox name="atdFechamentoMassaVO" property="operacaoWorkflow" indexed="true" value="true" onclick="controleDetalhe = false;" style="border=0; background-color=transparent;"></html:checkbox></vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                                <logic:iterate id="alertaVO" name="atdFechamentoMassaVO" property="atendimentoVO.alertaVOArray" indexId="idxAlerta">
                                                    <logic:notEmpty name="alertaVO" property="nmCor">
                                                        <acesso:controlHiddenItem nomeIdentificador="wor_fme_pesqlista">
                                                        <input type="image" name="" src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" onclick="submitAlerta('<bean:write name="atdFechamentoMassaVO" property="atendimentoVO.idAtendimento" format="####"/>');" style="border=0; background-color=transparent;" title="Ver alertas">
                                                        </acesso:controlHiddenItem>
                                                    </logic:notEmpty>
                                                </logic:iterate>
                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="atdFechamentoMassaVO" property="atendimentoVO.nrProtocolo" format="####"/><html:hidden name="atdFechamentoMassaVO" property="atendimentoVO.idAtendimento"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><vivo:hint maxLength="30" spaces="S"><%=textoContato%></vivo:hint></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="atdFechamentoMassaVO" property="atendimentoVO.nrTelefone"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="atdFechamentoMassaVO" property="atendimentoVO.WFEstadoVO.dsEstado"/> / <bean:write name="atdFechamentoMassaVO" property="atendimentoVO.WFSubEstadoVO.dsSubEstado"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="atdFechamentoMassaVO" property="atendimentoVO.dtSuspeito"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                                <logic:equal name="atdFechamentoMassaVO" property="atendimentoVO.inDocumento" value="S">
                                                    <acesso:controlHiddenItem nomeIdentificador="wor_fme_pesqlista">
                                                    <input type="image" name="" src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" onclick="submitDocAssociado('<bean:write name="atdFechamentoMassaVO" property="atendimentoVO.idAtendimento" format="####"/>');" style="border=0; background-color=transparent;" title="Ver documento associado">
                                                    </acesso:controlHiddenItem>
                                                </logic:equal>
                                            </vivo:tbRowColumn>
                                        </vivo:tbRow>
                                    </logic:iterate>

                                </vivo:tbRows>
                            </vivo:tbTable>
<%
StringBuffer sbScriptHtml = new StringBuffer("<script language=\"javascript\">");
sbScriptHtml.append("lblVermelho.innerHTML=").append(qtdVermelho).append(";");
sbScriptHtml.append("lblAmarelo.innerHTML=").append(qtdAmarelo).append(";");
sbScriptHtml.append("lblNormal.innerHTML=").append(qtdNormal).append(";");
sbScriptHtml.append("total.innerHTML=").append(total).append(";");
sbScriptHtml.append("total2.innerHTML=").append(total2).append(";");
sbScriptHtml.append("</script>");%><%=sbScriptHtml.toString()%>

                            <table width="770" cellspacing="0" cellpadding="0" border="0">
                                <tr>
                                    <td align="left">
                                        <img hspace="10" vspace="6" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;"/></td>
                                    <td align="right">
                                        <acesso:controlHiddenItem nomeIdentificador="wor_fme_reanali">
                                            <img hspace="10" vspace="6" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_reanalise_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_reanalise_over.gif" style="border:none;" onClick="submitVoltar(); return false"/>
                                        </acesso:controlHiddenItem>
                                        <acesso:controlHiddenItem nomeIdentificador="wor_fme_encerrar">
                                            <img vspace="6" id="btFecharMassa" src="/FrontOfficeWeb/resources/images/bt_encerrar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_encerrar_over.gif" style="border:none;" onClick="submitEncerramento(); return false"/>
                                        </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <!--ABA EXECUÇÃO-->
                    <tr id="trExecucao">
                        <td>
                            <div id="dvEncerramento" style="display:none;">
                                <vivo:quadro id="qdEncerramento" width="780" height="450" label="Encerramento BKO" scroll="n">
                                    <IFRAME id="ifrmEncerramento" name="ifrmEncerramento" width="770" height="440" frameborder=0 scrolling="auto" ></IFRAME>
                                </vivo:quadro>
                            </div>

                            <div id="dvVoltar" style="display:none;">
                                <vivo:quadro id="qdVoltar" width="780" height="450" label="Voltar" scroll="n">
                                    <IFRAME id="ifrmVoltar" name="ifrmVoltar" width="770" height="440" frameborder=0 scrolling="auto" ></IFRAME>
                                    <iframe id="iframeUsuario" name="iframeUsuario" src="" style="visibility:hidden;" scrolling="no" frameborder="0" width="0" height="0"></iframe>
                                </vivo:quadro>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
        </vivo:sessao>

        <!--Quadro Flutuante-->
        <vivo:quadroFlutuante id="dvAlerta" idIframe="ifrmAlerta" width="443" height="220" spacesTop="190" spacesLeft="175" display="none" url="<%=request.getContextPath()%>" label="Alertas" onclick=""/>
        <vivo:quadroFlutuante id="dvDocAssociado" idIframe="ifrmDocAssociado" width="755" height="220" spacesTop="190" spacesLeft="20" display="none" url="<%=request.getContextPath()%>" label="Documento Associado" onclick=""/>
        <vivo:quadroFlutuante id="dvArvore" idIframe="ifrmArvore" width="720" height="220" spacesTop="190" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Árvore de Contato" scroll="yes"/>
        <vivo:quadroFlutuante id="qdDocTec" idIframe="ifrmDocTec" label="Documentos Técnicos" spacesLeft="20" spacesTop="70" url="<%=request.getContextPath()%>" display="none" height="430" width="755"/>

<script language="javascript">
    //Controle do click no detalhe
    var controleDetalhe = true;

    //Controle da exibição
    function exibicaoAbaPesquisas(index) {
        if( index == 0) {
            abaSelected(btAbaPesquisas, btExecucao);
            trAnalise.style.display  = 'none';
            trExecucao.style.display = '';
        } else {
            abaSelected(btAbaPesquisas, btAnalise);
            trAnalise.style.display  = '';
            trExecucao.style.display = 'none';
        }
    }

    function limpar(){
       document.all['textoContato'].value = "";
       document.all['idContato'].value = "";
       document.all['login'].value = "";
       document.all['dtSuspeitoInicio'].value = "";
       document.all['dtSuspeitoFim'].value = "";
    }

    function linhaSel( linha ) {
        //Verifica os controles de check e lupa
        if( ! controleDetalhe )  {
            controleDetalhe = true;
            return;
        }
        //Obtem os dados para obter detalhes
                var idAtd = linha.cells(2).childNodes[0].childNodes[1].value;
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        //Obtém tela de detalhe
        //document.forms["formFechMassa"].target = "frameApp";
        //document.forms["formFechMassa"].action = "/FrontOfficeWeb/workflow/AtendimentoDetalhe/detalheBegin.do?idAtendimento=" + idAtd;
        //document.forms["formFechMassa"].submit();
        fila = escape("/FrontOfficeWeb/workflow/AtendimentoFechamentoMassa/voltarFechamento.do");
        top.frameApp.location="../AtendimentoDetalhe/begin.do?idAtendimento=" + idAtd + "&fila=" + fila;
    }

    function submitAlerta(linha) {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        controleDetalhe = false;
        //Obtem os dados para obter detalhes
        //   var idAtd = linha.cells(2).innerText;
        dvAlerta.style.display = '';
        document.forms["formFechMassa"].method = "POST";
        document.forms["formFechMassa"].action = "obterAlerta.do?idAtendimento=" + linha;
        document.forms["formFechMassa"].target = "ifrmAlerta";
        document.forms["formFechMassa"].submit();
    }

    function arvoreContato() {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        dvArvore.style.display = '';
        document.forms["formFechMassa"].method = "POST";
        document.forms["formFechMassa"].action = "obterArvoreContato.do";
        document.forms["formFechMassa"].target = "ifrmArvore";
        document.forms["formFechMassa"].submit();
    }

    function submitDocAssociado(linha) {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        controleDetalhe = false;

        //Obtem os dados para obter detalhes
        //   var idAtd = linha.cells(2).innerText;

        dvDocAssociado.style.display = '';

        document.forms["formFechMassa"].method = "POST";
        document.forms["formFechMassa"].action = "obterDocAssociado.do?idAtendimento=" + linha;
        document.forms["formFechMassa"].target = "ifrmDocAssociado";
        document.forms["formFechMassa"].submit();
    }

    function submitEncerramento() {
        //Liga animação
        var idContato;
        var numContatos = 0;
        var contEncerramento = 0;

        aCheckbox=document.getElementsByTagName('input');

                for(i=0;i<aCheckbox.length;i++){
            if(aCheckbox[i].type == 'checkbox' && aCheckbox[i].checked == true){
                        if (contEncerramento == 0){
                    contEncerramento++;
                    idContato = aCheckbox[i].parentElement.all['atendimentoVO.arvoreAtendimentoVO.idContato'].value;
                        }else{
                            if(aCheckbox[i].parentElement.all['atendimentoVO.arvoreAtendimentoVO.idContato'].value!=idContato){
                        aCheckbox[i].checked=false;
                        numContatos++;
                    }
                }
            }
        }

                if (numContatos > 0){
            alert("Todos os processos seleccionados tem que pertenecer ao mesmo tipo de Contato");
            return;
        }

        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        escondeDiv();
        exibicaoAbaPesquisas(0);

        dvEncerramento.style.display='';
        document.forms["formFechMassa"].idContatoWF.value = idContato;
        document.forms["formFechMassa"].method = "POST";
        document.forms["formFechMassa"].action = "encerrar.do";
        document.forms["formFechMassa"].target = "ifrmEncerramento";
        document.forms["formFechMassa"].submit();
    }

    function submitVoltar() {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        escondeDiv();
        exibicaoAbaPesquisas(0);
        dvVoltar.style.display='';
        document.forms["formFechMassa"].method = "POST";
        document.forms["formFechMassa"].action = "voltar.do";
        document.forms["formFechMassa"].target = "ifrmVoltar";
        document.forms["formFechMassa"].submit();
    }

    function submitPesquisar() {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        salvaFiltros();
        escondeDiv();

        document.forms["formFechMassa"].method = "POST";
        document.forms["formFechMassa"].action = "pesquisar.do";
        document.forms["formFechMassa"].target = "";
        document.forms["formFechMassa"].submit();
    }

    function escondeDiv() {
        dvEncerramento.style.display='none';
        dvVoltar.style.display='none';
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

    function salvaFiltros() {
        f=document.forms["formFechMassa"]; filtro=top.frameCTI.filtro;
        filtro.massa.login = f.login.value;
        filtro.massa.dtSuspeitoInicio = f.dtSuspeitoInicio.value;
        filtro.massa.dtSuspeitoFim = f.dtSuspeitoFim.value;
        filtro.massa.textoContato = f.textoContato.value;
        filtro.massa.idContato = f.idContato.value;
    }

    // marcar que a tela eh fechamento em massa
    top.frameCTI.filtro.telaOrigem=3;

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

<logic:present parameter="Exibir_Msg" scope="request">
    alert('<%=request.getParameter("Exibir_Msg")%>');
</logic:present>
</script>

        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>