<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <style type="text/css">
            SPAN.TreeviewSpanArea A {
                font-size: 12px;
                font-family: Tahoma;
                text-decoration: none;
                color: black;
            }
            SPAN.TreeviewSpanArea A:hover {
                color: '#000';
            }
        </style>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script src="<%=request.getContextPath()%>/resources/scripts/ua.js"></script>
        <script src="<%=request.getContextPath()%>/resources/scripts/ftiens4.js"></script>
        <script>
            <!--
                USETEXTLINKS  = 0
                STARTALLOPEN  = 0
                USEFRAMES     = 0
                USEICONS      = 0
                WRAPTEXT      = 1
                PRESERVESTATE = 0
                ICONPATH = '<%=request.getContextPath()%>/resources/images/';
                foldersTree = gFld("", "");
                foldersTree.treeID = "Frameless";
                <%=(String)request.getAttribute("arvore")%>
            -->
        </script>
        <script language="javascript" for="window" event="onload">
            <!--
                //top.frameApp.oculta_div();
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            -->
        </script>
    </netui-template:section>
<netui-template:section name="bodySection">
    <div style="display:none;">
    <TABLE border=0><TR><TD><FONT size=-2><A style="font-size:7pt;text-decoration:none;color:silver" href="http://www.treemenu.net/" target=_blank>Javascript Tree Menu</A></FONT></TD></TR></TABLE>
    </div>

    <table width="100%" cellpadding="0" cellspacing="2" align="center" bgcolor="#ffffff">
        <tr>
            <td><b>Tipo de Consulta:</b></td>
            <td align="right"></td>
        </tr>
    </table>

    <table width="715" cellpadding="0" cellspacing="0" align="center">
        <tr>
            <td>
                <div style="width:715px; height:365px;overflow:scroll;border-bottom:1px solid #adadad;border-top:1px solid #adadad;">
                    <span class=TreeviewSpanArea style="width:345px;height:275px;overflow-y:auto;overflow-x:hidden;">
                        <script>initializeDocument()</script>
                        <noscript>
                        N&atilde;o foi poss&iacute;vel a exibi&ccedil;&atilde;o da árvore de filtros devido
                        a limita&ccedil;&atilde;o em seu navegador. Entre em contato com o Help Desk.
                        </noscript>
                    </span>
                </div>
            </td>
        </tr>
    </table>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
</netui-template:section>
</netui-template:template>
