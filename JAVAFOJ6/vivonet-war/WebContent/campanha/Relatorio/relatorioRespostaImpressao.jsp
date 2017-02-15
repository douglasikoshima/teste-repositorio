<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
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
<%@ taglib uri="/WEB-INF/fidelizacao.tld" prefix="fid"%>

<bean:define id="relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relaRespostaForm.relatorioRespostasVO"/>
<bean:define id="numerico" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relaRespostaForm.relatorioRespostasVO.numericoArray"/>
<bean:define id="relHeader" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="headerVO"/>

<html>
<head>
    <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <SCRIPT FOR=window EVENT=onload>
        window.print();
    </script>
</head>
<body leftmargin="0" bottommargin="0" rightmargin="0" topmargin="0">
<table width="600" align="left" cellpadding="5" cellspacing="0">
    <tr>
      <td>
        <table width="100%" cellpadding="2" cellspacing="2" border="1">
          <tr bordercolor="#FFFFFF">
            <td rowspan="7"><img src="/FrontOfficeWeb/resources/images/logo_relatorio.jpg"></td>
            <td colspan="4" bgcolor="#00569f" align="center"><b><font color="#FFFFFF">Relatório
            Percentual de Respostas</font></b></td>
          </tr>
            <tr>
                <td width="100" bordercolor="#FFFFFF" bgcolor="#ededed"><b>Data de Emissão:</b></td>
                <td width="140" bordercolor="#adadad"><bean:write name="relHeader" property="dtEmissao" /></td>
                <td width="100" bordercolor="#FFFFFF" bgcolor="#ededed"><b>Operador:</b></td>
                <td width="170" bordercolor="#adadad"><bean:write name="relHeader" property="sgUsuario" /></td>
            </tr>
            <tr>
                <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Campanha:</b></td>
                <td bordercolor="#adadad"><bean:write name="relHeader" property="sgCampanha" /></td>
                <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Sub Campanha:</b></td>
                <td bordercolor="#adadad"><bean:write name="relHeader" property="nmSubCampanha" /></td>
            </tr>
            <tr>
                <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Versão:</b></td>
                <td bordercolor="#adadad"><bean:write name="relHeader" property="sqVersao" /></td>
                <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Canal:</b></td>
                <td bordercolor="#adadad"><bean:write name="relHeader" property="nmCanal" /></td>
            </tr>
            <tr>
                <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Período:</b></td>
                <td bordercolor="#adadad"> de <bean:write name="relHeader" property="filtroDtInicio" />
                      a <bean:write name="relHeader" property="filtroDtFim" />
                </td>
                <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Área de Registro:</b></td>
                <td bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="areaRegistro" /></td>
            </tr>
            <tr>
                <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Grupo:</b></td>
                <td bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="nmGrupo" /></td>
                <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Distinção:</b></td>
                <td bordercolor="#adadad"><bean:write name="relHeader" property="inDistincao" /></td>
            </tr>
            <tr>
                <td bordercolor="#FFFFFF" bgcolor="#ededed"><b>Regional:</b></td>
                <td colspan="4" bordercolor="#adadad">&nbsp;<bean:write name="relHeader" property="nmRegional" /></td>
            </tr>
        </table>

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <div class="holderTabela">

            <fid:table>
                <fid:tr bgColor="#545454">
                    <fid:headerTd>Pergunta</fid:headerTd>
                    <fid:headerTd>Resposta</fid:headerTd>
                    <fid:headerTd>Nro de Atendimentos</fid:headerTd>
                    <fid:headerTd>Percentagem</fid:headerTd>
                </fid:tr>
                <logic:iterate id="linha" name="numerico">
                    <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                        <fid:dataTd><bean:write name="linha" property="dsPergunta"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="dsResposta"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="nroAtendimento"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="porcent"/></fid:dataTd>
                    </fid:tr>

                </logic:iterate>
           </fid:table>

            <table align="center">
                <tr>
                    <td width="100%" align="left">
                        <%
                            Integer Int;
                            try { Int = new Integer((String)request.getAttribute("length")); }
                            catch ( NumberFormatException nfe ) { Int = new Integer(ConstantesCRM.SZERO); }

                            int size = Int.intValue();
                            for( int i=0; i < size; i++ ) {
                                 %>
                                    <img src="<%=((String)request.getAttribute("graph"+i)) + "&Random=" + i%>">
                                 <%
                            }
                        %>
                      <br>
                      <b>* Aguarde até o gráfico ser apresentado.</b>
                    </td>
                </tr>
            </table>
        </div>
    </td>
  </tr>
 </table>
</body>
</html>