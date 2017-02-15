<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="arvoreEncerramentoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.arvoreEncerramentoVO"/>
<bean:define id="arvoreVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFArvoreVOArray"/>
<bean:define id="scriptArvoreBaixa"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.scriptArvoreBaixa" />

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">

<script src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/xtree.css">

<vivo:body idTable="tbMain" idDiv="dvMain" height="210" width="770">
    <form action="listaEncerrarGravar.do" name="atendimentoForm" method="post" tagId="formEncerrar" id="formEncerrar">
        <%--<html:hidden property="idUsuario" name="form"></html:hidden>--%>
        <html:hidden property="idBaixa" name="form"></html:hidden>
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
                            <td width="125" align="left">C√≥digo de Baixa:</td>
                            <td width="200" align="left"><b><bean:write name="arvoreEncerramentoVO" property="encerramentoVO.codBaixa"/></b></td>
                        </tr>

                        <tr>
                            <td>Resposta Padr√£o:</td>
                            <td><html:textarea property="encerramentoVO.dsRespostaPadrao" name="arvoreEncerramentoVO" rows="3" style="width=200" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td>Coment√°rio:</td>
                            <td><html:textarea property="encerramentoVO.dsComentario" name="arvoreEncerramentoVO" rows="3" style="width=200" onkeyup="checaTextarea(this, 500);"/></td>
                        </tr>
                        <tr>
                            <td>Documento Associado:</td>
                            <td><html:textarea property="encerramentoVO.dsDocumentoAssociado" name="arvoreEncerramentoVO" rows="2" style="width=200" onkeyup="checaTextarea(this, 500);"/></td>
                        </tr>
        		<tr>
            			<td align="right">CCC:</td>
            			<td>
                			<html:select name="form" property="tipoRetornoSel" title="tipoRetornoSel" style="width:260px;" value="1">
                    			<html:option value="1" key="tipoRetornoSel">CCC 1</html:option>
                    			<html:option value="2" key="tipoRetornoSel">CCC 2</html:option>
                    			<html:option value="3" key="tipoRetornoSel">CCC 3</html:option>
                			</html:select>
            			</td>
        		</tr>
        		<tr>
            			<td align="right">ERB:</td>
            			<td>
                			<html:select name="form" property="tipoRetornoSel" title="tipoRetornoSel" style="width:260px;" value="1">
                    			<html:option value="1" key="tipoRetornoSel">ERB 1</html:option>
                    			<html:option value="2" key="tipoRetornoSel">ERB 2</html:option>
                    			<html:option value="3" key="tipoRetornoSel">ERB 3</html:option>
                			</html:select>
            			</td>
        		</tr>
                    </table>
                    <table width="100%" border="0" bgcolor="#E3ECF4">
                        <tr>
                            <td align="left">                            
                                <vivo:botao id="btAplicar" width="60px" height="10px" value="Salvar" styleClass="btTemplate" onclick="submitAplicar()"/>                            
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</vivo:body>
   
<script language="javascript">
    

    function checaTextarea(obj, tamanho){
      obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
    }
    
    function initPagina() { }

    function submitAplicar() {
        //Liga anima√ß√£o
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        
        if (parent.dvRetornoOperacao) {
            parent.dvRetornoOperacao.style.visibility="";
            targetFrame = "iframeRetornoOperacao";
        } else { 
            targetFrame = "";
        }

        document.forms["formEncerrar"].method = "POST";
        document.forms["formEncerrar"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/listaEncerrarGravar.do";
        document.forms["formEncerrar"].target = targetFrame;
        document.forms["formEncerrar"].submit();
    }
    
    function iniciaEncerramento(codBaixa) {
       
        //Inicio animaÁ„o
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        
        document.forms["formEncerrar"].idBaixa.value = codBaixa;
        document.forms["formEncerrar"].method = "POST";
        document.forms["formEncerrar"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/listaEncerrarPesquisarResposta.do";
        document.forms["formEncerrar"].target = "";
        document.forms["formEncerrar"].submit();

    }
    
    //Fim animaÁ„o
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

</script>


