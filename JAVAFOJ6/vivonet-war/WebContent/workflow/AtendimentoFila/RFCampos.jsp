<%--
*** REFACTORING ***
 Date: 29/11/2004
 Author: emocana - osaavedra
*******************
--%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<script type="text/javascript" src="../../resources/scripts/frameweb.js"></script>
<%--
<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="wor_rfcampos_verpagina">
--%>
<script>top.frameCTI.filtro.psqAv=null;</script>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFilaForm"/>
<vivo:quadro id="qdPesquisaWhere" height="125" width="765" label="&nbsp;Condições para Pesquisa Selecionada" scroll="N">
<vivo:tbTable selectable="true" layoutWidth="735" layoutHeight="90" tableWidth="735" styleId="tbListaPesquisa" sortable="true">
 <vivo:tbHeader>
  <vivo:tbHeaderColumn align="left" width="40%" type="string">Campo</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="10%" type="string">Comparação</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="left" width="40%" type="string">Valor</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="10%" type="string">Exclusão</vivo:tbHeaderColumn>
 </vivo:tbHeader>
 <vivo:tbRows>
  <logic:notEmpty name="form" property="atdFilaPesqVO.WFPesquisaAvancadaVO.WFPesquisaAvancadaComparacaoVOArray">
  <script>top.frameCTI.filtro.psqAv=new Array();</script>
  <logic:iterate id="comparacaoVO" name="form" property="atdFilaPesqVO.WFPesquisaAvancadaVO.WFPesquisaAvancadaComparacaoVOArray" indexId="idxComp">
   <vivo:tbRow key="dsCampo">
    <vivo:tbRowColumn><bean:write name="comparacaoVO" property="nmCampo"/></vivo:tbRowColumn>
    <vivo:tbRowColumn><bean:write name="comparacaoVO" property="dsComparacao"/></vivo:tbRowColumn>
    <vivo:tbRowColumn><bean:write name="comparacaoVO" property="valor"/></vivo:tbRowColumn>
    <vivo:tbRowColumn><center><a href="/FrontOfficeWeb/workflow/AtendimentoFila/excluirItem.do" style="cursor:pointer;border:0px;" onclick="deleteFiltroItem('<%=idxComp%>'); return false;"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" style="cursor:pointer;border:0px;"/></a></center></vivo:tbRowColumn>
   </vivo:tbRow>
   <script>
    psqAv = new Object();
    psqAv.idCampo='<bean:write name="comparacaoVO" property="idCampo"/>';
    psqAv.nmCampo='<bean:write name="comparacaoVO" property="nmCampo"/>';
    psqAv.tpComparacao='<bean:write name="comparacaoVO" property="tpComparacao"/>';
    psqAv.dsComparacao='<bean:write name="comparacaoVO" property="dsComparacao"/>';
    psqAv.valor='<bean:write name="comparacaoVO" property="valor"/>';
    psqAv.idFormularioCampoValor='<bean:write name="comparacaoVO" property="idFormularioCampoValor"/>';
    top.frameCTI.filtro.psqAv[<%=idxComp%>]=psqAv;
   </script>
  </logic:iterate>
  </logic:notEmpty>
 </vivo:tbRows>
</vivo:tbTable>
</vivo:quadro>
<%-- TODO: Poner control de acceso
</acesso:controlHiddenItem>
--%>
<script>parent.carregaCampos();</script>