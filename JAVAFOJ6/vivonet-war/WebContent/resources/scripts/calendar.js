
var pCal;
var arrayData = new Array("Dom","Seg","Ter","Qua","Qui","Sex","S&aacute;b");
var arrayMes = new Array("Janeiro","Fevereiro","Mar&ccedil;o","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro");
var arraySemana = new Array("domingo","segunda-feira","ter&ccedil;a-feira","quarta-feira","quinta-feira","sexta-feira","s&aacute;bado");
var tbl = null;
var dataAtual = null;
var diaSelected = 0;
var mesSelected = 0;
var anoSelected = 0;
var inputData=null;
var dly = null;
function showCalendar(id,mask){
	dataAtual = null;
	diaSelected = 0;
	mesSelected = 0;
	anoSelected = 0;
	maskara = mask;
	inputData = document.getElementById(id);
	pCal = window.createPopup();
	getCalendar();
	pCal.document.oncontextmenu = contextmenu;
	pCal.document.onclick=click;
	pCal.show(event.x,event.y,210,153,document.body);
}
function click(){
	if(dly!=null)
		dly.style.display = 'none';
}
function contextmenu(){
	if(dly!=null){
		try{pCal.document.body.removeChild(dly);}catch(e){}
	}
	var e = pCal.document.parentWindow.event.srcElement;
	if(e.id == "idv_1"){
		listYear(1);
	}
	else
	if(e.id == "idv_4"){
		listYear(4);
	}
	return false;
}
function createDivListYear(){
	dly = pCal.document.createElement("div");
	dly.id = "dly";
	dly.style.overflow = "auto";
	dly.style.width = "50px";
	dly.style.height = "150px";
	dly.style.backgroundColor = "#d4d0c8";
	dly.style.position="absolute";
	dly.style.display = "none";
	dly.style.borderTop = "1px solid #fff";
	dly.style.borderRight = "1px solid #000";
	dly.style.borderBottom = "1px solid #000";
	dly.style.borderLeft = "1px solid #fff";
	dly.style.fontSize = "9px";
	dly.style.fontFamily = "tahoma";
	pCal.document.body.appendChild(dly);
}
function parseDate(){
	var data = inputData.value.split("/");
	if(data.length > 2){
		if(IsNumber(data[2])==true && IsNumber(data[1])==true && IsNumber(data[0])==true){
			var dt = new Date(data[2],(data[1]-1),data[0]);
			diaSelected = dt.getDate();
			return dt;
		}else{
			var dt = new Date();
			diaSelected = dt.getDate();
			return dt;
		}
	}else{
		var dt = new Date();
		diaSelected = dt.getDate();
		return dt;
	}
}
function IsNumber(n){
	return (isNaN(n))?false:true;
}
function getCalendar(){
	var rowHeaderToolbar;
	var rowHeaderSemana;
	var data = parseDate();
	anoSelected = data.getFullYear();
	mesSelected = data.getMonth();
	diaSelected = data.getDate();
	dataAtual = data;
	tbl = pCal.document.createElement("TABLE");
	setStyleTable(tbl);
	toolbarInit(tbl.insertRow(),arrayMes[data.getMonth()]+" "+data.getFullYear());
	headerWeek(tbl.insertRow());
	generateCalendar(data);
	pCal.document.body.appendChild(tbl);
}
function generateCalendar(data){
	var year = data.getFullYear();
	var month = data.getMonth();
	var daySem = data.getDay();
	var date = data.getDate();
	var dtBuffer = new Date(year, month, 1);
	var k = 0;
	var start = false;
	var totalDays = getTotalDays(month,year);
	for(var i=0;i<6;i++){
		var row = tbl.insertRow();
		row.align="center";
		row.height=18;
		for(var j=0;j<7;j++){
			var td = row.insertCell();
			if(j == dtBuffer.getDay()){
				start = true;
			}
			if(start == true && k < totalDays){
				var display = arraySemana[j]+", "+(k+1)+" de "+arrayMes[dtBuffer.getMonth()]+" de "+dtBuffer.getFullYear();
				td.id = "td"+i+"."+j;
				td.tp = "dia";
				if(k+1 == diaSelected){
					td.style.borderRigth="1px solid #FFFFFF";
					td.style.borderLeft="1px solid #000000";
					td.style.borderTop="1px solid #000000";
					td.style.borderBottom="1px solid #FFFFFF";
					td.style.backgroundColor = "#e4e0d8";
					td.style.color="blue";
					td.style.fontWeight = "bold";
				}
				td.innerHTML = "<div style=\"width:100%;height:100%;\" ondblclick=\"parent.selectDate('"+td.id+"',"+dtBuffer.getFullYear()+","+dtBuffer.getMonth()+","+(k+1)+",true); parent.pCal.hide();\" onclick=\"parent.selectDate('"+td.id+"',"+dtBuffer.getFullYear()+","+dtBuffer.getMonth()+","+(k+1)+");\" >"+(++k)+"</div>";
			}else{
				td.innerHTML = "&nbsp;";
			}
		}
	}
}
function selectDate(id,ano,mes,dia,selected){
	diaSelected = dia;
	mesSelected = mes;
	anoSelected = ano;
	dataAtual = new Date(anoSelected,mesSelected,diaSelected);
	for(var i=0;i<tbl.rows.length;i++){
		var cell = tbl.rows[i].cells;
		for(var j=0;j<cell.length;j++){
			if(cell[j].tp == "dia"){
				cell[j].style.color="#000000";
				cell[j].style.fontWeight = "normal";
				cell[j].style.border="0px";
				cell[j].style.backgroundColor = "#d4d0c8";
			}
		}
	}
	pCal.document.getElementById(id).style.borderRigth="1px solid #FFFFFF";
	pCal.document.getElementById(id).style.borderLeft="1px solid #000000";
	pCal.document.getElementById(id).style.borderTop="1px solid #000000";
	pCal.document.getElementById(id).style.borderBottom="1px solid #FFFFFF";
	pCal.document.getElementById(id).style.backgroundColor = "#e4e0d8";
	pCal.document.getElementById(id).style.color = "blue";
	pCal.document.getElementById(id).style.fontWeight = "bold";
	if (selected && !inputData.disabled) {
		inputData.value = formatDate(dataAtual.getFullYear(),(dataAtual.getMonth()),dataAtual.getDate());
		if (!inputData.readOnly) {
            inputData.focus();
        }
	}
}
function formatDate(ano,mes,dia){
	var dt = "";
	mes+=1;
	if(dia < 10){
		dia = "0"+dia;
	}
	if(mes < 10){
		mes = "0"+mes;
	}
	return (dia+"/"+mes+"/"+ano);
}

