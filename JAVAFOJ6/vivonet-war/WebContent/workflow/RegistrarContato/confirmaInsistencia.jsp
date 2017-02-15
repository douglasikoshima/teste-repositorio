<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="AtendimentoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO" />
<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />

<html>
    <head>
        <script type="text/javascript" src="../../resources/scripts/vivoval.js"></script>
        <script language="JavaScript">
            var tamanhoTdObservacao = 45;
            var tamanhoAreaObservacao = "310px";

            function checaTextarea(obj, tamanho){
              if(obj.value.length>tamanho){
                obj.value = obj.value.substr(0,tamanho);
                alert("O campo comentário não aceita mais de "+tamanho+" caracteres");
              }
            }

            function registrarInsistencia() {
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                document.forms["registrarContatoForm"].action = "registrarInsistencia.do";
                document.forms["registrarContatoForm"].submit();
            }

            function registrarComentario() {
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                document.forms["registrarContatoForm"].action = "registrarComentario.do";
                document.forms["registrarContatoForm"].submit();
            }

            function finalizar() {

                if (trim(document.forms["registrarContatoForm"].observacaoAtendimento.value) != ''){
                    registrarComentario();
                    return;
                }

                extra_vars="";
                filtro = top.frameCTI.filtro;

                if (filtro.telaOrigem==1) {
                    if (filtro.psqAv!=null) {
                        for (i=0; i<filtro.psqAv.length; i++) {
                            extra_vars+='&psqIdCampo='+filtro.psqAv[i].idCampo;
                            extra_vars+='&psqNmCampo='+filtro.psqAv[i].nmCampo;
                            extra_vars+='&psqTpComparacao='+filtro.psqAv[i].tpComparacao;
                            extra_vars+='&psqDsComparacao='+filtro.psqAv[i].dsComparacao;
                            extra_vars+='&psqValor='+filtro.psqAv[i].valor;
                            extra_vars+='&psqIdFormularioCampoValor='+filtro.psqAv[i].idFormularioCampoValor;
                        }
                    }
                }
                if (filtro.telaOrigem==3) {
                    extra_vars =
                    "&login="+filtro.massa.login+
                    "&dtSuspeitoInicio="+filtro.massa.dtSuspeitoInicio+
                    "&dtSuspeitoFim="+filtro.massa.dtSuspeitoFim+
                    "&textoContato="+filtro.massa.textoContato+
                    "&idContato="+filtro.massa.idContato;
                }

                if (filtro.telaOrigem == 'FILA_MEU_CLIENTE') {
                    extra_vars = '&' + filtro.queryString;
                }

                <logic:notEmpty name="Form" property="fila">
                    <logic:equal value="1" name="Form" property="fila">
                        if(top.frameApp.dvAtendimento)
                            top.frameApp.dvAtendimento.style.display = 'none';
                        else
                            top.frameApp.$('divPopupTI').style.display = 'none';
                        return false;
                    </logic:equal>
                    top.frameApp.location.href='<bean:write name="Form" property="fila"/>?voltar=1'+extra_vars;
                    return;
                </logic:notEmpty>

                if (top.frameApp.dvRetornoOperacao) {
                   top.frameApp.iframeRetornoOperacao.controlaProcessosCorrentes();
                } else {

                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    <logic:equal value="1" name="Form" property="fila">
                        top.frameApp.ifrmAtendimento.iframeRetornoOperacao.controlaProcessosCorrentes();
                        return;
                    </logic:equal>

                    if(typeof(top.frameApp.ti_frameAbas)!= "undefined"){
                        top.frameApp.ti_frameAbas.controlaProcessosCorrentes();
                    }

                }
            }
        </script>

        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_cins_verpagina">
        <form name="registrarContatoForm" action="registrarInsistencia.do">
         <html:hidden name="Form" property="idBaixa"/>
         <html:hidden name="Form" property="idBaixaMensagem"/>
         <html:hidden name="Form" property="observacaoFechamento"/>
         <%--<html:hidden name="Form" property="user"/>--%>
         <html:hidden name="Form" property="idGrupoAbertura"/>
         <html:hidden name="Form" property="idSegmentacao"/>
         <html:hidden name="Form" property="idTipoCarteira"/>
         <html:hidden name="Form" property="idTipoLinha"/>
         <html:hidden name="Form" property="idUfOperadora"/>
         <html:hidden name="Form" property="inResponsavelAbertura"/>
         <html:hidden name="Form" property="nomeContato"/>
         <html:hidden name="Form" property="idChamadaTelefonica"/>
         <html:hidden name="Form" property="telContato"/>
         <html:hidden name="Form" property="nrLinha"/>
         <html:hidden name="Form" property="nrConta"/>
         <html:hidden name="Form" property="idConta"/>
         <html:hidden name="Form" property="idContato"/>
         <html:hidden name="Form" property="contaSelecionada"/>
         <html:hidden name="Form" property="idLinha"/>
         <html:hidden name="Form" property="linhaSelecionada"/>
         <html:hidden name="Form" property="idClienteDePara"/>
         <html:hidden name="Form" property="telContatoFrm"/>
         <html:hidden name="Form" property="idUsuarioDePara"/>
         <html:hidden name="Form" property="idDiscador"/>
         <html:hidden name="Form" property="idPessoa"/>
         <html:hidden name="Form" property="inTipoPessoa"/>
         <html:hidden name="Form" property="idAtendimento"/>
         <html:hidden name="Form" property="nrProtocolo"/>
         <html:hidden name="Form" property="descricaoCompleta"/>
         <html:hidden name="Form" property="idAtendimentoSituacao"/>
         <html:hidden name="Form" property="nrProtocoloSituacao"/>
         <html:hidden name="Form" property="idTipoReaberturaProcesso"/>
         <html:hidden name="Form" property="nmTipo"/>
         <html:hidden name="Form" property="qtDias"/>
         <html:hidden name="Form" property="nmTipo"/>
         <html:hidden name="Form" property="abertura"/>
         <html:hidden name="Form" property="fila"/>
         <html:hidden name="Form" property="nomeContatoAlterado"/>
         <html:hidden name="Form" property="carregaLinha"/>
            <center>O contato <bean:write name="AtendimentoVO" property="arvoreAtendimentoVO.descricaoCompleta" />
            já esta registrado e aberto com o código <bean:write name="AtendimentoVO" property="atendimentoSituacaoVO.nrProtocolo" format="######"/>,
            deseja registrar insistência?</center><br>
            <table border="0" align="center">
            <tr>
            <td align="center">
            <table width="100%">
                <tr>
                    <td align="left" width="45">Observação:</td>
                    <td id="tdAreaObservacao" align="left"><html:textarea name="Form" property="observacaoAtendimento" onblur="return checaTextarea(this,1000);return false;" onkeyup="return checaTextarea(this,1000);return false;" style="WIDTH:450;color: #006699;" rows="4"/></td>
                </tr>
            </table>
            </td>
            </tr>
            <tr>
            <td>
            <table width="100%">
                <tr>
                    <td width="50%" align="right">
                    <acesso:controlHiddenItem nomeIdentificador="wor_cins_sim">
                        <vivo:botao id="btSim" width="60px" height="10px" value="Sim" styleClass="btTemplate" onclick="registrarInsistencia();"/>
                    </acesso:controlHiddenItem>
                    </td>
                    <td width="50%" align="left">
                    <acesso:controlHiddenItem nomeIdentificador="wor_cins_nao">
                        <vivo:botao id="btNao" width="60px" height="10px" value="Não" styleClass="btTemplate" onclick="finalizar();"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            </td>
            </tr>
            </table>
        </form>
    </acesso:controlHiddenItem>
    </body>
</html>
<script>
    //Fim animação
    parent.escondeBotoes();
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>
