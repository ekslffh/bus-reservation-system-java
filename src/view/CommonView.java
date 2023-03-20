package view;

import java.util.Scanner;

public class CommonView {
	private static Scanner sc = new Scanner(System.in);

	// 파트별 시작선
	public static void bar() {
		System.out.print("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
	}

	// 시작선 밑에 여백
	public static void barMiddle() {
		System.out.println();
		System.out.println();
		System.out.println();
	}

	// 파트별 끝선
	public static void barEnd() {
		System.out.println();
		System.out.println();
		System.out.print("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
		System.out.println();
		System.out.println();
	}

	// 제일처음 시작할때 1번만 나오게 출력해주세요 :)
	public void Default() {
		System.out.println(
				" __         _____       _____       ____     __    __             ____       __  __      ____       \r\n"
						+ "/\\ \\       /\\  __`\\    /\\  __`\\    /\\  _`\\  /\\ \\  /\\ \\           /\\  _`\\    /\\ \\/\\ \\    /\\  _`\\     \r\n"
						+ "\\ \\ \\      \\ \\ \\/\\ \\   \\ \\ \\/\\ \\   \\ \\ \\L\\ \\\\ `\\`\\\\/'/           \\ \\ \\L\\ \\  \\ \\ \\ \\ \\   \\ \\,\\L\\_\\   \r\n"
						+ " \\ \\ \\  __  \\ \\ \\ \\ \\   \\ \\ \\ \\ \\   \\ \\ ,__/ `\\ `\\ /'             \\ \\  _ <'  \\ \\ \\ \\ \\   \\/_\\__ \\   \r\n"
						+ "  \\ \\ \\L\\ \\  \\ \\ \\_\\ \\   \\ \\ \\_\\ \\   \\ \\ \\/    `\\ \\ \\              \\ \\ \\L\\ \\  \\ \\ \\_\\ \\    /\\ \\L\\ \\ \r\n"
						+ "   \\ \\____/   \\ \\_____\\   \\ \\_____\\   \\ \\_\\      \\ \\_\\              \\ \\____/   \\ \\_____\\   \\ `\\____\\\r\n"
						+ "    \\/___/     \\/_____/    \\/_____/    \\/_/       \\/_/               \\/___/     \\/_____/    \\/_____/");
		System.out.println();

		System.out.println(
				"  ∧ ＿∧  　             —̳͟͞͞💛            —̳͟͞͞💛\r\n" + "( ·•︠‿•︡ )つ  —̳͟͞͞❤          —̳͟͞͞❤\r\n"
						+ "(つ　  <\r\n" + "｜　  _つ\r\n" + "`し´      즐거운 여행의 시작과 끝, 루피버스와 함께!  \r ");

	}

	   public String startMenu() {
		      StringBuffer sb = new StringBuffer();

		      bar();
		      System.out.print(" Start Menu ");
		      bar();
		      barMiddle();
		      sb.append("------------------------------------\n");
		      sb.append(" 1. 사용자🚌 | 2. 관리자🚌 | 3. 종료🚌\n");
		      sb.append("------------------------------------\n");
		      System.out.println(sb);
		      System.out.print("루피버스에 오신걸 환영합니다! \n");
		      System.out.print("번호를 선택해 주세요 ₍ᐢ..ᐢ₎ : ");
		      return sc.nextLine();

		   }


}
