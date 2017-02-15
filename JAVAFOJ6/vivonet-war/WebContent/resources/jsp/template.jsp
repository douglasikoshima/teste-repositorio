<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<html>
    <head>
        <title><netui-template:attribute name="title"/></title>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" rel='stylesheet' type='text/css'>
        <script language="JavaScript">
        <!--
        function logoVivo(esconde) {
            document.hdr_big = document.all['hdr_big'];
            var logoBig = "<table width='765' height='55' border='0' cellpadding='0' cellspacing='0' background='/FrontOfficeWeb/resources/images/hdr_big_bgctrl.gif'><tr>"+
        
                "<td width='14' valign='top' background='/FrontOfficeWeb/resources/images/hdr_big_bgesq.gif'><div align='right'><img src='/FrontOfficeWeb/resources/images/hdr_bt_close.gif' width='11' height='12' vspace='3' id='hdr_button' style='cursor:pointer' onClick='logoVivo(true)'></div></td>"+
                "<td width='2' background='/FrontOfficeWeb/resources/images/hdr_big_bgsepara.gif'><img src='/FrontOfficeWeb/resources/images/transp.gif' width='2' height='2'></td>"+
                "<td width='248'><img id='ddfd' src='/FrontOfficeWeb/resources/images/hdr_big_logovivo.jpg' width='108' height='55'></td>"+
                "<td width='371'><img src='/FrontOfficeWeb/resources/images/hdr_big_logofo.jpg' width='231' height='55'></td>"+
                "<td width='120' class='hdr_big_subtitulo'><span valign=middle><netui-template:attribute name="area"/></span></td>"+
                "<td width='10' background='/FrontOfficeWeb/resources/images/hdr_big_bgdir.gif'></td>"+
                "</tr></table>";
            var logoSmall = "<table width='765' height='23' border='0' cellpadding='0' cellspacing='0' background='/FrontOfficeWeb/resources/images/hdr_sml_bgctrl.gif'><tr>"+
                "<td width='14' valign='top' background='/FrontOfficeWeb/resources/images/hdr_sml_bgesq.gif'><div align='right'><img id='hdr_button' src='/FrontOfficeWeb/resources/images/hdr_bt_open.gif' width='11' height='12' style='cursor:pointer' vspace='3' onClick='logoVivo(false)'></div></td>"+
                "<td width='2' background='/FrontOfficeWeb/resources/images/hdr_big_bgsepara.gif'><img src='/FrontOfficeWeb/resources/images/transp.gif' width='2' height='2'></td>"+
                "<td width='248'><img id='ddfd' src='/FrontOfficeWeb/resources/images/hdr_sml_logovivo.gif' width='34' height='23'></td>"+
                "<td width='371'><img src='/FrontOfficeWeb/resources/images/hdr_sml_logofo.gif' width='89' height='23'></td>"+
                "<td width='120' class='hdr_sml_subtitulo'><netui-template:attribute name="area"/></td>"+
                "<td width='10' background='/FrontOfficeWeb/resources/images/hdr_sml_bgdir.gif'></td>"+
                "</tr></table>";
            if(esconde){
                document.hdr_big.innerHTML=logoSmall;
            }else{
                document.hdr_big.innerHTML=logoBig;
            }
        }
        -->
        </script>
    </head>
    <body topmargin='0' leftmargin='0' onLoad="logoVivo(false)" scroll='no'>
        <table cellpadding="5" cellspacing="0">
            <tr>
                <td><div id='hdr_big' style='top:0; visibility:visible'></div></td>
            </tr>
        </table>
        <netui-template:includeSection name="bodySection"></netui-template:includeSection>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
