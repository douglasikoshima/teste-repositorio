<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">    
<netui-template:section name="headerSection">
<script>
        function descricao(esconde) {
            document.divcampo = document.all['divcampo'];
            var nada = "";
            var ctext = "<table align='center' width='100%' border='0' cellspacing='1' cellpadding='1'>"+
                            "<tr>"+
                                "<td class='tblEstatica_campoNome' width='20%'>Descrição:</td>"+
                                "<td width='80%'>"+
                                    "<textarea name='descricao' style='width:300px' rows='2' cols='30' value='' class='input'></textarea>"+
                                "</td>"+
                            "</tr>"+
                        "</table>";

            var cselect = "<table align='center' width='100%' border='0' cellspacing='1' cellpadding='1'>" +
                                "<tr>"+
                                    "<td class='tblEstatica_campoNome' width='20%'>Descrição:</td>" +
                                    "<td width='80%'>" +
                                        "<select name='descricao' class='SELECT' style='width:300px'>" +
                                            "<option value='0'>Pagamento em Duplicidade</option>" +
                                            "<option value='1'>Valor Acumulado</option>" +
                                            "<option value='2'>Pagamento não consta no sistema</option>" +
                                            "<option value='3'>Habilitação</option>" +
                                            "<option value='4'>Cliente pagou a conta  em duplicidade</option>" +
                                            "<option value='5'>Cliente pagou a conta em duplicidade</option>" +
                                            "<option value='6'>Falha Sistêmica</option>" +
                                            "<option value='7'>Falha da Instituição Financeira</option>" +
                                        "</select>" +
                                    "</td>" +
                                "</tr>" +
                            "</table>";
            
            if (esconde == 0){
                document.divcampo.innerHTML = nada;
            }else{
                if(esconde == 1){
                    document.divcampo.innerHTML=ctext;
                }else{
                    document.divcampo.innerHTML=cselect;
                }
            }
        }
        
        function msgtelef(esconde) {
            document.divtelef = document.all['divtelef'];
            var nada = "";
            var ctext = "<table align='center' width='100%' border='0' cellspacing='1' cellpadding='1'>"+
                            "<tr>"+
                                "<td align='center'>"+
                                    "<textarea name='msgtel' style='width:380px' rows='3' cols='52' value='' class='input'></textarea>"+
                                "</td>"+
                            "</tr>"+
                        "</table>";

            var cselect = "<table align='center' width='100%' border='0' cellspacing='1' cellpadding='1'>" +
                                "<tr>"+
                                    "<td align='center'>" +
                                        "<select name='msgtel' class='SELECT' style='width:420px'>" +
                                            "<option value='0'>Solicitação de parcelamento não foi possível, pelo motivo abaixo.</option>" +
                                            "<option value='1'>Não foi constatado o bloqueio, portanto a tarifação é devida.</option>" +
                                            "<option value='2'>Em caráter excepcional foi efetuado cancelamento da(s) contas(s).</option>" +
                                            "<option value='3'>Não foi constatado solicitação para alteração de plano.</option>" +
                                            "<option value='4'>Regularizado situação junto ao Serasa.</option>" +
                                            "<option value='5'>Não constatamos inclusão pela Telesp Celular. Favor verificar junto ao Serasa.</option>" +
                                            "<option value='6'>Em caráter excepcional efetuamos o parcelamento.</option>" +
                                            "<option value='7'>Efetuado o reparcelamento nas parcelas e valores abaixo.</option>" +
                                            "<option value='8'>Solicitação de parcelamento não foi possível, pelo motivo abaixo.</option>" +
                                        "</select>" +
                                    "</td>" +
                                "</tr>" +
                            "</table>";
            
            if (esconde == 0){
                document.divtelef.innerHTML = nada;
            }else{
                if(esconde == 1){
                    document.divtelef.innerHTML=ctext;
                }else{
                    document.divtelef.innerHTML=cselect;
                }
            }
        }
        
        function msgemail(esconde) {
            document.divemail = document.all['divemail'];
            var nada = "";
            var ctext = "<table align='center' width='100%' border='0' cellspacing='1' cellpadding='1'>"+
                            "<tr>"+
                                "<td align='center'>"+
                                    "<textarea name='msgemail' style='width:400px' rows='3' value=''></textarea>"+
                                "</td>"+
                            "</tr>"+
                        "</table>";

            var cselect = "<table align='center' width='100%' border='0' cellspacing='1' cellpadding='1'>" +
                                "<tr>"+
                                    "<td align='center'>" +
                                        "<select name='msgemail' class='SELECT' style='width:420px'>" +
                                            "<option value='0'>Solicitação de parcelamento não foi possível, pelo motivo abaixo.</option>" +
                                            "<option value='1'>Não foi constatado o bloqueio, portanto a tarifação é devida.</option>" +
                                            "<option value='2'>Em caráter excepcional foi efetuado cancelamento da(s) contas(s).</option>" +
                                            "<option value='3'>Não foi constatado solicitação para alteração de plano.</option>" +
                                            "<option value='4'>Regularizado situação junto ao Serasa.</option>" +
                                            "<option value='5'>Não constatamos inclusão pela Telesp Celular. Favor verificar junto ao Serasa.</option>" +
                                            "<option value='6'>Em caráter excepcional efetuamos o parcelamento.</option>" +
                                            "<option value='7'>Efetuado o reparcelamento nas parcelas e valores abaixo.</option>" +
                                            "<option value='8'>Solicitação de parcelamento não foi possível, pelo motivo abaixo.</option>" +
                                        "</select>" +
                                    "</td>" +
                                "</tr>" +
                            "</table>";
            
            if (esconde == 0){
                document.divemail.innerHTML = nada;
            }else{
                if(esconde == 1){
                    document.divemail.innerHTML=ctext;
                }else{
                    document.divemail.innerHTML=cselect;
                }
            }
        }
        
        function msgsms(esconde) {
            document.divsms = document.all['divsms'];
            var nada = "";
            var ctext = "<table align='center' width='100%' border='0' cellspacing='1' cellpadding='1'>"+
                            "<tr>"+
                                "<td align='center'>"+
                                    "<textarea name='msgsms' style='width:400px' rows='3' cols='52' value='' class='input'></textarea>"+
                                "</td>"+
                            "</tr>"+
                        "</table>";

            var cselect = "<table align='center' width='100%' border='0' cellspacing='1' cellpadding='1'>" +
                                "<tr>"+
                                    "<td align='center'>" +
                                        "<select name='msgsms' class='SELECT' style='width:420px'>" +
                                            "<option value='0'>Solicitação de parcelamento não foi possível, pelo motivo abaixo.</option>" +
                                            "<option value='1'>Não foi constatado o bloqueio, portanto a tarifação é devida.</option>" +
                                            "<option value='2'>Em caráter excepcional foi efetuado cancelamento da(s) contas(s).</option>" +
                                            "<option value='3'>Não foi constatado solicitação para alteração de plano.</option>" +
                                            "<option value='4'>Regularizado situação junto ao Serasa.</option>" +
                                            "<option value='5'>Não constatamos inclusão pela Telesp Celular. Favor verificar junto ao Serasa.</option>" +
                                            "<option value='6'>Em caráter excepcional efetuamos o parcelamento.</option>" +
                                            "<option value='7'>Efetuado o reparcelamento nas parcelas e valores abaixo.</option>" +
                                            "<option value='8'>Solicitação de parcelamento não foi possível, pelo motivo abaixo.</option>" +
                                        "</select>" +
                                    "</td>" +
                                "</tr>" +
                            "</table>";
            
            if (esconde == 0){
                document.divsms.innerHTML = nada;
            }else{
                if(esconde == 1){
                    document.divsms.innerHTML=ctext;
                }else{
                    document.divsms.innerHTML=cselect;
                }
            }
        }
    </script>    
    </netui-template:section>
    <netui-template:section name="bodySection">
    <table>
        <tr>
            <td>Item selecionado</td>
        </tr>
    </table>
    <vivo:quadro id="descricao" width="718" height="100" label="Descriç&atilde;o">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="tblEstatica_campoNome" width="20%">Tipo:</td>
                <td width="80%">
                    <netui:radioButtonGroup dataSource="{}" tagId="desctype">
                        <netui:radioButtonOption value="0" styleClass="radio" onclick="descricao(0);">&nbsp;Nenhuma</netui:radioButtonOption>
                        <netui:radioButtonOption value="1" styleClass="radio" onclick="descricao(1);">&nbsp;Nova</netui:radioButtonOption>
                        <netui:radioButtonOption value="2" styleClass="radio" onclick="descricao(2);">&nbsp;Existente</netui:radioButtonOption>
                    </netui:radioButtonGroup>                                            
                </td>
            </tr>
            <tr>
                <td colspan="2" height="50" valign="top">
                    <div id="divcampo" style="visibility:'visible'; top: 0px; left: 0px; height: 24px; padding: 0px;">
                    </div>
                </td>
            </tr>                                    
            <tr align="center">
                <td colspan="2">
                    <img style="border:none;" id="btSalvar1" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" />
                </td>
            </tr>
        </table>
    </vivo:quadro>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <vivo:quadro id="descricao" width="443" height="240" label="Mensagens">
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="1"></div>
        <table width="413" border="0" cellspacing="1" cellpadding="1">
            <tr>
                <td colspan="2" class="tbl_bgGray">&nbsp;Mensagem por Telefone</td>
            </tr>
            <tr>
                <td class="tblEstatica_campoNome">Tipo:</td>
                <td width="80%">
                    <netui:radioButtonGroup dataSource="{}" tagId="typetel">
                        <netui:radioButtonOption styleClass="radio" value="0" onclick="msgtelef(0);">&nbsp;Nenhuma</netui:radioButtonOption>
                        <netui:radioButtonOption styleClass="radio" value="1" onclick="msgtelef(1);">&nbsp;Nova</netui:radioButtonOption>
                        <netui:radioButtonOption styleClass="radio" value="2" onclick="msgtelef(2);">&nbsp;Existente</netui:radioButtonOption>
                    </netui:radioButtonGroup>                                            
                </td>
            </tr>                            
            <tr>
                <td colspan="2" align="left">
                    <div id="divtelef" style="visibility:'visible'; top: 0px; left: 0px; height: 24px; padding: 0px;"></div>
                </td>
            </tr>                   
            <tr>
                <td colspan="2" class="tbl_bgGray">&nbsp;Mensagem por Email</td>
            </tr>
            <tr>
                <td class="tblEstatica_campoNome" width="20%">Tipo:</td>
                <td>                            
                    <netui:radioButtonGroup dataSource="{}" tagId="typeemail">
                        <netui:radioButtonOption styleClass="radio" value="0" onclick="msgemail(0);">&nbsp;Nenhuma</netui:radioButtonOption>
                        <netui:radioButtonOption styleClass="radio" value="1"  onclick="msgemail(1);">&nbsp;Nova</netui:radioButtonOption>
                        <netui:radioButtonOption styleClass="radio" value="2" onclick="msgemail(2);">&nbsp;Existente</netui:radioButtonOption>
                    </netui:radioButtonGroup>                                            
                </td>
            </tr>                        
            <tr>
                <td colspan="2">
                    <div id="divemail" style="visibility:'visible'; top: 0px; left: 0px; height: 24px; padding: 0px;"></div>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="tbl_bgGray">&nbsp;Mensagem por Short Message (SMS)</td>
            </tr>
            <tr>
                <td class="tblEstatica_campoNome" width="20%">Tipo:</td>
                <td width="80%">                            
                    <netui:radioButtonGroup dataSource="{}" tagId="typesms">
                        <netui:radioButtonOption styleClass="radio" value="0" onclick="msgsms(0);">&nbsp;Nenhuma</netui:radioButtonOption>
                        <netui:radioButtonOption styleClass="radio" value="1" onclick="msgsms(1);">&nbsp;Nova</netui:radioButtonOption>
                        <netui:radioButtonOption styleClass="radio" value="2" onclick="msgsms(2);">&nbsp;Existente</netui:radioButtonOption>                                                    
                    </netui:radioButtonGroup>                                            
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="divsms" style="visibility:'visible'; top: 0px; left: 0px; height: 24px; padding: 0px;"></div>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <img style="border:none;" id="btSalvar2" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" />
                </td>
            </tr>
        </table>    
    </vivo:quadro>
    </netui-template:section>
</netui-template:template>