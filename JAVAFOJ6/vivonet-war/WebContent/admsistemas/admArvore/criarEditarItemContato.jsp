<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
    <script>
        function nome(esconde) {
            document.divcampo = document.all['divnome'];
            var nada = "";
            var ctext = "<table align='center' width='100%' border='0' cellspacing='1' cellpadding='1'>"+
                            "<tr>"+
                                "<td align='center'>"+
                                    "<input type='text' name='descricao' size='60' style='width:420px' value='' class='input'/>"+
                                "</td>"+
                            "</tr>"+
                        "</table>";

            var cselect = "<table align='center' width='100%' border='0' cellspacing='1' cellpadding='1'>" +
                                "<tr>"+
                                    "<td align='center'>" +
                                        "<select name='descricao' class='SELECT' style='width:420px'>" +
                                            "<option value='0'>A cobrar</option>" +
                                            "<option value='1'>Assinatura</option>" +
                                            "<option value='2'>Caixa Postal</option>" +
                                            "<option value='3'>Cancelamento</option>" +
                                            "<option value='4'>Chamadas originadas</option>" +
                                            "<option value='5'>DDD</option>" +
                                            "<option value='6'>DDI</option>" +
                                            "<option value='7'>Dificuldade na originação de recebimento</option>" +                                            
                                            "<option value='8'>Identificador</option>" +
                                            "<option value='9'>Informação</option>" +
                                            "<option value='10'>Oscilação de Analógico</option>" +
                                            "<option value='11'>Reclamação</option>" +
                                            "<option value='12'>Serviço</option>" +
                                            "<option value='13'>Técnica</option>" +
                                            "<option value='14'>WAP</option>" +
                                        "</select>" +
                                    "</td>" +
                                "</tr>" +
                            "</table>";
            
            if (esconde == 0){
                document.divcampo.innerHTML = ctext;
                }else{
                document.divcampo.innerHTML = cselect;
            }
        }
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_ceic_verpagina">
        <vivo:body idTable="tbMain" idDiv="dvMain" height="220" width="530">
            <table><tr><td height="4"></td></tr></table>
            <table width="100%" border="0" cellspacing="1" cellpadding="1" class="tbl_bgGray">
                <tr align="left">
                    <td class="tbl_titulo">Manutenção Árvore Contato > Incluir/Editar Item</td>
                </tr>
                <tr>
                    <td>
                    <table width="97%" border="0" cellspacing="1" cellpadding="1" align="center">
                        <tr><td height="4"></td></tr>
                        <tr>
                            <td align="center">
                                <table width="100%" border="0" cellspacing="2" cellpadding="2" align="center" class="tbl_bgBlue">
                                    <tr>
                                        <td width="30%" class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="Criar na Pasta: " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td width="70%" align="left">
                                            <netui:label value="RAÍZ DA ESTRUTURA" styleClass="tblEstatica_campoValor"/>                    
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="30%" class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="Tipo do item: " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td width="70%" class="tblEstatica_campoValor">
                                            <netui:radioButtonGroup dataSource="{}" tagId="tipo" defaultValue="0">
                                                <netui:radioButtonOption value="0" labelStyleClass="tblEstatica_campoValor" onClick="" tabindex="0">Pasta</netui:radioButtonOption>
                                                <netui:radioButtonOption value="1" labelStyleClass="tblEstatica_campoValor" onClick="" tabindex="1">Folha</netui:radioButtonOption>
                                            </netui:radioButtonGroup>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="30%" class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="Disponível: " styleClass="tblEstatica_campoNome"/>
                                        </td>
                                        <td width="70%" align="left">&nbsp;
                                            <netui:select dataSource="{}" styleClass="SELECT">
                                                <netui:selectOption value="Sim"/>
                                                <netui:selectOption value="Não"/>
                                            </netui:select>
                                        </td>
                                    </tr> 
                                    <tr>
                                        <td width="30%" class="tblEstatica_campoNome">&nbsp;
                                            <netui:label value="Nome do Item: " styleClass="tblEstatica_campoNome" defaultValue="0"/>
                                        </td>
                                        <td width="70%" align="left">
                                            <netui:radioButtonGroup dataSource="{}" tagId="nometipo">
                                                <netui:radioButtonOption value="0" onclick="nome(0);" tabindex="0">&nbsp;Novo</netui:radioButtonOption>
                                                <netui:radioButtonOption value="1" onclick="nome(1);" tabindex="1">&nbsp;Existente</netui:radioButtonOption>
                                            </netui:radioButtonGroup>  
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" align="center">
                                            <div id="divnome" style="visibility:'visible'; top: 0px; left: 0px; height: 24px; padding: 0px;">
                                            </div>
                                        </td>
                                    </tr>  
                                    <tr><td height="4"></td></tr>                                   
                                    <tr>
                                        <td colspan="2" align="right">
                                        <acesso:controlHiddenItem nomeIdentificador="adm_ceic_salvar">
                                            <img id="btSalvar1" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" />                        
                                        </acesso:controlHiddenItem>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr><td height="5"></td></tr>
                    </table>
                    </td>
                </tr>
            </table>
        </vivo:body>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>