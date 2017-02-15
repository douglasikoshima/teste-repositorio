#ifndef STVARIAVEISLSTDOCTECASS
	#define STVARIAVEISLSTDOCTECASS

struct st_VariaveisLstEstDocs
{
   char idDocumentoTecnicoTipo[23];
   char dsDocumentoTecnicoTipo[256];
};

struct st_vlVariaveisLstEstDocs
{
   short idDocumentoTecnicoTipo;
   short dsDocumentoTecnicoTipo;
};

struct st_VariaveisApoioProced
{
   char idProcedencia[23];
   char sgProcedencia[256];
   char dsProcedencia[256];
   char vlPeso[23];
   char idUsuarioAlteracao[23];
   char dtUltimaAlteracao[20];
};

struct st_vlVariaveisApoioProced
{
   short idProcedencia;
   short sgProcedencia;
   short dsProcedencia;
   short vlPeso;
   short idUsuarioAlteracao;
   short dtUltimaAlteracao;
};

#endif
