<!--
Módulo.....: Gestão de Processos
Caso de Uso: Detalhe do processo
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/25 18:27:57 $
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<acesso:controlHiddenItem nomeIdentificador="wor_ap_verpagina">
<form action="processoAction.do" method="post">
    <table width="100%">
        <tr valign="top">
            <td width="20%">Data:</td>
            <td><netui:textBox dataSource="{}"/></td>
        </tr>
        <tr valign="top">
            <td>Hora:</td>
            <td><netui:textBox dataSource="{}"/></td>
        </tr>
        <tr valign="top">
            <td colspan="2">Observações:</td>
        </tr>
        <tr valign="top">
            <td colspan="2"><netui:textArea rows="5" dataSource="{}" style="width=290"/></td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td width="50%" align="right">
            <acesso:controlHiddenItem nomeIdentificador="wor_ap_agendar">
                <vivo:botao id="btAgendar" width="60px" height="10px" value="Agendar" styleClass="btTemplate" onclick="window.close();"/>
            </acesso:controlHiddenItem>
            </td>
            <td width="50%" align="left">
            <acesso:controlHiddenItem nomeIdentificador="wor_hist_fechar">
                <vivo:botao id="btFechar" width="60px" height="10px" value="Fechar" styleClass="btTemplate" onclick="window.close();"/>
            </acesso:controlHiddenItem>
            </td>
        </tr>
    </table>
</form>
   
</acesso:controlHiddenItem>
