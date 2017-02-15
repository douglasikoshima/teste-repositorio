<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%> 

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="pontosContatoFormBean"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

<netui-template:setAttribute name="title" value="Pontos de Contato"/>
<netui-template:setAttribute name="modulo" value="VOL/TAV"/>

<netui-template:section name="headerSection">

    <script type="text/javascript" language="javascript">

        

    </script>

</netui-template:section>

<netui-template:section name="bodySection">

<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

<vivo:sessao id="qdMain" height="468" width="790" label="Parametrização" operacoes="Pontos de Contato - Consulta de Processamento" scroll="no">

    <acesso:controlHiddenItem nomeIdentificador="voltav_pcontato_consultaProcessamento">

    <table width="760" border="0" cellspacing="0" cellpadding="0" align="center" style="margin-top:10px;">
        <tr>
            <td valign="top" height="420">
                <logic:notEmpty name="Form" property="pontosContatoVO.itemPontosContatoArray">
                    <vivo:tbTable selectable="true" styleId="tbArquivosLote" layoutHeight="125" layoutWidth="750" tableWidth="765">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn width="15%" type="String">Data de Envio</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="20%" type="String">Login</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="15%" type="String">Data/Hora Processamento</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="20%" type="String">Status da Carga</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="10%" type="String">Registros Rejeitados</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="10%" type="String">Total de Registros</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate id="iteratorPontosContato" name="Form" property="pontosContatoVO.itemPontosContatoArray">
                            <vivo:tbRow key="linha1">
                                <vivo:tbRowColumn>
                                    <bean:write name="iteratorPontosContato" property="dtEnvio" />
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <bean:write name="iteratorPontosContato" property="dsLoginUsuario" />
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <bean:write name="iteratorPontosContato" property="dtProcessamento" />
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <bean:write name="iteratorPontosContato" property="dsStatusCarga" />
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <bean:write name="iteratorPontosContato" property="qtRegistrosRejeitados" />
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <bean:write name="iteratorPontosContato" property="qtRegistrosTotal" />
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>
                </logic:notEmpty>
            <td>
        </tr>
        <tr>
            <td align="left">
                <img id="btVoltar"
                               value="Voltar"
                               src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                               rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"
                               style="border:none;margin:10px 4px;"
                               onMouseUp="top.frameApp.location.href='begin.do'" />
            </td>
        </tr>
    </table>

    </acesso:controlHiddenItem>

</vivo:sessao>

<vivo:alert atributo="msgStatus" scope="request"/>

</netui-template:section>

</netui-template:template>