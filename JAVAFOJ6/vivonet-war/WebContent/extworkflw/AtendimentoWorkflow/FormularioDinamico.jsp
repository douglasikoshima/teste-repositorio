<!--
Módulo.....: Workflow
Caso de Uso: Registrar Contato (Atendimento)
-->
<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.TipoCampoVODocument.TipoCampoVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>

<bean:define id="Form"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm" />
<bean:define id="FormularioVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.arvoreEncerramentoVO.formularioVO" type="br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO" />
<bean:define id="GrupoCamposVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.admGrupoCamposVO"                  type="br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposVODocument.AdmGrupoCamposVO[]"/>

<%
    int qtdeCampos = 0;
    int contadorDadosAparelho = 0;
    boolean flDadosAparelho = false;
%>

<!-- Montagem do Formulário Dinâmico -->
<logic:iterate name="FormularioVO" property="formularioCampoVOArray" id="Campos" indexId="ctr" type="br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO">
    <bean:define id="TipoCampo" name="Campos" property="tipoCampoVO" />
    <bean:define id="MascaraCampo" name="Campos" property="tipoCampoVO.sgMascaraApresentacaoCampo" />
    <bean:define id="TamanhoCampo" name="Campos" property="tipoCampoVO.nrTamanho" />
    
        <%
            String tamanho = ((TipoCampoVO)TipoCampo).getNrTamanho();
            qtdeCampos = FormularioVO.getFormularioCampoVOArray().length;
            if (tamanho.equals(ConstantesCRM.SZERO)) {
                tamanho="10";
            }
        %>
    <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="TEXT">
        <tr>
            <td><bean:write name="Campos" property="nmCampo" /></td>
            <logic:equal name="Campos" property="inPesquisa" value="1">
                        <td id="tdValorCampo" align="left">
                            <html:text name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>'/>&nbsp;<img name="customerProfile" align="middle" src="<%=request.getContextPath()%>/resources/images/lupa_bg_transp.gif" onclick="mostraPesquisaFormulario(<bean:write name="Campos" property="idCampo" format="###" />, '<bean:write name="Campos" property="nmCampo" />', this);" style="cursor:pointer">
                        <td>
                    </logic:equal> 
                    <logic:notEqual name="Campos" property="inPesquisa" value="1">               
                    <td>
                <html:text name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>' size='<%= tamanho %>' onkeyup ='<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>' onchange='<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>' onblur='<%= "validaCampo(this, \'"+ MascaraCampo +"\');"%>'/><logic:equal name="TipoCampo" property="sgMascaraApresentacaoCampo" value="data"><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('<%= "valorCampo[" + ctr + "].valor" %>', '%d/%m/%Y');">
                </logic:equal>
            </td>
                    </logic:notEqual> 
        </tr>
    </logic:equal>

    <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="TEXTAREA">
        <tr>
            <td><bean:write name="Campos" property="nmCampo" /></td>
            <td>
                <html:textarea name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>' />
            </td>
        </tr>
    </logic:equal>

    <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="SELECT">
            <logic:equal name="Campos" property="inPesquisa" value="1">
                <tr>
                    <td align="left" width="180" style="font-size: 9px;"><bean:write name="Campos" property="nmCampo" /></td>
                        <td id="tdValorCampo" align="left">
                            <html:text readonly="true" name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>'/>&nbsp;<img name="customerProfile" src="<%=request.getContextPath()%>/resources/images/lupa_bg_transp.gif" onclick="mostraPesquisaFormulario(<bean:write name="Campos" property="idCampo" format="###" />, '<bean:write name="Campos" property="nmCampo" />',this);" style="cursor:pointer">
                        <td>
                </tr>
            </logic:equal>
            <logic:notEqual name="Campos" property="inPesquisa" value="1">
            <bean:define id="Valores" name="Campos" property="formularioCampoValorVOArray" />
