/*
 * CUtil.h
 *
 *  Created on: 03/07/2013
 *      Author: Jones Randis
 */

#ifndef CUTIL_H_
#define CUTIL_H_

#include <string>

namespace batch {

void movefile(const std::string& from, const std::string& to);
void delfile(const std::string& file);
bool replace(std::string& str, const std::string& from, const std::string& to);
bool ireplace(std::string& str, const std::string& from, const std::string& to);
bool isearch(std::string str, const std::string& what);
std::string& trim(std::string& str);

} /* namespace batch */
#endif /* CUTIL_H_ */
