<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="cliente.DadosComportamentais.subAssunto.SubAssuntoController.ListaSubAssuntoForm"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaSubAssuntoForm" type="cliente.DadosComportamentais.subAssunto.SubAssuntoController.ListaSubAssuntoForm"/>
<bean:define id="aAssuntoVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaSubAssuntoForm.listaSubAssuntoVO.assuntos.assuntoVOArray" />
<bean:define id="SubAssuntos"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaSubAssuntoForm.listaSubAssuntoVO.subAssuntos" />

<html>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
	<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
	<script for="window" event="onload">
        parent.oculta_div();
	</script>
	<script>    
		var flag = "";
		var indice;

		// Quadro flutuante - Alteração
		function AlterarSubassunto(idx) {
			indice =idx;    
			dvAlteracao.style.display = '';
			controlaCombos(true);
			document.forms["frmAlteracao"].target = "ifrmAlteracao";
			document.forms["frmAlteracao"].action = "incluirAlterarSubAssunto.do?operacao=UPDATE&index=" + idx;
			parent.mostrar_div();
			
			document.forms["frmAlteracao"].submit();
		}           

		function controlaCombos(estado) {
			parent.document.forms[0].assuntoSelecionado.disabled = estado;
			if(estado){
				parent.document.getElementById('botao').style.display = "none";
			}else{
				parent.document.getElementById('botao').style.display = "block";
			}
		}

		// Quadro flutuante - Inclusão
		function IncluirSubassunto() {
			dvInclusao.style.display = '';
			controlaCombos(true);
			document.forms["frmAlteracao"].target = "ifrmInclusao";
			document.forms["frmAlteracao"].action = "incluirAlterarSubAssunto.do?operacao=INSERT";
			parent.mostrar_div();
			document.forms["frmAlteracao"].submit();
		}
	</script>      

    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>              
       <vivo:tbTable selectable="true" onRowClick="" layoutWidth="760" layoutHeight="325" tableWidth="759" styleId="tableTitle" sortable="true">
           <vivo:tbHeader>
               <vivo:tbHeaderColumn align="left" width="10%" type="number">Seqüência</vivo:tbHeaderColumn>
               <vivo:tbHeaderColumn align="left" width="80%" type="string">Lista de Subassuntos</vivo:tbHeaderColumn>                    
               <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>                                
           </vivo:tbHeader>
           <vivo:tbRows>
               
               <logic:iterate name="SubAssuntos" property="subAssuntoVOArray" id="loopsubAssunto" indexId="ctr" >
                   <vivo:tbRow key="Linha">
                       <vivo:tbRowColumn><bean:write name="loopsubAssunto" property="sequenciaApresentacao"/></vivo:tbRowColumn>
                       <vivo:tbRowColumn><bean:write name="loopsubAssunto" property="descricao"/></vivo:tbRowColumn>
                       <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" onClick="AlterarSubassunto(<%=ctr%>);return false;"/></vivo:tbRowColumn>
                   </vivo:tbRow>
               </logic:iterate>
           
           </vivo:tbRows>
       </vivo:tbTable>
            
                    <form id="frmAlteracao" name="frmAlteracao" method="post" />
                    <input type="hidden"  name="control" value="">
        <vivo:quadroFlutuante id="dvAlteracao"  idIframe="ifrmAlteracao"    width="500" height="135" spacesTop="85" spacesLeft="150" display="none" url="<%=request.getContextPath()%>" label="Alteração de Subassunto" onclick="controlaCombos(false)"/>
        
        <vivo:quadroFlutuante id="dvInclusao"   idIframe="ifrmInclusao"     width="500" height="135" spacesTop="85" spacesLeft="150" display="none" url="<%=request.getContextPath()%>" label="Inclusão de Subassunto" onclick="controlaCombos(false)"/>
       <script>
           controlaCombos(false);
       </script>
  
  
  <logic:equal name="Form" property="inMsgRetorno" value="true">
    <logic:equal name="index" value="">
        <script>
            alert('Subassunto não pôde ser incluído/alterado pois já existe um Subassunto com essa descrição ou uma seqüencia de contato com este valor!');
            IncluirSubassunto();
            <%
                Form.setInMsgRetorno(null);
            %>            
        </script>
       </logic:equal> 
       <logic:notEqual name="index" value="">
        <script>
            alert('Subassunto não pôde ser incluído/alterado pois já existe um Subassunto com essa descrição ou uma seqüencia de contato com este valor!');
            AlterarSubassunto(<%=request.getParameter("index")%>);
            <%
                Form.setInMsgRetorno(null);
            %>            
             
        </script>
       </logic:notEqual> 
    </logic:equal>
      <script>   
        controlaCombos(false);
     </script>  

</html>