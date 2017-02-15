
#ifndef STVARIAVEISGRUPO
	#define STVARIAVEISGRUPO

struct st_VariaveisGrupo
{
   char idUsuario[256];
   char idGrupo[256];
   char dsGrupo[256];
   unsigned long idGrupoPesquisa;
   unsigned long idPessoaUsuarioPesquisa;
   char dtInicio[11];
   char dtFim[11];
   unsigned long inPagina;
} ;

struct st_vlVariaveisGrupo
{
   short idUsuario;
   short idGrupo;
   short dsGrupo;
   short idGrupoPesquisa;
   short idPessoaUsuarioPesquisa;
   short dtInicio;
   short dtFim;
   short inPagina;
} ;


/****************************************/

struct st_VariaveisRegional
{
   char idRegional[21];
   char dsRegional[256];
} ;

struct st_vlVariaveisRegional
{
   short idRegional;
   short dsRegional;
} ;

struct st_VariaveisTipoCarteira
{
   char idTipoCarteira[21];
   char dsTipoCarteira[256];
} ;

struct st_VariaveisSegmentacao
{
   char idSegmentacao[21];
   char dsSegmentacao[256];
} ;

struct st_VariaveisTipoPessoa
{
   char idTipoPessoa[21];
   char dsTipoPessoa[256];
} ;

struct st_VariaveisClassificacaoTipoLinha
{
   char idClassificacaoTipoLinha[21];
   char dsClassificacaoTipoLinha[256];
} ;

struct st_VariaveisAlerta
{
   char idAlerta[21];
   char dsAlerta[256];
} ;

/****************************************/

#endif
