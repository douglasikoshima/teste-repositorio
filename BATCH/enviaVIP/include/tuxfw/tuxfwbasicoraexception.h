/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: esiqueir $ - $Date: 2008/03/27 22:25:13 $
 * @ID      $Id: tuxfwbasicoraexception.h,v 1.1.4.1 2008/03/27 22:25:13 esiqueir Exp $
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
