package chap03.assembler;

import chap03.spring.ChangePasswordService;
import chap03.spring.MemberDao;
import chap03.spring.MemberRegisterService;
//객체 조립기: 의존주입(조립)을 처리하는 클래스
public class Assembler {

	private MemberDao memberDao;	//의존주입 클래스
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;

	public Assembler() {
		memberDao = new MemberDao();
		//의존주입 방식1
		regSvc = new MemberRegisterService(memberDao);
		//의존주입 방식2(추천)
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}

	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}

}
