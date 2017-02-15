<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    <script>
        <!--
        function initPagina() { }
        -->  
    </script>
    <netui-template:section name="bodySection">
        <table width="100%" border="0" cellspacing="0" cellpadding="1" class="tbl_bgBlue">
            <tr>
                <td class="tbl_titulo" colspan="2">Árvore de baixa</td>
            </tr>
            <tr>
                <td width="77%">
                <div style="width: 450px; top: 0px; left: 0px; height: 220px; padding: 5px; overflow: auto;">
                    <script>
                        if (document.getElementById) {
                            var tree = new WebFXTree('Root');
                            tree.setBehavior('classic');
                            var a = new WebFXTreeItem('Pagamento em Duplicidade');
                            var a1 = new WebFXTreeItem('Cliente pagou a conta  em duplicidade');
                            a1.add(new WebFXTreeItem('Credito em Conta Futura'));
                            a1.add(new WebFXTreeItem('Credito em Conta Corrente'));
                            a1.add(new WebFXTreeItem('Contatar Cliente'));
                            a.add(a1);
                            tree.add(a);
                            var b = new WebFXTreeItem('Valor Acumulado');
                            var b1 = new WebFXTreeItem('Cliente pagou a conta em duplicidade');
                            b1.add(new WebFXTreeItem('Credito em Conta Futura'));
                            b1.add(new WebFXTreeItem('Credito em Conta Corrente'));
                            b1.add(new WebFXTreeItem('Contatar Cliente'));
                            var b2 = new WebFXTreeItem('Falha Sistemica');
                            b2.add(new WebFXTreeItem('Credito em Conta Futura'));
                            b2.add(new WebFXTreeItem('Credito em Conta Corrente'));
                            b2.add(new WebFXTreeItem('Contatar Cliente'));
                            b.add(b1);
                            b.add(b2);
                            tree.add(b);
                            var c = new WebFXTreeItem('Pagamento nao consta no sistema');
                            var c1 = new WebFXTreeItem('Falha Sistemica');
                            c1.add(new WebFXTreeItem('Baixa da Conta Efetuada'));
                            c1.add(new WebFXTreeItem('Baixa e Emissao de 2a via da Conta'));
                            var c2 = new WebFXTreeItem('Falha da Instituicao Financeira');
                            c2.add(new WebFXTreeItem('Solicitado comprovante'));
                            c2.add(new WebFXTreeItem('Nao processado'));
                            var c3 = new WebFXTreeItem('Fraude na Autenticacao Bancaria');
                            c3.add(new WebFXTreeItem('Baixa da Conta Efetuada'));
                            c3.add(new WebFXTreeItem('Baixa e Emissao de 2a via da Conta'));
                            c.add(c1);
                            c.add(c2);
                            c.add(c3);
                            tree.add(c);
                            document.write(tree);
                        }
                    </script>
                </div>
                </td>
                <td valign="middle" width="23%">
                    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                        <tr>
                            <td align="center">
                                <img class="button"
                                     onmouseup="top.abreJanela();"
                                	 src="/FrontOfficeWeb/resources/images/bt_relacionamento_nrml.gif"
                                	 border="0" />
                            </td>
                        </tr>
                                                        
                    </table>
                     <vivo:quadro id="operArvBaixa" width="300" height="60" label="Manuten&ccedil;&atilde;o de N&oacute;s"></vivo:quadro>
                </td>
            </tr>
        </table>
    <!-- Funcoes para a arvore de baixa -->
        <script>
        
        function addNode() {
            if (tree.getSelected()) {
                tree.getSelected().add(new WebFXTreeItem(nodename.value));
            }
        }
        
        function addNodes() {
            if (tree.getSelected()) {
                var foo = tree.getSelected().add(new WebFXTreeItem('New'));
                var bar = foo.add(new WebFXTreeItem('Sub 1'));
                var fbr = foo.add(new WebFXTreeItem('Sub 2'));
            }
        }
        
        function delNode() {
            if (tree.getSelected()) {
                tree.getSelected().remove();
            }
        }
        </script>
        
    </netui-template:section>
</netui-template:template>