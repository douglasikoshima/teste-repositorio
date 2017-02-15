<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO"%>
<%@ page import="br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<bean:define id="Form"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />
<bean:define id="AtendimentoVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO" />
<bean:define id="FormularioVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO.arvoreAtendimentoVO.formularioVO" type="br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO"/>
<bean:define id="GrupoCamposVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.admGrupoCamposVO" type="br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposVODocument.AdmGrupoCamposVO[]"/>

<acesso:controlHiddenItem nomeIdentificador="wor_fdin_verpagina">

<%
    int qtdeCampos = 0;
    int contadorDadosAparelho = 0;
    boolean flDadosAparelho = false;
    if((FormularioVO!=null)&&(FormularioVO.getFormularioCampoVOArray()!=null)&&(FormularioVO.getFormularioCampoVOArray().length>0)){
        boolean primeiraVez = true;
        boolean campoTratado = true;
        int numCampoTratados = 0;
        qtdeCampos = FormularioVO.getFormularioCampoVOArray().length;
%>    
        <script>
            //se possue formulário dinâmco aumenta o tamanho da tabela
            tamanhoTabelaFormulario = "700px";
            tamanhoTdObservacao = 175;
            tamanhoAreaObservacao = "220px";
        </script>
        <!-- Montagem do Formulário Dinâmico -->
        <logic:iterate name="FormularioVO" property="formularioCampoVOArray" id="Campos" indexId="ctr" type="br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO">
            <bean:define id="TipoCampo" name="Campos" property="tipoCampoVO"/>
            <bean:define id="MascaraCampo" name="Campos" property="tipoCampoVO.sgMascaraApresentacaoCampo" />
            <bean:define id="TamanhoCampo" name="Campos" property="tipoCampoVO.nrTamanho" />
            <%  
                if (campoTratado)
                {
                    if(numCampoTratados<=1){
                        if(primeiraVez){
                        %>
                        <tr><td id="td<%=numCampoTratados%>" align="center" width="366"><table border="0" width="100%" cellpadding="0" cellspacing="0">
                        <%
                        }
                        else
                        {%>
                        <td id="td<%=numCampoTratados%>" align="center" width="366">
                            <table border="0" width="100%" cellpadding="0" cellspacing="0">
                        <%
                        }
                    }
                    else{
                        if(numCampoTratados%2==0){
                        %>
                        <tr><td id="td<%=numCampoTratados%>" align="center" width="366"><table border="0" width="100%" cellpadding="0" cellspacing="0">
                        <%
                        }
                        else
                        {%>
                        <td id="td<%=numCampoTratados%>" align="center" width="366">
                            <table border="0" width="100%" cellpadding="0" cellspacing="0">
                        <%
                        }
                    }
                }
                else
                {
                    campoTratado = true;
                }
            %>
            <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="TEXT">
                <tr>
                    <td id="tdText<%=ctr%>" align="left" width="180" style="font-size: 9px;"><bean:write name="Campos" property="nmCampo"/></td>
                    <logic:equal name="Campos" property="inPesquisa" value="1">
                        <td id="tdValorCampo" align="left" width="150" style="border-style:none;">
                        <script>tdText<%=ctr%>.style.width="177";</script>
                            <html:text name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>' style="WIDTH:125;"/>&nbsp;<img name="customerProfile" align="middle" src="<%=request.getContextPath()%>/resources/images/lupa_bg_ffffff.gif" onclick="mostraPesquisaFormulario(<bean:write name="Campos" property="idCampo" format="###" />, '<bean:write name="Campos" property="nmCampo" />', this);" style="cursor:pointer">
                        </td>
                    </logic:equal> 
                    <logic:notEqual name="Campos" property="inPesquisa" value="1">               
                        <td align="left" width="150" style="border-style:none;">
                            <logic:equal name="TipoCampo" property="sgMascaraApresentacaoCampo" value="data">
                            <script>tdText<%=ctr%>.style.width="177";</script>
                            <html:text name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>' maxlength='<%=(String)TamanhoCampo%>' onkeyup ='<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>' onchange='<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>' onblur='<%= "validaCampo(this, \'"+ MascaraCampo +"\');"%>' style="WIDTH:125;"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" align="middle" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('<%= "valorCampo[" + ctr + "].valor" %>', '%d/%m/%Y');">
                            </logic:equal>
                            <logic:notEqual name="TipoCampo" property="sgMascaraApresentacaoCampo" value="data">
                            <html:text name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>' maxlength='<%=(String)TamanhoCampo%>' onkeyup ='<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>' onchange='<%= "Formatar(this.value, this.form.name, this.name, \'"+ MascaraCampo +"\');"%>' onblur='<%= "validaCampo(this, \'"+ MascaraCampo +"\');"%>' style="WIDTH:150;"/>
                            </logic:notEqual>
                        </td>
                    </logic:notEqual>
                </tr>
            </logic:equal>
        
            <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="TEXTAREA">
                <tr>
                    <td align="left" width="180" style="font-size: 9px;border-style:none;"><bean:write name="Campos" property="nmCampo" /></td>
                    <td align="left" width="148">
                        <html:textarea name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>' style="WIDTH:148;font-size:9px;color: #006699;"  rows="3"/>
                    </td>
                </tr>
            </logic:equal>
        
            <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="SELECT">
                    <logic:equal name="Campos" property="inPesquisa" value="1">
                        <tr>
                            <td align="left" width="177" style="font-size: 9px;border-style:none;"><bean:write name="Campos" property="nmCampo" /></td>
                                <td id="tdValorCampo" align="left" width="150">
                                    <html:text readonly="true" name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>' style="WIDTH:125;"/>&nbsp;<img name="customerProfile" align="middle" src="<%=request.getContextPath()%>/resources/images/lupa_bg_ffffff.gif" onclick="mostraPesquisaFormulario(<bean:write name="Campos" property="idCampo" format="###" />, '<bean:write name="Campos" property="nmCampo" />',this);" style="cursor:pointer">
                                </td>
                        </tr>
                    </logic:equal>
                    <logic:notEqual name="Campos" property="inPesquisa" value="1">
                    <bean:define id="Valores" name="Campos" property="formularioCampoValorVOArray" />
<%
    if((Campos.getFormularioCampoValorVOArray()!=null)&&(Campos.getFormularioCampoValorVOArray().length>0)){
        String focaTip = "focaTip(this.options[this.selectedIndex].text, 'td" + numCampoTratados + "', 'tdL" + ctr + "', this);"; 
%>
                        <tr>
                            <td align="left" width="180" style="font-size: 9px;border-style:none;"><bean:write name="Campos" property="nmCampo" /></td>
                            <td id="tdL<%=ctr%>" align="left" width="148">
                                <html:select name="Form" property='<%= "valorCampo[" + ctr + "].valor" %>' style="WIDTH:148px; font-size: 9px;" onfocus='<%= focaTip %>' onmouseover='<%= focaTip %>' onchange='<%= focaTip %>' onblur="HideTip();" onmouseout="HideTip();">
                                    <html:option value="">-- Selecione --</html:option>
                                    <html:options collection="Valores" property="valor" labelProperty="valor" />
                                </html:select>
                            </td>
                        </tr>
 <%
            }
            else
                campoTratado = false;

%>
                    </logic:notEqual>
            </logic:equal>
    
            <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="CHECKBOX">
<%
    if((Campos.getFormularioCampoValorVOArray()!=null)&&(Campos.getFormularioCampoValorVOArray().length>0)){
%>
                <tr>
                    <td align="left" width="180" style="font-size: 9px;border-style:none;"><bean:write name="Campos" property="nmCampo" /></td>
                    <td align="left" width="148">
                        <table border="0" cellpadding="0" cellspacing="0">
                            <logic:iterate id="Valores" name="Campos" property="formularioCampoValorVOArray">
                                <tr>
                                    <td align="left">
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
            else
                campoTratado = false;
%>
            </logic:equal>
    
            <logic:equal name="TipoCampo" property="sgLayoutApresentacaoCampo" value="RADIO">
<%
    if((Campos.getFormularioCampoValorVOArray()!=null)&&(Campos.getFormularioCampoValorVOArray().length>0)){
%>
                <tr>
                    <td align="left" width="180" style="font-size: 9px;border-style:none;"><bean:write name="Campos" property="nmCampo" /></td>
                    <td align="left" width="150">
                        <table border="0" cellpadding="0" cellspacing="0">
                            <logic:iterate id="Valores" name="Campos" property="formularioCampoValorVOArray">
                                <bean:define id="idFormularioCampoValor" name="Valores" property="valor" />
                                <tr>
                                    <td align="left">
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
            else
                campoTratado = false;
%>
            </logic:equal>
            <%  
                if (campoTratado)
                {
                    if(numCampoTratados<=1){
                        if(numCampoTratados==1){
                            %>
                            </table></td></tr>
                            <%
                        }
                        else{%>
                            </table>
                            </td>
                            <%
                            }
                            primeiraVez=false;
                    }
                    else{
                        if(numCampoTratados%2==1){
                            %>
                            </table></td></tr>
                            <%
                        }
                        else{%>
                                </table>
                            </td>
                            <%
                            }
                    }
                    numCampoTratados++;
                }
                else
                {
                    if (FormularioVO.getFormularioCampoVOArray().length - 1 == ctr.intValue())
                    {
                        if(numCampoTratados%2==1){
                            %>
                            </table></td></tr>
                            <%
                        }
                        else{%>
                                </table>
                            </td>
                            <%
                        }
                    }
                }
                
                %>
            
        </logic:iterate>
        <%
        }
        %>   

	
    <logic:iterate name="GrupoCamposVO" id="GrupoCampos" indexId="idxGrupoCampos" type="br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposVODocument.AdmGrupoCamposVO">
        <bean:define id="FormularioCampoVO" name="GrupoCampos" property="formularioCampoVOArray" type="br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO[]"/>

        <!-- Utilizado para Grupos de Campos Dependentes -->
        <logic:equal name="GrupoCampos" property="idTipoGrupo" value="2">

            <tr><td colspan="2" style="padding-top:10px;">
                <fieldset style="width:690px;">
                    <legend style="font-weight:bold;"><bean:write name="GrupoCampos" property="nmGrupoCampos" /></legend>
                    <table width="690" style="font-size:9px">
                        <logic:iterate name="FormularioCampoVO" id="itemCampo" indexId="i" type="br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO">
                            <tr>
                                <td id="containerCamposDependentes<bean:write name="GrupoCampos" property="idGrupoCampos" />">

                                    <table id="tableDependentes|<bean:write name="GrupoCampos" property="idGrupoCampos" />|<bean:write name="itemCampo" property="nrNivel" />">
                                        <tr>
                                            <td width="150" nowrap style="padding-right:10px;">
                                                <bean:write name="itemCampo" property="nmCampo" />
                                            </td>
                                            <td nowrap>
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
                <fieldset style="width:690px;">
                    <legend style="font-weight:bold;"><% if (flDadosAparelho){ %>Dados de Aparelhos<% } else { %><bean:write name="GrupoCampos" property="nmGrupoCampos" /><% } %></legend>
                    <table width="690" style="font-size:9px">
        
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
    
                                            <html:select name="Form" property='<%= "valorCampo[" + qtdeCampos + "].valor" %>' style="width:148px;font-size:9px;" onfocus='<%= focaTip %>' onmouseover='<%= focaTip %>' onchange='<%= focaTip %>' onblur="HideTip();" onmouseout="HideTip();">
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

    <% if ("ENDERECO".equals(FormularioVO.getInFuncionalidade()) || "ENDERECOCOMPLETO".equals(FormularioVO.getInFuncionalidade())) { %>

    <bean:define id="dadosEndereco" name="Form" property="enderecoVO" />

    <tr><td colspan="2" style="padding-top:10px">

        <fieldset style="width:690px;">
            <legend style="font-weight:bold;">Pesquisa de Endere&ccedil;o</legend>

                <table width="690" border="0" cellspacing="0" cellpadding="3">
                    <tr>
                        <td>Tipo Logradouro:</td>
                        <td>
                            <html:text name="Form" property="enderecoVO.nmTipoLogradouro" maxlength="255" size="15" styleId="nmTipoLogradouro" readonly="true"/>
                        </td>
                        <td>T&iacute;tulo do Logradouro:</td>
                        <td>
                            <html:text name="Form" property="enderecoVO.nmTituloLogradouro" maxlength="255" size="15" styleId="nmTituloLogradouro" readonly="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Logradouro:</td>
                        <td colspan="3">
                            <html:text name="Form" property="enderecoVO.nmLogradouro" maxlength="255" style="width:200px;" styleId="nmLogradouro" readonly="true"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            N&uacute;mero: <span style="color:#ff0000">*</span>
                        </td>
                        <td>
                            <b></b><html:text name="Form" property="enderecoVO.nrEndereco" maxlength="10" style="width:35px;" styleId="nrEndereco"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            Complemento:
                            <html:text name="Form" property="enderecoVO.dsEnderecoComplemento" maxlength="255" style="width:70px;" styleId="dsEnderecoComplemento"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Bairro:</td>
                        <td colspan="2">
                            <html:text name="Form" property="enderecoVO.nmBairro" maxlength="255" size="35" styleId="nmBairro" readonly="true" />
                        </td>
                        <td colspan="2">
                            Munic&iacute;pio:
                            <html:text name="Form" style="width:129px;" property="enderecoVO.nmMunicipio" maxlength="255" size="35" styleId="nmMunicipio" readonly="true"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;
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

    <% if (flDadosAparelho){ %>
    <script language="javascript">
        getDadosAparelhos();
    </script>
    <% } %>

</acesso:controlHiddenItem>