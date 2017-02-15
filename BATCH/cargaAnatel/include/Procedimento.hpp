#ifndef PROCEDIMENTO_H
#define PROCEDIMENTO_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <iostream.h>
#include <unistd.h>
#include <list>
#include <unistd.h>
#include <ctype.h>
#include <dirent.h>
#include <string>
#include <atmi.h>
#include <userlog.h>

#include "../../commons/Log/include/Log.h"
#include "../include/Historico.hpp"
#include "../include/Arquivo.hpp"
#include "../include/XMLManager.hpp"
#include "../include/XMLReader.hpp"
#include "../../commons/tinyxml/include/tinyxml.h"
#include "../include/AtendimentoAnatel.hpp"
#include "../include/CCttItr.h"
#include "../include/CCtt.h"

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

using namespace std;

class Procedimento {
	private:
		Log log;
		XMLManager managerConfig;
		XMLReader xmlReader;
		AtendimentoAnatel atendimentoAnatel;
		Historico *historico;
		char *userDatabase;
		char *passwordDatabase;
		char *oracleSID;
		char *userTuxedo;
		char *passwordTuxedo;
		char *passwordTuxedoGen;
		char *cltTux;
		char *inputDiretorio;
		char *outputDiretorio;
		char *executeDiretorio;		
	public:
		Procedimento();
		~Procedimento();
		void processar();
		void registrarAtendimento(Arquivo &arquivo);
		int cadastrarContatoFolha(char *nmContato, char *idContato, char *idContatoPai);		
		int incluirContato(char *path, char *nmContato, char *inFolha, char *idTipoArvore, char *idContatoPai, char *idContato);
		void processarArquivo(Arquivo &arquivo);
		void listarArquivos( char *path, list<Arquivo> &arquivos, char *type);		
		void listarAnexos( char *path, list<Arquivo> &arquivos);	
		void findFileType( char *path, string &arquivo, char *type);		
		void salvarNovaSolicitacao(TiXmlNode *node, Arquivo &arquivo, char*);
		int loadConfig();
		int descompactar(Arquivo &arquivo);
		int tuxInit();
		int sendXML(char *pXmlSaida, char *pRetMsg, int *pErrorNumber);
		int abrirProcessoAtendimento(Atendimento &atendimento, char *idContato);
};

#endif