/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @remarks Rotinas comuns aos relatórios 1 a 6
 * @version $Revision: 1.1.126.1 $
 * @CVS     $Author: a5114242 $ - $Date: 2014/02/04 14:49:31 $
 **/

#define _CWFCOMUNSRELATORIOS_EXTERN_

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>

#include <tuxfw.h>
#include "../include/cWFComunsRelatorios.h"
#include "../../../commons/msgPadrao.h"

extern char* procGetIdGrupo(char* sNmGrupo,char *idGrupoRetorno);
extern char* procGetIdGrupoOperadora(char* sNmGrupoOperadora,char *idGrupoOperadoraRetorno);
extern char* procGetIdUf(char* sNmUf,char *idUfRetorno);
extern char* procGetIdPessoaUsuario(char* sNmLoginUsuario,char *idPessoaUsuarioRetorno);


typedef struct { unsigned short len; unsigned char arr[1]; } VARCHAR;
typedef struct { unsigned short len; unsigned char arr[1]; } varchar;

// Esta estrutura contém campos os quais os conteúdos alfanuméricos
// podem ter sido digitados pelos usuários com caracteres minúsculos
// ou maiúsculos. Todos os campos aqui presentes irão fazer com que
// a cláusula ORDER BY dos relatórios sejam acrescidas da instrução
// UPPER(nome do campo).
struct 
{
    char *nomeCampo;
} camposAlfanumericos[] = { "UF"
                          , "nmGrupoOperadora"
                          , "nmGrupo"
                          , "nmNome"
                          , "nmPessoa"
                          , "nmLoginUsuario"
                          , "nmLoginUsuarioAtual"
                          , 0 };

struct ColunasFixas
{
    int idColunaFixa;
    char *nomeColuna;
} colunasFixas[] = {
                    /*00*/ ID_DATA_MAIS_ANTIGA,"Data mais Antiga",
                    /*01*/ ID_TOTAL_DENTRO_PRAZO,"Total Dentro do Prazo",
                    /*02*/ ID_TOTAL_FORA_PRAZO,"Total Fora do Prazo",
                    /*03*/ ID_QTDE_FECHADOS,"Qtde Fechados",
                    /*04*/ ID_NUM_TENT_RETORNO,"Num Tentativas Retorno",
                    /*05*/ ID_NUM_TENT_TRATMTO,"Num Tentativas Tratamento",
                    /*06*/ ID_NUM_ENCERRADOS,"Num Encerrados",
                    /*07*/ ID_MOTIVO_FECHMTO,"Motivo Fechamento",
                    /*08*/ ID_NUM_FECHADOS,"Num Fechados",
                    /*09*/ ID_NOME_REPRESENTANTE,"Nome Representante",
                    /*10*/ ID_ID_LOGON_REPRES,"ID Logon Representante",
                    /*11*/ ID_ESTR_ORG,"Estrutura Organizacional",
                    /*12*/ ID_TEMPO_TRATAMENTO,"Tempo de Tratamento",
                    /*13*/ ID_TEMPO_ATENDIMENTO,"Tempo Atendimento",
                    /*14*/ ID_ID_ANDAMENTO,"ID Andamento",
                    /*15*/ ID_ID_ATENDIMENTO,"ID Atendimento",
                    /*16*/ ID_TEMPO_DEMORA_DIAS,"Tempo de Pausa do Processo (dias)",
                    /*17*/ ID_OPERADORA,"Operadora ",
                    /*18*/ ID_UF,"UF ",
                    /*19*/ ID_NUM_PROCESSO,"N. Processo",
                    /*20*/ ID_DATA_ABERTURA,"Data da Abertura",
                    /*21*/ ID_DATA_ENTR_GRUPO,"Data Entrada Grupo",
                    /*22*/ ID_GRUPO_ATUAL,"Grupo Atual",
                    /*23*/ ID_GRUPO_ABERTURA,"Grupo Abertura",
                    /*24*/ ID_USUARIO_ABERTURA,"Usuário Abertura",
                    /*25*/ ID_CARTEIRA,"Carteira ",
                    /*26*/ ID_SEGMENTO,"Segmento ",
                    /*27*/ ID_FONE_CONTATO,"Telefone Contato",
                    /*28*/ ID_TIPO_CLIENTE,"Tipo Cliente",
                    /*29*/ ID_ESTADO_PROCESSO,"Status Processo",
                    /*30*/ ID_SUBEST_PROCESSO,"Sub Status Processo",
                    /*31*/ ID_ARVORE_CONTATO,"Árvore Contato",
                    /*32*/ ID_HORAS_DECORRIDAS,"Horas Decorridas",
                    /*33*/ ID_PRAZO_ANATEL,"Prazo ANATEL",
                    /*34*/ ID_PRAZO_INTERNO,"Prazo Vivo",
                    /*35*/ ID_NOME_USU_ABER,"Nome Usuário Abertura",
                    /*36*/ ID_TMP_PSA_PROCESSO,"Tempo de Pausa do Processo",
                    /*37*/ ID_DIAS_PSA_PROCESSO,"Dias de Pausa do Processo",
                    /*38*/ ID_PATH,"Tipo do Processo",
                    /*39*/ ID_NOME_RESPONSAVEL,"Nome Responsável",
                    /*40*/ ID_NOME_GESTOR,"Gestor",
                    /*41*/ ID_NOME_USUARIO_ATUAL,"Nome Usuario Atual",
                    /*42*/ ID_LOGIN_USU_ATUAL,"Login Usuario Atual",
                    /*43*/ ID_DATA_DEVOLUCAO,"Data de Devolução",
                    /*44*/ ID_NUM_PROCESSOS,"Num. Processos",
                    /*45*/ ID_RAZ_SOCIAL,"Razão Social",
                    /*46*/ ID_NUM_DOCUMENTO,"CNPJ",
                    /*47*/ ID_NUM_PROTOCOLO,"N. Protocolo",
                    /*48*/ ID_NUM_LINHA,"N. Linha",
                    -1,0
                    };
