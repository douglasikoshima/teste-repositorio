<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<bean:define id="Form"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm" type="cliente.DadosComportamentais.valorPossivel.ValorPossivelController.ListaValorPossivelForm"/>
<bean:define id="aAssuntoVO"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.assuntos.assuntoVOArray" />
<bean:define id="aSubAssuntoVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.subAssuntos.subAssuntoVOArray" />
<bean:define id="aConteudoVO"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.conteudos.conteudoVOArray" />
<bean:define id="valoresPossiveis" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.valoresPossiveis" />
<html>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
	<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
	<script for="window" event="onload">
		parent.oculta_div();
	</script>
	<script language="javaScript">
		var flag = "";
		var indice;

		// Quadro flutuante - Alteração
        function AlterarValorPossivel(idx) {                                
	        controlaCombos(true);
	        dvAlteracao.style.display = '';
	        document.forms["frmAlteracao"].target = "ifrmAlteracao";
	        document.forms["frmAlteracao"].action = "incluirAlterarValorPossivel.do?operacao=UPDATE&index=" + idx;
	        parent.mostrar_div();
	        document.forms["frmAlteracao"].submit();
		}
            
        function IncluirValorPossivel() {
	        controlaCombos(true);
	        dvInclusao.style.display = '';
	        document.forms["frmAlteracao"].target = "ifrmInclusao";
	        document.forms["frmAlteracao"].action = "incluirAlterarValorPossivel.do?operacao=INSERT&conteudo="+parent.document.getElementById("conteudo").value;
	        parent.mostrar_div();
	        document.forms["frmAlteracao"].submit();
        }
            
            function controlaCombos(estado) {
                parent.document.forms[0].assuntoSelecionado.disabled = estado;
                parent.document.forms[0].subAssuntoSelecionado.disabled = estado;
                parent.document.forms[0].conteudoSelecionado.disabled = estado;
                if(estado){
                   parent.document.getElementById('botao').style.display = "none";
                }else{
                   parent.document.getElementById('botao').style.display = "block";
                }

            }

     </script>
     <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>              
     <vivo:tbTable  selectable="true" onRowClick="" layoutWidth="760" layoutHeight="300" tableWidth="759" styleId="tableTitle" sortable="true">
         <vivo:tbHeader>
             <vivo:tbHeaderColumn align="left" width="70" type="number">Seqüência</vivo:tbHeaderColumn>
             <vivo:tbHeaderColumn align="left" width="510" type="string">Lista de Valores Possíveis</vivo:tbHeaderColumn>
             <vivo:tbHeaderColumn align="center" width="20" type="string">&nbsp;</vivo:tbHeaderColumn>                                
         </vivo:tbHeader>
         <vivo:tbRows>
         
             <logic:iterate name="valoresPossiveis" property="valorPossivelVOArray" id="ValorPossivelVO" indexId="ctr" >
                 <vivo:tbRow key="Linha">
                     <vivo:tbRowColumn><bean:write name="ValorPossivelVO" property="sequenciaApresentacao"/></vivo:tbRowColumn>
                     <vivo:tbRowColumn><bean:write name="ValorPossivelVO" property="descricao"/></vivo:tbRowColumn>
                     <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" onClick="AlterarValorPossivel(<%=ctr%>);" /></vivo:tbRowColumn>
                 </vivo:tbRow>
             </logic:iterate>
         </vivo:tbRows>
      </vivo:tbTable>
      <form id="frmAlteracao" name="frmAlteracao" method="post"></form>
      <vivo:quadroFlutuante id="dvAlteracao"  idIframe="ifrmAlteracao"    width="500" height="200" spacesTop="55" spacesLeft="150" display="none" url="<%=request.getContextPath()%>" label="Alterar Valor Poss&iacute;vel" onclick="controlaCombos(false)"/>
      <vivo:quadroFlutuante id="dvInclusao"   idIframe="ifrmInclusao"     width="500" height="200" spacesTop="55" spacesLeft="150" display="none" url="<%=request.getContextPath()%>" label="Incluir Valor Poss&iacute;vel" onclick="controlaCombos(false)"/>
      <logic:equal name="Form" property="inMsgRetorno" value="true">
      <logic:equal name="index" value="">
      <script>
          alert('Valor possível não pôde ser incluído/alterado pois já existe um valor possível com essa descrição!');
          IncluirValorPossivel();
           <%
               Form.setInMsgRetorno(null);
           %>            
      </script>
      </logic:equal> 
      <logic:notEqual name="index" value="">
      <script>
          alert('Valor possível não pôde ser incluído/alterado pois já existe um valor possível com essa descrição!');
             AlterarValorPossivel(<%=request.getParameter("index")%>);
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