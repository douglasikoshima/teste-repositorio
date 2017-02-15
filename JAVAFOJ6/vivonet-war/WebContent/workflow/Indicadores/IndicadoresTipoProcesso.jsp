<!--
Módulo.....: Gestão de Processos
Caso de Uso: Indicadores Tipo Processo
Versão.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:41 $
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFIndicadoresVODocument.WFIndicadoresVO.Indicadores.DetalheRegional.DetalheGrupo"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="indicadoresForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="indicadoresForm"/>
<%
//Definições
int     sizeBar     = 0;    //Controle do size da barra de percentual
Integer Arraylength = null; //Controle de impressão
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<!--Tipo de Processo-->
<vivo:quadro id="qdTipoProcesso" height="355" width="775" label="&nbsp;Avaliação por Tipo Processo" scroll="N">

    <vivo:tbTable selectable="false" layoutWidth="750" layoutHeight="325" tableWidth="750" styleId="tdTipoProcessos" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="8%" type="string">Ordem</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="10%" type="string">Regional</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="82%" type="string">Detalhe Regional</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        
        <vivo:tbRows>    
        <!-- Regionais ... -->
        <logic:iterate id="regional" name="indicadoresForm" property="indicadoresVO.indicadoresArray" indexId="idx1">
            <vivo:tbRow key="linha1" zebrar="S">
                <vivo:tbRowColumn><b><bean:write name="regional" property="ordem"/></b></vivo:tbRowColumn>
                <vivo:tbRowColumn><b><bean:write name="regional" property="dsRegional"/></b></vivo:tbRowColumn>
                <vivo:tbRowColumn>
                
                    <vivo:tbTable selectable="false" layoutWidth="598" layoutHeight="300" tableWidth="598" styleId="tdTipoProcessosDet1" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="center" width="25%" type="string">Grupo</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="75%" type="string">Detalhe</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        
                        <vivo:tbRows>
                        <!-- Grupos -->
                        <logic:iterate id="grupos" name="regional" property="detalheRegionalArray" indexId="idx2">
                            <vivo:tbRow key="linha1" zebrar="S">
                                <vivo:tbRowColumn><b><bean:write name="grupos" property="dsGrupo"/></b></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <vivo:tbTable selectable="false" layoutWidth="430" layoutHeight="60" tableWidth="430" styleId="tdTipoProcessoGruposDet1" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="center" width="40%" type="string">Contato</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Quantidade</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="left"   width="30%" type="string">Percentual</vivo:tbHeaderColumn>
                                        </vivo:tbHeader>

                                        <bean:define name="grupos" property="detalheGrupoArray" id="detalhes" />
                                        <%
                                        //Obtém o tamanho do vetor para impressão do bold no total
                                        Arraylength = new Integer(((Object[])detalhes).length - 1);
                                        %>
                                        
                                        <vivo:tbRows>
                                        <!-- Contato -->
                                        <logic:iterate id="detalhes" name="grupos" property="detalheGrupoArray" indexId="idx3">
                                            <vivo:tbRow key="linha1">
                                                                                        
                                                <%
                                                //Calcula o tamanho da barra de percentual
                                                sizeBar = (Integer.parseInt(((DetalheGrupo)detalhes).getPorcentagem()) * 100) / 100;
                                                sizeBar = (sizeBar < 10 ? 20 : sizeBar);
                                                
                                                //Tratamento para bold na linha de total geral
                                                if( Arraylength.intValue() != idx3.intValue() ) {
                                                %>
                                                    <vivo:tbRowColumn><bean:write name="detalhes" property="descricao"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="detalhes" property="quantidade"/></vivo:tbRowColumn>
                                                <%
                                                } else {
                                                %>
                                                    <vivo:tbRowColumn><b><bean:write name="detalhes" property="descricao"/></b></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><b><bean:write name="detalhes" property="quantidade" format="######"/></b></vivo:tbRowColumn>
                                                <%
                                                }
                                                %>
                                                
                                                <vivo:tbRowColumn>
                                                    <div style="width:<%=sizeBar%>px;height:15px;border:0;overflow:hidden;padding:2px;font:bold 6pt verdana;color:white;filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=1, StartColorStr='#FF000066', EndColorStr='#FF99CCFF');">
                                                    <bean:write name="detalhes" property="porcentagem" format="###"/>%
                                                    </div>
                                                </vivo:tbRowColumn>

                                            </vivo:tbRow>
                                        </logic:iterate>
                                        </vivo:tbRows>
                                        
                                    </vivo:tbTable> 
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                        </logic:iterate>
                        </vivo:tbRows>
                        
                    </vivo:tbTable>
                </vivo:tbRowColumn>
            </vivo:tbRow>
        </logic:iterate>
        </vivo:tbRows>
        
    </vivo:tbTable>
</vivo:quadro>
            
<script language="javascript">
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>
