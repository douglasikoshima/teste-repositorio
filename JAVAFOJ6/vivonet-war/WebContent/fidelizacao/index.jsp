<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Bem-Vindo"/>
    <netui-template:setAttribute name="modulo" value="Bem-Vindo"/>
    <netui-template:section name="headerSection">
    </netui-template:section>
    <netui-template:section name="bodySection">
    	<acesso:controlHiddenItem nomeIdentificador="fid_idxfidelizacao_verpagina">
            <!--APLICAÇÃO->MENU-->
            <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
                    
            <!--APLICACAO-->
            <!-- <vivo:quadro id="qdMain" height="478" width="790" label="Bem-Vindo"> -->
                <table width="790" height="440" border="0" cellpadding="0" cellspacing="0">
                    <tr>                    
                        <td valign="middle" align="center">
                            <table width="462" height="101">
                                <tr>
                                    <td><img src="<%=request.getContextPath()%>/resources/images/inmodulos_fidelizacao.jpg"></td>
                                    <td width="10"></td>
                                    <td><img src="<%=request.getContextPath()%>/resources/images/inmodulos_fidelizacao.gif"></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
        <!--</vivo:quadro> -->
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
