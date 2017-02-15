/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:38 $
 **/

#ifndef __STWFATDFECHAPROT_H_
#define __STWFATDFECHAPROT_H_

struct st_DadosEntrada
{
    char idAtendimentoProtocolo[39];
    char idSistemaOrigem[39];
    char idUsuarioAlteracao[39];
};

struct st_StatusEntrada
{
    short idAtendimentoProtocolo;
    short idSistemaOrigem;
    short idUsuarioAlteracao;
};

#endif //#ifndef __STWFATDFECHAPROT_H_
