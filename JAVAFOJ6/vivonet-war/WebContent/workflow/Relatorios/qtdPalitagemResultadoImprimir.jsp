<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ColunasRelatorio"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ValoresRelatorio"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ValoresRelatorio.ValorColuna"%>
<%@ page import="workflow.Relatorios.RelatoriosController.RelatorioForm"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<bean:define id="stNiveis" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="stNiveisPalitagem"/>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", -1); //evita o caching no servidor proxy

String dsTituloRelatorio = ((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getDsTituloRelatorio();
String detalheScript = ((RelatorioForm)relatorioForm).getDetalheScript();
%>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<table cellpadding="5" cellspacing="1" border="0" width="650" bordercolor="black">
<tr bgcolor="#CCCCCC">
    <td colspan="4">
        <b><%= dsTituloRelatorio %></b>
    </td>
</tr>
<tr bgcolor="#CCCCCC">
    <td width="110" align="right"><b>Grupo Selecionado:</b></td>
    <td width="150"><bean:write name="relatorioForm" property="dsGrupo"/></td>
    <td align="center"><b>
    <logic:empty name="stNiveis">
        <bean:write name="relatorioForm" property="nivelSel"/>
    </logic:empty>
    <logic:notEmpty name="stNiveis">
        <logic:iterate id="niveis" name="stNiveis" type="java.lang.String" indexId="idxNiveis"><logic:notEqual name="idxNiveis" value="0">/</logic:notEqual><bean:write name="niveis"/></logic:iterate>
    </logic:notEmpty>    
    </b></td>
    <td align="right" width="260">
    </td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="1" bordercolor="black" width="650">
    <tr bgcolor="#CCCCCC">
        <td align="center">
            <b>Horários</b>
        </td>
        <td align="center">
            <b>Detalhes</b>
        </td>
    </tr>
    <logic:iterate id="horarios" name="relatorioForm" property="htHorariosRelatorios" indexId="idxHorarios">        
    <tr>
        <td>
            <center><font size="3"><b><bean:write name="horarios" property="key"/></b></font></center>
        </td>
        <td>
            <table cellpadding="5" cellspacing="1" border="0" bordercolor="black" width="100%">
                <tr bgcolor="#CCCCCC">
                <logic:iterate offset="1" id="colunasVO" name="relatorioForm" property="wFRelatorioDinamicoVO.colunasRelatorioArray" indexId="idx">
                    <td align="center" width="25%">
                        <b><bean:write name="colunasVO" property="dsColuna"/></b>
                    </td>
                </logic:iterate>
                </tr>
                <logic:iterate id="valoresRelatorioVO" name="horarios" property="value" indexId="idx2">
                    <%
                    ValorColuna[] valorColuna = ((ValoresRelatorio)valoresRelatorioVO).getValorColunaArray();
                    String inProximoNivel = ((ValoresRelatorio)valoresRelatorioVO).getInProximoNivel();
                    int porcentagem = ((RelatorioForm)relatorioForm).getAlarme();
                    double vlHoje  = Double.parseDouble( valorColuna[2].getValor() );
                    double vlData1 = Double.parseDouble( valorColuna[3].getValor() );
                    double vlData2 = Double.parseDouble( valorColuna[4].getValor() );
                    double p = (vlHoje/(vlData1+vlData2) - 1) * -100;
                    String fgColor = "darkblue";
                    if (porcentagem < p) {
                        fgColor = "darkred";
                    }        
                    %>
                    <tr>
                    <logic:iterate id="valoresColunasVO" offset="1" name="valoresRelatorioVO" property="valorColunaArray" indexId="idx3">
                        <td>
                            <center>
                            <logic:equal name="idx3" value="2">
                                <font color="<%=fgColor%>">
                            </logic:equal>
                            <bean:write name="valoresColunasVO" property="valor"/>
                        </td>
                    </logic:iterate>
                    </tr>
                </logic:iterate>
            </table>
        </td>
    </tr>
    </logic:iterate>
</table>
<script>window.print();</script>