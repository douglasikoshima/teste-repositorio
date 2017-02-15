<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormFrameLupaLinha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm" type="cliente.TelaInicial.TelaInicialController.LupaLinhaForm"/>
<bean:define id="DadosLupaLinha"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm.formDadosLupaLinha"/>
<bean:define id="FormDetalheLinha"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm.formDetalheLinhaVO"/>
<% String divulga = FormFrameLupaLinha.getFormInDivulgaNumeroFiltro(); %>
<html>
<head>
    <script type="text/javascript">
        onload = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            parent.parent.oculta_div();
            document.body.style.backgroundColor = '#ededed';
            <% if (request.getAttribute(ConstantesCRM.NRPROTOCOLO) != null) { %>
            alert('Protocolo <%=request.getAttribute(ConstantesCRM.NRPROTOCOLO)%> gerado.');
            top.frameApp.updateProtocolo('<%=request.getAttribute(ConstantesCRM.NRPROTOCOLO)%>');
            top.frameApp.nrProtocoloScreenPop = '';
            <% } else if (request.getAttribute("erroAPI") != null) { %>
            alert('<%=request.getAttribute("erroAPI")%>');
            <% } %>
        }

        function atualizaDivulgaNumero() {
            document.forms[0].formDivulga.value=document.forms[0].divulga.value;
        }

        function salvarLinha() {
            if(document.forms[0].divulga.value != valor){
                document.forms[0].action="salvar.do";
                parent.parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }
        }
    </script>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
