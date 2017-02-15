<!--
Módulo.....: Gestão de Senhas
Caso de Uso: Tratamento encerramento em massa
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/25 18:52:33 $
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<!--acesso:controlInitEnv/-->

<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="validarClienteForm" />
<bean:define id="ramais" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="validarClienteForm.ramalVO" />
<bean:define id="ramaisUra" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="validarClienteForm.ramalUraVO" />

<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
    
    <form action="begin.do" name="formTransferecia" id="formTransferecia" method="post">
        
        <table width="100%" class="tbl_bggray">
            <tr>
                <td width="100%" align="left">
                    <input  class="radio "type="radio" id="destinoTransferencia" name="destinoTransferencia" value="1" onclick="trocarDestino(this.value)"/>Call Center
                    <input  class="radio" type="radio" id="destinoTransferencia" name="destinoTransferencia" value="2" onclick="trocarDestino(this.value)"/>URA
                </td>
            </tr>
            <tr>
                <td align="left">
                    <div id="idDivRamal" style="display:none;">
                        Ramais/Grupo<br>
                        <html:select name="form" property="numRamalSel" title="numRamalSel" style="width=200px">
                            <html:options collection="ramais" property="nrRamal" labelProperty="dsRamal" /> 
                        </html:select>
                    </div>
                    <div id="idDivRamalUra" style="display:none;">
                        Ramais/Grupo<br>
                        <html:select name="form" property="numRamalUraSel" title="numRamalUraSel" style="width=200px">
                            <html:options collection="ramaisUra" property="nrRamal" labelProperty="dsSenhaUra"/> 
                        </html:select>
                    </div>
                </td>
            </tr>
        </table>
        <div id="idDivIniciar" style="display=none">
            <table class="tbl_bggray" width="100">
                <tr>
                    <td>Observações</td>
                </tr>
                <tr>
                    <td>
                        <html:textarea name="form" property="observacao" rows="3" style="width=280px"></html:textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div id="idDivTransferirIniciar">
                        <!--acesso:controlHiddenItem nomeIdentificador="wor_itrans_iniciar"-->
                            <img id="imgIniciar" src="<%=request.getContextPath()%>/resources/images/bt_iniciar_transf_nrml.gif" onmouseover="this.src='<%=request.getContextPath()%>/resources/images/bt_iniciar_transf_over.gif'" onmouseout="this.src='<%=request.getContextPath()%>/resources/images/bt_iniciar_transf_nrml.gif'" onClick="iniciarTransferencia();" style="cursor:hand; border:none;"/>
                        <!--/acesso:controlHiddenItem-->
                        </div>
                        <div id="idDivTransferirOpcoes" style="display:none;">
                            <img id="imgCancelar" src="<%=request.getContextPath()%>/resources/images/bt_cancelar_transf_nrml.gif" onmouseover="this.src='<%=request.getContextPath()%>/resources/images/bt_cancelar_transf_over.gif'" onmouseout="this.src='<%=request.getContextPath()%>/resources/images/bt_cancelar_transf_nrml.gif'" onClick="cancelarTransferencia();" style="cursor:hand; border:none;"/>
                        <!--acesso:controlHiddenItem nomeIdentificador="wor_itrans_completar"-->
                            <img id="imgCancelar" src="<%=request.getContextPath()%>/resources/images/bt_completar_transf_nrml.gif" onmouseover="this.src='<%=request.getContextPath()%>/resources/images/bt_completar_transf_over.gif'" onmouseout="this.src='<%=request.getContextPath()%>/resources/images/bt_completar_transf_nrml.gif'" onClick="completarTransferencia();" style="cursor:hand; border:none;"/>
                        <!--/acesso:controlHiddenItem-->
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        
    </form>

    <script language="javascript">
        
        function submitUra() {
            try {
                top.frameApp.areaDetalhe.style.visibility = 'hidden';
                top.frameApp.dvFrameDados.style.visibility = 'hidden';
            }
            catch(ex) {
            }
    
            document[getNetuiTagName("formValidarCliente")].method = "POST";
            document[getNetuiTagName("formValidarCliente")].action = "transferirUraAction.do";
            document[getNetuiTagName("formValidarCliente")].target = "";
            document[getNetuiTagName("formValidarCliente")].submit();
        }
        
        function trocarDestino(item) {
            if(item == 1) {
                document.getElementById('idDivRamal').style.display = '';
                document.getElementById('idDivRamalUra').style.display = 'none';
            }
            else {
                document.getElementById('idDivRamal').style.display = 'none';
                document.getElementById('idDivRamalUra').style.display = '';
            }
            
            document.getElementById('idDivIniciar').style.display = '';
        }
        
        function iniciarTransferencia() {
            
            //na transferência grupOrigCh passa a ser grupDest
            var GrupOrigCh = '<bean:write name="form" property="idCallCenter"/>';
            
            //numero origem da chamada
            var NumOrigCh = '<bean:write name="form" property="numeroOrigem"/>';
            
            //numero consultado 
            var NumLinCliUra = '<bean:write name="form" property="numeroConsultado"/>'; // 
            
            //titularidade
            var IndTitular = '';
            <logic:equal name="form" property="tipoRelacionamento" value="2">
                //cliente
                IndTitular = 'C';
            </logic:equal>
            <logic:equal name="form" property="tipoRelacionamento" value="1">
                //usuario
                IndTitular = 'U';
            </logic:equal>
            <logic:equal name="form" property="tipoRelacionamento" value="3">
                //outros
                IndTitular = 'N';
            </logic:equal>
            
            //verificação de autenticaçao de cliente
            var CliAut ='N';
            <logic:equal name="form" property="senhaValidada" value="true">
                //atribui sim caso senha validada
                CliAut = 'S'; 
                <logic:equal name="form" property="cliente" value="true">
                    //usuario
                    IndTitular = 'C';
                </logic:equal>
                <logic:equal name="form" property="usuario" value="true">
                    //usuario
                    IndTitular = 'U';
                </logic:equal>
            </logic:equal>
            
            //verificaçao de identificaçao positiva
            var CliIdent = 'N';
            <logic:equal name="form" property="idPos" value="2">
                //atribui sim caso identificado
                CliIdent = 'S';
            </logic:equal>

            
            //navegaçao URA
            var NavCliURA = top.frameCTI.navCliURA; 

            //grupo destino chamada
            var GrupDest = '';            
            if(document.getElementById('destinoTransferencia').checked) {
                GrupDest = document.getElementById('numRamalSel').value; // 
            }
            else {
                GrupDest = document.getElementById('numRamalUraSel').value; // 
            }
            
            //tipo proc            
            var TipProc = top.frameCTI.tipProc; 
            
            //numero proc
            var NumProc = top.frameCTI.numProc; 
            
            //observação atendimento
            var ObsAtend = document.getElementById('observacao').value;
            
            //timeoutURA
            var IndRedirTimeoutURA = '<bean:write name="form" property="indRedirTimeoutURA"/>'; // 
               
            var ret = top.frameCTI.transferirLigacao(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA);
            
            if(ret) {
                document.getElementById('idDivTransferirIniciar').style.display = 'none';
                document.getElementById('idDivTransferirOpcoes').style.display = '';
            }
        }
        
        function cancelarTransferencia() {
            if(top.frameCTI.recuperarLigacao()) {
                document.getElementById('idDivTransferirIniciar').style.display = '';
                document.getElementById('idDivTransferirOpcoes').style.display = 'none';
            }
        }
        
        function completarTransferencia() {
            if(top.frameCTI.completarTransferencia()) {
                document.getElementById('idDivTransferirIniciar').style.display = '';
                document.getElementById('idDivTransferirOpcoes').style.display = 'none';
                
                //encerra atendimento
                top.frameApp.frameURA.completarTerminar();
            }
        }
        
        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
    </script>
<!--/acesso:controlHiddenItem-->
</body>

