<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaHistCampanhaForm" />
<bean:define id="lHistorico" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lHistorico" />

<acesso:controlInitEnv/>

<html>
    <head>
        <script language="javascript">
        function mostrar(obj) {
            var indice = obj.value;            
            //Caso o grid retorne mais de uma linha		
            if(dtAtendimento.length) {
                with(document.forms.listaHistCampanhaForm)
                {
                    desDtAtendimento.innerText  = dtAtendimento[indice].innerText;
                    //usuário                   = //usuário
                    desSgCampanha.innerText     = sgCampanha[indice].innerText;
                    desSgSubCampanha.innerText  = sgSubCampanha[indice].innerText;
                    desSgCanal.innerText        = sgCanal[indice].innerText;
                    desSgSubMotivo.innerText    = sgSubMotivo[indice].innerText;
                    desSgMotivo.innerText       = sgMotivo[indice].innerText;
                    desSgStatus.innerText       = sgStatus[indice].innerText;
                }
            } else {        
                //Caso o grid tenha somente uma linha        
                desDtAtendimento.innerText  = dtAtendimento.innerText;
                //usuário                   = //usuário
                desSgCampanha.innerText     = sgCampanha.innerText;
                desSgSubCampanha.innerText  = sgSubCampanha.innerText;
                desSgCanal.innerText        = sgCanal.innerText;
                desSgSubMotivo.innerText    = sgSubMotivo.innerText;
                desSgMotivo.innerText       = sgMotivo.innerText;
                desSgStatus.innerText       = sgStatus.innerText;
            }
            
            document.getElementById("detalhe").style.display = "block";
        }
        
        
        function pinta(obj) 
        {        
            for(i=0; i < document.styleSheets[0].rules.length; i++)
            {
                if(document.styleSheets[0].rules[i].selectorText == ".tblDinamica_linhapar")
                {
                    linhapar = document.styleSheets[0].rules[i].style.backgroundColor;
                }
            }
        
            for(i=0; i < document.all.length; i++)
            {
                if(document.all[i].type == "radio")
                {
                    document.all[i].parentNode.parentNode.style.backgroundColor = linhapar;
                }
            }
        
            for(i=0; i < document.styleSheets[0].rules.length; i++)
            {
                if(document.styleSheets[0].rules[i].selectorText == ".tblDinamica_linhaSelected")
                {
                    linhaSelected = document.styleSheets[0].rules[i].style.backgroundColor;
                }
            }
            
            obj.parentNode.parentNode.style.backgroundColor = linhaSelected;
        
        }
            
        function submeterConsulta() {
            var objForm    = document.forms.listaHistCampanhaForm;
            
            var dia  = new Date();
            var mes  = dia.getMonth() + 1;
            var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
            
            if(objForm.elements['dtInicio'].value.length < 10 || objForm.elements['dtFim'].value.length < 10) {
                alert("Favor preencher corretamente a data início e data fim!");
                return false;        
            } else if(!validaData(objForm.elements['dtInicio'].value) || !validaData(objForm.elements['dtFim'].value)) {
                alert("Data inválida!");
                return false;
            } else if(!validaDataFinal(objForm.elements['dtInicio'].value, objForm.elements['dtFim'].value)) {
                alert("Data fim não pode ser menor que data início!");
                return false;            
            } else if(!validaDataFinal(objForm.elements['dtInicio'].value, hoje)) {
                alert("Data início não pode ser posterior a data atual!");
                return false;
            }
                    
            objForm.action = "consultar.do";
            objForm.submit();
            parent.mostrar_div();
        }    
        </script>
        <SCRIPT FOR=window EVENT=onload>
            parent.oculta_div();
        </script>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <title></title>
    </head>
    <acesso:controlHiddenItem nomeIdentificador="cam_histcamp_verpagina">
        <form action="listaHistCampanha.do" name="listaHistCampanhaForm" id="listaHistCampanhaForm" method="post">
		<html:hidden name="Form" property="idPessoadePara"/>
        <table align="center" border="0" width="750" cellpadding="0" cellspacing="10" class="tbl_bgGray">
            <tr>
                <td><b>Data Início:</b></td>
                <td>               
                    <html:text name="Form" property="dtInicio" styleClass="textfield" size="10" onKeyUp="this.value = Format(this.value,'##/##/####');"/>
                    <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtInicio', '%d/%m/%Y');">
                        </td>             	
                <td class="tblEstatica_campoNome"><netui:label value="até :"/>
                </td>
                <td>
                    <html:text name="Form" property="dtFim" styleClass="textfield" size="10"  onKeyUp="this.value = Format(this.value,'##/##/####');"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFim', '%d/%m/%Y');">
                </td>
                <td>
                <acesso:controlHiddenItem nomeIdentificador="cam_histcamp_pesquisar">
                    <img onClick="submeterConsulta();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="border:none;cursor:hand;"/>
                </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        <table border="0" align="center" width="750" cellpadding="0" cellspacing="0">
            <tr>
                <td colspan="8" width="100%">
                <vivo:tbTable selectable="true" onRowClick="" layoutWidth="740" layoutHeight="150" tableWidth="750" styleId="table" sortable="true" >
                <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="110" type="string">Atendimento</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="65" type="string">Operadora</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="65" type="string">Campanha</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="85" type="string">Subcampanha</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="90" type="string">Canal</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="90" type="string">Motivo</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="90" type="string">Sub Motivo</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="60" type="string">Status</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="30" type="string">TA</vivo:tbHeaderColumn> 
				<vivo:tbHeaderColumn align="center" width="30">&nbsp;</vivo:tbHeaderColumn> 
				</vivo:tbHeader>
	                <vivo:tbRows>
	                <logic:iterate id="item" name="lHistorico" indexId="c">
	                	<vivo:tbRow key="historico">
								<vivo:tbRowColumn><bean:write name="item" property="dtAtendimento" /></vivo:tbRowColumn>
								<vivo:tbRowColumn><bean:write name="item" property="sgOperadora" /></vivo:tbRowColumn>
								<vivo:tbRowColumn><bean:write name="item" property="sgCampanha" /></vivo:tbRowColumn>
								<vivo:tbRowColumn><bean:write name="item" property="sgSubCampanha" /></vivo:tbRowColumn>
								<vivo:tbRowColumn><bean:write name="item" property="sgCanal" /></vivo:tbRowColumn>
								<vivo:tbRowColumn><bean:write name="item" property="sgMotivo" /></vivo:tbRowColumn>
								<vivo:tbRowColumn><bean:write name="item" property="sgSubMotivo" /></vivo:tbRowColumn>
								<vivo:tbRowColumn><bean:write name="item" property="sgStatus" /></vivo:tbRowColumn>
								<vivo:tbRowColumn><bean:write name="item" property="vlTempoMedio" /></vivo:tbRowColumn>
								<vivo:tbRowColumn>
									<acesso:controlHiddenItem nomeIdentificador="cam_histcamp_detalhes">
										<img src="/FrontOfficeWeb/resources/images/lupa_bg_transp.gif"
											 onclick="getArvoreHistorico.do?idAtendimento=XXXXX"
											 class="button"
											 onClick="top.frameApp.mostrar_div();document.forms[0].action='getArvoreHistorico.do?idAtendimento=<bean:write name="item" property="idAtendimentoCampanha" />';document.forms[0].submit()" />
									</acesso:controlHiddenItem>	
								</vivo:tbRowColumn>
							</vivo:tbRow>
	                </logic:iterate>
	                </vivo:tbRows>
                </vivo:tbTable>
                </td>
            </tr>
            <tr>
                <td colspan="8">
                <div>
                    <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5">
                </div>
                <div id="detalhe" style="display:none">
                    <vivo:quadro id="detalhecampanha" width="765" height="110" label="Detalhe Campanha">
                    <table border="0" cellpadding="0" cellspacing="0" height="100">
                        <tr>
                            <td rowspan="4" width=10></td>
                            <td width="140">
                            <b><netui:label value="Data do Atendimento:"/></b>
                            </td>
                            <td width="175">
                            <netui:label id="desDtAtendimento" value="11/05/2004 22:00:03"/>
                            </td>
                            <td width="100">
                            <b><netui:label value="Usuário:"/></b>
                            </td>
                            <td>
                            <netui:label id="" value="{pageFlow.user}"/>
                            </td>
                        </tr>
                        <tr class="corpo">
                            <td>
                            <b><netui:label value="Campanha:"/></b>
                            </td>
                            <td>
                            <netui:label id="desSgCampanha" value=""/>
                            </td>
                            <td>
                            <b><netui:label value="Sub Campanha:"/></b>
                            </td>
                            <td>
                            <netui:label id="desSgSubCampanha" value=""/>
                            </td>
                        </tr>
                        <tr class="corpo">
                            <td>
                            <b><netui:label value="Canal:"/></b>
                            </td>
                            <td>
                            <netui:label id="desSgCanal" value="Atendimento Telefônico"/>
                            </td>
                            <td>
                            <b><netui:label value="Sub Motivo:"/></b>
                            </td>
                            <td>
                            <netui:label id="desSgSubMotivo" value="Respondeu a Campanha"/>
                            </td>
                        </tr>
                        <tr class="corpo">
                            <td>
                            <b><netui:label value="Motivo:"/></b>
                            </td>
                            <td>
                            <netui:label id="desSgMotivo" value="Aderiu"/>
                            </td>
                            <td>
                            <b><netui:label value="Status:"/></b>
                            </td>
                            <td>
                            <netui:label id="desSgStatus" value="Terminado"/>
                            </td>
                        </tr>
                    </table>
                    </vivo:quadro>
                </div>    
    </td>
    </tr>
    </table>
    </form>
</acesso:controlHiddenItem>
</html>