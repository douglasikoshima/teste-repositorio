<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Tipos" name="abaContato" property="listaTipos.tipoComunicacaoVOArray"/>
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script type="text/javascript" language="javascript">

        var f;

        onload = function() {
            parent.parent.parent.oculta_div();
            f = document.forms[0];
            document.body.style.backgroundColor = '#ededed';

            <acesso:controlHiddenItem nomeIdentificador="cli_ico_verpagina">
            // Para prospects nao há opção celular
            if (top.frameApp.idRelacionamento == 6) {
                var d = document.forms[0].idTipoSelecionado;
                for (i = 0; i < d.length; i++) {
                    if (d.options[i].text == 'CELULAR') {
                        d.options.remove(i);
                        break;
                    }
                }
            }
            </acesso:controlHiddenItem>
        }

        function salvar(tipo) {

            if (valida() != 'TRUE') {
                alert(valida());
            } else {
                parent.parent.parent.mostrar_div();
                $('dsContato').value = $F('dsContato').strip();
                f.action = 'salvarContato.do?tipo=' + tipo;
                f.target = 'executa';
                f.method = 'POST';
                f.submit();
            }
        }

        valida = function() {

            tipo = document.forms[0].elements['idTipoSelecionado'].options[document.forms[0].elements['idTipoSelecionado'].selectedIndex].text;

            if (tipo == '--Selecione--') {
              return('Por favor, selecione um tipo de contato.');
            } else if (tipo.toUpperCase() == 'TELEFONE RESIDENCIAL'       ||
                       tipo.toUpperCase() == 'TELEFONE COMERCIAL'         ||
                       tipo.toUpperCase() == 'TELEFONE RECADO'            ||
                       tipo.toUpperCase() == 'TELEFONE DE RECADO'         ||
                       tipo.toUpperCase() == 'TELEFONE PARA RECADO'       ||
                       tipo.toUpperCase() == 'GESTOR DA CONTA - TELEFONE' ||
                       tipo.toUpperCase() == 'CELULAR'                    ||
                       tipo.toUpperCase() == 'TELEFONE CELULAR'           ||
                       tipo.toUpperCase() == 'FAX') {                           // Telefone
                if ($F('dsContato').blank()) {
                    return ('Por favor, digite um número de telefone.');
                }else if ($F('dsContato').length < 12) {
                    return ('Por favor, digite um número de telefone válido.');
                }else{
                    return('TRUE');
                }
            } else if (tipo.toUpperCase() == 'ENDERECO DE EMAIL' ||
                       tipo.toUpperCase() == 'ENDEREÇO DE EMAIL' ||
                       tipo.toUpperCase() == 'E-MAIL PESSOAL'    ||
                       tipo.toUpperCase() == 'E-MAIL PARTICULAR' ||
                       tipo.toUpperCase() == 'E-MAIL COMERCIAL'  ||
                       tipo.toUpperCase() == 'GESTOR DA CONTA - E-MAIL') {      // E-mail
                if ($F('dsContato').blank()) {
                    return ('Por favor, digite um e-mail.');
                } else if (!validaEmail($F('dsContato'))) {
                    return ('Por favor, digite um e-mail válido.');
                } else {
                    return('TRUE');
                }
            } else {
                if ($F('dsContato').blank()) {
                    return ('Por favor, digite a descrição do contato.');
                } else {
                    return('TRUE');
                }
            }
        }

        changeTipoContato = function(obj) {
            var objValue = f.elements['contato.dsContato'];
            var tipo = f.elements['idTipoSelecionado'].options[f.elements['idTipoSelecionado'].selectedIndex].text;
            objValue.value = '';
            objValue.readOnly = false;
            <%
            ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
            %>
            if (obj.options[obj.selectedIndex].text.toUpperCase() == 'CELULAR') {
                objValue.value = '<%=parametros.getNrLinha() != null ? parametros.getNrLinha() : ConstantesCRM.SVAZIO %>';
                objValue.readOnly = true;
                formatPhoneNumberObj(objValue);
            }
            else{
            	var input = $('campo');
            	if (tipo.toUpperCase() == 'TELEFONE RESIDENCIAL'              ||
                        tipo.toUpperCase() == 'TELEFONE COMERCIAL'            ||
                        tipo.toUpperCase() == 'TELEFONE RECADO'               ||
                        tipo.toUpperCase() == 'RECADO'                        ||
                        tipo.toUpperCase() == 'COMERCIAL'                     ||
                        tipo.toUpperCase() == 'RESIDENCIAL'                   ||
                        tipo.toUpperCase() == 'CONTATO'                       ||
                        tipo.toUpperCase() == 'TELEFONE DE CONTATO'           ||
                        tipo.toUpperCase() == 'TELEFONE DE COMERCIAL'         ||
                        tipo.toUpperCase() == 'TELEFONE CONTATO'              ||
                        tipo.toUpperCase() == 'TELEFONE CONTATO COMERCIAL'    ||
                        tipo.toUpperCase() == 'TELEFONE DE CONTATO COMERCIAL' ||
                        tipo.toUpperCase() == 'NÚMERO DO HOTEL PARA'          ||
                        tipo.toUpperCase() == 'NÚMERO DO HOTEL PARA RECADO'   ||
                        tipo.toUpperCase() == 'NUMERO DO HOTEL PARA'          ||
                        tipo.toUpperCase() == 'NUMERO DO HOTEL PARA RECADO'   ||
                        tipo.toUpperCase() == 'NUMERO HOTEL PARA'             ||
                        tipo.toUpperCase() == 'NÚMERO HOTEL PARA'             ||
                        tipo.toUpperCase() == 'NUMERO HOTEL'                  ||
                        tipo.toUpperCase() == 'NÚMERO HOTEL'                  ||
                        tipo.toUpperCase() == 'TELEFONE DE RECADO'            ||
                        tipo.toUpperCase() == 'SMS'                           ||
                        tipo.toUpperCase() == 'TELEFONE PARA RECADO'          ||
                        tipo.toUpperCase() == 'FAX'                           ||
                        tipo.toUpperCase() == 'GESTOR DA CONTA - TELEFONE'    ||
                        tipo.toUpperCase() == 'CELULAR')  {                             // Telefones
                	input.innerHTML = "<input type=\"text\" name=\"contato.dsContato\" maxlength=\"11\" style=\"width:300px;\" id=\"dsContato\" onblur=\"formatPhoneNumberObj(this);\" />";

                } else if (tipo.toUpperCase() == 'ENDERECO DE EMAIL'  ||
                           tipo.toUpperCase() == 'ENDEREÇO DE E-MAIL' ||
                           tipo.toUpperCase() == 'ENDEREÇO DE EMAIL'  ||
                           tipo.toUpperCase() == 'EMAIL'              ||
                           tipo.toUpperCase() == 'E-MAIL PESSOAL'     ||
                           tipo.toUpperCase() == 'E-MAIL PARTICULAR'  ||
                           tipo.toUpperCase() == 'E-MAIL COMERCIAL'   ||
                           tipo.toUpperCase() == 'EMAIL PESSOAL'      ||
                           tipo.toUpperCase() == 'EMAIL PARTICULAR'   ||
                           tipo.toUpperCase() == 'EMAIL COMERCIAL') {                   // E-mail
                	input.innerHTML = "<input type=\"text\" name=\"contato.dsContato\" maxlength=\"255\" style=\"width:300px;\" id=\"dsContato\" />";
                } else if (tipo.toUpperCase() == 'PAGER' ||
                           tipo.toUpperCase() == 'BIP') {                               // Pager
                	input.innerHTML = "<input type=\"text\" name=\"contato.dsContato\" maxlength=\"25\" style=\"width:300px;\" id=\"dsContato\" />";
                } else {
                	input.innerHTML = "<input type=\"text\" name=\"contato.dsContato\" maxlength=\"50\" style=\"width:300px;\" id=\"dsContato\" />";
                }
            }
        }

    </script>

</head>

<body>
<acesso:controlHiddenItem nomeIdentificador="cli_ico_verpagina">
        <html:form action="/cliente/TelaInicial/DetalheCliente/salvarContato.do" target="_parent" method="post">
            <table border="0" cellspacing="10" cellpadding="0">
                <tr>
                    <html:hidden property="idPessoa"/>
                    <html:hidden name="abaContato" property="contato.nrSequencia"/>
                    <td valign="top" width="80">
                        Tipo:
                    </td>
                    <td>
                        <html:select style="text-indent:3px;" property="idTipoSelecionado" styleId="idTpComunica" onchange="changeTipoContato(this)">
                            <html:option value="">--Selecione--</html:option>
                            <html:options collection="Tipos" property="idTipoComunicacao" labelProperty="dsTipoComunicacao"/>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        Descrição:
                    </td>
                    <td id="campo">
                        <html:text name="abaContato" property="contato.dsContato" maxlength="255" style="width:300px;" styleId="dsContato" /> 
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="2">
                    <acesso:controlHiddenItem nomeIdentificador="cli_ico_gravar">
                        <img style="border:none;" onClick="salvar('novo'); return false" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
       </html:form>

</acesso:controlHiddenItem>
</body>