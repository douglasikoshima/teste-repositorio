<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm" />
<bean:define id="ClienteDisp"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.regrasEncaminhamentoVO.regrasEncaminhamentoDisponivelVO.tipoClienteVOArray" />
<html>
    <head>
        <title>
            
        </title>
    </head>
    <body>
        <netui:label value="TipoClienteDisponível"/><br/> 
        <netui-data:repeater dataSource="{pageFlow.listaGruposForm.tipoClienteDisponivel}">
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
        <netui-data:repeater dataSource="{pageFlow.listaGruposForm.tipoClienteAssociada}">
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
        <netui-data:repeater dataSource="{pageFlow.listaGruposForm.segmentacaoDisponivel}">
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
        <netui-data:repeater dataSource="{pageFlow.listaGruposForm.segmentacaoAssociada}">
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
        <netui-data:repeater dataSource="{pageFlow.listaGruposForm.carterizacaoDisponivel}">
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
        <netui-data:repeater dataSource="{pageFlow.listaGruposForm.carterizacaoAssociada}">
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
        <netui-data:repeater dataSource="{pageFlow.listaGruposForm.procedenciaDisponivel}">
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
        <netui-data:repeater dataSource="{pageFlow.listaGruposForm.procedenciaAssociada}">
            <netui-data:repeaterHeader><ol></netui-data:repeaterHeader>
            <netui-data:repeaterItem>
                <li>
                    <netui:label value="{container.item}" defaultValue="&nbsp;"></netui:label>
                </li>
            </netui-data:repeaterItem>
            <netui-data:repeaterFooter></ol></netui-data:repeaterFooter>
        </netui-data:repeater>
        <br/>
    </body>
</html>
        <script>
            //Desliga animação
            alert('Operação realizada com sucesso!');
            top.frameApp.oculta_div();
        </script>
