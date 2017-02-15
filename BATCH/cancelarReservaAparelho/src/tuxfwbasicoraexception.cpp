/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.10.1 $
 * @CVS     $Author: a5110705 $ - $Date: 2012/01/20 20:38:27 $
 * @ID      $Id: tuxfwbasicoraexception.cpp,v 1.1.10.1 2012/01/20 20:38:27 a5110705 Exp $
 **/

#include "tuxfwbasicoraexception.h"

//namespace tuxfw
//{

//##ModelId=41079D2A00FB
TuxBasicOraException::TuxBasicOraException(int err) : pMsg(0L)
{
	eCode=err;
}

//##ModelId=41079D2A00FD
TuxBasicOraException::TuxBasicOraException(int err, char* pmsg, int mil)
{
	eCode=err;
	pmsg[mil]=0;
	// Max: Garante a liberação de memória já alocada
//	if(pMsg) free(pMsg); pMsg = 0L;
	pMsg = derivStr(pmsg);
}

//##ModelId=41079D2A010B
TuxBasicOraException::~TuxBasicOraException()
{
	if(pMsg)
		free(pMsg);
	pMsg=0L;
}

char* TuxBasicOraException::derivStr(char*_x)
{
	char*_X;
	int l;
	if(!_x)return 0L;
	l=strlen(_x);
	_X=(char*)malloc(l+1);
	if(!_X)return 0L;
	memcpy(_X,_x,l);
	_X[l]=0;
	return _X;
}

//}
