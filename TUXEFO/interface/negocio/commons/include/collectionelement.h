// collectionelement.h: interface for the CCollectionElement class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_COLLECTIONELEMENT_H__94325291_40EE_47B9_AB01_9BFFCBB26614__INCLUDED_)
#define AFX_COLLECTIONELEMENT_H__94325291_40EE_47B9_AB01_9BFFCBB26614__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000


template<class T> class CCollectionElement  
{
public:
	CCollectionElement();
	CCollectionElement(T data);
	void set(T data);
	virtual ~CCollectionElement();
public:
	CCollectionElement *m_next;
	CCollectionElement *m_prev;
	T m_data;
};

template<class T> CCollectionElement<T>::CCollectionElement()
{
	m_data = NULL;
}

template<class T> CCollectionElement<T>::CCollectionElement(T data)
{	
	m_data = data;
}

template<class T> void CCollectionElement<T>::set(T data)
{
	m_data = data;
}

template<class T> CCollectionElement<T>::~CCollectionElement()
{
	
}
#endif // !defined(AFX_COLLECTIONELEMENT_H__94325291_40EE_47B9_AB01_9BFFCBB26614__INCLUDED_)
