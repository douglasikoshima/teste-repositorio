<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm" />
<bean:define id="aAssuntoVO"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.assuntos.assuntoVOArray" />
<bean:define id="aSubAssuntoVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.subAssuntos.subAssuntoVOArray" />
<bean:define id="aConteudoVO"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.conteudos.conteudoVOArray" />
<bean:define id="valoresPossiveis" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.valoresPossiveis" />

  <netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Conteudo" name="title"></netui-template:setAttribute>
    <netui-template:setAttribute value="Gestão de Clientes" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>         
        <script language="javaScript">
            function bemVindo() {
                document.forms[0].action = "<%=request.getContextPath()%>/index.jsp";
                mostrar_div();
                document.forms[0].submit();
            }
            
            function obterSubAssuto() {
                document.forms[0].target="frameApp";
                document.forms[0].action = "obterSubAssunto.do";
                mostrar_div();
                document.forms[0].submit();
            }

            function obterConteudo() {
                document.forms[0].target="frameApp";
                document.forms[0].action = "obterConteudo.do";
                mostrar_div();
                document.forms[0].submit();
            }

            function Pesquisar(){    
                if (document.forms[0].assuntoSelecionado.value == "0"){
                    alert("Favor selecionar um Assunto!");
                }else if (document.forms[0].subAssuntoSelecionado.value == "0"){
                    alert("Favor selecionar um SubAssunto!");
                }else if (document.forms[0].conteudoSelecionado.value == "0"){
                    alert("Favor selecionar um Conteúdo!");
                } else {
                    document.forms[0].target ="frmResultadoPesquisa";
                    document.forms[0].action = "pesquisarValorPossivel.do";
                    mostrar_div();
                    document.forms[0].submit();
                }
            }
            
            function AtualizaConteudoSelecionado(){    
                document.forms[0].target ="frmResultadoPesquisa"; 
                document.forms[0].action = "atualizarConteudoSelecionado.do";
                mostrar_div();
                document.forms[0].submit();
            }

            // Quadro flutuante - Inclusão
            function IncluirValorPossivel() {
                if (document.forms[0].assuntoSelecionado.value == "0"){
                    alert("Favor selecionar um Assunto!");
                }else if (document.forms[0].subAssuntoSelecionado.value == "0"){
                    alert("Favor selecionar um SubAssunto!");
                }else if (document.forms[0].conteudoSelecionado.value == "0"){
                    alert("Favor selecionar um Conteúdo!");
                } else {                  
                
                  frmResultadoPesquisa.IncluirValorPossivel();
                }
            }
        
            
            //Refresh no formulário
            function refresh() {
                document.forms["frmAlteracao"].target = "";
                document.forms["frmAlteracao"].action = "begin.do";
                mostrar_div();
                document.forms["frmAlteracao"].submit();
            }
            
       </script>
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cli_vlevl_verpagina">
    
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <script>
        mostrar_div();
    </script>
    <script for="window" event="onload">
        oculta_div();
    </script>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <div style="z-index:999999999;position:absolute;top:0;left:0;"><iframe frameborder="0" width="1" height="1" id="executa" name="executa" src="" style="allow-transparency:true;"></iframe></div>
    
    <vivo:sessao id="qdMain" height="470" width="790" label="Customer Profile" operacoes="Valor Poss&iacute;vel" scroll="N">
        <form action="incluirAlterarValorPossivel.do" method="post">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                    <td width="70" style="text-indent:6px;">Assunto</td>
                    <td>
                        <html:select name="Form" property="assuntoSelecionado" title="assuntoSelecionado" onchange="JavaScript:obterSubAssuto()"  styleClass="SELECT" style="width:300px">
                            <html:option value="0">-- Selecione --</html:option>
                            <html:options collection="aAssuntoVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                </tr>
                <tr><td height="5"></td></tr>
                <tr> 
                    <td style="text-indent:6px;">Subassunto</td>
                    <td>
                        <html:select name="Form" property="subAssuntoSelecionado" title="subAssuntoSelecionado"  onchange="JavaScript:obterConteudo()" styleClass="SELECT" style="width:300px">
                            <html:option value="0">-- Selecione --</html:option>
                            <html:options collection="aSubAssuntoVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                </tr>  
                <tr><td  height="5"></td></tr>    
                <tr> 
                    <td style="text-indent:6px;">Conteúdo:</td>
                    <td>
                        <html:select name="Form" property="conteudoSelecionado" title="conteudoSelecionado"  onchange="JavaScript:AtualizaConteudoSelecionado();" styleClass="SELECT" style="width:300px" styleId="conteudo">
                            <html:option value="0">-- Selecione --</html:option>
                            <html:options collection="aConteudoVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                </tr>
                <tr><td height="5"></td></tr>
                <tr>
                    <td></td>
                    <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cli_vl_pesquisar">
                    <div id="botao">
                    <img style="border:none;" onClick="Pesquisar(); return false"  src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/>&nbsp;
                     </div>                    
                    </acesso:controlHiddenItem></td>
                </tr>
            </table>
              <iframe  name="frmResultadoPesquisa" src="resultadoPesquisaValorPossivel.jsp"  id="ifrResultadoPesquisa" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" width="780" height="315"></iframe>            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            
   
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
        
            <table width="780" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                <tr>
                    <td valign="middle" width="100"><b>&nbsp;Legendas:</b></td>
                    <td width="680"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Alterar Valor Possível</td>
                </tr>
            </table>
            
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td><img vspace="6" style="border:none;" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"/></td>
                    <td align="right"><acesso:controlHiddenItem nomeIdentificador="cli_vl_incluir"><img style="border:none;" onClick="IncluirValorPossivel(); return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_over.gif"/></acesso:controlHiddenItem></td>
                </tr>
            </table>
            
        </form>
        
        </vivo:sessao>        
        <!--Form e Quadro Flutuante-->

    </acesso:controlHiddenItem>    
    </netui-template:section>
</netui-template:template>
