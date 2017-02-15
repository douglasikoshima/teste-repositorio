<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fidelizacao.tld" prefix="fid"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="Form"	    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosClienteForm" />
<bean:define id="histAgend" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaHistoricoAgendamentoVO" />

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js" ></script>
        <script language="javascript">
            dia = new Date();
            mes = dia.getMonth() + 1;
            hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

            var hojeMinutos = dia.getHours().toString() + ":" + dia.getMinutes().toString()

            function salvar(obj){
                if(document.forms[0].elements["nomeContato"].value == ""){
                    alert("Favor preencher o campo Contato!");
                }else if(document.forms[0].elements["telefoneContato"].value == ""){
                    alert("Favor preencher o campo Telefone de Contato!");
                }else if(document.forms[0].elements["data"].value == ""){
                    alert("Favor selecionar uma Data!");
                }else if(!validaDataFinalEx(hoje,document.forms[0].elements["data"].value, hojeMinutos, document.forms[0].elements["horas"].value + ":" + document.forms[0].elements["minutos"].value)){
                    alert('Data / hora menor que a atual, favor corrigir!');
                }else{
                    valor = obj.href.split("?");
                    valor[1]?action = document.forms[0].action + "?" + valor[1]:action = document.forms[0].action;
                    document.forms[0].action = action;
                    document.forms[0].target="frmQuestionario";
                    parent.parent.mostrar_div();
                    document.forms[0].submit();
                    parent.voltar();
                }
            }

            function voltar(){
                 parent.voltar();
            }

            function verificarTmnMsg(obj){
                if(obj.value.length > 230){
                    obj.value = obj.value.substr(0,230);
                }
            }
         </script>
         <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
                <!--
                    top.frameApp.oculta_div();
                -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_acon_verpagina">
    <vivo:quadro id="qdMain" height="325" width="760" label="Agendamento de Contato" scroll="no">
        <div style="width:760px;height:325px;overflow:auto;margin-bottom:1px solid #adadad;">
        <form action="agendarContato.do" name="dadosClienteForm" method="post">
        <fid:table>
                <fid:tr bgColor="#545454">
                    <fid:headerTd>Linha</fid:headerTd>
                    <fid:headerTd>Segmentação</fid:headerTd>
                    <fid:headerTd>Rentabilidade</fid:headerTd>
                    <fid:headerTd>Contrato de Fidelização</fid:headerTd>
                    <fid:headerTd>Multa Contratual</fid:headerTd>
                    <fid:headerTd>Término do Contrato</fid:headerTd>
                    <fid:headerTd>Plano Serviço</fid:headerTd>
                    <fid:headerTd>Habilitação</fid:headerTd>
                </fid:tr>
                <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                        <fid:dataTd><bean:write name="Form" property="detalheLinha.numero"/></fid:dataTd>
                        <fid:dataTd><bean:write name="Form" property="detalheLinha.segmentacao"/></fid:dataTd>
                        <fid:dataTd><bean:write name="Form" property="detalheLinha.rentabilidade"/></fid:dataTd>
                        <fid:dataTd><bean:write name="Form" property="detalheLinha.contrato"/></fid:dataTd>
                        <fid:dataTd><bean:write name="Form" property="detalheLinha.valorMulta"/></fid:dataTd>
                        <fid:dataTd><bean:write name="Form" property="detalheLinha.dtFimContrato"/></fid:dataTd>
                        <fid:dataTd><bean:write name="Form" property="detalheLinha.plano"/></fid:dataTd>
                        <fid:dataTd><bean:write name="Form" property="detalheLinha.dtHabilitacao"/></fid:dataTd>
                    </fid:tr>
                </fid:table>
               
               	<div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

        <table width="750" cellpadding="0" cellspacing="0" align="center">
            <tr>
                <td width="120">Contato:</td>
                <td width="610" colspan="5">
                    <html:text style="width:610px;" name="Form" property="nomeContato" maxlength="255" styleClass="textfield"/>
                </td>
            </tr>
            <tr>
                <td>Telefone de Contato:</td>
                <td width="110"><html:text style="width:90px;" name="Form" property="telefoneContato" styleClass="textfield" onkeyup="maskPhoneNumberObj(this);" maxlength="14"/></td>
                <td width="40">Data:</td>
                <td width="120"><html:text name="Form" property="data" readonly="true" styleClass="textfield" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('data', '%d/%m/%Y');"></td>
                <td width="40">Hora:</td>
                <td nowrap width="317">
				 <html:select name="Form" property="horas">        
                    <html:option value="00"></html:option>
                    <html:option value="01"></html:option>
                    <html:option value="02"></html:option>
                    <html:option value="03"></html:option>
                    <html:option value="04"></html:option>
                    <html:option value="05"></html:option>
                    <html:option value="06"></html:option>
                    <html:option value="07"></html:option>
                    <html:option value="08"></html:option>
                    <html:option value="09"></html:option>
                    <html:option value="10"></html:option>
                    <html:option value="11"></html:option>
                    <html:option value="12"></html:option>
                    <html:option value="13"></html:option>
                    <html:option value="14"></html:option>
                    <html:option value="15"></html:option>
                    <html:option value="16"></html:option>
                    <html:option value="17"></html:option>
                    <html:option value="18"></html:option>
                    <html:option value="19"></html:option>
                    <html:option value="20"></html:option>
                    <html:option value="21"></html:option>
                    <html:option value="22"></html:option>
                    <html:option value="23"></html:option>
                </html:select>
                :
				<html:select name="Form" property="minutos">                
                    <html:option value="00"></html:option>
                    <html:option value="05"></html:option>
                    <html:option value="10"></html:option>
                    <html:option value="15"></html:option>
                    <html:option value="20"></html:option>
                    <html:option value="25"></html:option>
                    <html:option value="30"></html:option>
                    <html:option value="35"></html:option>
                    <html:option value="40"></html:option>
                    <html:option value="45"></html:option>
                    <html:option value="50"></html:option>
                    <html:option value="55"></html:option>
                </html:select>
                </td>
            </tr>
            <tr>
                <td>Observações:</td>
                <td colspan="5" style="padding-left:3px;">
                    <html:text name="Form" property="comentario" rows="2" style="width:610px;"  onKeyDown="verificarTmnMsg(this);"/>
                </td>
            </tr>
        </table>

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

        <vivo:tbTable selectable="true" layoutWidth="722" layoutHeight="110" tableWidth="722" styleId="TBHistorico" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="20%" type="string">Data/Hora</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="20%" type="string">Telefone Contato</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="20%" type="string">Pessoa de Contato</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="20%" type="string">Observação</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="20%" type="string">Usuário</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
            	<logic:iterate id="item" name="histAgend" property="historicoAgendamentoVOArray" indexId="c">
            		<vivo:tbRow key="historico">
                        <vivo:tbRowColumn>
                        	<bean:write name="item" property="dataHora" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        	<bean:write name="item" property="telContato" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        	<bean:write name="item" property="nmContato" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        	<bean:write name="item" property="observacao" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        	<bean:write name="item" property="usuario" />
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
            	</logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="1"></div>
        <table width="740" cellpadding="0" cellspacing="0" align="center">
            <tr>
                <td>
                <acesso:controlHiddenItem nomeIdentificador="fid_acon_terminar">
                    <img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onClick="voltar();" style="border:none;cursor:hand;" border="0"/>
					</acesso:controlHiddenItem>
                </td>
                <td align="right" style="padding-right:5px;">
                	<acesso:controlHiddenItem nomeIdentificador="fid_acon_gravar">
                    	<img src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"
                    		 href="?acao=agendar"
                    		 onClick="salvar(this); return false" />
					</acesso:controlHiddenItem>
            	</td>
            </tr>
        </table>
        </form></div>
    </vivo:quadro>

</acesso:controlHiddenItem>
</netui-template:section>
</netui-template:template>
