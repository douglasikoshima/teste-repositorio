Application = {
	dataTables: []
}
Constantes = {
	WEB_ROOT : '/catalogo',
	MANUTENCAO_ITENSPERPAGE : 500
};
jQuery.fn.dataTablesCaller = function(options) {
	var dataTableOptions = {};
	var settings = {
		horizontalScroll: false,			// Booleano para paginacao horizontal
		formID: null,									// ID do formulario para pesquisas com filtros
		paginate: true,								// Booleano para paginação
		action: null,									// Para paginação via aplicacao, caminho para action deve ser informado
		scrollHeight: null, 					// Altura do tbody
		itensPerPage: 10,							// Quantidade de itens por pagina
		language: 'ptbr', 						// Idioma
		initialSort: true,						// Determina se Datatables vai ordenar tabela inicialmente
		initialColumnSort: null,
		initialColumnSortDirection: 'asc',
		showInfo: true, 							// Rodape: Mostrando de...
		paginationType: 'full_numbers',	// 'two_button' ou 'full_numbers'
		unableSort: [],
		fnDrawCallback: null,
		callback: null
	};
	var methods = {
		buildOptions : function(tableObj) {
			if (options) {
				jQuery.extend(settings, options);
			}
			dataTableOptions.bJQueryUI = true;
			dataTableOptions.bPaginate = settings.paginate;
			dataTableOptions.fnDrawCallback = settings.fnDrawCallback;
			var tableIDRef = jQuery(tableObj).attr('id');
			dataTableOptions.fnRowCallback = function(nRow) {
				jQuery(nRow.cells).addClass('tableCellFix');
				if (settings.horizontalScroll) {
					jQuery(nRow.cells).addClass('tableCellFixHorizontalScroll');
				}
				return nRow;
			}
			if (!settings.fnDrawCallback) {
				dataTableOptions.fnDrawCallback = function(obj) {
					// Não foram encontrados registros
					if (jQuery('tbody tr', jQuery(tableObj)).length == 1) {
						if (jQuery('tbody tr td', jQuery(tableObj)).html().toLowerCase().indexOf('não foram') >= 0 && settings.horizontalScroll) {
							jQuery('tbody tr td', jQuery(tableObj)).attr('style', 'text-align:left;')
						}
					}

					if (settings.unableSort.length) {
						dataTableOptions.aoColumns = [];
						var uS = settings.unableSort;
						var columns = jQuery('thead tr th', jQuery(tableObj));
						for (var i = 0; i < columns.length; i++) {
							if (jQuery.inArray(i+1, uS) >= 0) {
								dataTableOptions.aoColumns[i] = {bSortable: false};
							} else {
								dataTableOptions.aoColumns[i] = {};
							}
						}
					}

					var rowCount = 0;
					var nowrap_columns = [];
					var centered_columns = [];
					var left_columns = [];
					jQuery('thead th', jQuery(tableObj)).each(function() {
						var selectedIndex = jQuery(jQuery('thead th', jQuery(tableObj)), jQuery(this)).index(jQuery(this));
						var thObj = jQuery(this).closest('table').parent().prev().find('th').get(selectedIndex);
						var uS = settings.unableSort;

						if (jQuery(this).hasClass('tac')) {
							centered_columns[centered_columns.length] = rowCount;
						} else if (jQuery(this).hasClass('tal')) {
							left_columns[left_columns.length] = rowCount;
						} else if (jQuery(this).hasClass('nowrap')) {
							nowrap_columns[nowrap_columns.length] = rowCount;
						}

						if (jQuery('.DataTables_sort_wrapper', jQuery(thObj)).length) {
							if (jQuery.inArray(rowCount + 1, uS) >= 0) {
								var thHtml = jQuery('.DataTables_sort_wrapper', jQuery(thObj)).html();
								jQuery('.DataTables_sort_wrapper', jQuery(thObj)).remove();
								jQuery(thObj).html(thHtml);
								jQuery('.DataTables_sort_icon', jQuery(thObj)).remove();
							}
						}

						jQuery('span', jQuery(this)).css('float', 'right');
						if (jQuery(this).hasClass('checkbox')) {

							// Remove padding do container de checkbox
							jQuery('div.DataTables_sort_wrapper', jQuery(thObj)).remove();
							if (jQuery('input', jQuery(thObj)).length == 0) {
								jQuery(thObj).append('<input type="checkbox" style="margin:0;padding:0" />');
							}
							jQuery(':checkbox', jQuery(thObj)).click(function() {
								var checked = jQuery(this).is(':checked');
								var jsF = jQuery(this).parent().attr('callback');
								jQuery('#'+tableIDRef).find('tbody tr').each(function() {
									var cbInput = jQuery(jQuery(this).find('td').get(selectedIndex)).find('input');
									if (!jQuery(cbInput).attr('readonly')) {
										if (checked) {
											cbInput[0].checked = true;
										} else {
											cbInput[0].checked = false;
										}
									}
								});
								if (jsF) {
									eval(jsF);
								}
 							});
						}
						rowCount++;
					});

					var table_lines = jQuery('tbody tr', jQuery(tableObj));
					table_lines.each(function(index_tr){
						jQuery.each(centered_columns, function(index, value) {
							jQuery(jQuery('td', jQuery(table_lines[index_tr])).get(value)).css('text-align', 'center');
						});
						jQuery.each(left_columns, function(index, value) {
							jQuery(jQuery('td', jQuery(table_lines[index_tr])).get(value)).css('text-align', 'left');
						});
						// jQuery.each(nowrap_columns, function(index, value) {
						// 	jQuery(jQuery('td', jQuery(table_lines[index_tr])).get(value)).css('white-space', 'nowrap');
						// });
					});

					jQuery('tbody tr', jQuery(tableObj)).each(function(){
						jQuery('td', jQuery(this)).css('padding', '5px 0');
						jQuery('td', jQuery(this)).css('vertical-align', 'middle');
					});

					if (jQuery('.export').length > 0) {
						jQuery('.export').show();
					}

				};
			}
			dataTableOptions.bAutoWidth = false;
			dataTableOptions.bDestroy = true;
	    	dataTableOptions.bScrollCollapse = false;
			dataTableOptions.bFilter = false;
			dataTableOptions.bLengthChange = false;
			dataTableOptions.bInfo = settings.showInfo;
			dataTableOptions.iDisplayLength = settings.itensPerPage;
      		dataTableOptions.sPaginationType =  settings.paginationType;
			dataTableOptions.oLanguage = {};
			dataTableOptions.oLanguage.sUrl = Constantes.WEB_ROOT + '/static_server/jquery/js/datatables_' + settings.language + '.txt';
			if (!settings.initialSort) {
				dataTableOptions.aaSorting = [];
			}
			if (settings.initialColumnSort) {
				dataTableOptions.aaSorting = [[settings.initialColumnSort-1, settings.initialColumnSortDirection]];
			}
			if (settings.horizontalScroll) {
				dataTableOptions.sScrollX = '100%';
			}
			dataTableOptions.fnInitComplete = function(obj) {
				jQuery('thead tr', jQuery(tableObj)).each(function(){
					// jQuery('th', this).attr('style', 'white-space:nowrap;padding:0;border:none');
				});
				if (settings.scrollHeight) {
					jQuery(tableObj).css('width','100%');
				}
				if (settings.horizontalScroll) {
					if (settings.scrollHeight) {
						jQuery(tableObj).css('width','100%').dataTable().fnAdjustColumnSizing();
					} else {
						jQuery(tableObj).dataTable().fnAdjustColumnSizing();
					}
				}
			};
			if (settings.scrollHeight) {
				dataTableOptions.sScrollY = settings.scrollHeight + 'px';
			}
			if (settings.unableSort.length) {
				dataTableOptions.aoColumns = [];
				var uS = settings.unableSort;
				var columns = jQuery('thead tr th', jQuery(tableObj));
				for (var i = 0; i < columns.length; i++) {
					if (jQuery.inArray(i+1, uS) >= 0) {
						dataTableOptions.aoColumns[i] = {bSortable: false};
					} else {
						dataTableOptions.aoColumns[i] = {};
					}
				}
			}
			if (settings.action) {
				dataTableOptions.bProcessing = true;
        dataTableOptions.sPaginationType = settings.paginationType;
        dataTableOptions.sAjaxSource = settings.action;
        dataTableOptions.bServerSide = true;
				dataTableOptions.fnServerData = function(sSource, aoData, fnCallback) {
					if (settings.action) {
						var conector = sSource.indexOf('?') >= 0 ? '&' : '?';
						var queryString = conector;
						jQuery.each(aoData, function(index, obj) {
							if (index > 0) {
								queryString += '&';
							}
							queryString += obj.name + '=' + obj.value;
						});
						if (jQuery('#'+settings.formID)) {
							// console.log(jQuery('#'+settings.formID).serializeArray());
							jQuery.each(jQuery('#'+settings.formID).serializeArray(), function(index, obj) {
								aoData.push({'name': obj.name, 'value': obj.value});
							});
						}
						jQuery.each(aoData, function(index, obj) {
							if (obj.name == "iSortCol_0") {
								aoData.push({'name': 'nomeCampoOrdenacao', 'value': jQuery("th:eq(" + obj.value + ")", jQuery(tableObj.parentNode.parentNode)).attr('column') });
							} else if (obj.name == "sSortDir_0") {
								aoData.push({'name': 'direcaoOrdenacao', 'value': obj.value });
							}
            });
						jQuery.ajax({
							url: sSource + queryString,
							data: aoData,
							type: 'POST',
							cache: false,
							dataType: "text",
							success: function(data) {
								var errorsObject = null;
								if (errorsObject) {
									// TODO: Inserir tratamento de erros
								} else {
									var jSONObj = jQuery.parseJSON(data);
									fnCallback(jSONObj);
								}
							},
							error: function(errorData) {
								if (errorData.responseText.indexOf('login-redirect') >= 0) {
									window.location.href = Constantes.WEB_ROOT;
								}
							}
						});
					}
        };
			}

		}
	};
	this.each(function() {
		var tag = this.tagName.toLowerCase();
		if (tag == 'table') {
			methods.buildOptions(this);
			Application.dataTables[Application.dataTables.length] = jQuery(this).dataTable(dataTableOptions);
		}
	});
	if (settings.callback && typeof(settings.callback) == 'function') {
		settings.callback();
	}
};