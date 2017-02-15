
#include "../include/svcmain.h"

DECLARE_TUXEDO_SERVICE(WRITERETORNO);

class TuxHelperImpl:public TuxHelper
{
    public:
        DOMNode * walkDOMImpl( DOMNode*a , char*b , int*c , int d )
        {
                return walkDOM( a , b , c , d );
        }
};

void implWRITERETORNO::Execute( DOMNode*XMLIn , XMLGen*XMLOut )
{
    ULOG_START("implWRITERETORNO::Execute()");

    bool tratar = false;
    char *pidContato;
    char *pRetorno;
    char *p;
    int pRetornoContato;
    int index;
    int numRegistros = 0;
    int i = 0;
    int iRec = 0;
    long idTipoCarteiraArray[500];
    long idSegmentacaoArray[500];
    long idTipoLinhaArray[500];
    long idTipoRelacionamentoArray[500];
    long idTipoPessoaArray[500];
    long idCanalArray[500];
    long idProcedenciaArray[500];
    long idGrupoArray[500];
    long idUfOperadoraArray[500];
    unsigned long pRetornoTipoContato;

    DOMNode        *pDOMAux;
    TuxHelperImpl   tuxhelperIMPL;

    pidContato = walkTree( XMLIn, "idContato",0 );

    if( !pidContato )
    {
        ULOG_END("implWRITERETORNO::Execute()");
        throw new TuxBasicSvcException("09E0001","Nao Encontrou idContato");
    }

    if( atoi(pidContato) == 0 )
    {
        XMLString::release(&pidContato);
        ULOG_END("implWRITERETORNO::Execute()");
        throw new TuxBasicSvcException("09E0002","Nao Encontrou idContato");
    }

    pRetorno = walkTree( XMLIn, "idTipoRetornoAtivo",0 );

    if ( !pRetorno )
    {
        XMLString::release(&pidContato);
        ULOG_END("implWRITERETORNO::Execute()");
        throw new TuxBasicSvcException("09E0001","Nao Encontrou idTipoRetornoAtivo");
    }

    if ( *pRetorno == 0 )
    {
        XMLString::release(&pidContato);
        ULOG_END("implWRITERETORNO::Execute()");
        throw new TuxBasicSvcException("09E0002","Nao Encontrou idTipoRetornoAtivo");
    }

    // TipoLinha
    index = 0;
    for ( i=0;;i++ )  
    {
        iRec = 0;
        pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"AdmTipoLinhaVO",&iRec,i );
        if ( pDOMAux == NULL )
            break;
        p = walkTree( pDOMAux,"idTipoLinha",0 );
        if( p == NULL )
            break;

        if( *p )
        {
            if ( !tratar ) { tratar = true; }
            idTipoLinhaArray[index++] = atol(p);
        }
    }
    idTipoLinhaArray[index] = -1L;

    // Segmentacao
    index = 0;
    for ( i=0;;i++ )  
    {
        iRec = 0;
        pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"AdmSegmentacaoVO",&iRec,i );
        if ( pDOMAux == NULL )
            break;
        p = walkTree( pDOMAux,"idSegmentacao",0 );
        if( p == NULL )
            break;
        if ( *p ) { idSegmentacaoArray[index++] = atol(p); }
        XMLString::release(&p);
    }
    idSegmentacaoArray[index] = -1L;

    // TipoCarteira
    index = 0;
    for ( i=0;;i++ )  
    {
        iRec = 0;
        pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"AdmCarteirizacaoVO",&iRec,i );
        if ( pDOMAux == NULL )
            break;
        p = walkTree( pDOMAux,"idCarteirizacao",0 );
        if( p == NULL )
            break;
        if ( *p ) { idTipoCarteiraArray[index++] = atol(p); }
        XMLString::release(&p);
    }
    idTipoCarteiraArray[index] = -1L;

    // TipoRelacionamento
    index = 0;
    for ( i=0;;i++ )  
    {
        iRec = 0;
        pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"AdmTipoClienteVO",&iRec,i );
        if ( pDOMAux == NULL )
            break;
        p = walkTree( pDOMAux,"idTipoCliente",0 );
        if( p == NULL )
            break;
        if ( *p ) { idTipoRelacionamentoArray[index++] = atol(p); }
        XMLString::release(&p);
    }
    idTipoRelacionamentoArray[index] = -1L;

    // TipoPessoa
    index = 0;
    for ( i=0;;i++ )  
    {
        iRec = 0;
        pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"AdmPessoaVO",&iRec,i );
        if ( pDOMAux == NULL )
            break;
        p = walkTree( pDOMAux,"idPessoa",0 );
        if( p == NULL )
            break;
        if ( *p ) { idTipoPessoaArray[index++] = atol(p); }
        XMLString::release(&p);
    }
    idTipoPessoaArray[index] = -1L;

    // Procedencia
    index = 0;
    for ( i=0;;i++ )  
    {
        iRec = 0;
        pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"AdmProcedenciaVO",&iRec,i );
        if ( pDOMAux == NULL )
            break;
        p = walkTree( pDOMAux,"idProcedencia",0 );
        if( p == NULL )
            break;
        if ( *p ) { idProcedenciaArray[index++] = atol(p); }
        XMLString::release(&p);
    }
    idProcedenciaArray[index] = -1L;

    // Grupo
    index = 0;
    for ( i=0;;i++ )  
    {
        iRec = 0;
        pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"AdmGrupoVO",&iRec,i );
        if ( pDOMAux == NULL )
            break;
        p = walkTree( pDOMAux,"idGrupo",0 );
        if( p == NULL )
            break;
        if ( *p ) { idGrupoArray[index++] = atol(p); }
        XMLString::release(&p);
    }
    idGrupoArray[index] = -1L;

    // Canal de Entrada
    index = 0;
    for ( i=0;;i++ )  
    {
        iRec = 0;
        pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"AdmCanalEntradaVO",&iRec,i );
        if ( pDOMAux == NULL )
            break;
        p = walkTree( pDOMAux,"idCanalEntrada",0 );
        if( p == NULL )
            break;
        if ( *p ) { idCanalArray[index++] = atol(p); }
        XMLString::release(&p);
    }
    idCanalArray[index] = -1L;

    // UFOperadora de Entrada
    index = 0;
    for ( i=0;;i++ )  
    {
        iRec = 0;
        pDOMAux = tuxhelperIMPL.walkDOMImpl( XMLIn,"AdmTipoUfOperadoraVO",&iRec,i );
        if ( pDOMAux == NULL )
            break;
        p = walkTree( pDOMAux,"idUfOperadora",0 );
        if( p == NULL )
            break;
        if ( *p ) { idUfOperadoraArray[index++] = atol(p); }
        XMLString::release(&p);
    }
    idUfOperadoraArray[index] = -1L;


    /* Valida se existe outro Tipo de Retorno cadastrado com as mesmas variaveis
       para atender a solicitação documentada na incidência 3271.
    */
    ULOG( "Tratar: %d",  tratar);
    if ( tratar )
    {
         numRegistros = 
             RegistrosInconsistentes( pidContato,pRetorno
                                    ,idTipoLinhaArray,idSegmentacaoArray
                                    ,idTipoCarteiraArray,idTipoPessoaArray
                                    ,idTipoRelacionamentoArray,idGrupoArray
                                    ,idCanalArray,idProcedenciaArray
                                    ,idUfOperadoraArray);
    }

    if ( numRegistros > 0 )
    {
        setStatusCode( "04I0000","Sucesso Na Execucao" );

        XMLOut->createTag("WFAcaoRetornoVO");
        XMLOut->addProp("xmlns","workflow.fo.vivo.com.br/vo");
        
        XMLOut->addItem("acaoExecucao","0");
        XMLOut->addItem("mensagem","Existe Tipo de Retorno Cadastrado com as mesmas variaveis.");
        ULOG_END("implWRITERETORNO::Execute()");
        return;
    }

    /*
    *  Remove dados nas tabelas a serem atualizadas
    */
   pRetornoContato = ObtemContatoRetorno(pidContato, pRetorno);
   if (pRetornoContato > 0)
   {
        if ( !RemoveCanalEntrada(pRetornoContato) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            return;
        }

        if ( !RemoveGrupo(pRetornoContato) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            return;
        }

        if ( !RemovePessoa(pRetornoContato) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            return;
        }

        if ( !RemoveProcedencia(pRetornoContato) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            return;
        }

        if ( !RemoveRelacionamento(pRetornoContato) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            return;
        }

        if ( !RemoveSegmentacao(pRetornoContato) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            return;
        }

        if ( !RemoveTipoCarteira(pRetornoContato) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            return;
        }

        if ( !RemoveTipoLinha(pRetornoContato) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            return;
        }

        if ( !RemoveTipoRetornoUfOperadora(pRetornoContato) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            return;
        }

        if ( !RemoveContatoTipoRetorno(pRetornoContato) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            return;
        }
    }

   /*
    *  Atualiza os dados nas tabelas se existir ao menos uma variável associada ao
    *  tipo de retorno do contato.
    */
    if ( idCanalArray[0] != -1 ||
         idGrupoArray[0] != -1 ||
         idTipoPessoaArray[0] != -1 ||
         idProcedenciaArray[0] != -1 ||
         idTipoRelacionamentoArray[0] != -1 ||
         idSegmentacaoArray[0] != -1 ||
         idTipoCarteiraArray[0] != -1 ||
         idTipoLinhaArray[0] != -1 ||
         idUfOperadoraArray[0] != -1 )
    {
        pRetornoTipoContato = InsereRetornoTipoContato(pidContato, pRetorno);

        if (pRetornoTipoContato == 0)
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            throw new TuxBasicSvcException("09E0006","INSERT: TIPO RETORNO CONTATO");
        }

        if ( !InsereCanalEntrada( pRetornoTipoContato, idCanalArray ) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            throw new TuxBasicSvcException("09E0006","INSERT: CANAL ENTRADA");
        }

        if ( !InsereGrupo( pRetornoTipoContato, idGrupoArray ) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            throw new TuxBasicSvcException("09E0007","INSERT: GRUPO");
        }

        if ( !InserePessoa( pRetornoTipoContato, idTipoPessoaArray ) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            throw new TuxBasicSvcException("09E0008","INSERT: PESSOA");
        }

        if ( !InsereProcedencia( pRetornoTipoContato, idProcedenciaArray ) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            throw new TuxBasicSvcException("09E0009","INSERT: PROCEDENCIA");
        }

        if ( !InsereRelacionamento( pRetornoTipoContato, idTipoRelacionamentoArray ) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            throw new TuxBasicSvcException("09E0009","INSERT: RELACIONAMENTO");
        }

        if ( !InsereSegmentacao( pRetornoTipoContato, idSegmentacaoArray ) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            throw new TuxBasicSvcException("09E0009","INSERT: SEGMENTACAO");
        }

        if ( !InsereTipoCarteira( pRetornoTipoContato, idTipoCarteiraArray ) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            throw new TuxBasicSvcException("09E0009","INSERT: TIPO CARTEIRA");
        }

        if ( !InsereTipoLinha( pRetornoTipoContato, idTipoLinhaArray ) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            throw new TuxBasicSvcException("09E0009","INSERT: TIPO LINHA");
        }

        if ( !InsereTipoRetornoUfOperadora( pRetornoTipoContato, idUfOperadoraArray ) )
        {
            XMLString::release(&pidContato);
            XMLString::release(&pRetorno);
            ULOG_END("implWRITERETORNO::Execute()");
            throw new TuxBasicSvcException("09E0009","INSERT: UFOPERADORA");
        }
    }

   XMLString::release(&pidContato);
   XMLString::release(&pRetorno);

   setStatusCode("09I0000","Sucesso Na Execucao");
   XMLOut->createTag("WFAcaoRetornoVO");
   XMLOut->addProp("xmlns","workflow.fo.vivo.com.br/vo");

   XMLOut->addItem("acaoExecucao","0");
   XMLOut->addItem("mensagem","Informações Atualizadas com Sucesso");

   ULOG_END("implWRITERETORNO::Execute()");

   return;
}

