<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="atendimentoForm" />
<bean:define id="atdWFComumVO"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO" />
<bean:define id="motivosVO"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray" />

<html:hidden name="form" property="idAtendimento" title="idAtendimento"/>
<html:hidden name="form" property="idAtividade"/>
<html:hidden name="form" property="inCRI"/>
<html:hidden name="form" property="fila"/>
<html:hidden name="form" property="inTratamentoUsuario"/>

<table width="100%">
    <tr valign="top">
        <td width="60">Motivo:</td>
        <td align="left" style="padding-left:6px;">
            <html:select name="form"
                         property="motivoSel"
                         styleId="motivoSel"
                         title="motivoSel"
                         style="width:250px">
                <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/>
            </html:select>
        </td>
        <td>Comentário:</td>
        <td>
            <html:textarea name="form"
                           property="comentario"
                           styleId="comentario"
                           rows="3"
                           style="width:280px;height:90px;"
                           onkeyup="checaTextarea(this, 500);"/>
        </td>
    </tr>
    <tr>
        <td align="right" colspan="4">
            <input type="button"
                   class="input_small"
                   value="Gravar"
                   onmouseup="submitAplicar('listaVoltarGravar')" />
        </td>
    </tr>
</table>