﻿<!--
Módulo.....: Workflow
Caso de Uso: Registrar Contato (Atendimento)
-->
<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%-- page import="br.com.vivo.fo.workflow.vo.AtendimentoTipoComunicacaoVODocument.AtendimentoTipoComunicacaoVO"--%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%-- page import="br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO"--%>
<%-- page import="workflow.RegistrarContato.RegistrarContatoController.RegistrarContatoForm"--%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>


<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />

<%
/* remover os itens com SMS e E-MAIL - workaround incidencia 2684
AtendimentoVO atdVO = ((RegistrarContatoForm)Form).getAtendimentoVO();
AtendimentoTipoComunicacaoVO[] aCom = (AtendimentoTipoComunicacaoVO[])atdVO.getPessoaVO().getAtendimentoTipoComunicacaoVOArray();

int tot=aCom.length;
for(int i=tot-1; i>=0; i--) {
    if (aCom[i].getDescricao().toUpperCase().matches("(.*)?(SMS|MAIL)(.*)?")) {
        atdVO.getPessoaVO().removeAtendimentoTipoComunicacaoVO(i);
    }
}
*/
%>

<bean:define id="aComunicacao"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm.atendimentoVO.pessoaVO.atendimentoTipoComunicacaoVOArray" />

<html>
    <head>
        <title>
            Web Application Page
        </title>
    </head>
    <body>


        <form name="registrarContatoFormIframe">
            <html:select name="Form" property="tipoComunicacaoSelecionada" onchange="obtemComunicacaoIFrame(this,document.registrarContatoForm.comunicacaoDisponivel);">
                <html:option value="-1">-- Selecione --</html:option>
                <logic:present  name="aComunicacao">
                	<html:options collection="aComunicacao" property="idTipoComunicacao" labelProperty="descricao" />
                </logic:present>
            </html:select>
        </form>
        <script>

            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

            var aOptComponent = document.forms["registrarContatoFormIframe"].elements["tipoComunicacaoSelecionada"];
            var aOptComponentsParent = parent.document.forms["registrarContatoForm2"].elements["tipoComunicacaoSelecionada"];
            var aOptComponentsParentDisp = parent.document.forms["registrarContatoForm2"].elements["comunicacaoDisponivel"];
            var aOptComponentsParentSelec = parent.document.forms["registrarContatoForm2"].elements["comunicacaoSelecionada"];

            parent.parent.inCelular = false;

            var indice = aOptComponentsParent.selectedIndex;
            var valor = aOptComponentsParent.options[indice].value;
            var novoIndice = 0;
            var atualizaIndice = true;

            //limpa o combo de comunicação disponível
            while(aOptComponentsParentDisp.options.length != 0)
                aOptComponentsParentDisp.options.remove(0);

            //limpa o combo de tipo comunicação
            while(aOptComponentsParent.options.length != 0)
                aOptComponentsParent.options.remove(0);

            for( x = 0; x < aOptComponent.options.length; x++ ) {

                oOption = parent.document.createElement("OPTION");
                aOptComponentsParent.options.add(oOption);
                oOption.innerText = aOptComponent.options(x).text;
                oOption.value     = aOptComponent.options(x).value;
                if (oOption.value == valor && atualizaIndice)
                    novoIndice = x;

                //Verificou que a opção "Celular" retornou do serviço
                if (aOptComponent.options(x).text == "CELULAR") {
                    parent.parent.inCelular = true;
                    novoIndice = x;
                    atualizaIndice = false;
                }

            }

            //Se não existir opção de contato "Celular" proveniente do serviço, 
            //combo é preenchido com número da linha na tela de atendimento.
            if (parent.parent.inCelular == false
                && (top.frameApp.idRelacionamento != 6 || top.frameApp.idRelacionamento == undefined)) {

                <%
                ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
                if (parametros != null) {
                %>


                    nrCelular = parent.parent.formatCelular("<%= parametros.getNrLinha() %>");

                    oOption = parent.document.createElement("OPTION");
                    aOptComponentsParent.options.add(oOption);
                    oOption.innerText = "CELULAR";
                    oOption.value     = "41";

                    var inCreateCell = true;
                    for (i=0; i<aOptComponentsParentSelec.length; i++) {
                        if (aOptComponentsParentSelec.options[i].value == nrCelular) {
                            inCreateCell = false;
                        }
                    }

                    if (inCreateCell) {

                        oOption2 = parent.document.createElement("OPTION");
                        aOptComponentsParentSelec.options.add(oOption2);
                        oOption2.innerText = nrCelular;
                        oOption2.value     = nrCelular;
                    }

                    aOptComponentsParent.selectedIndex = aOptComponentsParent.options.length-1;

                <%
                }
                %>
            } else {
                if (novoIndice > 0)
                    aOptComponentsParent.selectedIndex = novoIndice;
            }

            var constroi = true;
            for(x=0;x<aOptComponentsParent.options.length;x++){
                if(aOptComponentsParent.options[x].value=="0"){
                    constroi = false;
                }
            }

            /*if(constroi){
                oOption = parent.document.createElement("OPTION");
                aOptComponentsParent.options.add(oOption);
                oOption.innerText = "Sem Retorno";
                oOption.value     = "0";
            }*/

            // parent.obtemComunicacaoIFrame(aOptComponentsParent, aOptComponentsParentDisp);

        </script>

    
    </body>

</html>