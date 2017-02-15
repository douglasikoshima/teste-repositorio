<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/> 
  
<bean:define id="ManterCargoForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCargoForm"/>
<bean:define id="ListaCargos"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCargoForm.listaCargoVO"/>
<bean:define id="CargoVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCargoForm.cargoVO"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Grupo"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>

    <netui-template:section name="headerSection">
        <script language="javascript">
        <!--
        function listaCargo() 
        {
            divAbas.style.display = '';
            document.forms[0].target = '_parent';
            document.forms[0].nmCargo.value = '';
            var pagina = '/FrontOfficeWeb/usuario/Organograma/manterCargoOrganograma/begin.do?isMenu=false';
            document.forms[0].action = pagina;
            oculta_div();
            document.forms[0].submit();
        }
        
        function relacionarCargoAtividade()
        {
            var pagina = 'relacionarCargoAtividade.do?idCargo='+document.forms[0].idCargo.value;
            document.forms[0].target = "ifrmAbas";
            oculta_div();
            document.getElementById("ifrmAbas").src = pagina;
        }
        
        function CarregaAba(nome)
        {
            var pagina = "";
            if(nome=="bt01") pagina = "relacionarCargoAtividade.do?operacao=alterar&idCargo="+document.forms[0].idCargo.value;
            document.forms[0].action = pagina;
            oculta_div();
            document.forms[0].submit();
        }
        // -->
        </script>
       
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_ercargo_verpagina">
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        
            <vivo:sessao id="qdMain" height="470" width="790" label="Relacionamentos do Cargo" operacoes="Manuten&ccedil;&atilde;o de Par&acirc;metros">
            <!--vivo:quadro id="qdMain" height="468" width="780" label="Relacionamentos do Cargo"-->
                                
                <form action="<%=request.getContextPath()%>/usuario/Organograma/manterCargoOrganograma/begin.do"  id="begin" name="begin" method="POST" onSubmit="return false;">
                
                <html:hidden property="idCargo" name="ManterCargoForm"/>
                <html:hidden property="nmCargo" name="ManterCargoForm"/>

                <table><tr><td></td></tr></table>
                <table width="98%" cellspacing="1" cellpadding="1" class="tbl_bgGray" align="center">
                    <tr>
                        <td>
                        <table width="95%" border="0" cellspacing="1" cellpadding="1" align="center">
                            <tr>
                                <td width="20%" class="tblEstatica_campoNome">&nbsp;
                                    <netui:label value="Nome do Cargo :" styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="80%" style="tblEstatica_campoValor">
                                    <bean:write name="ManterCargoForm" property="nmCargo"/>
                                </td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                </table>
                <table><tr><td height="6"></td></tr></table>
                
                <table width="98%" cellpadding="0" cellspacing="0" align="center">
                    <tr>
                        <td valign="top">
                            <vivo:abaGrupo id="btAba" width="400" height="10px" styleClass="abatexto">
                            <acesso:controlHiddenItem nomeIdentificador="usu_ercargo_relacionar">
                                <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);"  value="Relacionar Atividade ao Cargo" select="S"/>
                            </acesso:controlHiddenItem> 
                            </vivo:abaGrupo>
                        </td>
                    </tr>
                    <tr>
                        <!--bean:write name="ManterCargoForm" property="indicativoOperacao" /-->
                        <!--logic:equal name="ManterCargoForm" property="indicativoOperacao" value=""-->
                            <td valign="top" align="center">
                            <div id="divAbas">
                                <table width="100%" cellpadding="0" cellspacing="0" align="center" style="border:1px solid #adadad;">
                                    <tr>
                                        
                                        <td valign="top" align="center" width="100%">
                                            <IFRAME id="ifrmAbas" name="ifrmAbas" width="100%" height="250" frameborder=0 scrolling="no" src="relacionarCargoAtividade.jsp"></IFRAME>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            </td>
                        <!--/logic:equal-->
                    </tr>
                </table>
                
                <table>                    
                    <tr>
                        <td>
                            <img hspace="5" onclick="listaCargo();" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>
                        </td>
                    </tr>
                </table>
                </form>
                </vivo:sessao>
                <!--/vivo:quadro-->
                <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
                <!--
                    relacionarCargoAtividade();
                -->
                </script> 
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
