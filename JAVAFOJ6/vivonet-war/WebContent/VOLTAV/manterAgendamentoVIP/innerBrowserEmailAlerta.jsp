<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="FormManterBanner" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formUploadBanner" type="VOLTAV.manterBanner.ManterBannerController.FormUploadBanner" />

<html>
    <head>
            <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

    </head>

    <body>
        <div id="manter_banner_lista_inner">
                   <logic:greaterThan name="FormManterBanner" property="listaLength" value="0">
  
                        
                    <table align="center">
                        <tr>
                            <td>
                                <logic:notEqual name="FormManterBanner" property="paginaAtual" value="0">
                                    <a href="Javascript:paginarPesquisa('-1', true);"><<</a>
                                </logic:notEqual>
                                    <%
                                        for(int i = 0; i < Integer.parseInt(FormManterBanner.getTotalPaginas()); i++){
                                    %> 
                                        <%if(FormManterBanner.getPaginaAtual().equals(String.valueOf(i))){%>
                                            <%=i+1%>
                                        <%}else{%>
                                            <a href="Javascript:paginarPesquisa('<%=i%>', false);"><%=i+1%></a>
                                        <%}%>
                                    <%}%>
                                    
                                 <%if(!FormManterBanner.getTotalPaginas().equals(String.valueOf(Integer.parseInt(FormManterBanner.getPaginaAtual())+1))){%>
                                    <a href="Javascript:paginarPesquisa('1', true);">>></a>
                                <%}%>
                            </td>
                        </tr>
                    </table>  
                    
                    <script>
                        if(window.top.frameApp.document.getElementById('btAssociar'))
                            window.top.frameApp.document.getElementById('btAssociar').style.visibility = 'visible';
                        
                        if(window.top.frameApp.document.getElementById('btDesassociar'))
                            window.top.frameApp.document.getElementById('btDesassociar').style.visibility = 'visible';
                    </script>                    
        </logic:greaterThan>
        
        <logic:equal name="FormManterBanner" property="listaLength" value="0">
            <table width="100%">
                <tr>
                    <td align="center">
                        <b>* Não existem dados para a pesquisa realizada.</b>
                    </td>
                </tr>
            </table>

            <script>
                if(window.top.frameApp.document.getElementById('btAssociar'))
                    window.top.frameApp.document.getElementById('btAssociar').style.visibility = 'hidden';
                
                if(window.top.frameApp.document.getElementById('btDesassociar'))
                    window.top.frameApp.document.getElementById('btDesassociar').style.visibility = 'hidden';
            </script>              
            
        </logic:equal>
        
             <script>
                window.top.frameApp.oculta_div();
                window.top.frameApp.idGrupoBannerArray = new Array(50);
                window.top.frameApp.idBannerArray      = new Array(50);
                window.top.frameApp.idAreaBannerArray  = new Array(50);
            </script>  
        
        <html:hidden name="FormManterBanner" property="paginaAtual"/>
        
        </div>
        
    <script>
        window.top.frameApp.document.getElementById("lista_manter_banner").innerHTML = document.getElementById('manter_banner_lista_inner').innerHTML;
        if (window.top.frameApp.document.getElementById("divAssociarBanner") != null) 
            window.top.frameApp.document.getElementById("divAssociarBanner").style.display = "none";
            
    </script>

    <vivo:alert atributo="msgStatus" scope="request"/>

</body>

</html>