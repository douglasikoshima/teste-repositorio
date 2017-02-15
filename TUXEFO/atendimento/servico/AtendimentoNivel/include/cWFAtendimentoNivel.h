/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/ 

#ifndef CWFATENDIMENTONIVEL
    #define CWFATENDIMENTONIVEL

#include <tuxfw.h>
#include "stWFAtendimentoNivel.h"

class cWFAtendimentoNivel
{
    public:
        cWFAtendimentoNivel() {entrada=0;saida=0;}
        cWFAtendimentoNivel(st_AtendimentoNivel* dados, st_vlAtendimentoNivel* indicator, XMLGen*xml_g);
        cWFAtendimentoNivel(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAtendimentoNivel() {}

    public:
        long incluir();
        int alterar();
        int excluir();
        bool concluir();
        bool consultar();
        bool ObtemDetalheAtend();
        bool ObtemDetalheAtend( long idAtendimento, int idFase, XMLGen* saida );
        bool ObtemNivGrAt();
        bool ObtemNivGrAt(st_AtendimentoNivel* dados,XMLGen* saida);
        bool ObtemNivGrAt(long idAtendimento, int nrNivel, XMLGen* saida,int *contadorLinhas=0);
		bool ObtemNivGrAt(long idAtendimento, XMLGen* saida,int *contadorLinhas=0);        
		bool ObtemNivContato();
        bool ObtemNivContato(st_AtendimentoNivel* dados,XMLGen* saida);
        bool ObtemNivContato(int idContato, int idFase, XMLGen* saida);
        bool ObterHistoricoAtendNivel(long idAtendimento,int idFase,int idFaseAtual,XMLGen* saida);
        bool ObterHistoricoAtendNivelEx(long idAtendimento,int idFase,int idFaseAtual,XMLGen* saida);
        bool ObterHistoricoAtendNivelMC1( long idAtendimento,XMLGen* saida );

    private:
        void carregaDados();

    public:

        int nrNivelAt;

        st_AtendimentoNivel m_stDados;
        st_vlAtendimentoNivel m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;
};

#endif
