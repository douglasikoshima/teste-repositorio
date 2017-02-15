<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">

    <script>
        function initPagina() { }
        
        function mudarImagem(url) {
            document.btSalvar.src = url;
        }

    </script>

    <netui-template:section name="bodySection">
        <vivo:quadro id="qdAba" height="385" width="747" label="Vincular Question&aacute;rio de Satisfa&ccedil;&atilde;o">
            <table width="100%" cellspacing="0" cellpadding="0">
                <tr><td height="6"></td></tr>
                <tr>
                    <td colspan="4">
                    <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center" class="tbl_bgGray">
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td width="20%" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="Operadora:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="30%">
                            <netui:select dataSource="{}" style="width:150" styleClass="SELECT">
                                <netui:selectOption value="Bahia - Sergipe"/>
                                <netui:selectOption value="Centro-Oeste - Norte"/>
                                <netui:selectOption value="Paraná - Santa Catarina"/>
                                <netui:selectOption value="Rio Grande do Sul"/>
                                <netui:selectOption value="Rio de Janeiro - Espírito Santo"/>
                                <netui:selectOption value="São Paulo"/>
                            </netui:select>
                            </td>
                            <td width="20%" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="Tipo de Linha:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="30%">
                            <netui:select dataSource="{}" style="width:150" styleClass="SELECT">
                                <netui:selectOption value="Corporativo"/>
                                <netui:selectOption value="Gestão"/>
                                <netui:selectOption value="Pré-pago"/>
                                <netui:selectOption value="Pós-pago"/>
                            </netui:select>
                            </td>
                        </tr>
                        <tr>
                            <td width="20%" class="tblEstatica_campoNome">&nbsp;
                            <netui:label value="Questionário:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td colspan="3">
                            <netui:select dataSource="{}" style="width:340" styleClass="SELECT">
                                <netui:selectOption value="Questionário 1"/>
                                <netui:selectOption value="Questionário 2"/>
                                <netui:selectOption value="Questionário 3"/>
                                <netui:selectOption value="Questionário 4"/>
                                <netui:selectOption value="Questionário 5"/>
                                <netui:selectOption value="Questionário 6"/>
                            </netui:select>
                            &nbsp;
                            <img id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" />
                            </td>
                        </tr>
                        <tr><td height="4"></td></tr>
                    </table>
                    </td>
                </tr>
                <tr><td height="6"></td></tr>
                <tr>
                    <td colspan="4" align="center">
                    <table width="98%" border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray">
                        <tr>
                            <td width="2%" align="center" class="tblDinamica_titulo">&nbsp;</td>
                            <td width="38%" class="tblDinamica_titulo">Operadora</td>
                            <td width="40%" align="center" class="tblDinamica_titulo">Tipo
                            de Linha</td>
                            <td width="20%" align="center" class="tblDinamica_titulo">Questionário</td>
                        </tr>
                        <tr>
                            <td align="center" class="tblDinamica_linhaimpar">
                            </td>
                            <td align="left" class="tblDinamica_linhaimpar">Telerj</td>
                            <td align="left" class="tblDinamica_linhaimpar">Pós-Pago</td>
                            <td align="left" class="tblDinamica_linhaimpar">Questionário 3</td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr><td height="4"></td></tr>
            </table>
        </vivo:quadro>
    </netui-template:section>
</netui-template:template>
