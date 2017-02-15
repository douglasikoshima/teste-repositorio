/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/

#ifndef __CWF_ATENDIMENTOCONTATO_H__
#define __CWF_ATENDIMENTOCONTATO_H__

#include <tuxfw.h>
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoContato.h"

class cWFAtendimentoContato
{

    st_AtendimentoContato*      m_stDados;
    st_vlAtendimentoContato*    m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoContato(st_AtendimentoContato* dados, st_vlAtendimentoContato* indicator, XMLGen*xml_g);
        cWFAtendimentoContato() {entrada=0;saida=0;locado=false;}
        cWFAtendimentoContato(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAtendimentoContato();

        long incluir(XMLDPR *xmlDpr);
        int incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        int alterar();
        int excluir();
        bool consultar();
        bool consultarEx();
        bool ObterAtendimentoContato(long idAtendimento,AtendimentoContato *ac);
        bool ObterAtendimentoContatoEx(long idAtendimento,AtendimentoContato *ac);
        bool ObterAtendimentoContatoEx_Migracao(long idAtendimento, AtendimentoContato *ac );

    private:
        bool locado;

        void carregaDados();

};

#endif
