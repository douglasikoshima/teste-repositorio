<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaAssuntoForm" />
<bean:define id="Assuntos"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaAssuntoForm.listaAssuntoVO.assuntos" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

    <netui-template:section name="headerSection">

        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>            
        
        <script language="JavaScript">
            function bemVindo() {
                document.forms[0].action = "<%=request.getContextPath()%>/index.jsp";
                mostrar_div();
                document.forms[0].submit();
            }
            
            // Quadro flutuante - Inclusão
            function IncluirAssunto() {
                dvInclusao.style.display = '';
                document.forms["frmAlteracao"].target = "ifrmInclusao";
                document.forms["frmAlteracao"].action = "incluirAlterarAssunto.do?operacao=INSERT";
                mostrar_div();
                document.forms["frmAlteracao"].submit();
            }
        
            // Quadro flutuante - Alteração
            function AlterarAssunto(idx) {
                dvAlteracao.style.display = '';
                document.forms["frmAlteracao"].target = "ifrmAlteracao";
                document.forms["frmAlteracao"].action = "incluirAlterarAssunto.do?operacao=UPDATE&index=" + idx;
                mostrar_div();
                document.forms["frmAlteracao"].submit();
            }
            
             //Refresh no formulário
            function refresh() {
                document.forms["frmAlteracao"].target = "";
                document.forms["frmAlteracao"].action = "begin.do";
                mostrar_div();
                document.forms["frmAlteracao"].submit();
            }
            
            
        </script>            
        
    </netui-template:section>
    
    <netui-template:section name="bodySection">  
    <acesso:controlHiddenItem nomeIdentificador="cli_assu_verpagina">    

    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <script>
        mostrar_div();
    </script>
    <script for="window" event="onload">
        oculta_div();
    </script>
    <div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div>
    <div style="z-index:999999999;position:absolute;top:0;left:0;"><iframe frameborder="0" width="1" height="1" id="executa" name="executa" src="" style="allow-transparency:true;"></iframe></div>
    <vivo:sessao id="cpAssunto" height="470" width="790" label="Customer Profile" operacoes="Assunto" scroll="no">
    
        <html:form action="incluirAlterarAssunto">
        
        <vivo:tbTable  selectable="true" onRowClick="" layoutWidth="764" layoutHeight="375" tableWidth="764" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>                
                <vivo:tbHeaderColumn align="left" width="95%" type="string">Lista de Assuntos</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>                                
            </vivo:tbHeader>
            <vivo:tbRows>
            
                <logic:iterate name="Assuntos" property="assuntoVOArray" id="AssuntoVO" indexId="ctr" >
                    <vivo:tbRow key="Linha">                        
                        <vivo:tbRowColumn><bean:write  name="AssuntoVO" property="descricao"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <acesso:controlHiddenItem nomeIdentificador="cli_assu_alterar">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" alt="Alterar Assunto" border="0" onclick="AlterarAssunto(<%=ctr%>);"/>
                            </acesso:controlHiddenItem>
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            
            </vivo:tbRows>
        </vivo:tbTable>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        
        <table width="780" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
            <tr>
                <td valign="middle" width="100"><b>&nbsp;Legendas:</b></td>
                <td width="680"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Assunto</td>
            </tr>
        </table>
        
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td><img vspace="6" style="border:none;" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"/></td>
                <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cli_assu_incluir">
                        <img vspace="6" style="border:none;" onClick="IncluirAssunto();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"/>
                    </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        </html:form>                       
    </vivo:sessao>
        <!--Form e Quadro Flutuante-->
        <form id="frmAlteracao" name="frmAlteracao" method="post"></form>
        <vivo:quadroFlutuante id="dvAlteracao"  idIframe="ifrmAlteracao"    width="500" height="135" spacesTop="185" spacesLeft="150" display="none" url="<%=request.getContextPath()%>" label="Alteração de Assunto" onclick="refresh();"/>
        <vivo:quadroFlutuante id="dvInclusao"   idIframe="ifrmInclusao"     width="500" height="135" spacesTop="185" spacesLeft="150" display="none" url="<%=request.getContextPath()%>" label="Inclusão de Assunto" onclick="refresh();"/>
        <script>
            top.frameApp.dvInclusao.style.display = 'none';
            top.frameApp.dvAlteracao.style.display = 'none';
        </script>
</acesso:controlHiddenItem>      
</netui-template:section>
</netui-template:template>