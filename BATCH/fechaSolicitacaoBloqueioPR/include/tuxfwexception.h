/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2011/04/06 23:54:50 $
 * @ID      $Id: tuxfwexception.h,v 1.1.4.1 2011/04/06 23:54:50 a5114878 Exp $
 **/

#ifndef TuxFW_TuxException
#define TuxFW_TuxException

//namespace tuxfw
//{

/*****************************************************************************
	Tuxedo exception class
*****************************************************************************/
//##ModelId=41079D2902B1
class TuxException
{
public:
	//##ModelId=41079D2902BF
	TuxException(char*pCd,char *pszFormat, ...);
//	TuxException(char*statusCode,char*statusText);
	//##ModelId=41079D2902C3
	TuxException(char*pCd);
	//##ModelId=41079D2902C5
	TuxException();
	//##ModelId=41079D2902C6
	~TuxException();
	//##ModelId=41079D2902CE
	void setCode(char* pErrorCode);
	//##ModelId=41079D2902D0
	void setMessage(char* pErrorMessage);
	//##ModelId=41079D2902D2
	char* getCode(void);
	//##ModelId=41079D2902D4
	char* getMessage(void);
// should not remain proteced
protected:
	//##ModelId=41079D2902DE
	char* pCode;
	//##ModelId=41079D2902DF
	char* pMsg;
};

//}
#endif