<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="detalhesSaldoForm"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script>
        function mostraSaldo() {
            top.frameApp.mostrar_div();
            document.forms[0].action="loadPrePago.do";
            document.forms[0].method = "POST";
            document.forms[0].submit();
        }
    
        function limparDados(){
            document.getElementById('dvDados').style.display = '';
            document.getElementById('dvErro').style.display = 'none';
            cdSeguranca.innerHTML = "";
            preSaldo.innerHTML = "";
            preValid.innerHTML = "";
            preStatus.innerHTML = "";
        }
    </script>
    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        parent.parent.oculta_div();    
    -->
    </SCRIPT>
</head>
<body>
<acesso:controlHiddenItem nomeIdentificador="cli_dpp_verpagina">
<html:form action="/cliente/TelaInicial/loadFaturaIni.do">
<table width="345" height="75" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="63" valign="top">
            <acesso:controlHiddenItem nomeIdentificador="cli_dpp_abrirlupapp">
            <img src="/FrontOfficeWeb/resources/images/ti_lupa_prepago.jpg" width="63" height="75" onclick="top.frameApp.abraLupa('Detalhes de Pré-Pago','DetalhePrePago/lupaPrePago.jsp');" onmouseover="this.style.cursor='hand'" onmouseout="this.style.cursor=''">
            </acesso:controlHiddenItem>
            </td>
        <td width="3"></td>
        <td width="279" valign="top">
            <table width="279" height="75" border="0" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/ti_bg_prepago.gif">
                <tr>
                    <td height="2" colspan="4" valign="top"></td>
                    </tr>
                <tr>
                    <td height="15" colspan="2" valign="top" style="text-indent:5px;"><b>C&oacute;digo de Seguran&ccedil;a:</b><span id="cdSeguranca">&nbsp;<bean:write name="form" property="detalhesSaldoVO.cdSeguranca" /></span></td>
                    <td width="58" valign="top"></td>
                    <td width="20">
                    <acesso:controlHiddenItem nomeIdentificador="cli_dpp_mostrarsaldo">
                    <img src="/FrontOfficeWeb/resources/images/ti_bt_verfatura.gif" width="15" height="13" style="cursor: hand;" onclick="mostraSaldo()">
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" height="2"></td>
                </tr>
                    <tr>
                        <td colspan="5">
                            <div id="dvDados">
                                <table width="100%" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td width="140" height="19" style="text-indent:5px;"><b>Saldo Total:</b></td>
                                        <td><span id="preSaldo"><bean:write name="form" property="balanco" /></span></td>
                                    </tr>
                                    <tr>
                                        <td height="19" style="text-indent:5px;"><b></b></td>
                                        <td align="left"><span id="preValid"></span></td>
                                    </tr>
                                    <tr>
                                        <td height="18" style="text-indent:5px;"><b></b></td>
                                        <td><span id="preStatus"></span></td>
                                    </tr>
                                </table>
                            </div>
                            <div style="display:none" id="dvErro">
                                <logic:notEmpty name="error">
                                    <bean:write name="error"/>
                                </logic:notEmpty>
                            </div>
                        </td>
                    </tr>
                <logic:notEmpty name="error">
                    <script>
                        document.getElementById('dvDados').style.display = 'none';
                        document.getElementById('dvErro').style.display = '';
                    </script>
                </logic:notEmpty>
            </table>
        </td>
    </tr>
</table>

<script>
    top.frameApp.oculta_div();
</script>

</html:form>
</acesso:controlHiddenItem>
</body>