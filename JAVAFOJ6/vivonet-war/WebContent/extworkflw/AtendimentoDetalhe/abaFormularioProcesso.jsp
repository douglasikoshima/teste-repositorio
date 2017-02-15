<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm"/>
<bean:define id="atdVO" name="form" property="atendimentoDetalheVO.atendimentoVO"/>
<bean:define id="formularioVO" name="form" property="atendimentoDetalheVO.atendimentoVO.arvoreAtendimentoVO.formularioVO"/>


<form action="processoAction.do" method="post">

    <table width="100%">
        <logic:iterate id="Campos" name="formularioVO" property="formularioCampoVOArray" indexId="ctr">
            <bean:define id="TipoCampo" name="Campos" property="tipoCampoVO" />
            <bean:define id="MascaraCampo" name="Campos" property="tipoCampoVO.sgMascaraApresentacaoCampo" />
            <bean:define id="TamanhoCampo" name="Campos" property="tipoCampoVO.nrTamanho" />
            <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="TEXT">
                <tr>
                    <td><bean:write name="Campos" property="nmCampo" /></td>
                    <td>
                        <logic:iterate id="formCampoValoresVO" name="Campos" property="formularioCampoValorVOArray" indexId="idxFormCpoVlr">
                            <html:text name="formCampoValoresVO" property="valor" style="width=300" onchange="Formatar(this.value, this.form.name, this.name, '<%=MascaraCampo%>');" readonly="true"/>
                        </logic:iterate>
                    </td>
                </tr>
            </logic:equal>
        
            <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="TEXTAREA">
                <tr>
                    <td><bean:write name="Campos" property="nmCampo"/></td>
                    <td>
                        <logic:iterate id="formCampoValoresVO" name="Campos" property="formularioCampoValorVOArray" indexId="idxFormCpoVlr">
                            <html:textarea name="formCampoValoresVO" property="valor" style="width=300" rows="4" readonly="true"/>
                        </logic:iterate>
                    </td>
                </tr>
            </logic:equal>
        
            <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="SELECT">
                <bean:define id="Valores" name="Campos" property="formularioCampoValorVOArray"/>
                <bean:define id="SelectSel" value="0"/>
                <tr>
                    <td><bean:write name="Campos" property="nmCampo"/></td>
                    
                    <td>
                        <html:select name="form" property="subEstadoSel" disabled="true">
                            <html:options collection="Valores" property="valor" labelProperty="valor"/>
                        </html:select>
                    </td>
                </tr>
            </logic:equal>
    
            <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="CHECKBOX">
                <tr>
                    <td><bean:write name="Campos" property="nmCampo" /></td>
                    <td>
                        <table border="0">
                            <logic:iterate id="Valores" name="Campos" property="formularioCampoValorVOArray">
                                <tr>
                                    <td>
                                        <bean:define id="CheckSel" value="0"/>
                                        <html:multibox name="form" property="subEstadoSel" styleClass="radio" disabled="true">
                                            <bean:write name="Valores" property="valor" format="###"/>
                                        </html:multibox>
                                    </td>
                                    <td>
                                        <bean:write name="Valores" property="valor"/>
                                    </td>
                                </tr>
                            </logic:iterate>
                        </table>
                    </td>
                </tr>
            </logic:equal>
    
            <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="RADIO">
                <tr>
                    <td><bean:write name="Campos" property="nmCampo"/></td>
                    <td>
                        <table border="0">
                            <logic:iterate id="Valores" name="Campos" property="formularioCampoValorVOArray">
                                <bean:define id="idFormularioCampoValor" name="Valores" property="valor"/>
                                <tr>
                                    <td>
                                        <html:radio name="Valores" property="valor" value='<%="" + idFormularioCampoValor + ""%>' styleClass="radio" disabled="true"/>
                                    </td>
                                    <td>
                                        <bean:write name="Valores" property="valor" />
                                    </td>
                                </tr>
                            </logic:iterate>
                        </table>
                    </td>
                </tr>
            </logic:equal>
            
        </logic:iterate>
    </table>

    <table width="745" height="45">
        <tr valign="top">
            <td width="77"><b>Observação</b></td>
            <td width="668" style="color:#006699;">
                <div style="overflow:auto;height:38px;width:657px;">
                    <bean:write name="atdVO" property="observacao"/>
                </div>
            </td>
        </tr>
    </table>
    
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
</form>
   

