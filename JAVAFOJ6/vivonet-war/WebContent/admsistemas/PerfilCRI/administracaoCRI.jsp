<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="perfilForm" type="admsistemas.PerfilCRI.PerfilCRIController.PerfilForm"/>
<bean:define id="PerfilVariaveis" name="Form" property="perfilVariaveisVO"/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> Administração CRI"/>
    <netui-template:setAttribute name="modulo" value="Administração Sistemas"/>
    <netui-template:section name="headerSection">
	    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
	    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
	    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
	    <script language="Javascript">        
	        var selecionaAbaContato = true;
	        
	        function limpar(){
	            document.forms[0].target='';
	            document.forms[0].action='limpaPesquisa.do';
	            document.forms[0].submit();
	        }
	        
	        function pesquisar(){
	            //Incia animação
	            if( top.frameApp.dvAnimarAguarde != null ){
	                top.frameApp.startAnimation();
	            }
	            //divPesqPerfil.style.display = '';
	            document.forms[0].target='';
	            document.forms[0].action='pesquisaPerfil.do';
	            document.forms[0].submit();
	        }      
	        
	        function pesquisarPagina(offset){
	        
	            //Incia animação
	            if( top.frameApp.dvAnimarAguarde != null ){
	                top.frameApp.startAnimation();
	            }
	            var Bloco = parseInt(document.forms[0].bloco.value);
	            if(isNaN(Bloco)){          
	                Bloco = 0;
	            }
	            var resultado = Bloco+offset;
	            document.forms[0].bloco.value = resultado;
	            document.forms[0].target='';
	            document.forms[0].action='pesquisaPerfil.do?bloco='+resultado;
	            document.forms[0].submit();
	        }  
	        
	        function incluiPerfil()
	            document.forms[0].target='frmIncluiPerfil';
	            document.getElementById('divIncluiPerfil').style.display = '';
	            frmIncluiPerfil.src = 'incluirAlteraPerfil.jsp';
	            mostrar_div();
	            document.forms[0].action='carregaInlcui.do';
	            document.forms[0].submit();
	        }
	
	        function alteraPerfil(id){
	            document.forms[0].idPerfil.value = id;
	            document.forms[0].target='frmAlteraPerfil';
	            document.getElementById('divAlteraPerfil').style.display = '';
	            frmIncluiPerfil.src = 'incluirAlteraPerfil.jsp';
	            mostrar_div();
	            document.forms[0].action='carregaAltera.do';
	            document.forms[0].submit();
	        }
	        
	        function removeItem(id) {
	            document.forms[0].idPerfil.value = id;       
	            document.forms[0].target = '';
	            document.forms[0].action = "excluiPerfil.do";
	            if (window.confirm("Confirma exclusão do Item?")) {
	                mostrar_div();
	                document.forms[0].submit(); 
	            }else{
	                return false;
	            }
	        }
	        
	        function editaRelPerfil(id){
	            document.forms[0].idPerfil.value = id;
	            document.forms[0].target = '';
	            document.forms[0].action = 'editaRelPerfil.do';
	            document.forms[0].submit();
	        }
	        
	        function statusPerfil(id,status) {
	            document.forms[0].idPerfil.value = id;
	            document.forms[0].inAcao.value = status;
	            document.forms[0].target = '';
	            document.forms[0].action = 'statusPerfil.do';
	            document.forms[0].submit();
	        }
	    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">            
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
		<!--acesso:controlHiddenItem nomeIdentificador="adm_aret_verpagina"--> 
		<div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <vivo:sessao id="qdMain" height="470" width="790" label="CRI" operacoes="Manutenção Perfil CRI" scroll="no">
        <!--netui : form action="pesquisaPerfil" name="formPerfil" -->
        <form name="formPerfil">
            
            <html:hidden property="bloco" name="Form" />
            <html:hidden property="inAcao" name="Form" value="1"/>
            <html:hidden property="idPerfil" name="Form"/>
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        
                <table width="760" height="435" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                    <tr valign="top">
                        <td align="center">
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                            <table width="740" border="0" cellspacing="5" cellpadding="0" align="center" class="tbl_bgBlue">
                            <tr>
                                <td style="text-indent:6px;" align="right">
                                    <b>Perfil:</b>
                                </td>
                                <td class="tblEstatica_campoNome" align="left">
                                    <html:text name="Form" property="nmPerfil" size="100"/>
                                </td>
                                <td align="left" valign="bottom">
                                    <div id="divLimpar">
                                    <img style="cursor:hand;border:none" id="btLimpar" onClick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                    </div>
                                </td>   
                                <td align="left" valign="bottom">
                                    <div id="divPesquisar">
                                    <img style="cursor:hand;border:none" id="btPesquisar" onClick="pesquisar();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />
                                    </div>
                                </td>  
                                <!--td align="left" valign="bottom">
                                    <div id="divAlterar" style="display:none;">
                                    < netui : image  style="cursor:hand;border:none;" id="btAlterar" onClick="incluir();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'"/>
                                    </div>
                                    <div id="divIncluir" style="display:none;">
                                    < netui : image  style="cursor:hand;border:none;" id="btIncluir" onClick="incluir();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'"/>
                                    </div>
                                    <div id="divNovo">
                                    < netui : image  style="cursor:hand;border:none;" id="btIncluir" onClick="criarNovo();" src="/FrontOfficeWeb/resources/images/bt_criarnovo_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_criarnovo_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_criarnovo_over.gif'"/>
                                    </div>
                                </td -->    
                            </tr>
                        </table>
                    <logic:equal name="Form" property="arrayLength" value="0">
                                <br>
                                <b>Não foi encontrado nenhum perfil com a descrição fornecida.</b>
                    </logic:equal>
                    </td>
                </tr> 
                <tr valign="top">
                    <td valign="top" align="center">
                        <logic:greaterThan name="Form" property="arrayLength" value="0">
                        <vivo:tbTable selectable="true" layoutWidth="745" layoutHeight="280" tableWidth="745" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left"   width="65%" type="string">Perfis encontrados</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="15%" type="string">Associado</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string">Estado</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate name="PerfilVariaveis" property="perfilArray" id="perfil">
                                    <vivo:tbRow key="linha">
                                        <vivo:tbRowColumn>
                                            <vivo:hint maxLength="80"> <bean:write name="perfil" property="nmPerfil"/></vivo:hint>
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
                                            <logic:equal value="0" name="perfil" property="habilitado">
                                            <a href="#" onclick="statusPerfil('<bean:write name="perfil" property="idPerfil" />',1); return false">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" border="0" alt="Perfil desabilitado. Clique para Habilitar."></a>
                                            </logic:equal>
                                            <logic:equal value="1" name="perfil" property="habilitado">
                                            <a href="#" onclick="statusPerfil('<bean:write name="perfil" property="idPerfil" />',0); return false">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_habilitar.gif" border="0" alt="Perfil habilitado. Clique para desabilitar."></a>
                                            </logic:equal>
                                        </vivo:tbRowColumn>                                        
                                        <vivo:tbRowColumn>
                                            <!--acesso:controlHiddenItem nomeIdentificador="usu_pg_editargrupo"-->
                                            <a href="Javascript:alteraPerfil('<bean:write name="perfil" property="idPerfil" />');">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif"  border="0" alt="Editar Grupo"></a>
                                            <!--/acesso:controlHiddenItem-->
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <!--acesso:controlHiddenItem nomeIdentificador="usu_pg_excluirgrupo"-->
                                            <a href="#" onclick="removeItem('<bean:write name="perfil" property="idPerfil" />'); return false">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" alt="Excluir Perfil"></a>
                                            <!--/acesso:controlHiddenItem-->
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <a href="#" onclick="editaRelPerfil('<bean:write name="perfil" property="idPerfil" />'); return false">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" border="0" alt="Alterar Parâmetros"></a>
                                        </vivo:tbRowColumn>
                                    
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>                    
                    
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                        <table width="755" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
                            <tr>
                                <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                                <td>
                                    <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4">
                                </td>
                                <td>
                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Editar Perfil
                                </td>
                                <td>
                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Remover Perfil
                                </td>
                                <td>
                                    <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" align="middle"> &nbsp;Alterar Parâmetros
                                </td>
                                <td>
                                    <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" align="middle"> &nbsp;Perfil Desabilitado
                                </td>
                                <td>
                                    <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_habilitar.gif" align="middle"> &nbsp;Perfil Habilitado
                                </td>
                            </tr>
                        </table>
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                    <table width="100%">
                        <tr>
                            <td align="center">
                            <% 
                            if (Form.getBloco()==null || Form.getBloco().equals(ConstantesCRM.SZERO) || Form.getBloco().equals(ConstantesCRM.SVAZIO)) {
                            %>
                            <% } else { %>
                            <img onClick="pesquisarPagina(-1); return false;" id="btAnterior" src="/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_over.gif'" style="border:nonecursor:hand;"/>
                            <% } %>   
                            <% if (Form.getInFim() == null || Form.getInFim().equals(ConstantesCRM.SONE)) {%>
                            <% } else { %>
                            <img onClick="pesquisarPagina(1); return false;" id="btProximo" src="/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_over.gif'" style="border:nonecursor:hand;"/>
                            <% } %>
                            </td>
                        </tr>
                    </table>
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                    </logic:greaterThan>
                    </td>                
                </tr>   
                <tr>
                    <td valign="bottom" align="right">
                        <img id="btIncluir" onClick="incluiPerfil();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="cursor:hand;border:none"/>
                    </td>
                </tr>
            </table>
            
            <table>
                <tr>
                    <td>
                        <img id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false;" style="border:none;cursor:hand;" vspace="5" hspace="5"  onBlur="document.forms[0].dsPerfil.focus();"/>
                    </td>
                </tr>
            </table>
            </form>
	        <!--/netui : form-->  
        </vivo:sessao>
        <iframe scrolling="yes" style="visibility:hidden;" name="frameEscondido" id="frameEscondido" height="1px" width="1px"></iframe>
        <vivo:quadroFlutuante id="divIncluiPerfil" idIframe="frmIncluiPerfil" spacesLeft="150" height="80" width="500" spacesTop="150" display="none" url="<%=request.getContextPath()%>" label="Incluir Perfil" onclick=""/>        
        <vivo:quadroFlutuante id="divAlteraPerfil" idIframe="frmAlteraPerfil" spacesLeft="150" height="80" width="500" spacesTop="150" display="none" url="<%=request.getContextPath()%>" label="Incluir Perfil" onclick=""/>        
	    <script>
	        document.body.style.backgroundColor = '#ededed';
	    </script>
	    <vivo:alert atributo="msgStatus" scope="request"/>
    </netui-template:section>
</netui-template:template>