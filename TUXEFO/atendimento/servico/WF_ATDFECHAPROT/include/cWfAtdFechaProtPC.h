/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:38 $
 **/

#ifndef __CWFATDFECHAPROTPC_H__
#define __CWFATDFECHAPROTPC_H__

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#include <tuxfw.h>

#include "stWfAtdFechaProt.h"

class cWfAtdFechaProtPC
{
    public:
        TuxHelper tx;

    private:
        struct sqlca sqlca;

    public:
        cWfAtdFechaProtPC() {}
        ~cWfAtdFechaProtPC() {}

    public:
        void FecharProtocolo(st_DadosEntrada *dados,st_StatusEntrada *status);

    private:
        void sql_error_WfAtdFechaProtPC();

};

#endif // __CWFATDFECHAPROTPC_H__
