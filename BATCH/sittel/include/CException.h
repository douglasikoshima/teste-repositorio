/*
 * CException.h
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#ifndef CEXCEPTION_H_
#define CEXCEPTION_H_

#include <string>
#include <exception>

namespace batch {

class CException : public std::exception
{
public:
	CException();
	CException(std::string emsg);
	virtual const char* what() const throw();
	virtual ~CException() throw();
protected:
	std::string emsg;
};

extern CException XConfigFile;
extern CException XConfigParam;
extern CException XParamDatabase;
extern CException XDatabaseConnect;
extern CException XDatabaseDisconnect;
extern CException XDatabaseSqlError;
extern CException XTipoSolicitacao;
extern CException XXmlParserError;
extern CException XXmlSchemaError;
extern CException XXmlCreateDocument;
extern CException XXmlCreateElement;
extern CException XCreateFile;
extern CException XCloseFile;
extern CException XReadFile;
extern CException XMoveFile;
extern CException XDelFile;
extern CException XChangeDir;
extern CException XCurrentDir;
extern CException XAlreadyRunning;
extern CException XSoapCall;
extern CException XLoadParam;
extern CException XReadBuffer;

} /* namespace batch */
#endif /* CEXCEPTION_H_ */
