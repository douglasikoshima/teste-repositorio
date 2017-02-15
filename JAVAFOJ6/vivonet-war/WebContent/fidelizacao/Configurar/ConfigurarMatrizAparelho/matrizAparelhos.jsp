<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/abas.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language=javascript>
            <!--
                var aba = new Abas();
                var abaAparelho;
                var abaFiltro;

                function desabilitaSubmit(){
                    aba.desabilitaAbas();
                }

                function habilitaSubmit(){
                    aba.habilitaAbas();
                }

                function initPagina(){
                    top.frameApp.mostrar_div();

                    abaFiltro   = aba.criaAba("Filtro de Pesquisa", "/FrontOfficeWeb/fidelizacao/Configurar/ConfigurarMatrizAparelho/abaFiltro.do");
                    document.getElementById(abaFiltro).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");

                    abaAparelho = aba.criaAba("Aparelhos", "/FrontOfficeWeb/fidelizacao/Configurar/ConfigurarMatrizAparelho/pesquisar.do");
                    document.getElementById(abaAparelho).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");

                    aba.selecionaAba(document.getElementById(abaFiltro), true);
                }

                function selecionaAbaAparelho(){
                    aba.selecionaAba(document.getElementById(abaAparelho), false);
                }

                function selecionaAbaFiltro(){
                    aba.selecionaAba(document.getElementById(abaFiltro), false);
                }

                function matrizOferta() {
                    top.frameApp.mostrar_div();
                    dvAparelho.style.display = 'block';
                    document.forms[0].target = "ifrmAparelho";
                    document.forms[0].action="montarArvore.do?acao=matrizAparelho";
                    document.forms[0].submit();
                }

                function validarCombos(){
                    msg = "";
                    if(document.forms[0].elements["regionalSel"].options[document.forms[0].elements["regionalSel"].selectedIndex].value==""){
                        msg+= "- Regional\n";
                    }
                    if(document.forms[0].elements["tipoClienteSel"].options[document.forms[0].elements["tipoClienteSel"].selectedIndex].value==""){
                        msg+= "- Tipo de Cliente\n";
                    }
                    if(document.forms[0].elements["idGrupo"].options[document.forms[0].elements["idGrupo"].selectedIndex].value==""){
                        msg+= "- Grupo\n";
                    }
                    if(document.forms[0].elements["segmentacaoSel"].options[document.forms[0].elements["segmentacaoSel"].selectedIndex].value==""){
                        msg+= "- Segmentação\n";
                    }
                    if(msg!=""){
                        alert("Por favor selecione:\n" + msg);
                        return false;
                    }
                    return true;
                }

                function actionMatrizAparelhoCon(){
                    if(validarCombos()){
                        top.frameApp.mostrar_div();
                        document.forms[0].action = "ActionMatrizAparelho.do?acao=consultar";
                        document.forms[0].submit();
                    }
                }

                function cerrar(){
                    document.forms[0].elements["regionalSel"].style.visibility = "visible";
                    document.forms[0].elements["tipoClienteSel"].style.visibility = "visible";
                    dvAparelho.style.display = 'none';
                    document.forms[0].target = '';
                    document.forms[0].method = "POST";
                    document.forms[0].action = "VuelveConsMatriz.do";
                    document.forms[0].submit();
                }

                function limparCampos(){
                    deleteFixoTodos(document.forms[0].aparelhosDispSelecionados);
                    deleteFixoTodos(document.forms[0].aparelhosSelecionados);
                }
            -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                <logic:notEmpty name="msgError" scope="request">
                alert('<bean:write name="msgError" scope="request"/>');
                </logic:notEmpty>
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                desabilitaSubmit();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_map_verpagina">
    <form method="post" action="" id="formMatrizAparelho" name="formMatrizAparelho" enctype="multipart/form-data" style="margin:0px;">
        <table border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;margin-left:10px;">
            <tr id="trAbas">
        </table>
        <table width="760" height="390" cellpadding="0" cellspacing="0" class="BorderTable1" style="margin-left:10px;">
            <tr>
                <td valign="top" class="tbl_bgGray">
                    <iframe id="fraAbas" width="760" height="390" frameborder="0" scrolling="no" src="about:blank"></iframe>
                </td>
            </tr>
        </table>
        <script language="javaScript">
            initPagina();
        </script>
        <%--table width="730">
            <tr>
                <td>
                <table width="100%">
                    <tr valign="top">
                        <td align="right">
                            <acesso:controlHiddenItem nomeIdentificador="fid_map_consultarmatriz">
                                <img src="/FrontOfficeWeb/resources/images/bt_consmat_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_consmat_over.gif" style="border:none;" onClick="matrizOferta(); return false"/>
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="fid_map_gravar">
                                <img  id="btRightSimp" onclick="salvar(this); return false;" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
        </table--%>
    </form>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
    <vivo:quadroFlutuante onclick="cerrar();" id="dvAparelho" idIframe="ifrmAparelho" width="767" height="389" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Consulta à Matriz de Aparelho" />
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
