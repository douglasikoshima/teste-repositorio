<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"   prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="prePagoClienteLinhaForm"/>
<html:html>
    <title>Pré Pago</title>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script>
         function setarDadosTransfT(idClienteDestino, inTipoPessoa){
            parent.document.forms[0].idClienteDestino.value=idClienteDestino;
            parent.document.forms[0].inTipoPessoa.value=inTipoPessoa;
        }
    </script>
 </head>

<body>
    
    <html:hidden name="Form" property="idPessoa" styleId="idPessoaSelecionada" value=""/>
    <html:hidden name="Form" property="inTipoPessoa" styleId="tipoPessoa" value=""/>
    <html:hidden name="Form" property="listaDocumento.documentoArray.dsDocumento" styleId="nrDocumento" value=""/>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <logic:equal name="Form" property="inErroPesquisa" value="TRUE">
        <script>
            alert('Muitos resultados de retorno! Favor refinar a pesquisa!');
        </script>
    </logic:equal>
    <logic:equal name="Form" property="inErroPesquisa" value="FALSE">
    <vivo:tbTable selectable="true" layoutWidth="745" layoutHeight="225" tableWidth="735" styleId="tableTitle" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="55%" type="string">Nome</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Documento</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Nº Documento</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate id="ListaClientes" name="Form" property="pesquisaPessoa.clientesPesquisadosArray" indexId="idx">
                <vivo:tbRow key="Linha">
                    <vivo:tbRowColumn><input type="radio" class="radio" name="clienteSelecionado" value="" onclick="document.getElementById('tipoPessoa').value='<bean:write name="ListaClientes" property="dsTipoPessoa"/>';document.getElementById('idPessoaSelecionada').value='<bean:write name="ListaClientes" property="idPessoa"/>';document.getElementById('nrDocumento').value='<bean:write name="ListaClientes" property="nrDocumento"/>'; setarDadosTransfT(<bean:write name="ListaClientes" property="idPessoa"/>)"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="ListaClientes" property="nmNome"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="ListaClientes" property="dsTipoDocumento"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="ListaClientes" property="nrDocumento"/></vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>
    </logic:equal>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>        
</body>
</html:html>