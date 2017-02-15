/**
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Charles Machado dos Santos
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef __RESPCLIENTEPC_H__
#define __RESPCLIENTEPC_H__

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#undef SQLCA
#define SQLCA_NONE
#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
#include <map>
using namespace std;

#define NR_TENTATIVAS_MAX 4

struct Resposta
{
    int nrTentativas;
    int idGrupoOrigem;
    int idPessoaUsuarioOrigem;
    int inContatoPrevioRealizado;

    Resposta() { memset(this,0,sizeof(Resposta)); }
};


#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------		
// cRespCliente 
//------------------------------------------------------------------------------		
class cRespCliente : public cWF_Acao
{
	public:
		 cRespCliente(DOMNode*, XMLGen* , char *);
        ~cRespCliente(){};

	public:
		void Executar(DOMNode*entrada, XMLGen*saida); 

	private:
        bool AtualizarRespCliente(long _idAtendimento
                                 ,unsigned long _idUsuarioAlteracao
                                 ,unsigned long _idUsuarioAtual
                                 ,unsigned long _idGrupoAtual
                                 ,const char *_dsMotivo
                                 ,Resposta &resposta);

        bool ehRetorno(unsigned long _idAgrupamentoEstadoTpProc);

        void sql_error_WFRespCliente(sqlca*sqlca);

    private:
         
};

#endif
