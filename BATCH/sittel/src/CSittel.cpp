/*
 * sittel.cpp
 *
 *  Created on: 14/05/2013
 *      Author: Jones Randis
 */

#include "garbage_collector.h"

#include <exception>
#include <iostream>
#include <string>
#include <fstream>
#include <memory>
#include <algorithm>
#include <utility>
#include <functional>
#include <typeinfo>
#include <vector>
#include <map>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cerrno>
#include <clocale>
#include <cwchar>

extern "C" {
#include <unistd.h>
#include <sys/file.h>
#include <sys/mode.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <libgen.h>
#include <fcntl.h>
#include <xml.h>
#include <xmlotn.h>
#include <xmlerr.h>
#include <xmlsoap.h>
}

#include <xml.hpp>
#include <xmlotn.hpp>
#include <xmlctx.hpp>
#include <xmlsoap.hpp>

#include "config.h"
#include "CSittel.h"
#include "CLog.h"
#include "CLock.h"
#include "CUtil.h"
#include "CException.h"
#include "CSoapException.h"
#include "CAlvoProcessum.h"
#include "CPessoaDocumento.h"
//#include "CContaEndereco.h"
#include "CPessoaEndereco.h"
#include "CPessoaLinha.h"
#include "CPessoaLinhaHistorico.h"
//#include "CLinhaConta.h"
#include "CLinhaEndereco.h"
#include "CEnderecoLinha.h"
#include "CAssinante.h"
#include "CAssinanteTerminal.h"
#include "CInstalacao.h"
#include "CRequisicaoJudicial.h"

