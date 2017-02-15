<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administração de Workflow"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <script language="javascript" >
        <!--
        var up = true;
        function resizeFrameDetalhe(){
            var dynfrm = document.getElementById("ifrmAbas");
            var dyndiv = document.getElementById("areaWorkflow");
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
        

        function mostraJanelaDetalhe(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'visible';
            var b = document.getElementById("bg");
            b.style.visibility = 'visible';
        }
        
        
        function abreJanela(){
            mostraJanelaDetalhe()
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'criarEditarItemBaixa.jsp';

        }
        
        function fechaJanela(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'hidden';
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'nada.html';
            var b = document.getElementById("bg");
            b.style.visibility = 'hidden';
        }
        
        function CarregaAba(nome){
            var pagina = "";
            if(nome=="bt01") pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/abaDadosBasicosW/begin.do?idContato=<%=request.getParameter("idContato")%>";
            if(nome=="bt02"){
                var ds = escape('<%=request.getParameter("dsPath").replaceAll("\\\\","\\\\\\\\")%>');
                pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/ConfigGruposProcessosSequencia/begin.do?idContato=<%=request.getParameter("idContato")%>&dsPath="+ds;
            }
            if(nome=="bt03") pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/abaCamposForm/begin.do?idContato=<%=request.getParameter("idContato")%>";
            if(nome=="bt04") pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/abaEntrevista/begin.do?idContato=<%=request.getParameter("idContato")%>";
            if(nome=="bt05") pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/abaQuestionario/begin.do?idContato=<%=request.getParameter("idContato")%>";
            if(nome=="bt06") pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/abaRetorno/begin.do?idContato=<%=request.getParameter("idContato")%>";
            if(nome=="bt07") pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/ConfigGruposTratamentoNiveis/begin.do?idContato=<%=request.getParameter("idContato")%>";            
            if(nome=="bt08") pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/PerfilGrupoContatoCRI/begin.do?idContato=<%=request.getParameter("idContato")%>";
            if(nome=="bt09") pagina = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/abaAssocGrupoContato/begin.do?idContato=<%=request.getParameter("idContato")%>";
            document.getElementById("ifrmAbas").src = pagina;
            top.frameApp.mostrar_div();            
        }


        function voltar() {
            top.frameApp.mostrar_div();            
            window.location.href='/FrontOfficeWeb/admsistemas/admArvoreContato/begin.do';        
            //document.forms[0].action = '/FrontOfficeWeb/admsistemas/admArvoreContato/begin.do';
            //top.frameApp.mostrar_div();
            //document.forms[0].submit();
        }

        // -->
        
        function fecharIframe()
        {
           //CarregaAba('bt07');
           // top.frameApp.mostrar_div();
           return false;

        }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            CarregaAba('bt02');
            top.frameApp.mostrar_div();
        -->
        </SCRIPT>

    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_caw_verpagina">

        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
                
        <!--APLICACAO-->

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4"></div>

        <vivo:sessao id="qdMain" height="470" width="790" label="Árvore de Contatos" operacoes="Manuten&ccedil;&atilde;o de Workflow" scroll="no">
            <form name="myForm" action="">
            <table width="98%" cellpadding="1" cellspacing="8" align="center">
                <tr>
                    <td colspan="2" valign="top">
                        <table width="100%" border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray">
                        <tr>
                            <td class="tblEstatica_campoNome" align="left" width="20%">&nbsp;
                                <netui:label value="Item Selecionado:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td class="tblEstatica_campoValor" align="left" width="80%">&nbsp;
                                <netui:label value='<%=request.getParameter("nmContato")%>'/>
                            </td>
                        <tr>
                        </table>
                    </td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="0" width="757" align="center">
                <tr>
                    <td valign="top">
                      <vivo:abaGrupo id="btAba" width="757" height="10" styleClass="abatexto">
                        <acesso:controlHiddenItem nomeIdentificador="adm_caw_carregarabafasesequencia">
                          <vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Fase Seqüencia" select="S"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="adm_caw_carregarabafaseniveis">
                          <vivo:abaItem id="bt07" onclick="abaSelected(btAba, bt07);CarregaAba(this.id);" value="Fase Níveis"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="adm_caw_carregarabaformulario">
                          <vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);CarregaAba(this.id);" value="Formulário"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="adm_caw_carregarabaentrevista">
                          <vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);CarregaAba(this.id);" value="Entrevista"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="adm_caw_carregarabapesquisa">
                          <vivo:abaItem id="bt05" onclick="abaSelected(btAba, bt05);CarregaAba(this.id);" value="Pesquisa"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="adm_caw_carregarabaretorno">
                          <vivo:abaItem id="bt06" onclick="abaSelected(btAba, bt06);CarregaAba(this.id);" value="Retorno"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="adm_caw_aba_prefilcri">
                          <vivo:abaItem id="bt08" onclick="abaSelected(btAba, bt08);CarregaAba(this.id);" value="Perfil CRI"/>
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="adm_caw_carregar_aba_assocgrupo">
                          <vivo:abaItem id="bt09" onclick="abaSelected(btAba, bt09);CarregaAba(this.id);" value="Grupo C. Prévio"/>
                        </acesso:controlHiddenItem>
                      </vivo:abaGrupo>
                    </td>
                </tr>
            </table>
            <table width="97%" cellpadding="0" cellspacing="0" align="center" style="border:1px solid #adadad;">
                <tr>
                    <td valign="top">
                        <IFRAME  id="ifrmAbas" name="ifrmAbas" WIDTH="100%" HEIGHT="380" FRAMEBORDER=0 SCROLLING=no SRC="/FrontOfficeWeb/admsistemas/nada.html"></IFRAME>
                    </td>
                </tr>
            </table>
            <div style="height: 5px">
            </div>
            <img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="voltar(); return false" style="border:none;cursor:hand;" hspace="5" vspace="0"/>
            </form>
            
        </vivo:sessao>

        <div id="detalhe" style="z-index:999 ;visibility: hidden; position:absolute; top:60; left:260; width:450; height:530; border-style:solid; border-width:1px; border-color:#adadad; background-color:#ededed;">
            <table width="450" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                <tr>
                    <td id="titleBar" style="cursor:move" width="450" height="21">Cadastrar nó da árvore de baixa</td>
                    <td width="64" valign="top" background="/FrontOfficeWeb/resources/images/window_topbtbg.gif">
                        <div align="right">
                            <img src="/FrontOfficeWeb/resources/images/window_fechar.gif" hspace="3" onClick="fechaJanela();">
                        </div>
                    </td>
                </tr>
            </table>
            <table width="440" cellpadding="0" cellspacing="5">
                <tr>
                    <td>
                        <iframe id="frameDetalhe" src="/FrontOfficeWeb/admsistemas/nada.html" frameborder="0" width="440" height="467"></iframe>
                    </td>
                </tr>
            </table>
        </div>
        <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad;"></div>

        <vivo:quadroFlutuante label="Regra de Encaminhamento" scroll="false" src="" idIframe="ifrmLupa" id="divLupa" spacesLeft="2" height="520" spacesTop="40" url="<%=request.getContextPath()%>" display="none" width="795"></vivo:quadroFlutuante>
        <vivo:quadroFlutuante id="dvGrupoAssociado" idIframe="ifrmGrupoAssociado" width="760" height="400" spacesTop="120" spacesLeft="20" display="none" url="<%=request.getContextPath()%>" label="Associação de Grupo"/>   

    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>