

#include <string>
using namespace std;


//////////////////////////////////////////////////////////////////////
// CCoreRetencao.h: interface for the CRelatorioGerenciamento class.
//////////////////////////////////////////////////////////////////////

#include "../../negocio/CRetencaoDao/include/utilretencao.h"
#include "../../negocio/CRetencaoDao/include/CRetencao.h"
#include "../../negocio/CRetencaoDao/include/CRetencaoLinha.h"
#include "../../negocio/CRetencaoDao/include/CRetencaoConsolidada.h"
#include "../../negocio/CRetencaoDao/include/COfertaRealizada.h"
#include "../../negocio/CRetencaoDao/include/CCaractOfAceita.h"
#include "../../negocio/CRetencaoDao/include/CRetencaoAparelho.h"
#include "../../negocio/CRetencaoDao/include/CRetencaoSuspensao.h"
#include "../../negocio/CRetencaoDao/include/CRetencaoBonus.h"
#include "../../negocio/CRetencaoDao/include/CRetencaoPontos.h"
#include "../../negocio/CRetencaoDao/include/CRetencaoPlano.h"
#include "../../negocio/CRetencaoDao/include/CRetencaoMigracao.h"
#include "../../negocio/CRetencaoDao/include/CRetencaoAnalise.h"
#include "../../negocio/CRetencaoDao/include/CApoioRet.h"
#include  "CallTux.h"

#define AGENDOU           1 //agendar - nao utilizado mais
#define CANCELADO		  2 //cancelar linha
#define RETIDO            3 //retido com altuma oferta 
#define LIGACAO_INDEVIDA  4  //operação padrao de ligação indevida
#define VAIPENSAR         5  //vai pensar na oferta ou no detalhamento da oferta
#define RETIDO_RESTRICAO  9  // Retido po restricao  
#define ANALISECRDB      10  // Analise de credito/debito
#define ANALISEENDERECO  11  // Analise de Endereco
#define RETIDO_PORTABILIDADE 12 // Retido em Portabilidade
#define NAO_RETIDO_PORTABILIDADE 13 //Nao  Retido em Portabilidade
#define VAIPENSAR_PORTABILIDADE 14 // Vai pensar em Portabilidade
#define OFERTATROCADA_PORTABILIDADE 15 // Oferta Trocada em Portabilidade -Nao existe caso de uso mas esta publicado para futuras utilizacoes.
#define RETIDO_URA                  16 //retido URA
#define NAORETIDO_URA               17 // nao retido URA
#define RETIDO_IVR                  18 //retido IVR
#define NAORETIDO_IVR               19 // nao retido IVR


#define APARELHO		1
#define BONUS			2
#define PONTOS			3
#define PLANOS			4
#define SUSPENSAO		5
#define MIGRACAO		6
#define ARGUMENTACAO	7


class CCoreRetencao 
{
public:
    DOMNode* pDocURA;
	int Executar(DOMNode*,char *idUser);
	int RegistraContato(DOMNode*,char *idUser);
    void FormataMsgCoreWrkFlow( XMLGen *oEntrada );
	int CoreWorkflow(DOMNode*,char *idUser);
	int GeraMsgRetorno(XMLGen*);
	int ValidaChamadaREGCONTATO();
	char m_nrTelefoneContato[256];         // OS-614 - Telefone de Contato
	int  m_inPortOut;
	char m_idRetencao[21+1];				//é propagado por várias tabelas
	//int  CapturaDadosXMLEntrada(DOMNode*,char *idUser);
    int RetencaoBonusURA(DOMNode *pdnode,char *idUser );

private:
	//variaveis para retencao
	char m_idOfertaRealizada[21+1];			//mantem a oferta aceita para propagar na CARACTERISTICAOFERTAREALIZADA.
	char m_idTipoEncerramento[21+1];		//idtipoencerramento para mantermos os procedimentos atuais para retorno.	
	int iAnalise;
	char m_idPessoaContato[21+1];                  
	bool   m_bAttContato; //os 614

	//variaveis para Registro de Contato 
	char m_idAtendimentoProtocolo[21+1];	//os 799 protocolo
	char m_ddd[4];							//os 799 protocolo
	char m_nrTelAberturaProtocolo[10];		//os 799 protocolo
	int	 m_inControleProtocolo;
	int	 m_inLinhaDiferente;				//OS PROTOCOLO F3

	char m_idPessoaLinhaHistorico[21+1];	//idlinhatelefonica?
	char m_nmContato[255];					//nome do contato(abertura normal)
	char m_observacao[21+1];				//observacao??
	char m_nrTelefoneSMS[21+1];				//telefone para Retorno SMS

	char m_idChamadaTelefonica[21+1];		//valor fixo encaminhamento
	char m_idGrupoAbertura[21+1];			//valor do grupo	encaminhamento
	char m_inResponsavelAbertura[21+1];		//cliente ou usuario	encaminhamento
	char m_tpOperacao[21+1];				//fechamento imediato ou abertura
	char m_idProcedencia[21+1];				//fixo 1 telefone encaminhamento
	char m_idCanal[21+1];					//fixo 16 RETENCAO encaimhamento
	char m_idSegmentacao[21+1];				//segmentacao da linha encaminhamento
	char m_inTipoPessoa[21+1];				//indicador do tipo de pessoa; encaminhamento
	
	char m_idTipoCarteira[21+1];			//carteira do cliente encaimnhamento
	char m_idTipoLinha[21+1];				//tipo da linha (Sempre será POS, mas podera ser GSM OU CDMA); encaminhamento
	char m_idContato[21+1];					//contato a ser registrado
	char m_idConta[21+1];					//conta daquele Cliente

	char m_idUfOperadora[21+1];				//operadora da linha encaminhamento
	char m_idPessoa[21+1];					//idPessoaDepara da linha
	char m_AtendimentoTipoComunicacaoVO[2]; //valor fixo 1--sem função aparente.
	
	char m_idTipoComunicacao[2];			//9 SMS retorno de SMS
	char m_nrTelefone[21+1];				//Telefone do cliente em formato (99)9999-9999
	
	// Variaveis CoreWorkFlow
	char m_idAtendimento[33];
	char m_idUsuario[33];
	char m_idAtividade[17];

