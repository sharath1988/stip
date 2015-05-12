package edu.ucsd.som.stip.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import edu.ucsd.som.stip.beans.AppInfo;
import edu.ucsd.som.stip.entity.AuthUser;

@Name("userService")
@Scope(ScopeType.APPLICATION)
@AutoCreate
public class UserService {
	
	private static final String BADGWS_PATH = "/badgws/seam/resource/rest";
	private static final String LIST_USERS_PATH = "/listUsers";
	private static final String USER_INFO_PATH = "/user/";
	
	@In private AppInfo appInfo;
	
	public AuthUser loadUserInfo(String userId) {
		AuthUser user;
		ClientRequest request = new ClientRequest("http://" + appInfo.getWebServiceServer() +
				BADGWS_PATH + USER_INFO_PATH + userId);
		request.accept(MediaType.APPLICATION_XML_TYPE);
		try {
			ClientResponse<AuthUser> response = request.get(AuthUser.class);
			user = response.getEntity();
		} catch (Exception e) {
			user = null;
		}
		return user;
	}
	
	public List<AuthUser> loadUserList() {
		List<AuthUser> ulist;
		ClientRequest request = new ClientRequest("http://" + appInfo.getWebServiceServer() +
				BADGWS_PATH + LIST_USERS_PATH);
		request.accept(MediaType.APPLICATION_XML_TYPE);
		try {
			ClientResponse<List<AuthUser>> response = request.get(new GenericType<ArrayList<AuthUser>>() {});
			ulist = (List<AuthUser>)response.getEntity();
		} catch (Exception e) {
			ulist = new ArrayList<AuthUser>();
		}
		return ulist;
	}
}
