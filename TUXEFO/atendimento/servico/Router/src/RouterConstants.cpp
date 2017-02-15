#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterConstants.cpp

Abstract:
	Constants of project

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-06-30 - Ivan Mentone - update build to 5
	2004-07-29 - Ivan Mentone - update build to 6
	2004-08-03 - Ivan Mentone - update build to 7
	2004-08-04 - Ivan Mentone - update minor to 2 and build to 8

--*/

#ifdef WIN32
char cSO[]="WIN";
#else
char cSO[]="UNIX";
#endif
#ifdef RELEASE
char cType[]="RELEASE";
#else
char cType[]="BETA";
#endif

BUILD_SIGNATURE(1,2,8)

char*cbREIType[]=
{
	"Parse",
	"RunTime",
	"Release",
	"Warning",
	"CodeErr"
};

char*cbTOC[]=
{
	"&#160;","&#161;","&#162;","&#163;","&#164;","&#165;","&#166;","&#167;","&#168;",
	"&#169;","&#170;","&#171;","&#172;","&#173;","&#174;","&#175;","&#176;","&#177;",
	"&#178;","&#179;","&#180;","&#181;","&#182;","&#183;","&#184;","&#185;","&#186;",
	"&#187;","&#188;","&#189;","&#190;","&#191;","&#192;","&#193;","&#194;","&#195;",
	"&#196;","&#197;","&#198;","&#199;","&#200;","&#201;","&#202;","&#203;","&#204;",
	"&#205;","&#206;","&#207;","&#208;","&#209;","&#210;","&#211;","&#212;","&#213;",
	"&#214;","&#215;","&#216;","&#217;","&#218;","&#219;","&#220;","&#221;","&#222;",
	"&#223;","&#224;","&#225;","&#226;","&#227;","&#228;","&#229;","&#230;","&#231;",
	"&#232;","&#233;","&#234;","&#235;","&#236;","&#237;","&#238;","&#239;","&#240;",
	"&#241;","&#242;","&#243;","&#244;","&#245;","&#246;","&#247;","&#248;","&#249;",
	"&#250;","&#251;","&#252;","&#253;","&#254;","&#255;"
};

char*cbVT[]=
{
	"NN","XG","LF","UK","CT","DN","ST","IT","CR","RS","RC","RT","SI","SM","XR","RS"
};

char*cbVarType[]=
{
	"NoType","XMLGen","LibFunction","Unknown","CustomType","DOMNode","String","Integer",
	"Cursor","Step","Conditional","Task","Indicator","Stream","XMLGen","Recordset"
};