	//OS 694 Cancelamento multiplas linhas ao mesmo protocolo
	char m_nrIdAtendimento[21+1];				//id do atendimento a ser anexado.
	int	 m_iAtendimento;						//identificador se a finalizacao tera ou nao mais linhas.

	//OS 686 Portabilidade ativacoes de legados
	int m_iAtivalegado; //controla a ativacao do legado (OBSCURE CODE)
	
	//metodos privados para funcionalidades internas
	void FormataMsgRegContato(XMLGen *oEntrada,DOMNode *dnode);
	void FormataMsgCoreWorkflow(XMLGen *oEntrada);
	void RegContato(XMLGen *oEntrada);
	int  ValidaOferta(char *psgOferta);

	char m_nrLinhaTI[21+1];						//nrTelefone Tela inicial

	char m_nrProtocolo[21+1];				//661 Persistencia e retorno do numero de protocolo na tabela RETENCAOCOSOLIDADA.

};


int CCoreRetencao::ValidaChamadaREGCONTATO()
{
	int iRetorno=-1;
	ULOG("m_nrIdAtendimento:%s",m_nrIdAtendimento);

	if (m_iAtendimento==-1 || !strcmp(m_nrIdAtendimento,"0"))
	{
		iRetorno=0;
	}
	
	return iRetorno;


}

