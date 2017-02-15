<%@ page language="java" contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ page import="br.com.vivo.fo.commons.utils.StringUtilStatic"%>
<%@ page import="br.com.vivo.fo.commons.utils.exceptions.ExceptionContainer"%>
<%@ page import="br.com.vivo.fo.commons.utils.exceptions.ExceptionHandler"%>
<%@ page import="commons.errors.FormError"%>
<%@ page import="br.com.vivo.fo.commons.utils.exceptions.ErrorCodes"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@ page import="br.com.vivo.fo.log.Logger"%>

<%!private static Logger log = new Logger("start");%>
<%
    String errorIcon    = ExceptionContainer.DEFAULT_ICON;
    String errorCode    = ExceptionContainer.DEFAULT_ERROR_CODE;
    String errorMessage = ExceptionContainer.INTERNAL_ERROR;
    String errorStack   = ExceptionContainer.NO_STACK_AVAILABLE;
    String url          = request.getContextPath() + ExceptionContainer.DEFAULT_CONTROLLER;
    String target       = ExceptionContainer.DEFAULT_TARGET;
    String tmpMessage   = null;

    Object obj = request.getAttribute(ExceptionContainer.KEYRING);
    if(obj == null){
        obj = request.getAttribute("org.apache.struts.action.EXCEPTION");
    }
    if(obj == null){
        obj = request.getAttribute("javax.servlet.error.exception");
    }
    if(obj == null){
        obj = pageContext.getException();
    }
    if(obj != null){
        if (!(obj instanceof FormError) && !(obj instanceof ExceptionContainer) && !(obj instanceof InternalError)) {
            obj = ExceptionHandler.buildExceptionContainer((Exception)obj, url);
        }
        if(obj instanceof FormError){
            errorIcon  = ((FormError)obj).getErrorIcon();
            errorCode  = ((FormError)obj).getErrorCode();
            tmpMessage = StringEscapeUtils.escapeJavaScript(((FormError)obj).getErrorMessage());
            errorStack = StringEscapeUtils.escapeJavaScript(((FormError)obj).getErrorStack());
            url        = ((FormError)obj).getUrlToGo();
            target     = ((FormError)obj).getTarget();
            //tmpMessage = StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeHtml(((FormError)obj).getErrorMessage()));
            //errorStack = StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeHtml(((FormError)obj).getErrorStack()));
        }else if(obj instanceof ExceptionContainer){
            errorIcon  = ((ExceptionContainer)obj).getErrorIcon();
            errorCode  = ((ExceptionContainer)obj).getErrorCode();
            tmpMessage = StringEscapeUtils.escapeJavaScript(((ExceptionContainer)obj).getErrorMessage());
            errorStack = StringEscapeUtils.escapeJavaScript(((ExceptionContainer)obj).getErrorStack());
            url        = ((ExceptionContainer)obj).getUrlToGo();
            target     = ((ExceptionContainer)obj).getTarget();
            //tmpMessage = StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeHtml(((ExceptionContainer)obj).getErrorMessage()));
            //errorStack = StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeHtml(((ExceptionContainer)obj).getErrorStack()));
        }
    }else{
        errorIcon = ExceptionContainer.DEFAULT_ICON;
        Integer intErrorCode = (Integer)(request.getAttribute("javax.servlet.error.status_code"));
        try{
            if(intErrorCode != null){
                errorCode = intErrorCode.toString();
            }else{
                intErrorCode = new Integer(0);
            }
        }catch(Exception e){
            // nothing! keep default value!
        }
        StringBuffer sb = new StringBuffer();
        sb.append("Server Error ").append(errorCode).append(": ").append(ErrorCodes.translateHttpServletResponseErrorCode(intErrorCode.intValue()));
        //tmpMessage = StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeHtml(sb.toString()));

        tmpMessage = StringEscapeUtils.escapeJavaScript(sb.toString());
        sb.append("\nResource: ").append(StringUtilStatic.emptyIfNull(request.getAttribute("javax.servlet.error.request_uri")).toString());
        sb.append("\nReferer: ").append(StringUtilStatic.emptyIfNull(request.getHeader("Referer")));
        sb.append("\nAccept-Language: ").append(StringUtilStatic.emptyIfNull(request.getHeader("Accept-Language")));
        sb.append("\nAccept-Encoding: ").append(StringUtilStatic.emptyIfNull(request.getHeader("Accept-Encoding")));
        sb.append("\nUser-Agent: ").append(StringUtilStatic.emptyIfNull(request.getHeader("User-Agent")));
        sb.append("\nHost: ").append(StringUtilStatic.emptyIfNull(request.getHeader("Host")));
        sb.append("\nConnection: ").append(StringUtilStatic.emptyIfNull(request.getHeader("Connection")));
        sb.append("\nCookie: ").append(StringUtilStatic.emptyIfNull(request.getHeader("Cookie")));

        sb.append("\nweblogic.servlet.network_channel.port: ").append(request.getAttribute("weblogic.servlet.network_channel.port"));
        sb.append("\nweblogic.servlet.network_channel.sslport: ").append(request.getAttribute("weblogic.servlet.network_channel.sslport"));
        sb.append("\njavax.servlet.error.servlet_name: ").append(request.getAttribute("javax.servlet.error.servlet_name"));
        sb.append("\ncom.bea.wlw.netui.pageflow.scoping.ScopedServletUtils_decodedURI: ").append(request.getAttribute("com.bea.wlw.netui.pageflow.scoping.ScopedServletUtils_decodedURI"));
        sb.append("\ncom.bea.wlw.netui.pageflow.scoping.ScopedServletUtils_originalURI: ").append(request.getAttribute("com.bea.wlw.netui.pageflow.scoping.ScopedServletUtils_originalURI"));
        sb.append("\nweblogic.servlet.errorPage: ").append(request.getAttribute("weblogic.servlet.errorPage"));
        sb.append("\ncom.bea.wlw.runtime.core.dispatcher.AppManager.ensured: ").append(request.getAttribute("com.bea.wlw.runtime.core.dispatcher.AppManager.ensured"));
        sb.append("\norg.apache.struts.action.MODULE: ").append(request.getAttribute("org.apache.struts.action.MODULE"));
        log.error(sb.toString());
        //errorStack = StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeHtml(sb.toString()));
        errorStack = StringEscapeUtils.escapeJavaScript(sb.toString());
        url        = request.getContextPath() + ExceptionContainer.DEFAULT_CONTROLLER;
        target     = ExceptionContainer.DEFAULT_TARGET;
    }
    int widthWide   = 600;
    int heightTall  = 350;
    int widthNarrow = 450;
    int heightSmall = 150;
    if((tmpMessage != null) && (tmpMessage.length() > 0)){
        errorMessage = tmpMessage;
    }%>
