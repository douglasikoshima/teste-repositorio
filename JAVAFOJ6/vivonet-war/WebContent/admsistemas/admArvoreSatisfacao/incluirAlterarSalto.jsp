<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormArvoreSatisfacao"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreSatisfacao"/>
<bean:define id="AdmPerguntaVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreSatisfacao.admPerguntaVO"/>


    <head>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    </head>
    <acesso:controlHiddenItem nomeIdentificador="adm_iasal_verpagina">
    <form action="salvaSalto.do" name="formArvoreSatisfacao" method="post">
        <script language="Javascript">
            function enviar()
            {
                if(valida())
                {
                    document.forms[0].target= '_parent';
                    document.forms[0].action = 'salvaSalto.do';
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            }
            
            function valida()
            {
               form = document.forms[0];
               if(form.idPerguntaSalto.selectedIndex == 0)
               {
                    alert('Pergunta é um campo obrigatório.');
                    return false;
               
               }
               else if(form.idPerguntaSalto.selectedIndex != 1 && form.ativoSaltoFuncao.selectedIndex == 0)
               {
                    alert('Ativo é um campo obrigatório.');
                    return false;
               }
    
                return true;        
            }
        </script>
    
        <input type="hidden" name="idRespostaFuncao" value="<%=request.getParameter("idResposta")%>"/>
        <input type="hidden" name="idQuestionarioFuncao" value="<%=request.getParameter("idQuestionarioFuncao")%>"/>
        <input type="hidden" name="dsQuestionarioFuncao" value="<%=request.getParameter("dsQuestionarioFuncao")%>"/>                                  

        <input type="hidden" name="dsRespostaFuncao" value="<%=request.getParameter("dsRespostaFuncao")%>"/>
        <input type="hidden" name="dsScriptRespostaFuncao" value="<%=request.getParameter("dsScriptRespostaFuncao")%>"/>
        <input type="hidden" name="inDisponibilidadeRespostaFuncao" value="<%=request.getParameter("inDisponibilidadeRespostaFuncao")%>"/>
        <input type="hidden" name="inEncerramentoRespostaFuncao" value="<%=request.getParameter("inEncerramentoRespostaFuncao")%>"/>
        <input type="hidden" name="sqApresentacaoRespostaFuncao" value="<%=request.getParameter("sqApresentacaoRespostaFuncao")%>"/>
        
        <table width="100%">
            <tr>
                <td valign="top">
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                    Selecione a pergunta destino do salto:
                </td>
            </tr>
            <tr>
                <td valign="top" width="160" align="left">
                    <html:select name="FormArvoreSatisfacao" property="idPerguntaSalto" style="width:230px;"  size="1" styleClass="SELECT">
                            <html:option value="-1">-- Selecione --</html:option>  
                            <html:option value="">Sem Salto</html:option>  
                            <html:options collection="AdmPerguntaVO" property="idPergunta" labelProperty="dsPergunta" /> 
                    </html:select>
                </td>
            </tr>
            <tr>
            <td valign="top">
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                    Ativo?
                </td>
            </tr>
            <tr>
                <td valign="top" width="160" align="left">
                    <html:select name="FormArvoreSatisfacao" property="ativoSaltoFuncao">
                        <html:option value="-1">-- Selecione --</html:option>
                        <html:option value="1">Sim</html:option>
                        <html:option value="0">Não</html:option>
                    </html:select>
                </td>
            </tr>
            
            <tr>
                <td align="right" colspan="2">
                <acesso:controlHiddenItem nomeIdentificador="adm_iasal_gravar">
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
        document.body.style.backgroundColor = '#E3ECF4';
    </script>
</acesso:controlHiddenItem>
</body>