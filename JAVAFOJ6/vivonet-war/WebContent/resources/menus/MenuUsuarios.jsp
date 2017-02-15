<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:verifySession/>
<vivo:menu idMenuMin="mnMin" idMenuMax="mnMax" width="260px"> 
    <!-- Menu de Usuarios -->
    <vivo:menuRaiz value=" Usuarios" haveNextMenu="S" title="Menu de Gestão de Usuarios" styleClass="menuItem" onclick="showMenu('menuRaiz', 'menuItemHolder');" style="cursor:pointer;" width="130px">
        <!--Exemplo de Menu-->
        <vivo:menuGrupo styleId="menuRaiz" styleClass="menuItemHolder" style="display:none;" menu="none"> 
            <vivo:subMenu menu="menuA13" styleClass="menuItem" onclick="doShowSubMenu('menuA13','menuHover');" onmouseover="setStyle('menuItem');" onmouseout="setStyle('menu');" title="Descrição do Sub-Menu 1-3">Usuários ></vivo:subMenu>
            <vivo:subMenu menu="menuA14" styleClass="menuItem" onclick="doShowSubMenu('menuA14','menuHover');" onmouseover="setStyle('menuItem');" onmouseout="setStyle('menu');" title="Descrição do Sub-Menu 1-4">Grupos ></vivo:subMenu>

            <vivo:menuSeparator styleClass="separator"></vivo:menuSeparator>
            <vivo:menuItem href="/FrontOfficeWeb/index.jsp" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();" onmouseover="doHideAllSubMenu('menuRaiz');" title="Menu inicial">Bem Vindo</vivo:menuItem>
            
            <vivo:menuSeparator styleClass="separator"></vivo:menuSeparator>
            <vivo:menuItem page="#" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();" onmouseover="doHideAllSubMenu('menuRaiz');" title="Sair do Sistema">Sair</vivo:menuItem>
        </vivo:menuGrupo>   

        <!-- Menu de Usuarios -> Usuarios -->
        <vivo:menuGrupo styleId="menuA13" styleClass="menuItemHolder" style="display:none;" menu="menuRaiz"> 
            <vivo:menuItem href="/FrontOfficeWeb/usuario/manterUsuario/consultarCadastroUsuario.jsp" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuA13');" onclick="hideMenu();mudarMenuMinMax();">Cadastrar</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/usuario/manterUsuario/editarUsuario/begin.do" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuA13');" onclick="hideMenu();mudarMenuMinMax();">Pesquisar</vivo:menuItem>

        </vivo:menuGrupo>

        <!-- Menu de Usuarios -> Grupos -->
        <vivo:menuGrupo styleId="menuA14" styleClass="menuItemHolder" style="display:none;" menu="menuRaiz"> 
            <vivo:menuItem href="/FrontOfficeWeb/usuario/manterGrupo/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Manter</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/usuario/manterGrupo/manterGrupoParam/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Pesquisar</vivo:menuItem>
        </vivo:menuGrupo>
    </vivo:menuRaiz>
    
    <!-- Menu Perfil -->
    <vivo:menuRaiz value=" Perfil" haveNextMenu="S" title="Menu de Gestão de Perfis" styleClass="menuItem" onclick="showMenu('menuRaiz2', 'menuItemHolder');" style="cursor:pointer;" width="130px">
        <!--Exemplo de Menu-->
        <vivo:menuGrupo styleId="menuRaiz2" styleClass="menuItemHolder" style="display:none;" menu="none"> 
            <vivo:menuItem href="/FrontOfficeWeb/usuario/manterPerfil/manterPerfil.jsp" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz2');" onclick="hideMenu();mudarMenuMinMax();" title="Cadastrar/alterar/remover perfil">Manter Perfil</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/usuario/manterPerfil/manterPerfParam/pesquisarPerfil.jsp" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz2');" onclick="hideMenu();mudarMenuMinMax();" title="Procurar por um perfil">Pesquisar Perfil</vivo:menuItem>
        </vivo:menuGrupo>   
    </vivo:menuRaiz>
    
    <!-- Menu Sistemas -->
    <vivo:menuRaiz value=" Sistema" haveNextMenu="N" title="Menu de Gestão de Sistemas" styleClass="menuItem" onclick="showMenu('menuRaiz3', 'menuItemHolder');" style="cursor:pointer;" width="130px">
        <!--Exemplo de Menu-->
        <vivo:menuGrupo styleId="menuRaiz3" styleClass="menuItemHolder" style="display:none;" menu="none"> 
            <vivo:menuItem href="/FrontOfficeWeb/usuario/manterSistema/manterSistema.jsp" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz3');" onclick="hideMenu();mudarMenuMinMax();">Manter Sistema</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/usuario/manterSistema/pesquisarSistema.jsp" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz3');" onclick="hideMenu();mudarMenuMinMax();">Pesq. Sistema</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/usuario/manterSistema/manterServidor.jsp" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz3');" onclick="hideMenu();mudarMenuMinMax();">Manter Servidor</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/usuario/manterSistema/manterGrupam.jsp" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz3');" onclick="hideMenu();mudarMenuMinMax();">Manter Grupam.</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/usuario/manterSistema/manterUnidade.jsp" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz3');" onclick="hideMenu();mudarMenuMinMax();">Manter Unidade</vivo:menuItem>
        </vivo:menuGrupo>   
    </vivo:menuRaiz>
</vivo:menu>
<div id='idAnime'  style='display:none;z-index:2000 ;position:absolute; top:0px; left:0px; width:100%; height:100%;background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); '>
    <table border='1' cellpadding='0' cellspacing='0' height="100%" width="100%">
        <tr>
            <td align="center" valign="middle"><iframe frameborder="0" scrolling="no" height='63' width='81'  src='http:<%=request.getContextPath()%>/resources/images/carregar.html'></iframe>
            </td>
        </tr>
    </table>
</div >

<script language="javascript">
    
    //idAnime.style.display="block";
    
    function oculta_div(obj){
        idAnime.style.display="none";
    }
    
    function mostrar_div(obj){
        idAnime.style.display="block";
    }
    
    
  
</script>