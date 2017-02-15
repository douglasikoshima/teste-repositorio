/*
 * CNet.cpp
 *
 *  Created on: 24/06/2013
 *      Author: Jones Randis
 */

#include "CNet.h"
#include "CLog.h"

#include <cerrno>
#include <cstring>

extern "C" {
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
}

namespace batch {

bool getHostName(std::string& hostname)
{
	char buff[256+1]={0};

	if (gethostname(buff, sizeof(buff)) == -1) {
		hostname.clear();
		LOG_ERR("gethostname: " << strerror(errno))
		return false;
	}

	hostname = buff;
	return true;
}

bool getIpAddress(std::string& ipaddr)
{
	std::string hostname;
	getHostName(hostname);

	struct hostent* host = gethostbyname(hostname.c_str());
	if ( !host ) {
		ipaddr.clear();
		LOG_ERR("gethostbyname: " << hostname << " - " << strerror(errno))
		return false;
	}

	char* addr = inet_ntoa( *((struct in_addr *)host->h_addr) );
	if (!addr) {
		ipaddr.clear();
		LOG_ERR("inet_ntoa: " << strerror(errno))
		return false;
	}

	ipaddr = addr;
	return true;
}

} /* namespace batch */
