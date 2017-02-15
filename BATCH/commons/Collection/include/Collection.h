#include<stdio.h>


#ifndef COLLECTION
	#define COLLECTION

class Itens
{
public:
	Itens():pv(0L),next(0L),pvAlocado(ITEM_NAO_ALOCADO) {}
	~Itens();
	deleter();
public:
	void*pv;
        int pvAlocado; // se==ITEM_ALOCADO chama delete pv ou 
                       // se!=ITEM_ALOCADO não considera pv como área no heap
	Itens*next;
    enum { ITEM_NAO_ALOCADO,ITEM_ALOCADO };
};

class Collection
{
public:
	Collection():coll(0L),lst(0L),ic(0) {}
	~Collection() { if(coll) delete coll; }

public:
    int GetCount() { return ic; }
    int AddItem(void*pv,bool pvAlocado=Itens::ITEM_ALOCADO);
	void*GetItem(int);

private:
	Itens*coll,*lst;
	int ic;
};

#endif

