<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gestorContasForm" type="cliente.GestorContas.GestorContasController.GestorContasForm" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="FrontOffice - Administração" />
<netui-template:setAttribute name="modulo" value="Gestor de Contas" />
<netui-template:section name="headerSection">
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
	<script type="text/javascript" language="javascript">
        getPesquisa = function() {
            if (validarPesquisa()) {
                new Ajax.Updater('containerPesquisa', 'getPesquisa.do', {
                    method: 'post',
                    evalScripts: true,
                    parameters: {
                        'gestorContasVO.filtros.tpOperacao': 'pesquisarGestor',
                        'gestorContasVO.filtros.nmGestor': $F('nmGestorFiltro').strip(),
                        'gestorContasVO.filtros.nrCPF': $F('nrCPFFiltro').gsub('[^0-9]', ''),
                        'gestorContasVO.filtros.nrConta': $F('nrContaFiltro').strip(),
                        'gestorContasVO.filtros.nrCNPJ': $F('nrCNPJFiltro').gsub('[^0-9]', '')
                    },
                    onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    }
                });
            }
        }

        validarPesquisa = function() {
            if($F('nmGestorFiltro').blank() && $F('nrCPFFiltro').blank() && $F('nrContaFiltro').blank() && $F('nrCNPJFiltro').blank()){
                alert('Por favor, informe ao menos um filtro para a pesquisa de gestores.');
                return false;

            } else if (!$F('nrCPFFiltro').blank() && !validaCPF($F('nrCPFFiltro'))) {
                alert('Por favor, informe um número de CPF válido.');
                window.setTimeout(function(){$('nrCPFFiltro').focus();$('nrCPFFiltro').select()},200);
                return false;

            } else if (!$F('nrCNPJFiltro').blank() && !validaCNPJ($F('nrCNPJFiltro'))) {
                alert('Por favor, informe um número de CNPJ válido.');
                window.setTimeout(function(){$('nrCNPJFiltro').focus();$('nrCNPJFiltro').select()},200);
                return false;
            }
            return true;
        }

        inserirAlterarGestor = function(nrCPF, tpGestor) {
            var titulo = (nrCPF) ? "Alteração" : "Inclusão";
            if(!nrCPF) nrCPF = "";
            createNewPopUp('manutencaoGestorContas', titulo + ' de Gestor de Contas', 750, 465, null, 'getDadosGestor.do?nrCPF='+nrCPF+'&tpGestor='+tpGestor, true);
            window.setTimeout("formataCampos()", 2000);
        }

        formataCampos = function(){
            $('nrCPF').focus();$('nrCPF').blur();
            $('nrTelComercial1').focus();$('nrTelComercial1').blur();
            $('nrTelComercial2').focus();$('nrTelComercial2').blur();
            $('nrTelCelular').focus();$('nrTelCelular').blur();
            $('nrCEP').focus();$('nrCEP').blur();
            $('nmPrimeiro').focus();
        }

        excluirGestor = function(nrCPF, tpGestor) {
            if (confirm('Deseja realmente remover o gestor de contas ?')) {
                new Ajax.Updater('containerPesquisa', 'excluirGestor.do', {
                    method: 'post',
                    evalScripts: true,
                    parameters: {
                        nrCPF: nrCPF.gsub('[^0-9]', ''),
                        tpGestor: tpGestor,
                        'gestorContasVO.filtros.tpOperacao': 'pesquisarGestor',
                        'gestorContasVO.filtros.nmGestor': $F('nmGestorFiltro').strip(),
                        'gestorContasVO.filtros.nrCPF': $F('nrCPFFiltro').gsub('[^0-9]', ''),
                        'gestorContasVO.filtros.nrConta': $F('nrContaFiltro').strip(),
                        'gestorContasVO.filtros.nrCNPJ': $F('nrCNPJFiltro').gsub('[^0-9]', '')
                    },
                    onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    },
                    onComplete: function(){
                        if(window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        alert('Exclusão realizada com sucesso.');
                    }, on503: function(e) {
                        createErrorPopUp('erroPortabilidade', e.statusText, e.responseText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            }
        }
    </script>
    <style type="text/css">
        #containerPesquisa { margin:3px; background:#f7f9fa; width:100%; height:395px; overflow:hidden; }
        input { width:110px; }
        select {width:320px;height:123px}
        .divFiltros { margin:3px 3px 5px 3px; padding:3px; width:100%; border:1px solid #ccc; background:#ededed; }
        #manutencaoGestorContasForm input { width:150px; }
        #divUltimaAlteracao { display:block; float:right; padding:3px; border:1px dotted #ccc; background:#fff; width:180px }
        #divUltimaAlteracao span {color:#1865c5;}
    </style>
</netui-template:section>
<netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="bodyRelatoriosTracking" height="470" width="790" label="Administração" operacoes="Manutenção de Gestor de Contas" scroll="N">
        <html:form styleId="gestorContasForm" method="post" action="getPesquisa.do" onsubmit="return false;">
        <div id="filtrosGestor" class="divFiltros">
            <table width="100%">
                <tr>
                    <td nowrap style="padding-right:10px;">Nome Gestor <html:text name="Form" property="gestorContasVO.filtros.nmGestor" styleId="nmGestorFiltro" /></td>
                    <td nowrap style="padding-right:10px;">CPF Gestor <html:text name="Form" property="gestorContasVO.filtros.nrCPF" styleId="nrCPFFiltro" style="width:85px;" onkeyup="checaCPF(this)" onblur="checaCPF(this)" maxlength="14" /></td>
                    <td nowrap style="padding-right:10px;">Conta Cliente <html:text name="Form" property="gestorContasVO.filtros.nrConta" styleId="nrContaFiltro" style="width:75px;" onkeyup="checaInteiro(this)" onblur="checaInteiro(this)" maxlength="100" /></td>
                    <td nowrap>CNPJ Cliente <html:text name="Form" property="gestorContasVO.filtros.nrCNPJ" styleId="nrCNPJFiltro" onkeyup="checaCNPJ(this)" onblur="checaCNPJ(this)" maxlength="18" /></td>
                    <td align="right" width="100%">
                        <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" border="0" onclick="getPesquisa();"/>
                    </td>
                </tr>
            </table>
        </div>
        <div id="containerPesquisa"></div>
        <div style="float:left;">
            <a href="/FrontOfficeWeb/index.jsp">
                <img style="margin:5px 0 0 5px;border:none;" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif"/>
            </a>
        </div>
        <div style="float:right">
            <img style="margin:5px 5px 0 0;border:none;cursor:pointer" onclick="inserirAlterarGestor();" src="<%=request.getContextPath()%>/resources/images/bt_incluir_nrml.gif"/>
        </div>
        </html:form>
    </vivo:sessao>

    <vivo:alert atributo="msgRetorno" scope="request" />

</netui-template:section>
</netui-template:template>