<%
boolean detailsEnabled = true;
if((!detailsEnabled) && (request != null) && (request.getSession() != null)){
    String serverName = (String)request.getSession().getAttribute("ServerName");
    String integrationServerName = "aroeiras";
    if((serverName != null) && (serverName.equals(integrationServerName))){
        detailsEnabled |= true;
    }
}%>
<html>
    <head>
        <title>Vivo Net - Erro Fatal</title>
        <script language="javascript">
            function buildSettings(width, height, top, left, scrollbars, statusbar, resizable) {
                var settings = "dialogWidth:"+width+"px; dialogHeight:"+height+"px; dialogTop:"+top+"px; dialogLeft:"+left+"px; help:no";
                settings += "; scroll:" + ((scrollbars) ? "yes" : "no");
                settings += "; status:" + ((statusbar) ? "yes" : "no");
                settings += "; resizable:" + ((resizable) ? "yes" : "no");
                settings += ";center: yes;";
                return settings;
            }

            function popupWindow() {
                var default_url = 'msgError.jsp';
                var width  = <%=widthNarrow%>;
                var height = <%=heightSmall%>;
                // show a modal (blocking) popup window
                popUp(default_url, width, height,"Error");
                //enviaAcao();
            }

            function popUp(sPage, sWidth, sHeight, sTitle){
                var sFeatures = "center:yes;status:no;dialogWidth:" + sWidth + "px;dialogHeight:" + sHeight + "px;scroll:no;resizable:no;status:no;";
                var objPop = new Object();
                objPop.title = sTitle;
                objPop.pageToOpen = sPage;
                objPop.path = "<%=request.getContextPath()%>/";
                objPop.window1 = window;
                objPop.widthWide      = <%=widthWide%>;
                objPop.heightTall     = <%=heightTall%>;
                objPop.widthNarrow    = <%=widthNarrow%>;
                objPop.heightSmall    = <%=heightSmall%>;
                objPop.detailsEnabled = <%=detailsEnabled%>;
                objPop.context        = "<%=request.getContextPath()%>";
                objPop.errorIcon      = "<%=errorIcon%>";
                objPop.errorCode      = "<%=errorCode%>";
                objPop.errorMessage   = "<%=errorMessage%>";
                objPop.errorStack     = "<%=errorStack%>";
                
                if( top.frameApp != null ){
                    if (top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
                    var divAguardeErro = top.frameApp.document.getElementById("dvAnimarAguardeErro");
                    var docFrameApp = top.frameApp.document;
                    var arrayIframe = docFrameApp.getElementsByTagName("IFRAME");
                } else {
                    var divAguardeErro = document.getElementById("dvAnimarAguardeErro");
                    var arrayIframe = document.getElementsByTagName("IFRAME");
                }
                
                if (getObject("errTitle") == null ) {
                    
                    document.getElementById("dvAnimarAguardeErro").display = "block";
                    
                } else {
                    getObject("errTitle").innerHTML = sTitle;
                    getObject("errCode").innerHTML = "<%=errorCode%>";
                    getObject("errIcon").src = "<%=request.getContextPath()%>/resources/images/<%=errorIcon%>";
                    getObject("errMsg").innerHTML = "<%=errorMessage%>";
                    getObject("errDetails").value = "<%=errorStack%>";
                    divAguardeErro.style.display = "block";
                }
            }

            function getObject(objName){
                if( top.frameApp != null ){
                    return top.frameApp.document.getElementById(objName);
                } else {
                    return document.getElementById(objName);
                }
            }

            function enviaAcao(){
                document.forms['___hidden_redirectForm___'].action = '<%=url%>';
                document.forms['___hidden_redirectForm___'].target = '<%=target%>';
                document.forms['___hidden_redirectForm___'].submit();
            }

			goHome = function() {
                getObject('dvAnimarAguardeErro').style.display = 'none';
                document.forms['___hidden_redirectForm___'].action = '<%=request.getContextPath()%>/autenticado.do';
                document.forms['___hidden_redirectForm___'].target = '<%=target%>';
                document.forms['___hidden_redirectForm___'].submit();
            }

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
            html {
                height:100%;
                max-height:100%;
                padding:0;
                margin:0;
                border:0;
                background:#fff;
                font-family:Tahoma,Arial;
                font-size:12px;
            }
            
            body
                {
                font-family: Tahoma, Arial, Helvetica, sans-serif;
                scrollbar-face-color:#006bcd;
                scrollbar-highlight-color:#006bcd;
                scrollbar-3dlight-color:#ffffff;
                scrollbar-darkshadow-color:#ffffff;
                scrollbar-shadow-color:#006bcd;
                scrollbar-arrow-color:#ffffff;
                scrollbar-track-color:#ffffff;
                margin: 0px;
                background-color: #ffffff;
                background-repeat: no-repeat;
                height:100%;
                max-height:100%;
                overflow:hidden;
                padding:0;
                border:0;
			}
        </style>
    </head>
    <body onload="popupWindow()">
        <form name='___hidden_redirectForm___' method='get' action='' target=''>
            <input type="hidden" value="1" name="acao"/>
        </form>

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
                <div id="head">
                    <div id="close"></div>
                    <span id="errTitle"></span>
                </div>
                <div id="foot">
                    <textarea id="errDetails" rows="0" style="overflow:hidden;margin-right:20px;background-color:#e4ebf5;color:#e4ebf5;border:none;font-family:Tahoma;font-size:10px;height:15px;width:340px;"></textarea>
                    <img src="<%=request.getContextPath()%>/resources/images/bt_fechar_nrml.gif" border="0" style="cursor:pointer" onmouseup="goHome()">
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

    </body>
</html>
<!-- Some browsers will not display this page unless the response status code is 200. -->
<% response.setStatus(200); %>
