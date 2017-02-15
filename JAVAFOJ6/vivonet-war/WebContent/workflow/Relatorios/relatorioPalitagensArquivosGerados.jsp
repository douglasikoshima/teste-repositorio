<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
   
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<bean:define id="lArq" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm.WFListaArquivosGeradosVO" type="br.com.vivo.fo.workflow.vo.WFListaArquivosGeradosVODocument.WFListaArquivosGeradosVO"/>

<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="Vivo Net >> Workflow"/>
<netui-template:setAttribute name="modulo" value="Workflow"/>
<netui-template:section name="headerSection">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/xtree.css"  />

<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script language="Javascript">
        <!--
            function download(nrPos) {
                document.forms[0].target = '';
                document.forms[0].action = 'downloadArquivoPalitagens.do?nrPos='+nrPos;
                document.forms[0].submit();
            }

            function consultaFiltrosSelecionados(nrPos, nmArquivo) {
                dvFiltros = document.getElementById("dvFiltrosSelecionados");
                document.getElementById("dv_dvFiltrosSelecionados").innerHTML = nmArquivo;
                dvFiltros.style.display = "block";
                ifrmFiltrosSelecionados.document.body.style.fontFamily = "Tahoma";
                ifrmFiltrosSelecionados.document.body.style.fontSize = "11px";
                ifrmFiltrosSelecionados.document.body.innerHTML = "&nbsp;carregando...";
                document.forms[0].target = 'ifrmFiltrosSelecionados';
                document.forms[0].action = 'consultaFiltrosSelecionados.do?nrPos='+nrPos;
                document.forms[0].submit();
            }
        -->
    </script>
    <script language="javascript" for="window" event="onload">
        <!--
        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        -->
    </script>
</netui-template:section>
<netui-template:section name="bodySection">
<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>     
<vivo:sessao id="qdMain" height="475" width="790" label="Vivo Net >> Workflow >> Relatórios" operacoes="Palitagens - Arquivos Gerados" scroll="N">
    <form id="formFiltro" name="relatorioForm" action="palitagensArquivosGerados.do" onSubmit="return false;" method="post">
        <table cellpadding="5" cellspacing="0" border="0" width="100%" height="100%">
            <tr>
                <td colspan="2" valign="top">
                    <vivo:tbTable selectable="true" styleId="tbArquivosGerados" layoutHeight="400" layoutWidth="750" tableWidth="750">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn width="55%" type="String">Arquivo</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="35%" type="String">Data/Hora de Solicitação</vivo:tbHeaderColumn>
                            <%--vivo:tbHeaderColumn width="5%" type="String">&nbsp;</vivo:tbHeaderColumn--%>
                            <vivo:tbHeaderColumn width="5%" type="String">&nbsp;</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="5%" type="String">&nbsp;</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate id="it" name="lArq" property="listaRelatoriosArray" indexId="indx">
                            <vivo:tbRow key="linha1">
                                <vivo:tbRowColumn><bean:write name="it" property="nmArquivo"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="it" property="dtSolicitacao"/></vivo:tbRowColumn>
                                <%--vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_filtro.gif" title="Visualizar filtros utilizados" style="cursor:pointer;" onclick="consultaFiltrosSelecionados('<%=indx%>','<bean:write name="it" property="nmArquivo"/>')"/></vivo:tbRowColumn--%>
                                <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_filtro.gif" title="Visualizar filtros utilizados" style="cursor:pointer;" onclick="consultaFiltrosSelecionados('<%=indx%>','<bean:write name="it" property="nmArquivo"/>')"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_download.gif" title="Download" style="cursor:pointer;" onclick="download('<%=indx%>')"/></vivo:tbRowColumn>
                            </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="left" valign="bottom">
                    <img style="border:0px;"
                                       src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                                       id="btVoltar"
                                       value="Voltar"
                                       onClick="document.location='beginPalitagens.do';return false;"/>
                </td>
                <td align="right" valign="bottom">
                    <img style="border:0px;" 
                                       src="/FrontOfficeWeb/resources/images/bt_gerarrelatorio_nrml.gif"
                                       id="btGerar"
                                       value="Gerar"
                                       onClick="submitGerar(); return false"/>
                </td>
            </tr>
        </table>
    </form>
</vivo:sessao>
<vivo:quadroFlutuante id="dvFiltrosSelecionados" idIframe="ifrmFiltrosSelecionados" height="300" width="350" spacesTop="60" spacesLeft="250" display="none" url="<%=request.getContextPath()%>" label="Filtros selecionados"/>
</netui-template:section>
</netui-template:template>