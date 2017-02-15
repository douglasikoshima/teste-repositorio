///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaFisica
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "../include/PessoaFisica.h"

CPessoaFisica::CPessoaFisica(void)
{
    // Inicializa as estruturas do objeto.
    memset(&tPessoaFisica, 0x00, sizeof(TPessoaFisica));

}

void CPessoaFisica::setIdPessoa(char *pszIdPessoa)
{
    strcpy(tPessoaFisica.szIdPessoa, pszIdPessoa);
}


// Mapeia os dados de entrada para a estrutura do FO.

void CPessoaFisica::setData( TPessoaFisicaXML xm )
{
    strcpy(tPessoaFisica.szIdPessoa, xm.idPessoa ); 
    strcpy(tPessoaFisica.szDtNascimento, xm.dtNascimento ); 
    strcpy(tPessoaFisica.szIdEstadoCivil, xm.idEstadoCivilSelecionado ); 
	strcpy(tPessoaFisica.szIdSexo, xm.idSexoSelecionado ); 
    strcpy(tPessoaFisica.szNmMae, "" ); 
    strcpy(tPessoaFisica.szNmPai, "" ); 
    strcpy(tPessoaFisica.szIdTratamento,	"1" ); 
    strcpy(tPessoaFisica.szIdPais,			"1" ); 
    
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

void CPessoaFisica::getData(TPessoaFisicaXML *xmlp)
{
//                  , :oszNmMae:iNmMae
//                  , :oszNmPai:iNmPai
//                  , :oszIdTratamento:iIdTratamento
//                  , :oszIdUsuarioAlteracao:iIdUsuarioAlteracao
//                  , :oszDtUltimaAlteracao:iDtUltimaAlteracao
//                  , :oszIdPais:iIdPais
	strcpy(xmlp->dtNascimento,tPessoaFisica.szDtNascimento);
	strcpy(xmlp->idEstadoCivilSelecionado,tPessoaFisica.szIdEstadoCivil);
	strcpy(xmlp->idSexoSelecionado,tPessoaFisica.szIdSexo);
}
