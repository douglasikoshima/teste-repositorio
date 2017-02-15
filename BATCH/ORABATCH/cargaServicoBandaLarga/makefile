#!/bin/bash
############################################################
#  BATCH......: cargaServicoBandaLarga
#  Criado por.: Jair 21/01/2016
#  Funcional..: Mirian Yuka
############################################################
APPNAME=cargaServicoBandaLarga

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


clean:
	rm -f $(EXEC_DIR)/*.txt
	rm -f $(EXEC_DIR)/*.cfg
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.lock
	rm -f $(SQL_DIR)/*.sql
	rm -f $(SQL_DIR)/*.ctl
	rm -f $(SQL_DIR)/*.par


build:	cargaServicoBandaLarga $(APPNAME)


uninstall:
	rm -r -f $(EXEC_DIR)


cargaServicoBandaLarga: install


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


	cp -f $(COMPILE_CFG)/orabatch.`hostname`.`whoami`.cfg    $(EXEC_DIR)/orabatch.cfg
	cp -f $(COMPILE_DIR)/cargaServicoBandaLarga.sh                 $(EXEC_DIR)/cargaServicoBandaLarga.sh
	cp -f $(COMPILE_DIR)/cargaServicoBandaLarga_db_functions.sh    $(EXEC_DIR)/cargaServicoBandaLarga_db_functions.sh
	cp -f $(COMPILE_DIR)/cargaServicoBandaLarga_files_functions.sh $(EXEC_DIR)/cargaServicoBandaLarga_files_functions.sh
	cp -f $(COMPILE_SQL)/cargaServicoBandaLarga.sql                $(SQL_DIR)/cargaServicoBandaLarga.sql
	cp -f $(COMPILE_SQL)/cargaServicoBandaLarga.ctl                $(SQL_DIR)/cargaServicoBandaLarga.ctl
	cp -f $(COMPILE_SQL)/cargaServicoBandaLarga.par                $(SQL_DIR)/cargaServicoBandaLarga.par


	chmod 777 $(EXEC_DIR)
	chmod 644 $(EXEC_DIR)/orabatch.cfg
	chmod 755 $(EXEC_DIR)/cargaServicoBandaLarga.sh
	chmod 755 $(EXEC_DIR)/cargaServicoBandaLarga_db_functions.sh
	chmod 755 $(EXEC_DIR)/cargaServicoBandaLarga_files_functions.sh
	chmod 644 $(SQL_DIR)/cargaServicoBandaLarga.sql
	chmod 644 $(SQL_DIR)/cargaServicoBandaLarga.ctl
	chmod 644 $(SQL_DIR)/cargaServicoBandaLarga.par