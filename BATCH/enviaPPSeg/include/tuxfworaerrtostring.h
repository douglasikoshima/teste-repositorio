/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2014/08/14 15:45:46 $
 * @ID      $Id: tuxfworaerrtostring.h,v 1.1.6.2 2014/08/14 15:45:46 a5114878 Exp $
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
