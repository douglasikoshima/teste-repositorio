<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="FormManterBanner" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formUploadBanner"/>

<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    
    <script language="javaScript">
        top.frameApp.oculta_div();
    </script>
</head>
<body >
    
    <logic:equal name="FormManterBanner" property="dsTipoBanner" value="2">
    
        <logic:equal name="FormManterBanner" property="idArea"  value="1">
            <table align="center">
                <tr>
                    <td>
                        <img width="468" height="60" src="/FrontOfficeWeb/BannerServlet?bannerName=<bean:write name="FormManterBanner" property="nmImagem"/>">
                    </td>
                </tr>
            </table>
        </logic:equal>
        
        <logic:notEqual name="FormManterBanner" property="idArea" value="1">        
            <table align="center">
                <tr>
                    <td>
                        <img width="234" height="60" src="/FrontOfficeWeb/BannerServlet?bannerName=<bean:write name="FormManterBanner" property="nmImagem"/>">
                    </td>
                </tr>
            </table>
        </logic:notEqual>
        
    </logic:equal>

    <logic:equal name="FormManterBanner" property="dsTipoBanner" value="1">
    
        <logic:equal name="FormManterBanner" property="idArea"  value="1">
            <table align="center">
                <tr>
                    <td>
                        <object type="application/x-shockwave-flash" 
                                data="/FrontOfficeWeb/BannerServlet?bannerName=<bean:write name="FormManterBanner" property="nmImagem"/>" 
                                wmode="transparent"  width="468" height="60">
                            <param name="movie" value="/FrontOfficeWeb/BannerServlet?bannerName=<bean:write name="FormManterBanner" property="nmImagem"/>" />
                            <param name="wmode" value="transparent" />
                            <param name="width" value="468" />
                            <param name="height" value="60" />
                        </object>
                    </td>
                </tr>
            </table>                
        </logic:equal>
        
        <logic:notEqual name="FormManterBanner" property="idArea"  value="1">
            <table align="center">
                <tr>
                    <td>
                        <object type="application/x-shockwave-flash" 
                                data="/FrontOfficeWeb/BannerServlet?bannerName=<bean:write name="FormManterBanner" property="nmImagem"/>" 
                                wmode="transparent"  width="234" height="60">
                            <param name="movie" value="/FrontOfficeWeb/BannerServlet?bannerName=<bean:write name="FormManterBanner" property="nmImagem"/>" />
                            <param name="wmode" value="transparent" />
                            <param name="width" value="234" />
                            <param name="height" value="60" />
                        </object>
                    </td>
                </tr>
            </table>                
        </logic:notEqual>       
         
    </logic:equal>
</body>
</html>