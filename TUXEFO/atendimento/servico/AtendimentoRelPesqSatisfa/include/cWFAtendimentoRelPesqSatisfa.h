/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:42 $
 **/

#include <tuxfw.h>
#include "../include/stWFAtendimentoRelPesqSatisfa.h"

class cWFAtdRelPSS : public TuxBasicSvc
{
    public:
        st_AtdPesquisa   m_stDados;
        st_vlAtdPesquisa m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    public:
        cWFAtdRelPSS() {entrada=0;saida=0;}
        cWFAtdRelPSS(DOMNode* entrada,XMLGen* saida);
        ~cWFAtdRelPSS() {}
        void Executar();

    private:
        void carregarDados();
        void ObterDadosAtd();
        void ObterDadosQuestionario();
} ;

