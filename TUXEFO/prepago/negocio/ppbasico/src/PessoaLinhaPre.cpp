///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaLinhaPre
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Eder Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:23 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "PessoaLinhaPre.h"
#include "PessoaLinhaPrepc.h"
#include "tuxfw.h"


CPessoaLinhaPre::CPessoaLinhaPre(void)
{
    memset(&tPessoaLinhaPre, 0x00, sizeof(TPessoaLinhaPre));
}


void CPessoaLinhaPre::setIdPessoaLinha(char *pszIdPessoaLinha)
{
    strcpy(tPessoaLinhaPre.szIdPessoaLinha, pszIdPessoaLinha);
}

void CPessoaLinhaPre::setInMudancaTitularidade(char *pszInMudancaTitularidade)
{
    strcpy(tPessoaLinhaPre.szInMudancaTitularidade, pszInMudancaTitularidade);
}

void CPessoaLinhaPre::setInSincronismo(char *pszInSincronismo)
{
    strcpy(tPessoaLinhaPre.szInSincronismo, pszInSincronismo);
}

void CPessoaLinhaPre::setInUsuarioNaoInformado(char *pszInUsuarioNaoInformado)
{
    strcpy(tPessoaLinhaPre.szInUsuarioNaoInformado, pszInUsuarioNaoInformado);
}


void CPessoaLinhaPre::inserePessoaLinhaPre(void)
{
    clPessoaLinhaPrepc.proCInserePessoaLinhaPre(tPessoaLinhaPre);
}

bool CPessoaLinhaPre::buscaPessoaLinhaPre(TPessoaLinhaPre *ptPessoaLinhaPre)
{
    return clPessoaLinhaPrepc.proCBuscaPessoaLinhaPre(ptPessoaLinhaPre);
}

bool CPessoaLinhaPre::buscaPessoaLinhaPre(void)
{
    return clPessoaLinhaPrepc.proCBuscaPessoaLinhaPre(&tPessoaLinhaPre);
}

void CPessoaLinhaPre::atualizaPessoaLinhaPre(void)
{
    return clPessoaLinhaPrepc.proCAtualizaPessoaLinhaPre(tPessoaLinhaPre);
}

void CPessoaLinhaPre::insereMudancaTitularidade(char* szCodAreaRegistro, char* szNrLinha)
{
    return clPessoaLinhaPrepc.proCInsereMudancaTitularidade(szCodAreaRegistro, szNrLinha);
}

void CPessoaLinhaPre::setStruct(TPessoaLinhaPre *ptPessoaLinhaPre)
{
    memcpy(&tPessoaLinhaPre, ptPessoaLinhaPre, sizeof(TPessoaLinhaPre));
    memset(ptPessoaLinhaPre, 0x00, sizeof(TPessoaLinhaPre));
}
