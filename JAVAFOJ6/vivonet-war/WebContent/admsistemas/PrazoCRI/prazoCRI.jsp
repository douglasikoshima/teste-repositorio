<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="prazoCRIForm" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> prazo CRI"/>
    <netui-template:setAttribute name="modulo" value="Administração Sistemas"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">        
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="JavaScript"  src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script>
            function limpar(){
                document.forms[0]['mensagem'].value='';
            }
            
            function incluir(){
                if(document.forms[0]['mensagem'].value==''){
                    alert('Quantidade dias obrigatório!');
                    return false;
                }
                //Incia animação
                if( top.frameApp.dvAnimarAguarde != null ){
                    top.frameApp.startAnimation();
                }
                
                document.forms[0].target='frameEscondido';
                document.forms[0].action='gravaPrazo.do';
                document.forms[0].submit();        
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection"> 
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <body>
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
            <vivo:sessao id="qdMain" height="460" width="790" label="CRI" operacoes="Prazo CRI" scroll="no">
                <form name="formPrazoCRI" id="formPrazoCRI">
                    <html:hidden property="idRetorno" name="form"/>
                    <table border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray">
                        <tr>
                            <td>
                                Qtd. Dias
                            </td>
                            <td>
                                <html:text property="mensagem" name="form" onkeyup ="Formatar(this.value, this.form.name, this.name, 'numero');" onchange="Formatar(this.value, this.form.name, this.name, 'numero');"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img  style="cursor:hand;border:none;" id="btIncluir" onClick="incluir();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'"/>
                            </td>
                            <td>
                                <img style="cursor:hand;border:none" id="btLimpar" onClick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                            </td>
                        </tr>
                    </table>
                </form>  
            </vivo:sessao>  
            <iframe scrolling="yes" style="visibility:hidden;" name="frameEscondido" id="frameEscondido" height="1px" width="1px"></iframe>
        </body>
    </netui-template:section>
</netui-template:template>