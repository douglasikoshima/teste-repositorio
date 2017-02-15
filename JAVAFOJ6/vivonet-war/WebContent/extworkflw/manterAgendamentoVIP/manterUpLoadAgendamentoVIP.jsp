<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>


<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP" type="extworkflw.manterAgendamentoVIP.ManterAgendamentoVIPController.FormManterAgendamentoVIP"/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="Manter Upload Agendamento VIP"/>
<netui-template:setAttribute name="modulo" value="VOL/TAV"/>
<netui-template:section name="headerSection">
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javascript">

        function enviarArquivo() {
            f = document.forms[0];
            if ($F('file')=="") {
                alert('Por favor, selecione um arquivo para upload.');
            } else {
                if (validaArquivoEntrada($F('file'))) {
                    f.submit();
                }
            }
        }

        function validaArquivoEntrada(pathArquivo) {
            var extensao = pathArquivo.substring(pathArquivo.lastIndexOf(".")+1, pathArquivo.length);
            if (extensao.toLowerCase() != "txt") {
                alert("Por favor, faça o upload de um arquivo TXT.");
                return false;
            }
            return true;
        }

        function paginacao(idx){
            document.formManterAgendamentoVIP.paginaAtual.value = idx;
            document.formManterAgendamentoVIP.action = "paginacao.do";
            document.formManterAgendamentoVIP.submit();
        }

        function proxima(){
            document.formManterAgendamentoVIP.paginaAtual.value = parseInt(document.formManterAgendamentoVIP.paginaAtual.value) + 1;
            document.formManterAgendamentoVIP.action = "paginacao.do";
            document.formManterAgendamentoVIP.submit();
        }

        function anterior(){
            document.formManterAgendamentoVIP.paginaAtual.value = parseInt(document.formManterAgendamentoVIP.paginaAtual.value) - 1;
            document.formManterAgendamentoVIP.action = "paginacao.do";
            document.formManterAgendamentoVIP.submit();
        }

        function validaDDD(obj){
            if(obj.value.length == 2){
                document.getElementById('FormManterAgendamentoVIP').linha.focus();
            }
        }

        function limpar(){
            document.getElementById('FormManterAgendamentoVIP').ddd.value = "";
            document.getElementById('FormManterAgendamentoVIP').linha.value = "";
            return false;
        }

        function incluirLinha(){
            if(document.getElementById('FormManterAgendamentoVIP').ddd.value.length != 2){
                alert('DDD é um campo obrigatório, preencha o campo corretamente.');
                return false;
            }
            if(document.getElementById('FormManterAgendamentoVIP').linha.value.length != 8){
                alert('Linha é um campo obrigatório, preencha o campo corretamente.');
                return false;
            }
            document.getElementById('FormManterAgendamentoVIP').action  = "incluirLinha.do";
            document.getElementById('FormManterAgendamentoVIP').submit();
        }

        function pesquisar(){
            if(document.getElementById('FormManterAgendamentoVIP').dsTipoPesquisa.value == 0){
                alert("Selecione o tipo de Pesquisa, DDD ou Linha.");
                return false;
            }
            if(document.getElementById('FormManterAgendamentoVIP').dsTipoPesquisa.value == 'ddd'){
                if(document.getElementById('FormManterAgendamentoVIP').queryPesquisa.value.length == 0){
                    alert('Preencha o campo de pesquisa e tente novamente.');
                    return false;
                }
                if(document.getElementById('FormManterAgendamentoVIP').queryPesquisa.value.length > 2
                || document.getElementById('FormManterAgendamentoVIP').queryPesquisa.value.length < 2){
                    alert("DDD deve ter 2 dígitos. Preencha corretamente e tente novamente.");
                    return false;
                }
            }
            if(document.getElementById('FormManterAgendamentoVIP').dsTipoPesquisa.value == 'linha'){
                if(document.getElementById('FormManterAgendamentoVIP').queryPesquisa.value.length == 0){
                    alert('Preencha o campo de pesquisa e tente novamente.');
                    return false;
                }
                if(document.getElementById('FormManterAgendamentoVIP').queryPesquisa.value.length < 8){
                    alert('Linha deve ter 8 dígitos, preencha corretamente e tente novamente.');
                    return false;
                }

            }
            document.getElementById('FormManterAgendamentoVIP').action = "begin.do";
            document.getElementById('FormManterAgendamentoVIP').submit();
        }

        function excluirLinha(id){
            if (confirm('Deseja excluir a linha selecionada?')){
                document.getElementById('FormManterAgendamentoVIP').dddLinha.value = id;
                document.getElementById('FormManterAgendamentoVIP').action = "excluirLinha.do";
                document.getElementById('FormManterAgendamentoVIP').submit();
            }
        }
    </script>
