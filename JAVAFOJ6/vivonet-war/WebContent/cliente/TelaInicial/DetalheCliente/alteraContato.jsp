<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>

<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script type="text/javascript" language="javascript">

        var f;
        onload = function() {
            parent.parent.parent.oculta_div();
            <acesso:controlHiddenItem nomeIdentificador="cli_alc_verpagina">
                f = document.forms[0];
                document.body.style.backgroundColor = '#ededed';
                <logic:equal name="abaContato" property="contato.tipoComunicacaoVO.dsTipoComunicacao" value="CELULAR">
                f['contato.dsContato'].readOnly = true;
                </logic:equal>
            </acesso:controlHiddenItem>
        }

        salvar = function(tipo) {
            if (valida() != 'TRUE') {
                alert(valida());
            } else {
                parent.parent.parent.mostrar_div();
                $('dsComunicacao').value = $F('dsComunicacao').strip();
                f.action = 'salvarContato.do?tipo='+tipo;
                f.target = 'executa';
                f.method = 'POST';
                f.submit();
	        }
        }

        changeTipoContato = function(obj) {
            var objValue = f.elements['contato.dsContato'];
            var tipo = $F('dsTpContato');

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
                    tipo.toUpperCase() == 'CELULAR') {				       // Telefones
                	input.innerHTML = "<input type=\"text\" name=\"contato.dsContato\" maxlength=\"11\" style=\"width:300px;\" id=\"dsContato\" onblur=\"formatPhoneNumberObj(this);\" />";

            } else if (tipo.toUpperCase() == 'ENDERECO DE EMAIL' ||
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
                    tipo.toUpperCase() == 'BIP') {                   //Pager
                	input.innerHTML = "<input type=\"text\" name=\"contato.dsContato\" maxlength=\"25\" style=\"width:300px;\" id=\"dsContato\" />";
            } else {
                	input.innerHTML = "<input type=\"text\" name=\"contato.dsContato\" maxlength=\"50\" style=\"width:300px;\" id=\"dsContato\" />";
            }
            
        }

        valida = function() {
            var tipo = $F('dsTpContato');
            if (tipo.toUpperCase() == 'TELEFONE RESIDENCIAL'           ||
                    tipo.toUpperCase() == 'TELEFONE COMERCIAL'         ||
                    tipo.toUpperCase() == 'TELEFONE RECADO'            ||
                    tipo.toUpperCase() == 'TELEFONE DE RECADO'         ||
                    tipo.toUpperCase() == 'TELEFONE PARA RECADO'       ||
                    tipo.toUpperCase() == 'FAX'                        ||
                    tipo.toUpperCase() == 'GESTOR DA CONTA - TELEFONE' ||
                    tipo.toUpperCase() == 'CELULAR'                    ||
                    tipo.toUpperCase() == 'TELEFONE CELULAR') {                 // Telefone Residencial
                if ($F('dsComunicacao').blank()) {
                    return ('Por favor, digite um número de telefone.');
                } else if ($F('dsComunicacao').length < 12) {
                    return ('Por favor, digite um número de telefone válido.');
                } else {
                    return('TRUE');
                }
            } else if (tipo.toUpperCase() == 'ENDERECO DE EMAIL' ||
                       tipo.toUpperCase() == 'ENDEREÇO DE EMAIL' ||
                       tipo.toUpperCase() == 'E-MAIL PESSOAL'    ||
                       tipo.toUpperCase() == 'E-MAIL PARTICULAR' ||
                       tipo.toUpperCase() == 'E-MAIL COMERCIAL'  ||
                       tipo.toUpperCase() == 'GESTOR DA CONTA - E-MAIL') {      // E-mail
                if ($F('dsComunicacao').blank()) {
                    return ('Por favor, digite um e-mail.');
                } else if (!validaEmail($F('dsComunicacao'))) {
                    return ('Por favor, digite um e-mail válido.');
                } else {
                    return('TRUE');
                }
            } else {
                if ($F('dsComunicacao').blank()) {
                    return ('Por favor, digite a descrição do contato.');
                } else {
                    return('TRUE');
                }
            }
        }
    </script>
</head>
<body>
<acesso:controlHiddenItem nomeIdentificador="cli_alc_verpagina">

<html:form action="/cliente/TelaInicial/DetalheCliente/salvarContato.do" target="_parent">
<table border="0" cellspacing="10" cellpadding="0">
    <tr>
        <html:hidden name="abaContato" property="idComunicacao" styleId="idContato"/>
        <html:hidden property="idPessoa"/>
        <html:hidden name="abaContato" property="inEmail" styleId="inEmail"/>
        <html:hidden name="abaContato" property="contato.nrSequencia" styleId="nrSequencia"/>
        <td>Tipo:</td>
        <td>
            <html:text name="abaContato" property="contato.tipoComunicacaoVO.dsTipoComunicacao" size="70" styleId="dsTpContato" readonly="true"/>
        </td>
    </tr>
    <tr>
        <td>Descrição:</td>
        <td id="campo">
            <html:text name="abaContato" property="contato.dsContato" size="70" maxlength="255" styleId="dsComunicacao" />
        </td>
    </tr>
    <tr>
        <td align="right" colspan="2">
        <acesso:controlHiddenItem nomeIdentificador="cli_alc_gravar">
            <img style="border:none;cursor:hand;" onClick="salvar('alteracao'); return false" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" />
        </acesso:controlHiddenItem>
        </td>
    </tr>
</table>
</html:form>

</acesso:controlHiddenItem>

</body>