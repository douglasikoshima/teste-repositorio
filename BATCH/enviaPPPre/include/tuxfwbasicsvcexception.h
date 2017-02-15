/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.6.1 $
 * @CVS     $Author: a5110706 $ - $Date: 2012/08/14 22:25:00 $
 * @ID      $Id: tuxfwbasicsvcexception.h,v 1.1.6.1 2012/08/14 22:25:00 a5110706 Exp $
 **/

#ifndef TuxFW_TuxBasicSvcException
#define TuxFW_TuxBasicSvcException

//namespace tuxfw
//{

//##ModelId=41079D29031E
class TuxBasicSvcException: public TuxException
{
public:
	/*****************************************************************************
		Constructor and Destructor
	*****************************************************************************/
	//##ModelId=41079D29032D
	TuxBasicSvcException(char*pCd,char*pMs);
	//##ModelId=41079D290330
	TuxBasicSvcException(char*pCd);
	//##ModelId=41079D290332
	~TuxBasicSvcException();
	//char* pCode;
	//char* pMsg;
};

//}
#endif
