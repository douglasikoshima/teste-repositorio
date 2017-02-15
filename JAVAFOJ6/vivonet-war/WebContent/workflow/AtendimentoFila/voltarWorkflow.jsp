<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<html>
<script>

if (parent.parent.ifrFilas != null) {
    parent.parent.ifrFilas.startRefresh();
    aCheckbox=parent.parent.document.getElementsByTagName('input');
} else if (parent.ifrFilas != null) {
    parent.ifrFilas.startRefresh();
    aCheckbox=parent.document.getElementsByTagName('input');

}
for(i=0;i<aCheckbox.length;i++){
    if(aCheckbox[i].type == 'checkbox'){
        aCheckbox[i].checked=false;
    }    
}
</script>
</html>
