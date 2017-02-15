<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache");
    if (request.getProtocol().equals("HTTP/1.1")) {
        response.setHeader("Cache-Control", "no-cache");
    }
%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="wFMotivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>


<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>

<script>
    function obtemDataAbertura(){
        data = new Date();
        dia = data.getDate();
        mes = data.getMonth() + 1;
        ano = data.getFullYear();
        
        if(dia < 16){
            if(mes == 5 || mes == 7 || mes == 10 || mes == 12) //o mes anterior tem 30 dias
                dia += 15;
            else if(mes == 3)  //o mes anterior é fevereiro ****falta fazer pra qd for ano bissexto****
                dia += 13;
            else{ 
                if(mes == 1){  //quando for janeiro
                    mes = 13;
                    ano -= 1;
                }
                dia += 16;
            }
            mes -= 1;
        }
        else
            dia -= 15;
        
        //document.forms[0].dtAbertura.value = dia + "/" + mes + "/" + ano; 

    }
    
    function mostraComentario(){
        dvComentAcao.style.display = '';
        document.forms[0].target = "ifrmComentAcao";
        document.forms[0].action = "comentarioAcao.jsp";
        document.forms[0].submit(); 
    }
</script>


<form action="historicoGravar.do" method="post">
    <table width="690" border="0">
        <tr>
            <td width="7%">Estado:</td>
            <td width="15%">
                <netui:select dataSource="{}">
                    <netui:selectOption value="Todos"/> 
                    <netui:selectOption value="Aberto"/>                
                    <netui:selectOption value="Fechado"/>                
                    <netui:selectOption value="Pausa"/>                
                    <netui:selectOption value="Tratamento"/>                
                    <netui:selectOption value="Retorno"/>                               
                </netui:select>
            </td>
            <td width="10%">Sub-Estado:</td>
            <td width="13%">
                <netui:select dataSource="{}">
                    <netui:selectOption value="Todos"/> 
                    <netui:selectOption value="Aberto"/>                
                    <netui:selectOption value="Fechado"/>                
                    <netui:selectOption value="Pausa"/>                
                    <netui:selectOption value="Tratamento"/>                
                    <netui:selectOption value="Retorno"/>                               
                </netui:select>
            </td>
            <td width="55%">
                <vivo:botao id="bt01" width="60px" height="25px" value="Filtro" styleClass="btTemplate"/>
            </td>
        </tr>
    </table>

    <vivo:tbTable selectable="true" onRowClick="mostraComentario();" layoutWidth="690" layoutHeight="135" tableWidth="100%" styleId="tableTitle" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="3%" type="string"></vivo:tbHeaderColumn>					
            <vivo:tbHeaderColumn align="center" width="29%" type="string">Tipo de Contato</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="13%" type="string">Grupo</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="14%" type="string">Atendente</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="13%" type="string">Operação</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="12%" type="string">Estado / SubEstado</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="14%" type="string">Data</vivo:tbHeaderColumn>                                                
        </vivo:tbHeader>

        <vivo:tbRows>
            <logic:iterate id="atdVO" name="form" property="atendimentosVO" indexId="idx">
                <vivo:tbRow key="atendimento">
                    <vivo:tbRowColumn><bean:write name="atdVO" property="idAtendimento" format="000000"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="atdVO" property="arvoreAtendimentoVO.descricaoCompleta"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="atdVO" property="WFGrupoVO.dsGrupo"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <logic:notEmpty name="atdVO" property="usuarioVIVO">
                            <bean:write name="atdVO" property="usuarioVIVO.nmNome"/>
                        </logic:notEmpty>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <bean:write name="atdVO" property="WFEstadoVO.dsEstado"/> / 
                        <bean:write name="atdVO" property="WFSubEstadoVO.dsSubEstado"/>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="atdVO" property="dtAbertura"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="atdVO" property="dtFechamento"/></vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>

        <vivo:tbRows>
            <vivo:tbRow key="linha1">
                <vivo:tbRowColumn>1</vivo:tbRowColumn>
                <vivo:tbRowColumn>De/Para Prefixo Específico</vivo:tbRowColumn>
                <vivo:tbRowColumn>Call-center</vivo:tbRowColumn>
                <vivo:tbRowColumn>Eric Senne</vivo:tbRowColumn>
                <vivo:tbRowColumn>Tratamento</vivo:tbRowColumn>
                <vivo:tbRowColumn>Em Ligação / SubEstado 1</vivo:tbRowColumn>
                <vivo:tbRowColumn>10/03/2004 12:00h</vivo:tbRowColumn>
            </vivo:tbRow>
            <vivo:tbRow key="linha2">
                <vivo:tbRowColumn>2</vivo:tbRowColumn>
                <vivo:tbRowColumn>Crédito em Conta Futura</vivo:tbRowColumn>
                <vivo:tbRowColumn>Call-center</vivo:tbRowColumn>
                <vivo:tbRowColumn>Marcelo Nunes</vivo:tbRowColumn>
                <vivo:tbRowColumn>Agendamento</vivo:tbRowColumn>
                <vivo:tbRowColumn>Pausa / SubEstado 2</vivo:tbRowColumn>
                <vivo:tbRowColumn>20/03/2004 13:00h</vivo:tbRowColumn>
            </vivo:tbRow>
            <vivo:tbRow key="linha3">
                <vivo:tbRowColumn>3</vivo:tbRowColumn>
                <vivo:tbRowColumn>De/Para Prefixo Específico</vivo:tbRowColumn>
                <vivo:tbRowColumn>Call-center</vivo:tbRowColumn>
                <vivo:tbRowColumn>Marcelo Torres</vivo:tbRowColumn>
                <vivo:tbRowColumn>Cancelamento</vivo:tbRowColumn>
                <vivo:tbRowColumn>Retorno / SubEstado 3</vivo:tbRowColumn>
                <vivo:tbRowColumn>15/03/2004 15:00h</vivo:tbRowColumn>
            </vivo:tbRow>
            <vivo:tbRow key="linha4">
                <vivo:tbRowColumn>4</vivo:tbRowColumn>
                <vivo:tbRowColumn>Contatar Cliente</vivo:tbRowColumn>
                <vivo:tbRowColumn>Call-center</vivo:tbRowColumn>
                <vivo:tbRowColumn>Alexandre Nunes</vivo:tbRowColumn>
                <vivo:tbRowColumn>Insistência</vivo:tbRowColumn>
                <vivo:tbRowColumn>Tratamento / SubEstado 4</vivo:tbRowColumn>
                <vivo:tbRowColumn>25/03/2004 10:00h</vivo:tbRowColumn>
            </vivo:tbRow>                    
        </vivo:tbRows>
    </vivo:tbTable>

    <!--Quadro Flutuante-->
    <vivo:quadroFlutuante id="dvComentAcao" idIframe="ifrmComentAcao" width="300" height="150" spacesTop="25" spacesLeft="100" display="none" url="<%=request.getContextPath()%>"/>

</form>
