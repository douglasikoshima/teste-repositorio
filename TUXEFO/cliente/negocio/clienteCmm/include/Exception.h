#ifndef EXCEPTION
#define EXCEPTION

#include <string.h>

class CException
{
public:
	CException(int, char *, int);
	~CException() {};
	char	*getMsg();

private:
	int		iTipo;
	char	sMsg[70];
};


#endif
