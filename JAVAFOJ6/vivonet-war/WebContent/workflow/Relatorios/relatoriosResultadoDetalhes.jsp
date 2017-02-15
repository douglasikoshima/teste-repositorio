<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="workflow.Relatorios.RelatoriosController.RelatorioForm"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<bean:define id="relatorioForm" scope="request" name="relatorioForm"/>
<%
    String dsTituloRelatorio = ((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getDsTituloRelatorio();
%>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<!--APLICACAO-->
<acesso:controlHiddenItem nomeIdentificador="wor_rrstdod_verpagina">
<form>
<vivo:quadro id="qdMain" height="400" width="770" label='<%= dsTituloRelatorio %>' scroll="no">
    <br>
    <vivo:tbTable selectable="true" layoutWidth="740" layoutHeight="310" tableWidth="740" styleId="tableTitle" sortable="false">
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
            <logic:iterate id="valoresRelatorioVO" name="relatorioForm" property="wFRelatorioDinamicoVO.valoresRelatorioArray" indexId="idx">
                <vivo:tbRow key="">
                    <logic:iterate id="valoresColunasVO" name="valoresRelatorioVO" property="valorColunaArray" indexId="idx2">
                        <vivo:tbRowColumn key=""><center><bean:write name="valoresColunasVO" property="valor"/></vivo:tbRowColumn>
                    </logic:iterate>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>
    <br>
    <table width="100%">
        <tr>
            <td align="left">
                <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_fechar_over.gif" id="btFechar"  value="Fechar"  onClick="fecharQuadroFlutuante(); return false"/>
            </td>
        </tr>
    </table>
</vivo:quadro>

</form>
   
</acesso:controlHiddenItem>

<script>

    function fecharQuadroFlutuante() {
        parent.fecharQuadroFlutuante();
    }

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

    
</script>
