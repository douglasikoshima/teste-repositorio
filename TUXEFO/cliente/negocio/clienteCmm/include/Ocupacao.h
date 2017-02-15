/**
  * @modulo  Cliente
  * @usecase Ocupacao
  * @author  Robinson Vieira
  * @version $Revision: 1.1 $
  * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
  **/
 
#ifndef COcupacaoHH
 #define COcupacaoHH
 
 #define MSG_ERR_MEMORIA		"Erro de alocação de memória"
 #define NRO_ERR_MEMORIA		"24E0359"
 
 class COcupacao
 {
 public:
 	// Construtor/Destrutor
 	COcupacao();
 	virtual ~COcupacao();
 
 	// Métodos de acesso a banco de dados
 	static COcupacao* lista(int*);			// lista todos os objetos na tabela
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

