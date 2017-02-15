<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="camposDinamicosForm" type="admsistemas.AdmFormularios.AdmFormulariosController.CamposDinamicosForm"/>


<logic:equal name="Form" property="tpOperacao" value="pesquisarEmpresaVO">
    <html:select name="Form" property="listaContasDisponiveis" styleId="listaContasDisponiveis" multiple="true" styleClass="select2" size="8" onmouseover="ativarToolTip(this,1);" style="width:300px;">
        <logic:iterate id="iterContas" name="Form" property="contasDisponiveis.IDValorClienteVOArray">
            <option value="<bean:write name="iterContas" property="id" />"><bean:write name="iterContas" property="valor" />&nbsp;CNPJ:<bean:write name="iterContas" property="nrDocumentoFmt" /></option>
        </logic:iterate>
    </html:select>
</logic:equal>

<logic:equal name="Form" property="tpOperacao" value="pesquisarClienteVOFuncionalidade">
    <html:select name="Form" property="listaContasSelecionadas" styleId="listaContasSelecionadas" multiple="true" styleClass="select2" size="4" style="width:300px;" onmouseover="ativarToolTip(this, 1);">
        <logic:iterate id="iterContas" name="Form" property="arrClientes">
            <option value="<bean:write name="iterContas" property="nrCNPJ" />"><bean:write name="iterContas" property="nome" /><logic:notEqual name="iterContas" property="nrDocumentoFormatado" value="">&nbsp;CNPJ:<bean:write name="iterContas" property="nrDocumentoFormatado" /></logic:notEqual></option>
        </logic:iterate>
    </html:select>

 <br>Clientes desassociados
 <html:select name="Form" property="listaContasDessassociadas" styleId="listaContasDessassociadas" multiple="true" styleClass="select2" size="4" style="width:300px;" onmouseover="ativarToolTip(this, 1);">
    <logic:iterate id="iterContasDessassociados" name="Form" property="arrClientesDessassociados">
        <option value="<bean:write name="iterContasDessassociados" property="nrCNPJ" />"><bean:write name="iterContasDessassociados" property="nome" /><logic:notEqual name="iterContasDessassociados" property="nrDocumentoFormatado" value="">&nbsp;CNPJ:<bean:write name="iterContasDessassociados" property="nrDocumentoFormatado" /></logic:notEqual></option>
    </logic:iterate>     
 </html:select>

</logic:equal>


    



