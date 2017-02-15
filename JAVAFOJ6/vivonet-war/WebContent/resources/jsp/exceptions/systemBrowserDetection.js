//
// systemBrowserDetection.js
//


// Browser Detection
isMac = (navigator.appVersion.indexOf("Mac")!=-1) ? true : false;
NS4 = (document.layers) ? true : false;
IEmac = ((document.all)&&(isMac)) ? true : false;
IE4plus = (document.all) ? true : false;
IE4 = ((document.all)&&(navigator.appVersion.indexOf("MSIE 4.")!=-1)) ? true : false;
IE5 = ((document.all)&&(navigator.appVersion.indexOf("MSIE 5.")!=-1)) ? true : false;

IE6 = ((document.all)&&(navigator.appVersion.indexOf("MSIE 6.")!=-1)) ? true : false;
ver4 = (NS4 || IE4plus) ? true : false;
NS6 = (!document.layers) && (navigator.userAgent.indexOf('Netscape')!=-1)?true:false;

IE5plus = IE5 || IE6;
IEMajor = 0;

if (IE4plus)
{
	var start = navigator.appVersion.indexOf("MSIE");
	var end = navigator.appVersion.indexOf(".",start);
	IEMajor = parseInt(navigator.appVersion.substring(start+5,end));
	IE5plus = (IEMajor>=5) ? true : false;
}
