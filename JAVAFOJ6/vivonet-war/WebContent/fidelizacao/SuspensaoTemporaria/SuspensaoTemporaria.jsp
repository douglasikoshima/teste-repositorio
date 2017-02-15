<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript">
            function fecharjanela(){
                history.back(-1);
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <vivo:body idTable="tbMain" idDiv="dvMain" height="500" width="800">
    <vivo:quadro id="qdMain" height="450"  width="750" label="Suspensão Temporária">
     <table  width="100%" >
             <tr>
                <td>Cliente:</td>
                <td>Segmentação:</td>
                <td>Carteirização:
                </td>
                <td>Tipo de Cliente:</td>
            </tr>
            <tr>
                <td>
                    <netui:textBox dataSource="{}"  styleClass="textfield" size="50"  defaultValue="Paulo José Santos"/>
                </td>
                <td>
                    <netui:textBox dataSource="{}"  styleClass="textfield" size="20"  defaultValue="Diamante 2"/>
                </td>
                <td>
                    <netui:textBox dataSource="{}"  styleClass="textfield" size="20"  defaultValue="Cinco Estrelas"/>
                </td>
                  <td>
                    <netui:textBox dataSource="{}"  styleClass="textfield" size="20"  defaultValue="Pessoa Física"/>
                </td>
            </tr>
        </table>
    <table>

         <tr>
            <td>&nbsp;</td>
        </tr>
         <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>

            <td>
            <netui:textArea dataSource="{}" cols="85" rows="15" defaultValue="Cliente retido com suspensão temporária por 90 dias, de 11/11/2003
até 10/01/2004. O mesmo está ciente que após este prazo a linha será
automaticamente reativada, sendo necessário novo contato
caso não seja este seu interesse."  />

            </td>
        </tr>
        <tr>
            <td align="right">
	            <img src="/FrontOfficeWeb/resources/images/bt_vaipensar_nrml.gif"
	            	 border="0" 
	            	 class="button"
	            	 onmouseup="window.location.href='SuspensaoTemporaria.do';" />
            	<img src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif"
	            	 border="0" 
	            	 class="button"
	            	 onmouseup="fecharjanela()" />
	           	<img src="/FrontOfficeWeb/resources/images/bt_ok_nrml.gif"
	            	 border="0" 
	            	 class="button"
	            	 onmouseup="fecharjanela()" />
             </td>
        </tr>
    </table>
    </vivo:quadro>
    </vivo:body>
</netui-template:section>
</netui-template:template>