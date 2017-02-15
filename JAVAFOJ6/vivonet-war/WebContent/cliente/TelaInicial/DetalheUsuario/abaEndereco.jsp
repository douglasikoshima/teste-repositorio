<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                top.frameApp.oculta_div();
                if(document.getElementById('inReload').value=="TRUE"){
                    parent.document.getElementById('inReloadTOTAL').value='TRUE';
                }
            -->
        </SCRIPT>
        
        <script language="javaScript">
            function validaDelete(){
                if(document.forms[0].idEndereco.length){
                    return true;
                }else{
                    return false;
                }
            }
        
            function acao(acao, id, sincronizado){
                if (acao!="novo"){        
                    
                    if (acao=="excluir"){
                        if(validaDelete()){
                            if (window.confirm("Confirma remoção do item?")){
                                if (sincronizado == "1"){
                                    alert('Esta informação está sincronizada com o Billing. Alterações não são permitidas aqui!')
                                }else{
                                    if(document.forms[0].id.length){
                                        document.forms[0].action="loadEndereco.do?tipo="+acao+"&idArray="+id+"&idEndereco="+document.forms[0].id[id].value;
                                    }else{
                                        document.forms[0].action="loadEndereco.do?tipo="+acao+"&idArray="+id+"&idEndereco="+document.forms[0].id.value;
                                    }
                                    parent.parent.mostrar_div();
                                    document.forms[0].method = "POST";
                                    document.forms[0].submit();
                                }
                            }
                        }else{
                            alert('Único endereço da pessoa! Exclusão NÃO permitida!');
                        }
                    }
                    else{                    
                        dvEndereco.style.display = '';
                        document.forms[0].target = "ifrmEndereco";
                        if(document.forms[0].id.length){
                            document.forms[0].action="loadEndereco.do?tipo="+acao+"&idArray="+id+"&idEndereco="+document.forms[0].id[id].value+"&sincronizado="+sincronizado;
                        }else{
                            document.forms[0].action="loadEndereco.do?tipo="+acao+"&idArray="+id+"&idEndereco="+document.forms[0].id.value+"&sincronizado="+sincronizado;
                        }
                        parent.parent.mostrar_div();
                        document.forms[0].method = "POST";
                        document.forms[0].submit();
                    }
                    
                }
                else{
                    dvEndereco.style.display = '';
                    document.forms[0].target = "ifrmEndereco";
                    document.forms[0].action="loadEndereco.do?tipo="+acao;
                    parent.parent.mostrar_div();
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            }
        </script>
    </head>
    
    <body rightmargin="0" leftmargin="0" topmargin="0">    
        <script>
            document.body.style.backgroundColor = '#E3ECF4';    
        </script>
    <acesso:controlHiddenItem nomeIdentificador="cli_aend_verpagina">
        <form id="fClienteEndereco" action="loadEndereco.do" onSubmit="return false;" method="post">
         <html:hidden name="abaEndereco" property="idPessoa"/>
         <html:hidden name="abaEndereco" property="inReload" styleId="inReload"/>
         <logic:equal name="abaEndereco" property="inMsgRetorno" value="true">
            <script>
                <logic:notEmpty name="abaEndereco" property="dsMsgRetorno">
                alert('<bean:write name="abaEndereco" property="dsMsgRetorno"/>');
                </logic:notEmpty>
                <logic:empty name="abaEndereco" property="dsMsgRetorno">
                alert('Erro ao adicionar endereço!');
                </logic:empty>
            </script>
        </logic:equal>
            <table border="0" cellpadding="0" bgcolor="white"cellspacing="1">
                <tr>
                    <td>            
                        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="730" layoutHeight="140" tableWidth="730" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left" width="20%" type="string">Tipo Endereço</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="50%" type="string">Endereço</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="20%" type="string">Município</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate name="abaEndereco" property="abaEndereco" indexId="idx" id="aba">
                                <bean:define id="addrTit" name="aba" property="nmTituloLogradouro" type="java.lang.String"/>
                                    <vivo:tbRow key="Linha">
                                        <vivo:tbRowColumn><bean:write name="aba" property="tipoEnderecoVO.dsTipoEndereco"/></vivo:tbRowColumn>
                                        <logic:equal name="aba" property="nmTipoLogradouro" value="<%=addrTit%>">
                                        <vivo:tbRowColumn>
                                            <bean:write name="aba" property="nmTituloLogradouro"/> <bean:write name="aba" property="nmLogradouro"/>, <bean:write name="aba" property="nrEndereco"/><html:hidden name="aba" property="idEndereco" styleId="id"/>
                                        </vivo:tbRowColumn>
                                        </logic:equal>
                                        <logic:notEqual name="aba" property="nmTipoLogradouro" value="<%=addrTit%>">
                                        <vivo:tbRowColumn>
                                            <bean:write name="aba" property="nmTipoLogradouro"/> <bean:write name="aba" property="nmTituloLogradouro"/> <bean:write name="aba" property="nmLogradouro"/>, <bean:write name="aba" property="nrEndereco"/><html:hidden name="aba" property="idEndereco" styleId="id"/>
                                        </vivo:tbRowColumn>
                                        </logic:notEqual>
                                        <vivo:tbRowColumn><bean:write name="aba" property="nmMunicipio"/></vivo:tbRowColumn>  
                                        <vivo:tbRowColumn>
                                            <acesso:controlHiddenItem nomeIdentificador="cli_aend_alterar">
                                            <logic:equal name="aba" property="inSincronismo" value="1">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" onclick="acao('alterar',<%=idx%>,1)" style="border:none;cursor:hand;"/>
                                            </logic:equal>
                                            <logic:equal name="aba" property="inSincronismo" value="0">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" onclick="acao('alterar',<%=idx%>,0)" style="border:none;cursor:hand;"/>
                                            </logic:equal>
                                            </acesso:controlHiddenItem>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <acesso:controlHiddenItem nomeIdentificador="cli_aend_excluir">
                                            <logic:equal name="aba" property="inSincronismo" value="1">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" onclick="acao('excluir',<%=idx%>,1)" style="border:none;cursor:hand;"/>
                                            </logic:equal>
                                            <logic:equal name="aba" property="inSincronismo" value="0">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" onclick="acao('excluir',<%=idx%>,0)" style="border:none;cursor:hand;"/>
                                            </logic:equal>
                                            </acesso:controlHiddenItem>
                                        </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>                                                                                                   
                            </vivo:tbRows>
                        </vivo:tbTable>
                    </td>
                </tr>
            </table>
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
            
            <table width="750" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                <tr>
                    <td valign="middle" width="100"><b>&nbsp;Legendas:</b></td>
                    <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle">&nbsp;Alterar Endereço</td>
                    <td width="519"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle">&nbsp;Excluir Endereço</td>
                </tr>
            </table>
            
            <table width="750" height="20">
                <tr>                    
                    <td align="right" valign="baseline">
                    <acesso:controlHiddenItem nomeIdentificador="cli_aend_incluir">
                        <img hspace="13" vspace="3" onClick="acao('novo', 999);" src="/FrontOfficeWeb/resources/images/bt_incluir_endereco_nrml.gif" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>            
        </form>
    </acesso:controlHiddenItem>
    </body>
    <vivo:quadroFlutuante id="dvEndereco" idIframe="ifrmEndereco" width="745" height="212" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Endere&ccedil;o > Inclusão / Alteração" onclick="document.forms[0].target='fraSubAbas'"/>
</html>