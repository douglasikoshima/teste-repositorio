///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase ContaEndereco
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "ContaEndereco.h"

CContaEndereco::CContaEndereco(void)
{
    memset(&tContaEndereco, 0x00, sizeof(TContaEndereco));
}

bool CContaEndereco::buscaContaEnderecoAntigo(void)
{
    return(clContaEnderecopc.proCBuscaContaEnderecoAntigo(&tContaEndereco));
}

bool CContaEndereco::buscaContaEndereco(void)
{
    return(clContaEnderecopc.proCBuscaContaEndereco(&tContaEndereco));
}

bool CContaEndereco::buscaContaEndereco(TContaEndereco *ptContaEndereco)
{
    return(clContaEnderecopc.proCBuscaContaEndereco(ptContaEndereco));
}

void CContaEndereco::apagaContaEndereco(void)
{
	clContaEnderecopc.proCApagaContaEndereco(tContaEndereco);
}

void CContaEndereco::insereContaEndereco(void)
{
	clContaEnderecopc.proCInsereContaEndereco(&tContaEndereco);
}

void CContaEndereco::atualizaContaEndereco(void)
{
	clContaEnderecopc.proCAtualizaContaEndereco(tContaEndereco);
}


void CContaEndereco::setIdConta(char *pszIdConta)
{
    strcpy(tContaEndereco.szIdConta, pszIdConta);
}

void CContaEndereco::setIdPessoaEndereco(char *pszIdPessoaEndereco)
{
    strcpy(tContaEndereco.szIdPessoaEndereco, pszIdPessoaEndereco);
}

void CContaEndereco::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tContaEndereco.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CContaEndereco::setIdContaEndereco(char *pszIdContaEndereco)
{
    strcpy(tContaEndereco.szIdContaEndereco, pszIdContaEndereco);
}

char *CContaEndereco::getIdPessoaEndereco(void)
{
    static char szAux[LEN_IDPESSOAENDERECO + LEN_EOS];

    strcpy(szAux, tContaEndereco.szIdPessoaEndereco);
    return szAux;
}

void CContaEndereco::setIdTipoEnderecoCobranca(char *pszIdTipoEnderecoCobranca)
{
    strcpy(tContaEndereco.szIdTipoEnderecoCobranca, pszIdTipoEnderecoCobranca);
}
