//---------------------------------------------------------------------------
//                         (c) Cons�rcio Indra/PT-SI.
//                            xxxxxxxxxxxxxxxxxxxxxxx
//                                xxxxxxxxxxxxxx
//---------------------------------------------------------------------------
//*  M�dulo                   : TuxReporter
//*  Arquivo                  : TuxCaller
//*  Tipo                     : .h
//*  Autor                    : Michel Mastriani
//*//------------------------------------------------------------------------
//*  Prop�sito:
//*
//*  Implementar os negocios referentes � chamada de servi�o Tuxedo
////-------------------------------------------------------------------------

#ifndef __C_TUX_CALLER__
#define __C_TUX_CALLER__

class CTuxCaller
{

public:
	CTuxCaller();
	~CTuxCaller();

	int  getTran();

	// retorna nova c�pia de pcXmlIn
	// usar delete para desalocar o retorno
	char *getXmlIn();

	// retorna nova c�pia de pcXmlOut
	// usar delete para desalocar o retorno
	char *getXmlOut();

	// retorna c�pia de cError
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