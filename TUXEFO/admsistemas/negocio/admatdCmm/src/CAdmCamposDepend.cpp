/**
 * @modulo  Admsistemas
 * @usecase Admsistemas
 * @author  Max
 * @version $Revision: 1.1.118.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/01/24 17:16:01 $
 **/

#include "../include/CAdmCamposDepend.h"

// =========================================================================
// Prototipos ProC
// =========================================================================
extern bool proCAlteraValor(long idUsuarioPrm,int iNivelPrm,int idSubFormularioPrm,int idCampoPrm,char * nmCampoPrm);
extern void proCAtualizarCampos();
extern void proCCamposValores( int idGrupoCampo,XMLGen * saida );
extern void proCExcluirCampos(long idUsuarioPrm,int idSubFormularioPrm,vector <string> &lstValorExc);
extern void proCExluirHierarquia(int iNivelPrm,int idSubFormularioPrm);
extern void proCGetCamposNivel(DadosEntradaGetCamposNivel dadosEntradaGetCamposNivel,XMLGen * saida );
extern void proCGetCamposNivelArvore( int idSubFormularioPrm, int iNivelPrm, XMLGen * saida );
extern void proCGravaProcArvore(long idUsuarioPrm,int idSubFormularioPrm,int iNivelPrm,int idCampoPrm,const char *nmValorPaiPrm,const char *nmValorFilhoPrm);
extern void proCGravaRelacionamentoCpoDepend(long idUsuarioPrm,VEC_PRODOMHIERARQUIA &vecProcDomHierarquia,const char *nmSubFormularioPrm);
extern void proCInsereValor( char * MsgErro,bool flgRemove,int iNivelPrm,long idUsuarioPrm,int * idSubFormularioPrm,char * nmGrupoPrm,int idContatoPrm,int * idCampoPrm,char * nmCampoPrm,list <string> * lstValorInc,list <string> * lstValorExc);
extern void proCListaArvore( int idSubFormularioPrm, int iNivelPrm, XMLGen * saida );
extern void proCListaGrupos( XMLGen * saida );
extern void proCLupaCarregaCampoDependente(int idSubFormularioPrm,char *nmCampoPrm,XMLGen *saida);
extern void proCRemoverRelacionamentos( int idCampoPrm, long idUsuarioPrm );

const char *obterNmPathAnterior(const char *nmPath,int nivel);

// =========================================================================
// Classe local
// =========================================================================
class TuxHelperImpl:public TuxHelper
{
    public:
        DOMNode * walkDOMImpl( DOMNode*a , char*b , int*c , int d )
        {
            return walkDOM( a , b , c , d );
        }
};

// =========================================================================
// Implementação dos métodos
// =========================================================================
CAdmCamposDepend::CAdmCamposDepend( char * idUsuarioPrm, DOMNode* dnode ,XMLGen* xml_g )
{
    entrada = dnode;
    saida = xml_g;
    idUsuario = atol(idUsuarioPrm);
    CarregaParam();
}


