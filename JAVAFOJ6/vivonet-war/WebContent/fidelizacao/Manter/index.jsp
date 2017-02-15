<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
        <!-- Script para criacao da abas -->
        <script>
            <!--
                var aba = new Abas();
                var abaAparelho;
                var abaChip;
                var abaDestino;
                var abaIntencao;
                var abaOfertas;
                var abaBonus;
                var abaSair;

                function desabilitaSubmit(){
                    aba.desabilitaAbas();
                }

                function habilitaSubmit(){
                    aba.habilitaAbas();
                }

                function initPagina(){
                    top.frameApp.mostrar_div();
                    <acesso:controlHiddenItem nomeIdentificador="fid_idxmfid_abaintenc">
                        abaIntencao = aba.criaAba("Definição de Intenção", "/FrontOfficeWeb/fidelizacao/Manter/ManterDefIntencao/begin.do?operacao=1");
                        document.getElementById(abaIntencao).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_idxmfid_abadestino">
                        abaDestino = aba.criaAba("Definição de Destinos", "/FrontOfficeWeb/fidelizacao/Manter/ManterDefIntencao/begin.do?operacao=2");
                        document.getElementById(abaDestino).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");
                    </acesso:controlHiddenItem>
                    //abaOfertas = aba.criaAba("Ofertas","/FrontOfficeWeb/fidelizacao/Manter/ManterOfertas/begin.do");
                    <acesso:controlHiddenItem nomeIdentificador="fid_idxmfid_abaplano">
                        abaPlano = aba.criaAba("Planos", "/FrontOfficeWeb/fidelizacao/Manter/ManterPlanos/begin.do");
                        document.getElementById(abaPlano).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_idxmfid_ababonus">
                        abaBonus = aba.criaAba("Bônus", "/FrontOfficeWeb/fidelizacao/Manter/ManterBonus/begin.do");
                        document.getElementById(abaBonus).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_idxmfid_abaparelho">
                        abaAparelho = aba.criaAba("Aparelhos", "/FrontOfficeWeb/fidelizacao/Manter/ManterAparelho/begin.do");
                        document.getElementById(abaAparelho).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");
                    </acesso:controlHiddenItem>

                    <acesso:controlHiddenItem nomeIdentificador="fid_idxmfid_abachip">
                        abaChip = aba.criaAba("Chip", "/FrontOfficeWeb/fidelizacao/Manter/ManterChip/begin.do");
                        document.getElementById(abaChip).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");
                    </acesso:controlHiddenItem>

                    <acesso:controlHiddenItem nomeIdentificador="fid_idxmfid_abaintenc">
                        aba.selecionaAba(document.getElementById(abaIntencao), true);
                    </acesso:controlHiddenItem>
                    aba.habilitaAbas();
                }

                function selecionaAbaAparelho(){
                    aba.selecionaAba(document.getElementById(abaAparelho), false);
                }

                function selecionaAbaChip(){
                    aba.selecionaAba(document.getElementById(abaChip), false);
                }

                function selecionaAbaDestino(){
                    aba.selecionaAba(document.getElementById(abaDestino), false);
                }

                function selecionaAbaMensagens(){
                    aba.selecionaAba(document.getElementById(abaMensagens), false);
                }

                function selecionaAbaIntencao(){
                    aba.selecionaAba(document.getElementById(abaIntencao), false);
                }

                function selecionaAbaOferta(){
                    aba.selecionaAba(document.getElementById(abaOferta), false);
                }

                function selecionaAbaBonus(){
                    aba.selecionaAba(document.getElementById(abaBonus), false);
                }

                function fecharPlanos(lPesquisa){
                    document.getElementById('ifrmManPlanos').src = 'about:blank';
                    document.getElementById('dvManPlanos').style.display = 'none';
                    if(lPesquisa){
                        top.frameApp.fraAbas.pesquisarPlanos();
                    }
                }

                function fecharBonus(lPesquisa){
                    document.getElementById('ifrmManBonus').src = 'about:blank';
                    document.getElementById('dvManBonus').style.display = 'none';
                    if(lPesquisa){
                        top.frameApp.fraAbas.pesquisar();
                    }
                }
            -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <acesso:controlHiddenItem nomeIdentificador="fid_idxmfid_verpagina">
            <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <vivo:sessao id="manutencao" width="790" height="480" label="Fidelização" operacoes="Manutenção" scroll="no">
                <table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td height="2"></td>
                    </tr>
                </table>
                <table border="0" cellspacing="0" cellpadding="0">
                    <tr id="trAbas">
                </table>
                <table width="780" height="445" cellpadding="0" cellspacing="0" class="BorderTable1">
                    <tr>
                        <td valign="top" class="tbl_bgGray">
                            <iframe id=fraAbas width="777" height="420" frameborder=0 scrolling=no src="about:blank"></iframe>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <img src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" border="0" onmouseup="window.location.href='sair.do';" />
                        </td>
                    </tr>
                </table>
                <script language="javaScript">
                    initPagina();
                </script>
            </vivo:sessao>
            <vivo:quadroFlutuante id="dvManPlanos" idIframe="ifrmManPlanos" height="417" width="589" spacesTop="105" spacesLeft="100" display="none" url="<%=request.getContextPath()%>" label="Fidelização" onclick="fecharPlanos(false);"/>
            <vivo:quadroFlutuante id="dvManBonus"  idIframe="ifrmManBonus"  height="552" width="590" spacesTop="12"  spacesLeft="115" display="none" url="<%=request.getContextPath()%>" label="Fidelização" onclick="fecharBonus(false);"/>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>