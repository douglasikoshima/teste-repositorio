///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Cliente
 * @usecase PessoaLinhaPre
 * @author  Renato Striitzel Russo
 * @author  Robinson Vieira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <tuxfw.h>
#include "../include/PessoaLinhaPrepc.h"
#include "../include/PessoaLinhaPre.h"

CPessoaLinhaPre::CPessoaLinhaPre(void)
{
    memset(&tPessoaLinha, 0x00, sizeof(TPessoaLinha));
}

CPessoaLinhaPre::CPessoaLinhaPre(char *pszIdTipoRelacionamento, char *pszIdLinhaTelefonica) 
{
    memset(&tPessoaLinha, 0x00, sizeof(TPessoaLinha));

    strcpy(tPessoaLinha.szIdTipoRelacionamento, pszIdTipoRelacionamento);
    strcpy(tPessoaLinha.szIdLinhaTelefonica, pszIdLinhaTelefonica);
    strcpy(tPessoaLinha.szIdUsuarioAlteracao, ID_USUARIO_ALTERACAO);
}

bool CPessoaLinhaPre::buscaPessoaLinha(void)
{
    return clPessoaLinhapc.proCBuscaPessoaLinha(&tPessoaLinha);  
}

bool CPessoaLinhaPre::buscaPessoaLinha(TPessoaLinha *ptPessoaLinhaAux)
{
    return clPessoaLinhapc.proCBuscaPessoaLinha(ptPessoaLinhaAux);
}

void CPessoaLinhaPre::falsoInsertPessoaLinha(void)
{
    clPessoaLinhapc.proCInserePessoaLinha(&tPessoaLinha);
}

void  CPessoaLinhaPre::atualizaPessoaLinha(void) 
{
    clPessoaLinhapc.proCAtualizaPessoaLinha(tPessoaLinha);
}

void  CPessoaLinhaPre::inserePessoaLinha(void) 
{
    clPessoaLinhapc.proCInserePessoaLinha(&tPessoaLinha);
}

void CPessoaLinhaPre::apagaPessoaLinha(void)
{
    clPessoaLinhapc.proCApagaPessoaLinha(tPessoaLinha);
}


void CPessoaLinhaPre::setIdLinhaTelefonica(char *pszIdLinhaTelefonica)
{
    strcpy(tPessoaLinha.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CPessoaLinhaPre::setIdPessoaDePara(char *pszIdPessoaDePara)
{
    strcpy(tPessoaLinha.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaLinhaPre::setIdTipoRelacionamento(char *pszIdTipoRelacionamento)
{
    strcpy(tPessoaLinha.szIdTipoRelacionamento, pszIdTipoRelacionamento);
}

void CPessoaLinhaPre::setIdPessoaLinha(char *pszIdPessoaLinha)
{
    strcpy(tPessoaLinha.szIdPessoaLinha, pszIdPessoaLinha);
}
