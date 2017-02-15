/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <iostream>
#include <string>
#include <vector>
#include <map>
using namespace std;

#include <tuxfw.h>
#include "../../AtendimentoCommonsRel/include/stWFAtendimentoRel.h"

struct GroupByNaoEncaminhadosKey
{
    string nmGrupo;
    string nmPath;
    string dsTipoRelacionamento;
    string dsTipoCarteira;
    string dsSegmentacao;
    string nmCanal;
    string dsProcedencia;
    string nmGrupoAbertura;
    string dsTipoPessoa;
    string dsTipoLinha;
    long ctAtendimentos;
};

typedef map<string,GroupByNaoEncaminhadosKey> MAP_GRPBYNENC;

class cWFAtdRelNaoEncam : public TuxBasicSvc
{
    public:
        st_AtendimentoRel   m_stDados;
        st_vlAtendimentoRel m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    public:
        cWFAtdRelNaoEncam(DOMNode* entrada,XMLGen* saida);
        bool Executar();

    private:
        void carregaDados();
} ;
