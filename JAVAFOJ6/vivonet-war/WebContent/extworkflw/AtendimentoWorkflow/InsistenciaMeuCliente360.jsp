<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>

<html:hidden name="form" property="idAtendimento" title="idAtendimento" />
<html:hidden name="form" property="idAtividade" />
<html:hidden name="form" property="inCRI" />
<html:hidden name="form" property="fila" />

<table border="0" width="100%">
    <tr>
        <td valign="top" width="60">Motivo</td>
        <td valign="top" align="left" style="padding-left:4px;">
            <html:select name="form"
                         property="motivoSel"
                         title="motivoSel"
                         style="width:250px">
                <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                <html:options collection="motivosVO"
                              property="idMotivo"
                              labelProperty="dsMotivo" />
            </html:select>
        </td>
        <td valign="top">Comentário:</td>
        <td rowspan="4" style="padding-left:4px;">
            <html:textarea name="form"
                           property="comentario"
                           rows="3"
                           style="width:280px;height:90px;"
                           onkeyup="checaTextarea(this, 500);" />
        </td>
    </tr>
    <tr valign="top">
        <td valign="top" width="60">Total de Insistências:</td>
        <td valign="top" align="left">
            <html:text name="form" property="qtInsistencia" size="4" title="qtInsistencia" disabled="true"/>
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
               onmouseup="submitAplicar('insistenciaGravar')" />
        </td>
    </tr>
</table>