<!--
Módulo.....: Gestão de Processos
Caso de Uso: Monitoramento Estado da Linha
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
int idxrow = 0;
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<acesso:controlHiddenItem nomeIdentificador="wor_mest_verpagina">
<!--Estado da Linha-->
<vivo:quadro id="qdGeralEstado" height="110" width="775" label="&nbsp;Avaliação Geral por Estado" scroll="N">
    <vivo:tbTable resize="false" selectable="false" layoutWidth="750" layoutHeight="80" tableWidth="750" styleId="tbGeralEstado" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="10%" type="string">Ordem</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="40%" type="string">Estado</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Quantidade</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left"   width="30%" type="string">Percentual</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows scroll="false">
            <!--Analise de registros encontrados-->
            <logic:notEmpty name="monitoramentoForm" property="relatorioEstadoTopVO">
                <!--Obtenção do vetor-->
                <bean:define name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="monitoramentoForm.relatorioEstadoTopVO" id="aRelatorioEstadoTopVO" />
                
                <%
                //Obtém o tamanho do vetor para impressão do bold no total
                Arraylength = new Integer(((Object[])aRelatorioEstadoTopVO).length - 1);
                %>
                <!--Impressao da estatistica-->
                <logic:iterate name="aRelatorioEstadoTopVO" id="relatorioEstadoTopVO" indexId="rownum">
                    <vivo:tbRow key="linha1" zebrar="S">
                        <%
                        //Calcula o tamanho da barra de percentual
                        sizeBar = (((RelatorioEstadoTopVO)relatorioEstadoTopVO).getPercentual() * 170) / 100;
                        sizeBar = (sizeBar < 10 ? 20 : sizeBar);
                        
                        //Tratamento para bold na linha de total geral
                        if( Arraylength.intValue() != rownum.intValue() ) {%>
                            <vivo:tbRowColumn><bean:write name="relatorioEstadoTopVO" property="ordem"      format="####"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="relatorioEstadoTopVO" property="estado"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="relatorioEstadoTopVO" property="quantidade" format="######"/></vivo:tbRowColumn>

                        <%} else {%>
                            <vivo:tbRowColumn><b><bean:write name="relatorioEstadoTopVO" property="ordem"      format="####"/></b></vivo:tbRowColumn>
                            <vivo:tbRowColumn><b><bean:write name="relatorioEstadoTopVO" property="estado"/></b></vivo:tbRowColumn>
                            <vivo:tbRowColumn><b><bean:write name="relatorioEstadoTopVO" property="quantidade" format="######"/></b></vivo:tbRowColumn>
                        <%}%>
                        <vivo:tbRowColumn>
                            &nbsp;
                            <table cellpadding="0" cellspacing="0" border=0><tr>
                            <td align="center" valign="middle" width="40px">&nbsp;<bean:write name="relatorioEstadoTopVO" property="percentual" format="###"/>%</td>
                            <td valign="middle"><div style="width:<%=sizeBar%>px;height:15px;border:0;overflow:hidden;padding:2px;font:bold 6pt verdana;color:white;filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=1, StartColorStr='#FF000066', EndColorStr='#FF99CCFF');"></div></td>
                            </tr></table>
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            </logic:notEmpty>
        </vivo:tbRows>
    </vivo:tbTable>
</vivo:quadro>
<vivo:quadro id="qdGeralEstadoDetalhe" height="230" width="775" label="&nbsp;Avaliação Geral por Estado em Grupo" scroll="N">
    <vivo:tbTable resize="false" selectable="false" layoutWidth="750" layoutHeight="220" tableWidth="750" styleId="tbGeralEstadoDetalhe" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="10%" type="string">Ordem</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="40%" type="string">Grupo</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left"   width="50%" type="string">Detalhe</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows scroll="false">
            <!--Analise de registros encontrados-->
            <logic:notEmpty name="monitoramentoForm" property="relatorioEstadoBottonVO">
                <!--Obtenção do vetor-->
                <bean:define name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="monitoramentoForm.relatorioEstadoBottonVO" id="aRelatorioEstadoBottonVO" />
                
                <!--Impressao da estatistica Grupo-->
                <logic:iterate name="aRelatorioEstadoBottonVO" id="relatorioEstadoBottonVO">
                    <vivo:tbRow key="linha1" zebrar="S">
                        <vivo:tbRowColumn><b><bean:write name="relatorioEstadoBottonVO" property="ordem" format="####"/></b></vivo:tbRowColumn>
                        <vivo:tbRowColumn><b><bean:write name="relatorioEstadoBottonVO" property="grupo"/></b></vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <table id="tab01" cellpadding="5" cellspacing="0" width="100%">
                                <tr> <%-- cabecalho --%>
                                    <td class="holderTabela" align="center" width="15%">Ordem</td>
                                    <td class="holderTabela" align="center" width="35%">Estado</td>
                                    <td class="holderTabela" align="center" width="15%">Quant.</td>
                                    <td class="holderTabela" align="center" width="35%">Percentual</td>
                                </tr>
                                <!--Analise de registros encontrados-->
                                <logic:notEmpty name="relatorioEstadoBottonVO" property="relatorioEstadoTopVO">
                                    <!--Obtenção do vetor-->
                                    <bean:define name="relatorioEstadoBottonVO" property="relatorioEstadoTopVO" id="aRelatorioEstadoTopVO" />

                                    <%
                                    //Obtém o tamanho do vetor para impressão do bold no total
                                    Arraylength = new Integer(((Object[])aRelatorioEstadoTopVO).length - 1);
                                    %>
                                    <!--Impressao da estatistica Grupo+Estado-->
                                    <logic:iterate name="aRelatorioEstadoTopVO" id="relatorioEstadoTopVO" indexId="rownum">
                                        <tr>
                                            <%
                                            //Calcula o tamanho da barra de percentual
                                            sizeBar = (((RelatorioEstadoTopVO)relatorioEstadoTopVO).getPercentual() * 75) / 100;
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
                                            <td valign="middle">
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
