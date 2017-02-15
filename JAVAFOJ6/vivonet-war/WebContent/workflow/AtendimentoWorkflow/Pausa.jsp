<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<head>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="gruposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFGrupoVOArray"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>
<bean:define id="usuariosVIVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.usuarioVIVO"/>
</head>
<body>
<acesso:controlHiddenItem nomeIdentificador="wor_paus_verpagina">
<form action="pausaGravar.do" name="atendimentoForm" method="post" tagId="formPausa" id="formPausa">
    <html:hidden name="form" property="idAtendimento" title="idAtendimento"/>
    <html:hidden name="form" property="idAtividade"/>
    <html:hidden name="form" property="inCRI"/>
    <html:hidden name="form" property="fila"/>
<div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>
<script>
    <!--
    var moveToolTip = true;;
    xBump=yBump=10;
    MSIE=document.all;
    NS6=document.getElementById&&!document.all;
    if(MSIE||NS6){
        ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
    }

    function carregaToolTip(content) {
        ttipObj.innerHTML=content;
        ttipObj.style.display='';
    }
    -->
</script>
    <table border="0" width="100%">
        <tr>
            <td height="10" colspan="4"></td>
        </tr>
        <logic:equal name="form" property="inCRI" value="0">
        <tr>
            <td width="60">Grupo:</td>
            <td align="left" style="padding-left:4px;">
            <html:select name="form" property="grupoSel" title="grupoSel" style="width=250px" onchange="submitGrupo();focaTipCampos(this.options[this.selectedIndex].text,this, 0, 70, 35);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 0, 70, 35);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 0, 70, 35);" onblur="HideTip();" onmouseout="HideTip();">
                <html:option value="-1" key="grupoSel">&nbsp;</html:option>
                <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
            </html:select>
            </td>
            <td>Usuário:</td>
            <td align="left">
            <html:select name="form" property="usuarioSel" title="usuarioSel" style="width=250px">
                <html:option value="-1" key="usuarioSel">&nbsp;</html:option>
                <html:options collection="usuariosVIVO" property="idPessoaUsuario" labelProperty="nmNome"/>
            </html:select>
            </td>
        </tr>
        </logic:equal>
        <tr valign="top">
            <td width="60" valign="top">Motivo:</td>
            <td valign="top" align="left" style="padding-left:4px;">
            <html:select name="form" property="motivoSel" title="motivoSel" style="width=250px">
                <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/>
            </html:select>
            </td>
            <td>Comentário:</td>
            <td rowspan="3" align="left">
                <html:textarea name="form" property="comentario" rows="3" style="width:280px;height:70px;" onblur="return checaTextarea(this,1000);return false;"  onkeyup="return checaTextarea(this,1000);return false;"/>
            </td>
        </tr>
        <tr valign="top">
            <td valign="top" width="62">Data do contato:</td>
            <td valign="top" align="left">
                <html:text name="form" property="atendimentoWorkflowVO.atendimentoWorkflowAgendamentoVO.dtContato" styleId="dtContato" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="verificaData(this);"/>
                <img src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtContato', '%d/%m/%Y');">
                Horário do contato:
                <html:text name="form" property="atendimentoWorkflowVO.atendimentoWorkflowAgendamentoVO.hrContato" styleId="hrContato" size="7" maxlength="5" onkeyup ="Formatar(this.value, this.form.name, this.name, 'hora');" onchange="Formatar(this.value, this.form.name, this.name, 'hora');" onblur="verificaHora(this);"/>
            </td>
        </tr>
        <tr>
            <td height="15" colspan="4">
        </tr>
        <tr>
            <td colspan="4" align="right">
                <acesso:controlHiddenItem nomeIdentificador="wor_paus_gravar">
                    <img hspace="1" onClick="submitAplicar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
                </acesso:controlHiddenItem>
            </td>
        </tr>
    </table>
