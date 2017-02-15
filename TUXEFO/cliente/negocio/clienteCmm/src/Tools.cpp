///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Tools
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <stdarg.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#include "../include/Exception.h"
#include <tuxfw.h>
#include "../include/Tools.h"

/***************************************************************************************/
void DuplicaCaracter(char *pStringIn, char *pStringOut, char cCaracter)
{
    register int iCont, iContAux;

    ULOG( "pStringIn[%s](%d)", pStringIn, strlen(pStringIn));
    ULOG( "cCaracter[%c]", cCaracter);

    for(iCont=iContAux=0; pStringIn[iCont]!=NULL; iCont++, iContAux++) {
        pStringOut[iContAux]=pStringIn[iCont];
        if(pStringOut[iContAux] == cCaracter)
            pStringOut[++iContAux] = cCaracter;
    }

    ULOG( "pStringOut[%s](%d)", pStringOut, strlen(pStringOut));

}

/***************************************************************************************/
void DesmembraNome(TDesmembraNome *ptDesmembraNome)
{
    struct TNomeAux {
        char szParteDoNome[LEN_NOME + LEN_EOS];
        struct TNomeAux *ptNomeNext;
    } *ptNomeAux=NULL, *ptNomeAtual=NULL, *ptNomeNovo=NULL;
    char szAux[LEN_NOME + LEN_EOS], *pszAux;

    strcpy(szAux, ptDesmembraNome->szNomeCompleto);

    NormalizaNome(szAux);

    ptDesmembraNome->szNomePrimeiro[0] = ptDesmembraNome->szNomeMeio[0] = ptDesmembraNome->szNomeFim[0] = 0x00;


    for(pszAux = strtok(szAux, " "); pszAux != NULL; pszAux = strtok(NULL, " "))
    {
        /* Aloca memoria para o fragmento da parte do nome */
        if((ptNomeNovo = (struct TNomeAux *)malloc(sizeof(struct TNomeAux))) == NULL)
            return; 

        /* Processo da lista encadeada */
        if(ptNomeAux == NULL)
            ptNomeAux = ptNomeNovo;
        else
        {
            ptNomeAtual = ptNomeAux;
            while(ptNomeAtual->ptNomeNext != NULL)
                ptNomeAtual=ptNomeAtual->ptNomeNext;

            ptNomeAtual->ptNomeNext = ptNomeNovo;
        }

        /* Copia a parte desmembrada do nome */
        strcpy(ptNomeNovo->szParteDoNome, pszAux);
        ptNomeNovo->ptNomeNext=NULL;

        ptNomeAtual=ptNomeNovo;
    }

    /* Copia o PrimeiroNome */
    ptNomeAtual = ptNomeAux;
    if(ptNomeAtual != NULL)
    {
        strcpy(ptDesmembraNome->szNomePrimeiro, ptNomeAtual->szParteDoNome);
        ptNomeAtual = ptNomeAtual->ptNomeNext;
    }

    /* Copia o(s) Nome(s) do Meio */
    while(ptNomeAtual != NULL && ptNomeAtual->ptNomeNext != NULL)
    {
        strcat(ptDesmembraNome->szNomeMeio, ptNomeAtual->szParteDoNome);

        ptNomeAtual = ptNomeAtual->ptNomeNext;

        if(ptNomeAtual->ptNomeNext != NULL)
            strcat(ptDesmembraNome->szNomeMeio, " ");
    }

    /* Copia o UltimoNome */
    if(ptNomeAtual != NULL)
        strcpy(ptDesmembraNome->szNomeFim, ptNomeAtual->szParteDoNome);

    /* Desaloca memoria da lista encadeada */
    ptNomeAtual = ptNomeAux;
    while(ptNomeAtual != NULL)
    {
        ptNomeAtual = ptNomeAux->ptNomeNext;
        free(ptNomeAux);
        ptNomeAux = ptNomeAtual;
    }
}

/***************************************************************************************/
void NormalizaNome(char *pszString)
{
    alltrim(pszString);
    RetiraMultiplosEspacos(pszString);
}

