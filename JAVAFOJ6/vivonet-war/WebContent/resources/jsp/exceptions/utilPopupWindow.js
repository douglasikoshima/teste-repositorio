//
// utilPopupWindow.js
//


function PUW_Show()
{
	var settings = "width="+this.width+",height="+this.height+",top="+this.top+",left="+this.left+",";
	settings += "scrollbars=" + ((this.scrollbars) ? "yes," : "no,");
	settings += "toolbar=" + ((this.toolbar) ? "yes," : "no,");
	settings += "location=" + ((this.locationbar) ? "yes," : "no,");
	settings += "menubar=" + ((this.menubar) ? "yes," : "no,");
	settings += "status=" + ((this.statusbar) ? "yes," : "no,");
	settings += "resizable=" + ((this.resizable) ? "yes," : "no,");
	settings += "modal=" + ((this.modal) ? "yes," : "no,");
	settings += "alwaysRaised=" + ((this.modal) ? "yes" : "no");
	
	var newWin = window.open(this.url, this.name, settings);

	//if (! this.ontop)
		window.focus();
}


function PopupWindow(url, width, height)
{
	this.width = width;
	this.height = height;
	this.top = screen.availHeight/2 - height/2; // center
	this.left = screen.availWidth/2 - width/2; // center
	this.name = "mypopupwin";
	this.url = url;
	this.showDelay = 2000;
	this.frequency = 1; // how many times show per renewal time period
	this.renew = 1; // renew showing every x hours
	this.scrollbars= true;
	this.toolbar= false;
	this.statusbar= false;
	this.resizable = false;
	this.locationbar = false;
	this.menubar = false;
	this.ontop = true;
	this.modal = false;
	
	this.Show = PUW_Show;
}


function PopupWarningWindow(context, code, msg, stack) {
	var url = context+'/warning.html';
    
	var width  = 500;
	var height = 300;

	this.width = width;
	this.height = height;
	this.top = screen.availHeight/2 - height/2; // center
	this.left = screen.availWidth/2 - width/2; // center
	this.name = "mypopupwin";
	this.url = url;
	this.showDelay = 0;
	this.frequency = 1; // how many times show per renewal time period
	this.renew = 1; // renew showing every x hours
	this.scrollbars= true;
	this.toolbar= false;
	this.statusbar= false;
	this.resizable = false;
	this.locationbar = false;
	this.menubar = false;
	this.ontop = true;
	this.modal = true;

	this.exceptionCode = code;
	this.exceptionMessage = msg;
	this.exceptionStack = stack;
	
    //alert('this.exceptionCode='+this.exceptionCode);
    //alert('this.exceptionMessage='+this.exceptionMessage);
    //alert('this.exceptionStack='+this.exceptionStack);
    
	this.Show = PUW_Show;
}
