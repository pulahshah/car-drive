package com.cardrive.ftpserver;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FtpServerStarter {

	public FtpServerStarter(FtpServer ftpServer) throws FtpException {
	    ftpServer.start();
	}

	public static void main(String[] args) {
	    new ClassPathXmlApplicationContext("ftp-server-config.xml");
	}
}
