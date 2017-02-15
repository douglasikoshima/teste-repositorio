//##############################################################################################
struct retfid{
	char szContrato[50];
	char szVMulta[20];
	char szDTFimCtr[20];
};



class CCallTuxSrv{
private:
	
    //const char *pMemBufId;
    TuxRemoteService *remoteService;
	TuxMessage *inputMessage;


public:
	CCallTuxSrv();
	~CCallTuxSrv();
	
	int SetUser(char* idUser);
	int SetBody(XMLGen *XmlCorpo);
	int SetService(char*);
    int SetServiceName(char*);
	int SetInputMessage();
	int RemoteCall();
	int IsValidMessage();
	int GetMessgeStr(char *pRet);
	int GetErrMsg();


};


CCallTuxSrv::CCallTuxSrv()
{
	remoteService = new TuxRemoteService();
	inputMessage = new TuxMessage();
}


CCallTuxSrv::~CCallTuxSrv()
{
    delete remoteService;
	delete inputMessage;
}

int CCallTuxSrv::SetUser(char *idUser)
{
	inputMessage->setUser(idUser);
	return 1;	
}

int CCallTuxSrv::SetBody(XMLGen *XmlCorpo)
{
	inputMessage->setMessageBody(XmlCorpo);
	return 1;
}

int CCallTuxSrv::SetService(char* pName)
{
	inputMessage->setService(pName);
	return 1;

}

int CCallTuxSrv::SetServiceName(char* pName)
{
	remoteService->setServiceName(pName);
	
	return 1;
}

int CCallTuxSrv::SetInputMessage()
{
	remoteService->setInputMessage(inputMessage);
	return 1;
}


int CCallTuxSrv::RemoteCall()
{
	return (remoteService->remoteCall() != TUXFWRET_OK)?0:1;

}

int CCallTuxSrv::IsValidMessage()
{
	return(remoteService->getOutputMessage()->getStatusCode() != NULL && strlen(remoteService->getOutputMessage()->getStatusCode()) >= 3 && remoteService->getOutputMessage()->getStatusCode()[2] !='I')?0:1;

}

int CCallTuxSrv::GetMessgeStr(char *pRet)
{
	
	strcpy(pRet,remoteService->getOutputMessage()->getMessageBody());
	return 1;
}

int CCallTuxSrv::GetErrMsg()
{

	ULOG(inputMessage->getStatusCode());
	return 1;
}
