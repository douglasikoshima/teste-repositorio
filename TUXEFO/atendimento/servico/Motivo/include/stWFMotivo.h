/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.114.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/


#ifndef _STWFMOTIVO_H_
	#define _STWFMOTIVO_H_

struct st_Motivo
{
	int idMotivo;
    int inVisivel;
	//int idTabelaMotivo;
	int idUsuarioAlteracao;
	char dsMotivo[256];
	char dtUltimaAlteracao[24];
	char dtExclusao[24];
} ;

struct st_vlMotivo
{
	short idMotivo;
    short inVisivel;
	//short idTabelaMotivo;
	short idUsuarioAlteracao;
	short dsMotivo;
	short dtUltimaAlteracao;
	short dtExclusao;
} ;

#endif

