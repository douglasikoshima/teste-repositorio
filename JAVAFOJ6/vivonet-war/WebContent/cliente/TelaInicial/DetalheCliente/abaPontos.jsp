<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.parent.oculta_div();
            -->
        </SCRIPT>
        <script>
            <!--
                function atualizarLinhas(posicao){
                    document.forms[0].conta.value = posicao;
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            // -->
        </script>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
    <acesso:controlHiddenItem nomeIdentificador="cli_apo_verpagina">
        <form name="fPontuacao" id="fPontuacao">
            <input type="hidden" name="conta"/>
            <table width="767" height="234" border="0" cellpadding="0" bgcolor="#E3ECF4" cellspacing="1">
                <tr>
                    <td colspan="3">
                        Data de Vigência de Contrato:&nbsp;<b><bean:write name="dtVigenciaP"/></b>
                    </td>
                </tr>
                <tr>
                    <td valign="top" width="361">
                        <vivo:tbTable selectable="false" layoutWidth="361" layoutHeight="100" tableWidth="361" styleId="tableTitle1" sortable="false">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="40%" type="string">Conta</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="55%" type="string">Saldo</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="conta" name="piPontuacao" indexId="cnt">
                                        <vivo:tbRow key="linha1">
                                            <vivo:tbRowColumn><input type="radio" name="atualizalinhas" class="radio" onselect='<%= "atualizarLinhas("+ cnt+")" %>'/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="conta" property="nrConta"/></vivo:tbRowColumn>
                                            <vivo:tbRowColumn><bean:write name="conta" property="saldo"/></vivo:tbRowColumn>
                                        </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                        <script language="Javascript">
                            if(document.forms[0].atualizalinhas.length){
                                document.forms[0].atualizalinhas[0].checked = true;
                            }else{
                                document.forms[0].atualizalinhas.checked = true;
                            }
                        </script>
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                        <table width="378" height="20">
                            <tr>
                                <td class="tbl_bgGray" align="center">Selecione a Conta desejada para visualizaç&atilde;o das linhas</td>
                            </tr>
                        </table>
                    </td>
                    <td width="45"></td>
                    <td valign="top" width="361">
                        <vivo:tbTable  selectable="true" layoutWidth="361" layoutHeight="180" tableWidth="361" styleId="tableTitle2" sortable="false">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left" width="100%" type="number">Número Linha</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="linha" name="piLinPontos">
                                    <vivo:tbRow key="linha1">
                                        <vivo:tbRowColumn>(<bean:write name="linha" property="nrCodArea"/>)&nbsp;<bean:write name="linha" property="nrLinha"/></vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </td>
                </tr>
            </table>
        </form>
        </acesso:controlHiddenItem>
    </body>
</html>