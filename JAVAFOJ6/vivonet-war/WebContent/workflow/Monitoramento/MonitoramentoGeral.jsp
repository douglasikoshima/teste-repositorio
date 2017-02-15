<!--
Módulo.....: Gestão de Processos
Caso de Uso: Monitoramento Geral
Versão.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:41 $
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="workflow.Monitoramento.MonitoramentoProcessosController.RelatorioGeralVO"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
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

<acesso:controlHiddenItem nomeIdentificador="wor_mgral_verpagina">
<!--Geral-->
<vivo:tbTable selectable="false" layoutWidth="760" layoutHeight="345" tableWidth="760" styleId="tbGeral" sortable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Ordem</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="40%" type="string">Grupo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="20%" type="string">Quantidade</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="30%" type="string">Percentual</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    
    <vivo:tbRows scroll="false">
        <!--Analise de registros encontrados-->
        <logic:notEmpty name="monitoramentoForm" property="relatorioGeralVO">
            <!--Obtenção do vetor-->
            <bean:define id="aRelatorioGeralVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="monitoramentoForm.relatorioGeralVO"/>
            
            <%
            //Obtém o tamanho do vetor para impressão do bold no total
            Arraylength = new Integer(((Object[])aRelatorioGeralVO).length - 1);
            %>
            <!--Impressao da estatistica-->
            <logic:iterate name="aRelatorioGeralVO" id="relatorioGeralVO" indexId="rownum">
                <%
                sizeBar = (((RelatorioGeralVO)relatorioGeralVO).getPercentual() * 180) / 100;
                %>

                <vivo:tbRow key="linha1" zebrar="S">
                    <%
                    //Tratamento para bold na linha de total geral
                    if( Arraylength.intValue() != rownum.intValue() ) {%>
                        <vivo:tbRowColumn><bean:write name="relatorioGeralVO" property="ordem"      format="####"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><vivo:hint maxLength="35" spaces="S"><bean:write name="relatorioGeralVO" property="grupo"/></vivo:hint></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="relatorioGeralVO" property="quantidade" format="######"/></vivo:tbRowColumn>

                    <%} else {%>
                        <vivo:tbRowColumn><b><bean:write name="relatorioGeralVO" property="ordem"      format="####"/></b></vivo:tbRowColumn>
                        <vivo:tbRowColumn><b><vivo:hint maxLength="35" spaces="S"><bean:write name="relatorioGeralVO" property="grupo"/></vivo:hint></b></vivo:tbRowColumn>
                        <vivo:tbRowColumn><b><bean:write name="relatorioGeralVO" property="quantidade" format="######"/></b></vivo:tbRowColumn>
                    <%}%>
                    
                    <vivo:tbRowColumn>
                        &nbsp;
                        <table cellpadding="0" cellspacing="0" border=0><tr>
                        <td align="center" valign="middle" width="40px">&nbsp;<bean:write name="relatorioGeralVO" property="percentual" format="###"/>%&nbsp;</td>
                        <td valign="middle"><div style="width:<%=sizeBar%>px;height:15px;border:0;overflow:hidden;padding:2px;font:bold 6pt verdana;color:white;filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=1, StartColorStr='#FF000066', EndColorStr='#FF99CCFF');"></div></td>
                        </tr></table>
                    </vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </logic:notEmpty>
    </vivo:tbRows>
</vivo:tbTable>
   
</acesso:controlHiddenItem>
<script language="javascript">
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>
