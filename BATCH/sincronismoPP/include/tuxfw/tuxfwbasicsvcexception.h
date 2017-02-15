/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.8.1 $
 * @CVS     $Author: a5110705 $ - $Date: 2015/09/21 20:38:55 $
 * @ID      $Id: tuxfwbasicsvcexception.h,v 1.1.8.1 2015/09/21 20:38:55 a5110705 Exp $
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
