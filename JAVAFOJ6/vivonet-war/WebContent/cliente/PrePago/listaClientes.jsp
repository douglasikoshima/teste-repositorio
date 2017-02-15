<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ListasVODocument.ListasVO.ListaTipoDocumento"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="prePagoForm"
             type="cliente.PrePago.PrePagoController.PrePagoForm" />

<bean:define id="listas"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="listasVO"
             type="br.com.vivo.fo.cliente.vo.ListasVODocument.ListasVO" />

<%
String inTipoPessoa = ConstantesCRM.SONE.equals(form.getClientesPesquisados().getInTipoPessoa()) ? "PF" : "PJ";
%>

<%!
private String getTipoDocumento(String idTipoDocumento, ListaTipoDocumento[] arg) {
    String dsTipoDocumento = ConstantesCRM.SVAZIO;
    for (int i = 0; i < arg.length; i++) {
        if (arg[i].getIdTipoDocumento().equals(idTipoDocumento)) {
            dsTipoDocumento = arg[i].getDsTipoDocumento();
            break;
        }
    }
    return dsTipoDocumento;
}
%>

<html>
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script type="text/javascript">

        selecionarLinhaCliente = function(index) {

            var inLinhaSelecionada = false;
            var indexSelecionado;
            var inTipoPessoa = $('tpPessoaF').checked ? 'PF' : 'PJ';
            var tpRelaciona = (bt03.className == 'abaSelected') ? 'U' : (bt01.className == 'abaSelected') ? 'C' : '';
            var idTipoDocumento = (inTipoPessoa == 'PJ') ? '' : $F('idTipoDocumentoACli');

            for (var i = 0; i < $('tableClientes').select('[class="radio"]').length; i++) {
                if ($('tableClientes').select('[class="radio"]')[i].checked) {
                    inLinhaSelecionada = true;
                    indexSelecionado = i;
                    break;
                }
            }

            if (!inLinhaSelecionada) {
                alert('Por favor, selecione um cliente.')

            } else {
                //if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
                var nrDocumento = $('tableClientes').select('[class="class_nrdocumento"]')[indexSelecionado].innerText;

                new Ajax.Request('loadDadosPrePago.do', {
                    method: 'get',
                    parameters: {
                        indexSelecionado: indexSelecionado,
                        idPessoa: idPessoa,
                        idLinhaTelefonica: idLinhaTelefonica,
                        tpRel: tpRelaciona,
                        tpPes: inTipoPessoa,
                        tpDoc: idTipoDocumento,
                        nrDoc: nrDocumento
                    },
                    contentType: 'text/xml',
                    onComplete: function(r) {

                        var xmlString = r.responseText;
                        var oXml      = new ActiveXObject("microsoft.xmldom");
                        oXml.async    = false;
                        var regExp    = new RegExp ('&', 'gi') ;
                        xmlString     = xmlString.replace(regExp,"&amp;");
                        
                        oXml.loadXML(xmlString);

                        var dom = parseXml(xmlString);
                        var jsonString = xml2json(dom, '');
                        var jsonObj = jsonString.evalJSON();

                        if (jsonObj.AjaxErrorHandlerVO && jsonObj.AjaxErrorHandlerVO.friendlyMessage) {

                            exceptionMessage = oXml.selectSingleNode("/AjaxErrorHandlerVO/exceptionMessage").text;
                            friendlyMessage  = oXml.selectSingleNode("/AjaxErrorHandlerVO/friendlyMessage").text;
                            alert(friendlyMessage);

                            // Cliente
                            if (bt01.className=="abaSelected") {
                                ttPesquisaInicial = true;
                                limparDadosEndereco();
                                limparFormCliente();
                            } else {
                                // Usuário
                                if (bt03.className == "abaSelected") {
                                    limparFormUsuario();
                                }
                            }

                            if (inTipoPessoa == 'PJ' && bt01.className == 'abaSelected') {
                                $('nrCNPJA').value = nrDocumento;
                            } else {
                                $('idTipoDocumentoACli').value = idTipoDocumento;
                                $('nrDocumentoACli').value = nrDocumento;
                            }

                            if (bt01.className == 'abaSelected') {
                                loadEnderecos(inTipoPessoa, "C");
                            } else if (bt03.className=="abaSelected") {
                                loadEnderecos(inTipoPessoa, "U");
                            }

                        } else {

                            clientePesquisado = jsonObj;
                            if (inTipoPessoa == 'PF' && bt01.className == 'abaSelected') {
                                ttPesquisaInicial = true;
                                setFormClientePFByVO(oXml);
                            } else if (inTipoPessoa=="PJ" && bt01.className == 'abaSelected') {
                                ttPesquisaInicial = true;
                                setFormClientePJByVO(oXml);
                            } else if (bt03.className == 'abaSelected') {
                                setFormUsuarioByVO(oXml);
                            }

                        }

                        if (top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();

                        if (inTipoPessoa == 'PF' && $('idTipoDocumentoACli').options[$('idTipoDocumentoACli').selectedIndex].text == 'CPF') {
                            $('idTipoDocumentoACli').disabled = true;
                        }

                        $('pesquisaCliente').remove();
                        inAlteracaoCliente = false;

                    },
                    onFailure: function(e){alert("[Failure] "+e.description);},
                    onException: function(transport, e){alert("[Exception] "+e.description+"\n"+transport.xml);}
                })
            }

        }

        paginacao = function(nrPagina) {

            if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();

            var inTipoPessoa = $('tpPessoaF').checked ? 'PF' : 'PJ';
            var tpRelaciona = (bt03.className == 'abaSelected') ? 'U' : (bt01.className == 'abaSelected') ? 'C' : '';
            var idTipoDocumento = (inTipoPessoa == 'PJ') ? '' : $F('idTipoDocumentoACli');
            var nrDocumento = $('tbClientes').select('[class="class_nrdocumento"]')[0].innerText;

            new Ajax.Updater('containerPesquisaCliente', 'getListaClientes.do', {
                method: 'get',
                parameters: {
                    tpPes: inTipoPessoa, limit: 2,
                    tpRel: tpRelaciona, limit: 1,
                    tpDoc: idTipoDocumento, limit: 10,
                    nrDoc: nrDocumento, limit: 18,
                    nrPag: nrPagina
                },
                onComplete: function(r){
                    if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
                },
                onFailure: function(e){alert("[Failure] "+e.description);},
                onException: function(transport, e){alert("[Exception] "+e.description+"\n"+transport.xml);}
            });
        }

        showDetalhesEnderecoPrincipal = function(index) {

            createNewPopUp('detalhesEndereco', 'Endereço', 700, 200, null, 'detalhesEndereco.do?index=' + index + '&inTipoPessoa=<%=inTipoPessoa%>', true, null);

        }

        filtrar = function(){
            try{
                var nrPaginacao = 1;
                var nrLinha = $('nrLinhaFiltro').value.gsub('[^0-9]','');
                var nrConta = $('nrContaFiltro').value.gsub('[^0-9]','');
                var inTipoPessoa = 'PJ';
                var tpRelaciona = (bt03.className == 'abaSelected') ? 'U' : (bt01.className == 'abaSelected') ? 'C' : '';
                var idTipoDocumento = '';
                var nrDocumento = $('nrCNPJA').value.gsub('[^0-9]','');
    
                var params = $H();
                params.set('tpPes', inTipoPessoa);
                params.set('tpRel', tpRelaciona);
                params.set('tpDoc', idTipoDocumento);
                params.set('nrDoc', nrDocumento);
                params.set('nrLin', nrLinha);
                params.set('nrCon', nrConta);
                params.set('nrPag', nrPaginacao);

                if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
                new Ajax.Updater('containerPesquisaCliente', 'getListaClientes.do', {
                    method: 'get',
                    parameters: params,
                    onComplete: function(r){
                        if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
                    },
                    onFailure: function(e){
                        alert("[Failure] "+e.description);
                    },
                    onException: function(transport, e){
                        alert("[Exception] "+e.description+"\n"+transport.xml);
                    }
                });
            }catch(e){
                alert(e.description);
            }
        }

    </script>
</head>
<body>

<form method="post" action="pesquisaCliente.do" id="tableClientes" name="tableClientes">

    <%
    int nrPaginas = 1;
    boolean hNext = false;
    try{
        hNext   = (form.getClientesPesquisados().getPaginacao()!=null && form.getClientesPesquisados().getPaginacao().getHasNext()==1)?true:false;
        nrPaginas = Integer.parseInt(form.getPageNumber());
    }catch(Exception e){
        nrPaginas = 1;
    }
    if(nrPaginas > 1 || hNext){%>
    <table width="783" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;margin-top:3px;">
        <tr>
            <td align="center">Foram encontrados múltiplos resultados. Selecione a opção desejada e clique em<b>&nbsp;Prosseguir.</b></td>
        </tr>
    </table>
    <%}%>

    <%if(!"PF".equals(inTipoPessoa)){%>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

    <table width="765" border="0" cellpadding="2" cellspacing="2">
        <tr>
            <td align="right">Linha:&nbsp;</td>
            <td align="left"><input type="text" name="nrLinhaFiltro" id="nrLinhaFiltro" maxlength="14" onkeyup="maskPhoneNumberObj(this)"/></td>
            <td align="right">Conta:&nbsp;</td>
            <td align="left"><input type="text" name="nrContaFiltro" id="nrContaFiltro" maxlength="15"/></td>
            <td align="right">
                <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" 
                     style="border:none;cursor:pointer;" 
                     onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';" 
                     onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';"
                     onClick="filtrar();"/>
            </td>
        </tr>
    </table>
    <%}%>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

    <vivo:tbTable selectable="true"
                  layoutWidth="760"
                  layoutHeight="300"
                  tableWidth="760"
                  styleId="tbClientes"
                  sortable="true"
                  onRowClick="">

        <% if ("PF".equals(inTipoPessoa)) { %>

        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="15%" type="string">Nº <%=getTipoDocumento(form.getClientesPesquisados().getPFArray(0).getDocumentoArray(0).getIdTipoDocumento(), listas.getListaTipoDocumentoArray())%></vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="30%" type="string">Nome</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="14%" type="string">Linha</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="10%" type="string">Tipo da linha</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="15%" type="string">Estado da linha</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="10%" type="string">Endereço</vivo:tbHeaderColumn>
        </vivo:tbHeader>

        <% } else { %>

        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="10%" type="string">Nº CNPJ</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="25%" type="string">Razão Social</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="10%" type="string">Nº Conta</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="15%" type="string">Linha</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="10%" type="string">Tipo da linha</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="15%" type="string">Estado da linha</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="10%"  type="string">Endereço</vivo:tbHeaderColumn>
        </vivo:tbHeader>

        <% } %>

        <vivo:tbRows>

            <% if ("PF".equals(inTipoPessoa)) { %>

            <logic:iterate id="listaClientes" name="form" property="clientesPesquisados.PFArray" indexId="idx" type="br.com.vivo.fo.cliente.vo.PFDocument.PF">
                <vivo:tbRow key="Linha">
                    <vivo:tbRowColumn>
                        <input type="radio"
                               class="radio"
                               name="clienteSelecionado"
                               onclick="cdTipoLinha='<bean:write name="listaClientes" property="telefoneArray[0].idClassificacaoTipoLinha"/>';idPessoa='<bean:write name="listaClientes" property="idPessoa"/>';idLinhaTelefonica='<bean:write name="listaClientes" property="telefoneArray[0].idLinhaTelefonica"/>'"
                               value="<bean:write name="listaClientes" property="idPessoa"/>"/>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <span class="class_nrdocumento"><bean:write name="listaClientes" property="documentoArray[0].nrDocumento" /></span>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaClientes" property="nmPessoa"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaClientes" property="telefoneArray[0].nrTelefone"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaClientes" property="telefoneArray[0].idClassificacaoTipoLinha"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaClientes" property="telefoneArray[0].dsEstadoLinha"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <img src="<%=request.getContextPath()%>/resources/images/bt_icon_pesquisar_lista.gif"
                             style="vertical-align:middle;"
                             onmouseup="showDetalhesEnderecoPrincipal(<%=idx%>)" />
                    </vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>

            <% } else { %>

            <logic:iterate id="listaClientes" name="form" property="clientesPesquisados.PJArray" indexId="idx" type="br.com.vivo.fo.cliente.vo.PJDocument.PJ">
                <vivo:tbRow key="Linha">
                    <vivo:tbRowColumn>
                        <input type="radio"
                               class="radio"
                               name="clienteSelecionado"
                               onclick="cdTipoLinha='<bean:write name="listaClientes" property="telefone.idClassificacaoTipoLinha"/>';idPessoa='<bean:write name="listaClientes" property="idPessoa"/>';idLinhaTelefonica='<bean:write name="listaClientes" property="telefone.idLinhaTelefonica"/>'"
                               value="<bean:write name="listaClientes" property="idPessoa"/>"/>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <span class="class_nrdocumento"><bean:write name="listaClientes" property="nrCNPJ" /></span>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaClientes" property="nmRazaoSocial"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaClientes" property="telefone.nrConta"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaClientes" property="telefone.nrTelefone"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaClientes" property="telefone.idClassificacaoTipoLinha"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaClientes" property="telefone.dsEstadoLinha"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <img src="<%=request.getContextPath()%>/resources/images/bt_icon_pesquisar_lista.gif"
                             style="vertical-align:middle;"
                             onmouseup="showDetalhesEnderecoPrincipal(<%=idx%>)" />
                    </vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>

            <% } %>

        </vivo:tbRows>
    </vivo:tbTable>

    <table width="765" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;margin-top:3px;">
        <tr>
            <td valign="middle" width="100" nowrap><b>&nbsp;Legendas:</b></td>
            <td width="800">
                <img src="<%=request.getContextPath()%>/resources/images/bt_icon_pesquisar_lista.gif" style="vertical-align:middle;" />
                Exibir detalhes do endereço principal
            </td>
        </tr>
    </table>

    <table width="765" id="tablePaginacao">
        <tr>
            <td width="50%" align="left">
                <%
                int nrPagina = 0;
                try{
                    nrPagina = Integer.parseInt(form.getPageNumber());
                }catch(Exception e){}
                boolean hasNext = (form.getClientesPesquisados().getPaginacao() != null
                    && form.getClientesPesquisados().getPaginacao().getHasNext() == 1) ? true : false;
                if (nrPagina > 1) {
                %>
                    <img src="<%=request.getContextPath()%>/resources/images/bt_anterior_nrml.gif" style="cursor:pointer" onmouseup="paginacao(<%=(nrPagina-1)%>)" />
                <% } %>
            </td>
            <td width="50%" align="right">
                <% if (hasNext) { %>
                <img src="<%=request.getContextPath()%>/resources/images/bt_proxima_nrml.gif" style="cursor:pointer" onmouseup="paginacao(<%=(nrPagina+1)%>)" />
                <% } %>
            </td>
        </tr>
    </table>

    <table width="765">
        <tr>
            <td align="right" style="padding-top:30px;">
                <img id="btProsseguir"
                     src="/FrontOfficeWeb/resources/images/bt_prosseguir_nrml.gif"
                     onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_prosseguir_over.gif';"
                     onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_prosseguir_nrml.gif';"
                     style="cursor:pointer;border:none;"
                     onmouseup="selecionarLinhaCliente()">
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            </td>
        </tr>
    </table>

</form>

</body>
</html>