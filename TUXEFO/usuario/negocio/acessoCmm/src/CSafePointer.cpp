#include "CSafePointer.h"

CSafePointer::CSafePointer()
{
	//ULOG_START("CSafePointer::CSafePointer()");
	
	//Zera contadores
	pcaPointers = NULL;
	_iQuantidadeBlocos = 0;
	_iPonteirosUtilizados = 0;
	
	//ULOG_END("CSafePointer::CSafePointer()");
}

CSafePointer::~CSafePointer()
{
	//ULOG_START("CSafePointer::~CSafePointer()");
	desalocaTudo();
	//ULOG_END("CSafePointer::~CSafePointer()");
}

void CSafePointer::alocaBloco( void )
{
	//ULOG_START("CSafePointer::alocaBloco()");
	//Adiciona um ao total de blocos
	_iQuantidadeBlocos++;
	//Aloca mais um bloco
	pcaPointers = (char**) realloc( pcaPointers, sizeof(char*)*_iQuantidadeBlocos*SP_TAMANHO_BLOCO );
	//ULOG_END("CSafePointer::alocaBloco()");
	
}

void CSafePointer::desalocaTudo( void )
{
	//ULOG_START("CSafePointer::desalocaTudo()");
	//Libera todos os ponteiros utilizados
	for( int x = 0; x < _iPonteirosUtilizados; x++ )
	{
		XMLString::release( &pcaPointers[x] );
	}
	//Libera a matriz de ponteiros
	free( pcaPointers );
	pcaPointers = NULL;
	//Zera contadores
	_iQuantidadeBlocos = 0;
	_iPonteirosUtilizados = 0;
	//ULOG_END("CSafePointer::desalocaTudo()");
}

char* CSafePointer::getTag( DOMNode* pdnNode, char* pcNomeTag )
{
	//ULOG_START("CSafePointer::getTag()");
	//ULOG_END("CSafePointer::getTag()");
	return getTag( pdnNode, pcNomeTag, 0 );
}

char* CSafePointer::getTag( DOMNode* pdnNode, char* pcNomeTag, int nNivel )
{
	//ULOG_START("CSafePointer::getTag()");
	if( (_iPonteirosUtilizados+1) >= ( _iQuantidadeBlocos * SP_TAMANHO_BLOCO ) )
	{
		alocaBloco();
	}
	pcaPointers[_iPonteirosUtilizados] = TuxHelper::walkTree( pdnNode, pcNomeTag, nNivel );
	_iPonteirosUtilizados++;
    
    //ULOG_END("CSafePointer::getTag()");
	return pcaPointers[_iPonteirosUtilizados-1];
}

/***************************************************************************************/
void CSafePointer::DesmembraNome(TDesmembraNome *ptDesmembraNome)
{
    //ULOG_START("CSafePointer::DesmembraNome()");
    
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
        throw "CSafePointer::DesmembraNome():Falha na alocação de memória";

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
    
    //ULOG_END("CSafePointer::DesmembraNome()");
}

/***************************************************************************************/
void CSafePointer::NormalizaNome(char *pszString)
{
    //ULOG_START("CSafePointer::NormalizaNome()");
    alltrim(pszString);
    RetiraMultiplosEspacos(pszString);
    //ULOG_END("CSafePointer::NormalizaNome()");
}

/***************************************************************************************/
void CSafePointer::RetiraMultiplosEspacos(char *pszString)
{
    //ULOG_START("CSafePointer::RetiraMultiplosEspacos()");
    register unsigned int iPosOrigem, iPosDestino;
    unsigned int iLen;
    char *pszTmp;

    iLen=strlen(pszString);

    if((pszTmp = (char *)malloc(iLen + LEN_EOS)) == NULL)
        throw "CSafePointer::RetiraMultiplosEspacos():Falha na alocação de memória";

    memset(pszTmp, 0x00, iLen + LEN_EOS);

    for(iPosOrigem=iPosDestino=0; iPosOrigem<iLen; iPosOrigem++)
        if(pszString[iPosOrigem] == 0x20 && pszString[iPosOrigem + 1] == 0x20)
            continue;
        else
            pszTmp[iPosDestino++] = pszString[iPosOrigem];

    strcpy(pszString, pszTmp);

    free(pszTmp);
    
    //ULOG_END("CSafePointer::RetiraMultiplosEspacos()");
}

/***************************************************************************************/
void CSafePointer::alltrim(char *pszString)
{
    //ULOG_START("CSafePointer::alltrim()");
    ltrim(pszString);
    rtrim(pszString);
    //ULOG_END("CSafePointer::alltrim()");
}

/***************************************************************************************/
void CSafePointer::ltrim(char *pszString)
{
    //ULOG_START("CSafePointer::ltrim()");
    register unsigned int iPos;
    unsigned int iLen;
    char *pszTmp, *pszAlocado;

    iLen=strlen(pszString);

    if((pszAlocado = (char *)malloc(iLen + LEN_EOS)) == NULL)
        throw "CSafePointer::ltrim():Falha na alocação de memória";

    strcpy(pszAlocado, pszString);

    pszTmp=pszAlocado;


    for(iPos=0; iPos < iLen; iPos++)
        if(pszString[iPos] == 0x20)
            pszTmp++;
        else
            break;

    strcpy(pszString, pszTmp);

    free(pszAlocado);
    //ULOG_END("CSafePointer::ltrim()");
}

/***************************************************************************************/
void CSafePointer::rtrim(char *pszString)
{
    //ULOG_START("CSafePointer::rtrim()");
    register int iPos;

    for(iPos=strlen(pszString)-1; iPos >= 0; iPos--)
        if(pszString[iPos] == 0x20)
            pszString[iPos] = 0x00;
        else
            break;
    //ULOG_END("CSafePointer::rtrim()");
}
