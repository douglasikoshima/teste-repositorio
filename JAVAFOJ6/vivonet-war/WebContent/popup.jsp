<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
        <script language="JavaScript" type="text/javascript">
            var linksHistory = new Array();
            function autoAjustScreen(){
                linksHistory.push(document.getElementById("popIframe").src);
                window.external.dialogHeight = document.frames["popIframe"].document.body.scrollHeight + 30 + "px";
                var calcY = (screen.height - (document.frames["popIframe"].document.body.scrollHeight + 30))/2
                window.dialogTop = calcY;
                if(document.frames["popIframe"].document.body.children[0].tagName.toLowerCase() == "table"){
                    window.external.dialogWidth = parseInt(document.frames["popIframe"].document.body.children[0].width) + 30 + "px";
                    var calcX = (screen.width - parseInt(document.frames["popIframe"].document.body.children[0].width) + 30)/2
                    window.dialogLeft = calcX;
                }
            }

            function back(){
                linksHistory.pop();
                document.getElementById("popIframe").src = linksHistory[linksHistory.length-1];
            }

            oParam = window.dialogArguments;
            oParam.title = 'VIVO - Front Office ' + ((oParam.title) ? ' - ' + oParam.title : '');
            document.title = oParam.title;
            try{
                Parameter = window.dialogArguments;
                pageToOpen = "";
                if(Parameter.pageToOpen){
                    if(Parameter.path){
                        pageToOpen = Parameter.path + Parameter.pageToOpen;
                    } else {
                        pageToOpen = Parameter.pageToOpen;
                    }
                }
                this.onload = function(){
                    //document.getElementById("popIframe").attachEvent('onload', autoAjustScreen);
                    document.getElementById("popIframe").src = pageToOpen;
                }
            } catch(e) {
                alert(e.message);
            }
    </script>
    </head>
    <body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
        <iframe src="" frameborder="0" marginheight="0" marginwidth="0" height="100%" width="100%" scrolling="no" id="popIframe"></iframe>
    </body>
</html>

