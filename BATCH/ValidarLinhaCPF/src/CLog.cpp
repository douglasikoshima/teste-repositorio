/*
 * CLog.cpp
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#include "CLog.h"
#include "config.h"

#include <iostream>
#include <algorithm>
#include <ctime>
#include <cctype>

namespace batch {

static short _logLevel = LERR | LINFO | LDEBUG;

short logLevel() {
	return _logLevel;
}

void logLevel(short level) {
	if (level)
		_logLevel=level;
}

void logLevel(std::string& level) {
	if ( !level.empty() ) {
		std::string lvl( level );
		std::transform(level.begin(), level.end(), lvl.begin(), ::toupper);

		if (lvl == "NONE")
			_logLevel = LNONE;

		if (lvl == "ERR")
			_logLevel = LERR;

		if (lvl == "INFO")
			_logLevel = LINFO | LERR;

		if (lvl == "DEBUG")
			_logLevel = LINFO | LERR | LDEBUG;
	}
}

char* now(const std::string& fmt) {
	time_t t = time(0);
	struct tm* ptm = localtime( &t );
	static char buff[64];
	strftime(buff, sizeof(buff), fmt.c_str(), ptm);
	return buff;
}

std::ostream& log(short level) {
	if (_logLevel & level)
		return (std::cout << now(LOG_TIME_FORMAT) << (level & LERR? " -- [ERR] ": " -- "));

	static std::ostream osnull(0);
	return osnull;
}

} /* namespace batch */