BEGIN_EXTERN_C

void WFAtdRelSqlErro(sqlca*sqlca)
{
    ULOGE("WFAtdRelSqlErro:sqlcode=%d,sqlerrmc=%.70s"
                              ,sqlca->sqlcode
                              ,sqlca->sqlerrm.sqlerrmc);

    throw new TuxBasicOraException(sqlca->sqlcode
                                  ,sqlca->sqlerrm.sqlerrmc
                                  ,sqlca->sqlerrm.sqlerrml);
}

void WFAtdRelCompletarWhere(string &where,DOMNode *entrada,char *dnode)
{
    bool firstTime = true;
    char *p0;
    char *p1;
    DOMNode *dn;
    int index = 0;
    TuxHelper tx;

    if ( !entrada )
    {
        ULOGE(erroPonteiroInvalido());
        throw new TuxBasicSvcException("04E9999",erroPonteiroInvalido());
    }

    if ( !dnode )
    {
        ULOGE(erroPonteiroInvalido());
        throw new TuxBasicSvcException("04E9999",erroPonteiroInvalido());
    }

    while ( dn = tx.walkDOM(entrada,dnode,index++ ) )
    {
        if ( !strcmp(dnode,"WFRelatoriosFiltroRegionalVO") )
        {
            if ( p0 = tx.walkTree(dn,"idRegional",0),p0 )
            {
                if ( firstTime )
                {
                    if ( where.size() ) where += " AND ";
                    where += "(";
                    firstTime = false;
                }
                else
                {
                    where += " OR ";
                }
                where += "idUFOperadora=" + (string) p0;
                XMLString::release(&p0);
            }
        }
        if ( !strcmp(dnode,"WFFaseVO") )
        {
            if ( p0 = tx.walkTree(dn,"idFase",0),p0 )
            {
                if ( firstTime )
                {
                    if ( where.size() ) where += " AND ";
                    where += "(";
                    firstTime = false;
                }
                else
                {
                    where += " OR ";
                }
                where += " idFase=" + (string) p0;
                XMLString::release(&p0);
            }
        }
        else if ( !strcmp(dnode,"StatusUsuarioVO") )
        {
            if ( p0 = tx.walkTree(dn,"idStatus",0),p0 )
            {
                if ( firstTime )
                {
                    if ( where.size() ) where += " AND ";
                    where += "(";
                    firstTime = false;
                }
                else
                {
                    where += " OR ";
                }
                where += " idStatusUsuario=" + (string) p0;
                XMLString::release(&p0);
            }
        }
/*
        else if ( !strcmp(dnode,"WFRelatoriosFiltroOperadoraVO") )
        {
            if ( p0 = tx.walkTree(dn,"idOperadora",0),p0 )
            {
                if ( firstTime )
                {
                    if ( where.size() ) where += " AND ";
                    where += "(";
                    firstTime = false;
                }
                else
                {
                    where += " OR ";
                }
                where += " idOperadora=" + (string) p0;
                XMLString::release(&p0);
            }
        }
*/
        else if ( p0 = tx.walkTree(dn,"idGrupo",0),p0 )
        {
            if ( firstTime )
            {
                if ( where.size() ) where += " AND ";
                where += "(";
                firstTime = false;
            }
            else
            {
                where += " OR ";
            }
            where += " idGrupo=" + (string) p0;
            XMLString::release(&p0);
        }
        else if ( p0 = tx.walkTree(dn,"idEstado",0),p0 )
        {
            if ( firstTime )
            {
                if ( where.size() ) where += " AND ";
                where += "(";
                firstTime = false;
            }
            else
            {
                where += " OR ";
            }
            where += " idEstado=" + (string) p0;
            XMLString::release(&p0);
        }
        else if ( p0 = tx.walkTree(dn,"idColuna",0),p0 )
        {
            p1 = tx.walkTree(dn,"valor",0);

            if ( p1 )
            {
                if ( *p1 )
                {
                    if ( where.size() ) where += " AND ";
                    where += (string)p0 + " = '" + (string)p1 + "'";
                }
                else
                {
                    if ( where.size() ) where += " AND ";
                    where += (string)p0 + (string)" IS NULL";
                }

                XMLString::release(&p1);
            }
            else
            {
                Mensagem me = Endereco(__LINE__,__FILE__).mensagem("campo 'valor' para '%s' "
                                                                   "do %do donNode '%s' nao definido"
                                                                  ,p0,index,dnode);
                ULOGE("%s",me.MsgPadrao());
                throw new TuxBasicSvcException("04E9999",me.MsgPadrao());
            }
            XMLString::release(&p0);
        }
    }

    if ( !firstTime )
    {
        where += ")";
    }
}

