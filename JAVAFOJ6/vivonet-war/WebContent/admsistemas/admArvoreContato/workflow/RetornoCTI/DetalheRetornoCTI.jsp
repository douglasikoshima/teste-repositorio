<!--
Módulo.....: Gestão de Processos
Caso de Uso: Gerenciamento de grupos para retorno no cti / Edição
Versão.....: $Author: a5113943 $ - $Revision: 1.7 $ - $Date: 2011/06/01 20:09:11 $
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="retornoCTIForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="retornoCTIForm"/>
<bean:define id="WFGrupoDisponivelVOArray" name="retornoCTIForm" property="retornoWFCTIVO.WFGrupoDisponivelVOArray" />
<bean:define id="WFGrupoSelecionadoVOArray" name="retornoCTIForm" property="retornoWFCTIVO.WFGrupoSelecionadoVOArray" />

   

<netui-template:template templatePage="./../../../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Workflow"/>
    <netui-template:setAttribute name="modulo" value="Administração Retorno CTI Detalhe"/>
    <netui-template:section name="headerSection"> 
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>

        <script language="javascript">
        
            var gruposConfigurados = false;
        
            //Controle da exibição
            function exibicaoAba(index) {
                //Seleciona Aba
                if( index == 0) {
                    abaSelected(btAbaConfiguracao, btDetalhe);
                } else if( index == 1) {
                    carregaGrupos();
                    abaSelected(btAbaConfiguracao, btGrupos);
                }

                //Controla a exibição
                trDetalhe.style.display = (index == 0 ? "" : "none");
                trGrupos.style.display  = (index == 1 ? "" : "none");
            }

            function carregaGrupos() {
                //Liga animação
                if( top.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                
                gruposConfigurados = true;
                
                //Processa a pesquisa
                document.forms["retornoCTIForm"].target = "ifrmLoadData";
                document.forms["retornoCTIForm"].action = "carregaGrupos.do";
                document.forms["retornoCTIForm"].submit();                
            }

            
            function confirmar() {
            
                if (!gruposConfigurados) {
                    alert("Grupos não foram configurados!\nClique na aba Grupos para configura-los!");
                    return false;
                }
            
                //Verifica as entradas
                if( document.forms["retornoCTIForm"].elements["retornoWFCTIVO.sgRetornoWFCTI"].value.replace(" ", "").length == 0 ) {
                    document.forms["retornoCTIForm"].elements["retornoWFCTIVO.sgRetornoWFCTI"].focus();
                    alert("Sigla informada não é valida!");
                    return;
                }
                
                if( document.forms["retornoCTIForm"].elements["retornoWFCTIVO.dsRetornoWFCTI"].value.replace(" ", "").length == 0 ) {
                    document.forms["retornoCTIForm"].elements["retornoWFCTIVO.dsRetornoWFCTI"].focus();
                    alert("Descrição informada não é valida!");
                    return;
                }

                if( (document.forms["retornoCTIForm"].elements["retornoWFCTIVO.sgStatus"].value.replace(" ", "").length == 0) ||
                    (document.forms["retornoCTIForm"].elements["retornoWFCTIVO.sgStatus"].value == "S") ) {
                    document.forms["retornoCTIForm"].elements["retornoWFCTIVO.sgStatus"].focus();
                    alert("Selecione o status para continuar!");
                    return;
                }
                
                //Abilita o codigo para a postagem
                document.forms["retornoCTIForm"].elements["retornoWFCTIVO.idRetornoWFCTI"].disabled = false;

                //Monta a seleção nos disponíveis e selecionados
                selectOptions(document.forms["retornoCTIForm"].elements["idGruposDisponiveis"]);
                selectOptions(document.forms["retornoCTIForm"].elements["idGruposSelecionados"]);
                
                //Processa o retorno
                document.forms["retornoCTIForm"].target = "";
                document.forms["retornoCTIForm"].action = "iniciarMetodo.do";
                document.forms["retornoCTIForm"].submit();
            }
            
            function voltar() {
                //Liga animação
                if( top.dvAnimarAguarde != null ) top.startAnimation();
                
                //Metodo de pesquisa
                document.forms["retornoCTIForm"].elements["method"].value = <%=ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_VOLTAR%>;
            
                //Processa a pesquisa
                document.forms["retornoCTIForm"].target = "";
                document.forms["retornoCTIForm"].action = "iniciarMetodo.do";
                document.forms["retornoCTIForm"].submit();
            }
            
            function transfereSelecaoLista(listaOrigem, listaDestino) {
                var i;
                
                for(i = 0; i<listaOrigem.options.length; i++)
                    if(listaOrigem.options[i].selected)
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        
                for(i = listaOrigem.options.length-1; i>=0; i--)
                    if(listaOrigem.options[i].selected)
                        listaOrigem.options[i] = null;

                return false;
            }
        
            function transfereSelecaoListaTodos(listaOrigem, listaDestino) {
                var i;
                for(i = 0; i<listaOrigem.options.length; i++)
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    
                for(i = listaOrigem.options.length-1; i>=0; i--)
                    listaOrigem.options[i] = null;
                    
                return false;
            }

            function selectOptions(selectElement) {
                for( x = 0; x < selectElement.options.length; x++ )
                    selectElement.options[x].selected = true;
            }

        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_drct_verpagina">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        
        <!--APLICACAO-->
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>

        <vivo:quadro id="qdMain" height="480" width="790" label="&nbsp;Vivo Net >> Workflow >> Administração Retorno CTI Detalhe" scroll="N">
            <html:form styleId="retornoCTIForm" action="/admsistemas/admArvoreContato/workflow/RetornoCTI/iniciarMetodo.do" method="post">
                 <html:hidden name="retornoCTIForm" property="method"/>
                
                <!--CONTROLE ABAS-->
                <vivo:abaGrupo id="btAbaConfiguracao" width="780px" height="10px" styleClass="abatexto">
                <acesso:controlHiddenItem nomeIdentificador="adm_drct_abadet">
                    <vivo:abaItem id="btDetalhe" onclick="exibicaoAba(0);" value="Detalhe" select="S"/>
                </acesso:controlHiddenItem>
                <acesso:controlHiddenItem nomeIdentificador="adm_drct_abagrp">
                    <vivo:abaItem id="btGrupos"  onclick="exibicaoAba(1);" value="Grupos"/>
                </acesso:controlHiddenItem>
                </vivo:abaGrupo>

                <!--ABAS-->
                <table width="100%" cellpadding="0" cellspacing="0" border="1">
                    <!--DETALHE-->
                    <tr id="trDetalhe" name="trDetalhe">
                        <td>
                            <vivo:quadro id="qdDetalhe" height="390" width="775" scroll="N">
                                <table width="100%" cellpadding="2" cellspacing="2" border="0">
                                    <tr>
                                        <td>Código: </td>
                                        <td><html:text name="retornoCTIForm" property="retornoWFCTIVO.idRetornoWFCTI" size="20" maxlength="10" disabled="true" onkeyup ="Formatar(this.value, this.form.name, this.name, 'numero');" onchange="Formatar(this.value, this.form.name, this.name, 'numero');" /></td>
                                    </tr>
                                    <tr>
                                        <td>Sigla: </td>
                                        <td><html:text name="retornoCTIForm" property="retornoWFCTIVO.sgRetornoWFCTI" size="5" maxlength="3"/></td>
                                    </tr>
                                    <tr>
                                        <td>Descrição: </td>
                                        <td><html:text name="retornoCTIForm" property="retornoWFCTIVO.dsRetornoWFCTI" size="60" maxlength="60"/></td>
                                    </tr>
                                    <tr>
                                        <td>Status: </td>
                                        <td>
                                            <html:select name="retornoCTIForm" property="retornoWFCTIVO.sgStatus" style="width=100px">
                                                <option value="S" selected>Selecione</option>
                                                <option value="A">Ativo</option>
                                                <option value="C">Cancelado</option>
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Padrão: </td>
                                        <td><html:checkbox name="retornoCTIForm" property="retornoWFCTIVO.inPadrao" styleClass="checkbox"/></td>
                                    </tr>
                                </table>
                            </vivo:quadro>
                        </td>
                    </tr>
                    
                    <!--GRUPOS-->
                    <tr id="trGrupos" name="trGrupos" style="display:none">
                        <td>
                            <vivo:quadro id="qdGrupos" height="390" width="775" scroll="N">
                                <table border="0" width="100%">
                                    <tr align="center">
                                        <td>
                                            Disponivel<br>
                                            <html:select name="retornoCTIForm" property="idGruposDisponiveis" multiple="true" size="10" style="width=340px;height=360px;" ondblclick="btSegmentacao01.click();">
                                                <html:options collection="WFGrupoDisponivelVOArray" property="idGrupo" labelProperty="dsGrupo"/>
                                            </html:select>
                                        </td>
                                        <td>
                                            <vivo:botao id="btSegmentacao01" width="25px" height="20px" value=">" styleClass="btTemplate"  onclick="transfereSelecaoLista(document.retornoCTIForm.idGruposDisponiveis, document.retornoCTIForm.idGruposSelecionados);return false;"/>
                                            <vivo:botao id="btSegmentacao02" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.retornoCTIForm.idGruposDisponiveis, document.retornoCTIForm.idGruposSelecionados);return false;"/>
                                            <vivo:botao id="btSegmentacao03" width="25px" height="20px" value="<" styleClass="btTemplate"  onclick="transfereSelecaoLista(document.retornoCTIForm.idGruposSelecionados, document.retornoCTIForm.idGruposDisponiveis);return false;"/>
                                            <vivo:botao id="btSegmentacao04" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.retornoCTIForm.idGruposSelecionados, document.retornoCTIForm.idGruposDisponiveis);return false;"/>
                                        </td>
                                        <td>
                                            Associada<br>                    
                                            <html:select name="retornoCTIForm" property="idGruposSelecionados"  multiple="true" size="10" style="width=340px;height=360px;" ondblclick="btSegmentacao03.click();">
                                                <html:options collection="WFGrupoSelecionadoVOArray" property="idGrupo" labelProperty="dsGrupo"/>
                                            </html:select>
                                        </td>
                                    </tr>
                                </table>
                            </vivo:quadro>
                        </td>
                    </tr>
                </table>

                <vivo:quadro id="qdAcao" height="30" width="780" label="&nbsp;Ação" scroll="N">
                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                        <tr align="center">
                            <td align="left">
                                <img hspace="10" vspace="6" id="btGravar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" style="border:none;" onClick="voltar(); return false"/>                            </td>
                            <td align="right">
                                <acesso:controlHiddenItem nomeIdentificador="adm_drct_gravar">
                                    <img hspace="10" vspace="6" id="btGravar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none;" onClick="confirmar(); return false"/>
                                </acesso:controlHiddenItem>
                            </td>
                        </tr>
                </vivo:quadro>
            </html:form>
        </vivo:quadro>
        
        <iframe id="ifrmLoadData" name="ifrmLoadData" width="0px" height="0px" style="visibility:hidden"></iframe>
        
        <script language="javascript">
            
             //Monta o check padrao se necessario
            <logic:equal name="retornoCTIForm" property="retornoWFCTIVO.inPadrao" value="1">
            document.forms["retornoCTIForm"].elements["retornoWFCTIVO.inPadrao"].checked = true;
            </logic:equal>

            //Identificação do procedimento corrente
            var method;
            //Se branco é inclusão
            if( document.forms["retornoCTIForm"].elements["retornoWFCTIVO.idRetornoWFCTI"].value.replace(" ", "").length == 0 )
                method = <%=ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_NOVO_GRAVAR%>;
                
            //Se não branco é alteração
            else
                method = <%=ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_ALTERAR_GRAVAR%>;

            //Metodo de pesquisa
            document.forms["retornoCTIForm"].elements["method"].value = method;
            
            //Monta o combo status
            document.forms["retornoCTIForm"].elements["retornoWFCTIVO.sgStatus"].value = "<bean:write name="retornoCTIForm" property="retornoWFCTIVO.sgStatus"/>";
            
            //Fim animação
            if( top.dvAnimarAguarde != null ) top.stopAnimation();
            
            //Inicializa vizualiando os detalhes
            exibicaoAba(0);
            
            //Foca na sigla
            document.forms["retornoCTIForm"].elements["retornoWFCTIVO.sgRetornoWFCTI"].focus();

            //Verifica erros do tuxedo para apresentação
            <logic:notEmpty name="retornoCTIForm" property="errorMessage">
                //Controle da tread de apresentação da informação
                function informarErro() {
                    //Limpa a tread se necessario
                    window.clearInterval(treadRefresh);

                    //Apresenta mensagem de erro
                    alert("<bean:write name="retornoCTIForm" property="errorMessage"/>");
                }
                
                //Inicializa o refresh automático
                var treadRefresh = window.setInterval("informarErro()", 1000);
            </logic:notEmpty>
            
        </script>

       
        </acesso:controlHiddenItem>
        
           
        
    </netui-template:section>
</netui-template:template>
