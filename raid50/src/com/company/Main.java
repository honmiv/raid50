package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double dataNumber = 0;
        do {
            System.out.println("Enter the number of data sets (>=1): ");
            if (sc.hasNextLine())
                dataNumber = Integer.valueOf(sc.nextLine());
        } while (dataNumber < 1);

        double dataSize = 0;
        do {
            System.out.println("Enter the size of each data set (>0): ");
            if (sc.hasNextLine())
                dataSize = Integer.valueOf(sc.nextLine());
        } while (dataSize < 1);

        double stripeNum = 0;
        do {
            System.out.println("Enter the number of striped sets (>=2): ");
            if (sc.hasNextLine())
                stripeNum = Integer.valueOf(sc.nextLine());
        } while (stripeNum < 2);

        double driveNumber = 6;
        double minDriveNumber = stripeNum * (dataNumber + 1);
        do {
            System.out.println("Enter the number of drives (>=" + minDriveNumber + "): ");
            if (sc.hasNextLine())
                driveNumber = Integer.valueOf(sc.nextLine());
        } while (driveNumber < minDriveNumber);

        double driveCapacity = 0;
        double minDriveCapacity = dataSize / stripeNum / (driveNumber / stripeNum - 1) * dataNumber;
        do {
            System.out.println("Enter the capacity of each drive (>=" + minDriveCapacity + "): ");
            if (sc.hasNextLine())
                driveCapacity = Integer.valueOf(sc.nextLine());
        } while (driveCapacity < minDriveCapacity);

        System.err.println("Real data:\n");
        for (int i = 0; i < dataNumber; i++) {
            System.err.printf("\uD83D\uDCBF|_%c:_%.2fGB_|\n",(char) ((double) 'A' + i),dataSize);
        }

        System.err.println("\nStriped to:\n");
        for (int i = 0; i < dataNumber; i++) {
            for (int j = 1; j <= stripeNum; j++) {
                System.err.printf("\uD83D\uDCBF|_%c%d:_%.2fGB_|    ",
                        (char) ((double) 'A' + i), j, dataSize / stripeNum);
            }
            System.err.println();
        }

        System.err.println("\nDivided into:\n");
        for (int i = 0; i < dataNumber; i++) {
            for (int j = 0; j < stripeNum; j++) {
                int stripeDriveNum = (int) (driveNumber / stripeNum - 1); // без учета Parity
                System.err.print("\uD83D\uDCBF");
                for (int k = 1; k <= stripeDriveNum; k++) {
                    int partNum = j * (stripeDriveNum) + k;
                    if (k == i + 1) {
                        System.err.print("|_Parity_" + (char) ((double) 'A' + i) + "_|    ");
                    }
                    System.err.printf("|_%c%d:_%.2fGB_|    ", (char) ((double) 'A' + i),
                            partNum, dataSize / stripeNum / (driveNumber / stripeNum - 1));
                }
            }
            System.err.println();
        }

        double totalCapacity = driveCapacity * driveNumber;
        double used =  dataNumber * (driveNumber - stripeNum) * (dataSize / stripeNum / (driveNumber / stripeNum - 1));
        double efficiency = used / totalCapacity * 100;
        System.err.println("\nTotal capacity: " + totalCapacity + " GB\n"
                + "Used: " + used + " GB\n" + "Efficiency: " + efficiency + " %");
    }
}
