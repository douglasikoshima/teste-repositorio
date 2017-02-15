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
<head>

    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>

</head>
<body>
<acesso:controlHiddenItem nomeIdentificador="adm_iares_verpagina">
    <%if(request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("incluir")){%>
        <form action="salvaRespostaNova.do" name="formArvoreSatisfacao" method="post">
            <script language="Javascript">
                function salvaResposta()
                {
                    if(trim(document.forms[0].dsRespostaFuncao.value) == '')
                    {
                        alert('Descrição é um campo obrigatório.');
                        return false;
                        
                    }else if(trim(document.forms[0].dsScriptRespostaFuncao.value) == '')
                    {
                        alert('Script é um campo obrigatório.');
                        return false;
                        
                    }else if(document.forms[0].inEncerramentoRespostaFuncao.value == '')
                    {
                        alert('Encerra Pergunta é um campo obrigatório.');
                        return false;
                        
                    }else if(document.forms[0].inDisponibilidadeRespostaFuncao.value == '')
                    {
                        alert('Disponível é um campo obrigatório.');
                        return false;
                    }
                    else
                    {
                    
                        document.forms[0].target= '_parent';
                        document.forms[0].action = 'salvaRespostaNova.do';
                        parent.mostrar_div();
                        document.forms[0].submit();
                    }
                }
            </script>
            <input type="hidden" name="idTipoPerguntaFuncao" value="<%=request.getParameter("idTipoPerguntaFuncao")%>"/>
            <input type="hidden" name="dsPerguntaFuncao" value="<%=request.getParameter("dsPerguntaFuncao")%>"/>
            <input type="hidden" name="dsScriptPerguntaFuncao" value="<%=request.getParameter("dsScriptPerguntaFuncao")%>"/>
            <input type="hidden" name="idTipoApresentacaoPerguntaFuncao" value="<%=request.getParameter("idTipoApresentacaoPerguntaFuncao")%>"/>
            <input type="hidden" name="inDisponibilidadePerguntaFuncao" value="<%=request.getParameter("inDisponibilidadePerguntaFuncao")%>"/>
            <input type="hidden" name="inEncerramentoPerguntaFuncao" value="<%=request.getParameter("inEncerramentoPerguntaFuncao")%>"/>
            <input type="hidden" name="inObrigatoriaFuncao" value="<%=request.getParameter("inObrigatoriaFuncao")%>"/>
            <input type="hidden" name="sqApresentacaoPerguntaFuncao" value="<%=request.getParameter("sqApresentacaoPerguntaFuncao")%>"/>
            <input type="hidden" name="idQuestionarioFuncao" value="<%=request.getParameter("idQuestionarioFuncao")%>"/>
            <input type="hidden" name="dsQuestionarioFuncao" value="<%=request.getParameter("dsQuestionarioFuncao")%>"/>
            
            <table width="100%">
                <tr>
                    <td valign="top">
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                        Descri&ccedil;&atilde;o:
                    </td>
                    <td valign="top" width="160" align="left">
                        <html:textarea name="FormArvoreSatisfacao" property="dsRespostaFuncao" style="width:165px;height:80;font-family:Tahoma;font-size:11px;" onkeypress="return verificaEnter(event);" onkeyup="checaTextarea(this,240);"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">Script:</td>
                    <td>
                        <html:textarea name="FormArvoreSatisfacao" property="dsScriptRespostaFuncao" style="width:165px;height:80;font-family:Tahoma;font-size:11px;" onkeypress="return verificaEnter(event);" onkeyup="checaTextarea(this,240);"/>
                    </td>
                </tr>
                <tr>
                    <td>Encerra Pergunta?</td>
                    <td>
                        <html:select name="FormArvoreSatisfacao" property="inEncerramentoRespostaFuncao">
                            <html:option value="">-- Selecione --</html:option>
                            <html:option value="1">Sim</html:option>
                            <html:option value="0">Não</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td>Disponível?</td>
                    <td>
                        <html:select name="FormArvoreSatisfacao" property="inDisponibilidadeRespostaFuncao">
                            <html:option value="">-- Selecione --</html:option>
                            <html:option value="1">Sim</html:option>
                            <html:option value="0">Não</html:option>
                        </html:select>                    
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="2">
                    <acesso:controlHiddenItem nomeIdentificador="adm_iares_salvarnov"><br>
                        <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none;" onClick="salvaResposta(); return false;"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>

        <script language="javascript" for="window" event="onload">
        <!--   
            //document.body.style.backgroundColor = '#E3ECF4';
             parent.oculta_div();
        -->
        </script> 


        </form>
    <%}else{%>
        <form action="salvaRespostaExistente.do" name="formArvoreSatisfacao" method="post">
            <input type="hidden" name="idQuestionarioFuncao" value="<%=request.getParameter("idQuestionarioFuncao")%>"/>
            <input type="hidden" name="dsQuestionarioFuncao" value="<%=request.getParameter("dsQuestionarioFuncao")%>"/>
            <input type="hidden" name="idRespostaFuncao" value="<%=request.getParameter("idRespostaFuncao")%>"/>
            <input type="hidden" name="sqApresentacaoRespostaFuncao" value="<%=request.getParameter("sqApresentacaoRespostaFuncao")%>"/>

            <script language="Javascript">
                function salvaResposta()
                {
                    if(trim(document.forms[0].dsRespostaFuncao.value) == '')
                    {
                        alert('Descrição é um campo obrigatório.');
                        return false;
                        
                    }else if(trim(document.forms[0].dsScriptRespostaFuncao.value) == '')
                    {
                        alert('Script é um campo obrigatório.');
                        return false;
                        
                    }else if(document.forms[0].inEncerramentoRespostaFuncao.value == '')
                    {
                        alert('Encerra Pergunta é um campo obrigatório.');
                        return false;
                        
                    }else if(document.forms[0].inDisponibilidadeRespostaFuncao.value == '')
                    {
                        alert('Disponível é um campo obrigatório.');
                        return false;
                    }
                    else
                    {
                
                        document.forms[0].target= '_parent';
                        document.forms[0].action = 'salvaRespostaExistente.do';
                        parent.mostrar_div();
                        document.forms[0].submit();
                    }
                }
            </script>
            <table width="100%">
                <tr>
                    <td valign="top">
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                        Descri&ccedil;&atilde;o:
                    </td>
                    <td valign="top" width="160" align="left">
                        <textarea name="dsRespostaFuncao" style="width:165px;height:80;font-family:Tahoma;font-size:11px;" onkeypress="return verificaEnter(event);" onkeyup="checaTextarea(this,240);"><%=request.getParameter("dsRespostaFuncao")%></textarea>
                    </td>
                </tr>
                <tr>
                    <td valign="top">Script:</td>
                    <td>
                        <textarea name="dsScriptRespostaFuncao" style="width:165px;height:80;font-family:Tahoma;font-size:11px;" onkeypress="return verificaEnter(event);" onkeyup="checaTextarea(this,240);"><%=request.getParameter("dsScriptRespostaFuncao")%></textarea>
                    </td>
                </tr>
                <tr>
                    <td>Encerra Questionário?</td>
                    <td>
                        <%if(request.getParameter("inEncerramentoRespostaFuncao").equalsIgnoreCase("1")){%>
                            <select name="inEncerramentoRespostaFuncao" style="SELECT">
                                <option value="">-- Selecione --</option>
                                <option value="1" selected>Sim</option>
                                <option value="0">Não</option>
                            </select>
                        <%}else{%>
                            <select name="inEncerramentoRespostaFuncao" style="SELECT">
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
                            
                            <%if(request.getParameter("inDisponibilidadeRespostaFuncao").equalsIgnoreCase("1")){%>
                                <select name="inDisponibilidadeRespostaFuncao" style="SELECT">
                                    <option value="">-- Selecione --</option>
                                    <option value="1" selected>Sim</option>
                                    <option value="0">Não</option>
                                </select>
                                
                            <%}else{%>
                                <select name="inDisponibilidadeRespostaFuncao" style="SELECT">
                                    <option value="">-- Selecione --</option>
                                    <option value="1">Sim</option>
                                    <option value="0" selected>Não</option>
                                </select>
                            <%}%>
                        </td>
                </tr>
                <tr>
                    <td align="right" colspan="2">
                    <acesso:controlHiddenItem nomeIdentificador="adm_iares_salvaralt"><br>
                        <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" onClick="salvaResposta(); return false;" onKeyPress="salvaResposta(); return false;" style="border:none;"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>

        </form>
    <%}%>
    
        <script language="javascript" for="window" event="onload">
        <!--   
            document.body.style.backgroundColor = '#E3ECF4';
             parent.oculta_div();
        -->
        </script> 
    
</acesso:controlHiddenItem>
</body>
