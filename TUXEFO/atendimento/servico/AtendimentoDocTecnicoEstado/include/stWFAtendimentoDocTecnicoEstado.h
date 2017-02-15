
#ifndef STWFATENDDOCTECNESTADO
	#define STWFATENDDOCTECNESTADO

struct st_AtendimentoDocTecnicoEstado
{
	int  idDocumentoTecnicoEstado;
	char dsDocumentoTecnicoEstado[256];
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoDocTecnicoEstado
{
	short idDocumentoTecnicoEstado;
	short dsDocumentoTecnicoEstado;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

