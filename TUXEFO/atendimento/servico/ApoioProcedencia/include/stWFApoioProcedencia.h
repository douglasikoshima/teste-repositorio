
#ifndef STWFAPOIOPROCEDENCIA
	#define STWFAPOIOPROCEDENCIA

struct st_ApoioProcedencia
{
	int  idProcedencia;
	char sgProcedencia[256];
	char dsProcedencia[256];
	int  vlPeso;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlApoioProcedencia
{
	short idProcedencia;
	short sgProcedencia;
	short dsProcedencia;
	short vlPeso;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

