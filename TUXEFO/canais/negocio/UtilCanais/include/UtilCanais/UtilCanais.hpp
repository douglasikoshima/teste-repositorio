#ifndef FUNCOES
#define FUNCOES

#include <tuxfw.h>

#define ASSERT_STR(value, tag) \
		{	char cTag[64]; \
			sprintf(cTag, "TAG_%s_", tag); \
			if (value == NULL) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "INEXISTENTE")); \
			if (!*value) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "VALOR_VAZIO")); \
		}

#define ASSERT_INT(output, value, tag) \
		{	char cTag[64]; \
			sprintf(cTag, "TAG_%s_", tag); \
			ASSERT_STR(value, tag) \
			if ((output = atoi(value)) < 0) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "VALOR_INVALIDO")); \
		}






class CUtilCanais
{
    CUtilCanais();
    virtual ~CUtilCanais();

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
	virtual ~CStatusCode();

	char* getCod();
	char* getMsg();

	void setCod(char* value);
	void setMsg(char* value);

private:
	char  m_cCod[32];
	char  m_cMsg[256];
};

#endif
