<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormSistema" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm"/>
<bean:define id="sistema"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm.sistema"/>
<bean:define id="itemSistema" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm.itemSistema"/>
<bean:define id="idSistema"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm.idSistema"/>
<bean:define id="dsSistema"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="sistemasForm.dsSistema"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Sistema"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>

    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
        <script language="javascript">
        <!--
        
        var up = true;
        function resizeFrameDetalhe(){
            var dynfrm = document.getElementById("ifrmAbas");
            var dyndiv = document.getElementById("areaUsuario");
            var objRef = document.getElementById("imgRefUp");
            var objDow = document.getElementById("imgRefDw");
            dyndiv.style.backgroundColor = '#e3ecf4';
            objRef.style.position = 'absolute';
            objDow.style.position = 'absolute';
            var novaTop   = objRef.offsetTop;
            var oldposdiv = objDow.offsetTop;
            objRef.style.position = '';
            objDow.style.position = '';
            if(up){
                dyndiv.style.position = 'absolute';
                dyndiv.style.top = novaTop;
                dyndiv.style.left= 0;
                oldtamfrm = dynfrm.height;
                oldlardiv = dyndiv.style.width;
                dynfrm.height = parseInt(oldtamfrm) + parseInt(oldposdiv) - parseInt(novaTop);
                dyndiv.style.height = dynfrm.height;
                up = false;
            }else{
                dyndiv.style.backgroundColor = '';
                dyndiv.style.left= 0;
                dynfrm.height = oldtamfrm;
                dyndiv.style.height = oldtamfrm;
                dyndiv.style.position = '';
                dyndiv.style.width = oldlardiv;
                up = true;
            }
        }
        

        function mostraJanelaDetalheEditar(){
            var x = document.getElementById("detalheEditar");
            x.style.visibility = 'visible';
            var b = document.getElementById("bg");
            b.style.visibility = 'visible';
        }
        
        function mostraJanelaDetalheInserir(){
            var x = document.getElementById("detalheInserir");
            x.style.visibility = 'visible';
            var b = document.getElementById("bg");
            b.style.visibility = 'visible';
        }
        
        
        function abreJanelaInserir(tela)
        {
            mostraJanelaDetalheInserir();
            divFrame = document.getElementById('frameDetalheInserir');
            if(tela.idPagina.value!="")
            {                                     
                divFrame.src = 'inserirItemMenu.do?idItemMenu=' +
                               tela.idItemMenu.value;
                               /* 
                               +
                               '&nmMenu=' +
                               tela.nmMenu.value + 
                               '&dsHint=' +
                               tela.dsHint.value + 
                               '&nrNivel=' +
                               tela.nrNivel.value + 
                               '&idItemMenuPai=' +
                               tela.idItemMenuPai.value + 
                               '&inVisibilidade=' +
                               tela.inVisibilidade.value + 
                               '&sqSequencia=' + 
                               tela.sqSequencia.value + 
                               '&idPagina=' + 
                               tela.idPagina.value + 
                               '&idSubSistema=' + 
                               tela.idSubSistema.value + 
                               '&nmPagina=' +
                               tela.nmPagina.value +
                               '&nmURL=' + 
                               tela.nmURL.value + 
                               '&dsSistema=' + 
                               tela.dsSistema.value + 
                               '&inFolha=' +
                               tela.inFolha.value;
                               */
                                 
            }else{
                divFrame.src = 'inserirItemMenu.do?idItemMenu=' +
                               tela.idItemMenu.value;
                               /*
                               +
                               '&nmMenu=' +
                               tela.nmMenu.value + 
                               '&dsHint=' +
                               tela.dsHint.value + 
                               '&nrNivel=' +
                               tela.nrNivel.value + 
                               '&idItemMenuPai=' +
                               tela.idItemMenuPai.value + 
                               '&inVisibilidade=' +
                               tela.inVisibilidade.value + 
                               '&sqSequencia=' + 
                               tela.sqSequencia.value +
                               '&dsSistema=' + 
                               tela.dsSistema.value +
                               '&inFolha=' + 
                               tela.inFolha.value; 
                               */
            }
        }
        
        function fechaJanela(){
             document.forms[0].action = "insereItem.do";
             document.forms[0].target = "_self";
             mostrar_div();
             document.forms[0].submit(); 
        }
        
        
        function abreJanelaEditar(tela)
        {
            mostraJanelaDetalheEditar()
            divFrame = document.getElementById('frameDetalheEditar');
            if(tela.idPagina.value!="")
            {
                divFrame.src = 'editarItemMenu.do?idItemMenu=' +
                               tela.idItemMenu.value;
                               /*
                               +
                               '&nmMenu=' +
                               tela.nmMenu.value + 
                               '&dsHint=' +
                               tela.dsHint.value + 
                               '&nrNivel=' +
                               tela.nrNivel.value + 
                               '&idItemMenuPai=' +
                               tela.idItemMenuPai.value + 
                               '&inVisibilidade=' +
                               tela.inVisibilidade.value + 
                               '&sqSequencia=' + 
                               tela.sqSequencia.value + 
                               '&idPagina=' + 
                               tela.idPagina.value + 
                               '&idSubSistema=' + 
                               tela.idSubSistema.value + 
                               '&nmPagina=' +
                               tela.nmPagina.value +
                               '&nmURL=' + 
                               tela.nmURL.value + 
                               '&dsSistema=' + 
                               tela.dsSistema.value +
                               '&inFolha=' +
                               tela.inFolha.value;
                               */
                                 
            }else{
                divFrame.src = 'editarItemMenu.do?idItemMenu=' +
                               tela.idItemMenu.value;
                               /*
                               +
                               '&nmMenu=' +
                               tela.nmMenu.value + 
                               '&dsHint=' +
                               tela.dsHint.value + 
                               '&nrNivel=' +
                               tela.nrNivel.value + 
                               '&idItemMenuPai=' +
                               tela.idItemMenuPai.value + 
                               '&inVisibilidade=' +
                               tela.inVisibilidade.value + 
                               '&sqSequencia=' + 
                               tela.sqSequencia.value +
                               '&dsSistema=' + 
                               tela.dsSistema.value + 
                               '&inFolha=' + 
                               tela.inFolha.value; 
                               */
            }
        }

        function fechaJanelaInserir(){
            var x = document.getElementById("detalheInserir");
            x.style.visibility = 'hidden';
            divFrame = document.getElementById('frameDetalheInserir');
            divFrame.src = '/FrontOfficeWeb/admsistemas/nada.html';
            var b = document.getElementById("bg");
            b.style.visibility = 'hidden';
        }
        
        function fechaJanelaEditar(){
            var x = document.getElementById("detalheEditar");
            x.style.visibility = 'hidden';
            divFrame = document.getElementById('frameDetalheEditar');
            divFrame.src = '/FrontOfficeWeb/admsistemas/nada.html';
            var b = document.getElementById("bg");
            b.style.visibility = 'hidden';
        }
        
        function initPagina() { }
        
        function CarregaAba(nome){
            mostrar_div();
            var pagina = ""; 
            if(nome=="bt01") pagina = "/FrontOfficeWeb/usuario/manterSistema/editarParSistema/relacionarServidor/begin.do?sistemaId=<%=idSistema%>&dsSistema=<%=dsSistema%>";
            if(nome=="bt02") pagina = "montaSubSistema.do?sistemaId=<%=idSistema%>&dsSistema=<%=dsSistema%>";
            if(nome=="bt03") pagina = "/FrontOfficeWeb/usuario/manterSistema/editarParSistema/manterSubSistema/begin.do?sistemaId=<%=idSistema%>&dsSistema=<%=dsSistema%>";
            if(nome=="bt04") pagina = "/FrontOfficeWeb/usuario/manterSistema/editarParSistema/manterPagina/begin.do?sistemaId=<%=idSistema%>&dsSistema=<%=dsSistema%>";
            if(nome=="bt05") pagina = "/FrontOfficeWeb/usuario/manterSistema/editarParSistema/manterUnidade/begin.do?sistemaId=<%=idSistema%>&dsSistema=<%=dsSistema%>";
            document.getElementById("ifrmAbas").src = pagina;
        }
    
        // envia para action Begin.
        function envia()
        {
            var form = document.forms[0];
            form.action = 'begin.do';
            mostrar_div();
            form.submit();
        }
        
        // -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            <%=request.getAttribute("aba")%>
        -->
        </SCRIPT>
        
        </netui-template:section>
        <netui-template:section name="bodySection">
        <acesso:controlHiddenItem nomeIdentificador="usu_eps_verpagina">
        
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <SCRIPT>mostrar_div();</SCRIPT>
    
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>        
        
        <!--APLICACAO-->
        <form name="begin.do" method="post" style="margin:0px;">
        <vivo:sessao id="qdMain" height="470" width="790" label="Sistema" operacoes="Editar Par&acirc;metros" scroll="no">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                    <table width="98%" border="0" cellspacing="1" cellpadding="1" align="center">
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="center" width="100%">
                                <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                                    <tr>
                                        <td class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="Nome do Sistema: " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td class="tblEstatica_campoValor" colspan="5">
                                            <bean:write name="FormSistema" property="sistema.dsSistema"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="URL Base: " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td class="tblEstatica_campoValor">
                                            <bean:write name="FormSistema" property="sistema.nmUrlBase"/>
                                        </td>
                                        <td class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="Acesso Controlado: " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td class="tblEstatica_campoValor">
                                            <logic:equal name="sistema" property="inAcessoControlado" value="0">
                                                Não
                                            </logic:equal>
                                            <logic:equal name="sistema" property="inAcessoControlado" value="1">
                                                Sim
                                            </logic:equal>
                                        </td>
                                        <td class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="Sigla: " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td class="tblEstatica_campoValor">
                                            <bean:write name="FormSistema" property="sistema.sgSistema"/>
                                        </td>
                                        
                                    </tr>
                                    <tr><td height="2"></td></tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    </td>
                </tr>            
            </table>
            <table><tr><td height="2"></td></tr></table>
            <table width="97%" cellpadding="0" cellspacing="0" align="center">
                <tr>
                    <td valign="top">
                        <vivo:abaGrupo id="btAba" width="760" height="10px" styleClass="abatexto">
                            <acesso:controlHiddenItem nomeIdentificador="usu_eps_rss"><vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);" value="Relacionar Sistema aos Servidores" select="n"/></acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="usu_eps_msubs"><vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);CarregaAba(this.id);" value="Manutenção de Subsistema"/></acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="usu_eps_mpag"><vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);CarregaAba(this.id);" value="Manutenção de Página"/></acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="usu_eps_muni"><vivo:abaItem id="bt05" onclick="abaSelected(btAba, bt05);CarregaAba(this.id);" value="Manutenção de Unidade"/></acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="usu_eps_mmenu"><vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Manutenção de Menu" select="n"/></acesso:controlHiddenItem>
                        </vivo:abaGrupo>
                    </td>
                </tr>
            </table>
            <center>
            <table width="760" cellpadding="0" cellspacing="5" class="tbl_bgGray" height="370">
                <tr>
                    <td valign="top">
                        <iframe id="ifrmAbas" name="ifrmAbas" width="100%" height="100%" frameborder="0" scrolling="no" src="/FrontOfficeWeb/admsistemas/nada.html"></iframe>
                    </td>
                </tr>
            </table>
            </center>
            <table>
                <tr>
                    <td align="left">
                        <img hspace="8" onClick="envia();" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" vspace="5" style="border:none;cursor:hand"/>
                    </td>
                </tr>
            </table>
        </vivo:sessao>
        <div id="detalheInserir" style="z-index:999 ;visibility: hidden; position:absolute; top:130; left:130; width:560; height:286; border-style:solid; border-width:1px; border-color:#adadad; background-color:#ededed;">
            <table width="560" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                <tr>
                    <td id="titleBar" style="cursor:move" width="545" height="21">Sistema > Cadastro de Menu</td>
                    <td width="64" valign="top" background="/FrontOfficeWeb/resources/images/window_topbtbg.gif">
                        <div align="right">
                            <img src="/FrontOfficeWeb/resources/images/window_fechar.gif" hspace="3" onClick="fechaJanelaInserir();">
                        </div>
                    </td>
                </tr>
            </table>
            <table width="440" cellpadding="0" cellspacing="5">
                <tr>
                    <td>
                        <iframe id="frameDetalheInserir" src="/FrontOfficeWeb/admsistemas/nada.html" frameborder="0" width="540" height="286"></iframe>
                    </td>
                </tr>
            </table>
        </div>
        <div id="detalheEditar" style="z-index:999 ;visibility: hidden; position:absolute; top:130; left:130; width:560; height:286; border-style:solid; border-width:1px; border-color:#adadad; background-color:#ededed;">
            <table width="560" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                <tr>
                    <td id="titleBar" style="cursor:move" width="545" height="21">Editar item do menu</td>
                    <td width="64" valign="top" background="/FrontOfficeWeb/resources/images/window_topbtbg.gif">
                        <div align="right">
                            <img src="/FrontOfficeWeb/resources/images/window_fechar.gif" hspace="3" onClick="fechaJanelaEditar();">
                        </div>
                    </td>
                </tr>
            </table>
            <table width="440" cellpadding="0" cellspacing="5">
                <tr>
                    <td>
                        <iframe id="frameDetalheEditar" src="/FrontOfficeWeb/admsistemas/nada.html" frameborder="0" width="540" height="286"></iframe>
                    </td>
                </tr>
            </table>
        </div>
            <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad;"></div>
        </form>
        
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
