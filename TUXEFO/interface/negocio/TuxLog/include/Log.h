#ifndef __LOG_H__
#define __LOG_H__
#include <tuxfw.h>
#include <time.h>

class CLogBase
{
public:
	CLogBase();
	~CLogBase();
	int log(char*,char*,char*,char*,int);
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

class CLog : public CLogBase
{
public:
	CLog();
	~CLog();
public:
	int getTimeSysdate();
	int getSistemaOrigem(char*sgSistemaOrigem);
	int getCanal(char*nmCanal);
	int getParametroLog();
private:

};

#endif