/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.2.2.1 $
 * @CVS     $Author: a5114242 $ - $Date: 2013/08/06 19:13:55 $
 * @ID      $Id: tuxfworaerrtostring.h,v 1.2.2.1 2013/08/06 19:13:55 a5114242 Exp $
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
