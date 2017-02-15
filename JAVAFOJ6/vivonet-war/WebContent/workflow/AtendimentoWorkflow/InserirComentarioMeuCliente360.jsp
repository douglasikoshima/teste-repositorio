<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="gruposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFGrupoVOArray"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>
<bean:define id="usuarioVIVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.usuarioVIVO" />

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>

<html:hidden name="form" property="idAtendimento" />
<html:hidden name="form" property="fila" />
<html:hidden name="form" property="idAtividade" />
<html:hidden name="form" property="inCRI" />
<html:hidden name="form" property="dsMotivo" />
<html:hidden name="form" property="grupoSel" value="" />
<html:hidden name="form" property="usuarioSel" value="" />

<table border="0" width="100%">
    <tr>
        <td valign="top">Comentário:</td>
        <td rowspan="4" style="padding-left:4px;">
            <html:textarea name="form"
                           property="atendimentoWorkflowVO.atendimentoWorkflowComumVO.dsObservacao"
                           styleId="comentario"
                           rows="3"
                           style="width:360px;height:90px;"
                           onkeyup="checaTextarea(this,500);" />
        </td>
    </tr>
    <tr>
        <td height="20" colspan="4">
    </tr>
    <tr>
        <td height="20" colspan="4">
    </tr>
    <tr>
        <td colspan="4" valign="bottom" align="right" width="100%">
            <input type="button"
               class="input_small"
               value="Gravar"
               onmouseup="submitAplicar('inserirComentarioGravar')" />
        </td>
    </tr>
</table>