int CCoreRetencao::Executar(DOMNode *pdnode,char *idUser )
{
	int idEncerramento=0;
	m_inControleProtocolo=0;
	int isgOferta=0;
	char szTipoEncerramento[21+1];
	char szidMtOferta[21+1];
	int  iOf=0;

	m_inControleProtocolo=0;

	char sgOf[6];
	char sContatoPrm[256];
	char sParam[256];

	m_inPortOut=-1;
	
    
	memset(sContatoPrm,0x0,sizeof(sContatoPrm)); // OS-614
	memset(sParam,0x0,sizeof(sParam)); // OS-614
	memset(szidMtOferta,0x0,sizeof(szidMtOferta)); // OS-614
	memset(m_idPessoaContato,0x0,sizeof(m_idPessoaContato));
	memset(m_nrTelefoneContato,0x0,sizeof(m_nrTelefoneContato)); 

	memset(m_nrIdAtendimento,0x0,sizeof(m_nrIdAtendimento)); 
	
	memset(m_idAtendimentoProtocolo,0x0,sizeof(m_idAtendimentoProtocolo)); 
	
	m_iAtendimento=-1;
	int icrtAtivaOferta=0;
	
	m_inControleProtocolo=0;

	int idPessoaDePara;

	CRetencao				oRetencao;
	CRetencaoLinha			oRetencaoLinha;
	CRetencaoConsolidada	oRetencaoConsolidada;
	COfertaRealizada		oOfertaRealizada;
	CApoioRet				oCApoioRet;
	CRetencaoAparelho		oRetencaoAparelho;//reutilizado em outros fluxos
	CRetencaoAnalise		oRetencaoAnalise;
	CCaractOfAceita			oCaracOfAceita;


	memset(szTipoEncerramento,0,sizeof(szTipoEncerramento));

	// Variaveis CoreWorkFlow
	m_idAtendimento[0] = 0x0;
	m_idUsuario[0] = 0x0;
	m_idAtividade[0] = 0x0;

	ULOG("Retencao....");
	oRetencao.Inserir(pdnode,idUser,m_idRetencao);

	ULOG("RetencaoLinha....");
	oRetencaoLinha.Inserir(pdnode,idUser,m_idRetencao);

  	get_tag(szTipoEncerramento,pdnode,"idTipoEncerramento",0,0);
    get_tag(sParam,pdnode,"idPessoaDePara",0,-1); // OS-614
    if ( sParam[0] != NULL )
    {
	    idPessoaDePara = atoi(sParam);
    }
    ULOG( "idPessoaDePara [%d]",idPessoaDePara );

    get_tag(m_idGrupoAbertura,pdnode,"idGrupo",0,0); //valor para validacao de contato OS-614
	
	
	m_inPortOut = get_tag(sParam,pdnode,"nrProtocoloPortabilidade",0,-1); // OS-661

	ULOG( "m_inPortOut=>nrProtocoloPortabilidade [%d]",m_inPortOut );

	if ( m_inPortOut== 0 )
		{
			strcpy(m_nrProtocolo,sParam);
		}

    if (atoi(szTipoEncerramento)!=1)
    {

        //So ira entrar no switch se não foi ligação indevida.
        ULOG("switch(idEncerramento)");
        switch(oCApoioRet.ValidaTipoEncerramento(szTipoEncerramento))
        {
    		case CANCELADO:
            case NAORETIDO_URA:
            case NAORETIDO_IVR:

				ULOG("CANCELADO");		
				m_iAtendimento=get_tag(sParam,pdnode,"idPalitagem",0,-1); 

				if(!m_iAtendimento) 
				{
					strcpy(m_nrIdAtendimento,sParam);
					if(strcmp(m_nrIdAtendimento,"0") )//isto significa que NAO SERAH CHAMADO O REGCONTATO com as linhas.
					{
		    							
						oCApoioRet.GravaLinhasAssociadasCancelamento(pdnode);	
					}
					
				}

                if(m_inPortOut==-1) //atendimento em linha de frente
                {
                    ULOG( "m_inPortOut Antes do ValidaPortout [%d]",m_inPortOut );
                        m_inPortOut=oCApoioRet.ValidaPortout(pdnode,m_nrProtocolo);
                    ULOG( "m_inPortOut DEPOIS do ValidaPortout [%d]",m_inPortOut );
                }

                //m_bAttContato = oCApoioRet.VerificaContatoCliente( &idPessoaDePara,m_idGrupoAbertura,"2","-1",sContatoPrm );   // OS-614
                m_bAttContato = oCApoioRet.VerificaContatoCliente( &idPessoaDePara,m_idGrupoAbertura,szTipoEncerramento,"-1",sContatoPrm );
    	        sprintf(m_idPessoaContato,"%d",idPessoaDePara);
    			if(sContatoPrm[0]!=NULL)
    			{
    				strcpy(m_nrTelefoneContato,sContatoPrm);
    			}
    			ULOG("sContatoPrm [%s]",sContatoPrm);
    			ULOG("m_nrTelefoneContato [%s]",m_nrTelefoneContato);
    
    			oRetencaoConsolidada.Inserir(pdnode,idUser,m_idRetencao);
    			oOfertaRealizada.Inserir(pdnode,idUser,m_idRetencao,m_idOfertaRealizada);

                if ( atoi(szTipoEncerramento) == 17 ) // URA
                {
				   strcpy( m_idAtividade,"64" );
                }
                else
            	{
	                if ( atoi(szTipoEncerramento) == 19 ) // IVR
	                {
					   strcpy( m_idAtividade,"65" );
	                }
            	}
				   
    		break;
    		case NAO_RETIDO_PORTABILIDADE:
				ULOG("NAO_RETIDO_PORTABILIDADE");		
    			m_bAttContato = oCApoioRet.VerificaContatoCliente( &idPessoaDePara,m_idGrupoAbertura,"2","-1",sContatoPrm );   // OS-614
    	        sprintf(m_idPessoaContato,"%d",idPessoaDePara);
    			if(sContatoPrm[0]!=NULL)
    			{
    				strcpy(m_nrTelefoneContato,sContatoPrm);
    			}
    			ULOG("sContatoPrm [%s]",sContatoPrm);
    			ULOG("m_nrTelefoneContato [%s]",m_nrTelefoneContato);
    
    			oRetencaoConsolidada.Inserir(pdnode,idUser,m_idRetencao);
    			oOfertaRealizada.Inserir(pdnode,idUser,m_idRetencao,m_idOfertaRealizada);
    			strcpy( m_idAtividade,"51" );
    		break;
    		case RETIDO_RESTRICAO:
				ULOG("RETIDO_RESTRICAO");	
    			oRetencaoConsolidada.Inserir(pdnode,idUser,m_idRetencao);
    			oOfertaRealizada.Inserir(pdnode,idUser,m_idRetencao,m_idOfertaRealizada);
    			oRetencaoAparelho.Inserir(pdnode,idUser,m_idRetencao); //esta analise é por aparelho
    		break;
    		case ANALISECRDB:
				ULOG("ANALISECRDB");	
    			oRetencaoConsolidada.Inserir(pdnode,idUser,m_idRetencao);
    			oOfertaRealizada.Inserir(pdnode,idUser,m_idRetencao,m_idOfertaRealizada);       
    			oRetencaoAparelho.Inserir(pdnode,idUser,m_idRetencao); //esta analise é por aparelho
    			oRetencaoAnalise.Inserir(pdnode,idUser,m_idRetencao);
    			oRetencaoAnalise.InserirAnaliseCredito(pdnode,idUser,m_idRetencao);
    		break;
    		case ANALISEENDERECO:
				ULOG("ANALISEENDERECO");	
    			oRetencaoConsolidada.Inserir(pdnode,idUser,m_idRetencao);
    			oOfertaRealizada.Inserir(pdnode,idUser,m_idRetencao,m_idOfertaRealizada);
    			oRetencaoAparelho.Inserir(pdnode,idUser,m_idRetencao);//esta analise é por aparelho
    			oRetencaoAnalise.Inserir(pdnode,idUser,m_idRetencao);
    			oRetencaoAnalise.InserirAnaliseEndereco(pdnode,idUser,m_idRetencao);
    
    		break;
    		case RETIDO:
            case RETIDO_URA:
            case RETIDO_IVR:
            	
    				ULOG("RETIDO");
				
					 ULOG( "helllllllll !!!!");
					//captura o identificador de portabilidade <inPortabilidade>.
					//m_inPortOut=get_tag(sParam,pdnode,"inPortabilidade",0,-1); 
				
					
					if(m_inPortOut==-1) //atendimento em linha de frente
					{
						ULOG( "m_inPortOut Antes do ValidaPortout [%d]",m_inPortOut );
							m_inPortOut=oCApoioRet.ValidaPortout(pdnode,m_nrProtocolo);
						ULOG( "m_inPortOut DEPOIS do ValidaPortout [%d]",m_inPortOut );
					}

				
					ULOG( "m_nrProtocolo [%s]",m_nrProtocolo );

					ULOG( "m_inPortOut [%d]",m_inPortOut );

					ULOG( "helllllllll 222222!!!!");
	    
					get_tag(sgOf,pdnode,"sgOfertaAceita",0,0);
					get_tag(szidMtOferta,pdnode,"idOfertaAceita",0,0); 
    
    			m_bAttContato = oCApoioRet.VerificaContatoCliente( &idPessoaDePara,m_idGrupoAbertura,"3",szidMtOferta,sContatoPrm );   // OS-614
    	        sprintf(m_idPessoaContato,"%d",idPessoaDePara);
    			if(sContatoPrm[0]!=NULL)
    			{
    				strcpy(m_nrTelefoneContato,sContatoPrm);
    			}
    			ULOG("sContatoPrm [%s]",sContatoPrm);
    			ULOG("m_nrTelefoneContato [%s]",m_nrTelefoneContato);
    
    			oRetencaoConsolidada.Inserir(pdnode,idUser,m_idRetencao);
    			oOfertaRealizada.Inserir(pdnode,idUser,m_idRetencao,m_idOfertaRealizada);
    			iOf=ValidaOferta(sgOf);
					
				
				if (m_inPortOut==1)
				{
					//significa que estamos na linha de frente porem existe um bilhete
					//de portabiliade em aberto.
					ULOG("m_inPortOut==1:%d",m_inPortOut);
					
	                if ( atoi(szTipoEncerramento) == 16 ) // URA
	                {
					   strcpy( m_idAtividade,"62" );
	                }
	                else
                	{
		                if ( atoi(szTipoEncerramento) == 18 ) // IVR
		                {
						   strcpy( m_idAtividade,"63" );
		                }
		                else
	                	{
					strcpy( m_idAtividade,"55" );
	                	}
                	}
					
					ULOG("m_idAtividade:%s",m_idAtividade);
				}

    			ULOG_INT(iOf);
					switch(iOf)
					{
						case APARELHO:
							oRetencaoAparelho.Inserir(pdnode,idUser,m_idRetencao); //esta analise é por aparelho
							ULOG("Validacao de ativacao legado");
							icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
							ULOG("icrtAtivaOferta:%d",icrtAtivaOferta); 
						break;
						case BONUS:
							CRetencaoBonus			oRetencaoBonus;
							oRetencaoBonus.Inserir(pdnode,idUser,m_idRetencao);
							
							ULOG("Validacao de ativacao legado");
							icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
							ULOG("icrtAtivaOferta:%d",icrtAtivaOferta); 
						break;
						case PONTOS:
							CRetencaoPontos			oRetencaoPontos;
							oRetencaoPontos.Inserir(pdnode,idUser,m_idRetencao);

							ULOG("Validacao de ativacao legado");
							icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
							ULOG("icrtAtivaOferta:%d",icrtAtivaOferta); 
							
						break;
						case PLANOS:
							CRetencaoPlano			oRetencaoPlanos;
							oRetencaoPlanos.Inserir(pdnode,idUser,m_idRetencao);
							
							ULOG("Validacao de ativacao legado");
							icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
							ULOG("icrtAtivaOferta:%d",icrtAtivaOferta); 
						break;
						case MIGRACAO:
							CRetencaoMigracao		oRetencaoMigracao;
							oRetencaoMigracao.Inserir(pdnode,idUser,m_idRetencao);

							ULOG("Validacao de ativacao legado");
							icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
							ULOG("icrtAtivaOferta:%d",icrtAtivaOferta); 
						break;
						case SUSPENSAO:
							CRetencaoSuspensao		oRetencaoSuspensao;
							oRetencaoSuspensao.Inserir(pdnode,idUser,m_idRetencao);

							ULOG("Validacao de ativacao legado");
							icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
							ULOG("icrtAtivaOferta:%d",icrtAtivaOferta); 
							
						break;
						case ARGUMENTACAO:
							//do nothing.....
							ULOG("PARA ARGUMENTACAO NAO EXISTE TABELA.");
						break;
					}
					
			

    		break;
    			case RETIDO_PORTABILIDADE:
				ULOG("RETIDO_PORTABILIDADE");
                get_tag(sgOf,pdnode,"sgOfertaAceita",0,0);
    			get_tag(szidMtOferta,pdnode,"idOfertaAceita",0,0); 
    
    			m_bAttContato = oCApoioRet.VerificaContatoCliente( &idPessoaDePara,m_idGrupoAbertura,"3",szidMtOferta,sContatoPrm );   // OS-614
    	        sprintf(m_idPessoaContato,"%d",idPessoaDePara);
    			if(sContatoPrm[0]!=NULL)
    			{
    				strcpy(m_nrTelefoneContato,sContatoPrm);
    			}
    			ULOG("sContatoPrm [%s]",sContatoPrm);
    			ULOG("m_nrTelefoneContato [%s]",m_nrTelefoneContato);
    
    			oRetencaoConsolidada.Inserir(pdnode,idUser,m_idRetencao);
    			oOfertaRealizada.Inserir(pdnode,idUser,m_idRetencao,m_idOfertaRealizada);
    			iOf=ValidaOferta(sgOf);
    
    			ULOG_INT(iOf);
    			switch(iOf)
    			{
    				case APARELHO:
    					oRetencaoAparelho.Inserir(pdnode,idUser,m_idRetencao); //esta analise é por aparelho
    					
						ULOG("Validacao de ativacao legado");
						icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
						ULOG("icrtAtivaOferta:%d",icrtAtivaOferta); 
    				break;
    				case BONUS:
    					CRetencaoBonus			oRetencaoBonus;
    					oRetencaoBonus.Inserir(pdnode,idUser,m_idRetencao);

						ULOG("Validacao de ativacao legado");
						icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
						ULOG("icrtAtivaOferta:%d",icrtAtivaOferta); 
    					
    				break;
    				case PONTOS:
    					CRetencaoPontos			oRetencaoPontos;
    					oRetencaoPontos.Inserir(pdnode,idUser,m_idRetencao);
						
						ULOG("Validacao de ativacao legado");
						icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
						ULOG("icrtAtivaOferta:%d",icrtAtivaOferta); 	
    				break;
    				case PLANOS:
    					CRetencaoPlano			oRetencaoPlanos;
    		    		oRetencaoPlanos.Inserir(pdnode,idUser,m_idRetencao);

						ULOG("Validacao de ativacao legado");
						icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
						ULOG("icrtAtivaOferta:%d",icrtAtivaOferta); 
    					
    				break;
    				case MIGRACAO:
    					/*CRetencaoMigracao		oRetencaoMigracao;
    		
    					oRetencaoMigracao.Inserir(pdnode,idUser,m_idRetencao);
    					oCaracOfAceita.Inserir( pdnode,idUser,m_idOfertaRealizada,sgOf );*/
    					ULOG("PARA MIGRACAO NAO EXISTE funcionalidade.");
    				break;
    				case SUSPENSAO:
    					CRetencaoSuspensao		oRetencaoSuspensao;
    					oRetencaoSuspensao.Inserir(pdnode,idUser,m_idRetencao);

						ULOG("Validacao de ativacao legado");
						icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
						ULOG("icrtAtivaOferta:%d",icrtAtivaOferta); 
    					
    				break;
    				case ARGUMENTACAO:
    					//do nothing.....
					ULOG("PARA ARGUMENTACAO NAO EXISTE TABELA.");
    				break;
    			}

			/*	ULOG("Validacao de ativacao legado");
				icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf);
				ULOG("icrtAtivaOferta:%d",icrtAtivaOferta);*/
				
				strcpy( m_idAtividade,"55" );
				ULOG("m_idAtividade:%s",m_idAtividade);
    		break;
    		case VAIPENSAR:
				ULOG("VAIPENSAR");
    			oRetencaoConsolidada.Inserir(pdnode,idUser,m_idRetencao);
    			oOfertaRealizada.Inserir(pdnode,idUser,m_idRetencao,m_idOfertaRealizada);
    		break;
    		case VAIPENSAR_PORTABILIDADE:
				ULOG("VAIPENSAR_PORTABILIDADE");
    			oRetencaoConsolidada.Inserir(pdnode,idUser,m_idRetencao);
    			oOfertaRealizada.Inserir(pdnode,idUser,m_idRetencao,m_idOfertaRealizada);
    			if ( get_tag(sParam,pdnode,"idOfertaAceita",0,-1) != -1 )
				{
    			   strcpy( m_idAtividade,"52" );
				   ULOG("Validacao de ativacao legado(comentado)");
					//icrtAtivaOferta=oCApoioRet.InserirDadosAtivacao(pdnode,m_idRetencao,idUser,iOf));
				ULOG("icrtAtivaOferta:%d",icrtAtivaOferta);
				}
    		    else
				{
    			   strcpy( m_idAtividade,"53" );
				}
    		break;
	    }

    	//fechando análise:
    
    	//montando dados para o Registro de Contato
       	get_tag(m_idPessoa,pdnode,"idPessoaDePara",0,0);
    
    	get_tag(m_idPessoaLinhaHistorico,pdnode,"idLinhaTelefonica",0,0);
    
    	
    
        get_tag(m_idUfOperadora,pdnode,"idUFOperadora",0,0);//idUFOperadora
    
        get_tag(m_idSegmentacao,pdnode,"idSegmentacao",0,0);//idSegmentacao
    
    	get_tag(m_nrTelefone,pdnode,"nrLinha",0,0);//nrTelefone
		get_tag(m_nrLinhaTI,pdnode,"nrLinhaTI",0,-1);//m_nrLinhaTI

		m_inLinhaDiferente=strcmp(m_nrTelefone,m_nrLinhaTI);

		get_tag(m_idAtendimentoProtocolo,pdnode,"nrProtocolo",0,-1);//idAtendimentoProtocolo

		m_inControleProtocolo=(strlen(m_idAtendimentoProtocolo))>0?0:1;

		//pog sem vergonha!!!!
		sprintf(m_ddd,"%c%c",m_nrTelefone[0],m_nrTelefone[1]);

		//sprintf(m_nrTelAberturaProtocolo,"%c%c%c%c%c%c%c%c",m_nrTelefone[2],m_nrTelefone[3],m_nrTelefone[4],m_nrTelefone[5],m_nrTelefone[6],m_nrTelefone[7],m_nrTelefone[8],m_nrTelefone[9]);
        
		sprintf(m_nrTelAberturaProtocolo,"%s",(char*)&m_nrTelefone[2]);


    
    	strcpy(m_nrTelefoneSMS,m_nrTelefone);
    	
    	get_tag(m_inTipoPessoa,pdnode,"sgTipoPessoa",0,0);//sgTipoPessoa


    	oCApoioRet.GetContatoTipoEncerramento(atoi(szTipoEncerramento),m_idOfertaRealizada,m_idContato,sgOf,
    										  m_idConta,m_idTipoCarteira,m_idTipoLinha,
    										  m_idPessoa,m_idPessoaLinhaHistorico);
    
    		
    	(oCApoioRet.ValidaTipoEncerramento(szTipoEncerramento),m_idOfertaRealizada,m_idContato,sgOf); //contato

    	//apenas para preencher o encaminhamento ou fechamento imediato
    	int iTipoAbertura=oCApoioRet.ValidaTipoEncerramento(szTipoEncerramento);
    
    	if (iTipoAbertura==ANALISECRDB ||iTipoAbertura==ANALISEENDERECO)
    	{
    		strcpy(m_tpOperacao,"2");
    		iAnalise=1;
    	}
    	else
    	{
    		strcpy(m_tpOperacao,"1");
    		iAnalise=0;
    	}

    	int  iRetencaoOld=0;
    	char szIdRetOld[21+1];
    
    	//Finalizando continuação de retencao
    	iRetencaoOld=get_tag(szIdRetOld,pdnode,"idRetencaoOld",0,-1);//idRetencaoOld
    
    	if(iRetencaoOld!=-1) oCApoioRet.ConcluiAnalise(szIdRetOld);
       
    
    	get_tag(m_nrTelefone,pdnode,"nrLinha",0,0);//nrTelefone

    }

	return 1;
}



