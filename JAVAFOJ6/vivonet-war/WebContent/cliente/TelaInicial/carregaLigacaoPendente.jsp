<!--
Módulo.....: Gestão de Senhas
Caso de Uso: Validar Cliente
Versão.....: $Author: a5112272 $ - $Revision: 1.5 $ - $Date: 2011/06/14 14:34:06 $
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="validarClienteForm"/>

<html>
    <head>
        <link href="../../resources/css/frontoffice.css" rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="../../resources/scripts/validacioncliente.js"></script>                                
    </head>
    <body>
    <!--acesso:controlHiddenItem nomeIdentificador="wor_vc_verpagina"--> 
    <html:form action="/cliente/TelaInicial/transferirUraAction.do">
        <div id="divNovaLigacao" style="z-index:999; position:absolute; left:0; top:0; visibility:hidden;">            
            <logic:equal name="form" property="novaLigacao" value="true">
                <bean:define id="formNovaLigacao" name="form" property="novaLigacaoForm"></bean:define>
                 <table id="tabelaNovaLigacao" width="790" height="41" border="0" align="left" cellpadding="0" cellspacing="0" bgcolor="#e2e2e2" class="bura_texto">
                    <tr>
                        <td width="36" rowspan="2"><img src="<%=request.getContextPath()%>/resources/images/bura_bg2.gif"/></td>
                        <td width="100" align="right">Nr. Telefone:</td>
                        <td width="80" align="left" style="padding-left:2px;"><bean:write name="formNovaLigacao" property="numeroOrigem"/></td>
                        <td width="80" align="right">Nr. Linha:</td>
                        <td width="80" align="left" style="padding-left:2px;"><bean:write name="formNovaLigacao" property="numeroConsultado"/></td>
                        <td align="center" width="120"  >
                            <logic:equal name="formNovaLigacao" property="cliente" value="true">
                                <label style="color=green; background-color=yellow;" ><b>Cliente</b></label>
                            </logic:equal>
                            <logic:equal name="formNovaLigacao" property="usuario" value="true">
                                <label style="color=green; background-color=yellow;"><b>Usuário</b></label>
                            </logic:equal>
                        </td>
                        <td rowspan="2" align="center" valign="middle">
                            <img id="imgVoltar" src="../../resources/images/bt_voltar_nrml.gif" onclick="voltarAtendimentoAnterior();" style="cursor:hand; border:none;"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">IdUra:</td>
                        <td align="left" style="padding-left:2px;"><bean:write name="formNovaLigacao" property="idUra"/></td>
                        <td align="right">IdCallCenter:</td>
                        <td align="left" style="padding-left:2px;"><bean:write name="formNovaLigacao" property="idCallCenter"/></td>
                        <td align="center" >
                            <logic:equal name="formNovaLigacao" property="senhaValidada" value="false">
                                <label style="color=red;"><b>Senha não validada</b></label>
                            </logic:equal>
                            <logic:equal name="formNovaLigacao" property="senhaValidada" value="true">
                                <label style="color=green;"><b>Senha validada</b></label>
                            </logic:equal>
                        </td>
                        
                    </tr>
                </table>
            </logic:equal>
        </div>
    </html:form>
    <!--/acesso:controlHiddenItem-->
    </body>
