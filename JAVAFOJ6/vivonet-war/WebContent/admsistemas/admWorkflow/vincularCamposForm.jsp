<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    <netui-template:section name="bodySection">

    <script>
        function initPagina() { }
    </script>

        <vivo:quadro id="qdAba" height="385" width="747" label="Vincular Campos para Informa&ccedil;&atilde;o">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr><td height="6"></td></tr>
                <tr>
                    <td>
                        <table width="98%" border="0" cellspacing="1" cellpadding="0" align="center">
                            <tr>
                                <td width="20%" class="tblEstatica_campoNome">&nbsp;
                                    <netui:label value="Operadora:" styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="30%">
                                    <netui:select dataSource="{}" style="width:200" styleClass="SELECT" multiple="true" size="4">
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
                                    <netui:select dataSource="{}" style="width:150" styleClass="SELECT" multiple="true" size="4">
                                        <netui:selectOption value="Corporativo"/>
                                        <netui:selectOption value="Gestão"/>
                                        <netui:selectOption value="Pré-pago"/>
                                        <netui:selectOption value="Pós-pago"/>
                                    </netui:select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr><td height="6"></td></tr>
                <tr>
                    <td>
                        <table width="98%" border="0" cellspacing="1" cellpadding="0" align="center">
                            <tr>
                                <td width="20%" class="tblEstatica_campoNome">&nbsp;
                                    <netui:label value="Classificador:" styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="30%">
                                    <netui:select dataSource="{}" style="width:200" styleClass="SELECT">
                                        <netui:selectOption value="Classificador 1"/>
                                        <netui:selectOption value="Classificador 2"/>
                                        <netui:selectOption value="Classificador 3"/>
                                        <netui:selectOption value="Classificador 4"/>
                                        <netui:selectOption value="Classificador 5"/>
                                    </netui:select>
                                </td>
                                <td width="20%" class="tblEstatica_campoNome">&nbsp;
                                    <netui:label value="Fase:" styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="30%">
                                    <netui:select dataSource="{}" style="width:150" styleClass="SELECT">
                                        <netui:selectOption value="Fase 1"/>
                                        <netui:selectOption value="Fase 2"/>
                                        <netui:selectOption value="Fase 3"/>
                                        <netui:selectOption value="Fase 4"/>
                                        <netui:selectOption value="Fase 5"/>
                                    </netui:select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr><td height="8"></td></tr>
                <tr>
                    <td>
                    <table width="98%" class="tbl_bgGray" cellspacing="2" cellpadding="0" align="center">
                        <tr>
                            <td width="45%" class="tbl_titulo">&nbsp;
                                <netui:label value="Campos Existentes"/> 
                            </td>
                            <td width="6%">
                            </td>
                            <td width="49%" class="tbl_titulo" colspan="2">&nbsp;
                                <netui:label value="Campos Incluídos"/> 
                            </td>
                        </tr>
                        <tr>
                            <td width="45%" align="center">&nbsp;
                                <netui:select dataSource="{}" multiple="true" style="width:200" styleClass="SELECT">
                                    <netui:selectOption value="Ambiente"/>
                                    <netui:selectOption value="País"/>
                                    <netui:selectOption value="Sinal"/>
                                    <netui:selectOption value="Tipo Solicitação"/>
                                </netui:select>                                  
                            </td>
                            <td width="6%" valign="middle" align="center">
                                <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                                    <tr>
                                        <td align="center">
                                            <img id="btRightSimp" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">
                                            <img id="btRightDuplo" src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_right_over.gif" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">
                                            <img id="btLeftDuplo" src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_left_over.gif" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">
                                            <img id="btRightSimp" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td width="40%" align="right">&nbsp;
                                <netui:select dataSource="{}" multiple="true" style="width:200" styleClass="SELECT">
                                    <netui:selectOption value="Estado"/>
                                    <netui:selectOption value="Local Problema"/>
                                    <netui:selectOption value="Tempo Problema"/>
                                </netui:select> 
                            </td>
                            <td width="9%" align="center">
                                <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                                    <tr>
                                        <td align="center">
                                            <img src="/FrontOfficeWeb/resources/images/setaCima.gif"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">
                                            <img src="/FrontOfficeWeb/resources/images/setaBaixo.gif"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr><td height="6"></td></tr>
                <tr>
                    <td align="center">
                        <img id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" />
                    </td>
                </tr>
            </table>
        </vivo:quadro>
    </netui-template:section>
</netui-template:template>

                
