/*
 * CValidarLinhaCPF.cpp
 *
 *  Created on: 12/07/2013
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
#include <sstream>
#include <iterator>

extern "C" {
#include <unistd.h>
#include <sys/file.h>
#include <sys/mode.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <libgen.h>
#include <fcntl.h>
#include <dirent.h>
}

#include "CValidarLinhaCPF.h"
#include "config.h"
#include "CLog.h"
#include "CLock.h"
#include "CUtil.h"
#include "CException.h"

namespace batch {

int CValidarLinhaCPF::main(int argC, char **argV)
{
	LOG_BEGIN
	try {
		std::string configFile( basename(argV[0]) );
		configFile.append(".cfg");

		CValidarLinhaCPF app(configFile);
		app.run();
	}
	catch (oracle::occi::SQLException& e) {
		LOG_ERR("SQLException: code=[" << e.getErrorCode() << "], message=[" << e.getMessage() << "]");
		LOG_ERR("Processo finalizado com erro!");
		LOG_END
		return EXIT_FAILURE;
	}
	catch(std::exception& e) {
		LOG_ERR("Exception: " << e.what());
		LOG_ERR("Processo finalizado com erro!");
		LOG_END
		return EXIT_FAILURE;
	}
	catch(...) {
		LOG_ERR("Unknown Exception!");
		LOG_ERR("Processo finalizado com erro!");
		LOG_END
		return EXIT_FAILURE;
	}
	LOG_END
	return EXIT_SUCCESS;
}


CValidarLinhaCPF::CValidarLinhaCPF(std::string& configFile): config(configFile), env(0), conn(0)
{
	config.load();
	logLevel(config.log_level);
	env = oracle::occi::Environment::createEnvironment(ORA_ENCODING, ORA_ENCODING, oracle::occi::Environment::DEFAULT);
}


CValidarLinhaCPF::~CValidarLinhaCPF()
{
	dbDisconnect();
	if (env) oracle::occi::Environment::terminateEnvironment(env);
}

void CValidarLinhaCPF::dbConnect()
{
	LOG_INFO("Conectando em " << config.usr_db << "@" << config.inst_db);

	try {
		conn = env->createConnection(config.usr_db, config.pwd_db, config.inst_db);
	}
	catch (oracle::occi::SQLException &ex) {
		LOG_INFO("SQLException: " << ex.what());
		throw XDatabaseConnect;
	}

	LOG_INFO("Conectado com sucesso!");
}


void CValidarLinhaCPF::dbDisconnect()
{
	if (env && conn) {
		LOG_INFO("Encerrando conexao " << config.usr_db << "@" << config.inst_db);
		try {
			env->terminateConnection(conn);
		}
		catch (oracle::occi::SQLException &ex) {
			LOG_INFO("SQLException: " << ex.what());
			throw XDatabaseDisconnect;
		}
		conn = 0;
	}
}

void CValidarLinhaCPF::run()
{LOG_FUNC

	CLock lock(config.path_tmp);
	dbConnect();

	getDataFiles();
	if ( !data_files.size() ) {
		LOG_INFO(">>> NAO HA ARQUIVOS PARA LER EM " << config.path << " <<<");
		return;
	}

	readDataFiles();
	dbDisconnect();
}

void CValidarLinhaCPF::getDataFiles()
{
	DIR* pathdir = opendir(config.path.c_str());
	if ( !pathdir ) {
		LOG_ERR("opendir: " << config.path << " - " << strerror(errno));
		throw XPathDir;
	}

	std::string ext(DATAFILE_EXT);
	std::transform( ext.begin(), ext.end(), ext.begin(), ::toupper );

	struct dirent* pdir;
	while ( (pdir = readdir(pathdir)) ) {
		std::string ent(pdir->d_name);
		std::transform( ent.begin(), ent.end(), ent.begin(), ::toupper );

		size_t found = ent.rfind(ext);
		if ( found != std::string::npos && (ent.size() - int(found)) == ext.size() )
		{
			if ( !isearch(pdir->d_name, STR_CONSULTA))
				continue;

			std::string file = config.path + DIR_SEP + pdir->d_name;
			struct stat stfile;
			if ( stat(file.c_str(), &stfile) ) {
				LOG_ERR("stat: " << file << " - " << strerror(errno));
				throw XStatFile;
			}

			data_files.push_back( TpairDataFile(stfile.st_mtime, file) );
		}
	}
	closedir( pathdir );

	std::sort(data_files.begin(), data_files.end());
}

void CValidarLinhaCPF::readDataFiles()
{
	LOG_FUNC

	while( !data_files.empty() ) {
		std::string& datafile = data_files.back().second;
		std::string datafilename = basename((char*)datafile.c_str());

		std::string prcfilename = datafilename;
		ireplace(prcfilename, DATAFILE_EXT, PRCFILE_EXT);
		std::string prcfile = config.path + DIR_SEP + prcfilename;

		LOG_INFO("movefile: " << datafile << " -> " << prcfile);
		movefile(datafile, prcfile);

		LOG_DEBUG("ifstream: " << prcfile);
		std::ifstream infile(prcfile.c_str());
		if (infile.fail()) {
			LOG_ERR("ifstream: " << prcfile << " - " << strerror(errno));
			data_files.pop_back();
			continue;
		}

		std::string outfilename = datafilename;
		ireplace(outfilename, STR_CONSULTA, STR_RETORNO);
		std::string tmpoutfile = config.path_tmp + DIR_SEP + outfilename;

		std::ofstream ofsOutFile;
		TmapHeader mapHeader;
		std::string buff;
		int32_t lines=0;
		int32_t badlines=0;
		int32_t wrtlines=0;

		while( infile.good() )
		{
			std::getline(infile, buff);

			if (buff.empty() && infile.eof())
				break;

			if (!buff.empty() && *buff.rbegin() == '\r')
				buff.resize(buff.size() - 1);

			lines++;
			if (logLevel() & LDEBUG) LOG_DEBUG("line[" << lines << "]: buff[" << buff << "]");
			//if (buff.empty()) continue;

			std::istringstream line(buff);
			std::string token;
			TvecLine vecLine;

			while (std::getline(line, token, LINE_SEP_CHR))
				vecLine.push_back(token);

			// HEADER
			if ( mapHeader.empty() )
			{
				if ( !ofsOutFile.is_open() ) {
					LOG_DEBUG("ofstream: " << tmpoutfile);
					ofsOutFile.open(tmpoutfile.c_str());
					if (ofsOutFile.fail()) {
						LOG_ERR("ofstream: " << tmpoutfile << " - " << strerror(errno));
						throw XCreateFile;
					}
				}

				for (int i=0; i < vecLine.size(); i++)
					mapHeader[vecLine.at(i)] = i;

				if (!mapHeader.count("MIN") || !mapHeader.count("NR_CPF")) {
					LOG_DEBUG(prcfilename << ": header invalido (MIN e/ou NR_CPF nao encontrado)");

					if (lines == 1)
						ofsOutFile << line.str() << LINE_SEP_CHR << std::endl;
					else
						ofsOutFile << line.str() << LINE_SEP_CHR << "1" << std::endl;

					mapHeader.clear();
					continue;
				}

				ofsOutFile << line.str() << LINE_SEP_CHR << "CODCRM" << std::endl;
				wrtlines++;
				continue;
			}

			if (vecLine.size() < 2 || (vecLine.at(0).empty() || vecLine.at(1).empty())) {
				LOG_ERR(prcfilename << ": linha[" << lines << "] invalida!");
				ofsOutFile << line.str() << LINE_SEP_CHR << "1" <<std::endl;
				badlines++;
				continue;
			}

			std::string& min = vecLine.at(mapHeader["MIN"]);
			std::string& nr_cpf = vecLine.at(mapHeader["NR_CPF"]);
			std::string codcrm;

			try {
				oracle::occi::Number cdarearegistro(0);
				cdarearegistro.fromText(env, min.substr(0, 2), "99");

				oracle::occi::Number nrlinha(0);
				nrlinha.fromText(env, min.substr(2), "9999999999");

				if (logLevel() & LDEBUG) {
					LOG_DEBUG("cdarearegistro: [" << static_cast<int64_t>(cdarearegistro) << "]");
					LOG_DEBUG("nrlinha: [" << static_cast<int64_t>(nrlinha) << "]");
					LOG_DEBUG("nrdocumento: [" << nr_cpf << "]");
				}

				std::ostringstream sql;
				sql << "SELECT ";
				sql << "  count(1) ";
				sql << "FROM ";
				sql << "  linha.linhabase linhabase, ";
				sql << "  APOIO.AREAREGISTRO AREAREGISTRO, ";
				sql << "  APOIO.ESTADOLINHA ESTADOLINHA, ";
				sql << "  linha.linhatelefonica linhatelefonica, ";
				sql << "  customer.documento documento, ";
				sql << "  apoio.tipodocumento tipodocumento, ";
				sql << "  customer.pessoadocumento pessoadocumento, ";
				sql << "  CUSTOMER.PESSOADEPARA PESSOADEPARA, ";
				sql << "  CUSTOMER.PESSOALINHA PESSOALINHA, ";
				sql << "  CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO ";
				sql << "WHERE ";
				sql << "  linhabase.idarearegistro = AREAREGISTRO.idarearegistro ";
				sql << "AND linhabase.idestadolinha = ESTADOLINHA.idestadolinha ";
				sql << "and estadolinha.sgclassificacao = 'A' ";
				sql << "and estadolinha.inlinhacancelada = 0 ";
				sql << "AND arearegistro.cdarearegistro = :cdarearegistro ";
				sql << "AND linhabase.nrlinha = :nrlinha ";
				sql << "and linhabase.idlinhabase = linhatelefonica.idlinhabase ";
				sql << "and linhatelefonica.idlinhatelefonica = pessoalinha.idlinhatelefonica ";
				sql << "and documento.idtipodocumento = tipodocumento.idtipodocumento ";
				sql << "and documento.nrdocumento = :nrdocumento ";
				sql << "AND tipodocumento.sgclassificacao = 'CPF' ";
				sql << "AND documento.iddocumento = pessoadocumento.iddocumento ";
				sql << "AND pessoadocumento.idpessoa = pessoadepara.idpessoa ";
				sql << "AND pessoadepara.idpessoadepara = pessoalinha.idpessoadepara ";
				sql << "AND pessoalinha.idtiporelacionamento = tiporelacionamento.idtiporelacionamento ";
				sql << "AND tiporelacionamento.sgtiporelacionamento = 'C' ";

				oracle::occi::Statement* stmt = conn->createStatement(sql.str());
				uint16_t paramIndex=0;

				stmt->setNumber(++paramIndex, cdarearegistro);
				stmt->setNumber(++paramIndex, nrlinha);
				stmt->setString(++paramIndex, nr_cpf);

				if (logLevel() & LDEBUG) LOG_DEBUG("executeQuery: [" << sql.str() << "]");
				oracle::occi::ResultSet* rs = stmt->executeQuery();

				if ( rs->next() ) {
					paramIndex=0;
					int32_t count = rs->getInt(++paramIndex);
					codcrm = (count > 0)? "0": "1";
				}
				if (logLevel() & LDEBUG) LOG_DEBUG("CODCRM: [" << codcrm << "]");

				stmt->closeResultSet( rs );
				conn->terminateStatement(stmt);

				for (int i=0; i < vecLine.size(); i++) ofsOutFile << vecLine.at(i) << LINE_SEP_CHR;
				ofsOutFile << codcrm << std::endl;
				wrtlines++;
			}
			catch(oracle::occi::SQLException &e) {
				switch(e.getErrorCode()) {
				case 1722:  // ORA-01722: invalid number
				case 22062: // ORA-22062: invalid input string [%s]
				case 32152: // ORA-32152: Cannot perform operation on a null number
					LOG_ERR(prcfilename << ": linha[" << lines << "] invalida!");
					LOG_ERR("SQLException: code=[" << e.getErrorCode() << "], message=[" << e.getMessage() << "]");
					ofsOutFile << line.str() << LINE_SEP_CHR << "1" << std::endl;
					badlines++;
					break;
				default:
					throw;
				}
			}
		}
		if (infile.is_open())
			infile.close();

		if (lines)
			LOG_DEBUG(prcfilename << ": ReadLines[" << lines << "]")

		if (badlines)
			LOG_DEBUG(prcfilename << ": BadLines[" << badlines << "]")

		if (ofsOutFile.is_open()) {
			ofsOutFile.close();
			LOG_DEBUG(outfilename << ": WriteLines[" << wrtlines << "]")

			std::string envoutfile = config.path_env + DIR_SEP + outfilename;
			LOG_INFO("movefile: " << tmpoutfile << " -> " << envoutfile);
			movefile(tmpoutfile, envoutfile);

			LOG_INFO("delfile: " << prcfile);
			delfile(prcfile);
		}
		else {
			LOG_INFO(">>> ARQUIVO " << datafilename << " NAO CONTEM DADOS PARA LEITURA <<<");
		}

		data_files.pop_back();
	}
}

} // Namespace


