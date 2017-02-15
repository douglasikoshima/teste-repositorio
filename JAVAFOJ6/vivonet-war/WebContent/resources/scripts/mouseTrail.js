/*
Simple Image Trail script- By JavaScriptKit.com
Visit http://www.javascriptkit.com for this script and more
This notice must stay intact
*/

var trailimage      = ["/FrontOfficeWeb/resources/images/loader.gif", 16, 16];
var offsetfrommouse = [10,20];

document.write('<div id="trailimageid" style="visibility:hidden;position:absolute;left:0px;top:0px;width:1px;height:1px"><img src="'+trailimage[0]+'" border="0" width="'+trailimage[1]+'px" height="'+trailimage[2]+'px"></div>');

function truebody() {
    return (!window.opera && document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function showTrail() {
    $('trailimageid').style.visibility = "visible";
    followMouse();
    document.onmousemove = followMouse;
}

function hideTrail() {
    $('trailimageid').hide();
    document.onmousemove = "";
}

function followMouse(e) {
    var xcoord = offsetfrommouse[0];
    var ycoord = offsetfrommouse[1];
    if (typeof e != "undefined") {
        xcoord += e.pageX;
        ycoord += e.pageY;
    } else if (typeof window.event !="undefined") {
        xcoord += truebody().scrollLeft + event.clientX;
        ycoord += truebody().scrollTop + event.clientY;
    }
    var docwidth  = document.all? truebody().scrollLeft + truebody().clientWidth : pageXOffset + window.innerWidth - 15;
    var docheight = document.all? Math.max(truebody().scrollHeight, truebody().clientHeight) : Math.max(document.body.offsetHeight, window.innerHeight);
    if (xcoord + trailimage[1] + 3 > docwidth || ycoord + trailimage[2] > docheight)
        $('trailimageid').style.display = "none";
    else 
        $('trailimageid').style.display = "";
    $('trailimageid').style.left = xcoord + "px";
    $('trailimageid').style.top  = ycoord + "px";
}