<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO"%>
<%@ page import="workflow.AtendimentoFila.AtendimentoFilaController.AtendimentoFilaForm"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%--@ taglib uri="/WEB-INF/weblogic-tags.tld" prefix="wl"--%>
<%@ page import="workflow.AtendimentoFila.AtendimentoFilaController.AtendimentoFilaForm"%>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm"/>
<bean:define id="gruposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm.atendimentoInformacaoVO.WFGrupoVOArray"/>
<bean:define id="regionaisVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm.atendimentoInformacaoVO.WFRegionalVOArray"/>
<bean:define id="usuariosVIVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm.atendimentoInformacaoVO.usuarioVIVOArray"/>
<bean:define id="atdFilaPesqVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm.atdFilaPesqVO"/>
<bean:define id="formCamposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm.formularioVO.formularioCampoVOArray"/>
<bean:define id="camposValorVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm.camposValorVO"/>
<bean:define id="ufVO"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm.formularioVO.UFVOArray"/>
<bean:define id="tipoLinhaVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm.formularioVO.tipoLinhaVOArray"/>
<acesso:controlInitEnv/>
<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="Workflow"/>
<netui-template:setAttribute name="modulo" value="Fila de Processos"/>
<netui-template:section name="headerSection">
    <link rel="stylesheet" type="text/css" href="../../resources/css/calendar.css">
    <script type="text/javascript" src="../../resources/scripts/calendar.js"></script>
    <script type="text/javascript" src="../../resources/scripts/validacao.js"></script>
    <script type="text/javascript" src="../../resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="../../resources/scripts/RWFFila.js"></script>
    <script type="text/javascript" src="../../resources/scripts/toolTip.js"></script>
    <script type="text/javascript" src="../../resources/scripts/formatPhoneNumber.js"></script>
    <script type="text/javascript">
        <!--
        function verificaCampos(){
            var f  = document.forms["formFila"];
            var op = f.elements['atdFilaPesqVO.nrProtocolo'];
            var ol = f.elements['atdFilaPesqVO.nrLinha'];
            try{
                if(op.value.length>0){
                    desabilitarCampos_PesquisarProcesso(op);
                }else if(ol.value.length>0){
                    desabilitarCampos_PesquisarLinha(ol);
                }
            }catch(e){
                alert(e.description);
            }
        }

        function carregaComboCampos(){
            dComboCampos.innerHTML=ifComboCampos.document.body.innerHTML;
            dComboCampos.style.display='';
            eval(zso);
        }

        function carregaCampos() {
            dvd.innerHTML=iframeValoresDominio.document.body.innerHTML;
            document.forms[0].tipoLinhaSel.value=-1;
            document.forms[0].regionalSel.value= -1;
            submitTipoLinhaUF();
            dvd.style.display='';
            f.valor.value="";
            eval(zso);
        }

        function carregaFilas() {
            df.innerHTML=ifrFilas.document.body.innerHTML;
            df.style.display='';
            DoResizeHeaderTableVivo();
            eval(zso);
        }

        function validaDataOnBlur(data){
            if(data.value == '')
                return false;
            if(!validaData(data.value)){
                data.value = '';
                data.focus();
                alert("Data inválida");
            }
        }

        function retornarAnterior()  {
            //extra_vars="";
            //filtro = top.frameCTI.filtro;
            //if (filtro.telaOrigem==1) {
            //    if (filtro.psqAv!=null) {
            //        for (i=0; i<filtro.psqAv.length; i++) {
            //            extra_vars+='&psqIdCampo='+filtro.psqAv[i].idCampo;
            //            extra_vars+='&psqNmCampo='+filtro.psqAv[i].nmCampo;
            //            extra_vars+='&psqTpComparacao='+filtro.psqAv[i].tpComparacao;
            //            extra_vars+='&psqDsComparacao='+filtro.psqAv[i].dsComparacao;
            //            extra_vars+='&psqValor='+filtro.psqAv[i].valor;
            //            extra_vars+='&psqIdFormularioCampoValor='+filtro.psqAv[i].idFormularioCampoValor;
            //        }
            //    }
            //}
            //if (filtro.telaOrigem==3) {
            //    extra_vars +=
            //   "&login="+filtro.massa.login+
            //    "&dtSuspeitoInicio="+filtro.massa.dtSuspeitoInicio+
            //    "&dtSuspeitoFim="+filtro.massa.dtSuspeitoFim+
            //    "&textoContato="+filtro.massa.textoContato+
            //    "&idContato="+filtro.massa.idContato;
            //}
            dvEncaminhar.style.display='none';
            document.getElementById("ifrmdvEncaminhar").style.display='none';
            dvSuspeito.style.display='none';
            document.getElementById("ifrmdvSuspeito").style.display='none';
            ativar_combos();
            document.forms[0].method = "POST";
            document.forms[0].action = "/FrontOfficeWeb/workflow/AtendimentoWorkflow/atendimentoWorkflowVoltar.do";
            document.forms[0].target = "iframeUsuario";
            document.forms[0].submit();
            //window.location.href='/FrontOfficeWeb/workflow/AtendimentoFila/begin.do?voltar=1'+extra_vars;
        }

        function testaEnter() {
            if (window.event.keyCode==13) {
                return false;
            } else {
                return true;
            }
        }

        function getQtdeProcessos(){
            /*
             * Chama método para busca da quantidade total de processos.
             * As tres linhas abaixo sao apenas exemplo para chamada de método e definicao do iframe escondido como target.
             */
            document.forms[0].action = "contarTotalProcessos.do?";
            document.forms[0].target = "hiddenIframe";
            //parent.mostrar_div();
            document.forms[0].submit();
            //Fake
        }
        -->
    </script>
