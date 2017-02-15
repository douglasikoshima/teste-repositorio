<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<bean:define id="formDadosPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa"/>
<script>
        function mostrarTelaImpressao(arqJSP,nome)
        {
            // default values
            var default_url = arqJSP + "?totalReg=<%=request.getParameter("totalReg")%>&nome="+nome;
    
            // define popup window attributes
            var width  = 750;
            var height = 590;
    
            var top = screen.availHeight/2 - height/2; // center
            var left = screen.availWidth/2 - width/2; // center
    
            var scrollbars = true;
            var statusbar  = false;
            var resizable  = true;
        
            // show a modal (blocking) popup window
            var settings = buildSettings(width, height, top, left, scrollbars, statusbar, resizable);
            window.showModalDialog(default_url, window, settings);
            
            //window.open(default_url);
        }
        </script>
        <script>
    function imprimir()
    {
        document.getElementById("lyrBotoes").style.display = "none";
        window.focus();
        window.print();        
        document.getElementById("lyrBotoes").style.display = "block";
        document.getElementById("imprimir").focus();
    }
    </script>

<table width="780" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td  class="tbl_bggray">                               
            <table width="100%" cellpadding="5" cellspacing="0" border="0">
                <tr>
                    <td>Período de:</td>
                    <td >
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td><b><bean:write name="formDadosPesquisa" property="dataInicio"/></b></td>
                                <td>&nbsp;até:</td>
                                <td>&nbsp;<b><bean:write name="formDadosPesquisa" property="dataFim"/></b>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td align="left" valign="top">Login do Usuário:</td>
                    <td align="left" valign="top"><b><bean:write name="formDadosPesquisa" property="operador"/></b></td>
                </tr>
                <tr>
                    <td>Segmentação:</td>
                    <td>
                        <b><bean:write name="formDadosPesquisa" property="dsClassificacao"/></b>
                    </td>
                    <td valign="top" align="left">Oferta:</td>
                    <td valign="top"  style="padding-left:2px"><b><bean:write name="formDadosPesquisa" property="dsOferta"/></b></td>
                </tr>
                <tr>
                    <td valign="top">Regional:</td>
                    <td valign="top">
                        <table>
                            <logic:iterate id="operadora" name="formDadosPesquisa" property="dsOperadora">
                                <tr>
                                    <td>
                                        <b><bean:write name="operadora"/></b>
                                    </td>
                                </tr>
                            </logic:iterate>
                        </table>
                    </td>
                    <td valign="top">Tipo Cliente:</td>
                    <td valign="top" style="padding-left:2px"><b><bean:write name="formDadosPesquisa" property="dsTipoCliente"/></b></td>
                </tr>
                <tr>
                    <td >Grupo:</td>
                    <td><b><bean:write name="formDadosPesquisa" property="dsGrupo"/></b></td>
                    <td >Foram selecionados </td>
                    <td><b><%=request.getParameter("totalReg")%></b> registros</td>
                </tr>
            </table>
        </td>
    </tr>            
</table>