int CCoreRetencao::RegistraContato(DOMNode* p,char *idUser)
{
    ULOG_START( "RegistraContato()" );
    
	CCallTuxSrv TuxServico;
	XercesDOMParser *pParser;
	MemBufInputSource *pMemBuf;
	XMLGen oEntrada;
	char szRetorno[4000];
    char szXmlIn[2500];
	int iStatus=0;
	const char *pMemBufId = "inputInfo";
    char parm[512]; 
	char szXml[4000];
	CApoioRet   oCApoioRetContato;
	


	try
	{	
    	TuxServico.SetService("REGCONTATOA");
        TuxServico.SetUser(idUser);
    	FormataMsgRegContato(&oEntrada,p);
		
        ULOG("Configurou objeto TuxServico");
        TuxServico.SetServiceName("REGCONTATOA");
        TuxServico.SetBody(&oEntrada);
        TuxServico.SetInputMessage();
        TuxServico.RemoteCall();
     	
       	    
        if(	TuxServico.IsValidMessage())
        {
            ULOG("Entrou no IsValidMessage()");
            TuxServico.GetMessgeStr(szXml);
            sprintf(szRetorno, "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>%s", szXml);
            
            ULOG("conteudo do xml()");
            ULOG(szRetorno);
            
            pParser =  new XercesDOMParser;
            pMemBuf =  new MemBufInputSource( (const XMLByte*)szRetorno, strlen(szRetorno), pMemBufId);
            
            
            pParser->parse(*pMemBuf);
            DOMNode* pDoc = pParser->getDocument();
            
            get_tag(parm,pDoc,"nrProtocolo",0,-1);
            strcpy(m_nrProtocolo,parm);
            
			if (!strcmp(m_nrProtocolo,m_idAtendimentoProtocolo))
			{
				oCApoioRetContato.AtualizaNRProtocolo(m_idRetencao,m_nrProtocolo);	
			}
			else
			{
				strcpy(m_idAtendimentoProtocolo,m_nrProtocolo);
				m_inControleProtocolo=1;
				oCApoioRetContato.AtualizaNRProtocolo(m_idRetencao,m_nrProtocolo);
			}
			
            
            /* if (iAnalise)
            {
            strcpy(m_idRetencao,parm);
            }*/
        }
        ULOG_END( "RegistraContato()" );
    	delete pParser;
    	delete pMemBuf;
    	return iStatus;
    }
    catch(...)
    {
		iStatus = -1;
		delete pParser;
		delete pMemBuf;
        ULOG_END( ">>> ERRO:RegistraContato()" );
        return iStatus;
    }
}



