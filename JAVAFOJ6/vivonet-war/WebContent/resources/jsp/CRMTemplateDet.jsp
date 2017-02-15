<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<html>
    <head>
        <netui:base />
        <title><netui-template:attribute name="title"/></title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivomenu.js" ></script>
        <script>
            <!--
            document.onkeydown = checkKeyCode;
            var oldKeyCode;
            var closingKey;
            function checkKeyCode(key){
                var keyCode;
                if (window.event){
                    keyCode = window.event.keyCode;
                }else if(key){
                    keyCode = key.which;
                }
                if((keyCode == 115) && (oldKeyCode == 18)){
                    closingKey = true;
                }
                oldKeyCode = keyCode;
            }
             -->
        </script>
        <script language="JavaScript">
            <!--
                function fecharSessao(){
                <%if(session.getAttribute(ConstantesCRM.USUARIO_LOGIN) != null) {%>
                    //document.getElementById("ifrLogout").src = "<%=request.getContextPath()%>/logout.do";
                    location.href="<%=request.getContextPath()%>/logout.do";
                <%}%>
                }
            -->
        </script>
        <script for=window event=onbeforeunload>
            <!--
            <%if(session.getAttribute(ConstantesCRM.USUARIO_LOGIN) != null) {%>
                if(window.event != null){
                    var abssize = document.body.offsetWidth-30;
                    if(((window.event.clientY < 0) && (event.clientX >= abssize)) || (closingKey)){
                        document.getElementById("ifrLogout").src = "<%=request.getContextPath()%>/logout.do";
                    }
                    closingKey = false;
                }
            <%}%>
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
    <body onClick="hideMenu();mudarMenuMinMax();" style="margin-left:5px;" topmargin="0" bgcolor="#ffffff" scroll="no">
        <!--Animação-->
        <div id="dvAnimarAguarde" name="dvAnimarAguarde" style="display:none;padding:0px;position:absolute;zIndex:0;">
            <table id="tbAnimarAguarde" name="tbAnimarAguarde" border="0" cellpadding="0" cellspacing="0" width="40px" height="30px" align="center" valign="middle">
                <tr class="rowTabelaZebradoOn">
                    <td align="center" valign="middle"  height="30px">
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
        <vivo:header reqMin="false" idMin="lbMinModulo" idMax="lbMaxModulo" idMenuMin="hdMinPrincipal" idMenuMax="hdMaxPrincipal" value="" width="790"/>
        
        <!--Template->APLICACAO-->
        <netui-template:includeSection name="bodySection"></netui-template:includeSection>

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
</html>
