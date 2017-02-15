/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2014/08/14 15:47:12 $
 * @ID      $Id: tuxfwbasicsvcexception.h,v 1.1.6.2 2014/08/14 15:47:12 a5114878 Exp $
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
