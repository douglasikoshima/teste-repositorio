/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Charles Santos
 * @version $Revision: 1.1 $
 * @CVS     $Author: cmgarcia $ - $Date: 2005/09/11 02:13:15 $
 **/

#ifndef _CONTATOPREVIOPC_H_
#define _CONTATOPREVIOPC_H_

#undef SQLCA
#define SQLCA_NONE

#ifdef WIN32
#pragma warning(disable:4786)
#include <tuxfw.h>
#endif

#include <iostream>
#include <string>

#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>
#include <stdlib.h>

#include "../../commons/Propriedade/include/Propriedade.h"
#include "../../commons/queryMacro.h"
#include "../../commons/Log/include/Log.h"

using namespace std;

class cContatoPrevioPC
{
    public:
        cContatoPrevioPC();
        ~cContatoPrevioPC();

        void ProcessarAtualizacao();
        bool ObterConfiguracaoCompleta() { return configuracaoCompleta; }

    private:
        bool VerificarContatoPrevio( char *sData,int nHoras,int nAreaReg, char *sDataCalc,unsigned long);
        bool obterGrupoRCConfigurado(unsigned long,int,int,int,int,int,int,int &);
        bool AtualizarGrupoUsuarioAtual(unsigned long _idAtendimento,int,int,int,char *);
        void Conectar();
        void Desconectar();
        void ContatoPrevioSQLError(sqlca*sqlcaerr, int linha,unsigned long idAtendimento);

        bool configuracaoCompleta;
        bool conectado;
        string szConnStr;
        char* szPws;
        char* szUsr;
        char* szPath;
        char* szInst;
        int vlLog;
        Log log;
};
 
#endif
