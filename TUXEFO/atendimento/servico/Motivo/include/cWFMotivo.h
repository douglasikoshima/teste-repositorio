/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:51 $
 **/


#ifndef _CWFMOTIVO_H
    #define _CWFMOTIVO_H

#include<tuxfw.h>
#include "stWFMotivo.h"
#include "../../../commons/Collection/include/Collection.h"

class cWFMotivo
{
    st_Motivo   m_stDados;
    st_vlMotivo m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFMotivo();
        cWFMotivo(DOMNode*dnode, XMLGen*xml_g);
        cWFMotivo(st_Motivo* dados, st_vlMotivo* status, XMLGen*xml_g);
        
        int alterar();
        int consultar();
        int consultar(Collection *registrosMotivo);
        int excluir();
        int incluir();

        bool obterIdMotivo(char *dsMotivo,int &idMotivo);
        bool ObterMotivoEncAutomatico(int &idMotivo);

        void setarIdMotivo(int);
        void setarIdTabelaMotivo(int);
        void setarIdUsuarioAlteracao(int);

        void setarIdMotivo(char *valor) { if (valor) setarIdMotivo(atoi(valor)); }
        //void setarIdTabelaMotivo(char *valor) { if (valor) setarIdTabelaMotivo(atoi(valor)); }
        void setarIdUsuarioAlteracao(char *valor) { if (valor) setarIdUsuarioAlteracao(atoi(valor)); }

        void setarDsMotivo(char *);
        void setarDtUltimaAlteracao(char *);

    private:
        void carregaDados();
};

#endif
