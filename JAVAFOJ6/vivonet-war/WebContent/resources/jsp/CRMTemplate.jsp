<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
    <head>
        <html:base/>
        <title><%--netui-template:attribute name="title"/--%>Vivo Net</title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js" ></script>
        <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivomenu.js" ></script>
        <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/livepipe.js"></script>
        <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/hotkey.js"></script>
        <script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/controleEventos.js"></script>
        <script type="text/javascript" language="JavaScript">
            <!--
                function abrePopModal(titulo, pagina){
                    document.getElementById("dvPopModal").style.visibility = 'visible';
                    document.getElementById("bgPop").style.visibility = 'visible';
                    document.getElementById("qdrPopModal").innerHTML = titulo;
                    document.getElementById('popIframe').src = pagina;
                }

                function fecharModal(){
                    top.frameApp.document.getElementById('dvAnimarAguardeErro').style.display = 'none';
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation(); 
                    var divElem = top.frameApp.document.getElementsByTagName("DIV");
                    for (i=0; i < divElem.length; i++) {
                        if (divElem[i].style.backgroundImage == "url(/FrontOfficeWeb/resources/images/window_bg.gif)"
                            && (divElem[i].style.display == "block" || divElem[i].style.display == "")) {
                            divElem[i].style.display = "none";
                        }
                    }
                }

                function fecharSessao(){
                <%if(session.getAttribute(ConstantesCRM.USUARIO_LOGIN) != null) {%>
                    //document.getElementById("ifrLogout").src = "<%=request.getContextPath()%>/logout.do";
                    location.href="<%=request.getContextPath()%>/logout.do";
                <%}%>
                }

            -->
        </script>
		<style type="text/css">
            #content {overflow:hidden; padding:10px; position:absolute; z-index:3; top:21px; bottom:50px; left:100px; right:0; background:#e4ebf5;}
            #errMsg {height:100%; overflow-x:hidden; width:100%; overflow-y:auto; padding:10px; }
            * html #content {top:0; left:0; right:0; bottom:0; height:100%; color:#000; max-height:100%; width:100%; overflow:auto; position:absolute; z-index:3; border-top:21px solid #fff; border-bottom:30px solid #fff; border-left:100px solid #fff;}
            #holder {position:absolute;left:150px;top:245px;width:500px;height:150px;}
            #head {position:absolute; color:#fff; font-weight:bold; font-size:11px; padding:3 0 0 10px;; margin:0; top:0; left:0; display:block; width:100%; height:21px; background:url(<%=request.getContextPath()%>/resources/images/window_topbg.gif) #000; background-repeat:repeat-x; z-index:5; overflow:hidden;}
            #close {background-image:url(<%=request.getContextPath()%>/resources/images/window_topbtbg.gif); height:21px; width:64px; float:right;margin-top:-3px;text-align:right;}
            #foot {text-align:right; position:absolute; margin:0; bottom:0; left:0; display:block; width:100%; height:30px; z-index:5; overflow:hidden; background:#e4ebf5; color:#000; padding:5 20 0 0}
            #left {position:absolute; text-align:center; left:0; top:21px; bottom:50px; width:100px; background-color:#e4ebf5; z-index:4; overflow:hidden; padding-top:20px;color:#000}
            * html #left {height:100%; top:0; bottom:0; border-top:21px solid #fff; border-bottom:30px solid #fff; color:#000;}
            #errIcon {margin-bottom:20px;}
            body {background:#f3f3f3 url(/FrontOfficeWeb/resources/images/bg.gif) no-repeat fixed top left;}
        </style>
        <netui-template:includeSection name="headerSection"></netui-template:includeSection>
    </head>
    <body id="body" onClick="hideMenu();mudarMenuMinMax();" style="margin-left:5px;" topmargin="0" bgcolor="#ffffff" scroll="no" background="<%=request.getContextPath()%>/resources/images/bg_teste.gif">

        <div id="debugWindow" style="position:absolute;width:780px;height:580px;left:10px;top:10px;border:1px solid black;background:#fff;color:#000;display:none">
            <div style="width:112px;height:57px;background:url(/FrontOfficeWeb/resources/images/vivonetDebugWindowLogo.gif) center no-repeat"></div>
            <textarea id="debugWindowContent" style="border:none;clear:both;height:363px;width:760px;margin-left:10px;background:#f0f0f0;color:#000;font-family:Arial;font-size:12px;overflow:auto;"></textarea>
            <textarea id="debugWindowBody" style="border:none;clear:both;height:150px;width:760px;margin-left:10px;background:#f0f0f0;color:#000;font-family:Arial;font-size:10px;overflow:auto;"></textarea>
        </div>

        <!--Animação-->
        <div id="dvAnimarAguarde" name="dvAnimarAguarde" style="display:none;padding:0px;position:absolute;zIndex:0;">
            <table id="tbAnimarAguarde" name="tbAnimarAguarde" border="0" cellpadding="0" cellspacing="0" width="40px" height="30px" align="center" valign="middle">
                <tr class="rowTabelaZebradoOn">
                    <td align="center" valign="middle" height="30px">
                        <img src="<%=request.getContextPath()%>/resources/images/anAguarde1.gif" style="display:none" id="imgAguarde" />
                        <img src="<%=request.getContextPath()%>/resources/images/anAguarde2.gif" style="display:none" id="imgAguarde" />
                        <img src="<%=request.getContextPath()%>/resources/images/anAguarde3.gif" style="display:none" id="imgAguarde" />
                        <img src="<%=request.getContextPath()%>/resources/images/anAguarde4.gif" style="display:none" id="imgAguarde" />
                    </td>
                </tr>
                <tr class="rowTabelaZebradoOn">
                    <td align="center"  height="10px">Aguarde...</td>
                </tr>
            </table>
        </div>
        <div id="dvLogout" style="display:none;padding:0px;position:absolute;zIndex:0;">
            <iframe id="ifrLogout" src="" style="display:none;"></iframe>
        </div>
        <!--Template->CABECALHO-->
        <vivo:header idMin="lbMinModulo" idMax="lbMaxModulo" idMenuMin="hdMinPrincipal" idMenuMax="hdMaxPrincipal" value="" width="790"/>
        <!--APLICACAO BEGIN-->
        <netui-template:includeSection name="bodySection"></netui-template:includeSection>
        <!--APLICACAO END-->
        <!--Template->SCRIPT CABECALHO-->
        <script language="javascript">
            //Monta o descritivo do cabeçalho
            lbMinModulo.innerText = "<netui-template:attribute name="modulo"/>";
            lbMaxModulo.innerText = lbMinModulo.innerText;
            //Monta o controle sobre o resize da janela
            window.onresize = windowResize;
            //Inicializa o posicionamento
            windowResize();
        </script>
    </body>
    <div id="bgPop" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-color:#000000; border-width:1px; border-color:#adadad;filter:alpha(opacity=60);"></div>
    <div id="dvPopModal" style="z-index:999 ;visibility: hidden; position:absolute; top:15; left:15; width:750; height:550; border-style:solid; border-width:1px; border-color:#adadad; background-color:#e3ecf4;">
        <vivo:quadro width="750" height="550" id="qdrPopModal">
            <iframe id="popIframe" src="" frameborder="0" marginheight="0" marginwidth="0" height="100%" width="100%" scrolling="no"></iframe>
        </vivo:quadro>
    </div>

	<div id="dvAnimarAguardeErro"
         style="padding-right:0;
         top:0;
         left:0;
         display:none;
         padding-left:0;
         padding-bottom:0;
         padding-top:0;
         width:100%;
         background-image:url(<%=request.getContextPath()%>/resources/images/window_bg.gif);
         height:200%;
         position:absolute;
         z-index:99999">
        <div id="holder">
            <iframe width="500" height="150" frameborder="0" marginheight="0" marginwidth="0"></iframe>
            <div id="head">
                <div id="close"></div>
                <span id="errTitle"></span>
            </div>
            <div id="foot">
                <textarea id="errDetails" rows="0" style="overflow:hidden;margin-right:20px;background-color:#e4ebf5;color:#e4ebf5;border:none;font-family:Tahoma;font-size:10px;height:15px;width:340px;"></textarea>
                <img src="<%=request.getContextPath()%>/resources/images/bt_fechar_nrml.gif" border="0" style="cursor:pointer" onmouseup="fecharModal()">
            </div>
            <div id="left">
                <img id="errIcon" src="<%=request.getContextPath()%>/resources/images/erroFO.gif">
                <br>
                <span id="errCode"></span>
            </div>
            <div id="content">
                <div id="errMsg"></div>
            </div>
        </div>
    </div>
</html>