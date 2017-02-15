<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
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

<bean:define id="FormCalendario"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario"/>
<bean:define id="AdmTipoFeriadoVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario.admTipoFeriadoVO"/>
<bean:define id="AdmDescricaoFeriadoVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario.admDescricaoFeriadoVO"/>

<html>
    <head>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="Javascript">
        function ocultaCampos(){
            var a = document.getElementById("div1");
            a.style.visibility = 'visible';
            var b = document.getElementById("div2");
            b.style.visibility = 'hidden';
        } 
        
        function exibeCampos(){
            var a = document.getElementById("div1");
            a.style.visibility = 'hidden';
            var b = document.getElementById("div2");
            b.style.visibility = 'visible';
            document.forms[0].admDescricaoNova.value='';
        }     

        function valida()
        {
            var form = document.forms[0];
            
            if(trim(form.dtFeriado.value) == '')
            {
                alert('Data é um campo obrigatório.');
                return false;
            
            }else if(form.indMovel.value == "000")
            {
                alert('Feriado Móvel é um campo obrigatório.');
                return false;
            
            }else if(form.admTipoFeriado.value == "000")
            {
                alert('Tipo Feriado é um campo obrigatorio.');
                return false;
                
            }else if(form.inRelatorio.value == "000")
            {
                alert('Relatório DRC é um campo obrigatório.');
                return false;
            
            }else if(form.descr[0].checked)
            {
                if(trim(form.admDescricaoNova.value) == '')
                {
                
                    alert('Preencha uma nova descrição.');
                    return false;
                }
            }else if(form.descr[1].checked)
            {
                if(form.admDescricaoExistente.value == "000")
                {
                    alert('Selecione uma descrição existente.');
                    return false;
                
                }
            }            
            
            if((document.forms[0].dtFeriado.value != '') && (!validaData(document.forms[0].dtFeriado.value)))
            {
                alert('Data deve estar no formato DD/MM/AAAA.');
                document.forms[0].dtFeriado.focus();
                return false;
            }
            
            return true;
        
        }

        function Enviar()
        {
            if(valida())
            {
                document.forms[0].target = '_parent';
                document.forms[0].action = 'incluirFeriado.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }        
        }

        function enviarEditado()
        {
            if(valida())
            {
                document.forms[0].target = '_parent';
                document.forms[0].action = 'salvarAlterarFeriado.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }
    </script>
    
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        
    </head>
    
    <%if(request.getParameter(ConstantesCRM.SACTION) != null && request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("incluir")){%>
    <body style="padding-left:5px;padding-top:5px;">
        <form action="incluirFeriado" id="incluirFeriado" name="incluirFeriado" method="POST">
        
                    
            <acesso:controlHiddenItem nomeIdentificador="adm_iferi_verpagina">
               
               <table align="center" width="620" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td colspan="6"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="45"></div></td>                            
                    </tr>
                    <tr>
                    </tr>
                    <tr>
                        <td>
                            <netui:label value="Descrição: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td valign="top" >&nbsp;&nbsp; 
                            <input type="radio" class="radio" name="descr" value="0" checked onkeypress="ocultaCampos();" onclick="ocultaCampos();">&nbsp;Nova
                            <input type="radio" class="radio" name="descr" value="1" onkeypress="exibeCampos();" onclick="exibeCampos();">&nbsp;Existente                       
                        </td>
                        <td width="100">                                    
                            &nbsp;
                        </td>
                        <td style="text-align:left;" width="220" align="left">
                           <netui:label value="Data:" styleClass="tblEstatica_campoNome"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           <input type="text" id="dtFeriado" name="dtFeriado" onkeypress="checaData(this);" value="<bean:write name="FormCalendario" property="dtFeriado" />"  style='width:65px;' maxlength="10"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFeriado', '%d/%m/%Y');">
                        </td>
                        <td align="left" colspan="2">&nbsp;</td>

                    </tr>
                    <tr>
                        <td></td>            
                        <td colspan="2">
                            <div id="div3" style="visibility:'visible'; top: 0px; left: 0px; height: 24px; padding: 0px; position:relative;">
                                <div id="div1" style="visibility:'visible'; top: 0px; left: 0px; height: 24px; padding: 0px; position:absolute;">
                                    &nbsp;&nbsp;<input type='text' name='admDescricaoNova' size='40' style='text-indent:2px;width:195px' value='' class='input' maxlength="200"/>
                                </div> 
                                <div id="div2" style="visibility:'hidden'; top: 0px; left: 0px; height: 24px; padding: 0px; position:absolute;">
                                    <html:select name="FormCalendario" property="admDescricaoExistente" styleClass='SELECT' style='text-indent:8px;width:198px'>
                                        <html:option value="000">Escolha uma opção...</html:option>
                                        <html:options collection="AdmDescricaoFeriadoVO" property="idNomeFeriado" labelProperty="dsFeriado" /> 
                                    </html:select>
                                </div> 
                            </div>
                        </td>                        
                        <td colspan="3">
                        <netui:label value="Feriado Móvel:" styleClass="tblEstatica_campoNome"/>
                            &nbsp;&nbsp;
                            <html:select name="FormCalendario" property="indMovel" style="width:140px" styleClass="SELECT">
                                <html:option value="000">Escolha uma opção...</html:option>
                                <html:option value="1">Sim</html:option>
                                <html:option value="0">Não</html:option>
                            </html:select>  
                        </td>                       
                    </tr>
                    <tr>
                        <td>
                            <netui:label value="Tipo do Feriado: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td colspan="2" class="tblEstatica_campoValor" align="left">
                                <html:select name="FormCalendario" property="admTipoFeriado" style="text-indent:3px;width:200px" styleClass="SELECT">
                                    <html:option value="000">Escolha uma opção...</html:option>
                                    <html:options collection="AdmTipoFeriadoVO" property="idTipoFeriado" labelProperty="dsTipoFeriado" /> 
                                </html:select>
                        </td>
                        <td align="left">
                            <netui:label value="Relatório DRC:" styleClass="tblEstatica_campoNome"/>&nbsp;&nbsp;&nbsp;
                                <html:select name="FormCalendario" property="inRelatorio" style="width:140px" styleClass="SELECT">
                                    <html:option value="000">Escolha uma opção...</html:option>
                                    <html:option value="1">Sim</html:option>
                                    <html:option value="0">Não</html:option>
                                </html:select> 
                        </td>
                        <td>&nbsp;</td>
                        <td class="tblEstatica_campoValor">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="6"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div></td>                            
                    </tr>
                </table>
                
                <table width="100%">
                    <tr>
                        <td align="right">
                        <acesso:controlHiddenItem nomeIdentificador="adm_iferi_salvar">
                            <img onclick="Enviar();" hspace="20" vspace="15" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand"/>
                        </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
               
                <script>
                    document.body.style.backgroundColor = '#ededed';
                </script>
              </acesso:controlHiddenItem>
               </form>
            </body>
            
        <script language="javascript" for="window" event="onload">
            <!--   
                if('<bean:write name="FormCalendario" property="msgError" />' != "")
                {
                alert('<bean:write name="FormCalendario" property="msgError" />');
                }
                parent.oculta_div();

            -->
        </script> 
                   
    <%}else{%>                  
            <body style="padding-left:5px;padding-top:5px;">
              <form action="salvarAlterarFeriado" name="salvarAlterarFeriado" id="salvarAlterarFeriado" method="POST">
              <input type="hidden" name="idFeriado" value="<%=(String)request.getAttribute("idFeriado")%>"/>
             <acesso:controlHiddenItem nomeIdentificador="adm_iferi_verpagina">  
               <table align="center" width="560" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td colspan="6"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div></td>                            
                    </tr>
                    <tr>
                        <td width="105">                                    
                            <netui:label value="Data:" styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td style="text-align:left;" width="220" align="left">
                           &nbsp;&nbsp; <input type="text" id="dtFeriado" onkeypress="checaData(this);" name="dtFeriado"  value="<%=(String)request.getAttribute("dtFeriado")%>"  style='width:65px;' maxlength="10"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFeriado', '%d/%m/%Y');">
                        </td>
                        <td align="left" colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>
                            <netui:label value="Descrição: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td valign="top" colspan="5"> 
                            <input class="radio" type="radio" name="descr" value="0"  onkeypress="ocultaCampos();" onclick="ocultaCampos();">Nova
                            <input class="radio" type="radio" name="descr" value="1" checked onkeypress="exibeCampos();" onclick="exibeCampos();">Existente                       
                        </td>
                    </tr>
                    <tr>
                        <td></td>            
                        <td colspan="2">
                            <div id="div3" style="visibility:'visible'; top: 0px; left: 0px; height: 24px; padding: 0px; position:relative;">
                                <div id="div1" style="visibility:'visible'; top: 0px; left: 0px; height: 24px; padding: 0px; position:absolute;">
                                    <input type='text' name='admDescricaoNova' size='40' style='text-indent:8px;width:200px' value='' class='input' maxlength="200"/>
                                </div> 
                                <div id="div2" style="visibility:'hidden'; top: 0px; left: 0px; height: 24px; padding: 0px; position:absolute;">
                                    <%String s = (String)request.getAttribute("idDsFeriado");%>
                                    <html:select name="FormCalendario" property="admDescricaoExistente" value="<%=s%>"  styleClass='SELECT' style='text-indent:8px;width:198px'>
                                        <html:options collection="AdmDescricaoFeriadoVO" property="idNomeFeriado" labelProperty="dsFeriado" /> 
                                    </html:select>
                                </div> 
                            </div>
                        </td>                        
                        <td colspan="3">
                            <netui:label value="Feriado Móvel: " styleClass="tblEstatica_campoNome"/>
                            &nbsp;&nbsp;
                            <%String indMovel = (String)request.getAttribute("indMovel");
                            if(indMovel != null && indMovel.equals(ConstantesCRM.SONE)){%>
                                <html:select name="FormCalendario" property="indMovel" style="width:100px" styleClass="SELECT">
                                    <option value="1" selected>Sim</option>
                                    <option value="0">Não</option>
                                </html:select>  
                            <%}else{%>
                                <html:select name="FormCalendario" property="indMovel" style="width:100px" styleClass="SELECT">
                                    <option value="1">Sim</option>
                                    <option value="0" selected>Não</option>
                                </html:select>  
                            <%}%>
                        </td>                       
                    </tr>
                    <tr>
                        <td>
                            <netui:label value="Tipo do Feriado: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td colspan="2" class="tblEstatica_campoValor" align="left">
                                <%String r = (String)request.getAttribute("idTipoFeriado");%>
                                <html:select name="FormCalendario" property="admTipoFeriado" value="<%=r%>" style="text-indent:3px;width:200px" styleClass="SELECT">
                                    <html:options collection="AdmTipoFeriadoVO" property="idTipoFeriado" labelProperty="dsTipoFeriado" /> 
                                </html:select>
                        </td>
                        <td align="left">
                            
                            <netui:label value="Relatório DRC:" styleClass="tblEstatica_campoNome"/>&nbsp;&nbsp;&nbsp;
                            <%String inRelatorio = (String)request.getAttribute("inRelatorio");
                            if(inRelatorio != null && inRelatorio.equals(ConstantesCRM.SONE)){%>
                               <html:select name="FormCalendario" property="inRelatorio" style="width:100px" styleClass="SELECT">
                                    <option value="1" selected>Sim</option>
                                    <option value="0">Não</option>
                                </html:select> 
                            <%}else{%>
                                <html:select name="FormCalendario" property="inRelatorio" style="width:100px" styleClass="SELECT">
                                    <option value="1" >Sim</option>
                                    <option value="0" selected>Não</option>
                                </html:select> 
                            <%}%>
                        </td>
                        <td>&nbsp;</td>
                        <td class="tblEstatica_campoValor">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="6"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div></td>                            
                    </tr>
                </table>
                
                <table width="100%">
                    <tr>
                        <td align="right">
                        <acesso:controlHiddenItem nomeIdentificador="adm_iferi_salvaralt">
                            <img onclick="enviarEditado();" hspace="20" vspace="15" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand"/>
                        </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
               
                <script>
                    document.body.style.backgroundColor = '#ededed';
                </script>
            </acesso:controlHiddenItem>              
        </form>
         </body>
        <script language="javascript" for="window" event="onload">
            <!--   
                if('<bean:write name="FormCalendario" property="msgError" />' != "")
                {
                alert('<bean:write name="FormCalendario" property="msgError" />');
                }

                exibeCampos();
                parent.oculta_div();

            -->
        </script> 

    <%}%>
</html>