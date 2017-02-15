/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.6.1 $
 * @CVS     $Author: jones $ - $Date: 2013/07/10 18:04:55 $
 * @ID      $Id: tuxfwbasicoraexception.h,v 1.1.6.1 2013/07/10 18:04:55 jones Exp $
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
