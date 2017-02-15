/**
 * @modulo  Atendimento
 * @usecase Fechamento em massa
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.5.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2011/09/22 13:16:42 $
 **/ 


#include "../include/cWF_CERRAMEFEC.h"
#include "../include/cWF_CERRAMEFECPC.h"

//******************************************************************************************************************
// cWF_CERRAMEFEC - Component Implementation
//******************************************************************************************************************

//Construtor
cWF_CERRAMEFEC::cWF_CERRAMEFEC(DOMNode* dnRaiz, char* pzcidUsuario)
{
    //Zerando variaveis
    memset( &stDadosEntrada, 0, sizeof( struct ST_DADOS_ENTRADA ) );
    memset( &cErro, 0, sizeof( cErro ) );
    memset( &stFormulario, 0, sizeof( struct ST_ATENDIMENTOFORMULARIO ) );
    iErro = 0;//Indica sucesso
	flagBloqueioBatch = false;

    if( STRLENNULL( pzcidUsuario ) <= 0 )
        setErro( "idUsuario está vazio. Para esta operação ele é obrigatório", CF_ERRO );
    else
    {

        if( dnRaiz == NULL )
            setErro( "DOMNode raiz está nulo (dnRaiz)", CF_ERRO );
        else
        {

            //Peenche a estrutura interna com os dados fixos
            strcpy( stDadosEntrada.cidUsuario, pzcidUsuario );

            //Recupera todos os dados da lista de processos
            getDadosEntrada( dnRaiz );
            //Recupera todos os formularios necessários
            getFormulario( dnRaiz );

        }//if( dnRaiz == NULL )
    }//if( STRLENNULL( pzcidUsuario ) > 0 )
}

//Destrutor padrão
cWF_CERRAMEFEC::~cWF_CERRAMEFEC()
{
    //Libera os campos dinamicos
    if( stFormulario.stAtendimentoFrm->stCampoReg.stCampo != NULL )
        free( stFormulario.stAtendimentoFrm->stCampoReg.stCampo );
    //Libera os formularios dinamicos
    if( stFormulario.stAtendimentoFrm != NULL )
        free( stFormulario.stAtendimentoFrm );
    //Não deixa nada na memoria
    memset( &stFormulario, 0, sizeof( struct ST_ATENDIMENTOFORMULARIO ) );
    memset( &stDadosEntrada, 0, sizeof( struct ST_DADOS_ENTRADA ) );
    memset( &cErro, 0, sizeof( cErro ) );
    iErro = CF_SUCESSO;
}


//Altera a mensagem de erro sem mudar o status
void cWF_CERRAMEFEC::setErro( char* pzcErro )
{
    if( STRLENNULL( pzcErro ) > 0 )
        strcpy( cErro, pzcErro );
    else
        strcpy( cErro, "cWF_CERRAMEFEC::setErro:ULTIMA MENSAGEM DE ERRO ENVIADA ESTAVA NULA" );
}
//Altera a mensagem de erro juntamente com seu status
void cWF_CERRAMEFEC::setErro( char* pzcErro, int iStatusErro )
{
    //Modifica a mensagem de erro
    setErro( pzcErro );
    //Modifica o status de erro
    iErro = iStatusErro;
}

//Altera o status de erro
void cWF_CERRAMEFEC::setErro( int iStatusErro )
{
    //Modifica o status de erro
    iErro = iStatusErro;
}

//Recupera uma TAG a partir de uma DOMNode
int cWF_CERRAMEFEC::getTag( DOMNode* dnEntrada, char* pzcValor, char* pzcTag )
{
    return getTag( dnEntrada, pzcValor, pzcTag, 0 );
}

