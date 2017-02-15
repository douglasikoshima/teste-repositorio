/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:34:03 $
 **/

#ifndef STWFAtendimentoConta
#define STWFAtendimentoConta

struct st_AtendimentoConta
{
	long idAtendimento;
	char cdConta[256];
	int  cdDigitoConta;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
};

struct st_vlAtendimentoConta
{
	short idAtendimento;
	short cdConta;
	short cdDigitoConta;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
};

struct AtendimentoConta
{
	char cdConta[256];
	char cdDigitoConta[256];
};

#endif
