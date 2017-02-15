#ifndef PESSOALINHAHISTORICOPORT
#define PESSOALINHAHISTORICOPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "PessoaLinhaHistoricopcPORT.h"

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
  void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
  void setCdAreaRegistro(char *pszCdAreaRegistro);
  void setNrLinha(char *pszNrLinha);

  char *getIdPessoaLinhaHistorico(void);

};

#endif
