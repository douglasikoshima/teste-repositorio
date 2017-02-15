/*
 * CLog.h
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#ifndef CLOG_H_
#define CLOG_H_

#include <iostream>
#include <ostream>
#include <string>

#define LOG_FUNC log(LDEBUG) << "[" << __func__ << "]" << std::endl;
#define LOG_BEGIN log(LINFO) << ">>>" << __func__ << std::endl;
#define LOG_END log(LINFO) << "<<<" << __func__ << std::endl;

#define LOG_INFO(MSG) log(LINFO) << MSG << std::endl;
#define LOG_DEBUG(MSG) log(LDEBUG) << MSG << std::endl;
#define LOG_ERR(MSG) log(LERR) << MSG << std::endl;

namespace batch {

enum ELogLevel {
	LNONE = 1 << 0,
	LERR = 1 << 1,
	LINFO = 1 << 2,
	LDEBUG = 1 << 3
};

short logLevel();
void logLevel(short level);
void logLevel(std::string& level);
char* now(const std::string& fmt);
std::ostream& log(short level = LINFO);

} /* namespace batch */
#endif /* CLOG_H_ */
