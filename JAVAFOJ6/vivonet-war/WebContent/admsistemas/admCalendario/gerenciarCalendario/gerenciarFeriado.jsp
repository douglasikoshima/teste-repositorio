<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="FormGerenciamento"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formGerenciamento"/>
<bean:define id="ListaAdmFeriadoVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formGerenciamento.listaAdmFeriadoVO"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Gerenciamento Anual de Feriados"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    </netui-template:section>
    <netui-template:section name="bodySection">
    
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script>
    <!--    
        function copiarCalendario(){
            if(document.forms[0].anoBase.value=='0000'){
                alert('Selecione um ano base válido.');
                return;
            }
            if(document.forms[0].anoCopia.value=='0000'){
                alert('Selecione um ano válido para efetuar a cópia.');
                return;
            }
            if(parseInt(document.forms[0].anoBase.value)>=parseInt(document.forms[0].anoCopia.value)){
                alert('O ano para a cópia do calendário deve ser maior que o ano base.');
                return;
            }
            if(confirm("Deseja realmente copiar o calendário ?\nSe existirem datas cadastradas para este ano, serão sobrescritos."))
            {
                document.forms[0].target = '_self';
                document.forms[0].action = 'copiarCalendario.do';
                document.forms[0].submit();
            }
        }
        
        function SalvarFeriadosMoveis(){
            if (validate()) {
                document.forms[0].target = '_self';
                document.forms[0].action = 'salvarFeriadosMoveis.do';
                document.forms[0].submit();
            }
        }
        
        function carregarPonte(){
            document.forms[0].target = '_self';
            document.forms[0].action = 'carregarPonte.do';
            document.forms[0].submit(); 
        }
        
        function SalvarPonte(){
            document.forms[0].target = '_self';
            document.forms[0].action = 'salvarPonte.do';
            document.forms[0].submit();            
        }

        function abrePopup(){
            alert('Ponte selecionada registrada com sucesso!');
        }

        function msgFeriadosFixos(){
            alert('Feriados fixos foram automaticamente registrados.');            
            var gerFerMoveis = document.getElementById("gerFerMoveis");
            gerFerMoveis.style.visibility = "visible";
            bla.style.visibility = "visible";
        }
        
        function okFeriadosMoveis(){
            var okFeriadosMoveis   = document.getElementById("okFeriadosMoveis");            
            okFeriadosMoveis.style.visibility = "visible";            
        }
        
        function gerarPonte() {
            divGeraPonte = document.getElementById('pontes');
            divGeraPonte.style.position = '';            
            divGeraPonte.style.visibility = 'visible';
            bla2.style.visibility = "visible";
        }
        
        function Retornar(){
            document.forms[0].action = 'begin.do';
            document.forms[0].submit();
        }
        
        function validate(){
            <%int i =0; %>
            <logic:iterate name="FormGerenciamento" property="listaAdmFeriadoVO" id="item">
                if (!validaData(eval("document.forms[0].dtAbertura"+<%=i%>+".value")) && eval("document.forms[0].dtAbertura"+<%=i%>+".value") != '') {
                    alert('Data deve estar no formato DD/MM/AAAA.');
                    eval("document.forms[0].dtAbertura"+<%=i%>+".focus()");
                    return false;
                }
            <%i=i+1;%>
            </logic:iterate>
            return true;
        }
        
    -->        
    </script>

        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <acesso:controlHiddenItem nomeIdentificador="adm_gferi_verpagina">
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        
            <form action="copiarCalendario" id="copiarCalendario" name="copiarCalendario" method="POST">
            <vivo:sessao id="qdMain" height="448" width="790" label="Calendário - Manutenção de Feriados" operacoes="Gerenciamento Anual">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
                
                <logic:equal name="FormGerenciamento" property="indicativoExibeCopia" value="Sim">
                    <table align="center" width="757" border="0" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                        <tr>
                            <td><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div></td>                            
                        </tr>
                        <tr>
                            <td >&nbsp;
                                <netui:label value="Cópia de Calendário:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td >Ano base:&nbsp;
                                <html:select name="FormGerenciamento" property="anoBase" style="text-indent:3px;width:155px" styleClass="SELECT">
                                <!--select name='anoBase' class='SELECT' style='width:50px;text-indent:3px;'-->
                                    <option value='0000'>Selecione ano base...</option>
                                    <option value='2004'>2004</option>
                                    <option value='2005'>2005</option>
                                    <option value='2006'>2006</option>
                                    <option value='2007'>2007</option>
                                    <option value='2008'>2008</option>
                                    <option value='2009'>2009</option>
                                    <option value='2010'>2010</option>
                                    <option value='2011'>2011</option>
                                    <option value='2012'>2012</option>
                                    <option value='2013'>2013</option>
                                    <option value='2014'>2014</option>
                                    <option value='2015'>2015</option>
                                    <option value='2016'>2016</option>
                                    <option value='2017'>2017</option>
                                    <option value='2018'>2018</option>
                                    <option value='2019'>2019</option>
                                    <option value='2020'>2020</option>
                                </html:select>
                                <!--/select-->&nbsp;&nbsp;&nbsp;&nbsp;
                                Ano para cópia:&nbsp;
                                <html:select name="FormGerenciamento" property="anoCopia" style="text-indent:3px;width:155px" styleClass="SELECT">
                                <!--select name='anoCopia' class='SELECT' style='width:50px;text-indent:3px;'-->
                                    <option value='0000'>Selecione ano para copiar...</option>
                                    <option value='2004'>2004</option>
                                    <option value='2005'>2005</option>
                                    <option value='2006'>2006</option>
                                    <option value='2007'>2007</option>
                                    <option value='2008'>2008</option>
                                    <option value='2009'>2009</option>
                                    <option value='2010'>2010</option>
                                    <option value='2011'>2011</option>
                                    <option value='2012'>2012</option>
                                    <option value='2013'>2013</option>
                                    <option value='2014'>2014</option>
                                    <option value='2015'>2015</option>
                                    <option value='2016'>2016</option>
                                    <option value='2017'>2017</option>
                                    <option value='2018'>2018</option>
                                    <option value='2019'>2019</option>
                                    <option value='2020'>2020</option>
                                </html:select>
                                <!--/select-->
                            </td>
                            <td valign="bottom" align="left">
                            <acesso:controlHiddenItem nomeIdentificador="adm_gferi_copcalendar">
                                <img onclick="copiarCalendario();" src="/FrontOfficeWeb/resources/images/bt_copcalend_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_copcalend_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_copcalend_over.gif'" style="border:none;cursor:hand"/>
                            </acesso:controlHiddenItem>
                            </td>
                        </tr>                     
                        <tr>
                            <td><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div></td>                            
                        </tr>
                    </table>
                </logic:equal>
                
                
                <div id="msgFixos" style="visibility:visible;"></div>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                
                <logic:equal name="FormGerenciamento" property="indicativoExibirMoveis" value="Sim">
                    <div id="gerFerMoveis" style="visibility:visible;">
                    <center>
                    <vivo:quadro width="757" height="300" id="UFs" scroll="yes" label="Gerenciamento de Feriados Móveis">
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4"></div>
                        <table align="center" width="100%" height="25" border="0" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                            <tr>
                                <td align="center">Tabela para alteração de data de feriado no ano selecionado. Selecione as linhas que deseja alterar e após inserção de Nova Data, clique em Salvar.</td>
                            </tr>                  
                        </table>
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4"></div>                   
                        <div id="tabela" style="visibility:visible;">
                        <%int contador=0;%>
                        <vivo:tbTable selectable="true" layoutWidth="715" layoutHeight="235" tableWidth="715" styleId="tableTitle" sortable="true">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="center" width="31"  type="string"></vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="105"  type="string">Nova data</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="408"  type="string">Feriado</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="60"    type="string">Tipo</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="center" width="110"    type="string">UF</vivo:tbHeaderColumn>                            
                            </vivo:tbHeader>
                            <vivo:tbRows>
                            
                            <logic:iterate name="FormGerenciamento" property="listaAdmFeriadoVO" id="item">
                            
                                <vivo:tbRow key="linha1">
                                    <vivo:tbRowColumn>
                                        
                                        <input type="checkbox" name="seleciona<%=contador%>" checked/>
                                        
                                    </vivo:tbRowColumn>
                                    
                                    <vivo:tbRowColumn>
                                        <input type="text" name="dtAbertura<%=contador%>" onkeypress="checaData(this);" value="<bean:write name="item" property="dtFeriado" />" size="10" maxlength="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtAbertura<%=contador%>', '%d/%m/%Y');">
                                    </vivo:tbRowColumn>
                                    
                                    <vivo:tbRowColumn>
                                        <bean:write name="item" property="dsFeriado" />
                                    </vivo:tbRowColumn>
                                    
                                    <vivo:tbRowColumn>
                                         <bean:write name="item" property="dsTipoFeriado" />
                                    </vivo:tbRowColumn>
                                    
                                    <vivo:tbRowColumn>
                                        <bean:write name="item" property="nmUF" />
                                    </vivo:tbRowColumn>                                                                
                                </vivo:tbRow>
                                <%contador = contador + 1;%>
                            </logic:iterate>
                            
                            </vivo:tbRows>
                        </vivo:tbTable>
                        <input type="hidden" name="contador" value="<%=contador%>"/>
                        
                        </div>
                    </vivo:quadro>               
                    <table width="757" border="0" cellspacing="0" cellpadding="0" align="center">
                        <tr>                                    
                            <td align="right">
                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>            
                                <acesso:controlHiddenItem nomeIdentificador="adm_gferi_salvarferi">
                                    <img id="btSalvar" onclick="SalvarFeriadosMoveis();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"  style="border:none;cursor:hand"/>
                                </acesso:controlHiddenItem>
                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>            
                            </td>
                        </tr>                       
                    </table>
                    </div>
                </logic:equal>
                
                <logic:equal name="FormGerenciamento" property="indicativoExibirBotoesPonte" value="Sim">
                    <div id="okFeriadosMoveis" style="visibility:visible;">
                    <table align='center' width='757' border='0' cellpadding='0' cellspacing='0'>
                       <tr>                       
                           <td align='right' valign='top'>
                               <acesso:controlHiddenItem nomeIdentificador="adm_gferi_carponte"><img vspace='4' onclick="carregarPonte();" id='btGerarPonte' src="/FrontOfficeWeb/resources/images/bt_gerarponte_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gerarponte_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gerarponte_over.gif'"  style='border:none;cursor:hand'/></acesso:controlHiddenItem>
                               <img vspace='4' onclick="Retornar();" id='btGerarPonte' src='/FrontOfficeWeb/resources/images/bt_retornar_nrml.gif' onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_retornar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_retornar_over.gif'"  style='border:none;cursor:hand' />
                           </td>
                       </tr>
                    </table>
                    </div>
                </logic:equal>
                
                
                <logic:equal name="FormGerenciamento" property="indicativoExibirPontes" value="Sim">
                    <div id="pontes" style="visibility:visible;position:absolute;left:0;top:0;">
                        <vivo:quadro width="757" height="185" id="UFs" scroll="no" label="Gerenciamento de Pontes">
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4"></div>
                            
                            
                                                                                             
                            <div id="tabela2">
                                <%int contadorPonte=0;%>
                                <vivo:tbTable selectable="true" layoutWidth="733" layoutHeight="100" tableWidth="733" styleId="tableTitle" sortable="true">
                                    <vivo:tbHeader>
                                        <vivo:tbHeaderColumn align="center" width="31"  type="string"></vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width=""  type="string">Referente ao Feriado</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn align="center" width="240" type="string">Data da Ponte</vivo:tbHeaderColumn>                           
                                    </vivo:tbHeader>
                                    <vivo:tbRows>
                                        <logic:iterate name="FormGerenciamento" property="listaAdmFeriadoVO" id="item">
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn>
                                                    <input type="checkbox" name="seleciona<%=contadorPonte%>" checked/>
                                                </vivo:tbRowColumn>
                                                <vivo:tbRowColumn>
                                                    <bean:write name="item" property="dsFeriado" />
                                                </vivo:tbRowColumn>
                                                
                                                <vivo:tbRowColumn>
                                                    <bean:write name="item" property="dtFeriado" />
                                                </vivo:tbRowColumn>
                                                
                                            </vivo:tbRow>
                                        <%contadorPonte = contadorPonte + 1;%>
                                        </logic:iterate>
                                    </vivo:tbRows>
                                </vivo:tbTable>
                                 <input type="hidden" name="contadorPonte" value="<%=contadorPonte%>"/>
                            </div>
                            
                            <table align='center' width='757' border='0' cellpadding='0' cellspacing='0'>
                               <tr>                       
                                   <td align='right' valign='top'>
                                   <acesso:controlHiddenItem nomeIdentificador="adm_gferi_salvarponte">
                                       <img vspace='4' onclick="SalvarPonte();" id='btSalvarPonte' src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif' onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"  style='border:none;cursor:hand' hspace="10"/>                                       
                                   </acesso:controlHiddenItem>
                                   </td>
                               </tr>
                                <tr>                       
                                <td align='left' valign='top'>
                                   <img hspace="8" vspace="10" id="btVoltar" onClick="window.location.href='/FrontOfficeWeb/index.jsp';" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>
                                  </td>
                                </tr>
                            </table>
                        </vivo:quadro>
                        
                    </div>
                    
                </logic:equal>
                
                
                </center>
                <table align='center' width='757' border='0' cellpadding='0' cellspacing='0'>
                   
                </table>
                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10">
            
        </div>            
            </vivo:sessao>
            <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad;"></div>
            
            <SCRIPT FOR=window EVENT=onload  LANGUAGE="JScript">
            <!--   
                if('<bean:write name="FormGerenciamento" property="msgError" />' != "")
                {
                    alert('<bean:write name="FormGerenciamento" property="msgError" />');
                }
            -->
            </SCRIPT>
        </form>
            
	</acesso:controlHiddenItem>		      
    </netui-template:section>
</netui-template:template>
