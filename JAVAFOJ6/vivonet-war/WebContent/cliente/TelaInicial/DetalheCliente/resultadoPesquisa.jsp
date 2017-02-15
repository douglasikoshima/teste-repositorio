<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="ErroForm" name="abaEndereco" property="pesquisaEndereco"/>

<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        top.frameApp.oculta_div();
    -->
</SCRIPT>

<html>
    <head>
        <title></title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js" ></script>

        <script>
            <!--
            function get_radio_value(){
                if(document.formPesqEndereco.enderecoSelecionado){
                    if(document.formPesqEndereco.enderecoSelecionado.length){
                        for (var i=0; i < document.formPesqEndereco.enderecoSelecionado.length; i++){
                           if (document.formPesqEndereco.enderecoSelecionado[i].checked)
                              {
                              var rad_val = document.formPesqEndereco.enderecoSelecionado[i].value;
                              return rad_val;
                              }
                        }
                    }else{
                        if (document.formPesqEndereco.enderecoSelecionado.checked){
                            var rad_val = document.formPesqEndereco.enderecoSelecionado.value;
                            return rad_val;
                        }
                    }
                }else{
                    return false;
                }
            }

            function utilizaEndereco(valor){
                if(valor){
                    selecionaEndereco(valor);
                }else{
                    alert('Execute uma pesquisa e/ou selecione um endereço!');
                    return false;
                }
            }

            function selecionaEndereco(idArray){
                var f;
                if(idArray){
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    f = window.top.frameApp.iframePopupTI.ifrmProfile.document.forms[0];
                    f.action = '/FrontOfficeWeb/cliente/TelaInicial/DetalheCliente/pesquisaEndereco.do?pagina=nao&&idArray='+idArray;
                    f.target = 'ifrmEndereco';
                    f.method = "POST";
                    f.submit();
                    window.top.frameApp.iframePopupTI.document.getElementById('dvProfile').style.display = 'none';
                }else{
                    alert('Execute uma pesquisa e/ou selecione um endereço!');
                    return false;
                }
            }



            //-->
        </script>

    </head>

<body>
<form action="buscaEndereco.do" name="formPesqEndereco">

<acesso:controlHiddenItem nomeIdentificador="cli_rep_verpagina">

    <logic:empty name="ErroForm" property="erro">

        <bean:define id="Form" name="abaEndereco" property="pesquisaEndereco.listaEnderecos.enderecoVOArray"/>
        <bean:define id="FormEnd" name="abaEndereco"/>

        <logic:present parameter="filtroPesquisa.nrCEP">
           <logic:equal name="FormEnd" property="isAddrPresent" value="false">
                <logic:notEmpty name="ErroForm" property="noCep">
                  <script>
                        alert('CEP não encontrado!');
                  </script>
                </logic:notEmpty>
                <logic:empty name="ErroForm" property="noCep">
                  <logic:empty name="erro">
                    <script>
                        <logic:equal name="FormEnd" property="pesquisaBaseFO" value="false">
                            var parametrosPesquisa;
                            if (!parent.$F('cep').blank()) {
                                parametrosPesquisa = '\nCEP: ' + parent.$F('cep');
                            } else {
                                parametrosPesquisa  = '\nLogradouro: ' + parent.$F('logradouro').strip();
                                parametrosPesquisa += '\nMunicípio: ' + parent.$F('cidade').strip();
                                parametrosPesquisa += '\nUF: ' + parent.$('uf').options[parent.$('uf').selectedIndex].text;
                            }
                            alert('Endereço não localizado. Por favor, refine a pesquisa ou cadastre o endereço no novo ADM.' + parametrosPesquisa);
                        </logic:equal>
                        <logic:equal name="FormEnd" property="pesquisaBaseFO" value="true">
                        alert('A pesquisa não retornou resultados!');
                        </logic:equal>
                    </script>
                  </logic:empty>
                  <logic:notEmpty name="erro">
                    <script>
                        alert('<bean:write name="erro" />');
                    </script>
                  </logic:notEmpty>
                </logic:empty>
           </logic:equal>
         </logic:present>


        <vivo:tbTable  selectable="true" onRowClick="" layoutWidth="750" layoutHeight="350" tableWidth="750" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="5%" type="string"></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">CEP</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="25%" type="string">Localidade</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="25%" type="string">Logradouro</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">Lado</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="20%" type="string">Bairro</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%" type="string">UF</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <logic:iterate id="resultados" name="Form" indexId="idx">
                <bean:define id="addrTit" name="resultados" property="nmTituloLogradouro" type="java.lang.String"/>
                    <vivo:tbRow key="Linha">
                        <vivo:tbRowColumn><input name="enderecoSelecionado" type="radio" class="radio" value="<%=idx%>"></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="resultados" property="nrCEP"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="resultados" property="nmMunicipio"/></vivo:tbRowColumn>
                        <logic:equal name="resultados" property="nmTipoLogradouro" value="<%=addrTit%>">
                          <vivo:tbRowColumn><bean:write name="resultados" property="nmTituloLogradouro"/> <bean:write name="resultados" property="nmLogradouro"/></vivo:tbRowColumn>
                        </logic:equal>
                        <logic:notEqual name="resultados" property="nmTipoLogradouro" value="<%=addrTit%>">
                          <vivo:tbRowColumn><bean:write name="resultados" property="nmTipoLogradouro"/> <bean:write name="resultados" property="nmTituloLogradouro"/> <bean:write name="resultados" property="nmLogradouro"/></vivo:tbRowColumn>
                        </logic:notEqual>
                        <vivo:tbRowColumn><bean:write name="resultados" property="dsLado"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="resultados" property="nmBairro"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="resultados" property="UFVO.sgUF"/></vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

        <div align="right">
        <acesso:controlHiddenItem nomeIdentificador="cli_rep_selecionar">
        <img onClick="selecionaEndereco(get_radio_value()); return false" src="/FrontOfficeWeb/resources/images/bt_selecionar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_selecionar_over.gif" style="border:none;"/>
        </acesso:controlHiddenItem>
    </logic:empty>
    <logic:notEmpty name="ErroForm" property="erro">
        <table width="100%" align="center" height="100%" border="0" bgcolor="#E3ECF4">
            <tr>
                <td align="center"><b>Foram encontrados mais de 50 registros para os dados de pesquisa selecionados. Por favor refine a pesquisa!<b></td>
            </tr>
        </table>
    </logic:notEmpty>
    </div>

    <script type="text/javascript">
        document.body.style.backgroundColor = '#ededed';
    </script>
</acesso:controlHiddenItem>
</form>
</body>
</html>