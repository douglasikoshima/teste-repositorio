<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<netui-template:template templatePage="/resources/jsp/templateUsuarioArvore.jsp">
    <netui-template:section name="bodySection">
    
        <script>
            <%=(String)request.getAttribute("arvoreBaixa")%>
            <%request.setAttribute("arvoreBaixa",(String)request.getAttribute("arvoreBaixa"));%>
        </script>

    <body>
    
    <script language="javascript" for="window" event="onload">
        <!--   
            parent.parent.oculta_div();
        -->
    </script> 
    
    </body>
    </netui-template:section>
</netui-template:template>