#include "PessoaComunicacaoPORT.h"
#include "PessoaComunicacaopcPORT.h"

CPessoaComunicacao::CPessoaComunicacao( void ) {
    memset( &tPessoaComunicacao, 0, sizeof( TPessoaComunicacao ) );
}

CPessoaComunicacao::~CPessoaComunicacao( void ) {
}

void CPessoaComunicacao::setIdPessoaComunicacao( char* pszIdPessoaComunicacao ) {
    strcpy( tPessoaComunicacao.szIdPessoaComunicacao, pszIdPessoaComunicacao );
}

void CPessoaComunicacao::setIdSistemaOrigem( char* pszIdSistemaOrigem ) {
    strcpy( tPessoaComunicacao.szIdSistemaOrigem, pszIdSistemaOrigem );
}

void CPessoaComunicacao::setIdPessoa( char* pszIdPessoa ) {
    strcpy( tPessoaComunicacao.szIdPessoa, pszIdPessoa );
}

void CPessoaComunicacao::setIdTipoComunicacao( char* pszIdTipoComunicacao ) {
    strcpy( tPessoaComunicacao.szIdTipoComunicacao, pszIdTipoComunicacao );
}

void CPessoaComunicacao::setDsContato( char* pszDsContato ) {
    strcpy( tPessoaComunicacao.szDsContato, pszDsContato );
}

void CPessoaComunicacao::setTsSincronismo( char* pszTsSincronismo ) {
    strcpy( tPessoaComunicacao.szTsSincronismo, pszTsSincronismo );
}

void CPessoaComunicacao::setSqSincronismo( char* pszSqSincronismo ) {
    strcpy( tPessoaComunicacao.szSqSincronismo, pszSqSincronismo );
}

void CPessoaComunicacao::setIdComunicacaoSistemaOrigem( char* pszIdComunicacaoSistemaOrigem ) {
    strcpy( tPessoaComunicacao.szIdComunicacaoSistemaOrigem, pszIdComunicacaoSistemaOrigem );
}

void CPessoaComunicacao::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaComunicacao.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaComunicacao::setNmContato(char *pszNmContato) {
    strcpy(tPessoaComunicacao.szNmContato, pszNmContato);
}

char* CPessoaComunicacao::getIdPessoaComunicacao( void ) {
    static char szAux[LEN_IDPESSOACOMUNICACAO + LEN_EOS ];

    strcpy( szAux, tPessoaComunicacao.szIdPessoaComunicacao );
    return szAux;
}

char* CPessoaComunicacao::getSqSincronismo( void ) {
    static char szSqSincronismoAUX[ LEN_SQSINCRONISMO + LEN_EOS ];

    strcpy( szSqSincronismoAUX, tPessoaComunicacao.szSqSincronismo );
    return szSqSincronismoAUX;
}

char* CPessoaComunicacao::getIdTipoComunicacao( void ) {
    static char szIdTipoComunicacaoAUX[ LEN_IDTIPOCOMUNICACAO + LEN_EOS ];

    strcpy( szIdTipoComunicacaoAUX, tPessoaComunicacao.szIdTipoComunicacao );
    return szIdTipoComunicacaoAUX;
}

char* CPessoaComunicacao::getIdSistemaOrigem(void) {
    static char szAUX[LEN_IDSISTEMAORIGEM + LEN_EOS];

    strcpy(szAUX, tPessoaComunicacao.szIdSistemaOrigem);
    return szAUX;
}

char* CPessoaComunicacao::getIdPessoa(void) {
    static char szAUX[LEN_IDPESSOA + LEN_EOS];

    strcpy(szAUX, tPessoaComunicacao.szIdPessoa);
    return szAUX;
}

char* CPessoaComunicacao::getDsContato( void ) {
    static char szAUX[LEN_DSCONTATO + LEN_EOS ];

    strcpy(szAUX, tPessoaComunicacao.szDsContato);
    return szAUX;
}

char* CPessoaComunicacao::getIdComunicacaoSistemaOrigem( void ) {
    static char szIdComunicacaoSistemaOrigemAUX[ LEN_IDCOMUNICACAOSISTEMAORIGEM + LEN_EOS ];

    strcpy( szIdComunicacaoSistemaOrigemAUX, tPessoaComunicacao.szIdComunicacaoSistemaOrigem );
    return szIdComunicacaoSistemaOrigemAUX;    
}

char* CPessoaComunicacao::getNmContato( void ) {
    static char szAUX[LEN_NMCONTATO + LEN_EOS];

    strcpy(szAUX, tPessoaComunicacao.szNmContato);
    return szAUX;    
}


bool CPessoaComunicacao::buscaPessoaComunicacao( void ) {
	return clPessoaComunicacaopc.proCBuscaPessoaComunicacao(&tPessoaComunicacao);
}

bool CPessoaComunicacao::buscaPessoaComunicacao(TPessoaComunicacao *ptPessoaComunicacao) {
	return clPessoaComunicacaopc.proCBuscaPessoaComunicacao(ptPessoaComunicacao);
}

bool CPessoaComunicacao::buscaPessoaComunicacaoIdPessoaSgClass(TPessoaComunicacao *ptPessoaComunicacao) {
	return clPessoaComunicacaopc.proCBuscaPessoaComunicacaoIdPessoaSgClass(ptPessoaComunicacao);
}

void CPessoaComunicacao::inserePessoaComunicacao( void ) {
    clPessoaComunicacaopc.proCInserePessoaComunicacao(&tPessoaComunicacao);
}

void CPessoaComunicacao::atualizaPessoaComunicacao( void ) {
    clPessoaComunicacaopc.proCAtualizaPessoaComunicacao(tPessoaComunicacao);
}

void CPessoaComunicacao::setStruct(TPessoaComunicacao *ptPessoaComunicacao) {
    memcpy(&tPessoaComunicacao, ptPessoaComunicacao, sizeof(TPessoaComunicacao));
    memset(ptPessoaComunicacao, 0x00, sizeof(TPessoaComunicacao));
}
