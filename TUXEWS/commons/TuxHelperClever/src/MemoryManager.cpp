#include <tuxfw/tuxfw.h>
#include <TuxHelperClever/MemoryManager.h>
 
CMemory::CMemory()
{
	this->vMemory = NULL;
	this->iMemReleaseType = RELEASE_UNKNOWED;
}

 
CMemory::CMemory(int iDefaultReleaseType)
{
	CMemory();

	if(iDefaultReleaseType >= RELEASE_FREE && iDefaultReleaseType <= RELEASE_XML_STRING)
		this->iMemReleaseType = iDefaultReleaseType;

}

 
CMemory::~CMemory()
{

}

 
char* CMemory::releaseType()
{
	if(this->iMemReleaseType == RELEASE_UNKNOWED)
		return "RELEASE_UNKNOWED";
	else if(this->iMemReleaseType == RELEASE_FREE)
		return "RELEASE_FREE";
	else if(this->iMemReleaseType == RELEASE_DELETE)
		return "RELEASE_DELETE";
	else if(this->iMemReleaseType == RELEASE_XML_STRING)
		return "RELEASE_XML_STRING";
	else
		return NULL;
}
 
void* CMemory::getMemoryReference()
{
	return this->vMemory;
}
 
void CMemory::setMemoryReference(void* vMem, int iReleaseType)
{
	this->vMemory = vMem;

	if(iReleaseType >= RELEASE_FREE && iReleaseType <= RELEASE_XML_STRING)
		this->iMemReleaseType = iReleaseType;

	tuxfw_getlogger()->debug("CMemory: Referenciando %X | %s", this->vMemory, this->releaseType());
}

 
void CMemory::release()
{
	if(this->vMemory)
	{ 
		tuxfw_getlogger()->debug("CMemory: Liberando %X | %s", this->vMemory, this->releaseType());

		if(this->iMemReleaseType == RELEASE_FREE)
			free(this->vMemory);
		else if(this->iMemReleaseType == RELEASE_DELETE)
			delete this->vMemory;
		else if(this->iMemReleaseType == RELEASE_XML_STRING)
			XMLString::release((char **) &this->vMemory);
		else
			delete this->vMemory;  
	}
}


////////////////////////////////////////////////////////////////////////

CMemoryManager::CMemoryManager()
{
	iMemReleaseType = RELEASE_UNKNOWED;
}

 
CMemoryManager::CMemoryManager(int iDefaultReleaseType)
{
	iMemReleaseType = iDefaultReleaseType;
}

 
void CMemoryManager::operator=(void* value) 
{
	//referenciando
	this->Add(value);
}

 
CMemoryManager::~CMemoryManager()
{
	CMemory mem;

	while(0 < m_lstMem.size())
	{
		mem = m_lstMem.front();

		mem.release();

		m_lstMem.pop_front();

	}
}

 
void* CMemoryManager::Add(void* pcMem, int iReleaseType)
{
	if(pcMem)
	{
		CMemory mem(iMemReleaseType);

		mem.setMemoryReference(pcMem, iReleaseType);

		m_lstMem.push_back(mem);
	}

	return pcMem;
}
