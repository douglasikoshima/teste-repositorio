#include "RegistraContatoPORT.h"

CRegistraContato::CRegistraContato(void) {
    memset(&tRegistraContato, 0x00, sizeof(TRegistraContato));
}

void CRegistraContato::atualizaRegistraContato(void) {
    clRegistraContatopc.proCAtualizaRegistraContato(tRegistraContato);
}

void CRegistraContato::insereRegistraContato(void) {
    clRegistraContatopc.proCIncluiRegistraContato(&tRegistraContato);
}

bool CRegistraContato::existeRegistraContato(void) {
    return clRegistraContatopc.proCExisteRegistraContato(tRegistraContato);
}



void CRegistraContato::setIdPessoaDePara(char *pszIdPessoaDePara) {
    strcpy(tRegistraContato.szIdPessoaDePara, pszIdPessoaDePara);
}

void CRegistraContato::setIdLinhaTelefonica(char *pszIdLinhaTelefonica) {
    strcpy(tRegistraContato.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CRegistraContato::setCdAreaRegistro(char *pszCdAreaRegistro) {
    strcpy(tRegistraContato.szCdAreaRegistro, pszCdAreaRegistro);
}

void CRegistraContato::setNrLinha(char *pszNrLinha) {
    strcpy(tRegistraContato.szNrLinha, pszNrLinha);
}

void CRegistraContato::setIdPessoaLinhaHistorico(char *pszIdPessoaLinhaHistorico) {
    strcpy(tRegistraContato.szIdPessoaLinhaHistorico, pszIdPessoaLinhaHistorico);
}

void CRegistraContato::setXml(char *pszXml) {
    strcpy(tRegistraContato.szXML, pszXml);
}

void CRegistraContato::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tRegistraContato.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CRegistraContato::setNrTelefone(char *pszNrTelefone)
{
    char szAux[16];

    memset(szAux, 0x00, sizeof(szAux));
    memcpy(szAux, pszNrTelefone, 2);
    this->setCdAreaRegistro(szAux);

    memset(szAux, 0x00, sizeof(szAux));
    //memcpy(szAux, pszNrTelefone+2, 8);
    sprintf(szAux,"%s",(char*)&pszNrTelefone[2]);
    this->setNrLinha(szAux);
}

char *CRegistraContato::getIdRegistraContato(void) {
    static char szAux[LEN_IDREGISTRACONTATO + LEN_EOS];

    strcpy(szAux, tRegistraContato.szIdRegistraContato);
    return szAux;
}
