package chap03.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chap03.assembler.Assembler;
import chap03.spring.ChangePasswordService;
import chap03.spring.DuplicateMemberException;
import chap03.spring.MemberNotFoundException;
import chap03.spring.MemberRegisterService;
import chap03.spring.RegisterRequest;
import chap03.spring.WrongIdPasswordException;

public class MainForAssembler {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {	//command(입력값)과 "exit" 값을 비교
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("new ")) {
				processNewCommand(command.split(" ")); //공백을 기준으로 구분하여 배열생성
				continue;							//{new, test@test.com, test, 1234, 1234}
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp();	//그외 입력값 처리 메서드
		}
	}

	private static Assembler assembler = new Assembler();

	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = assembler.getMemberRegisterService();
		RegisterRequest req = new RegisterRequest();
		//arg[0]은 "new"가 들어가 있음
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(req); //입력한 데이터 등록 후 id값 받아옴.
			System.out.println("등록했습니다.\n");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}

	private static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		ChangePasswordService changePwdSvc = 
				assembler.getChangePasswordService();
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}
}