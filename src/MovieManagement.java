import java.util.Scanner;
import java.util.regex.Pattern;

public class MovieManagement {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        String red = "\u001B[31m", green = "\u001B[32m", blue = "\u001B[34m", reset = "\u001B[0m";
        boolean isHallNum = true, isSeatNum = true;
        int hallNum = 0, seatNum = 0;

        System.out.println("=============================== Setting up Cinema ===============================\n");
        do {
            System.out.print("-> Enter number of Hall in Cinema: ");
            String hallInput = scanner.nextLine().replaceAll(" ", "");
            try {
                hallNum = Integer.parseInt(hallInput);
                if (hallNum > 0) {
                    do {
                        System.out.print("-> Enter number of seat in each Hall: ");
                        String seatInput = scanner.nextLine().replaceAll(" ", "");
                        try {
                            seatNum = Integer.parseInt(seatInput);
                            if (seatNum >= 50) {
                                isSeatNum = false;
                            } else {
                                System.out.println(red + "Number of hall is allowed only number and 50 seats up" + reset);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(red + "Number of hall is allowed only number and 50 seats up" + reset);
                        }
                    } while (isSeatNum);
                    isHallNum = false;
                } else {
                    System.out.println(red + "Number of hall is allowed only number and more than 0" + reset);
                }
            } catch (NumberFormatException e) {
                System.out.println(red + "Number of hall is allowed only number and more than 0" + reset);
            }
        } while (isHallNum);


        boolean[] storeHall = new boolean[hallNum];
        for (int i = 0; i < hallNum; i++) {
            storeHall[i] = true;
        }

        boolean isChoice = true;
        int countHall = 0;
        String[] storeMovies = new String[hallNum];
        String[] storeTypes = new String[hallNum];
        int[] storeDuration = new int[hallNum];
        int[][] bookedSeat = new int[hallNum][seatNum];
        int perPage = 5;

        if (!isSeatNum) {
            do {
                System.out.println("\n\n=========================================================");
                System.out.println(green + "\t\t\t\tCinema Management System" + reset);
                System.out.println("=========================================================");
                System.out.println(blue + "\t1. Insert Movie\n\t2. Check & Booking Movie\n\t3. Check Ticket");
                System.out.println("\t4. Reset Hall\n\t5. Set row to show record\n\t6. Exit" + reset);
                System.out.println("-----------------------------------------------------------");
                int choiceNum;
                boolean isChoose = true;
                do {
                    System.out.print("Please input your choice (1-6): ");
                    String choiceInput = scanner.nextLine().replaceAll(" ", "");
                    isChoice = false;
                    try {
                        choiceNum = Integer.parseInt(choiceInput);
                        if (choiceNum >= 1 && choiceNum <= 6) {
                            isChoose = false;
                            switch (choiceNum) {
                                case 1 -> {
                                    boolean isInsert = true;
                                    if (countHall != storeHall.length) {
                                        System.out.println(green + "\n============ Insert information of movie ============" + reset);
                                        do {
                                            System.out.print("-> Enter Movie Name: ");
                                            String movieName = scanner.nextLine();
                                            if (!movieName.isEmpty()) {
                                                boolean isMovieName = true;
                                                do {
                                                    System.out.print("-> Enter Movie Type: ");
                                                    String types = scanner.nextLine();
                                                    boolean typeMatch = Pattern.matches("^[a-zA-Z\\s]+$", types);
                                                    if (typeMatch) {
                                                        boolean isMovieType = true;
                                                        do {
                                                            System.out.print("-> Enter Duration (min): ");
                                                            String duration = scanner.nextLine().replaceAll(" ", "");
                                                            try {
                                                                int durationMatch = Integer.parseInt(duration);
                                                                if (durationMatch >= 60 && durationMatch <= 180) {
                                                                    int hallMovie = 0;
                                                                    for (int i = 0; i < storeHall.length; i++) {
                                                                        if (storeHall[i]) {
                                                                            storeHall[i] = false;
                                                                            countHall += 1;
                                                                            hallMovie = i + 1;
                                                                            storeMovies[i] = movieName;
                                                                            storeTypes[i] = types;
                                                                            storeDuration[i] = durationMatch;
                                                                            break;
                                                                        }
                                                                    }

                                                                    System.out.println("Movie " + blue + movieName + reset + " will show in hall #" + hallMovie);
                                                                    boolean isContinue = true;
                                                                    do {
                                                                        System.out.print("Do you want to continue? (Y/N): ");
                                                                        String continueInsert = scanner.nextLine().toLowerCase();
                                                                        if ("y".equals(continueInsert)) {
                                                                            if (countHall == storeHall.length) {
                                                                                System.out.println(red + "Unavailable hall to show movie!" + reset);
                                                                                System.out.print("Please any key to continue...");
                                                                                scanner.nextLine();
                                                                                isMovieName = false;
                                                                                isMovieType = false;
                                                                                isInsert = false;
                                                                                isChoice = true;
                                                                                isContinue = false;
                                                                            } else {
                                                                                isMovieName = false;
                                                                                isMovieType = false;
                                                                                isContinue = false;
                                                                            }
                                                                        } else if ("n".equals(continueInsert)) {
                                                                            isMovieName = false;
                                                                            isMovieType = false;
                                                                            isInsert = false;
                                                                            isChoice = true;
                                                                            isContinue = false;
                                                                        } else {
                                                                            System.out.println(red + "Please input (Y/N) or (y/n)!" + reset);
                                                                        }
                                                                    } while (isContinue);
                                                                } else {
                                                                    System.out.println(red + "Duration is allowed input only number and between 60 min to 180 min!" + reset);
                                                                }
                                                            } catch (NumberFormatException e) {
                                                                System.out.println(red + "Duration is allowed input only number and between 60 min to 180 min!" + reset);
                                                            }
                                                        } while (isMovieType);
                                                    } else {
                                                        System.out.println(red + "Movie Type is allowed to insert only text!" + reset);
                                                    }
                                                } while (isMovieName);
                                            } else {
                                                System.out.println(red + "Please input movie name!" + reset);
                                            }
                                        } while (isInsert);
                                    } else {
                                        System.out.println(red + "Unavailable hall to show movie!" + reset);
                                        System.out.print("Please any key to continue...");
                                        scanner.nextLine();
                                        isChoice = true;
                                    }
                                }
                                case 2 -> {
                                    int counts = 0;
                                    for (int i = 0; i < storeMovies.length; i++) {
                                        if (storeMovies[i] != null) counts += 1;
                                    }

                                    if (counts != 0) {
                                        boolean isOption = true;
                                        int currentPage = 1;
                                        do {
                                            int[] unavailable = new int[storeMovies.length];
                                            for (int i = 0; i < bookedSeat.length; i++) {
                                                int countUn = 0;
                                                for (int j = 0; j < bookedSeat[i].length; j++) {
                                                    if (bookedSeat[i][j] != 0) {
                                                        countUn += 1;
                                                    }
                                                }
                                                unavailable[i] = countUn;
                                            }

                                            int totalMovie = 0;

                                            for (int i = 0; i < storeMovies.length; i++) {
                                                if (storeMovies[i] != null) {
                                                    totalMovie += 1;
                                                }
                                            }

                                            int pages = (totalMovie%perPage) > 0 ? (totalMovie/perPage+1) : totalMovie/perPage;
                                            int showFrom = currentPage*perPage-perPage+1;
                                            int showTo = currentPage*perPage;
                                            showTo = showTo > totalMovie ? totalMovie : showTo;

                                            System.out.print(green + "\nPage: " + currentPage + " of " + pages);
                                            System.out.print("\t\tShowing: " + showFrom + " to " + showTo + " of " + totalMovie + " rows" + reset);
                                            System.out.println("\n===========================================================================================================================================");
                                            System.out.printf("║\t%-13s | %-39s | %-29s | %-14s | %-15s | %-15s | %-24s | %-30s ║%n", green + "ID" + reset, green + "Movie Name" + reset, green + "Movie Type" + reset, green + "Duration" + reset, green + "Hall" + reset, green + "Seat" + reset, green + "Available Seat" + reset, red + "Unavailable for Booking" + reset);
                                            System.out.println("===========================================================================================================================================");

                                            for (int i = 0, j = 0; i < storeMovies.length; i++) {
                                                if (storeMovies[i] != null) {
                                                    if (j >= (currentPage*perPage-perPage) && j < (currentPage*perPage) && j < totalMovie) {
                                                        System.out.printf("║\t%-4s | %-39s | %-20s | %-5s%s | %-6s | %-6s | %-15s | %-23s ║%n", i + 1, blue + storeMovies[i] + reset, storeTypes[i], storeDuration[i], "min", i + 1, seatNum, seatNum - unavailable[i], unavailable[i]);
                                                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
                                                    }
                                                    j++;
                                                }
                                            }
                                            System.out.println("1. Booking ticket \t2. First \t3. Previous \t4. Next \t5. Last \t6. Exit");
                                            String booking = scanner.nextLine().replaceAll(" ", "");
                                            try {
                                                int optionNum = Integer.parseInt(booking);
                                                switch (optionNum) {
                                                    case 1 -> {
                                                        boolean isMovieId = true;
                                                        do {
                                                            System.out.print("Enter Movie ID: ");
                                                            String movieBook = scanner.nextLine().replaceAll(" ", "");
                                                            try {
                                                                int movieId = Integer.parseInt(movieBook);
                                                                if (movieId >= 1 && movieId <= totalMovie) {
                                                                    isMovieId = false;
                                                                    System.out.println("\n===================================================== SCREEN HALL #" + movieId + " =====================================================");
                                                                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                                                                    for (int i = 0; i < bookedSeat.length; i++) {
                                                                        int j = i + 1;
                                                                        if (j == movieId) {
                                                                            for (int k = 0; k < bookedSeat[i].length; k++) {
                                                                                int l = k+1;
                                                                                if (bookedSeat[i][k] == 0)
                                                                                    System.out.print("|\t" + green + "(+) " + reset + (k < 9 ? ("0" + l) : l) + "\t|");
                                                                                else
                                                                                    System.out.print("|\t" + red + "(-) " + reset + (k < 9 ? ("0" + l) : l) + "\t|");

                                                                                if ((l % 10 == 0 && l != 0))
                                                                                    System.out.println("\n-------------------------------------------------------------------------------------------------------------------------");
                                                                                if (l % 10 != 0 && l == bookedSeat[i].length) System.out.println("\n-------------------------------------------------------------------------------------------------------------------------");
                                                                            }
                                                                        }
                                                                    }
                                                                    boolean isBooking = true;
                                                                    do {
                                                                        System.out.println("1. Booking ticket \t2. Back");
                                                                        String booked = scanner.nextLine().replaceAll(" ", "");
                                                                        try {
                                                                            int bookedNum = Integer.parseInt(booked);
                                                                            switch (bookedNum) {
                                                                                case 1 -> {
                                                                                    boolean isBooked = true;
                                                                                    do {
                                                                                        System.out.print("Choose seat that you want to booking (e.g:1,2,3): ");
                                                                                        String chooseSeat = scanner.nextLine().replaceAll(" ", "");
                                                                                        String[] bookNumber = chooseSeat.split(",");
                                                                                        int countString = 0;
                                                                                        for (int i = 0; i < bookNumber.length; i++) {
                                                                                            boolean checkNumber = Pattern.matches("^[0-9]+$", bookNumber[i]);
                                                                                            if (!checkNumber)  {
                                                                                                countString += 1;
                                                                                                break;
                                                                                            }
                                                                                        }

                                                                                        if (countString == 0) {
                                                                                            int existedCount = 0;
                                                                                            bookingLoop:
                                                                                            for (int i = 0; i < bookedSeat.length; i++) {
                                                                                                if (i == (movieId - 1)) {
                                                                                                    for (int j = 0; j < bookedSeat[i].length; j++) {
                                                                                                        for (int k = 0; k < bookNumber.length; k++) {
                                                                                                            try {
                                                                                                                int seatNumber = Integer.parseInt(bookNumber[k]);
                                                                                                                if (seatNumber == bookedSeat[i][seatNumber - 1]) {
                                                                                                                    existedCount = seatNumber;
                                                                                                                    System.out.println(red + "Seat Number " + existedCount + " is already booked! Please choose another seat!" + reset);
                                                                                                                    break bookingLoop;
                                                                                                                }
                                                                                                            } catch (ArrayIndexOutOfBoundsException e) {
                                                                                                                System.out.println(red + "Not allow to input string and over then number of seat!" + reset);
                                                                                                                break bookingLoop;
                                                                                                            }
                                                                                                        }

                                                                                                        if (existedCount == 0) {
                                                                                                            for (int k = 0; k < bookNumber.length; k++) {
                                                                                                                int seatNumber = Integer.parseInt(bookNumber[k]);
                                                                                                                if (seatNumber != bookedSeat[i][seatNumber-1]) {
                                                                                                                    bookedSeat[i][seatNumber-1] = seatNumber;
                                                                                                                }
                                                                                                            }
                                                                                                            isBooked = false;
                                                                                                            isBooking = false;
                                                                                                            System.out.println(blue + "\n<<<<<<<<<<<<<<<<< Booked is successfully! >>>>>>>>>>>>>>>>>\n" + reset);
                                                                                                            break bookingLoop;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println(red + "Not allow to input string and over then number of seat!" + reset);
                                                                                        }
                                                                                    } while (isBooked);
                                                                                }
                                                                                case 2 -> isBooking = false;
                                                                                default -> System.out.println(red + "Invalid option! Please choose a valid option (1-2)!" + reset);
                                                                            }
                                                                        } catch (NumberFormatException e) {
                                                                            System.out.println(red + "Invalid option! Please choose a valid option (1-2)!" + reset);
                                                                        }
                                                                    } while (isBooking);
                                                                } else {
                                                                    System.out.println(red + "Movie ID is allowed only number and between 1 to " + totalMovie + reset);
                                                                }
                                                            } catch (NumberFormatException e) {
                                                                System.out.println(red + "Movie ID is allowed only number and between 1 to " + totalMovie + reset);
                                                            }
                                                        } while (isMovieId);
                                                    }
                                                    case 2 -> {
                                                        currentPage = 1;
                                                    }
                                                    case 3 -> {
                                                        if (currentPage > 1)
                                                            currentPage -= 1;
                                                    }
                                                    case 4 -> {
                                                        if (totalMovie-(perPage*currentPage) >= 1)
                                                            currentPage += 1;
                                                    }
                                                    case 5 -> {
                                                        if (totalMovie % perPage > 0 )
                                                            currentPage = totalMovie / perPage + 1;
                                                        else
                                                            currentPage = totalMovie / perPage;
                                                    }
                                                    case 6 -> {
                                                        isOption = false;
                                                        isChoice = true;
                                                    }
                                                    default -> System.out.println(red + "Invalid option! Please choose a valid option (1-6)!" + reset);
                                                }
                                            } catch (NumberFormatException e) {
                                                System.out.println(red + "Invalid option! Please choose a valid option (1-6)!" + reset);
                                            }
                                        } while (isOption);
                                    } else {
                                        System.out.println(red + "\nNo data to show. Please input movie first!" + reset);
                                        System.out.print("Please any key to continue...");
                                        scanner.nextLine();
                                        isChoice = true;
                                    }
                                }
                                case 3 -> {
                                    int countSeat = 0;
                                    int[] countBook = new int[bookedSeat.length];
                                    for (int i = 0; i < bookedSeat.length; i++) {
                                        for (int j = 0; j < bookedSeat[i].length; j++) {
                                            if (bookedSeat[i][j] != 0) {
                                                int countTotal = 0;
                                                countSeat += 1;
                                                countTotal += 1;
                                                countBook[i] = countTotal;
                                            }
                                        }
                                    }

                                    if (countSeat > 0) {
                                        System.out.println("============================================");
                                        System.out.println(green + "\tYour ticket has been booked!" + reset);
                                        System.out.println("============================================");
                                        for (int i = 0; i < bookedSeat.length; i++) {
                                            for (int k = 0; k < countBook.length; k++) {
                                                if (i == k && countBook[k] > 0) {
                                                    int m = i+1;
                                                    System.out.print("Hall"+ blue + " #" + m + reset + "\nMovie Name: " + blue + storeMovies[i] + reset + "\nSeat Booked: ");
                                                    String texts = "";
                                                    for (int j = 0; j < bookedSeat[i].length; j++) {
                                                        if (bookedSeat[i][j] != 0) {
                                                            texts += bookedSeat[i][j] + ", ";
                                                        }
                                                    }
                                                    System.out.print(green + texts.substring(0, texts.length()-2) + reset);
                                                    System.out.println("\n--------------------------------------");
                                                }
                                            }
                                        }
                                    } else {
                                        System.out.println(red + "\nNo data to show. Please book ticket first!" + reset);
                                        System.out.print("Please any key to continue...");
                                        scanner.nextLine();
                                    }
                                    isChoice = true;
                                }
                                case 4 -> {
                                    int countReset = 0;
                                    resetLoop:
                                    for (int i = 0; i < bookedSeat.length; i++) {
                                        for (int j = 0; j < bookedSeat[i].length; j++) {
                                            if (bookedSeat[i][j] != 0) {
                                                countReset += 1;
                                                break resetLoop;
                                            }
                                        }
                                    }

                                    if (countReset > 0) {
                                        for (int i = 0; i < bookedSeat.length; i++) {
                                            for (int j = 0; j < bookedSeat[i].length; j++) {
                                                bookedSeat[i][j] = 0;
                                            }
                                        }
                                        System.out.println(blue + "\n✅ All Hall is already reset!" + reset);
                                        System.out.print("Please any key to continue...");
                                        scanner.nextLine();
                                    } else {
                                        System.out.println(red + "\nNo data to reset!" + reset);
                                        System.out.print("Please any key to continue...");
                                        scanner.nextLine();
                                    }
                                    isChoice = true;
                                }
                                case 5 -> {
                                    int counts = 0;
                                    for (int i = 0; i < storeMovies.length; i++) {
                                        if (storeMovies[i] != null) counts += 1;
                                    }

                                    if (counts > 0) {
                                        boolean isSetRow = true;
                                        do {
                                            System.out.print("=> Enter number of row to show record: ");
                                            String rows = scanner.nextLine();
                                            if (Pattern.matches("^[0-9]\\d*$", rows)) {
                                                if (Integer.parseInt(rows) > 0) {
                                                    perPage = Integer.parseInt(rows);
                                                    isSetRow = false;
                                                    isChoice = true;
                                                    System.out.printf(green + "\n✅ Set Row is successfully!\n" + reset);
                                                } else {
                                                    System.out.println(red + "Number of row is allowed only number and more than 0" + reset);
                                                }
                                            } else {
                                                System.out.println(red + "Number of row is allowed only number and more than 0" + reset);
                                            }
                                        } while (isSetRow);
                                    } else {
                                        System.out.println(red + "\nNo data cannot set row to show movie!" + reset);
                                        System.out.print("Please any key to continue...");
                                        scanner.nextLine();
                                        isChoice = true;
                                    }
                                }
                                default -> {
                                    System.out.println(blue + "\n=============================");
                                    System.out.println("|\tThank you so much!😽\t|");
                                    System.out.println("=============================" + reset);
                                    return;
                                }
                            }
                        } else {
                            System.out.println(red + "Invalid option!" + reset);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(red + "Invalid option!" + reset);
                    }
                } while (isChoose);
            } while (isChoice);
        }

        scanner.close();
    }
}