<%
if((Campos.getFormularioCampoValorVOArray()!=null)&&(Campos.getFormularioCampoValorVOArray().length>0)){
%>
                <tr>
                    <td align="left" width="180" style="font-size: 9px;"><bean:write name="Campos" property="nmCampo" /></td>
                    <td align="left">
                        <html:select name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>' style="WIDTH:150px; font-size: 9px;">
                            <html:option value="">-- Selecione --</html:option>
                            <html:options collection="Valores" property="valor" labelProperty="valor" />
                        </html:select>
                    </td>
                </tr>
<%
    }
%>
            </logic:notEqual>
    </logic:equal>

    <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="CHECKBOX">
<%
if((Campos.getFormularioCampoValorVOArray()!=null)&&(Campos.getFormularioCampoValorVOArray().length>0)){
%>
        <tr>
            <td align="left" width="180" style="font-size: 9px;"><bean:write name="Campos" property="nmCampo" /></td>
            <td align="left">
                <table border="0">
                    <logic:iterate id="Valores" name="Campos" property="formularioCampoValorVOArray">
                        <tr>
                            <td>
                                <html:multibox  name="Form" property='<%= "valorCampo[" + ctr + "].valorArray" %>' styleClass="radio">
                                    <bean:write name="Valores" property="valor" format="###"/>
                                </html:multibox>
                            </td>
                            <td style="font-size: 9px;">
                                <bean:write name="Valores" property="valor" />
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </td>
        </tr>
<%
    }
%>
    </logic:equal>

    <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="RADIO">
<%
if((Campos.getFormularioCampoValorVOArray()!=null)&&(Campos.getFormularioCampoValorVOArray().length>0)){
%>
        <tr>
            <td align="left" width="180" style="font-size: 9px;"><bean:write name="Campos" property="nmCampo" /></td>
            <td align="left">
                <table border="0">
                    <logic:iterate id="Valores" name="Campos" property="formularioCampoValorVOArray">
                        <bean:define id="idFormularioCampoValor" name="Valores" property="valor" />
                        <tr>
                            <td>
                                <html:radio name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>' value='<%="" + idFormularioCampoValor + ""%>' styleClass="radio" />
                            </td>
                            <td>
                                <bean:write name="Valores" property="valor" />
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </td>
        </tr>
<%
    }
%>
    </logic:equal>
    
</logic:iterate>

