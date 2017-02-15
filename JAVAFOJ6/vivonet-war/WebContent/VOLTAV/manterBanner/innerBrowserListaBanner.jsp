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
                        <vivo:tbTable selectable="true" layoutWidth="745" layoutHeight="200" tableWidth="745" styleId="tableTitle" sortable="false">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="5%"  type="string"><input type="checkbox" name="chk_banner_all" class="radio"  onclick="checaTodos(this);"></vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%"  type="string">UF</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="10%" type="string">Area do Banner</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="15%" type="string">Tipo de Linha</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="15%" type="string">Grupo Usuário</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="30%" type="string">Link do Banner</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="15%" type="string">Descrição</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%"  type="string">&nbsp;</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate name="FormManterBanner" property="resultadoPesquisaBanner" id="lista" indexId="id">
                                    <vivo:tbRow key="linha">
                                        <vivo:tbRowColumn>
                                            <logic:equal name="lista" property="idBanner" value="">
                                                <input type="checkbox" name="chk_banner<%=id%>" value="0;<bean:write name="lista" property="idAreaBanner" />;<bean:write name="lista" property="idGrupoBanner" />;<%=id%>" class="radio" onclick="checkLista(this);">
                                            </logic:equal>
                                            <logic:notEqual name="lista" property="idBanner" value="">
                                                <input type="checkbox" name="chk_banner<%=id%>" value="<bean:write name="lista" property="idBanner" />;<bean:write name="lista" property="idAreaBanner" />;<bean:write name="lista" property="idGrupoBanner" />;<%=id%>" class="radio" onclick="checkLista(this);">                                            
                                            </logic:notEqual>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <bean:write name="lista" property="sgUF" />
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <bean:write name="lista" property="dsAreaBanner" />
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <bean:write name="lista" property="dsTipoLinha" />
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <bean:write name="lista" property="nmGrupo" />
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <vivo:hint maxLength="80"><bean:write name="lista" property="urlBanner" /></vivo:hint>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <vivo:hint maxLength="10"><bean:write name="lista" property="dsBanner" /></vivo:hint>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                                <logic:notEqual name="lista" property="idBanner" value="">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" border="0" alt="Visualizar Banner" onclick="loadImagem('<bean:write name="lista" property="nmBanner" />', '<bean:write name="lista" property="dsTipoBanner" />','<bean:write name="lista" property="idAreaBanner" />');">
                                                </logic:notEqual>
                                        </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>    
                        
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