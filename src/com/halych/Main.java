package com.halych;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static int purchasedTicketsAmount = 0;
    private static int currentIncome = 0;
    private static int totalSeats = 0;


    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scanner.nextInt();
        System.out.println();
        char[][] cinema = new char [numberOfRows][numberOfSeats];
        totalSeats = numberOfRows * numberOfSeats;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                cinema[i][j] = 'S';
            }
        }
        boolean exit = false;
        while(!exit) {
            printMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> printSeats(cinema);
                case 2 -> buyTicket(cinema);
                case 3 -> printStatistics(cinema);
                case 0 -> exit = true;
                default -> System.out.println("Please choose the correct option.");
            }
        }
    }

    private static void printStatistics(char[][] cinema) {
        float purchasedTicketsPercantage = (float) purchasedTicketsAmount/totalSeats * 100;
        int totalIncome = (cinema.length/2 * cinema[0].length * 10) + ((totalSeats - cinema.length/2 * cinema[0].length) * 8);
        System.out.printf("%nNumber of purchased tickets: %d%nPercentage: %.2f%%%nCurrent income: $%d%nTotal income: $%d%n%n",
                purchasedTicketsAmount, purchasedTicketsPercantage, currentIncome, totalIncome);
    }

    private static void printSeats(char[][] cinema) {
        System.out.println();
        int index = 0;
        for(int i = -1; i < cinema.length; i++) {
            if(i == -1) {
                System.out.print(" ");
            }
            else {
                index = i;
                System.out.print((i+1));
            }
            for(int j = 0; j < cinema[index].length; j++){
                if(i == -1) System.out.print(" "+(j+1));
                else System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printMenu() {
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
    }

    private static void buyTicket(char[][] cinema) {
        boolean exit = false;
        int seatNum;
        int rowNum;
        do {
            System.out.println("\nEnter a row number:");
            rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNum = scanner.nextInt();
            if (rowNum - 1 < 0 || rowNum > cinema.length ) {
                System.out.println("\nWrong input!");
            } else if (seatNum - 1 < 0 || seatNum > cinema[seatNum - 1].length) {
                System.out.println("\nWrong input!");
            } else if (cinema[rowNum-1][seatNum-1] == 'B') {
                System.out.println("\nThat ticket has already been purchased!");
            } else {
                exit = true;
            }
        } while (!exit);
        int price = 10;
        if (totalSeats>60&&rowNum>cinema.length/2) price = 8;
        System.out.println("Ticket price: $" + price + "\n");
        cinema[rowNum-1][seatNum-1] = 'B';
        purchasedTicketsAmount++;
        currentIncome+=price;
    }


}
