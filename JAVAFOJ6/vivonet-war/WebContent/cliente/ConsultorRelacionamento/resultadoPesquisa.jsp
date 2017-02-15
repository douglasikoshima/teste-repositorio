<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="consultorRelacionamentoForm"
             type="cliente.ConsultorRelacionamento.ConsultorRelacionamentoController.ConsultorRelacionamentoForm" />

<logic:equal name="Form" property="tpOperacao" value="pesquisarConsultorVO">
    <html:select onchange="getPesquisa('pesquisarClienteVOPorIDConsultor')" name="Form" property="idConsultor" styleId="idConsultor" styleClass="select3">
        <% if (Form.getConsultoresDisponiveis().getIDValorRelacionamentoVOArray().length == 0) { %>
        <html:option value="">-- Nenhum consultor foi encontrado --</html:option>
        <% } else {%>
        <html:option value="">-- Selecione --</html:option>
        <% } %>
        <logic:iterate id="iterConsultores" name="Form" property="consultoresDisponiveis.IDValorRelacionamentoVOArray">
            <option value="<bean:write name="iterConsultores" property="id" />"><bean:write name="iterConsultores" property="valor" /></option>
        </logic:iterate>
    </html:select>
</logic:equal>

<logic:equal name="Form" property="tpOperacao" value="pesquisarClienteVO">
    <html:select name="Form" property="listaContasDisponiveis" styleId="listaContasDisponiveis" multiple="true" styleClass="select2" size="8" onchange="getPesquisa('pesquisarConsultorVOPorIDCliente');"  onmouseover="ativarToolTip(this,1);">
        <logic:iterate id="iterContas" name="Form" property="contasDisponiveis.IDValorRelacionamentoVOArray">
            <option onmouseup="alert('<bean:write name="iterContas" property="valor" />&nbsp;CNPJ:<bean:write name="iterContas" property="nrDocumentoFmt" />');"  value="<bean:write name="iterContas" property="id" />"><bean:write name="iterContas" property="valor" />&nbsp;CNPJ:<bean:write name="iterContas" property="nrDocumentoFmt" /></option>
        </logic:iterate>
    </html:select>
</logic:equal>

<logic:equal name="Form" property="tpOperacao" value="pesquisarClienteVOPorIDConsultor">
    <html:select name="Form" property="listaContasSelecionadas" styleId="listaContasSelecionadas" multiple="true" styleClass="select1" size="5" onchange="loadTipoRelacionamento(this);" onmouseover="ativarToolTip(this, 1);">
        <logic:iterate id="iterContas" name="Form" property="contasSelecionadas.IDValorRelacionamentoVOArray">
            <option value="<bean:write name="iterContas" property="id" />" id="<bean:write name="iterContas" property="idRelacionamento" />"><bean:write name="iterContas" property="valor" />&nbsp;CNPJ:<bean:write name="iterContas" property="nrDocumentoFmt" /></option>
        </logic:iterate>
    </html:select>
</logic:equal>


<logic:equal name="Form" property="tpOperacao" value="pesquisarConsultorVOPorIDCliente">
    <table border="0" width="100%" cellpadding="0" cellspacing="0">
     <tr>
         <td width="320" valign="top" style="padding-left:10px;">
         <logic:notEqual name="Form" property="idConsultorRelacionamento1" value="">
            <html:select name="Form" 
                         property="listaConsultoresRelacionamento1" 
                         styleId="listaConsultoresRelacionamento1" 
                         styleClass="selectOne" 
                         multiple="true" 
                         size="1"
                         onchange="loadTipoRelacionamento(this);">
                    <option value="<bean:write name="Form" property="idConsultorRelacionamento1" />" id="R"><bean:write name="Form" property="nomeConsultorRelacionamento1" /></option>
            </html:select>
         </logic:notEqual>
         <logic:equal name="Form" property="idConsultorRelacionamento1" value="">
            <html:select name="Form"
                         property="listaConsultoresRelacionamento1"
                         styleId="listaConsultoresRelacionamento1"
                         multiple="true"
                         styleClass="selectOne"
                         size="1"
                         onchange="loadTipoRelacionamento(this);">
                    <option value=""></option>
            </html:select>
         </logic:equal>
         <td align="center" valign="middle">&nbsp;</td>
         <td width="320" valign="top" style="padding-left:10px;">
            <logic:notEqual name="Form" property="idConsultorRelacionamento2" value="">
                <html:select name="Form" 
                             property="listaConsultoresRelacionamento2" 
                             styleId="listaConsultoresRelacionamento2" 
                             styleClass="selectOne" 
                             multiple="true" 
                             size="1"
                             onchange="loadTipoRelacionamento(this);">
                    <option value="<bean:write name="Form" property="idConsultorRelacionamento2" />" id="R2"><bean:write name="Form" property="nomeConsultorRelacionamento2" /></option>
                </html:select>
             </logic:notEqual>
             <logic:equal name="Form" property="idConsultorRelacionamento2" value="">
                <html:select name="Form" 
                             property="listaConsultoresRelacionamento2" 
                             styleId="listaConsultoresRelacionamento2" 
                             multiple="true" 
                             styleClass="selectOne"
                             size="1"
                             onchange="loadTipoRelacionamento(this);">
                    <option value=""></option>
                </html:select>
             </logic:equal>
         </td>
     </tr>
     </table> 
</logic:equal>


