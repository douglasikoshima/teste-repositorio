/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  
 * @author  Charles Santos
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/

#ifndef __STWFATENDIMENTOPAUSA_H__
    #define __STWFATENDIMENTOPAUSA_H__

struct st_AtendimentoPausa
{
  long idAtendimento ;
  char dtFimPausaAtendimento[64];
  int  idUsuarioAlteracao;
  char dtUltimaAlteracao[64];
};

struct st_vlAtendimentoPausa
{ 
  short idAtendimento;
  short dtFimPausaAtendimento;
  short idUsuarioAlteracao;
  short dtUltimaAlteracao;
};

#endif