//Recupera uma TAG a partir de uma DOMNode
int cWF_CERRAMEFEC::getTag( DOMNode* dnEntrada, char* pzcValor, char* pzcTag, int iPosicao )
{
    setErro( CF_SUCESSO );
    //Verifica se existe um DOMNode valido
    if( dnEntrada != NULL )
    {
        //Verifica se existe um ponteiro para algum lugar
        if( pzcValor != NULL )
        {
            //Verifica se existe um nome de TAG
            if( STRLENNULL( pzcTag ) > 0 )
            {
                //Recupera um valor necessario de uma certa TAG
                char* pzcValorLocal = thUtil.walkTree( dnEntrada, pzcTag, iPosicao );

                //Verifida se retornou alguma coisa
                if( STRLENNULL( pzcValorLocal ) > 0 )
                    strcpy( pzcValor, pzcValorLocal );
                else
                    setErro( "cWF_CERRAMEFEC::getTag:O conteudo da TAG está nulo", CF_ERRO );

                //Libera o ponteiro do walkTree
                XMLString::release( &pzcValorLocal );
                    
            }//if( STRLENNULL( pzcTag ) > 0 )
            else
                setErro( "cWF_CERRAMEFEC::getTag:O nome da TAG para pesquisa esta nula", CF_ERRO );

        }//if( pzcValor != NULL )
        else
            setErro( "cWF_CERRAMEFEC::getTag:variavel com o valor de retorno está nula", CF_ERRO );
    }//if( dnEntrada != NULL )
    else
        setErro( "cWF_CERRAMEFEC::getTag:(DOMNode*)dnEntrada está nulo", CF_ERRO );

    //Retorno o status da pesquisa: CF_ERRO ou CF_SUCESSO
    return getErroNo();
}