void CAdmCamposDepend::CarregaParam()
{
    ULOG_START( "CAdmCamposDepend::CarregaParam()" );

    char* p;

    if ( p = tx.walkTree( entrada, "inOperacao", 0 ),p ) 
    {
        inOperacao = p;
        XMLString::release(&p);

        if ( false == ObterOperacao(inOperacao.c_str()) )
        {
            throw TuxException("14E9999","Operação inválida do chamador");
        }
    }
    else
    {
        ULOG("tag <inOperacao> não informada");
    }

    idSubFormularioPrm = 0;
    if ( p = tx.walkTree( entrada, "idGrupoCampos", 0 ),p ) 
    {
        idSubFormularioPrm = atoi(p);
        XMLString::release(&p);
        ULOG( "idSubFormularioPrm=%d",idSubFormularioPrm );
    }
    else
    {
        ULOG("tag <idGrupoCampos> não informada");
    }

    if ( p = tx.walkTree( entrada, "idGrupoCamposDependentes", 0 ),p ) 
    {
        idSubFormularioPrm = atoi(p);
        XMLString::release(&p);
        ULOG( "idSubFormularioPrm=%d",idSubFormularioPrm );
    }
    else
    {
        ULOG("tag <idGrupoCamposDependentes> não informada");
    }

    nmCampoPrm[0] = 0x0;
    if ( p = tx.walkTree( entrada, "nmCampo", 0 ),p ) 
    {
        strcpy(nmCampoPrm,p);
        XMLString::release(&p);
        ULOG( "nmCampoPrm=%s",nmCampoPrm );
    }
    else
    {
        ULOG("tag <nmCampoCampo> não informada");
    }

    nmGrupoPrm[0] = 0x0;
    if ( p = tx.walkTree( entrada, "nmGrupoCampos", 0 ),p ) 
    {
        strcpy(nmGrupoPrm,p);
        XMLString::release(&p);
        ULOG( "nmGrupoPrm=%s",nmGrupoPrm );
    }
    else
    {
        ULOG("tag <nmGrupoCampos> não informada");
    }

    if ( p = tx.walkTree( entrada, "idContato", 0 ),p ) 
    {
        idContatoPrm = atoi(p);
        XMLString::release(&p);
        ULOG( "idContatoPrm=%d",idContatoPrm );
    }
    else
    {
        ULOG("tag <idContato> não informada");
    }

    if ( p = tx.walkTree( entrada, "idCampo", 0 ),p ) 
    {
        idCampoPrm = atoi(p);
        XMLString::release(&p);
        ULOG( "idCampoPrm=%d",idCampoPrm );
    }
    else
    {
        ULOG("tag <idCampo> não informada");
    }

    iNivel = 1;
    if ( p = tx.walkTree( entrada, "nrNivel", 0 ),p ) 
    {
        iNivel = atoi(p);
        XMLString::release(&p);
        ULOG( "iNivel=%d",iNivel );
    }
    else
    {
        ULOG("tag <nrNivel> não informada");
    }

    if ( p = tx.walkTree( entrada, "nmPath", 0 ),p ) 
    {
        nmPath = p;
        XMLString::release(&p);
        ULOG( "nmPath=%s",nmPath.c_str());
    }
    else
    {
        ULOG("tag <nmPath> não informada");
    }

    ULOG_END( "CAdmCamposDepend::CarregaParam()" );
}


void CAdmCamposDepend::ListaGrupos( void )
{
    saida->createTag( "AdmCamposFormularioVO" );
        saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
        saida->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
        proCListaGrupos( saida );
    saida->closeTag();

}


