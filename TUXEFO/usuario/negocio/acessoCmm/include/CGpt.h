/*****************************************************************************
 *
 * Modulo:    CGpt
 * Arquivo:   CGpt.h
 * Proposito: servicos Pro*C++ para persistencia de banco de dados
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  --------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
#ifndef CGptH

//Definicao Global
#define CGptH

//Header
#include<tuxfw.h>
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

//Classe
class CGpt
{
	public:
		CGpt();
		~CGpt();
		char* RTrim(char *pszString);
		int GptEditar(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int GptInserir(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int GptRemover(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int GptLista(DOMNode*dnode, XMLGen*xml_g);
		int GptListaId(DOMNode*dnode, XMLGen*xml_g);
		int GptListaId(char* cidGrupamento, XMLGen*xml_g);
		int GptListaPar(DOMNode*dnode, XMLGen*xml_g);
		int GptUniRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int GptUniRelacao(DOMNode*dnode, XMLGen*xml_g);
		//Metodos do Eder
		int GptSisLista(DOMNode*dnode, XMLGen*xml_g);
		void Zerar( void );
		char* getidGrupamento(void);
		char* getdsGrupamento(void);
		void setidGrupamento(char* pzcidGrupamento);
		void setdsGrupamento(char* pzcdsGrupamento);
			
	private:
		char cidGrupamento[21+1];
		char cdsGrupamento[255+1];
};

#endif