</html>
<script language="javascript">
     
    
    <logic:equal name="form" property="novaLigacao" value="false">
    
    top.frameApp.frameURA.spNrOrigem.innerText='<bean:write name="form" property="numeroOrigem" />';
    
    top.frameApp.frameURA.ligacaoCTI=true;
    
    top.frameApp.frameURA.divNovaLigacao.innerHTML = '';
    
    top.frameApp.frameURA.dvTerminar.style.display='';
    top.frameApp.frameURA.dvLigacaoPendente.style.display='none';
    
    top.frameApp.frameURA.carregaLigacaoPendente = new function (){}; 
    
    top.frameApp.frameURA.document.forms[0]["numeroConsultado"].value="<bean:write name="form" property="numeroConsultado"/>"; 
    top.frameApp.frameURA.document.forms[0]["idUra"].value="<bean:write name="form" property="idUra"/>"; 
    top.frameApp.frameURA.document.forms[0]["idCallCenter"].value="<bean:write name="form" property="idCallCenter"/>"; 
    top.frameApp.frameURA.document.forms[0]["navegacaoURA"].value="<bean:write name="form" property="navegacaoURA"/>"; 
    top.frameApp.frameURA.document.forms[0]["senha"].value="<bean:write name="form" property="senhaValidada"/>"; 
    top.frameApp.frameURA.document.forms[0]["tipoProc"].value="<bean:write name="form" property="tipoProc"/>";
    top.frameApp.frameURA.document.forms[0]["numeroProc"].value="<bean:write name="form" property="numeroProc"/>";
    top.frameApp.frameURA.document.forms[0]["relacionamento"].value="<bean:write name="form" property="tipoRelacionamento"/>"; 
    top.frameApp.frameURA.document.forms[0]["identificacao"].value="<bean:write name="form" property="idPos"/>"; 
    top.frameApp.frameURA.document.forms[0]["numeroOrigem"].value="<bean:write name="form" property="numeroOrigem"/>"; 
    top.frameApp.frameURA.document.forms[0]["observacao"].value="<bean:write name="form" property="observacao"/>"; 
    top.frameApp.frameURA.document.forms[0]["indRedirTimeoutURA"].value="<bean:write name="form" property="indRedirTimeoutURA"/>";
    
        
    <logic:equal name="form" property="senhaValidada" value="true">
        top.frameApp.frameURA.dvSenhaValidadaTrue.style.display='';
        top.frameApp.frameURA.dvSenhaValidadaFalse.style.display='none';
        <logic:equal name="form" property="cliente" value="true">
        top.frameApp.frameURA.sp_Cli_user.innerHTML="Cliente";
        </logic:equal>
        <logic:equal name="form" property="usuario" value="true">
        top.frameApp.frameURA.sp_Cli_user.innerHTML="Usuário";
        </logic:equal>        
        top.frameApp.frameURA.senhaValidadaCTI = true;
    </logic:equal>
    // arrumar para carregamento com bindCombos
    <logic:equal name="form" property="senhaValidada" value="false">
        top.frameApp.frameURA.dvSenhaValidadaTrue.style.display='none';
        top.frameApp.frameURA.dvSenhaValidadaFalse.style.display='';
        top.frameApp.frameURA.senhaValidadaCTI = false;
    </logic:equal>
    
    /**
    *   Quando recebe uma chamada telefonica carrega o id da chamada
    */     
    top.frameApp.idChamadaTelefonica = '<bean:write name="form" property="idChamadaTelefonica"/>';  
         
    /**
    *   Caso senha validade carrega o tipo de relacionamento
    */     
    <logic:equal name="form" property="senhaValidada" value="true">
    top.frameApp.idRelacionamento = '<bean:write name="form" property="tipoRelacionamento"/>';
    </logic:equal>
    
    <logic:notEqual name="form" property="observacao" value="">
    //mostra observaçao da CTI
    alert("Observaçao Atendente: "+"<bean:write name="form" property="observacao" />");
    </logic:notEqual>
    

    if(top.frameApp.idChamadaTelefonica!=''){
        top.frameApp.document.forms[0].valorPesquisa.value='<bean:write name="form" property="numeroConsultado"/>';
        top.frameApp.carregaTela();
    }
    
    </logic:equal>
     
    <logic:equal name="form" property="novaLigacao" value="true">
    
    top.frameApp.frameURA.ligacaoCTIPendente=true;
    
    /**
     *   carrega ligacao pendente
    */
    function carregaLigacaoPendente() {
        <bean:define id="formNovaLigacao" name="form" property="novaLigacaoForm"></bean:define>
        var strAction = "carregaParametrosCTI.do?";
        strAction += "idUra=<bean:write name="formNovaLigacao" property="idUra"/>";
        strAction += "&";
        strAction += "idCallCenter=<bean:write name="formNovaLigacao" property="idCallCenter"/>";
        strAction += "&";
        strAction += "inSenhaValidada=<bean:write name="formNovaLigacao" property="senhaValidada"/>";
        strAction += "&";
        strAction += "nrLinha=<bean:write name="formNovaLigacao" property="numeroConsultado"/>";
        strAction += "&";
        strAction += "nrTelefone=<bean:write name="formNovaLigacao" property="numeroOrigem"/>";   
        strAction += "&";
        strAction += "tipProc=<bean:write name="formNovaLigacao" property="tipoProc"/>";            
        strAction += "&";
        strAction += "numProc=<bean:write name="formNovaLigacao" property="numeroProc"/>";   
        strAction += "&";
        strAction += "observacao=<bean:write name="formNovaLigacao" property="observacao"/>";
        strAction += "&";
        strAction += "timeoutURA=<bean:write name="formNovaLigacao" property="indRedirTimeoutURA"/>";     
        strAction += "&";
        strAction += "navegacaoURA=<bean:write name="formNovaLigacao" property="navegacaoURA"/>";            
        strAction += "&";
        strAction += "idTipoRelacionamento=<bean:write name="formNovaLigacao" property="tipoRelacionamento"/>";
        
        top.frameApp.document.forms[0].method = "POST";
        top.frameApp.document.forms[0].target = "frameEscondido";
        top.frameApp.document.forms[0].action = strAction;  
        top.frameApp.document.forms[0].submit();        
    }     
        
    top.frameApp.frameURA.divNovaLigacao.innerHTML = divNovaLigacao.innerHTML;
    
    top.frameApp.frameURA.dvTerminar.style.display='none';
    top.frameApp.frameURA.dvLigacaoPendente.style.display='';
      
    top.frameApp.frameURA.carregaLigacaoPendente = carregaLigacaoPendente; 
    
    </logic:equal> 
    
    //verifica se tem ligacao caso tenha disabilita combos
    if(obtemEstadoLigacaoCTI()!=2)
    {
        top.frameApp.frameURA.document.getElementById('numCampanhaSel').disabled = true;   
        top.frameApp.document.getElementById('grupoSel').disabled = true;   
        <logic:equal name="form" property="novaLigacao" value="false">     
            top.frameApp.frameURA.document.getElementById('imgTransferir').style.display = '';     
        </logic:equal>
        
        top.frameApp.frameURA.document.getElementById('imgUra').style.display = '';
        top.frameApp.frameURA.document.getElementById('tdNrOrigem').innerText = 'Nº de Origem:';        
    }
    else
    {
        top.frameApp.frameURA.document.getElementById('imgTransferir').style.display = 'none';
        
        top.frameApp.frameURA.document.getElementById('imgUra').style.display = 'none';
        top.frameApp.frameURA.document.getElementById('tdNrOrigem').innerText  = '';        
    } 
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
</script>