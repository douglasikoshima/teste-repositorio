<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!-- COMBOS FORMULARIO -->
<bean:define id="FormArvoreParametro" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro"/>

<!-- COMBOS EXISTENTES -->
<bean:define id="aTipoRelacionamento"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admTipoRelacionamentoVOArray"/>
<bean:define id="atipoSegmentacao"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admTipoSegmentacaoVOArray"/>
<bean:define id="atipoCarteira"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admTipoCarteiraSimplVOArray"/>
<bean:define id="aGrupoAbertura"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admGrupoAberturaVOArray"/>
<bean:define id="aGrupoRetorno"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admGrupoRetornoVOArray"/>
<bean:define id="aGrupoTratamento"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admGrupoTratamentoVOArray"/>
<bean:define id="aTipoLinha"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admTipoLinhaSimplVOArray"/>
<bean:define id="aUFOperadora"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admUFOperadoraSimplVOArray"/>
<bean:define id="aFechamento"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admTipoFechamentoVOArray"/>
<bean:define id="aDisponivel"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.disponivelExist"/>
<bean:define id="aCanal"                name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admCanalVOArray"/>
<bean:define id="aNatureza"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admNaturezaVOArray"/>
<bean:define id="aProcedencia"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosExist.admProcedenciaVOArray"/>

