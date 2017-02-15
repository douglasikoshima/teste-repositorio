/*
 * CUtil.cpp
 *
 *  Created on: 03/07/2013
 *      Author: Jones Randis
 */

#include "CUtil.h"
#include "CLog.h"
#include "CException.h"

#include <algorithm>
#include <functional>
#include <sstream>
#include <locale>
#include <cctype>
#include <cstdio>
#include <cstddef>
#include <cstring>
#include <cerrno>

namespace batch {

void movefile(const std::string& from, const std::string& to)
{
	if ( rename(from.c_str(), to.c_str()) != 0 ) {
		LOG_ERR("rename: " << from << " -> " << to << " - " << strerror(errno));
		throw XMoveFile;
	}
}

void delfile(const std::string& file)
{
	if ( remove(file.c_str()) != 0 ) {
		LOG_ERR("remove: " << file << " - " << strerror(errno));
		throw XDelFile;
	}
}

bool replace(std::string& str, const std::string& from, const std::string& to) {
    size_t start_pos = str.find(from);
    if(start_pos == std::string::npos)
        return false;
    str.replace(start_pos, from.length(), to);
    return true;
}

bool _cmp_upper (char l, char r) {
  return (std::toupper(l) == std::toupper(r));
}

bool ireplace(std::string& str, const std::string& from, const std::string& to)
{
	std::string::iterator pos = std::search(str.begin(), str.end(), from.begin(), from.end(), _cmp_upper);
	if (pos != str.end()) {
		str.replace(std::distance(str.begin(), pos), from.length(), to);
		return true;
	}
	return false;
}

bool isearch(std::string str, const std::string& what) {
	std::string::iterator pos = std::search(str.begin(), str.end(), what.begin(), what.end(), _cmp_upper);
	if (pos != str.end()) {
		return true;
	}
	return false;
}


// trim from start
std::string &ltrim(std::string &s) {
        s.erase(s.begin(), std::find_if(s.begin(), s.end(), std::not1(std::ptr_fun<int, int>(std::isspace))));
        return s;
}

// trim from end
std::string &rtrim(std::string &s) {
        s.erase(std::find_if(s.rbegin(), s.rend(), std::not1(std::ptr_fun<int, int>(std::isspace))).base(), s.end());
        return s;
}

// trim from both ends
std::string &trim(std::string &s) {
        return ltrim(rtrim(s));
}


std::string& xmlescapechars(std::string &s) {
	std::stringstream d;
	std::string::iterator it;

	for (it = s.begin(); it != s.end(); it++) {
		switch (*it) {
			//case '&': d << "&amp;"; break; 	// xdk ja faz esse tratamento
			//case '<': d << "&lt;"; break;
			//case '>': d << "&gt;"; break;

			// ignora estes caracteres
			case '<':
			case '>':
				break;

			default: d << *it; break;
		}
	}

	s = d.str();
	return s;
}


} /* namespace batch */
