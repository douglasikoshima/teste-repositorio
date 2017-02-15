<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0" onload="reloadPage()">
        <SCRIPT LANGUAGE="JavaScript">
        <!--
            var status = 2;
            var mensagem = "";
            function reloadPage(){   
                var msg = document.getElementById("mensagemErro");		
                if(status == 2){          			
                    var ImgSource = document.getElementById("ImgSource");
                    ImgSource.src = "loadDetalhadaLoad.do?data=<%=(String)request.getAttribute("data")%>&rand="+getTime();                   
                }else if(status == 1){
                    location.href = "abaDetalhada.jsp?rand="+getTime();
                }
                else{            			
                    msg.innerHTML = mensagem;
                    parent.parent.oculta_div();
                }
            }
        
        	function getTime(){
        	   var d, s, t;
        	   var MinMilli = 1000 * 60;
        	   var HrMilli = MinMilli * 60;
        	   var DyMilli = HrMilli * 24;
        	   d = new Date();
        	   t = d.getTime();  
        	   return t;
        	}    
        //-->
        </SCRIPT>
        <P>&nbsp;<P>&nbsp;
        <center><div id="mensagemErro"></div></center>
        <iframe ID="ImgSource" src="blank.jsp" style="display:'none';" width="300" height="200"></iframe>
    </body>
</html>