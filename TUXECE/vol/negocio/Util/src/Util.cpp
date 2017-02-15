#include <ctype.h>
#include <string.h>
#include <math.h>
#include <tuxfw/tuxfw.h>
#include <Util/Util.hpp>

#define VAL_ATIVAR         "ATIVAR"
#define VAL_DESATIVAR      "DESATIVAR"
#define VAL_REINICIALIZAR  "REINICIALIZAR"
#define VAL_INCLUIR        "INCLUIR"
#define VAL_REMOVER        "REMOVER"
#define VAL_EXCLUIR        "EXCLUIR"

CUtil::CUtil(){};

CUtil::~CUtil(){};

char* CUtil::rtrim(char *pStr)
{
	int rInd;

	if (pStr == NULL)
			return NULL;

   rInd = strlen(pStr) - 1;
   while ( isspace(pStr[rInd]) && rInd >= 0){
      rInd--;
   }

   pStr[rInd + 1] = '\0';
   
   return pStr;
}

char* CUtil::ltrim(char *pStr)
{
	int lInd = 0;

	if (pStr == NULL)
			return NULL;

    //rInd = strlen(pStr) - 1;

    while (isspace(pStr[lInd])){
        lInd++;
	}

	pStr = pStr + lInd;
     
   return pStr;
}

char* CUtil::trim(char *pStr){
	return ltrim(rtrim(pStr));		
}



char* CUtil::upper(char *pTok)
{ 
	char* p = pTok;

    while(*p != '\0'){
       *p = toupper(*p);

		switch(*p)
		{
			case 'á': *p = 'Á'; break;
			case 'à': *p = 'À'; break;
			case 'ã': *p = 'Ã'; break;
			case 'â': *p = 'Â'; break;
			case 'é': *p = 'É'; break;
			case 'ê': *p = 'Ê'; break;
			case 'ë': *p = 'Ë'; break;
			case 'í': *p = 'Í'; break;
			case 'ó': *p = 'Ó'; break;
			case 'ô': *p = 'Ô'; break;
			case 'ü': *p = 'Ü'; break;
			case 'ú': *p = 'Ú'; break;
			case 'ç': *p = 'Ç'; break;
		}


	   p++;
	}
	return pTok;
}

long CUtil::pot(int base, int potencia) {

	long returnValue = 1;

	if (base == 0)
		return 1;

	if (potencia > 0)
		for (int i=0;i < potencia; i++)
			returnValue *= base;
	else
		for (int i=0; i < (potencia*(-1)); i++)
			returnValue /= base;

	return returnValue;
}

unsigned long CUtil::HexaToLong(char *hex) {

	char cCaracter;
	int iValorDec;
	unsigned long dValorFinal = 0;
	int iSz = strlen(hex);

	for (unsigned int i=iSz; i > 0; i--) {
		cCaracter = hex[i-1];

		switch ((int)cCaracter){
			case 97: case 65: iValorDec = 10; break;
			case 98: case 66: iValorDec = 11; break;
			case 99: case 67: iValorDec = 12; break;
			case 100: case 68: iValorDec = 13; break;
			case 101: case 69: iValorDec = 14; break;
			case 102: case 70: iValorDec = 15; break;
			default: iValorDec = (int)cCaracter - 48;
		}

		dValorFinal += iValorDec*((long)pot(16,(iSz-i)));
	}

	return dValorFinal;
}

/************************************************************************
 * Funcao: Verifica se em um buffer contem somente caracter numerico.   *
 * Parametro: d = dado para realizar a verificacao.                     *
 * Retorno: 0 = contem caracter diferente de numerico                   *
 *          1 = contem somente caracter numerico                        *
 ************************************************************************/
int CUtil::IsNumeric(char* d){
    for(; *d; d++ )
       if ( !isdigit(*d) )
            return 0;
    return 1;
}

