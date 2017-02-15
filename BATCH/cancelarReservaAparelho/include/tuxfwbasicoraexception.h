#ifndef TuxFW_TuxBasicOraException
#define TuxFW_TuxBasicOraException
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

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
	char* derivStr(char*_x);
	int eCode;
	//##ModelId=41079D2A010D
	char*pMsg;
};

//}
#endif
