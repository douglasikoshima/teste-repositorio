#ifndef _IcWFAtdTratamentoCri_
#define _IcWFAtdTratamentoCri_
/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/ 
 
 
#include <tuxfw.h>
#include "stWFAtdTratamentoCri.h"
#include "../../Usuario/include/cWFUsuario.h"

class cWFAtdTratamentoCri
{

    st_AtdTratamentoCri     *m_stDados;
    st_vlAtdTratamentoCri   *m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;
    bool alocado;

	public:
		cWFAtdTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* indicator, XMLGen*xml_g);
        cWFAtdTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
		cWFAtdTratamentoCri(DOMNode*dnode, XMLGen*xml_g);
        cWFAtdTratamentoCri() {m_stDados=0;m_vlDados=0;entrada=0;saida=0;alocado=false;}
        ~cWFAtdTratamentoCri();

		long incluirAtendimentoCri();
		long incluirTratamentoCri();
        long incluirTratamentoGrupoCri();

		long alterarAtendimentoGrupoAtualCri();
        long alterarTratamentoGrupoCri();
		long alterarGrupoCri();
		long alterarAtendimentoCri();
		long alterarTratamentoCri();
		long alterarUsuarioDevolucaoCri();
		long alterarGrupoDevolucaoCri();

        int incAtendimentoCriQtEnvioBKO(long idAtendimento);

		long excluirTratamentoCri();
        long excluirAtendimentoCri(long idAtendimento);

		int  getUsuarioAtualCri(long idAtendimento);
		int  getGrupo();
		long getPessoaLinhaHistorico();
		int  getIdPessoaUsuarioCri();

        bool getGrupoCri();
        bool getGrupoCri(long idAtendimento);
        bool getGrupoCriEx(long idAtendimento);
        bool getTratamentoCri();
        bool getTratamentoGrupoCri();
        bool getAtendimentoCri();
        bool getAtendimentoCriEx();
        bool processoDentroPrazoRetInboxSimNao(long idAtendimento);
        bool usuarioIsSupervisor(int idGrupo);

        void atualizarPrazoCRI(long idAtendimento);

	private:
		void carregaDados();

};
#endif