void WFAtdRelCompletarWhereDetalhe(string &where,DOMNode *entrada,char *dnode)
{
    bool firstTime = true;
    char buffer[256];
    char *p0;
    char *p1;
    DOMNode *dn;
    int index = 0;
    TuxHelper tx;

    if ( !entrada )
    {
        ULOGE(erroPonteiroInvalido());
        throw new TuxBasicSvcException("04E9999",erroPonteiroInvalido());
    }

    if ( !dnode )
    {
        ULOGE(erroPonteiroInvalido());
        throw new TuxBasicSvcException("04E9999",erroPonteiroInvalido());
    }

    while ( dn = tx.walkDOM(entrada,dnode,index++ ) )
    {
        if ( p0 = tx.walkTree(dn,"idColuna",0),p0 )
        {
            p1 = tx.walkTree(dn,"valor",0);

            if ( p1 )
            {
                if ( *p1 )
                {
					if (strcmp(p0,"idAtendimento") == 0 )
					{
	                    if ( where.size() ) where += " AND ";
						where += (string)p0 + " = " + (string)p1 + "";
					}
					else if (strcmp(p0,"nmGrupo") == 0 )
					{
	                    if ( where.size() ) where += " AND ";
						where += " idGrupo = " + (string)procGetIdGrupo(p1,buffer) + "";
					}
					else if (strcmp(p0,"nmGrupoOperadora") == 0 )
					{
	                    if ( where.size() ) where += " AND ";
						where += " idOperadora = " + (string)procGetIdGrupoOperadora(p1,buffer) + "";
					}
					else if (strcmp(p0,"UF") == 0 )
					{
	                    if ( where.size() ) where += " AND ";
						where += " idUf = " + (string)procGetIdUf(p1,buffer) + "";
					}
					else if (strcmp(p0,"nmLoginUsuario") == 0 )
					{
	                    if ( where.size() ) where += " AND ";
						where += " idPessoaUsuario = " + (string)procGetIdPessoaUsuario(p1,buffer) + "";
					}
					else if (strcmp(p0,"tempoDemoraDias") == 0 )
					{
	                    if ( where.size() ) where += " AND ";
	                    where += (string)p0 + " = " + (string)p1 + "";
					}
                }
				else
				{
					if (strcmp(p0,"nmLoginUsuario") == 0 )
					{
	                    if ( where.size() ) where += " AND ";
						where += " idPessoaUsuario IS NULL ";
					}
				}
                XMLString::release(&p1);
            }
            else
            {
                Mensagem me = Endereco(__LINE__,__FILE__).mensagem("campo 'valor' para '%s' "
                                                                   "do %do donNode '%s' nao definido"
                                                                  ,p0,index,dnode);
                ULOGE("%s",me.MsgPadrao() );
                throw new TuxBasicSvcException("04E9999",me.MsgPadrao());
            }
            XMLString::release(&p0);
        }
    }

    if ( !firstTime )
    {
        where += ")";
    }

    ULOG( "where=%s",where.c_str() );
}