int CCoreRetencao::GeraMsgRetorno(XMLGen *pxml)
{
	pxml->addItem("descricao","IDRETENCAO");
		
	if (!iAnalise)
	{
		pxml->addItem("valor",m_idRetencao);
	}
	else 
	{
		pxml->addItem("valor",m_nrProtocolo);
	}

	//POGMASTER - OS EXPURGO
	if(m_inLinhaDiferente)
	{
		pxml->addItem("inReutilizaProtocolo","0");
		pxml->addItem("nrProtocolo",m_idAtendimentoProtocolo);
	}
	else
	{
		pxml->addItem("inReutilizaProtocolo","1");
		pxml->addItem("nrProtocolo",m_idAtendimentoProtocolo);
	}
	   
	if( m_bAttContato == true )
	{
	    pxml->createTag("ns1:FidelizacaoContatoVO");
			pxml->addItem("ns1:idPessoa",m_idPessoaContato);
			pxml->addItem("ns1:dsContato",m_nrTelefoneContato);
	    pxml->closeTag();       
	}
return 1;
}



void CCoreRetencao::FormataMsgRegContato(XMLGen *oEntrada,DOMNode *dnode)
{
 
 ULOG_START("FormataDados()");
	char szNrLinha[21+1];
	char szCdConta[21+1];

 //monta xml para o REGCONTATO
    oEntrada->createTag("script");
    oEntrada->addProp("type", "D");
    oEntrada->addProp("dbid", "3");
    oEntrada->closeTag();       
    oEntrada->createTag("rsBody");
    oEntrada->createTag("xml-fragment");
    
    oEntrada->createTag("AtendimentoVO");
    //oEntrada->addProp("xmlns","HELL.fo.vivo.com.br/vo"      );
    oEntrada->addItem("ProxyLinha","");
    oEntrada->addItem("ProxyOperacao","getFidelizacao");
		
		oEntrada->createTag("DadosProtocoloVO");
			if(m_inLinhaDiferente)
			{
				oEntrada->addItem("nrProtocolo","");
				
			}
			else
			{
				oEntrada->addItem("nrProtocolo",m_idAtendimentoProtocolo);
			}
			oEntrada->addItem("idTipoAberturaProtocolo","3");
			oEntrada->addItem("idSistemaOrigemProtocolo","7");
			oEntrada->addItem("dddSMSProtocolo",m_ddd);
			oEntrada->addItem("nrLinhaSMSProtocolo",m_nrTelAberturaProtocolo);
			//oEntrada->addItem("forceUsoProtocoloNaoAberto","");
		oEntrada->closeTag();

    oEntrada->addItem("idChamadaTelefonica",    "0"   );  
    oEntrada->addItem("idGrupoAbertura",        m_idGrupoAbertura       );//encaminhamento
    oEntrada->addItem("inResponsavelAbertura",  "2" );//encaminhamento
    oEntrada->addItem("nmContato",              "o proprio"             );
    oEntrada->addItem("observacao",             "Processo de Retencao"  );
    oEntrada->addItem("nrTelefone",             m_nrTelefone            );
    oEntrada->addItem("tpOperacao",             m_tpOperacao            );
	//dados necessários para encaminhamento.
    oEntrada->addItem("idTipoLinha",              m_idTipoLinha            );//encaminhamento
	oEntrada->addItem("inTipoPessoa",           m_inTipoPessoa            );//encaminhamento
	oEntrada->addItem("idUfOperadora",           m_idUfOperadora            );//encaminhamento

     


    oEntrada->createTag("ProcedenciaVO");
      //  oEntrada->addProp("xmlns","admsistemas.fo.vivo.com.br/vo"       );
        oEntrada->addItem("idProcedencia",      "1");//fixo 1 telefone encaminhamento 
    oEntrada->closeTag();

    oEntrada->createTag("CanalVO");
        oEntrada->addItem("idCanal",            "16");//encaminhamento//fixo 16 RETENCAO encaimhamento
    oEntrada->closeTag();

    oEntrada->createTag("Contas");
        oEntrada->createTag("ContaVO");
            oEntrada->addItem("idConta",        m_idConta);
            oEntrada->createTag("LinhaVO");
                oEntrada->addItem("idPessoaLinhaHistorico", m_idPessoaLinhaHistorico);
            oEntrada->closeTag();
        oEntrada->closeTag();
    oEntrada->closeTag();

    oEntrada->createTag("PessoaVO");
        oEntrada->addItem("idPessoa",                           m_idPessoa);
        oEntrada->addItem("AtendimentoTipoComunicacaoVO",       "1");
    oEntrada->closeTag();

    oEntrada->createTag("UsuarioLinhaVO");
        //oEntrada->addProp("xmlns","cliente.fo.vivo.com.br/vo"       );
        oEntrada->addItem("idPessoa",                           m_idPessoa);
    oEntrada->closeTag();


    oEntrada->createTag("ArvoreAtendimentoVO");
      //  oEntrada->addProp("xmlns","admsistemas.fo.vivo.com.br/vo"       );
        oEntrada->addItem("idContato",                          m_idContato);
        oEntrada->createTag("CarterizacaoVO");
            oEntrada->addItem("idTipoCarteira",                  m_idTipoCarteira);//encaminhamento
        oEntrada->closeTag();
        oEntrada->createTag("SegmentacaoVO");
            oEntrada->addItem("idSegmentacao",                  m_idSegmentacao);//encaminhamento
        oEntrada->closeTag();
    oEntrada->closeTag();

    // problema de envio para o SMS

    char  buffer[32], s[32];
   
    memset(buffer,0,sizeof(buffer));
    memset(s,0,sizeof(s));

    ULOG("m_nrTelefoneSMS() = [%s]",m_nrTelefoneSMS);  

    if (strlen(m_nrTelefoneSMS) != 0 )
        sprintf( buffer,"%s",m_nrTelefoneSMS);
    else
    {        
        /*--- coisas de Charles ------
        ULOG("m_nrTelefone()=[%s] ",m_nrTelefone);
        sprintf(s,"%s",m_nrTelefone);
        ULOG("s=[%s] ",s);
        sprintf( buffer,"(%c%c)%c%c%c%c-%c%c%c%c",s[0],s[1],s[2],s[3],s[4],s[5],s[6],s[7],s[8],s[9]  );
        ULOG("buffer=[%s] ",buffer);
        */

        ULOG("m_nrTelefone()=[%s] ",m_nrTelefone);
        if ( strlen(m_nrTelefone) < 11 )
        {
            sprintf( buffer,"(%.2s)%.4s-%.4s", m_nrTelefone, (char*)&m_nrTelefone[2], (char*)&m_nrTelefone[6] );
        }
        else
        {
            sprintf( buffer,"(%.2s)%.5s-%.4s", m_nrTelefone, (char*)&m_nrTelefone[2], (char*)&m_nrTelefone[7] );
        }
        
        ULOG("buffer=[%s] ",buffer);
    }

    oEntrada->createTag("AtendimentoRetornoVO"); //
        //oEntrada->addProp("xmlns","workflow.fo.vivo.com.br/vo");
        oEntrada->addItem("idTipoComunicacao","9");   //	sms	= <idTipoComunicacao>9</idTipoComunicacao>
		oEntrada->addItem("dsComunicacao",buffer); 	  //	telefone formatado <dsComunicacao>(11)9648-1869</dsComunicacao>
    oEntrada->closeTag();

	

    oEntrada->closeTag();
    
    int i=0;
	char parm[255];

	while ( get_tag(parm,dnode,"nrConta",i,-1) != -1) 
            {
				ULOG("i:%d",i);
			
					strcpy(szCdConta,parm);
					get_tag(parm,dnode,"nrLinha",i+1,-1);
					strcpy(szNrLinha,parm);
				
				 ULOG("i+1:%d",i+1);

					oEntrada->createTag("LinhasAssociadasVO");
						oEntrada->addItem("cdConta",szCdConta);
						oEntrada->addItem("nrTelefone",szNrLinha);
					oEntrada->closeTag();
				i++;
			}

 oEntrada->closeTag();
oEntrada->closeTag();

ULOG_END("FormataDados()");

}



