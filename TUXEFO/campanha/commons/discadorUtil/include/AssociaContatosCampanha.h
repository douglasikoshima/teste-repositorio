/**
 * 
 * @modulo  Commons
 * @usecase Associa contatos a ligação das campanhas.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:32 $
 **/ 

#include "cDiscadorUtil.h"

class AssociaContatosCampanha: private cDiscadorUtil
{

	public:

		AssociaContatosCampanha();
		AssociaContatosCampanha(DOMNode*dnode, XMLGen*xml_g);		
		
		int   getNPU();
		int   getIdCampanha();
		char* getHoraLigacoesInicio();
		char* getHoraLigacoesFim();
		char* getDataHoraCampanhaInicio();
		char* getDataHoraCampanhaFim();         
		char* getDataHoraLigacaoInicio();
		char* getDataHoraLigacaoFim();
		int   getNumeroTentativas();
		int   getIntervaloTentativa();
		int   getIdProcessoRetorno();
		char* getFileName();

		void setNPU(int npu);
		void setIdCampanha(int idCampanha);
		void setHoraLigacoesInicio(char* horaInicio);
		void setHoraLigacoesFim(char* horaFim);
		void setDataHoraCampanhaInicio(char* dataHoraInicio);
		void setDataHoraCampanhaFim(char* dataHoraFim);
		void setDataHoraLigacaoInicio(char* dataHoraLigacaoInicio);
		void setDataHoraLigacaoFim(char* dataHoraLigacaoFim);
		void setNumeroTentativas(int numeroTentativas);
		void setIntervaloTentativa(int intervaloTentativa);
		void setIdProcessoRetorno(int idProcessoRetorno);
		void setFileName(char* fileName);

		void enviar();

};
