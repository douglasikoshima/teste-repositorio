<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="abaContato" type="extworkflw.RegistrarContato.RegistrarContatoController.AbaContato"/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javaScript">            
            function acao(acao, id, obj, tipoComm){
                if (acao!="novo"){        
                    if (document.forms[0].sinc.length && document.forms[0].sinc[id].value=="1"){
                        alert('Esta informação está sincronizada com o Billing. Alterações não são permitidas aqui!');
                    
                    }else if(document.forms[0].sinc.value == "1"){
                        alert('Esta informação está sincronizada com o Billing. Alterações não são permitidas aqui!');

                    }else{        
                        if (acao=="excluir"){
                            if (window.confirm("Confirma remoção do item?")){                        
                                var dsComunicacao;
                                //var dsTipoComunicacao = obj.parentNode.parentNode.childNodes[1].innerText;
                                var dsTipoComunicacao = tipoComm;
        
                                if (trim(dsTipoComunicacao) == "CELULAR") {
                                    parent.inCelular = true;
                                }
        
                                if (document.forms[0].sinc.length){
                                    document.forms[0].action="loadContato.do?tipo="+acao+"&idArray="+id+"&idComunicacao="+document.forms[0].id[id].value;
                                    dsComunicacao = document.forms[0].idT[id].value + ',' + document.forms[0].ds[id].value;
                                
                                }else{
                                    document.forms[0].action="loadContato.do?tipo="+acao+"&idArray="+id+"&idComunicacao="+document.forms[0].id.value;
                                    dsComunicacao = document.forms[0].idT.value + ',' + document.forms[0].ds.value;
                                }
                                //Liga animação
                                var aOptComponentsParentSelec = parent.ifrmAbas.document.forms["registrarContatoForm2"].elements["comunicacaoSelecionada"];
                                var len = aOptComponentsParentSelec.options.length;
                                for( x = 0; x < len; x++ ){
                                    if (aOptComponentsParentSelec.options(x).value == dsComunicacao){
                                        aOptComponentsParentSelec.options.remove(x);
                                        break;
                                    }
                                }

                                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                                document.forms[0].submit();
                            }
                        
                        }else{
                            dvContato.style.display = '';
                            document.forms[0].target = "ifrmContato";
                            if (document.forms[0].sinc.length){
                                document.forms[0].action="loadContato.do?tipo="+acao+"&idArray="+id+"&idComunicacao="+document.forms[0].id[id].value;
                            }else{
                                document.forms[0].action="loadContato.do?tipo="+acao+"&idArray="+id+"&idComunicacao="+document.forms[0].id.value;
                            }
                            //Liga animação
                            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                            document.forms[0].submit();
                        }
                    }

                }else{
                    //Liga animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    dvContato.style.display = '';
                    document.forms[0].target = "ifrmContato";
                    document.forms[0].action="loadContato.do?tipo="+acao;
                    document.forms[0].submit();
                }
            }
        </script>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
        <script>
            document.body.style.backgroundColor = '#E3ECF4';
        </script>
        <form action="loadContato.do" name="abaContato" method="post">
        <html:hidden name="Form" property="idPessoa"/>
        <table border="0" cellpadding="0" bgcolor="white" cellspacing="1">
            <tr>
                <td>
                    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="725" layoutHeight="200" tableWidth="725" styleId="tableTitle" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="5%" type="string">Seq.</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="50%" type="string">Tipo Contato</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="40%" type="string">Descrição Contato</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate id="aba" name="Form" property="abaContato" indexId="idx">
                                <vivo:tbRow key="Linha">
                                    <vivo:tbRowColumn><bean:write name="aba" property="nrSequencia"/></vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="aba" property="tipoComunicacaoVO.dsTipoComunicacao"/>
                                        <html:hidden name="aba" property="inSincronismo" styleId="sinc"/>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="aba" property="dsContato"/>
                                        <html:hidden name="aba" property="idComunicacao" styleId="id"/>
                                        <html:hidden name="aba" property="tipoComunicacaoVO.idTipoComunicacao" styleId="idT"/>
                                        <html:hidden name="aba" property="dsContato" styleId="ds"/>
                                    </vivo:tbRowColumn>                                                                                        
                                    <vivo:tbRowColumn>                                        
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" onClick="acao('alterar',<%=idx%> ,this);" style="cursor:pointer;"/>                                        
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>                                        
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" onClick="acao('excluir',<%=idx%>,this,'<bean:write name='aba' property='tipoComunicacaoVO.dsTipoComunicacao'/>')" style="cursor:pointer;">                                        
                                    </vivo:tbRowColumn>
                                </vivo:tbRow>
                            </logic:iterate>                                                                
                        </vivo:tbRows>
                    </vivo:tbTable>
                </td>
            </tr>
        </table>

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>

        <table width="100%" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
            <tr>
                <td valign="middle" width="90"><b>&nbsp;Legendas:</b></td>
                <td><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle">&nbsp;Alterar Contato</td>
                <td><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle">&nbsp;Excluir Contato</td>                
            </tr>
        </table>

        <table width="100%" height="20" bgcolor="#E3ECF4">
            <tr>
                <td align="right" valign="baseline">                    
                        <img vspace="5" onclick="acao('novo', 999)" src="/FrontOfficeWeb/resources/images/bt_adcontato_nrml.gif" border="0" style="cursor:pointer;"/>                    
                </td>
            </tr>
        </table>
    </form>    
    <!--Form e Quadro Flutuante-->
    <vivo:quadroFlutuante id="dvContato" idIframe="ifrmContato" width="720" height="212" spacesTop="0" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Contato > Adicionar" />
    <script>
        parent.ifrmAbas.desativar_combos();
        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    </script>
    </body>
</html>