<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript">
            function SetCodigo(iCod){
                document.all('Acao').style.display = "block";
            }

            function none(){
                void(0);
            }

            function addNode(){
                if(tree.getSelected()){
                    var sName = document.frmSelecao.nmAparelho.options[document.frmSelecao.nmAparelho.selectedIndex].text;
                    tree.getSelected().add(new WebFXTreeItem(sName ,'javascript:Insert();','', '/FrontOfficeWeb/resources/images/icon_celular.gif', '/FrontOfficeWeb/resources/images/icon_celular.gif'));
                }else{
                    alert('Selecione um item da árvore');
                }
            }

            function delNode() {
                if(tree.getSelected()){
                    tree.getSelected().remove();
                }
            }

            function Insert(){
                if(tree.getSelected()){
                    var sIcon =  tree.getSelected().icon;
                    var sMsg = "";
                    if(sIcon.indexOf("_rj") >0){
                        sMsg = "Rio de Janeiro";
                    }
                    abreGeral("Dados do Aparelho");
                }
            }

            function abreGeral(sMsg){
                dvNrProcesso.style.display = '';
                document.forms["frmSelecao"].target = "ifrmNrProcesso";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/fidelizacao/Configurar/ConfigurarMatrizAparelho/showDadosAparelho.do";
                document.forms["frmSelecao"].submit();
                dv_dvNrProcesso.innerText = sMsg;
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
        <script language="javascript" for="window" event="onload">
            <!--
                top.frameApp.oculta_div();
            -->
        </script>
    </netui-template:section>
<netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_cmoc_verpagina">
    <table width="715" cellpadding="0" cellspacing="0" align="center">
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>
                <div style="width:715px; height:365px;overflow:auto;border-bottom:1px solid #adadad;border-top:1px solid #adadad;">
                    <script>
                        <%=(String)request.getAttribute("arvore")%>
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
