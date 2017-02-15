/*
 * CConfig.h
 *
 *  Created on: 15/06/2013
 *      Author: Jones Randis
 */

#ifndef CCONFIG_H_
#define CCONFIG_H_

#include <string>

namespace batch {

class CConfig {
protected:
	std::string _config;

public:
	std::string usr_db;
	std::string pwd_db;
	std::string inst_db;
	std::string path;
	std::string path_tmp;
	std::string path_xsd;
	std::string log_level;
	bool zip_xml;

	CConfig(std::string& file);
	virtual ~CConfig();

	void verify();
	void load();
};

} /* namespace batch */
#endif /* CCONFIG_H_ */