int CCoreRetencao::ValidaOferta(char *psgOferta)
{
int iOferta=0;
	 if(strcmp(psgOferta,"AP")==0)		 iOferta= APARELHO;
	 else if (strcmp(psgOferta,"APC")==0) iOferta= APARELHO;
	 else if (strcmp(psgOferta,"BN")==0) iOferta= BONUS;
	 else if(strcmp(psgOferta,"PT")==0)	 iOferta= PONTOS; 	
	 else if (strcmp(psgOferta,"SP")==0) iOferta= SUSPENSAO;
	 else if(strcmp(psgOferta,"PL")==0)	 iOferta= PLANOS;
	 else if (strcmp(psgOferta,"AG")==0) iOferta= ARGUMENTACAO;
	 else if(strcmp(psgOferta,"MIG")==0) iOferta= MIGRACAO;
return iOferta;
}



int CCoreRetencao::CoreWorkflow(DOMNode*,char *idUser)
{
    ULOG_START("CoreWorkflow()");

    CCallTuxSrv TuxServico;
    XercesDOMParser *pParser;
    MemBufInputSource *pMemBuf;
    XMLGen oEntrada;
    char szRetorno[4000];
    char szXmlIn[2500];
    int iStatus=0;
    const char *pMemBufId = "inputInfo";
    char parm[512]; 
    char szXml[4000];
    CApoioRet				oCApoioRetContato;
    CRetencao				oRetencao;
    
	try
	{
        oRetencao.ObtemIdAtendimento( m_nrProtocolo, m_idAtendimento );
        strcpy( m_idUsuario, idUser );
    		
    	TuxServico.SetService("COREWORKFLOW");
        TuxServico.SetUser(idUser);
    	FormataMsgCoreWrkFlow(&oEntrada);
		
        ULOG("Configurou objeto TuxServico");
        TuxServico.SetServiceName("COREWORKFLOW");
        TuxServico.SetBody(&oEntrada);
        TuxServico.SetInputMessage();
        TuxServico.RemoteCall();

        if(	TuxServico.IsValidMessage())
        {
            ULOG("Entrou no IsValidMessage()");
            TuxServico.GetMessgeStr(szXml);
            sprintf(szRetorno, "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>%s", szXml);
            
            ULOG("conteudo do xml()");
            ULOG(szRetorno);
            
            pParser =  new XercesDOMParser;
            pMemBuf =  new MemBufInputSource( (const XMLByte*)szRetorno, strlen(szRetorno), pMemBufId);
            
            
            pParser->parse(*pMemBuf);
            DOMNode* pDoc = pParser->getDocument();
            
        }
    
    	delete pParser;
    	delete pMemBuf;
        ULOG_END("CoreWorkflow()");
    	return iStatus;
    }
    catch(...)
    {
		/*  POG - Incidencia 5010 - para que seja efetivado alteracao no Java
		iStatus=-1;
		delete pParser;
		delete pMemBuf;
        ULOG_END( ">>> ERRO:CoreWorkflow()" );
    	return iStatus;
    	*/
		delete pParser;
		delete pMemBuf;
        ULOG_END( ">>> ERRO:CoreWorkflow()" );
        return -1;
    }
	
}