<!-- COMBOS ASSOCIADOS -->
<bean:define id="aaTipoLinha"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admTipoLinhaSimplVOArray"/>
<bean:define id="aatipoSegmentacao"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admTipoSegmentacaoVOArray"/>
<bean:define id="aaGrupoTratamento"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admGrupoTratamentoVOArray"/>
<bean:define id="aaGrupoAbertura"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admGrupoAberturaVOArray"/>
<bean:define id="aaGrupoRetorno"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admGrupoRetornoVOArray"/>
<bean:define id="aatipoCarteira"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admTipoCarteiraSimplVOArray"/>
<bean:define id="aaTipoRelacionamento"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admTipoRelacionamentoVOArray"/>
<bean:define id="aaFechamento"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admTipoFechamentoVOArray"/>
<bean:define id="aaDisponivel"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.disponivelAssoc"/>
<bean:define id="aaUFOperadora"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admUFOperadoraSimplVOArray"/>
<bean:define id="aaCanal"               name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admCanalVOArray"/>
<bean:define id="aaNatureza"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admNaturezaVOArray"/>
<bean:define id="aaProcedencia"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.containerCombosAssoc.admProcedenciaVOArray"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administração de Workflow"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">

        <title>Arvore Parâmetros</title>
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    </head>
    <body>
    <script language="JavaScript">
    var m_split = false;
    var inVarArvCon = 1;
    function splitPane(img){
        var tb_filtros = document.getElementById("tb_filtros");
        var tb_visualizacao = document.getElementById("tb_visualizacao");
        var tb_arvore = document.getElementById("tb_arvore");
        if(!m_split){
            tb_filtros.style.display='none';
            tb_visualizacao.style.display = 'none';
            tb_arvore.style.height='420';
            img.src = "/FrontOfficeWeb/resources/images/bt_lupa_aba_down.gif";
            m_split = true;
        }else{
            tb_filtros.style.display='';
            tb_visualizacao.style.display = '';
            tb_arvore.style.height='220';
            img.src = "/FrontOfficeWeb/resources/images/bt_lupa_aba.gif";
            m_split = false;
        }
    }

            function valida() {
                var tmpRadio = '';
                for (i=0;i<document.forms[0].idContatoGrupoRadio.length;i++){
                    if (document.forms[0].idContatoGrupoRadio[i].checked) {
                        tmpRadio = i;
                    }
                }
                // Se todos os combos estao habilitados.
                if(tmpRadio != 1) {
                    if(existemDadosComboGrupo() == false && existemDadosComboContato() == false) {
                        alert('É necessária a seleção de pelo menos uma variável.');
                        return false;
                    }
                }else {
                    if(existemDadosComboContato() == false) {
                        alert('É necessária a seleção de pelo menos uma variável.');
                        return false;
                    }
                }
                if(tmpRadio == 1) {
                    document.forms[0].idDisponivelAssocArray.options.length = 0;
                    document.forms[0].idGrupoTratamentoAssocArray.options.length = 0;
                    document.forms[0].idGrupoRetornoAssocArray.options.length = 0;
                    document.forms[0].idFechamentoAssocArray.options.length = 0;
                 }
                 if (document.forms[0].idDisponivelAssocArray){
                    if(document.getElementById("table_qdrDisponivel").style.visibility == "visible"
                    && document.forms[0].idDisponivelAssocArray.length == 0){
                        alert('É necessária a seleção do filtro Disponível.');
                        return false;
                    }
                 }
                return true;
            }

            function existemDadosComboGrupo() {
                if(document.forms[0].idDisponivelAssocArray.options.length != 0 ||
                   document.forms[0].idGrupoTratamentoAssocArray.options.length != 0 ||
                   document.forms[0].idFechamentoAssocArray.options.length != 0 ||
                   document.forms[0].idGrupoRetornoAssocArray.options.length != 0)
                {
                    return true;

                }else
                {
                    return false;
                }
            }

            function existemDadosComboContato() {
               if(document.forms[0].idTipoLinhaAssocArray.options.length != 0 ||
                 document.forms[0].idSegmentacaoAssocArray.options.length != 0 ||
                 document.forms[0].idGrupoAberturaAssocArray.options.length != 0 ||
                 document.forms[0].idTipoCarteiraAssocArray.options.length != 0 ||
                 document.forms[0].idTipoRelacionamentoAssocArray.options.length != 0 ||
                 document.forms[0].idOperadoraAssocArray.options.length != 0 ||
                 document.forms[0].idOperadoraAssocArray.options.length != 0 ||
                 document.forms[0].idNaturezaAssocArray.options.length != 0 ||
                 document.forms[0].idProcedenciaAssocArray.options.length != 0 ||
                 document.forms[0].idCanalAssocArray.options.length != 0) {
                    return true;
                 }else{

                    return false;
                 }
            }

            function filtarArvore() {
                if(valida()){
                    selecionaCombos(true);
                    document.forms[0].target = '';
                    document.forms[0].action = 'exportaRetorno.do';
                    mostrar_div();
                    document.forms["formVar"].submit();
                }
            }

            function habilitaCampos(op) {

                /* Contato */
                if (op == 0) {
                    document.getElementById('table_qdrFechamento').style.visibility         = 'visible';
                    document.getElementById('table_qdrDisponivel').style.visibility         = 'visible';
                    document.getElementById('table_qdrGrupoTratamento').style.visibility    = 'visible';
                    document.getElementById('table_qdrGrupoRetorno').style.visibility       = 'visible';
                    document.getElementById('table_qdrNatureza').style.visibility           = 'hidden';
                    document.getElementById('table_qdrCanal').style.visibility              = 'hidden';
                    document.getElementById('table_qdrProcedencia').style.visibility        = 'hidden';
                }
                /* Grupo */
                else if (op == 1) {
                    document.getElementById('table_qdrFechamento').style.visibility         = 'hidden';
                    document.getElementById('table_qdrDisponivel').style.visibility         = 'hidden';
                    document.getElementById('table_qdrGrupoTratamento').style.visibility    = 'hidden';
                    document.getElementById('table_qdrGrupoRetorno').style.visibility       = 'hidden';
                    document.getElementById('table_qdrNatureza').style.visibility           = 'visible';
                    document.getElementById('table_qdrCanal').style.visibility              = 'visible';
                    document.getElementById('table_qdrProcedencia').style.visibility        = 'visible';
                }
                /* Contato x Grupo */
                else {
                    document.getElementById('table_qdrNatureza').style.visibility           = 'visible';
                    document.getElementById('table_qdrGrupoTratamento').style.visibility    = 'hidden';
                    document.getElementById('table_qdrFechamento').style.visibility         = 'hidden';
                    document.getElementById('table_qdrDisponivel').style.visibility         = 'visible';
                    document.getElementById('table_qdrGrupoRetorno').style.visibility       = 'hidden';
                    document.getElementById('table_qdrProcedencia').style.visibility        = 'visible';
                }
            }

            function transfereSelecaoLista(listaOrigem, listaDestino) {

                var i;
                var permiteTransferencia = true;

                //Permite apenas uma opção para DISPONIBILIDADE
                if (listaOrigem.name == "idDisponivelExistArray") {
                    var j=0;
                    for (i=0; i < listaOrigem.length; i++) {
                        if (listaOrigem.options[i].selected) j++;
                    }
                    if (j==2 || listaDestino.length > 0) {
                        alert('Para "Disponível" selecione apenas Sim ou Não.');
                        permiteTransferencia = false;
                    }
                }

                if (permiteTransferencia) {
                    for(i = 0; i<listaOrigem.options.length; i++)
                        if(listaOrigem.options[i].selected)
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);

                    for(i = listaOrigem.options.length-1; i>=0; i--)
                        if(listaOrigem.options[i].selected)
                            listaOrigem.options[i] = null;
                }
            }

            function selecionaCombos(op)
            {

                var componentName = new Array(  "idTipoLinhaAssocArray",
                                                "idSegmentacaoAssocArray",
                                                "idGrupoTratamentoAssocArray",
                                                "idGrupoAberturaAssocArray",
                                                "idGrupoRetornoAssocArray",
                                                "idTipoCarteiraAssocArray",
                                                "idTipoRelacionamentoAssocArray",
                                                "idFechamentoAssocArray",
                                                "idDisponivelAssocArray",
                                                "idOperadoraAssocArray",
                                                "idNaturezaAssocArray",
                                                "idProcedenciaAssocArray",
                                                "idCanalAssocArray",
                                                "idTipoLinhaExistArray",
                                                "idSegmentacaoExistArray",
                                                "idGrupoTratamentoExistArray",
                                                "idGrupoAberturaExistArray",
                                                "idGrupoRetornoExistArray",
                                                "idTipoCarteiraExistArray",
                                                "idTipoRelacionamentoExistArray",
                                                "idFechamentoExistArray",
                                                "idDisponivelExistArray",
                                                "idOperadoraExistArray",
                                                "idNaturezaExistArray",
                                                "idProcedenciaExistArray",
                                                "idCanalExistArray");

                for(x =0;x < componentName.length ; x++)
                    for(z =0;z < eval("document.forms[0]."+componentName[x]).options.length;z++)
                    {
                        eval("document.forms[0]."+componentName[x]).options[z].selected = op;
                    }

            }

            function limpar()
            {
                var form  = document.forms[0];

                //form.limpaForm.value  = 'limpa';
                //form.dataInicio.value = '';
                //form.dataFim.value = '';
                form.target = '';
                form.action = 'carregaFiltro.do';
                form.submit();

            }

    </script>

    </netui-template:section>

    <netui-template:section name="bodySection">

        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />

        <div style="height:5px;line-height:1px;font-size:4px;"></div>

        <vivo:sessao width="790" height="405" id="qdrTipoLinha" label="Variáveis de Árvore de Contato" operacoes="Exportação">

        <form action="montaLista.do" name="formVar" id="formVar" method="POST" onsubmit="return false;">
        <input type="text" name="var_menu" style="width:0px;height:0px;">
        <table width="100%" border="0" class="tbl_bgGray">
            <tr>
                <td>
                  <vivo:quadro width="370" height="80" id="qdrTipoLinha" scroll="no" label="Tipo de Linha">
                    <table align="left" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="310" align="center" valign="top">

                                Existente<br>
                                <html:select name="FormArvoreParametro" property="idTipoLinhaExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idTipoLinhaExistArray, document.forms[0].idTipoLinhaAssocArray);">
                                    <html:options collection="aTipoLinha" property="idTipoLinha" labelProperty="dsTipoLinha" />
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idTipoLinhaExistArray, document.forms[0].idTipoLinhaAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" />
                                <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idTipoLinhaAssocArray, document.forms[0].idTipoLinhaExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                            </td>
                            <td width="310" align="center" valign="top">
                                Associada<br>
                                <html:select name="FormArvoreParametro" property="idTipoLinhaAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idTipoLinhaAssocArray, document.forms[0].idTipoLinhaExistArray);">
                                    <html:options collection="aaTipoLinha" property="idTipoLinha" labelProperty="dsTipoLinha" />
                                </html:select>
                            </td>
                            <tr>
                        </table>
                   </vivo:quadro>
               </td>
               <td>
                   <vivo:quadro width="370" height="80" id="qdrSegmentacao" scroll="no" label="Segmentação">
                        <table align="right" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="310" align="center" valign="top">
                                    Existente<br>
                                    <html:select name="FormArvoreParametro" property="idSegmentacaoExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idSegmentacaoExistArray, document.forms[0].idSegmentacaoAssocArray);">
                                        <html:options collection="atipoSegmentacao" property="idSegmentacao" labelProperty="dsSegmentacao" />
                                    </html:select>
                                </td>
                                <td width="70" align="center" valign="bottom">
                                    <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idSegmentacaoExistArray, document.forms[0].idSegmentacaoAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                                    <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idSegmentacaoAssocArray, document.forms[0].idSegmentacaoExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                                </td>
                                <td width="310" align="center" valign="top">
                                    Associada<br>
                                    <html:select name="FormArvoreParametro" property="idSegmentacaoAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idSegmentacaoAssocArray, document.forms[0].idSegmentacaoExistArray);">
                                        <html:options collection="aatipoSegmentacao" property="idSegmentacao" labelProperty="dsSegmentacao" />
                                    </html:select>
                                </td>
                            </tr>
                        </table>
                    </vivo:quadro>
            </td>
        </tr>
        <tr>
            <td>
              <vivo:quadro width="370" height="80" id="qdrGrupoTratamento" scroll="no" label="Grupo Tratamento" >
                <table align="left" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="310" align="center" valign="top">
                            Existente<br>
                            <html:select name="FormArvoreParametro" property="idGrupoTratamentoExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idGrupoTratamentoExistArray, document.forms[0].idGrupoTratamentoAssocArray);">
                                <html:options collection="aGrupoTratamento" property="idGrupo" labelProperty="nmGrupo" />
                            </html:select>
                        </td>
                        <td width="70" align="center" valign="bottom">
                            <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idGrupoTratamentoExistArray, document.forms[0].idGrupoTratamentoAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                            <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idGrupoTratamentoAssocArray, document.forms[0].idGrupoTratamentoExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                        </td>
                        <td width="310" align="center" valign="top">
                            Associado<br>
                            <html:select name="FormArvoreParametro" property="idGrupoTratamentoAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idGrupoTratamentoAssocArray, document.forms[0].idGrupoTratamentoExistArray);">
                                <html:options collection="aaGrupoTratamento" property="idGrupo" labelProperty="nmGrupo"/>
                            </html:select>
                        </td>
                    </tr>
                </table>
              </vivo:quadro>
            </td>
            <td>
              <vivo:quadro width="370" height="80" id="qdrGrupoAbertura" scroll="no" label="Grupo Abertura" >
                    <table align="left" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="310" align="center" valign="top">
                                Existente<br>
                                <html:select name="FormArvoreParametro" property="idGrupoAberturaExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idGrupoAberturaExistArray, document.forms[0].idGrupoAberturaAssocArray);">
                                    <html:options collection="aGrupoAbertura" property="idGrupo" labelProperty="nmGrupo" />
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idGrupoAberturaExistArray, document.forms[0].idGrupoAberturaAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                                <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idGrupoAberturaAssocArray, document.forms[0].idGrupoAberturaExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                            </td>
                            <td width="310" align="center" valign="top">
                                Associado<br>
                                <html:select name="FormArvoreParametro" property="idGrupoAberturaAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idGrupoAberturaAssocArray, document.forms[0].idGrupoAberturaExistArray);">
                                    <html:options collection="aaGrupoAbertura" property="idGrupo" labelProperty="nmGrupo" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
              </vivo:quadro>
            </td>
        </tr>
        <tr>
            <td>
              <vivo:quadro width="370" height="80" id="qdrGrupoRetorno" scroll="no" label="Grupo Retorno" >
                    <table align="left" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="310" align="center" valign="top">
                                Existente<br>
                                <html:select name="FormArvoreParametro" property="idGrupoRetornoExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idGrupoRetornoExistArray, document.forms[0].idGrupoRetornoAssocArray);">
                                    <html:options collection="aGrupoRetorno" property="idGrupo" labelProperty="nmGrupo" />
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idGrupoRetornoExistArray, document.forms[0].idGrupoRetornoAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                                <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idGrupoRetornoAssocArray, document.forms[0].idGrupoRetornoExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                            </td>
                            <td width="310" align="center" valign="top">
                                Associado<br>
                                <html:select name="FormArvoreParametro" property="idGrupoRetornoAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idGrupoRetornoAssocArray, document.forms[0].idGrupoRetornoExistArray);">
                                    <html:options collection="aaGrupoRetorno" property="idGrupo" labelProperty="nmGrupo" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
               </vivo:quadro>
            </td>
            <td>
              <vivo:quadro width="370" height="80" id="qdrCarteirizacao" scroll="no" label="Carteirização" >
                    <table align="left" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="310" align="center" valign="top">
                                Existente<br>
                                <html:select name="FormArvoreParametro" property="idTipoCarteiraExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idTipoCarteiraExistArray, document.forms[0].idTipoCarteiraAssocArray);">
                                    <html:options collection="atipoCarteira" property="idTipoCarteira" labelProperty="dsTipoCarteira" />
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idTipoCarteiraExistArray, document.forms[0].idTipoCarteiraAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                                <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idTipoCarteiraAssocArray, document.forms[0].idTipoCarteiraExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                            </td>
                            <td width="310" align="center" valign="top">
                                Associado<br>
                                <html:select name="FormArvoreParametro" property="idTipoCarteiraAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idTipoCarteiraAssocArray, document.forms[0].idTipoCarteiraExistArray);">
                                    <html:options collection="aatipoCarteira" property="idTipoCarteira" labelProperty="dsTipoCarteira" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
               </vivo:quadro>
            </td>
        </tr>
        <tr>
            <td>
              <vivo:quadro width="370" height="80" id="qdrTipoPessoa" scroll="no" label="Tipo de Cliente" >
                    <table align="left" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="310" align="center" valign="top">
                                Existente<br>
                                <html:select name="FormArvoreParametro" property="idTipoRelacionamentoExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idTipoRelacionamentoExistArray, document.forms[0].idTipoRelacionamentoAssocArray);">
                                    <html:options collection="aTipoRelacionamento" property="idTipoRelacionamento" labelProperty="nmTipoRelacionamento" />
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idTipoRelacionamentoExistArray, document.forms[0].idTipoRelacionamentoAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                                <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idTipoRelacionamentoAssocArray, document.forms[0].idTipoRelacionamentoExistArray);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                            </td>
                            <td width="310" align="center" valign="top">
                                Associado<br>
                                <html:select name="FormArvoreParametro" property="idTipoRelacionamentoAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idTipoRelacionamentoAssocArray, document.forms[0].idTipoRelacionamentoExistArray);">
                                    <html:options collection="aaTipoRelacionamento" property="idTipoRelacionamento" labelProperty="nmTipoRelacionamento" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
               </vivo:quadro>
            </td>

            <td>
              <vivo:quadro width="370" height="80" id="qdrFechamento" scroll="no" label="Fechamento" >
                    <table align="left" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="310" align="center" valign="top">
                                Existente<br>
                                <html:select name="FormArvoreParametro" property="idFechamentoExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idFechamentoExistArray, document.forms[0].idFechamentoAssocArray);">
                                    <html:options collection="aFechamento" property="idTipoFechamento" labelProperty="nmTipoFechamento" />
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idFechamentoExistArray, document.forms[0].idFechamentoAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                                <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idFechamentoAssocArray, document.forms[0].idFechamentoExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                            </td>
                            <td width="310" align="center" valign="top">
                                Associado<br>
                                <html:select name="FormArvoreParametro" property="idFechamentoAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idFechamentoAssocArray, document.forms[0].idFechamentoExistArray);">
                                    <html:options collection="aaFechamento" property="idTipoFechamento" labelProperty="nmTipoFechamento" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
               </vivo:quadro>
            </td>
        </tr>
        <tr>
            <td>
              <vivo:quadro width="370" height="80" id="qdrDisponivel" scroll="no" label="Disponível" >
                    <table align="left" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="310" align="center" valign="top">
                                Existente<br>
                                <html:select name="FormArvoreParametro" property="idDisponivelExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idDisponivelExistArray, document.forms[0].idDisponivelAssocArray);">
                                    <html:options collection="aDisponivel" property="codigo" labelProperty="descricao" />
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idDisponivelExistArray, document.forms[0].idDisponivelAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                                <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idDisponivelAssocArray, document.forms[0].idDisponivelExistArray);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                            </td>
                            <td width="310" align="center" valign="top">
                                Associado<br>
                                <html:select name="FormArvoreParametro" property="idDisponivelAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idDisponivelAssocArray, document.forms[0].idDisponivelExistArray);">
                                    <html:options collection="aaDisponivel" property="codigo" labelProperty="descricao" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
               </vivo:quadro>
            </td>
            <td>
              <vivo:quadro width="370" height="80" id="qdrOperadora" scroll="no" label="Regional" >
                    <table align="left" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="310" align="center" valign="top">
                                Existente<br>
                                <html:select name="FormArvoreParametro" property="idOperadoraExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idOperadoraExistArray, document.forms[0].idOperadoraAssocArray);">
                                    <html:options collection="aUFOperadora" property="idUFOperadora" labelProperty="dsUFOperadora" />
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idOperadoraExistArray, document.forms[0].idOperadoraAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                                <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idOperadoraAssocArray, document.forms[0].idOperadoraExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                            </td>
                            <td width="310" align="center" valign="top">
                                Associado<br>
                                <html:select name="FormArvoreParametro" property="idOperadoraAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idOperadoraAssocArray, document.forms[0].idOperadoraExistArray);">
                                    <html:options collection="aaUFOperadora" property="idUFOperadora" labelProperty="dsUFOperadora" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
               </vivo:quadro>
            </td>
        </tr>
        <tr>
            <td>
              <vivo:quadro width="370" height="80" id="qdrNatureza" scroll="no" label="Natureza" >
                    <table align="left" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="310" align="center" valign="top">
                                Existente<br>
                                <html:select name="FormArvoreParametro" property="idNaturezaExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idNaturezaExistArray, document.forms[0].idNaturezaAssocArray);">
                                    <html:options collection="aNatureza" property="idNatureza" labelProperty="dsNatureza" />
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idNaturezaExistArray, document.forms[0].idNaturezaAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                                <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idNaturezaAssocArray, document.forms[0].idNaturezaExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                            </td>
                            <td width="310" align="center" valign="top">
                                Associado<br>
                                <html:select name="FormArvoreParametro" property="idNaturezaAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idNaturezaAssocArray, document.forms[0].idNaturezaExistArray);">
                                    <html:options collection="aaNatureza" property="idNatureza" labelProperty="dsNatureza" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
               </vivo:quadro>
            </td>
            <td>
              <vivo:quadro width="370" height="80" id="qdrCanal" scroll="no" label="Canal" >
                    <table align="left" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="310" align="center" valign="top">
                                Existente<br>
                                <html:select name="FormArvoreParametro" property="idCanalExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idCanalExistArray, document.forms[0].idCanalAssocArray);">
                                    <html:options collection="aCanal" property="idCanal" labelProperty="nmCanal" />
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idCanalExistArray, document.forms[0].idCanalAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                                <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idCanalAssocArray, document.forms[0].idCanalExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                            </td>
                            <td width="310" align="center" valign="top">
                                Associado<br>
                                <html:select name="FormArvoreParametro" property="idCanalAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idCanalAssocArray, document.forms[0].idCanalExistArray);">
                                    <html:options collection="aaCanal" property="idCanal" labelProperty="nmCanal" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
               </vivo:quadro>
            </td>
        </tr>
        <tr>
            <td>
              <vivo:quadro width="370" height="80" id="qdrProcedencia" scroll="no" label="Procedência" >
                    <table align="left" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="310" align="center" valign="top">
                                Existente<br>
                                <html:select name="FormArvoreParametro" property="idProcedenciaExistArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idProcedenciaExistArray, document.forms[0].idProcedenciaAssocArray);">
                                    <html:options collection="aProcedencia" property="idProcedencia" labelProperty="nmProcedencia" />
                                </html:select>
                            </td>
                            <td width="70" align="center" valign="bottom">
                                <img id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idProcedenciaExistArray, document.forms[0].idProcedenciaAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /><br><br>
                                <img id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idProcedenciaAssocArray, document.forms[0].idProcedenciaExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" />
                            </td>
                            <td width="310" align="center" valign="top">
                                Associado<br>
                                <html:select name="FormArvoreParametro" property="idProcedenciaAssocArray" multiple="true" style="width:150px;" size="4" ondblclick="transfereSelecaoLista(document.forms[0].idProcedenciaAssocArray, document.forms[0].idProcedenciaExistArray);">
                                    <html:options collection="aaProcedencia" property="idProcedencia" labelProperty="nmProcedencia" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
               </vivo:quadro>
            </td>
            <td></td>
        </tr>
    </table>

    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

    </vivo:sessao>

    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

    <table class="tbl_bgGray" width="789">
        <tr>
            <td>
               <input type="radio" name="idContatoGrupoRadio" class="radio" value="0" checked onclick="habilitaCampos(this.value);">Contato &nbsp;&nbsp;&nbsp;&nbsp;
               <input type="radio" name="idContatoGrupoRadio" class="radio" value="1" onclick="habilitaCampos(this.value);">Grupo   &nbsp;&nbsp;&nbsp;&nbsp;
               <input type="radio" name="idContatoGrupoRadio" class="radio" value="2" onclick="habilitaCampos(this.value)">Contato X Grupo
            </td>
        </tr>
        <tr>
            <td  align="left">
                <img id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;cursor:hand;" vspace="5" hspace="5"/>
            </td>
            <td align="right" >
                <img vspace="5" onClick="return limpar(); return false;" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;"/>
                <img vspace="5" onClick="filtarArvore();" src="/FrontOfficeWeb/resources/images/bt_exportar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_exportar_over.gif" style="border:none;"/>
            </td>
        </tr>
    </table>

  </form>

    </body>

    <script language="javascript" for="window" event="onload">
    <!--
        selecionaCombos(false);
        habilitaCampos(0);
        oculta_div();
    -->

    </script>
    <vivo:alert atributo="msgStatus" scope="request"/>

    </netui-template:section>
</netui-template:template>

