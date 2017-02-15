<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="FormArvoreConsulta" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreConsulta"/>

<bean:define id="aRetornoArvore" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreConsulta.dadosRetorno"/>

    <head>
        <title>Arvore Parâmetros</title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        
        <script language="Javascript">        

            // Paginação de Ítem selecionados.            
            function next(valor,botao)
            {
                if(botao == '')
                {
                    document.forms[0].paginaSeleciona.value = valor;
                    
                }else
                {
                    document.forms[0].paginaSeleciona.value = parseInt(document.forms[0].paginaSeleciona.value) + parseInt(valor);
                }
                document.forms[0].target = '';
                document.forms[0].action = 'montaLista.do';                
                parent.mostrar_div();
                document.forms[0].submit();
            }
        </script>
    </head>
    <body>
    <vivo:sessao id="sessao" width="790" height="470" label="Tela de Consulta" scroll="yes">
        <html:form action="montaLista.do" onsubmit="return false;">
                <html:hidden name="FormArvoreConsulta" property="paginaSeleciona"/>
                
                <vivo:tbTable  selectable="true" layoutWidth="755" layoutHeight="370" tableWidth="755" styleId="tableTitle" sortable="true">
                    <vivo:tbHeader>
                        <logic:iterate name="FormArvoreConsulta" property="dadosRetorno" id="linhaIdx" indexId="idxContato">
                            <logic:equal name="idxContato"  value="0">
                                <vivo:tbHeaderColumn align="center" width="16" type="string">Contato(<bean:write  name="linhaIdx" property="admContatoVO.nota"/>)</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5" type="number">Valor</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="16" type="string">Carteira(<bean:write  name="linhaIdx" property="admTipoCarteiraSimplVO.nota"/>)</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5" type="number">Valor</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="16" type="string">Segmentação(<bean:write  name="linhaIdx" property="admTipoSegmentacaoVO.nota"/>)</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5" type="number">Valor</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="16" type="string">Procedência(<bean:write  name="linhaIdx" property="admProcedenciaVO.nota"/>)</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5" type="number">Valor</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="16" type="number">Total</vivo:tbHeaderColumn>
                            </logic:equal>
                        </logic:iterate>

                    </vivo:tbHeader>
                    <vivo:tbRows>
                        <logic:iterate name="FormArvoreConsulta" property="dadosRetorno" id="linha">
                            <vivo:tbRow key="linha">
                                <vivo:tbRowColumn>
                                    <bean:write  name="linha" property="admContatoVO.nmContato"/>
                                </vivo:tbRowColumn>

                                <vivo:tbRowColumn>
                                    <bean:write  name="linha" property="admContatoVO.total"/>
                                </vivo:tbRowColumn>

                                <vivo:tbRowColumn>
                                    <bean:write  name="linha" property="admTipoCarteiraSimplVO.dsTipoCarteira"/>
                                </vivo:tbRowColumn>

                                <vivo:tbRowColumn>
                                    <bean:write  name="linha" property="admTipoCarteiraSimplVO.total"/>
                                </vivo:tbRowColumn>

                                <vivo:tbRowColumn>
                                    <bean:write  name="linha" property="admTipoSegmentacaoVO.dsSegmentacao"/>
                                </vivo:tbRowColumn>

                                <vivo:tbRowColumn>
                                    <bean:write  name="linha" property="admTipoSegmentacaoVO.total"/>
                                </vivo:tbRowColumn>

                                <vivo:tbRowColumn>
                                    <bean:write  name="linha" property="admProcedenciaVO.nmProcedencia"/>
                                </vivo:tbRowColumn>

                                <vivo:tbRowColumn>
                                    <bean:write  name="linha" property="admProcedenciaVO.total"/>
                                </vivo:tbRowColumn>

                                <vivo:tbRowColumn>
                                    <bean:write  name="linha" property="total"/>
                                </vivo:tbRowColumn>

                            </vivo:tbRow>
                        </logic:iterate>
                    </vivo:tbRows>
                </vivo:tbTable>

        <table border="0" width="100%">
            <tr>
                <td align="center">
                <% int paginaSelecionada = ((Integer)request.getAttribute("paginaSelecionada")).intValue();
                    if(paginaSelecionada > 0){
                %>
                
                    <a href="javascript:next('-1','true');">Anterior</a>
                
                <%}%>
                <%
                int count = 0;
                int total = ((Integer)request.getAttribute("totalPagina")).intValue();
                int controlaLoop = 0;
                if(total > 30)
                {
                    if(paginaSelecionada >= 14)
                        controlaLoop = paginaSelecionada-14;
                }
                
                if(controlaLoop > 0)
                %>
                    ...
                <%
                  for(int i=controlaLoop;i < total;i++){%>
                    <%if(count < 30){%>
                        <%if(i == paginaSelecionada){%>
                            <%=i+1%>
                        <%}else{%>
                            <a href="javascript:next('<%=i%>', '')"><%=i+1%></a>
                        <%}%>
                    <%}else{%>
                        ...
                    <%
                    break;
                    }%>
                
                <%++count;}%>
	                <logic:equal name="FormArvoreConsulta" property="inProximo" value="">
	                    <a href="javascript:next('1','true');">Próximo</a>
	                </logic:equal>
                </td>
            </tr>
        </table>
        <script language="Javascript">        
            parent.oculta_div();
            DoResizeHeaderTableVivo();
        </script>
        
        
        </html:form>
</body>
        
    </vivo:sessao> 
