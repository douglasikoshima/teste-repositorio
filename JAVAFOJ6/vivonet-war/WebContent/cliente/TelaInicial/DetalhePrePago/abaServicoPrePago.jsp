<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="historicoAtendimentoForm"/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">        
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script>
            function validaPesquisa() {
                if (document.getElementById("dtIni").value == "")
                    alert("A data inicial é obrigatoria!");
                else if (document.getElementById("dtFin").value == "")
                    alert("A data final é obrigatoria!");
                else {
                    document.forms[0].action="pesquisarHistoricoAtendimento.do";
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            }
        </script>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">    
	<acesso:controlHiddenItem nomeIdentificador="cli_asppre_verpagina">
        <html:form action="pesquisarHistoricoAtendimento.do">          
            <div class="tbl_bgGray">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td height="5"></td>
                    </tr>
                    <tr>                                
                        <td style="text-indent:8px;" width="50">Período</td>
                        <td colspan="3">&nbsp;de 
                            <html:text style="width:65px;" name="form" property="dtIni" styleId="dtIni" readonly="true"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtIni', '%d/%m/%Y');">
                                at&eacute;&nbsp;
                            <html:text style="width:65px;" name="form" property="dtFin" styleId="dtFin" readonly="true"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFin', '%d/%m/%Y');">
                        </td>
                        <td valign="middle" align="right">
						<acesso:controlHiddenItem nomeIdentificador="cli_asp_pesq">
                            <img style="border:none;" onClick="validaPesquisa(); return false" vspace="5" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/>
						</acesso:controlHiddenItem>
                        </td>                                       
                    </tr>                    
                </table>
            </div>                    
            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="755" layoutHeight="126" tableWidth="755" styleId="tableTitle" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="200" type="string">Descrição da Operação</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="90" type="string">Data Operação</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="150" type="string">Serviço</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="90" type="string">Prioridade</vivo:tbHeaderColumn>                                
                </vivo:tbHeader>            
                <vivo:tbRows>
                    <logic:empty name="error">
                    <logic:iterate id="item" name="form" property="historicoAtendimentoVO.historicoAtendimentoItemArray">                
                        <vivo:tbRow key="Linha">
                            <vivo:tbRowColumn><bean:write name="item" property="descricao"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="item" property="data"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="item" property="servico"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="item" property="inPrioridade"/></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
                    </logic:empty>
                    <logic:notEmpty name="error">
                        <vivo:tbRow key="Linha">
                            <td colspan="4"><bean:write name="error"/></td>
                        </vivo:tbRow>
                    </logic:notEmpty>
                </vivo:tbRows>                            
            </vivo:tbTable>
        </html:form>    
	</acesso:controlHiddenItem>
    </body>
</html>