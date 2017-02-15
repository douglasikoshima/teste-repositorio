/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:39:15 $
 **/

#ifndef STWFATENDIMENTODOCUMENTO
	#define STWFATENDIMENTODOCUMENTO

struct st_AtendimentoDocumento
{
	long idAtendimentoDocumento;
	long idAtendimento;
	int  idDocumentoTecnico;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoDocumento
{
	short idAtendimentoDocumento;
	short idAtendimento;
	short idDocumentoTecnico;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

