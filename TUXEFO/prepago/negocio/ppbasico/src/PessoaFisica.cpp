///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaFisica
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "PessoaFisica.h"

CPessoaFisica::CPessoaFisica(void)
{
    memset(&tPessoaFisica, 0x00, sizeof(TPessoaFisica));
}

void CPessoaFisica::setIdPessoa(char *pszIdPessoa)
{
    strcpy(tPessoaFisica.szIdPessoa, pszIdPessoa);
}

void CPessoaFisica::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tPessoaFisica.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaFisica::setStruct(TPessoaFisica *ptPessoaFisica)
{
    memcpy(&tPessoaFisica, ptPessoaFisica, sizeof(TPessoaFisica));
    memset(ptPessoaFisica, 0x00, sizeof(TPessoaFisica));
}

void CPessoaFisica::setIdPais(char *pszIdPais)
{
    strcpy(tPessoaFisica.szIdPais, pszIdPais);
}

void CPessoaFisica::setIdTratamento(char *pszIdTratamento)
{
    strcpy(tPessoaFisica.szIdTratamento, pszIdTratamento);
}

void CPessoaFisica::inserePessoaFisica(void)
{
    clPessoaFisicapc.proCInserePessoaFisica(&tPessoaFisica);
}

void CPessoaFisica::atualizaPessoaFisica(void)
{
    clPessoaFisicapc.proCAtualizaPessoaFisica(tPessoaFisica);
}

bool CPessoaFisica::buscaPessoaFisica(void)
{
    return clPessoaFisicapc.proCBuscaPessoaFisica(&tPessoaFisica);
}

bool CPessoaFisica::buscaPessoaFisica(TPessoaFisica *ptPessoaFisicaAUX)
{
    return clPessoaFisicapc.proCBuscaPessoaFisica(ptPessoaFisicaAUX);
}
