///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaJuridica
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:18 $
 **/
///////////////////////////////////////////////////////////////////////////////


#include "../include/PessoaJuridica.h"
//#include "../include/SincException.h"
//#include "../include/Tools.h"


CPessoaJuridica::CPessoaJuridica(void)
{
    // Inicializa as estruturas do objeto.
    memset(&tPessoaJuridica, 0x00, sizeof(TPessoaJuridica));
    memset(&tPessoaJuridicaXML, 0x00, sizeof(TPessoaJuridicaXML));
}

void CPessoaJuridica::setIdPessoa(char *pszIdPessoa )
{
    strcpy(tPessoaJuridica.szIdPessoa, pszIdPessoa);
}

// Mapeia os dados de entrada para a estrutura do PREPAGO

void CPessoaJuridica::SetData(TPessoaJuridicaXML xmJ)
{
    strcpy( tPessoaJuridica.szIdPessoa	,		xmJ.idPessoa    );
    strcpy( tPessoaJuridica.szNmFantasia,		xmJ.nmFantasia  );
    strcpy( tPessoaJuridica.szNmPessoaFilial,	xmJ.usuarioLinha ); 
    strcpy( tPessoaJuridica.szdtFundacao,		xmJ.dtFundacao ); 
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
