/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Miguel Angel Benavente
 * @version $Revision: 1.1.2.3.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:03:39 $
 **/


#ifndef STWFANDAMENTOMOTIVO
	#define STWFANDAMENTOMOTIVO

struct st_AndamentoMotivo
{
	long idAndamento;
	int  idMotivo;
	int  idFase;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAndamentoMotivo
{
	short idAndamento;
	short idMotivo;
	short idFase;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

