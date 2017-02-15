/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.4 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../include/cWFAtdGetMotTab.h"

#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../TabelaMotivo/include/cWFTabMotivo.h"
#include "../../AtividadeMotivo/include/cWFAtividadeMotivo.h"
#include "../../Usuario/include/cWFUsuario.h"
#include "../../../commons/msgPadrao.h"

extern bool proCConsultaWFGruposPorAtendimentoEUsuario(int _User,int _inCRI,long _idAtendimento, XMLGen* saida);
extern bool proCConsultaWFGruposPorAtendimentoEUsuarioParaTrocarCri(int _User,int _inCRI,long _idAtendimento, XMLGen* saida);
extern bool proCConsultaWFTodosGrupos(int iUser, char* csgTipoGrupo, XMLGen* saida);
extern bool proCGMTConsultaWFGruposTratamento(int _idContato,  XMLGen* saida); 
extern void proCGMTConsultaWFGrupos(int _User, int _inCRI, int _idContato, int _idFase, int TipoRetorno, long _idAtendimento,XMLGen* saida);
extern void proCGMTConsultaWFGruposAbertura(int _idContato, int _idFase, int _idPessoaUsuario,long _idAtendimento,XMLGen* saida);
extern void proCGMTConsultaWFGruposBKO(int _inCRI, int _idContato, int _idFase, int TipoRetorno, XMLGen* saida);
extern void proCGMTConsultaWFGruposCri(XMLGen* saida);
extern void proCGMTConsultaWFGruposCriAssociados(int _idContato, XMLGen* saida);
extern void proCGMTConsultaWFGruposMC1Associados(long _idAtendimento, XMLGen* saida);
extern void proCGMTConsultaWFGruposReAbertura(int _idContato, int _idFase, XMLGen* saida);
extern void proCTodosGrupoCRIFolhaSupervisor(long idAtendimento, int idUser, int idContato, XMLGen* saida);
extern void proCTodosGruposCRI(XMLGen* saida);
extern void proCTodosGruposNormal(XMLGen* saida);
extern void proCTodosGruposTipoCRISupervisor(int idUser, XMLGen* saida);

