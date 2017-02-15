#include <ctype.h>

#include "tuxfw.h"
#include "PPExceptionTMA.h"
#include "PPGlobalTMA.h"
#include "ToolsTMA.h"

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
            throw PPExceptionTMA(1, "Erro alocando memoria");

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
        throw PPExceptionTMA(1, "Erro alocando memoria");

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
        throw PPExceptionTMA(1,"Erro alocando memoria");

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
void TrataNrDocumento(char *pszNrDocumento)
{
	register int iPosOrig, iPosDest;
    int iLen;
	char szTmp[LEN_AUX_TOOLS + LEN_EOS];

    ULOG_START("TrataNrDocumento");

    if(pszNrDocumento == NULL)
    {
        ULOG("NrDocumento vazio[%s]", pszNrDocumento);
        ULOG_END("TrataNrDocumento");
        return;
    }

    ULOG("IN->TrataNrDocumento[%s]", pszNrDocumento);
    
	memset(szTmp, 0x00, sizeof(szTmp));
    iLen=strlen(pszNrDocumento);

	for(iPosOrig=iPosDest=0; iPosOrig<iLen; iPosOrig++)
		if(isalnum(pszNrDocumento[iPosOrig]))
			szTmp[iPosDest++]=pszNrDocumento[iPosOrig];

    strcpy(pszNrDocumento, szTmp);
    ULOG("OUT->TrataNrDocumento[%s]", pszNrDocumento);

    ULOG_END("TrataNrDocumento");
	return;
}

/***************************************************************************************/
char *TimeStamp(char *pszTimeStamp)
{
	register int iPosOrig, iPosDest, iLen;
	static char szTmp[LEN_AUX_TOOLS + LEN_EOS];

    if( pszTimeStamp == NULL )
        return NULL;
    
	memset(szTmp, 0x00, sizeof(szTmp));

	for(iLen=strlen(pszTimeStamp), iPosOrig=iPosDest=0; iPosOrig<iLen; iPosOrig++)
		if(isdigit(pszTimeStamp[iPosOrig]))
			szTmp[iPosDest++]=pszTimeStamp[iPosOrig];

	return szTmp;
}

/***************************************************************************************/
void ConvDataFromJava(char *pszDataJava)
{
    char szAux[LEN_AUX_TOOLS + LEN_EOS];

    ULOG_START("ConvDataFromJava");

    ULOG("pszDataJava[%s]", pszDataJava);

    sprintf(szAux, "%.*s%.*s%.*s",  4, pszDataJava+6,
                                    2, pszDataJava+3,
                                    2, pszDataJava);
    ULOG("szAux[%s]", szAux);
    strcpy(pszDataJava, szAux);

    ULOG_END("ConvDataFromJava");
}
