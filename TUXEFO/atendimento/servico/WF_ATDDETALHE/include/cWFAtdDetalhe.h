/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:09 $
 **/

#include <tuxfw.h>

#include "stWFAtdDetalhe.h"

class cWFAtdDetalhe : public TuxBasicSvc
{
    public:
        cWFAtdDetalhe() {}
        cWFAtdDetalhe(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdDetalhe() {}

    public:
        bool executar(char **codErro,char **msgErro);
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdDetalhe   m_stDados;
        st_vlAtdDetalhe m_vlDados;

        st_AtdDetalhePesq   m_stAtdDetalhe;
        st_vlAtdDetalhePesq m_vlAtdDetalhe;

        st_AtdBaixa m_stAtdBaixa;
        st_vlAtdBaixa m_vlAtdBaixa;

        st_GrupoAtendimento m_stGrupoAtendimento;
        st_vlGrupoAtendimento m_vlGrupoAtendimento;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        int idUsuario;


    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

        void carregaDados();
} ;
