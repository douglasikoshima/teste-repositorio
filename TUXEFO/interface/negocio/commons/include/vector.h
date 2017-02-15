// vector.h: interface for the CVector class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_VECTOR_H__2D38E0F0_70FF_4F94_BC2C_FD89780E255B__INCLUDED_)
#define AFX_VECTOR_H__2D38E0F0_70FF_4F94_BC2C_FD89780E255B__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "collection.h"
#include "collectionelement.h"


template <class T> class CVector : public CCollection<T>  
{
public:
	CVector();
	~CVector();
	void add(T *data);
	void add(T data[],int size);
	void set(T*item,int indice);
	T* get(int indice);
	int const toArray(T *array);
	void remove(T*item);
	void remove(int indice);
	void removeAll();	
	int  sizeOf();
	void getElements(CEnumeration<T> &e);
private:
	// atributos
	CCollectionElement<T> **m_pfirst;
};

//////////////////////////////////////////////////////////
// Implementação
// vector.h

template <class T> CVector<T>::CVector()
{
	this->m_current = NULL;
	this->m_first = NULL;
	this->m_size = 0;
	this->m_pfirst = &this->m_first;
}
template <class T> CVector<T>::~CVector()
{
	CCollectionElement<T> *buffer = this->m_first;
	while(buffer!=NULL)
	{
		CCollectionElement<T> *aux = buffer;
		if(aux!=NULL)
		{		
			buffer = buffer->m_next;			
			delete aux;					
		}
	}
}

template <class T> void CVector<T>::add(T *data)
{	
	CCollectionElement<T> *buffer = NULL;
	buffer = new CCollectionElement<T>(*data);
	buffer->m_next = NULL;
	buffer->m_prev = NULL;	
	if(this->m_current == NULL)
	{
		this->m_first = buffer;
	}
	else
	{
		this->m_current->m_next = buffer;
		buffer->m_prev = this->m_current;
	}
	this->m_current = buffer;
	this->m_size++;
}

template <class T> void CVector<T>::add(T data[],int size)
{	
	for(int i=0;i<size;i++)
	{
		CCollectionElement<T> *buffer = NULL;
		buffer = new CCollectionElement<T>(data[i]);
		buffer->m_next = NULL;
		buffer->m_prev = NULL;	
		if(this->m_current == NULL)
		{
			this->m_first = buffer;
		}
		else
		{
			this->m_current->m_next = buffer;
			buffer->m_prev = this->m_current;
		}
		this->m_current = buffer;
		this->m_size++;		
	}
}

template <class T> void CVector<T>::set(T*item,int indice)
{
	if(indice < 0 || indice > this->m_size)
		throw new CExceptionIndiceOut;

	CCollectionElement<T> *buffer = this->m_first;
	int i = 0;
	while(buffer!=NULL)
	{
		CCollectionElement<T> *aux = buffer;
		if(aux!=NULL && i == indice)
		{		
			aux->set(*item);			
		}
		buffer = buffer->m_next;
		i++;
	}

}
template <class T> T* CVector<T>::get(int indice)
{	
	if(indice < 0 || indice > this->m_size)
		throw new CExceptionIndiceOut;

	CCollectionElement<T> *buffer = this->m_first;
	int i = 0;
	while(buffer!=NULL)
	{
		CCollectionElement<T> *aux = buffer;
		if(aux!=NULL && i == indice)
		{								
			return &aux->m_data;			
		}
		buffer = buffer->m_next;
		i++;
	}

	return NULL;
}

template <class T> int const CVector<T>::toArray(T *array)
{
	CCollectionElement<T> *buffer = this->m_first;
	int i = 0;
	while(buffer!=NULL)
	{
		CCollectionElement<T> *aux = buffer;
		if(aux!=NULL)
		{					
			array[i++] = buffer->m_data;			
			buffer = buffer->m_next;						
		}
	}	
	return m_size;

}

template <class T> void CVector<T>::remove(T*item)
{
	if(this->m_size > 0)
		this->m_size--;
}

template <class T> void CVector<T>::removeAll()
{
	CCollectionElement<T> *buffer = this->m_first;
	while(buffer!=NULL)
	{
		CCollectionElement<T> *aux = buffer;
		if(aux!=NULL)
		{		
			buffer = buffer->m_next;
			delete aux;
		}
	}
	this->m_current = NULL;
	this->m_first = NULL;
	this->m_size = 0;
}

template <class T> void CVector<T>::remove(int indice)
{	
	CCollectionElement<T> *buffer = this->m_first;
	int i = 0;
	if(this->m_size == 0)
		return;
	while(buffer!=NULL)
	{		
		if(i == indice)
		{
			// primeiro e único
			if(buffer->m_next == NULL && buffer->m_prev == NULL)
			{
				this->m_first = NULL;
				this->m_current = NULL;
			}
			// primeiro não único
			else
			if(buffer->m_next != NULL && buffer->m_prev == NULL)
			{
				this->m_first = buffer->m_next;
				buffer->m_next->m_prev = NULL;
			}
			// último
			else
			if(buffer->m_next == NULL && buffer->m_prev != NULL)
			{
				this->m_current = buffer->m_prev;
				this->m_current->m_next = NULL;
			} // outros
			else
			{
				buffer->m_prev->m_next = buffer->m_next;
				buffer->m_next->m_prev = buffer->m_prev;
			}
			delete buffer;
			this->m_size--;
			return;
		}
		buffer = buffer->m_next;
		i++;
	}
}

template <class T> int CVector<T>::sizeOf()
{
	return this->m_size;
}

template <class T> void CVector<T>::getElements(CEnumeration<T> &e)
{

	e.setCollection(this->m_pfirst);
}

///////////////



#endif // !defined(AFX_VECTOR_H__2D38E0F0_70FF_4F94_BC2C_FD89780E255B__INCLUDED_)


