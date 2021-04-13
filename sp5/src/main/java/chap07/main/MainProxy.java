package chap07.main;

import chap07.aop.ExeTimeCalculator;
import chap07.aop.ImpeCalculator;
import chap07.aop.RecCalculator;

public class MainProxy {

	public static void main(String[] args) {
		//미리초를 나노초로 바꾸고자 할때 수정대신 ExeTimeCaclulator를 이용하여 분리 재사용성 증가(aop방식)
		ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator());
		System.out.println(ttCal1.factorial(20));

		ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new RecCalculator());
		System.out.println(ttCal2.factorial(20));
	}
}
