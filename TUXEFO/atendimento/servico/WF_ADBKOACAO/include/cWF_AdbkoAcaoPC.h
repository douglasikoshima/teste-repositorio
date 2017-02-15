#ifndef __CWFADBKOACAO_H__
#define __CWFADBKOACAO_H__

#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

#define SUCESSO 0
#define ERRO    1

class cWF_AdquirirBko : public cWF_Acao
{
    public:
         cWF_AdquirirBko(DOMNode*,XMLGen*,char *);
         ~cWF_AdquirirBko() {idUsuarioCRIAlterado=0;};

    public:
        void Executar();

    private: 
        bool adquirirCRI();
        int idUsuarioCRIAlterado;
};

#endif // __CWFADBKOACAO_H__
