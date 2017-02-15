<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%> 
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="submotivoForm" type="admsistemas.parametrizacoes.motivos.MotivosController.SubmotivoForm" />
<bean:define id="lista" name="Form" property="submotivos" />

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/controleEventos.js"></script>
    <SCRIPT FOR=window EVENT=onload  LANGUAGE="JScript">
        if (document.getElementById('codigo').value > 0) {
            $('btAtualizar').style.display = "";
            $('btSalvar').style.display = "none";
        } else {
            $('btAtualizar').style.display = "none";
            $('btSalvar').style.display = "";
        }
    </SCRIPT>   
    <script type="text/javascript" language="javascript">

        function atualizaStatusSubmotivo(id,status) {            
            document.forms[0].action = 'atualizaStatusSubmotivo.do?id='+id+'&status='+status;
            document.forms[0].submit();
        } 

        function alterarSubmotivo(id) {    
            document.forms[0].action = 'alterarSubmotivo.do?id='+id;
            document.forms[0].submit();
        }

        function gravarSubmotivo() {
            var f = document.forms[0];
            if (validarFormulario()) {
                document.forms[0].submit();
            }
        }
        
        function validarFormulario() {
            if (trim($F('nomeSubmotivo'))=="") {
                alert("Campo Submotivo é de preenchimento obrigatório");
                $('nomeSubmotivo').focus();
                return false;
            }
            return true;     
        }      

    </script>
</head>
<body style="background-color:#ededed;">
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <form action="gravarSubmotivo.do" method="post">
        <html:hidden name="Form" property="pai" styleId="menu" />
        <html:hidden name="Form" property="codigo" styleId="codigo" />
        <table width="400px" align="left">
            <tr>
                <td>
                    Motivo: <html:text name="Form" property="nomePai" styleId="nomeMotivo" maxlength="50" style="width:100px;margin-left:3px;" readonly="true"/><br>
                    Submotivo: <html:text name="Form" property="nome" styleId="nomeSubmotivo" maxlength="50" style="width:100px;margin-left:3px;" />
                    <img onclick="gravarSubmotivo(); return false;"  align="middle" id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" style="border:none;cursor:pointer;"/>
                    <img onclick="gravarSubmotivo(); return false;"  align="middle" id="btAtualizar" src="/FrontOfficeWeb/resources/images/bt_atualizar_nrml.gif" style="border:none;display:none;cursor:pointer;"/>
                </td>
            </tr>    
            <tr>
                <td>
                    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="750" layoutHeight="200" tableWidth="749" styleId="tableTitle" sortable="true" >
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="10%" type="string">Código</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="75%" type="string">Descrição do Motivo</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="5%" type="string">Alterar</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Status</vivo:tbHeaderColumn>
                        </vivo:tbHeader>       
                        <vivo:tbRows>   
                            <logic:iterate name="lista" id="motivo" indexId="ctr">
                                <vivo:tbRow key="campanha" idClass="classListaCampanhas">
                                    <vivo:tbRowColumn><bean:write name="motivo" property="codigo" format="000" /></vivo:tbRowColumn>
                                    <vivo:tbRowColumn><bean:write name="motivo" property="nome"/></vivo:tbRowColumn> 
                                    <vivo:tbRowColumn>
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" style="cursor:pointer;" onmouseup="alterarSubmotivo(<bean:write name="motivo" property="codigo" format="#" />);" />          
                                    </vivo:tbRowColumn>                                 
                                    <vivo:tbRowColumn>
                                        <logic:equal value="0" name="motivo" property="inativo">
                                            <input type="checkbox" name="inFolhaForm" id="checkboxFolha" value="0" onclick="atualizaStatusSubmotivo(<bean:write name="motivo" property="codigo" format="#" />,1);">    
                                        </logic:equal>
                                        <logic:equal value="1" name="motivo" property="inativo">                                    
                                            <input type="checkbox" name="inFolhaForm" id="checkboxFolha" value="0" onclick="atualizaStatusSubmotivo(<bean:write name="motivo" property="codigo" format="#" />,0);" checked>    
                                        </logic:equal>
                                    </vivo:tbRowColumn>                                                                 
                                </vivo:tbRow>
                            </logic:iterate>       
                        </vivo:tbRows>                                                    
                    </vivo:tbTable>             
                </td>
            </tr>           
        </table>    
    </form>      
    <vivo:alert atributo="msgStatus" scope="request" afterFunction="closeWindow()" />
    <vivo:alert atributo="msgErro" scope="request" />
    <vivo:alert atributo="msgRetorno" scope="request" />
</body>
</html>