void CAdmCamposDepend::InsereValor()
{
    bool flgRemove;
    bool excluirHierarquia = false;
    char *p = 0x0;
    char *result = 0x0;
    char delims[] = "|";
    char MsgErro[256];
    char nmCampo[256];
    char nmCampoFilho[256];
    char nmCampoPai[256];
    DOMNode *pDOMAux;
    DOMNode *pDOMDepend;
    int i,it;
    int iNivelAnterior;
    int iMenorNivel = MAX_NIVEIS+1;
    int j,itDepend;
    list<string> sExc;
    list<string> sInc;
    string str;
    TuxHelper tx;
    TuxHelperImpl tuxhelperIMPL;
    vector<string> vecExc;

    memset(MsgErro,0x0,sizeof(MsgErro));

    saida->createTag( "AdmCamposFormularioVO" );
    saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
    saida->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );

    nmCampoPai[0] = 0x0;
    nmCampoFilho[0] = 0x0;

    for ( j=0;;j++ )  
    {
        itDepend=0;
        pDOMDepend = tuxhelperIMPL.walkDOMImpl( entrada,"AdmGrupoCamposDependentesVO",&itDepend,j );

        if ( pDOMDepend )
        {    
            iNivel = 0;
            if ( p = tx.walkTree( pDOMDepend, "nrNivel", 0 ),p ) 
            {
                iNivel = atoi(p);
                XMLString::release(&p);
            }
            ULOG( "Analisando Nivel [%d]",iNivel );
            if ( iNivel > 0 ) continue;

            idCampoPrm = 0;
            if ( p = tx.walkTree( pDOMDepend, "idCampoDependente", 0 ),p ) 
            {
                idCampoPrm = atoi(p);
                XMLString::release(&p);
            }

            proCRemoverRelacionamentos( idCampoPrm,idUsuario );
        }
        else
        {
            break;
        }
    }

    vecExc.clear();
    // Campos para exclusão
    if ( p = tx.walkTree( entrada, "idsExclusao", 0 ),p ) 
    {
        result = strtok(p,delims);
        while ( result ) 
        {
            str = result;
            ULOG( "Valor a remover [%s]",(char *)str.c_str() );
            vecExc.push_back(str);
            result = strtok( NULL, delims );
        }            
        XMLString::release(&p);
    }

    if ( vecExc.size() > 0 )
    {
        proCExcluirCampos(idUsuario,idSubFormularioPrm,vecExc);
    }

    for ( j=0;;j++ )  
    {
        sInc.clear();
        sExc.clear();
        itDepend=0;
        pDOMDepend = tuxhelperIMPL.walkDOMImpl( entrada,"AdmGrupoCamposDependentesVO",&itDepend,j );
        if ( pDOMDepend )
        {    
            nmCampo[0] = 0x0;
            if ( p = tx.walkTree( pDOMDepend, "nmCampoDependente", 0 ),p ) 
            {
                strcpy(nmCampo,p);
                XMLString::release(&p);
            }

            idCampoPrm = 0;
            if ( p = tx.walkTree( pDOMDepend, "idCampoDependente", 0 ),p ) 
            {
                idCampoPrm = atoi(p);
                XMLString::release(&p);
            }

            iNivel = 0;
            if ( p = tx.walkTree( pDOMDepend, "nrNivel", 0 ),p ) 
            {
                iNivel = atoi(p);
                XMLString::release(&p);
            }

            // Verifica se esta apenas enviando IDs para alteração ao inves de estar
            // querendo excluir ou incluir
            it = 0;
            if ( tuxhelperIMPL.walkDOMImpl( pDOMDepend,"AdmCampoVO",&it,0 ) == NULL )
            {
                if ( proCAlteraValor(idUsuario,iNivel,idSubFormularioPrm,idCampoPrm,nmCampo) )
                {
                    excluirHierarquia = true;
                    if ( iMenorNivel >= iNivel ) { iMenorNivel = iNivel; }
                }
            }
            else
            {
                for ( i=0;;i++ )
                {
                    flgRemove = (i > 0) ? false : true;
                    it = 0;
                    pDOMAux = tuxhelperIMPL.walkDOMImpl( pDOMDepend,"AdmCampoVO",&it,i );
                    if ( pDOMAux )
                    {
                        // Itens a serem incluidos na tabela Dominio
                        if ( p = tx.walkTree( pDOMAux, "IdsInclusao", 0 ),p ) 
                        {
                            result = strtok(p,delims);
                            while( result ) 
                            {
                                str = result;
                                ULOG( "Valor a incluir [%s]",(char *)str.c_str() );
                                sInc.push_back(str);
                                result = strtok( NULL, delims );
                            }            
                            XMLString::release(&p);
                        }
                     
                        // Itens a serem removidos da tabela Dominio
                        if ( p = tx.walkTree( pDOMAux, "IdsExclusao", 0 ),p ) 
                        {
                            result = strtok(p,delims);
                            while( result ) 
                            {
                                str = result;
                                ULOG( "Valor a remover [%s]",(char *)str.c_str() );
                                sExc.push_back(str);
                                result = strtok( NULL, delims );
                            }            
                            XMLString::release(&p);
                        }

                        if ( 1 == iNivel )
                        {
                            strcpy( nmCampoPai,nmCampo );
                            strcpy( nmCampoFilho,nmCampo );
                            iNivelAnterior = iNivel;
                        }
                        else
                        {
                            if ( iNivelAnterior < iNivel )
                            {
                                strcpy( nmCampoPai,nmCampoFilho );
                                strcpy( nmCampoFilho,nmCampo );
                                iNivelAnterior = iNivel;
                            }
                        }

                        proCInsereValor(MsgErro,flgRemove,iNivel,idUsuario,&idSubFormularioPrm
                                       ,nmGrupoPrm,idContatoPrm,&idCampoPrm,nmCampo,&sInc,&sExc);

                        if ( MsgErro[0] )
                        {
                            throw MsgErro;
                        }

                        proCGravaProcArvore(idUsuario,idSubFormularioPrm,iNivel
                                           ,idCampoPrm,nmCampoPai,nmCampoFilho);
                    }
                    else
                    {
                        break;
                    }
                } // for ( i=0;;i++ )
            }
        }
        else
        {
            break;
        }
    }

    // se houve troca de níveis entre campos então exclui a árvore de hierarquia a partir do
    // menor nível trocado.
    if ( excluirHierarquia )
    {
        proCExluirHierarquia(iMenorNivel,idSubFormularioPrm);
    }

    saida->closeTag();
}


void CAdmCamposDepend::ListaArvore()
{
    saida->createTag( "AdmCamposFormularioVO" );
        saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
        saida->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
        ULOG( "Nivel selecionado [%d]",iNivel);
        proCListaArvore( idSubFormularioPrm,iNivel,saida );
    saida->closeTag();
}


void CAdmCamposDepend::AtualizarCampos()
{
    proCAtualizarCampos();
}


