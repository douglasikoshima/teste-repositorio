<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">

<script src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/xtree.css">

<form action="encerramentoGravar.do" method="post">
    <table width="100%" border="0" bgcolor="#E3ECF4">
        <tr valign="top">
            <td rowspan="8">
                <script>
                    function checaTextarea(obj, tamanho){
                      obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
                    }

                    if (document.getElementById) {
                        var tree = new WebFXTree('Entrevista de Solução');
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
                <br><br>
                <b>Código de Baixa:</b> 030304
            </td>
        </tr>
        <tr>
            <td width="13%">Resposta Padrão:</td>
            <td width="37%"><netui:textArea rows="4" dataSource="{}" style="width=260" readonly="true" defaultValue="O pagamento foi localizado. Efetuamos a baixa da conta."/></td>
        </tr>
        <tr valign="top">
            <td>Comentário:</td>
            <td colspan="3"><netui:textArea rows="4" dataSource="{}" style="width:260px" onKeyUp="checaTextarea(this, 500);" /></td>
        </tr>
        <tr>
            <td></td>
            <td align="left" colspan="3">
                <vivo:botao id="btAplicar" width="60px" height="10px" value="Salvar" styleClass="btTemplate" onclick=""/>
            </td>
        </tr>
    </table>
</form>

