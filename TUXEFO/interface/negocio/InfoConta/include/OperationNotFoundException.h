#ifndef OPERATIONNOTFOUNDEXCEPTION_H_HEADER_INCLUDED_BD907316
#define OPERATIONNOTFOUNDEXCEPTION_H_HEADER_INCLUDED_BD907316

class OperationNotFoundException
{
public:
	OperationNotFoundException(char*msg);
	~OperationNotFoundException();
	char*getCause();
public:
	char *m_msg;
};



#endif /* OPERATIONNOTFOUNDEXCEPTION_H_HEADER_INCLUDED_BD907316 */
