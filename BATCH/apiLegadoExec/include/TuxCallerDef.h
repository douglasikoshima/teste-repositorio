//---------------------------------------------------------------------------
//                         (c) Consórcio Indra/PT-SI.
//                            xxxxxxxxxxxxxxxxxxxxxxx
//                                xxxxxxxxxxxxxx
//---------------------------------------------------------------------------
//*  Módulo                   : TuxReporter
//*  Arquivo                  : TuxCallerDef
//*  Tipo                     : .h
//*  Autor                    : Michel Mastriani
//*//------------------------------------------------------------------------
//*  Propósito:
//*
//*  Implementar os negocios referentes à chamada de serviço Tuxedo
////-------------------------------------------------------------------------

#ifndef __C_TUX_CALLER_DEF__
#define __C_TUX_CALLER_DEF__

class CTuxCallerDef
{

public:
	CTuxCallerDef();
	~CTuxCallerDef();

	int   getTran();
	char *getServiceName();
	char *getFileName();

	void  setTran(int value);
	void  setServiceName(char *value);
	void  setFileName(char *value);

private:
	char cServiceName[256];
	char cFile[1024];
	int  iTran;
};

#endif