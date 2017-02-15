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

<html>

<bean:define id="pesqEndereco" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="pesquisarEnderecoForm.pesqEndereco"/>

<head>

<title></title>
<link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
<script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>      

<script>
    <!--
    function get_radio_value() {
        for (var i=0; i < document.formPesqEndereco.enderecoSelecionado.length; i++) {
            if (document.formPesqEndereco.enderecoSelecionado[i].checked) {
                var rad_val = document.formPesqEndereco.enderecoSelecionado[i].value;
                return rad_val;
            }
        }
    }

    function recuperarEndereco() {
        endereco = document.forms[0].enderecoSelecionado;

        indexEndereco = '0';

        if (endereco.length) {
            for(i = 0; i < endereco.length; i ++) {
                if (endereco[i].checked)
                    indexEndereco = i;
            }
        }

        if (top.frameApp.dvAnimarAguarde != null)
            top.frameApp.startAnimation();

        parent.parent.divPesquisaEndereco.style.display = 'none';
//        parent.ifrResultadoPesquisa.style.display = 'none';

        formulario = parent.parent.document.forms[0];

        formulario.indexEndereco.value = indexEndereco;
        formulario.target = '';
        formulario.action = 'recuperarEndereco.do';

        formulario.submit();
    }
    //-->
</script>

<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
    if (top.frameApp.dvAnimarAguarde != null)
        top.frameApp.stopAnimation();
//    parent.controlCombos();
//    if('<abean:write name="erro"/>' != null && '<abean:write name="erro"/>' != '') {
//        alert('<abean:write name="erro"/>');
//    }
    -->
</SCRIPT>

</head>

<form>

    <body onload="parent.oculta_div();">
      <acesso:controlHiddenItem nomeIdentificador="term_pesq_endr">

        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="675" layoutHeight="100" tableWidth="675" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="left" width="3%" type="string"></vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="10%" type="string">CEP</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="25%" type="string">Localidade</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="27%" type="string">Logradouro</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="10%" type="string">Lado</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="20%" type="string">Bairro</vivo:tbHeaderColumn>   
                    <vivo:tbHeaderColumn align="center" width="5%" type="string">UF</vivo:tbHeaderColumn>                                                             
            </vivo:tbHeader>

            <logic:notEmpty name="pesqEndereco" property="erro">
                <vivo:tbRows>
                    <vivo:tbRow key="linha">
                        <td colspan="5"><b><bean:write name="pesqEndereco" property="erro"/></b></td>
                    </vivo:tbRow>
                </vivo:tbRows>
            </logic:notEmpty>

            <logic:empty name="pesqEndereco" property="erro">
                <logic:notEmpty name="msg">                
                    <script>          
                        alert('<bean:write name="msg"/>');
                    </script>  
                    <vivo:tbRows>
                        <vivo:tbRow key="linha">
                            <td colspan="5"></td>
                        </vivo:tbRow>
                    </vivo:tbRows>
                </logic:notEmpty>

                <logic:empty name="msg">
                    <vivo:tbRows>
                        <logic:iterate id="endereco" name="pesqEndereco" property="listaEnderecos.enderecoVOArray" indexId="idx">
                            <bean:define id="addrTit" name="endereco" property="nmTituloLogradouro" type="java.lang.String"/>
                            <vivo:tbRow key="Linha">
                                <vivo:tbRowColumn><input name="enderecoSelecionado" id="enderecoSelecionado" type="radio" class="radio" value="<bean:write name="endereco" property="idEndereco" />"></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="endereco" property="nrCEP"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="endereco" property="nmMunicipio"/></vivo:tbRowColumn>
                            <%-- Se o titulo do logradouro for igual ao tipo, exibe apenas o titulo --%>
                            <logic:equal name="endereco" property="nmTipoLogradouro" value="<%=addrTit%>">
                                <vivo:tbRowColumn><bean:write name="endereco" property="nmTituloLogradouro"/> <bean:write name="endereco" property="nmLogradouro"/></vivo:tbRowColumn>
                            </logic:equal>
                            <%-- Se forem diferentes, exibe apenas o tipo e o titulo --%>
                            <logic:notEqual name="endereco" property="nmTipoLogradouro" value="<%=addrTit%>">
                                 <vivo:tbRowColumn><bean:write name="endereco" property="nmTipoLogradouro"/> <bean:write name="endereco" property="nmTituloLogradouro"/> <bean:write name="endereco" property="nmLogradouro"/></vivo:tbRowColumn>
                            </logic:notEqual>
                                <vivo:tbRowColumn><bean:write name="endereco" property="dsLado"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="endereco" property="nmBairro"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="endereco" property="UFVO.sgUF"/></vivo:tbRowColumn>
                            </vivo:tbRow>
                        </logic:iterate>
                    </vivo:tbRows>
                </logic:empty>
            </logic:empty>
        </vivo:tbTable>

        <div><img src="<%= request.getContextPath() %>/resources/images/transp.gif" width="1" height="5"></div>

        <div align="right">
        <logic:empty name="msg">
            <acesso:controlHiddenItem nomeIdentificador="term_pesq_endr_selecionar">
                <logic:empty name="erro">
                    <img src="/FrontOfficeWeb/resources/images/bt_selecionar_nrml.gif" onmouseout="/FrontOfficeWeb/resources/images/bt_selecionar_nrml.gif" onmouseover="/FrontOfficeWeb/resources/images/bt_selecionar_over.gif" style="border:none;" onClick="recuperarEndereco();"/>
                </logic:empty>
            </acesso:controlHiddenItem>
        </logic:empty>

        </div>
        
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
     
    </acesso:controlHiddenItem>

    <vivo:alert atributo="msgError" scope="request"/>

</body>

</form>

</html>
