/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#include<tuxfw.h>

#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAndamento.h"

#ifndef CWFANDAMENTO
    #define CWFANDAMENTO

class cWFAndamento
{
    public:
        cWFAndamento() { entrada = 0; saida = 0; }
        cWFAndamento(st_Andamento* dados, st_vlAndamento* indicator, XMLGen*xml_g);
        cWFAndamento(st_AndamentoEx* dados, st_vlAndamento* indicator, XMLGen*xml_g);
        cWFAndamento(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAndamento() {}
        long incluir(XMLDPR *xmlDpr);
        long incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        long incluir(st_Andamento* dados, st_vlAndamento* status,XMLDPR *xmlDpr,XMLGen *saida=0);
        long alterar();
        //int excluir();
        bool consultar();
        //bool ObtemHistAtend();
        bool ObtemHistPsq();
        bool ObtemHistAtendEstado(st_Andamento* dados, st_vlAndamento* status, XMLGen* saida);
        bool ObtemHistAtendEstadoEx(st_Andamento* dados, st_vlAndamento* status, XMLGen* saida);
        bool ObtemHistAtendEstado();
        bool ObtemHistAtendEstadoEx();
        bool ObtemHistAtendEstadoCRI();

    private:
        void carregaDados();

    public:
        st_Andamento m_stDados;
        st_AndamentoEx m_stDadosEx;
        st_vlAndamento m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;
};

#endif
