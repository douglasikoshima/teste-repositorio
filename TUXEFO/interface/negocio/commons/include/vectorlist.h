// vectorlist.h: interface for the CVectorList class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_VECTORLIST_H__38C53AF4_34F4_4CF0_BADE_952779CF3088__INCLUDED_)
#define AFX_VECTORLIST_H__38C53AF4_34F4_4CF0_BADE_952779CF3088__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include"collection.h"

template<class T> class CVectorList  : public CCollection<T>
{
public:
	CVectorList();
	virtual ~CVectorList();
	void add(T* const data);
	void add(T data[],int size);
	void set(T*item,int indice);
	T* get(int indice);
	int const toArray(T *array);
	void remove(T*item);
	void remove(int indice);
	void removeAll();	
	int  sizeOf();
	// void getElements(CEnumeration<T> &e);
private:
	// atributos
	T**m_element;
	T**m_anterior;
};

// implementação
template<class T> CVectorList<T>::CVectorList()
{
	this->m_size = 0;
	this->m_element = new T*[(this->m_size+1)];
	this->m_anterior = NULL;
}

template<class T> CVectorList<T>::~CVectorList()
{
	if(*m_element!=NULL)
	{
		for(int i=0;i<sizeOf();i++)
		{
			delete m_element[i];			
		}
	}

	if(this->m_anterior != NULL)
		delete [] this->m_anterior;
}

template<class T> void CVectorList<T>::add(T* const data)
{	
	T **nova = new T*[(this->m_size+1)];
	// copia os dados
	for(int i=0;i<m_size;i++)
		nova[i] = m_element[i];
	m_element = nova;
	m_element[this->m_size] = data;

	if(this->m_anterior != NULL)
		delete [] this->m_anterior;

	this->m_anterior = nova;
	this->m_size++;
}

template<class T> void CVectorList<T>::add(T data[],int size)
{
}

template<class T> void CVectorList<T>::set(T*item,int indice)
{
}

template<class T> T* CVectorList<T>::get(int indice)
{
	if(indice < 0 || indice >= m_size)	
		return NULL; // throw
	return m_element[indice];
}

template<class T> int const CVectorList<T>::toArray(T *array)
{
	return this->m_size;
}

template<class T> void CVectorList<T>::remove(T*item)
{
}

template<class T> void CVectorList<T>::remove(int indice)
{
	if(indice < 0 || indice >= m_size)	
		return; // throw
	// remove o item
	delete m_element[indice];
	// se for o ultimo
	if(indice == m_size-1)
	{
		m_element[indice] = 0;
		m_size--;
		return;
	}
	// reposiciona os itens do array a direita do indice
	for(int i=indice;i<m_size-1;i++)
		m_element[i] = m_element[i+1];
	m_element[m_size-1] = 0;
	// decrementa o array
	m_size--;
}

template<class T> void CVectorList<T>::removeAll()
{
	if(*m_element!=NULL)
	{
		for(int i=0;i<sizeOf();i++)
		{
			delete m_element[i];			
		}
	}
	m_size = 0;
	this->m_element = new T*[(this->m_size+1)];
	this->m_anterior = NULL;
}

template<class T> int CVectorList<T>::sizeOf()
{
	return this->m_size;
}




#endif // !defined(AFX_VECTORLIST_H__38C53AF4_34F4_4CF0_BADE_952779CF3088__INCLUDED_)