char *CUtil::converteFolha(char *pc_out, char *pc_in)
{
	int  i;
	int  i_pos      = 0;
	int  i_inLen    = 0;
	char c_in[1024] = "";

	if(strlen(pc_in) < sizeof(c_in))
		strcpy(c_in, pc_in);
	else
		strcpy(c_in, &pc_in[ (strlen(pc_in) - sizeof(c_in) + 1) ]);

	CUtil::upper(c_in);

	i_inLen = strlen(pc_in);

	if( strcmp(&c_in[i_inLen - strlen(VAL_DESATIVAR)], VAL_DESATIVAR) == 0 )
	{
		strcpy(pc_out, "Desativar ");
	}
	else if( strcmp(&c_in[i_inLen - strlen(VAL_ATIVAR)], VAL_ATIVAR) == 0 )
	{
		strcpy(pc_out, "Ativar ");
	}
	else if( strcmp(&c_in[i_inLen - strlen(VAL_REINICIALIZAR)], VAL_REINICIALIZAR) == 0 )
	{
		strcpy(pc_out, "Reinicializar ");
	}
	else if( strcmp(&c_in[i_inLen - strlen(VAL_INCLUIR)], VAL_INCLUIR) == 0 )
	{
		strcpy(pc_out, "Incluir ");
	}
	else if( strcmp(&c_in[i_inLen - strlen(VAL_REMOVER)], VAL_REMOVER) == 0 )
	{
		strcpy(pc_out, "Remover ");
	}
	else if( strcmp(&c_in[i_inLen - strlen(VAL_EXCLUIR)], VAL_EXCLUIR) == 0 )
	{
		strcpy(pc_out, "Excluir ");
	}
	else
	{
		i_pos = 0;

		for(i = (i_inLen - 1); i >= 0; i--)
		{
			if(pc_in[i] == '/')
			{
				i_pos = i + 1;				
				break;
			}
		}

		strcpy(pc_out, &pc_in[i_pos]);

		// após removerem a árvore de canais eletrônicos
		CUtil::upper(pc_out);

		return pc_out;
	}

	int i_found  = 0;
	i_pos        = 0;

	for(i = (i_inLen - 1); i >= 0; i--)
	{
		if(pc_in[i] == '/')
		{
			if(i_found == 0)
			{
				pc_in[i] = '\0';
				i_found++;
			}
			else
			{
				i_pos = i + 1;
				break;
			}
		}
	}

	strcat(pc_out, &pc_in[i_pos]);

	// após removerem a árvore de canais eletrônicos
	CUtil::upper(pc_out);

	return pc_out;
}

//-----------------------------------------------------------------------

CStatusCode::CStatusCode()
{
	this->setCod("");
	this->setMsg("");
}


CStatusCode::~CStatusCode()
{

}

char* CStatusCode::getCod()
{
	return this->m_cCod;
}

char* CStatusCode::getMsg()
{
	return this->m_cMsg;
}

void CStatusCode::setCod(char* value)
{
	strcpy(m_cCod, value);
}

void CStatusCode::setMsg(char* value)
{
	strcpy(m_cMsg, value);
}

//-----------------------------------------------------------------------

CXmlHelper::CXmlHelper()
{
	// inicializando ambiente XML
	try
    {
         XMLPlatformUtils::Initialize();
    }
    catch (...)
    {
		 parser     = NULL;
		 memBufIS   = NULL;
		 isXercesOk = false;
         return;
    }
	
	parser = new XercesDOMParser();

	parser->setValidationScheme(XercesDOMParser::Val_Never);
	parser->setCreateEntityReferenceNodes(false);
		
	memBufIS = NULL;

	isXercesOk = true;

}

CXmlHelper::~CXmlHelper()
{
	if(parser)
		delete parser;

	if(memBufIS)
		delete memBufIS;

	XMLPlatformUtils::Terminate();
}

XercesDOMParser & CXmlHelper::getParser()
{
	return *parser;
}

void CXmlHelper::setXml(char *value)
{
	if(value == NULL || strlen(value) <= 0 || !this->isXercesOk)
		return;

	if(memBufIS)
		delete memBufIS;

	memBufIS = new MemBufInputSource
					(
						(const XMLByte*) value, 
						strlen(value),
						"util"
					);
}

int CXmlHelper::parse()
{
	int errorCount = -1;
	
	try
	{
		parser->parse(*memBufIS);
		errorCount = parser->getErrorCount();			
	}
	catch(...)
	{
		return 1;
	}

	if(!errorCount) 
	{
		return 0;
	}

	return 2;
}