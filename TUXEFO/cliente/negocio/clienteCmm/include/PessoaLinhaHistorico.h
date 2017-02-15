///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaLinhaHistorico
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOALINHAHISTORICO
#define PESSOALINHAHISTORICO

#include "Global.h"
#include "PessoaLinhaHistoricopc.h"

class CPessoaLinhaHistorico
{
public:
  TPessoaLinhaHistorico tPessoaLinhaHistorico;
  CPessoaLinhaHistoricopc clPessoaLinhaHistoricopc;

  CPessoaLinhaHistorico(void);  
  void inserePessoaLinhaHistorico(void);

  void setIdPessoaDePara(char *pszIdPessoaDePara);
  void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
  void setIdTipoRelacionamento(char *pszIdTipoRelacionamento);
};

#endif

