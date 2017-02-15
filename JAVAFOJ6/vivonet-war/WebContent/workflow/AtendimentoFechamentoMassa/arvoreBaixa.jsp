<!--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento Workflow
Versão.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:40 $
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>

<bean:define id="form"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFormTemp" />
<bean:define id="scriptArvoreBaixa"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFechamentoMassaForm.scriptArvoreBaixa" />

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">

<script src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/xtree.css">
<acesso:controlHiddenItem nomeIdentificador="wor_arvb_verpagina">
<vivo:body idTable="tbMain" idDiv="dvMain" height="210" width="770">
    <form>
        <html:hidden name="form" property="idBaixa"/>
        <html:hidden name="form" property="comentario"/>
        <html:hidden name="form" property="documentoAssociado"/>
        <table width="100%" border="0" bgcolor="#E3ECF4">
            <tr>
                <td valign="top" width="450">
                    <script>
	                     <%=scriptArvoreBaixa%>    
                    </script>
                </td>
                <td valign="top" width="325">
                    <table width="325" border="0" bgcolor="#E3ECF4">
                    
                        <tr>
                            <td width="125" align="left">Código de Baixa:</td>
                            <td width="200" align="left"><b><div id="dvCodigoBaixa"></div></b></td>
                        </tr>

                        <tr>
                            <td>Resposta Padrão:</td>
                            <td id="tdRespostaPadrao"></td>
                        </tr>
                        <tr>
                            <td>Comentário:</td>
                            <td><textarea  id="comentarioTxt" rows="3" style="width=200" onblur="return checaTextarea(this,1000);return false;" onkeyup="return checaTextarea(this,1000);return false;"></textarea></td>
                        </tr>
                        <tr>
                            <td>Documento Associado:</td>
                            <td><textarea id="documentoAssociadoTxt" rows="2" style="width=200" onblur="return checaTextarea(this,1000);return false;" onkeyup="return checaTextarea(this,1000);return false;"></textarea></td>
                        </tr>
                    </table>
                    <table width="100%" border="0" bgcolor="#E3ECF4">
                        <tr>
                            <td align="left">
                            <acesso:controlHiddenItem nomeIdentificador="wor_arvb_salvar">
                            <vivo:botao id="btAplicar" width="60px" height="10px" value="Gravar" styleClass="btTemplate" onclick="submitAplicar()"/>
                            </acesso:controlHiddenItem>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    <form>
</vivo:body>
   
</acesso:controlHiddenItem>
<script language="javascript">
    
    
    function checaTextarea(obj, tamanho){
      if(obj.value.length>tamanho){
        obj.value = obj.value.substr(0,tamanho);
        alert("O campo comentário não aceita mais de "+tamanho+" caracteres");
      }
    }

    function initPagina() { }

    function submitAplicar() {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        document.forms[0].elements["idBaixa"].value = document.all['dvCodigoBaixa'].innerText;
        document.forms[0].elements["comentario"].value = document.all['comentarioTxt'].innerText;
        document.forms[0].elements["documentoAssociado"].value = document.all['documentoAssociadoTxt'].innerText;

        document.forms[0].method = "POST";
        document.forms[0].action = "encerrarGravar.do";
        //?idBaixa=" + document.all['dvCodigoBaixa'].innerText
        document.forms[0].target = "";
        document.forms[0].submit();
    }
    
    function iniciaEncerramento(codBaixa) {

        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
       
        document.forms[0].idBaixa.value = codBaixa;
        document.forms[0].method = "POST";
        document.forms[0].action = "/FrontOfficeWeb/workflow/AtendimentoWorkflow/listaEncerrarPesquisarResposta.do";
        document.forms[0].target = "";
        document.forms[0].submit();

    }
    
    function mostraDadosArvore(idBaixa, respostaPadrao){
        document.all['dvCodigoBaixa'].innerText = idBaixa;
        document.all['tdRespostaPadrao'].innerText = respostaPadrao;   
    }
    
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

</script>

