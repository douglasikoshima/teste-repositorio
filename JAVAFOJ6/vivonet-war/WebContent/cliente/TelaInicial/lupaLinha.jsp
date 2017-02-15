<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="cliente.TelaInicial.TelaInicialController.LupaLinhaForm"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm" type="cliente.TelaInicial.TelaInicialController.LupaLinhaForm"/>
<html>
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            parent.oculta_div();
            new Ajax.Request('getQuantidadeLinhas.do', {
                method: 'post',
                onSuccess: function(e) {
                    var dom = parseXml(e.responseText);
                    var jsonString = xml2json(dom, '');
                    var jsonObj = jsonString.evalJSON();

                    $('qtdLinhasTotal').update(jsonObj.LupaLinhaVO.qtdLinhas);
                    $('qtdLinhasAtivas').update(jsonObj.LupaLinhaVO.qtdLinhasAtivas);
                    $('qtdLinhasCanceladas').update(jsonObj.LupaLinhaVO.qtdLinhasInativas);
                    $('quantidade_linhas').show();
                    $('loading').hide();

                    if (linhaSelecionada() != undefined && linhaSelecionada()) {
                        document.getElementById('tbAbas').style.visibility = 'visible';
                    }

                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

                }, onCreate: function() {
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                }, onComplete: function() {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                }, onException: function(e) {
                    alert(e.description);
                }, on406: function(e) {
                    window.top.frameApp.createErrorPopUp('erroAlterarNumeroSMS', e.statusText, e.responseText, false);
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                }
            });
            if (document.getElementById('tbAbas').style.visibility == 'visible') {
                loadAbaGeral();
            }
        -->
    </SCRIPT>
    <script type="text/javascript">
        <!--
            var inLegadoInicial = <%=((ParametrosVO)request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getInLegado()%>;
            function linhaSelecionada(){
                if(document.forms[0].rdoLinhas){
                    if(document.forms[0].rdoLinhas.length){
                        for(var i=0; i < document.forms[0].rdoLinhas.length; i++){
                           if(document.forms[0].rdoLinhas[i].checked){
                              return true;
                           }
                        }
                    }else{
                        if(document.forms[0].rdoLinhas.checked){
                            return true;
                        }
                    }
                }else{
                    return false;
                }
            }

            function loadAbaGeral(){
                var idLinha = document.getElementById('idLinha').value;
                var nrLinhaAG = document.getElementById('nrCodAreaG').value + document.getElementById('nrLinhaG').value;
                var dyniframe = document.getElementById("iframeLupaLinha");
                dyniframe.src = "pesquisarDetalheLinha.do?nrLinhaAG="+nrLinhaAG+"&redirect=GERAL"+"&idLinha="+idLinha;
            }

            function CarregaAba(nome){
                var idLinha = document.getElementById('idLinha').value;
                var nrLinhaAG = document.getElementById('nrCodAreaG').value + document.getElementById('nrLinhaG').value;
                var dyniframe = document.getElementById("iframeLupaLinha");
                var pagina = "";
                if(nome=="btx01") pagina = "loadAbaGeral.do?nrLinhaAG="+nrLinhaAG+"&redirect=GERAL"+"&idLinha="+idLinha;
                if(nome=="btx02") pagina = "pesquisarDetalheLinha.do?nrLinhaAG="+nrLinhaAG+"&redirect=SERVICO"+"&idLinha="+idLinha;
                if(nome=="btx03") pagina = "DetalhePrePago/begin.do?redirect=Promocoes";
                if(nome=="btx04") pagina = "DetalhePrePago/begin.do?redirect=Favoritos";
                if(nome=="btx05") pagina = "pesquisarDetalheLinha.do?nrLinhaAG="+nrLinhaAG+"&redirect=PERMISSOES"+"&idLinha="+idLinha;
                if(nome=="btx06") pagina = "pesquisarDetalheLinha.do?nrLinhaAG="+nrLinhaAG+"&redirect=TRAFEGODADOS"+"&idLinha="+idLinha;
                if(nome=="btx07") pagina = "modeloAparelho.do?operacao=historico&nrLinha=" + nrLinhaAG;
                if(nome=="btx08") pagina = "modeloAparelho.do?operacao=configuracoes&nrLinha=" + nrLinhaAG;
                if(nome=="first") pagina = "pesquisarDetalheLinha.do?first=TRUE";
                dyniframe.src = pagina;
            }

            function chamaDetalheLinha(idLinhaPar, nrCodArea, nrLinha) {
                document.getElementById('tbAbas').style.visibility = "visible";
                document.forms[0].idLinha.value=idLinhaPar;
                var nrLinhaAG = nrCodArea + nrLinha;
                document.getElementById('nrLinhaG').value = nrLinha;
                document.getElementById('nrCodAreaG').value = nrCodArea;
                document.forms[0].action="pesquisarDetalheLinha.do?nrLinhaAG="+nrLinhaAG;
                parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }

            function pesquisarLinha() {
                document.forms[0].formNrLinhaFiltro.value = limpaInteiro(document.forms[0].formNrLinhaFiltro.value);
                document.forms[0].formNrLinhaFiltroAte.value = limpaInteiro(document.forms[0].formNrLinhaFiltroAte.value);
                document.forms[0].action="pesquisarLinha.do"
                parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }

            function reloadLinha() {
                top.frameApp.fechaLupa();
                document.forms[0].action="loadLinha.do?reloadLinha=OK&idLinhaSelecionada="+document.forms[0].idLinha.value;
                document.forms[0].target="ifrLinha";
                parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }

            function limpar(){
                document.forms[0].action="limpaFiltro.do"
                document.getElementById('tbAbas').style.visibility = 'hidden';
                parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }

            function paginacao(op){
                var form = document.forms[0];
                var pagAtual = '<bean:write name="Form" property="formPaginaAtual"/>';
                if(pagAtual == '0' || pagAtual == ''){
                    pagAtual++;
                    op = '';
                }
                if(op == 'proximo'){
                    //pagAtual++;
                    form.pagOperacao.value = 'proximo';

                }else if(op == 'anterior'){
                    pagAtual--;
                    form.pagOperacao.value = 'anterior';
                }
                form.formPaginaAtual.value = pagAtual;
                form.action = 'DetalheLinha.do';
                parent.mostrar_div();
                form.method = "POST";
                form.submit();
            }

            function openPersonalizaChip(){
                var f = document.forms[0];
                f.action = "personalizaChip.do";
                f.target = "blank";
                f.submit();
            }
        -->
    </script>
</head>
<body style="margin-left:5px; margin-right:5px; margin-top:5px;" >
<acesso:controlHiddenItem nomeIdentificador="cli_lpli_verpagina">
    <% ParametrosVO param = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);%>
    <html:form action="/cliente/TelaInicial/DetalheLinha.do" styleId="LupaLinha" method="post">
    <html:hidden name="Form" property="formIDLinhaFiltro" styleId="idLinha"/>
    <html:hidden name="Form" property="formPaginaAtual"/>
    <input type="hidden" name="pagOperacao" value=""/>
    <input type="hidden" id="nrCodAreaG">
    <input type="hidden" id="nrLinhaG">
    <!-- ppaula -->
    <logic:notEmpty name="error">
        <table>
            <tr>
                <td height="7">(*) Dados não recuperados pois Sistema Legado temporariamente fora de serviço!</td>
            </tr>
        </table>
    </logic:notEmpty>
    <logic:empty name="error">
        <bean:define id="TipoLinha"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm.formTipoLinhaVO"/>
        <bean:define id="StatusLinha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm.formStatusLinhaVO"/>
        <table width="755" height="20" cellpadding="0" cellspacing="0" border="0" style="background-color:#ededed;border:1px solid #adadad;margin-bottom:3px;">
            <tr>
                <td valign="top">
                    <div id="loading" style="margin:2px 0 0 5px">Carregando dados...</div>
                    <div id="quantidade_linhas" style="display:none">
                        <table width="755" cellpadding="2" cellspacing="2" border="0">
                            <tr>
                                <td width="16%" align="right"><b>Qtde. de linhas</b></td>
                                <td width="16%" align="left"><span id="qtdLinhasTotal"></span></td>
                                <td width="16%" align="right"><b>Linhas ativas</b></td>
                                <td width="16%" align="left"><span id="qtdLinhasAtivas"></span></td>
                                <td width="16%" align="right"><b>Linhas canceladas</b></td>
                                <td width="16%" align="left"><span id="qtdLinhasCanceladas"></span></td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
        <vivo:quadro id="filtrolinha" width="755" height="100" label="Filtro" scroll="no">
            <table width="740" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td height="7"></td>
                </tr>
                <tr>
                    <td width="80">&nbsp;Linha:</td>
                    <td>De: <html:text name="Form" property="formNrLinhaFiltro" maxlength="11" size="15" onblur="formatPhoneNumberObj(this);" /> <!-- onkeyup="checaTelefone(this)"  -->
                        até <html:text name="Form" property="formNrLinhaFiltroAte" onblur="formatPhoneNumberObj(this);" maxlength="11" size="15"/> <!-- onkeyup="checaTelefone(this)" -->
                    </td>
                    <!--<script language="javascript">
                        checaTelefone(document.forms[0].formNrLinhaFiltro);
                        checaTelefone(document.forms[0].formNrLinhaFiltroAte);
                    </script>-->
                </tr>
                <tr>
                    <td height="7"></td>
                </tr>
                <tr>
                    <td>&nbsp;Tipo da Linha:</td>
                    <td>
                        <html:select name="Form" property="formIDTipoLinhaFiltro" style="width:180px">
                            <html:options collection="TipoLinha" property="id"  labelProperty="descricao"/>
                        </html:select>
                    </td>
                </tr>
                <tr><td height="4"></td></tr>
                <tr>
                    <td>&nbsp;Status:</td>
                    <td>
                        <html:select name="Form" property="formIDStatusLinhaFiltro" style="width:180px;">
                            <html:options collection="StatusLinha" property="id"  labelProperty="descricao"/>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td height="3"></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="left" colspan="2">
                        <acesso:controlHiddenItem nomeIdentificador="cli_lpli_pesquisar">
                            <img onClick="pesquisarLinha(); return false" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:hand;"/>
                        </acesso:controlHiddenItem>
                        <img onClick="limpar(); return false" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" style="border:none;cursor:hand;"/>
                    </td>
                    <td align="right">
                        <logic:greaterThan name="Form" property="formPaginaAtual" value="0">
                            <img onClick="paginacao('anterior');" id="btAnterior" src="/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif" style="border:none;cursor:hand;"/>
                        </logic:greaterThan>
                        <logic:equal property="inProxima" name="Form" value="1">
                            <img  onClick="paginacao('proximo');" id="btProximo" src="/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif" style="border:none;cursor:hand;"/>
                        </logic:equal>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        <table width="100%" align="center">
            <tr>
                <td align="center">
                      <logic:equal name="Form" property="formPaginaAtual" value="0">
                            Página&nbsp;1&nbsp;de&nbsp;<bean:write name="Form" property="formTotalPagina"/>
                      </logic:equal>
                      <logic:notEqual name="Form" property="formPaginaAtual" value="0">
                            Página&nbsp;<%=Integer.parseInt(Form.getFormPaginaAtual())+1%>&nbsp;de&nbsp;<bean:write name="Form" property="formTotalPagina"/>
                      </logic:notEqual>
                </td>
            </tr>
        </table>
    </logic:empty>
    <!-- fim ppaula -->
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></div>
    <!-- ppaula -->
    <logic:empty name="error">
        <bean:define id="ListaDadosLinha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm.formListaDadosLupaLinha"/>
        <div id="iLupaLinha_div_principal">
            <table width="700" cellpadding="0" cellspacing="1">
                <tr>
                    <td valign="top">
                        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="741" layoutHeight="100" tableWidth="741" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="15%" type="string">Linha</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="20%" type="string">Tipo da Linha</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="20%" type="string">Plano de Serviço</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="20%" type="string">Status da Linha</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="10%" type="date">Habilitação</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="10%" type="string">Divulgação (S/N)</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                             <vivo:tbRows scroll="false">
                                <logic:iterate name="ListaDadosLinha"  id="linhas" indexId="indiceLinha" >
                                    <vivo:tbRow key="linha1">
                                        <vivo:tbRowColumn>
                                            <logic:equal name="linhas" property="inLegado" value="1"><input type="radio" class="radio" name="rdoLinhas" value="<%=indiceLinha%>" onclick="chamaDetalheLinha('<bean:write name="linhas" property="idLinha"/>', '<bean:write name="linhas" property="nrCodArea"/>', '<bean:write     name="linhas" property="nrLinha"/>'); document.getElementById('tbAbas').style.visibility = 'visible';" <logic:equal name="linhas" property="idLinha" value="<%=Form.getFormIDLinhaFiltro()%>">checked</logic:equal>/>
                                            <logic:equal name="linhas"  property="idLinha" value="<%=Form.getFormIDLinhaFiltro()%>"><script>document.getElementById('nrCodAreaG').value=<bean:write name="linhas" property="nrCodArea"/>;document.getElementById('nrLinhaG').value=<bean:write name="linhas" property="nrLinha"/>;</script></logic:equal></logic:equal>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>(<bean:write name="linhas" property="nrCodArea"/>) <bean:write name="linhas" property="nrLinha"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="linhas" property="dsTipoLinha"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="linhas" property="dsPlanoServico"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="linhas" property="dsStatusLinha"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="linhas" property="dtHabilitacao"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="linhas" property="inDivulgaNumero"/></vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </td>
                </tr>
            </table>
        </div>
        <table width="100%" id="tbLinha" style="visibility:hidden;display;none" align="center">
            <tr>
                <td align="center"><netui:label value="Não existe nenhuma linha selecionada." styleClass="tblEstatica_campoNome"/></td>
            </tr>
        </table>
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></div>
        <table width="755" border="0" cellspacing="0" cellpadding="0" style="visibility:hidden" id="tbAbas">
            <tr>
                <td>
                    <vivo:abaGrupo id="btxAba" width="700" height="10" styleClass="abatexto">
                        <acesso:controlHiddenItem nomeIdentificador="cli_lpli_ageral">
                            <vivo:abaItem id="btx01" onclick="abaSelected(btxAba, btx01);CarregaAba(this.id);" value="Geral" select="S"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="cli_lpli_aser">
                            <vivo:abaItem id="btx02" onclick="abaSelected(btxAba, btx02);CarregaAba(this.id);" value="Serviços"/>
                        </acesso:controlHiddenItem>
                        <%if( param.getIdTipoRelacionamento()!=null && "2".equals(param.getIdTipoLinha()) /*|| "4".equals(param.getIdTipoLinha()) || "7".equals(param.getIdTipoLinha()*/  ) {%>
                            <acesso:controlHiddenItem nomeIdentificador="cli_lpli_apro">
                                <vivo:abaItem id="btx03" onclick="abaSelected(btxAba, btx03);CarregaAba(this.id);" value="Promoções"/>
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="cli_lpli_afav">
                                <vivo:abaItem id="btx04" onclick="abaSelected(btxAba, btx04);CarregaAba(this.id);" value="Favoritos"/>
                            </acesso:controlHiddenItem>
                        <%}%>
                        <acesso:controlHiddenItem nomeIdentificador="cli_lpli_aperm">
                            <vivo:abaItem id="btx05" onclick="abaSelected(btxAba, btx05);CarregaAba(this.id);" value="Permissões de Contatos"/>
                        </acesso:controlHiddenItem>
                        <%
                        // Aba acessível apenas para linhas pós-pagas
                        if (ConstantesCRM.SONE.equals(param.getIdTipoLinha()) || ConstantesCRM.SFOUR.equals(param.getIdTipoLinha())
                            || ConstantesCRM.SFIVE.equals(param.getIdTipoLinha()) || ConstantesCRM.SSEVEN.equals(param.getIdTipoLinha())) { %>
                        <acesso:controlHiddenItem nomeIdentificador="cli_lpli_tdados">
                            <vivo:abaItem id="btx06" onclick="abaSelected(btxAba, btx06);CarregaAba(this.id);" value="Tráfego de Dados"/>
                        </acesso:controlHiddenItem>
                        <% } %>
                        <%
                        // Abas acessíveis apenas para linhas GSM
                        if (ConstantesCRM.SFIVE.equals(param.getIdTipoLinha())
                                || ConstantesCRM.SSIX.equals(param.getIdTipoLinha())
                                || ConstantesCRM.SSEVEN.equals(param.getIdTipoLinha())) { %>
                        <acesso:controlHiddenItem nomeIdentificador="cli_lpli_histaparelhos">
                            <vivo:abaItem id="btx07" onclick="abaSelected(btxAba, btx07);CarregaAba(this.id);" value="Hist. Aparelhos"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="cli_lpli_confaparelho">
                            <vivo:abaItem id="btx08" onclick="abaSelected(btxAba, btx08);CarregaAba(this.id);" value="Configurações do Aparelho"/>
                        </acesso:controlHiddenItem>
                        <% } %>
                    </vivo:abaGrupo>
                <td>
            </tr>
            <tr>
                <td class="tbl_bgGray" height="164">
                    <iframe id="iframeLupaLinha" scrolling="no" marginheight="0" marginwidth="0" frameborder="0" width="753" height="162" src="" style="background-color:#ededed;"></iframe>
                </td>
            </tr>
        </table>



        <%String actionOrigem = request.getHeader("referer");
        actionOrigem = actionOrigem.substring(actionOrigem.indexOf(request.getContextPath())+request.getContextPath().length());
        if(actionOrigem.equals("/cliente/TelaInicial/begin.do")){%>
            <script>
                if(document.forms[0].rdoLinhas && document.forms[0].rdoLinhas.length && inLegadoInicial==1){
                    for(i=0;i<document.forms[0].rdoLinhas.length;i++){
                        if(document.forms[0].rdoLinhas[i].checked){
                            document.getElementById('tbAbas').style.visibility = "visible";
                        }
                    }
                }else if(document.forms[0].rdoLinhas && document.forms[0].rdoLinhas.checked && inLegadoInicial==1){
                    document.getElementById('tbAbas').style.visibility = "visible";
                }
                if(document.getElementById('tbAbas').style.visibility != "visible"){
                    document.getElementById('tbLinha').style.visibility = "visible";
                    document.getElementById('tbLinha').style.display    = "block";
                }
            </script>
        <%}else if(actionOrigem.equals("/cliente/TelaInicial/pesquisarDetalheLinha.do")){%>
            <script>
                document.getElementById('tbAbas').style.visibility = "visible";
            </script>
        <%}else if(actionOrigem.equals("/cliente/TelaInicial/pesquisarLinha.do")){%>
            <logic:equal name="Form" property="formQtLinhasRetornadas" value="0">
                <script>
                    //document.getElementById('iLupaLinha_div_principal').style.visibility = "hidden";
                    document.getElementById('tbLinha').style.visibility = "visible";
                </script>
            </logic:equal>
        <% }  %>
    </logic:empty>
    <logic:notEqual name="first" value="TRUE">
        <script>
            try{
                document.getElementById('iframeLupaLinha').contentWindow.document.body.style.backgroundColor="#ededed";
            }catch(err){}
            parent.oculta_div();
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            if(linhaSelecionada() && inLegadoInicial==1){
                abaSelected(btxAba, btx01);
                CarregaAba('btx01');
            }
        </script>
    </logic:notEqual>
    <logic:equal name="first" value="TRUE">
        <script>
            //var dyniframe = document.getElementById("iframeLupaLinha");
            //dyniframe.src ='lupaLinhaiFrame.jsp';
        </script>
    </logic:equal>
    <%--
    //Gambi para depois habilitar pois cancelaram temporariamente (!!??).
    if("5".equals(Form.getFormIDTipoLinha()) || "6".equals(Form.getFormIDTipoLinha()) || "7".equals(Form.getFormIDTipoLinha())){ % >
    <acesso:controlHiddenItem nomeIdentificador="cli_lpli_bvchip">
    <table cellpadding="0" cellspacing="0" border="0" width="755">
        <tr>
            <td align="right">
                <img onClick="openPersonalizaChip();return false;" src="../../resources/images/bt_configvivochip_nrml.gif" rolloverImage="../../resources/images/bt_configvivochip_over.gif" style="border:none;"/>
            </td>
        </tr>
    </table>
    </acesso:controlHiddenItem>
    < %}--%>
    <iframe style="width:0px;height:0px;" name="iPaginacao"></iframe>
    </html:form>
    <table cellpadding="0" cellspacing="0" border="0" width="755" >
        <tr>
            <td align="right">
            <%if( param.getIdTipoRelacionamento()!=null && param.getIdTipoRelacionamento().equals("2")) {%>
                <acesso:controlHiddenItem nomeIdentificador="cli_lpli_selecionar">
                    <img onclick="reloadLinha(); return false;" src="/FrontOfficeWeb/resources/images/bt_selecionar_nrml.gif" style="border:none;cursor:hand;"/>
                </acesso:controlHiddenItem>
            <%}%>
            </td>
        </tr>
    </table>
</acesso:controlHiddenItem>
</body>
</html>