</form>
</acesso:controlHiddenItem>
<iframe scrolling="yes" style="visibility:hidden;" name="iframeUsuario" height="1px" width="1px"></iframe>
<script language="javascript">
    <!--
    function checaTextarea(obj, tamanho){
      if(obj.value.length>tamanho){
        obj.value = obj.value.substr(0,tamanho);
        alert("O campo comentário não aceita mais de "+tamanho+" caracteres");
      }
    }

    function submitAplicar() {
        data         = new Date();
        minutos      = data.getMinutes();
        hora         = data.getHours();
        horaAtual    = hora+":"+minutos;
        horaAgendada = document.forms["formPausa"]['atendimentoWorkflowVO.atendimentoWorkflowAgendamentoVO.hrContato'].value ;
        dia = data.getDate();
        mes = data.getMonth() + 1;
        ano = data.getFullYear();
        dtAtual    = dia+"/"+mes+"/"+ano;
        dtAgendada = document.forms["formPausa"]['atendimentoWorkflowVO.atendimentoWorkflowAgendamentoVO.dtContato'].value ;

        if (document.forms[0].elements["motivoSel"].value <= 0) {
            alert("Selecione um Motivo!");
            return;
        }
        <logic:equal name="form" property="inCRI" value="0">
        if (document.forms[0].elements["grupoSel"].value <= 0) {
            alert("Selecione um Grupo!");
            return;
        }
        </logic:equal>
        //Valida as datas
        if(document.forms[0].elements["atendimentoWorkflowVO.atendimentoWorkflowAgendamentoVO.dtContato"].value=="") {
            alert("Selecione uma Data!");
            return;
        }
        if(!verificaData(document.forms[0].elements["atendimentoWorkflowVO.atendimentoWorkflowAgendamentoVO.dtContato"])) {
            alert("Selecione uma Data!");
            return;
        }
        if(document.forms[0].elements["atendimentoWorkflowVO.atendimentoWorkflowAgendamentoVO.hrContato"].value=="") {
            alert("Digite a hora no formato hh:mm");
            return;
        }
        if(!verificaHora(document.forms[0].elements["atendimentoWorkflowVO.atendimentoWorkflowAgendamentoVO.hrContato"])){
            return;
        }
        if( !(validaDataFinalEx(dtAtual, dtAgendada, horaAtual, horaAgendada))) {
            document.forms["formPausa"]['atendimentoWorkflowVO.atendimentoWorkflowAgendamentoVO.dtContato'].focus();
            alert('A data de agendamento deve ser posterior a data atual!');
            return;
        }
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        if (parent.dvRetornoOperacao) {
            parent.dvRetornoOperacao.style.visibility="";
            targetFrame = "iframeRetornoOperacao";
        } else {
            targetFrame = "";
        }
        document.forms["formPausa"].method = "POST";
        document.forms["formPausa"].action = "pausaGravar.do";
        document.forms["formPausa"].target = targetFrame;
        document.forms["formPausa"].submit();
    }

    function submitGrupo() {
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        var meuCliente = '';
        if (parent.inMeuCliente) {
            meuCliente = '?meuCliente=true';
        }

        document.forms["formPausa"].method = "POST";
        document.forms["formPausa"].action = "obterUsuario.do" + meuCliente;
        document.forms["formPausa"].target = "iframeUsuario";
        document.forms["formPausa"].submit();
    }

    function verificaData(data){
        if(data.value == '')
            return false;
        if(!validate_date(data.value)){
            data.value = '';
            data.focus();
            alert("Data inválida!");
            return false;
        }else{
            return true;
        }
    }

    function verificaHora(hora){
        if(hora.value == '')
            return false;
        if(!validaHora(hora.value)){
            hora.value = '';
            hora.focus();
            alert("Hora inválida!");
            return false;
        }else{
            return true;
        }
    }

    function testaEnter() { if (window.event.keyCode==13) return false; else return true; }
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    document.body.style.backgroundColor = '#ededed';
    -->
</script>
</body>

