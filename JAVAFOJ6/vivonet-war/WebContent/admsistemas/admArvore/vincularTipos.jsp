<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_vt_verpagina">

    <script>
    
        document.body.style.backgroundColor = '#ededed';
        
        function initPagina() { }
    </script>
    <center><span style="font-weight:bold; font-size:11px; ">Filtros para Visualização da Árvore de Contatos</span></center>
    <table width="740">
            <tr>
                <td width="339">
                    <vivo:quadro width="339" height="70" label=" Tipos de Pessoas Existentes" id="regExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                    <netui:select dataSource="{}" multiple="true" style="width:200" styleClass="SELECT" size="3">
                        <netui:selectOption value="Cliente"/>
                        <netui:selectOption value="Prospect"/>
                        <netui:selectOption value="Usuario"/>
                    </netui:select>
                    </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
                <td width="62" align="right" valign="bottom">
                    <img id="btRightSimp" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"/>
                    <img id="btRightTodos" src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_right_over.gif" style="border:none;"/><br><br>
                    <img id="btLeftSimp" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"/>
                    <img id="btLeftTodos" src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_left_over.gif" style="border:none;"/>
                </td>
                <td width="339">
                    <vivo:quadro label=" Tipos de Pessoas Associados " width="339" height="70" id="regExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                    <netui:select dataSource="{}" multiple="true" style="width:200" styleClass="SELECT" size="3">
                        <netui:selectOption value=""/>
                        <netui:selectOption value=""/>
                        <netui:selectOption value=""/>
                    </netui:select>
                    </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
        </table>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        
        <table width="740">
            <tr>
                <td width="339">
                    <vivo:quadro width="339" height="70" label="Tipos de Linhas Existentes" id="linhasExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                    <netui:select dataSource="{}" multiple="true" style="width:200" styleClass="SELECT" size="3">
                        <netui:selectOption value="Cliente"/>
                        <netui:selectOption value="Prospect"/>
                        <netui:selectOption value="Usuario"/>
                    </netui:select>
                    </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
                <td width="62" align="right" valign="bottom">
                    <img id="btRightSimp" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"/>
                    <img id="btRightTodos" src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_right_over.gif" style="border:none;"/><br><br>
                    <img id="btLeftSimp" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"/>
                    <img id="btLeftTodos" src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_left_over.gif" style="border:none;"/>
                </td>
                <td width="339">
                    <vivo:quadro label="Tipos de Linhas Associados" width="339" height="70" id="linhasAssoc">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                    <netui:select dataSource="{}" multiple="true" style="width:200" styleClass="SELECT" size="3">
                        <netui:selectOption value=""/>
                        <netui:selectOption value=""/>
                        <netui:selectOption value=""/>
                    </netui:select>
                    </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
        </table>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        
        <table width="740">
            <tr>
                <td width="339">
                    <vivo:quadro width="339" height="70" label="Segmentações Existentes" id="segmExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                    <netui:select dataSource="{}" multiple="true" style="width:200" styleClass="SELECT" size="4">
                        <netui:selectOption value="Segmentação 1"/>
                        <netui:selectOption value="Segmentação 2"/>
                        <netui:selectOption value="Segmentação 5"/>
                        <netui:selectOption value="Segmentação 6"/>
                    </netui:select>
                    </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
                <td width="62" align="right" valign="bottom">
                    <img id="btRightSimp" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"/>
                    <img id="btRightTodos" src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_right_over.gif" style="border:none;"/><br><br>
                    <img id="btLeftSimp" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"/>
                    <img id="btLeftTodos" src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_left_over.gif" style="border:none;"/>
                </td>
                <td width="339">
                    <vivo:quadro label="Segmentações Associadas" width="339" height="70" id="segmAssoc">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                    <netui:select dataSource="{}" multiple="true" style="width:200" styleClass="SELECT" size="4">
                        <netui:selectOption value="Segmentação 3"/>
                        <netui:selectOption value="Segmentação 4"/>
                        <netui:selectOption value="Segmentação 7"/>
                    </netui:select>
                    </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
        </table>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        
        <table width="740">
            <tr>
                <td width="339">
                    <vivo:quadro width="339" height="70" label="Carteiras Existentes" id="cartExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                    <netui:select dataSource="{}" multiple="true" style="width:200" styleClass="SELECT" size="4">
                        <netui:selectOption value="Carteira 2"/>
                        <netui:selectOption value="Carteira 4"/>
                        <netui:selectOption value="Carteira 6"/>
                        <netui:selectOption value="Carteira 7"/>
                    </netui:select>
                    </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
                <td width="62" align="right" valign="bottom">
                    <img id="btRightSimp" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"/>
                    <img id="btRightTodos" src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_right_over.gif" style="border:none;"/><br><br>
                    <img id="btLeftSimp" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"/>
                    <img id="btLeftTodos" src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_left_over.gif" style="border:none;"/>
                </td>
                <td width="339">
                    <vivo:quadro label="Carteiras Associadas" width="339" height="70" id="cartAssoc">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                    <netui:select dataSource="{}" multiple="true" style="width:200" styleClass="SELECT" size="4">
                        <netui:selectOption value="Carteira 1"/>
                        <netui:selectOption value="Carteira 3"/>
                        <netui:selectOption value="Carteira 5"/>
                    </netui:select>
                    </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
        </table>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td align="right">
                <acesso:controlHiddenItem nomeIdentificador="adm_vt_salvar">
                    <img id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;"/>
                </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>