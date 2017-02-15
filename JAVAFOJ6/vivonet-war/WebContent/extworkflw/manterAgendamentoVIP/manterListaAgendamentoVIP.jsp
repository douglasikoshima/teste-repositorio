<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterAgendamentoVIP" type="extworkflw.manterAgendamentoVIP.ManterAgendamentoVIPController.FormManterAgendamentoVIP"/>
<bean:define id="listaAgenda" name="Form" property="listaAgendamentos" type="br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output.AgendamentoType[]"/>

<%
String agendado = "false";    
int countStatus = 0; 
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" language="javascript">
    
        function validaPesquisa()
        {
            document.forms[0].target="";
            if (document.forms[0].consHexa.value.length == 0) {
                alert('Preencha o campo de Consulta Hexa');
            } else if (document.forms[0].consHexa.value.length != 8) {                
                alert('Campo de Hexa deve ser preenchido com 8 caracteres!');
            } else {                                        
                top.frameApp.mostrar_div();
                document.forms[0].action="/FrontOfficeWeb/extworkflw/ConsultaHexa/consultaHexa.do";
                document.forms[0].submit();
            }
        }

        
        function voltar() {
            document.forms[0].action="voltar.do";
            document.forms[0].target="ifrmFalse";
            document.forms[0].submit();
        }
        
        function ocultaCampoEmail(paramIdTipoEmail){
            
            if (paramIdTipoEmail != 1){

                document.getElementById('trEmail').style.display = "block";
                document.getElementById('trEmail').style.visibility.display = "hidden";
                
            }else{
            
                document.getElementById('trEmail').style.display = "none";
                document.getElementById('trEmail').style.visibility.display = "visible";
            }
        }
        
        function buscarDetalhe(paramIdAgendamento){
        
            document.formManterAgendamentoVIP.idAgendamento.value = paramIdAgendamento;
            document.formManterAgendamentoVIP.action  = "buscarDetalheAgendamento.do"
            document.formManterAgendamentoVIP.submit();
        }
        
        function alterarAgendamento(idAgendamentoParam){
        
            document.formManterAgendamentoVIP.idAgendamento.value = idAgendamentoParam;
            
            top.frameApp.showPopupTI('Agendamento VIP > Incluir/Alterar Agendamento',700,550,30,"/FrontOfficeWeb/extworkflw/manterAgendamentoVIP/buscaAlteracaoAgendamento.do?tipoOperacao=alterar&idAgendamento=" + idAgendamentoParam);
        
        }
        
        function excluirAgendamento(paramIdAgendamento, idLojaParam, dtAgendamentoParam, idHorarioParam, dsNomeLojaParam){

            if (confirm('Deseja realmente cancelar este Agendamento?')){

                document.formManterAgendamentoVIP.dsNomeLoja.value = dsNomeLojaParam;
                document.formManterAgendamentoVIP.idAgendamento.value = paramIdAgendamento;
                document.formManterAgendamentoVIP.idLoja.value = idLojaParam;
                document.formManterAgendamentoVIP.dtAgendamento.value = dtAgendamentoParam;
                document.formManterAgendamentoVIP.idHorario.value = idHorarioParam;
                
                document.formManterAgendamentoVIP.action  = "excluirAgendamento.do"
                document.formManterAgendamentoVIP.submit();
        
            }
        }
        
        function visualizarDetalhes(idAgendamentoParam)
        {
            top.frameApp.showPopupTI('Agendamento VIP > Detalhe Agendamento',630,320,100,"/FrontOfficeWeb/extworkflw/manterAgendamentoVIP/visualizarDetalhes.do?idAgendamento=" + idAgendamentoParam);
        }
        
        function incluirAlterarAlerta(idAgendamentoParam){
        
            top.frameApp.showPopupTI('Agendamento VIP > Lembrete de Agendamento',630,220,150,"/FrontOfficeWeb/extworkflw/manterAgendamentoVIP/buscaAlertaAgendamento.do?idAgendamento=" + idAgendamentoParam);
            
        }
        
        function agendar(){
        
            top.frameApp.showPopupTI('Agendamento VIP > Incluir/Alterar Agendamento',700,550,30,"/FrontOfficeWeb/extworkflw/manterAgendamentoVIP/buscaAlteracaoAgendamento.do?tipoOperacao=incluir");
        
        }
        
        function habilitaAgendar(obj){
            
            if(obj.checked){
                   document.getElementById('botaoAgendar').style.display = 'block';         
            }
        
        }        
        
        function mensagem(){
        
            alert("Já existe um agendamento com status 'Agendado' cadastrado.");
            document.getElementById('checkExcecao').checked = false;
            return false;
        
        }
        
        updateNrProtocolo = function() {
        <logic:notEmpty name="Form" property="nrProtocolo">
            top.frameApp.nrProtocolo = <bean:write name="Form" property="nrProtocolo"/>
            top.frameApp.$('nrProtocolo').update('<bean:write name="Form" property="nrProtocolo"/>');
            alert("Protocolo <bean:write name="Form" property="nrProtocolo"/> gerado.");
        </logic:notEmpty>
        }
      
        
    </script>    
