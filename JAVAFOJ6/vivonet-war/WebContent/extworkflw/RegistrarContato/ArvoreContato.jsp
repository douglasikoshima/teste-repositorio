<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="scriptArvore" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.scriptArvore" />
<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />

<netui-template:template templatePage="/resources/jsp/templateUsuarioArvore.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script type="text/javascript" language="javascript">
            <!--
                function replaceAll( str, from, to ) {
                    var idx = str.indexOf( from );
                    while ( idx > -1 ) {
                        str = str.replace( from, to );
                        idx = str.indexOf( from );
                    }
                    return str;
                }

                function LTrim( value ) {
                    var re = /\s*((\S+\s*)*)/;
                    return value.replace(re, "$1");
                }

                function RTrim( value ) {
                    var re = /((\s*\S+)*)\s*/;
                    return value.replace(re, "$1");
                }

                function trim( value ) {
                    return LTrim(RTrim(value));
                }

                function pesquisar(){
                    var f = document.forms[0];
                    if(f.txtPesquisa.value.indexOf('%')>-1)
                        f.txtPesquisa.value = replaceAll(f.txtPesquisa.value,"%","");
                    if(f.txtPesquisa.value.indexOf('*')>-1)
                        f.txtPesquisa.value = replaceAll(f.txtPesquisa.value,"*","");
                    f.txtPesquisa.value = trim(f.txtPesquisa.value);
                    if(f.txtPesquisa.value.length>4){
                        document.forms[0].target = "ifrmPesquisa";
                        document.forms[0].action = "pesquisaArvore.do";
                        dvPesquisa.style.display = '';
                        document.forms[0].submit();
                    }else{
                        return false;
                    }
                }

                function pressEnter(obj){
                    if(event.keyCode==13){
                        event.keyCode = 0;
                        if(obj.value.length>4){
                            pesquisar();
                        }else{
                            return false;
                        }
                    }
                }

                function iniciaAtendimento(idContato, urlContato, inRelacionamento) {

                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    parent.atualizaURL(urlContato);

                    if ((idContato == undefined
                        || idContato == null
                        || idContato == ''
                        || idContato == 'null') && top.frameApp.idContato != undefined) {
                        idContato = top.frameApp.idContato;
                    }
                    top.frameApp.idContato = idContato;

                    if(inRelacionamento == 0){
                        <logic:equal name="Form" property="inResponsavelAbertura" value="1">
                            alert("Um usuário não pode abrir esse contato!");
                        </logic:equal>
                        <logic:equal name="Form" property="inResponsavelAbertura" value="2">
                            alert("Um cliente não pode abrir esse contato!");
                        </logic:equal>
                        <logic:equal name="Form" property="inResponsavelAbertura" value="6">
                            alert("Um prospect não pode abrir esse contato!");
                        </logic:equal>
                        <logic:equal name="Form" property="inResponsavelAbertura" value="7">
                            alert("Um não cliente não pode abrir esse contato!");
                        </logic:equal>
                        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
                        return;
                    }
                    parent.document.getElementById('abasRegContato').style.display = 'block';
                    document.forms[0].target = "ifrmAbas";
                    document.forms[0].action = "iniciaAtendimento.do?IDCONTATO="+idContato;
                    document.forms[0].submit();
                    //parent.ifrmAbas.document.location.replace("iniciaAtendimento.do?IDCONTATO="+idContato);
                    parent.redimensionaFrames('botaoEsqArvore');
                    //parent.exibeBotoes();
                    parent.setFlagClickArvore();
                }

                function loadPage(idContato, urlContato, inRelacionamento){
                    url = '<%=request.getContextPath()%>' + urlContato;
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    if(inRelacionamento == 0){
                        <logic:equal name="Form" property="inResponsavelAbertura" value="1">
                            alert("Um usuário não pode abrir esse contato!");
                        </logic:equal>
                        <logic:equal name="Form" property="inResponsavelAbertura" value="2">
                            alert("Um cliente não pode abrir esse contato!");
                        </logic:equal>
                        <logic:equal name="Form" property="inResponsavelAbertura" value="6">
                            alert("Um prospect não pode abrir esse contato!");
                        </logic:equal>
                        <logic:equal name="Form" property="inResponsavelAbertura" value="7">
                            alert("Um não cliente não pode abrir esse contato!");
                        </logic:equal>
                        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
                        return;
                    }
                    parent.document.getElementById('abasRegContato').style.display = 'none';
                    parent.document.getElementById('ifrmAbas').src = url;
                    parent.redimensionaFrames('botaoEsqArvore');
                    parent.setFlagClickArvore();
                    if( top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                }

                function expandirNo(idContato, urlContato){
                    parent.atualizaURL(urlContato);
                    if (!tree.getSelected().isAddEnabled()) {
                        return;
                    }
                    //Liga animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    document.forms[0].target = "ifrmArvoreContato";
                    document.forms[0].action = "expandeArvoreContato.do?IDCONTATO="+idContato+"&idFase=<bean:write name="Form" property="idFase"/>";
                    document.forms[0].submit();
                }

                // Função que inicia um atendimento a partir do fechamento
                // de processo em atendikento workflow.
                function iniciaAtendimentoContato() {
                    iniciaAtendimento('<bean:write name="Form" property="idContato"/>', '', '1');
                }

                onload = function() {
                    parent.redimensionaFrames('botaoDirContato');
                    if (parent.parent.inTI!=null && parent.parent.inTI == true) parent.parent.controlCombos1();
                    <logic:equal name="Form" property="flagAtendimentosCorrentes" value="true">
                    // Se há atendimentos correntes e a aba atendimento é clicada novamente, na tela aparece a árvore e a aba de
                    // processos correntes. Essa função teve que ser chamada por aqui ao invés de na página AtendimentoFrame.jsp
                    // porque controlaProcessosCorrentes() faz uso de atributos do frame da árvore que só está carregado totalmente
                    // aqui Liga animação
                    if(top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
                    parent.controlaProcessosCorrentes();
                    </logic:equal>
                    <logic:notEmpty name="Form" property="idContato">
                    iniciaAtendimentoContato();
                    </logic:notEmpty>
                }

            -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">    
        <html:form action="/extworkflw/RegistrarContato/expandeArvoreContato" method="post" onsubmit="return false;">
            <%--<html:hidden name="Form" property="user"/>--%>
            <html:hidden name="Form" property="idAtendimento"/>
            <html:hidden name="Form" property="nrProtocolo"/>
            <html:hidden name="Form" property="idGrupoAbertura"/>
            <html:hidden name="Form" property="idSegmentacao"/>
            <html:hidden name="Form" property="idTipoCarteira"/>
            <html:hidden name="Form" property="idTipoLinha"/>
            <html:hidden name="Form" property="idUfOperadora"/>
            <html:hidden name="Form" property="idLinhaAtendimento"/>
            <html:hidden name="Form" property="inResponsavelAbertura"/>
            <html:hidden name="Form" property="inTipoPessoa"/>
            <html:hidden name="Form" property="nomeContato"/>
            <html:hidden name="Form" property="telContato"/>
            <html:hidden name="Form" property="telContatoFrm"/>
            <html:hidden name="Form" property="nrLinha"/>
            <html:hidden name="Form" property="nrConta"/>
            <html:hidden name="Form" property="idConta"/>
            <html:hidden name="Form" property="contaSelecionada"/>
            <html:hidden name="Form" property="idLinha"/>
            <html:hidden name="Form" property="linhaSelecionada"/>
            <html:hidden name="Form" property="idClienteDePara"/>
            <html:hidden name="Form" property="idUsuarioDePara"/>
            <html:hidden name="Form" property="idPessoa"/>
            <html:hidden name="Form" property="idDiscador"/>
            <html:hidden name="Form" property="idChamadaTelefonica"/>
            <html:hidden name="Form" property="fila"/>
            <html:hidden name="Form" property="abreProcessoReaRei"/>
            <html:hidden name="Form" property="idAtendimentoSituacao"/>
            <html:hidden name="Form" property="nrProtocoloSituacao"/>
            <html:hidden name="Form" property="idTipoReaberturaProcesso"/>
            <html:hidden name="Form" property="qtDias"/>
            <html:hidden name="Form" property="nmTipo"/>
            <html:hidden name="Form" property="nomeContatoAlterado"/>
            <html:hidden name="Form" property="carregaLinha"/>
            <table id="tableArvore" width="100%" border="0" cellspacing="1" cellpadding="2" class="tbl_bgBlue" align="center">
                <tr>
                    <td class="tbl_titulo">&Aacute;rvore de contatos</td>
                </tr>
                <tr>
                    <td>
                        <div id="divArvore" style="top: 0px; left: 0px; width:322px; height: 285px; padding: 1px; overflow: auto; border-bottom:solid 1px;">
                                <script>
                                  <%=scriptArvore%>
                                </script>
                            <iframe name="ifrmArvoreContato" style="visibility:hidden;" width="1" height="1"></iframe>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        Pesquisa por: <input name="txtPesquisa" type="text" size="30" maxlength="40" onkeydown="pressEnter(this);">&nbsp;<img id="btPesquisar" src="../../resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:pointer;" onClick="pesquisar();return false;"/> (* m&iacute;nimo 5 caracteres)
                    </td>
                </tr>
            </table>
        </html:form>
        <vivo:quadroFlutuante id="dvPesquisa" label="Pesquisa de Contatos" scroll="no" idIframe="ifrmPesquisa" width="760" height="301" spacesTop="0" spacesLeft="2" display="none" url="<%=request.getContextPath()%>" onclick="ifrmPesquisa.fechar();"/>        
    </netui-template:section>
</netui-template:template>