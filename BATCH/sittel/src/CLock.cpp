/*
 * CLock.cpp
 *
 *  Created on: 15/06/2013
 *      Author: Jones Randis
 */

#include "CLock.h"
#include "CLog.h"
#include "CException.h"

extern "C" {
#include <unistd.h>
#include <sys/file.h>
#include <sys/mode.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
}

namespace batch {

CLock::CLock(const std::string& path, const std::string& file)
: lockfile(file), plockfile(0)
{
	if ( !path.empty() )
		lockfile = path + DIR_SEP + file;

	int oflag = O_WRONLY | O_CREAT | O_NONBLOCK | O_NDELAY /* | O_NSHARE */;
	int mode = /* S_ISGID | S_ENFMT | */ S_IWUSR | S_IWGRP /* | S_IWOTH */;
	plockfile = open(lockfile.c_str(), oflag, mode);

	if (plockfile == -1) {
		LOG_ERR("open: " << lockfile << " - " << strerror(errno))
		throw XCreateFile;
	}

	int operation = LOCK_EX | LOCK_NB;

	if (flock(plockfile, operation) == -1) {
		LOG_ERR("flock: " << lockfile << " - " << strerror(errno))
		throw XAlreadyRunning;
	}
}

CLock::~CLock() {
	if (plockfile) {
		close(plockfile);
		plockfile=0;
	}
	if (unlink(lockfile.c_str()) == -1) {
		LOG_ERR("unlink: " << lockfile << " - " << strerror(errno))
		throw XDelFile;
	}
}

} /* namespace batch */