<!-- Inicio da apresentação de grupos de campos e funcionalidades -->
<logic:iterate name="GrupoCamposVO" id="GrupoCampos" indexId="idxGrupoCampos" type="br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposVODocument.AdmGrupoCamposVO">
    
    <bean:define id="FormularioCampoVO" name="GrupoCampos" property="formularioCampoVOArray" type="br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO[]"/>

    <!-- Utilizado para Grupos de Campos Dependentes -->
    <logic:equal name="GrupoCampos" property="idTipoGrupo" value="2">

        <tr><td colspan="2" style="padding-top:10px;">
            <fieldset>
                <legend style="font-weight:bold;"><bean:write name="GrupoCampos" property="nmGrupoCampos" /></legend>
                <table style="font-size:9px">
                    <logic:iterate name="FormularioCampoVO" id="itemCampo" indexId="i" type="br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO">
                        <tr>
                            <td id="containerCamposDependentes<bean:write name="GrupoCampos" property="idGrupoCampos" />">

                                <table id="tableDependentes|<bean:write name="GrupoCampos" property="idGrupoCampos" />|<bean:write name="itemCampo" property="nrNivel" />">
                                    <tr>
                                        <td width="150" nowrap style="padding-right:10px;">
                                            <bean:write name="itemCampo" property="nmCampo" />
                                        </td>
                                        <td>
                                            <bean:define id="Valores" name="itemCampo" property="formularioCampoValorVOArray" />
                                            <select id="grupoCamposDependentes;<bean:write name="GrupoCampos" property="idGrupoCampos" />;<%=itemCampo.getIdCampo() %>;1" 
                                                    name="grupoCamposDependentes;<bean:write name="GrupoCampos" property="idGrupoCampos" />;1" 
                                                    onchange="getValoresProximoNivel(this)" 
                                                    class="grupos<bean:write name="GrupoCampos" property="idGrupoCampos" />"
                                                    style="width:150px">
                                                <option value="<%=itemCampo.getIdCampo() %>">-- Selecione --</option>
                                                <logic:iterate id="valoresCamposDependentes" name="Valores">
                                                <option value="<%=itemCampo.getIdCampo() %>|<bean:write name="valoresCamposDependentes" property="idFormularioCampoValor" />">
                                                    <bean:write name="valoresCamposDependentes" property="valor" />
                                                </option>
                                                </logic:iterate>
                                            </select>
                                        </td>
                                        <td width="100%"></td>
                                    </tr>
                                </table>

                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </fieldset>
        </td></tr>

    </logic:equal>

    <!-- Utilizado para Grupos de Campos Normais e Funcionalidades -->
    <logic:notEqual name="GrupoCampos" property="idTipoGrupo" value="2">

        <%  if ("DADOS DE APARELHOS".equalsIgnoreCase(GrupoCampos.getNmGrupoCampos())){
                flDadosAparelho = true;
            } else {
                flDadosAparelho = false;
            }
        %>

        <tr><td colspan="2" style="padding-top:10px;">
            <fieldset>
                <legend style="font-weight:bold;"><% if (flDadosAparelho){ %>Dados de Aparelhos<% } else { %><bean:write name="GrupoCampos" property="nmGrupoCampos" /><% } %></legend>
                <table style="font-size:9px">
    
                    <logic:iterate name="FormularioCampoVO" id="itemCampo" indexId="i" type="br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO">
                        <bean:define id="TipoCampo"    name="itemCampo" property="tipoCampoVO"/>
                        <bean:define id="MascaraCampo" name="itemCampo" property="tipoCampoVO.sgMascaraApresentacaoCampo" />
                        <bean:define id="TamanhoCampo" name="itemCampo" property="tipoCampoVO.nrTamanho" />

                        <% if (i.intValue()%2==0) { %>
                            <tr>
                        <% }
                        idxGrupoCampos = new Integer(i.intValue() + qtdeCampos);
                        %>

                            <td width="25%" style="font-size:9px"><bean:write name="itemCampo" property="nmCampo" /></td>
                            <td width="25%">
                            
                                <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="TEXT">

                                    <logic:equal name="itemCampo" property="inPesquisa" value="1">
                                            <html:text name="Form" property='<%= "valorCampo[" + qtdeCampos + "].valor" %>' style="width:125px;"/>&nbsp;
                                            <img name="customerProfile" align="middle" src="<%=request.getContextPath()%>/resources/images/lupa_bg_ffffff.gif" onclick="mostraPesquisaFormulario(<bean:write name="itemCampo" property="idCampo" format="###" />, '<bean:write name="itemCampo" property="nmCampo" />', this);" style="cursor:pointer">
                                    </logic:equal>

                                    <logic:notEqual name="itemCampo" property="inPesquisa" value="1">               

                                        <logic:equal name="TipoCampo" property="sgMascaraApresentacaoCampo" value="data">
                                            <html:text name="Form" property='<%= "valorCampo[" + qtdeCampos + "].valor" %>' maxlength='<%=(String)TamanhoCampo%>' onkeyup ='<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>' onchange='<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>' onblur='<%= "validaCampo(this, \'"+ MascaraCampo +"\');"%>' style="WIDTH:125;"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" align="middle" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('<%= "valorCampo[" + qtdeCampos + "].valor" %>', '%d/%m/%Y');">
                                        </logic:equal>

                                        <logic:notEqual name="TipoCampo" property="sgMascaraApresentacaoCampo" value="data">

                                            <% if (flDadosAparelho){
                                            String[] idCampos = new String[3];
                                            idCampos[0] = "dsMarcaAparelho";
                                            idCampos[1] = "dsModeloAparelho";
                                            idCampos[2] = "dsTecnologia";
                                            %>
                                            <input <%if(contadorDadosAparelho==2){%>readonly<%}%> type="text" name="valorCampo[<%=qtdeCampos%>].valor" id="<%=idCampos[contadorDadosAparelho]%>" value="" maxlength="<%=(String)TamanhoCampo%>" onkeyup="<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>" onchange="<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>" onblur="<%= "validaCampo(this, \'"+ MascaraCampo +"\');"%>" style="width:150px;">
                                            <% 
                                            contadorDadosAparelho++;
                                            } else { %>
                                            <html:text name="Form" property='<%= "valorCampo[" + qtdeCampos + "].valor" %>' maxlength='<%=(String)TamanhoCampo%>' onkeyup ='<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>' onchange='<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>' onblur='<%= "validaCampo(this, \'"+ MascaraCampo +"\');"%>' style="WIDTH:150;"/>
                                            <% } %>
                                        </logic:notEqual>

                                    </logic:notEqual>
                                
                                </logic:equal>
                                
                                <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="TEXTAREA">
                                    <html:textarea name="Form" property='<%= "valorCampo[" + qtdeCampos + "].valor" %>' style="width:148px;font-size:9px;color:#006699;" rows="3"/>
                                </logic:equal>
                                
                                <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="SELECT">

                                    <logic:equal name="itemCampo" property="inPesquisa" value="1">
                                        <html:text readonly="true" name="Form" property='<%= "valorCampo[" + qtdeCampos + "].valor" %>' style="width:125;"/>&nbsp;<img name="customerProfile" align="middle" src="<%=request.getContextPath()%>/resources/images/lupa_bg_ffffff.gif" onclick="mostraPesquisaFormulario(<bean:write name="itemCampo" property="idCampo" format="###" />, '<bean:write name="itemCampo" property="nmCampo" />',this);" style="cursor:pointer">
                                    </logic:equal>

                                    <logic:notEqual name="itemCampo" property="inPesquisa" value="1">

                                        <bean:define id="Valores" name="itemCampo" property="formularioCampoValorVOArray" />
                                        <%
                                            if ((itemCampo.getFormularioCampoValorVOArray()!=null) && (itemCampo.getFormularioCampoValorVOArray().length>0)){
                                                String focaTip = "focaTip(this.options[this.selectedIndex].text, 'td" + "numCampoTratados" + "', 'tdL" + qtdeCampos + "', this);"; focaTip = "";  %>

                                        <html:select name="Form" property='<%= "valorCampo[" + qtdeCampos + "].valor" %>' style="width:148px;font-size:9px;" >
                                            <html:option value="">-- Selecione --</html:option>
                                            <html:options collection="Valores" property="valor" labelProperty="valor" />
                                        </html:select>

                                        <% } %>

                                    </logic:notEqual>

                                </logic:equal>

                                <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="CHECKBOX">

                                    <% if ((itemCampo.getFormularioCampoValorVOArray()!=null) && (itemCampo.getFormularioCampoValorVOArray().length>0)) { %>
                                        <logic:iterate id="Valores" name="itemCampo" property="formularioCampoValorVOArray">
                                            <html:multibox name="Form" property='<%= "valorCampo[" + qtdeCampos + "].valorArray" %>' styleClass="radio">
                                                <bean:write name="Valores" property="valor" format="###"/>
                                            </html:multibox>
                                            <bean:write name="Valores" property="valor" />
                                        </logic:iterate>
                                    <% } %>

                                </logic:equal>

                                <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="RADIO">

                                    <% if ((itemCampo.getFormularioCampoValorVOArray()!=null) && (itemCampo.getFormularioCampoValorVOArray().length > 0)) { %>

                                        <logic:iterate id="Valores" name="itemCampo" property="formularioCampoValorVOArray">
                                            <bean:define id="idFormularioCampoValor" name="Valores" property="valor" />
                                            <html:radio name="Form" property='<%= "valorCampo[" + qtdeCampos + "].valor" %>' value='<%="" + idFormularioCampoValor + ""%>' styleClass="radio" />
                                            <bean:write name="Valores" property="valor" />
                                        </logic:iterate>
                                                
                                    <% } %>

                                </logic:equal>

                            </td>
                        
                        <% if ( i.intValue()%2!=0) { %>
                        </tr>
                        <% } %>

                        <% qtdeCampos++; %>

                    </logic:iterate>
    
                </table>
            </fieldset>
        </td></tr>

    </logic:notEqual>

</logic:iterate>

<% if ("ENDERECO".equals(FormularioVO.getInFuncionalidade())
    || "ENDERECOCOMPLETO".equals(FormularioVO.getInFuncionalidade())) { %>

<bean:define id="dadosEndereco" name="Form" property="enderecoVO" />

<tr><td colspan="2" style="padding-top:10px">

    <fieldset>
        <legend style="font-weight:bold;">Pesquisa de Endere&ccedil;o</legend>

            <table border="0" cellspacing="0" cellpadding="3">
                <tr>
                    <td>Tipo Logradouro:</td>
                    <td>
                        <html:text name="Form" property="enderecoVO.nmTipoLogradouro" maxlength="255" size="15" styleId="nmTipoLogradouro" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td>T&iacute;tulo do Logradouro:</td>
                    <td>
                        <html:text name="Form" property="enderecoVO.nmTituloLogradouro" maxlength="255" size="15" styleId="nmTituloLogradouro" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td>Logradouro:</td>
                    <td>
                        <html:text name="Form" property="enderecoVO.nmLogradouro" maxlength="255" style="width:200px;" styleId="nmLogradouro" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        N&uacute;mero: <span style="color:#ff0000">*</span>
                    </td>
                    <td>
                        <b></b><html:text name="Form" property="enderecoVO.nrEndereco" maxlength="10" style="width:35px;" styleId="nrEndereco"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Complemento:
                    </td>
                    <td>
                        <html:text name="Form" property="enderecoVO.dsEnderecoComplemento" maxlength="255" style="width:70px;" styleId="dsEnderecoComplemento"/>
                    </td>
                </tr>
                <tr>
                    <td>Bairro:</td>
                    <td colspan="2">
                        <html:text name="Form" property="enderecoVO.nmBairro" maxlength="255" size="35" styleId="nmBairro" readonly="true" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Munic&iacute;pio:
                        <html:text name="Form" style="width:129px;" property="enderecoVO.nmMunicipio" maxlength="255" size="35" styleId="nmMunicipio" readonly="true"/>
                    </td>
                    <td>
                        CEP:
                        <html:text name="Form" property="enderecoVO.nrCEP" maxlength="9" onkeyup="checaCEP(this)" size="8" styleId="nrCEP" readonly="true"/>
                        <img src="<%=request.getContextPath()%>/resources/images/lupa_bg_ffffff.gif" style="border:none;cursor:pointer" onclick="pesquisaEndereco()">
                        <script language="javascript">
                            checaCEP(document.getElementById("nrCEP"));
                        </script>
                    </td>
                </tr>
                <tr>
                    <td id="estado"><html:hidden name="Form" property="sgUF" styleId="sgUF" />Estado: <span id="spanEstado" style="font-weight:bold;"><bean:write name="Form" property="sgUF" /></span></td>
                    <td id="pais" colspan="2"><html:hidden name="Form" property="nmPais" styleId="nmPais" />País: <span id="spanPais" style="font-weight:bold;"><bean:write name="Form" property="nmPais" /></span></td>
                </tr>
            </table>
            
        </fieldset>

</td></tr>

<% } %>
<!-- Fim da apresentação de grupos de campos -->

<% if (flDadosAparelho){ %>
<script language="javascript">
    document.body.onload = function(){getDadosAparelhos()};
</script>
<% } %>

<!-- fim Formulario Dinamico -->