/***************************************************************************************/
void RetiraMultiplosEspacos(char *pszString)
{
    register unsigned int iPosOrigem, iPosDestino;
    unsigned int iLen;
    char *pszTmp;

    iLen=strlen(pszString);

    if((pszTmp = (char *)malloc(iLen + LEN_EOS)) == NULL)
        return;

    memset(pszTmp, 0x00, iLen + LEN_EOS);

    for(iPosOrigem=iPosDestino=0; iPosOrigem<iLen; iPosOrigem++)
        if(pszString[iPosOrigem] == 0x20 && pszString[iPosOrigem + 1] == 0x20)
            continue;
        else
            pszTmp[iPosDestino++] = pszString[iPosOrigem];

    strcpy(pszString, pszTmp);

    free(pszTmp);
}

/***************************************************************************************/
void alltrim(char *pszString)
{
    ltrim(pszString);
    rtrim(pszString);
}

/***************************************************************************************/
void ltrim(char *pszString)
{
    register unsigned int iPos;
    unsigned int iLen;
    char *pszTmp, *pszAlocado;

    iLen=strlen(pszString);

    if((pszAlocado = (char *)malloc(iLen + LEN_EOS)) == NULL)
        return; 

    strcpy(pszAlocado, pszString);

    pszTmp=pszAlocado;


    for(iPos=0; iPos < iLen; iPos++)
        if(pszString[iPos] == 0x20)
            pszTmp++;
        else
            break;

    strcpy(pszString, pszTmp);

    free(pszAlocado);
}

/***************************************************************************************/
void rtrim(char *pszString)
{
    register int iPos;

    for(iPos=strlen(pszString)-1; iPos >= 0; iPos--)
        if(pszString[iPos] == 0x20)
            pszString[iPos] = 0x00;
        else
            break;
}

/***************************************************************************************/
bool DataMenorDataAtual(char *pszData)
{
    char *pszDataAtual;

    ULOGI("Inicio de DataMenorDataAtual");
    ULOG( "pszData[%s]", pszData);

    if(strlen(pszData) == 8)             // formato de data YYYYMMDD
        pszDataAtual = DataAtual();
    else if(strlen(pszData) == 14)       // formato de data YYYYMMDDHHMMSS
        pszDataAtual = DataHoraAtual();
    else
    {
        ULOGI("Data de tamanho desconhecido[%s]", pszData);
        return false; 
    }

    if(strcmp(pszData, pszDataAtual) < 0)
    {
        ULOGI("Data Atual[%s] - Data Passada[%s] - <true>", pszDataAtual, pszData);
        return true;
    }
    else
    {
        ULOGI("Data Atual[%s] - Data Passada[%s] - <false>", pszDataAtual, pszData);
        return false;
    }
}

/***************************************************************************************/
char *TimeStamp(char *pszTimeStamp)
{
	int iPosOrig, iPosDest, iLen;
	static char szTmp[LEN_AUX + LEN_EOS];

	memset(szTmp, 0x00, sizeof(szTmp));

	for(iLen=strlen(pszTimeStamp), iPosOrig=iPosDest=0; iPosOrig<iLen; iPosOrig++)
		if(isdigit(pszTimeStamp[iPosOrig]))
			szTmp[iPosDest++]=pszTimeStamp[iPosOrig];

	return szTmp;
}

/***************************************************************************************/
char *DataAtual(void)
{
  time_t tAgora;
  struct tm *pstData; 
  static char szAgora[LEN_AUX + LEN_EOS];

  tAgora = time(0);
  pstData = localtime(&tAgora);
  strftime(szAgora, 10, "%Y%m%d", pstData);
  
  return szAgora;
}

/***************************************************************************************/
char *DataHoraAtual(void)
{
  time_t tAgora;
  struct tm *pstData; 
  static char szAgora[LEN_AUX + LEN_EOS];

  tAgora = time(0);
  pstData = localtime(&tAgora);
  strftime(szAgora, 20, "%Y%m%d%H%M%S", pstData);
  
  return szAgora;
}
