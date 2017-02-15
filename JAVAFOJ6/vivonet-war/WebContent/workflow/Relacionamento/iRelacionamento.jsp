<!--
Módulo.....: Workflow
Caso de Uso: Relacionamento
-->

<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentoVODocument.AtendimentoRelacionamentoVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<html>
    <head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_irel_verpagina">
        <form action="pesquisarRelacionamento.do" method="post" name="relacionamentoForm">
            <vivo:tbTable selectable="true" layoutWidth="740" layoutHeight="100" tableWidth="740" styleId="tableRelacionamentoIFrame" sortable="true">
                    <vivo:tbHeader>					
                        <vivo:tbHeaderColumn align="right"  width="10%" type="number">Processo</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="15%" type="string">Linha</vivo:tbHeaderColumn> 					                
                        <vivo:tbHeaderColumn align="left"   width="40%" type="string">Contato</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left"   width="15%" type="string">Estado / Subestado</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="10%" type="date">Abertura</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="10%" type="date">Fechamento</vivo:tbHeaderColumn>
                    </vivo:tbHeader>

                    <vivo:tbRows>
        
                        <logic:notEmpty name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm.atendimentoRelacionamentosVO">
                            <bean:define id="atendimentoRelacionamentosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm.atendimentoRelacionamentosVO" />                
                            <logic:iterate name="atendimentoRelacionamentosVO" property="atendimentoRelacionamentoVOArray" id="correnteAtendimento">
                                    <% 
                                        String inACS = ((AtendimentoRelacionamentoVO)correnteAtendimento).getAtendimentoVO().getInACS();
                                        String idAtendimento = ((AtendimentoRelacionamentoVO)correnteAtendimento).getAtendimentoVO().getIdAtendimento();
                                    %>
                                    <vivo:tbRow key="linha1" onRowClick='<%="obtemDetalhes(\'"+inACS+"\',\'"+idAtendimento+"\');"%>'>
                                        <vivo:tbRowColumn>
                                            <bean:write name="correnteAtendimento" property="atendimentoVO.nrProtocolo" format="###"/>
                                            <html:hidden name="correnteAtendimento" property="atendimentoVO.idAtendimento" />
                                            <logic:equal name="correnteAtendimento" property="atendimentoVO.inACS" value="S">
                                                <img src="<%=request.getContextPath()%>/resources/images/icon_acs.gif">
                                            </logic:equal>
                                        </vivo:tbRowColumn>                      
                                        <vivo:tbRowColumn>
                                            <bean:write name="correnteAtendimento" property="atendimentoVO.nrTelefone"/>
                                            <html:hidden name="correnteAtendimento" property="atendimentoVO.nmURLDados" />                                        
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="correnteAtendimento" property="atendimentoVO.arvoreAtendimentoVO.descricaoCompleta" /></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="correnteAtendimento" property="atendimentoVO.WFEstadoVO.dsEstado"/><br><bean:write name="correnteAtendimento" property="atendimentoVO.WFSubEstadoVO.dsSubEstado"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="correnteAtendimento" property="atendimentoVO.dtAbertura"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="correnteAtendimento" property="atendimentoVO.dtFechamento"/></vivo:tbRowColumn>
                                    </vivo:tbRow>
                            </logic:iterate>
                        </logic:notEmpty>
                    </vivo:tbRows>
            </vivo:tbTable>
        </form>   
   
    </acesso:controlHiddenItem>      
    </body>
    
    <script language="javascript">
    //Detalhe do processo
    function obtemDetalhes(inACS, idAtendimento) {
        //Inicializa a animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        //Exibe o quadro flutuante
        //parent.parent.dvAtendimento.style.display = '';
        
        var action = "";

        //Obtem a primeira coluna para validar o icone do acs    
        //coluna1 = poRow.cells(0);
        
        //Verifica se é acs
        if( inACS == 'S' ) {
            //Obtém tela de detalhe            
            document.forms["relacionamentoForm"].target = "ifrmAtendimento";
            document.forms["relacionamentoForm"].action = "/FrontOfficeWeb/workflow/AtendimentoDetalheACS/begin.do?IDATENDIMENTO="+idAtendimento;
            document.forms["relacionamentoForm"].submit();
        } else {
        
            //Obtém tela de detalhe do front-office
            //document.forms["relacionamentoForm"].target = "ifrmAtendimento";
            //document.forms["relacionamentoForm"].action = "/FrontOfficeWeb/workflow/AtendimentoDetalhe/detalheBegin.do?idAtendimento="+idAtendimento+"&inMenu=N";
            //document.forms["relacionamentoForm"].submit();
        }
        top.frameApp.showPopupTI('Atendimento', 800, 590, null, action);
    }
    
    //Fim animação
    //if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

    //Resize da grade
    parent.rezisePage();
    </script>
</html>
