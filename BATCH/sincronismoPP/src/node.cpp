#include "node.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream.h>
#include <ctype.h>
#include <errno.h>
#include <time.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <unistd.h>



Node::Node()
{
	memset(this->id,0,sizeof(this->id));
	memset(this->status,0,sizeof(this->status));
	this->qtTentativas = 0;	
	strcpy(this->status,"P");
	this->next = NULL;
	this->prev = NULL;
	this->lista = NULL;
	this->size = 0;
	this->processado = false;
	this->debug = 0;
	this->setTipo(0);
}

Node::Node(char*id)
{
	memset(this->id,0,sizeof(this->id));
	memset(this->status,0,sizeof(this->status));
	this->qtTentativas = 0;	
	strcpy(this->status,"P");
	this->next = NULL;
	this->prev = NULL;
	this->lista = NULL;
	this->setId(id);
	this->size = 0;
	this->processado = false;
	this->debug = 0;
	this->setTipo(0);
}
/**
 * Node é uma classe do tipo lista
 * cada objeto tem uma coleção de nodes
 * Nesse construtor, é enviado como parâmetro de entrada
 * dois ponteiros para logar quantidade de objetos alocados
 * e desalocados na memória.
 */
Node::Node(char*id, Log *log,int *objetoAlocado, int *objetoDesalocado, int debug)
{	
	memset(this->id,0,sizeof(this->id));
	memset(this->status,0,sizeof(this->status));
	this->qtTentativas = 0;	
	strcpy(this->status,"P");
	this->next = NULL;
	this->prev = NULL;
	this->lista = NULL;
	this->setId(id);
	this->setLog(log);	
	this->objetoAlocado = objetoAlocado;
	this->objetoDesalocado = objetoDesalocado;
	(*objetoAlocado)++;
	this->size = 0;
	this->processado = false;
	this->debug = debug;
	this->setTipo(0);
	this->logar("ALOCANDO");	
}

Node::~Node()
{
	this->logar("DESALOCANDO");
	(*objetoDesalocado)++;
}

void Node::setId(char*id)
{
	if (strlen(id) <= sizeof(this->id)) {
		strcpy(this->id, id);
	}
}

char* Node::getId()
{
	return this->id;
}

void Node::setStatus(char*status)
{
	strcpy(this->status, status);
}

void Node::setFirstElement(Node*node) {
	this->first = node;
}

void Node::addItem(Node *item)
{	
	logar("adicionar item");
	if (this->lista == NULL) {
		this->lista = item;
		this->first = this->lista;
	} else {
		item->setPrev(this->lista);
		this->lista->setNext(item);
		this->lista = item;
	}
	this->size++;
}

char* Node::getStatus()
{
	return this->status;
}

Node* Node::getNext()
{
	return this->next;
}

Node* Node::getPrev()
{
	return this->prev;
}

Node* Node::getLista()
{
	return this->lista;
}

void Node::incrementaTentativa() {
	this->qtTentativas++;
}

int Node::getQtTentativas() {
	return this->qtTentativas;
}

void Node::setNext(Node *item) {
	this->next = item;
}

void Node::setPrev(Node *item) {
	this->prev = item;
}

void Node::setLog(Log *log) {
	this->log = log;
}

void Node::logar(char*str) {
	char buffer[200];
	memset(buffer,0,sizeof(buffer));
	if (this->debug == 1) {
		if (str != NULL) {
			sprintf(buffer, "NODE[%s][%s]", id, str);
			this->log->logDebug(buffer);
		}
	}
}

Node* Node::getFirstElement() {
	return this->first;
}

int Node::getSize() {
	return size;
}

bool Node::isProcessado() {
	return this->processado;
}

void Node::setProcessado(bool processado) {
	this->processado = processado;
}

void Node::setTipo(int tipo) {
	this->tipo = tipo;
}

int Node::getTipo() {
	return this->tipo;
}

