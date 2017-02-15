<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTestesQuestVODocument.AtendimentoWorkflowTestesQuestVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFQuestVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowTestesVO.atendimentoWorkflowTestesQuestVO" type="br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTestesQuestVODocument.AtendimentoWorkflowTestesQuestVO"/>
<bean:define id="atdWFHistVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowTestesVO.atendimentoWorkflowTestesHistVO"/>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<form action="testesGravar.do" method="post" name="atendimentoForm">
    <html:hidden name="form" property="numeroCampos" />
    <html:hidden name="form" property="idAtendimento"/>
    <vivo:abaGrupo id="btAba" width="300px" height="10px" styleClass="abatexto">
        <vivo:abaItem id="btTestes" onclick="abaSelected(btAba, btTestes); testes();" value="Testes" select="S"/>
        <vivo:abaItem id="btHistorico" onclick="abaSelected(btAba, btHistorico); historico();" value="Histórico"/>                                
    </vivo:abaGrupo>
    <div id="divHist" style="DISPLAY:none;POSITION:absolute;">   
        <logic:notEmpty name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowTestesVO.atendimentoWorkflowTestesHistVO.atendimentoWorkflowTestesQuestVOArray">
            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="720" layoutHeight="100" tableWidth="720" styleId="tableTestes" sortable="true">
                        <vivo:tbHeader>					
                            <vivo:tbHeaderColumn align="center"  width="20%" type="string">Usuário</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Data</vivo:tbHeaderColumn> 					                
                            <vivo:tbHeaderColumn align="left"   width="40%" type="data">Testes</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left"   width="30%" type="string">Observaçao</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows scroll="false">
                            <logic:iterate name="atdWFHistVO" property="atendimentoWorkflowTestesQuestVOArray" id="Historico">
                                    <vivo:tbRow key="linha1">
                                        <vivo:tbRowColumn><bean:write name="Historico" property="usuarioVIVO.nmLoginUsuario" /></vivo:tbRowColumn>                      
                                        <vivo:tbRowColumn><bean:write name="Historico" property="dtTeste"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn>                                        
                                            <table width="100%">
                                                    <logic:iterate name="Historico" property="atendimentoWorkflowTesteVOArray" id="Teste">                                                    
                                                        <tr>
                                                            <td><bean:write name="Teste" property="dsTeste" /></td>                      
                                                            <td><bean:write name="Teste" property="dsResposta"/></td>                                       
                                                        </tr>
                                                    </logic:iterate>
                                            </table>                                            
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn><vivo:hint maxLength="25" spaces="S"><bean:write name="Historico" property="dsObservacao"/></vivo:hint></vivo:tbRowColumn>                                        
                                    </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                </vivo:tbTable>
        </logic:notEmpty><br>
    </div>
    <div id="divTestes" style="DISPLAY:;POSITION:absolute;">  
        <logic:notEmpty name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowTestesVO.atendimentoWorkflowTestesQuestVO">           
            <table width="100%">
                <tr>
                    <td><b>Testes</b></td>
                </tr>
            <logic:iterate id="Testes" name="atdWFQuestVO" property="atendimentoWorkflowTesteVOArray" indexId="ctr">
                <tr valign="middle">
                    <td>
                        <bean:write name="Testes" property="dsTeste" />:<br>
    
                        <html:hidden name="form" property="idCampoTestes" value='<%=atdWFQuestVO.getAtendimentoWorkflowTesteVOArray(ctr.intValue()).getIdTeste()%>'/>
                        <html:hidden name="form" property="dsCampoTestes" value='<%=atdWFQuestVO.getAtendimentoWorkflowTesteVOArray(ctr.intValue()).getDsTeste()%>'/>
                        <html:radio name="form" property='<%= "valorCampo[" + ctr + "].valor" %>' value='S' styleClass="radio" />Sim
    
                        <html:radio name="form" property='<%= "valorCampo[" + ctr + "].valor" %>' value='N' styleClass="radio" />Não                
                    </td>                
                </tr>
            </logic:iterate>
        </logic:notEmpty>
                <tr>
                    <td><br>Comentário:<br><html:textarea name="form" property='observacaoTestes' rows="6" cols="50"/></td>
                </tr>
                
                <tr>
                    <td align="center" colspan="2">
                        <br><vivo:botao id="btAplicar" width="60px" height="10px" value="Gravar" styleClass="btTemplate" onclick="enviar()"/>
                    </td>
                </tr>
            </table>
        </div>
</form>

<script>
    function alteraSize() {
        document.getElementById('tableTestes_div').style.height="290";
    }

    function alteraSizeMin() {
        document.getElementById('tableTestes_div').style.height="100";
    }

    function enviar(){
    
        var nTestes = <%= ((AtendimentoWorkflowTestesQuestVO)atdWFQuestVO).getAtendimentoWorkflowTesteVOArray().length %>;
        
        // verificar se cada uma das perguntas foi respondida
        for(i=0; i<nTestes; i++) {
            opcoes = document.forms[0].elements["valorCampo[" + i + "].valor"];
            testeSelecionado = false;
            for (j=0; j<opcoes.length; j++) {
                if (opcoes[j].checked) {
                    testeSelecionado = true;
                }
            }
            
            // se esse teste nao foi selecionado envie um alert
            // e retorne false
            if (!testeSelecionado) {
                alert("Responda todas as perguntas do Questionário!");
                return false;
            }
        }
        
        if (parent.dvRetornoOperacao) {
            //parent.dvRetornoOperacao.style.visibility="";
            targetFrame = "iframeRetornoOperacao";
        } else {
            targetFrame = "";
        }
        
        if(nTestes != 0){
            //Inicio animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

            document.forms[0].action = "testesGravar.do";
            document.forms[0].target = "";
            document.forms[0].submit();
        }
        else{
                alert("Não existe perguntas Cadastradas!");
                return false;
        }
    }
    
    function historico(){
        document.all['divHist'].style.display = '';
        document.all['divTestes'].style.display = 'none';
    }
    
    function testes(){
        document.all['divHist'].style.display = 'none';
        document.all['divTestes'].style.display = '';
    }
        /*
    if (parent.up)
    {
        alteraSizeMin();
    }
    else
    {
        alteraSize();
    }*/
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
</script>

