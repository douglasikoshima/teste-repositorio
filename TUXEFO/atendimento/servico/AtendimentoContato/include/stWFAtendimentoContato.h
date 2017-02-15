/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:36:02 $
 **/

#ifndef STWFATENDIMENTOCONTATO
	#define STWFATENDIMENTOCONTATO

struct st_AtendimentoContato
{
	long idAtendimento;
	char nmContato[256];
	char nrTelefoneContato[32];
	int  cdAreaRegistro;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
    char nmFalandoCom[256];
} ;

struct st_vlAtendimentoContato
{
	short idAtendimento;
	short nmContato;
	short nrTelefoneContato;
	short cdAreaRegistro;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
    short nmFalandoCom;
} ;

struct AtendimentoContato
{
    long idAtendimento;
    char nmContato[256];
    char nmFalandoCom[256];
    char nrTelefoneContato[256];
};

#endif

