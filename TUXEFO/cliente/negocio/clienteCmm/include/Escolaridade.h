/**
  * @modulo  Cliente
  * @usecase Escolaridade
  * @author  Robinson Vieira
  * @version $Revision: 1.1 $
  * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
  **/
 
#ifndef CEscolaridadeHH
 #define CEscolaridadeHH
 
 #define MSG_ERR_MEMORIA		"Erro de alocação de memória"
 #define NRO_ERR_MEMORIA		"24E0359"
 
 class CEscolaridade
 {
 public:
 	// Construtor/Destrutor
 	CEscolaridade();
 	virtual ~CEscolaridade();
 
 	// Métodos de acesso a banco de dados
 	static CEscolaridade* lista(int*);			// lista todos os objetos na tabela
 	int getId();
 	char *getDs();
 	// Setters
 	void setId(int value);
 	void setDs(char* value);
 private:
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iId;
 	char cDs[256];
	EXEC SQL END DECLARE SECTION;
 };
#endif

