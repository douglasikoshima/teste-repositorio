<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Administração de Workflow"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
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
        
        function CarregaAba(nome){
            var pagina = "";
            if(nome=="bt01") pagina = "carregaFiltro.do"
            if(nome=="bt02") pagina = "/FrontOfficeWeb/admsistemas/admArvoreConsulta/montaArvore.do";
            
            document.getElementById("ifrmAbas").src = pagina;
            mostrar_div();
            
        }

        // -->
        
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            CarregaAba('bt01');
            mostrar_div();
        -->
        </SCRIPT>

    </netui-template:section>

    <netui-template:section name="bodySection">

        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
                
        <!--APLICACAO-->

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4"></div>

        <vivo:sessao id="qdMain" height="473" width="790" label="Variáveis de Árvore de Contato" operacoes="Filtros" scroll="no">
            <form name="myForm" action="">
            <table border="0" cellspacing="0" cellpadding="0" width="757" align="center">
                <tr>
                    <td valign="top">
                        <vivo:abaGrupo id="btAba" width="780" height="10" styleClass="abatexto">
                            <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);" value="Filtros" select="S"/>
                            <vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Árvore"/>
                        </vivo:abaGrupo>
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0" align="center" style="border:1px solid #adadad;">
                <tr>
                    <td valign="top" bgcolor="#adadad">
                        <IFRAME id="ifrmAbas" name="ifrmAbas" WIDTH="100%" HEIGHT="430" FRAMEBORDER=0 SCROLLING=no SRC="/FrontOfficeWeb/admsistemas/admArvoreParametro/nada.html"></IFRAME>
                    </td>
                </tr>
            </table>
            <img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false;" style="border:none;cursor:hand;" hspace="5" vspace="4"/>
        
            </form>
            
        </vivo:sessao>

            <vivo:quadroFlutuante idIframe="ifrPesquisa" id="dvPesquisa" spacesLeft="5" height="490" spacesTop="80" url="<%=request.getContextPath()%>" display="none" width="790" label="Resultado Pesquisa" scroll="no"/>    

    </netui-template:section>
</netui-template:template>