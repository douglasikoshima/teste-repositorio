/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.2.2.1 $
 * @CVS     $Author: a5114242 $ - $Date: 2013/08/06 19:14:06 $
 * @ID      $Id: tuxfwbasicsvcexception.h,v 1.2.2.1 2013/08/06 19:14:06 a5114242 Exp $
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
