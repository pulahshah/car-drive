package com.cardrive.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;

public abstract class FileUtils {
	private static final int BUFFER_LENGTH = 1024;
	private static final int ZIP_OUTFILE_RANDOM_LENGTH = 4;

	public static String handleZipFile(String infilePath) throws Exception {
		String outfilePath = null;
		ZipFile zf = new ZipFile(infilePath);
		Enumeration<? extends ZipEntry> entries = zf.entries();
		if (zf.size() > 1) {
			StringBuilder names = new StringBuilder();
			while (entries.hasMoreElements()) {
				names.append(entries.nextElement().getName()).append("\n");
			}
			throw new Exception("more than 1 file contained inside zip file.");
		}

		// only 1 entry
		ZipEntry entry = entries.nextElement();
		outfilePath = handleFileUncompression(zf.getInputStream(entry),
				infilePath, entry.getName());
		return outfilePath;
	}

	protected static String handleFileUncompression(InputStream inputStream,
			String infilePath, String outfilePath) throws IOException {
		int count;
		byte buf[] = new byte[BUFFER_LENGTH];

		BufferedInputStream in = new BufferedInputStream(inputStream);
		File outFile = new File(new File(infilePath).getParentFile(),
				RandomStringUtils.randomAlphanumeric(ZIP_OUTFILE_RANDOM_LENGTH)
						+ "-" + new File(outfilePath).getName());
		FileOutputStream fos = new FileOutputStream(outFile);
		BufferedOutputStream out = new BufferedOutputStream(fos, BUFFER_LENGTH);

		try {
			while ((count = in.read(buf, 0, BUFFER_LENGTH)) != -1) {
				out.write(buf, 0, count);
			}
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(in);
		}

		return outFile.getAbsolutePath();
	}

}
