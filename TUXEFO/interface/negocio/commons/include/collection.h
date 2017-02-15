// collection.h: interface for the Collection class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_COLLECTION_H__46810171_E307_4AE8_9C71_308AAC1C8B7A__INCLUDED_)
#define AFX_COLLECTION_H__46810171_E307_4AE8_9C71_308AAC1C8B7A__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "exceptionindiceout.h"
#include <string.h>
#include "enumeration.h"
#include "collectionelement.h"

template<class T> class CCollection  
{
public:
	virtual void add(T* const data)=0;
	virtual void add(T data[],int size)=0;
	virtual void set(T*item,int indice)=0;
	virtual T* get(int indice)=0;
	virtual int const toArray(T *array)=0;
	virtual void remove(T*item)=0;
	virtual void remove(int indice)=0;
	virtual void removeAll()=0;	
	virtual int  sizeOf()=0;
	void getElements(CEnumeration<T> &e);
protected:
	CCollectionElement<T> *m_current;
	CCollectionElement<T> *m_first;	
	int m_size;
};

#endif // !defined(AFX_COLLECTION_H__46810171_E307_4AE8_9C71_308AAC1C8B7A__INCLUDED_)