//Le todos os dados da lista de processo recebemos
int cWF_CERRAMEFEC::getDadosEntrada( DOMNode* dnProcessos )
{
    //Ativa o status de sucesso, caso ocorra algum problema a mensagem e o status sera modificado
    setErro( "cWF_CERRAMEFEC::getProcessos:finalizado com sucesso", CF_SUCESSO );

    //Verifica se existe algum XML de entrada (pelo menos o ponteiro)
    if( dnProcessos == NULL )
        setErro( "cWF_CERRAMEFEC::getProcessos:dnProcessos está nulo", CF_ERRO );
    else
    {
        //Recupera os dados fixos
        DOMNode* dnWFExecucao = thUtil.walkDOM( dnProcessos, "WFExecucao" );
        if( dnWFExecucao != NULL )
        {
            //Se entrou aqui é porque recebeu um XML vindo do COREWORKFLOW
            getTag( dnWFExecucao, stDadosEntrada.stDadosFixos.cidAgrupamentoEstadoTProcFut, "idAgrupamentoEstadoTProcFut" );
            getTag( dnWFExecucao, stDadosEntrada.stDadosFixos.cidAgrupamentoEstadoTProcAt, "idAgrupamentoEstadoTProcAt" );
            getTag( dnWFExecucao, stDadosEntrada.stDadosFixos.cidAtividadeMassa, "idAtividadeMassa" );
            getTag( dnWFExecucao, stDadosEntrada.stDadosFixos.cinCri, "inCRI" );
            getTag( dnWFExecucao, stDadosEntrada.stDadosFixos.cidFase, "idFase" );
            getTag( dnWFExecucao, stDadosEntrada.stDadosFixos.cnmUrlDestino, "nmUrlDestino" );
            getTag( dnWFExecucao, stDadosEntrada.stDadosFixos.cidGrupo, "idGrupo" );
            getTag( dnWFExecucao, stDadosEntrada.stDadosFixos.cidMotivo, "idMotivo" );
            getTag( dnWFExecucao, stDadosEntrada.stDadosFixos.cdsObservacao, "dsObservacao" );
            getTag( dnWFExecucao, stDadosEntrada.stDadosFixos.cidBaixa, "idBaixa" );
            getTag( dnWFExecucao, stDadosEntrada.stDadosFixos.cidMensagemBaixa, "idBaixaMensagem" );
        }
        else
        {
            //Se entrou aqui é porque recebeu um XML vindo direto da tela
            getTag( dnProcessos, stDadosEntrada.stDadosFixos.cidAtividadeMassa, "idAtividade" );
            getTag( dnProcessos, stDadosEntrada.stDadosFixos.cinCri, "inCRI" );
            getTag( dnProcessos, stDadosEntrada.stDadosFixos.cidFase, "idFase" );
            getTag( dnProcessos, stDadosEntrada.stDadosFixos.cidGrupo, "idGrupo" );
            getTag( dnProcessos, stDadosEntrada.stDadosFixos.cidMotivo, "idMotivo" );
            getTag( dnProcessos, stDadosEntrada.stDadosFixos.cdsObservacao, "dsObservacao" );
            getTag( dnProcessos, stDadosEntrada.stDadosFixos.cidBaixa, "idBaixa" );
            getTag( dnProcessos, stDadosEntrada.stDadosFixos.cidMensagemBaixa, "idBaixaMensagem" );
        }
        //Recupera os dados de cada processo
        for(int iRegistros = 0;iRegistros < CF_NUMERO_MAXIMO_PROCESSOS;iRegistros++)
        {
            //A primeira coisa eh verificar se existe atendimento, caso exista prosegue, caso contrario para o laço
            if( getTag( dnProcessos, stDadosEntrada.stDadosProcesso[iRegistros].cidAtendimento,  "idAtendimento", iRegistros ) == CF_ERRO )
                break;

			ULOG( "### Vai tratar observacao com bloqueio de aparelhos..." );
			// Bloqueio de aparelhos
            if( getTag( dnProcessos, stDadosEntrada.stDadosProcesso[iRegistros].cdsObservacao,  "dsObservacaoBlq", iRegistros ) == CF_ERRO )
			{
				stDadosEntrada.stDadosProcesso[iRegistros].cdsObservacao[0] = 0x0;
				flagBloqueioBatch = false;
			}
			else
			{
				ULOG( "*** Encontrou idAtendimento bloqueio de aparelhos[%s]", (char*)stDadosEntrada.stDadosProcesso[iRegistros].cidAtendimento );
				ULOG( "*** Encontrou Observacao    bloqueio de aparelhos[%s]", (char*)stDadosEntrada.stDadosProcesso[iRegistros].cdsObservacao );
				flagBloqueioBatch = true;
			}
			
            //Só incrementa um registro na estrutura se existir atendimento
            stDadosEntrada.iTotalProcessos++;
        }//for(;;)

        //Verifica se existe alum processo antes de continuar
        if( stDadosEntrada.iTotalProcessos <= 0 )
            setErro( "Não foram enviados processos para processar", CF_ERRO );
    }//else if( dnProcessos == NULL )

    //Retorna o resultado do status CF_ERRO ou CF_SUCESSO para usar direto num if sem precisar de chamar getErroNo()
    return getErroNo();
}
//------------------------------------------------------------------------------------------------------------------
void cWF_CERRAMEFEC::getFormulario(DOMNode* domFormulario) 
{
    ULOG( "cWF_CERRAMEFEC::getFormulario" );

    int contador  = 0;
    int contador2 = 0;
    bool iIncluir;
    char* valor;
    char* p;
    struct
    {
        char cidCampo[21+1];
        char cidDominio[21+1];
        char cdsValor[255+1];
    } stCampo;

    DOMNode* domAux;
    DOMNode* domAux2;

    //Verifica se existe formulário
    while( domAux = thUtil.walkDOM( domFormulario, "FormularioCampoVO", contador++) )
    {
        ULOG( "local1.0" );

        memset( &stCampo, 0, sizeof( stCampo ) );
        //Eder: idContatoFolhaCampo recebe um valor de controle e nao um ID (ao menos foi o que eu entendi
        if( valor = thUtil.walkTree(domAux, "idContatoFolhaCampo", 0 ), valor )
        {
            ULOG( "local2.0" );

            if( p = thUtil.walkTree( domAux, "idCampo", 0 ), p )
            {
                ULOG( "local3.0" );

                //Copia o ponteiro para dentro da estrutura
                strcpy( stCampo.cidCampo, p );
                //Libera a memoria do walkTree
                XMLString::release( &p );

                contador2 = 0;
                while( domAux2 = thUtil.walkDOM( domAux, "FormularioCampoValorVO", contador2 ) )
                {
                    ULOG( "local4.0" );
                    //REcupera o dominio
                    if( p = thUtil.walkTree( domAux2, "idFormularioCampoValor", 0 ), p )
                    {
                        ULOG( "local5.0" );

                        //Verifica se o idFormularioCampoValor eh valido
                        if( atol(p) > 0)
                        {
                            ULOG( "local6.0" );
                            strcpy( stCampo.cidDominio, p );
                            XMLString::release(&p);
                        }
                    }
                    ULOG( "local7.0" );
                    iIncluir = true;

                    //Verifica se existe um dominio, para poder pegar um valor caso nao haja
                    if( strlen( stCampo.cidDominio ) <= 0  )
                    {
                        ULOG( "local8.0" );
                        if( p = thUtil.walkTree( domAux2, "valor", 0 ), p )
                        {
                            ULOG( "local9.0" );

                            strcpy( stCampo.cdsValor, p );
                            XMLString::release(&p);
                        }
                        //Se nao tem dominio e nao tem valor, nao pode inserir
                        if( strlen( stCampo.cdsValor ) <= 0 ) 
                        {
                            ULOG( "local10.0" );
                            iIncluir = false;
                        }
                    }
                        
                    //Para incluir um campo, tem que existir Dominio ou Valor
                    if(iIncluir)
                    {
                        ULOG( "local11.0" );
                        if( contador2 == 0 )
                        {
                            ULOG( "local12.0" );
                            //Aloca um item para cada campo retornado
                            stFormulario.stAtendimentoFrm = (struct ST_ATENDIMENTOFORMULARIO*) realloc( stFormulario.stAtendimentoFrm, sizeof( struct ST_ATENDIMENTOFORMULARIO )*(stFormulario.iQuantidade+1) );
                            memset( &stFormulario.stAtendimentoFrm[stFormulario.iQuantidade], 0, sizeof( struct ST_ATENDIMENTOFORMULARIO ) );
                            stFormulario.iQuantidade++;
                            stFormulario.stAtendimentoFrm->stCampoReg.iQuantidade = 0;
                            strcpy( stFormulario.stAtendimentoFrm[stFormulario.iQuantidade-1].cidCampo, stCampo.cidCampo );
                        }
                        ULOG( "local13.0" );

                        //Aloca um item para o valor ou dominio
                        stFormulario.stAtendimentoFrm[stFormulario.iQuantidade-1].stCampoReg.stCampo = (struct ST_ATENDIMENTOFORMULARIOCAMPO*) 
                            realloc(stFormulario.stAtendimentoFrm[stFormulario.iQuantidade-1].stCampoReg.stCampo,
                                    sizeof(struct ST_ATENDIMENTOFORMULARIOCAMPO )*(stFormulario.stAtendimentoFrm[stFormulario.iQuantidade-1].stCampoReg.iQuantidade+1));
                        ULOG( "local14.0" );

                        memset( &stFormulario.stAtendimentoFrm[stFormulario.iQuantidade-1].stCampoReg.stCampo[stFormulario.stAtendimentoFrm->stCampoReg.iQuantidade], 0, sizeof( struct ST_ATENDIMENTOFORMULARIOCAMPO ) );
                        stFormulario.stAtendimentoFrm->stCampoReg.iQuantidade++;
                        strcpy( stFormulario.stAtendimentoFrm->stCampoReg.stCampo[stFormulario.stAtendimentoFrm->stCampoReg.iQuantidade-1].cdsValor  , stCampo.cdsValor   );
                        strcpy( stFormulario.stAtendimentoFrm->stCampoReg.stCampo[stFormulario.stAtendimentoFrm->stCampoReg.iQuantidade-1].cidDominio, stCampo.cidDominio );
                    }//if (incluir)
                    ULOG( "local15.0" );
                    contador2++;
                }//while( domAux2 = walkDOM( domAux, "FormularioCampoValorVO", contador2 ) )
                ULOG( "local16.0" );
            }//if( p = walkTree( domAux, "idCampo", 0 ), p )
            ULOG( "local17.0" );
        }//if (valor=walkTree(domAux, "idContatoFolhaCampo", 0 ),valor)
        ULOG( "local18.0" );
    }//if( domAux != NULL )
    ULOG( "local19.0" );
}
//------------------------------------------------------------------------------------------------------------------
void cWF_CERRAMEFEC::execute( void ) 
{
    try
    {
        ST_DADOS_ENTRADA stDadosEntradaEmail;

        // Inicia o encerramento do(s) processo(s)
        cWF_CERRAMEFECPC::encerrarProcessos( &stDadosEntrada );

        // Copia os dados de entrada para utilizar no envio do email
        memcpy((void*)&stDadosEntradaEmail, (const void*)&stDadosEntrada, sizeof(ST_DADOS_ENTRADA));

        // ==> Remodelagem do Atendimento, Março, 2007 - Cassio 
        // O último grupo pelo qual o processo passou fica registrado mesmo após o fechamento
        // para com isso se evitar uma pesquisa em andamento.andamento e obter ganho de
        // performance nas pesquisas de fila.
        // cWF_CERRAMEFECPC::removeAtendimentoGrupoAtual( &stDadosEntrada );

        cWF_CERRAMEFECPC::removeCancelamentoSolicitado( &stDadosEntrada );
        cWF_CERRAMEFECPC::removeAtendimentoSuspeito( &stDadosEntrada );

        if ( flagBloqueioBatch == false )
		{
			sendMail( &stDadosEntradaEmail );
		}
    }
    catch (...)
    {
        throw;
    }
}