</head>

<body>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    
        <vivo:sessao id="listaAgendamendo"  width="700" height="260" label="Agendamento VIP"  scroll="false" operacoes="Lista Agendamentos">            
           
            <form name="formManterAgendamentoVIP" onsubmit="return false;">

                <html:hidden name="Form" property="idAgendamento"/>
                <html:hidden name="Form" property="idLoja"/>
                <html:hidden name="Form" property="dtAgendamento"/>
                <html:hidden name="Form" property="idHorario"/>
                <html:hidden name="Form" property="dsNomeLoja"/>

                    <logic:notEqual name="Form" property="listaAgendamentoSemDados" value="true">

                        <vivo:tbTable selectable="false" layoutWidth="665" layoutHeight="180" tableWidth="665" onRowClick="false" resize="false" styleId="tableTitle" sortable="false">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="20%"  type="string">Data/Hora</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="25%"  type="string">Loja</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="20%" type="string">Lembrete</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="15%" type="string">Status</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate name="Form" property="listaAgendamentos" id="agendamento" indexId="id">
                                    <vivo:tbRow key="linha">
                                        <vivo:tbRowColumn>
                                                <bean:write name="agendamento" property="data" /> as <bean:write name="agendamento" property="hora" />
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <bean:write name="agendamento" property="nome" />
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <logic:equal name="Form" property="semAlerta" value="true">
                                                ---
                                            </logic:equal>
                                            
                                            <logic:equal  name="agendamento" property="status" value="Agendado">
                                            
                                                <logic:equal name="Form" property="semAlerta" value="false">
                                                    <logic:iterate name="listaAlerta" id="listaAlertas" scope="request">
                                                        <bean:write name="listaAlertas" property="sgTipoComunicacao" />-<bean:write name="listaAlertas" property="dtAlerta" />
                                                    </logic:iterate>
                                                </logic:equal>
                                                
                                            </logic:equal>
                                            
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <logic:equal  name="agendamento" property="status" value="Agendado">
                                                <%
                                                    agendado = "true";
                                                %>
                                            </logic:equal>
                                            <bean:write name="agendamento" property="status" />
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" onclick="visualizarDetalhes('<bean:write name="agendamento" property="idAgendamento" />');" style="cursor: hand;" border="0" alt="Detalhe de Agendamento" >
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                        
                                                <logic:equal  name="agendamento" property="status" value="Agendado">
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="alterarAgendamento('<bean:write name="agendamento" property="idAgendamento" />');" border="0" style="cursor: hand;" alt="Alterar Agendamento" >
                                                </logic:equal>
                                                
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                        
                                            <logic:equal  name="agendamento" property="status" value="Agendado">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="excluirAgendamento('<bean:write name="agendamento" property="idAgendamento" />','<bean:write name="agendamento" property="idLoja" />', '<bean:write name="agendamento" property="data" />','<bean:write name="agendamento" property="hora" />', '<bean:write name="agendamento" property="nome" />');" style="cursor: hand;" border="0" alt="Cancelar Agendamento" >
                                            </logic:equal>
                                           
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                        
                                            <logic:equal  name="agendamento" property="status" value="Agendado">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_endereco_pendente.gif" onclick="incluirAlterarAlerta('<bean:write name="agendamento" property="idAgendamento" />');" style="cursor: hand;" border="0" alt="Incluir/Alterar Lembrete" >
                                            </logic:equal>
                                           
                                           <logic:equal name="agendamento" property="status" value="Agendado">
                                                <%countStatus = countStatus + 1;%>
                                           </logic:equal>

                                            <logic:equal name="agendamento" property="status" value="Concluído">
                                                <%countStatus = countStatus + 1;%>
                                           </logic:equal>

                                            <logic:equal name="agendamento" property="status" value="Não Comparecimento">
                                                <%countStatus = countStatus + 1;%>
                                           </logic:equal>


                                        </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>

                            </vivo:tbRows>

                        </vivo:tbTable> 
                        
            </logic:notEqual>
            
            <logic:equal name="Form" property="listaAgendamentoSemDados" value="true">
                <table width="100%" border="0">
                    <tr>
                        <td align="center">
                            <b>Não existem agendamentos para a linha informada.</b>
                        </td>
                    <tr>
                </table>
            </logic:equal>
                        
                        <table border="0" width="100%">
                            <tr>
                            
                                <td align="left">
                                
                                <%
                                    if("true".equals(agendado)){
                                %>                                
                                    <input class="radio" type="checkbox" name="checkExcecao" onclick="mensagem();"/>Exceção de agendamento
                                    
                                <%}else{%>
                                
                                    <input class="radio" type="checkbox" name="checkExcecao" onclick="habilitaAgendar(this);"/>Exceção de agendamento
                                    
                                <%}%>
                                
                                </td>
                                
                                
                                <td align="right">
                                    <div id="botaoAgendar">

                                <%
                                    if(!"true".equals(agendado)){
                                %>                                

                                            <img id="btAgendar"
                                                               value="Agendar"
                                                               src="/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif"
                                                               rolloverImage="/FrontOfficeWeb/resources/images/bt_agendar_over.gif"
                                                               style="border:none;margin-left:4px;"
                                                               onMouseUp="agendar();" />
                                                               
                                <%}%>                                                               

                                    </div>
                                </td>
                            </tr>
                        </table> 
            </form>            
        </vivo:sessao>

        <iframe id="ifrmFalse" name="ifraSubmit" style="visibility:hidden;" width="0px" height="0px"></iframe>
        
        <vivo:alert atributo="msgStatus" scope="request" />
        
        
