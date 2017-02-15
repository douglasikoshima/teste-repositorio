/*
 * CValidarLinhaCPF.h
 *
 *  Created on: 12/05/2013
 *      Author: Jones Randis
 */

#ifndef CVALIDARLINHACPF_H_
#define CVALIDARLINHACPF_H_

#include <exception>
#include <iostream>
#include <ostream>
#include <sstream>
#include <string>
#include <memory>
#include <vector>
#include <map>
#include <cstdint>
#include <cstdlib>
#include <ctime>

#include <occi.h>
#include <occiCommon.h>

#include "CConfig.h"

namespace batch {

typedef std::pair<int32_t, std::string> TpairDataFile;
typedef std::vector<TpairDataFile> TvecDataFiles;
typedef std::vector<std::string> TvecLine;
typedef std::map<std::string, int32_t> TmapHeader;

class CValidarLinhaCPF {
public:
	CValidarLinhaCPF(std::string& configFile);
	virtual ~CValidarLinhaCPF();
	void run();
	static int main(int argC, char **argV);

protected:
	CConfig config;
	oracle::occi::Environment *env;
	oracle::occi::Connection *conn;
	TvecDataFiles data_files;

	void dbConnect();
	void dbDisconnect();
	void getDataFiles();
	void readDataFiles();
};

} // Namespace

#endif /* CVALIDARLINHACPF_H_ */
