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
        <script for="window" event="onLoad" language="JScript">
        <!--
            CarregaAba('bt01');
        -->
        </script>
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_iaa_verpagina">
    <!-- Script para criacao da abas -->
    <script>

        var aba = new Abas();
        var abaDados;
        var abaTipos;
        var abaOperadoras;
        var abaLink
        var up = true;
        
        function desabilitaSubmit()
        {
            aba.desabilitaAbas();
        }
        
        function habilitaSubmit()
        {
            aba.habilitaAbas();
        }
        
        function initPagina()
        {
            abaDados = aba.criaAba("Dados B&aacute;sicos", "vincularDadosBasicos.jsp");
            abaTipos = aba.criaAba("Visualização","vincularTipos.jsp");
            abaOperadoras = aba.criaAba("Regionais", "vincularOperadoras.jsp");
            abaLink = aba.criaAba("Link", "vincularLinkInfo.jsp");
            
            aba.selecionaAba(document.getElementById(abaDados), true);
            aba.habilitaAbas();
        }
        
        function selecionaAbaDados()
        {
            aba.selecionaAba(document.getElementById(abaDados), false);
        }
        
        function selecionaAbaTipos()
        {
            aba.selecionaAba(document.getElementById(abaTipos), false);
        }
        
        function selecionaAbaOperadoras()
        {
            aba.selecionaAba(document.getElementById(abaOperadoras), false);
        }
        
        function selecionaAbaLink()
        {
            aba.selecionaAba(document.getElementById(abaLink), false);
        }
        
        function CarregaAba(nome){
            var pagina = "";
            if(nome=="bt01") pagina = "vincularDadosBasicos.jsp";
            if(nome=="bt02") pagina = "vincularTipos.jsp";
            if(nome=="bt03") pagina = "vincularOperadoras.jsp";
            if(nome=="bt04") pagina = "vincularLinkInfo.jsp";
            document.getElementById("fraAbas").src = pagina;
        }
        
        function resizeFrameDetalhe(){
            var dynfrm = document.getElementById("fraAbas");            
            var dyndiv = document.getElementById("areaDetalhe");
            var objRef = document.getElementById("imgRef");
            var objDow = document.getElementById("imgRefDown");
            dyndiv.style.backgroundColor = 'E3ECF4';
            objRef.style.position = 'absolute';
            objDow.style.position = 'absolute';
            var novaTop   = objRef.offsetTop;
            var oldposdiv = objDow.offsetTop;
            objRef.style.position = '';
            objDow.style.position = '';            
            if(up){
                dyndiv.style.position = 'absolute';
                dyndiv.style.top = novaTop;
                dyndiv.style.left = 0;
                oldtamfrm = dynfrm.height;
                oldlardiv = dyndiv.style.width;
                dynfrm.height = parseInt(oldtamfrm) + parseInt(oldposdiv) - parseInt(novaTop);
                dyndiv.style.height = dynfrm.height;
                up = false;
                document['lupaaba'].src='<%=request.getContextPath()%>/resources/images/bt_lupa_aba_down.gif';
            }else{
                dyndiv.style.backgroundColor = '';
                dyndiv.style.left = 0;
                dynfrm.height = oldtamfrm;                
                dyndiv.style.height = oldtamfrm;
                dyndiv.style.position = '';
                dyndiv.style.width = oldlardiv;
                up = true;
                document['lupaaba'].src='<%=request.getContextPath()%>/resources/images/bt_lupa_aba.gif';
            }
        }
        

    </script>
    
        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuAdmSistemas.jsp"/>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
    
        <!--APLICACAO-->
            <vivo:sessao id="qdMain" height="470" scroll="no" width="790" label="Árvore de Contatos" operacoes="Manter Parâmetros">
            <div><img id="imgRef" src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="1"></div>
                <table width="780" cellpadding="0" cellspacing="0">
                    <tr>
                        <td valign="top">
                            <table width="100%" border="0" cellspacing="0" cellpadding="1" class="tbl_bgBlue">
                            <jsp:include page="/admsistemas/admArvore/arvoreContato.jsp" />
                            </table>
                        </td>
                    </tr>
                </table>                
                <div><img id="imgRefDown" src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></div>
                <div id="areaDetalhe">
                    <table width="780" cellspacing="0" cellpadding="0">
                        <tr>
                            <td valign="top">
                                <table height="16" border="0" width="780" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td background="<%=request.getContextPath()%>/resources/images/aba_bg_line.gif" valign="top" height="16">
                                            <vivo:abaGrupo id="btAba" width="700" height="16" styleClass="abatexto">
                                            <acesso:controlHiddenItem nomeIdentificador="adm_iaa_carregarabadadosbasicos">
                                                <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);" value="Dados B&aacute;sicos" select="S"/>                                
                                            </acesso:controlHiddenItem>
                                            <acesso:controlHiddenItem nomeIdentificador="adm_iaa_carregarabavisualizacao">
                                                <vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Visualização"/>
                                            </acesso:controlHiddenItem>
                                            <acesso:controlHiddenItem nomeIdentificador="adm_iaa_carregarabaregionais">
                                                <vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);CarregaAba(this.id);" value="Regionais"/>
                                            </acesso:controlHiddenItem>
                                            <acesso:controlHiddenItem nomeIdentificador="adm_iaa_carregarabalink">
                                                <vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);CarregaAba(this.id);" value="Link"/>
                                            </acesso:controlHiddenItem>
                                            </vivo:abaGrupo>
                                        </td>
                                        <td background="<%=request.getContextPath()%>/resources/images/aba_bg_line.gif" height="16" valign="top">
                                        <acesso:controlHiddenItem nomeIdentificador="adm_iaa_alterarframedetalhe">
                                            <img name="lupaaba" src="<%=request.getContextPath()%>/resources/images/bt_lupa_aba.gif" onclick="resizeFrameDetalhe();" style="cursor:hand">
                                        </acesso:controlHiddenItem>
                                        </td>
                                    </tr>
                                </table>               
                                <table width="780" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td width="1" rowspan="2" bgcolor="#adadad"></td>
                                        <td width="2" rowspan="2"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="2" height="1"></td>
                                        <td height="4" valign="top"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="4"></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" height="170">
                                            <iframe id="fraAbas" src="" width="777" height="220" frameborder="0"></iframe>
                                        </td>
                                    </tr>
                                    <tr bgcolor="#adadad">
                                        <td height="1" colspan="3"></td>
                                    </tr>
                                </table>
                         
                            </td>
                        </tr>
                    </table>
                </div>
                                
            </vivo:sessao>        
    <script language="Javascript">
        <!--
        initPagina();
        -->
    </script>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>