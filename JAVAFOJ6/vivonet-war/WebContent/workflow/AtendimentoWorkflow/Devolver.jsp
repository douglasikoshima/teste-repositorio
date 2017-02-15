<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="wFMotivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>

<form action="devolverGravar.do" method="post" name="atendimentoForm">
    <table width="100%">
        <tr valign="top">
            <td width="10%">Motivo:</td>
            <td width="40%">
                <html:select name="form" property="motivoSel" title="motivoSel" style="width=200px">
                    <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                    <html:options collection="wFMotivosVO" property="idMotivo" labelProperty="dsMotivo"/>
                </html:select>
            </td>
            <td width="10%">Comentário:</td>
            <td width="40%"><html:textarea name="atdWFComumVO" property="dsObservacao" rows="3" style="width:280px" onkeyup="checaTextarea(this, 500);"/></td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td align="center">
                <vivo:botao id="btSalvar" width="60px" height="10px" value="Salvar" styleClass="btTemplate" onclick=""/>
            </td>
        </tr>
    </table>
</form>

<script language="javascript">
    function checaTextarea(obj, tamanho){
      obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
    }
</script>