/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#ifndef __CWFATDALTERPROTPC_H__
#define __CWFATDALTERPROTPC_H__

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#include "stWfAtdAlterProt.h"

class cWfAtdAlterProtPC
{
    private:
        struct sqlca sqlca;

    public:
        cWfAtdAlterProtPC() {}
        ~cWfAtdAlterProtPC() {}

    public:
        void AlterarDadosProtocolo(st_DadosEntradaAltProt *dados,st_StatusEntradaAltProt *status);
        void DecrementarPendentes(const char *idAtendimentoProtocolo,const char *incPendente="0",const char *idPessoaUsuarioWeb="1");
        void IncrementarQuantidades(const char *idAtendimentoProtocolo,const char *incAberto="0",const char *incPendente="0",const char *idPessoaUsuarioWeb="1");
    private:
        void sql_error_WfAtdAlterProtPC();
};

#endif // __CWFATDALTERPROTPC_H__
