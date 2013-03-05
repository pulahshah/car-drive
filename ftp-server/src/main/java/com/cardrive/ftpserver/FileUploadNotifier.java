package com.cardrive.ftpserver;

import java.io.File;
import java.io.IOException;

import org.apache.ftpserver.filesystem.nativefs.impl.NativeFtpFile;
import org.apache.ftpserver.ftplet.DefaultFtplet;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpFile;
import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.ftplet.FtpSession;
import org.apache.ftpserver.ftplet.FtpletResult;

import com.cardrive.dao.ICrudDao;
import com.cardrive.metrics.Metric;
import com.cardrive.metrics.metricvalue.MetricValueBase;

public class FileUploadNotifier extends DefaultFtplet {
    
    private ICrudDao<Metric<? extends MetricValueBase<? extends Object>>> metricDao;
    
	@Override
	public FtpletResult onUploadEnd(FtpSession session, FtpRequest request)
			throws FtpException, IOException {
		NativeFtpFile workingDirectory = (NativeFtpFile)session.getFileSystemView().getWorkingDirectory();
		String uploadedFileName = request.getArgument();
		File uploadedFile = new File(workingDirectory.getPhysicalFile(), uploadedFileName);
		MetricFileParser parser = new MetricFileParser(uploadedFile, metricDao);
		parser.parse();
		return super.onUploadEnd(session, request);
	}

    public ICrudDao<Metric<? extends MetricValueBase<? extends Object>>> getMetricDao() {
        return metricDao;
    }

    public void setMetricDao(ICrudDao<Metric<? extends MetricValueBase<? extends Object>>> metricDao) {
        this.metricDao = metricDao;
    }
}
