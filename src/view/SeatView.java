package view;

import java.util.ArrayList;
import java.util.List;

public class SeatView {
      boolean[] reserveCommonSeat = new boolean[29];
      boolean[] reservePremiumSeat = new boolean[22];
      int numSeat = 0;
      
      List<String> reserveSeatList = new ArrayList<String>(); // {1, 10, 20 ,,...}
      
      public void printCommon(List<String> selectSeatNum) {
         for(int i = 0; i < selectSeatNum.size(); i++) {
            int seatNum = Integer.parseInt(selectSeatNum.get(i));
            reserveCommonSeat[seatNum] = true;
         }
         
            for (int i = 1; i < 29; i++) {
               if (reserveCommonSeat[i] == false) {
                  if (i < 10) {
                     System.out.print("[0" + i + "]");
                  } else {
                     System.out.print("[" + i + "]");
                  }
                  if (i % 2 == 0 && i % 4 != 0) {
                     System.out.print("    ");
                  } else if (i % 4 == 0) {
                     System.out.println();
                  }
               }else {
                  System.out.print("[XX]");
                  if (i % 2 == 0 && i % 4 != 0) {
                     System.out.print("    ");
                  } else if (i % 4 == 0) {
                     System.out.println();
                  }
               }
            }
         }
      
      public void printPremium(List<String> selectSeatNum) {
         for(int i = 0; i < selectSeatNum.size(); i++) {
            int seatNum = Integer.parseInt(selectSeatNum.get(i));
            reservePremiumSeat[seatNum] = true;
         }
            
            for (int i = 1; i < 22; i++) {
               if (reservePremiumSeat[i] == false) {
                  if (i < 10) {
                     System.out.print("[0" + i + "]");
                  } else {
                     System.out.print("[" + i + "]");
                  }
                  if (i % 3 == 2) {
                     System.out.print("    ");
                  } else if (i % 3 == 0) {
                     System.out.println();
                  }
               }else {
                  System.out.print("[XX]");
                  if (i % 3 == 2) {
                     System.out.print("    ");
                  } else if (i % 3 == 0) {
                     System.out.println();
                  }
               }
            }
      }
}