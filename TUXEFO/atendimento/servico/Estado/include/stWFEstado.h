#ifndef STWFESTADO
	#define STWFESTADO

struct st_Estado
{
	int  idEstado;
	char sgEstado[256];
	char dsEstado[256];
	int  inFiltro;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[20];
} ;

struct st_vlEstado
{
	short idEstado;
	short sgEstado;
	short dsEstado;
	short inFiltro;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif


