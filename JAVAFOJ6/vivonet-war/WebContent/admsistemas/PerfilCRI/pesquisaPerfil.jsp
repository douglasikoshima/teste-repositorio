<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="perfilForm" type="admsistemas.PerfilCRI.PerfilCRIController.PerfilForm"/>
<bean:define id="PerfilVariaveis" name="Form" property="perfilVariaveisVO"/>

    <html>
    <head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="Javascript">

        function removeItem(id) {
            
            //Incia animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.startAnimation();
            }
            
            document.forms[0]['idPerfil'].value=id;
            document.forms[0].target='';
            document.forms[0].action='excluiPerfil.do';
            document.forms[0].submit();
        }        
        
        function pesquisar(offset){
        
            //Incia animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.startAnimation();
            }
            var Bloco = Number(document.forms[0].bloco.value);
            var resultado = Bloco+offset;
            document.forms[0].bloco.value = resultado;
            document.forms[0].target='';
            document.forms[0].action='pesquisaPerfil.do';
            document.forms[0].submit();
        }  
        
        function editarPerfil(id) {
        
            parent.divPesqPerfil.style.display = 'none';
            
            //Incia animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.startAnimation();
            }
            
            document.forms[0]['idPerfil'].value=id;
            document.forms[0].target='frameEscondido';
            document.forms[0].action='carregaPerfil.do';
            document.forms[0].submit();
        }      
             
        function DisponibilizaTela() {
            //Incia animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.stopAnimation();
            }
        }
              
        var idRetorno = '<bean:write name="Form" property="idRetorno"/>';
        //alert('-'+idRetorno+'-');
        if(idRetorno=='S'){
            alert('<bean:write name="Form" property="mensagem"/>');

            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.stopAnimation();
            }
        }        
    </script>
    </head>
    <!--acesso:controlHiddenItem nomeIdentificador="usu_pg_verpagina"-->  
    <body onload="DisponibilizaTela();">    
        <form action="carregaPerfil.do" name="perfilForm" onSubmit="return false;" method="post">
            <html:hidden property="idPerfil" name="Form"/>
            <html:hidden property="nmPerfil" name="Form"/>
            <html:hidden property="bloco" name="Form"/>
            <table border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                <tr>
                    <td align="center">
                        <table border="0" cellspacing="1" cellpadding="1" align="center">
                            <tr>
                                <td width="100%" align="center"></td>
                            </tr>
                            <tr>
                                <td width="100%" align="left">
                                <logic:greaterThan name="Form" property="arrayLength" value="0">
                                    <vivo:tbTable selectable="true" layoutWidth="745" layoutHeight="300" tableWidth="745" styleId="tableTitle" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn align="left"   width="" type="string">Perfis encontrados</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="" type="string">Habilitado</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="40" type="string">Associado</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn align="center" width="20" type="string"></vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <vivo:tbRows>
                                            <logic:iterate name="PerfilVariaveis" property="perfilArray" id="perfil">
                                                <vivo:tbRow key="linha">
                                                    <vivo:tbRowColumn>
                                                        <vivo:hint maxLength="80"> <bean:write name="perfil" property="nmPerfil"/></vivo:hint>
                                                    </vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>
                                                        <logic:equal value="0" name="perfil" property="habilitado">
                                                            Não
                                                        </logic:equal>
                                                        <logic:equal value="1" name="perfil" property="habilitado">
                                                            Sim
                                                        </logic:equal>
                                                    </vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>
                                                        <logic:equal value="0" name="perfil" property="associado">
                                                            Não
                                                        </logic:equal>
                                                        <logic:equal value="1" name="perfil" property="associado">
                                                            Sim
                                                        </logic:equal>
                                                    </vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>
                                                        <!--acesso:controlHiddenItem nomeIdentificador="usu_pg_editargrupo"-->
                                                        <a href="Javascript:editarPerfil('<bean:write name="perfil" property="idPerfil" />');">
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" alt="Editar Grupo"></a>
                                                        <!--/acesso:controlHiddenItem-->
                                                    </vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>
                                                        <!--acesso:controlHiddenItem nomeIdentificador="usu_pg_excluirgrupo"-->
                                                        <a href="#" onclick="removeItem('<bean:write name="perfil" property="idPerfil" />'); return false">
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Perfil"></a>
                                                        <!--/acesso:controlHiddenItem-->
                                                    </vivo:tbRowColumn>
                                                </vivo:tbRow>
                                            </logic:iterate>
                                        </vivo:tbRows>
                                    </vivo:tbTable>
                                    </logic:greaterThan>
                                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                                    <table width="100%">
                                        <tr>
                                            <td align="center">
                                            <% 
                                            if (Form.getBloco()==null || Form.getBloco().equals(ConstantesCRM.SZERO)) {
                                            %>
                                            <% } else { %>
                                            <img onClick="pesquisar(-1); return false;" id="btAnterior" src="/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_over.gif'" style="border:nonecursor:hand;"/>
                                            <% } %>   
                                            <% if (Form.getInFim() == null || Form.getInFim().equals(ConstantesCRM.SONE)) {%>
                                            <% } else { %>
                                            <img onClick="pesquisar(1); return false;" id="btProximo" src="/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_over.gif'" style="border:nonecursor:hand;"/>
                                            <% } %>
                                            </td>
                                        </tr>
                                    </table>
                                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                                    <table width="755" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                                        <tr>
                                            <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                                            <td>
                                                <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
                                            </td>
                                            <td>
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Editar Perfil
                                            </td>
                                            <td>
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="absmiddle"> &nbsp;Remover Perfil
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>  
    </body>
</html>   