</netui-template:section>
<netui-template:section name="bodySection">
<%  String titulo = "Fila de Processos";
    //titulo = ("1".equals(request.getParameter("inResp"))?"Resposta ao Cliente":"");
    if ("1".equals(request.getParameter("inResp"))) titulo = "Fila de Processos Resposta ao Cliente";
    if ("2".equals(request.getParameter("inCRI"))) titulo = "Fila de Processos CRI";%>
<acesso:controlHiddenItem nomeIdentificador="wor_wf_verpagina">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="qdMain" height="470" width="790" label="Vivo Net >> Workflow " operacoes="<%=titulo%>" scroll="N">
    <form action="begin" name="formFila" id="formFila" method="POST" onKeyPress="return testaEnter();">
    <html:hidden property="fila" name="form"></html:hidden>
    <html:hidden property="inCRI" name="form"></html:hidden>
    <html:hidden property="inResp" name="form"></html:hidden>
    <%--html:hidden property="idUsuario" name="form"></html:hidden>--%>
    <html:hidden property="abaSelecionada" name="form"></html:hidden>
    <html:hidden property="atdFilaPesqVO.idContato" name="form"></html:hidden>
    <input type="hidden" name="somaDias">
    <div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
    <iframe name="hiddenIframe" width="0" height="0" frameborder="0"></iframe>
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td>
                <vivo:abaGrupo id="btAbaPesquisas" width="350px" height="10px" styleClass="abatexto">
                    <acesso:controlHiddenItem nomeIdentificador="wor_wf_abapesquisabasica">
                        <vivo:abaItem id="btPesquisaBasica" onclick="exibicaoAbaPesquisas(1); top.frameCTI.filtro.ultimaTab=1;" value="Pesquisa Básica" select="S"/>
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="wor_wf_abapesquisaavancada">
                        <vivo:abaItem id="btPesquisaAvancada" onclick="exibicaoAbaPesquisas(0);top.frameCTI.filtro.ultimaTab=0;" value="Pesquisa Avançada"/>
                    </acesso:controlHiddenItem>
                </vivo:abaGrupo>
            </td>
            <td width="20">
                <img id="btMaxMin" name="btMaxMin" title="Clique para minimizar/maximizar os parâmetros de pesquisa" src="/FrontOfficeWeb/resources/images/bt_lupa_aba.gif" style="cursor:pointer" onclick="reziseMaxMin();">
            </td>
        </tr>
    </table>
    <table id="tbConfiguracoes" cellpadding="0" cellspacing="0" border="0">
        <tr id="trPesquisaBasica">
            <td>
                <table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
                    <tr>
                        <td>Regional</td>
                        <td>
                            <html:select name="form" property="regionalSelecionada" title="regionalSelecionada" style="width=250px" onchange="focaTipCampos(this.options[this.selectedIndex].text,this, 10, 85, 35);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 10, 85, 35);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 10, 85, 35);" onblur="HideTip();" onmouseout="HideTip();">
                                <html:option value="-1" key="regionalSelecionada" >&nbsp;</html:option>
                                <html:options collection="regionaisVO" property="idRegional" labelProperty="dsRegional"/>
                            </html:select>
                        </td>
                        <%--wl:cache name="estados_subestados_FILA" timeout="30s"--%>
                        <%--jsp:include page="LoadEstados.do" flush="true"/--%>
                <bean:define id="estadosVO" name="form" property="estadoVO.WFEstadoVOArray"/>
                        <td>Estado</td>
                        <td>
                            <html:select name="form" property="estadoSel" title="estadoSel" style="width=250px" onchange="loadSubEstados(this.value);">
                                <html:option value="-1" key="estadoSel">&nbsp;</html:option>
                                <html:options collection="estadosVO" property="idEstado" labelProperty="dsEstado" />
                            </html:select>
                        </td>
                    </tr>
                    <script>
                        var arrCombos = new Array();
                        <logic:iterate id="estadosVO" name="form" property="estadoVO.WFEstadoVOArray" indexId="idxEstados">
                        var combo=document.createElement("<SELECT NAME='subEstadoSel'>");
                        combo.idEstado='<bean:write name="estadosVO" property="idEstado"/>';
                        combo.inFechadoCancelado='<bean:write name="estadosVO" property="inFiltro"/>';
                        <logic:iterate id="subEstadosVO" name="estadosVO" property="WFSubEstadoVOArray" indexId="idxSubEstados">
                        var opt=document.createElement("OPTION");
                        opt.value='<bean:write name="subEstadosVO" property="idSubEstado"/>';
                        opt.text='<bean:write name="subEstadosVO" property="dsSubEstado"/>';
                        combo.add(opt);
                        </logic:iterate>
                        arrCombos[arrCombos.length]=combo;
                        </logic:iterate>
                        function loadSubEstados(id) {
                            if(id==-1){
                                tdSubEstados.innerHTML="<select name='subEstadoSel' style='width:250px'></select>";
                                inFechadoCancelado=-1;
                                return;
                            }
                            for (var i=0;i<arrCombos.length;i++) {
                                if(arrCombos[i].idEstado==id){
                                    tdSubEstados.innerHTML=arrCombos[i].outerHTML;
                                    tdSubEstados.children[0].name='subEstadoSel';
                                    tdSubEstados.children[0].style.width='250px';
                                    inFechadoCancelado=arrCombos[i].inFechadoCancelado;
                                    break;
                                }
                            }
                        }

                        var moveToolTip = true;;
                        xBump=yBump=10;
                        MSIE=document.all;
                        NS6=document.getElementById&&!document.all;
                        if(MSIE||NS6){
                            ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
                        }
                        function carregaToolTip(content) {
                            ttipObj.innerHTML=content;
                            ttipObj.style.display='';eval(zso)
                        }
                    </script>
                    <%--/wl:cache--%>
                    <tr>
                        <td>Grupo</td>
                        <td>
                            <html:select name="form" property="grupoSel" title="grupoSel" style="width=250px" onchange="submitGrupo();focaTipCampos(this.options[this.selectedIndex].text,this, 10, 85, 35);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 10, 85, 35);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 10, 85, 35);" onblur="HideTip();" onmouseout="HideTip();">
                                <html:option value="-1" key="grupoSel" >&nbsp;</html:option>
                                <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
                            </html:select>
                        </td>
                        <td>Sub-Estado</td>
                        <td id="tdSubEstados">
                            <html:select name="form" property="subEstadoSel" title="subEstadoSel" style="width=250px">
                                <html:option value="-1" key="subEstadoSel">&nbsp;</html:option>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Usuário</td>
                        <td>
                            <html:select name="form" property="usuarioSel" title="usuarioSel" style="width=250px">
                                <html:option value="-1" key="usuarioSel">&nbsp;</html:option>
                                <html:options collection="usuariosVIVO" property="idPessoaUsuario" labelProperty="nmLoginUsuario"/>
                            </html:select>
                        </td>
                        <td> <!-- actionForm.optGrpSel -->
                            <netui:radioButtonGroup dataSource="{pageContext.form.optGrpSel}" defaultValue="" tagId="optGrpSel">
                                <netui:radioButtonOption value="0" styleClass="radio" onClick="habilitaData(this.value);">Abertura de</netui:radioButtonOption>
                            </netui:radioButtonGroup>
                        </td>
                        <td>
                            <html:text property="atdFilaPesqVO.dtAberturaInicio" name="form" size="10" maxlength="10"  onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                            <img id="imgCalendDtAbIni" src="../../resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="onClickCalendar('atdFilaPesqVO.dtAberturaInicio');/*return showCalendar('atdFilaPesqVO.dtAberturaInicio', '%d/%m/%Y');*/">&nbsp;Até&nbsp;
                            <html:text property="atdFilaPesqVO.dtAberturaFim" name="form" size="10" maxlength="10"  onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                            <img id="imgCalendDtAbFim" src="../../resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="onClickCalendar('atdFilaPesqVO.dtAberturaFim');/*return showCalendar('atdFilaPesqVO.dtAberturaFim', '%d/%m/%Y');*/">
                        </td>
                    </tr>
                    <tr>
                        <td>Protocolo</td>
                        <td>
                            <html:text property="atdFilaPesqVO.nrProtocolo" name="form" maxlength="30" style="width:80px" onkeyup="Formatar(this.value, this.form.name, this.name, 'numero');desabilitarCampos_PesquisarProcesso(this);" onchange="Formatar(this.value, this.form.name, this.name, 'numero');desabilitarCampos_PesquisarProcesso(this);"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Linha&nbsp;&nbsp;
                            <html:text property="atdFilaPesqVO.nrLinha" name="form" maxlength="14" style="width:90px" onkeyup="maskPhoneNumberObj(this);desabilitarCampos_PesquisarLinha(this);" />
                        </td>
                        <td><!-- actionForm.optGrpSel -->
                            <netui:radioButtonGroup dataSource="{pageContext.form.optGrpSel}" defaultValue="" tagId="optGrpSel">
                                <netui:radioButtonOption styleClass="radio" value="1" onClick="habilitaData(this.value);">Fechamento de</netui:radioButtonOption>
                            </netui:radioButtonGroup>
                        </td>
                        <td>
                            <html:text property="atdFilaPesqVO.dtFechamentoInicio" name="form" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                            <img id="imgCalendDtFecIni" src="../../resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="onClickCalendar('atdFilaPesqVO.dtFechamentoInicio');/*return showCalendar('atdFilaPesqVO.dtFechamentoInicio', '%d/%m/%Y');*/">&nbsp;Até&nbsp;
                            <html:text property="atdFilaPesqVO.dtFechamentoFim" name="form" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                            <img id="imgCalendDtFecFim" src="../../resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="onClickCalendar('atdFilaPesqVO.dtFechamentoFim');/*return showCalendar('atdFilaPesqVO.dtFechamentoFim', '%d/%m/%Y');*/">
                        </td>
                    </tr>
                    <tr>
                        <td>Processo</td>
                        <td>
                            <html:text property="atdFilaPesqVO.idAtendimento" name="form" maxlength="30" style="width:80px" onkeyup="Formatar(this.value, this.form.name, this.name, 'numero');" onchange="Formatar(this.value, this.form.name, this.name, 'numero');"/>
                        </td>
                        <td colspan="2">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td width="310">
                                        Contato
                                        <html:text property="textoContato" name="form" maxlength="255" style="width:200px" readonly="true"/>
                                        <img action="obterArvoreContato"
                                        	 id="imgLupa"
                                        	 href='';
                                        	 class="button"
                                        	 src="../../resources/images/lupa_bg_transp.gif"
                                        	 onClick="showArvoreContato(); return false;"
                                        	 style="cursor:pointer;border:0px;" />
                                    </td>
                                    <td width="90">
                                        <acesso:controlHiddenItem nomeIdentificador="wor_wf_pesquisar">
                                            <img style="border:0px;"
                                            	 action="pesquisar"
                                            	 id="btPesquisar"
                                            	 class="button"
                                            	 src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"
                                            	 onClick="submitPesquisaBasica(); return false;" />
                                        </acesso:controlHiddenItem>
                                    </td>
                                    <td><img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" id="btLimpar" value="Limpar" onClick="execLimparPesquisa(); return false;"/></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr id="trPesquisaAvancada" style="display:none;">
            <td>
                <table class="tbl_bggray" cellpadding="2" cellspacing="3" width="780" border="0">
                    <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td>
                                        <table border="0">
                                            <tr>
                                                <td align="right" height="37">Campo:</td>
                                                <td>
                                                    <iframe id="ifComboCampos" style="visibility:hidden" class="tbl_bggray" src="" height="0" width="0" frameborder="0"></iframe>
                                                    <div id="dComboCampos" style="display:none"><a href="obterComboCampos.do"></a></div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td>
                                        <div id="divFormSemDominio" style="display:none">
                                            <table border="0">
                                                <tr>
                                                    <td align="right">Comparação:</td>
                                                    <td>
                                                        <html:select name="form" property="comparacaoSel" title="comparacaoSel" style="width=75px">
                                                            <html:option value="0" key="comparacaoSel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=</html:option>
                                                            <html:option value="1" key="comparacaoSel">&nbsp;&nbsp;&nbsp;&nbsp;<></html:option>
                                                            <html:option value="2" key="comparacaoSel">&nbsp;&nbsp;contém</html:option>
                                                        </html:select>
                                                    </td>
                                                    <td align="right">Valor:</td>
                                                    <td><html:text name="form" property="valor" maxlength="250" style="width=274px"/></td>
                                                    <td>
                                                        <acesso:controlHiddenItem nomeIdentificador="wor_wf_adicionar">
                                                        	<button style="width:15px;height:20px" styleClass="btTemplate" id="idAdicionar" type="button" value="+" action="adicionarItem" onClick="submitPesquisaAvancadaAdicionarItem(); return false;"/>
                                                        </acesso:controlHiddenItem>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div id="divFormComDominio" style="display:none">
                                            <table border="0">
                                                <tr>
                                                    <td align="right">Tipo Linha:</td>
                                                    <td>
                                                        <html:select name="form" property="tipoLinhaSel" style="width=115px" onchange="submitTipoLinhaUF()">
                                                            <html:option value="-1" key="tipoLinhaSel">-- Selecione --</html:option>
                                                            <html:options collection="tipoLinhaVO" property="id" labelProperty="descricao"/>
                                                        </html:select>
                                                    </td>
                                                    <td align="right">Regional:</td>
                                                    <td>
                                                        <html:select name="form" property="regionalSel" style="width=50px" onchange="submitTipoLinhaUF()">
                                                            <html:option value="-1" key="regionalSel"> --- </html:option>
                                                            <html:options collection="ufVO" property="idUF" labelProperty="sgUF"/>
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <div id="divValorDominio" style="display:none;">Valor:
                                                            <html:select name="form" property="valorSel" title="valorSel" style="width=180px">
                                                                <html:option value="-1" key="valorSel">-- Selecione --</html:option>
                                                            </html:select>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <acesso:controlHiddenItem nomeIdentificador="wor_wf_adicionar">
                                                            <button style="width:15px;height:20px" styleClass="btTemplate" id="idAdicionar" type="button" value="+" action="adicionarItem" onClick="submitPesquisaAvancadaAdicionarItem(); return false;"/>
                                                        </acesso:controlHiddenItem>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <iframe id="iframeValoresDominio" name="iframeValoresDominio" style="visibility:hidden" width="0px" height="0px"></iframe>
                            <div id="dvd">
                                <vivo:quadro id="qdPesquisaWhere" height="125" width="765" label="&nbsp;Condições para Pesquisa Selecionada" scroll="N">
                                    <vivo:tbTable selectable="true" layoutWidth="735" layoutHeight="90" tableWidth="735" styleId="tbListaPesquisa" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="left" width="40%" type="string">Campo</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Comparação</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left" width="40%" type="string">Valor</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Exclusão</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <logic:iterate id="comparacaoVO" name="form" property="atdFilaPesqVO.WFPesquisaAvancadaVO.WFPesquisaAvancadaComparacaoVOArray" indexId="idxComp">
                                                <vivo:tbRow key="dsCampo">
                                                    <vivo:tbRowColumn><bean:write name="comparacaoVO" property="nmCampo"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="comparacaoVO" property="dsComparacao"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="comparacaoVO" property="valor"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>
                                                        <center>
                                                            <a href="/FrontOfficeWeb/workflow/AtendimentoFila/excluirItem.do" style="cursor:pointer;border:0px;" onclick="deleteFiltroItem('<%=idxComp%>'); return false;">
                                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" style="cursor:pointer;border:0px;"/>
                                                            </a>
                                                        </center>
                                                    </vivo:tbRowColumn>
                                                </vivo:tbRow>
                                            </logic:iterate>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                </vivo:quadro>
                            </div>
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                            <table border="0" cellpadding="0" width="100%">
                                <tr>
                                    <td valign="middle" align="right" width="50%">
                                        <acesso:controlHiddenItem nomeIdentificador="wor_wf_pesquisaavancada">
                                            <img action="pesquisarAvancado" onClick="submitPesquisaAvancada(); return false;" style="border:0px;" id="idPesquisarAvancado" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"></img>
                                        </acesso:controlHiddenItem>
                                    </td>
                                    <td valign="middle" align="left" width="50%">
                                        <img style="border:0px;" action="limparPesquisaAvancada" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" id="idLimparAvancado" onClick="submitLimparPesquisaAvancada(); return false;"></img>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <table class="tbl_bggray" width="780" cellspacing="0" cellpadding="0" border="0" height="24">
        <tr>
            <td width="104" align="center" height="24">Atualização:  <bean:write name="form" property="atualizacao" format="###"/> min</td>
            <td width="115" align="center">Vermelho: <span id="lblVermelho">0</span></td>
            <td width="110" align="center">Amarelo: <span id="lblAmarelo">0</span></td>
            <td width="95"  align="center">Normal: <span id="lblNormal">0</span></td>
            <td width="155" align="center">Registros Retornados: <span id="total">
                <bean:write name="form" property="atendimentoInformacaoVO.nrRegistros" format="###"/></span>
            </td>
            <td width="141" align="right" valign="middle">Registros Encontrados:
                <img align="absmiddle" title="Exibir quantidade de processos existentes de acordo com os filtros selecionados." src="/FrontOfficeWeb/resources/images/bt_getprocessos.gif" border="0" style="cursor:pointer;" onclick="submitPesquisaTotalResultado();return false;">
            </td>
            <td width="60"  align="left">&nbsp;<span id="total2" style="color:#1865c5;font-weight:bold;"></span></td>
        </tr>
    </table>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <iframe id="ifrFilas" name="ifrFilas" style="visibility:hidden" width="0" height="0"></iframe>
    <div id="df">
        <vivo:tbTable selectable="true" layoutWidth="760" layoutHeight="183" tableWidth="760" styleId="tbResultadoPesquisa" sortable="true" onRowClick="linhaSel(this);">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="7%" type="string">Alerta</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="14%" type="string">Protocolo</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="14%" type="string">Processo</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="15%" type="string">Contato</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">N. Linha</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="14%" type="string">Estado / Sub-Estado</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Operador</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="date">Abertura</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="date">Fechamento</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows></vivo:tbRows>
        </vivo:tbTable>
    </div>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:quadro id="qdOperacoes" height="35" width="780" label="Ação" scroll="N">
        <table border="0" width="100%">
            <tr>
                <td width="50%" align="left">
                    <img vspace="6" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;"/>
                </td>
                <td width="50%" align="right">
                    <logic:equal value="2" name="form" property="inCRI">
                        <img action="trocar" style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_trocar_nrml.gif" id="btEncaminhar" onClick='submitTrocar(); return false;'></img>
                    </logic:equal>
                    <acesso:controlHiddenItem nomeIdentificador="wor_wf_encaminhar">
                        <img action="encaminhar" style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_encaminhar_nrml.gif" id="btEncaminhar" onClick='submitEncaminhar(); return false;'></img>
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="wor_wf_suspeito">
                    <logic:equal value="0" name="form" property="inCRI">
                            <img action="suspeito" style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_suspeito_over.gif" id="btSuspeito" onClick="submitSuspeito(); return false;"></img>
                        </logic:equal>
                    </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
    </vivo:quadro>
    <iframe scrolling="yes" style="visibility:hidden;" name="iframeEstado" height="1px" width="1px"></iframe>
    <iframe scrolling="yes" style="visibility:hidden;" name="iframeUsuario" height="1px" width="1px"></iframe>
    <iframe scrolling="yes" style="visibility:hidden;" name="iframeCampo" height="1px" width="1px"></iframe>
    </form>
    </vivo:sessao>
    <vivo:quadroFlutuante id="dvTrocar" idIframe="ifrmTrocar" width="780" height="290" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Trocar" onclick="ifrmTrocar.fechar()"/>
    <iframe id='ifrmdvEncaminhar' name='ifrmdvEncaminhar' scrolling='no' height='21' width='780' style='z-index:997;position:absolute;top:190;left:10;display:none;'></iframe>
    <vivo:quadroFlutuante id="dvEncaminhar" scroll="no" idIframe="ifrmEncaminhar" width="780" height="290" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Encaminhar" onclick="ifrmEncaminhar.fechar()"/>
    <iframe id='ifrmdvSuspeito' name='ifrmdvSuspeito' scrolling='no' height='21' width='780' style='z-index:997;position:absolute;top:190;left:10;display:none;'></iframe>
    <vivo:quadroFlutuante id="dvSuspeito"   idIframe="ifrmSuspeito"   width="780" height="260" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Suspeito"   onclick="ifrmSuspeito.fechar()"/>
    <vivo:quadroFlutuante id="dvAlerta" idIframe="ifrmAlerta" width="443" height="220" spacesTop="190" spacesLeft="175" display="none" url="<%=request.getContextPath()%>" label="Alertas" onclick=""/>
    <vivo:quadroFlutuante id="dvArvore" idIframe="ifrmArvore" width="720" height="220" spacesTop="190" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Árvore de Contato" scroll="auto" onclick="ativar_combos();"/>
    <script>
        f=document.forms["formFila"];
        filtro=top.frameCTI.filtro;
        var controleDetalhe=true;
        var abaPesquisaBasica=true;
        var up=true;
        frmsState=null;
        pava=true;
        exibicaoAbaPesquisas(<bean:write name="form" property="abaSelecionada"/>);
        <%--
        loadSubEstados('<%=((AtendimentoFilaForm)form).getEstadoSel()%>');
        habilitaData(<bean:write name="form" property="optGrpSel"/>);
        f.estadoSel.value='<%=((AtendimentoFilaForm)form).getEstadoSel()%>';
        f.subEstadoSel.value='<%=((AtendimentoFilaForm)form).getSubEstadoSel()%>';
        <logic:equal parameter="voltar" value="1">
        f.elements['atdFilaPesqVO.nrLinha'].focus();
        f.elements['atdFilaPesqVO.nrLinha'].blur();
        submitPesquisaBasica();
        alert("TesteReload");
        </logic:equal>
        --%>
        <logic:equal parameter="voltar" value="1">
        carregaFiltros();
        exibicaoAbaPesquisas(filtro.ultimaTab);
        if (filtro.ultimaTab==1) {
            submitPesquisa(1);
        } else {
            submitPesquisaAvancada();
        }
        </logic:equal>
        <logic:notPresent parameter="voltar">
        filtro.ultimaTab=1;
        eval(zso);
        </logic:notPresent>
        // marcar a tela de origem
        filtro.telaOrigem=1;
        verificaCampos();
    </script>
</acesso:controlHiddenItem>
</netui-template:section>
</netui-template:template>