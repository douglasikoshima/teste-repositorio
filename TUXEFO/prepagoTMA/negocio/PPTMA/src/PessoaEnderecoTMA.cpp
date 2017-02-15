#include "PessoaEnderecoTMA.h"

CPessoaEndereco::CPessoaEndereco( void ) {
    memset(&tPessoaEndereco, 0x00, sizeof(TPessoaEndereco));
}

CPessoaEndereco::~CPessoaEndereco( void ) {
}

void CPessoaEndereco::enderecoSujo(void) {
	clPessoaEnderecopc.proCEnderecoSujo(&tPessoaEndereco);
}

void CPessoaEndereco::apagaPessoaEndereco(void) {
	clPessoaEnderecopc.proCApagaPessoaEndereco(&tPessoaEndereco);
}

void CPessoaEndereco::inserePessoaEndereco(void) {
    clPessoaEnderecopc.proCInserePessoaEndereco(&tPessoaEndereco);
}

bool CPessoaEndereco::existePessoaEndereco(const char *idPessoa,char *idPessoaEndereco) {
    return clPessoaEnderecopc.proCExistePessoaEndereco(idPessoa,idPessoaEndereco);
}

bool CPessoaEndereco::buscaPessoaEndereco( void ) {
    return clPessoaEnderecopc.proCBuscaPessoaEndereco(&tPessoaEndereco);
}

bool  CPessoaEndereco::buscaPessoaEndereco(TPessoaEndereco &tPessoaEnderecoAUX)
{
    return clPessoaEnderecopc.proCBuscaPessoaEndereco(&tPessoaEnderecoAUX);
}

void CPessoaEndereco::atualizaPessoaEndereco(void) {
    clPessoaEnderecopc.proCAtualizaPessoaEndereco(&tPessoaEndereco);
}

void CPessoaEndereco::excluirEndereco(const char *idPessoa) {
    clPessoaEnderecopc.procExcluirEndereco(idPessoa);
}

void CPessoaEndereco::ProcuraSPED(char * nrCEP, char * nmLograd, char * nmBairro, char * nmCidade) {
    clPessoaEnderecopc.proCBuscaSPED(&tPessoaEndereco, nrCEP, nmLograd, nmBairro, nmCidade);
}

void CPessoaEndereco::BuscaSiglaUF( int idUF ) {
    clPessoaEnderecopc.proCBuscaSiglaUF( &tPessoaEndereco, idUF );
}

///////////////////////////////////////////////////////////////////////////////
// Métodos de SET.
///////////////////////////////////////////////////////////////////////////////
void CPessoaEndereco::setInEnderecoSujo(char *pszInEnderecoSujo) {
    strcpy( tPessoaEndereco.szInEnderecoSujo, pszInEnderecoSujo );
}

void CPessoaEndereco::setIdPessoa( char* pszIdPessoa ) {
    strcpy( tPessoaEndereco.szIdPessoa, pszIdPessoa );
}

void CPessoaEndereco::setNrSequencia( char* pszNrSequencia ) {
    strcpy( tPessoaEndereco.szNrSequencia, pszNrSequencia );
}

void CPessoaEndereco::setIdEnderecoSistemaOrigem( char* pszIdEnderecoSistemaOrigem ) {
    strcpy( tPessoaEndereco.szIdEnderecoSistemaOrigem, pszIdEnderecoSistemaOrigem );
}

void CPessoaEndereco::setIdPessoaEndereco( char* pszIdPessoaEndereco ) {
    strcpy( tPessoaEndereco.szIdPessoaEndereco, pszIdPessoaEndereco );
}

void CPessoaEndereco::setIdTipoEndereco( char* pszIdTipoEndereco ) {
    strcpy( tPessoaEndereco.szIdTipoEndereco, pszIdTipoEndereco );
}

void CPessoaEndereco::setIdSistemaOrigem( char* pszIdSistemaOrigem ) {
    strcpy( tPessoaEndereco.szIdSistemaOrigem, pszIdSistemaOrigem );
};

void CPessoaEndereco::setTsSincronismo( char* pszTsSincronismo ) {
    strcpy( tPessoaEndereco.szTsSincronismo, pszTsSincronismo );
}

void CPessoaEndereco::setSqSincronismo( char* pszSqSincronismo ) {
    strcpy( tPessoaEndereco.szSqSincronismo, pszSqSincronismo );
}