void CCoreRetencao::FormataMsgCoreWrkFlow( XMLGen *oEntrada )
{
    ULOG_START("FormataMsgCoreWrkFlow()");
    
    oEntrada->createTag("atendimentos");
        oEntrada->createTag("xml-fragment");
            oEntrada->createTag("AtendimentoWorkflowVO");
            oEntrada->addProp("xmlns:vo", "workflow.fo.vivo.com.br/vo");
            oEntrada->createTag("AtendimentoWorkflowComumVO");
                oEntrada->addItem("idPessoaUsuario", m_idUsuario );
            oEntrada->closeTag();
            oEntrada->createTag("AtendimentoWorkflowVO");
            oEntrada->createTag("AtdWFVO");
            oEntrada->addProp("xmlns:vo", "workflow.fo.vivo.com.br/vo");
                oEntrada->addItem("idAtendimento", m_idAtendimento );
            oEntrada->closeTag();
            oEntrada->createTag("WFAcaoVO");
            oEntrada->addProp("xmlns:vo", "workflow.fo.vivo.com.br/vo");
                oEntrada->addItem("idAtividade",m_idAtividade );
                oEntrada->addItem("dsTipoAtividade","PORTOUT" );
                oEntrada->addItem("inCRI",0);
            oEntrada->closeTag();
        oEntrada->closeTag();
    oEntrada->closeTag();

    ULOG_END("FormataMsgCoreWrkFlow()");
}



