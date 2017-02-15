<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        parent.oculta_div();
    -->
</SCRIPT>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <script>
            function validaPesquisa() {
                if((trim(document.getElementById('con').value) == '') && (trim(document.getElementById('lin').value) == '')){
                    alert('Conta ou Linha Obrigatórios!');
                    return false;
                }else{
                    document.forms[0].action="filtroAbaServico.do";
                    parent.mostrar_div();
                    document.getElementById('lin').value = limpaInteiro(document.getElementById('lin').value);
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            }
        </script>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
    <acesso:controlHiddenItem nomeIdentificador="cli_as_verpagina">
         <bean:define id="Servicos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="servicos.historicoAtendimentoItemArray"/>
         <bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="abaServ" />
    
        <script>
            if ("<bean:write name="Form" property="filtroServicos.mensagem" />" != "") {
                alert('<bean:write name="Form" property="filtroServicos.mensagem" />');
            }
        </script>
         <html:form action="/cliente/TelaInicial/filtroAbaServico.do">  
            <table border="0" cellspacing="0" cellpadding="0">                    
                <tr>
                    <td width="230" style="font-weight:bold;text-indent:5px;">Conta</td>
                    <td width="210" style="font-weight:bold;text-indent:5px;">Linha</td>
                    <td width="450"></td>
                </tr>
                <tr>
                    <td>
                        <html:text name="Form" property="filtroServicos.nrConta" styleId="con" size="30" onkeydown="checaInteiro(this)" onkeyup="checaInteiro(this)"/>
                    </td>
                    <td>
                        <html:text name="Form" property="filtroServicos.nrLinha" styleId="lin" size="30" onkeyup="maskPhoneNumberObj(this)" maxlength="14"/> 
                    </td>
                    <td>
                        <acesso:controlHiddenItem nomeIdentificador="cli_as_pesquisar">
                        <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:hand;" onClick="validaPesquisa(); return false;" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'"/>
                        </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            <table width="500" border="0" cellpadding="0" bgcolor="white"cellspacing="1">
                <tr>
                    <td>            
                        <vivo:tbTable selectable="true" layoutWidth="750" layoutHeight="300" tableWidth="750" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="15%" type="string">Data</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="75%" type="string">Descrição</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="10%" type="string">Alta Prioridade (S/N)</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="Serv" name="Servicos">
                                    <vivo:tbRow key="idConta1">
                                         <vivo:tbRowColumn><bean:write name="Serv" property="data" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="Serv" property="descricao" /></vivo:tbRowColumn>
                                         <vivo:tbRowColumn><bean:write name="Serv" property="inPrioridade" /></vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </td>     
                </tr>
            </table>
        </html:form>        
    </acesso:controlHiddenItem>
    </body>
</html>