<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<vivo:menu idMenuMin="mnMin" idMenuMax="mnMax" width="260px"> 
    <!-- Menu Adm. Sistemas -->
    <vivo:menuRaiz value=" Adm. de Sistemas" haveNextMenu="S" title="Menu de Administração de Sistemas" styleClass="menuItem" onclick="showMenu('menuRaiz', 'menuItemHolder');" style="cursor:pointer;" width="130px">
        <!-- Itens do Menu Adm. Sistemas -->
        <vivo:menuGrupo styleId="menuRaiz" styleClass="menuItemHolder" style="display:none;" menu="none"> 
            <vivo:subMenu menu="menuA13" styleClass="menuItem" onclick="doShowSubMenu('menuA13','menuHover');" onmouseover="setStyle('menuItem');" onmouseout="setStyle('menu');" title="Administração da árvore de contatos">Árvore Contato ></vivo:subMenu>
            <vivo:subMenu menu="menuA14" styleClass="menuItem" onclick="doShowSubMenu('menuA14','menuHover');" onmouseover="setStyle('menuItem');" onmouseout="setStyle('menu');" title="Administração dos parâmetros para Workflow">Adm Workflow ></vivo:subMenu>

            <vivo:menuSeparator styleClass="separator"></vivo:menuSeparator>
            <vivo:menuItem href="/FrontOfficeWeb/index.jsp" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();" onmouseover="doHideAllSubMenu('menuRaiz');" title="Menu inicial">Bem Vindo</vivo:menuItem>
            
            <vivo:menuSeparator styleClass="separator"></vivo:menuSeparator>
            <vivo:menuItem href="#" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();" onmouseover="doHideAllSubMenu('menuRaiz');" title="Sair do Sistema">Sair</vivo:menuItem>
        </vivo:menuGrupo>   

        <!-- Menu Adm. Sistemas -> Arvore de Contatos -->
        <vivo:menuGrupo styleId="menuA13" styleClass="menuItemHolder" style="display:none;" menu="menuRaiz"> 
            <vivo:menuItem href="/FrontOfficeWeb/admsistemas/admArvore/manterArvoreContato.jsp" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuA13');" onclick="hideMenu();mudarMenuMinMax();">Manter Árvore</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/admsistemas/admArvore/indexAdmArvore.jsp" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuA13');" onclick="hideMenu();mudarMenuMinMax();">Config. Param</vivo:menuItem>
        </vivo:menuGrupo>

        <!-- Menu Adm. Sistemas -> Adm. Workflow -->
        <vivo:menuGrupo styleId="menuA14" styleClass="menuItemHolder" style="display:none;" menu="menuRaiz"> 
            <vivo:menuItem href="/FrontOfficeWeb/admsistemas/admWorkflow/indexAdmWorkflow.jsp" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Param. Workflow</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/admsistemas/GrupoUsuarioRegrasEncaminhamento/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Encaminhamento</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/RetornoCTI/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Retorno CTI</vivo:menuItem>
        </vivo:menuGrupo>
    </vivo:menuRaiz>
    
    <!-- Menu Calendario -->
    <vivo:menuRaiz value=" Adm. Calendário" haveNextMenu="N" title="Menu de Administração de Calendário" styleClass="menuItem" onclick="showMenu('menuRaiz2', 'menuItemHolder');" style="cursor:pointer;" width="130px">
        <!-- Itens do menu Calendario -->
        <vivo:menuGrupo styleId="menuRaiz2" styleClass="menuItemHolder" style="display:none;" menu="none"> 
            <vivo:menuItem href="/FrontOfficeWeb/admsistemas/admCalendario/cadastrarFeriado.jsp" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz2');" onclick="hideMenu();mudarMenuMinMax();">Cadastrar Feriado</vivo:menuItem>
            <vivo:menuItem href="#" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz2');" onclick="hideMenu();mudarMenuMinMax();">Pesquisar Feriado</vivo:menuItem>
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