<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Tipos"  name="abaEndereco" property="listas.listaTipoEnderecoArray"/>
<bean:define id="Paises" name="abaEndereco" property="listas.listaPaisArray"/>
<bean:define id="UFs"    name="abaEndereco" property="listas.listaUFArray"/>
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>

<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        parent.parent.parent.oculta_div();
    -->
</SCRIPT>

<script language="javaScript">

    function salvar(tipo){
        if (document.getElementById("nrCEP").value == "" || document.getElementById("nrCEP").value.length < 9){
            alert('Favor realizar uma pesquisa para preencher o campo CEP com um número válido!');
        }else if (trim(document.getElementById("nmTipoLogradouro").value) == ""){
            alert('Favor realizar uma pesquisa para preencher o campo Tipo Logradouro!');
        }else if (trim(document.getElementById("nmLogradouro").value) == ""){
            alert('Favor realizar uma pesquisa para preencher o campo Logradouro!');
        }else if (trim(document.getElementById("nrEndereco").value) == ""){
            alert('Favor preencher o campo Número!');
        }else if (trim(document.getElementById("nmBairro").value) == ""){
            alert('Favor preencher o campo Bairro!');
        }else if (trim(document.getElementById("nmMunicipio").value) == ""){
            alert('Favor realizar uma pesquisa para preencher o campo Municipio!');
        }else if (trim(document.getElementById("idUFSelecionado").value) == ""){
            alert('Favor preencher o campo UF!');
        }else if (trim(document.getElementById("idTipoSelecionado").value) == ""){
            alert('Favor preencher o campo Tipo de Endereço!');
        }else{
            document.getElementById("nrCEP").value = limpaInteiro(document.getElementById("nrCEP").value);
            document.forms[0].action="salvarEndereco.do?tipo="+tipo;
            parent.parent.parent.mostrar_div();
            document.forms[0].method = "POST";
            document.forms[0].submit();
        }
    }

    function pesquisaEndereco(){
        var o = parent.parent.document.getElementById("dvProfile");
        var t = parent.parent.document.getElementById("ifrmProfile");
        o.style.display = 'block';
        parent.parent.document.getElementById("dv_dvProfile").innerHTML = 'Busca CEP';
        t.src  = 'pesquisaEndereco.do?pagina=inicioPesquisa&retorno=inclui&idPessoa='+document.getElementById("idPessoa").value+'&idTipoSelecionado='+document.getElementById('idTpSelecionado').value;
    }

    function limpar(){
        document.getElementById('nmTipoLogradouro').value="";
        document.getElementById('nmTituloLogradouro').value="";
        document.getElementById('nmLogradouro').value="";
        document.getElementById('nrEndereco').value="";
        document.getElementById('dsEnderecoComplemento').value="";
        document.getElementById('nmBairro').value="";
        document.getElementById('nmMunicipio').value="";
        document.getElementById('nrCEP').value="";
        document.getElementById('idUFSelecionado').value="";
        document.getElementById('idTipoSelecionado').value="";
    }

</script>
</head>
<body style="background-color:#E3ECF4;">
<acesso:controlHiddenItem nomeIdentificador="cli_ine_verpagina">
    <html:form action="/cliente/TelaInicial/DetalheUsuario/salvarEndereco.do" target="_parent" method="post">
        <html:hidden name="abaEndereco" property="idPessoa" styleId="idPessoa"/>
        <table width="100%" height="200" border="0" cellspacing="8" cellpadding="0" >
            <tr>
                <td width="120">Tipo de Endere&ccedil;o:</td>
                <td width="120" >
                    <html:select property="idTipoSelecionado" styleId="idTpSelecionado">
                        <option value=""></option>
                        <html:options collection="Tipos" property="tipoEnderecoVO.idTipoEndereco" labelProperty="tipoEnderecoVO.dsTipoEndereco"/>
                    </html:select>
                </td>
                <td colspan="3"></td>
            </tr>
            <tr>
                <td>Tipo Logradouro:</td>
                <td><html:text name="abaEndereco" property="endereco.nmTipoLogradouro" maxlength="255" size="15" styleId="nmTipoLogradouro" readonly="true"/></td>
                <td>T&iacute;tulo do Logradouro:</td>
                <td><html:text name="abaEndereco" property="endereco.nmTituloLogradouro" maxlength="255" size="15" styleId="nmTituloLogradouro" readonly="true"/></td>
            </tr>
            <tr>
                <td>Logradouro:</td>
                <td colspan="3"><html:text name="abaEndereco" property="endereco.nmLogradouro" maxlength="60" style="width:265px;" styleId="nmLogradouro" readonly="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>N&uacute;mero:&nbsp;<html:text name="abaEndereco" property="endereco.nrEndereco" maxlength="60" style="width:35px;" styleId="nrEndereco"/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Complemento:
                <html:text name="abaEndereco" property="endereco.dsEnderecoComplemento" maxlength="255" style="width:70px;" styleId="dsEnderecoComplemento"/>
                </td>
            </tr>
            <tr>
                <td>Bairro:</td>
                <td colspan="2">
                    <html:text name="abaEndereco" property="endereco.nmBairro" maxlength="60" size="35" styleId="nmBairro"/>
                </td>
                <td colspan="2">
                    Munic&iacute;pio:
                    <html:text name="abaEndereco" style="width:129px;" property="endereco.nmMunicipio" maxlength="255" size="35" styleId="nmMunicipio" readonly="true"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    CEP:
                    <html:text name="abaEndereco" property="endereco.nrCEP" maxlength="9" onKeyUp="checaCEP(this)" size="8" styleId="nrCEP" readonly="true"/>
                    <script language="javascript">
                        checaCEP(document.getElementById("nrCEP"))
                    </script>
                </td>
            </tr>
            <tr>
                <td>Estado (UF):</td>
                <td>
                    <html:hidden name="abaEndereco" property="idUFSelecionado"/>
                    <html:select property="idUFSelecionado" disabled="true">
                        <option value=""></option>
                        <html:options collection="UFs" property="UFVO.idUF" labelProperty="UFVO.sgUF"/>
                    </html:select>
                </td>
                <td colspan="3">
                    Pa&iacute;s&nbsp;:
                    <html:hidden name="abaEndereco" property="idPaisSelecionado"/>
                    <html:select property="idPaisSelecionado" disabled="true">
                        <html:options collection="Paises" property="paisVO.idPais" labelProperty="paisVO.nmPais"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td colspan="5" align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cli_ine_pesquisar">
                        <img hspace="15" style="border:none;" onClick="pesquisaEndereco(); return false" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/>
                    </acesso:controlHiddenItem>
                    <img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;" hspace="5" onClick="limpar(); return false"/>
                    <acesso:controlHiddenItem nomeIdentificador="cli_ine_gravar">
                        <img src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_registrar_over.gif" style="border:none;" hspace="5" onClick="salvar('novo'); return false"/>
                    </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
   </html:form>
</acesso:controlHiddenItem>
</body>