<!--
Módulo.....: Gestão de Processos
Caso de Uso: Detalhe do processo
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<netui-data:getData resultId="warningFrame" value="{globalApp.warningFrame}" />

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm"/>
<bean:define id="atdVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm.atendimentoDetalheVO.atendimentoVO"/>
<bean:define id="alertasVO" name="atdVO" property="alertaVOArray"/>
<bean:define id="acaoVO" name="atdVO" property="WFAcaoVOArray"/>
<bean:define id="faseVO" name="atdVO" property="faseVOArray"/>
<bean:define id="procVO" name="atdVO" property="procedenciaVO"/>

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Workflow"/>
    <netui-template:setAttribute name="modulo" value="Detalhe do Processo Workflow"/>
    <netui-template:section name="headerSection">
    </netui-template:section>
    <netui-template:section name="bodySection">    
        <logic:notEqual name="form" property="inMenu" value="N">
            <!--APLICAÇÃO->MENU-->
            <jsp:include page="/resources/menus/MenuExterno.jsp" />
        </logic:notEqual>
        
        <!--APLICACAO-->
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            
        <vivo:sessao id="qdMain" height="473" width="790" label="Processo" operacoes="Detalhamento" scroll="no">
            <form action="processoAction.do" method="post" id="frmSelecao">            
            <html:hidden name="atdVO" property="idAtendimento" title="idAtendimento"/>
            <html:hidden name="form" property="inMenu" title="inMenu"/>
            <html:hidden property="fila" name="form"></html:hidden>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <vivo:abaGrupo id="btAbaInfo" width="778" height="10" styleClass="abatexto">
                <vivo:abaItem id="btInfo01" onclick="abaSelected(btAbaInfo, btInfo01); abaDetalhes();" value="Detalhes" select="S"/>
                <vivo:abaItem id="btInfo02" onclick="abaSelected(btAbaInfo, btInfo02); abaAtendimento();" value="Atendimento"/>
                <vivo:abaItem id="btInfo03" onclick="abaSelected(btAbaInfo, btInfo03); abaSolicitante();" value="Solicitante"/>
                <vivo:abaItem id="btInfo04" onclick="abaSelected(btAbaInfo, btInfo04); abaNivelProc();" value="N&iacute;vel do Processo"/>           
                <vivo:abaItem id="btInfo05" onclick="abaSelected(btAbaInfo, btInfo05);" value="Linhas Associadas"/>
            </vivo:abaGrupo>
            <table width="778" height="175" cellpadding="0" cellspacing="0" style="background-color:#ededed;border-left:1px solid #adadad;border-right:1px solid #adadad;border-bottom:1px solid #adadad;">
                <tr>
                    <td valign="top">
                        <!-- Aba de Informacao : Detalhes -->
                        <div id="divdetalhes" style="display:none;">
                            <table width="98%" align="right">
                                <tr valign="top">
                                    <td width="30%">Alerta:</td>
                                    <td width="70%">
                                        <html:select name="form" property="motivoSel" title="grupoSel" style="width=200px">
                                            <html:options collection="alertasVO" property="idAlerta" labelProperty="dsMensagem"/> 
                                        </html:select>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td>Fechamento Previsto:</td>
                                    <td><html:text name="atdVO" property="dtParaFechamento" readonly="true" style="width=110"/></td>
                                </tr>
                                <tr valign="top">
                                    <td>Processo:</td>
                                    <td><html:text name="atdVO" property="nrProtocolo" readonly="true" style="width=160"/></td>
                                </tr>
                                <tr valign="top">
                                    <td>Estado:</td>
                                    <td><html:text name="atdVO" property="dsEstado" readonly="true" style="width=160"/></td>
                                </tr>
                                <tr valign="top">
                                    <td>SubEstado:</td>
                                    <td><html:text name="atdVO" property="dsSubEstado" readonly="true" style="width=160"/></td>
                                </tr>  
                                <tr valign="top">
                                    <td>Data de Abertura:</td>
                                    <td><html:text name="atdVO" property="dtAbertura" readonly="true" style="width=110"/></td>
                                </tr>
                                <tr valign="top">
                                    <td>Data de Fechamento:</td>
                                    <td><html:text name="atdVO" property="dtFechamento" readonly="true" style="width=110"/></td>
                                </tr>
                                <logic:notEmpty name="atdVO" property="idAtendimentoOrigem">
                                    <tr valign="top">
                                        <td>Link de Origem:</td>
                                        <td onclick="exibeLinkOrigem();">Clique aqui</td>
                                    </tr>
                                </logic:notEmpty>
                            </table>
                        </div>
                        
                         <!-- Aba de Informacao : Atendimento -->
                         
                        <div id="divatendimento" style="display:none;padding-left:20px;padding-top:10px;">
                            <div style="width:378px;height:160px;overflow:auto;">
                                <bean:define id="scrPalitagem" name="form" property="scriptPalitagem"/>
                                <%= scrPalitagem.toString()%>
                            </div>
                        </div>
                        
                        <!-- Aba de Informacao : Solicitante -->
                        
                        <div id="divsolicitante" style="display:none;">
                            <table width="760" cellpadding="0" cellspacing="0" border="0" align="center">
                                <tr>
                                    <td height="5"></td>
                                </tr>
                                <tr>
                                    <td valign="top">
                                        <vivo:abaGrupo id="btAba0" width="400" height="16" styleClass="abatexto">
                                            <logic:equal name="atdVO" property="inResponsavelAbertura" value="2">
                                                <vivo:abaItem id="bt02" onclick="abaSelected(btAba0, bt02); cliente();" value="Cliente" select="S"/>
                                            </logic:equal>
                                            <logic:equal name="atdVO" property="inResponsavelAbertura" value="1">
                                                <vivo:abaItem id="bt03" onclick="abaSelected(btAba0, bt03); usuario();" value="Usuário" select="S"/>
                                            </logic:equal>
                                            <logic:equal name="atdVO" property="inResponsavelAbertura" value="6">
                                                <vivo:abaItem id="bt04" onclick="abaSelected(btAba0, bt04); prospect();" value="Prospect" select="S"/>
                                            </logic:equal>
                                            <logic:equal name="atdVO" property="inResponsavelAbertura" value="7">
                                                <vivo:abaItem id="bt04" onclick="abaSelected(btAba0, bt04); prospect();" value="Nao Cliente" select="S"/>
                                            </logic:equal>
                                            <vivo:abaItem id="btProcessoFrmContato" onclick="abaSelected(btAba0, btProcessoFrmContato); frmProcessoContato();" value="Formulário de Processo"/>
                                            <vivo:abaItem id="btProcessoContato" onclick="abaSelected(btAba0, btProcessoContato); ProcessoContato();" value="Contato"/>
                                        </vivo:abaGrupo>
                                    </td>
                                </tr>
                            </table>
                            <div id="divbodysolicitante" style="display:none;">
                                <table height="145" width="760" align="center" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;">
                                    <tr>
                                        <td valign="top" class="BorderTable1">
                                            <logic:equal name="atdVO" property="inResponsavelAbertura" value="2">
                                                <jsp:include page="abaCliente.jsp" />
                                            </logic:equal>
                                            <logic:equal name="atdVO" property="inResponsavelAbertura" value="1">
                                                <jsp:include page="abaUsuario.jsp" />
                                            </logic:equal>
                                            <logic:equal name="atdVO" property="inResponsavelAbertura" value="3">
                                                <jsp:include page="abaProspect.jsp" />
                                            </logic:equal>
                                         </td>
                                    </tr>
                                </table>
                            </div>
                            <div id="divbodyfrmcontato" style="display:none;">
                                <table height="145" width="760" align="center" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;">
                                    <tr>
                                        <td valign="top" class="BorderTable1">
                                            <jsp:include page="abaFormularioProcesso.jsp" />
                                         </td>
                                    </tr>
                                </table>
                            </div>
                            <div id="divbodycontato" style="display:none;">
                                <table height="145" width="760" align="center" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;">
                                    <tr>
                                        <td valign="top" class="BorderTable1">
                                            <jsp:include page="abaContato.jsp" />
                                         </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        
                        <!-- Aba de Informacao : Nivel do Processo -->
                        
                        <div id="divnivelproc" style="display:none;">
                            <table width="750" height="165" cellpadding="0" cellspacing="0" border="0" align="center">
                                <tr>
                                    <td height="5"></td>
                                </tr>
                                <tr>
                                    <td valign="top">
                                        <vivo:tbTable selectable="false" layoutWidth="732" layoutHeight="140" tableWidth="732" styleId="tableTitle" sortable="false">
                                            <vivo:tbHeader>
                                                <logic:iterate id="faseVO" name="atdVO" property="faseVOArray" indexId="idxFase">
                                                    <vivo:tbHeaderColumn align="center" width="20%" type="string"><bean:write name="faseVO" property="dsFase"/></vivo:tbHeaderColumn>
                                                </logic:iterate>
                                            </vivo:tbHeader>
                                            <vivo:tbRows>
                                                <vivo:tbRow key="linha0">
                                                    <logic:iterate id="faseVO" name="atdVO" property="faseVOArray" indexId="idxFase">
                                                        <vivo:tbRowColumn>
                                                                <vivo:tbTable selectable="false" layoutWidth="230" layoutHeight="60" tableWidth="230" styleId="tableTitle" sortable="false">
                                                                    <vivo:tbHeader>
                                                                        <vivo:tbHeaderColumn align="center" width="30%" type="string">Nível</vivo:tbHeaderColumn>
                                                                        <vivo:tbHeaderColumn align="left" width="40%" type="string">Grupo</vivo:tbHeaderColumn>
                                                                        <vivo:tbHeaderColumn align="center" width="30%" type="string">Ação</vivo:tbHeaderColumn>
                                                                    </vivo:tbHeader>
                                                                    <vivo:tbRows>
                                                                        <logic:iterate id="nivelVO" name="faseVO" property="nivelVOArray" indexId="idxNivel">
                                                                            <vivo:tbRow key="linha0">
                                                                                <vivo:tbRowColumn><%=idxNivel%></vivo:tbRowColumn>
                                                                                <vivo:tbRowColumn><bean:write name="nivelVO" property="nmGrupo"/></vivo:tbRowColumn>
                                                                                <vivo:tbRowColumn><bean:write name="nivelVO" property="dsAtividade"/></vivo:tbRowColumn>
                                                                            </vivo:tbRow>
                                                                         </logic:iterate>
                                                                    </vivo:tbRows>
                                                                </vivo:tbTable>
                                                        </vivo:tbRowColumn>
                                                    </logic:iterate>
                                                </vivo:tbRow>
                                            </vivo:tbRows>
                                        </vivo:tbTable>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        
                        <div id="divlinhasassoc" style="display:none;">
                            <table width="750" height="165" cellpadding="0" cellspacing="0" border="0" align="center">
                                <tr>
                                    <td height="5"></td>
                                </tr>
                                <tr>
                                    <td valign="top">
                                        <vivo:tbTable selectable="false" layoutWidth="230" layoutHeight="60" tableWidth="230" styleId="tableTitle" sortable="false">
                                            <vivo:tbHeader>
                                                <vivo:tbHeaderColumn align="center" width="50%" type="string">Conta</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="left" width="50%" type="string">Linha</vivo:tbHeaderColumn>
                                            </vivo:tbHeader>
                                            <vivo:tbRows>
                                                <vivo:tbRow key="linha0">
                                                    <vivo:tbRowColumn>123456</vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>(11)6546-3216</vivo:tbRowColumn>
                                                </vivo:tbRow>
                                                <vivo:tbRow key="linha0">
                                                    <vivo:tbRowColumn>123456</vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>(11)6546-3216</vivo:tbRowColumn>
                                                </vivo:tbRow>
                                                <vivo:tbRow key="linha0">
                                                    <vivo:tbRowColumn>123456</vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>(11)6546-3216</vivo:tbRowColumn>
                                                </vivo:tbRow>
                                                <vivo:tbRow key="linha0">
                                                    <vivo:tbRowColumn>123456</vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>(11)6546-3216</vivo:tbRowColumn>
                                                </vivo:tbRow>
                                            </vivo:tbRows>
                                        </vivo:tbTable>
                                    </td>
                                </tr>
                            </table>
                        </div>

                    </td>
                </tr>
            </table>
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <div id="dvRetornoOperacao" style="visibility:hidden; position:absolute; left:0; top:200px; width:778px; height:245px; z-index:0">
                <iframe id="iframeRetornoOperacao" name="iframeRetornoOperacao" src="AguardeAcao.jsp" width="100%" height="100%" >
                </iframe>
            </div>
            <div id="dvOperacoes" style="">             
                <vivo:quadro id="qdMainAbas" width="778" height="228" label="Operações" scroll="NO" onclick="atendimentoProcesso()">
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                    <table width="768" style="background-color:#ffffff;border:1px solid #adadad;">
                        <tr>
                            <td width="70">&nbsp;Operações:</td>
                            <td width="698">
                                
                                <html:select name="form" property="acaoSel"  onchange="changeOpcao(this.value)" style="width=200">
                                     <html:option value="0">Selecionar Operação</html:option>
                                     <html:options collection="acaoVO" property="idAtividade" labelProperty="dsAtividade"/>
                                </html:select>
                                
                            </td>
                        </tr>
                    </table>
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                    <table width="768" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td valign="top">                            
                                <vivo:abaGrupo id="btAba" width="300px" height="10px" styleClass="abatexto">
                                    <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01); historico();" value="Histórico" select="S"/>
                                    <vivo:abaItem id="bt05" onclick="abaSelected(btAba, bt05); abaDinamica();" value="Selecionar Operação"/>                            
                                    <logic:notEmpty name="atdVO" property="encerramentoVO">
                                        <vivo:abaItem id="btEncerramento" onclick="abaSelected(btAba, btEncerramento); abaEncerramento();" value="Encerramento BKO"/>
                                    </logic:notEmpty>
                                </vivo:abaGrupo>
                            </td>
                        </tr>
                    </table>
                    <div id="divbodyhistorico">
                        <table width="768" height="167" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;background-color:#ededed;">
                            <tr>
                                <td valign="top" class="BorderTable1" width="768" height="100%">
                                     <jsp:include page="Historico.jsp" />
                                </td>
                            </tr>
                        </table>
                    </div>            
                    <div id="divbodyacoe" style="display:none;">
                        <table width="768" height="167" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;background-color:#ededed;">
                            <tr>
                                <td valign="top" class="BorderTable1" width="768" height="100%">
                                     <iframe id="ifrmAbas" name="ifrmAbas" width="755" height="153" frameborder="0" scrolling="auto" src=""></iframe>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div id="divBodyEncerramento" style="display:none;">
                        <table width="768" height="167" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;background-color:#ededed;">
                            <tr>
                                <td valign="top" class="BorderTable1" width="768" height="100%">
                                     <jsp:include page="encerramentoBKO.jsp" />
                                </td>
                            </tr>
                        </table>
                    </div>            
                </vivo:quadro>
            </div>
            
            <logic:notEqual name="form" property="inMenu" value="N">
                <img onClick="voltar(); return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" style="border:none;" vspace="5"/>
            </logic:notEqual>
            
            <!--Quadro Flutuante-->
            <vivo:quadroFlutuante id="dvConPesq" idIframe="ifrmConPesq" width="300" height="100" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" onclick="voltar();"/>
            <vivo:quadroFlutuante id="dvDetalheOrigem" idIframe="ifrmDetalheOrigem" width="780" height="600" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Detalhe Origem"/>
            <vivo:quadroFlutuante id="qdDocTec" idIframe="ifrmDocTec" label="Documentos Técnicos" spacesLeft="10" spacesTop="70" url="<%=request.getContextPath()%>" display="none" height="200" width="780"/>

            </form>
            
        </vivo:sessao>
        

        <!--Quadro Flutuante-->
        <vivo:quadroFlutuante id="dvAtendimento2" idIframe="iframeAtendimento" width="800" height="600" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Atendimento"/>
        
        <!--Controle das abas-->
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script> 
        <script language="javascript">
            var abaValue      = 0;
            var limparObjetos = "";
            
            /* 
            * Tratamento do quadro solicitante
            */
            function cliente() {
                divbodysolicitante.style.display = '';
                divbodyfrmcontato.style.display = 'none';
                divbodycontato.style.display = 'none';
            }
            
            function usuario() {
                divbodysolicitante.style.display = '';
                divbodyfrmcontato.style.display = 'none';
                divbodycontato.style.display = 'none';
            }
            
            function prospect() {
                divbodysolicitante.style.display = '';
                divbodyfrmcontato.style.display = 'none';
                divbodycontato.style.display = 'none';
            }

            function frmProcessoContato() {
                divbodysolicitante.style.display = 'none';
                divbodyfrmcontato.style.display = '';
                divbodycontato.style.display = 'none';
            }
            
            function ProcessoContato() {
                divbodysolicitante.style.display = 'none';
                divbodyfrmcontato.style.display = 'none';
                divbodycontato.style.display = '';
            }
            //Inicializa a aba de cliente, usuário ou prospect
            <logic:equal name="atdVO" property="inResponsavelAbertura" value="2">
                abaSelected(btAba0, bt02); cliente();
            </logic:equal>
            <logic:equal name="atdVO" property="inResponsavelAbertura" value="1">
                abaSelected(btAba0, bt03); usuario();
            </logic:equal>
            <logic:equal name="atdVO" property="inResponsavelAbertura" value="6">
                abaSelected(btAba0, bt04); prospect();
            </logic:equal>
            <logic:equal name="atdVO" property="inResponsavelAbertura" value="7">
                abaSelected(btAba0, bt04); prospect();
            </logic:equal>

            function voltar() {
                window.location.href='<bean:write name="form" property="fila"/>';
            }
            
            
            /* 
            * Fim do tratamento do quadro solicitante
            */

            /* 
            * Tratamento do quadro operações
            */
            function historico() {
                divbodyhistorico.style.display = '';
                divbodyacoe.style.display = 'none';
                divBodyEncerramento.style.display = 'none';
            }
                        
            function abaEncerramento() {
                divBodyEncerramento.style.display = '';
                divbodyacoe.style.display = 'none';
                divbodyhistorico.style.display = 'none';
            }
            
            function fechamento() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/fechamentoBegin.do?idAtividade=" + abaValue + "&idTabelaMotivo=4";
                document.forms["frmSelecao"].submit();
            }

            function encerramentoBKO() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/encerramentoBegin.do?docTec=0&idAtividade=" + abaValue;
                document.forms["frmSelecao"].submit();
            }
            
            function encerramentoDocTecBKO() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/encerramentoBegin.do?docTec=1&idAtividade=" + abaValue;
                document.forms["frmSelecao"].submit();
            }

            function concluir() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/concluirBegin.do?idAtividade=" + abaValue + "&idTabelaMotivo=3";
                document.forms["frmSelecao"].submit();
            }
            
            function devolverBKO() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/devolverBegin.do?idAtividade=" + abaValue + "&idTabelaMotivo=2";
                document.forms["frmSelecao"].submit();
            }
            
            function encaminhar() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/encaminharBegin.do?idAtividade=" + abaValue + "&idTabelaMotivo=1";
                document.forms["frmSelecao"].submit();
            }

            function reabertura() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/reaberturaBegin.do?idAtividade=" + abaValue;
                document.forms["frmSelecao"].submit();
            }
            
            function pausa() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/pausaBegin.do?idAtividade=" + abaValue + "&idTabelaMotivo=5";
                document.forms["frmSelecao"].submit();
            }
            
            function cancelar() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/cancelarBegin.do?idAtividade=" + abaValue;
                document.forms["frmSelecao"].submit();
            }
            
            function insistencia() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/insistenciaBegin.do?idAtividade=" + abaValue + '&qtInsistencia=<bean:write name="atdVO" property="qtInsistencia"/>';
                document.forms["frmSelecao"].submit();
            }
            
            function documentoAssociado() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/documentoAssociadoBegin.do?idAtividade=" + abaValue + '&idProcedencia=<bean:write name="procVO" property="idProcedencia" format=""/>';
                document.forms["frmSelecao"].submit();
            }
            
            function solucaoTecnica() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/testesBegin.do?idAtividade=" + abaValue;
                document.forms["frmSelecao"].submit();
            }
            
            function suspeito() {
                document.forms["frmSelecao"].target = "ifrmAbas";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/suspeitoBegin.do?idAtividade=" + abaValue + "&idTabelaMotivo=6";
                document.forms["frmSelecao"].submit();
            }

            function changeOpcao(opcao) {
                divbodyhistorico.style.display = 'none';
                divbodyacoe.style.display = '';
                abaValue = opcao;
                abaDinamica();
            }
            
            
            function abaDinamica() {
            
                abaSelected(btAba, bt05);

                if(abaValue == 0) {
                    bt05.innerText = 'Selecione Operaçao';
                }
                <logic:iterate id="acoeVO" name="atdVO" property="WFAcaoVOArray" indexId="idxAcoe">
                if(abaValue == <bean:write name="acoeVO" property="idAtividade"/>) {
                    //Inicio animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();                
                    bt05.innerText = '<bean:write name="acoeVO" property="dsAtividade"/>';
                    <bean:write name="acoeVO" property="jsAtividade"/>;
                }
                </logic:iterate>
                divbodyhistorico.style.display = 'none';
                divbodyacoe.style.display = '';
                divBodyEncerramento.style.display = 'none';
                
            }
            
            function chamaHistoricoTeste(){
               ifrmAbas.document.all['divTestes'].style.display='none';
               ifrmAbas.document.all['divHist'].style.display='';
            }
            
            //Inicializa a aba de histórico
            abaSelected(btAba, bt01); historico();

            /* 
            * fim do tratamento do quadro operações
            */

        function formataNrLinha(linha){
       
            valor = linha;     
            for(i=0;i<valor.length;i++){
              if(!inteiro.test(valor.charAt(i))){
                valor = valor.substring(0,i) + valor.substring(i+1,valor.length)
                i = 0;
              }
            }
            //caso valor tenha 0 como primeiro dígito
            if(valor.substring(0,1)==0)
                valor = valor.substring(1,valor.length);
            
            return valor;                                     
        }
        
            /* 
            * Tratamento do click da lupa do quadro solicitante
            */
            function atendimentoProcesso() {
                <logic:equal name="atdVO" property="inResponsavelAbertura" value="2">
                dvAtendimento2.style.display = '';
                document.forms["frmSelecao"].target = "iframeAtendimento";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/cliente/TelaInicial/pesquisaCliente.do?tipoLista=clientes&pesquisa=celular&valor="+formataNrLinha('<bean:write name="form" property="linhaFormatada"/>');
                document.forms["frmSelecao"].submit();
                </logic:equal>
                <logic:equal name="atdVO" property="inResponsavelAbertura" value="1">
                dvAtendimento2.style.display = '';
                document.forms["frmSelecao"].target = "iframeAtendimento";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/cliente/TelaInicial/pesquisaCliente.do?tipoLista=clientes&pesquisa=celular&valor="+formataNrLinha('<bean:write name="form" property="linhaFormatada"/>');
                document.forms["frmSelecao"].submit();
                </logic:equal>
                //Processa a limpeza do cabeçalho, menu e rodapé
               // limparObjetos = window.setInterval( 'atendimentoProcessoClearCabecMenu()', 1000 );
            }

            function atendimentoProcessoClearCabecMenu() {
                try {
                    //Limpa a apresentação do header
                    iframeAtendimento.hdMinPrincipal.style.display = "none";
                    iframeAtendimento.hdMaxPrincipal.style.display = "none";
                    
                    //Limpa a apresentação do menu
                    iframeAtendimento.mnMin.style.display = "none";
                    iframeAtendimento.mnMax.style.display = "none";
                    
                    //Limpa a pesquisa + ura
                    iframeAtendimento.tbPesquisa.style.display = "none";
                    iframeAtendimento.dvFrameURA.style.display = "none";
                    
                    window.clearInterval(limparObjetos);
                    limparObjetos = "";
                } 
                catch(e)
                {}
            }
            /* 
            * Fim do tratamento do click da lupa do quadro solicitante
            */

            function submitPesquisar() {
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

                document.forms["frmSelecao"].method = "POST";
                document.forms["frmSelecao"].action = "detalheBegin.do?inMenu=<bean:write name="form" property="inMenu"/>";
                document.forms["frmSelecao"].target = "";
                document.forms["frmSelecao"].submit();
            }
            
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            
            divAbaAtendimento   = document.getElementById('divatendimento');
            divAbaDetalhes      = document.getElementById('divdetalhes');
            divAbaSolicitante   = document.getElementById('divsolicitante');
            divAbaNivelProc     = document.getElementById('divnivelproc');
            divAbaLinhasAssoc   = document.getElementById('divlinhasassoc');
            
            function abaDetalhes() {
                divAbaAtendimento.style.display = 'none';
                divAbaSolicitante.style.display = 'none';
                divAbaNivelProc.style.display = 'none';
                divAbaDetalhes.style.display = '';
                divAbaLinhasAssoc.style.display = 'none';
            }
            
            function abaAtendimento() {
                divAbaDetalhes.style.display = 'none';
                divAbaSolicitante.style.display = 'none';
                divAbaNivelProc.style.display = 'none';
                divAbaAtendimento.style.display = '';
                divAbaLinhasAssoc.style.display = 'none';
            }
            
            function abaSolicitante() {
                divAbaDetalhes.style.display = 'none';
                divAbaAtendimento.style.display = 'none';
                divAbaNivelProc.style.display = 'none';
                divAbaSolicitante.style.display = '';
                divAbaLinhasAssoc.style.display = 'none';
            }
            
            function abaNivelProc() {
                divAbaDetalhes.style.display = 'none';
                divAbaAtendimento.style.display = 'none';
                divAbaNivelProc.style.display = 'none';
                divAbaSolicitante.style.display = 'none';
                divAbaNivelProc.style.display = '';
                divAbaLinhasAssoc.style.display = 'none';
            }

            function abaLinhaAssoc() {
                divAbaDetalhes.style.display = 'none';
                divAbaAtendimento.style.display = 'none';
                divAbaNivelProc.style.display = 'none';
                divAbaSolicitante.style.display = 'none';
                divAbaNivelProc.style.display = 'none';
                divAbaLinhasAssoc.style.display = '';
            }
            
            <logic:notEmpty name="atdVO" property="idAtendimentoOrigem">
                function exibeLinkOrigem(){
                    document.forms["frmSelecao"].action = "/FrontOfficeWeb/extworkflw/AtendimentoDetalhe/detalheBegin.do?idAtendimento="+<bean:write name="atdVO" property="idAtendimentoOrigem"/>+"&inMenu=N";
                    document.forms["frmSelecao"].target = "ifrmDetalheOrigem";
                    document.forms["frmSelecao"].submit();
                    dvDetalheOrigem.style.display = '';
                }
            </logic:notEmpty>
            
            abaDetalhes();
        </script>

<%=pageContext.getAttribute("warningFrame")%>
     
    </netui-template:section>

</netui-template:template>


