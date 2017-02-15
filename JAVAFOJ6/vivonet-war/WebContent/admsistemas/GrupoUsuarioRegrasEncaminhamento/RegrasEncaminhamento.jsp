<!--
Módulo.....: Gestão de Sistemas
Caso de Uso: Configurar Grupo de Usuário com Regras de Encaminhamento
Versão.....: $Author: a5112272 $ - $Revision: 1.4 $ - $Date: 2011/06/14 14:34:06 $
-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm" />
<bean:define id="ClienteDisp"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.tipoClienteVOArray" />
<bean:define id="ClienteAssoc" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.tipoClienteVOArray" />
<bean:define id="SegmenDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.segmentacaoVOArray" />
<bean:define id="SegmenAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.segmentacaoVOArray" />
<bean:define id="CarterDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.carterizacaoVOArray" />
<bean:define id="CarterAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.carterizacaoVOArray" />
<bean:define id="ProcedDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.procedenciaVOArray" />
<bean:define id="ProcedAssoc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoSelecionadoVO.procedenciaVOArray" />
<bean:define id="aGrupoVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.AGrupoVO" />

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> Administração de Sitemas"/>
    <netui-template:setAttribute name="modulo" value="Administração Sistemas"/>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuAdmSistemas.jsp" />
        
        <!--APLICACAO-->
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <vivo:sessao id="qdMain" height="475" width="790" label="Grupo de Usuário com Regras de Encaminhamento" operacoes="Configuração">
                <form id="RegrasEncaminhamento" action="AplicarRegrasEncaminhamento.do" name="usuarioEncaminhamentoForm" method="post">
                    <html:hidden name="Form" property="codigoGrupo"/>
                    <br>&nbsp;&nbsp;&nbsp;<html:select name="Form" property="grupoVOSelecionado.codigo" size="10" onclick="ObtemRegrasIFrame(this);" style="width=250px;">
                        <html:options collection="aGrupoVO" property="codigo" labelProperty="descricao" /> 
                    </html:select>
                    
                    <br><br>
                    <table align="center" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td>
                                <vivo:quadro id="qdTipoCliente" height="170" width="370" label="Tipo Cliente">
                                    <table border="0">
                                        <tr>
                                            <td>
                                                Dispon&iacute;veis<br/>
                                                <html:select name="Form" property="tipoClienteDisponivel" multiple="true" size="10" style="width=160px"/>
                                            </td>
                                            <td>
                                                <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.tipoClienteDisponivel, document.usuarioEncaminhamentoForm.tipoClienteAssociada);return false;"/>
                                                <vivo:botao id="bt02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.tipoClienteDisponivel, document.usuarioEncaminhamentoForm.tipoClienteAssociada);return false;"/>
                                                <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.tipoClienteAssociada, document.usuarioEncaminhamentoForm.tipoClienteDisponivel);return false;"/>
                                                <vivo:botao id="bt04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.tipoClienteAssociada, document.usuarioEncaminhamentoForm.tipoClienteDisponivel);return false;"/>                                                
                                            </td>
                                            <td>
                                                Associada<br/>
                                                <html:select name="Form" property="tipoClienteAssociada" multiple="true" size="10" style="width=160px"/>
                                            </td>
                                        </tr>
                                    </table>
                                </vivo:quadro>                            
                            </td>
                            <td>&nbsp;</td>
                            <td>
                                <vivo:quadro id="qdSegmentacao" height="170" width="370" label="Segmentação">
                                    <table border="0">
                                        <tr>
                                            <td>
                                                Dispon&iacute;veis<br>
                                                <html:select name="Form" property="segmentacaoDisponivel" multiple="true" size="10" style="width=160px">
                                                    
                                                </html:select>
                                            </td>
                                            <td>
                                                <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.segmentacaoDisponivel, document.usuarioEncaminhamentoForm.segmentacaoAssociada);return false;"/>
                                                <vivo:botao id="bt02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.segmentacaoDisponivel, document.usuarioEncaminhamentoForm.segmentacaoAssociada);return false;"/>
                                                <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.segmentacaoAssociada, document.usuarioEncaminhamentoForm.segmentacaoDisponivel);return false;"/>
                                                <vivo:botao id="bt04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.segmentacaoAssociada, document.usuarioEncaminhamentoForm.segmentacaoDisponivel);return false;"/>                                                                                            
                                            </td>
                                            <td>
                                                Associados<br>                    
                                                <html:select name="Form" property="segmentacaoAssociada" multiple="true" size="10" style="width=160px">
                                                    
                                                </html:select>
                                            </td>
                                        </tr>
                                    </table>
                                </vivo:quadro>
                            </td>
                        </tr>
                        <tr>
                            <td colspan=3 style="height=5px;"></td>
                        </tr>
                        <tr>
                            <td>
                                <vivo:quadro id="qdCarterizacao" height="170" width="370" label="Carteirização">
                                    <table border="0">
                                        <tr>
                                            <td>
                                                Dispon&iacute;veis<br>
                                                <html:select name="Form" property="carterizacaoDisponivel" multiple="true" size="10" style="width=160px">
                                                    
                                                </html:select>
                                            </td>
                                            <td>
                                                <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.carterizacaoDisponivel, document.usuarioEncaminhamentoForm.carterizacaoAssociada);return false;"/>
                                                <vivo:botao id="bt02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.carterizacaoDisponivel, document.usuarioEncaminhamentoForm.carterizacaoAssociada);return false;"/>
                                                <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.carterizacaoAssociada, document.usuarioEncaminhamentoForm.carterizacaoDisponivel);return false;"/>
                                                <vivo:botao id="bt04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.carterizacaoAssociada, document.usuarioEncaminhamentoForm.carterizacaoDisponivel);return false;"/>                                                
                                            </td>
                                            <td>
                                                Associados<br>                    
                                                <html:select name="Form" property="carterizacaoAssociada" multiple="true" size="10" style="width=160px">
                                                    
                                                </html:select>
                                            </td>
                                        </tr>
                                    </table>
                                </vivo:quadro>
                            </td>
                            <td>&nbsp;</td>
                            <td>
                                <vivo:quadro id="qdProcedencia" height="170" width="370" label="Procedência">
                                    <table border="0">
                                        <tr>
                                            <td>
                                                Dispon&iacute;veis<br>
                                                <html:select name="Form" property="procedenciaDisponivel" multiple="true" size="10" style="width=160px">
                                                    
                                                </html:select>
                                            </td>
                                            <td>
                                                <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.procedenciaDisponivel, document.usuarioEncaminhamentoForm.procedenciaAssociada);return false;"/>
                                                <vivo:botao id="bt02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.procedenciaDisponivel, document.usuarioEncaminhamentoForm.procedenciaAssociada);return false;"/>
                                                <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.usuarioEncaminhamentoForm.procedenciaAssociada, document.usuarioEncaminhamentoForm.procedenciaDisponivel);return false;"/>
                                                <vivo:botao id="bt04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.usuarioEncaminhamentoForm.procedenciaAssociada, document.usuarioEncaminhamentoForm.procedenciaDisponivel);return false;"/>                                                
                                            </td>
                                            <td>
                                                Associados<br>                    
                                                <html:select name="Form" property="procedenciaAssociada" multiple="true" size="10" style="width=160px">
                                                    
                                                </html:select>
                                            </td>
                                        </tr>
                                    </table>
                                </vivo:quadro>
                            </td>
                        </tr>
                        <tr>
                            <td colspan=3 style="height=5px;"></td>
                        </tr>
                        <tr>
                            <td align="center" colspan=3><vivo:botao id="bt01" width="60px" height="10px" value="Aplicar" styleClass="btTemplate" onclick="gravar();"/></td>
                        </tr>
                    </table>
                    <iframe scrolling="yes" style="visibility:hidden;" name="iframe" height="1px" width="1px"></iframe>
                </form>
            </vivo:sessao>
            <script language=javascript>
                var actionSalvar = document.forms["usuarioEncaminhamentoForm"].action;
                
                function ObtemRegrasIFrame(grupo) {
                    //Liga animação
                    if( top.dvAnimarAguarde != null ) top.startAnimation();

                    //Informa o código selecionado
                    document.forms["usuarioEncaminhamentoForm"].elements["codigoGrupo"].value = grupo.value;
                    
                    //Monta o path seleção
                    document.forms["usuarioEncaminhamentoForm"].target = "iframe";
                    document.forms["usuarioEncaminhamentoForm"].action = "ObtemRegrasEncaminhamento.do";
                    document.forms["usuarioEncaminhamentoForm"].submit();
                }
                
                function transfereSelecaoLista(listaOrigem, listaDestino)
                {
                    var i;
                    for(i = 0; i<listaOrigem.options.length; i++)
                        if(listaOrigem.options[i].selected)
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                            
                    for(i = listaOrigem.options.length-1; i>=0; i--)
                        if(listaOrigem.options[i].selected)
                            listaOrigem.options[i] = null;

                    return false;
                }
            
                function transfereSelecaoListaTodos(listaOrigem, listaDestino)
                {
                    var i;
                    for(i = 0; i<listaOrigem.options.length; i++)
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    for(i = listaOrigem.options.length-1; i>=0; i--)
                        listaOrigem.options[i] = null;
                    return false;
                }
                
                function gravar(){
                    //Liga animação
                    if( top.dvAnimarAguarde != null ) top.startAnimation();

                    //Processa gravação
                    var form = document.usuarioEncaminhamentoForm;
                    var campo = new Array(form.tipoClienteDisponivel, form.tipoClienteAssociada, 
                                          form.segmentacaoDisponivel, form.segmentacaoAssociada,
                                          form.carterizacaoDisponivel, form.carterizacaoAssociada,
                                          form.procedenciaDisponivel, form.procedenciaAssociada);
                                 
                    for(x = 0; x < 8; x++){
                        temp = campo[x];                    
                        for( y = 0; y < temp.options.length; y++ )
                            temp.options[y].selected = true;
                    } 
    
                    //Monta o path seleção
                    document.forms["usuarioEncaminhamentoForm"].target = "iframe";
                    document.forms["usuarioEncaminhamentoForm"].action = actionSalvar;
                    document.forms["usuarioEncaminhamentoForm"].submit();
                }
                
                //Fim animação
                if( top.dvAnimarAguarde != null ) top.stopAnimation();
            </script>
    </netui-template:section>
</netui-template:template>