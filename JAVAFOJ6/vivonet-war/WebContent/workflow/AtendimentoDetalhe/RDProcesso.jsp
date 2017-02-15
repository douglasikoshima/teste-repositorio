<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<%--
<acesso:controlInitEnv/>
--%>
<link rel="STYLESHEET" type="text/css" href="../../resources/css/frontoffice.css">
<bean:define id="form" scope="request" name="rWFFormularioForm"/>
<bean:define id="formularioProcessoVO" name="form" property="formularioProcessoVO"/>
<bean:define id="formularioVO" name="formularioProcessoVO" property="formularioVO"/>
<%--
<acesso:controlHiddenItem nomeIdentificador="wor_rdabfp_verpagina">
--%>

<table border=0 height="75">
<%
    boolean primeiraVez = true;
%>
<logic:iterate id="Campos" name="formularioVO" property="formularioCampoVOArray" indexId="ctr">
 <bean:define id="TipoCampo" name="Campos" property="tipoCampoVO" />
 <bean:define id="MascaraCampo" name="Campos" property="tipoCampoVO.sgMascaraApresentacaoCampo" />
 <bean:define id="TamanhoCampo" name="Campos" property="tipoCampoVO.nrTamanho" /><%  
    if(ctr.intValue()<=1) {
        if(primeiraVez){ %>
            <tr><td valign="middle" align="center" width="366"><table border="0" width="100%">
        <% } else { %>
            <td valign="middle" align="center" width="366">
                <table border="0" width="100%">
        <% }
    } else {
        if(ctr.intValue()%2==0) { %>
            <tr><td valign="middle" align="center" width="366"><table border="0" width="100%">
        <% } else { %>
            <td valign="middle" align="center" width="366">
                <table border="0" width="100%">
        <% }
    }
%>
 <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="TEXT">
  <tr>
   <td width="150" style="font-size: 9px;"><bean:write name="Campos" property="nmCampo" /></td>
   <td>
    <logic:iterate id="formCampoValoresVO" name="Campos" property="formularioCampoValorVOArray" type="br.com.vivo.fo.admsistemas.vo.FormularioCampoValorVODocument.FormularioCampoValorVO" indexId="idxFormCpoVlr">
     <html:text name="formCampoValoresVO" property="valor" onchange="Formatar(this.value, this.form.name, this.name, '<%=MascaraCampo%>');" style="WIDTH:145;" readonly="true" title='<%=""+formCampoValoresVO.getValor()%>'/>
    </logic:iterate>
   </td>
  </tr>
 </logic:equal>
 <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="TEXTAREA">
  <tr>
   <td width="150" style="font-size: 9px;"><bean:write name="Campos" property="nmCampo"/></td>
   <td>
    <logic:iterate id="formCampoValoresVO" name="Campos" property="formularioCampoValorVOArray" indexId="idxFormCpoVlr">
     <html:textarea name="formCampoValoresVO" property="valor" rows="2" readonly="true" style="WIDTH:150;"/>
    </logic:iterate>
   </td>
  </tr>
 </logic:equal>
 <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="SELECT">
  <bean:define id="Valores" name="Campos" property="formularioCampoValorVOArray" type="br.com.vivo.fo.admsistemas.vo.FormularioCampoValorVODocument.FormularioCampoValorVO[]"/>
  <bean:define id="SelectSel" value="0"/>
  <tr>
   <td width="153" style="font-size: 9px;"><bean:write name="Campos" property="nmCampo"/></td>
   <td>
    <html:select name="form" property="formularioVO" disabled="true" style="WIDTH:145;" title='<%=Valores[0].getValor()%>'>
     <html:options collection="Valores" property="valor" labelProperty="valor"/>
    </html:select>
   </td>
  </tr>
 </logic:equal>
 <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="CHECKBOX">
  <tr>
   <td width="150" style="font-size: 9px;"><bean:write name="Campos" property="nmCampo" /></td>
   <td>
    <table border="0">
     <logic:iterate id="Valores" name="Campos" property="formularioCampoValorVOArray">
     <tr>
      <td>
       <bean:define id="CheckSel" value="0"/>
       <html:multibox name="form" property="formularioVO" styleClass="radio" disabled="true">
        <bean:write name="Valores" property="valor" format="###"/>
       </html:multibox>
      </td>
      <td><bean:write name="Valores" property="valor"/></td>
     </tr>
    </logic:iterate>
    </table>
   </td>
  </tr>
 </logic:equal>
 <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="RADIO">
  <tr>
   <td width="150" style="font-size: 9px;"><bean:write name="Campos" property="nmCampo"/></td>
   <td>
    <table border="0">
     <logic:iterate id="Valores" name="Campos" property="formularioCampoValorVOArray">
     <bean:define id="idFormularioCampoValor" name="Valores" property="valor"/>
     <tr>
      <td><html:radio name="Valores" property="valor" value='<%="" + idFormularioCampoValor + ""%>' styleClass="radio" disabled="true"/></td>
      <td><bean:write name="Valores" property="valor" /></td>
     </tr>
     </logic:iterate>
    </table>
   </td>
  </tr>
 </logic:equal>
<%  
    if(ctr.intValue()<=1){
        if(ctr.intValue()==1){%>
            </table></td></tr>
      <%}else{%>
            </table>
            </td>
      <%}
        primeiraVez=false;
    }else{
        if(ctr.intValue()%2==1){%>
            </table></td></tr>
      <%}else{%>
                </table>
            </td>
      <%}
    }%>
</logic:iterate>
    <tr>
        <td valign="middle" align="center" colspan="2">
            <table border="0" width="100%">
                <tr valign="top">
                    <td id="tdObservacao" align="left" width="140">Observação:</td>
                    <td id="tdAreaObservacao" align="left" width="520" colspan="2">
                        <textarea style="width:515px;height:55px;color:#006699;background-color:#ededed;" wrap=virtual rows="4" readonly="true"><bean:write name="formularioProcessoVO" property="observacao"/></textarea>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<a href="abaVoltar.do" id="abaVoltar"></a>
<%--
</acesso:controlHiddenItem>
--%>
<script>
    document.body.style.backgroundColor='#ededed';
    parent.showIfrS('FProc');
    abaVoltar.click();
</script>