void WFAtdRelGerarHeaderSaidaXML(string &nmColunas,XMLGen *saida,DOMNode*entrada)
{
    if ( !nmColunas.size() )
    {
        ULOGE("%s",mensagemSimples("nomes de colunas nao informados"));
        throw new TuxBasicSvcException("04E9999",mensagemSimples("nomes de colunas nao informados"));
    }

    string header;
    TuxHelper tx;

    char seps[] = ",";
    char *token = strtok( (char*)nmColunas.c_str(), seps );
    DOMNode *dn;

    int index = 0;

    while( token )
    {
        header += token; // idColuna
        header += ",";

        //if ( stricmp(token,"Data mais Antiga")
        //  && stricmp(token,"Total Dentro do Prazo")
        //  && stricmp(token,"Total Fora do Prazo")
        //  && stricmp(token,"Qtde Fechados")
        //  && stricmp(token,"Num Tentativas Retorno")
        //  && stricmp(token,"Num Tentativas Tratamento")
        //  && stricmp(token,"Num Encerrados")
        //  && stricmp(token,"Motivo Fechamento")
        //  && stricmp(token,"Num Fechados")
        //    )

        if ( !isColunaFixa(token) )
        {
            if ( dn = tx.walkDOM(entrada,"WFRelatoriosQuebraVO",index++ ),dn )
            {
                char *dsCampo = tx.walkTree(dn,"dsCampo",0);

                if ( dsCampo )
                {
                    header += dsCampo;
                    XMLString::release(&dsCampo);
                }
                else
                {
                    header += "(void)";
                }
            }
            else
            {
                header += "(void)";
            }

            header += ",";
        }
        else
        {
            header += "(void),";
        }

        token = strtok( NULL, seps );
    }

    ULOG( "nmColunas=%s",nmColunas.c_str() );

    ULOG( "header=%s",header.c_str() );

    token = strtok( (char*)header.c_str(), seps );

    while( token )
    {
        saida->createTag("ColunasRelatorio");

        //if ( stricmp(token,"Data mais Antiga") == 0
        //  || stricmp(token,"Total Dentro do Prazo") == 0
        //  || stricmp(token,"Total Fora do Prazo") == 0
        //  || stricmp(token,"Qtde Fechados") == 0
        //  || stricmp(token,"Num Tentativas Retorno") == 0
        //  || stricmp(token,"Num Tentativas Tratamento") == 0
        //  || stricmp(token,"Num Encerrados") == 0
        //  || stricmp(token,"Motivo Fechamento") == 0
        //  || stricmp(token,"Num Fechados") == 0
        //    )

        if ( isColunaFixa(token) )
        {
            saida->createTag("idColuna");
            saida->closeTag();

            saida->addItem("dsColuna",token);
            token = strtok( NULL, seps );
        }
        else
        {
            saida->addItem("idColuna",token);

            if ( token = strtok( NULL, seps ),token )
            {
                if ( strcmp(token,"(void)") )
                {
                    saida->addItem("dsColuna",token);
                }
                else
                {
                    saida->createTag("dsColuna");
                    saida->closeTag();
                }
            }
            else
            {
                saida->createTag("dsColuna");
                saida->closeTag();
            }
        }

        saida->closeTag();

        token = strtok( NULL, seps );
    }
}

