#include "OperationNotFoundException.h"

OperationNotFoundException::OperationNotFoundException(char*msg)
{
	this->m_msg = msg;
}
OperationNotFoundException::~OperationNotFoundException()
{

}
char*OperationNotFoundException::getCause()
{
	return this->m_msg;
}
