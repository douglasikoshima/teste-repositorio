<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" />
<bean:define id="campanhasRelacionadas" name="campanhasRelacionadas" />

<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
    <script language="javascript" src="/FrontOfficeWeb/resources/scripts/executarCampanha.js"  charset="ISO-8859-1"></script>
    <SCRIPT FOR=window EVENT=onload>
        parent.oculta_div();
    </script>
</head>
<body>
<acesso:controlHiddenItem nomeIdentificador="cam_campreltelini_verpagina">
<form action="ViewScriptAction.do" method="post">
    <html:hidden name="form" property="viewScriptActionForm.idSubCampanhaHistoricoSel"  />
    <html:hidden name="form" property="viewScriptActionForm.idListaConteudoSel"  />
    <vivo:quadro id="qdPerg" width="785" height="360" label="Campanha Relacionadas">
        <vivo:tbTable selectable="false" onRowClick="" layoutWidth="757" layoutHeight="300" tableWidth="765" styleId="table" sortable="true" >
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="30%" type="string">Campanha</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="30%" type="string">Sub Campanha</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="35%" type="string">Canal Atendimento</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <netui-data:repeater dataSource="{pageContext.campanhasRelacionadas}">
                <netui-data:repeaterHeader></netui-data:repeaterHeader>
                <netui-data:repeaterItem>
            <vivo:tbRow key="linha1">
                <vivo:tbRowColumn>
                    <netui:radioButtonGroup dataSource="{actionForm.campanha}"  >
                        <netui:radioButtonOption labelStyle="display:none;" value="{container.item.idCanalCampanha}"  styleClass="radio" onClick="campanhasRelacionadasTelaInicial_selecionar();"/>
                    </netui:radioButtonGroup>
                </vivo:tbRowColumn>
                <vivo:tbRowColumn>
                      <netui:label value="{container.item.sgCampanha}"/>
                </vivo:tbRowColumn>
                <vivo:tbRowColumn>
                    <!--<netui:label value="{container.item.versaoCampanha}"/>-->
                    <netui:label value="{container.item.nmSubCampanha}"/>
                    <netui:hidden dataSource="{actionForm.idSubCampanhaHistorico}" dataInput="{container.item.idSubCampanhaHistorico}" />
                    <netui:hidden dataSource="{actionForm.idListaConteudo}" dataInput="{container.item.idListaConteudo}" />
                </vivo:tbRowColumn>
                <vivo:tbRowColumn>
                    <netui:label value="{container.item.nmCanal}"/>
                </vivo:tbRowColumn>
            </vivo:tbRow>
            </netui-data:repeaterItem>
            </netui-data:repeater>
            </vivo:tbRows>
        </vivo:tbTable>
    </vivo:quadro>
</form>
</acesso:controlHiddenItem>
</body>
</html>