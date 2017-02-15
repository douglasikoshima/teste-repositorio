/*
 * CSoapException.h
 *
 *  Created on: 11/02/2014
 *      Author: Jones Randis
 */

#ifndef CSOAPEXCEPTION_H_
#define CSOAPEXCEPTION_H_

#include <sstream>
#include <string>
#include <map>
#include <xmlsoap.hpp>

extern "C" {
#include <xmlerr.h>
}


namespace batch {

class CSoapException : public OracleXml::Soap::SoapException {
public:
	CSoapException();
	virtual ~CSoapException() throw();

	typedef std::map<xmlerr, std::string> TmapXmlErr;
	TmapXmlErr mapXmlErr;

	virtual std::string& getMessageCode(xmlerr ecode) throw();
};

} /* namespace batch */
#endif /* CSOAPEXCEPTION_H_ */
