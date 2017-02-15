#ifndef __LOG_H__
#define __LOG_H__
#include <tuxfw.h>
#include <time.h>

class CRemoteLogBase
{
public:
	CRemoteLogBase();
	~CRemoteLogBase();
	int log(XMLGen*,char*,char*,char*,int);
	char* remoteCall(char* nomeServico, char* sMsgIn, int lFlags);
public:
	void setIdSistemaOrigem(char*);
	void setDtInicio(char*);
	void setDtFim(char*);
	void setNrTerminal(char*);
	void setIdLinhaSistemaOrigem(char*);
	void setIdContaSistemaOrigem(char*);
	void setNmAPI(char*);
	void setDsProcesso(char*);
	void setIdCanal(char*);
	void setExecute(bool);

	char*getIdSistemaOrigem();
	char*getDtInicio();
	char*getDtFim();
	char*getNrTerminal();
	char*getIdLinhaSistemaOrigem();
	char*getIdContaSistemaOrigem();
	char*getNmAPI();
	char*getDsProcesso();
	char*getIdCanal();
	bool getExecute();

	int getTimeSysdateFim();

private:
	char m_idSistemaOrigem[21];
	char m_dtInicio[20]; // dd/mm/yyyy hh:ii:ss
	char m_dtFim[20]; // dd/mm/yyyy hh:ii:ss
	char m_nrTerminal[11];
	char m_idLinhaSistemaOrigem[256];
	char m_idContaSistemaOrigem[256];
	char m_nmAPI[256];
	char m_dsProcesso[256];
	char m_idCanal[21];
	bool m_execute;

};

class CRemoteLog : public CRemoteLogBase
{
public:
	CRemoteLog();
	~CRemoteLog();
public:
	int getTimeSysdate();	
	int getSistemaOrigem(char*sgSistemaOrigem);
	int getCanal(char*nmCanal);
	int getParametroLog();
private:

};

#endif