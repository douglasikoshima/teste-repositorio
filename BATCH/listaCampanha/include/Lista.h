/* Lista.pcpp: Modela uma lista de campanha
*/
 
#ifndef Lista
#define Lista

class CLista  
{
public:
	/* Construtor/Destrutor */
	CLista();
	CLista(int);
	/*virtual ~CLista(void); */
 
	/* Métodos de acesso a banco de dados */
	/* Traz os arquivos a processar. */
	static CLista* recuperaAProcessar(int*); 
	/* Limpa processamentos anteriores dessa lista */
	int desfazProcessamento();
	/* Processa a lista */
	int processaLista(char*);
	/* Marca como falha */
	int marcaFalha(char*);
 
	/* Métodos de acesso aos atributos */
	/* Getters */
	int getIdLista();
	char* getNmLista();
	int getInStatusCarga();
	int getQtTentativas();
	/* Setters */
	void setIdLista(int value);
	void setNmLista(char* value);
	void setInStatusCarga(int value);
	void setQtTentativas(int value);
	/* Usuário */
	void setUsuarioAlteracao(char*);
 
private:
 
	EXEC SQL BEGIN DECLARE SECTION;
	int iIdLista;
	char cNmLista[255];
	int iInStatusCarga;
	int iQtTentativas;
    char szFullPath[];
	/* Usuario alteração */
	char sIdUsuarioAlteracao[256];
	EXEC SQL END DECLARE SECTION;
};
 
#endif
