/*
 * CSittel.h
 *
 *  Created on: 14/05/2013
 *      Author: Jones Randis
 */

#ifndef SITTEL_H_
#define SITTEL_H_

#include <exception>
#include <iostream>
#include <ostream>
#include <sstream>
#include <string>
#include <memory>
#include <vector>
#include <cstdint>
#include <cstdlib>
#include <ctime>

#include <occi.h>
#include <occiCommon.h>

#include "CConfig.h"
#include "CFilaProcessum.h"
#include "CParametro.h"

namespace batch {

class CSittel {
public:
	CSittel(std::string& configFile);
	virtual ~CSittel();
	void Run();
    void Run( char* idFilaProcessum );
	static int main(int argC, char **argV);
    int NrMaxTentativa;
    std::string MsgNroExec;

protected:
	CConfig config;
	oracle::occi::Environment *env;
	oracle::occi::Connection *conn;
	TmapParametro mapParametro;

	void DBConnect();
	void DBDisconnect();
	void loadParam();
	void ProcessarAssinante(SFilaProcessum& filaprocessum);
	void ProcessarAssinanteTerminal(SFilaProcessum& filaprocessum);
	void ProcessarInstalacao(SFilaProcessum& filaprocessum);
	void setInicioProcessamento(SFilaProcessum& filaprocessum);
	void setFimProcessamento(SFilaProcessum& filaprocessum, int inprocessado=1, int64_t cderro=0, const std::string& dserro=std::string("Sucesso"));
	void setFimProcessamento(SFilaProcessum& filaprocessum, int inprocessado, const std::string& cderro, const std::string& dserro);
	bool encaminharResultadoProcessamento(SFilaProcessum& sFilaProcessum, const std::string& filename);
	bool encaminharResultadoProcessamento(SFilaProcessum& sFilaProcessum, int64_t cderro, const std::string& dserro);
	bool encaminharResultadoProcessamento(SFilaProcessum& sFilaProcessum);
    void TrataMsgPrc( std::string& msg );
};

} // Namespace

#endif /* SITTEL_H_ */
