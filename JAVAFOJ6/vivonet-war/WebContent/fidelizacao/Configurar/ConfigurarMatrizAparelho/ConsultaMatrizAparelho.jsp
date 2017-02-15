<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <link href="/FrontOfficeWeb/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtree.js"></script>
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
        <script language="javascript">
            function SetCodigo(iCod){
                document.all('Acao').style.display = "block";
            }

            function abreGeral(parametro){
                parametro = parametro.split("X")
                param = parametro[parametro.length - 1];
                //alert(param);
                top.frameApp.dvNrProcesso.style.display = '';
                document.forms["frmSelecao"].target = "ifrmNrProcesso";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/fidelizacao/Configurar/ConfigurarMatrizAparelho/showDadosAparelho.do?id=" + param;
                document.forms["frmSelecao"].submit();
                //dv_dvNrProcesso.innerText = sMsg;
            }

            function expandirNo(idPai){
                if (!treeAparelhos.getSelected().isAddEnabled()){
                    return;
                }
                //alert("hello");
                top.frameApp.mostrar_div();
                document.forms[0].target = "iFrame";
                document.forms[0].action = "montarArvore.do?idPai=" + idPai
                document.forms[0].submit();
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                top.frameApp.oculta_div();
            -->
        </SCRIPT>
    </netui-template:section>
<netui-template:section name="bodySection">
<acesso:controlHiddenItem nomeIdentificador="fid_cma_verpagina">
    <table width="715" cellpadding="0" cellspacing="0" align="center">
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>
                <div style="width:715px; height:365px;overflow:auto;border-bottom:1px solid #adadad;border-top:1px solid #adadad;">
                    <script>
                           <%=request.getAttribute("arvore")%>
                    </script>
                    <form id="frmSelecao" name="frmSelecao" method="post"/></form>
                 </div>
            </td>
        </tr>
    </table>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
    <iframe name="iFrame" id="iFrame" width="0" height="0" style="display:none" scrolling="no">
</acesso:controlHiddenItem>
</netui-template:section>
</netui-template:template>