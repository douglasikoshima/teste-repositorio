<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css"/>
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript">
            function SetCodigo(iCod){
                document.all('Acao').style.display = "block";
            }

            function addNode(){
                if (tree.getSelected()) {
                      var sName = document.frmSelecao.nmAparelho.options[document.frmSelecao.nmAparelho.selectedIndex].text;
                      tree.getSelected().add(new WebFXTreeItem(sName ,'javascript:Insert();','', '/FrontOfficeWeb/resources/images/icon_celular.gif', '/FrontOfficeWeb/resources/images/icon_celular.gif'));
                }else{
                    alert('Selecione um item da árvore');
                }
            }

            function delNode() {
                if (tree.getSelected()) {
                    tree.getSelected().remove();
                }
            }

            function Insert(){
                if (tree.getSelected()){
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
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/fidelizacao/Configurar/ConfigurarMatrizAparelho/dadosAparelho.jsp";
                document.forms["frmSelecao"].submit();
                dv_dvNrProcesso.innerText = sMsg;
            }
        </script>
</netui-template:section>
<netui-template:section name="bodySection">
<vivo:sessao id="qdMain" width="100%" height="100%" operacoes="Consultar Matriz de Script">
<table>
    <tr>
        <td width="40%">
            <script>
                if (document.getElementById) {
                    var tree = new WebFXTree('Script','','',  '/FrontOfficeWeb/resources/images/icon_generico.gif', '/FrontOfficeWeb/resources/images/icon_generico.gif');

                    tree.setBehavior('classic');
                    var aIntencao = new WebFXTreeItem('Cancelamento da  Linha' , '' , '', '/FrontOfficeWeb/resources/images/icon_cancela.gif' ,'/FrontOfficeWeb/resources/images/icon_cancela.gif');
                    aDestino       = new WebFXTreeItem('Ficar sem celular' , '' , '', '/FrontOfficeWeb/resources/images/icon_destino.gif' , '/FrontOfficeWeb/resources/images/icon_destino.gif');

                    aIntencao.add(aDestino) ;
                    tree.add(aIntencao);

                    tree.setBehavior('classic');
                    var aIntencao = new WebFXTreeItem('Mudar de Plano' , '' , '', '/FrontOfficeWeb/resources/images/icon_cancela.gif' ,'/FrontOfficeWeb/resources/images/icon_cancela.gif');
                    aDestino       = new WebFXTreeItem('Migração para pré pago' , '' , '', '/FrontOfficeWeb/resources/images/icon_destino.gif' , '/FrontOfficeWeb/resources/images/icon_destino.gif');

                    aIntencao.add(aDestino) ;
                    tree.add(aIntencao);

                    document.write(tree);
                }
            </script>
        </td>
    </tr>
    <tr>
        <td height="30" valign="bottom" colspan="3"><br>
            &nbsp;&nbsp;
            <img src="/FrontOfficeWeb/resources/images/bt_retornar_nrml.gif"
            	 onmouseup="window.location.href='/FrontOfficeWeb/fidelizacao/realizarRetencao.jsp';"
            	 border="0" />
        </td>
    </tr>
</table>
</vivo:sessao>
</netui-template:section>
</netui-template:template>