cWFAtdGetMotTab::cWFAtdGetMotTab(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdGetMotTab::executar(char **codErro,char **msgErro)
{
    ULOG_START( "cWFAtdGetMotTab::executar()" );

    if ( -1 == m_vlDados.idAtividade )
    {
        *msgErro = "O valor da tag 'idAtividade' eh obrigatorio";

        ULOG_END( "cWFAtdGetMotTab::executar()" );

        return false;
    }

    saida->createTag("AtendimentoWorkflowVO");
        saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
        saida->createTag("AtendimentoWorkflowComumVO");

    // Se recebeu 'idAtendimento' captura dados do atendimento e retorna apenas
    // grupos relativos ao idFase/idContato do atendimento
    if ( m_stDados.idAtendimento > 0 )
    {
        cWFAtendimento cwfAtendimento;
        DetalheAtendimento detalheAtendimento;

        if ( cwfAtendimento.ObtemDetalheAtend(m_stDados.idAtendimento,&detalheAtendimento) )
        {
            //if ( strcmp(detalheAtendimento.sgFluxoAtendimento,"MC1")==0 )
            //{
            //    ConsultarGruposMC1Associados(m_stDados.idAtendimento);
            //}
            //else
            //{
                if (  -1 == m_vlDados.inGrupo )
                {
                    if( cwfAtendimento.ObterTipoGrupo(m_stDados.idAtendimento) == TIPO_GRUPO_CRI )
                    { // se processo é CRI ...

                        if ( m_stDados.idAtividade == ENCAMINHAR_CRI_ECRI || m_stDados.idAtividade == TROCAR_CRI_TRCRI )
                        {
                            // Retorna grupos CRI associados ao perfil do contato
                            ConsultarGruposCRIAssociados(detalheAtendimento.idContato);
                        }
                        else
                        {
                            // Apesar do nome se referir a grupos de reabertura, esta função
                            // retorna grupos configurados para um contato respectivos à
                            // fase de ABERTURA. - Jun,2006 - Cassio
                            ConsultarGruposReAbertura( detalheAtendimento.idContato,ABERTURA );
                        }
                    }
                    else
                    {
                        // incidencia 3271 validacao no item 3.2.2 do documento de especificacao
                        // verifica se o processo esta no estado de EM RETORNO
                        if ( detalheAtendimento.idFase == RETORNO )
                        {
                             if ( strcmp(detalheAtendimento.sgFluxoAtendimento,"MC2")==0 )
                             { // processos MC2 só podem trafegar entre grupos de retorno quando na fase RETORNO.
                                 ConsultarGruposAbertura( detalheAtendimento.idContato,RETORNO );
                             }
                             else
                             {
                                 if( detalheAtendimento.idTipoRetornoContato == TP_RET_COM_RET_GRP_BKO )
                                 {
                                     ConsultarGruposAbertura( detalheAtendimento.idContato,TRATAMENTO );
                                 }
                                 else if(detalheAtendimento.idTipoRetornoContato == TP_RET_COM_RET_GRP_RET )
                                 {
                                     ConsultarGruposAbertura( detalheAtendimento.idContato,RETORNO );
                                 }
                             }
                        }  // fim da incidencia 3271
                        else
                        {
                            // Esta função retorna grupos configurados para um contato respectivos à
                            // fase de ABERTURA considerando apenas aqueles grupos aos quais o usuário 
                            // corrente estiver associado. - Jun,2006 - Cassio
                            ConsultarGruposAbertura( detalheAtendimento.idContato,detalheAtendimento.idFase);
                        }
                    }
                }
                else
                {
                    ConsultarGruposAtendimento(detalheAtendimento.idContato
                                              ,detalheAtendimento.idFase
                                              ,detalheAtendimento.idTipoRetornoContato);
                }
            //}
        }
        else
        {
            *msgErro = "Processo não encontrado";
            ULOG_END( "cWFAtdGetMotTab::executar()" );
            return false;
        }
    }
    else
    {
        // Se não recebeu um 'idAtendimento' pesquisa todos os grupos de acesso
        ConsultarWFGrupos();
    }

    ConsultarMotivoAtividade();

        saida->closeTag();
            //saida->createTag("WFAcaoVO");
            //    saida->addItem("idAtividade",m_stDados.idAtividade);
            //saida->closeTag();
        //saida->closeTag();
    saida->closeTag();

    ULOG_END( "cWFAtdGetMotTab::executar()" );

    return true;
}

void cWFAtdGetMotTab::ConsultarGruposMC1Associados( long idAtendimento )
{
    ULOG_START( "cWFAtdGetMotTab::ConsultarGruposMC1Associados()" );

    proCGMTConsultaWFGruposMC1Associados(idAtendimento,saida);

    ULOG_END( "cWFAtdGetMotTab::ConsultarGruposMC1Associados()" );
}

void cWFAtdGetMotTab::ConsultarGruposCRIAssociados( int idContato )
{
    ULOG_START( "cWFAtdGetMotTab::ConsultarGruposCRIAssociados()" );

    ULOG("idContato: %d", idContato);

    proCGMTConsultaWFGruposCriAssociados(idContato,saida);

    ULOG_END( "cWFAtdGetMotTab::ConsultarGruposCRIAssociados()" );
}

void cWFAtdGetMotTab::ConsultarGruposReAbertura( int idContato, int idFase )
{
    ULOG_START( "cWFAtdGetMotTab::ConsultarGruposReAbertura()" );

    ULOG("idContato: %d", idContato);
    ULOG("idFase: %d", idFase);

    proCGMTConsultaWFGruposReAbertura(idContato, idFase, saida);

    ULOG_END( "cWFAtdGetMotTab::ConsultarGruposReAbertura()" );
}

void cWFAtdGetMotTab::ConsultarGruposPorAtendimento(int idContato)
{
    ULOG_START( "cWFAtdGetMotTab::ConsultarGruposPorAtendimento()" );

    // se atividade = encaminhar ...
    proCGMTConsultaWFGruposTratamento(idContato,saida);

    ULOG_END( "cWFAtdGetMotTab::ConsultarGruposPorAtendimento()" );

}

void cWFAtdGetMotTab::ConsultarGruposAtendimento( int idContato,int idFase,int TipoRetorno )
{
    ULOG_START( "cWFAtdGetMotTab::ConsultarGruposAtendimento()" );

    if ( m_stDados.inCri == 0 )
    {
        // Especialmente no caso de reabertura ou reincidência, os grupos que devem ser
        // apresentados não correspondem à fase atual do processo e sim a fase de ABERTURA
        // A incidência 3887 de Homologação VIVO pede que a ação de reabertura não deve ser
        // apresentada a um usuário se o mesmo não pertencer a nenhum grupo de abertura do
        // contato do processo e esta regra por sí só ja irá garantir que ao menos no caso
        // da reabertura (-reanalise) o resultado irá trazer ao menos um grupo para o usuário.
        if ( m_stDados.idAtividade == REABRIR_RB || m_stDados.idAtividade == REINCIDENCIA_R )
        {
            // Apesar do nome se referir a grupos de reabertura, esta função
            // retorna grupos configurados para um contato respectivos à
            // fase de ABERTURA. - Jun,2006 - Cassio
            ConsultarGruposReAbertura( idContato,ABERTURA );
        }
        else
        {
            proCGMTConsultaWFGrupos(obterIdUsuario(),m_stDados.inCri,idContato,idFase
                                   ,TipoRetorno,m_stDados.idAtendimento,saida);
        }
    }
    else
    {
        if ( m_stDados.idAtividade == ENCAMINHAR_CRI_ECRI && m_stDados.inCri == ACESSO_PELA_FILA )
        {
            proCTodosGruposTipoCRISupervisor(obterIdUsuario(),saida);
        }
        else if ( m_stDados.idAtividade == ENCAMINHAR_E )
        {
            proCGMTConsultaWFGruposBKO(0 /*inCri*/,idContato,idFase,TipoRetorno,saida);
        }
        else 
        {
            // Esta chamada esta sendo alterada para o atendimento da incidencia 3211
            // onde deve ser incluido na lista de grupos o grupo associado a linha do atendimento
            proCTodosGrupoCRIFolhaSupervisor(m_stDados.idAtendimento, obterIdUsuario(), idContato, saida);
        }
    }

    ULOG_END( "cWFAtdGetMotTab::ConsultarGruposAtendimento()" );
}

void cWFAtdGetMotTab::ConsultarGruposAbertura( int idContato,int idFase )
{
    ULOG_START( "cWFAtdGetMotTab::ConsultarGruposAbertura()" );

    int idPessoaUsuario = obterIdUsuario();

    ULOG("    idContato: %d", idContato);
    ULOG("       idFase: %d", idFase);
    ULOG("    idUsuario: %d", idPessoaUsuario);
    ULOG("idAtendimento: %lu", m_stDados.idAtendimento);

    proCGMTConsultaWFGruposAbertura(idContato,idFase,idPessoaUsuario,m_stDados.idAtendimento,saida);

    ULOG_END( "cWFAtdGetMotTab::ConsultarGruposAbertura()" );
}

void cWFAtdGetMotTab::ConsultarWFGrupos()
{
    ULOG_START( "cWFAtdGetMotTab::ConsultarWFGrupos()" );
    
    bool retorno = true;
    cWFUsuario cwfUsuario;

    /***************************************************************************
	 * ENCAMINHAR EM MASSA / TROCAR CRI (ECRIACAO/TRCRI)
	 * Exibir todos os grupos do TIPO CRI que o Usuário Logado está associado.
	 * Caso o usuário não seja Supervisor do grupo escolhido, o sistema 
	 * emitirá um alerta e não realizará o encaminhamento.
	 ***************************************************************************/

	if ( m_stDados.idAtividade == ENCAMINHAR_CRI_ECRI || m_stDados.idAtividade == TROCAR_CRI_TRCRI )
	{
		proCGMTConsultaWFGruposCri(saida); // Recupera todos os grupos CRI (incidência 3224)
	}
	else
    {
		if ( m_stDados.idAtividade == ENCAMINHAR_E )
        {
			proCTodosGruposNormal(saida); // Recupera todos os grupos BKO (incidência 3224)
        }
		else if (m_stDados.inRC == ACESSO_PELO_INBOX)
        {
		    retorno = cwfUsuario.pesqConsultaWFGruposRC(saida,obterIdUsuario()); // Verifica se RC tem combo de grupo, se nao tem, eliminar esta linha (Eder)
        }
		else
        {
		    retorno = cwfUsuario.pesqConsultaWFGrupos1(saida,obterIdUsuario());
        }
    }

    if ( !retorno )
    {
        ULOG( "ConsultarWFGrupos():falhou a busca de grupos" );

        return;
    }

    ULOG_END( "cWFAtdGetMotTab::ConsultarWFGrupos()" );
}

void cWFAtdGetMotTab::ConsultarMotivoAtividade(int _idFase )
{
    ULOG_START( "cWFAtdGetMotTab::ConsultarMotivoAtividade()" );
    
    cWFTabMotivo cwfTabMotivo;
    
    if ( !cwfTabMotivo.pesqMotivoAtividade(_idFase,entrada,saida) )
    {
        ULOG( "cWFTabMotivo::pesqMotivoAtividade() falhou execução" );
        return;
    }
    
    ULOG_END( "cWFAtdGetMotTab::ConsultarMotivoAtividade()" );
}


void cWFAtdGetMotTab::ConsultarMotivoAtividade()
{
    ULOG_START( "cWFAtdGetMotTab::ConsultarMotivoAtividade()" );

    cWFTabMotivo cwfTabMotivo;

    if ( !cwfTabMotivo.pesqMotivoAtividade(entrada,saida) )
    {
        ULOG( "cWFTabMotivo::pesqMotivoAtividade() falhou execução" );
        return;
    }

    ULOG_END( "cWFAtdGetMotTab::ConsultarMotivoAtividade()" );

}

void cWFAtdGetMotTab::carregaDados()
{
    ULOG_START( "cWFAtdGetMotTab::carregaDados()" );

    memset(&m_stDados, 0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

    char *p;

    if ( entrada )
    {
	    if (p=tx.walkTree( entrada, "idAtividade", 0 ),p) 
	    {
		    if (strlen(p))
		    {
		        m_stDados.idAtividade = atoi(p);
		        m_vlDados.idAtividade = 1;
            }
		    XMLString::release(&p);
	    }

	    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
	    {
		    if (strlen(p))
		    {
		        m_stDados.idAtendimento = atol(p);
		        m_vlDados.idAtendimento = 1;
            }
		    XMLString::release(&p);
	    }

	    if (p=tx.walkTree( entrada, "inGrupo", 0 ),p) 
	    {
		    if (strlen(p))
		    {
			    m_stDados.inGrupo = atoi(p);
			    m_vlDados.inGrupo = 1;
		    }
		    XMLString::release(&p);
	    }

	    if (p=tx.walkTree( entrada, "inCRI", 0 ),p) 
	    {
		    if (strlen(p))
		    {
			    m_stDados.inCri = ACESSO_PELO_INBOX;
			    m_vlDados.inCri = 1;
		    }
		    XMLString::release(&p);
	    }

	    if (p=tx.walkTree( entrada, "inRC", 0 ),p) 
	    {
		    if (strlen(p))
		    {
			    m_stDados.inRC = atoi(p);
			    m_vlDados.inRC = 1;
		    }
		    XMLString::release(&p);
	    }
    }

	ULOG("idAtividade=%d",m_stDados.idAtividade);

	ULOG("idAtendimento=%d",m_stDados.idAtendimento);

	ULOG("inGrupo=%d",m_stDados.inGrupo);

	ULOG("inCri=%d",m_stDados.inCri);

	ULOG("inRC=%d",m_stDados.inRC);

    ULOG_END( "cWFAtdGetMotTab::carregaDados()" );
}