function getTotalDays(month,year){
	var fev = 28;
	if (((year % 4) == 0 && (year % 100) != 0) || (year %400) == 0)
		fev = 29;
	else
		fev = 28;
	switch(month){
		case 0: return 31; // janeiro
		case 1: return fev; // fevereiro
		case 2: return 31; // março
		case 3: return 30; // abril
		case 4: return 31; // maio
		case 5: return 30; // junho
		case 6: return 31; // julho
		case 7: return 31; // agosto
		case 8: return 30; // setembro
		case 9: return 31; // outubro
		case 10: return 30; // novembro
		case 11: return 31; // dezembro
	}
}
function setStyleTable(t){
	t.cellPadding = 0;
	t.cellSpacing = 0;
	t.style.border = "1px solid #000000";
	t.style.fontSize = "11px";
	t.style.color = "#000000";
	t.style.backgroundColor = "#d4d0c8";
	t.style.fontFamily = "tahoma,verdana,sans-serif";
	t.style.cursor = "default";
}
function toolbarInit(row,label){
	var td = row.insertCell();
	var table = pCal.document.createElement("TABLE");
	var row1 = table.insertRow();
	table.cellPadding = 3;
	table.style.border = "0px; solid #000000";
	table.style.backgroundColor = "#e4e0d8";
	table.style.fontSize = "10px";
	table.style.fontWeight = "bold";
	table.width = "100%";
	row1.align = "center";
	td.colSpan = 7;
	toolBarCell(row1,"&lt&lt",15,true,1);
	toolBarCell(row1,"&lt",15,true,2);
	toolBarCell(row1,label,105,false,0);
	toolBarCell(row1,"&gt",15,true,3);
	toolBarCell(row1,"&gt&gt",15,true,4);
	td.appendChild(table);
}
function toolBarCell(row,label,width,button,nav){
	var td = row.insertCell();
	td.style.fontFamily = "tahoma,verdana,sans-serif";
	td.readOnly = true;
	if(button){
		td.style.borderTop = "1px solid #fff";
		td.style.borderRight = "1px solid #000";
		td.style.borderBottom = "1px solid #000";
		td.style.borderLeft = "1px solid #fff";
		td.style.cursor = "hand";
		td.id = "ids_"+nav;
		label = "<div id=\"idv_"+nav+"\" style=\"width:100%;height:100%;\"  onmouseover=\"parent.overnav('ids_"+nav+"')\" onmouseout=\"parent.outnav('ids_"+nav+"')\"  onclick=\"javascript:parent.navigateDate("+nav+")\">" + label;
	}else{
		td.style.fontSize = "11px";
		td.style.padding="1px";
		td.style.border="1px solid #000";
		td.style.backgroundColor = "#848078";
		td.style.color = "#ffffff";
		td.textAlign = "center";
		td.style.cursor = "default";
		label = "<div>" + label;
	}
	td.style.width = width;
	td.innerHTML = label+"</div>";
	return td;
}
function navigateDate(type){
	navigate(type);
	return false;
}
function selectYear(){
    try{
        if(!isNaN(pCal.document.parentWindow.event.srcElement.value))
            navigateYear(pCal.document.parentWindow.event.srcElement.value);
    }catch(e){
    }
}
function listYear(type){
	dly = null;
	createDivListYear();
	dly.style.display = '';
	var range = 2100;
	var steps = 1;
	var anolabel = anoSelected;
    var divAll = "<div onclick=\"parent.selectYear();\" onmouseover=\"parent.overnav2();\" onmouseout=\"parent.outnav2();\">";
	for(var i=1850;i<range;i+=steps){
		var div2 = pCal.document.createElement("div");
		anolabel = i;
		div2.style.fontWeight = "normal";
		div2.style.border = "1px solid #d4d0c8";
		div2.id = "y_"+anolabel;
		div2.style.cursor = "default";
		div2.align="center";
		div2.innerHTML = "<span value=\""+anolabel+"\" style=\"width:100%;height:100%;\">"+anolabel+"</span>";
		divAll += div2.outerHTML;
	}
    divAll += "</div>";
    dly.innerHTML = divAll;
	if(type == 1){
		dly.style.top = 1;
		dly.style.left = 1;
	}else{
		dly.style.top = 1;
		dly.style.left = 160;
	}
}
function overnav2(){
    try{
        if(!isNaN(pCal.document.parentWindow.event.srcElement.value)){
            id = "y_"+pCal.document.parentWindow.event.srcElement.value;
            pCal.document.getElementById(id).style.border = "1px solid #000";
            pCal.document.getElementById(id).style.backgroundColor = "#e4e0d8";
        }
    }catch(e){
    }
}
function outnav2(){
    try{
        if(!isNaN(pCal.document.parentWindow.event.srcElement.value)){
            id = "y_"+pCal.document.parentWindow.event.srcElement.value;
            pCal.document.getElementById(id).style.border = "1px solid #d4d0c8";
            pCal.document.getElementById(id).style.backgroundColor = "#d4d0c8";
        }
    }catch(e){
    }
}
function navigateYear(ano){
	dataAtual = data = new Date(ano,dataAtual.getMonth(),dataAtual.getDate());
	pCal.document.body.innerHTML = "";
	tbl = pCal.document.createElement("TABLE");
	setStyleTable(tbl);
	toolbarInit(tbl.insertRow(),arrayMes[data.getMonth()]+" "+data.getFullYear());
	headerWeek(tbl.insertRow());
	generateCalendar(data);
	pCal.document.body.appendChild(tbl);
}
function overnav(id){
	pCal.document.getElementById(id).style.borderTop = "1px solid #000";
	pCal.document.getElementById(id).style.borderRight = "1px solid #fff";
	pCal.document.getElementById(id).style.borderBottom = "1px solid #fff";
	pCal.document.getElementById(id).style.borderLeft = "1px solid #000";
}
function outnav(id){
	pCal.document.getElementById(id).style.borderTop = "1px solid #fff";
	pCal.document.getElementById(id).style.borderRight = "1px solid #000";
	pCal.document.getElementById(id).style.borderBottom = "1px solid #000";
	pCal.document.getElementById(id).style.borderLeft = "1px solid #fff";
}
function navigate(param){
	var data;
	var dia = dataAtual.getDate();
	var mes = dataAtual.getMonth();
	var ano = dataAtual.getFullYear();
	switch(param){
		case 1: {
			if(ano > 1850)ano-=1;
			dataAtual = data = new Date(ano,mes,dia);
			break;
		}
		case 2: {
			if(mes > 0){
				mes-=1;
			}else{
				mes = 11;
				ano -=1;
			}
			dataAtual = data = new Date(ano,mes,dia);
			break;
		}
		case 3: {
			if(mes < 12){
				mes+=1;
			}else{
				mes=1;
				ano+=1;
			}
			dataAtual = data = new Date(ano,mes,dia);
			break;
		}
		case 4: {
			if(ano < 2100)ano+=1;
			dataAtual = data = new Date(ano,mes,dia);
			break;
		}
		default: {
			dataAtual = data = new Date();
			break;
		}
	}
	//inputData.value = formatDate(dataAtual.getFullYear(),(dataAtual.getMonth()),dataAtual.getDate());
	//inputData.focus();
	pCal.document.body.innerHTML = "";
	tbl = pCal.document.createElement("TABLE");
	setStyleTable(tbl);
	toolbarInit(tbl.insertRow(),arrayMes[data.getMonth()]+" "+data.getFullYear());
	headerWeek(tbl.insertRow());
	generateCalendar(data);
	pCal.document.body.appendChild(tbl);
}
function headerWeek(row){
	row.align="center";
	row.style.backgroundColor = "#e4e0d8";
	row.style.height = 18;
	var td1 = row.insertCell();
		td1.innerHTML = arrayData[0]; setStyleCell(td1,true);
	var td2 = row.insertCell();
		td2.innerHTML = arrayData[1]; setStyleCell(td2,false);
	var td3 = row.insertCell();
		td3.innerHTML = arrayData[2]; setStyleCell(td3,false);
	var td4 = row.insertCell();
		td4.innerHTML = arrayData[3]; setStyleCell(td4,false);
	var td5 = row.insertCell();
		td5.innerHTML = arrayData[4]; setStyleCell(td5,false);
	var td6 = row.insertCell();
		td6.innerHTML = arrayData[5]; setStyleCell(td6,false);
	var td7 = row.insertCell();
		td7.innerHTML = arrayData[6]; setStyleCell(td7,true);
}

function setStyleCell(td,tp){
	if(tp){
		td.style.color="red";
	}
	td.style.border = "2px solid #e4e0d8";
	td.style.width = "30px";
}