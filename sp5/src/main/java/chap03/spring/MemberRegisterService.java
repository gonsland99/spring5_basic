package chap03.spring;

import java.time.LocalDateTime;

public class MemberRegisterService {
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		//중복된 이메일 체크
		if (member != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		//입력한 회원가입 데이터를 member에 저장, id값을 리턴
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), 
				LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