void CAdmCamposDepend::GravarArvore()
{
    ULOG_START("CAdmCamposDepend::GravarArvore");

    char *p;
    int idNivelCampoValorAnt = MAX_NIVEIS+1;
    string nmPath;
    string nmPathAnt;

    if ( p = tx.walkTree( entrada, "idGrupo", 0 ),p ) 
    {
        idSubFormularioPrm = atoi(p);
        XMLString::release(&p);
        ULOG( "idSubFormularioPrm (idGrupo)=%d",idSubFormularioPrm );
    }
    else
    {
        throw new TuxException("14E9999","tag <idGrupo> não fornecida");
    }

    if ( p = tx.walkTree( entrada, "nmGrupo", 0 ),p ) 
    {
        nmSubFormularioPrm = p;
        XMLString::release(&p);
        ULOG( "nmSubFormularioPrm (nmGrupo)=[%s]",nmSubFormularioPrm.c_str() );
    }
    else
    {
        throw new TuxException("14E9999","tag <nmGrupo> não fornecida");
    }

    ProcessaDominioHierarquia procDomHierarquia;
    TuxHelper tx;
    VEC_PRODOMHIERARQUIA vecProcDomHierarquia;

    for ( int j=0;;j++ )
    {
        memset(&procDomHierarquia,0,sizeof(procDomHierarquia));

        // Trata o Nivel =======================================================
        p = tx.walkTree(entrada,"nivel",j);

        if (!p) { break; }

        int idNivelCampoValor = atoi(p);
        XMLString::release(&p);

        if (  0 == idNivelCampoValor) { continue; }

        if ( idNivelCampoValor == 1 )
        {
            nmPath.erase();
        }
        else if ( idNivelCampoValor < idNivelCampoValorAnt )
        {
            nmPath = obterNmPathAnterior((char*)nmPath.c_str(),idNivelCampoValor);
            nmPathAnt = nmPath;
        }
        else if ( idNivelCampoValor > idNivelCampoValorAnt )
        {
            nmPath = obterNmPathAnterior((char*)nmPath.c_str(),idNivelCampoValor);
            nmPathAnt = nmPath;
        }
        else if ( idNivelCampoValor >= idNivelCampoValorAnt )
        {
            nmPath = nmPathAnt;
        }

        idNivelCampoValorAnt = idNivelCampoValor;

        // idPai ===============================================================
        if ( p = tx.walkTree( entrada, "idPai", j ), p )
        {
            procDomHierarquia.idDominioPai = atoi(p);
            ULOG("idPai=[%s]",p);
            XMLString::release(&p);
        }
        else
        {
            break;
            //throw new TuxException("14E9999","Tag <idPai> não informada");
        }

        // id ===============================================================+++
        if ( p = tx.walkTree( entrada, "id", j ), p )
        {
            procDomHierarquia.idDominioFilho = atoi(p);

            ULOG("id=[%s]",p);
            XMLString::release(&p);
        }
        else
        {
            throw new TuxException("14E9999","Tag <id> não informada");
        }

        // descricaoPai ========================================================
        if ( p = tx.walkTree( entrada, "descricaoPai", j ), p )
        {
            procDomHierarquia.nmDominioPai = p;
            ULOG("nmDominioPai=[%s]",p);
            XMLString::release(&p);
        }
        else
        {
            throw new TuxException("14E9999","Tag <descricaoPai> não informada");
        }

        // descricao ===========================================================
        if ( p = tx.walkTree( entrada, "descricao", j ), p )
        {
            //if ( nmPath.size() ) { nmPath += "/"; }
            nmPath += p;
            nmPath += "/";

            procDomHierarquia.nmDominioFilho = p;
            ULOG("nmDominioFilho=[%s]",p);
            XMLString::release(&p);
        }
        else
        {
            throw new TuxException("14E9999","Tag <descricao> não informada");
        }

        // idCampo =============================================================
        if ( p = tx.walkTree( entrada, "idCampo", j ), p )
        {
            procDomHierarquia.idCampo = atoi(p);
            ULOG("idCampo=[%s]",p);
            XMLString::release(&p);
        }
        else
        {
            throw new TuxException("14E9999","Tag <idCampo> não informada");
        }

        // insere
        procDomHierarquia.nmPath = nmPath;
        procDomHierarquia.idSubFormulario = idSubFormularioPrm;

        vecProcDomHierarquia.push_back(procDomHierarquia);
    }

    saida->createTag( "AdmCamposFormularioVO" );
    saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
    saida->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );

    if ( vecProcDomHierarquia.size() > 0 )
    {
        for (int i=0; i<vecProcDomHierarquia.size(); i++)
        {
            ULOG("idDominioPai=%d,idDominioFilho=%d,nmDominioPai=%s,nmDominioFilho=%s,nmPath=%s"
                ,vecProcDomHierarquia[i].idDominioPai,vecProcDomHierarquia[i].idDominioFilho
                ,vecProcDomHierarquia[i].nmDominioPai.c_str()
                ,vecProcDomHierarquia[i].nmDominioFilho.c_str()
                ,vecProcDomHierarquia[i].nmPath.c_str());
        }

        proCGravaRelacionamentoCpoDepend(idUsuario,vecProcDomHierarquia,nmSubFormularioPrm.c_str());
    }
    else
    {
        ULOG("Nós da árvore não enviados");
    }

    saida->closeTag();

    ULOG_END("CAdmCamposDepend::GravarArvore");
}


