<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="admsistemas.admArvoreSatisfacao.admArvoreSatisfacaoController.FormArvoreSatisfacao"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormArvoreSatisfacao"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreSatisfacao"/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Gerenciamento de Árvore de Perguntas"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>
    <netui-template:section name="headerSection">
    <head>
    <script language="Javascript">
   
        function capturaQuestionario(id, ds){
            document.forms[0].idQuestionarioFuncao.value = id;
            document.forms[0].dsQuestionarioFuncao.value = ds;
            botoesQuestionario();
            escondeDivSequencia();
            escondeFrame();
            
        }
		function setIdTipoPerguntaFuncao(idTipoPerguntaFuncao)
        {
            document.forms[0].idTipoPerguntaFuncao.value = idTipoPerguntaFuncao;
        }
        function capturaParametrosPerguntaSatisfacao(idTipoPergunta, dsPergunta, dsScriptPergunta, idTipoApresentacaoPergunta, idTipoPergunta, inDisponibilidade, inEncerramento, inObrigatoria, sqApresentacao, idQuestionario, dsQuestionario, dsTipoApresentacaoPerguntaFuncao){
            document.forms[0].idTipoPerguntaFuncao.value = idTipoPergunta;
            document.forms[0].dsPerguntaFuncao.value = dsPergunta;
            document.forms[0].dsScriptPerguntaFuncao.value = dsScriptPergunta;
            document.forms[0].idTipoApresentacaoPerguntaFuncao.value = idTipoApresentacaoPergunta;
            document.forms[0].idTipoPerguntaFuncao.value = idTipoPergunta;
            document.forms[0].inDisponibilidadePerguntaFuncao.value = inDisponibilidade;
            document.forms[0].inEncerramentoPerguntaFuncao.value = inEncerramento;
            document.forms[0].inObrigatoriaFuncao.value = inObrigatoria;
            document.forms[0].sqApresentacaoPerguntaFuncao.value = sqApresentacao;
            document.forms[0].idQuestionarioFuncao.value = idQuestionario;
            document.forms[0].dsQuestionarioFuncao.value = dsQuestionario;
            document.forms[0].dsTipoApresentacaoPerguntaFuncao.value = dsTipoApresentacaoPerguntaFuncao;
            document.forms[0].indPerguntaResposta.value = 'pergunta';
            document.forms[0].dsRespostaFuncao.value = '';
            botoesPergunta(); 
            exibeDivSequencia();
            escondeFrame();
        }
        
        function capturaParametrosRespostaSatisfacao(idResposta, dsResposta, dsScriptResposta, idResposta, inDisponibilidade, inEncerramento, sqApresentacao, idQuestionario, dsQuestionario, salto, idPerguntaSalto){
            document.forms[0].idRespostaFuncao.value = idResposta;
            document.forms[0].dsRespostaFuncao.value = dsResposta;
            document.forms[0].dsScriptRespostaFuncao.value = dsScriptResposta;
            document.forms[0].inDisponibilidadeRespostaFuncao.value = inDisponibilidade;
            document.forms[0].inEncerramentoRespostaFuncao.value = inEncerramento;
            document.forms[0].sqApresentacaoRespostaFuncao.value = sqApresentacao;
            document.forms[0].idQuestionarioFuncao.value = idQuestionario;
            document.forms[0].dsQuestionarioFuncao.value = dsQuestionario;
            document.forms[0].indPerguntaResposta.value = 'resposta';
            document.forms[0].dsPerguntaFuncao.value = '';
            document.forms[0].idAtivoPerguntaSaltoFuncao.value = salto;
            document.forms[0].idPerguntaSaltoFuncao.value = idPerguntaSalto;
            botoesResposta();  
            exibeDivSequencia();
            escondeFrame();
        }
        
        function capturaParametrosRespostaSatisfacaoSalto(idResposta, dsResposta, dsScriptResposta, idResposta, inDisponibilidade, inEncerramento, sqApresentacao, ativo, idPergunta, idQuestionario, dsQuestionario){
            document.forms[0].idRespostaFuncao.value = idResposta;
            document.forms[0].dsRespostaFuncao.value = dsResposta;
            document.forms[0].dsScriptRespostaFuncao.value = dsScriptResposta;
            document.forms[0].inDisponibilidadeRespostaFuncao.value = inDisponibilidade;
            document.forms[0].inEncerramentoRespostaFuncao.value = inEncerramento;
            document.forms[0].sqApresentacaoRespostaFuncao.value = sqApresentacao;
            document.forms[0].idQuestionarioFuncao.value = idQuestionario;
            document.forms[0].dsQuestionarioFuncao.value = dsQuestionario;
            document.forms[0].idPerguntaSaltoFuncao.value = idPergunta;
            document.forms[0].idAtivoPerguntaSaltoFuncao.value = ativo;
            document.forms[0].indPerguntaResposta.value = 'resposta';
            document.forms[0].dsPerguntaFuncao.value = '';
            botoesResposta();
            exibeDivSequencia();
            escondeFrame();
        }
        
        function Questionario() {
            mostraFrame();
            document.forms[0].target = 'iframeQuestionario';
            document.forms[0].action = 'incluirAlterarPergunta.do?acao=incluir&idQuestionario=' + document.forms[0].idQuestionarioFuncao.value + '&dsQuestionario=' + document.forms[0].dsQuestionarioFuncao.value;
            mostrar_div();
            document.forms[0].submit();
        }
        
        function validaIncluirResposta()
        {
            if(document.forms[0].dsTipoApresentacaoPerguntaFuncao.value == "MEM"
                || document.forms[0].dsTipoApresentacaoPerguntaFuncao.value == "TXT")
            {
                alert("Pergunta do tipo Texto ou Memo não possui  resposta.");
                return false;
            }
            
            return true;
                    
        }
        function IncluirResposta()
        {
            if(validaIncluirResposta())
            {
                mostraFrame();
                document.forms[0].target = 'iframeQuestionario';
                document.forms[0].action = 'incluirAlterarResposta.do?acao=incluir';
                                           // + '&idTipoPerguntaFuncao=' + document.forms[0].idTipoPerguntaFuncao.value
                                           // + '&dsPerguntaFuncao=' + document.forms[0].dsPerguntaFuncao.value
                                           // + '&dsScriptPerguntaFuncao=' + document.forms[0].dsScriptPerguntaFuncao.value
                                           // + '&idTipoApresentacaoPerguntaFuncao=' + document.forms[0].idTipoApresentacaoPerguntaFuncao.value
                                           // + '&inDisponibilidadePerguntaFuncao=' +  document.forms[0].inDisponibilidadePerguntaFuncao.value
                                           // + '&inEncerramentoPerguntaFuncao=' + document.forms[0].inEncerramentoPerguntaFuncao.value
                                           // + '&inObrigatoriaFuncao=' + document.forms[0].inObrigatoriaFuncao.value
                                           // + '&sqApresentacaoPerguntaFuncao' + document.forms[0].sqApresentacaoPerguntaFuncao.value
                                           // + '&idQuestionarioFuncao=' + document.forms[0].idQuestionarioFuncao.value
                                           // + '&dsQuestionarioFuncao=' + document.forms[0].dsQuestionarioFuncao.value
                                           // + '&idRespostaFuncao=' + document.forms[0].idRespostaFuncao.value;
                mostrar_div();
                document.forms[0].submit();
            }
        }
        
        function AlterarPergunta(){
            mostraFrame();
            document.forms[0].target = 'iframeQuestionario';
            document.forms[0].action = 'incluirAlterarPergunta.do?acao=alterar';
                               // + '&idTipoPerguntaFuncao=' + document.forms[0].idTipoPerguntaFuncao.value
                               // + '&dsPerguntaFuncao=' + document.forms[0].dsPerguntaFuncao.value
                               // + '&dsScriptPerguntaFuncao=' + document.forms[0].dsScriptPerguntaFuncao.value
                               // + '&idTipoApresentacaoPerguntaFuncao=' + document.forms[0].idTipoApresentacaoPerguntaFuncao.value
                               // + '&inDisponibilidadePerguntaFuncao=' +  document.forms[0].inDisponibilidadePerguntaFuncao.value
                               // + '&inEncerramentoPerguntaFuncao=' + document.forms[0].inEncerramentoPerguntaFuncao.value
                               // + '&inObrigatoriaFuncao=' + document.forms[0].inObrigatoriaFuncao.value
                               // + '&sqApresentacaoPerguntaFuncao' + document.forms[0].sqApresentacaoPerguntaFuncao.value
                               // + '&idQuestionarioFuncao=' + document.forms[0].idQuestionarioFuncao.value
                               // + '&dsQuestionarioFuncao=' + document.forms[0].dsQuestionarioFuncao.value
                                //+ '&dsTipoApresentacaoPerguntaFuncao=' + document.forms[0].dsTipoApresentacaoPerguntaFuncao.value;
            mostrar_div();
            document.forms[0].submit();
        }
        
        function AlterarResposta(){
            mostraFrame();
            document.forms[0].target = 'iframeQuestionario';
            document.forms[0].action = 'incluirAlterarResposta.do?acao=alterar';
                                       // + '&idTipoPerguntaFuncao=' + document.forms[0].idTipoPerguntaFuncao.value
                                       // + '&dsPerguntaFuncao=' + document.forms[0].dsPerguntaFuncao.value
                                       // + '&dsScriptPerguntaFuncao=' + document.forms[0].dsScriptPerguntaFuncao.value
                                       // + '&idTipoApresentacaoPerguntaFuncao=' + document.forms[0].idTipoApresentacaoPerguntaFuncao.value
                                       // + '&inDisponibilidadePerguntaFuncao=' +  document.forms[0].inDisponibilidadePerguntaFuncao.value
                                       // + '&inEncerramentoPerguntaFuncao=' + document.forms[0].inEncerramentoPerguntaFuncao.value
                                       // + '&inObrigatoriaFuncao=' + document.forms[0].inObrigatoriaFuncao.value
                                       // + '&sqApresentacaoRespostaFuncao' + document.forms[0].sqApresentacaoRespostaFuncao.value
                                       // + '&idQuestionarioFuncao=' + document.forms[0].idQuestionarioFuncao.value
                                        //+ '&dsQuestionarioFuncao=' + document.forms[0].dsQuestionarioFuncao.value
                                       // + '&idRespostaFuncao=' + document.forms[0].idRespostaFuncao.value;
            mostrar_div();
            document.forms[0].submit();
        }
        
        function ExcluirPergunta(){
            if (window.confirm("Confirma remoção da pergunta?")) {
                document.forms[0].target = '_parent';
                document.forms[0].action = 'excluirPergunta.do';
                mostrar_div();
                document.forms[0].submit();
            }
        }
        
        function ExcluirResposta(){
            if (window.confirm("Confirma remoção da resposta?")) {
                document.forms[0].target = '_parent';
                document.forms[0].action = 'excluirResposta.do';
                mostrar_div();
                document.forms[0].submit();
            }
        }
        
        function escondeFrame(){
            
            var x = document.getElementById("iframeQuestionario");
            x.style.visibility = 'hidden';
        }
        
        function mostraFrame(){
            var x = document.getElementById("iframeQuestionario");
            x.style.visibility = 'visible';
        }
        
        function validaGerenciaSalto()
        {
            if(document.forms[0].inEncerramentoRespostaFuncao.value == '1')
            {
                alert('Esta resposta Encerra o Questionário, não pode conter Salto.');
                return false;
            }
            
            
            return true;
        }
        
        function GerenciaSalto()
        {
        
            if(validaGerenciaSalto())
            {
                mostraFrame();
                document.forms[0].target = 'iframeQuestionario';
                
                if(document.forms[0].idRespostaFuncao.value!='')
                {
                    document.forms[0].action = 'incluirAlterarSalto.do';
                                              //  + 'idQuestionarioFuncao=' + document.forms[0].idQuestionarioFuncao.value
                                              //  + '&dsQuestionarioFuncao=' + document.forms[0].dsQuestionarioFuncao.value
                                              //  + '&idResposta=' + document.forms[0].idRespostaFuncao.value
                                              //  + '&idTipoPerguntaFuncao=' + document.forms[0].idTipoPerguntaFuncao.value
                                              //  + '&dsPerguntaFuncao=' + document.forms[0].dsPerguntaFuncao.value
                                              //  + '&dsScriptPerguntaFuncao=' + document.forms[0].dsScriptPerguntaFuncao.value
                                               // + '&idTipoApresentacaoPerguntaFuncao=' + document.forms[0].idTipoApresentacaoPerguntaFuncao.value
                                               // + '&inDisponibilidadePerguntaFuncao=' +  document.forms[0].inDisponibilidadePerguntaFuncao.value
                                               // + '&inEncerramentoPerguntaFuncao=' + document.forms[0].inEncerramentoPerguntaFuncao.value
                                               // + '&inObrigatoriaFuncao=' + document.forms[0].inObrigatoriaFuncao.value
                                               // + '&sqApresentacaoPerguntaFuncao' + document.forms[0].sqApresentacaoPerguntaFuncao.value
                                               // + '&idRespostaFuncao=' + document.forms[0].idRespostaFuncao.value;
                    mostrar_div();
                    document.forms[0].submit();                                            
    
                }

            }
        }
                

        function mostraJanelaDetalhe(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'visible';
            var b = document.getElementById("bg");
            b.style.visibility = 'visible';
        }
        
        
        function abreJanela(){
            mostraJanelaDetalhe()
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'criarEditarItemArvore.jsp';
            mostrar_div();
          }

        function fechaJanela(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'hidden';
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'nada.html';
            var b = document.getElementById("bg");
            b.style.visibility = 'hidden';
        }

        function excluirResposta() {
            confirm('Confirma remoção da resposta?');
        }

        function botoesQuestionario() {
            btQuestionario.style.display = '';
            btPergunta.style.display = 'none';
            btResposta.style.display = 'none';
        }
        
        function botoesPergunta() {
            btPergunta.style.display = '';
            btQuestionario.style.display = 'none';
            btResposta.style.display = 'none';
        }
        
        function botoesResposta() {
            btResposta.style.display = '';
            btQuestionario.style.display = 'none';
            btPergunta.style.display = 'none';
        }
        
        function escondeDivSequencia() {
            divSeq = document.getElementById('divSequencia');
            divSeq.style.visibility = 'hidden';
            var divUpDown = document.getElementById('divUpDown');
            divUpDown.style.visibility = 'hidden';

        }
        
        function exibeDivSequencia() {
            divSeq = document.getElementById('divSequencia');
            divSeq.style.visibility = 'visible';
            var divUpDown = document.getElementById('divUpDown');
            divUpDown.style.visibility = 'visible';

            
        }   
        
        function enviaSequenciaUp(){
            document.forms[0].target = '';
            document.forms[0].indUpDown.value = '1';
            document.forms[0].action = 'perguntaRespostaUpDown.do';
            document.forms[0].submit();
        }     
        
        function enviaSequenciaDown(){
            document.forms[0].target = '';
            document.forms[0].indUpDown.value = '0';
            document.forms[0].action = 'perguntaRespostaUpDown.do';
            document.forms[0].submit();
        }           
        
        function voltar()
        {
            document.forms[0].target = '';
            document.forms[0].action = 'begin.do';
            document.forms[0].submit();
        
        
        }
   </script>

        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

    </head>    
    </netui-template:section>
    <netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <acesso:controlHiddenItem nomeIdentificador="adm_asat_verpagina">
    <vivo:sessao id="qdMain" height="475" width="790" label="Questionário de Satisfação" operacoes="Gerenciamento de Perguntas e Respostas" scroll="no">
    <html:form action="/admsistemas/admArvoreSatisfacao/begin.do" method="post">
        <html:hidden name="FormArvoreSatisfacao" property="idPerguntaSalto"/>
        <html:hidden name="FormArvoreSatisfacao" property="ativoSaltoFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="dsPerguntaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="dsQuestionarioFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="dsRespostaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="dsScriptPerguntaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="dsScriptRespostaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="idPerguntaSaltoFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="idQuestionario"/>
        <html:hidden name="FormArvoreSatisfacao" property="idQuestionarioFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="idRespostaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="idTipoApresentacaoPerguntaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="idTipoPerguntaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="inDisponibilidadePerguntaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="inDisponibilidadeRespostaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="inEncerramentoPerguntaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="inEncerramentoRespostaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="inObrigatoriaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="sqApresentacaoPerguntaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="sqApresentacaoRespostaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="dsTipoApresentacaoPerguntaFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="idAtivoPerguntaSaltoFuncao"/>
        <html:hidden name="FormArvoreSatisfacao" property="indPerguntaResposta"/>
        <html:hidden name="FormArvoreSatisfacao" property="indUpDown"/>
            <table  width="780">
                <tr>
                    <td width="100%" height="351" bgcolor="#E3ECF4">
                        <vivo:quadro id="arvOperacoes" width="520" height="392" label="Questionário" scroll="yes">
                        <!-- div style="scroll:yes;padding-left:10px;padding-bottom:10px;padding-right:10px;padding-top:10px;width:100%;height:380px;overflow:auto;background-color:#ededed;border:1px solid #adadad;"-->
                            <script>
                                <%=request.getAttribute("arvore")%>
                            </script>
                        <!-- /div -->
                        </vivo:quadro>
    
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="1"></div>
    
                    </td>
                    <td width="255" valign="top" rowspan="2">                    
                        
                        <vivo:quadro id="arvOperacoes" width="250" height="90" label="Sele&ccedil;&atilde;o" scroll="no">
                            
                            <table width="100%">
                                <tr>
                                    <td colspan="2" align="center">
                                        <!--Mensagem do Item Selecionado do Questionario -->

                                        <!-- Botoes para Selecao de Questionario -->
                                        <div id="btQuestionario" style="height:61px;display:none;">
                                        <acesso:controlHiddenItem nomeIdentificador="adm_asat_inclp">
                                            <img vspace="24" src="/FrontOfficeWeb/resources/images/bt_incluir_pergunta_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_pergunta_over.gif" style="border:none;" onClick="Questionario(); return false"/>
                                        </acesso:controlHiddenItem>
                                        </div>
                                        
                                        <!-- Botoes para Selecao de Pergunta -->
                                        <div id="btPergunta" style="display:none;">
                                        <acesso:controlHiddenItem nomeIdentificador="adm_asat_inclres">
                                            <img vspace="4" src="/FrontOfficeWeb/resources/images/bt_incluir_resposta_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_resposta_over.gif" style="border:none;" onClick="IncluirResposta(); return false"/><br>
                                        </acesso:controlHiddenItem>
                                        <acesso:controlHiddenItem nomeIdentificador="adm_asat_altp">
                                            <img vspace="4" src="/FrontOfficeWeb/resources/images/bt_alterar_pergunta_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_alterar_pergunta_over.gif" style="border:none;" onClick="AlterarPergunta(); return false"/><br>
                                        </acesso:controlHiddenItem>
                                        <acesso:controlHiddenItem nomeIdentificador="adm_asat_excp">
                                            <img vspace="4" src="/FrontOfficeWeb/resources/images/bt_excluir_pergunta_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_excluir_pergunta_over.gif" style="border:none;" onClick="ExcluirPergunta(); return false"/>
                                        </acesso:controlHiddenItem>
                                        </div>
                                        
                                        <!-- Botoes para Selecao de Resposta -->
                                        <div id="btResposta" style="display:none;">
                                        <acesso:controlHiddenItem nomeIdentificador="adm_asat_alres">
                                            <img vspace="4" src="/FrontOfficeWeb/resources/images/bt_alterar_resposta_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_alterar_resposta_over.gif" style="border:none;" onClick="AlterarResposta(); return false;"/><br>
                                        </acesso:controlHiddenItem>
                                        <acesso:controlHiddenItem nomeIdentificador="adm_asat_exre">
                                            <img vspace="4" src="/FrontOfficeWeb/resources/images/bt_excluir_resposta_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_excluir_resposta_over.gif" style="border:none;" onClick="ExcluirResposta(); return false"/><br>
                                        </acesso:controlHiddenItem>
                                        <acesso:controlHiddenItem nomeIdentificador="adm_asat_gersa">
                                            <img vspace="4" src="/FrontOfficeWeb/resources/images/bt_gerenciar_salto_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gerenciar_salto_over.gif" style="border:none;" onClick="GerenciaSalto(); return false;"/>
                                        </acesso:controlHiddenItem>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            
                        </vivo:quadro>
                        
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                        
                        <vivo:quadro id="gerPerguntas" width="250" height="282" label="Gerenciamento" scroll="no">
                            <iframe name="iframeQuestionario" src="blank.jsp" width="240" height="256" frameborder="0" scrolling="no"></iframe>
                        </vivo:quadro>
                      
                        
                        <div id="resposta" style="position:absolute;visibility:hidden">
                        <vivo:quadro id="gerRespostas" width="250" height="225" label="Inserir / Alterar Resposta">
                            <table class="tbl_bgGray" width="100%">
                                <tr>
                                    <td width="80"><b>Questionário:</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Nome do Questionário
                                    </td>
                                </tr>
                            </table>
                            <table width="100%">
                                <tr>
                                    <td valign="top">
                                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                                        Pergunta
                                    </td>
                                    <td valign="bottom" width="160" align="left">
                                        Qual é a sua operadora?
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top">
                                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                                        Descri&ccedil;&atilde;o:
                                    </td>
                                    <td valign="top" width="160" align="left">
                                        <textarea style="width:155px;height:65;font-family:Tahoma;font-size:11px;">Vivo</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Salto
                                    </td>
                                    <td>
                                        <netui:select dataSource="{}" style="width:170px" styleClass="SELECT" size="1">
                                            <netui:selectOption value="Gostaria de mudar de operadora?"/>
                                            <netui:selectOption value="Qual é a sua operadora?"/>
                                            <netui:selectOption value="Qual seu tipo de plano?"/>
                                            <netui:selectOption value="Gostaria de mudar de plano?"/>
                                        </netui:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" colspan="2">
                                    <acesso:controlHiddenItem nomeIdentificador="adm_asat_salvar">
                                        <img src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;" alt="Salvar alterações"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                            </table>                    
                        </vivo:quadro>
                        </div>
                    </td>
                </tr>
            </table>
            <table class="tbl_bgGray" width="100%">
                <tr>
                    <td>
                        &nbsp;Legenda:
                    </td>
                    <td>
                        <img src="/FrontOfficeWeb/resources/images/icon_perg_indic.gif" hspace="4" align="middle"> Pergunta
                    </td>
                    <td>
                        <img src="/FrontOfficeWeb/resources/images/icon_perg_indic_desabilitada.gif" hspace="4" align="middle"> Pergunta Inativa
                    </td>
                    <td>
                        <img src="/FrontOfficeWeb/resources/images/icon_resp_nrml.gif" hspace="4" align="middle"> Resposta
                    </td>
                    <td>
                        <img src="/FrontOfficeWeb/resources/images/icon_resp_desabilitado.gif" hspace="4" align="middle"> Resposta Inativa
                    </td>
                    <td>
                        <img src="/FrontOfficeWeb/resources/images/icon_resp_salto.gif" hspace="4" align="middle"> Resposta com salto
                    </td>
                    <td>
                        <img src="/FrontOfficeWeb/resources/images/icon_resp_salto_block.gif" hspace="4" align="middle"> Resposta com salto inativo
                    </td>
                </tr>
            </table>
            <table>
                <tr>                            
                    <td align="left">
                    <img vspace="7" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" style="border:none;" onClick="voltar(); return false;"/>
                    </td>
                </tr>
            </table>
        </html:form>
        </vivo:sessao>
        
        <div id="detalhe" style="z-index:999 ;visibility: hidden; position:absolute; top:36; left:36; width:728; height:528; border-style:solid; border-width:1px; border-color:#adadad; background-color:#e3ecf4;">
                    <table width="728" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                        <tr>
                            <td id="titleBar" style="cursor:move" width="540" height="21">Pesquisa de Feriados </td>
                            <td width="64" valign="top" background="/FrontOfficeWeb/resources/images/window_topbtbg.gif">
                                <div align="right">
                                    <img src="/FrontOfficeWeb/resources/images/window_fechar.gif" hspace="3" onClick="fechaJanela();" style="cursor:hand;" alt="Fechar">
                                </div>
                            </td>
                        </tr>
                    </table>
                    <table width="718" cellpadding="0" cellspacing="0" align="center">
                        <tr>
                            <td>
                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                                <iframe id="frameDetalhe" src="/FrontOfficeWeb/admsistemas/nada.html" frameborder="0" width="718" height="507"></iframe>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad;"></div>
                
                <div id="divSequencia" style="position:absolute;top:310px;left:450px;width:42px;height:48px;">
                    <table border="0">
                            <tr>
                            <td>
                               <div id="divUpDown" style="visibility:hidden;">
                                    <img vspace="4" src="/FrontOfficeWeb/resources/images/bt_up_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_up_over.gif" onclick="enviaSequenciaUp(); return false;" style="border:none;"/>
                                    &nbsp;<img vspace="4" src="/FrontOfficeWeb/resources/images/bt_down_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_down_over.gif" onclick="enviaSequenciaDown(); return false;" style="border:none;"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>

    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormArvoreSatisfacao" property="msgError" />' != "")
        {
            alert('<bean:write name="FormArvoreSatisfacao" property="msgError" />');
        }
             oculta_div();
    -->
    </script> 
        
    </acesso:controlHiddenItem>

           

    </netui-template:section>
</netui-template:template>
