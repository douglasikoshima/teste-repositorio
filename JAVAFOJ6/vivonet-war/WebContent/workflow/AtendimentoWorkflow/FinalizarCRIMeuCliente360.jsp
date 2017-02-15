<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm" />
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO" />
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray" />

<html:hidden name="form" property="idAtendimento" title="idAtendimento"/>
<html:hidden name="form" property="idAtividade"/>
<html:hidden name="form" property="inCRI"/>
<html:hidden name="form" property="fila"/>
<html:hidden name="form" property="inTratamentoUsuario"/>

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
        <td style="padding-left:4px;">
            <html:textarea name="form"
                           property="comentario"
                           rows="3"
                           style="width:280px;height:90px;"
                           onkeyup="checaTextarea(this, 500);" />
        </td>
    </tr>
    <tr>
        <td colspan="4" valign="bottom" align="right" width="100%">
            <input type="button"
               class="input_small"
               value="Gravar"
               onmouseup="submitAplicar('listaVoltarGravar')" />
        </td>
    </tr>
</table>