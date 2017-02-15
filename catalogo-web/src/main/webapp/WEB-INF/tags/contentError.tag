<%@ tag pageEncoding='UTF-8' %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>

<%@attribute name="id" required="true" type="java.lang.String" %>

<catalogo:contentSimple>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td rowspan="2" class="box_middle_left"></td>
			<td>
				<div class="box_middle_center">
					<div id="${id}" class="box_middle_center_conteudo conteudo_divErros"><jsp:doBody /></div>
				</div>
			</td>
			<td rowspan="2" class="box_middle_right"></td>
		</tr>
	</table>
</catalogo:contentSimple>	
