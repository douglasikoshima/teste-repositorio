<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administração de Workflow"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/dtree.css" type="text/css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <!-- link rel="StyleSheet" href="dtree.css" type="text/css" / -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/dtree.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script language="javascript">
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

        function initPagina() { }
                

        // -->
        </script>
    </netui-template:section>

    <netui-template:section name="bodySection">

        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuAdmSistemas.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
                
        <!--APLICACAO-->
        <vivo:body idTable="tbMain" idDiv="dvMain" height="500" width="790">
            <vivo:quadro id="qdMain" height="470" width="780" label="Manter Workflow da Árvore de Contatos">
            <form id="frmSelecao" name="frmSelecao" method="post"/></form>
            <div align="center"><img id="imgRefUp" src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
                <table width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <td valign="top">
                            <table width="97%" border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray" align="center">
                            <jsp:include page="/admsistemas/admArvore/arvoreContato.jsp" />
                            </table>
                        </td>
                    </tr>
                </table>
                <div align="center"><img id="imgRefDw" src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="4"></div>
                <div id="areaWorkflow" style="width:100%;">
                    <table border="0" cellspacing="0" cellpadding="0" width="97%" align="center">
                        <tr>
                            <td valign="top">
                                <vivo:abaGrupo id="btAba" width="40%" height="10px" styleClass="abatexto">
                                    <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);selecionarPesquisa();"  value="Pesquisa" select="S"/>
                                    <vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);selecionarFormulario();" value="Formulario"/>
                                    <vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);selecionarEntrevista();" value="Entrevista"/>
                                    <vivo:abaItem id="bt05" onclick="abaSelected(btAba, bt05);selecionarProcessos();" value="Processos"/>
                                </vivo:abaGrupo>
                            </td>
                            <td background="<%=request.getContextPath()%>/resources/images/aba_bg_line.gif" height="16" valign="top" align="right">
                                <img src="<%=request.getContextPath()%>/resources/images/bt_lupa_aba.gif" onclick="resizeFrameDetalhe();" style="cursor:hand">
                            </td>
                        </tr>
                    </table>
                    <table width="97%" cellpadding="1" cellspacing="5" class="tbl_bgGray" align="center">
                        <tr>
                            <td valign="top">
                                <IFRAME id="ifrmAbas" name="ifrmAbas" WIDTH="100%" HEIGHT="200" FRAMEBORDER=0 SCROLLING=auto SRC="vincularQuestionario.jsp"></IFRAME>
                            </td>
                        </tr>
                    </table>
                </div>
                <div align="center"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="4"></div>
            </vivo:quadro>
        </vivo:body>
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
    <script language="Javascript">
    <!--Template->SCRIPT CABECALHO-->
    
        //Configura o processamento
        document.forms["frmSelecao"].target = "ifrmAbas";
    
        function selecionarPesquisa() {
            document.forms["frmSelecao"].action = "vincularQuestionario.jsp";
            document.forms["frmSelecao"].submit();
        }
        
        function selecionarFormulario() {
            document.forms["frmSelecao"].action = "vincularCamposForm.jsp";
            document.forms["frmSelecao"].submit();
        }
        
        function selecionarEntrevista() {
            document.forms["frmSelecao"].action = "vincularEntrevista.jsp";
            document.forms["frmSelecao"].submit();
        }
                
        function selecionarProcessos() {
            document.forms["frmSelecao"].action = "/FrontOfficeWeb/admsistemas/admArvoreContato/workflow/ConfigGruposProcessosSequencia/begin.do";
            document.forms["frmSelecao"].submit();
        }
        
    </script>
    </netui-template:section>
</netui-template:template>