<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="retornoCTIForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="retornoCTIForm"/>

   

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<acesso:controlHiddenItem nomeIdentificador="adm_rpes_verpagina">

<vivo:tbTable selectable="false" layoutWidth="753" layoutHeight="320" tableWidth="753" styleId="tableTitle2" sortable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="15%" type="string">Código</vivo:tbHeaderColumn>					
        <vivo:tbHeaderColumn align="center" width="5%" type="string">Sigla</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left"   width="40%" type="string">Descrição</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left"   width="10%" type="string">Status</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left"   width="5%" type="string">Padrão</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Editar</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Excluir</vivo:tbHeaderColumn>
    </vivo:tbHeader>

    <vivo:tbRows>
        <logic:notEmpty name="retornoCTIForm" property="retornoWFCTIResultado">
            <bean:define name="retornoCTIForm" property="retornoWFCTIResultado" id="retornoWFCTIResultado"/>
            
            <logic:iterate name="retornoWFCTIResultado"  property="retornoWFCTIVOArray" id="retornoWFCTIVO">
                <vivo:tbRow key="linha1" zebrar="S">
                    <vivo:tbRowColumn><bean:write name="retornoWFCTIVO" property="idRetornoWFCTI"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="retornoWFCTIVO" property="sgRetornoWFCTI"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="retornoWFCTIVO" property="dsRetornoWFCTI"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <logic:equal name="retornoWFCTIVO" property="sgStatus" value="A">Ativo</logic:equal>
                        <logic:equal name="retornoWFCTIVO" property="sgStatus" value="C">Cancelado</logic:equal>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <logic:equal name="retornoWFCTIVO" property="inPadrao" value="1">Sim</logic:equal>
                        <logic:equal name="retornoWFCTIVO" property="inPadrao" value="0">Não</logic:equal>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                    <acesso:controlHiddenItem nomeIdentificador="adm_rpes_alterar">
                    <img src="<%=request.getContextPath()%>/resources/images/bt_icon_alterar.gif" style="cursor:hand;" title="Alterar retorno" onclick="parent.editar(<bean:write name="retornoWFCTIVO" property="idRetornoWFCTI"/>);">
                    </acesso:controlHiddenItem>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                    <acesso:controlHiddenItem nomeIdentificador="adm_rpes_excluir">
                    <img src="<%=request.getContextPath()%>/resources/images/bt_icon_excluir.gif" style="cursor:hand;" title="Excluir retorno" onclick="parent.excluir(<bean:write name="retornoWFCTIVO" property="idRetornoWFCTI"/>,'<bean:write name="retornoWFCTIVO" property="dsRetornoWFCTI"/>');">
                    </acesso:controlHiddenItem>
                    </vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </logic:notEmpty>
    </vivo:tbRows>
</vivo:tbTable>

   

</acesso:controlHiddenItem>
<script language="javascript">
    //Fim animação
    if( top.dvAnimarAguarde != null ) top.stopAnimation();    
</script>
