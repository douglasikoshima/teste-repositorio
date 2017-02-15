// MemoryManager.h: interface for the CMemoryManager class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __C_MEMORY_MANAGER__
#define __C_MEMORY_MANAGER__

#include <tuxfw.h>

#include <list>

using namespace std;

#define RELEASE_UNKNOWED      0
#define RELEASE_FREE          1
#define RELEASE_DELETE        2
#define RELEASE_XML_STRING    3


class CMemory
{
public:
	CMemory();
	CMemory(int iDefaultReleaseType);
	~CMemory();
	
	void* getMemoryReference();
	void  setMemoryReference(void* vMem, int iReleaseType = RELEASE_UNKNOWED);
	void  release();

private:

	void* vMemory;
	int   iMemReleaseType;
	char* releaseType();

};

class CMemoryManager
{
public:
	CMemoryManager();
	CMemoryManager(int iDefaultReleaseType);
	~CMemoryManager();

	void operator = (void* value);
		
	void* Add(void* pcMem, int iReleaseType = RELEASE_UNKNOWED);
	
private:

	int  iMemReleaseType;
	list< CMemory >  m_lstMem;

};

#endif //__C_MEMORY_MANAGER__