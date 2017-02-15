<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="servicosDeParaForm" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute value="Vivonet" name="title"/>
<netui-template:setAttribute value="Parametrização" name="modulo"/>
<netui-template:section name="headerSection">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript">
        acao = function(dsAcao, idServico) {
            var f = document.forms['0'];
            if (dsAcao == 'alterar') {
                f.action = 'carregarServico.do';
                $('idPlano').value = idServico;
                f.submit();
            } else if (dsAcao == 'excluir') {
                f.action = 'excluirServico.do';
                $('idPlano').value = idServico;
                if (confirm('Tem certeza que deseja excluir este serviço?')) {
                    f.submit();
                }
            }
        }
    </script>
</netui-template:section>
<netui-template:section name="bodySection">
<div id="menuPrincipal" style="margin-bottom:4px;"><jsp:include page="/resources/menus/MenuPrincipal.jsp" /></div>
<vivo:sessao height="470" id="permissoes" width="790" scroll="no" label="Serviços de Para" operacoes="Parametrização">
    <form action="begin.do" method="post" name="formServicos">
    <html:hidden name="form" property="idPlano" styleId="idPlano" />
    <table border="0" cellpadding="0" bgcolor="white"cellspacing="1">
        <tr>
            <td>
                <vivo:tbTable selectable="true" onRowClick="" layoutWidth="760" layoutHeight="370" tableWidth="760" styleId="tableTitle" sortable="true">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn align="left" width="20%" type="string">Código do Plano</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="30%" type="string">Descrição do Plano</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="30%" type="string">Código do plano no Atlys</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="10%" type="string">Ativo?</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="10%" type="string">Ações</vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows>
                        <logic:iterate name="form" property="listaServicos" indexId="idx" id="listaServicos" type="br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara">
                            <vivo:tbRow key="Linha">
                                <vivo:tbRowColumn><bean:write name="listaServicos" property="cdPlano"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="listaServicos" property="dsPlano"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="listaServicos" property="cdPlanoAtlys"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <logic:equal name="listaServicos" property="inAtivo" value="1">Sim</logic:equal>
                                    <logic:equal name="listaServicos" property="inAtivo" value="0">Não</logic:equal>
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif"
                                                 border="0"
                                                 onClick="acao('alterar', <%=listaServicos.getIdPlano()%>)"/>&nbsp;&nbsp;
                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif"
                                                 border="0"
                                                 onClick="acao('excluir', <%=listaServicos.getIdPlano()%>)" />
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                        </logic:iterate>
                    </vivo:tbRows>
                </vivo:tbTable>
            </td>
        </tr>
    </table>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
    <table width="780" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
        <tr>
            <td valign="middle" width="100"><b>&nbsp;Legendas:</b></td>
            <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Serviço</td>
            <td width="519"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Serviço</td>
        </tr>
    </table>
    <img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"
         style="margin: 10px 0 0 2px;cursor:pointer"
         onmouseup="document.forms[0].action='carregarServico.do';document.forms[0].submit();" />
    </form>
    <vivo:alert atributo="msg" scope="request" />
</vivo:sessao>
</netui-template:section>
</netui-template:template>