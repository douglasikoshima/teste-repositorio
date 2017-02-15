/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/

#ifndef STWFATENDIMENTOPRIORIZACAO
	#define STWFATENDIMENTOPRIORIZACAO

struct st_AtendimentoPriorizacao
{
	char dtAtualizacao[256];
    char nmCor[256];
	 long idAtendimento;
	int idAlerta;
    char nrCriticidade[16];
    int  nrTentativas;
} ;

struct st_vlAtendimentoPriorizacao
{
	short dtAtualizacao;
    short nmCor;
	short idAtendimento;
	short idAlerta;
    short nrCriticidade;
    short nrTentativas;
} ;

#endif

