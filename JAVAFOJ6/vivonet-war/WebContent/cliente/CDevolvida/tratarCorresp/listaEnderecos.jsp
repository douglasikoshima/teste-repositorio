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

<bean:define id="ErroForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="incluirForm.pesquisaEndereco"/>

<html>
    <head>
        <title></title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
        <script>            
            <!--
            function get_radio_value(){                
                if(document.forms[0].enderecoSelecionado){
                    if(document.forms[0].enderecoSelecionado.length){
                        for (var i=0; i < document.forms[0].enderecoSelecionado.length; i++){                   
                           if (document.forms[0].enderecoSelecionado[i].checked)
                              {
                              var rad_val = document.forms[0].enderecoSelecionado[i].value;
                              return rad_val;
                              }
                        }
                    }else{
                        if (document.forms[0].enderecoSelecionado.checked){
                            var rad_val = document.forms[0].enderecoSelecionado.value;
                            return rad_val;
                        }
                    }
                }else{
                    return false;
                }
            }            
                                    
            function selEndereco(idArray){                
                if(idArray){
                    document.forms[0].action="buscaEnderecoCD.do?selecionado=TRUE&idArrayEndereco="+idArray;
                    document.forms[0].target="frameApp";
                    parent.parent.mostrar_div();
                    document.forms[0].submit();                
                    top.frameApp.$('divPopupTI').hide();
                }else{
                    alert('Execute uma pesquisa e/ou selecione um endereço!');
                    return false;
                }
            }
            
            //-->
        </script>
        <script for="window" event="onload">
            parent.parent.oculta_div();
        </script>

    </head>

<html:form action="/cliente/CDevolvida/tratarCorresp/buscaEnderecoCD.do">
    <body>
    <acesso:controlHiddenItem nomeIdentificador="cli_liend_verpagina">
        <logic:empty name="ErroForm" property="erro">
            <bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="incluirForm.pesquisaEndereco.listaEnderecos.enderecoVOArray"/>
            <vivo:tbTable  selectable="true" onRowClick="" layoutWidth="740" layoutHeight="300" tableWidth="740" styleId="tableTitle" sortable="true">
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
                                  <vivo:tbRowColumn><bean:write name="resultados" property="nmTipoLogradouro"/> <bean:write name="resultados" property="nmLogradouro"/></vivo:tbRowColumn>
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
            <acesso:controlHiddenItem nomeIdentificador="cli_liend_selecionar">
            <img hspace="22" onClick="selEndereco(get_radio_value());" src="/FrontOfficeWeb/resources/images/bt_selecionar_nrml.gif" style="border:none;cursor:hand;"/>
            </acesso:controlHiddenItem>
        </logic:empty>
        <div align="right">
        <logic:notEmpty name="ErroForm" property="erro">
            <table width="100%" align="center" height="100%" border="0" bgcolor="#E3ECF4">
                <tr>
                    <td align="center"><b>Foram encontrados mais de 50 registros para os dados de pesquisa selecionados. Por favor refine a pesquisa!</b></td>
                </tr>
            </table>
        </logic:notEmpty>
        </div>
        
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>        
    </acesso:controlHiddenItem>
    </body>
</html:form>
</html>