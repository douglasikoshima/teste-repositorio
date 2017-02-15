/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.126.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/12/05 13:13:12 $
 **/

#ifndef __CWFATDABREPROTPC_H__
#define __CWFATDABREPROTPC_H__

//#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#include "stWfAtdAbreProt.h"

class cWfAtdAbreProtPC
{
    public:
        cWfAtdAbreProtPC() {}
        ~cWfAtdAbreProtPC() {}

    public:
        void CriarNovoProtocolo(st_DadosEntrada *dados,st_StatusEntrada *status);
        void CriarNovoProtocolo(st_DadosEntrada *dados,st_StatusEntrada *status,char * idLinhaTelefonica);

    private:
        void sql_error_WfAtdAbreProtPC(sqlca *sqlca);

};

#endif // __CWFATDABREPROTPC_H__
