<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%> 

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="quadroAvisoForm" type="VOLTAV.quadroAviso.QuadroAvisoController.QuadroAvisoForm"/>
<bean:define id="segmentos" name="Form" property="segmentos" />    
<bean:define id="ufs"       name="Form" property="ufs" />    

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="Quadro de Avisos VOL-E"/>
<netui-template:setAttribute name="modulo" value="VOL/TAV"/>
<netui-template:section name="headerSection">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/controleEventos.js"></script>    
    <script type="text/javascript" language="javascript">
        function limpar() {
            self.location.href = 'begin.do';
        }

        function confirmarMensagem() {
            var f = document.forms[0];
            if (validarFormulario()) {
                document.forms[0].action = 'confirmarMensagem.do';
                document.forms[0].submit();     
            }
        }

        function closeWindow() {
            document.forms[0].action = 'begin.do';
            document.forms[0].submit();     
        }        

        function validarFormulario() {
            var dia  = new Date();
            var mes  = dia.getMonth() + 1;
            var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

            if (trim($F('mensagem'))=="") {
                alert("Entre com a mensagem que deseja enviar.");
                $('mensagem').focus();
                return false;
            }        
            
            if (validaData($F('dtValidadeDe')) == false) {
                alert("Selecione um período de validade.");
                $('dtValidadeDe').focus();
                return false;              
            }
            
            if (validaData($F('dtValidadeAte')) == false) {
                alert("Selecione um período de validade.");
                $('dtValidadeAte').focus();
                return false;              
            }            
            
            if(!validaDataFinal(hoje, $F('dtValidadeDe'))) {
                alert("Selecione uma data de validade de início igual ou maior à data atual.");
                $('dtValidadeDe').focus();
                return false;   
            }

            if (validaDataFinal($F('dtValidadeDe'),$F('dtValidadeAte')) == false) {            
                alert("Selecione uma data de validade final maior que a data de início.");
                $('dtValidadeDe').focus();
                return false;             
            }        
            return true;
        }

        function pesquisaConta() {
            if (trim($F('conta'))=="") {
                alert("Selecione uma conta válida.");
                $('conta').focus();
                return false;
            }  
            document.forms[0].action = 'pesquisaConta.do';
            document.forms[0].submit();              
        }        
    </script>
    </netui-template:section>

<netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="qdMain" height="468" width="790" label="Quadro de Aviso VOL-E" operacoes="Enviar mensagem" scroll="no">
    <form action="confirmarMensagem.do" name="quadroAvisoForm" enctype="multipart/form-data" method="post" onSubmit="return false">
        <table cellpadding="2" cellspacing="1" style="margin-left:35px;">
            <tr>
                <td align="right" nowrap>Conta:</td>
                <td>
                     <html:text name="Form" property="conta" styleId="conta" maxlength="50" style="width:100px;margin-left:3px;" />
                     <img id="btPesquisarConta" value="Pesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;margin-right:4px;" onMouseUp="pesquisaConta()"/>             
                </td>
                <td align="right" nowrap>Mensagem</td>
                <td><html:textarea name="Form" property="mensagem" cols="40" rows="3"/></td>        
            </tr>
            <tr>
                <td align="right" nowrap>Regional</td>
                <td>
                    <html:select name="Form" property="regional" styleId="regional" style="margin-left:3px;width:235px">
                        <html:option value="">-- Selecione --</html:option>
                        <html:options collection="ufs" property="iduf" labelProperty="nmuf"/>                                
                     </html:select>
                </td>
                <td align="right">Validade: De</td>
                <td>
                    <html:text name="Form"
                               property="validadeInicial"
                               styleId="dtValidadeDe"
                               onkeyup="checaData(this)"
                               onblur="checaData(this)"
                               maxlength="10"
                               style="width:70px" />
                    <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;margin-right:20px;" title="Calendário" onclick="return showCalendar('dtValidadeDe', '%d/%m/%Y');">
                    At&eacute;: &nbsp;&nbsp;
                    <html:text name="Form"
                               property="validadeFinal"
                               styleId="dtValidadeAte"
                               onkeyup="checaData(this)"
                               onblur="checaData(this)"
                               maxlength="10"
                               style="width:70px" />
                    <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtValidadeAte', '%d/%m/%Y');">
                </td>        
            </tr>
            <tr>
                <td align="right" nowrap>Segmentação</td>
                <td colspan="4">
                     <html:select name="Form" property="segmentacao" styleId="segmentacao" style="margin-left:3px;width:235px">
                        <html:option value="">-- Selecione --</html:option>
                        <html:options collection="segmentos" property="idsegmentacao" labelProperty="dssegmentacao"/>                
                     </html:select>
                </td>
            </tr>            
        </table>
        <table cellpadding="2" cellspacing="1" align="right" style="margin-right:15px;">    
            <tr>
                <td colspan="5" valign="top" align="right">
                    <img id="btCancelar" value="Cancelar" src="/FrontOfficeWeb/resources/images/bt_enviar_nrml.gif" style="border:none;margin-right:4px;" onMouseUp="confirmarMensagem()" />
                    <img id="btLimpar" value="Gravar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" style="border:none;margin-right:4px;" onMouseUp="limpar()" />
                </td>
            </tr>
        </table>  
    </form>        
    <vivo:quadroFlutuante id="divInclusaoAlteracao"
                          idIframe="iframeInclusaoAlteracao"
                          width="763"                
                          height="520"
                          spacesTop="65"
                          spacesLeft="25"
                          display="none"
                          url="<%=request.getContextPath()%>"
                          label="Manter Questionário"
                          onclick="return false;"/>
</vivo:sessao>
<vivo:alert atributo="msgStatus" scope="request" afterFunction="closeWindow()" />
<vivo:alert atributo="msgErro" scope="request" />
</netui-template:section>
</netui-template:template>