/*
 * CConfig.cpp
 *
 *  Created on: 15/06/2013
 *      Author: Jones Randis
 */

#include "CConfig.h"
#include "CLog.h"
#include "CException.h"

#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>
#include <cstdlib>

namespace batch {

CConfig::CConfig(std::string& file)
: _config(file), zip_xml(false)
{

}

CConfig::~CConfig() {

}

void CConfig::load() {LOG_FUNC

	LOG_INFO("Obtendo parametros de configuracao: " << _config)

	std::ifstream file(_config.c_str());
	if (file.fail()) {
		LOG_ERR("ifstream: " << _config << " - " << strerror(errno))
		throw XConfigFile;
	}

	std::string buff;
	while( file.good() )
	{
		std::getline(file, buff);

		if (buff.empty() && file.eof())
			break;

		if (!buff.empty() && *buff.rbegin() == '\r')
				buff.resize(buff.size() - 1);

		std::istringstream line(buff);
		std::string key, val;
		std::getline(line, key, '=');
		std::getline(line, val);

		if (!key.compare("usr_db"))
			usr_db = val;

		if (!key.compare("pwd_db"))
			pwd_db = val;

		if (!key.compare("inst_db"))
			inst_db = val;

		if (!key.compare("path"))
			path = val;

		if (!key.compare("path_tmp"))
			path_tmp = val;

		if (!key.compare("path_xsd"))
			path_xsd = val;

		if (!key.compare("log_level"))
			log_level = val;

		if (!key.compare("zip_xml")) {
			std::transform(val.begin(), val.end(), val.begin(), ::toupper);
			if (val == "TRUE" || val == "1")
				zip_xml = true;
			else
				zip_xml = false;
		}
	}
	file.close();

	if (path.empty() || path_tmp.empty() || path_xsd.empty()) {
		throw XConfigParam;
	}

	/*
	if (usr_db.empty() || pwd_db.empty()) {
		throw XParamDatabase;
	}

	if (inst_db.empty() && !getenv("TWO_TASK") && !getenv("ORACLE_SID")) {
		throw XParamDatabase;
	}
	*/
}

} /* namespace batch */
