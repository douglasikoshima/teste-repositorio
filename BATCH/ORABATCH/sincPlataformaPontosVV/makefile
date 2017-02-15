#!/bin/bash
############################################################
#  BATCH......: sincPlataformaPontosVV
#  Criado por.: Williams Santos 25/04/2016
#  Funcional..: Dener Santos
############################################################
APPNAME=sincPlataformaPontosVV

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
	

build:	sincPlataformaPontosVV $(APPNAME)


uninstall:
	rm -r -f $(EXEC_DIR)


sincPlataformaPontosVV: install


install:
	umask 022
# CRIACAO DE DIRETORIOS
	[ -d $(DEPLOY_DIR) ]    || mkdir $(DEPLOY_DIR)
	[ -d $(PROCESSOS_DIR) ] || mkdir $(PROCESSOS_DIR)
	[ -d $(EXEC_DIR) ]      || mkdir $(EXEC_DIR)
	[ -d $(EXEC_LOG) ]      || mkdir $(EXEC_LOG)
	[ -d $(DATA_DIR) ]      || mkdir $(DATA_DIR)
	[ -d $(HIST_DIR) ]      || mkdir $(HIST_DIR)
	[ -d $(SQL_DIR) ]       || mkdir $(SQL_DIR)
	[ -d $(PRC_DIR) ]       || mkdir $(PRC_DIR)
	[ -d $(BAD_DIR) ]       || mkdir $(BAD_DIR)
		


# COPIA DE ARQUIVOS
	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg    $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/sincPlataformaPontosVV.sh                 $(EXEC_DIR)/sincPlataformaPontosVV.sh
	cp -f $(COMPILE_DIR)/sincPlataformaPontosVV_db_functions.sh    $(EXEC_DIR)/sincPlataformaPontosVV_db_functions.sh
	cp -f $(COMPILE_DIR)/sincPlataformaPontosVV_files_functions.sh $(EXEC_DIR)/sincPlataformaPontosVV_files_functions.sh
	cp -f $(COMPILE_SQL)/sincPlataformaPontosVV.sql                $(SQL_DIR)/sincPlataformaPontosVV.sql


	chmod 777 $(EXEC_DIR)
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	chmod 755 $(EXEC_DIR)/sincPlataformaPontosVV.sh
	chmod 755 $(EXEC_DIR)/sincPlataformaPontosVV_db_functions.sh
	chmod 755 $(EXEC_DIR)/sincPlataformaPontosVV_files_functions.sh
	chmod 644 $(SQL_DIR)/sincPlataformaPontosVV.sql