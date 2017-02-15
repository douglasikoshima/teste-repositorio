<%@ tag language="java" pageEncoding="ISO-8859-1"%>

	<div class="popupConfirmacao">
		<div class="popup_header">
			<div class="popup_box_top_center">
				<div class="box_titulo">Confirmação</div>
			</div>
			<div class="popup_box_top_left">
			</div>
			<div class="popup_box_top_right_close" onclick="fecharPopup($(this).up(2).id);">
			</div>
		</div>
		<div class="popup_middle">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td class="box_middle_left">
					</td>
					<td>
						<div class="box_middle_center">
							<div class="popup_box_middle_center_conteudo" align="center" style="background-color: white;">
								<div  style="width:90%;padding-top:5px;margin-bottom:5px; background-color: white; ">
									<div id="div_erros_popup_confirmacao">
									</div>
									<jsp:doBody/>
								</div>
							</div>
						</div>
					</td>
					<td class="box_middle_right">
					</td>
				</tr>
			</table>
		</div>
		<div class="popup_bottom">
			<div class="popup_box_bottom_center">
			</div>
			<div class="popup_box_bottom_left">
			</div>
			<div class="popup_box_bottom_right">
			</div>
		</div>
	</div>
