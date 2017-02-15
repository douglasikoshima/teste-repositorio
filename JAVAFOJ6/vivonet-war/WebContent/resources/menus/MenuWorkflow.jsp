<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<vivo:menu idMenuMin="mnMin" idMenuMax="mnMax" width="768px"> 
    <vivo:menuRaiz value=" Workflow" haveNextMenu="N" title="Operações Disponíveis no Workflow" styleClass="menuItem" onclick="showMenu('menuRaiz', 'menuItemHolder');" style="cursor:pointer;" width="130px">
        <!--Exemplo de Menu-->
        <vivo:menuGrupo styleId="menuRaiz" styleClass="menuItemHolder" style="display:none;" menu="none"> 
            <vivo:menuItem href="/FrontOfficeWeb/workflow/AtendimentoFila/begin.do" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Fila de Processos</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/workflow/AtendimentoInBox/begin.do" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Processos In-Box</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/workflow/AtendimentoFechamentoMassa/begin.do" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Fechamento Massa</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/workflow/Monitoramento/begin.do" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Monitoramento</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/workflow/Indicadores/begin.do" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Indicadores</vivo:menuItem>
            <vivo:subMenu menu="menuA14" styleClass="menuItem" onclick="doShowSubMenu('menuA14','menuHover');" onmouseover="setStyle('menuItem');" onmouseout="setStyle('menu');" title="Relatorios Workflow">Relatórios ></vivo:subMenu>

            <vivo:menuSeparator styleClass="separator"></vivo:menuSeparator>
            <vivo:menuItem href="/FrontOfficeWeb/index.jsp" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();" onmouseover="doHideAllSubMenu('menuRaiz');" title="Sair do Sistema">Bem-Vindo</vivo:menuItem>

            <vivo:menuSeparator styleClass="separator"></vivo:menuSeparator>
            <vivo:menuItem href="#" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();" onmouseover="doHideAllSubMenu('menuRaiz');" title="Sair do Sistema">Sair</vivo:menuItem>
        </vivo:menuGrupo>
        
        <!--Exemplo de Menu -> menuA14-->
        <vivo:menuGrupo styleId="menuA14" styleClass="menuItemHolder" style="display:none;" menu="menuRaiz"> 
            <vivo:menuItem href="/FrontOfficeWeb/workflow/Relatorios/beginTotalGrupoDestinoBKO.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Total Grupo Dest</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/workflow/Relatorios/beginTotalRepresentanteBKO.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Total Rep</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/workflow/Relatorios/beginProdRepresentanteBKO.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Prod Rep</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/workflow/Relatorios/beginQtdMotivoRepresentante.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Qtd Ret/Mot Rep</vivo:menuItem>
            <vivo:menuItem href="/FrontOfficeWeb/workflow/Relatorios/beginTempoMedioFila.do" styleClass="menuItem" onclick="hideMenu();mudarMenuMinMax();">Tempo Medio Fila</vivo:menuItem>
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