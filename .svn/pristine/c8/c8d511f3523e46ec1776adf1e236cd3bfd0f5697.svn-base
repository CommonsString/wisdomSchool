package com.cj.witcommon.utils.common;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ActionUtil {
	public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yy");
	public static final DecimalFormat decimalFormat = new DecimalFormat("0000");
	public static final DecimalFormat decimalFormat1 = new DecimalFormat("0.00");

	public final static String RECEIPT_SAVE_PATH = File.separator+"upload"+File.separator+"Receipt";


	public static final String SESSION_USER = "SESSION_USER";
	public static final String SESSION_USER_LOGOUT = "TK_USER_LOGOUT";// 用户登出
	public static final String SESSION_USER_ACTIVATION_KEY = "TK_USER_ACTIVATION_KEY";
	public static final String SESSION_USER_RESETPASSWORD_KEY = "TK_USER_RESETPASSWORD_KEY";

	public static final String SESSION_Admin = "TK_Admin";
	public static final String SESSION_Admin_LOGOUT = "TK_Admin_LOGOUT";
	public static final String SESSION_Admin_ACTIVATION_KEY = "TK_Admin_ACTIVATION_KEY";
	public static final String SESSION_Admin_RESETPASSWORD_KEY = "TK_Admin_RESETPASSWORD_KEY";

	public static final String SESSION_Doctor = "TK_Doctor";
	public static final String SESSION_Doctor_LOGOUT = "TK_Doctor_LOGOUT";
	public static final String SESSION_Doctor_ACTIVATION_KEY = "TK_Doctor_ACTIVATION_KEY";
	public static final String SESSION_Doctor_RESETPASSWORD_KEY = "TK_Doctor_RESETPASSWORD_KEY";

	public static final String SESSION_FINANCINGCOMPANY = "TK_FINANCINGCOMPANY";
	public static final String SESSION_FINANCINGCOMPANY_LOGOUT = "TK_FINANCINGCOMPANY_LOGOUT";
	public static final String SESSION_FINANCINGCOMPANY_ACTIVATION_KEY = "TK_FINANCINGCOMPANY_ACTIVATION_KEY";
	public static final String SESSION_FINANCINGCOMPANY_RESETPASSWORD_KEY = "TK_FINANCINGCOMPANY_RESETPASSWORD_KEY";

	public static final String CONTRACT_NUM = "CONTRACT_NUM";

	public static final Map<String,String> fileNameMap = new HashMap();
//	public static User getCurrentUser(HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute(SESSION_USER);
//		return user;
//	}
//	public static User getCurrentUser(HttpSession httpSession) {
//		Object obj = httpSession.getAttribute(SESSION_USER);
//		if(obj == null){
//			return null;
//		}
//		return (User)obj;
//	}

//	public static void setCurrentUser(HttpServletRequest request, User user) {
//		HttpSession session = request.getSession();
//		session.setAttribute(SESSION_USER, user);
//		session.setAttribute(SESSION_USER_LOGOUT, false);
//	}
//
//	public static Admin getCurrentAdmin(HttpServletRequest request) {
//		Admin Admin = (Admin) request.getSession().getAttribute(SESSION_Admin);
//		return Admin;
//	}
//
//	public static void setCurrentAdmin(HttpServletRequest request, Admin Admin) {
//		HttpSession session = request.getSession();
//		session.setAttribute(SESSION_Admin, Admin);
//		session.setAttribute(SESSION_Admin_LOGOUT, false);
//	}

//	public static Doctor getCurrentDoctor(HttpServletRequest request) {
//		Doctor doctor = (Doctor) request.getSession().getAttribute(SESSION_Doctor);
//		return doctor;
//	}

//	public static void setCurrentDoctor(HttpServletRequest request, Doctor doctor) {
//		HttpSession session = request.getSession();
//		session.setAttribute(SESSION_Doctor, doctor);
//		session.setAttribute(SESSION_Doctor_LOGOUT, false);
//	}


	public static void removeCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(SESSION_USER);
		session.setAttribute(SESSION_USER_LOGOUT, true);
	}

	public static void removeCurrentAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(SESSION_Admin);
		session.setAttribute(SESSION_Admin_LOGOUT, true);
	}

	public static void removeCurrentDoctor(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(SESSION_Doctor);
		session.setAttribute(SESSION_Doctor_LOGOUT, true);
	}

	public static void removeCurrentManufacturer(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(SESSION_Doctor);
		session.setAttribute(SESSION_Doctor_LOGOUT, true);
	}

	public static void removeCurrentOperate(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(SESSION_FINANCINGCOMPANY);
		session.setAttribute(SESSION_FINANCINGCOMPANY_LOGOUT, true);
	}


	public static boolean getLogout(HttpServletRequest request) {
		Object r = request.getSession().getAttribute(SESSION_USER_LOGOUT);
        return r != null && (Boolean) r;
	}

	public static boolean getLogoutAdmin(HttpServletRequest request) {
		Object r = request.getSession().getAttribute(SESSION_Admin_LOGOUT);
		return r != null && (Boolean) r;
	}

	public static void setActivationKey(HttpServletRequest request, String key) {
		request.getSession().setAttribute(SESSION_USER_ACTIVATION_KEY, key);
	}

	public static void setActivationKeyAdmin(HttpServletRequest request, String key) {
		request.getSession().setAttribute(SESSION_Admin_ACTIVATION_KEY, key);
	}

	public static String getActivationKey(HttpServletRequest request) {
		String key = (String) request.getSession().getAttribute(
				SESSION_USER_ACTIVATION_KEY);
		return key;
	}

	public static String getActivationKeyAdmin(HttpServletRequest request) {
		String key = (String) request.getSession().getAttribute(
				SESSION_Admin_ACTIVATION_KEY);
		return key;
	}

	public static void removeActivationKey(HttpServletRequest request) {
		request.getSession().removeAttribute(SESSION_USER_ACTIVATION_KEY);
	}

	public static void removeActivationKeyAdmin(HttpServletRequest request) {
		request.getSession().removeAttribute(SESSION_Admin_ACTIVATION_KEY);
	}

	public static void setResetPasswordKey(HttpServletRequest request,
			String key) {
		request.getSession().setAttribute(SESSION_USER_RESETPASSWORD_KEY, key);
	}

	public static void setResetPasswordKeyAdmin(HttpServletRequest request,
										   String key) {
		request.getSession().setAttribute(SESSION_Admin_RESETPASSWORD_KEY, key);
	}

	public static String getResetPasswordKey(HttpServletRequest request) {
		String key = (String) request.getSession().getAttribute(
				SESSION_USER_RESETPASSWORD_KEY);
		return key;
	}

	public static String getResetPasswordKeyAdmin(HttpServletRequest request) {
		String key = (String) request.getSession().getAttribute(
				SESSION_Admin_RESETPASSWORD_KEY);
		return key;
	}

	public static void removeResetPasswordKey(HttpServletRequest request) {
		request.getSession().removeAttribute(SESSION_USER_RESETPASSWORD_KEY);
	}

	public static void removeResetPasswordKeyAdmin(HttpServletRequest request) {
		request.getSession().removeAttribute(SESSION_Admin_RESETPASSWORD_KEY);
	}
}
