<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:verifySession/>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/vivomenu.js"></script>
<vivo:menu idMenuMin="mnMin" idMenuMax="mnMax" width="300px">
    <vivo:menuRaiz value="Relatorios" haveNextMenu="N" title="relatorio" styleClass="menuItem" onclick="showMenu('menuRaiz', 'menuItemHolder');" style="cursor:pointer;" width="200px">
        <vivo:menuGrupo styleId="menuRaiz" styleClass="menuItemHolder" style="display:none;" menu="none">
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelRetencao.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Retenção</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelRetencaoOferta.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Retenção de Ofertas</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelRetencaoDistincao.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Retenção com Destinção</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelResultadoDestino.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Resultado Destino</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelLigRetencao.jsp"  target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Ligações de Retenção</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelControleCred.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Controle de Crédito</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelNotesLoja.jsp"  target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Notes Lojas</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelDestinoDistincao.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Resultado Destino com Destinção</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelMovDiaria.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Movimentações Diarias</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelRetPlano.jsp"  target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Retenção de Planos</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelRetPlanoDistincao.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Retenção de Planos com Distinção</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelOperador.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Operadores</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelOfertas.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Ofertas</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelOfertaDiaPontos.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Ofertas Diarias por Pontos</vivo:menuItem>
            <vivo:menuItem page="./../../fidelizacao/Relatorio/RelTotalMensal.jsp" target="fraAbas" styleClass="menuItem" onmouseover="doHideAllSubMenu('menuRaiz');" onclick="hideMenu();mudarMenuMinMax();">Total de Ofertas Mensal</vivo:menuItem>
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

