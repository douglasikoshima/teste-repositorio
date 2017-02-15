<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="admsistemas.admArvoreSatisfacao.admArvoreSatisfacaoController.FormArvoreSatisfacao"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormArvoreSatisfacao"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreSatisfacao"/>

<bean:define id="ListaObjetos"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreSatisfacao.admListaObjetosSatisfacaoVO"/>
    <head>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    </head>
    <acesso:controlHiddenItem nomeIdentificador="adm_manu_verpagina">
    <%if(request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("incluir")){%>
        <form action="salvaPerguntaNova.do" name="formArvoreSatisfacao" method="post">
            <script language="Javascript">        
            function enviar()
            {
                if(trim(document.forms[0].dsPerguntaFuncao.value) == '')
                {
                    alert('Descrição é um campo obrigatório.');
                    
                }else if(trim(document.forms[0].dsScriptPerguntaFuncao.value) == '')
                {
                    alert('Script é um campo obrigatório.');
                    
                }else if(document.forms[0].inEncerramentoPerguntaFuncao.value == '')
                {
                    alert('Encerra Questionário é um campo obrigatório.');
                                    
                }else if(document.forms[0].inDisponibilidadePerguntaFuncao.value == '')
                {
                    alert('Disponível é um campo obrigatório.');                
                    
                }else if(document.forms[0].inObrigatoriaFuncao.value == '')
                {
                    alert('Obrigatória é um campo obrigatório.');                
                    
                }else if(document.forms[0].arrayListaObjetosArvoreSatisfacao.value == '')
                {
                    alert('Tipo é um campo obrigatório.');                    
                }
                else
                {
                    document.forms[0].target ='_parent';
                    document.forms[0].action = 'salvaPerguntaNova.do';
                    parent.mostrar_div();
                    document.forms[0].submit();
                } 
            }
            </script>
            <input type="hidden" name="idQuestionarioFuncao" value="<%=request.getParameter("idQuestionario")%>"/>
            <input type="hidden" name="dsQuestionarioFuncao" value="<%=request.getParameter("dsQuestionario")%>"/>
            <table width="100%" border="0">
                <tr>
                    <td valign="top">
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                        Descri&ccedil;&atilde;o:
                    </td>
                    <td valign="top" width="160" align="left">
                        <html:textarea name="FormArvoreSatisfacao" property="dsPerguntaFuncao" style="width:165px;height:65;font-family:Tahoma;font-size:11px;" onkeypress="return verificaEnter(event);" onkeyup="checaTextarea(this,240);"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">Script:</td>
                    <td>
                        <html:textarea name="FormArvoreSatisfacao" property="dsScriptPerguntaFuncao" style="width:165px;height:65;font-family:Tahoma;font-size:11px;" onkeypress="return verificaEnter(event);" onkeyup="checaTextarea(this,240);"/>
                    </td>
                </tr>
                <tr>
                    <td>Encerra Questionário?</td>
                    <td>
                        <html:select name="FormArvoreSatisfacao" property="inEncerramentoPerguntaFuncao" style="width:95px;">
                            <html:option value="">-- Selecione --</html:option>
                            <html:option value="1">Sim</html:option>
                            <html:option value="0">Não</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td>Disponível?</td>
                    <td>
                        <html:select name="FormArvoreSatisfacao" property="inDisponibilidadePerguntaFuncao" style="width:95px;">
                            <html:option value="">-- Selecione --</html:option>
                            <html:option value="1">Sim</html:option>
                            <html:option value="0">Não</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td>Obrigatória?</td>
                    <td>
                        <html:select name="FormArvoreSatisfacao" property="inObrigatoriaFuncao" style="width:95px;">
                            <html:option value="">-- Selecione --</html:option>
                            <html:option value="1">Sim</html:option>
                            <html:option value="0">Não</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td>Tipo:</td>
                    <td>
                    
                        <html:select name="FormArvoreSatisfacao" property="arrayListaObjetosArvoreSatisfacao"  size="1" styleClass="SELECT" style="width:95px;">
                            <html:option value="">-- Selecione --</html:option>
                            <html:options collection="ListaObjetos" property="idTipoApresentacaoPergunta" labelProperty="dsTipoApresentacaoPergunta" /> 
                        </html:select>
                    </td>
                </tr> 
                <tr>
                    <td align="right" colspan="2">
                    <acesso:controlHiddenItem nomeIdentificador="adm_manu_salnov">
                        <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none;" onClick="enviar(); return false"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            
        <script language="javascript" for="window" event="onload">
        <!--   
             parent.oculta_div();
        -->
        </script> 
            
            
    </form>
<%}else{%>    
        <form action="salvaPerguntaExistente.do" name="formArvoreSatisfacao" method="post">
            <script language="Javascript">
            function enviar()
            {
                if(trim(document.forms[0].dsPerguntaFuncao.value) == '')
                {
                    alert('Descrição é um campo obrigatório.');
                    
                }else if(trim(document.forms[0].dsScriptPerguntaFuncao.value) == '')
                {
                    alert('Script é um campo obrigatório.');
                    
                }else if(document.forms[0].inEncerramentoPerguntaFuncao.value == '')
                {
                    alert('Encerra Questionário é um campo obrigatório.');
                                    
                }else if(document.forms[0].inDisponibilidadePerguntaFuncao.value == '')
                {
                    alert('Disponível é um campo obrigatório.');                
                    
                }else if(document.forms[0].inObrigatoriaFuncao.value == '')
                {
                    alert('Obrigatória é um campo obrigatório.');                
                    
                }else if(document.forms[0].arrayListaObjetosArvoreSatisfacao.value == '')
                {
                    alert('Tipo é um campo obrigatório.');                    
                }
                else
                {
                    document.forms[0].target ='_parent';
                    document.forms[0].action = 'salvaPerguntaExistente.do';
                    parent.mostrar_div();
                    document.forms[0].submit();
                } 
            }
            </script>
            <input type="hidden" name="idTipoPerguntaFuncao" value="<%=request.getParameter("idTipoPerguntaFuncao")%>"/>
            <input type="hidden" name="idQuestionarioFuncao" value="<%=request.getParameter("idQuestionarioFuncao")%>"/>
            <input type="hidden" name="dsQuestionarioFuncao" value="<%=request.getParameter("dsQuestionarioFuncao")%>"/>
            <table width="100%" border="0">
                <tr>
                    <td valign="top">
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                        Descri&ccedil;&atilde;o:
                    </td>
                    <td valign="top" width="160" align="left">
                        <textarea name="dsPerguntaFuncao" style="width:165px;height:65;font-family:Tahoma;font-size:11px;" onkeypress="return verificaEnter(event);" onkeyup="checaTextarea(this,240);"><%=request.getParameter("dsPerguntaFuncao")%></textarea>
                    </td>
                </tr>
                <tr>
                    <td valign="top">Script:</td>
                    <td>
                        <textarea name="dsScriptPerguntaFuncao" style="width:165px;height:65;font-family:Tahoma;font-size:11px;" onkeypress="return verificaEnter(event);" onkeyup="checaTextarea(this,240);"><%=request.getParameter("dsScriptPerguntaFuncao")%></textarea>
                    </td>
                </tr>
                <tr>
                    <td>Encerra Questionário?</td>
                    <td>
                        <%if(request.getParameter("inEncerramentoPerguntaFuncao").equalsIgnoreCase("1")){%>
                            <select name="inEncerramentoPerguntaFuncao"  style="width:95px;">
                                <option value="">-- Selecione --</option>
                                <option value="1" selected>Sim</option>
                                <option value="0">Não</option>
                            </select>

                            
                        <%}else{%>
                            <select name="inEncerramentoPerguntaFuncao"  style="width:95px;">
                                <option value="">-- Selecione --</option>
                                <option value="1">Sim</option>
                                <option value="0" selected>Não</option>
                            </select>
                        <%}%>
                    </td>
                </tr>
                <tr>
                <td>Disponível?</td>
                <td>
                        <%if(request.getParameter("inDisponibilidadePerguntaFuncao").equalsIgnoreCase("1")){%>
                            <select name="inDisponibilidadePerguntaFuncao"  style="width:95px;">
                                <option value="">-- Selecione --</option>
                                <option value="1" selected>Sim</option>
                                <option value="0">Não</option>
                            </select>
                            
                        <%}else{%>
                            <select name="inDisponibilidadePerguntaFuncao"  style="width:95px;">
                                <option value="">-- Selecione --</option>
                                <option value="1">Sim</option>
                                <option value="0" selected>Não</option>
                            </select>
                        <%}%>
                <td>                
                </tr>
                <tr>
                    <td>Obrigatória?</td>
                    <td>                       
                        <%if(request.getParameter("inObrigatoriaFuncao").equalsIgnoreCase("1")){%>
                            <select name="inObrigatoriaFuncao"  style="width:95px;">
                                <option value="">-- Selecione --</option>
                                <option value="1" selected>Sim</option>
                                <option value="0">Não</option>
                            </select>
                            
                        <%}else{%>
                            <select name="inObrigatoriaFuncao"  style="width:95px;">
                                <option value="">-- Selecione --</option>
                                <option value="1">Sim</option>
                                <option value="0" selected>Não</option>
                            </select>
                        <%}%>                        
                    </td>
                </tr>
                <tr>
                    <td>Tipo:</td>
                    <td>
                    
                        <html:select name="FormArvoreSatisfacao" property="arrayListaObjetosArvoreSatisfacao" styleClass="SELECT" style="width:95px;">
                            <!--option value="< %=request.getParameter("idTipoApresentacaoPerguntaFuncao")%>" selected>< %=request.getParameter("dsTipoApresentacaoPerguntaFuncao")%></option-->
                            <html:option value="">-- Selecione --</html:option>
                            <html:options collection="ListaObjetos" property="idTipoApresentacaoPergunta" labelProperty="dsTipoApresentacaoPergunta" /> 
                        </html:select>
                    </td>
                </tr> 
                <tr>
                    <td align="right" colspan="2">
                    <acesso:controlHiddenItem nomeIdentificador="adm_manu_salex">
                        <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none;" onClick="enviar(); return false;"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>

        <script language="javascript" for="window" event="onload">
        <!--   
             parent.oculta_div();
        -->
        </script> 
            
            
    </form>    
    <script>
        var tipo = '<%=request.getParameter("dsTipoApresentacaoPerguntaFuncao")%>';
        var oOption = document.getElementById('arrayListaObjetosArvoreSatisfacao');
        for(i = 0; i < oOption.options.length; i++ )
        {
            if(oOption.options(i).innerText == tipo)
            {
                oOption.options(i).selected=true;
            }
        }
    </script>

<%}%>    
    <script>
        document.body.style.backgroundColor = '#E3ECF4';
    </script>
</acesso:controlHiddenItem>
</body>
    