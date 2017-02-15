/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:52 $
 **/

#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "../include/stWFAtendimentoObservacao.h"
#include <tuxfw.h>

class cWFAtendimentoObservacao
{
    public:
        DOMNode *entrada;
        XMLGen  *saida;
        bool locado;

        TuxHelper tx;
        st_AtendimentoObservacao *m_stDados;
        st_vlAtendimentoObservacao *m_vlDados;

    public:
        cWFAtendimentoObservacao();
        cWFAtendimentoObservacao(st_AtendimentoObservacao *dados, st_vlAtendimentoObservacao *status, XMLGen*xml_g);
        cWFAtendimentoObservacao(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAtendimentoObservacao();

        void incluir(st_AtendimentoObservacao *dados, st_vlAtendimentoObservacao *status,XMLDPR *xmlDpr);

    private:
        void carregarDados(DOMNode *entrada);

};

