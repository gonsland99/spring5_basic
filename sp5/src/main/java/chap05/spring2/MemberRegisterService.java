package chap05.spring2;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chap05.config.NoProduct;
import chap05.spring.DuplicateMemberException;
import chap05.spring.Member;
import chap05.spring.MemberDao;
import chap05.spring.RegisterRequest;

@NoProduct
@Component
public class MemberRegisterService {
	private MemberDao memberDao;

	public MemberRegisterService() {
	}

	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(),
				LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
