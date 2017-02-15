/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:28 $
 **/

#ifndef __CWFATDPESQESTSUB_H__
#define __CWFATDPESQESTSUB_H__

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdPesqEstSub.h"

class cWFAtdPesqEstSub : public TuxBasicSvc
{
    public:
        cWFAtdPesqEstSub() { entrada=0; saida=0; }
        cWFAtdPesqEstSub(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdPesqEstSub() {}

    public:
        bool Executar();
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    private:
        bool inPortOut;
        int idUsuario;
        st_AtdPesqEstSub m_stDados;
        st_vlAtdPesqEstSub m_vlDados;
        DOMNode *entrada;
        XMLGen *saida;
        TuxHelper tx;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        bool ConsultarEstado(char **codErro,char **msgErro);
        void carregaDados();
} ;

#endif
