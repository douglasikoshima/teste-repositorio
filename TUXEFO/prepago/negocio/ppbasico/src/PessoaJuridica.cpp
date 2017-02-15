///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaJuridica
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:23 $
 **/
///////////////////////////////////////////////////////////////////////////////


#include "PessoaJuridica.h"

CPessoaJuridica::CPessoaJuridica(void)
{
    memset(&tPessoaJuridica, 0x00, sizeof(TPessoaJuridica));
}

void CPessoaJuridica::setIdPessoa(char *pszIdPessoa )
{
    strcpy(tPessoaJuridica.szIdPessoa, pszIdPessoa);
}

void CPessoaJuridica::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tPessoaJuridica.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaJuridica::inserePessoaJuridica(void)
{
    clPessoaJuridicapc.proCInserePessoaJuridica(&tPessoaJuridica);
}

void CPessoaJuridica::atualizaPessoaJuridica(void)
{
    clPessoaJuridicapc.proCAtualizaPessoaJuridica(tPessoaJuridica);
}

bool CPessoaJuridica::buscaPessoaJuridica(void)
{
    return clPessoaJuridicapc.proCBuscaPessoaJuridica(&tPessoaJuridica);
}

bool CPessoaJuridica::buscaPessoaJuridica(TPessoaJuridica *ptPessoaJuridicaAUX)
{
    return clPessoaJuridicapc.proCBuscaPessoaJuridica(ptPessoaJuridicaAUX);
}

void CPessoaJuridica::setStruct(TPessoaJuridica *ptPessoaJuridica)
{
    memcpy(&tPessoaJuridica, ptPessoaJuridica, sizeof(TPessoaJuridica));
    memset(ptPessoaJuridica, 0x00, sizeof(TPessoaJuridica));
}
