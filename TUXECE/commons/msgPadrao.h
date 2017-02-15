/***************************************************************************************************
 *--Uteis-Commons--msgPadrao.h--Outubro/2004--Cassio
 *--Método para gerar mensagem com mapeamento de linha e source file
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/08/06 14:23:20 $
 **/

#ifdef WIN32
#define MASCFLOAT "%f"
#define MASCFLOATSZ "%.*f"
#else
#define MASCFLOAT "%i"
#define MASCFLOATSZ "%.*i"
#endif

#ifndef __MSG_PADRAO_H__
#define __MSG_PADRAO_H__

//==================================================================================================
////////////////////////////////////////////////////////////////////////////////////////////////////
//
// EXEMPLO DE UTILIZAÇÃO:
//
// suaOperacao(...)
// {
//     ------------------------------------------------------------------------------------------
//      Formato 1 - Uso através de variável explicita
//     ------------------------------------------------------------------------------------------
//     Mensagem er = Endereco(__LINE__,__FILE__).mensagem("%s","ponteiro 'p' invalido");
//
//     cout << "\n" << er.msgPadrao() << "\n";
//
//     ------------------------------------------------------------------------------------------
//      Formato 2 - Uso através de variável volátil implicita
//     ------------------------------------------------------------------------------------------
//     cout << "\n" << (Endereco(__LINE__,__FILE__).mensagem("%s","ponteiro 'p0' invalido")).MsgPadrao() << "\n";
//
//     ------------------------------------------------------------------------------------------
//      Formato 3 - Chamada através de labels pré-definidos (veja lista no final deste arquivo)
//     ------------------------------------------------------------------------------------------
//     cout << "\n" << erroPonteiroInvalido() << "\n";
//
//     ------------------------------------------------------------------------------------------
//      Formato 4 - Chamada através de label genérico para mensagens sem parâmetros
//     ------------------------------------------------------------------------------------------
//     cout << "\n" << mensagemSimples("erro de escrita no disco") << "\n";
//
//  Resultados: 
//      formato1 --> 'Arq yyy.cpp Lin X0: ponteiro 'p' invalido'
//      formato2 --> 'Arq yyy.cpp Lin X1: ponteiro 'p0' invalido'
//      formato3 --> 'Arq yyy.cpp Lin X2: ponteiro invalido'
//      formato4 --> 'Arq yyy.cpp Lin X3: erro de escrita no disco'
//
//      onde [X0,X3] são os números de linha onde a mensagem foi gerada
//      e yyy corresponde ao nome do arquivo onde a mensagem foi gerada.
//
//  Os exemplos 1 a 3 podem ser utilizados para gerar mensagens onde o nome do arquivo não aparece.
//  Este tipo de mensagem pode ser utilizado para debug, gerando menos escrita em disco e melhorando
//  a performance. Para isto simplesmente substitua a operação 'mensagem()' por 'mensagemDebug()'.
//
// }
//
//==================================================================================================

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>

//--------------------------------------------------------------------------------------------------
////////////////////////////////////////////////////////////////////////////////////////////////////
// Defines
//--------------------------------------------------------------------------------------------------
#ifndef _MAX_PATH
#   define _MAX_PATH 1024
#endif

#ifdef WIN32
#   define VSNPRINTF _vsnprintf
#else
#   define VSNPRINTF vsnprintf
#endif

#define MAX_RESULTADO 1024
#define MAX_MSG_PADRAO (MAX_RESULTADO + 320)
//--------------------------------------------------------------------------------------------------
////////////////////////////////////////////////////////////////////////////////////////////////////
// Objetos
//--------------------------------------------------------------------------------------------------
struct Mensagem
{
    char _resultado[MAX_RESULTADO];
    char _msgPadrao[MAX_MSG_PADRAO];

    Mensagem() { strcpy(_resultado,"(null)"); }

    void Formatar(const char *fmt, va_list marker) 
    {
        try
        {
            if ( !fmt )
            {
                strcpy(_resultado,"Erro de formatacao! (1)");
                return;
            }
            _resultado[MAX_RESULTADO-1] = 0;
            VSNPRINTF(_resultado,MAX_RESULTADO-1,fmt,marker);
        }
        catch (...) 
        {
            strcpy(_resultado,"Erro de formatacao! (2)");
        }
    }

    void SetarMensagemPadrao(const char *msgPadrao) 
    { 
        if (msgPadrao) sprintf(_msgPadrao,"%.*s",MAX_MSG_PADRAO-1,msgPadrao); 
        else strcpy(_msgPadrao,"ponteiro invalido!"); 
    }

    const char * resultado() { return _resultado[0] ? _resultado : "(null)"; }
    char * MsgPadrao() { return _msgPadrao[0] ? _msgPadrao : "(null)"; }
};

struct Endereco
{
    int _linha;
    char _msgPadrao[MAX_MSG_PADRAO];
    char _arquivo[_MAX_PATH];

    Endereco(int lin = __LINE__, const char *arq = __FILE__) 
    { 
#ifdef WIN32
        char drive[_MAX_DRIVE];
        char dir[_MAX_DIR];
        char fname[_MAX_FNAME];
        char ext[_MAX_EXT];

        _splitpath(arq,drive,dir,fname,ext);
        sprintf(_arquivo,"%.*s%.*s",_MAX_PATH/2,fname,_MAX_PATH/2,ext);
#else
        sprintf(_arquivo,"%.*s",sizeof(_arquivo)-1,arq);
#endif
        _linha=lin;
    }

    Mensagem mensagem(const char *fmt, ...) 
    { // Mensagem completa (com nome de arquivo e linha)
        Mensagem me;
        va_list marker;

        if ( fmt )
        {
            va_start(marker,fmt);

            me.Formatar(fmt,marker);

            sprintf(_msgPadrao,"Arq %.256s Lin %d:'%s'",_arquivo,_linha,me.resultado());
        }
        else
        {
            strcpy(_msgPadrao,"ponteiro invalido!(3)");
        }

        me.SetarMensagemPadrao(_msgPadrao);

        return me;
    }

    Mensagem mensagemDebug(const char *fmt, ...) 
    { // Mensagem para debug (sem nome de arquivo)
        Mensagem me;
        va_list marker;

        if ( fmt )
        {
            va_start(marker,fmt);

            me.Formatar(fmt,marker);

            sprintf(_msgPadrao,"Lin %d:'%s'",_linha,me.resultado());
        }
        else
        {
            strcpy(_msgPadrao,"ponteiro invalido!(4)");
        }

        me.SetarMensagemPadrao(_msgPadrao);

        return me;
    }
};

////////////////////////////////////////////////////////////////////////////////////////////////////
//--------------------------------------------------------------------------------------------------
// Definição para mensagens sem parâmetros
//
#define mensagemSimples(a)              (Endereco(__LINE__,__FILE__).mensagem("%s",a)).MsgPadrao()

////////////////////////////////////////////////////////////////////////////////////////////////////
//--------------------------------------------------------------------------------------------------
// Definições para a mensagens mais comuns
//
#define erroPonteiroInvalido()          mensagemSimples("ponteiro invalido")
#define erroFalhaAlocacaoMemoria()      mensagemSimples("Falha de alocação de memória")
#define bufferDestInsuficiente()        mensagemSimples("buffer destino com tamanho insuficiente")
#define resultadoNaoEncontradoSql()     mensagemSimples("*** resultado nao encontrado para a query fornecida ***")

#endif
