<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<% 
String template = "../../resources/jsp/CRMTemplate.jsp";
%>
<netui-template:template templatePage='<%=template%>'>
    <netui-template:setAttribute value="FrontOffice - Atendimento" name="title"/>
    <netui-template:setAttribute value="Atendimento" name="modulo"/>
    <netui-template:section name="headerSection">
        <netui:exceptions showMessage="false"/>    
        <link href="../../resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="../../resources/scripts/frameweb.js"></script>
		<script type="text/javascript" src="../../resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="../../resources/scripts/telainicial_externa.js"></script>                                
        <script type="text/javascript" src="../../resources/scripts/toolTip.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
       
        CarregaAba('bt02');
       
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">

<div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>
    <script>
        var moveToolTip = true;;
                            
        xBump=yBump=10;
        MSIE=document.all;
        NS6=document.getElementById&&!document.all;
        if(MSIE||NS6){
            ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
        }
                
        function carregaToolTip(content) {
            ttipObj.innerHTML=content;
            ttipObj.style.display='';
        } 
        
    -->
    </script>
<!--
div colocado aqui para mostrar gif de aguarde
-->    
<div id='idAnimeTelaInicial'  style='display:none;z-index:2000 ;position:absolute; top:0px; left:0px; width:100%; height:100%;background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); '>
    <table border='1' cellpadding='0' cellspacing='0' height="100%" width="100%">
        <tr>
            <td align="center" valign="middle"><iframe frameborder="0" scrolling="no" height='63' width='81'  src='http:/FrontOfficeWeb/resources/images/carregar.html'></iframe>
            </td>
        </tr>
    </table>
</div >

<div id="divTodaPagina"  style="display:block;">   
    <div id='menuPrincipal'>
    <jsp:include page="/resources/menus/MenuExterno.jsp" />
    </div>
   
<div id="divPopupTI" class="popUpDiv" style="z-index:998;position:absolute;top:0;left:0;width:150%;height:150%;background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif);display:none;"></div>     

    <table height="16" border="0" width="790" cellspacing="0" cellpadding="0">
        <tr>
            <td background="../../resources/images/aba_bg_line.gif" valign="top" height="16">
                <vivo:abaGrupo id="btAba" width="766" height="16" styleClass="abatexto">
                    <vivo:abaItem id="bt02" onclick="CarregaAba(this.id);" value="Atendimento"/>
                </vivo:abaGrupo>
            </td>
        </tr>
    </table>
        
    <div style="display:none" id="dvFraAbas">
            <iframe id="fraAbas" name="fraAbas" src="" width="785" height="380" frameborder="0"></iframe>
    </div>         
        
        <!--Form e Quadro Flutuante-->
        <form id="frmSelecao" name="frmSelecao" method="post" />
        <vivo:quadroFlutuante id="dvAtendimento" idIframe="ifrmAtendimento" width="800" height="570" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Atendimento"/>        
             
</div>

<frame name="frameApp" src="about:blank" noResize scrolling="no">

        </netui-template:section>
</netui-template:template>