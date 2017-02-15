<!--
Módulo.....: Gestão de Processos
Caso de Uso: Monitoramento Atendente
Versão.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:41 $
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="workflow.Monitoramento.MonitoramentoProcessosController.RelatorioEstadoTopVO"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
   
<acesso:controlInitEnv/>
<bean:define id="monitoramentoForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="monitoramentoForm"/>
<%
//Definições
int     sizeBar     = 0;    //Controle do size da barra de percentual
Integer Arraylength = null; //Controle de impressão
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<!--Atendente-->

<acesso:controlHiddenItem nomeIdentificador="wor_met_verpagina">
<vivo:quadro id="qdAtendente" height="355" width="775" label="&nbsp;Avaliação Geral por Estado em Grupo em Atendente" scroll="N">
    <vivo:tbTable resize="false" selectable="false" layoutWidth="750" layoutHeight="325" tableWidth="750" styleId="tbAtendentes" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="10%" type="string">Ordem</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Grupo</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Login</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="50%" type="string">Detalhe</vivo:tbHeaderColumn>
        </vivo:tbHeader>

        <vivo:tbRows scroll="false">
            <!--Analise de registros encontrados-->
            <logic:notEmpty name="monitoramentoForm" property="relatorioAtendenteVO">
                <!--Obtenção do vetor-->
                <bean:define name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="monitoramentoForm.relatorioAtendenteVO" id="aRelatorioAtendenteVO"/>

                <!--Impressao da estatistica-->
                <% int i=0; %>
                <logic:iterate name="aRelatorioAtendenteVO" id="relatorioAtendenteVO" indexId="rownum">
                    <logic:notEmpty name="relatorioAtendenteVO" property="login">
                    <vivo:tbRow key="linha1" zebrar="S">
                        <% i++; %>
                        <vivo:tbRowColumn><b><vivo:hint maxLength="25" spaces="S"><%=i%></vivo:hint></b></vivo:tbRowColumn>
                        <vivo:tbRowColumn><b><vivo:hint maxLength="25" spaces="S"><bean:write name="relatorioAtendenteVO" property="grupo"/></vivo:hint></b></vivo:tbRowColumn>
                        <vivo:tbRowColumn><b><bean:write name="relatorioAtendenteVO" property="login"/></b></vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <table id="tab01" cellpadding="5" cellspacing="0" width="100%">
                                <tr> <%-- cabecalho --%>
                                    <td class="holderTabela" align="center" width="15%">Ordem</td>
                                    <td class="holderTabela" align="center" width="35%">Estado</td>
                                    <td class="holderTabela" align="center" width="15%">Quant.</td>
                                    <td class="holderTabela" align="center" width="35%">Percentual</td>
                                </tr>
                                <!--Analise de registros encontrados-->
                                <logic:notEmpty name="relatorioAtendenteVO" property="relatorioEstadoTopVO">
                                    <!--Obtenção do vetor-->
                                    <bean:define name="relatorioAtendenteVO" property="relatorioEstadoTopVO" id="aRelatorioEstadoTopVO" />

                                    <%
                                    //Obtém o tamanho do vetor para impressão do bold no total
                                    Arraylength = new Integer(((Object[])aRelatorioEstadoTopVO).length - 1);
                                    %>
                                    <!--Impressao da estatistica Grupo+Estado-->
                                    <logic:iterate name="aRelatorioEstadoTopVO" id="relatorioEstadoTopVO" indexId="rownum">
                                        <tr>
                                            <%
                                            //Calcula o tamanho da barra de percentual
                                            sizeBar = (((RelatorioEstadoTopVO)relatorioEstadoTopVO).getPercentual() * 70) / 100;
                                            sizeBar = (sizeBar < 10 ? 20 : sizeBar);
                                            
                                            //Tratamento para bold na linha de total geral
                                            if( Arraylength.intValue() != rownum.intValue() ) {%>
                                                <td align="center"><bean:write name="relatorioEstadoTopVO" property="ordem" format="####"/></td>
                                                <td align="left"><bean:write name="relatorioEstadoTopVO" property="estado"/></td>
                                                <td align="center"><bean:write name="relatorioEstadoTopVO" property="quantidade" format="######"/></td> 
                                            <%} else {%>
                                                <td align="center"><b><bean:write name="relatorioEstadoTopVO" property="ordem" format="####"/></b></td>
                                                <td align="left"><b><bean:write name="relatorioEstadoTopVO" property="estado"/></b></td>
                                                <td align="center"><b><bean:write name="relatorioEstadoTopVO" property="quantidade" format="######"/></b></td>
                                            <%}%>
                                            
                                            <td>
                                                <table cellpadding="0" cellspacing="0" border=0><tr>
                                                <td align="center" valign="middle" width="40px">&nbsp;<bean:write name="relatorioEstadoTopVO" property="percentual" format="###"/>% </td>
                                                <td valign="middle"><div style="width:<%=sizeBar%>px;height:15px;border:0;overflow:hidden;padding:2px;font:bold 6pt verdana;color:white;filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=1, StartColorStr='#FF000066', EndColorStr='#FF99CCFF');"></div></td>
                                                </tr></table>
                                            </td>
                                        </tr>
                                    </logic:iterate>
                                </logic:notEmpty>
                            </table>
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                    </logic:notEmpty>
                </logic:iterate>
            </logic:notEmpty>
        </vivo:tbRows>
    </vivo:tbTable>
</vivo:quadro>
   
</acesso:controlHiddenItem>
<script language="javascript">
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>
