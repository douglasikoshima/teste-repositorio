#ifndef __DADOSLINHA__
#define __DADOSLINHA__

#include <DadosLinha/stLinha.hpp>

/*
 * Constantes e definições comuns
 */


// Codigo de retorno
// Alterado as mensagens de "E"rro para "W"arning
#define COD_I_0000					"24I0000"	// Prossegue atendimento - OK
#define COD_E_0001					"24W0001"	// Conta não cadastrada / desligada
#define COD_E_0002					"24W0002"	// Erro nos parametros de entrada
#define COD_E_0003					"24W0003"	// Erro no acesso ao banco
#define COD_E_0004					"24W0004"	// Erro no acesso as sub-rotinas
#define COD_E_0005					"24W0005"	// Erro troca de senha = cli = usu
#define COD_E_0006					"24W0006"	// Cliente não pode ser atendido pelo FO
#define COD_E_0007					"24W0007"	// Senha sequencial
#define COD_E_0008					"24W0008"	// Dígitos iguais
#define COD_E_0009					"24W0009"	// Operação não encontrada
#define COD_E_0010					"24W0010"	// Senha anterior diferente da atual

// TAG STATCOM
#define TAG_STATCOM                                     "statCom"

// Mensagem de retorno
#define MSG_I_0000					"Successfully executed"
#define MSG_E_0001					"Account disabled or not found"
#define MSG_E_0002					"Error in input/output"
#define MSG_E_0003					"Error access database"
#define MSG_E_0004					"Error access sub-routines"
#define MSG_E_0005					"Unknown error"
#define MSG_E_0006					"Não atendido pelo FO"
#define MSG_E_0007					"Senha sequencial"
#define MSG_E_0008					"Dígitos iguais"
#define MSG_E_0009					"Dados não encontrados"
#define MSG_E_0010					"Senha anterior diferente da atual"
#define MSG_E_0011					"Senha bloqueada"
#define MSG_E_SENHA					"Senha inválida"

// Status da comunicação
#define MSG_E_1001					"24W1001"	// Erro de programa
#define MSG_E_1002					"24W1002"	// Programa fora do ar

/* Constantes de retorno dos serviços */
#define RET_OK					0

/* Constantes de códigos de erro */
#define ERR_NOT_FOUND_OR_DISABLED		1
#define ERR_IN_PARAM				2
#define ERR_DATABASE				3
#define ERR_SUBROUTINES				4
#define ERR_UNDEFINED				5
#define ERR_SENHA_CLI_USU			6
#define ERR_NAO_ATENDIDO_FO			7
#define ERR_SEQ_INVALIDA			8
#define ERR_DIG_IGUAIS				9
#define ERR_SENHA_INVALIDA			10
#define ERR_SENHA_BLOQUEADA			11
/* 
 *Constantes de erro, aviso e sucesso.
 */

/* Finalização OK */
#define MSG_OK						"24I0000"	// Finalização OK.

/* Erros gerais */
#define MSG_E_2050					"24W2050"	// Erro de alocação de memória
#define MSG_E_2051					"24W2051"	// Erro chamando subrotinas
#define MSG_E_2052					"24W2052"	// Sistema indisponível

/* Erros de parâmetros de entrada */
#define MSG_E_2101					"24W2101"	// DDD não encontrado nos parâmetros de entrada
#define MSG_E_2102					"24W2102"	// Fone não encontrado nos parâmetros de entrada

/* Erros de processamento */
#define MSG_E_2201					"24W2201"	// Erro na recuperação do valor de pontos

class CDadosLinha
{
	public:

		CDadosLinha();
		~CDadosLinha();
		char* getEstadoLinha();
		char* getTipoConta();
		char* getSegmento();
		char* getCarteira();
		char* getPrioriadadeLojista();

		void setEstadoLinha(char*);
		void setTipoConta(char*);
		void setSegmento(char*);
		void setCarteira(char*);
		void setPrioridadeLojista(char*);

		void sql_error(struct stStatuslinha *stLinha, int Status);

		void RecuperarDados( struct stStatuslinha *stLinha, struct stVlStatuslinha *stVlLinha );
		void VerificaLinha( struct stStatuslinha *stLinha, struct stVlStatuslinha *stVlLinha );
		void DecodeEstadoLinha(struct stStatuslinha *stLinha);
		
		bool Premium( struct stStatuslinha *stLinha);
		
	private:
		char m_estadoLinha[256];
		char m_tipoConta[256];
		char m_segmento[256];
		char m_carteira[256];
		char m_prioridadeLojista[256];
		
};

#endif