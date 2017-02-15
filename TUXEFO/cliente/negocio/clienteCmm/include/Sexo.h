/**
  * @modulo  Cliente
  * @usecase SEXO
  * @author  Robinson Vieira
  * @version $Revision: 1.1 $
  * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
  **/
 
 #ifndef CSexoHH
 #define CSexoHH
 
 #define MSG_ERR_MEMORIA		"Erro de alocação de memória"
 #define NRO_ERR_MEMORIA		"24E0359"
 
 class CSexo
 {
 public:
 	// Construtor/Destrutor
 	CSexo();
 	CSexo(int);
 	virtual ~CSexo();
 
 	// Métodos de acesso a banco de dados
 	static CSexo* lista(int*);			// lista todos os objetos na tabela
 
 	// Métodos de acesso aos atributos
 	// Getters
 	int getIdSexo();
 	char *getDsSexo();
 	// Setters
 	void setIdSexo(int value);
 	void setDsSexo(char* value);
 private:
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdSexo;
 	char cDsSexo[256];
	EXEC SQL END DECLARE SECTION;
 };
 
 #endif

