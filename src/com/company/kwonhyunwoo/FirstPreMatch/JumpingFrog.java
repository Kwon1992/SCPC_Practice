package com.company.kwonhyunwoo.FirstPreMatch;
/*
You should use the standard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful.
*/

import java.util.Scanner;

class JumpingFrog {
    static int Answer;

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);

        int total_test_case = sc.nextInt(); // total test_case

        for (int test_case = 0; test_case < total_test_case; test_case++) {
            Answer = 0;
            int num_of_stone = sc.nextInt();
            int[] stones = new int[num_of_stone];

            for (int i = 0; i < num_of_stone; i++) {
                stones[i] = sc.nextInt();
            } // 돌의 위치 저장

            int max_jump_len = sc.nextInt();

            int cur_pos = 0; // 현재 개구리 위치

            for (int i = 0; i < num_of_stone; i++) {
                if (i == num_of_stone - 1 && cur_pos + max_jump_len >= stones[i]) { // 마지막 돌 체크?
                    cur_pos = stones[i];
                    Answer++;
                }
                if (cur_pos + max_jump_len < stones[i]) {
                    if (i == 0 || cur_pos == stones[i - 1]) { // 바로 다음 돌로 갈 수 있는 방법이 존재하지 않음.
                        Answer = -1;
                        break;
                    } else {
                        cur_pos = stones[i - 1];
                        Answer++;
                        i = i - 1;
                    }
                }
            }

            System.out.println("Case #" + (test_case + 1));
            System.out.println(Answer);

        }
    }
}
