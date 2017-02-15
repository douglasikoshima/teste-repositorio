<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="abaContato" type="cliente.TelaInicial.DetalheCliente.DetalheClienteController.AbaContato"/>
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
                if(document.forms[0].idComunicacao.length){
                    return true;
                }else{
                    return false;
                }
            }
            
            function mudaSequencia(direcao,id){
                document.forms[0].action="controlaSequencia.do?direcao="+direcao+"&idArray="+id+"&idComunicacao="+document.forms[0].id[id].value;
                parent.parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }
            
            function acao(acao, id){
                if (acao!="novo"){
                    if (acao=="excluir"){
                        if(validaDelete()){
                            if (window.confirm("Confirma remoção do item?")){
                                if(document.forms[0].id.length){
                                    document.forms[0].action="loadContato.do?tipo="+acao+"&idArray="+id+"&idComunicacao="+document.forms[0].id[id].value;
                                }else{
                                    document.forms[0].action="loadContato.do?tipo="+acao+"&idArray="+id+"&idComunicacao="+document.forms[0].id.value;
                                }
                                parent.parent.mostrar_div();
                                document.forms[0].method = "POST";
                                document.forms[0].submit();
                            }
                        }else{
                            alert('Único contato da pessoa! Exclusão NÃO permitida!');
                        }
                    }
                    else{
                        dvContato.style.display = '';
                        document.forms[0].target = "ifrmContato";
                        if(document.forms[0].id.length){
                            document.forms[0].action="loadContato.do?tipo="+acao+"&idArray="+id+"&idComunicacao="+document.forms[0].id[id].value;
                        }else{
                            document.forms[0].action="loadContato.do?tipo="+acao+"&idArray="+id+"&idComunicacao="+document.forms[0].id.value;
                        }
                        parent.parent.mostrar_div();
                        document.forms[0].method = "POST";
                        document.forms[0].submit();
                    }
                }
                else{
                    dvContato.style.display = '';
                    document.forms[0].target = "ifrmContato";
                    document.forms[0].action="loadContato.do?tipo="+acao;
                    parent.parent.mostrar_div();
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            }
            
            function refresh(idPessoa){
                document.forms[0].target ='fraSubAbas';
                document.forms[0].action ='loadContato.do?reload=OK&&idPessoaCliente='+idPessoa;
                parent.parent.mostrar_div();
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }
            
            function msg() {
                alert('Esta informação está sincronizada com o Billing. Alterações não são permitidas aqui!');
            }
            
        </script>
    </head>
    
    <body rightmargin="0" leftmargin="0" topmargin="0">
    <script>
        document.body.style.backgroundColor = '#E3ECF4';    
    </script>
    <acesso:controlHiddenItem nomeIdentificador="cli_ac_verpagina">
        <form action="loadContato.do" method="post" onsubmit="return false;">
        <div style="z-index:999999999;position:absolute;top:0;left:0;">
            <iframe frameborder="0" width="1" height="1" id="executa" name="executa" src="" style="allow-transparency:true;"></iframe>
        </div>
        <html:hidden name="abaContato" property="idPessoa" styleId="idPessoa"/>
        <html:hidden name="abaContato" property="inReload" styleId="inReload"/>
        <table width="751" border="0" cellpadding="0" bgcolor="white"cellspacing="1">
            <tr>
                <td>
                    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="750" layoutHeight="140" tableWidth="750" styleId="tableTitle" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="5%" type="string">Seq.</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="45%" type="string">Tipo do Contato</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="40%" type="string">Descrição do Contato</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>                                
                            <logic:iterate name="abaContato" property="abaContato" indexId="idx" id="aba">
                                <vivo:tbRow key="Linha">
                                    <vivo:tbRowColumn>
                                    <% if (Form.getAbaContato().length>1){ %>
                                    <img src="/FrontOfficeWeb/resources/images/seq_down.gif" border="0" onClick="mudaSequencia('down', <%=idx%>)"/>
                                    <img src="/FrontOfficeWeb/resources/images/seq_up.gif" border="0" onClick="mudaSequencia('up', <%=idx%>)"/>
                                    <% } %>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="aba" property="tipoComunicacaoVO.dsTipoComunicacao"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="aba" property="dsContato"/>
                                        <html:hidden name="aba" property="idComunicacao" styleId="id"/>
                                    </vivo:tbRowColumn>   
                                    <vivo:tbRowColumn>
                                        <acesso:controlHiddenItem nomeIdentificador="cli_ac_alterar">
                                            <logic:equal name="aba" property="inSincronismo" value="0">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" onclick="acao('alterar', <%=idx%>)" style="border:none;cursor:hand;"/>
                                            </logic:equal>
                                            <logic:equal name="aba" property="inSincronismo" value="1">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" onclick="msg(); return false;" style="border:none;cursor:hand;"/>
                                            </logic:equal>
                                        </acesso:controlHiddenItem>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <acesso:controlHiddenItem nomeIdentificador="cli_ac_excluir">
                                            <logic:equal name="aba" property="inSincronismo" value="0">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" onclick="acao('excluir', <%=idx%>)" style="border:none;cursor:hand;"/>
                                            </logic:equal>
                                            <logic:equal name="aba" property="inSincronismo" value="1">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" onclick="msg();return false;" style="border:none;cursor:hand;"/>
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
            
        <table width="769" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
            <tr>
                <td valign="middle" width="100"><b>&nbsp;Legendas:</b></td>
                <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle">&nbsp;Alterar Contato</td>
                <td width="519"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle">&nbsp;Excluir Contato</td>
            </tr>
        </table>
        
        <table width="760" height="20" bgcolor="#E3ECF4">
            <tr>                    
                <td align="right" valign="baseline">
                <acesso:controlHiddenItem nomeIdentificador="cli_ac_incluir">
                    <img vspace="5" onclick="acao('novo', 999)" src="/FrontOfficeWeb/resources/images/bt_incluir_contato_nrml.gif" style="border:none;cursor:hand;"/>
                </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        </form>
    </acesso:controlHiddenItem>
    <!--Form e Quadro Flutuante-->    
    <vivo:quadroFlutuante id="dvContato" idIframe="ifrmContato" width="767" height="212" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Contato > Inclusão / Alteração" onclick="document.forms[0].target='fraSubAbas'"/>
    </body>
</html>
