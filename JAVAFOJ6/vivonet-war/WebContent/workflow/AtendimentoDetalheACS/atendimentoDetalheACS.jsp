<!--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento Detalhe ACS
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/17 17:25:01 $
-->
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
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheACSForm"/>
<bean:define id="atdDetalhe" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheACSForm.atendimentoDetalheACS"/>

    <head>
        <title>
            Web Application Page
        </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <acesso:controlHiddenItem nomeIdentificador="wor_adacs_verpagina">
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        
        <vivo:sessao id="qdMain" height="470" width="790" operacoes="Atendimento Detalhe ACS" scroll="no">
            <form id="detalheACSForm">
                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                
                <table width="780" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="160">
                            Nº do Processo: 
                        </td>
                        <td width="220">
                            <html:text name="atdDetalhe" property="idProcesso" readonly="true" style="width=95"/>                
                        </td>
                        <td rowspan="11" width="20"></td>
                        <td width="130">
                            Nome Cliente Usuário: 
                        </td>
                        <td width="250">    
                            <html:text name="atdDetalhe" property="nmClienteUsuario" readonly="true" style="width:250px;"/>                    
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Número da Linha: 
                        </td>
                        <td>
                            <html:text name="atdDetalhe" property="nrLinha" readonly="true" style="width=80"/>
                        </td>
                        <td>
                            Nome para Contato: 
                        </td>    
                        <td>
                            <html:text name="atdDetalhe" property="nmContato" readonly="true" style="width:250px;"/>                    
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Telefone para Contato: 
                        </td>    
                        <td>
                            <html:text name="atdDetalhe" property="nmTelefoneContato" readonly="true" style="width=80"/>
                        </td>
                        <td>
                            E-mail para Contato: 
                         </td>   
                         <td>   
                            <html:text name="atdDetalhe" property="nmEmailContato" readonly="true" style="width:200px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Cliente VIVO: 
                         </td>
                         <td>                       
                            <html:text name="atdDetalhe" property="inClienteVIVO" readonly="true" style="width=30"/>
                        </td>
                        <td>
                            Tipo de Cliente: 
                        </td>
                        <td>
                            <html:text name="atdDetalhe" property="nmTipoCliente" readonly="true" style="width=95"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            CPF/CNPJ: 
                        </td>
                        <td>                        
                            <html:text name="atdDetalhe" property="nmCPFCNPJ" readonly="true" style="width=95"/>
                        </td>
                        <td>
                            Tipo de Serviço: 
                        </td>    
                        <td>    
                            <html:text name="atdDetalhe" property="nmTipoServico" readonly="true" style="width=95"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Tipo de Carteira: 
                        </td>    
                        <td>    
                            <html:text name="atdDetalhe" property="nmTipoCarteira" readonly="true" style="width=95"/>
                        </td>
                        <td>
                            Segmentação: 
                        </td>    
                        <td>    
                            <html:text name="atdDetalhe" property="nmSegmentacao" readonly="true" style="width=95"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Processo Técnico: 
                        </td>    
                        <td>    
                            <html:text name="atdDetalhe" property="inProcessoTecnico" readonly="true" style="width=30"/>
                        </td>
                        <td>
                            Processo: 
                        </td>    
                        <td>    
                            <html:text name="atdDetalhe" property="nmProcesso" readonly="true" style="width=250px"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            Nome Usu&aacute;rio Grupo BKO: 
                        </td>    
                        <td>    
                            <html:text name="atdDetalhe" property="nmUsuarioGrupoBKO" readonly="true" style="width=95"/>
                        </td>
                        <td>
                            Nome Usu&aacute;rio BKO: 
                        </td>    
                        <td>    
                            <html:text name="atdDetalhe" property="nmUsuarioBKO" readonly="true" style="width:95px"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Data Trat. BKO: 
                        </td>    
                        <td>    
                            <html:text name="atdDetalhe" property="dtTratamentoBKO" readonly="true" style="width=95px"/>
                        </td>
                        <td>
                            Data de Atualização: 
                        </td>
                        <td>    
                            <html:text name="atdDetalhe" property="dtAtualizacao" readonly="true" style="width=95px"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Data de Importação: 
                        </td>    
                        <td>    
                            <html:text name="atdDetalhe" property="dtImportacao" readonly="true" style="width=95px"/>
                        </td>                
                        <td>
                            Tipo de Atendimento
                        </td>
                        <td>
                            <html:text name="atdDetalhe" property="tipoAtendimentoVO.dsTipoAtendimento" readonly="true" style="width:95px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" style="padding-top:3px;">
                            Obs. Hist&oacute;rico: 
                        </td>    
                        <td style="padding-left:3px;">
                            <html:textarea name="atdDetalhe" property="obsHistorico" readonly="true" style="width:230px;height:60px;"/>
                        </td>
                        <td valign="top" style="padding-top:3px;">
                            Obs. Atendimento: 
                        </td>    
                        <td style="padding-left:3px;">    
                            <html:textarea name="atdDetalhe" property="obsAtendimento" readonly="true" style="width:250px;height:60px;"/>
                        </td>
                    </tr>
                </table>
                
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                
                <vivo:abaGrupo id="btAba0" width="780" height="16" styleClass="abatexto">
                        <acesso:controlHiddenItem nomeIdentificador="wor_adacs_abaandamento"><vivo:abaItem id="btAndamento"   onclick="mostraAba(btAba0, btAndamento, 'andamento');"   value="Andamento" select="S"/></acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="wor_adacs_abacomentario"><vivo:abaItem id="btComentarios" onclick="mostraAba(btAba0, btComentarios, 'comentarios');" value="Comentários" select="N"/></acesso:controlHiddenItem>                
                </vivo:abaGrupo>
                
                <div style="border: 1px solid #adadad;width:780px;" align="center">
                    <iframe name="ifrmACS" src="tabelaAndamentoACS.jsp" width="778" height="158" scrolling="no" frameborder="0"></iframe>
                </div>
                
                <img vspace="6" src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_fechar_over.gif" onClick="fechar(); return false" style="border:none;"/>
                
            </form>
        </vivo:sessao>
        <script>
            function fechar(){
                //Fecha o quadro flutuante
                if(parent.dvAtendimento)
                    parent.dvAtendimento.style.display = 'none';
                else
                    parent.$('divPopupTI').style.display = 'none';
            }

            function mostraAba(abaPai, abaFilho, div){

                //Inicio animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

                if(div == 'andamento'){
                    document.forms["detalheACSForm"].method = "POST";
                    document.forms["detalheACSForm"].target = "ifrmACS";
                    document.forms["detalheACSForm"].action = "tabelaAndamentoACS.jsp";
                    document.forms["detalheACSForm"].submit();
                }
                else{
                    document.forms["detalheACSForm"].method = "POST";
                    document.forms["detalheACSForm"].target = "ifrmACS";
                    document.forms["detalheACSForm"].action = "tabelaComentariosACS.jsp";
                    document.forms["detalheACSForm"].submit();
                }
                    
                abaSelected(abaPai, abaFilho); 
            }
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>
   
        </acesso:controlHiddenItem>
    </body>
