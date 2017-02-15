<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:verifySession/>
<vivo:menu idMenuMin="mnMin" idMenuMax="mnMax" width="790px"> 


    <vivo:menuRaiz value="&nbsp;&nbsp;Menu - Gestão Clientes" haveNextMenu="N" title="Pacote Carteirização" styleClass="menuItem" onclick="showMenu('menuRaiz', 'menuItemHolder');" style="cursor:pointer;" width="180">


        <vivo:menuGrupo styleId="menuRaiz" styleClass="menuItemHolder" style="display:none;" menu="none"> 
            <vivo:subMenu menu="menuGC01" styleClass="menuItem" onclick="doShowSubMenu('menuGC01','menuHover');" onmouseover="setStyle('menuItem');" onmouseout="setStyle('menu');" title="Correspondência Devolvida">Cor. Devolvida ></vivo:subMenu>
            <vivo:subMenu menu="menuGC02" styleClass="menuItem" onclick="doShowSubMenu('menuGC02','menuHover');" onmouseover="setStyle('menuItem');" onmouseout="setStyle('menu');" title="HEXA">HEXA ></vivo:subMenu>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/AssociarGestor/begin.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();" onmouseover="doHideAllSubMenu('menuRaiz');" title="Sair do Sistema">Associar CR</vivo:menuItem>            
            <vivo:subMenu menu="menuGC03" styleClass="menuItem" onclick="doShowSubMenu('menuGC03','menuHover');" onmouseover="setStyle('menuItem');" onmouseout="setStyle('menu');" title="Pré Pago">Pré Pago ></vivo:subMenu>
            <vivo:menuSeparator styleClass="separator"></vivo:menuSeparator>
            <vivo:subMenu menu="menuGC04" styleClass="menuItem" onclick="doShowSubMenu('menuGC04','menuHover');" onmouseover="setStyle('menuItem');" onmouseout="setStyle('menu');" title="Administração de Clientes">ADM Clientes ></vivo:subMenu>
            <vivo:menuSeparator styleClass="separator"></vivo:menuSeparator>
            <vivo:menuItem href="/FrontOfficeWeb/index.jsp" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();" onmouseover="doHideAllSubMenu('menuRaiz');" title="Sair do Sistema">Bem-Vindo</vivo:menuItem>
        </vivo:menuGrupo>   
        
        <vivo:menuGrupo styleId="menuGC01" styleClass="menuItemHolder" style="display:none;" menu="menuRaiz"> 
            <vivo:menuItem href="/FrontOfficeWeb/cliente/CDevolvida/tratarCorresp/incluirCorrespDevolvida.do" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuA13');" onclick="hideMenu();mudarMenuMinMax();">Incluir</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/CDevolvida/tratarCorresp/manterDevolvida.do" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuA13');" onclick="hideMenu();mudarMenuMinMax();">Manter</vivo:menuItem>
        </vivo:menuGrupo>
        
        <vivo:menuGrupo styleId="menuGC02" styleClass="menuItemHolder" style="display:none;" menu="menuRaiz"> 
            <vivo:menuItem href="/FrontOfficeWeb/cliente/ConsultaHexa/consultaHexa.do" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuA13');" onclick="hideMenu();mudarMenuMinMax();">Consultar</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/ConsultaHexa/begin.do" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuA13');" onclick="hideMenu();mudarMenuMinMax();">Monitorar</vivo:menuItem>
        </vivo:menuGrupo>

        <vivo:menuGrupo styleId="menuGC03" styleClass="menuItemHolder" style="display:none;" menu="menuRaiz"> 
            <vivo:menuItem href="/FrontOfficeWeb/cliente/PrePago/begin.do"             styleClass="menuItem" onmouseover="doHideAllSubMenu('menuA13');" onclick="hideMenu();mudarMenuMinMax();">Cadastro            </vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/PrePago/begin.do?alter=true"  styleClass="menuItem" onmouseover="doHideAllSubMenu('menuA13');" onclick="hideMenu();mudarMenuMinMax();">Alteração           </vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/PrePago/begin.do?transf=true" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuA13');" onclick="hideMenu();mudarMenuMinMax();">Transf. Titularidade</vivo:menuItem>
        </vivo:menuGrupo>
        
        <vivo:menuGrupo styleId="menuGC04" styleClass="menuItemHolder" style="display:none;" menu="menuRaiz">
            <vivo:subMenu menu="menuGCB01" styleClass="menu" onclick="doShowSubMenu('menuB111','menuHover');" onmouseout="setStyle('menu');" title="Correspondência Devolvida">Cor. Devolvida ></vivo:subMenu>
            <vivo:subMenu menu="menuGCB02" styleClass="menu" onclick="doShowSubMenu('menuB112','menuHover');" onmouseout="setStyle('menu');" title="Customer Profile">Customer Prof. ></vivo:subMenu>
        </vivo:menuGrupo>
        
        <vivo:menuGrupo styleId="menuB111" styleClass="menuItemHolder" style="display:none;" menu="menuGC03"> 
            <vivo:menuItem href="/FrontOfficeWeb/cliente/CDevolvida/tipocorresp/begin.do" styleClass="menuItem" onclick="doHideAllSubMenu('menuB111');">Tipo Corresp.</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/CDevolvida/motivodevolucao/begin.do" styleClass="menuItem" onclick="doHideAllSubMenu('menuB111');">Motivo Devolução</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/CDevolvida/status/begin.do" styleClass="menuItem" onclick="doHideAllSubMenu('menuB111');">Status</vivo:menuItem>
        </vivo:menuGrupo>

        <vivo:menuGrupo styleId="menuB112" styleClass="menuItemHolder" style="display:none;" menu="menuGC03"> 
            <vivo:menuItem href="/FrontOfficeWeb/cliente/DadosComportamentais/assunto/begin.do" styleClass="menuItem" onclick="doHideAllSubMenu('menuB112');">Assunto</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/DadosComportamentais/subAssunto/begin.do" styleClass="menuItem" onclick="doHideAllSubMenu('menuB112');">Sub-assunto</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/DadosComportamentais/conteudo/begin.do" styleClass="menuItem" onclick="doHideAllSubMenu('menuB112');">Conteúdo</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/cliente/DadosComportamentais/valorPossivel/begin.do" styleClass="menuItem" onclick="doHideAllSubMenu('menuB112');">Valor Possivel</vivo:menuItem>
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