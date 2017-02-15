<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
            <tr>
                <td class="tbl_titulo" colspan="2">Árvore de contatos</td>
            </tr>
            <tr>
                <td>
                <div style="top: 0px; left: 0px; height: 180px; padding: 5px; overflow: auto;">
                        <script>
                            if (document.getElementById) {
                                var tree = new WebFXTree('Root');
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
