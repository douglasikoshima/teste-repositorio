#ifndef FUNCOES
#define FUNCOES

#include <tuxfw/tuxfw.h>

class CUtil
{
    CUtil();
    virtual ~CUtil();

public:

	static char* upper(char *);
	static char* rtrim(char *);
	static char* ltrim(char *);
	static char* trim(char *);
	static unsigned long HexaToLong(char*);
	static long pot(int, int);
	static int IsNumeric(char*);
	static char *converteFolha(char *pc_out, char *pc_in);
};

class CStatusCode
{
public:
	CStatusCode();
	virtual ~CStatusCode();

	char* getCod();
	char* getMsg();

	void setCod(char* value);
	void setMsg(char* value);

private:
	char  m_cCod[32];
	char  m_cMsg[256];
};

class CXmlHelper
{

public:

	CXmlHelper();
	virtual ~CXmlHelper();
	
	XercesDOMParser &getParser();

	void setXml(char *value);

	int  parse();	

private:

	XercesDOMParser*   parser;
	MemBufInputSource* memBufIS;

	bool isXercesOk;

};

class CStr
{

public:

	CStr() { memset(cValue, 0, sizeof(cValue)); }
	CStr(char *value) { strcpy(cValue, value); }

	char *getValue(char *value) { strcpy(value, cValue); return value; }

	char setValue(char *value)  { strcpy(cValue, value); }

private:
	char cValue[256];

};

#endif
