/*
 * CZip.h
 *
 *  Created on: 03/07/2013
 *      Author: Jones Randis
 */

#ifndef CZIP_H_
#define CZIP_H_

#include <string>
#include <cstdio>
#include <cerrno>

extern "C" {
#include <zip.h>
#include <libgen.h>
}

namespace batch {

class CZip {
protected:
	std::string& zipfile;
	struct zip* zipp;
	int err;

public:
	CZip(std::string& zipfile);
	virtual ~CZip();

	void create();
	void add(std::string& file);
	void add(std::string& file, const void *data, zip_uint64_t len);
	void close();
};

} /* namespace batch */
#endif /* CZIP_H_ */
