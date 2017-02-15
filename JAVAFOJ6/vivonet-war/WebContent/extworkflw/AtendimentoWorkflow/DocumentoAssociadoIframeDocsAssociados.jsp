<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="tiposVO" name="form" property="atendimentoWorkflowVO.atendimentoWorkflowTecnicoVO.WFDocumentoTecnicoTipoVOArray"/>
<bean:define id="estadosVO" name="form" property="atendimentoWorkflowVO.atendimentoWorkflowTecnicoVO.WFDocumentoTecnicoEstadoVOArray"/>
<bean:define id="procedenciasVO" name="form" property="atendimentoWorkflowVO.atendimentoWorkflowTecnicoVO.procedenciaVOArray"/>


<form>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td width="100%">
            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="725" layoutHeight="110" tableWidth="741" styleId="docTecAssoc" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Tipo</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="15%" type="string">No.</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="string">Estado</vivo:tbHeaderColumn>					
                    <vivo:tbHeaderColumn align="center" width="10%" type="data">Abertura</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left"   width="15%" type="string">Comentários</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="10%" type="data">Fechamento</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left"   width="20%" type="string">Resposta</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="right"  width="10%" type="string">QPV</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows scroll="false">
                    <logic:iterate id="associadosVO" name="form" property="documentosAssociados" indexId="idx">
                    <vivo:tbRow key="documento">
                        <vivo:tbRowColumn>
                            <bean:write name="associadosVO" property="dsTipoDocumentoProcesso"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="associadosVO" property="nrDocumento"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="associadosVO" property="dsEstadoDocumentoProcesso"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="associadosVO" property="dtAbertura"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <vivo:hint maxLength="15" spaces="S"><bean:write name="associadosVO" property="dsDocumento"/></vivo:hint>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="associadosVO" property="dtFechamento"/>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <vivo:hint maxLength="15" spaces="S"><bean:write name="associadosVO" property="dsResposta"/></vivo:hint>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <center><bean:write name="associadosVO" property="qtdProcessosVinculados"/><center>
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                    </logic:iterate>
                </vivo:tbRows>
            </vivo:tbTable>
        </td>
    </tr>
</table>
</form>
   
<script language="JavaScript">
    function submitAplicarAss() {

        dt1 = document.forms["filtrarAss"].elements["filtroAssociados.dtPeriodo1"].value;
        dt2 = document.forms["filtrarAss"].elements["filtroAssociados.dtPeriodo2"].value;

        if (dt1 == "" || dt2 == "") {
            //Liga animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    
            document.forms["filtrarAss"].method = "POST";
            document.forms["filtrarAss"].action = "documentoAssociadoPesquisarAssociados.do";
            document.forms["filtrarAss"].submit();            
            parent.document.location="documentoAssociadoBegin.do?idAtendimento=<bean:write name="form" property="idAtendimento"/>";
        } else {
            if (validaDataFinal(dt1,dt2)) {
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        
                document.forms["filtrarAss"].method = "POST";
                document.forms["filtrarAss"].action = "documentoAssociadoPesquisarAssociados.do";
                document.forms["filtrarAss"].submit();                
                parent.document.location="documentoAssociadoBegin.do?idAtendimento=<bean:write name="form" property="idAtendimento"/>";
            } else {
                alert("Data início deve ser anterior que a Data final!");
            }
        }
    }
    
    function limparFiltroAss() {
        document.forms["filtrarAss"].elements["filtroAssociados.idDocTecTipo"].value="";
        document.forms["filtrarAss"].elements["filtroAssociados.nrDocumentoTecnico"].value="";
        document.forms["filtrarAss"].elements["filtroAssociados.dtPeriodo1"].value="";
        document.forms["filtrarAss"].elements["filtroAssociados.dtPeriodo2"].value="";
        document.forms["filtrarAss"].elements["filtroAssociados.comentario"].value="";
        document.forms["filtrarAss"].elements["filtroAssociados.idDocTecEstado"].value="";
        
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        document.forms["filtrarAss"].method = "POST";
        document.forms["filtrarAss"].action = "documentoAssociadoPesquisarAssociados.do";
        document.forms["filtrarAss"].submit();
    }

    function validaData(data){
        if(data.value == '')
            return false;
        if(!validate_date(data.value)){
            data.value = '';
            data.focus();
            alert("Data inválida");
        }
    }
    
    function recarregaTabela()
    {
       DoResizeHeaderTableVivo();
    }
        
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

    document.body.style.backgroundColor = '#ededed';
    if (parent.up)
    {
        parent.alteraSizeMin();
    }
    else
    {
        parent.alteraSize();
    }
</script>
