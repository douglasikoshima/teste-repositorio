/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.4.6.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:11:38 $
 **/

#ifndef CWFATENDIMENTO
	#define CWFATENDIMENTO

#include <tuxfw.h>
#include "stWFAtendimento.h"
#include "stWFAtendimentoFila.h"

#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "../../../commons/definesAtendimento.h"
#include "../../../commons/Collection/include/Collection.h"

#define STRLENNULL( y ) ( y == NULL ? 0 : strlen( y )  )

class cWFAtendimento
{
	st_Atendimento			m_stDados;
	st_vlAtendimento		m_vlDados;

	st_AtendimentoFila		m_stFila;
	st_vlAtendimentoFila	m_vlFila;

    DadosAtendimento dadosAtendimento;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
        cWFAtendimento();
        ~cWFAtendimento();
        cWFAtendimento(st_Atendimento* dados, st_vlAtendimento* indicator, XMLGen*xml_g);
        cWFAtendimento(DOMNode*dnode, XMLGen*xml_g);
        long incluir(); // *** não usar!! mantido para compatibilidade com o SSKlunk ***
        long incluir(XMLDPR *xmlDpr);
        long incluir(st_Atendimento* dados, st_vlAtendimento* status,XMLDPR *xmlDpr);
        long alterar(); // *** não usar!! mantido para compatibilidade com o SSKlunk ***
        long alterar(XMLDPR *xmlDpr);
        long excluir(); // *** não usar!! mantido para compatibilidade com o SSKlunk ***
        long excluir(XMLDPR *xmlDpr);
        void diasUteis();
        void diasUteis(char* telefone,double horas,int incremento,char* data);
        int  ObterTipoGrupo( long idAtendimento);

        bool consultar();
        bool consultarEx();
        bool consultarBox();
        bool consultarBox(int idPessoaUsuario);
        bool consultarRelacionamento();
        bool consultarRelacionamentoMig();
        bool consultarProtocolos();
        bool consultarProtocolosMig();
        bool consultarAlerta(long* _idAtendimento, XMLGen* saida);
        bool consultarAlertaEx(long* _idAtendimento, XMLGen* saida);
        bool consultarAlerta(long* _idAtendimento, int _isSimplificado, XMLGen* saida);
        bool consultarAlertaEx(long* _idAtendimento, int _isSimplificado, XMLGen* saida);
        bool consultarMassa();
        bool ObtemAtend(long * idAtendimento=0);
        bool ObtemContasCliente();
        bool ObtemContasCliente(int idPessoaDePara, int idConta, int idTipoLinha, int idUfOperadora, XMLGen* _saida=0);
        bool ObtemLinhas();
        bool ObtemPessoa();
        bool ObtemCanal();
        bool ObtemTipoComunic();
        bool ObtemComunicacao();
        bool ObtemPessoaComunic();
        bool ObtemPessoaComunic(int idPessoaDePara,XMLGen* _saida);
        bool ObtemPessoaComunic(int idPessoaDePara, long idPessoa, XMLGen* _saida);
        bool ObtemDetalheAtend();
        bool ObtemDetalheAtend(long idAtendimento,XMLGen* saida);
        bool ObtemDetalheAtend(long idAtendimento,XMLGen* saida,int *idAgrEstadoTProc);
        bool ObtemDetalheAtend(long idAtendimento,int * idContato );
        bool ObtemDetalheAtend(long idAtendimento,DetalheAtendimento *pDetalheAtendimento);
        bool ObtemDetalheAtend( long idAtendimento, DetalheAtendimento *pDetalheAtendimento, char * c_idAtendimentoOrigem );
        bool ObtemDetalheAtendEx(long idAtendimento, DetalheAtendimento *pDetalheAtendimento );
        bool ObtemDetalheAtendEx_Migracao(long idAtendimento, DetalheAtendimento *pDetalheAtendimento );
        bool ObtemReaberAtend();
        bool ObtemReaberAtend(long idAtendimento,XMLGen* saida,DetReabertura *detReabertura=0);
        bool ObterTipoRetornoAtendimento(long idAtendimento, int *tipoRetorno );
        bool ObterNrProtocolo(long idAtendimento,char * idAtendimentoProtocolo);
        void AtualizarTipoRetornoContato(long idAtendimento,int idTipoRetornoContato,XMLDPR *xmlDpr);

        char *ObterDtAbertura();
        char *ObterDtPrazoFinalAnatel();
        char *ObterDtPrazoFinalInterno();
        char *ObterDtUltimaAlteracao();
        char *ObterPsqNrProtocolo();
        bool ObterMigracao();
        int ObterIdCanal();
        int ObterIdContato();
        //int ObterIdDiscador();
        int ObterIdFase();
        int ObterIdGrupoAbertura();
        long ObterIdPessoaUsuarioAbertura();
        int ObterIdProcedencia();
        int ObterIdSegmentacao();
        int ObterIdTipoCarteira();
        int ObterIdTipoRetornoContato();
        int ObterIdUsuarioAlteracao();
        int ObterInAlarme();
        int ObterNrNivel();
        int ObterQtHorasPrazoAtendimento();
        int ObterQtInsistencia();
        int ObterTipoRetornoContato(long idAtendimento);
        char *ObterVlPesoAtendimento();

		//Pesquisas de fila BKO (Atendimento back office)
        bool consultarFila();
        bool consultarFila(int idPessoaUsuario);
        bool consultarFila(Collection* _grupos);
        bool consultarFila(Collection* _grupos, int idPessoaUsuario);

		//Pesquisas de fila de portabilidade
        bool consultarFilaPortOut(Collection* _grupos, int idPessoaUsuario);
        bool consultarFilaPortOutConsRelacionamento(Collection* _grupos, int idPessoaUsuario);
        bool consultarFilaPortOutSemConsRelacionamento(Collection* _grupos, int idPessoaUsuario);

		//Pesquisas de fila de consultores "Meu clientw"
        bool consultarFilaMeuCliente(Collection* _grupos, int idPessoaUsuario);
        bool consultarFilaMCSemConsRelacionamento(Collection* _grupos, int idPessoaUsuario);
        bool consultarFilaMCSemConsRelacionamentoMP(Collection* _grupos, int idPessoaUsuario);
        //bool consultarFilaMCConsRelacionamento(Collection* _grupos, int idPessoaUsuario);


		//Pesquisas de fila CRI (Consultor de relacionamento de incidências)
        bool consultarFilaCRI(Collection* _grupos);
        bool consultarFilaCRI(Collection* _grupos, int idPessoaUsuario);

		//Pesquisas de fila RC (Resposta ao cliente)
        bool consultarFilaRC(Collection* _grupos, int idPessoaUsuario);
        bool consultarMassaRC();
        bool consultarBoxRC() ;       
        bool consultarBoxRC(int idPessoaUsuario);

	private:
        bool inMigracao;
		void carregaDados();
		void carregaDadosConsultaFila();
        void carregaDadosConsultaFilaRC();
};

#endif
