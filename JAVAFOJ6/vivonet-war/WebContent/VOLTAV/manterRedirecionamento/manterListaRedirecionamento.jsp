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
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterRedirecionamento" type="VOLTAV.manterRedirecionamento.ManterRedirecionamentoController.FormManterRedirecionamento"/>

<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="Tabela Direcionamento"/>
<netui-template:setAttribute name="modulo" value="VOL/TAV"/>
<netui-template:section name="headerSection">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css"/>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript">
        function pesquisarCanal(id){
           mostrar_div();
           document.forms[0].submit();
        }

        function voltar() {
            document.forms[0].action="voltar.do";
            document.forms[0].target="ifrmFalse";
            mostrar_div();
            document.forms[0].submit();
        }
    </script> 
</netui-template:section>    
<netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:sessao id="listaRedirecionamento"  width="790" height="468" scroll="false" operacoes="Consulta de Par&acirc;metros de Direcionamento">            
            <form action="consultarRedirecionamento.do" method="post" name="formManterRedirecionamento">
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="left"><b>Canal:</b></td>
                        <td align="left">
                            <html:radio name="Form" property="idSistema" value="9" styleClass="radio" onclick="pesquisarCanal(this.value);" styleId="radioSwf"/><b>VOL</b>
                            <html:radio name="Form" property="idSistema" value="28" styleClass="radio" onclick="pesquisarCanal(this.value);" styleId="radioImagem" /><b>TAV</b>
                        </td>
                    </tr>
                    <tr>
                        <td align="left"><b>URL para Direcionamento:</b></td>
                        <td align="left"><b><bean:write name="Form" property="urlExemplo" /></b></td>
                    </tr>           
              </table>
              <logic:notEmpty name="Form" property="listaItemRedirecionamento">
                  <vivo:tbTable selectable="true" layoutWidth="760" layoutHeight="300" tableWidth="760" onRowClick="false" resize="true" styleId="tableTitle" sortable="true">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn align="center" width="50%" type="string">Caminho Funcionalidade</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="50%"  type="string">Par&acirc;metro</vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows>
                        <logic:iterate name="Form" property="listaItemRedirecionamento" id="item" indexId="id">
                            <vivo:tbRow key="linha">
                                <vivo:tbRowColumn>
                                        <bean:write name="item" property="primeiroNivel" /> / <bean:write name="item" property="segundoNivel" />
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                        <bean:write name="item" property="idRedirecionamento" />
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                        </logic:iterate>
                    </vivo:tbRows>
                </vivo:tbTable> 
            </logic:notEmpty>
            
            <logic:empty name="Form" property="listaItemRedirecionamento">
                <table width="100%" border="0">
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="center">
                            <b>Não existem redirecionamentos cadastrados.</b>
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