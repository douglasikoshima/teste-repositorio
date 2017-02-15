//---------------------------------------------------------------------------
//                         (c) Consórcio Indra/PT-SI.
//                            xxxxxxxxxxxxxxxxxxxxxxx
//                                xxxxxxxxxxxxxx
//---------------------------------------------------------------------------
//*  Módulo                   : TuxReporter
//*  Arquivo                  : TuxCaller
//*  Tipo                     : .h
//*  Autor                    : Michel Mastriani
//*//------------------------------------------------------------------------
//*  Propósito:
//*
//*  Implementar os negocios referentes à chamada de serviço Tuxedo
////-------------------------------------------------------------------------

#ifndef __C_TUX_CALLER__
#define __C_TUX_CALLER__

class CTuxCaller
{

public:
	CTuxCaller();
	~CTuxCaller();

	int  getTran();

	// retorna nova cópia de pcXmlIn
	// usar delete para desalocar o retorno
	char *getXmlIn();

	// retorna nova cópia de pcXmlOut
	// usar delete para desalocar o retorno
	char *getXmlOut();

	// retorna cópia de cError
	// usar delete para desalocar o retorno
	char *getError();

	void setTimeout(int value);
	void setServiceName(char *value);
	void setTran(int value);
	void setXmlIn(char *value);

	int  callService();

protected:
	void setError(char *value);

private:
	int  iTran;
	int  iTimeout;
	
	char cServiceName[256];
	char cError[256];

	char *pcXmlIn;
	char *pcXmlOut;
};

#endif