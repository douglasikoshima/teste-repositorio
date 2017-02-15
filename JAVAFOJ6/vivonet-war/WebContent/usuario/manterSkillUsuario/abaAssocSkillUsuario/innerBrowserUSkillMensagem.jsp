<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

    <script FOR=window EVENT=onload LANGUAGE="JScript">
        parent.parent.oculta_div();
    </script>
    <script>
        <logic:equal scope="request" name="statusGravacao" value="1">
            parent.document.forms[0].usuarioSelecionado.length = 0;
		    //parent.document.forms[0].contatoSelecionado.length = 0;                        
        </logic:equal>
    </script>    

    <vivo:alert atributo="msgStatus" scope="request"/>

