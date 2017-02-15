/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: cmgarcia $ - $Date: 2008/08/22 20:09:28 $
 * @ID      $Id: tuxfworaerrtostring.h,v 1.1.4.1 2008/08/22 20:09:28 cmgarcia Exp $
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