void CAdmCamposDepend::ListaCamposNiveis()
{
    saida->createTag( "AdmCamposFormularioVO" );
    saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
    saida->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
        saida->createTag( "AdmGruposCamposDependentesVO" );
            ULOG( "Nivel selecionado [%d]",iNivel);
            proCGetCamposNivelArvore(idSubFormularioPrm,iNivel,saida);
        saida->closeTag();
    saida->closeTag();
}


void CAdmCamposDepend::LupaGetCampo()
{
    proCLupaCarregaCampoDependente(idSubFormularioPrm,nmCampoPrm,saida);
}

void CAdmCamposDepend::CamposValores()
{
    saida->createTag( "AdmCamposFormularioVO" );
        saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
        saida->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );
        proCCamposValores( idSubFormularioPrm,saida );
    saida->closeTag();
}


void CAdmCamposDepend::ListaProximoNivel()
{
    ULOG("Nivel selecionado[%d]", iNivel);

    saida->createTag( "AdmCamposFormularioVO" );
    saida->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
    saida->addProp("xmlns:ns2", "cliente.fo.vivo.com.br/vo" );
    saida->addProp("xmlns:ns1", "usuario.fo.vivo.com.br/vo" );

        saida->createTag( "AdmGruposCamposDependentesVO" );

            DadosEntradaGetCamposNivel dadosEntradaGetCamposNivel;

            dadosEntradaGetCamposNivel.idDominioPai = idCampoPrm;
            dadosEntradaGetCamposNivel.idSubFormulario = idSubFormularioPrm;
            dadosEntradaGetCamposNivel.idNivel = iNivel+1;
            dadosEntradaGetCamposNivel.nmPath = nmPath.c_str();

            proCGetCamposNivel(dadosEntradaGetCamposNivel,saida);

        saida->closeTag();

    saida->closeTag();
}

bool CAdmCamposDepend::ObterOperacao(const char *inOperacao)
{
    struct
    {
        int idOperacao;
        char *inOperacao;

    } TiposDeOperacao[] =
    {
        {OPERACAO_GETCAMPOSDEPENDENTES,"GETCAMPOSDEPENDENTES"},
        {OPERACAO_SETCAMPOSVALORES,"SETCAMPOSVALORES"},
        {OPERACAO_GETCAMPOSVALORES,"GETCAMPOSVALORES"},
        {OPERACAO_GETTREE,"GETTREE"},
        {OPERACAO_GETCAMPOSNIVEL,"GETCAMPOSNIVEL"},
        {OPERACAO_PROXIMONIVEL,"PROXIMONIVEL"},
        {OPERACAO_SETTREE,"SETTREE"},
        {OPERACAO_LUPAGETCAMPODPD,"LUPAGETCAMPODPD"},
        {-1,NULL}
    };

    int it = 0;
    while ( TiposDeOperacao[it].inOperacao )
    {
        if ( strcmp(inOperacao,TiposDeOperacao[it].inOperacao) == 0 )
        {
            iOperacao = TiposDeOperacao[it].idOperacao;

            ULOG("iOperacao=%d para inOperacao=[%s]",iOperacao,inOperacao);

            return true;
        }
        it++;
    }

    ULOG("inOperacao=[%s] não possui iOperacao respectivo.",inOperacao);

    return false;
}


//================================================================================================
// Função de suporte ao método proCGetCamposNivel().
// Julho, 2007 -- Cassio.
const char *obterNmPathAnterior(const char *nmPath,int nivel)
{
    char seps[]   = "/";
    char destino[256] = "";
    char nmPathTemp[4001];
    static string nmPathAnterior;

    strcpy(nmPathTemp,nmPath);

    nmPathAnterior.erase();

    char *token = strtok( nmPathTemp, seps );

    while( token && --nivel )
    {
        nmPathAnterior += token;
        nmPathAnterior += "/";

        token = strtok( 0, seps );
    }

    return nmPathAnterior.c_str();
}
