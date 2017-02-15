<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_vdb_verpagina">    

    <script>        
    
        document.body.style.backgroundColor = "#ededed";
        
        function initPagina() { }
        
        function descricao(esconde) {
            document.divcampo = document.all['divcampo'];
            
            var nada = "";
            
            var ctext = "<table align='center' width='100%' border='0' cellspacing='1' cellpadding='1'>"+
                            "<tr>"+
                                "<td valign='top'>"+
                                    "<textarea name='mensagem' style='width:370px' rows='3' cols='40' value='' class='input'></textarea>"+
                                "</td>"+
                            "</tr>"+
                        "</table>";

            var cselect = "<table align='center' width='100%' height='100%' border='0' cellspacing='1' cellpadding='1'>" +
                                "<tr>"+
                                    "<td valign='top'>" +
                                        "<select name='mensagem' class='SELECT' style='width:370'>" +
                                            "<option value='0'>Mensagem de Aviso 1</option>" +
                                            "<option value='1'>Mensagem de Aviso 2</option>" +
                                            "<option value='2'>Mensagem de Aviso 3</option>" +
                                            "<option value='3'>Mensagem de Aviso 4</option>" +
                                            "<option value='4'>Mensagem de Aviso 5</option>" +
                                            "<option value='5'>Mensagem de Aviso 6</option>" +
                                            "<option value='6'>Mensagem de Aviso 7</option>" +
                                            "<option value='7'>Mensagem de Aviso 8</option>" +
                                        "</select>" +
                                    "</td>" +
                                "</tr>" +
                            "</table>";
            
            if (esconde){
                document.divcampo.innerHTML=ctext;
            }
            else { 
                document.divcampo.innerHTML=cselect;
            }
        }

    </script>
            <table width="777" cellpadding="0" cellspacing="0" bgcolor="#ededed">
                <tr><td height="4"></td></tr>
                <tr>
                    <td>
                    <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                        <tr>
                            <td width="25%" class="tblEstatica_campoNome">&nbsp;
                                <netui:label value="Prazo de Atendimento:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="25%" class="tblEstatica_campovalor">
                                <netui:textBox dataSource="{}" styleClass="input" style='width:60px; text-indent:4px;'/> (em horas)
                            </td>
                            <td width="25%" class="tblEstatica_campoNome">&nbsp;
                                <netui:label value="Fechamento:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="25%">
                                <netui:select dataSource="{}" styleClass="SELECT" style="text-indent:3px;">
                                    <netui:selectOption value="Imediato"/>
                                    <netui:selectOption value="Encaminhamento"/>
                                    <netui:selectOption value="Escolha"/>
                                </netui:select>
                            </td>
                        </tr>
                        <tr>
                            <td width="25%" class="tblEstatica_campoNome">&nbsp;
                                <netui:label value="Retorno:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="25%">&nbsp;
                                <netui:select dataSource="{}" styleClass="SELECT">
                                    <netui:selectOption value="Atendente Backoffice"/>
                                    <netui:selectOption value="Grupo Retorno"/>
                                    <netui:selectOption value="Não tem retorno"/>
                                </netui:select>
                            </td>
                            <td width="25%" class="tblEstatica_campoNome">&nbsp;
                                <netui:label value="Processo Técnico:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="25%">
                                <netui:select dataSource="{}" styleClass="SELECT" style="text-indent:3px;">
                                    <netui:selectOption value="Sim"/>
                                    <netui:selectOption value="Não"/>
                                </netui:select>
                            </td>
                        </tr>
                        <tr>
                            <td width="25%" class="tblEstatica_campoNome">&nbsp;
                                <netui:label value="Disponível:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="25%">&nbsp;
                                <netui:select dataSource="{}" styleClass="SELECT">
                                    <netui:selectOption value="Sim"/>
                                    <netui:selectOption value="Não"/>
                                </netui:select>
                            </td>
                            <td width="25%">&nbsp;&nbsp;
                                <netui:label value="Peso/Prioridade:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="25%" align="left">
                                <netui:textBox dataSource="{}" styleClass="input" style='width:60px'/>
                            </td>
                        </tr>                                        
                        <tr><td height="4"></td></tr>
                    </table>
                    </td>
                </tr>
                <tr><td height="1"></td></tr>
                <tr>
                    <td>
                    
                    <center>
                    <table width="743">
                        <tr>
                            <td width="490">
                                <table width="490" height="86" class="tbl_bgGray" cellspacing="2" cellpadding="1">
                                    <tr>
                                        <td class="tbl_titulo" align="center" height="16" colspan="3">
                                            <netui:label value="Mensagem de Aviso"/> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="middle" width="114">
                                            <netui:radioButtonGroup dataSource="{}" tagId="msg">
                                                <netui:radioButtonOption value="0" styleClass="radio" onclick="descricao(true);">&nbsp;Nova</netui:radioButtonOption><br>
                                                &nbsp;<netui:radioButtonOption value="1" styleClass="radio" onclick="descricao(false);">&nbsp;Existente</netui:radioButtonOption><br>
                                                &nbsp;<netui:radioButtonOption value="2" styleClass="radio" onclick="descricao(true);">&nbsp;Sem mensagem</netui:radioButtonOption>
                                            </netui:radioButtonGroup>
                                        </td>
                                        <td width="376">
                                            <div id="divcampo" style="visibility:'visible'; top: 0px; left: 0px; height: 24px; padding: 0px;">
                                            </div>
                                        </td>                                      
                                    </tr>
                                </table>    
                            </td>
                            <td width="253" align="center">
                            <acesso:controlHiddenItem nomeIdentificador="adm_vdb_salvar">
                                <img id="btSalvar2" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;"/>
                            </acesso:controlHiddenItem>
                            </td>
                        </tr>
                    </table>
                    </center>
                    
                    
                    
                    
                    
                    
                    </td>
                </tr>
                <tr><td height="4"></td></tr>
            </table>           
            <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                
            -->
            </SCRIPT>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>

                
