<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ColunasRelatorio"%>
<%@ page import="workflow.Relatorios.RelatoriosController.RelatorioForm"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<netui-data:getData resultId="warningFrame" value="{globalApp.warningFrame}" />
<acesso:controlInitEnv/>
<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<%
    String dsTituloRelatorio = ((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getDsTituloRelatorio();
    String detalheScript = ((RelatorioForm)relatorioForm).getDetalheScript();
%>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<!--APLICACAO-->
<acesso:controlHiddenItem nomeIdentificador="wor_rrstdo_verpagina">
<form>
<vivo:quadro id="qdMain" height="580" width="790" label='<%= dsTituloRelatorio %>' scroll="no">
    <br>
    <vivo:tbTable selectable="true" onRowClick="<%=detalheScript%>" layoutWidth="760" layoutHeight="490" tableWidth="760" styleId="tableTitle" sortable="false">
        <vivo:tbHeader>
            <logic:iterate id="colunasVO" name="relatorioForm" property="wFRelatorioDinamicoVO.colunasRelatorioArray" indexId="idx">
                <vivo:tbHeaderColumn align="left" width="10%" type="string">
                    <bean:write name="colunasVO" property="dsColuna"/>
                </vivo:tbHeaderColumn>
                <logic:notEmpty name="colunasVO" property="idColuna">
                <input type="hidden" name="colunasDetalhe" value='<bean:write name="colunasVO" property="idColuna"/>'>
                <input type="hidden" name="valoresDetalhe">
                </logic:notEmpty>
            </logic:iterate>
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate id="valoresRelatorioVO" name="relatorioForm" property="wFRelatorioDinamicoVO.valoresRelatorioArray" indexId="idx2">
                <vivo:tbRow key="">
                    <logic:iterate id="valoresColunasVO" offset="1" name="valoresRelatorioVO" property="valorColunaArray" indexId="idx3">
                        <vivo:tbRowColumn key=""><center><bean:write name="valoresColunasVO" property="valor"/></vivo:tbRowColumn>
                    </logic:iterate>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>
    <br>
    <table width="100%">
        <tr>
            <td align="center">
                <vivo:botao id="btFechar" width="60px" height="10px" value="Voltar" styleClass="btTemplate" onclick="submitVoltar();"/>
            </td>
        </tr>
    </table>
</vivo:quadro>

</form>
<%=pageContext.getAttribute("warningFrame")%>
</acesso:controlHiddenItem>
<script>

    <%
    // calcula o numero de colunas dinamicas
    int nColunas = ((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getColunasRelatorioArray().length;
    ColunasRelatorio[] colunasRelatorio = ((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getColunasRelatorioArray();
    for (int i=0; i<nColunas; i++) {
        String idColuna = colunasRelatorio[i].getIdColuna();
        if (idColuna == null) {
            nColunas--;
        }
    }
    %>
    
    function submitDetalheQuadro(linha) {
        //Liga animação
        //if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

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
            
            document.forms[0].method = "POST";
            document.forms[0].action = "<bean:write name="relatorioForm" property="detalheAction"/>.do";
            document.forms[0].target = "_top";
            document.forms[0].submit();
            
        </logic:notEmpty>
    }
        
    function fecharQuadroFlutuante() {
        dvDetalhes.style.display = 'none';
    }

    function submitVoltar() {
        document.forms[0].method = "POST";
        document.forms[0].action = "<bean:write name="relatorioForm" property="inicioAction"/>.do";
        document.forms[0].target = "_top";
        document.forms[0].submit();
    }

</script>
