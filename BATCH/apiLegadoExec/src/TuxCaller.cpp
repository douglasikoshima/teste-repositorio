#include <string.h>
#include "atmi.h"
#include "TuxCaller.h"

CTuxCaller::CTuxCaller()
{	
	iTran    = 0;
	iTimeout = 30;

	///
	memset(cServiceName, 0, sizeof(cServiceName));

	///
	pcXmlIn  = NULL;
	pcXmlOut = NULL;
}

CTuxCaller::~CTuxCaller()
{
	if(pcXmlIn)
		delete pcXmlIn;

	if(pcXmlOut)
		delete pcXmlOut;
}

int CTuxCaller::getTran()
{
	return iTran;
}

char *CTuxCaller::getXmlIn()
{
	char *pcXmlInRet;

	if(pcXmlIn == NULL)
		return NULL;

	pcXmlInRet = new char[strlen(pcXmlIn) + 1];

	strcpy(pcXmlInRet, pcXmlIn);

	return pcXmlInRet;
}

char *CTuxCaller::getXmlOut()
{
	char *pcXmlOutRet;

	if(pcXmlOut == NULL)
		return NULL;

	pcXmlOutRet = new char[strlen(pcXmlOut) + 1];

	strcpy(pcXmlOutRet, pcXmlOut);

	return pcXmlOutRet;
}

char *CTuxCaller::getError()
{
	char *pcError;

	pcError = new char[strlen(cError) + 1];

	strcpy(pcError, cError);

	return pcError;
}

void CTuxCaller::setTimeout(int value)
{
	//vamos limitar a três minutos
	if(value > 0 && value < 180)
		iTimeout = value;
}

void CTuxCaller::setServiceName(char *value)
{
	if(strlen(value) < sizeof(cServiceName))
		strcpy(cServiceName, value);
}

void CTuxCaller::setTran(int value)
{
	iTran = value;
}

void CTuxCaller::setXmlIn(char *value)
{
	if(pcXmlIn)
		delete pcXmlIn;

	pcXmlIn = new char[strlen(value) + 1];

	strcpy(pcXmlIn, value);

}

void CTuxCaller::setError(char *value)
{
	if(strlen(value) < sizeof(this->cError))
		strcpy(this->cError, value);
}

int CTuxCaller::callService()
{
	char error[512];
	char *sendbuf, *rcvbuf = NULL;
	long sendlen, rcvlen;
	int ret;

	if(!pcXmlIn)
	{
		this->setError("unable to read input file");
		return 1;
	};

	sendlen = strlen(pcXmlIn);

	// Allocate STRING buffers for the request and the reply 

	if((sendbuf = (char *) tpalloc("STRING", NULL, sendlen + 1)) == NULL) 
	{
		this->setError("Error allocating send buffer");

		tpterm();
		
		return 2;
	}

	if((rcvbuf = (char *) tpalloc("STRING", NULL, sendlen + 1)) == NULL) 
	{
		this->setError("Error allocating receive buffer");
		tpfree(sendbuf);
		tpterm();
		return 3;
	}

	strcpy(sendbuf, pcXmlIn);

	if(this->getTran()) 
	{
		ret = tpbegin(this->iTimeout, 0);

		if (ret < 0) 
		{
			sprintf(error, "error in tpbegin %i,%s", tperrno, tpstrerror(tperrno));
			this->setError(error);
			tpterm();
			return 4;
		};
	};

	strcpy(rcvbuf,"");

	//printf("\nxmlIn\n%s\n", sendbuf);

	// Request the service INSEAI2, waiting for a reply 
	ret = tpcall(this->cServiceName, (char *)sendbuf, 0, (char **)&rcvbuf, &rcvlen, (long)0);

	if(ret == -1) 
	{
		sprintf(error, "Can't send request to service %s\n", this->cServiceName);
		this->setError(error);

		if(rcvbuf)
		{
			pcXmlOut = new char[strlen(rcvbuf) + 1];
			strcpy(pcXmlOut, rcvbuf);
		}

		tpfree(sendbuf);
		tpfree(rcvbuf);

		if(this->getTran())
			tpabort(0);

		tpterm();

		return 5;
	}

	if(this->getTran())
		tpcommit(0);

	pcXmlOut = new char[strlen(rcvbuf) + 1];

	strcpy(pcXmlOut, rcvbuf);

	// Free Buffers & Detach from System/T 
	tpfree(sendbuf);
	tpfree(rcvbuf);

	return 0;
}