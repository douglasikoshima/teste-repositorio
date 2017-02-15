#include <string.h>
#include "TuxCallerDef.h"


CTuxCallerDef::CTuxCallerDef()
{
	iTran = 0;
	
	memset(&cServiceName, 0, sizeof(cServiceName));
	memset(&cFile, 0, sizeof(cFile));
}

CTuxCallerDef::~CTuxCallerDef()
{

}

int CTuxCallerDef::getTran()
{
	return this->iTran;
}

char *CTuxCallerDef::getServiceName()
{
	return this->cServiceName;
}

char *CTuxCallerDef::getFileName()
{
	return this->cFile;
}

void CTuxCallerDef::setTran(int value)
{
	this->iTran = value;
}

void CTuxCallerDef::setServiceName(char *value)
{
	strcpy(this->cServiceName, value);
}

void CTuxCallerDef::setFileName(char *value)
{
	strcpy(this->cFile, value);
}
