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


<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaSubAssuntoForm" />
<bean:define id="aAssuntoVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaSubAssuntoForm.listaSubAssuntoVO.assuntos.assuntoVOArray" />
<bean:define id="SubAssuntos"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaSubAssuntoForm.listaSubAssuntoVO.subAssuntos" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>            

        <script language="javaScript">
            function bemVindo() {
                document.forms[0].action = "<%=request.getContextPath()%>/index.jsp";
                mostrar_div();
                document.forms[0].submit();
            }
            
            function atualizar(){
                document.forms[0].target = "frmResultadoPesquisa";
                document.forms[0].action="atualizar.do";
                mostrar_div();
                document.forms[0].submit();
            }
            
            function Pesquisar() {
                
                if (document.forms[0].assuntoSelecionado.value == "0"){
                    alert("Por favor, selecione um Assunto");
                } else {
                    mostrar_div();
                    document.forms[0].target = "frmResultadoPesquisa";
                    document.forms[0].action="pesquisarSubAssunto.do";
                    document.forms[0].submit();
                }
            }
            
            // Quadro flutuante - Inclusão
            function IncluirSubassunto() {
                if (document.forms[0].assuntoSelecionado.value == "0"){
                    alert("Por favor, selecione um Assunto");
                } else {
	              frmResultadoPesquisa.IncluirSubassunto();
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
    <acesso:controlHiddenItem nomeIdentificador="cli_sa_verpagina">       
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <script>
        mostrar_div();
    </script>
    <script for="window" event="onload">
        oculta_div();
    </script>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <div style="z-index:999999999;position:absolute;top:0;left:0;"><iframe frameborder="0" width="1" height="1" id="executa" name="executa" src="" style="allow-transparency:true;"></iframe></div>
    <vivo:sessao id="qdMain" height="475" width="780" label="Customer Profile" operacoes="Subassunto" scroll="N">
        <form action="pesquisarSubAssunto.do" method="post" name="listaSubAssuntoForm">
        
            <table height="20" cellspacing="0" cellpadding="4">
                <tr>
                    <td width="50">Assunto:</td>
                    <td>
                        <html:select name="Form" property="assuntoSelecionado" title="assuntoSelecionado" styleClass="SELECT" style="width:300px" onchange="atualizar()">
                            <html:option value="0">-- Selecione --</html:option>
                            <html:options collection="aAssuntoVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                    <td align="left">
                        <acesso:controlHiddenItem nomeIdentificador="cli_sa_pesquisar">
                          <div id="botao"> 
                            <img style="border:0px;" onClick="Pesquisar(); return false" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/>&nbsp;
                          </div>  
                        </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
                <iframe src="resultadoPesquisaSubAssunto.jsp" name="frmResultadoPesquisa" id="ifrResultadoPesquisa" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" width="780" height="355"></iframe>            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        
            <table width="780" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                <tr>
                    <td valign="middle" width="100"><b>&nbsp;Legendas:</b></td>
                    <td width="680"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Alterar Subassunto</td>
                </tr>
            </table>
            
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td><img vspace="6" style="border:none;" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"/></td>
                    <td align="right"><acesso:controlHiddenItem nomeIdentificador="cli_sa_incluir"><img vspace="6" style="border:none;" onClick="IncluirSubassunto(); return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_over.gif"/></acesso:controlHiddenItem></td>
                </tr>
            </table>
        </form>
        
        </vivo:sessao>
        
        
        
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
