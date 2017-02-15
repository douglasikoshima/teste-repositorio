#ifndef _MAILINGSERVICOH_
#define _MAILINGSERVICOH_

/************************************************************************/
/* Comprovante de servico                                               */
/************************************************************************/

#include "../../commons/Log/include/Log.h"
#include "../../commons/Propriedade/include/MFile.h"
#include <list>
using namespace std;

#define CONST_TPEINVAL		"[TPEINVAL] Invalid arguments were given"
#define CONST_TPENOENT		"[TPENOENT] Cannot send to svc because it does not exist"
#define CONST_TPEITYPE		"[TPEITYPE] The type and sub-type of idata is not one of the allowed types and sub-types that svc accepts"
#define CONST_TPEOTYPE		"[TPEOTYPE] Either the type and sub-type of the reply are not known to the caller"
#define CONST_TPETRAN		"[TPETRAN] svc belongs to a server that does not support transactions and TPNOTRAN was not set"
#define CONST_TPETIME		"[TPETIME] A timeout occurred"
#define CONST_TPESVCFAIL	"[TPESVCFAIL] The service routine sending the caller's reply called tpreturn() with TPFAIL"
#define CONST_TPESVCERR		"[TPESVCERR] Tuxedo error"
#define CONST_TPEBLOCK		"[TPEBLOCK] A blocking condition was found on the send call and TPNOBLOCK was specified"
#define CONST_TPGOTSIG		"[TPGOTSIG] A signal was received and TPSIGRSTRT was not specified"
#define CONST_TPEPROTO		"[TPEPROTO] tpcall() was called improperly"
#define CONST_TPESYSTEM		"[TPESYSTEM] A BEA Tuxedo system error has occurred"
#define CONST_TPEOS			"[TPEOS] An operating system error has occurred"


#define XML_ENVIO_LEN 2000

class MailingServico{
public:
	MailingServico();
	~MailingServico();
	char* getParametro(char*nome,char*param);
	void processaMailing();
	int connect(char *sid);

	void insereLinha(char*ddd,char*linha,char*idMailingBanner);
	void excluirMailing(char*idMailingBanner);

	int executar();
	int atualizaRegistro(char*idcompservicodesativado,int qttentativa,char*cdErro,char*dsErro,char*xmlRetorno);
	int removeRegistro(char*registro);
	void disconnect();
	int tuxedoInit(char*userTux,char*cltTux,char*pwsTux,char*pwsTuxGen);
	int sendXML(char *pXmlSaida, char *pRetMsg, int *pErrorNumber);
	char *getTag(char *pXml, char *pTag); 
	int permiteReprocessar(int data,int segundos);

	void setQttentativas(int qtTentativas);
	void setRegistros(int registros);
	void setTempo(int tempo);
	void setTempoSemFila(int tempoSemFila);
	void setTempoReprocessar(int tempoReprocessar);
	void setSignalProcessa(int *signal);

	int getQttentativas();
	int getRegistros();
	int getTempo();
	int getTempoSemFila();
	int getTempoReprocessar();
	int getSignalProcessa();
	int getTempoSemFilaDB();
	int getTempoDB();

	char*getNomeArquivo();
	void setNomeArquivo(char*nomeArquivo);
	char*getIdMailingBanner();
	void setIdMailingBanner(char*idMailingBanner);
	void setPathArquivo(char*pathArquivo);
	char*getPathArquivo();
	void removeParametro(char*cdParametro);


private: // m�todos
	bool continuaProcessamento();
	int getParametrosBase();
private:	
	char m_pathArquivo[500];
	char m_nomeArquivo[256];
	char m_idMailingBanner[256];
	Log m_log;
	char m_erroOracle[5096 + 1];
	char szAux[6096 + 1];
	int m_qtTentativas;
	int m_registros;
	int m_tempo;
	int m_tempoSemFila;
	int m_tempoReprocessar;
	int m_servicoAtivo;
	int *m_piSignalProcessa;

};


#endif