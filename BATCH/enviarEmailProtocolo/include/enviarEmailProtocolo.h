/*
 * enviarEmailProtocolo.h
 *
 *  Created on: 23/02/2013
 *      Author: Jones Randis
 */

#ifndef ENVIAREMAILPROTOCOLO_H_
#define ENVIAREMAILPROTOCOLO_H_

#include <exception>
#include <string>
#include <memory>
#include <vector>
#include <ostream>
#include <ctime>
#include <map>
using namespace std;

#include <tuxfw.h>

#define VIVO_GENERIC_PASSWORD       "vivo"
#define EMAIL_SERVICO 				"SMTPSEND"
#define EMAIL_FROM 					"noreply@vivo.com.br"
#define EMAIL_SUBJECT 				"Atendimento VIVO"

#define NO_DATA_FOUND                   1403

/*******************************************************************************
 * Macros para manipulacao de dados ProC/C++
 *******************************************************************************/
#define STRCPY_TO_ORA(dest, source) { \
    dest.len = (unsigned short) strlen(source);\
    strncpy((char *) dest.arr, (const char *) source, (size_t)dest.len); }

#define STRCPY_FROM_ORA(dest, source) { \
    dest[source.len] = 0; \
    strncpy((char *)dest,(const char *)source.arr, source.len); }

#endif

#define CONVIND(O,I) \
{\
    if (I == -1) { \
        ##O.arr[0]=0; \
    } else { \
        ##O.arr[##O.len]=0; \
    } \
}

#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;
#define endOraStr(varstr)      varstr.arr[varstr.len]= '\0'

// Prototypes
int ReservaLoteRegistros( char * idControleFilaSMS, char * nrMaxRegistros_PRM );
double getRealTime( void );
void ElapsedTime(double StartTime, double EndTime, char * UsedTime);
int AtualizaEstadoProcesso ( char * idCtlFilaSMS_PRM, char * Status_Prm );
int VoltaEstadoOriginal( char * idControleFilaSMS );

class CEnviarEmailProtocolo
{
public:
	CEnviarEmailProtocolo(string& configFile);
	virtual ~CEnviarEmailProtocolo();

	static ostream& log(ostream& out = cout) {
		time_t t = time(0);
		struct tm* ptm = localtime( &t );
		char buff[24];
		strftime(buff, sizeof(buff), "%Y-%m-%d %H:%M:%S -- ", ptm);
	    return (out << buff);
	}

    void Run( char * );
    void DBLoadTable( char * nrMaxRegistros );
    bool EmailValido( char * email );
    int  ReservaLoteRegistros( char * idControleFilaSMS, char * nrMaxRegistros_PRM );
    int  AtualizaEstadoProcesso ( char * idCtlFilaSMS_PRM, char * Status_Prm );
    int  VoltaEstadoOriginal( char * idControleFilaSMS );

protected:
	class CException : public exception
	{
	public:
		CException() : emsg("CEnviarEmailProtocolo Exception!") {}
		CException(string emsg) : emsg(emsg) {}
		virtual const char* what() const throw() { return emsg.c_str(); }
		virtual ~CException() throw() {}
	protected:
		string emsg;
	}
	Exception,
	ExceptionConfigFile,
	ExceptionConfigParam,
	ExceptionTUXinit,
	ExceptionParamDatabase,
	ExceptionDatabaseConnect,
	ExceptionDatabaseDisonnect,
	ExceptionDatabaseSqlError,
	ExceptionRegexCompilation;

	struct CParamConf
	{
		auto_ptr<string> usr_db;
		auto_ptr<string> pwd_db;
		auto_ptr<string> inst_db;
		auto_ptr<string> usr_tux;
		auto_ptr<string> pwd_tux;
		auto_ptr<string> pwd_tux_gen;
		auto_ptr<string> clt_tux;

		bool verify() {
			if ( !usr_db.get() ) return false;
			if ( !pwd_db.get() ) return false;
			if ( !inst_db.get() ) return false;
			if ( !usr_tux.get() ) return false;
			if ( !pwd_tux.get() ) return false;
			if ( !pwd_tux_gen.get() ) return false;
			if ( !clt_tux.get() ) return false;
			return true;
		}
	}
	ParamConf;

	string conn_str;
	vector<string> data_files;
	vector<string>::iterator it_vec_str;
	bool connected;

	TPINIT *tpInitInfo;
	regex_t regex;
	bool regok;
	string pattern;
	size_t nmatch;

	void GetParamConf(string& configFile);
	void DBConnect();
	void DBDisconnect();
	void DBLoadTable();
    void ReenvioMail();
    void ReenvioAgrupadoMail();
	bool TUXinit();
	void TUXterm();

    string msg_prot_fixa_clie;
    string msg_prot_fixa_pros;
    string msg_prot_movel_pros;
    string NroMaxLeitura;   // 156141 - Desligamento Tibco - Fase 02

    void getApoioParameto();

    typedef struct SEmailAgrupadoRec {
        string idatendimentoprotocolo;
        string dtencerramento;
        string sgtipopessoa;
        string idtipolinha;
        string sguf;
        string cdarearegistro;
        string nrlinha;
        string dsmensagemenvio;
        string dtultimaalteracao;
        string idusuarioalteracao;
        string idatendimentoprotocolofilho;
        string tpenvio;
        string inenvio;
        string dsemail;
        string rid;
        string cdorigem;
        string dtaberturaprotocolo;
        string idagrupador;
        string dsmsg;
    } TEmailAgrupadoRec;

    typedef vector<TEmailAgrupadoRec> TvecEmailAgrupadoRec;
    typedef map<long, TvecEmailAgrupadoRec> TmapEmailAgrupado;
};

