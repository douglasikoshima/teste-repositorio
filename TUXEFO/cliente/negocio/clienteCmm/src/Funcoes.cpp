#include <ctype.h>
#include <string.h>
#include <math.h>
#include <tuxfw.h>

char *rtrim(char *pStr)
{
    int rInd;

    if (pStr == NULL)
        return NULL;

    rInd = strlen(pStr) - 1;

    while ( isspace(pStr[rInd]) && rInd >= 0 )
        rInd--;

    pStr[rInd + 1] = '\0';

    return( pStr );
}


void upper(char *pTok)
{ 
    for(; *pTok != '\0' ;pTok++)
        *pTok = toupper(*pTok);
}

long pot(int base, int potencia)
{

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

unsigned long HexaToLong(char *hex)
{

	char cCaracter;
	int iValorDec;
	unsigned long dValorFinal = 0;
	int iSz = strlen(hex);

	for (unsigned int i=iSz; i > 0; i--)
    {
		cCaracter = hex[i-1];

		switch ((int)cCaracter)
        {
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
int IsNumeric(char* d)
{
    for(; *d; d++ )
    {
       if ( !isdigit(*d) )
       {
            return 0;
       }
    }

    return 1;
}

/***************************************************************************************/
void translateSpecialChars(char *pszInputValue)
{
    char szReturn[256 + 1];
    register int inputIndex;
    register int outputIndex;
    register int movement;
    char* psubst;
    int inputLen;

    inputLen = strlen(pszInputValue);
    psubst = szReturn;
    outputIndex = 0;


    /* loop for each input char and copy into substField */
    for ( inputIndex = 0 ; inputIndex < inputLen ; inputIndex++)
    {
        if ( pszInputValue[inputIndex] == '&' )
        {
            strcpy( psubst , "&amp;");
            movement = 5;
        }
        else if ( pszInputValue[inputIndex] == '>' )
        {
            strcpy(psubst ,"&gt;");
            movement = 4;
        }
        else if ( pszInputValue[inputIndex] == '<' )
        {
            strcpy(psubst ,"&lt;");
            movement = 4;
        }
        else if ( pszInputValue[inputIndex] == '\"' )
        {
            strcpy(psubst ,"&quot;");
            movement = 6;
        }
        else if ( pszInputValue[inputIndex] == '\'' )
        {
            strcpy(psubst ,"&apos;");
            movement = 6;
        }
        else {
            *psubst = pszInputValue[inputIndex];
            movement = 1;
        }

        /* Se a mensagem final for ficar maior que o buffer alocado para o processamento. */
        if(( movement + outputIndex ) > sizeof(szReturn)-1)
        {
            throw new TuxException("99E0017", "Error translating special chars, resulting field too long...");
            return;
        }
        else
        {
            outputIndex = movement + outputIndex;
            
            /* desloca o ponteiro o quanto necessário */
            for ( ; movement > 0; movement--)
            {
                psubst++;
            }
        
        }
    }
    
    /* Garante que a string termina com NULL */
    *psubst = '\0';

    strcpy(pszInputValue, szReturn);
}
