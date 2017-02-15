/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.118.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 

#ifndef STWFDOCUMENTOTECNICO
	#define STWFDOCUMENTOTECNICO

struct st_DocumentoTecnico
{
	int  idDocumentoTecnico;
	char nrDocumento[256];
	char dtAbertura[256];
	char dtEstimadaFechamento[256];
	char dtFechamento[256];
	char dsDocumento[256];
	char dsResposta[501];
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
	int  idDocumentoTecnicoTipo;
	char ComentarioAbertura[256];
	char ComentarioFechamento[256];
	int  inEstadoTecnico;
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
} ;

#endif