namespace batch {

int CSittel::main(int argC, char **argV)
{
	std::setlocale(LC_ALL, "en_US.ISO8859-1");

	LOG_BEGIN
	try {
        char idFilaProcessum[128] = {0};
		std::string configFile( basename(argV[0]) );
		configFile.append(".cfg");

		CSittel app(configFile);
        
        if ( argC == 2 )
        {
            strcpy ( idFilaProcessum, argV[1] );
            app.Run( idFilaProcessum );
        }
        else
        {
		app.Run();
	}
		//app.Run();
	}
	catch (oracle::occi::SQLException& e) {
		LOG_ERR("SQLException: code=[" << e.getErrorCode() << "], message=[" << e.getMessage() << "]")
		LOG_ERR("Processo finalizado com erro!")
		LOG_END
		return EXIT_FAILURE;
	}
	catch (OracleXml::Soap::SoapException& e) {
		LOG_ERR("SoapException: code=[" << e.getCode() << "], message=[" << (char*)e.getMessage() << "]")
		LOG_ERR("Processo finalizado com erro!")
		LOG_END
		return EXIT_FAILURE;
	}
	catch (OracleXml::XmlException& e) {
		LOG_ERR("XmlException: code=" << e.getCode())
		LOG_ERR("Processo finalizado com erro!")
		LOG_END
		return EXIT_FAILURE;
	}
	catch(std::exception& e) {
		LOG_ERR("Exception: " << e.what())
		LOG_ERR("Processo finalizado com erro!")
		LOG_END
		return EXIT_FAILURE;
	}
	catch(...) {
		LOG_ERR("Unknown Exception!")
		LOG_ERR("Processo finalizado com erro!")
		LOG_END
		return EXIT_FAILURE;
	}
	LOG_END
	return EXIT_SUCCESS;
}


CSittel::CSittel(std::string& configFile): config(configFile), env(0), conn(0)
{
	config.load();
	logLevel(config.log_level);
	env = oracle::occi::Environment::createEnvironment(ORA_ENCODING, ORA_ENCODING, oracle::occi::Environment::DEFAULT);
}


CSittel::~CSittel()
{
	DBDisconnect();
	if (env) oracle::occi::Environment::terminateEnvironment(env);
}

void CSittel::DBConnect()
{
	LOG_INFO("Conectando em " << config.usr_db << "@" << config.inst_db)

	try {
		conn = env->createConnection(config.usr_db, config.pwd_db, config.inst_db);
	}
	catch (oracle::occi::SQLException &ex) {
		LOG_INFO("SQLException: " << ex.what())
		throw XDatabaseConnect;
	}

	LOG_INFO("Conectado com sucesso!")
}


void CSittel::DBDisconnect()
{
	if (env && conn) {
		LOG_INFO("Encerrando conexao " << config.usr_db << "@" << config.inst_db)
		try {
			env->terminateConnection(conn);
		}
		catch (oracle::occi::SQLException &ex) {
			LOG_INFO("SQLException: " << ex.what())
			throw XDatabaseDisconnect;
		}
		conn = 0;
	}
}

void CSittel::Run()
{LOG_FUNC

	char bf[16];
    int tentativa_atual;
    std::string msgTmp;
    
	CLock lock( config.path_tmp );
	DBConnect();
	loadParam();

	TvecFilaProcessum vecFilaProcessum;
	{
		CFilaProcessum cFilaProcessum( conn );
		cFilaProcessum.execute();
		cFilaProcessum.fetch(vecFilaProcessum);
	}

	for(int i=0; i < vecFilaProcessum.size(); i++) {
		SFilaProcessum& sFilaProcessum = vecFilaProcessum.at(i);
		CFilaProcessum::print(sFilaProcessum);

        msgTmp = sFilaProcessum.dserro;
        
        /*
         * Identifica da coluna dsErro, a ultima tentativa que foi executada
         */
        sprintf( bf, "%.12s", msgTmp.c_str() );
        sprintf( bf, "%s", (char*)&bf[10] );
        tentativa_atual = atoi(bf);
        LOG_INFO( "Tentativa Atual " << tentativa_atual )
        tentativa_atual++;
        sFilaProcessum.MsgTentativa = "Tentativa ";
        sprintf( bf,"%d", tentativa_atual );
        sFilaProcessum.MsgTentativa += bf;
        sFilaProcessum.MsgTentativa += " - ";
        MsgNroExec = sFilaProcessum.MsgTentativa;
        /*----------------------------------------------------------------*/
        
        if ( tentativa_atual > NrMaxTentativa )
        {
            TrataMsgPrc( msgTmp );
            setFimProcessamento(sFilaProcessum, 1, 7, msgTmp.c_str() );
            continue;
        }

        
		switch( sFilaProcessum.tiposolicitacao ) {
		case CFilaProcessum::CONSULTA_ASSINANTE:
			ProcessarAssinante(sFilaProcessum);
			break;

		case CFilaProcessum::CONSULTA_TERMINAL_ASSINANTE:
			ProcessarAssinanteTerminal(sFilaProcessum);
			break;

		case CFilaProcessum::CONSULTA_INSTALACAO:
			ProcessarInstalacao(sFilaProcessum);
			break;

		default:
			LOG_ERR("tiposolicitacao: " << sFilaProcessum.tiposolicitacao)
			//throw XTipoSolicitacao;
			break;
		}
	}

	if ( vecFilaProcessum.empty() )
		LOG_INFO(">>> NAO HA REGISTROS PARA PROCESSAMENTO EM CUSTOMER.FILAPROCESSUM <<<")

	DBDisconnect();
}


void CSittel::Run( char* idFilaProcessum )
{LOG_FUNC

	char bf[16];
    int tentativa_atual;
    std::string msgTmp;
    
	CLock lock( config.path_tmp );
	DBConnect();
	loadParam();

	TvecFilaProcessum vecFilaProcessum;
	{
		CFilaProcessum cFilaProcessum( conn );
		cFilaProcessum.execute( idFilaProcessum );
		cFilaProcessum.fetch(vecFilaProcessum);
	}

	for(int i=0; i < vecFilaProcessum.size(); i++) {
		SFilaProcessum& sFilaProcessum = vecFilaProcessum.at(i);
		CFilaProcessum::print(sFilaProcessum);

        msgTmp = sFilaProcessum.dserro;
        
        /*
         * Identifica da coluna dsErro, a ultima tentativa que foi executada
         */
        sprintf( bf, "%.12s", msgTmp.c_str() );
        sprintf( bf, "%s", (char*)&bf[10] );
        tentativa_atual = atoi(bf);
        LOG_INFO( "Tentativa Atual " << tentativa_atual )
        tentativa_atual++;
        sFilaProcessum.MsgTentativa = "Tentativa ";
        sprintf( bf,"%d", tentativa_atual );
        sFilaProcessum.MsgTentativa += bf;
        sFilaProcessum.MsgTentativa += " - ";
        MsgNroExec = sFilaProcessum.MsgTentativa;
        /*----------------------------------------------------------------*/
        
        if ( tentativa_atual > NrMaxTentativa )
        {
            TrataMsgPrc( msgTmp );
            setFimProcessamento(sFilaProcessum, 1, 7, msgTmp.c_str() );
            continue;
        }

		switch( sFilaProcessum.tiposolicitacao ) {
		case CFilaProcessum::CONSULTA_ASSINANTE:
			ProcessarAssinante(sFilaProcessum);
			break;

		case CFilaProcessum::CONSULTA_TERMINAL_ASSINANTE:
			ProcessarAssinanteTerminal(sFilaProcessum);
			break;

		case CFilaProcessum::CONSULTA_INSTALACAO:
			ProcessarInstalacao(sFilaProcessum);
			break;

		default:
			LOG_ERR("tiposolicitacao: " << sFilaProcessum.tiposolicitacao)
			//throw XTipoSolicitacao;
			break;
		}
	}

	if ( vecFilaProcessum.empty() )
		LOG_INFO(">>> NAO HA REGISTROS PARA PROCESSAMENTO EM CUSTOMER.FILAPROCESSUM <<<")

	DBDisconnect();
}


void CSittel::loadParam()
{
	mapParametro.clear();

	CParametro cParametro(conn);

	TvecCdParametro vecCdParametro;
	//vecCdParametro.push_back("PROCESSUM_LIMITE_PARAM_ALVO");
	//vecCdParametro.push_back("PROCESSUM_LIMITE_PERIODO_CONSULTA");
	//vecCdParametro.push_back("PROCESSUM_LIMITE_CONSULTA_TERMINAL");
	vecCdParametro.push_back("PROCESSUM_URI_ENCAMINHAR_RESULTADO_PROCESSAMENTO");
	vecCdParametro.push_back("PROCESSUM_USER_ENCAMINHAR_RESULTADO_PROCESSAMENTO");
	vecCdParametro.push_back("PROCESSUM_PASS_ENCAMINHAR_RESULTADO_PROCESSAMENTO");

	cParametro.where(vecCdParametro);
	cParametro.execute();
	cParametro.fetch(mapParametro);

	if (mapParametro.empty())
		throw XLoadParam;

	CParametro::print(mapParametro);
    
    cParametro.setSqlParam();
    cParametro.execute();
    cParametro.nextParam();
    NrMaxTentativa = cParametro.GetTentativas();
    if ( NrMaxTentativa <= 0 )
    {
        NrMaxTentativa = 3;
    }
    LOG_INFO( "NrMaxTentativa: " << NrMaxTentativa )
}


void CSittel::TrataMsgPrc( std::string& msg )
{
    std::string msgTmp;
    std::size_t npos;

    msgTmp = msg;
    
    if ( !msgTmp.compare(0,10,"Tentativa ") )
    {
        npos = msgTmp.find_first_of("-");
        msgTmp = msgTmp.substr(npos+2);
    }
    msgTmp = MsgNroExec + msgTmp;
    LOG_INFO( "msgTmp [" << msgTmp << "]" )
    
    msg = msgTmp;

}



bool CSittel::encaminharResultadoProcessamento(SFilaProcessum& sFilaProcessum, const std::string& filename)
{
	LOG_INFO(__func__ << ": " << filename)
    std::string msgTmp;

	try {
		CRequisicaoJudicial cRequisicaoJudicial(mapParametro);
		CRequisicaoJudicial::SEncaminharResultadoProcessamentoResponse response;
		if ( cRequisicaoJudicial.encaminharResultadoProcessamento(sFilaProcessum, filename, response) ) {
			if (response.status == "ERRO") {
				// Ex: 405 - O arquivo (ASSINANTE-TERMINAL_19818_VIVONET_20130712_180503.zip) não foi encontrado. /opt/spic/ASSINANTE-TERMINAL_19818_VIVONET_20130712_180503.zip (No such file or directory)
				if (response.codigoErro == "405") {
					LOG_ERR(">>> ARQUIVO " << filename << " NAO ENCONTRADO NO DESTINO <<<")
					LOG_ERR("response.status: " << response.status)
					LOG_ERR("response.codigoErro: " << response.codigoErro)
					LOG_ERR("response.descricaoErro: " << response.descricaoErro)

					if (sFilaProcessum.cderro.isNull()) {
						LOG_INFO(">>> MARCANDO encaminharResultadoProcessamento PARA PROXIMA EXECUCAO <<<")
                        msgTmp = filename;
                        TrataMsgPrc( msgTmp );
						setFimProcessamento(sFilaProcessum, 0, -1, msgTmp);
					}
					else if (static_cast<int64_t>(sFilaProcessum.cderro) < 0 && static_cast<int64_t>(sFilaProcessum.cderro) > -5) {
						LOG_INFO(">>> MARCANDO encaminharResultadoProcessamento PARA PROXIMA EXECUCAO (" << (static_cast<int64_t>(sFilaProcessum.cderro) * -1) + 1 <<") <<<")
                        msgTmp = filename;
                        TrataMsgPrc( msgTmp );
						setFimProcessamento(sFilaProcessum, 0, (static_cast<int64_t>(sFilaProcessum.cderro) - 1), msgTmp);
					}
					else 
                    {
                        msgTmp = response.descricaoErro;
                        TrataMsgPrc( msgTmp );
                        setFimProcessamento(sFilaProcessum, 0, response.codigoErro, msgTmp);
                    }
				}
				else 
                {
                    if ( response.codigoErro == "415" || response.codigoErro == "416" )
                    {
                        msgTmp = response.descricaoErro;
                        TrataMsgPrc( msgTmp );
                        setFimProcessamento(sFilaProcessum, 1, response.codigoErro, msgTmp);
                    }
                    else
                    {
                        msgTmp = response.descricaoErro;
                        TrataMsgPrc( msgTmp );
                        setFimProcessamento(sFilaProcessum, 0, response.codigoErro, msgTmp);
					}
				}
			}
			else if (response.status == "OK") {
				if (!response.codigoErro.empty() && !response.descricaoErro.empty())
                {
                    msgTmp = response.descricaoErro;
                    TrataMsgPrc( msgTmp );
					setFimProcessamento(sFilaProcessum, 0, response.codigoErro, msgTmp);
                }
				else
                {
                    msgTmp = "Sucesso - response.status[OK]";
                    TrataMsgPrc( msgTmp );
                    setFimProcessamento(sFilaProcessum, 1, 0, msgTmp);
                }
			}
			else 
            {
                msgTmp = response.descricaoErro;
                TrataMsgPrc( msgTmp );
                setFimProcessamento(sFilaProcessum, 0, response.codigoErro, msgTmp);
			}

			return true;
		}
	}
	catch(OracleXml::Soap::SoapException& e) {
		LOG_ERR("SoapException: code=[" << e.getCode() << "], message=[" << (char*)e.getMessage() << "]")
	}

	LOG_INFO(">>> MARCANDO encaminharResultadoProcessamento PARA PROXIMA EXECUCAO <<<")
    msgTmp = filename;
    TrataMsgPrc( msgTmp );
	setFimProcessamento(sFilaProcessum, 0, -1, msgTmp);
	return false;
}

bool CSittel::encaminharResultadoProcessamento(SFilaProcessum& sFilaProcessum, int64_t cderro, const std::string& dserro)
{
	LOG_INFO(__func__ << ": cderro: " << cderro << ", dserro: " << dserro)
    std::string msgTmp;

	try {
		CRequisicaoJudicial cRequisicaoJudicial(mapParametro);
		CRequisicaoJudicial::SEncaminharResultadoProcessamentoResponse response;
		if ( cRequisicaoJudicial.encaminharResultadoProcessamento(sFilaProcessum, cderro, dserro, response) ) {
			std::stringstream descricaoErro;
			descricaoErro << dserro << " - response.status[" << response.status << "]";

			if ( !response.codigoErro.empty() )
				descricaoErro << ", response.codigoErro[" << response.codigoErro << "]";

			if ( !response.descricaoErro.empty() )
				descricaoErro << ", response.descricaoErro[" << response.descricaoErro << "]";

            msgTmp = descricaoErro.str();
            TrataMsgPrc( msgTmp );
			setFimProcessamento(sFilaProcessum, 0, cderro, msgTmp);
			return true;
		}
	}
	catch(OracleXml::Soap::SoapException& e) {
		LOG_ERR("SoapException: code=[" << e.getCode() << "], message=[" << (char*)e.getMessage() << "]")
	}

	LOG_INFO(">>> MARCANDO encaminharResultadoProcessamento PARA PROXIMA EXECUCAO <<<")
    msgTmp = dserro;
    TrataMsgPrc( msgTmp );
	setFimProcessamento(sFilaProcessum, 0, cderro, msgTmp);
	return false;
}

bool CSittel::encaminharResultadoProcessamento(SFilaProcessum& sFilaProcessum) {
	bool retval = false;
	if ( !sFilaProcessum.cderro.isNull() ) {
		switch( static_cast<int64_t>(sFilaProcessum.cderro) ) {
		case -1:
		case -2:
		case -3:
		case -4:
		case -5:
			retval = true;
			encaminharResultadoProcessamento(sFilaProcessum, sFilaProcessum.dserro);
			break;

		case CDERRO_PERIODO:
			retval = true;
			encaminharResultadoProcessamento(sFilaProcessum, CDERRO_PERIODO, DSERRO_PERIODO);
			break;
#ifdef LIMITE_QUANTIDADE_RETORNO
		case CDERRO_RETORNO:
			retval = true;
			encaminharResultadoProcessamento(sFilaProcessum, CDERRO_RETORNO, DSERRO_RETORNO);
			break;
#endif
		}
	}
	return retval;
}

void CSittel::ProcessarAssinante(SFilaProcessum& sFilaProcessum)
{LOG_FUNC

	setInicioProcessamento( sFilaProcessum );

	if (encaminharResultadoProcessamento(sFilaProcessum))
		return;

	TvecAlvoProcessum vecAlvoProcessum;
	{
		CAlvoProcessum cAlvoProcessum(conn);
		cAlvoProcessum.where(sFilaProcessum);
		cAlvoProcessum.execute();
		cAlvoProcessum.fetch(vecAlvoProcessum);
	}

	CAssinante cAssinante(config);

	uint32_t err=0;
	for(int i=0; i < vecAlvoProcessum.size(); i++)
	{
		SAlvoProcessum& sAlvoProcessum = vecAlvoProcessum.at(i);
		CAlvoProcessum::print(sAlvoProcessum);

		bool alvoPessoa = !sAlvoProcessum.cnpj.empty() || !sAlvoProcessum.cpf.empty() || !sAlvoProcessum.documentoassinante.empty() || !sAlvoProcessum.nomeassinante.empty();
		bool alvoLinha = !sAlvoProcessum.nrterminalassinante.empty();

		try {
			if ( (alvoPessoa && alvoLinha) || alvoPessoa ) {
			TmapPessoaDocumento mapPessoaDocumento;
			{
				CPessoaDocumento cPessoaDocumento(conn);
				cPessoaDocumento.where(sAlvoProcessum);
				cPessoaDocumento.execute();
				cPessoaDocumento.fetch(mapPessoaDocumento);
			}

			if ( !mapPessoaDocumento.empty() ) {
				LOG_INFO(">>> ALVO ENCONTRADO EM PESSODOCUMENTO <<<")

				TmapPessoaDocumento::iterator itPD = mapPessoaDocumento.begin();
				for(; itPD != mapPessoaDocumento.end(); itPD++) {
					std::for_each(itPD->second.begin(), itPD->second.end(), std::ptr_fun(CPessoaDocumento::print));
					SPessoaDocumento& sPessoaDocumento = itPD->second.at(0);

					TvecPessoaLinha vecPessoaLinha;
					{
						CPessoaLinha cPessoaLinha(conn);
						cPessoaLinha.where(sPessoaDocumento, sAlvoProcessum);
						cPessoaLinha.execute();
						cPessoaLinha.fetch(vecPessoaLinha);
					}

					if ( !vecPessoaLinha.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHA <<<")
							//std::for_each(vecPessoaLinha.begin(), vecPessoaLinha.end(), std::ptr_fun(CPessoaLinha::print));
							//cAssinante.append(itPD->second);

							for(int i=0; i < vecPessoaLinha.size(); i++) {
								SPessoaLinha& sPessoaLinha = vecPessoaLinha.at(i);
								CPessoaLinha::print(sPessoaLinha);
						cAssinante.append( itPD->second );
					}
						}
					else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHA <<<")

					TvecPessoaLinhaHistorico vecPessoaLinhaHist;
					{
						CPessoaLinhaHistorico cPessoaLinhaHist(conn);
						cPessoaLinhaHist.where(sPessoaDocumento, sAlvoProcessum);
						cPessoaLinhaHist.execute();
						cPessoaLinhaHist.fetch(vecPessoaLinhaHist);
					}

					if ( !vecPessoaLinhaHist.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHAHISTORICO <<<")
							//std::for_each(vecPessoaLinhaHist.begin(), vecPessoaLinhaHist.end(), std::ptr_fun(CPessoaLinhaHistorico::print));
							//cAssinante.append( itPD->second );

							for(int i=0; i < vecPessoaLinhaHist.size(); i++) {
								SPessoaLinhaHistorico& sPessoaLinhaHistorico = vecPessoaLinhaHist.at(i);
								CPessoaLinhaHistorico::print(sPessoaLinhaHistorico);
						cAssinante.append( itPD->second );
					}
						}
					else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHAHISTORICO <<<")
				}
			}
			else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSODOCUMENTO <<<")
		}
		else if (alvoLinha) {
			TvecPessoaLinha vecPessoaLinha;
			{
				CPessoaLinha cPessoaLinha(conn);
				cPessoaLinha.where(sAlvoProcessum);
				cPessoaLinha.execute();
				cPessoaLinha.fetch(vecPessoaLinha);
			}

			if ( !vecPessoaLinha.empty() ) {
				LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHA <<<")

				for(int i=0; i < vecPessoaLinha.size(); i++) {
					SPessoaLinha& sPessoaLinha = vecPessoaLinha.at(i);
					CPessoaLinha::print(sPessoaLinha);

					TvecPessoaDocumento vecPessoaDocumento;
					{
						CPessoaDocumento cPessoaDocumento(conn);
							cPessoaDocumento.where(sPessoaLinha, sAlvoProcessum);
						cPessoaDocumento.execute();
						cPessoaDocumento.fetch(vecPessoaDocumento);
					}

					if ( !vecPessoaDocumento.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM PESSOADOCUMENTO <<<")
						std::for_each(vecPessoaDocumento.begin(), vecPessoaDocumento.end(), std::ptr_fun(CPessoaDocumento::print));
						cAssinante.append(vecPessoaDocumento);
					}
					else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOADOCUMENTO <<<")
				}
			}
			else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHA <<<")

			TvecPessoaLinhaHistorico vecPessoaLinhaHist;
			{
				CPessoaLinhaHistorico cPessoaLinhaHist(conn);
				cPessoaLinhaHist.where(sAlvoProcessum);
				cPessoaLinhaHist.execute();
				cPessoaLinhaHist.fetch(vecPessoaLinhaHist);
			}

			if ( !vecPessoaLinhaHist.empty() ) {
				LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHAHISTORICO <<<")

				for(int i=0; i < vecPessoaLinhaHist.size(); i++) {
					SPessoaLinhaHistorico& sPessoaLinhaHist = vecPessoaLinhaHist.at(i);
					CPessoaLinhaHistorico::print(sPessoaLinhaHist);

					TvecPessoaDocumento vecPessoaDocumento;
					{
						CPessoaDocumento cPessoaDocumento(conn);
							cPessoaDocumento.where(sPessoaLinhaHist, sAlvoProcessum);
						cPessoaDocumento.execute();
						cPessoaDocumento.fetch(vecPessoaDocumento);
					}

					if ( !vecPessoaDocumento.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM PESSOADOCUMENTO <<<")
						std::for_each(vecPessoaDocumento.begin(), vecPessoaDocumento.end(), std::ptr_fun(CPessoaDocumento::print));
						cAssinante.append(vecPessoaDocumento);
					}
					else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOADOCUMENTO <<<")
				}
			}
			else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHAHISTORICO <<<")
		}
	}
		catch (oracle::occi::SQLException& e) {
			LOG_ERR("SQLException: code=[" << e.getErrorCode() << "], message=[" << e.getMessage() << "]")

			SAssinante assinante;
			assinante.tipo = "1";
			assinante.cpfCnpj = "00000000000";
			assinante.documento = "00000000000";

			assinante.nome = "SQLException: ";
			assinante.nome.append(e.getMessage());
			trim(assinante.nome);

			cAssinante.append( assinante );
			err++;
		}
		catch (OracleXml::Soap::SoapException& e) {
			LOG_ERR("SoapException: code=[" << e.getCode() << "], message=[" << e.getMessage() << "]")

			SAssinante assinante;
			assinante.tipo = "1";
			assinante.cpfCnpj = "00000000000";
			assinante.documento = "00000000000";

			assinante.nome = "SoapException: ";
			assinante.nome.append((char*)e.getMessage());
			trim(assinante.nome);

			cAssinante.append( assinante );
			err++;
		}
		catch(std::exception& e) {
			LOG_ERR("Exception: " << e.what())

			SAssinante assinante;
			assinante.tipo = "1";
			assinante.cpfCnpj = "00000000000";
			assinante.documento = "00000000000";

			assinante.nome = "std::exception: ";
			assinante.nome.append(e.what());
			trim(assinante.nome);

			cAssinante.append( assinante );
			err++;
		}
		}

	if (err && err == vecAlvoProcessum.size()) {
		if ( !encaminharResultadoProcessamento(sFilaProcessum, CDERRO_TODOSERR, DSERRO_TODOSERR) )
			LOG_ERR(">>> NAO FOI POSSIVEL encaminharResultadoProcessamento <<<")
		return;
	}

	if ( cAssinante.hasChildNodes() ) {
#ifdef LIMITE_QUANTIDADE_RETORNO
		if (cAssinante.getChildLength() > LIMITE_QUANTIDADE_RETORNO) {
			LOG_INFO(">>> QUANTIDADE DE RETORNO ATINGIU LIMITE DE " << LIMITE_QUANTIDADE_RETORNO << " REGISTROS PARA IDFILAPROCESSUM " << static_cast<int64_t>(sFilaProcessum.idfilaprocessum) << " <<<")

			if ( !encaminharResultadoProcessamento(sFilaProcessum, CDERRO_RETORNO, DSERRO_RETORNO) )
				LOG_ERR(">>> NAO FOI POSSIVEL encaminharResultadoProcessamento <<<")

			return;
		}
#endif
	}
	else LOG_INFO(">>> NENHUM ALVO ENCONTRADO PARA IDFILAPROCESSUM " << static_cast<int64_t>(sFilaProcessum.idfilaprocessum) << " <<<")

	cAssinante.loadSchema();
	cAssinante.validate();

	std::string xmlfile;
	cAssinante.write(sFilaProcessum, xmlfile);
	if ( !encaminharResultadoProcessamento(sFilaProcessum, xmlfile) )
		LOG_ERR(">>> NAO FOI POSSIVEL encaminharResultadoProcessamento <<<")
}


void CSittel::ProcessarAssinanteTerminal(SFilaProcessum& sFilaProcessum)
{LOG_FUNC

	setInicioProcessamento( sFilaProcessum );

	if (encaminharResultadoProcessamento(sFilaProcessum))
		return;

	TvecAlvoProcessum vecAlvoProcessum;
	{
		CAlvoProcessum cAlvoProcessum(conn);
		cAlvoProcessum.where(sFilaProcessum);
		cAlvoProcessum.execute();
		cAlvoProcessum.fetch(vecAlvoProcessum);
	}

	CAssinanteTerminal cAssinanteTerminal(config);

	uint32_t err=0;
	for(int i=0; i < vecAlvoProcessum.size(); i++) {
		SAlvoProcessum& sAlvoProcessum = vecAlvoProcessum.at(i);
		CAlvoProcessum::print(sAlvoProcessum);

		bool alvoPessoa = !sAlvoProcessum.cnpj.empty() || !sAlvoProcessum.cpf.empty() || !sAlvoProcessum.documentoassinante.empty() || !sAlvoProcessum.nomeassinante.empty();
		bool alvoLinha = !sAlvoProcessum.nrterminalassinante.empty();

		try {
		if ( (alvoPessoa && alvoLinha) || alvoPessoa ) {
			TmapPessoaDocumento mapPessoaDocumento;
			{
				CPessoaDocumento cPessoaDocumento(conn);
				cPessoaDocumento.where(sAlvoProcessum);
				cPessoaDocumento.execute();
				cPessoaDocumento.fetch(mapPessoaDocumento);
			}

			if ( !mapPessoaDocumento.empty() ) {
				LOG_INFO(">>> ALVO ENCONTRADO EM PESSODOCUMENTO <<<")

				TmapPessoaDocumento::iterator itPD = mapPessoaDocumento.begin();
				for(; itPD != mapPessoaDocumento.end(); itPD++) {
					std::for_each(itPD->second.begin(), itPD->second.end(), std::ptr_fun(CPessoaDocumento::print));
					SPessoaDocumento& sPessoaDocumento = itPD->second.at(0);

					TvecPessoaLinha vecPessoaLinha;
					{
						CPessoaLinha cPessoaLinha(conn);
						cPessoaLinha.where(sPessoaDocumento, sAlvoProcessum);
						cPessoaLinha.execute();
						cPessoaLinha.fetch(vecPessoaLinha);
					}

					if ( !vecPessoaLinha.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHA <<<")

						for(int i=0; i < vecPessoaLinha.size(); i++) {
							SPessoaLinha& sPessoaLinha = vecPessoaLinha.at(i);
							CPessoaLinha::print(sPessoaLinha);
							cAssinanteTerminal.append(itPD->second, sPessoaLinha);
						}
					}
					else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHA <<<")

					TvecPessoaLinhaHistorico vecPessoaLinhaHist;
					{
						CPessoaLinhaHistorico cPessoaLinhaHist(conn);
						cPessoaLinhaHist.where(sPessoaDocumento, sAlvoProcessum);
						cPessoaLinhaHist.execute();
						cPessoaLinhaHist.fetch(vecPessoaLinhaHist);
					}

					if ( !vecPessoaLinhaHist.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHAHISTORICO <<<")

						for(int i=0; i < vecPessoaLinhaHist.size(); i++) {
							SPessoaLinhaHistorico& sPessoaLinhaHist = vecPessoaLinhaHist.at(i);
							CPessoaLinhaHistorico::print(sPessoaLinhaHist);
							cAssinanteTerminal.append(itPD->second, sPessoaLinhaHist);
						}
					}
					else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHAHISTORICO <<<")
				}
			}
			else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSODOCUMENTO <<<")
		}
		else if ( alvoLinha ) {
			TvecPessoaLinha vecPessoaLinha;
			{
				CPessoaLinha cPessoaLinha(conn);
				cPessoaLinha.where(sAlvoProcessum);
				cPessoaLinha.execute();
				cPessoaLinha.fetch(vecPessoaLinha);
			}

			if ( !vecPessoaLinha.empty() ) {
				LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHA <<<")

				for(int i=0; i < vecPessoaLinha.size(); i++) {
					SPessoaLinha& sPessoaLinha = vecPessoaLinha.at(i);
					CPessoaLinha::print(sPessoaLinha);

					TvecPessoaDocumento vecPessoaDocumento;
					{
						CPessoaDocumento cPessoaDocumento(conn);
							cPessoaDocumento.where(sPessoaLinha, sAlvoProcessum);
						cPessoaDocumento.execute();
						cPessoaDocumento.fetch(vecPessoaDocumento);
					}

					if ( !vecPessoaDocumento.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM PESSODOCUMENTO <<<")
						std::for_each(vecPessoaDocumento.begin(), vecPessoaDocumento.end(), std::ptr_fun(CPessoaDocumento::print));
						cAssinanteTerminal.append(vecPessoaDocumento, sPessoaLinha);
					}
					else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSODOCUMENTO <<<")
				}
			}
			else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHA <<<")

			TvecPessoaLinhaHistorico vecPessoaLinhaHist;
			{
				CPessoaLinhaHistorico cPessoaLinhaHist(conn);
				cPessoaLinhaHist.where(sAlvoProcessum);
				cPessoaLinhaHist.execute();
				cPessoaLinhaHist.fetch(vecPessoaLinhaHist);
			}

			if ( !vecPessoaLinhaHist.empty() ) {
				LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHAHISTORICO <<<")

				for(int i=0; i < vecPessoaLinhaHist.size(); i++) {
					SPessoaLinhaHistorico& sPessoaLinhaHist = vecPessoaLinhaHist.at(i);
					CPessoaLinhaHistorico::print(sPessoaLinhaHist);

					TvecPessoaDocumento vecPessoaDocumento;
					{
						CPessoaDocumento cPessoaDocumento(conn);
							cPessoaDocumento.where(sPessoaLinhaHist, sAlvoProcessum);
						cPessoaDocumento.execute();
						cPessoaDocumento.fetch(vecPessoaDocumento);
					}

					if ( !vecPessoaDocumento.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM PESSODOCUMENTO <<<")
						std::for_each(vecPessoaDocumento.begin(), vecPessoaDocumento.end(), std::ptr_fun(CPessoaDocumento::print));
						cAssinanteTerminal.append(vecPessoaDocumento, sPessoaLinhaHist);
					}
					else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSODOCUMENTO <<<")
				}
			}
			else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHAHISTORICO <<<")
		}
	}
		catch (oracle::occi::SQLException& e) {
			LOG_ERR("SQLException: code=[" << e.getErrorCode() << "], message=[" << e.getMessage() << "]")

			err++;
		}
		catch (OracleXml::Soap::SoapException& e) {
			LOG_ERR("SoapException: code=[" << e.getCode() << "], message=[" << e.getMessage() << "]")

			err++;
		}
		catch(std::exception& e) {
			LOG_ERR("Exception: " << e.what())

			err++;
		}
		}

	if (err && err == vecAlvoProcessum.size()) {
		if ( !encaminharResultadoProcessamento(sFilaProcessum, CDERRO_TODOSERR, DSERRO_TODOSERR) )
			LOG_ERR(">>> NAO FOI POSSIVEL encaminharResultadoProcessamento <<<")
		return;
	}

	if ( cAssinanteTerminal.hasChildNodes() ) {
#ifdef LIMITE_QUANTIDADE_RETORNO
		if (cAssinanteTerminal.getChildLength() > LIMITE_QUANTIDADE_RETORNO) {
			LOG_INFO(">>> QUANTIDADE DE RETORNO ATINGIU LIMITE DE " << LIMITE_QUANTIDADE_RETORNO << " REGISTROS PARA IDFILAPROCESSUM " << static_cast<int64_t>(sFilaProcessum.idfilaprocessum) << " <<<")

			if ( !encaminharResultadoProcessamento(sFilaProcessum, CDERRO_RETORNO, DSERRO_RETORNO) )
				LOG_ERR(">>> NAO FOI POSSIVEL encaminharResultadoProcessamento <<<")

			return;
		}
#endif
	}
	else LOG_INFO(">>> NENHUM ALVO ENCONTRADO PARA IDFILAPROCESSUM " << static_cast<int64_t>(sFilaProcessum.idfilaprocessum) << " <<<")

	cAssinanteTerminal.loadSchema();
	cAssinanteTerminal.validate();

	std::string xmlfile;
	cAssinanteTerminal.write(sFilaProcessum, xmlfile);
	if ( !encaminharResultadoProcessamento(sFilaProcessum, xmlfile) )
		LOG_ERR(">>> NAO FOI POSSIVEL encaminharResultadoProcessamento <<<")
}


void CSittel::ProcessarInstalacao(SFilaProcessum& sFilaProcessum)
{LOG_FUNC

	setInicioProcessamento( sFilaProcessum );

	if (encaminharResultadoProcessamento(sFilaProcessum))
		return;

	TvecAlvoProcessum vecAlvoProcessum;
	{
		CAlvoProcessum cAlvoProcessum(conn);
		cAlvoProcessum.where(sFilaProcessum);
		cAlvoProcessum.execute();
		cAlvoProcessum.fetch(vecAlvoProcessum);
	}

	CInstalacao cInstalacao(config);

	uint32_t err=0;
	for(int i=0; i < vecAlvoProcessum.size(); i++) {
		SAlvoProcessum& sAlvoProcessum = vecAlvoProcessum.at(i);
		CAlvoProcessum::print(sAlvoProcessum);

		bool alvoPessoa = !sAlvoProcessum.cnpj.empty() || !sAlvoProcessum.cpf.empty() || !sAlvoProcessum.documentoassinante.empty() || !sAlvoProcessum.nomeassinante.empty();
		bool alvoLinha = !sAlvoProcessum.nrterminalassinante.empty();

		try {
			if ( (alvoPessoa && alvoLinha) || alvoPessoa ) {
			TmapPessoaDocumento mapPessoaDocumento;
			{
				CPessoaDocumento cPessoaDocumento(conn);
				cPessoaDocumento.where(sAlvoProcessum);
				cPessoaDocumento.execute();
				cPessoaDocumento.fetch(mapPessoaDocumento);
			}

			if ( !mapPessoaDocumento.empty() ) {
				LOG_INFO(">>> ALVO ENCONTRADO EM PESSODOCUMENTO <<<")

				TmapPessoaDocumento::iterator itPD = mapPessoaDocumento.begin();
				for(; itPD != mapPessoaDocumento.end(); itPD++) {
					std::for_each(itPD->second.begin(), itPD->second.end(), std::ptr_fun(CPessoaDocumento::print));
					SPessoaDocumento& sPessoaDocumento = itPD->second.at(0);

					TvecPessoaLinha vecPessoaLinha;
					{
						CPessoaLinha cPessoaLinha(conn);
						cPessoaLinha.where(sPessoaDocumento, sAlvoProcessum);
						cPessoaLinha.execute();
						cPessoaLinha.fetch(vecPessoaLinha);
					}

					if ( !vecPessoaLinha.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHA <<<")

						for(int i=0; i < vecPessoaLinha.size(); i++) {
							SPessoaLinha& sPessoaLinha = vecPessoaLinha.at(i);
							CPessoaLinha::print(sPessoaLinha);

							TvecLinhaEndereco vecLinhaEndereco;
							{
								CLinhaEndereco cLinhaEndereco(conn);
								cLinhaEndereco.where(sPessoaLinha);
								cLinhaEndereco.execute();
								cLinhaEndereco.fetch(vecLinhaEndereco);
							}

							if ( !vecLinhaEndereco.empty() ) {
								LOG_INFO(">>> ALVO ENCONTRADO EM CONTAENDERECO <<<")

								for(int i=0; i < vecLinhaEndereco.size(); i++) {
									SLinhaEndereco& sLinhaEndereco = vecLinhaEndereco.at(i);
									CLinhaEndereco::print(sLinhaEndereco);
									cInstalacao.append(sPessoaLinha, sLinhaEndereco);
								}
							}
								else {
									LOG_INFO(">>> ALVO NAO ENCONTRADO EM CONTAENDERECO <<<")

									TvecPessoaEndereco vecPessoaEndereco;
									{
										CPessoaEndereco cPessoaEndereco(conn);
										cPessoaEndereco.where(sPessoaLinha);
										cPessoaEndereco.execute();
										cPessoaEndereco.fetch(vecPessoaEndereco);
									}

									if ( !vecPessoaEndereco.empty() ) {
										LOG_INFO(">>> ALVO ENCONTRADO EM PESSOAENDERECO <<<")

										for(int i=0; i < vecPessoaEndereco.size(); i++) {
											SPessoaEndereco& sPessoaEndereco = vecPessoaEndereco.at(i);
											CPessoaEndereco::print(sPessoaEndereco);
											cInstalacao.append(sPessoaLinha, sPessoaEndereco);
										}
									}
									else {
										LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOAENDERECO <<<");

										cInstalacao.append(sPessoaLinha);
									}
								}
						}
					}
					else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHA <<<")

					TvecPessoaLinhaHistorico vecPessoaLinhaHist;
					{
						CPessoaLinhaHistorico cPessoaLinhaHist(conn);
						cPessoaLinhaHist.where(sPessoaDocumento, sAlvoProcessum);
						cPessoaLinhaHist.execute();
						cPessoaLinhaHist.fetch(vecPessoaLinhaHist);
					}

					if ( !vecPessoaLinhaHist.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHAHISTORICO <<<")

						for(int i=0; i < vecPessoaLinhaHist.size(); i++) {
							SPessoaLinhaHistorico& sPessoaLinhaHist = vecPessoaLinhaHist.at(i);
							CPessoaLinhaHistorico::print(sPessoaLinhaHist);

							TvecLinhaEndereco vecLinhaEndereco;
							{
								CLinhaEndereco cLinhaEndereco(conn);
								cLinhaEndereco.where(sPessoaLinhaHist);
								cLinhaEndereco.execute();
								cLinhaEndereco.fetch(vecLinhaEndereco);
							}

							if ( !vecLinhaEndereco.empty() ) {
								LOG_INFO(">>> ALVO ENCONTRADO EM CONTAENDERECO <<<")

								for(int i=0; i < vecLinhaEndereco.size(); i++) {
									SLinhaEndereco& sLinhaEndereco = vecLinhaEndereco.at(i);
									CLinhaEndereco::print(sLinhaEndereco);
									cInstalacao.append(sPessoaLinhaHist, sLinhaEndereco);
								}
							}
								else {
									LOG_INFO(">>> ALVO NAO ENCONTRADO EM CONTAENDERECO <<<")

									TvecPessoaEndereco vecPessoaEndereco;
									{
										CPessoaEndereco cPessoaEndereco(conn);
										cPessoaEndereco.where(sPessoaLinhaHist);
										cPessoaEndereco.execute();
										cPessoaEndereco.fetch(vecPessoaEndereco);
									}

									if ( !vecPessoaEndereco.empty() ) {
										LOG_INFO(">>> ALVO ENCONTRADO EM PESSOAENDERECO <<<")

										for(int i=0; i < vecPessoaEndereco.size(); i++) {
											SPessoaEndereco& sPessoaEndereco = vecPessoaEndereco.at(i);
											CPessoaEndereco::print(sPessoaEndereco);
											cInstalacao.append(sPessoaLinhaHist, sPessoaEndereco);
										}
									}
									else {
										LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOAENDERECO <<<");

										cInstalacao.append(sPessoaLinhaHist);
					}
							}
							}
						}
					else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHAHISTORICO <<<")
				}
			}
			else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSODOCUMENTO <<<")
		}
		else if (alvoLinha) {
			TvecPessoaLinha vecPessoaLinha;
			{
				CPessoaLinha cPessoaLinha(conn);
				cPessoaLinha.where(sAlvoProcessum);
				cPessoaLinha.execute();
				cPessoaLinha.fetch(vecPessoaLinha);
			}

			if ( !vecPessoaLinha.empty() ) {
				LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHA <<<")

				for(int i=0; i < vecPessoaLinha.size(); i++) {
					SPessoaLinha& sPessoaLinha = vecPessoaLinha.at(i);
					CPessoaLinha::print(sPessoaLinha);

					TvecLinhaEndereco vecLinhaEndereco;
					{
						CLinhaEndereco cLinhaEndereco(conn);
						cLinhaEndereco.where(sPessoaLinha);
						cLinhaEndereco.execute();
						cLinhaEndereco.fetch(vecLinhaEndereco);
					}

					if ( !vecLinhaEndereco.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM CONTAENDERECO <<<")

						for(int i=0; i < vecLinhaEndereco.size(); i++) {
							SLinhaEndereco& sLinhaEndereco = vecLinhaEndereco.at(i);
							CLinhaEndereco::print(sLinhaEndereco);
							cInstalacao.append(sPessoaLinha, sLinhaEndereco);
						}
					}
					else {
						LOG_INFO(">>> ALVO NAO ENCONTRADO EM CONTAENDERECO <<<")

							TvecPessoaEndereco vecPessoaEndereco;
							{
								CPessoaEndereco cPessoaEndereco(conn);
								cPessoaEndereco.where(sPessoaLinha);
								cPessoaEndereco.execute();
								cPessoaEndereco.fetch(vecPessoaEndereco);
							}

							if ( !vecPessoaEndereco.empty() ) {
								LOG_INFO(">>> ALVO ENCONTRADO EM PESSOAENDERECO <<<")

								for(int i=0; i < vecPessoaEndereco.size(); i++) {
									SPessoaEndereco& sPessoaEndereco = vecPessoaEndereco.at(i);
									CPessoaEndereco::print(sPessoaEndereco);
									cInstalacao.append(sPessoaLinha, sPessoaEndereco);
								}
							}
							else {
								LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOAENDERECO <<<");

								cInstalacao.append(sPessoaLinha);
							}
					}
				}
			}
			else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHA <<<")

			TvecPessoaLinhaHistorico vecPessoaLinhaHist;
			{
				CPessoaLinhaHistorico cPessoaLinhaHist(conn);
				cPessoaLinhaHist.where(sAlvoProcessum);
				cPessoaLinhaHist.execute();
				cPessoaLinhaHist.fetch(vecPessoaLinhaHist);
			}

			if ( !vecPessoaLinhaHist.empty() ) {
				LOG_INFO(">>> ALVO ENCONTRADO EM PESSOALINHAHISTORICO <<<")

				for(int i=0; i < vecPessoaLinhaHist.size(); i++) {
					SPessoaLinhaHistorico& sPessoaLinhaHist = vecPessoaLinhaHist.at(i);
					CPessoaLinhaHistorico::print(sPessoaLinhaHist);

					TvecLinhaEndereco vecLinhaEndereco;
					{
						CLinhaEndereco cLinhaEndereco(conn);
						cLinhaEndereco.where(sPessoaLinhaHist);
						cLinhaEndereco.execute();
						cLinhaEndereco.fetch(vecLinhaEndereco);
					}

					if ( !vecLinhaEndereco.empty() ) {
						LOG_INFO(">>> ALVO ENCONTRADO EM CONTAENDERECO <<<")

						for(int i=0; i < vecLinhaEndereco.size(); i++) {
							SLinhaEndereco& sLinhaEndereco = vecLinhaEndereco.at(i);
							CLinhaEndereco::print(sLinhaEndereco);
							cInstalacao.append(sPessoaLinhaHist, sLinhaEndereco);
						}
					}
						else {
							LOG_INFO(">>> ALVO NAO ENCONTRADO EM CONTAENDERECO <<<")

							TvecPessoaEndereco vecPessoaEndereco;
							{
								CPessoaEndereco cPessoaEndereco(conn);
								cPessoaEndereco.where(sPessoaLinhaHist);
								cPessoaEndereco.execute();
								cPessoaEndereco.fetch(vecPessoaEndereco);
							}

							if ( !vecPessoaEndereco.empty() ) {
								LOG_INFO(">>> ALVO ENCONTRADO EM PESSOAENDERECO <<<")

								for(int i=0; i < vecPessoaEndereco.size(); i++) {
									SPessoaEndereco& sPessoaEndereco = vecPessoaEndereco.at(i);
									CPessoaEndereco::print(sPessoaEndereco);
									cInstalacao.append(sPessoaLinhaHist, sPessoaEndereco);
								}
							}
							else {
								LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOAENDERECO <<<");

								cInstalacao.append(sPessoaLinhaHist);
							}
						}
				}
			}
			else LOG_INFO(">>> ALVO NAO ENCONTRADO EM PESSOALINHAHISTORICO <<<")
		}
	}
		catch (oracle::occi::SQLException& e) {
			LOG_ERR("SQLException: code=[" << e.getErrorCode() << "], message=[" << e.getMessage() << "]")

			err++;
		}
		catch (OracleXml::Soap::SoapException& e) {
			LOG_ERR("SoapException: code=[" << e.getCode() << "], message=[" << e.getMessage() << "]")

			err++;
		}
		catch(std::exception& e) {
			LOG_ERR("Exception: " << e.what())

			err++;
		}
		}

	if (err && err == vecAlvoProcessum.size()) {
		if ( !encaminharResultadoProcessamento(sFilaProcessum, CDERRO_TODOSERR, DSERRO_TODOSERR) )
			LOG_ERR(">>> NAO FOI POSSIVEL encaminharResultadoProcessamento <<<")
		return;
	}

	if ( cInstalacao.hasChildNodes() ) {
#ifdef LIMITE_QUANTIDADE_RETORNO
		if (cInstalacao.getChildLength() > LIMITE_QUANTIDADE_RETORNO) {
			LOG_INFO(">>> QUANTIDADE DE RETORNO ATINGIU LIMITE DE " << LIMITE_QUANTIDADE_RETORNO << " REGISTROS PARA IDFILAPROCESSUM " << static_cast<int64_t>(sFilaProcessum.idfilaprocessum) << " <<<")

			if ( !encaminharResultadoProcessamento(sFilaProcessum, CDERRO_RETORNO, DSERRO_RETORNO) )
				LOG_ERR(">>> NAO FOI POSSIVEL encaminharResultadoProcessamento <<<")

			return;
		}
#endif
	}
	else LOG_INFO(">>> NENHUM ALVO ENCONTRADO PARA IDFILAPROCESSUM " << static_cast<int64_t>(sFilaProcessum.idfilaprocessum) << " <<<")

	cInstalacao.loadSchema();
	cInstalacao.validate();

	std::string xmlfile;
	cInstalacao.write(sFilaProcessum, xmlfile);
	if ( !encaminharResultadoProcessamento(sFilaProcessum, xmlfile) )
		LOG_ERR(">>> NAO FOI POSSIVEL encaminharResultadoProcessamento <<<")
}


void CSittel::setInicioProcessamento(SFilaProcessum& sFilaProcessum)
{LOG_FUNC

	std::stringstream sql;
	sql << "update CUSTOMER.FILAPROCESSUM set ";
	sql << "DTINICIOPROCESSAMENTO = sysdate ";
	sql << "where IDFILAPROCESSUM = :IDFILAPROCESSUM ";

	try {
		oracle::occi::Statement *stmt = conn->createStatement( sql.str() );
		stmt->setNumber(1, sFilaProcessum.idfilaprocessum);
		stmt->executeUpdate();
		conn->terminateStatement( stmt );
		conn->commit();
	}
	catch (oracle::occi::SQLException &ex) {
		LOG_ERR("SQLException: " << ex.what())
		throw XDatabaseSqlError;
	}
}

void CSittel::setFimProcessamento(SFilaProcessum& sFilaProcessum, int inprocessado, const std::string& cderro, const std::string& dserro)
{
	std::stringstream ssCdErro(cderro);
	int64_t iCdErro=0;
	ssCdErro >> iCdErro;
	setFimProcessamento(sFilaProcessum, inprocessado, iCdErro, dserro);
}

void CSittel::setFimProcessamento(SFilaProcessum& sFilaProcessum, int inprocessado, int64_t cderro, const std::string& dserro)
{LOG_FUNC

	std::stringstream sql;
	sql << "update CUSTOMER.FILAPROCESSUM set ";
	sql << "DTFIMPROCESSAMENTO=sysdate, INPROCESSADO=:INPROCESSADO, CDERRO=:CDERRO, DSERRO=SUBSTR(:CDERRO,1,255) ";
	sql << "where IDFILAPROCESSUM = :IDFILAPROCESSUM ";

	try {
		oracle::occi::Statement *stmt = conn->createStatement( sql.str() );
		stmt->setInt(1, inprocessado);
		stmt->setNumber(2, oracle::occi::Number(cderro));
		stmt->setString(3, dserro);
		stmt->setNumber(4, sFilaProcessum.idfilaprocessum);
		stmt->executeUpdate();
		conn->terminateStatement( stmt );
		conn->commit();
	}
	catch (oracle::occi::SQLException &ex) {
		LOG_ERR("SQLException: " << ex.what())
		throw XDatabaseSqlError;
	}
}

} // Namespace


