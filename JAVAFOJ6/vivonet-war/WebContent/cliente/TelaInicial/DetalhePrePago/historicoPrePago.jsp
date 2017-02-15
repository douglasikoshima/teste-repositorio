<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="historicoMovimentosForm"/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script>
            function validaPesquisa() {
                if (document.getElementById("dtIni").value == "")
                    alert("A data inicial é obrigatoria!");
                else if (document.getElementById("dtFin").value == "")
                    alert("A data final é obrigatoria!");
                else {
                    document.forms[0].action="pesquisarHistoricoMovimentos.do";
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            }
        </script>
    </head>
    <body style="margin-left:5px; margin-right:5px; margin-top:5px;">
	<acesso:controlHiddenItem nomeIdentificador="cli_hpp_verpagina">
        <form action="pesquisarHistoricoMovimentos.do" method="post" name="historicoMovimentosForm">    
            <div class="tbl_bgGray">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td height="5"></td>
                    </tr>
                    <tr>                                
                        <td style="text-indent:8px;" width="50">Período:</td>
                        <td colspan="3">&nbsp;de 
                            <html:text style="width:65px;" name="form" property="dtIni" styleId="dtIni" readonly="true"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtIni', '%d/%m/%Y');">
                                at&eacute;&nbsp;
                            <html:text style="width:65px;" name="form" property="dtFin" styleId="dtFin" readonly="true"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFin', '%d/%m/%Y');">
                        </td>
                        <td valign="middle" align="right">
						<acesso:controlHiddenItem nomeIdentificador="cli_hpp_pesq">
                            <img style="border:none;" onClick="validaPesquisa(); return false" vspace="5" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/>
						</acesso:controlHiddenItem>
                        </td>                                       
                    </tr>                    
                </table>
            </div>
            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="735" layoutHeight="360" tableWidth="735" styleId="tableTitle" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="247" type="string">Nome Comercial</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="68" type="string">Valor da Recarga</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="121" type="string">Data do Movimento</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="121" type="string">Data de Processamento do Movimento</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="176" type="string">Origem</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows>
                    <logic:empty name="error">
                        <logic:iterate id="itemHistorico" name="form" property="historicoMovimentosVO.historicoMovimentosItemArray">                
                            <vivo:tbRow key="Linha">
                                <vivo:tbRowColumn><bean:write name="itemHistorico" property="movimento"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="itemHistorico" property="recargaValor"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="itemHistorico" property="recargaData"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="itemHistorico" property="recargaDataProc"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="itemHistorico" property="origem"/></vivo:tbRowColumn>
                            </vivo:tbRow>
                        </logic:iterate>
                    </logic:empty>
                    <logic:notEmpty name="error">
                        <vivo:tbRow key="Linha">
                            <td colspan="5"><bean:write name="error"/></td>
                        </vivo:tbRow>
                    </logic:notEmpty>
                </vivo:tbRows>                            
            </vivo:tbTable>
        </form>
	</acesso:controlHiddenItem>
    </body>
</html>
