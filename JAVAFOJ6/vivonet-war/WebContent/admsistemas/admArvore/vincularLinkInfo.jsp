<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_vli_verpagina">

    <script>
        
        document.body.style.backgroundColor = '#ededed';
    
        function initPagina() { }
        
        var oneSelect = 0;
        var linha ="";
        function rowOver(obj){
            cell = obj.childNodes;
            for(i=0;i<obj.childNodes.length;i++)
                cell[i].className='tblDinamica_linhaOver';
        }
        
        function rowClick(obj,tipo){
            cell = obj.childNodes;
            if(oneSelected==0){
                for(i=0;i<obj.childNodes.length;i++){
                    cell[i].className='tblDinamica_linhaSelected';
                }
                oneSelected = 1;
            }else{
                oneSelected = 0;
                if(tipo==0){
                    rowOutEven(obj);
                }else{
                    rowOutOdd(obj);
                }
            }
        }
        
        function rowOutEven(obj){
            cell = obj.childNodes;
            for(i=0;i<obj.childNodes.length;i++)
                cell[i].className='tblDinamica_linhapar';
        }
        
        function rowOutOdd(obj){
            cell = obj.childNodes;
            for(i=0;i<obj.childNodes.length;i++)
                cell[i].className='tblDinamica_linhaimpar';
        }

    </script>


        
        
        <table width="740">
            <tr>
                <td width="339">
                    <vivo:quadro width="339" height="70" label="Tipos de Operadoras Disponíveis" id="regExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                                    <netui:select dataSource="{}" style="width:150" styleClass="SELECT" multiple="true" size="4">
                                        <netui:selectOption value="Bahia - Sergipe"/>
                                        <netui:selectOption value="Centro-Oeste - Norte"/>
                                        <netui:selectOption value="Paraná - Santa Catarina"/>
                                        <netui:selectOption value="Rio Grande do Sul"/>
                                        <netui:selectOption value="Rio de Janeiro - Espírito Santo"/>
                                        <netui:selectOption value="São Paulo"/>
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
                    <vivo:quadro label="Tipos de Operadoras Selecionadas" width="339" height="70" id="regExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                    <netui:select dataSource="{}" style="width:150" styleClass="SELECT" multiple="true" size="4">
                        <netui:selectOption value="Bahia - Sergipe"/>
                        <netui:selectOption value="Centro-Oeste - Norte"/>
                        <netui:selectOption value="Paraná - Santa Catarina"/>
                        <netui:selectOption value="Rio Grande do Sul"/>
                        <netui:selectOption value="Rio de Janeiro - Espírito Santo"/>
                        <netui:selectOption value="São Paulo"/>
                    </netui:select>
                    </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
        </table>
        
        
        <table width="740">
            <tr>
                <td width="339">
                    <vivo:quadro width="339" height="70" label="Tipos de Linhas Disponíveis" id="regExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                    <netui:select dataSource="{}" style="width:150" styleClass="SELECT" multiple="true" size="4">
                        <netui:selectOption value="Corporativo"/>
                        <netui:selectOption value="Gestão"/>
                        <netui:selectOption value="Pré-pago"/>
                        <netui:selectOption value="Pós-pago"/>
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
                    <vivo:quadro label="Tipos de Linhas Selecionadas" width="339" height="70" id="regExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                                    <netui:select dataSource="{}" style="width:150" styleClass="SELECT" multiple="true" size="4">
                                        <netui:selectOption value="Corporativo"/>
                                        <netui:selectOption value="Gestão"/>
                                        <netui:selectOption value="Pré-pago"/>
                                        <netui:selectOption value="Pós-pago"/>
                                    </netui:select>
                                </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
        </table>
        
        <table width="740">
            <tr>
                <td width="339">
                    <vivo:quadro width="339" height="70" label="Tipos de Clientes Disponíveis" id="regExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                    <netui:select dataSource="{}" style="width:150" styleClass="SELECT" multiple="true" size="4">
                        <netui:selectOption value="Corporativo"/>
                        <netui:selectOption value="Gestão"/>
                        <netui:selectOption value="Pré-pago"/>
                        <netui:selectOption value="Pós-pago"/>
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
                    <vivo:quadro label="Tipos de Clientes Selecionadas" width="339" height="70" id="regExist">
                        <table width="100%" height="100%">
                            <tr>
                                <td align="center" valign="middle">
                                    <netui:select dataSource="{}" style="width:150" styleClass="SELECT" multiple="true" size="4">
                                        <netui:selectOption value="Corporativo"/>
                                        <netui:selectOption value="Gestão"/>
                                        <netui:selectOption value="Pré-pago"/>
                                        <netui:selectOption value="Pós-pago"/>
                                    </netui:select>
                                </td>
                            </tr>
                        </table>
                    </vivo:quadro>
                </td>
            </tr>
        </table>
        
        
        <table width="100%">
            <tr>
                <td>
                    <table style="indent:100px;" width="740" border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray">
                        <tr>
                            <td width="2%" class="tblDinamica_titulo">&nbsp;</td>
                            <td class="tblDinamica_titulo">Operadora</td>
                            <td class="tblDinamica_titulo">Tipo de Linha</td>
                            <td class="tblDinamica_titulo">Link</td>
                            <td width="25" class="tblDinamica_titulo"></td>
                            <td width="25" class="tblDinamica_titulo"></td>
                        </tr>
                        <tr onMouseOver="rowOver(this)" onMouseOut="rowOutOdd(this)" onclick="rowClick(this,1);">
                            <td class="tblDinamica_linhaimpar">
                            </td>
                            <td class="tblDinamica_linhaimpar">Telerj</td>
                            <td class="tblDinamica_linhaimpar">Pós-Pago</td>
                            <td class="tblDinamica_linhaimpar">
                            <netui:anchor href="http://www.vivosp.com.br" styleClass="TableTitleC3">Texto
                                Apoio</netui:anchor>
                            </td>
                            <td class="tblDinamica_linhaimpar" align="center">
                            <acesso:controlHiddenItem nomeIdentificador="adm_vli_alterarlinhaimpar">
                                <img id="Alterar" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" style="cursor:hand"/>
                            </acesso:controlHiddenItem>
                            </td>
                            <td class="tblDinamica_linhaimpar" align="center">
                            <acesso:controlHiddenItem nomeIdentificador="adm_vli_excluirlinhaimpar">
                                <img id="Excluir"  src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" style="cursor:hand"/>
                            </acesso:controlHiddenItem>
                            </td>
            
                        </tr>
                        <tr onMouseOver="rowOver(this)" onMouseOut="rowOutEven(this)" onclick="rowClick(this,0);">
                            <td class="tblDinamica_linhapar">
                            <table border="0" cellspacing="0" cellpadding="1">
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                            </td>
                            <td class="tblDinamica_linhapar">Telest</td>
                            <td class="tblDinamica_linhapar">Pré-pago</td>
                            <td class="tblDinamica_linhapar">
                            <netui:anchor href="http://www.vivo.com.br" styleClass="LinkC1">Texto
                                Apoio</netui:anchor>
                            </td>
                            <td class="tblDinamica_linhapar" align="center">
                            <acesso:controlHiddenItem nomeIdentificador="adm_vli_alterarlinhapar">
                                <img id="Alterar" onClick="" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0"  style="cursor:hand"/>
                            </acesso:controlHiddenItem>
                            </td>
                            <td class="tblDinamica_linhapar" align="center">
                            <acesso:controlHiddenItem nomeIdentificador="adm_vli_excluirlinhapar">
                                <img id="Excluir" onClick="" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" style="cursor:hand"/>
                            </acesso:controlHiddenItem>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

        
        <table width="740">
            <tr>
                <td align="right" valign="middle">
        Link <netui:textBox dataSource="{}" style="width:305" styleClass="input"/>
        <acesso:controlHiddenItem nomeIdentificador="adm_vli_salvar">
        <img align="middle" id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;"/>
        </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        
     </acesso:controlHiddenItem>   
    </netui-template:section>
</netui-template:template>
