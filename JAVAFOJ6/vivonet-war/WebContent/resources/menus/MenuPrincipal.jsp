<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<vivo:jmenuTag width="790" id="menuPrincipal"/>

<!-- vivo:quadroFlutuante id="dvAbout" idIframe="frame_abouot" width="755" height="450" spacesTop="75" spacesLeft="25" display="none" src="about/aboutFO.jsp" scroll="auto" url="<%=request.getContextPath()%>" label="About"/ -->

<div id='idAnime'  style='display:none;z-index:2000 ;position:absolute; top:0px; left:0px; width:100%; height:100%;background-image:url(<%=request.getContextPath()%>/resources/images/window_bg.gif); '>
    <table border='1' cellpadding='0' cellspacing='0' height="100%" width="100%">
        <tr>
            <td align="center" valign="middle">
            	<iframe frameborder="0" scrolling="no" height='63' width='81'  src='http:<%=request.getContextPath()%>/resources/images/carregar.html'></iframe>
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
    
    
    function buildSettings(width, height, top, left, scrollbars, statusbar, resizable) {
        var settings = "dialogWidth:"+width+"px; dialogHeight:"+height+"px; dialogTop:"+top+"px; dialogLeft:"+left+"px; help:no";
        settings += "; scroll:" + ((scrollbars) ? "yes" : "no");
        settings += "; status:" + ((statusbar) ? "yes" : "no");
        settings += "; resizable:" + ((resizable) ? "yes" : "no");
        return settings;
    }


    function chamaAbout() {
        var default_url = '<%=request.getContextPath()%>/about.do';
        window.open(default_url,"janela","width=450,height=250,scrolling=no");
    }
</script>
