<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%> 
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="motivoForm" type="admsistemas.parametrizacoes.motivos.MotivosController.MotivoForm" />
<bean:define id="lista" name="Form" property="motivos" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Parametrização Motivos e Submotivos"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>
    <netui-template:section name="headerSection">
    
    
<script type="text/javascript" language="javascript">

        function exibir(idMotivo) {
            $('divInclusaoAlteracao').style.display = "block";
            $('iframeInclusaoAlteracao').src = "submotivo.do?motivo=" + idMotivo;
        }
                
        function alterar(id) {    
            document.forms[0].action = 'alterar.do?id='+id;
            document.forms[0].submit();
        }
        
        function atualizaStatus(id,status) {            
            if (status == '0') {        
                
                if (confirm('Ao inativar este motivo todos os submotivos serão inativados. Deseja continuar mesmo assim ?')){
                    document.forms[0].action = 'atualizaStatus.do?id='+id+'&status='+status;      
                } else {
                    document.forms[0].action = 'begin.do';
                }
                
                document.forms[0].submit();                          
                
            } else {
                document.forms[0].action = 'atualizaStatus.do?id='+id+'&status='+status;
                document.forms[0].submit();                            
            }    
        }        
        
        function gravar () {
            var f = document.forms[0];

            if (validarFormulario()) {
                document.forms[0].submit();     
            }                
        }
        

        function validarFormulario() {
            if (trim($F('nome'))=="") {
                alert("Campo Motivo é de preenchimento obrigatório");
                $('nome').focus();
                return false;
            }
            return true;     
        }        
        
    </script>    

        <SCRIPT FOR=window EVENT=onload  LANGUAGE="JScript">
            if (document.getElementById('codigo').value > 0) {
                $('btAtualizar').style.display = "";
                $('btSalvar').style.display = "none";
            } else {
                $('btAtualizar').style.display = "none";
                $('btSalvar').style.display = "";
            }
        </SCRIPT>    
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="470" width="790" label="Sistema" operacoes="Parametrização Motivos e Submotivos" scroll="no">
            <form action="gravar.do" method="post" onsubmit="return false;">
                <html:hidden name="Form" property="codigo" styleId="codigo" />
                <table width="400px" align="left">
                    <tr>
                        <td>
                            Motivo: <html:text name="Form" property="nome" styleId="nome" maxlength="50" style="width:100px;margin-left:3px;" />
                            <img onclick="gravar();return false;" align="middle" id="btSalvar" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" style="border:none;cursor:pointer;"/>
                            <img onclick="gravar();return false;" align="middle" id="btAtualizar" src="/FrontOfficeWeb/resources/images/bt_atualizar_nrml.gif" style="border:none;display:none;cursor:pointer;"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <vivo:tbTable selectable="true" onRowClick="" layoutWidth="750" layoutHeight="290" tableWidth="749" styleId="tableTitle" sortable="true" >
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
                                            <vivo:tbRowColumn>
                                                <span title="<bean:write name="motivo" property="nome"/>">
                                                   <a href="" onclick="exibir(<bean:write name="motivo" property="codigo" format="#" />);return false;"> <bean:write name="motivo" property="nome"/> </a>
                                                </span>
                                            </vivo:tbRowColumn> 
                                            <vivo:tbRowColumn>
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" style="cursor:pointer;" onmouseup="alterar(<bean:write name="motivo" property="codigo" format="#" />);" />          
                                            </vivo:tbRowColumn>                                 
                                            <vivo:tbRowColumn>
                                                <logic:equal value="0" name="motivo" property="inativo">
                                                    <input type="checkbox" name="inFolhaForm" id="checkboxFolha" value="0" onclick="atualizaStatus(<bean:write name="motivo" property="codigo" format="#" />,1);">    
                                                </logic:equal>
                                                <logic:equal value="1" name="motivo" property="inativo">                                    
                                                    <input type="checkbox" name="inFolhaForm" id="checkboxFolha" value="0" onclick="atualizaStatus(<bean:write name="motivo" property="codigo" format="#" />,0);" checked>    
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
            <vivo:quadroFlutuante id="divInclusaoAlteracao"
                                  idIframe="iframeInclusaoAlteracao"
                                  width="775"
                                  height="480"
                                  spacesTop="0"
                                  spacesLeft="15"
                                  display="none"
                                  url="<%=request.getContextPath()%>"
                                  label="Parametrização Submotivos"
                                  onclick="return false;"/>            
            <iframe name="hiddenFrame" id="hiddenFrame" src="" width="0" height="0" marginheight="0" marginwidth="0" frameborder="0" style="display:none;"></iframe>            
        </vivo:sessao>
        <vivo:alert atributo="msgRetorno" scope="request" />
    </netui-template:section>
</netui-template:template>