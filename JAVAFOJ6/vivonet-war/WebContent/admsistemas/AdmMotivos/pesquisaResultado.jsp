<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="pesquisaMotivosForm" name="pesquisaMotivosForm" scope="request" />
<html>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <body>
        <vivo:tbTable selectable="true" styleId="resultado" layoutHeight="328" tableWidth="757" layoutWidth="757">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn width="82%"> Motivos Encontrados </vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="6%" align="center">Editar</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="6%" align="center">Estado</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn width="6%" align="center">Associar</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
            <logic:iterate id="motivos" name="pesquisaMotivosForm" property="pesquisaMotivos">
                <vivo:tbRow key="linha">
                    <vivo:tbRowColumn>
                        <bean:write name="motivos" property="dsMotivo"/>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <img onclick="editar(<bean:write name="motivos" property="idMotivo"/>,'<bean:write name="motivos" property="dsMotivo"/>');" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" alt='Editar Motivo'>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <logic:equal name="motivos" property="ativo" value="1">
                            <img onclick="desativar(<bean:write name="motivos" property="idMotivo"/>,'<bean:write name="motivos" property="dsMotivo"/>');" src="/FrontOfficeWeb/resources/images/bt_icon_habilitar.gif" alt='Motivo habilitado. Clique para desabilitar.'>
                        </logic:equal>
                        <logic:equal name="motivos" property="ativo" value="0">
                            <img onclick="ativar(<bean:write name="motivos" property="idMotivo"/>,'<bean:write name="motivos" property="dsMotivo"/>');" src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" alt="Motivo desabilitado. Clique para reabilitar.">
                        </logic:equal>   
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <img onclick="associar(<bean:write name="motivos" property="idMotivo"/>,'<bean:write name="motivos" property="dsMotivo"/>');" src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" alt='Associar Ações do Workflow'>
                    </vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        <form action="excluirMotivo.do">
            <input type=hidden name="idMotivo">
            <input type=hidden name="dsMotivo">
        </form>
        <iframe id="ifrmResultExcl" name="ifrmResultExcl" style="width:0px; height:0px; display:none;"></iframe>
    </body>
<script>
f=document.forms[0];
function editar(idMotivo, dsMotivo) {
    parent.divIncluirAlterar.style.display="block";
    f.target="ifrmIncluirAlterar";
    f.idMotivo.value=idMotivo;
    f.dsMotivo.value=dsMotivo;
    f.action="lerMotivo.do";
    f.submit();
}
function desativar(idMotivo, dsMotivo) {
    if (confirm('Deseja desativar \''+dsMotivo+'\'?')) {
        parent.screen_block();
        f.idMotivo.value=idMotivo;
        f.action="excluirMotivo.do";
        f.target="ifrmResultExcl";
        f.submit();
    }
}
function ativar(idMotivo, dsMotivo) {
    if (confirm('Deseja reativar \''+dsMotivo+'\'?')) {
        parent.screen_block();
        f.idMotivo.value=idMotivo;
        f.action="excluirMotivo.do";
        f.target="ifrmResultExcl";
        f.submit();
    }
}
function associar(idMotivo, dsMotivo) {
    parent.divAssoc.style.display="block";
    f.idMotivo.value=idMotivo;
    f.dsMotivo.value=dsMotivo;
    f.target="ifrmAssoc";
    f.idMotivo.value=idMotivo;
    f.action="lerMotivoAcoes.do";
    f.submit();
}
function fechar() {
    parent.pesquisar();
}
parent.screen_unblock();

</script>
</html>