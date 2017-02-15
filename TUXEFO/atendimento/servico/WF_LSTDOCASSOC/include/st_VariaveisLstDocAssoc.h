#ifndef STVARIAVEISLSTDOCASSOC
	#define STVARIAVEISLSTDOCASSOC

struct st_VariaveisLstDocAssoc
{
      char idDocumentoTecnico[23];
      char nrDocumento[256];
		char dtAbertura[20];
      char dtEstimadaFechamento[20];
      char dtFechamento[20];
      char dsDocumento[256];
      char dsResposta[501];
		char idDocumentoTecnicoTipo[23];
      char inEstadoTecnico[2];
      char dsDocumentoTecnicoTipo[256];
      char qtdProcessosVinculados[23];
};

struct st_DocumentoTecnico
{
	int  idDocumentoTecnico;
	char nrDocumento[256];
	char dtAbertura[20];
	char dtEstimadaFechamento[20];
	char dtFechamento[20];
	char dsDocumento[256];
	char dsResposta[501];
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[20];
	int  idDocumentoTecnicoTipo;
	char ComentarioAbertura[256];
	char ComentarioFechamento[256];
	int  inEstadoTecnico;
   char dtInicio[20];
   char dtFinal[20];
   int  idAssociados;
} ;

struct st_vlDocumentoTecnico
{
	short idDocumentoTecnico;
	short nrDocumento;
	short dtAbertura;
	short dtEstimadaFechamento;
	short dtFechamento;
	short dsDocumento;
	short dsResposta;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
	short idDocumentoTecnicoTipo;
	short ComentarioAbertura;
	short ComentarioFechamento;
	short inEstadoTecnico;
   short dtInicio;
   short dtFinal;
   short idAssociados;
} ;

#endif
