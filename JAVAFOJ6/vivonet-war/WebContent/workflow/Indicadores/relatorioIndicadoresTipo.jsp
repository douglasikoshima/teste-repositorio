<!--
Módulo.....: Gestão de Processos
Caso de Uso: Indicadores Tipo Processo
Versão.....: $Author: a5112274 $ - $Revision: 1.1.2.1 $ - $Date: 2012/10/11 14:16:07 $
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFIndicadoresVODocument.WFIndicadoresVO.Indicadores.DetalheRegional.DetalheGrupo"%>
<%@ page import="workflow.Indicadores.IndicadoresController.IndicadoresForm"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
   
<acesso:controlInitEnv/>
<bean:define id="indicadoresForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="indicadoresForm"/>
<%
//Definições
int     sizeBar     = 0;    //Controle do size da barra de percentual
Integer Arraylength = null; //Controle de impressão
String dsTitulo = ((IndicadoresForm)indicadoresForm).getIndicadoresVO().getDsTituloIndicadores();
String strMediaPorcentagem = ((IndicadoresForm)indicadoresForm).getIndicadoresVO().getMediaPorcentagem();
int mediaPorcentagem = 0;
if (strMediaPorcentagem!=null && !ConstantesCRM.SVAZIO.equals(strMediaPorcentagem)) {
     mediaPorcentagem = Integer.parseInt(strMediaPorcentagem);
}
    
String barColor = ConstantesCRM.SVAZIO;
int tb1=0;
int tb2=0;
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<acesso:controlHiddenItem nomeIdentificador="wor_ritp_verpagina">
<!--Tipo de Processo-->
<vivo:quadro id="qdTipoProcesso" height="375" width="775" label='<%= dsTitulo %>' scroll="N">
    <div id="tbHeader" class="headerTabela" style="width: 766; overflow: auto;">
    <table class="normal" cellpadding="0" cellspacing="0" width="100%" border=0>    
    <%-- <vivo:tbTable selectable="false" resize="false" layoutWidth="750" layoutHeight="375" tableWidth="750" styleId="tdTipoProcessos" sortable="true"> --%>
        <tr>
            <td class="holderTabela" align="center" width="8%"  height="22px">Ordem</td>
            <td class="holderTabela" align="center" width="10%"  height="22px">Regional</td>
            <td class="holderTabela" align="center" width="82%" height="22px">Detalhe Regional</td>
        </tr>
        <!-- Regionais ... -->
    </table>
    </div>
    <div id="tbGeral" class="corpoTabela" style="width: 766; height: 348px; overflow: auto;">
    <table class="normal" cellpadding="0" cellspacing="0" width="100%" border=0>    
        <logic:iterate id="regional" name="indicadoresForm" property="indicadoresVO.indicadoresArray" indexId="idx1">
            <tr class='rowTabelaZebradoOff'>
                <td align="center" width="8%" ><b><bean:write name="regional" property="ordem"/></b></td>
                <td align="center" width="10%" ><b><bean:write name="regional" property="dsRegional"/></b></td>
                <td align="center" width="82%"> <%-- Detalhes das Regionais --%>
                    <table cellpadding="0" cellspacing="0" width="100%" border=0>
                        <tr>
                            <td class="holderTabela" align="center" width="25%" height="22px">Grupo</td>
                            <td class="holderTabela" align="center" width="75%" height="22px">Detalhe</td>
                        </tr>
                        <logic:iterate id="grupos" name="regional" property="detalheRegionalArray" indexId="idx2">
                            <tr>
                                <td align="center"><b><bean:write name="grupos" property="dsGrupo"/></b></td>
                                <td align="center" valign="top">                                
                                    <table cellpadding="5" cellspacing="0" width="100%" border=0>
                                        <tr> <%-- cabecalho --%>
                                            <td class="holderTabela" align="center" width="50%"><bean:write name="indicadoresForm" property="indicadoresVO.dsTipoIndicadores"/></td>
                                            <td class="holderTabela" align="center" width="10%">Qtd.</td>
                                            <td class="holderTabela" align="left"   width="30%">Percentual</td>
                                        </tr>
                                        <bean:define name="grupos" property="detalheGrupoArray" id="detalhes" />
                                        <%
                                        //Obtém o tamanho do vetor para impressão do bold no total
                                        Arraylength = new Integer(((Object[])detalhes).length - 1);
                                        %>
                                        <!-- Contato -->
                                        <logic:iterate id="detalhes" name="grupos" property="detalheGrupoArray" indexId="idx3">
                                            <tr>                                      
                                                <%
                                                //Calcula o tamanho da barra de percentual
                                                int porcentagem = Integer.parseInt(((DetalheGrupo)detalhes).getPorcentagem());
                                                int moduloPorcentagem = porcentagem;
                                                if (moduloPorcentagem < 0)
                                                    moduloPorcentagem*=-1;
                                                    
                                                sizeBar = (moduloPorcentagem * 110) / 100;
                                                // sizeBar = (sizeBar < 20 ? 20 : sizeBar);
                                                sizeBar = (sizeBar > 100 ? 100 : sizeBar);
                                                barColor = (porcentagem > mediaPorcentagem ? "StartColorStr='#FF660000', EndColorStr='#FFFFCC99'" : "StartColorStr='#FF000066', EndColorStr='#FF99CCFF'");
                                                String descricao = ((DetalheGrupo)detalhes).getDescricao().replaceAll("/"," / ");
                                                //Tratamento para bold na linha de total geral
                                                if( Arraylength.intValue() != idx3.intValue() ) {
                                                %>
                                                    <td align="center"><%=descricao%></td>
                                                    <td align="center"><bean:write name="detalhes" property="quantidade"/></td>
                                                <%
                                                } else {
                                                %>
                                                    <td align="center"><b><%=descricao%></b></td>
                                                    <td align="center"><b><bean:write name="detalhes" property="quantidade" format="######"/></b></td>
                                                <%

                                                }
                                                %>
                                                <td>
                                                    <bean:write name="detalhes" property="porcentagem" format="###"/>% <div style="width:<%=sizeBar%>px;height:15px;border:0;overflow:hidden;padding:2px;font:bold 6pt verdana;color:white;filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=1, <%=barColor%>);"></div>
                                                </td>
                                            </tr>
                                        </logic:iterate>
                                    </table> 
                                </td>
                            </tr>
                        </logic:iterate>                        
                    </table>
                </td>
            </tr>
        </logic:iterate>
    </table>
    </div>
</vivo:quadro>
</acesso:controlHiddenItem>            
<script language="javascript">
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>
