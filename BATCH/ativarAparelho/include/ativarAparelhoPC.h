
#include "../../commons/Propriedade/include/Propriedade.h"
#include "../../commons/queryMacro.h"
#include "../../commons/Log/include/Log.h"
#include "../../commons/tinyxml/include/tinyxml.h"
#include "ativarAparelhoException.h"
#include "ativarAparelhoSt.h"


#ifndef ATIVARAPARELHOPC
#define ATIVARAPARELHOPC

#define endOraStr(varstr)      varstr.arr[varstr.len]= '\0'
#define oraToStr(bstr,vchar)   if(!bstr) strncpy(bstr,vchar.arr,vchar.len)
#define strToOra(vchar,bstr)   vchar.len = strlen(bstr);strncpy((char *)vchar.arr,bstr,vchar.len);vchar.arr[vchar.len] = 0
#define strconv(buffer,fonte)  sprintf(buffer,"%d",fonte)

class CAtivarAparelhoPC
{
    private:
        bool tuxedoIniciado;
        bool conectado;
        char *rcvbuf;
        char *rcvbuftmp;
        char *sendbuf;
        char *pszXmlAux;
        char basicSvcId[256];
        char linha[2048];
        char xmlHeader[256];
        Log logFile;
        long rcvlen;
        ParametrosCOMSAP parametrosCOMSAP;
        //TuxHelper tuxhp;

	public:
		CAtivarAparelhoPC();
		~CAtivarAparelhoPC();
		void processaBatch();
	
	private:
		void iniciarTuxedo( char* userTux, char* cltTux, char* pwsTux, char* pwsTuxGen );
		void encerrarTuxedo();
		char* ObterValorTag( const char* pXml, const char* pTag );
		bool callSAP();
		bool buscarAvisoErro(char *errCd,char *msg);


};
 
#endif
