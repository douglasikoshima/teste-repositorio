<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<bean:define id="Form"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm"/>
<bean:define id="dados" name="Form" property="listaContatosVO" type="br.com.vivo.fo.workflow.vo.WFListaContatosVODocument.WFListaContatosVO"/>

<html>
    <head>
        <title>Vivo Net</title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script>
            <!--
                function atendimento(idContato, inRelaciona, nmUrl){
                    if(nmUrl!=null && nmUrl!=''){
                        parent.loadPage(idContato, nmUrl, inRelaciona);
                    }else{
                        parent.iniciaAtendimento(idContato, '', inRelaciona);
                    }
                }

                function fechar(){
                    parent.ifrmPesquisa.src = 'about:blank';
                }
            -->
        </script>
    </head>
    <body rightmargin="0" leftmargin="5" bottommargin="0" topmargin="5">
        <form action="expandeArvoreContato.do" method="post" name="registrarContatoForm2">
            <vivo:tbTable selectable="true" layoutWidth="737" layoutHeight='275' tableWidth="737" styleId="tableTitle" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="left" width="40%" type="string">Contato</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="left" width="60%" type="string">Path</vivo:tbHeaderColumn>
                </vivo:tbHeader>
                <vivo:tbRows scroll="true">
                <logic:notEmpty name="Form" property="listaContatosVO.contatoFolhaArray">
                    <logic:iterate id="oLista" name="Form" property="listaContatosVO.contatoFolhaArray" type="br.com.vivo.fo.workflow.vo.WFListaContatosVODocument.WFListaContatosVO.ContatoFolha" indexId="idxLista">
                    <vivo:tbRow key="linha" onRowClick='<%="atendimento("+oLista.getIdContato()+",\'"+oLista.getInRelacionamento()+"\',\'"+(oLista.getNmUrlFunc()!=null?oLista.getNmUrlFunc():"")+"\');"%>'>
                        <vivo:tbRowColumn><bean:write name="oLista" property="nmContato"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="oLista" property="nmPath"/></vivo:tbRowColumn>
                    </vivo:tbRow>
                    </logic:iterate>
                </logic:notEmpty>
                <%if(dados==null || dados.getContatoFolhaArray()==null || dados.getContatoFolhaArray().length<=0){%>
                    <vivo:tbRow key="linha">
                        <div align="center"><br><bean:write name="Form" property="listaContatosVO.msgStatus"/></div>
                    </vivo:tbRow>
                <%}%>
                </vivo:tbRows>
            </vivo:tbTable>
        </form>
    </body>
</html>