<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    top.frameApp.oculta_div();
    updateNrProtocolo();
    //alert(document.forms[0].resultadoBatimento.value);    
    //voltar();
    //alert('bauru was here');
            //parent.style.overflow = "hidden";
            //alert(window.top.frameApp.ti_frameAbas.ifrmAbas.document.body)
            //window.top.frameApp.ti_frameAbas.ifrmAbas.document.body.style.overflow = "hidden";
            //alert('1');
            
    <%
        int qtdAgendamento = 2;
        if(Form.getQuantidadeAgendamento() != null)
            qtdAgendamento = Integer.parseInt(Form.getQuantidadeAgendamento());
        if(listaAgenda != null){
            if(countStatus >= qtdAgendamento ){%>
                document.getElementById('botaoAgendar').style.display = 'none';
          <%}
        }%>
    <logic:equal name="fechaJanela" value="true">    
        top.frameApp.$('divPopupTI').style.display = 'none';
    </logic:equal>
    
        <logic:present name="msgPerguntaAlteracao" scope="request">
    
            <logic:notPresent name="listaAlerta" scope="request">
    
            if(confirm('<bean:write name="msgPerguntaAlteracao" scope="request"/>')){
                top.frameApp.showPopupTI('Agendamento VIP > Lembrete de Agendamento',630,220,170,"/FrontOfficeWeb/extworkflw/manterAgendamentoVIP/buscaAlertaAgendamento.do");
                
            // LEVA PARA A TELA DE DETALHES
            }else{
            
                top.frameApp.showPopupTI('Agendamento VIP > Detalhe Agendamento',630,320,170,"/FrontOfficeWeb/extworkflw/manterAgendamentoVIP/visualizarDetalhes.do");
            }
    
            </logic:notPresent>
            
            <logic:present name="listaAlerta" scope="request">
                alert("<bean:write name="msgPerguntaAlteracao" scope="request"/>");
                top.frameApp.showPopupTI('Agendamento VIP > Lembrete de Agendamento',630,220,170,"/FrontOfficeWeb/extworkflw/manterAgendamentoVIP/buscaAlertaAgendamento.do","Utilize o botão Gravar para confirmar o envio do lembrete.");
            </logic:present>
            
    </logic:present>
    

        <logic:present name="msgPergunta" scope="request">
    
            if(confirm('<bean:write name="msgPergunta" scope="request"/>')){
                top.frameApp.showPopupTI('Agendamento VIP > Lembrete de Agendamento',630,220,170,"/FrontOfficeWeb/extworkflw/manterAgendamentoVIP/buscaAlertaAgendamento.do");
                
            // LEVA PARA A TELA DE DETALHES
            }else{
            
                top.frameApp.showPopupTI('Agendamento VIP > Detalhe Agendamento',630,320,170,"/FrontOfficeWeb/extworkflw/manterAgendamentoVIP/visualizarDetalhes.do");
            }
            
        </logic:present>


    <logic:present name="msgSucessoAlerta" scope="request">
            
            top.frameApp.showPopupTI('Agendamento VIP > Detalhe Agendamento',630,320,170,"/FrontOfficeWeb/extworkflw/manterAgendamentoVIP/visualizarDetalhes.do?msgSucessoAlertaDetalhe=" + "<bean:write name="msgSucessoAlerta" scope="request"/>");
    
    </logic:present>
    
    
</SCRIPT>
</body>
</html>