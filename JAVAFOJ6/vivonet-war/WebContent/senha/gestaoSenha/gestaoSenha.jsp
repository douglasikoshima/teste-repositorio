<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

    <netui-template:template templatePage="/resources/jsp/template.jsp">
        <netui-template:section name="bodySection">
            <form tagId="formGestaoSenha" action="gerarSenhaAction.do" method="post">
                <table>
                    <tr valign="top">
                        <td>Operação: </td>
                        <td>
                            <netui:select dataSource="{}" style="width=200" onChange="changeOpcao(this.value)">
                                <netui:selectOption value="1">Gerar Senha</netui:selectOption>
                                <netui:selectOption value="2">Alterar Senha</netui:selectOption>
                                <netui:selectOption value="3">Validar Senha</netui:selectOption>
                                <netui:selectOption value="4">Reinicializar Senha</netui:selectOption>
                            </netui:select>
                        </td>
                    </tr>
                    <tr valign="top">
                        <td>Área: </td>
                        <td><netui:textBox tagId="txtArea" dataSource="{actionForm.foneArea}" disabled="true"/></td>
                    </tr>
                    <tr valign="top">
                        <td>Telefone: </td>
                        <td><netui:textBox tagId="txtNum" dataSource="{actionForm.foneNumero}" disabled="true"/></td>
                    </tr>
                    <tr valign="top">
                        <td>IdPessoa: </td>
                        <td><netui:textBox tagId="txtIdPessoa" dataSource="{actionForm.idPessoa}"/></td>
                    </tr>
                    <tr valign="top">
                        <td>Tipo de Pessoa: </td>
                        <td>
                            <netui:radioButtonGroup tagId="rgrpPessoa" dataSource="{actionForm.tipoPessoa}">
                                <netui:radioButtonOption value="{pageFlow.TP_USUARIO}">Usuário</netui:radioButtonOption>
                                <netui:radioButtonOption value="{pageFlow.TP_CLIENTE}">Cliente</netui:radioButtonOption>
                            </netui:radioButtonGroup>
                        </td>
                    </tr>
                    <tr valign="top">
                        <td>Senha: </td>
                        <td><netui:textBox tagId="txtSenha" dataSource="{actionForm.senha}" disabled="true"/></td>
                    </tr>
                    <tr valign="top">
                        <td>IdCanal: </td>
                        <td><netui:textBox tagId="txtIdCanal" dataSource="{actionForm.idCanal}" disabled="true"/></td>
                    </tr>
                    <tr valign="top">
                        <td>Usuário:</td>
                        <td><netui:textBox tagId="txtUsuario" dataSource="{actionForm.idUsuario}"/></td>
                    </tr>
                    <tr valign="top">
                        <td>Motivo: </td>
                        <td><netui:textArea tagId="txtMotivo" dataSource="{actionForm.motivo}" disabled="true"/></td>
                    </tr>
                    <tr valign="top">
                        <td>Retorno:</td>
                        <td><netui:label value="{actionForm.message}"/></td>
                    </tr>
                </table>
                <br/>
                &nbsp;
                <netui:button tagId="btGerar" value="Gerar" type="submit" action="gerarSenhaAction"/>
                <netui:button tagId="btAlterar" value="Alterar" type="submit" action="alterarSenhaAction" disabled="true"/>
                <netui:button tagId="btValidar" value="Validar" type="submit" action="validarSenhaAction" disabled="true"/>
                <netui:button tagId="btReinicializar" value="Reinicializar" type="submit" action="reinicializarSenhaAction" disabled="true"/>
            </form>

            <script language="javascript">
                function desabilita() {
                    document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtArea")].disabled = true;
                    document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtNum")].disabled = true;
                    document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtSenha")].disabled = true;
                    document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtIdCanal")].disabled = true;
                    document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtMotivo")].disabled = true;
                    document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtIdPessoa")].disabled = true;
    
                    document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("btGerar")].disabled = true;
                    document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("btAlterar")].disabled = true;
                    document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("btValidar")].disabled = true;
                    document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("btReinicializar")].disabled = true;
                }

                function changeOpcao(opcao) {
                    desabilita();

                    if(opcao == 1) {
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtIdPessoa")].disabled = false;
                        
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("btGerar")].disabled = false;
                    }
                    if(opcao == 2) {
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtIdPessoa")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtArea")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtNum")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtSenha")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtIdCanal")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtMotivo")].disabled = false;

                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("btAlterar")].disabled = false;
                    }
                    if(opcao == 3) {
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtArea")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtNum")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtSenha")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtIdCanal")].disabled = false;

                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("btValidar")].disabled = false;
                    }
                    if(opcao == 4) {
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtIdPessoa")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtArea")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtNum")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtSenha")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtIdCanal")].disabled = false;
                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("txtMotivo")].disabled = false;

                        document[getNetuiTagName("formGestaoSenha")][getNetuiTagName("btReinicializar")].disabled = false;
                    }
                }
            </script>

        </netui-template:section>
    </netui-template:template>

