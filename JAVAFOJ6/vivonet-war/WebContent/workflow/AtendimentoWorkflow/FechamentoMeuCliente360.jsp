<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>
<bean:define id="scriptValidacao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.scriptValidacao" />

<html:hidden name="form" property="idAtendimento" />
<html:hidden name="form" property="idAtividade" />
<html:hidden name="form" property="inCRI" />
<html:hidden name="form" property="fila" />

<table width="100%" border="0">
    <tr valign="top">
        <td valign="top" width="60">Motivo:</td>
        <td valign="top" align="left" style="padding-left:5px;">
            <html:select name="form" property="motivoSel" title="motivoSel" style="width=250px">
                <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/>
            </html:select>
        </td>
        <td>Comentário:</td>
        <td><html:textarea name="form" property="comentario" rows="3" style="width:280px;height:90px;" onkeyup="checaTextarea(this, 500);"/></td>
    </tr>
    <tr>
        <td colspan="4" align="right">
            <input type="button"
               class="input_small"
               value="Gravar"
               onmouseup="submitAplicar('fechamentoGravar')" />
        </td>
    </tr>
</table>