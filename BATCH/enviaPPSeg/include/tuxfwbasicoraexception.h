/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2014/08/14 15:45:46 $
 * @ID      $Id: tuxfwbasicoraexception.h,v 1.1.6.2 2014/08/14 15:45:46 a5114878 Exp $
 **/

#ifndef TuxFW_TuxBasicOraException
#define TuxFW_TuxBasicOraException

//namespace tuxfw
//{

/*****************************************************************************
	Oracle exception class
*****************************************************************************/
//##ModelId=41079D2A00FA
class TuxBasicOraException
{
public:
	//##ModelId=41079D2A00FB
	TuxBasicOraException(int err);
	//##ModelId=41079D2A00FD
	TuxBasicOraException(int err,char*pmsg,int mil);
	//##ModelId=41079D2A010B
	~TuxBasicOraException();
	//##ModelId=41079D2A010C
	int eCode;
	//##ModelId=41079D2A010D
	char*pMsg;
};

//}
#endif
