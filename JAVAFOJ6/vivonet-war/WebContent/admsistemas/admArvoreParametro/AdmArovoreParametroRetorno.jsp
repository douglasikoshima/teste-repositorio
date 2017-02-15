<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="FormArvoreParametro" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro"/>

<!-- Variaveis de Grupo -->
<bean:define id="aGrupo" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.admArvoreGrupoRetorno"/>
<!-- Variaveis de Encaminhamento -->
<bean:define id="aVariavel" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.admVariavelRetorno"/>
<bean:define id="aRetornoArvore" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.admArvoreRetorno"/>


    <head>
        <title>Arvore Parâmetros</title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        
        <script language="Javascript">        

            // Paginação de Ítem selecionados.            
            function next(valor)
            {
                document.forms[0].idContatoSelecionado.value = parseInt(document.forms[0].idContatoSelecionado.value) + parseInt(valor);
                document.forms[0].target = '';
                document.forms[0].action = 'montaLista.do';                
                parent.mostrar_div();
                document.forms[0].submit();
            }
        </script>
    </head>
    <body>
    <vivo:sessao id="sessao" width="790" height="470" label="Tela de Consulta" scroll="no">
        <html:form action="montaLista.do" onsubmit="return false;">
        <html:hidden name="FormArvoreParametro" property="idContatoSelecionado"/>
        <html:hidden name="FormArvoreParametro" property="inGrupos"/>
        <html:hidden name="FormArvoreParametro" property="inVariaveis"/>
        <html:hidden name="FormArvoreParametro" property="inRetorno"/>
            Ítem Selecionado: <bean:write name="aRetornoArvore" property="nmContato" />
      <!--logic:notEqual name="FormArvoreParametro" property="inRetorno" value="" -->
      <logic:notEqual name="FormArvoreParametro" property="inGrupos" value="">
            <vivo:tbTable  selectable="true" onRowClick="" layoutWidth="760" layoutHeight="180" tableWidth="1500" styleId="Table1" sortable="true" >            <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="9%" type="string">Grupo</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="9%" type="string">Fase</vivo:tbHeaderColumn>
                    <logic:equal name="FormArvoreParametro"  property="inGrupos" value="1">
                        <logic:equal name="FormArvoreParametro"  property="inVariaveis" value="1">
                            <vivo:tbHeaderColumn align="center" width="9%" type="string">Canal de Entrada</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="9%" type="string">Procedência</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="9%" type="string">Linha</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="9%" type="string">Segmentação</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="19%" type="string">Carteirização</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="9%" type="string">Natureza</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="9%" type="string">Pessoa</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="9%" type="string">Grupo Abertura</vivo:tbHeaderColumn> 
                        </logic:equal>
                    </logic:equal>
                </vivo:tbHeader>
                <vivo:tbRows> 
                    <logic:iterate name="aGrupo" id="grupoArray">
                        <vivo:tbRow key="Linha1">
                            <vivo:tbRowColumn>
                                <bean:write name="grupoArray" property="nmGrupo" />
                            </vivo:tbRowColumn>
        
                            <vivo:tbRowColumn>
                                <logic:iterate name="grupoArray" property="admGrupoFaseVOArray" id="idxTipoSequenciaGrp">
                                    <bean:write name="idxTipoSequenciaGrp"  property="dsTipoSequencia"/><BR>      
                                </logic:iterate>
                            </vivo:tbRowColumn>
    
                            <logic:equal name="FormArvoreParametro"  property="inGrupos" value="1">
                                <logic:equal name="FormArvoreParametro"  property="inVariaveis" value="1">
                                    <vivo:tbRowColumn>
                                        <logic:iterate name="grupoArray" property="admCanalEntradaVOArray" id="idxCanalEntradaGrp">
                                            <bean:write name="idxCanalEntradaGrp"  property="nmCanalEntrada"/><br>     
                                        </logic:iterate>
                                    </vivo:tbRowColumn>
                
                                    <vivo:tbRowColumn>
                                        <logic:iterate name="grupoArray" property="admProcedenciaVOArray" id="idxProcedenciaGrp">
                                            <bean:write name="idxProcedenciaGrp"  property="nmProcedencia"/><br>       
                                        </logic:iterate>
                                    </vivo:tbRowColumn>
                
                                    <vivo:tbRowColumn>
                                        <logic:iterate name="grupoArray" property="admTipoLinhaSimplVOArray" id="idxTipoLinhaGrp">
                                            <bean:write name="idxTipoLinhaGrp"  property="dsTipoLinha"/><br>     
                                        </logic:iterate>
                                    </vivo:tbRowColumn>
                                    
                                    <vivo:tbRowColumn>
                                        <logic:iterate name="grupoArray" property="admSegmentacaoSimplVOArray" id="idxSegmentacaoGrp">
                                            <bean:write name="idxSegmentacaoGrp"  property="dsSegmentacao"/><br>     
                                        </logic:iterate>
                                    </vivo:tbRowColumn>
                                    
                                    <vivo:tbRowColumn>
                                        <logic:iterate name="grupoArray" property="admCarteirizacaoVOArray" id="idxCarteirizacaoGrp">
                                            <bean:write name="idxCarteirizacaoGrp"  property="nmCarteirizacao"/><br>        
                                        </logic:iterate>
                                    </vivo:tbRowColumn>
                                    
                                    <vivo:tbRowColumn>
                                        <logic:iterate name="grupoArray" property="admTipoPessoaVOArray" id="idxTipoPessoaGrp">
                                            <bean:write name="idxTipoPessoaGrp"  property="dsTipoPessoa"/><br>      
                                        </logic:iterate>
                                    </vivo:tbRowColumn>
                                    
                                    <vivo:tbRowColumn>
                                        <logic:iterate name="grupoArray" property="admTipoRelacionamentoVOArray" id="idxTipoRelacionamentoGrp">
                                            <bean:write name="idxTipoRelacionamentoGrp"  property="nmTipoRelacionamento"/><br>      
                                        </logic:iterate>
                                    </vivo:tbRowColumn>
                                    
                                    <vivo:tbRowColumn>
                                        <logic:iterate name="grupoArray" property="admGrupoAberturaVOArray" id="idxGrupoAberturaGrp">
                                            <bean:write name="idxGrupoAberturaGrp"  property="nmGrupo"/>        
                                        </logic:iterate>
                                    </vivo:tbRowColumn>
                                </logic:equal>
                            </logic:equal>
        
                        </vivo:tbRow> 
                    </logic:iterate>                      
                </vivo:tbRows>
            </vivo:tbTable>
        </logic:notEqual>        
        <!--/logic:notEqual -->
      <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>  
      <logic:equal name="FormArvoreParametro" property="inRetorno" value="1">
            <vivo:tbTable  selectable="true" onRowClick="" layoutWidth="760" layoutHeight="180" tableWidth="1500" styleId="Table2" sortable="true">            <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Retorno</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Canal de Entrada</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Procedência</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Linha</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Segmentação</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="20%" type="string">Carteirização</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Natureza</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Pessoa</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Grupo Abertura</vivo:tbHeaderColumn> 
                </vivo:tbHeader>
                <vivo:tbRows>     
                    <logic:iterate name="aVariavel" id="arrayVariavel">                    
                        <vivo:tbRow key="Linha1">
                            <vivo:tbRowColumn>
                                <bean:write name="arrayVariavel" property="dsTipoRetornoContato" />
                            </vivo:tbRowColumn>
                            
                            <vivo:tbRowColumn>
                                <logic:iterate name="arrayVariavel" property="admCanalEntradaVOArray" id="idxRetCanalEntrada">
                                    <bean:write name="idxRetCanalEntrada"  property="nmCanalEntrada"/><br> 
                                </logic:iterate>
                            </vivo:tbRowColumn>
                            
                            <vivo:tbRowColumn>
                                <logic:iterate name="arrayVariavel" property="admProcedenciaVOArray" id="idxRetProcedencia">
                                    <bean:write name="idxRetProcedencia"  property="nmProcedencia"/><br>   
                                </logic:iterate>
                            </vivo:tbRowColumn>
                            
                            <vivo:tbRowColumn>
                                <logic:iterate name="arrayVariavel" property="admTipoLinhaSimplVOArray" id="idxRetTipoLinha">
                                    <bean:write name="idxRetTipoLinha"  property="dsTipoLinha"/><br>    
                                </logic:iterate>
                            </vivo:tbRowColumn>
                            
                            <vivo:tbRowColumn>
                                <logic:iterate name="arrayVariavel" property="admSegmentacaoSimplVOArray" id="idxRetSegmentacao">
                                    <bean:write name="idxRetSegmentacao"  property="dsSegmentacao"/><br>    
                                </logic:iterate>
                            </vivo:tbRowColumn>
                            
                            <vivo:tbRowColumn>
                                <logic:iterate name="arrayVariavel" property="admCarteirizacaoVOArray" id="idxRetCarteirizacao">
                                    <bean:write name="idxRetCarteirizacao"  property="nmCarteirizacao"/><br>     
                                </logic:iterate>
                            </vivo:tbRowColumn>
                            
                            <vivo:tbRowColumn>
                                <logic:iterate name="arrayVariavel" property="admTipoPessoaVOArray" id="idxRetTipoPessoa">
                                    <bean:write name="idxRetTipoPessoa"  property="dsTipoPessoa"/><br>     
                                </logic:iterate>
                            </vivo:tbRowColumn>
                            
                            <vivo:tbRowColumn>
                                <logic:iterate name="arrayVariavel" property="admTipoRelacionamentoVOArray" id="idxReRelacionamento">
                                    <bean:write name="idxReRelacionamento"  property="nmTipoRelacionamento"/><br>   
                                </logic:iterate>
                            </vivo:tbRowColumn>
                            
                            <vivo:tbRowColumn>
                                <logic:iterate name="arrayVariavel" property="admGrupoAberturaVOArray" id="idxRetGrupoAbertura">
                                    <bean:write name="idxRetGrupoAbertura"  property="nmGrupo"/>        
                                </logic:iterate>
                            </vivo:tbRowColumn>
                            
                        </vivo:tbRow> 
                    </logic:iterate>                      
                </vivo:tbRows>
            </vivo:tbTable>
    </logic:equal>
        <table border="0" width="100%">
            <tr>
                <td align="left">&nbsp;</td>
                <td align="right">
                    <logic:notEqual name="FormArvoreParametro" property="idContatoSelecionado" value="0">
                        <img onClick="next(-1);" id="btAnterior" src="/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pag_anterior_over.gif'" style="border:nonecursor:hand;"/>
                    </logic:notEqual>
                    <logic:equal name="FormArvoreParametro" property="inProximo" value="1">
                        <img onClick="next(1);" id="btProximo" src="/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_prox_pag_over.gif'" style="border:nonecursor:hand;"/>
                    </logic:equal>
                </td>
            </tr>
        </table>
        <script language="Javascript">        
            parent.oculta_div();
        </script>
        
        <vivo:alert atributo="msgStatus" scope="request"/>
        
        
        </html:form>
</body>
        
    </vivo:sessao> 