</netui-template:section>
<netui-template:section name="bodySection">
<jsp:include page="/resources/menus/MenuExterno.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
<vivo:sessao id="qdMain" height="468" width="790" label="Parametrização" operacoes="Agendamento VIP" scroll="no">    
    <form id="FormManterAgendamentoVIP" action="uploadArquivo.do" enctype="multipart/form-data" method="post" name="formManterAgendamentoVIP" onSubmit="return false;">
        <html:hidden name="Form" property="paginaAtual" />
        <html:hidden name="Form" property="dddLinha" />
        <vivo:sessao id="consultaHexa" width="780" height="90" label="Agendamento VIP"  scroll="false" operacoes="Upload de Linha">
            <table cellpadding="10">
                <tr>
                    <td width="110" nowrap>Arquivo para upload:</td>
                    <td><html:file name="Form" property="file" styleId="file" style="width:400px;" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <img id="btEnviar"
                                           value="Enviar"
                                           src="/FrontOfficeWeb/resources/images/bt_enviar_nrml.gif"
                                           rolloverImage="/FrontOfficeWeb/resources/images/bt_enviar_over.gif"
                                           style="border:none;margin-left:4px;"
                                           onMouseUp="enviarArquivo()" />
                    </td>
                </tr>
            </table>
        </vivo:sessao>
        <vivo:sessao id="consultaHexa" width="780" height="235" label="Agendamento VIP"  scroll="false" operacoes="Lista de Linhas Premmiun">
            <table>
                <tr>
                    <td width="120">&nbsp;</td>
                    <td>Pesquisar por:</td>
                    <td>
                        <html:select name="Form"  property="dsTipoPesquisa" style="width:50px;">
                            <html:option value="0">----</html:option>
                            <html:option value="ddd">DDD</html:option>
                            <html:option value="linha">Linha</html:option>
                        </html:select>
                    </td>
                    <td><html:text name="Form" property="queryPesquisa" style="width:100px;" maxlength="8"/></td>
                    <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:hand" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';" onClick="pesquisar();" />
                    </td>
                </tr>
            </table>
            <logic:notEqual name="Form" property="semRegistros" value="true">
                <table width="100%" border="0">
                    <tr>
                        <td align="center">
                            <vivo:tbTable selectable="true" layoutWidth="500" layoutHeight="150" tableWidth="500" onRowClick="false" resize="true" styleId="tableTitle" sortable="true">
                                <vivo:tbHeader>
                                    <vivo:tbHeaderColumn align="center" width="45%"  type="string">DDD</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="50%"  type="string">Linha</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                                </vivo:tbHeader>
                                <vivo:tbRows>
                                    <logic:iterate name="Form" property="linhasPremmiun" id="lista" indexId="id">
                                        <vivo:tbRow key="linha">
                                            <vivo:tbRowColumn>
                                                    <bean:write name="lista" property="ddd" />
                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                                    <bean:write name="lista" property="linha" format="####-####"/>
                                            </vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                               <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="excluirLinha('<bean:write name="lista" property="ddd" /><bean:write name="lista" property="linha" />');" style="cursor: hand;" border="0" alt="Excluir Agendamento" >
                                            </vivo:tbRowColumn>
                                        </vivo:tbRow>
                                    </logic:iterate>
                                </vivo:tbRows>
                            </vivo:tbTable>
                            <table width="67%" align="center" border="0">
                                <tr>
                                    <td align="center">
                                    <%
                                    int pagAtual = Integer.parseInt(Form.getPaginaAtual());
                                    int pagTotal = Integer.parseInt(Form.getTotalPagina());
                                    int pagLimit = 10;
                                    //int pagMulti = (((pagAtual-(pagAtual%pagLimit))/pagLimit)*pagLimit)-1;
                                    int pagMulti = pagAtual-(pagAtual%pagLimit);
                                    int pagVolta = pagMulti - pagLimit + 1;
                                    //pagMulti = pagMulti<0?0:pagMulti;

                                    if(pagMulti>0){%>
                                        <a href="javaScript:paginacao('<%=pagVolta%>')"><<</a>&nbsp;
                                    <%}

                                    if(pagTotal!=0){
                                        for(int i=1; i<pagLimit+1; i++){
                                            int pag = pagMulti+i;
                                            if(pag<=pagTotal){
                                                if(pagAtual!=pag){%>
                                                <a href="javaScript:paginacao('<%=pag%>')"><%=pag%></a>
                                                <%}else{%>
                                                    <%=pag%>
                                                <%}
                                            }
                                        }
                                    }
                                    if(pagMulti+pagLimit<pagTotal){
                                        if(pagTotal<(pagLimit+pagMulti+1)){%>
                                            <a href="javaScript:paginacao('<%=pagAtual+(pagTotal-pagAtual)%>')">>></a>
                                        <%}else{%>
                                            <a href="javaScript:paginacao('<%=pagLimit+pagMulti+1%>')">>></a>
                                        <%}
                                    }%>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                </logic:notEqual>
                <logic:equal name="Form" property="semRegistros" value="true">
                    <table width="100%">
                        <tr>
                            <td align="center">
                                <br><br>
                                <b>Não existem registros para a pesquisa realizada.</b>
                            </td>
                        </tr>
                    </table>
                </logic:equal>
        </vivo:sessao>
        <vivo:sessao id="consultaHexa" width="780" height="72" label="Agendamento VIP" scroll="false" operacoes="Incluir Linha Premmiun">
            <table width="100%" border="0" height="100%">
                <tr>
                    <td>
                        DDD:&nbsp;<html:text name="Form" property="ddd" maxlength="2" onkeypress="validaDDD(this);" size="3" onkeyup="checaInteiro(this)"/>&nbsp; &nbsp; &nbsp;
                        Linha:&nbsp;<html:text name="Form" property="linha" onkeyup="checaInteiro(this)" maxlength="8" size="11"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <img id="btIncluir" onClick="incluirLinha();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'"  onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none;" />
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <img style="cursor:hand;border:none;" tabindex="3" id="btLimpar" onclick="return limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                    </td>
                </tr>
            </table>
        </vivo:sessao>
    </form>    
</vivo:sessao>
<vivo:alert atributo="msgStatus" scope="request"/>
</netui-template:section>
</netui-template:template>