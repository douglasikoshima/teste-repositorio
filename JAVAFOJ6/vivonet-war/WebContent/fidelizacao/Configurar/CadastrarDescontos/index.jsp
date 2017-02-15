<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="descontoForm"/>
<bean:define id="ListaMatriz"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="descontoForm.listaMatriz"/>
<bean:define id="ListaDesconto"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="descontoForm.listaDesconto"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        
	<style type="text/css">
		.trClickSelected {
			background-color:cecece;
		}
	</style>        
        <script type="text/javascript">
            var objClicado = null;
            limparCampos = function(){
                var f = document.forms[0];
                f.s1.value="";
                f.e1.value="";
                f.e2.value="";
                f.e3.value="";
                f.r1.value="";
                f.r2.value="";
                f.r3.value="";
                f.d1.value="";
                f.d2.value="";
                f.d3.value="";
                f.nc.value="";
                f.idAparelhoIncluirEditar.value="";
                f.idMatrizDesconto.value="";
                
            }
            
            desabilitarCampos = function(){
                var f = document.forms[0];
                f.s1.disabled=true;
                f.e1.disabled=true;
                f.e2.disabled=true;
                f.e3.disabled=true;
                f.r1.disabled=true;
                f.r2.disabled=true;
                f.r3.disabled=true;
                f.d1.disabled=true;
                f.d2.disabled=true;
                f.d3.disabled=true;
                f.nc.disabled=true;
            }
            
            habilitarCampos = function(){
                var f = document.forms[0];
                f.s1.disabled=false;
                f.e1.disabled=false;
                f.e2.disabled=false;
                f.e3.disabled=false;
                f.r1.disabled=false;
                f.r2.disabled=false;
                f.r3.disabled=false;
                f.d1.disabled=false;
                f.d2.disabled=false;
                f.d3.disabled=false;
                f.nc.disabled=false;
            }
            
            
            editarAparelho=function(idMtzDesc,idAp,s1,e1,e2,e3,r1,r2,r3,d1,d2,d3,nc){
                habilitarCampos();
                limparCampos();
                var f = document.forms[0];
                f.idMatrizDesconto.value=idMtzDesc;
                f.idAparelhoIncluirEditar.value=idAp;
                f.s1.value=s1;
                f.e1.value=e1;
                f.e2.value=e2;
                f.e3.value=e3;
                f.r1.value=r1;
                f.r2.value=r2;
                f.r3.value=r3;
                f.d1.value=d1;
                f.d2.value=d2;
                f.d3.value=d3;
                f.nc.value=nc;
                
            }
            
            
            checaInteiro = function(e) {
                var keynum
                var keychar
                var numcheck
                
                 // IE
                if(window.event) {
                    keynum = e.keyCode
                
                 // Netscape/Firefox/Opera
                } else if(e.which) {
                    keynum = e.which
                }
                
                keychar = String.fromCharCode(keynum)
                
                numcheck = /\d/
                
                return numcheck.test(keychar) || keynum == 8
            }            
            
            carregaListaAparelhos = function(){
            
                var f = document.forms[0];
                if(f.idMatriz.value!=''){
                    var action = 'carregaListaAparelhos.do';
                    f.action = action;
                    top.frameApp.mostrar_div();
                    f.submit();
                }else{
                    f.idMatriz.value="";
                    f.idAparelhoIncluirEditar.value="";
                    f.idMatrizDesconto.value="";
                    f.nmMatriz.value="";
                    limparCampos();
                    var action = 'begin.do';
                    f.action = action;
                    top.frameApp.mostrar_div();
                    f.submit();
                }
                
            }
            
            gravar = function(){
                var f = document.forms[0];
                
                if(f.nmMatriz.value!=""){
                    if(f.idAparelhoIncluirEditar.value==''){
                        alert("Selecione uma linha de registro para configurar!");
                        return;
                    }else{
                        var action = 'gravarMatrizDesconto.do';
                        f.action = action;
                        top.frameApp.mostrar_div();
                        f.submit();
                    }
                }else{
                    alert("Favor incluir um nome para a Matriz de Descontos!");
                    f.nmMatriz.focus();
                    return;
                }
            }            

            validaForm = function(){
                
            }



            transfereSelecaoLista = function(listaOrigem, listaDestino, inverse, flAll){
                
            }

            function CarregaAba(nome){
                
            }

            function habilitarMatriz(valor){
               
            }
            
            function selectHistorico(obj){
                for(i=0; i< document.getElementById("table_body").rows.length; i++){
                    if(document.getElementById("table_body").rows[i].cells[0].all[0] == obj || document.getElementById("table_body").rows[i].cells[0].all[1] == obj){
                        document.getElementById("table_body").rows[i].style.backgroundColor="#cecece";
                    }else{
                        document.getElementById("table_body").rows[i].style.backgroundColor="#f5f5f5";
                    }
                }
            }            




		paint = function(obj) {
			obj.id = (obj.id == '') ? guid() : obj.id;
			var o = $(obj.id);            
            if (objClicado != null) {                
                objClicado.removeClassName('trClickSelected');
            }
			for (var i = 0; i < o.ancestors().length; i++) {
				if (o.ancestors()[i].tagName.toLowerCase() == 'tr') {
                    // TR clicada
                    var myTR = o.ancestors()[i];
                    myTR.id = (myTR.id == '' || myTR.id == null || myTR.id == undefined)
                        ? guid() : myTR.id;
                    objClicado = myTR;
					// Remover seleção de item clicado
                    if (myTR.hasClassName('trClickSelected')) {
						objClicado = null;
                        myTR.removeClassName('trClickSelected');
                        limparCampos();
                        desabilitarCampos();
					} else {
                        myTR.addClassName('trClickSelected');                        
					}
					break;
				}
			}
		}

		S4 = function() {
			return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
		}
		guid = function() {
			return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
		}


        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                limparCampos();
                desabilitarCampos();
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <form method="post" action="" name="descontoForm" style="margin:0px;" onsubmit="return false;">
        
        <html:hidden name="Form" property="idAparelhoIncluirEditar"/>
        <html:hidden name="Form" property="idMatrizDesconto"/>
        <table width="95%" align="center" cellpadding="2" cellspacing="2" border="0">
            <tr>
                <td width="345">
                    <span style="width:120px;">Matriz de Descontos:</span>
                    <html:select name="Form" property="idMatriz" style="width:220px;" styleClass="SELECT" onchange="carregaListaAparelhos();">
                        <option value="">-- Selecione --</option>
                        <logic:equal name="Form" property="idMatriz" value="N">
                            <option value="N" selected>Nova Matriz</option>
                        </logic:equal>
                        <logic:notEqual name="Form" property="idMatriz" value="N">
                            <option value="N">Nova Matriz</option>
                        </logic:notEqual>
                        <html:options collection="ListaMatriz" property="idMatriz" labelProperty="dsMatriz" />
                    </html:select>
                </td>
                <td align="left">
                    <img id="imgHabil" src="/FrontOfficeWeb/resources/images/bt_habilitar_nrml.gif" border="0" style="cursor:pointer;display:none;" onclick="habilitarMatriz(1);">
                    <img id="imgDesab" src="/FrontOfficeWeb/resources/images/bt_desabilitar_nrml.gif" border="0" style="cursor:pointer;display:none;" onclick="habilitarMatriz(0);">
                </td>
                <td align="right">
                    <logic:notEmpty name="Form" property="nmUsuario">
                    <div style="width:200px;">Usuario: <bean:write name="Form" property="nmUsuario"/> </div>
                    </logic:notEmpty>
                </td>
            </tr>
            <tr>
                <td>
                    <span style="width:120px;">Nome Matriz:</span>
                    <logic:notEmpty name="Form" property="idMatriz">
                        <html:text name="Form" property="nmMatriz" size="40" maxlength="255" disabled="false"/>
                    </logic:notEmpty>
                    <logic:empty name="Form" property="idMatriz">
                        <html:text name="Form" property="nmMatriz" size="40" maxlength="255" disabled="true"/>
                    </logic:empty>
                    
                </td>
                <td align="right">
                </td>
                <td align="right">
                    <logic:notEmpty name="Form" property="nmUsuario">
                    <div style="width:200px;">Dt Alt.: <bean:write name="Form" property="dtAlteracao"/> </div>
                    </logic:notEmpty>
                </td>
            </tr>
        </table>

        <div id="grpCampos1" style="height:185px;">
            <table width="95%" align="center" cellpadding="2" cellspacing="2">
                <tr>
                    <td>
                        <vivo:tbTable selectable="true" styleId="tableAparelhos" layoutHeight="175" layoutWidth="760" tableWidth="760">
                            <vivo:tbHeader>
                            
                                <vivo:tbHeaderColumn width="25" type="String"></vivo:tbHeaderColumn>                            
                                <vivo:tbHeaderColumn width="65" type="String" align="left">Aparelho</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn width="75" type="String" align="right">Diamante 3</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn width="75" type="String" align="right">Diamante 2</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn width="75" type="String" align="right">Diamante 1</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn width="45" type="String" align="right">Rubi 3</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn width="45" type="String" align="right">Rubi 2</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn width="45" type="String" align="right">Rubi 1</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn width="78" type="String" align="right">Esmeralda 3</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn width="78" type="String" align="right">Esmeralda 2</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn width="78" type="String" align="right">Esmeralda 1</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn width="45" type="String" align="right">Safira</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn width="20" type="String" align="right">N.C</vivo:tbHeaderColumn>
                            
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="listaMatrizes" name="ListaDesconto" indexId="c">
                                <vivo:tbRow key="linha1">
                                    <vivo:tbRowColumn>
                                        <img src="/FrontOfficeWeb/resources/images/lupa_bg_ffffff.gif" onclick="editarAparelho('<bean:write name="listaMatrizes" property="idDesconto" />','<bean:write name="listaMatrizes" property="idAparelho" />','<bean:write name="listaMatrizes" property="safira" />','<bean:write name="listaMatrizes" property="esmeralda1" />','<bean:write name="listaMatrizes" property="esmeralda2" />','<bean:write name="listaMatrizes" property="esmeralda3" />','<bean:write name="listaMatrizes" property="rubi1" />','<bean:write name="listaMatrizes" property="rubi2" />','<bean:write name="listaMatrizes" property="rubi3" />','<bean:write name="listaMatrizes" property="diamante1" />','<bean:write name="listaMatrizes" property="diamante2" />','<bean:write name="listaMatrizes" property="diamante3" />','<bean:write name="listaMatrizes" property="naoClassificado" />');paint(this);"/>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="dsAparelho" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="diamante3" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="diamante2" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="diamante1" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="rubi3" />
                                    </vivo:tbRowColumn>                                                                                                                                                                              
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="rubi2" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="rubi1" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="esmeralda3" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="esmeralda2" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="esmeralda1" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="safira" />
                                    </vivo:tbRowColumn>   
                                    <vivo:tbRowColumn>
                                        <bean:write name="listaMatrizes" property="naoClassificado" />
                                    </vivo:tbRowColumn>                                                                       
                                </vivo:tbRow>                              
                                            
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>                                  
                    </td>
                </tr>
            </table>
        </div>
        
        
        
        
        <fieldset>
                <legend style="font-weight:bold;">Manutenção</legend>
                <table width="730" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    
                    <tr>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        <netui:label value="Safira: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td width="50%">
                                        <html:text name="Form" property="s1" style="width:100px" styleClass="input" maxlength="2" tabindex="3" onkeypress="return checaInteiro(event)"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        <netui:label value="Rubi 1: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td width="50%">
                                        <html:text name="Form" property="r1" style="width:100px" styleClass="input" maxlength="2" tabindex="4" onkeypress="return checaInteiro(event)"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        <netui:label value="Diamante 2: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td width="50%">
                                        <html:text name="Form" property="d2" style="width:100px" styleClass="input" maxlength="2" tabindex="5" onkeypress="return checaInteiro(event)"/>
                                    </td>
                                </tr>
                            </table>
                        </td>                   
                    </tr>
        
                    <tr>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        <netui:label value="Esmeralda 1: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td width="50%">
                                        <html:text name="Form" property="e1" style="width:100px" styleClass="input" maxlength="2" tabindex="6" onkeypress="return checaInteiro(event)"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        <netui:label value="Rubi 2: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td width="50%">
                                        <html:text name="Form" property="r2" style="width:100px" styleClass="input" maxlength="2" tabindex="7" onkeypress="return checaInteiro(event)"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        <netui:label value="Diamante 3: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td width="50%">
                                        <html:text name="Form" property="d3" style="width:100px" styleClass="input" maxlength="2" tabindex="8" onkeypress="return checaInteiro(event)"/>
                                    </td>
                                </tr>
                            </table>
                        </td>                   
                    </tr>        
        
        
                    <tr>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        <netui:label value="Esmeralda 2: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td width="50%">
                                        <html:text name="Form" property="e2" style="width:100px" styleClass="input" maxlength="2" tabindex="9" onkeypress="return checaInteiro(event)"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        <netui:label value="Rubi 3: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td width="50%">
                                        <html:text name="Form" property="r3" style="width:100px" styleClass="input" maxlength="2" tabindex="10" onkeypress="return checaInteiro(event)"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        <netui:label value="Não Classificado: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td width="50%">
                                        <html:text name="Form" property="nc" style="width:100px" styleClass="input" maxlength="2" tabindex="11" onkeypress="return checaInteiro(event)"/>
                                    </td>
                                </tr>
                            </table>
                        </td>                   
                    </tr>         
        
                    <tr>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        <netui:label value="Esmeralda 3: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td width="50%">
                                        <html:text name="Form" property="e3" style="width:100px" styleClass="input" maxlength="2" tabindex="12" onkeypress="return checaInteiro(event)"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        <netui:label value="Diamante 1: " styleClass="tblEstatica_campoNome"/>
                                    </td>
                                    <td width="50%">
                                        <html:text name="Form" property="d1" style="width:100px" styleClass="input" maxlength="2" tabindex="13" onkeypress="return checaInteiro(event)"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="33%" style="text-indent:10px;">
                            <table width="100%">
                                <tr>
                                    <td width="50%">
                                        &nbsp;
                                    </td>
                                    <td width="50%">
                                        &nbsp;
                                    </td>
                                </tr>
                            </table>
                        </td>                   
                    </tr>  
                    <tr>
                        <td colspan="3" style="text-indent:10px;">
                            &nbsp;
                        </td>
                        
                    </tr>         
        
        
                </table>
        </fieldset>
        
        
        
            
        <div style="margin-top:3px;width:776px;">
                <img src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" onclick="gravar();" align="right" style="cursor:pointer;"/>
        </div>
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
         <vivo:alert atributo="msgErro" scope="request" />
    </form>
    </netui-template:section>
</netui-template:template>
