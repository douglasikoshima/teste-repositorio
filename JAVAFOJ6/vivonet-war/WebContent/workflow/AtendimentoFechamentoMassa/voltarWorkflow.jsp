<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<html>
<script>
aCheckbox=parent.document.getElementsByTagName('input');
for(i=0;i<aCheckbox.length;i++){
    if(aCheckbox[i].type == 'checkbox'){
        aCheckbox[i].checked=false;
    }    
}
</script>
</html>
