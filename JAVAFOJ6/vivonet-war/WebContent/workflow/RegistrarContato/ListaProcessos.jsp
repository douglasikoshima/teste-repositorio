<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<acesso:controlInitEnv/>

<bean:define id="Form" name="registrarContatoForm" scope="request"/>
<bean:define id="atendimentoVO" name="Form" property="atendimentoVOLista" />
<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>

    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_lp_verpagina">
        <html:form action="/workflow/RegistrarContato/excluirProcessoCorrente.do" method="post">
            <html:hidden name="Form" property="rowIndex" />
            <html:hidden name="Form" property="idAtendimentoExclusao"/>
        </html:form>
        <vivo:tbTable selectable="true" onRowClick="detalhe( this );" layoutWidth="400" layoutHeight="294" tableWidth="400" styleId="dvListaProcessos" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="20%" type="string">Exclusão</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="20%" type="number">Atendimento</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="60%" type="string">Contato</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
            	<c:catch var="errItatendimentoVOArray">
                <logic:iterate name="atendimentoVO" property="atendimentosCorrentes.atendimentoVOArray" id="correnteAtendimento">
                    <%String textoContato = ((AtendimentoVO)correnteAtendimento).getArvoreAtendimentoVO().getDescricaoCompleta().replaceAll("/"," / ");%>
                    <vivo:tbRow key="linha1">
                        <vivo:tbRowColumn>
                            <acesso:controlHiddenItem nomeIdentificador="wor_lp_excluir">
                                <center>
                                    <img src="<%=request.getContextPath()%>/resources/images/bt_icon_excluir.gif" onclick='<%--excluir(<bean:write name="correnteAtendimento" property="idAtendimento" format="###"/>,this);--%>'/>
                                </center>
                                </acesso:controlHiddenItem></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="correnteAtendimento" property="nrProtocolo" format="###"/><%--<bean:write name="correnteAtendimento" property="idAtendimento" format="###"/>--%></vivo:tbRowColumn>
                        <vivo:tbRowColumn><vivo:hint maxLength="25" spaces="S"><%=textoContato%></vivo:hint></vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            	</c:catch>
            </vivo:tbRows>
        </vivo:tbTable>

        <script language=javascript>
	        //controle do click
	        var detalheClick = true;
	
	        function excluir(idAtendimento, imgTD) {
	            <%--
	            //Não permite click na grade
	            detalheClick = false;
	            aTR = document.all['dvListaProcessos_body'].getElementsByTagName("TR")
	            //Obtém a linha TR da tabela
	            idAtendimentoExclusao = idAtendimento;
	            rowIndex = imgTD.parentElement.parentElement.parentElement.parentElement.rowIndex;
	            if(rowIndex==null){
	                rowIndex = imgTD.parentElement.parentElement.parentElement.rowIndex;
	            }
	            if(aTR[1] == null){
	                alert("Quando há apenas um processo, ele não pode ser excluído!");
	                return false;
	            }
	            //alert(imgTD.parentElement.parentElement.childNodes(1).innerText);
	            if(!(confirm("Deseja excluir esse processo: " + idAtendimentoExclusao + " ?")))
	                return false;
	
	            //Não permite click na grade
	            detalheClick = false;
	
	            //Monta os valores no form
	            //parent.document.forms["registrarContatoForm"].elements["{actionForm.idAtendimentoExclusao}"].value = idAtendimentoExclusao;
	            //parent.document.forms["registrarContatoForm"].elements["{actionForm.rowIndex}"].value = rowIndex;
	
	            //Monta os valores no form
	            parent.document.forms["registrarContatoForm"].elements["idAtendimentoExclusao"].value = idAtendimentoExclusao;
	            parent.document.forms["registrarContatoForm"].elements["rowIndex"].value = rowIndex;
	
	            //Liga animação
	            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
	            //Processa a exclusão
	            parent.document.forms["registrarContatoForm"].target = "ifrmRegistrarContato";
	            parent.document.forms["registrarContatoForm"].action = "excluirProcessoCorrente.do";
	            parent.document.forms["registrarContatoForm"].submit();
	            --%>
	        }

	        function detalhe(imgTD) {
	            //Analisa click na grade
	            if( ! detalheClick ) {
	                detalheClick = true;
	                return;
	            }
	            //Exibe o quadro flutuante
	            //parent.parent.parent.dvAtendimento.style.display = '';
	            //Processa o detalhe
	            //document.forms["registrarContatoForm"].target = "ifrmAtendimento";
	            //document.forms["registrarContatoForm"].action = "/FrontOfficeWeb/workflow/AtendimentoDetalhe/begin.do?IDATENDIMENTO="+imgTD.childNodes(1).innerText;
	            //document.forms["registrarContatoForm"].submit();
	        }

	        parent.parent.redimensionaProcessosCorrentes();
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>

        </acesso:controlHiddenItem>
    </body>
</html>
