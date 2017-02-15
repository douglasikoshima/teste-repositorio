/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:20 $
 **/

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "../../../commons/definesAtendimento.h"
#include "../../../../atendimento/servico/Atendimento/include/cWFAtendimento.h"

#include "stWFAtdDetAcaoEx.h"

class WFAtdDetAcaoEx : public TuxBasicSvc
{
    public:
        WFAtdDetAcaoEx() {}
        WFAtdDetAcaoEx(DOMNode* entrada,XMLGen* saida);
        ~WFAtdDetAcaoEx() {}

    public:
        bool executar();
        inline void setarIdUsuario(int valor) { idUsuario = valor; }
        inline int obterIdUsuario() { return idUsuario; }

    public:
        st_AtdDetAcao   m_stDados;
        st_vlAtdDetAcao m_vlDados;

        st_AtdBaixa m_stAtdBaixa;
        st_vlAtdBaixa m_vlAtdBaixa;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        DetalheAtendimento detalheAtendimento;
        int idUsuario;

        SmallString xmlAcCompletas;
        SmallString xmlObtemAcoes;

    private:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }
        
        void ObterDetalheAtdEx();
        void getUsuarioCri(int idPessoaUsuario);
    
        bool usuarioPossuiPerfilAbertura();

        void ObterAcoesEx(XMLGen* _montagem);

        void carregaDados();

        bool processoCriSimNao();
} ;
