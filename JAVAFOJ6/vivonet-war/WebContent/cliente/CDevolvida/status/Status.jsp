<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>

<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="statusForm"/>
<bean:define id="Status" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="statusForm.listaStatusCorrespVO"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">   
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>              
         
         <script language="JavaScript">
            // Quadro flutuante - Inclusão
            function IncluirStatus() {
                dvInclusao.style.display = '';
                document.forms["frmAlteracao"].target = "ifrmInclusao";
                document.forms["frmAlteracao"].action = "incluialterastatuscorresp.do?operacao=INSERT";
                mostrar_div();
                document.forms["frmAlteracao"].submit();
            }
        
            // Quadro flutuante - Alteração
            function AlterarStatus(idx) {
                dvAlteracao.style.display = '';
                document.forms["frmAlteracao"].target = "ifrmAlteracao";
                document.forms["frmAlteracao"].action = "incluialterastatuscorresp.do?operacao=UPDATE&index=" + idx;
                mostrar_div();
                document.forms["frmAlteracao"].submit();
            }
            
           // Exclusão
            function ExcluirStatus(idx) {                  
                if(confirm("Deseja realmente Excluir esse registro?")){
                    document.forms["frmAlteracao"].action = "excluirstatuscorresp.do?codigo=" + idx;
                    mostrar_div();
                    document.forms["frmAlteracao"].submit();
                }
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
    <acesso:controlHiddenItem nomeIdentificador="cli_sta_verpagina">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <script>
        mostrar_div();
    </script>
    <script for="window" event="onload">
        oculta_div();
    </script>
    <logic:equal name="Form" property="inMsgRetorno" value="naoExclui">
        <script>
            alert('Status de correspondência não pôde ser excluído. É necessário primeiramente remover todas as dependencias desse status!');
        </script>
    </logic:equal>
    
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <div style="z-index:999999999;position:absolute;top:0;left:0;"><iframe frameborder="0" width="1" height="1" id="executa" name="executa" src="" style="allow-transparency:true;"></iframe></div>
    <vivo:sessao id="qdMain" height="470" width="790" label="Correspondência Devolvida" operacoes="Status" scroll="N">
    
        <form action="incluialterastatuscorresp" method="post">
    
        <vivo:tbTable  selectable="true" onRowClick="" layoutWidth="764" layoutHeight="370" tableWidth="764" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="15%" type="string">Sigla</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="80%" type="string">Lista de Status</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>                        
                <logic:iterate name="Status" property="statusCorrespVOArray" id="ListaStatusCorrespVO" indexId="ctr" >
                    <vivo:tbRow key="Linha">
                        <vivo:tbRowColumn><bean:write name="ListaStatusCorrespVO" property="sigla"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="ListaStatusCorrespVO" property="descricao"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn>
							<acesso:controlHiddenItem nomeIdentificador="cli_sta_alterar">
								<img alt="Alterar"
								     src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif"
								     border="0"
									 onClick="AlterarStatus('<%=ctr%>')" />
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
                <td width="300"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Alterar Status de Correspondência Devolvida</td>                
            </tr>
        </table>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        
        <table width="780">
            <tr>
                <td align="left">
					<img style="border:0px;"
					     onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false"
						 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" />
				</td>
                <td align="right"><acesso:controlHiddenItem nomeIdentificador="cli_sta_incluir"><img style="border:0px;" onClick="IncluirStatus(); return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"/></acesso:controlHiddenItem></td>
            </tr>
        </table>
        
    </form>
    </vivo:sessao>
      
     <!--Form e Quadro Flutuante-->
        <form id="frmAlteracao" name="frmAlteracao" method="post"></form>
       	<vivo:quadroFlutuante id="dvInclusao" idIframe="ifrmInclusao" width="500" height="270" spacesTop="185" spacesLeft="150" display="none" url="<%=request.getContextPath()%>" label="Inclusão de Status de Correspondência Devolvida"  onclick="refresh();"/>
       	<vivo:quadroFlutuante id="dvAlteracao" idIframe="ifrmAlteracao" width="500" height="270" spacesTop="185" spacesLeft="150" display="none" url="<%=request.getContextPath()%>" label="Alteração de Status de Correspondência Devolvida"  onclick="refresh();"/>
        <script>
            dvInclusao.style.display = 'none';
            dvAlteracao.style.display = 'none';
        </script>        
</acesso:controlHiddenItem>
</netui-template:section>
</netui-template:template>