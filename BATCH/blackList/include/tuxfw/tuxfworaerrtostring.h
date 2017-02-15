/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: rrusso $ - $Date: 2008/01/04 14:48:47 $
 * @ID      $Id: tuxfworaerrtostring.h,v 1.1.2.1 2008/01/04 14:48:47 rrusso Exp $
 **/

#ifndef ORAERRTOSTR
#define ORAERRTOSTR

//namespace tuxfw
//{

//##ModelId=41079D29000F
typedef struct _ORAERR*PORAERR;

//##ModelId=41079D290011
extern struct _ORAERR
{
	//##ModelId=41079D29001F
	int oraCd;
	//##ModelId=41079D290020
	char*oraErr;
	//##ModelId=41079D290021
	char*oraMsg;
}


ORAERR[];

#define ORAERRLIMIT_LOWER		0
#define ORAERRLIMIT_UPPER		3565

//}
#endif
