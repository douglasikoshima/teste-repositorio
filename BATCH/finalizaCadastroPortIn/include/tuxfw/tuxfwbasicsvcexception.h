/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: cmgarcia $ - $Date: 2008/08/22 20:09:26 $
 * @ID      $Id: tuxfwbasicsvcexception.h,v 1.1.4.1 2008/08/22 20:09:26 cmgarcia Exp $
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
