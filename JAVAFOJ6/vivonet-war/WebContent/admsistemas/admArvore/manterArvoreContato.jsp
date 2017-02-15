<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administração de Árvore"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/esacStyle.css" type="text/css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/dtree.css" type="text/css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        
        <!-- link rel="StyleSheet" href="dtree.css" type="text/css" / -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/dtree.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
    </netui-template:section>

    <script>
        
        <!--
        function mostraJanelaDetalhe(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'visible';
            var b = document.getElementById("bg");
            b.style.visibility = 'visible';
        }
        
        
        function abreJanela(){
            mostraJanelaDetalhe()
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'criarEditarItemContato.jsp';
        }

        function fechaJanela(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'hidden';
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'nada.html';
            var b = document.getElementById("bg");
            b.style.visibility = 'hidden';
        }
        
        -->        
    </script>
    
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_mac_verpagina">

        <!--APLICACAO-->
        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuAdmSistemas.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>

        <vivo:sessao id="qdMain" height="473" width="790" label="Árvore de contatos" operacoes="Manutenç&atilde;o">
            <table>
                <tr>
                    <td>
                        <vivo:quadro width="383" height="50" id="grupo1" label="Manutenção da &Aacute;rvore de Contatos"><br>
                            <table width="100%" align="center">
                                <tr>
                                    <td align="center">
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mac_incluir">
                                        <img onclick="abreJanela();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" border="0" style="cursor:hand"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                    <td align="center">
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mac_editar">
                                        <img onclick="abreJanela();" src="/FrontOfficeWeb/resources/images/bt_editar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_editar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_editar_over.gif'" border="0" style="cursor:hand"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                    <td align="center">
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mac_apagar">
                                        <img onClick="location.href='/FrontOfficeWeb/admsistemas/nada.html'" src="/FrontOfficeWeb/resources/images/bt_apagar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_apagar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_apagar_over.gif'" border="0" style="cursor:hand"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                            </table>
                        </vivo:quadro>
                    </td>
                    <td>
                        <vivo:quadro width="383" height="50" id="grupo2" label="Manutenção de Parâmetros">
                             <table width="100%" height="100%" align="center">
                                <tr>
                                    <td align="center">
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mac_editardadosbasicos">
                                        <img onClick="location.href='/FrontOfficeWeb/admsistemas/admArvore/configArvoreContato.jsp'" src="/FrontOfficeWeb/resources/images/bt_eddadbasic_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_eddadbasic_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_eddadbasic_over.gif'" border="0" style="cursor:hand"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                    <td align="center">
                                    <acesso:controlHiddenItem nomeIdentificador="adm_mac_editarworkflow">
                                        <img onClick="location.href='/FrontOfficeWeb/admsistemas/admWorkflow/configArvoreWorkflow.jsp'" src="/FrontOfficeWeb/resources/images/bt_editarwflow_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_editarwflow_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_editarwflow_over.gif'" border="0" style="cursor:hand"/>
                                    </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                            </table>
                        </vivo:quadro>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div style="vertical-align:top; top:0px; left: 0px; height: 380px; width:770; padding: 5px; overflow: auto;">
                       
                                <script>
                                    if (document.getElementById) {
                                        var tree = new WebFXTree('Raiz');
                                        tree.setBehavior('classic');
                                        var a = new WebFXTreeItem('Reclamacao');
                                        var a1 = new WebFXTreeItem('Tecnica');
                                        var a2 = new WebFXTreeItem('Dificuldade na originacao de recebimentos');
                                        a2.add(new WebFXTreeItem('A COBRAR'));
                                        a2.add(new WebFXTreeItem('DDD'));
                                        a2.add(new WebFXTreeItem('DDI'));
                                        a2.add(new WebFXTreeItem('OSCILACAO DE ANALOGICO'));
                                        a1.add(a2);
                                        //a1.add(new WebFXTreeItem('Dificuldade no recebimento de chamadas'));
                                        //a1.add(new WebFXTreeItem('Desvio de chamadas - Reclamacao'));
                                        a.add(a1);
                                        tree.add(a);
                                        var b = new WebFXTreeItem('Informacao');
                                        var b1 = new WebFXTreeItem('Chamadas originadas');
                                        b.add(b1);
                                        tree.add(b);
                                        var c = new WebFXTreeItem('Servico');
                                        var c1 = new WebFXTreeItem('WAP');
                                        c1.add(new WebFXTreeItem('Assinatura'));
                                        c1.add(new WebFXTreeItem('Cancelamento'));
                                        var c2 = new WebFXTreeItem('Caixa postal');
                                        c2.add(new WebFXTreeItem('Troca de senha'));
                                        c2.add(new WebFXTreeItem('Assinatura'));
                                        var c3 = new WebFXTreeItem('Identificador');
                                        c3.add(new WebFXTreeItem('Cancelamento'));
                                        c3.add(new WebFXTreeItem('Assinatura'));
                                        c.add(c1);
                                        c.add(c2);
                                        c.add(c3);
                                        tree.add(c);
                                        var d = new WebFXTreeItem('Consulta');
                                        d.add(new WebFXTreeItem('Saldo'));
                                        tree.add(d);
                                        var e = new WebFXTreeItem('Transferencia');
                                        e.add(new WebFXTreeItem('Linha'));
                                        tree.add(e);
                                        document.write(tree);
                                    }
                                    
                                    
                                   
                                </script>
                        </div>
                    </td>
                </tr>
            </table>
        </vivo:sessao>
        
        <div id="detalhe" style="z-index:999 ;visibility: hidden; position:absolute; top:100; left:250; width:540; height:221; border-style:solid; border-width:1px; border-color:#adadad; background-color:#e3ecf4;">
            <table width="540" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                <tr>
                    <td id="titleBar" style="cursor:move" width="540" height="21">Manter Árvore de Contatos </td>
                    <td width="64" valign="top" background="/FrontOfficeWeb/resources/images/window_topbtbg.gif">
                        <div align="right">
                        <acesso:controlHiddenItem nomeIdentificador="adm_mac_fecharjanela">
                            <img src="/FrontOfficeWeb/resources/images/window_fechar.gif" hspace="3" onClick="fechaJanela();">
                        </acesso:controlHiddenItem>
                        </div>
                    </td>
                </tr>
            </table>
            <table width="530" cellpadding="0" cellspacing="5">
                <tr>
                    <td>
                        <iframe id="frameDetalhe" src="/FrontOfficeWeb/admsistemas/nada.html" frameborder="0" width="530" height="220"></iframe>
                    </td>
                </tr>
            </table>
        </div>
        <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad;"></div>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>

