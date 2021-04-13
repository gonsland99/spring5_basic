package chap07.aop;

public class ImpeCalculator implements Calculator{

	@Override
	public long factorial(long num) {
		//팩토리얼(3! = 3x2x1)
		long start = System.currentTimeMillis();
		long result = 1;
		for(long i=1; i<=num; i++) {
			result *= i;
		}
		long end = System.currentTimeMillis();
		System.out.printf("ImpeCalculator.factorial(%d) 실행시간: %d\n",num,(end-start));
		
		return result;
	}
}