void CPessoaEndereco::setIdPais(char *pszIdPais) {
    strcpy(tPessoaEndereco.szIdPais, pszIdPais);
}

void CPessoaEndereco::setIdUf(char *pszIdUf) {
    strcpy(tPessoaEndereco.szIdUf, pszIdUf);
}

void CPessoaEndereco::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaEndereco.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

///////////////////////////////////////////////////////////////////////////////
// Métodos de GET (recuperam dados de tPessoaEndereco)
///////////////////////////////////////////////////////////////////////////////
char* CPessoaEndereco::getInEnderecoSujo(void) {
    static char szAux[LEN_INENDERECOSUJO + LEN_EOS];

    strcpy( szAux, tPessoaEndereco.szInEnderecoSujo);
    return szAux;
}

char* CPessoaEndereco::getSqSincronismo( void ) {
    static char szAux[LEN_SQSINCRONISMO + LEN_EOS];

    strcpy( szAux, tPessoaEndereco.szSqSincronismo );
    return szAux;
}

char* CPessoaEndereco::getIdEnderecoSistemaOrigem( void ) {
    static char szAux[LEN_IDENDERECOSISTEMAORIGEM + LEN_EOS];

    strcpy( szAux, tPessoaEndereco.szIdEnderecoSistemaOrigem );
    return szAux;
}

char* CPessoaEndereco::getIdPessoaEndereco(void) {
    static char szAux[LEN_IDPESSOAENDERECO + LEN_EOS];

    strcpy( szAux, tPessoaEndereco.szIdPessoaEndereco );
    return szAux;
}

char *CPessoaEndereco::getIdTipoEndereco(void) {
    static char szAux[LEN_IDTIPOENDERECO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szIdTipoEndereco);
    return szAux;
}

void CPessoaEndereco::setStruct(TPessoaEndereco *ptPessoaEndereco) {
    memcpy(&tPessoaEndereco, ptPessoaEndereco, sizeof(TPessoaEndereco));
    memset(ptPessoaEndereco, 0x00, sizeof(TPessoaEndereco));
}

char* CPessoaEndereco::getNmTituloLogradouro(void) {
    static char szAux[LEN_NMTITULOLOGRADOURO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNmTituloLogradouro);
    return szAux;
}

char* CPessoaEndereco::getNmTipoLogradouro(void) {
    static char szAux[LEN_NMLOGRADOURO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNmTipoLogradouro);
    return szAux;
}

char* CPessoaEndereco::getNmLogradouro(void) {
    static char szAux[LEN_NMLOGRADOURO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNmLogradouro);
    return szAux;
}

char* CPessoaEndereco::getCdLogradouro(void) {
    static char szAux[40 + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szcdLogradouro);
    return szAux;
}

char* CPessoaEndereco::getInCNL(void) {
    static char szAux[8 + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szInCnl);
    return szAux;
}

char* CPessoaEndereco::getCdIbge(void) {
    static char szAux[7 + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szcdIbge);
    return szAux;
}

char* CPessoaEndereco::getNrEndereco(void) {
    static char szAux[LEN_NRENDERECO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNrEndereco);
    return szAux;
}

char* CPessoaEndereco::getDsEnderecoComplemento(void) {
    static char szAux[LEN_DSENDERECOCOMPLEMENTO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szDsEnderecoComplemento);
    return szAux;
}

char* CPessoaEndereco::getNmBairro(void) {
    static char szAux[LEN_NMBAIRRO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNmBairro);
    return szAux;
}

char* CPessoaEndereco::getNmMunicipio(void) {
    static char szAux[LEN_NMMUNICIPIO + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNmMunicipio);
    return szAux;
}

char* CPessoaEndereco::getNrCep(void) {
    static char szAux[LEN_NRCEP + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szNrCep);
    return szAux;
}

char* CPessoaEndereco::getIdUF(void) {
    static char szAux[LEN_IDUF + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szIdUf);
    return szAux;
}

char* CPessoaEndereco::getSgUF(void) {
    static char szAux[LEN_IDUF + LEN_EOS];

    strcpy( szAux, tPessoaEndereco.szSgUf );
    return szAux;
}

char* CPessoaEndereco::getIdPais(void) {
    static char szAux[LEN_IDPAIS + LEN_EOS];

    strcpy(szAux, tPessoaEndereco.szIdPais);
    return szAux;
}
