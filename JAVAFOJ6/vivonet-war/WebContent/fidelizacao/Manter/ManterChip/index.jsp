<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>

<bean:define id="Form"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterChipFormBean"/>
<bean:define id="ListaDDD"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterChipFormBean.listaDDD"/>

<bean:define id="chipsCadastrados" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterChipFormBean.chipsCadastrados"/>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
<netui-template:section name="headerSection">

    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/Formatacao.js"></script>
    <script language="javascript" type="text/javascript">
        editarChip = function(id,d,cdAvulso,vlAvulso,cdPre,vlPre){
            f = document.forms[0];
            f.id.value=id;
            f.ddd.value=d;
            f.cdChipAvulso.value=cdAvulso;
            f.vlChipAvulso.value=vlAvulso;
            f.cdChipPre.value=cdPre;
            f.vlChipPre.value=vlPre;
            document.getElementById('btIncluir').src = "/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif";            
        }
        
         pesquisaChip=function() {
            var action = 'pesquisarChip.do?ddd='+ document.forms[0].ddd.value;
            document.forms[0].action = action;
            top.frameApp.mostrar_div();
            document.forms[0].submit();
        }     
        
        limpar = function(){
            f = document.forms[0];
            f.id.value="";
            f.ddd.value="";
            f.cdChipAvulso.value="";
            f.vlChipAvulso.value="";
            f.cdChipPre.value="";
            f.vlChipPre.value="";
            
            document.getElementById('btIncluir').src = "/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif";            
        }   
        
        excluirChip = function(id){
            if (window.confirm("Deseja realmente excluir este Chip??")) {
                var action = 'excluirChip.do?idExcluir='+ id;
                document.forms[0].action = action;
                top.frameApp.mostrar_div();
                document.forms[0].submit();
            }
        }
        
        salvarChip = function(){
            f = document.forms[0];
            if(f.ddd.value==""){
                alert('DDD inválido!');
                f.ddd.focus();
                return;
            }
            if(f.cdChipAvulso.value==""){
                alert('Código Chip Avulso inválido!');
                f.cdChipAvulso.focus();
                return;
            }
            if(f.vlChipAvulso.value=="" || f.vlChipAvulso.value=="0,00"){
                alert('Valor Chip Avulso inválido!');
                f.vlChipAvulso.focus();
                return;
            } 
            if(f.cdChipPre.value==""){
                alert('Código Chip Pré-Programado inválido!');
                f.cdChipPre.focus();
                return;
            }
            if(f.vlChipPre.value=="" || f.vlChipPre.value=="0,00"){
                alert('Valor Chip Pré-Programado inválido!');
                f.vlChipPre.focus();
                return;
            }
            
            if(f.id.value!=""){
                var action = 'alterarChip.do?idChip='+ f.id.value + '&idDDD=' + f.ddd.value + '&cdChipAvulso=' + f.cdChipAvulso.value + '&cdChipPreProg=' + f.cdChipPre.value + '&vlChipAvulso=' + f.vlChipAvulso.value + '&vlChipPreProg=' + f.vlChipPre.value;
                document.forms[0].action = action;
                top.frameApp.mostrar_div();
                document.forms[0].submit();
            }else{
                var action = 'incluirChip.do?idDDD=' + f.ddd.value + '&cdChipAvulso=' + f.cdChipAvulso.value + '&cdChipPreProg=' + f.cdChipPre.value + '&vlChipAvulso=' + f.vlChipAvulso.value + '&vlChipPreProg=' + f.vlChipPre.value;
                document.forms[0].action = action;
                top.frameApp.mostrar_div();
                document.forms[0].submit();
            }
        }
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <acesso:controlHiddenItem nomeIdentificador="fid_manutchip_verpagina">
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                top.frameApp.oculta_div();
                document.body.style.backgroundColor = "#ededed";
                //if (document.forms[0].idChip == undefined) {
                   // document.getElementById("btAlterar").style.display = "none";
                //}
            -->
        </SCRIPT>
        <form action="begin.do" onSubmit="return false;" method="post">
            <input type="hidden" name="id" value=""/>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="2"></div>
            
            <table width="760" cellpadding="0" cellspacing="0" class="tbl_bgGray" align="center">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td width="20%" style="text-indent:10px;">
                        <netui:label value="DDD: " styleClass="tblEstatica_campoNome"/>
                        <html:select  name="Form" property="ddd" style="width:80px" styleClass="SELECT" size="1" tabindex="1" >
                            <option value="">Selecione</option>
                            <html:options collection="ListaDDD" property="descricao" labelProperty="descricao" />
                        </html:select>
                    </td>
                    <td width="40%" style="text-indent:10px;">
                        <table width="100%">
                            <tr>
                                <td width="60%">
                                    <netui:label value="Código Chip Avulso: " styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="40%">
                                    <html:text name="Form" property="cdChipAvulso" style="width:100px" styleClass="input" maxlength="14" tabindex="2"/>
                                </td>
                            </tr>
                        </table>
                        
                        
                    </td>
                    <td width="40%" style="text-indent:10px;">
                        <table width="100%">
                            <tr>
                                <td width="60%">
                                    <netui:label value="Valor Chip Avulso: " styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="40%">
                                    <html:text name="Form" property="vlChipAvulso" value="0,00" style="width:100px;text-align:right;" styleClass="input" maxlength="8" onkeypress="return exibirValorFormatado(event);" onkeydown="return capturaCodTecla(event);" tabindex="3"/>
                                </td>
                            </tr>
                        </table>
                    </td>                    
                </tr>
                <tr>
                    <td width="20%" style="text-indent:10px;">

                    </td>
                    <td width="40%" style="text-indent:10px;">
                        <table width="100%">
                            <tr>
                                <td width="60%">
                                    <netui:label value="Código Chip Pré-Programado: " styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="40%">
                                    <html:text name="Form" property="cdChipPre" style="width:100px" styleClass="input" maxlength="14" tabindex="4"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="40%" style="text-indent:10px;">
                        <table width="100%">
                            <tr>
                                <td width="60%">
                                    <netui:label value="Valor Chip Pré-Programado: " styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="40%">
                                    <html:text name="Form" property="vlChipPre" value="0,00" style="width:100px;text-align:right;" styleClass="input" maxlength="8" onkeypress="return exibirValorFormatado(event);" onkeydown="return capturaCodTecla(event);" tabindex="5"/>
                                </td>
                            </tr>
                        </table>
                    </td>                    
                </tr>    
                <tr>
                    <td colspan="3" align="right">
                        
                        <img onclick="limpar();" vspace="5" id="btLimpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" style="border:none;cursor:hand;"/>                        
                        <img onclick="pesquisaChip();" vspace="5" id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:hand;" />
                        <img onclick="salvarChip();" vspace="5" id="btIncluir" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" style="border:none;cursor:hand;"/>                                           
                    </td>               
                </tr>                              
            </table>
            
            <br>
        <table>
            <tr>
                <td>
                    <vivo:tbTable selectable="true" styleId="tbChipsCadastrados" layoutHeight="255" layoutWidth="750" tableWidth="750">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn width="50" type="String" align="center">DDD</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="197" type="String">C&oacute;digo do Chip Avulso</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="127" type="String">Valor unitário</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="197" type="String">C&oacute;digo do Chip Pr&eacute;-Programado</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="127" type="String">Valor unitário</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="25" type="String"></vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn width="25" type="String"></vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate id="iteratorChipsCadastrados" name="Form" property="chipsCadastrados">
                            <vivo:tbRow key="linha1">
                                <vivo:tbRowColumn>
                                    <bean:write name="iteratorChipsCadastrados" property="nrDDD" />
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <bean:write name="iteratorChipsCadastrados" property="cdChipAvulsoSAP" />
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <bean:write name="iteratorChipsCadastrados" property="vlChipAvulsoSAP" />
                                </vivo:tbRowColumn>  
                                <vivo:tbRowColumn>
                                    <bean:write name="iteratorChipsCadastrados" property="cdChipPreProgSAP" />
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <bean:write name="iteratorChipsCadastrados" property="vlChipPreProgSAP" />
                                </vivo:tbRowColumn> 
                                <vivo:tbRowColumn>
                                    <a onclick="editarChip('<bean:write name="iteratorChipsCadastrados" property="id"/>','<bean:write name="iteratorChipsCadastrados" property="nrDDD" />','<bean:write name="iteratorChipsCadastrados" property="cdChipAvulsoSAP" />','<bean:write name="iteratorChipsCadastrados" property="vlChipAvulsoSAP" />','<bean:write name="iteratorChipsCadastrados" property="cdChipPreProgSAP" />','<bean:write name="iteratorChipsCadastrados" property="vlChipPreProgSAP" />');">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" alt="Alterar Chip" border="0">
                                    </a>                                    
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <a onclick="excluirChip('<bean:write name="iteratorChipsCadastrados" property="id"/>');">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" alt="Excluir Chip" border="0">
                                    </a>
                                </vivo:tbRowColumn>                                
                            </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>                    
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                
                    <table width="770" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                        <tr>
                            <td width="100" valign="middle"><b>&nbsp;Legendas:</b></td>
                            <td width="150">
                                <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle">
                                &nbsp;Alterar 
                            </td>
                            <td width="520">
                                <img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle">
                                &nbsp;Excluir
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <vivo:alert atributo="msgErro" scope="request" />
        <vivo:alert atributo="msgOk" scope="request"  />
        </form>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>



