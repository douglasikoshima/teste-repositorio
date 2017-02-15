/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:19 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "stWFAtdHistorico.h"

#include "../../Andamento/include/cWFAndamento.h"

class cWFAtdHistorico : public TuxBasicSvc
{
    public:
        cWFAtdHistorico() {}
        cWFAtdHistorico(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdHistorico() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_Andamento   m_stDados;
        st_vlAndamento m_vlDados;

        st_AtdHistoricoPesq   m_stAtdHistorico;
        st_vlAtdHistoricoPesq m_vlAtdHistorico;

        st_AtdBaixa m_stAtdBaixa;
        st_vlAtdBaixa m_vlAtdBaixa;

        st_GrupoAtendimento m_stGrupoAtendimento;
        st_vlGrupoAtendimento m_vlGrupoAtendimento;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;

        SmallString xmlHistAtEst;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        void carregaDados();
} ;
