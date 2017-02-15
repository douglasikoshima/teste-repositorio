<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>


<acesso:controlInitEnv/>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script> 

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="arquivoForm" />
<bean:define id="listaCampanha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="arquivoForm.listaCampanha" />
<bean:define id="listaSubCampanha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="arquivoForm.listaSubCampanha" />
<bean:define id="versaoSelecionada" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="arquivoForm.listaVersao" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Fidelizacao" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection">
 <script language="Javascript">
 
    function consultaIFrameSubCampanha() 
    {         var objForm = document.forms["arquivoForm"];
        
        if(objForm.idCampanha.value == "")
        {
            alert('Favor selecionar uma Campanha!');
            objForm.idCampanha.focus();
            
        }
        else
        {
            objForm.idSubCampanha.options.length = 0;
            objForm.versaoSelecionada.options.length = 0;
            
            //Monta o path seleção
            objForm.target = "innerBrowser";
            objForm.action = "carregarSubCampanha.do";
            objForm.submit();
            
            top.frameApp.mostrar_div();
        }
    }
    
    function consultaIFrameVersao() 
    {            
        var objForm = document.forms["arquivoForm"];

        if(objForm.idSubCampanha.value == "")
        {
            alert('Favor selecionar uma Sub Campanha!');
            objForm.idSubCampanha.focus();
            
        }
        else
        {
            objForm.versaoSelecionada.options.length = 0;
            
    
            //Monta o path seleção
            objForm.target = "innerBrowser";
            objForm.action = "carregarVersao.do";
            objForm.submit();
            
            top.frameApp.mostrar_div();
        }
    }    
        
        function submit()
        {
            var objForm = document.forms["arquivoForm"];

            if(objForm.idCampanha.value == "")
            {
                alert('Favor selecionar uma Campanha!');
                objForm.idCampanha.focus();
                return false;
            }
            else if(objForm.idSubCampanha.value == "")
            {
                alert('Favor selecionar uma Sub Campanha!');
                objForm.idSubCampanha.focus();            
                return false;
            }
            else if(objForm.versaoSelecionada.value == "")
            {
                alert('Favor selecionar uma Versão!');
                objForm.versaoSelecionada.focus();            
                return false;
            }            
            else if(trim(objForm.elements["dtInicio"].value) == "" || !validaData ( objForm.elements["dtInicio"].value ) )
            {
                alert('Data inicial inválida!');
                objForm.elements["dtInicio"].focus();            
                return false;
            }        
            else if ( trim(objForm.elements["dtFim"].value) == "" || !validaData ( objForm.elements["dtFim"].value ) )
            {
                alert('Data final inválida!');
                objForm.elements["dtFim"].focus();
                return false;
            }   
            else if (objForm.elements["dtFim"].value != objForm.elements["dtInicio"].value && (!validaDataFinal(objForm.elements["dtInicio"].value,objForm.elements["dtFim"].value ))) {
                alert('Data final não pode ser menor que a data inicial!');
                objForm.elements["dtFim"].focus();            
                return false;
            }                      

          return true;
        }

        function gerar(){
            var obj = document.forms["arquivoForm"];
            obj.method="POST";
            obj.action = "/FrontOfficeWeb/campanha/arquivoResultado/gerarArquivo.do";

            if( submit() )
                obj.submit();
        }

    </script> 
    </netui-template:section>
    <netui-template:section name="bodySection">
    
    <acesso:controlHiddenItem nomeIdentificador="cam_indexarqresul_verpagina">
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();
    </script>
    
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="idFrelatorio" height="470" width="790" label="Campanha > Relatórios" operacoes="" scroll="N">
    <form action="gerarArquivo.do" name="arquivoForm" method="post">
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <table width="100%" cellpadding="0" cellspacing="0" border="0" height="100%">
        <tr>
        <td valign="top">
        
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:quadro id="qdMain" height="0" width="780" scroll="no" label="Filtros">
        <table width="100%">
            <tr>
                <td >
                <table id="todos3" width="100%" cellpadding="3" cellspacing="0" border="0">
                    <tr valign="top">
                        <td align="right" class="tblEstatica_campoNome" width="140"><font color="red">*</font>&nbsp;Campanha:
                        </td>
                        <td  colspan="3">
                        <html:select name="form" property="idCampanha" size="1" style="width=525px;height=10px" onchange="consultaIFrameSubCampanha();">
                        <option value=""></option>
                        <html:options collection="listaCampanha" property="codigo" labelProperty="sigla"/> </html:select>
                        </td>
                    </tr>
                    <tr  valign="top">
                        <td align="right" class="tblEstatica_campoNome" width="140"><font color="red">*</font>&nbsp;Sub
                        Campanha:
                        </td>
                        <td colspan="3">
                        <html:select name="form" property="idSubCampanha" size="1" style="width=525px;height=10px"  onchange="consultaIFrameVersao();">>
                            <option value=""></option>
                            <html:options collection="listaSubCampanha" property="codigo" labelProperty="nmSubCampanha"/> 
                        </html:select>
                        </td>
                    </tr>
                    
                    <tr  valign="top">
                        <td align="right" class="tblEstatica_campoNome" width="140"><font color="red">*</font>&nbsp;Versão:
                        </td>
                        <td colspan="3">
                        <html:select name="form" property="versaoSelecionada" size="1" style="width=150px;height=10px">
                            <html:options collection="versaoSelecionada" property="codigo" labelProperty="descricao"/> 
                        </html:select>
                        </td>
                    </tr>                       

                </table>
                <table width="700" border="0" cellpadding="3" cellspacing="0">
                    <tr id="datas" valign="top">
                        <td class="tblEstatica_campoNome" align="right" width="140"><font color="red">*</font>&nbsp;Data Inicial:</td>
                        <td width="250"><html:text name="form" property="dtInicio"  onKeyUp="this.value = Format(this.value,'##/##/####');" styleClass="textfield" maxlength="10" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtInicio', '%d/%m/%Y');" onkeydown="alert('dia')"></td>
                        <td width="111" align="right" class="tblEstatica_campoNome" ><font color="red">*</font>&nbsp;Data Final:</td>
                        <td ><html:text name="form" property="dtFim"  onKeyUp="this.value = Format(this.value,'##/##/####');" maxlength="10" styleClass="textfield" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFim', '%d/%m/%Y');" onkeydown="alert('dia')"></td>
                    </tr>
                    <!--
                    <tr>
                        <td class="tblEstatica_campoNome" align="right" width="140">&nbsp;Recontatos:</td>
                        <td colspan="3">
                            <html:radio style="border:none;background-color:#ededed;"  name="form" value="1" property="inDistincao"/>Sim
                            <html:radio style="border:none;background-color:#ededed;"  name="form" value="0" property="inDistincao"/>Não
                        </td>
                    </tr>                    
                    -->
                    <tr>
                        <td  colspan="4" align="right" >
                        <acesso:controlHiddenItem nomeIdentificador="cam_arqresul_gerar">
                            <img vspace="10"
                            	 class="button"
                            	 onmouseup="gerar();"
                            	 src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"
                            	 border="0"
                            	 formSubmit="false"/> 
                        </acesso:controlHiddenItem>
                        </td> 
                    </tr>
                    <vivo:alert atributo="msgRetorno" />
                </table>
                </td>
            </tr>
        </table>
        </vivo:quadro>
        </td>
        </tr>
        <tr>
        <td>
        <table height="100%" border="0">
        <tr>
        <td valign="bottom"><img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" border="0" onClick="top.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;cursor:hand;return false"/>
        </td>
        </tr>
        </table>
        </td>
        </tr>
        </table>
    </form>
    </vivo:sessao> 
    <iframe id="lyrInnerBrowser" name="innerBrowser" style="display:none;" width="0px" height="0px">
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
