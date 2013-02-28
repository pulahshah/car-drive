package com.cardrive.ftpserver;

import java.io.File;
import java.io.IOException;

import org.apache.ftpserver.ftplet.DefaultFtplet;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpFile;
import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.ftplet.FtpSession;
import org.apache.ftpserver.ftplet.FtpletResult;

public class FileUploadNotifier extends DefaultFtplet {
	@Override
	public FtpletResult onUploadEnd(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		FtpFile workingDirectory = session.getFileSystemView().getWorkingDirectory();
		String uploadedFileName = request.getArgument();
		File uploadedFile = new File(workingDirectory.getAbsolutePath(), uploadedFileName);
		
		return super.onUploadEnd(session, request);
	}
}
