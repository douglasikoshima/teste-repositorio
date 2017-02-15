<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<bean:define id="relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relaAgendaAnivForm"/>
<bean:define id="relHeader" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="headerVO"/>
<html>
<head>
    <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <SCRIPT FOR=window EVENT=onload>
        //top.frameApp.oculta_div();
        window.print();
    </script>
</head>
<body leftmargin="0" bottommargin="0" rightmargin="0" topmargin="0">
<table width="600">
    <tr>
        <td><img src="/FrontOfficeWeb/resources/images/img_relatorio.gif"></td>
    </tr>
</table>
<table border="0" width="600" class="tbl_bgblue">
    <tr>
        <td><b>Relatório Agenda de Aniverssário</b></td>
    </tr>
</table>
<table width="600" cellpadding="0" cellspacing="0" border="0" >
<tr>
    <td>
            <table width="100%" class="tbl_bggray" cellpadding="5" border="0">
                <tr>
                    <td width="20%">Período:</td>
                    <td width="80%">de <b><bean:write name="relHeader" property="filtroDtInicio" /></b> a <b><bean:write name="relHeader" property="filtroDtFim" /></b></td>
                </tr>
                <tr>
                    <td colspan="2">Foram selecionados <b><bean:write name="relHeader" property="total" /></b> registros</td>
                </tr>
            </table>
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
            <div class="holderTabela">
              <table border="0" cellpadding="0" cellspacing="0" width="100%" >

                                <tr>
                  <td>
                    <div class="headerTabela">
                        <table  id="tableTitle_header" border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                                  <td class="normal" width="20%">Linha</td>
                                  <td class="normal" width="12%" >Data de Aniversário</td>
                                  <td class="normal" width="26%" >Aniversariante</td>
                                  <td class="normal" width="10%" >Operador</td>
                                  <td class="normal" width="16%" >Data de Atendimento</td>
                                  <td class="normal" width="16%" >Período do Dia</td>
                            </tr>
                          </table>
                        </div>
                      </td>
                    </tr>

                                <tr>
                  <td>
                  <div class="corpoTabela">
                      <table id="tableTitle_body" class="selecionavel" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" >

                        <logic:iterate id="linha" name="relatorio" property="linhaAniversario">
                            <tr class='rowTabelaZebradoOff' >
                                <td align="center" width="20%" >
                                    <bean:write name="linha" property="linha"/>
                                </td>
                                <td align="center" width="12%" >
                                    <bean:write name="linha" property="dataAniversario"/>
                                </td>
                                <td align="center" width="26%" >
                                    <bean:write name="linha" property="aniversariante"/>
                                </td>
                                <td align="center" width="10%" >
                                    <bean:write name="linha" property="operador"/>
                                </td>
                                <td align="center" width="16%" >
                                    <bean:write name="linha" property="dataAtendimento"/>
                                </td>
                                <td align="center" width="16%" >
                                    <bean:write name="linha" property="periodoDia"/>
                                </td>
                            </tr>
                        </logic:iterate>
                        </table>
                    </div>
                </td>
            </tr>
            </table>
        </div>
    </td>
</tr>
</table>
</body>
</html>