void WFAtdRelGerarDadosSaidaXML(void *vlColunas,XMLGen *saida)
{
    if ( !vlColunas )
    {
        ULOGE("%s",erroPonteiroInvalido());
        throw new TuxBasicSvcException("04E9999",erroPonteiroInvalido());
    }

    if ( !saida )
    {
        ULOGE("%s",erroPonteiroInvalido());
        throw new TuxBasicSvcException("04E9999",erroPonteiroInvalido());
    }

    saida->createTag("ValorColuna");
        if ( (char*)((VARCHAR*)vlColunas)->arr )
        {
            saida->addItem("valor",(char*)((VARCHAR*)vlColunas)->arr);
        }
        else
        {
            saida->createTag("valor");
            saida->closeTag();
        }
    saida->closeTag();
}

char * rtrim(char *bf)
{
    char *p = bf ? strchr(bf,' ') : 0;

    if ( p ) *p = 0;

    return bf;
}


#ifndef WIN32

// itoa não é uma operação ANSI e portanto não existe em alguns compiladores que é
// infelizmente o caso do xlC. Abaixo é uma função simplificada que converte apenas
// valores inteiros positivos na base 10 em uma string do tipo "C".
// É responsabilidade do chamador prover um buffer de destino com tamanho suficiente.
char * itoa(int i,char *dest,int /*radix*/)
{
    sprintf(dest,"%d",i);
    return dest;
}

#endif

bool isColunaFixa(char *p)
{
    int i = 0;

    while ( colunasFixas[i].nomeColuna )
    {
        if ( !stricmp(p,colunasFixas[i].nomeColuna) )
        {
            return true;
        }

        i++;
    }

    return false;
}

char *obterNomeColunaFixa(int idColuna)
{
    int i = 0;

    while ( colunasFixas[i].idColunaFixa != -1 )
    {
        if ( colunasFixas[i].idColunaFixa == idColuna )
        {
            return colunasFixas[i].nomeColuna;
        }

        i++;
    }

    return 0;
}

bool isCampoAlfanumerico(const char *nomeCampo)
{
    int i = 0;

    while (camposAlfanumericos[i].nomeCampo)
    {
        if ( stricmp(camposAlfanumericos[i].nomeCampo,nomeCampo) == 0 )
        {
            return true;
        }

        i++;
    }

    return false;
}

END_EXTERN_C

