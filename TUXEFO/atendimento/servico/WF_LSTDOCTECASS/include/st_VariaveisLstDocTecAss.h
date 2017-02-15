#ifndef STVARIAVEISLSTDOCTECASS
	#define STVARIAVEISLSTDOCTECASS

struct st_VariaveisLstDocTecAss
{
   char idAtendimento[23];
   char idAtendimentoDocumento[23];
   char idDocumentoTecnico[23];
   char dsTipoDocumentoProcesso[256];
   char nrDocumento[256];
   char dtAbertura[20];
   char dtEstimadaFechamento[20];
   char dtFechamento[20];
   char dsDocumento[256];
   char dsResposta[501];
   char idUsuarioAlteracao[23];
   char dtUltimaAlteracao[20];
   char idDocumentoTecnicoTipo[23];
   char ComentarioAbertura[256];
   char ComentarioFechamento[256];
   char inEstadoTecnico[23];
};

#endif
