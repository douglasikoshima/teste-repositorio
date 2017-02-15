<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/vivomenu.js"></script>
<vivo:menu idMenuMin="mnMin" idMenuMax="mnMax" width="300px">
    <vivo:menuRaiz value="Campanha" haveNextMenu="N" title="Campanha" styleClass="menuItem" onclick="showMenu('menuRaiz', 'menuItemHolder');" style="cursor:pointer;" width="200px">
        <vivo:menuGrupo styleId="menuRaiz" styleClass="menuItemHolder" style="display:none;" menu="none">
            <vivo:menuItem page="/FrontOfficeWeb/campanha/Manter/begin.do" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Manter</vivo:menuItem>
            <vivo:menuItem page="/FrontOfficeWeb/campanha/Configurar/ConfigurarScript/begin.do" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Configurar</vivo:menuItem>
			<vivo:menuItem page="/FrontOfficeWeb/campanha/AgendarContato/begin.do" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Agendar Contato</vivo:menuItem>
			<vivo:menuItem page="/FrontOfficeWeb/campanha/Relatorio/index.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Relatorio</vivo:menuItem>
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
