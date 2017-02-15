<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="relatorioForm"
             type="VOLTAV.relatorios.RelatorioQuadroAviso.RelatorioQuadroAvisoController.RelatorioForm"/>

<%
 int i=0;
%>

    <div id="divTableTitle">
        <table width="100%" cellspacing="0" cellpadding="0">
            <tr>
                <td align="center" width="141">Conta</td>
                <td align="center" width="101">Regional</td>
                <td align="center" width="134">Segmentação</td>
                <td align="center" width="144">Data Envio</td>
                <td align="center" width="102">Validade</td>
                <td align="center" width="102">Status</td>
                <td align="center" width="102"></td>
            </tr>
        </table>
    </div>
    <logic:iterate id="itLinha" name="Form" property="resultset.linhas.linhaArray">
    <%i++;%>
        <div id="vertical_container">
            <h1 class="accordion_toggle">
                <table width="100%" cellspacing="1" cellpadding="0" class="tableProtocolo" >
                    <tr>
                        <td width="141" class="numeroProtocolo" onclick="exibirMensagem('divMensagem<%=i%>');"><span><bean:write name="itLinha" property="valorArray[0]"/></span></td>
                        <td width="101" align="center" onclick="exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[1]"/></td>
                        <td width="134" align="center" onclick="exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[2]"/></td>
                        <td width="144" align="center" onclick="exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[3]"/></td>
                        <td width="102" align="center" onclick="exibirMensagem('divMensagem<%=i%>');"><bean:write name="itLinha" property="valorArray[4]"/></td>
                        <td width="102" align="center" onclick="exibirMensagem('divMensagem<%=i%>');">

                            <logic:empty name="itLinha" property="valorArray[6]">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_habilitar.gif" border="0" alt="Mensagem ativa">
                            </logic:empty>

                            <logic:notEmpty name="itLinha" property="valorArray[6]">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" border="0" alt="Mensagem excluida">
                            </logic:notEmpty>

                        </td>
                        <td width="102" align="center">

                            <logic:empty name="itLinha" property="valorArray[6]">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir mensagem" onclick="excluirMensagem('<bean:write name="itLinha" property="valorArray[7]"/>');">
                            </logic:empty>

                            <logic:notEmpty name="itLinha" property="valorArray[6]">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir_inv.gif" border="0" alt="Mensagem excluida">
                            </logic:notEmpty>



                        </td>
                    </tr>
                </table>
            </h1>
        </div>
        <div id="divMensagem<%=i%>" class="accordion_content" style="display:none">
            <div style="width:745px">
                <table width="745" border="0" class="tableProcesso" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <td valign="top"height="29" width="27"></td>
                            <td width="255" valign="top"><img src="<%=request.getContextPath()%>/resources/images/tableMensagem.gif" width="100" height="27" alt="Contato" /></td>
                        </tr>
                    </thead>
                    <tbody >

                        <tr>
                            <td height="35">
                                <img src="<%=request.getContextPath()%>/resources/images/iconProcesso.gif" style="padding-left:3px;cursor:pointer"/>
                            </td>
                            <td>
                                <span style="width:255px;text-transform:uppercase"><bean:write name="itLinha" property="valorArray[5]"/></span>
                            </td>
                            </tr>
                        <tr><td colspan="5" class="lineSeparator" valign="top"><img src="<%=request.getContextPath()%>/resources/images/spacer.gif" height="6" /></td></tr>
                    </tbody>
                </table>
            </div>
        </div>
    </logic:iterate>