</head>
<body>
<acesso:controlHiddenItem nomeIdentificador="cli_lplif_verpagina">

    <html:form action="/cliente/TelaInicial/DetalheLinha.do" styleId="" target="_parent" method="post">
    <input id='idLinha' value='<%=FormFrameLupaLinha.getFormDadosLupaLinha().getIdLinha()%>' type='hidden' name='formIDLinhaFiltro'/>
    <!-- <%=request.getAttribute("erroInfoLog")!=null?request.getAttribute("erroInfoLog"):""%> -->

    <% String tpLinha = FormFrameLupaLinha.getFormIDTipoLinha();
    // Linhas pós-pagas
    if(ConstantesCRM.SONE.equals(tpLinha) || ConstantesCRM.SFOUR.equals(tpLinha) || ConstantesCRM.SFIVE.equals(tpLinha) || ConstantesCRM.SSEVEN.equals(tpLinha)){%>

        <table width="739" height="90" border="0" cellpadding="0" cellspacing="0" align="center">
            <tr><td height="7"></td></tr>
            <% // GSM
            if (ConstantesCRM.SFIVE.equals(tpLinha) || ConstantesCRM.SSEVEN.equals(tpLinha)) { %>
            <tr>
                <td width="150">&nbsp;<strong>Fabricante</strong></td>
                <td width="250" colspan="3"><bean:write name="DadosLupaLinha" property="marca"/></td>
            </tr>
            <% } %>
            <tr>
                <logic:equal value="FALSE" name="FormFrameLupaLinha" property="erro">
                    <td width="150">&nbsp;<strong>Aparelho</strong></td>
                </logic:equal>
                <logic:equal value="TRUE" name="FormFrameLupaLinha" property="erro">
                    <td width="150">&nbsp;<strong>Aparelho*</strong></td>
                </logic:equal>
                <td width="250"><bean:write name="DadosLupaLinha" property="dsAparelho"/></td>
                <td><strong>Usuário</strong></td>
                <td><bean:write name="DadosLupaLinha" property="usuarioLinhaVO.nmUsuario"/></td>
            </tr>
            <tr>
                <logic:equal value="FALSE" name="FormFrameLupaLinha" property="erro">
                    <td>&nbsp;<strong>Tecnologia</strong></td>
                </logic:equal>
                <logic:equal value="TRUE" name="FormFrameLupaLinha" property="erro">
                    <td>&nbsp;<strong>Tecnologia*</strong></td>
                </logic:equal>
                <td><bean:write name="DadosLupaLinha" property="dsTecnologia"/></td>
                <td><strong><bean:write name="DadosLupaLinha" property="usuarioLinhaVO.dsTipoDocumento"/></strong></td>
                <td><bean:write name="DadosLupaLinha" property="usuarioLinhaVO.nrDocumento"/></td>
            </tr>
            <tr>
                <logic:equal value="FALSE" name="FormFrameLupaLinha" property="erro">
                    <td>&nbsp;<strong>Contrato de Fidelização</strong></td>
                </logic:equal>
                <logic:equal value="TRUE" name="FormFrameLupaLinha" property="erro">
                    <td>&nbsp;<strong>Contrato de Fidelização*</strong></td>
                </logic:equal>
                <td><bean:write name="DadosLupaLinha" property="contratoFidelizacao"/></td>
                <td><strong><bean:write name="DadosLupaLinha" property="usuarioLinhaVO.dsTipoContato"/></strong></td>
                <td><bean:write name="DadosLupaLinha" property="usuarioLinhaVO.nrContato"/></td>
            </tr>
            <tr>
                <logic:equal value="FALSE" name="FormFrameLupaLinha" property="erro">
                    <td>&nbsp;<strong>Multa Contratual</strong></td>
                </logic:equal>
                <logic:equal value="TRUE" name="FormFrameLupaLinha" property="erro">
                    <td>&nbsp;<strong>Multa Contratual*</strong></td>
                </logic:equal>
                <td><bean:write name="DadosLupaLinha" property="dsMultaContrato" /></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>&nbsp;<strong>Tipo do Contrato</strong></td>
                <td><bean:write name="DadosLupaLinha" property="dsTipoContrato" /></td>
            </tr>
             <tr>
                <td>&nbsp;<strong>Término do Contrato</strong></td>
                <td><bean:write name="DadosLupaLinha" property="dtTerminoContrato" /></td>
            </tr>
        </table>
    <%
    }
    // Linhas Pré-pagas
    if (ConstantesCRM.STWO.equals(tpLinha) || ConstantesCRM.SSIX.equals(tpLinha)) { %>
        <% //CDMA
        if (ConstantesCRM.STWO.equals(tpLinha)) { %>
        <table width="739" height="90" border="0" cellpadding="0" cellspacing="0" align="center">
            <tr><td height="7"></td></tr>
            <tr>
                <td width="150">&nbsp;<strong>Descrição do modelo:</strong></td>
                <td width="150"><bean:write name="FormFrameLupaLinha" property="formDadosLupaLinha.descricao"/></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>&nbsp;<strong>Tecnologia:</strong></td>
                <td><bean:write name="FormFrameLupaLinha" property="formDadosLupaLinha.dsTecnologia"/></td>
                <td><strong>C&oacute;digo do Modelo:</strong><bean:write name="FormFrameLupaLinha" property="formDadosLupaLinha.modelo"/></td>
                <td><strong>Marca:</strong><bean:write name="FormFrameLupaLinha" property="formDadosLupaLinha.marca"/></td>
            </tr>
        </table>
        <% } %>
        <% // GSM
        if (ConstantesCRM.SSIX.equals(tpLinha)) { %>
        <table width="739" height="90" border="0" cellpadding="0" cellspacing="0" align="center">
            <tr><td height="7"></td></tr>
            <tr>
                <td width="150">&nbsp;<strong>Fabricante: </strong></td>
                <td width="150"><bean:write name="FormFrameLupaLinha" property="formDadosLupaLinha.marca"/></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td width="150">&nbsp;<strong>Aparelho:</strong></td>
                <td width="150"><bean:write name="FormFrameLupaLinha" property="formDadosLupaLinha.dsAparelho"/></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>&nbsp;<strong>Tecnologia:</strong></td>
                <td colspan="2"><bean:write name="FormFrameLupaLinha" property="formDadosLupaLinha.dsTecnologia"/></td>
            </tr>
        </table>
        <% } %>
    <%}%>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="20"></div>
    <table align="center" width="739" height="33" border="0" cellpadding="0" cellspacing="0" style="border:1px solid #adadad; background-color:#ffffff;">
        <tr>
            <td width="163" style="text-indent:16px;font-weight:bold;">Divulga&ccedil;&atilde;o de n&uacute;mero:</td>
            <td width="70">
                <html:hidden styleId="formDivulga" value="" name="FormFrameLupaLinha" property="formInDivulgaNumeroFiltro"/>
                <select name="divulga" onchange="atualizaDivulgaNumero()">
                    <%if(ConstantesCRM.SYES.equalsIgnoreCase(divulga)){%>
                        <option value="Sim" selected/>Sim</option>
                        <option value="Nao"/>Nao</option>
                        <script language="JavaScript">valor = "Sim"</script>
                    <%}else{%>
                        <option value="Sim"/>Sim</option>
                        <option value="Nao" selected/>Nao</option>
                        <script language="JavaScript">valor = "Nao"</script>
                    <%}%>
                </select>
            </td>
            <td width="80">
                <acesso:controlHiddenItem nomeIdentificador="cli_lplif_gravar">
                    <img onClick="salvarLinha(); return false" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" style="border:none;cursor:hand;"/>
                </acesso:controlHiddenItem>
            </td>
            <td align="right" style="padding-right:15px;">
                <strong>Data da &uacute;ltima atualiza&ccedil;&atilde;o:</strong>
                <bean:write name="DadosLupaLinha" property="dtUltimaAtualizacao"/>
            </td>
        </tr>
    </table>
    <logic:equal value="TRUE" name="FormFrameLupaLinha" property="erro">
    <table width="739" border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
            <td><i><strong>(*) Dados não recuperados pois Sistemas de LINHA temporariamente fora de serviço!</strong></i></td>
        </tr>
    </table>
    </logic:equal>
</html:form>
</acesso:controlHiddenItem>
</body>
</html>