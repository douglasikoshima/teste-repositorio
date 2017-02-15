#!/bin/bash
############################################################
#  BATCH......: cargaLoginClientesTVantagens
#  Criado por.: Williams Santos 22/01/2016
#  Funcional..: Erika Brum
############################################################
APPNAME=cargaLoginClientesTVantagens

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_SQL=$(COMPILE_DIR)/sql

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data
HIST_DIR=$(EXEC_DIR)/hist
SQL_DIR=$(EXEC_DIR)/sql
PRC_DIR=$(EXEC_DIR)/prc
BAD_DIR=$(EXEC_DIR)/bad


clean:
	rm -f $(EXEC_DIR)/*.txt
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.lock
	rm -f $(SQL_DIR)/*.sql
	rm -f $(SQL_DIR)/*.ctl
	rm -f $(SQL_DIR)/*.par


build:	cargaLoginClientesTVantagens $(APPNAME)


uninstall:
	rm -r -f $(EXEC_DIR)


cargaLoginClientesTVantagens: install


install:
	umask 022
	[ -d $(DEPLOY_DIR) ]    || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ]      || mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ]      || mkdir $(DATA_DIR)
	[ -d $(HIST_DIR) ]      || mkdir $(HIST_DIR)
	[ -d $(SQL_DIR) ]       || mkdir $(SQL_DIR)
	[ -d $(PRC_DIR) ]       || mkdir $(PRC_DIR)
	[ -d $(BAD_DIR) ]       || mkdir $(BAD_DIR)


	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg    $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/cargaLoginClientesTVantagens.sh                 $(EXEC_DIR)/cargaLoginClientesTVantagens.sh
	cp -f $(COMPILE_DIR)/cargaLoginClientesTVantagens_db_functions.sh    $(EXEC_DIR)/cargaLoginClientesTVantagens_db_functions.sh
	cp -f $(COMPILE_DIR)/cargaLoginClientesTVantagens_files_functions.sh $(EXEC_DIR)/cargaLoginClientesTVantagens_files_functions.sh
	cp -f $(COMPILE_SQL)/cargaLoginClientesTVantagens.sql                $(SQL_DIR)/cargaLoginClientesTVantagens.sql
	cp -f $(COMPILE_SQL)/cargaLoginClientesTVantagens.ctl                $(SQL_DIR)/cargaLoginClientesTVantagens.ctl
	cp -f $(COMPILE_SQL)/cargaLoginClientesTVantagens.par                $(SQL_DIR)/cargaLoginClientesTVantagens.par


	chmod 777 $(EXEC_DIR)
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	chmod 755 $(EXEC_DIR)/cargaLoginClientesTVantagens.sh
	chmod 755 $(EXEC_DIR)/cargaLoginClientesTVantagens_db_functions.sh
	chmod 755 $(EXEC_DIR)/cargaLoginClientesTVantagens_files_functions.sh
	chmod 644 $(SQL_DIR)/cargaLoginClientesTVantagens.sql
	chmod 644 $(SQL_DIR)/cargaLoginClientesTVantagens.ctl
	chmod 644 $(SQL_DIR)/cargaLoginClientesTVantagens.par