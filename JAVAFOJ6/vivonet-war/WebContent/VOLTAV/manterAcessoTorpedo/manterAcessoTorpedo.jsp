<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>
<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAcessoTorpedo" type="VOLTAV.manterAcessoTorpedo.ManterAcessoTorpedoController.FormManterAcessoTorpedo"/>

<netui-template:setAttribute name="title" value="Acesso Torpedo Web"/>
<netui-template:setAttribute name="modulo" value="VOL/TAV"/>

<netui-template:section name="headerSection">

<script type="text/javascript" language="javascript">

        function ativarAcesso(id) {
           if (confirm("Deseja habilitar para visualização o bloco do torpedo web gratuito para a funcionalidade selecionada?")) {
               document["formAcesso"].method = "POST";
               document["formAcesso"].action = "ativarAcessoTorpedo.do";
               document["formAcesso"].idAcessoSelecionado.value = id;
               mostrar_div();
               document["formAcesso"].submit();
           }
        }

        function desativarAcesso(id) {
           if (confirm("Deseja desabilitar para visualização o bloco do torpedo web gratuito para a funcionalidade selecionada?")) {
               document["formAcesso"].method = "POST";
               document["formAcesso"].action = "desativarAcessoTorpedo.do";
               document["formAcesso"].idAcessoSelecionado.value = id;
               mostrar_div();
               document["formAcesso"].submit();
           }
        }

</script>
</netui-template:section>
<netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

        <vivo:sessao id="listaAcessoTorpedo"  width="790" height="468" scroll="false" operacoes="Parametriza&ccedil;&atilde;o de Exibi&ccedil;&atilde;o Torpedo Web Gratuito">

            <form action="consultarAcessoTorpedo.do" method="post" name="formAcesso">
            <html:hidden name="Form" property="idAcessoSelecionado" styleId="idAcessoSelecionado" />
             <logic:notEmpty name="Form" property="listaItemAcesso">
                         <vivo:tbTable selectable="true" layoutWidth="760" layoutHeight="400" tableWidth="760" onRowClick="false" resize="true" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="55%" type="string">Nome da P&aacute;gina</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="40%" type="string">Status</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate name="Form" property="listaItemAcesso" id="item" indexId="id">
                                    <vivo:tbRow key="linha">
                                        <vivo:tbRowColumn>
                                                <bean:write name="item" property="primeiroNivel" /><logic:notEmpty name="item" property="segundoNivel"> / </logic:notEmpty><bean:write name="item" property="segundoNivel" />
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                                <bean:write name="item" property="descStatus" />
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <logic:equal name="item" property="inAtivo" value="1">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" border="0" alt="Desabilitar Item" onclick="desativarAcesso('<bean:write name="item" property="idAcessoTorpedo"/>');">
                                            </logic:equal>
                                            <logic:equal name="item" property="inAtivo" value="0">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_habilitar.gif" border="0" alt="Habilitar Item" onclick="ativarAcesso('<bean:write name="item" property="idAcessoTorpedo"/>');">
                                            </logic:equal>
                                    </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
            </logic:notEmpty>

            <logic:empty name="Form" property="listaItemAcesso">
            <table width="100%" border="0">
            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
            <tr>
                <td align="center">
                    <b>Não existem p&aacute;ginas cadastradas.</b>
                </td>
            <tr>
            </table>
             </logic:empty>
            </form>
        </vivo:sessao>
        <iframe id="ifrmFalse" name="ifraSubmit" style="visibility:hidden;" width="0px" height="0px"></iframe>
        <vivo:alert atributo="msgStatus" scope="request"/>
 </netui-template:section>
</netui-template:template>