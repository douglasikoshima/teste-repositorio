#ifndef XMLPARSEEXCEPTION_H_HEADER_INCLUDED_BD906543
#define XMLPARSEEXCEPTION_H_HEADER_INCLUDED_BD906543

#include "Constants.h"

class XMLParseException
{
public:
	XMLParseException(char*msg);
	~XMLParseException();
	char*getCause();
public:
	char *m_msg;
};



#endif /* XMLPARSEEXCEPTION_H_HEADER_INCLUDED_BD906543 */
