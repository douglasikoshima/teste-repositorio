<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<%
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache");
    if (request.getProtocol().equals("HTTP/1.1")) {
        response.setHeader("Cache-Control", "no-cache");
    }
%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm"/>
<bean:define id="atdVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm.atendimentoDetalheVO.atendimentoVO"/>
<bean:define id="estadosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm.atendimentoDetalheVO.WFEstadoVOArray"/>
<bean:define id="subEstadosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm.atendimentoDetalheVO.WFSubEstadoVOArray"/>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>

<script>
    function submitEstado() {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    
        document.forms["formHistorico"].method = "POST";
        document.forms["formHistorico"].action = "obterSubEstado.do";
        document.forms["formHistorico"].target = "iframeEstado";
        document.forms["formHistorico"].submit();
    }
    
    function submitPesquisarHist() {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        document.forms["formHistorico"].method = "POST";
        document.forms["formHistorico"].action = "historicoPesquisar.do";
        document.forms["formHistorico"].target = "iHistorico";
        document.forms["formHistorico"].submit();
    }
    
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
    
</script>

<form tagId="formHistorico" id="formHistorico" action="historicoPesquisar" method="post">
   <html:hidden name="atdVO" property="idAtendimento" title="idAtendimento"/>
    <table width="690" border="0">
        <tr>
            <td width="40%">Estado:
                <html:select name="form" property="estadoSel" title="estadoSel" style="width=200px" onchange="submitEstado();">
                    <html:option value="-1" key="estadoSel">&nbsp;</html:option>
                    <html:options collection="estadosVO" property="idEstado" labelProperty="dsEstado" /> 
                </html:select>
            </td>
            <td width="40%">Sub-Estado:
                <html:select name="form" property="subEstadoSel" title="subEstadoSel" style="width=200px">
                    <html:option value="-1" key="subEstadoSel">&nbsp;</html:option>
                    <html:options collection="subEstadosVO" property="idSubEstado" labelProperty="dsSubEstado" /> 
                </html:select>
            </td>
            <td width="20%">            
                <img onClick="submitPesquisarHist(); return false" style="border:none;" id="bt01" src="/FrontOfficeWeb/resources/images/bt_filtro_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_filtro_over.gif"/>            
            </td>
        </tr>
    </table>


    <!--Quadro Flutuante-->
    <vivo:quadroFlutuante id="dvComentAcao" idIframe="ifrmComentAcao" width="300" height="150" spacesTop="25" spacesLeft="100" display="none" url="<%=request.getContextPath()%>"/>

    <div id="divRelacionamento" style="visibility:visible;POSITION:relative;">
        <iframe name="iHistorico" id="iHistorico" src="iHistorico.jsp" width="758" height="135" scrolling="no"></iframe>
    </div>

    <iframe scrolling="yes" style="visibility:hidden;" name="iframeEstado" height="1px" width="1px"></iframe>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
</form>
   
