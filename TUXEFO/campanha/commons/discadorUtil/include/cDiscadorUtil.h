/**
 * 
 * @modulo  Commons
 * @usecase Envio de e-mails.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:32 $
 **/ 
#include <tuxfw.h>
#include "stDiscadorUtil.h"
#include "../../Collection/include/Collection.h"

#ifndef CDISCADORUTIL
	#define CDISCADORUTIL

class cDiscadorUtil:public TuxBasicSvc
{

	public:

		char* getEntity();
		char* getSequence();
		void setEntity(char* entity);
		void setSequence(char* sequence);

	protected:

		cDiscadorUtil();
		cDiscadorUtil(DOMNode*dnode, XMLGen*xml_g);		
		~cDiscadorUtil();
		
		int   getNPU();
		int   getIdLigacao();
		int   getIdCampanha();
		char* getHoraInicio();
		char* getHoraFim();
		char* getDataHoraInicio();
		char* getDataHoraFim();         
		char* getDataConsultaInicio();
		char* getDataConsultaFim();         
		char* getDataHoraLigacaoInicio();
		char* getDataHoraLigacaoFim();
		int   getNumeroTentativas();
		int   getIntervaloTentativa();
		int   getIdProcessoRetorno();
		char* getFileName();
		int   getIdUsuario();
		char* getLoginUsuarioCTI();
		char* getLoginPabx();
		char* getChamada();

		int getRegistrosContatoLista();
		int addContatoLista(char* contato);
		char* getContatoLista(int idContato);
		void limpaContatoLista();

		int getRegistrosCampanhaLista();
		int addCampanhaLista(char* contato);
		char* getCampanhaLista(int idContato);
		void limpaCampanhaLista();

		void setNPU(int npu);
		void setIdLigacao(int idLigacao);
		void setIdCampanha(int idCampanha);
		void setListaContatos(char* listaContatos);
		void setHoraInicio(char* horaInicio);
		void setHoraFim(char* horaFim);
		void setDataHoraInicio(char* dataHoraInicio);
		void setDataHoraFim(char* dataHoraFim);
		void setDataConsultaInicio(char* dataHoraInicio);
		void setDataConsultaFim(char* dataHoraFim);
		void setDataHoraLigacaoInicio(char* dataHoraLigacaoInicio);
		void setDataHoraLigacaoFim(char* dataHoraLigacaoFim);
		void setNumeroTentativas(int numeroTentativas);
		void setIntervaloTentativa(int intervaloTentativa);
		void setIdProcessoRetorno(int idProcessoRetorno);
		void setFileName(char* fileName);
		void setIdUsuario(int idUsuario);
		void setLoginUsuarioCTI(char* loginUsuarioCTI);
		void setLoginPabx(char* loginPabx);
		void setChamada(char* chamada);

		void enviar();

		st_DiscadorUtil	m_stDados;
		st_vlDiscadorUtil	m_vlDados;

		TuxHelper tx;

	protected:
		
		Collection* con;
		Collection* cam;

		DOMNode* entrada;
		XMLGen*  saida;

		void carregaDados(); 
};

#endif

