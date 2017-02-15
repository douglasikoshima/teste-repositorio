/**
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Marcelo Nunes
 * @version $Revision: 1.1.2.5 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:15 $
 **/

#include <tuxfw.h>

#ifndef SQLCA
    #define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>
#endif

#include "../../REGPALITAGEM/include/cRegPalitagem.h"
#include "stRegPalitageFOPC.h"

class CRegPalitageFO : public TuxBasicSvc
{
    private:
        cRegContato cregcontato;
        st_DadosRegPalitagem dados;

    public:

        CRegPalitageFO() {}
        ~CRegPalitageFO() {}

        void executar( DOMNode * dnode, char * idUsuario );
        char *getIdAtendimentoProtocolo() { return cregcontato.getIdAtendimentoProtocolo(); }
        long getIdAtendimento() { return cregcontato.getIdAtendimento(); }

    private:
        void carregaDados( DOMNode * dnode);
        void SelCfgPalitagem();
        void SelAtdProtocolo();
        void LePalitagemDefault();
        void sql_error_WFCRegPalitageFO( sqlca * sqlca );
        bool proCExisteOrdemVenda(const char *nrOrdemVenda);
};
