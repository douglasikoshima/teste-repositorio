<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>

<bean:define id="retornoCTIForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="retornoCTIForm"/>
<netui-template:template templatePage="./../../../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Workflow"/>
    <netui-template:setAttribute name="modulo" value="Administração Retorno CTI"/>
    <netui-template:section name="headerSection">
    	 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>

        <script language="javascript">
                
            function keyEnter() {
                //Caso não seja o enter continua
                if( window.event.keyCode != 13 ) return;
                
                //Efetua o logon
                window.event.keyCode = 0;
                pesquisar();
            }

            function pesquisar() {
                //Liga animação
                if( top.dvAnimarAguarde != null ) top.startAnimation();
                
                //Metodo de pesquisa
                document.forms["retornoCTIForm"].elements["method"].value = <%=ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_PESQUISAR%>;
            
                //Processa a pesquisa
                document.forms["retornoCTIForm"].target = "ifrmResultadoPesquisa";
                document.forms["retornoCTIForm"].action = "iniciarMetodo.do";
                document.forms["retornoCTIForm"].submit();
            }
            
            function limpar() {
                document.forms["retornoCTIForm"].elements["retornoWFCTIPesquisaVO.idRetornoWFCTI"].value = "";
                document.forms["retornoCTIForm"].elements["retornoWFCTIPesquisaVO.sgStatus"].value       = "T";
                document.forms["retornoCTIForm"].elements["retornoWFCTIPesquisaVO.dsRetornoWFCTI"].value = "";
                
                //Foca no codigo
                document.forms["retornoCTIForm"].elements["retornoWFCTIPesquisaVO.idRetornoWFCTI"].focus();
            }
            
            function novo() {
                //Liga animação
                if( top.dvAnimarAguarde != null ) top.startAnimation();

                //Metodo novo registro
                document.forms["retornoCTIForm"].elements["method"].value = <%=ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_NOVO%>;
            
                //Processa um novo registro
                document.forms["retornoCTIForm"].target = "";
                document.forms["retornoCTIForm"].action = "iniciarMetodo.do";
                document.forms["retornoCTIForm"].submit();
            }
            
            function editar(codigo) {
                //Obtém as informações da linha
                
                //descricao = poRow.cells(2).innerText;
            
                //Liga animação
                if( top.dvAnimarAguarde != null ) top.startAnimation();

                //Metodo editar
                document.forms["retornoCTIForm"].elements["method"].value    = <%=ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_ALTERAR%>;
                document.forms["retornoCTIForm"].elements["idRetorno"].value = codigo;

                //Processa a edição
                document.forms["retornoCTIForm"].target = "";
                document.forms["retornoCTIForm"].action = "iniciarMetodo.do";
                document.forms["retornoCTIForm"].submit();
            }
            
            function excluir(codigo,descricao) {
                //Obtém as informações da linha
                //codigo    = poRow.cells(0).innerText;
                //descricao = poRow.cells(2).innerText;
            
                //Confirma a exclusão
                if( ! confirm("Confirma a exclusão do registro [" + codigo + "][" + descricao + "]?") ) return;
            
                //Liga animação
                if( top.dvAnimarAguarde != null ) top.startAnimation();

                //Metodo excluir
                document.forms["retornoCTIForm"].elements["method"].value    = <%=ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_EXCLUIR%>;
                document.forms["retornoCTIForm"].elements["idRetorno"].value = codigo;
                
                //Processa a exclusão e atualiza a pesquisa
                document.forms["retornoCTIForm"].target = "ifrmResultadoPesquisa";
                document.forms["retornoCTIForm"].action = "iniciarMetodo.do";
                document.forms["retornoCTIForm"].submit();
            }
        </script>
    	
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_rcti_verpagina">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        
        <!--APLICACAO-->
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>

        <vivo:quadro id="qdMain" height="480" width="790" label="&nbsp;Vivo Net >> Workflow >> Administração Retorno CTI" scroll="N">
            <form id="retornoCTIForm" action="iniciarMetodo.do" method="post">
                <html:hidden name="retornoCTIForm" property="method"/>
                <html:hidden name="retornoCTIForm" property="idRetorno"/>
                <table width="690" cellspacing="0" cellpadding="0" border="0">
                    <tr>
                        <td style="height:5px;"></td>
                    </tr>
                </table>
                <!--CONFIGURAÇÕES-->
                <vivo:quadro id="qdPesquisa" height="55" width="780" label="&nbsp;Pesquisa" scroll="N">
                    <table width="100%" cellpadding="1" cellspacing="1" border="0">
                        <tr>
                            <td width="10%">Código: </td>
                            <td width="40%"><html:text name="retornoCTIForm" property="retornoWFCTIPesquisaVO.idRetornoWFCTI" size="20" maxlength="10" onkeypress="keyEnter();" onkeyup ="Formatar(this.value, this.form.name, this.name, 'numero');" onchange="Formatar(this.value, this.form.name, this.name, 'numero');" /></td>
                            <td width="10%">Status: </td>
                            <td width="40%">
                                <html:select name="retornoCTIForm" property="retornoWFCTIPesquisaVO.sgStatus" style="width=250px">
                                    <option value="T">Todos</option>
                                    <option value="A">Ativo</option>
                                    <option value="C">Cancelado</option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Descriçao:</td>
                            <td><html:text name="retornoCTIForm" property="retornoWFCTIPesquisaVO.dsRetornoWFCTI" size="60" maxlength="60" onkeypress="keyEnter();"/></td>
                            <td align="center" colspan="2">
                                <table width="100%" cellpadding="1" cellspacing="1" border="0">
                                    <tr align="center">
                                        <td align="right">
                                        <acesso:controlHiddenItem nomeIdentificador="adm_rcti_pesq">
                                            <img hspace="10" vspace="6" id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" style="border:none;" onClick="pesquisar(); return false"/>                                        
                                        </acesso:controlHiddenItem>
                                        </td>
                                        <td>&nbsp;</td>
                                        <td align="left">
                                            <img hspace="10" vspace="6" id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;" onClick="limpar(); return false"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </vivo:quadro>
                
                <table width="775" cellspacing="1" cellpadding="1" border="0">
                    <tr>
                        <td style="height:5px;"></td>
                    </tr>
                </table>

                <vivo:quadro id="qdResultadoPesquisa" height="325" width="780" label="&nbsp;Resultado" scroll="N">
                    <iframe id="ifrmResultadoPesquisa" name="ifrmResultadoPesquisa" height="330" width="770" frameborder="0" scrolling="no"></iframe>
                </vivo:quadro>
                
                <table width="775" cellspacing="1" cellpadding="1" border="0">
                    <tr>
                        <td style="height:5px;"></td>
                    </tr>
                </table>

                <vivo:quadro id="qdAcao" height="30" width="780" label="&nbsp;Ação" scroll="N">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td align="left">
                                <img hspace="10" vspace="6" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;"/>
                            </td>
                            <td align="right">
                                <acesso:controlHiddenItem nomeIdentificador="adm_rcti_novo">
                                    <img hspace="10" vspace="6" id="btIncluir" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_over.gif" style="border:none;" onClick="novo(); return false"/>
                                </acesso:controlHiddenItem>
                            </td>
                        </tr>
                    </table>                                                                            
                </vivo:quadro>
            </form>
        </vivo:quadro>
        
        <script language="javascript">
             //Fim animação
            if( top.dvAnimarAguarde != null ) top.stopAnimation();
            
            //Foca no codigo
            document.forms["retornoCTIForm"].elements["retornoWFCTIPesquisaVO.idRetornoWFCTI"].focus();
            
            //Verifica se deve iniciar a pesquisa
            if( document.forms["retornoCTIForm"].elements["method"].value == <%=ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_VOLTAR%> )
                pesquisar();                
                                
            <logic:notEmpty name="retornoCTIForm" property="errorMessage">
            
            alert("<bean:write name="retornoCTIForm" property="errorMessage"/>");
            
            </logic:notEmpty>       
        </script>       
       
    </acesso:controlHiddenItem>
    
       
    
    </netui-template:section>
</netui-template:template>
