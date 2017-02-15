<!--
Módulo.....: Gestão de Sistemas
Caso de Uso: Configurar Grupo de Usuário com Regras de Encaminhamento
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/25 14:01:46 $
-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm" />
<bean:define id="ClienteDisp"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="usuarioEncaminhamentoForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.tipoClienteVOArray" />
<html>
    <head>
        <title>
            Web Application Page
        </title>
    </head>
    <body>
        <netui:label value="TipoClienteDisponível"/><br/> 
        <netui-data:repeater dataSource="{pageFlow.usuarioEncaminhamentoForm.tipoClienteDisponivel}">
            <netui-data:repeaterHeader><ol></netui-data:repeaterHeader>
            <netui-data:repeaterItem>
                <li>
                    <netui:label value="{container.item}" defaultValue="&nbsp;"></netui:label>
                </li>
            </netui-data:repeaterItem>
            <netui-data:repeaterFooter></ol></netui-data:repeaterFooter>
        </netui-data:repeater>
        <br/>

        <netui:label value="TipoClienteAssociada"/><br/> 
        <netui-data:repeater dataSource="{pageFlow.usuarioEncaminhamentoForm.tipoClienteAssociada}">
            <netui-data:repeaterHeader><ol></netui-data:repeaterHeader>
            <netui-data:repeaterItem>
                <li>
                    <netui:label value="{container.item}" defaultValue="&nbsp;"></netui:label>
                </li>
            </netui-data:repeaterItem>
            <netui-data:repeaterFooter></ol></netui-data:repeaterFooter>
        </netui-data:repeater>
        <br/>
        
        <netui:label value="SegmentacaoDisponivel"/><br/> 
        <netui-data:repeater dataSource="{pageFlow.usuarioEncaminhamentoForm.segmentacaoDisponivel}">
            <netui-data:repeaterHeader><ol></netui-data:repeaterHeader>
            <netui-data:repeaterItem>
                <li>
                    <netui:label value="{container.item}" defaultValue="&nbsp;"></netui:label>
                </li>
            </netui-data:repeaterItem>
            <netui-data:repeaterFooter></ol></netui-data:repeaterFooter>
        </netui-data:repeater>
        <br/>
        
        <netui:label value="SegmentacaoAssociada"/><br/> 
        <netui-data:repeater dataSource="{pageFlow.usuarioEncaminhamentoForm.segmentacaoAssociada}">
            <netui-data:repeaterHeader><ol></netui-data:repeaterHeader>
            <netui-data:repeaterItem>
                <li>
                    <netui:label value="{container.item}" defaultValue="&nbsp;"></netui:label>
                </li>
            </netui-data:repeaterItem>
            <netui-data:repeaterFooter></ol></netui-data:repeaterFooter>
        </netui-data:repeater>
        <br/> 
        
        <netui:label value="CarterizacaoDisponivel"/><br/> 
        <netui-data:repeater dataSource="{pageFlow.usuarioEncaminhamentoForm.carterizacaoDisponivel}">
            <netui-data:repeaterHeader><ol></netui-data:repeaterHeader>
            <netui-data:repeaterItem>
                <li>
                    <netui:label value="{container.item}" defaultValue="&nbsp;"></netui:label>
                </li>
            </netui-data:repeaterItem>
            <netui-data:repeaterFooter></ol></netui-data:repeaterFooter>
        </netui-data:repeater>
        <br/>   
        
        <netui:label value="CarterizacaoAssociada"/><br/> 
        <netui-data:repeater dataSource="{pageFlow.usuarioEncaminhamentoForm.carterizacaoAssociada}">
            <netui-data:repeaterHeader><ol></netui-data:repeaterHeader>
            <netui-data:repeaterItem>
                <li>
                    <netui:label value="{container.item}" defaultValue="&nbsp;"></netui:label>
                </li>
            </netui-data:repeaterItem>
            <netui-data:repeaterFooter></ol></netui-data:repeaterFooter>
        </netui-data:repeater>
        <br/> 
        
        <netui:label value="ProcedenciaDisponivel"/><br/> 
        <netui-data:repeater dataSource="{pageFlow.usuarioEncaminhamentoForm.procedenciaDisponivel}">
            <netui-data:repeaterHeader><ol></netui-data:repeaterHeader>
            <netui-data:repeaterItem>
                <li>
                    <netui:label value="{container.item}" defaultValue="&nbsp;"></netui:label>
                </li>
            </netui-data:repeaterItem>
            <netui-data:repeaterFooter></ol></netui-data:repeaterFooter>
        </netui-data:repeater>
        <br/> 
        
        <netui:label value="ProcedenciaAssociada"/><br/> 
        <netui-data:repeater dataSource="{pageFlow.usuarioEncaminhamentoForm.procedenciaAssociada}">
            <netui-data:repeaterHeader><ol></netui-data:repeaterHeader>
            <netui-data:repeaterItem>
                <li>
                    <netui:label value="{container.item}" defaultValue="&nbsp;"></netui:label>
                </li>
            </netui-data:repeaterItem>
            <netui-data:repeaterFooter></ol></netui-data:repeaterFooter>
        </netui-data:repeater>
        <br/>
        <script>
            //Desliga animação
            if( top.dvAnimarAguarde != null ) top.stopAnimation();
        </script>
    </body>
</html>
