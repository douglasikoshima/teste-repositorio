/**
 * @modulo  Commons
 * @usecase Commons
 * @author  
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:13 $
 **/

#include <stdlib.h>
#include <string.h>

#include "../include/Collection.h"

Itens::~Itens()
{
    if ( pvAlocado==Itens::ITEM_ALOCADO && pv )
        delete pv;
    
	if(next)
		delete next;
}

int Collection::AddItem(void*pv,bool pvAlocado)
{
	if(!coll)
		coll=lst=new Itens;
	else
		lst=lst->next=new Itens;
	lst->pv=pv;
    lst->pvAlocado = pvAlocado;

    return ic++;
}

void*Collection::GetItem(int i)
{
	Itens*it;

	it=coll;
	while(i&&it)
	{
		it=it->next;
		i--;
	}
	if(i||!it)
		return 0L;
	return it->pv;
}
