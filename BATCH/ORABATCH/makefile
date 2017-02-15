SUBDIRS=`ls -l | grep ^d | grep -v CVS | awk '{print $$9}'`


all:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make; \
		cd ..; \
	done

clean:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done

install:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done 

uninstall:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make uninstall; \
		cd ..; \
	done 

build:	
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make build; \
		cd ..; \
	done
