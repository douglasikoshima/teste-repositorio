#ifndef CFILEXENOS_H_HEADER_INCLUDED_BD90502B
#define CFILEXENOS_H_HEADER_INCLUDED_BD90502B
#include "CFile.h"
#include <time.h>
#include "Constants.h"

class CFileXenos : public CFile
{
  public:
	CFileXenos(char*path,int type,int extention,char*id);
	~CFileXenos();
	bool isAppended();
	int write(char*buffer);
	int getType();
	int getExtension();
	char*getNameSimple();
	void setId(char*id);
  private:
	// criar um nome único para o arquivo com base na conta ou ano/mes/dia/hora/minuto/seg
    void createName();
  private:
    // nome do arquivo
    char m_name[255+1]; //AFP.200505031433210000
	char m_nameSimple[255+1];
	bool m_appended;
	int m_type;
	int m_extension;
	char m_id[256];
};



#endif /* CFILEXENOS_H_HEADER_INCLUDED_BD90502B */
