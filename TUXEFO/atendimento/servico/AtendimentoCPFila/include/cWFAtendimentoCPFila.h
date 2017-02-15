/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  
 * @author  Charles Santos
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/

#ifndef CWFATENDIMENTOCPFILA
    #define CWFATENDIMENTOCPFILA

#include <tuxfw.h>
#include "stWFAtendimentoCPFila.h"

class cWFAtendimentoCPFila
{

    st_AtendimentoCPFila    m_stDados;
    st_vlAtendimentoCPFila  m_vlDados;


    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFAtendimentoCPFila(st_AtendimentoCPFila* dados, st_vlAtendimentoCPFila* status);
        cWFAtendimentoCPFila(st_AtendimentoCPFila* dados, st_vlAtendimentoCPFila* status, XMLGen*xml_g);
        cWFAtendimentoCPFila(DOMNode*dnode, XMLGen*xml_g);
       
        long incluir();
        long incluir(st_AtendimentoCPFila* dados, st_vlAtendimentoCPFila* status);
        long alterar();
        long alterar(st_AtendimentoCPFila* dados);
        long excluir();
        bool consultar();
        bool ExistAtendimento(long _idAtendimento) ;
        long obterRegistroCPFila(long sIdAtendimento,st_AtendimentoCPFila* dados);
        bool obterAtendimentoGrupoRCConfigurado( FetchResultado &_dFetchResultado,int &idGrupoEncaminhamento,int _idUsuarioGrupo);
        bool obterGrupoRCConfigurado(long _idAtendimento ,int _idContato ,int _idCanal,int _idProcedencia ,int _idSegmentacao ,int _idTipoCarteira,int _idUFOperadora ,int &idGrupoEncaminhar ,int _idUsuarioGrupo);
        bool ObterAtdCPrevio(long idAtendimento,struct st_AtdCprevio *dados );
        int  GetGruposAssociadosRC( int _idUsuario );

    private:
        void carregaDados();

};

#endif