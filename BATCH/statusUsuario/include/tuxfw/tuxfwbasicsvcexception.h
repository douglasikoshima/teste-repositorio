/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1 $
 * @CVS     $Author: rrusso $ - $Date: 2007/01/08 18:47:55 $
 * @ID      $Id: tuxfwbasicsvcexception.h,v 1.1 2007/01/08 18:47:55 rrusso Exp $
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
