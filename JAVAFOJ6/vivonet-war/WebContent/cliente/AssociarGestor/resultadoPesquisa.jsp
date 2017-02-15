<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="ErroForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultorForm.associarCR"/>
    <script language="JavaScript">
            function validaTratar(){
                
                var valor = null;
                
                if(document.forms[0].clientesSelecionados.length){
                    for (var i=0; i < document.forms[0].clientesSelecionados.length; i++){                   
                        if (document.forms[0].clientesSelecionados[i].checked){
                            valor = document.forms[0].clientesSelecionados[i].value;                          
                        }
                    }
                }else{
                    if (document.forms[0].clientesSelecionados.checked){
                        valor = document.forms[0].clientesSelecionados.value;                        
                    }
                }
                
                if(valor != null){
                    return true;
                }else{
                    return false;
                }
            }
            
            function irAssociar(){
                if(validaTratar()){
                    document.forms[0].action="inlcuirAlterarRelacionamento.do";
                    document.forms[0].target="ifrmIARelacionamento";
                    parent.dvInclusao.style.display = '';
                    parent.mostrar_div();
                    document.forms[0].submit();
                }else{
                    alert('Selecione um ou mais clientes!');
                }
            }
            
            function excluiAssociar(){
                if(validaTratar()){
                    document.forms[0].action="salvar.do?exlcuirCR=TRUE";
                    parent.mostrar_div();
                    document.forms[0].submit();
                }else{
                    alert('Selecione um ou mais clientes!');
                }
            }
    </script>
    <script for="window" event="onload">
        parent.oculta_div();
        if(!document.getElementById('selecionar')){
            alert('A pesquisa não retornou nenhum resultado!');
        }
    </script>
    
<html>
    <form action="pesquisaClientes.do">
    <head>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body leftmargin="0" topmargin="0" bgcolor="#F7F9FA">
        <acesso:controlHiddenItem nomeIdentificador="cli_rpcr_verpagina">
        <logic:empty name="ErroForm" property="erro">
        <bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultorForm.associarCR" type="br.com.vivo.fo.cliente.vo.AssociarCRVODocument.AssociarCRVO"/>
        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="765" layoutHeight="140" tableWidth="765" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="5%" type="string"></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="Center" width="20%" type="string">Nome</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="Center" width="10%" type="string">Tipo Documento</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="Center" width="15%" type="string">Nº Documento</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="Center" width="15%" type="string">Segmentação</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="Center" width="15%" type="string">Carteirização</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="20%" type="string">Consultor de Relacionamento</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
            <logic:iterate id="clientes" name="Form" property="clientesArray" indexId="idx">
            <vivo:tbRow key="Linha">
                <vivo:tbRowColumn><input type="checkbox" class="radio" name="clientesSelecionados" value="<bean:write name="clientes" property="idCliente"/>" id="selecionar"></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="clientes" property="nmCliente"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="clientes" property="dsTipoDocumento"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="clientes" property="nrDocumento"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="clientes" property="dsSegmentacao"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="clientes" property="dsCarteirizacao"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="clientes" property="nmConsultor"/></vivo:tbRowColumn>
            </vivo:tbRow>
            </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        <table width="100%">
            <tr>
                <td align="right">
                <%if (Form.getClientesArray() != null && Form.getClientesArray().length > 0) {%>
                    <acesso:controlHiddenItem nomeIdentificador="cli_rpcr_incluir">
						<img style="border:0px;"
						     vspace="10"
							 hspace="5"
							 onClick="irAssociar(); return false"
							 src="/FrontOfficeWeb/resources/images/bt_associar_nrml.gif" />
					</acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="cli_rpcr_excluir">
						<img style="border:0px;"
							 vspace="10"
							 hspace="5"
							 onClick="excluiAssociar(); return false"
							 src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" />
					</acesso:controlHiddenItem>
                <%}%>
                </td>
            </tr>
        </table>
        </logic:empty>
        <logic:notEmpty name="ErroForm" property="erro">
            <table width="100%" align="center" height="100%" border="0" bgcolor="#E3ECF4">
                <tr>
                    <td align="center"><b>Foram encontrados mais de 50 registros para os dados de pesquisa selecionados. Por favor refine a pesquisa!<b></td>
                </tr>
            </table>
        </logic:notEmpty>
    </acesso:controlHiddenItem>
    </body>
    </form>
</html>