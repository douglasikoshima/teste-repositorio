<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/fidelizacao.tld" prefix="fid"%>

<acesso:controlInitEnv/>


<bean:define id="relatorio" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOferta"/>
<bean:define id="formDadosPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosPesquisa"/>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/fidelizacao_relatorios.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Fidelizacao" name="Relatorio"></netui-template:setAttribute>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_rel_ofer_verpagina">
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();       
    </script>    
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="idFrelatorio" height="438" width="790" label="Retenção por Oferta" operacoes="" scroll="y">
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
            <!--
                INICIO HEADER DE FILTROS ESCOLHIDOS
            -->
            <jsp:include page="headerRelCrit.jsp" />          
            <!--
                FIM HEADER DE FILTROS ESCOLHIDOS
            -->       
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
            
            <fid:table>
                <fid:tr bgColor="#545454">                    
                    <fid:headerTd>Intenção do Cancelamento</fid:headerTd>
                    <fid:headerTd>Qtd Intenções</fid:headerTd>
                    <fid:headerTd>% Intenções</fid:headerTd>
                    <fid:headerTd>Destino Previsto</fid:headerTd>
                    <fid:headerTd>Ofertas</fid:headerTd>
                    <fid:headerTd>% Ofertas</fid:headerTd>
                    <fid:headerTd><bean:write name="formDadosPesquisa" property="dsOferta"/></fid:headerTd>
                    <fid:headerTd>% <bean:write name="formDadosPesquisa" property="dsOferta"/></fid:headerTd>                    
                </fid:tr>
                <logic:iterate id="linha" name="relatorio" property="linhaOferta">
                    <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                        <fid:dataTd><bean:write name="linha" property="intCancelamiento"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="qtdintencao"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="porcintencao"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="destinoprevisto"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="qtdoferta"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="porcoferta"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="ofertaFiltro"/></fid:dataTd>
                        <fid:dataTd><bean:write name="linha" property="porcOfertaFiltro"/></fid:dataTd>                                
                    </fid:tr>            
                </logic:iterate>
            </fid:table>           

        </vivo:sessao>
        <table width="790" class="tbl_bgGray">        
            <tr>
                <td width="500">
                    <img align="letf" id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/fidelizacao/Relatorio/begin.do'; top.frameApp.mostrar_div(); return false" style="border:none;cursor:hand;" hspace="5" vspace="5"/>                
                </td>
                <td align="right">
                <acesso:controlHiddenItem nomeIdentificador="fid_rel_ofer_imp">
                    <img id="imprimir" src="/FrontOfficeWeb/resources/images/imprimir-retrato-nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/imprimir-retrato-over.gif" onClick="mostrarTelaImpressao('printRetencaoOferta.do','Relatório Retenção por Oferta'); return false;" style="border:none" hspace="5" vspace="5"/>
                </acesso:controlHiddenItem>
                </td>
                <td align="right">
                <acesso:controlHiddenItem nomeIdentificador="fid_rel_ofer_imp">
                    <img id="imprimir" src="/FrontOfficeWeb/resources/images/imprimir-paisagem-nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/imprimir-paisagem-over.gif" onClick="mostrarTelaImpressao('relatorioOfertaImpressao.jsp','Relatório Retenção por Oferta'); return false;" style="border:none" hspace="5" vspace="5"/>
                </acesso:controlHiddenItem>
                </td>
                <td align="right">
                <acesso:controlHiddenItem nomeIdentificador="fid_rel_ofer_arq">
                    <img id="gerararquivo" src="/FrontOfficeWeb/resources/images/bt_gerararq_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gerararq_over.gif" onClick="window.location.href='/FrontOfficeWeb/fidelizacao/Relatorio/gerarArquivo.do?idRelatorio=5'" style="border:none" hspace="5" vspace="5" />
                </acesso:controlHiddenItem>
                </td>                
            </tr>            
        </table>
        </acesso:controlHiddenItem>
        </netui-template:section>
        </netui-template:template>
    
