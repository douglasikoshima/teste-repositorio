<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="notasForm" name="notasForm" scope="request" />
<bean:define id="acaoVO" name="notasForm" property="acaoVO"/>
<bean:define id="notaVO" name="notasForm" property="notaVO"/>
<html>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <head>
        <title></title>
    </head>
<SCRIPT LANGUAGE="JavaScript">
<!--
function fechar() {
    if (parent.inboxNotaUra) {
        fecharInbox();
    } else {
        fecharNotaUra();
    }
}
function fecharInbox(){
    document.forms[0].action='voltarInbox.do';
    document.forms[0].submit();    
    parent.dvDetalheNota.style.display = 'none';  
}
function fecharNotaUra(){
   parent.dvDetalheNota.style.display='none';  
}
function abaHistoricoNota(){
	abaSelected(btAbaInfo, historicoNota);
    divOperacao.style.display='none';
    divHistoricoNota.style.display=''; 
}
function abaOperacao(){
	abaSelected(btAbaInfo,operacao);
    divHistoricoNota.style.display='none';
    divOperacao.style.display='';
}
function clickAbaOperacao(){
    var obj = document.getElementById("acaoSel");
    if(obj.value == -1)
        alert('A nota está fechada');
    else{
<%                        
  String dtAberturaFim = ((WFAtdNotaVO)notaVO).getDtAberturaFim();  
  boolean disabled = (dtAberturaFim != null && !dtAberturaFim.equals(ConstantesCRM.SVAZIO))?true:false;
  if(disabled){    
    out.println("alert('A nota está fechada');");
  }else{
    out.println("screen_block(); abaOperacao();");
    out.println("var iframe = document.getElementById(\"ifrmOperacao\");");
    out.println("iframe.src=\"iframeOperacaoNota.do\";");
  }
 %>
   }
}
function changeOpcao(obj){
    abaOperacao();
    var acaoLabel = obj.options[obj.selectedIndex].text;
    var value = obj.options[obj.selectedIndex].value;
    if(value != 0){
        var iframe = document.getElementById("ifrmOperacao");
        iframe.src="iframeOperacaoNota.do?acaoLabel="+acaoLabel+"&acaoSel="+value;
    }
    else{
        alert('Selecione uma operação');
    }
}
function screen_block() {
    //Liga animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
}

function screen_unblock() {
    //Desliga animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
}

//-->
</SCRIPT>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="ntura_det_verpagina">
    <html:form action="lerNotasURADetalhe.do">
    <html:hidden property="idAtendimentoNota" name="notasForm"/>
    <html:hidden property="acaoSel" name="notasForm"/>
    <input type="hidden" name="tela" value="">
        <table width="100%">
			<vivo:quadro id="detalhe_nota" height="400" width="600" label="&nbsp;Detalhes da Nota" scroll="N">
				<table width="100%" cellpadding="1" cellspacing="1" border="0">
					<!-- Dados da nota -->
					<tr>
						<td>
							<TABLE border=0>
							<TR>
								<TD valign=top>Número do Processo:</TD>
								<TD valign=top><bean:write name="notasForm" property="notaVO.idAtendimento"/></TD>                            
								<TD valign=top>Comentário:</TD>
								<TD><div style="overflow:auto; width:300px; height:45px;"><bean:write name="notasForm" property="notaVO.comentario"/></div></TD>
							</TR>
							<TR>
								<TD valign=top>Tipo da Nota:</TD>
								<TD valign=top><bean:write name="notasForm" property="notaVO.tipoNotaAtendimento"/></TD>
								<TD valign=top>Data de criação:</TD>
								<TD valign=top><bean:write name="notasForm" property="notaVO.dtAberturaIni"/></TD>
							</TR>
							</TABLE>
						</td>
					</tr>
				</table>
                <vivo:abaGrupo id="btAbaInfo" width="569" height="10" styleClass="abatexto">
                    <vivo:abaItem id="historicoNota" onclick="abaHistoricoNota();" value="Histórico Nota" select="S"/>
                    <vivo:abaItem id="operacao" onclick="clickAbaOperacao();" value="&nbsp;&nbsp;&nbsp;Operação&nbsp;&nbsp;&nbsp;"/>
                </vivo:abaGrupo>
                <div id="divHistoricoNota">						
                    <iframe id="ifrmHistorico" name="ifrmHistorico" src="lerNotasURAHistorico.do" width="590" height="270" frameborder="0"></iframe>
                </div>
                <div id="divOperacao" style="display:none;">
                    <iframe id="ifrmOperacao" name="ifrmOperacao" width="590" height="270" frameborder="0"></iframe>
                </div>
                <div align="right"><img hspace="8" vspace="6" style="border:none;" onClick="fechar(); return false;" src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_fechar_over.gif"/></div>
			</vivo:quadro>
         </html:form>
    </acesso:controlHiddenItem>
    </body>
</html>