#ifndef RESERVAAPARELHO_H
#define RESERVAAPARELHO_H
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <iostream.h>
#include <unistd.h>
#include <list>
#include <unistd.h>
#include <ctype.h>
#include <dirent.h>
#include <time.h>
#include <atmi.h>
#include <userlog.h>
#include "../../commons/Log/include/Log.h"
#include "../../commons/CFile/include/CFile.h"
#include "../include/XMLManager.hpp"
#include "../include/XMLReader.hpp"

#ifndef _MAX_PATH
#define _MAX_PATH 1024
#endif

#define TAM_BD_PASSWD 32
#define TAM_BD_USRNAME 32
#define TAM_BD_INST 32
#define TAM_TX_PASSWD TAM_BD_PASSWD
#define TAM_TX_USRNAME TAM_BD_USRNAME
#define TAM_TX_CLT 32
#define TAM_TX_GEN 32

#define STATUS_ERRO     0
#define STATUS_SUCESSO  1
#define STATUS_VAZIO    2

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


/*******************************************************************************
 * Macros para manipulacao de dados ProC/C++
 *******************************************************************************/
#define endOraStr(varstr)      varstr.arr[varstr.len]= '\0'
#define oraToStr(bstr,vchar)   if(!bstr) strncpy(bstr,vchar.arr,vchar.len)
#define strToOra(vchar,bstr)   vchar.len = strlen(bstr);strncpy((char *)vchar.arr,bstr,vchar.len);vchar.arr[vchar.len] = 0
#define strconv(buffer,fonte)  sprintf(buffer,"%d",fonte)

class ReservaAparelho {
	private:	    
		Log log;
		char szTempTrace[2048];
		char dsValorParametro[256];
		XMLManager managerConfig;
		XMLReader xmlReader;
		char *userDatabase;
		char *passwordDatabase;
		char *oracleSID;
		char *userTuxedo;
		char *passwordTuxedo;
		char *passwordTuxedoGen;
		char *cltTux;

	public:
		ReservaAparelho();
		~ReservaAparelho();
		
		// métodos para recuperar informações
		char* getParametro(char *parametro);
		int buscarReservaAparelho();
		int sendXML(char *pXmlSaida, char *pRetMsg, int *pErrorNumber);
		int processar();
		int loadConfig();
		int tuxInit();	

		// conexão
		int connect(char *user, char *password, char *sid);
		void disconnect();
};


#endif
