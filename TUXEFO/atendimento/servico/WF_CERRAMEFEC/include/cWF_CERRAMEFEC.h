/**
 * @modulo  Atendimento
 * @usecase Fechamento em massa
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.114.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2011/09/22 13:16:37 $
 **/ 

#ifndef __CWF_CERRAMEFEC_H__
#define __CWF_CERRAMEFEC_H__

#define STRLENNULL( y ) ( y == NULL ? 0 : strlen( y )  )

#define CF_SUCESSO                     0
#define CF_ERRO                        1

#include <tuxfw.h>
#include "../include/stWF_CERRAMEFEC.h"
#include "../include/cWF_CERRAMEFECPC.h"
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"

//------------------------------------------------------------------------------        
// cWF_CERRAMEFEC: Como esta classe ja estava com este nome estou mantendo
//                 mas acho que deveria ter um nome mais amigavel (Eder )
//------------------------------------------------------------------------------        

class cWF_CERRAMEFEC : private cWF_CERRAMEFECPC
{
    public:
	    bool flagBloqueioBatch;
        //Construtor 
		cWF_CERRAMEFEC(DOMNode* dnRaiz, char* pzcidUsuario);
		//Destrutor
         ~cWF_CERRAMEFEC();

		//Recupera o status: 0 sucesso, qualquer outra coisa falha
		int getErroNo( void ) { return iErro; };
		//Recupera a mensagem de erro: Nem sempre sera uma mensagem de erro
		char* getErroMsg( void ) { return cErro; };
		//Executa a execução do fechamento de processo em MASSA
		//Dados os parâmetros de entrada, efetua o processamento em massa
		void execute( void );
		

    private:
		//Armazena todos os dados de entrada
		struct ST_DADOS_ENTRADA stDadosEntrada;
		//Armazena o formulario e seus campos
		struct ST_ATENDIMENTOFORMULARIOREG stFormulario;
		//Para utilizar os metodos do FrameWork
		TuxHelper thUtil;
		//Status de erro, zero eh sucesso
		int  iErro;
		//Mensagem de erro, indica os mais diversos status
		char cErro[255+1];
		//Altera o status de erro
		void setErro( int iStatusErro );
		//Altera a mensagem de erro sem mudar o status
		void setErro( char* pzcErro );
		//Altera a mensagem de erro juntamente com seu status
		void setErro( char* pzcErro, int iStatusErro );
		//Le todos os dados da lista de processo recebidos
		int getDadosEntrada( DOMNode* dnProcessos );
		//Le o formulario do XML de entrada
		void getFormulario(DOMNode* domFormulario);
		//Recupera uma TAG a partir de uma DOMNode
		int getTag( DOMNode* dnEntrada, char* pzcValor, char* pzcTag );
		int getTag( DOMNode* dnEntrada, char* pzcValor, char* pzcTag, int iPosicao );

};

#endif
