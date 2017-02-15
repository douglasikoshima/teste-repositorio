<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="Form"                 name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm" type="cliente.DadosComportamentais.conteudo.ConteudoController.ListaConteudoForm"/>
<bean:define id="aAssuntoVO"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.assuntos.assuntoVOArray" />
<bean:define id="aSubAssuntoVO"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.subAssuntos.subAssuntoVOArray" />
<bean:define id="aTipoApresenacaoVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.tiposApresentacao.tipoApresentacaoVOArray" />
<bean:define id="aDisponibilidadeVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.disponibilidades.disponibilidadeVOArray" />
<bean:define id="dadosComportamentais" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.dadosComportamentais" />

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script for="window" event="onload">
            parent.oculta_div();
        </script>
        <script>
            var flag = "";
            var indice;
    
            // Quadro flutuante - Alteração
            function AlterarConteudo(idx) {
                controlaCombos(true);
                dvAlteracao.style.display = '';
                document.forms["frmAlteracao"].target = "ifrmAlteracao";
                document.forms["frmAlteracao"].action = "incluirAlterarConteudo.do?operacao=UPDATE&index=" + idx;
                document.forms["frmAlteracao"].submit();
            }
                
            // Quadro flutuante - Inclusão
            function IncluirConteudo() {
              controlaCombos(true);
              dvInclusao.style.display = '';
              document.forms["frmAlteracao"].subAssuntoSelecionado.value = parent.document.forms[0].subAssuntoSelecionado.value;
              document.forms["frmAlteracao"].assuntoSelecionado.value = parent.document.forms[0].assuntoSelecionado.value;
              document.forms["frmAlteracao"].target = "ifrmInclusao";
              document.forms["frmAlteracao"].action = "incluirAlterarConteudo.do?operacao=INSERT";
              document.forms["frmAlteracao"].submit();
            }
    
            function controlaCombos(estado) {
                parent.document.forms[0].assuntoSelecionado.disabled = estado;
                parent.document.forms[0].subAssuntoSelecionado.disabled = estado;
                if(estado){
                   parent.document.getElementById('botao').style.display = "none";
                }else{
                   parent.document.getElementById('botao').style.display = "block";
                }
    
            }
        </script>  
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    </head>
    <body>
        <vivo:tbTable  selectable="true" onRowClick="" layoutWidth="755" layoutHeight="325" tableWidth="754" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="60" type="number">Seqüência</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="510" type="string">Lista de Conteúdos</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="20" type="string">&nbsp;</vivo:tbHeaderColumn>                                
            </vivo:tbHeader>
                <vivo:tbRows>
                    <logic:iterate name="dadosComportamentais" property="dadoComportamentalArray" id="DadosComportamentaisVO" indexId="ctr" >
                        <vivo:tbRow key="Linha">    
                            <vivo:tbRowColumn><bean:write name="DadosComportamentaisVO" property="sequenciaApresentacao"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="DadosComportamentaisVO" property="conteudoVO.descricao"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" id="botaoIn" onClick="javaScript:AlterarConteudo(<%=ctr%>);" alt="Alterar Conte&uacute;do" /></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
                </vivo:tbRows>
            </vivo:tbTable>
            <form id="frmAlteracao" name="frmAlteracao" method="post">
                <html:hidden name="Form" property="subAssuntoSelecionado" />
                <html:hidden name="Form" property="assuntoSelecionado" />
            </form>
        <vivo:quadroFlutuante id="dvInclusao" idIframe="ifrmInclusao" width="500" height="300" spacesTop="5" spacesLeft="150" display="none" url="<%=request.getContextPath()%>" label="Inclusão de Conteúdo" onclick="controlaCombos(false)"/>
        <vivo:quadroFlutuante id="dvAlteracao" idIframe="ifrmAlteracao" width="500" height="300" spacesTop="5" spacesLeft="150" display="none" url="<%=request.getContextPath()%>" label="Alteração de Conteúdo" onclick="controlaCombos(false)"/>
        <script>   
            controlaCombos(false);
        </script>
        <logic:equal name="Form" property="inMsgRetorno" value="true">
        <logic:equal name="index" value="">
            <script>
                alert('Conteúdo não pôde ser incluído/alterado pois já existe um conteúdo com essa descrição!');
                IncluirConteudo();
                <%
                    Form.setInMsgRetorno(null);
                %>            
            </script>
        </logic:equal> 
        <logic:notEqual name="index" value="">
            <script>
                alert('Conteúdo não pôde ser incluído/alterado pois já existe um conteúdo com essa descrição!');
                AlterarConteudo(<%=request.getParameter("index")%>);
                <%
                Form.setInMsgRetorno(null);
                %>            
            </script>
       </logic:notEqual> 
     </logic:equal>
</body>
</html>