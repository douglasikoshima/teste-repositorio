<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ColunasRelatorio"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ValoresRelatorio"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO"%>
<%@ page import="workflow.Relatorios.RelatoriosController.RelatorioForm"%>
<%@ page import="java.util.HashMap"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<%
    String dsTituloRelatorio = ((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getDsTituloRelatorio();
    String detalheScript = ((RelatorioForm)relatorioForm).getDetalheScript();
%>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> Workflow"/>
    <netui-template:setAttribute name="modulo" value="Workflow"/>
    <netui-template:section name="headerSection">
    
    </netui-template:section>  
    <netui-template:section name="bodySection">
        
<!--APLICACAO-->
<acesso:controlHiddenItem nomeIdentificador="wor_rrstdo_verpagina">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
		<div><img src="/FrontOfficeWeb/resources/images/transp.gif" height="5"></div>
<vivo:quadro id="qdMain" height="475" width="790" label='<%= dsTituloRelatorio %>' scroll="no">
<form><% 
String mostrarFiltros = ((RelatorioForm)relatorioForm).getMostrarFiltros();
int tHeight = 380;
if (mostrarFiltros != null && mostrarFiltros.equals("S")) {
    
    Object[] keys = ((RelatorioForm)relatorioForm).getCabecRelatorios().keySet().toArray();
    HashMap cabec = ((RelatorioForm)relatorioForm).getCabecRelatorios();
    int quebrar=0;
    int linhas = (int)Math.floor(keys.length / 3) + 1;
    tHeight -= (linhas * 18);
%>
<table cellpadding="3" cellspacing="0" border="0" width="100%">
    <%
    for(int i=0; i<keys.length; i++) {
        quebrar++;
    %>
        <%if (quebrar==1){%><tr><%}%>
            <td width="33%"><b><%=keys[i]%>:</b> <%=cabec.get(keys[i])%></td>
        <%if (quebrar==3) {%></tr><%quebrar=0;}%>
    <%}%>
    <%if (quebrar<3){%></tr><%}%>
</table><%}%>
    <vivo:tbTable selectable="true" layoutWidth="760" layoutHeight="<%=String.valueOf(tHeight)%>" tableWidth="760" styleId="relatorioTable" sortable="false">
        <vivo:tbHeader>
            <logic:iterate id="colunasVO" name="relatorioForm" property="wFRelatorioDinamicoVO.colunasRelatorioArray" indexId="idx">
                <vivo:tbHeaderColumn align="left" width="700" type="string">
                    <bean:write name="colunasVO" property="dsColuna"/>
                </vivo:tbHeaderColumn>
                <logic:notEmpty name="colunasVO" property="idColuna">
                <input type="hidden" name="colunasDetalhe" value='<bean:write name="colunasVO" property="idColuna"/>'>
                <input type="hidden" name="valoresDetalhe">
                </logic:notEmpty>
            </logic:iterate>
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate id="valoresRelatorioVO" name="relatorioForm" property="wFRelatorioDinamicoVO.valoresRelatorioArray" indexId="idx">
                <% 
                String detScript = detalheScript;
                if (((ValoresRelatorio)valoresRelatorioVO).getInTotal()==1) {
                    detScript = "";
                }
                %>
                <vivo:tbRow key="linha1" onRowClick="<%=detScript%>">
                    <logic:iterate id="valoresColunasVO" name="valoresRelatorioVO" property="valorColunaArray" indexId="idx2">
                        <vivo:tbRowColumn key="">
                            <logic:equal name="valoresRelatorioVO" property="inTotal" value="1"><b></logic:equal>
                            <center><bean:write name="valoresColunasVO" property="valor"/>
                        </vivo:tbRowColumn>
                    </logic:iterate>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>
    <br>
    <table width="100%">
        <tr>
            <td align="left">
                <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" id="btVoltar"  value="Voltar"  onClick="submitVoltar(); return false"/>
            </td>
            <td align="center">
            <% 
            WFRelatoriosFiltrosVO filtro = ((RelatorioForm)relatorioForm).getFiltro();
            String inFim = ((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getInFin();
            if (filtro.getInTotal()==null || filtro.getInTotal().equals("0")) {
            %>
            <% } else { %>
            <img onClick="pesquisar(-1); return false;" id="btAnterior" src="/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_over.gif'" style="border:nonecursor:pointer;"/>
            <% } %>   
            <% if (inFim == null || inFim.equals("1")) {%>
            <% } else { %>
            <img onClick="pesquisar(1); return false;" id="btProximo" src="/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_over.gif'" style="border:nonecursor:pointer;"/>
            <% } %>
            </td>
            <td align="right">
                <logic:equal value="true" name="relatorioForm" property="exportar">
                    <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_exportar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_exportar_over.gif" id="btExportar"  value="Exportar"  onClick="submitExportarPagina(); return false"/>
                </logic:equal>
                <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_imprimir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_imprimir_over.gif" id="btImprimir"  value="Imprimir"  onClick="submitImprimir(); return false"/>
            </td>
        </tr>
    </table>
</form>
</vivo:quadro>
<vivo:quadroFlutuante id="dvDetalhes" idIframe="ifrmDetalhes" scroll="no"  width="780" height="428" spacesTop="90" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Detalhes" onclick=""/>
<iframe name="ifrmDownload" id="ifrmDownload" style="visibility: hidden;" width="0" height="0" onreadystatechange ="top.frameApp.stopAnimation();"></iframe>
<form name="refreshForm">
<input type=hidden name="refresh" value="1">
<input type=hidden name="bloco" value="0">
</form>
</acesso:controlHiddenItem> 
<script>
        //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

    //Controle da tread de refresh automatico
    var treadRefresh;
    var MinutesRefresh = '<bean:write name="relatorioForm" property="atualizacao" format="###"/>';
    if (MinutesRefresh == "") {
        SecondsRefresh = 0;
    } else {
        SecondsRefresh = MinutesRefresh * 60;
    }

    <%
    // calcula o numero de colunas dinamicas
    int nColunas = ((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getColunasRelatorioArray().length;
    ColunasRelatorio[] colunasRelatorio = ((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getColunasRelatorioArray();
    for (int i=0; i<((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getColunasRelatorioArray().length; i++) {
        String idColuna = colunasRelatorio[i].getIdColuna();
        if ("".equals(idColuna) || idColuna == null) {
            nColunas--;
        }
    }
    %>
        
    function submitDownload(linha) {
        top.frameApp.startAnimation();
        <logic:notEmpty name="relatorioForm" property="wFRelatorioDinamicoVO.colunasRelatorioArray">
            <% if (nColunas > 1) { %>
                <logic:iterate id="colunasVO" name="relatorioForm" property="wFRelatorioDinamicoVO.colunasRelatorioArray" indexId="idx">
                    <logic:notEmpty name="colunasVO" property="idColuna">
						<%-- document.forms[0].valoresDetalhe[<%=idx%>].value=linha.cells(<%=idx%>).innerText; --%>
                        document.forms[0].valoresDetalhe[<bean:write name="idx"/>].value=linha.cells(<bean:write name="idx"/>).innerText;
                    </logic:notEmpty>
                </logic:iterate>
            <% } else { %>
                document.forms[0].valoresDetalhe.value=linha.cells(0).innerText;
            <% } %>

        window.clearInterval(treadRefresh);
   
        document.forms[0].target = "ifrmDownload";
        document.forms[0].method = "GET";
        document.forms[0].action = '<bean:write name="relatorioForm" property="detalheAction"/>.do';
        document.forms[0].submit();                
        </logic:notEmpty>
        
        if (SecondsRefresh > 0) {
            startRefresh();
        }
		
    }
    
    function submitDetalheQuadro(linha) {
        //Inicio animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        <logic:notEmpty name="relatorioForm" property="wFRelatorioDinamicoVO.colunasRelatorioArray">
            <% if (nColunas > 1) { %>
                <logic:iterate id="colunasVO" name="relatorioForm" property="wFRelatorioDinamicoVO.colunasRelatorioArray" indexId="idx">
                    <logic:notEmpty name="colunasVO" property="idColuna">
						<%-- document.forms[0].valoresDetalhe[<%=idx%>].value=linha.cells(<%=idx%>).innerText; --%>
                        document.forms[0].valoresDetalhe[<bean:write name="idx"/>].value=linha.cells(<bean:write name="idx"/>).innerText;
                    </logic:notEmpty>
                </logic:iterate>
            <% } else { %>
                document.forms[0].valoresDetalhe.value=linha.cells(0).innerText;
            <% } %>
            window.clearInterval(treadRefresh);
            dvDetalhes.style.display = '';
            document.forms[0].method = "GET";
            document.forms[0].action = "<bean:write name="relatorioForm" property="detalheAction"/>.do";
            document.forms[0].target = "ifrmDetalhes";
            document.forms[0].submit();
        </logic:notEmpty>
        
        return false;
        
    }
        
    function fecharQuadroFlutuante() {
        dvDetalhes.style.display = 'none';
    }

    function submitVoltar() {
        //Inicio animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        document.forms[0].method = "POST";
        document.forms[0].target = "";
        document.forms[0].action = "<bean:write name="relatorioForm" property="inicioAction"/>.do";
        document.forms[0].submit();
    }
    

    function submitImprimir() {
        window.showModalDialog("relatorioImprimir.do","<%=dsTituloRelatorio%>","dialogWidth:675px;dialogHeight:450px");
    }

    function submitExportarPagina() {
        document.forms[0].method = "GET";
        document.forms[0].target = "";
        document.forms[0].action = "relatorioExportar.do";
        document.forms[0].submit();    
    }

    //Controle da tread do refresh automático parametrizável
    function startRefresh() {
                
        //Limpa a tread se necessario
        window.clearInterval(treadRefresh);
    
        //Calcula o numero de milesegundos
        var mlSecondsRefresh = SecondsRefresh * 1000;
        
        //Inicializa o refresh automático
        treadRefresh = window.setInterval("pesquisar(0)", mlSecondsRefresh );
        
    }
    
    function pesquisar(offset) {
        //Inicio animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();        
        document.forms["refreshForm"].bloco.value = offset;
        document.forms["refreshForm"].action = "<bean:write name="relatorioForm" property="gerarAction"/>.do";
        document.forms["refreshForm"].target = "";
        document.forms["refreshForm"].submit();
    }    

    if (SecondsRefresh > 0) {
        startRefresh();
    }
    
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

    DoResizeHeaderTableVivo();

</script>

    </netui-template:section>
</netui-template:template>
