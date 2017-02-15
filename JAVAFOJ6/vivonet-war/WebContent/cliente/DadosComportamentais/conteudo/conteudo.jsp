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

<bean:define id="Form"                 name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm" />
<bean:define id="aAssuntoVO"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.assuntos.assuntoVOArray" />
<bean:define id="aSubAssuntoVO"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.subAssuntos.subAssuntoVOArray" />
<bean:define id="aTipoApresenacaoVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.tiposApresentacao.tipoApresentacaoVOArray" />
<bean:define id="aDisponibilidadeVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.disponibilidades.disponibilidadeVOArray" />
<bean:define id="dadosComportamentais" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.dadosComportamentais" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Conteudo" name="title"></netui-template:setAttribute>
    <netui-template:setAttribute value="Gestão de Clientes" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>         
        <script language="javaScript">
            function bemVindo() {
                document.forms[0].action = "<%=request.getContextPath()%>/index.jsp";
                document.forms[0].submit();
            }
            

            function obterSubAssuto() {
                document.forms[0].target="frameApp";
                document.forms[0].action = "obterSubAssunto.do";
                document.forms[0].submit();
            }

            function Pesquisar(){                
                if (document.forms[0].assuntoSelecionado.value == "0"){
                    alert("Favor selecionar um Assunto!");
                }else if (document.forms[0].subAssuntoSelecionado.value == "-1"){
                    alert("Favor selecionar um Subassunto!");
                } else {
                    document.forms[0].target ="frmResultadoPesquisa";
                    document.forms[0].action = "pesquisarListaConteudo.do";
                    document.forms[0].submit();
                }
            }
            
            // Quadro flutuante - Inclusão
            function IncluirConteudo() {
                if (document.forms[0].assuntoSelecionado.value == "0"){
                    alert("Favor selecionar um Assunto!");
                }else if (document.forms[0].subAssuntoSelecionado.value == "-1"){
                    alert("Favor selecionar um Subassunto!");
                } else {
                     frmResultadoPesquisa.IncluirConteudo();                
                   }
            }
        
             //Refresh no formulário
            function refresh() {
                document.forms[0].target = "";
                document.forms[0].action = "begin.do";
                document.forms[0].submit();
            }
             function limpaLista(valor) {
                if(document.frames("frmResultadoPesquisa").document.getElementById('botaoIn') != null){
                    document.forms[0].action = "limpaListaConteudo.do";
                    document.forms[0].target = "";
                    document.forms[0].submit();
                }
            }

       </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cli_cont_verpagina">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <div style="z-index:999999999;position:absolute;top:0;left:0;"><iframe frameborder="0" width="1" height="1" id="executa" name="executa" src="" style="allow-transparency:true;"></iframe></div>
    
    <vivo:sessao id="qdMain" height="475" width="790" label="Customer Profile" operacoes="Conteúdo" scroll="N">
        
        <form action="pesquisarListaConteudo.do" name="listaConteudoForm" method="post">
        <html:hidden name="Form" property="disponibilidadeSelecionado"/>
        <html:hidden name="Form" property="tipoApresentacaoSelecionado"/>
            <table>
                <tr>
                    <td>Assunto:</td>
                    <td>
                        <html:select name="Form" property="assuntoSelecionado" title="assuntoSelecionado" onchange="obterSubAssuto();" style="width:280px;">
                            <html:option value="0">-- Selecione --</html:option>
                            <html:options collection="aAssuntoVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                    <td>Subassunto:</td>
                    <td>
                        <html:select name="Form" property="subAssuntoSelecionado" title="subAssuntoSelecionado" onchange="limpaLista()" styleId="comboSubAssunto" style="width:280px;">
                            <html:option value="-1">-- Selecione --</html:option>
                            <html:options collection="aSubAssuntoVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                    <td><acesso:controlHiddenItem nomeIdentificador="cli_cont_pesquisar">
                     <div id="botao">
                    <img style="border:0px;" onClick="Pesquisar(); return false"  src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/>
                    </div>
                    </acesso:controlHiddenItem></td>
                </tr>
              </table>
                <iframe   src="resultadoPesquisaConteudo.jsp" name="frmResultadoPesquisa" id="ifrResultadoPesquisa" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" width="780" height="355"></iframe>            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            
      
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        
            <table width="780" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                <tr>
                    <td valign="middle" width="100"><b>&nbsp;Legendas:</b></td>
                    <td width="680"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Alterar Conte&uacute;do</td>
                </tr>
            </table>
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            
            <table width="780">
                <tr>
                    <td align="left"><img style="border:0px;" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"/></td>
                    <td align="right"><acesso:controlHiddenItem nomeIdentificador="cli_cont_incluir"><img style="border:0px;" onClick="IncluirConteudo(); return false"      src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_over.gif"/></acesso:controlHiddenItem></td>
                </tr>
            </table>

        </form>

        </vivo:sessao>
        
        <!--Form e Quadro Flutuante-->
    
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
