#include <ctype.h>
#include <string.h>
#include <math.h>
#include <tuxfw.h>
#include "UtilCanais/UtilCanais.hpp"


CUtilCanais::CUtilCanais(){};

CUtilCanais::~CUtilCanais(){};

char* CUtilCanais::rtrim(char *pStr)
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

char* CUtilCanais::ltrim(char *pStr)
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

char* CUtilCanais::trim(char *pStr){
	return ltrim(rtrim(pStr));		
}



char* CUtilCanais::upper(char *pTok)
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

long CUtilCanais::pot(int base, int potencia) {

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

unsigned long CUtilCanais::HexaToLong(char *hex) {

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
int CUtilCanais::IsNumeric(char* d){
    for(; *d; d++ )
       if ( !isdigit(*d) )
            return 0;
    return 1;
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

