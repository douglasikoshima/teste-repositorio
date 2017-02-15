// enumeration.h: interface for the CEnumeration class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ENUMERATION_H__B4BE75E7_C644_4B5F_8830_8A37FA0E62DF__INCLUDED_)
#define AFX_ENUMERATION_H__B4BE75E7_C644_4B5F_8830_8A37FA0E62DF__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "collectionelement.h"

template<class T> class CEnumeration
{
public:
	CEnumeration();
	virtual ~CEnumeration();
	bool virtual hasMoreElements();
	virtual T* nextElement();
	void virtual setCollection(CCollectionElement<T>**data);
private:
	CCollectionElement<T> **m_iterator;
	CCollectionElement<T> **m_first;
};

///////////////////////////////////////////////////////////////////////
// implementação

template<class T> CEnumeration<T>::CEnumeration()
{
	this->m_iterator = NULL;
	this->m_first = NULL;
}
template<class T> CEnumeration<T>::~CEnumeration()
{

}

template<class T> bool CEnumeration<T>::hasMoreElements()
{
	if(*this->m_iterator == NULL)
	{
		return false;
	}
	else
	{
		return true;
	}	
}

template<class T> T* CEnumeration<T>::nextElement()
{
	CCollectionElement<T> *buffer = *this->m_iterator;
	this->m_iterator = &(buffer->m_next);
	if(buffer == NULL)
	{
		return NULL;
	}
	else
	{
		return &buffer->m_data;
	}	
}


template<class T> void CEnumeration<T>::setCollection(CCollectionElement<T>**data)
{
	this->m_iterator = data;
	this->m_first = data;
}


#endif // !defined(AFX_ENUMERATION_H__B4BE75E7_C644_4B5F_8830_8A37FA0E62DF__INCLUDED_)
