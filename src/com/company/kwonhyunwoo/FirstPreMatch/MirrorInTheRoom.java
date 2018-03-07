package com.company.kwonhyunwoo.FirstPreMatch;

import java.util.Arrays;
import java.util.Scanner;

enum Direction {
    LEFT, RIGHT, UP, DOWN;
}

class Light {
    private Direction direction;
    private int cur_pos;

    public Light(Direction direction, int cur_pos) {
        this.direction = direction;
        this.cur_pos = cur_pos;
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getPos() {
        return cur_pos;
    }

    public void setPos(int cur_pos) {
        this.cur_pos = cur_pos;
    }
}


class MirrorInTheRoom {

    static int Answer;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new FileInputStream("input.txt"));

        int T = sc.nextInt();
        for (int test_case = 0; test_case < T; test_case++) {
            Answer = 0;
            ///////////////////////////////////////////////////////////////////////////////////////////
            int room_size = sc.nextInt();
            int[] room = new int[(room_size * room_size)];
            boolean[] room_visited = new boolean[room.length];

            String mirror_type = "";
            sc.nextLine();

            for (int i = 0; i < room_size; i++) {
                mirror_type += sc.nextLine();
            }

            for (int idx = room.length - 1; idx >= 0; idx--) {
                room[idx] = mirror_type.charAt(idx) - '0';
            }


//            int[][] room = new int[room_size][room_size];

            Light light = new Light(Direction.RIGHT, 0);

            // 0 - no mirror | 1 - leftdown mirror | 2 - rightdown mirror
            while (light.getPos() >= 0 && light.getPos() < room.length) {
                moveLight(light, room[light.getPos()], room_size, room_visited);
            }

            for (int i = 0; i < room_visited.length; i++) {
                if (room_visited[i]) Answer++;
            }

            /////////////////////////////////////////////////////////////////////////////////////////////


            // Print the answer to standard output(screen).
            System.out.println("Case #" + (test_case + 1));
            System.out.println(Answer);
        }
    }

    private static void moveLight(Light light, int mirror, int roomSize, boolean[] visited) {
        // 0 - no mirror | 1 - leftdown mirror | 2 - rightdown mirror
        switch (mirror) {
            case 0:
                if (light.getDirection() == Direction.LEFT) {
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() - 1, roomSize));
                } else if (light.getDirection() == Direction.RIGHT) {
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() + 1, roomSize));
                } else if (light.getDirection() == Direction.UP) {
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() - roomSize, roomSize));
                } else {
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() + roomSize, roomSize));
                }
                break;
            case 1:
                visited[light.getPos()] = true;
                if (light.getDirection() == Direction.LEFT) {
                    light.setDirection(Direction.DOWN);
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() + roomSize, roomSize));
                } else if (light.getDirection() == Direction.RIGHT) {
                    light.setDirection(Direction.UP);
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() - roomSize, roomSize));
                } else if (light.getDirection() == Direction.UP) {
                    light.setDirection(Direction.RIGHT);
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() + 1, roomSize));
                } else {
                    light.setDirection(Direction.LEFT);
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() - 1, roomSize));
                }
                break;
            case 2:
                visited[light.getPos()] = true;
                if (light.getDirection() == Direction.LEFT) {
                    light.setDirection(Direction.UP);
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() - roomSize, roomSize));
                } else if (light.getDirection() == Direction.RIGHT) {
                    light.setDirection(Direction.DOWN);
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() + roomSize, roomSize));
                } else if (light.getDirection() == Direction.UP) {
                    light.setDirection(Direction.LEFT);
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() - 1, roomSize));
                } else {
                    light.setDirection(Direction.RIGHT);
                    light.setPos(checkOutOfRoom(light.getPos(), light.getPos() + 1, roomSize));
                }
                break;
        }
    }

    private static int checkOutOfRoom(int curPos, int targetPos, int roomSize) {
        if ((curPos % roomSize == 0 && targetPos == curPos - 1) ||
                curPos % roomSize == roomSize - 1 && targetPos == curPos + 1) {
            return -1;
        }

        return targetPos;
    }
}