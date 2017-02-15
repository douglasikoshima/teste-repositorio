#include <XMLParseException.h>

XMLParseException::XMLParseException(char*msg)
{
	this->m_msg = msg;
}

XMLParseException::~XMLParseException()
{

}

char*XMLParseException::getCause()
{
	return this->m_msg;
}
