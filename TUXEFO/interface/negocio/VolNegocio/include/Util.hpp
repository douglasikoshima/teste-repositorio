#ifndef FUNCOES
#define FUNCOES


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
};

class CStatusCode
{
public:
	CStatusCode();
	~CStatusCode();

	char* getCod();
	char* getMsg();

	void setCod(char* value);
	void setMsg(char* value);

private:
	char  m_cCod[32];
	char  m_cMsg[256];
};

#endif
