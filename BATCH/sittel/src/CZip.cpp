/*
 * CZip.cpp
 *
 *  Created on: 03/07/2013
 *      Author: Jones Randis
 */

#include "garbage_collector.h"

#include "CZip.h"
#include "CLog.h"
#include "CException.h"

namespace batch {

CZip::CZip(std::string& zipfile)
: zipfile(zipfile), zipp(0), err(0)
{
	//create();
}

CZip::~CZip() {
	close();
}

void CZip::create()
{LOG_FUNC

	LOG_INFO("zip_open: " << basename( const_cast<char*>(zipfile.c_str()) ))

	zipp = zip_open(zipfile.c_str(), ZIP_CREATE | ZIP_TRUNCATE, &err);
	if (err) {
		char errbuff[256]={0};
		zip_error_to_str(errbuff, sizeof(errbuff), err, errno);
		LOG_ERR("zip_open: " << zipfile << " - " << errbuff)
		throw XCreateFile;
	}
}

void CZip::add(std::string& file)
{
	struct zip_source* sfile = zip_source_file(zipp, file.c_str(), 0, 0);
	if (!sfile) {
		LOG_ERR("zip_source_file: " << file << " - " << zip_strerror(zipp))
		close();
		throw XReadFile;
	}

	zip_int64_t idx = zip_file_add(zipp, file.c_str(), sfile, ZIP_FL_ENC_GUESS);
	if (idx == -1) {
		LOG_ERR("zip_file_add: " << file << " - " << zip_strerror(zipp))
		zip_source_free(sfile);
		close();
		throw XReadFile;
	}

	//zip_source_free(sfile);
}

void CZip::add(std::string& file, const void *data, zip_uint64_t len)
{
	struct zip_source* sbuff = zip_source_buffer(zipp, data, len, 0);
	if (!sbuff) {
		LOG_ERR("zip_source_buffer: " << zip_strerror(zipp))
		close();
		throw XReadBuffer;
	}

	zip_int64_t idx = zip_file_add(zipp, file.c_str(), sbuff, ZIP_FL_ENC_GUESS);
	if (idx == -1) {
		LOG_ERR("zip_file_add: " << file << " - " << zip_strerror(zipp))
		zip_source_free(sbuff);
		close();
		throw XReadFile;
	}

	//zip_source_free(sbuff);
}

void CZip::close()
{
	if ( zipp ) {
		if (zip_close(zipp) == -1) {
			LOG_ERR("zip_close: " << zipfile << " - " << zip_strerror(zipp))
			throw XCloseFile;
		}
		zipp=0;
	}
}

} /* namespace batch */
