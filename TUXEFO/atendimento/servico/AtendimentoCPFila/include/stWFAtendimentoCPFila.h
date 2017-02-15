/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  
 * @author  Charles Santos
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:37:07 $
 **/

#ifndef STWFATENDIMENTOCPFILA
    #define STWFATENDIMENTOCPFILA

struct st_AtendimentoCPFila
{
  long idAtendimento ;
  int  idGrupoOrigem;             
  int  idPessoaUsuarioOrigem;
  int  inContatoPrevioRealizado;
  int  nrTentativas;
  char dtCalculada[64];
  int  idUsuarioAlteracao;
  char dtUltimaAlteracao[64];
};

struct st_vlAtendimentoCPFila
{
  short idAtendimento ;
  short idGrupoOrigem;             
  short idPessoaUsuarioOrigem;
  short inContatoPrevioRealizado;
  short nrTentativas;
  short dtCalculada;
  short idUsuarioAlteracao;
  short dtUltimaAlteracao;
} ;

struct FetchResultado
{
    long idAtendimento ; 
    int idGrupoEncaminhamento ;
    long idPessoaUsuarioAtual;
    int idGrupoAtual;
    char dtCalculada[64];
    int idUsuario;
    int idContato;
    int idCanal;
    int idProcedencia ;
    int idSegmentacao;
    int idTipoCarteira ;
    int idUFOperadora;
};


struct st_AtdCprevio
{
  double            nrCriticidade;
  long idAtendimento;
  char dtAbertura[64];
  char dtTPrazoFinalCPrevio[64];
  int idContato;
  int idCanal;
  int idProcedencia;
  int idSegmentacao; 
  int idTipoCarteira;
  long idPessoaUsuarioAtual;
  int idGrupoAtual;
  int idUFOperadora;
};

struct st_vAtdCprevio
{
  short nrCriticidade;
  short idAtendimento;
  short dtAbertura;
  short dtTPrazoFinalCPrevio;
  short idContato;
  short idCanal;
  short idProcedencia;
  short idSegmentacao;
  short idTipoCarteira;
  short idPessoaUsuarioAtual;
  short idGrupoAtual;
  short idUFOperadora;
};

#endif


