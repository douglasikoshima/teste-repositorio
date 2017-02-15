// systemMultipleOnLoads.js


// Body onload utility (supports multiple onload functions)
var gSafeOnload = new Array();


function SafeOnload()
{
alert('SafeOnLoad');
	for (var i=0;i<gSafeOnload.length;i++)
		gSafeOnload[i]();
}


function SafeAddOnload(f)
{
	if (IEmac && IE4)  // IE 4.5 blows out on testing window.onload
	{
		window.onload = SafeOnload;
		gSafeOnload[gSafeOnload.length] = f;
	}
	else if (window.onload)
	{
		if (window.onload != SafeOnload)
		{
			gSafeOnload[0] = window.onload;
			window.onload = SafeOnload;
		}		
		gSafeOnload[gSafeOnload.length] = f;
	}
	else
		window.onload = f;

}

