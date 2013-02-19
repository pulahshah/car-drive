package com.cardrive.ftpserver;

import java.util.ArrayList;
import java.util.List;

import org.apache.ftpserver.ftplet.Authentication;
import org.apache.ftpserver.ftplet.AuthenticationFailedException;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.UsernamePasswordAuthentication;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

public class CarDriveUserManager implements UserManager {

	private final String uploadDir;
	private final String ROOT_USER = "root";
	private final String ROOT_PASS = "root";

	public CarDriveUserManager(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	@Override
	public User authenticate(Authentication authentication)
			throws AuthenticationFailedException {
		if (authentication instanceof UsernamePasswordAuthentication) {
			String userName = ((UsernamePasswordAuthentication) authentication)
					.getUsername();
			String password = ((UsernamePasswordAuthentication) authentication)
					.getPassword();
			if (userName.equals(ROOT_USER) && password.equals(ROOT_PASS)) {
				BaseUser user = new BaseUser();
				user.setHomeDirectory(uploadDir);
				user.setName(ROOT_USER);
				user.setPassword(null);
				List<Authority> authorities = new ArrayList<Authority>();
				authorities.add(new WritePermission());
				user.setAuthorities(authorities);
				return user;
			}
		} else {
			// TODO
		}
		return null;
	}

	@Override
	public void delete(String arg0) throws FtpException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean doesExist(String arg0) throws FtpException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAdminName() throws FtpException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getAllUserNames() throws FtpException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByName(String arg0) throws FtpException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAdmin(String arg0) throws FtpException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void save(User arg0) throws FtpException {
		// TODO Auto-generated method stub

	}

}
