<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:verifySession/>
<vivo:menu idMenuMin="mnMin" idMenuMax="mnMax" width="768px"> 
    <vivo:menuRaiz value="&nbsp;&nbsp;Adm. Clientes" haveNextMenu="N" title="Operações Disponíveis no Workflow" styleClass="menuItem" onclick="showMenu('menuRaiz', 'menuItemHolder');" style="cursor:pointer;" width="130px">
        <!--Exemplo de Menu-->
        <vivo:menuGrupo styleId="menuRaiz" styleClass="menuItemHolder" style="display:none;" menu="none">             
            <vivo:subMenu menu="menuA13" styleClass="menuItem" onclick="doShowSubMenu('menuA13','menuHover');" onmouseover="setStyle('menuItem');" onmouseout="setStyle('menu');" title="Costumer Profile">Cor. Devolvida ></vivo:subMenu>
            <vivo:subMenu menu="menuA14" styleClass="menuItem" onclick="doShowSubMenu('menuA14','menuHover');" onmouseover="setStyle('menuItem');" onmouseout="setStyle('menu');" title="Costumer Profile">Costumer Profile ></vivo:subMenu>
            
            <vivo:menuSeparator styleClass="separator"></vivo:menuSeparator>
            <vivo:menuItem href="/FrontOfficeWeb/index.jsp" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();" onmouseover="doHideAllSubMenu('menuRaiz');" title="Sair do Sistema">Bem-Vindo</vivo:menuItem>

            <vivo:menuSeparator styleClass="separator"></vivo:menuSeparator>
            <vivo:menuItem href="#" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();" onmouseover="doHideAllSubMenu('menuRaiz');" title="Sair do Sistema">Sair</vivo:menuItem>
        </vivo:menuGrupo>
        
        <!--Exemplo de Menu -> menuA14-->
        <vivo:menuGrupo styleId="menuA13" styleClass="menuItemHolder" style="display:none;" menu="menuRaiz"> 
            <vivo:menuItem href="/FrontOfficeWeb/cliente/CDevolvida/tipocorresp/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Tipo de Correspondência</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/CDevolvida/motivodevolucao/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Motivo da Devolução</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/CDevolvida/status/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Status</vivo:menuItem>            
        </vivo:menuGrupo>
        
        <vivo:menuGrupo styleId="menuA14" styleClass="menuItemHolder" style="display:none;" menu="menuRaiz"> 
            <vivo:menuItem href="/FrontOfficeWeb/cliente/DadosComportamentais/assunto/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Assunto</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/DadosComportamentais/subassunto/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Sub-assunto</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/DadosComportamentais/conteudo/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Conteúdo</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/DadosComportamentais/valorpossivel/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Valor Possível</vivo:menuItem>
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