int CCoreRetencao::RetencaoBonusURA(DOMNode *pdnode,char *idUser )
{
    char prm[256];
    
    XercesDOMParser *pParser;
    MemBufInputSource *pMemBuf;
    const char *pMemBufId = "inputInfo";
    CRetencao   oRetencao;
    int retorno = 0;

    retorno = oRetencao.ObtemParametrosToURA( pdnode, idUser );
    if ( retorno != 0 )
    {
        memset( m_idRetencao, 0x0, sizeof(m_idRetencao) );
        memset( m_nrProtocolo, 0x0, sizeof(m_idRetencao) );
        memset( m_idAtendimentoProtocolo, 0x0, sizeof(m_idAtendimentoProtocolo) );
        string strMsgIn = 
            "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
                "<msg>"
                    "<msgHdr>"
                        "<user>"+(string)idUser+"</user>"
                        "<service>FINALRETENCAO</service>"
                    "</msgHdr>"
                    "<msgBody>"
                        "<Cliente>"
                            "<cdRetorno>"+oRetencao.prmURA.sCodErro+"</cdRetorno>"
                            "<msgRetorno>"+oRetencao.prmURA.sMsgErro+"</msgRetorno>"
                        "</Cliente>"
                    "</msgBody>"
                "</msg>";

        char *sMsgIn = (char*)strMsgIn.c_str();
        ULOG( "XML Gerado [%s]", sMsgIn);

        pParser =  new XercesDOMParser;
        pMemBuf =  new MemBufInputSource( (const XMLByte*)sMsgIn, strlen(sMsgIn), pMemBufId);
        pParser->parse(*pMemBuf);
        pDocURA = pParser->getDocument();
        return -1;
        
    }
    
    string strMsgIn = 
        "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
            "<msg>"
                "<msgHdr>"
                    "<user>"+(string)idUser+"</user>"
                    "<service>FINALRETENCAO</service>"
                "</msgHdr>"
                "<msgBody>"
                    "<Cliente>"
                        "<cdRetorno>"+oRetencao.prmURA.sCodErro+"</cdRetorno>"
                        "<msgRetorno>"+oRetencao.prmURA.sMsgErro+"</msgRetorno>"
                        "<idPessoaDePara>"+oRetencao.prmURA.idPessoadePara+"</idPessoaDePara>"
                        "<idUFOperadora>"+oRetencao.prmURA.idUFOperadora+"</idUFOperadora>"
                        "<sgTipoPessoa>"+oRetencao.prmURA.sgTipoPessoa+"</sgTipoPessoa>"
                        "<idSegmentacao>"+oRetencao.prmURA.idSegmentacao+"</idSegmentacao>"
                        "<idLinhaTelefonica>"+oRetencao.prmURA.idLinhaTelefonica+"</idLinhaTelefonica>"
                        "<nrLinha>"+oRetencao.prmURA.nrTelefone+"</nrLinha>"
                        "<idTipoLinha>"+oRetencao.prmURA.idTipoLinha+"</idTipoLinha>"
                    "</Cliente>"
                    "<Retencao>"
                        "<RetencaoHeader>"
                            "<nmLoginUsuario>"+oRetencao.prmURA.nmUsuario+"</nmLoginUsuario>"
                            "<idRespostaIntencao>"+oRetencao.prmURA.idRespostaIntencao+"</idRespostaIntencao>"
                            "<idRespostaDestino>"+oRetencao.prmURA.idRespostaDestino+"</idRespostaDestino>"
                            "<idGrupo>"+oRetencao.prmURA.idGrupo+"</idGrupo>"
                            "<idTipoEncerramento>"+oRetencao.prmURA.idTipoEncerramento+"</idTipoEncerramento>"
                            "<inExcecao>0</inExcecao>"
                        "</RetencaoHeader>"
                        "<RetencaoBody>"
                            "<Status>"
                                "<idOfertaAceita>"+oRetencao.prmURA.idOfertaAceita+"</idOfertaAceita>"
                                "<sgOfertaAceita>"+oRetencao.prmURA.sgOfertaAceita+"</sgOfertaAceita>"
                                "<ofertasRealizadas/>"
                            "</Status>"
                            "<Ofertas>"
                                "<Bonus>"
                                    "<nmBonus>"+oRetencao.prmURA.nmBonus+"</nmBonus>"
                                    "<idMatrizBonus>"+oRetencao.prmURA.idMatrizBonus+"</idMatrizBonus>"
                                    "<dtInicioVigencia>"+oRetencao.prmURA.dtInicioVigencia+"</dtInicioVigencia>"
                                    "<dtFinalVigencia>"+oRetencao.prmURA.dtFinalVigencia+"</dtFinalVigencia>"
                                "</Bonus>"
                            "</Ofertas>"
                        "</RetencaoBody>"
                    "</Retencao>"
                "</msgBody>"
            "</msg>";

    char *sMsgIn = (char*)strMsgIn.c_str();
    
    ULOG( "XML Gerado [%s]", sMsgIn);

    pParser =  new XercesDOMParser;
    pMemBuf =  new MemBufInputSource( (const XMLByte*)sMsgIn, strlen(sMsgIn), pMemBufId);
    
    
    pParser->parse(*pMemBuf);
    pDocURA = pParser->getDocument();
    
    return 0;

}
