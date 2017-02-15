/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:04 $
 **/

#ifndef STWFATENDIMENTOFRMCAMPO
	#define STWFATENDIMENTOFRMCAMPO

struct st_AtendimentoFrmCampo
{
	long idAtendimentoFrmCampo;
	long idAtendimentoFrm;
    long idAtendimento; // Usado pelo DPR
	int  idCampo;
	char dsValor[256];
	int  idDominio;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoFrmCampo
{
	short idAtendimentoFrmCampo;
	short idAtendimentoFrm;
    short idAtendimento; // Usado pelo DPR
	short idCampo;
	short dsValor;
	short idDominio;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

