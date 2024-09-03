import java.util.Scanner;
import java.util.Random;

//------------------------------------------------------------------------------------
class Players {
    String playerName;
    int playerID;
    // ------------------------------------------------------------------------------------
    int playerRuns;
    double playerStrikeRate;
    int playerBallsPlayed;
    int playerFours;
    int playerSixes;
    int playerDotBalls;
    boolean batted;
    // ------------------------------------------------------------------------------------
    boolean bowledOver;
    int playerWickets;
    int oversBowled;
    int gotRuns;
    int bowledExtra;
    double playerEconomy;
}

// ------------------------------------------------------------------------------------
class Team {
    int teamNumber;
    String teamName;
    Players[] teamPlayers;
    int teamWickets;
    double teamRunRate;
    int teamRuns;
    int teamFours;
    int teamSixes;
    int teamDotBalls;
    int teamExtras;
    // ------------------------------------------------------------------------------------
    Scanner sc = new Scanner(System.in);

    void setTeamAndPlayersAuto() {
        teamName = "Team-" + teamNumber;
        teamPlayers = new Players[11];
        for (int i = 0; i < teamPlayers.length; i++) {
            teamPlayers[i] = new Players();
            teamPlayers[i].playerName = teamName + ":Player " + (i + 1);
            teamPlayers[i].playerID = (i + 1);
        }
    }

    // ------------------------------------------------------------------------------------
    void editTeamDetails() {
        teamName = sc.nextLine();
    }

    // ------------------------------------------------------------------------------------
    void editPlayerDetails() {
        for (int i = 0; i < teamPlayers.length; i++) {
            System.out.println("Enter name of Player " + (i + 1));
            teamPlayers[i].playerName = sc.nextLine();
        }
    }
}

// ------------------------------------------------------------------------------------
class Innings {
    Team battingTeam;
    Team bowlingTeam;
    Team winnerTeam;
    int bowlerMaxOvers;
    int oversCompleted = 0;
    int balls = 1;
    int overs;
    int ballCount;
    // ------------------------------------------------------------------------------------
    Scanner sc = new Scanner(System.in);

    // ------------------------------------------------------------------------------------
    Innings(int overs, int bowlerMaxOvers) {
        this.overs = overs;
        ballCount = overs * 6;
        this.bowlerMaxOvers = bowlerMaxOvers;
    }

    // ------------------------------------------------------------------------------------
    void toss(Team t1, Team t2) {
        Random r = new Random();
        int teamWinToss = r.nextInt(2)+1 ;

        if (teamWinToss == t1.teamNumber) {

            System.out.println("Batting Team = " + t1.teamName);
            System.out.println("---------------------------------------------");
            System.out.println("Bowling Team = " + t2.teamName);
            System.out.println("---------------------------------------------");
            battingTeam = t1;
            bowlingTeam = t2;
        } else {
            System.out.println("Batting Team = " + t2.teamName);
            System.out.println("---------------------------------------------");
            System.out.println("Bowling Team = " + t1.teamName);
            System.out.println("---------------------------------------------");
            battingTeam = t2;
            bowlingTeam = t1;
        }
    }

    // ------------------------------------------------------------------------------------
    void startInnings() {
        Players striker = battingTeam.teamPlayers[0];
        battingTeam.teamPlayers[0].batted = true;

        Players nonStriker = battingTeam.teamPlayers[1];
        battingTeam.teamPlayers[1].batted = true;

        System.out.println(
                "Enter player number of " + bowlingTeam.teamName + " who is bowling.\nEnter from given list below:");
        Players bowler = checkPlayer(bowlingTeam);

        boolean endInnings = false;

        for (int i = 1; i <= ballCount && !endInnings;) {
            System.out.println("---------------------------------------------");
            System.out.println("Enter the score of the ball " + (oversCompleted + "." + balls)
                    + "\n \nEnter 0 -> Dot ball\n" +
                    "Enter 1 -> Single run - Enter 2 -> Two runs - Enter 3 -> Three runs \nEnter 4 -> Four runs" +
                    " - Enter 5 -> Five runs - Enter 6 -> Six runs\nEnter 7 -> Wicket - Enter 8 -> Wide bowl" +
                    " - Enter 9 -> No bowl\nEnter 10 -> End scoreboard.\n");

            System.out.println("Striker = " + striker.playerName);
            System.out.println("Non-striker = " + nonStriker.playerName);
            System.out.println("Bowler = " + bowler.playerName);
            System.out.println("---------------------------------------------");

            boolean checkInput = true;

            while (checkInput) {
                String input = sc.next();
                switch (input) {

                    case "0":
                        balls++;
                        battingTeam.teamDotBalls++;
                        striker.playerDotBalls++;
                        striker.playerBallsPlayed++;
                        checkInput = false;
                        i++;
                        break;

                    case "1":
                        striker.playerRuns += 1;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 1;
                        bowler.gotRuns += 1;
                        balls++;
                        Players temp;
                        temp = striker;
                        striker = nonStriker;
                        nonStriker = temp;
                        checkInput = false;
                        i++;
                        break;

                    case "2":
                        striker.playerRuns += 2;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 2;
                        bowler.gotRuns += 2;
                        balls++;
                        checkInput = false;
                        i++;
                        break;

                    case "3":
                        striker.playerRuns += 3;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 3;
                        bowler.gotRuns += 3;
                        balls++;
                        Players temp1;
                        temp1 = striker;
                        striker = nonStriker;
                        nonStriker = temp1;
                        checkInput = false;
                        i++;
                        break;

                    case "4":
                        striker.playerRuns += 4;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 4;
                        bowler.gotRuns += 4;
                        balls++;
                        striker.playerFours += 1;
                        battingTeam.teamFours += 1;
                        checkInput = false;
                        i++;
                        break;

                    case "5":
                        striker.playerRuns += 5;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 5;
                        bowler.gotRuns += 5;
                        balls++;
                        Players temp2;
                        temp2 = striker;
                        striker = nonStriker;
                        nonStriker = temp2;
                        checkInput = false;
                        i++;
                        break;

                    case "6":
                        striker.playerRuns += 6;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 6;
                        bowler.gotRuns += 6;
                        balls++;
                        striker.playerSixes += 1;
                        battingTeam.teamSixes += 1;
                        checkInput = false;
                        i++;
                        break;

                    case "7":
                        striker.playerBallsPlayed += 1;
                        bowlingTeam.teamWickets += 1;
                        bowler.playerWickets += 1;
                        balls++;
                        System.out.println("---------------------------------------------");
                        System.out.println("Is player caught out? If yes then was the strike changed before catch?\n" +
                                "Enter Yes if both condition satisfy else enter No :");

                        boolean checkStrikeChange = false;
                        boolean flag = false;
                        while (!flag) {
                            String setStrike = sc.next();
                            if (setStrike.equalsIgnoreCase("yes")) {
                                checkStrikeChange = true;
                                System.out.println("---------------------------------------------");
                                flag = true;
                            } else if (setStrike.equalsIgnoreCase("no")) {
                                checkStrikeChange = false;
                                System.out.println("---------------------------------------------");
                                flag = true;
                            } else {
                                System.out.println("---------------------------------------------");
                                System.out.println("Invalid input , Please Enter YES or NO only :");
                                System.out.println("---------------------------------------------");
                            }
                        }

                        if (bowlingTeam.teamWickets < 10) {
                            System.out.println("\nEnter new batsmen number from given list below:\n");
                            boolean match = false;
                            for (int j = 0; j < battingTeam.teamPlayers.length; j++) {
                                if (!battingTeam.teamPlayers[j].batted)
                                    System.out.println((j + 1) + " for " + battingTeam.teamPlayers[j].playerName);
                            }
                            while (!match) {
                                int check0 = 0;
                                boolean check1 = true;
                                while (check1) {
                                    if (sc.hasNextInt()) {
                                        check0 = sc.nextInt();
                                        if (check0 > 0 && check0 < 12) {
                                            check1 = false;
                                        } else {
                                            System.out.println("---------------------------------------------");
                                            System.out.println("Invalid Input , Please re-enter again :");
                                            System.out.println("---------------------------------------------");
                                        }
                                    } else {
                                        System.out.println("---------------------------------------------");
                                        System.out.println("Invalid Input , Please re-enter again :");
                                        System.out.println("---------------------------------------------");
                                        sc.next();
                                    }
                                }
                                for (int j = 0; j < battingTeam.teamPlayers.length; j++) {
                                    if (check0 == (battingTeam.teamPlayers[j].playerID)
                                            && !battingTeam.teamPlayers[j].batted) {
                                        striker = battingTeam.teamPlayers[j];
                                        battingTeam.teamPlayers[j].batted = true;
                                        match = true;
                                    }
                                }
                                if (!match) {
                                    System.out.println("---------------------------------------------");
                                    System.out.println("Invalid Input,Please re-enter:");
                                    System.out.println("---------------------------------------------");
                                }
                            }
                        } else {
                            endInnings = true;
                            System.out.println("End of the Innings here,the score is  ->> " + battingTeam.teamRuns + "/"
                                    + bowlingTeam.teamWickets);
                        }
                        if (checkStrikeChange) {
                            Players temp3;
                            temp3 = striker;
                            striker = nonStriker;
                            nonStriker = temp3;
                        }
                        checkInput = false;
                        i++;
                        break;

                    case "8":
                        battingTeam.teamRuns += 1;
                        bowler.gotRuns += 1;
                        checkInput = false;
                        bowler.bowledExtra++;
                        bowlingTeam.teamExtras++;
                        break;

                    case "9":
                        battingTeam.teamRuns += 1;
                        bowler.gotRuns += 1;
                        checkInput = false;
                        bowlingTeam.teamExtras++;
                        bowler.bowledExtra++;
                        break;

                    case "10":
                        System.out.println("---------------------------------------------");
                        System.out.println("The scoreboard input function ends here.");
                        System.out.println("---------------------------------------------");
                        endInnings = true;
                        checkInput = false;
                        break;

                    default:
                        System.out.println("---------------------------------------------");
                        System.out.println("Invalid Input,Please re-enter:");
                        System.out.println("---------------------------------------------");
                        break;
                }
                if (balls == 7) {
                    oversCompleted += 1;
                    balls = 1;
                    bowler.oversBowled++;
                    Players temp;
                    temp = striker;
                    striker = nonStriker;
                    nonStriker = temp;

                    if (oversCompleted != overs) {
                        System.out.println("Score after " + oversCompleted + " overs ->> " + battingTeam.teamRuns + "/"
                                + bowlingTeam.teamWickets);
                        System.out.println(
                                "Enter new bowler number from the given list below:\nDon't select a bowler who bowled last over.");
                        boolean check = true;
                        while (check) {
                            bowler = checkPlayer(bowlingTeam);
                            if (bowler.oversBowled == bowlerMaxOvers) {
                                System.out.println("\nBowler - " + bowler.playerName + " has already bowled "
                                        + bowlerMaxOvers + " over/s" +
                                        ".\nPlease select a different bowler.\n");
                            } else {
                                check = false;
                            }
                        }
                    } else {
                        balls = 0;
                        System.out.println(
                                "\n\t\t\t\t====================== END OF THE INNINGS ======================\n");
                        System.out.println(
                                "The score is  ->> " + battingTeam.teamRuns + "/" + bowlingTeam.teamWickets + "\n");
                    }
                }
            }
        }
    }

    // ------------------------------------------------------------------------------------
    void startInnings(Innings i1) {
        Players striker = battingTeam.teamPlayers[0];
        battingTeam.teamPlayers[0].batted = true;

        Players nonStriker = battingTeam.teamPlayers[1];
        battingTeam.teamPlayers[1].batted = true;

        System.out.println(
                "Enter player number of " + bowlingTeam.teamName + " who is bowling.\nEnter from given list below:");
        Players bowler = checkPlayer(bowlingTeam);

        boolean endInnings = false;

        for (int i = 1; i <= ballCount && !endInnings;) {
            System.out.println("---------------------------------------------");
            System.out.println("Enter the score of the ball " + (oversCompleted + "." + balls)
                    + "\n \nEnter 0 -> Dot ball\n" +
                    "Enter 1 -> Single run - Enter 2 -> Two runs - Enter 3 -> Three runs \nEnter 4 -> Four runs" +
                    " - Enter 5 -> Five runs - Enter 6 -> Six runs\nEnter 7 -> Wicket - Enter 8 -> Wide bowl" +
                    " - Enter 9 -> No bowl\nEnter 10 -> End scoreboard.\n");

            System.out.println("Striker = " + striker.playerName);
            System.out.println("Non-striker = " + nonStriker.playerName);
            System.out.println("Bowler = " + bowler.playerName);
            System.out.println("---------------------------------------------");

            boolean checkInput = true;

            while (checkInput) {
                String input = sc.next();
                switch (input) {

                    case "0":
                        balls++;
                        battingTeam.teamDotBalls++;
                        striker.playerDotBalls++;
                        striker.playerBallsPlayed++;
                        if (battingTeam.teamRuns > i1.battingTeam.teamRuns) {
                            endInnings = true;
                            System.out.println("---------------------------------------------");
                            System.out.println("********************************************* WINNER IS TEAM - " +
                                    battingTeam.teamName + " *********************************************");
                            System.out.println("---------------------------------------------");
                        }
                        checkInput = false;
                        i++;
                        break;

                    case "1":
                        striker.playerRuns += 1;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 1;
                        bowler.gotRuns += 1;
                        balls++;
                        Players temp;
                        temp = striker;
                        striker = nonStriker;
                        nonStriker = temp;
                        if (battingTeam.teamRuns > i1.battingTeam.teamRuns) {
                            endInnings = true;
                            System.out.println("---------------------------------------------");
                            System.out.println("********************************************* WINNER IS TEAM - " +
                                    battingTeam.teamName + " *********************************************");
                            winnerTeam = battingTeam;
                            if (balls < 7)
                                balls--;
                            System.out.println("---------------------------------------------");
                        }
                        checkInput = false;
                        i++;
                        break;

                    case "2":
                        striker.playerRuns += 2;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 2;
                        bowler.gotRuns += 2;
                        balls++;
                        if (battingTeam.teamRuns > i1.battingTeam.teamRuns) {
                            endInnings = true;
                            System.out.println("---------------------------------------------");
                            System.out.println("********************************************* WINNER IS TEAM - " +
                                    battingTeam.teamName + " *********************************************");
                            winnerTeam = battingTeam;
                            if (balls < 7)
                                balls--;
                            System.out.println("---------------------------------------------");
                        }
                        checkInput = false;
                        i++;
                        break;

                    case "3":
                        striker.playerRuns += 3;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 3;
                        bowler.gotRuns += 3;
                        balls++;
                        Players temp1;
                        temp1 = striker;
                        striker = nonStriker;
                        nonStriker = temp1;
                        if (battingTeam.teamRuns > i1.battingTeam.teamRuns) {
                            endInnings = true;
                            System.out.println("---------------------------------------------");
                            System.out.println("********************************************* WINNER IS TEAM - " +
                                    battingTeam.teamName + " *********************************************");
                            winnerTeam = battingTeam;
                            if (balls < 7)
                                balls--;
                            System.out.println("---------------------------------------------");
                        }
                        checkInput = false;
                        i++;
                        break;

                    case "4":
                        striker.playerRuns += 4;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 4;
                        bowler.gotRuns += 4;
                        balls++;
                        striker.playerFours += 1;
                        battingTeam.teamFours += 1;
                        if (battingTeam.teamRuns > i1.battingTeam.teamRuns) {
                            endInnings = true;
                            System.out.println("---------------------------------------------");
                            System.out.println("********************************************* WINNER IS TEAM - " +
                                    battingTeam.teamName + " *********************************************");
                            winnerTeam = battingTeam;
                            if (balls < 7)
                                balls--;
                            System.out.println("---------------------------------------------");
                        }
                        checkInput = false;
                        i++;
                        break;

                    case "5":
                        striker.playerRuns += 5;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 5;
                        bowler.gotRuns += 5;
                        balls++;
                        Players temp2;
                        temp2 = striker;
                        striker = nonStriker;
                        nonStriker = temp2;
                        if (battingTeam.teamRuns > i1.battingTeam.teamRuns) {
                            endInnings = true;
                            System.out.println("---------------------------------------------");
                            System.out.println("********************************************* WINNER IS TEAM - " +
                                    battingTeam.teamName + " *********************************************");
                            winnerTeam = battingTeam;
                            if (balls < 7)
                                balls--;
                            System.out.println("---------------------------------------------");
                        }
                        checkInput = false;
                        i++;
                        break;

                    case "6":
                        striker.playerRuns += 6;
                        striker.playerBallsPlayed += 1;
                        battingTeam.teamRuns += 6;
                        bowler.gotRuns += 6;
                        balls++;
                        striker.playerSixes += 1;
                        battingTeam.teamSixes += 1;
                        if (battingTeam.teamRuns > i1.battingTeam.teamRuns) {
                            endInnings = true;
                            System.out.println("---------------------------------------------");
                            System.out.println("********************************************* WINNER IS TEAM - " +
                                    battingTeam.teamName + " *********************************************");
                            winnerTeam = battingTeam;
                            if (balls < 7)
                                balls--;
                            System.out.println("---------------------------------------------");
                        }
                        checkInput = false;
                        i++;
                        break;

                    case "7":
                        striker.playerBallsPlayed += 1;
                        bowlingTeam.teamWickets += 1;
                        bowler.playerWickets += 1;
                        balls++;
                        System.out.println("---------------------------------------------");
                        System.out.println("Is player caught out? If yes then was the strike changed before catch?\n" +
                                "Enter Yes if both condition satisfy else enter No :");

                        boolean checkStrikeChange = false;
                        boolean flag = false;
                        while (!flag) {
                            String setStrike = sc.next();
                            if (setStrike.equalsIgnoreCase("yes")) {
                                checkStrikeChange = true;
                                System.out.println("---------------------------------------------");
                                flag = true;
                            } else if (setStrike.equalsIgnoreCase("no")) {
                                checkStrikeChange = false;
                                System.out.println("---------------------------------------------");
                                flag = true;
                            } else {
                                System.out.println("---------------------------------------------");
                                System.out.println("Invalid input , Please Enter YES or NO only :");
                                System.out.println("---------------------------------------------");
                            }
                        }

                        if (bowlingTeam.teamWickets < 10) {
                            System.out.println("\nEnter new batsmen number from given list below:\n");
                            boolean match = false;
                            for (int j = 0; j < battingTeam.teamPlayers.length; j++) {
                                if (!battingTeam.teamPlayers[j].batted)
                                    System.out.println((j + 1) + " for " + battingTeam.teamPlayers[j].playerName);
                            }
                            while (!match) {
                                int check0 = 0;
                                boolean check1 = true;
                                while (check1) {
                                    if (sc.hasNextInt()) {
                                        check0 = sc.nextInt();
                                        if (check0 > 0 && check0 < 12) {
                                            check1 = false;
                                        } else {
                                            System.out.println("---------------------------------------------");
                                            System.out.println("Invalid Input , Please re-enter again :");
                                            System.out.println("---------------------------------------------");
                                        }
                                    } else {
                                        System.out.println("---------------------------------------------");
                                        System.out.println("Invalid Input , Please re-enter again :");
                                        System.out.println("---------------------------------------------");
                                        sc.next();
                                    }
                                }
                                for (int j = 0; j < battingTeam.teamPlayers.length; j++) {
                                    if (check0 == (battingTeam.teamPlayers[j].playerID)
                                            && !battingTeam.teamPlayers[j].batted) {
                                        striker = battingTeam.teamPlayers[j];
                                        battingTeam.teamPlayers[j].batted = true;
                                        match = true;
                                    }
                                }
                                if (!match) {
                                    System.out.println("---------------------------------------------");
                                    System.out.println("Invalid Input,Please re-enter:");
                                    System.out.println("---------------------------------------------");
                                }
                            }
                        } else {
                            endInnings = true;
                            if (battingTeam.teamRuns == i1.battingTeam.teamRuns) {
                                System.out.println("---------------------------------------------");
                                System.out.println("********************************************* " +
                                        "MATCH IS TIED *********************************************");
                                if (balls < 7)
                                    balls--;
                                System.out.println("---------------------------------------------");
                            } else {
                                System.out.println("---------------------------------------------");
                                System.out.println("********************************************* WINNER IS TEAM - " +
                                        bowlingTeam.teamName + " *********************************************");
                                if (balls < 7)
                                    balls--;
                                winnerTeam = bowlingTeam;
                                System.out.println("---------------------------------------------");
                            }
                        }
                        if (checkStrikeChange) {
                            Players temp3;
                            temp3 = striker;
                            striker = nonStriker;
                            nonStriker = temp3;
                        }
                        checkInput = false;
                        i++;
                        break;

                    case "8":
                        battingTeam.teamRuns += 1;
                        bowler.gotRuns += 1;
                        checkInput = false;
                        bowler.bowledExtra++;
                        bowlingTeam.teamExtras++;
                        if (battingTeam.teamRuns > i1.battingTeam.teamRuns) {
                            endInnings = true;
                            System.out.println("---------------------------------------------");
                            System.out.println("********************************************* WINNER IS TEAM - " +
                                    battingTeam.teamName + " *********************************************");
                            winnerTeam = battingTeam;
                            if (balls < 7)
                                balls--;
                            System.out.println("---------------------------------------------");
                        }
                        break;

                    case "9":
                        battingTeam.teamRuns += 1;
                        bowler.gotRuns += 1;
                        checkInput = false;
                        bowlingTeam.teamExtras++;
                        bowler.bowledExtra++;
                        if (battingTeam.teamRuns > i1.battingTeam.teamRuns) {
                            endInnings = true;
                            System.out.println("---------------------------------------------");
                            System.out.println("********************************************* WINNER IS TEAM - " +
                                    battingTeam.teamName + " *********************************************");
                            winnerTeam = battingTeam;
                            if (balls < 7)
                                balls--;
                            System.out.println("---------------------------------------------");
                        }
                        break;

                    case "10":
                        System.out.println("---------------------------------------------");
                        System.out.println("The scoreboard input function ends here.");
                        System.out.println("---------------------------------------------");
                        endInnings = true;
                        checkInput = false;
                        break;

                    default:
                        System.out.println("---------------------------------------------");
                        System.out.println("Invalid Input,Please re-enter:");
                        System.out.println("---------------------------------------------");
                        break;
                }
                if (balls == 7) {
                    oversCompleted += 1;
                    balls = 1;
                    bowler.oversBowled++;
                    Players temp;
                    temp = striker;
                    striker = nonStriker;
                    nonStriker = temp;

                    if (oversCompleted != overs) {
                        System.out.println("Score after " + oversCompleted + " overs ->> " + battingTeam.teamRuns + "/"
                                + bowlingTeam.teamWickets);
                        System.out.println(
                                "Enter new bowler number from the given list below:\nDon't select a bowler who bowled last over.");
                        boolean check = true;
                        while (check) {
                            bowler = checkPlayer(bowlingTeam);
                            if (bowler.oversBowled == bowlerMaxOvers) {
                                System.out.println("\nBowler - " + bowler.playerName + " has already bowled "
                                        + bowlerMaxOvers + " over/s" +
                                        ".\nPlease select a different bowler.\n");
                            } else {
                                check = false;
                            }
                        }
                    } else {
                        balls = 0;
                        System.out.println(
                                "\n\t\t\t\t====================== END OF THE INNINGS ======================\n");
                        System.out.println("---------------------------------------------");
                        System.out.println("The score is  ->> " + battingTeam.teamRuns + "/" + bowlingTeam.teamWickets);
                        System.out.println("---------------------------------------------");
                        if (battingTeam.teamRuns == i1.battingTeam.teamRuns) {
                            System.out.println("********************************************* " +
                                    "MATCH IS TIED *********************************************");
                        } else if (battingTeam.teamRuns < i1.battingTeam.teamRuns) {
                            System.out.println("********************************************* WINNER IS TEAM - " +
                                    bowlingTeam.teamName + " *********************************************");
                            winnerTeam = bowlingTeam;
                        }
                        System.out.println("---------------------------------------------");
                    }
                }
            }
        }
    }

    // ------------------------------------------------------------------------------------
    void calculateScore() {
        if (oversCompleted == 0)
            battingTeam.teamRunRate = battingTeam.teamRuns / (balls / (double) 6);
        else if (balls != 0)
            battingTeam.teamRunRate = battingTeam.teamRuns / (oversCompleted * 6 + balls / (double) 6);
        else
            battingTeam.teamRunRate = battingTeam.teamRuns / (double) (oversCompleted);
        for (int i = 0; i < battingTeam.teamPlayers.length; i++) {
            battingTeam.teamPlayers[i].playerStrikeRate = battingTeam.teamPlayers[i].playerRuns
                    / (double) battingTeam.teamPlayers[i].playerBallsPlayed * 100;
            if (bowlingTeam.teamPlayers[i].oversBowled == 0) {
                bowlingTeam.teamPlayers[i].playerEconomy = Double.NaN;
            } else
                bowlingTeam.teamPlayers[i].playerEconomy = bowlingTeam.teamPlayers[i].gotRuns
                        / (double) bowlingTeam.teamPlayers[i].oversBowled;
        }
    }

    // ------------------------------------------------------------------------------------
    void displayOverallScore() {
        System.out.println("\n\t\t\t\t^^^^^^^^^^^^^^^^^^^^^^^^^ OVERALL SCOREBOARD ^^^^^^^^^^^^^^^^^^^^^^^^^\n");
        System.out.println("\t\t\t\t----------------------------------------------------------------------");
        System.out.println("\t\t\t\t-->> The batting team :- " + battingTeam.teamName);
        System.out.println("\t\t\t\t-->> The bowling team :- " + bowlingTeam.teamName);
        System.out.println("\t\t\t\t-->> Runs scored :- " + battingTeam.teamRuns);
        System.out.printf("\t\t\t\t-->> Run Rate :- %.2f ", battingTeam.teamRunRate);
        System.out.println("\n\t\t\t\t-->> Overs :- " + (oversCompleted + "." + balls));
        System.out.println("\t\t\t\t-->> Wickets fallen :- " + bowlingTeam.teamWickets);
        System.out.println("\t\t\t\t-->> Extras :- " + bowlingTeam.teamExtras);
        System.out.println("\t\t\t\t----------------------------------------------------------------------\n");
    }

    // ------------------------------------------------------------------------------------
    void displayBattingScore() {
        System.out.println("\n\t\t\t\t************************* BATTING SCOREBOARD *************************\n");
        System.out.println("\t\t\t\tBatting Team Name -->> " + battingTeam.teamName);
        System.out.println("\t\t\t\t----------------------------------------------------------------------");
        System.out.printf("\t\t\t\t-->> %-12s %-20s %-15s %-20s %-15s %-15s %-15s %-20s\n", "Batsmen no.", "Name",
                "Runs", "Balls Played",
                "Strike Rate", "Fours", "Sixes", "Dot Balls Played");
        for (int i = 0, k = 1; i < battingTeam.teamPlayers.length; i++) {
            if (battingTeam.teamPlayers[i].batted) {
                System.out.printf("\t\t\t\t-->> %-12d %-20s %-15d %-20d %-15.2f %-15d %-15d %-20d\n", k,
                        battingTeam.teamPlayers[i].playerName, battingTeam.teamPlayers[i].playerRuns,
                        battingTeam.teamPlayers[i].playerBallsPlayed, battingTeam.teamPlayers[i].playerStrikeRate,
                        battingTeam.teamPlayers[i].playerFours, battingTeam.teamPlayers[i].playerSixes,
                        battingTeam.teamPlayers[i].playerDotBalls);
                k++;
            }
        }
        System.out.println("\t\t\t\t----------------------------------------------------------------------\n");
    }

    // ------------------------------------------------------------------------------------
    void displayBowlingScore() {
        System.out.println("\n\t\t\t\t************************* BOWLING SCOREBOARD *************************\n");
        System.out.println("\t\t\t\tBowling Team Name -->> " + bowlingTeam.teamName);
        System.out.println("\t\t\t\t----------------------------------------------------------------------");
        System.out.printf("\t\t\t\t-->> %-12s %-20s %-15s %-15s %-15s %-15s %-15s\n", "Bowler no.", "Name",
                "Runs conceded", "Wickets Taken",
                "Overs bowled", "Economy", "Extras bowled");
        for (int i = 0, k = 1; i < bowlingTeam.teamPlayers.length; i++) {
            if (bowlingTeam.teamPlayers[i].bowledOver) {
                System.out.println();
                System.out.printf("\t\t\t\t-->> %-12d %-20s %-15d %-15d %-15d %-15.2f %-15d\n", k,
                        bowlingTeam.teamPlayers[i].playerName, bowlingTeam.teamPlayers[i].gotRuns,
                        bowlingTeam.teamPlayers[i].playerWickets, bowlingTeam.teamPlayers[i].oversBowled,
                        bowlingTeam.teamPlayers[i].playerEconomy, bowlingTeam.teamPlayers[i].bowledExtra);
                k++;
            }
        }
        System.out.println("\t\t\t\t----------------------------------------------------------------------\n");
    }

    // ------------------------------------------------------------------------------------
    void displayWinnerTeam(Innings i1, Innings i2) {
        if (winnerTeam == battingTeam) {
            System.out.println("\n\t\t\t\t====================== WINNER TEAM ======================\n");
            System.out.println("\t\t\t\tWinner Team is \"" + battingTeam.teamName + "\" and they won by "
                    + (10 - bowlingTeam.teamWickets) + " wickets.\n");
            System.out.println("\t\t\t\t=========================================================\n");
        } else if (winnerTeam == bowlingTeam) {
            System.out.println("\n\t\t\t\t====================== WINNER TEAM ======================\n");
            System.out.println("\t\t\t\tWinner Team is \"" + bowlingTeam.teamName + "\" and they won by "
                    + (bowlingTeam.teamRuns - battingTeam.teamRuns) + " wickets.\n");
            System.out.println("\t\t\t\t=========================================================\n");
        } else {
            System.out.println("\n\t\t\t\t====================== MATCH TIED ======================\n");
            System.out.println("\t\t\t\t=========================================================\n");
        }
    }

    // ------------------------------------------------------------------------------------
    Players checkPlayer(Team t) {
        Players found = new Players();
        for (int i = 0; i < t.teamPlayers.length; i++) {
            System.out.println((i + 1) + " ->> " + t.teamPlayers[i].playerName);
        }

        boolean match = false;
        while (!match) {
            int check0 = 0;
            boolean check1 = true;
            while (check1) {
                if (sc.hasNextInt()) {
                    check0 = sc.nextInt();
                    if (check0 > 0 && check0 < 12) {
                        check1 = false;
                    } else {
                        System.out.println("---------------------------------------------");
                        System.out.println("Invalid Input , Please re-enter again :");
                        System.out.println("---------------------------------------------");
                    }
                } else {
                    System.out.println("---------------------------------------------");
                    System.out.println("Invalid Input , Please re-enter again :");
                    System.out.println("---------------------------------------------");
                    sc.next();
                }
            }
            for (int i = 0; i < t.teamPlayers.length; i++) {
                if (check0 == (t.teamPlayers[i].playerID)) {
                    found = t.teamPlayers[i];
                    match = true;
                }
            }
            if (!match) {
                System.out.println("---------------------------------------------");
                System.out.println("Invalid Input,Please re-enter:");
                System.out.println("---------------------------------------------");
            }
        }
        found.bowledOver = true;
        return found;
    }
}

// ------------------------------------------------------------------------------------
class Cricket_Scoreboard_System {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "\n\t\t\t\t>>>>>>>>>>>>>>>>>>>>>> WELCOME TO THE CRICKET SCOREBOARD SYSTEM <<<<<<<<<<<<<<<<<<<<<<\n");
        boolean checkExitProgram = false;
        while (!checkExitProgram) {
            Team t1 = new Team();
            t1.teamNumber = 1;

            Team t2 = new Team();
            t2.teamNumber = 2;

            System.out.println("Do you want to enter Teams manually?\nEnter YES or NO :");

            boolean check1 = true;
            while (check1) {
                String checkTeamSettingManual = sc.next();
                if (checkTeamSettingManual.equalsIgnoreCase("yes")) {
                    System.out.println("---------------------------------------------");
                    t1.setTeamAndPlayersAuto();
                    System.out.println("Enter team name for Team 1 :");
                    t1.editTeamDetails();
                    t1.editPlayerDetails();
                    System.out.println("---------------------------------------------");
                    t2.setTeamAndPlayersAuto();
                    System.out.println("\nEnter team name for Team 2 :");
                    t2.editTeamDetails();
                    t2.editPlayerDetails();

                    System.out.println("Editing of Teams completed successfully.");
                    System.out.println("---------------------------------------------");
                    check1 = false;
                } else if (checkTeamSettingManual.equalsIgnoreCase("no")) {
                    System.out.println("---------------------------------------------");
                    System.out.println("Okay , setting team details automatically.");
                    t1.setTeamAndPlayersAuto();
                    t2.setTeamAndPlayersAuto();
                    System.out.println("---------------------------------------------");
                    check1 = false;
                } else {
                    System.out.println("---------------------------------------------");
                    System.out.println("Invalid input , Please Enter YES or NO only :");
                    System.out.println("---------------------------------------------");
                }
            }

            System.out.println("Enter number of innings , you can Enter 1 or 2 :");
            int numInnings = 0;

            boolean check2 = true;
            while (check2) {
                if (sc.hasNextInt()) {
                    numInnings = sc.nextInt();
                    if (numInnings > 0 && numInnings < 3) {
                        check2 = false;
                        System.out.println("---------------------------------------------");
                    } else {
                        System.out.println("---------------------------------------------");
                        System.out.println("Invalid input , Please enter 1 or 2 only :");
                        System.out.println("---------------------------------------------");
                    }
                } else {
                    System.out.println("---------------------------------------------");
                    System.out.println("Invalid input , Please enter 1 or 2 only :");
                    System.out.println("---------------------------------------------");
                    sc.next();
                }
            }

            if (numInnings == 1) {
                System.out.println("Enter number of overs :");
                int numOvers = 0;
                boolean check3 = true;
                while (check3) {
                    if (sc.hasNextInt()) {
                        numOvers = sc.nextInt();
                        if (numOvers > 0) {
                            check3 = false;
                            System.out.println("---------------------------------------------");
                        } else {
                            System.out.println("---------------------------------------------");
                            System.out.println("Invalid input , Please re-enter number of overs :");
                            System.out.println("---------------------------------------------");
                        }
                    } else {
                        System.out.println("---------------------------------------------");
                        System.out.println("Invalid input , Please re-enter number of overs :");
                        System.out.println("---------------------------------------------");
                        sc.next();
                    }
                }

                System.out.println("Enter number of max overs that can be bowled by a bowler :");
                int numMaxOvers = 0;
                boolean check4 = true;
                while (check4) {
                    if (sc.hasNextInt()) {
                        numMaxOvers = sc.nextInt();
                        if (numMaxOvers > 0 && numMaxOvers <= numOvers) {
                            System.out.println("---------------------------------------------");
                            check4 = false;
                        } else {
                            System.out.println("---------------------------------------------");
                            System.out.println("Invalid input , Please re-enter number of overs :");
                            System.out.println("---------------------------------------------");
                        }
                    } else {
                        System.out.println("---------------------------------------------");
                        System.out.println("Invalid input , Please re-enter number of overs :");
                        System.out.println("---------------------------------------------");
                        sc.next();
                    }
                }

                Innings i = new Innings(numOvers, numMaxOvers);

                System.out.println("\n\t\t\t\t====================== STARTING THE INNINGS ======================\n");

                System.out.println("Do you want to perform toss manually?\nEnter YES or NO :");
                boolean check5 = true;
                while (check5) {
                    String checkTossManual = sc.next();
                    if (checkTossManual.equalsIgnoreCase("yes")) {
                        boolean check6 = true;
                        System.out.println("---------------------------------------------");
                        System.out.println("Enter batting team accordingly :\n" +
                                "1 ->> " + t1.teamName + "\n2 ->> " + t2.teamName);
                        while (check6) {
                            String battingTeam = sc.next();
                            if (battingTeam.equals("1")) {
                                i.battingTeam = t1;
                                i.bowlingTeam = t2;
                                System.out.println("---------------------------------------------");
                                System.out.println("Batting Team = " + i.battingTeam.teamName);
                                System.out.println("---------------------------------------------");
                                System.out.println("Bowling Team = " + i.bowlingTeam.teamName);
                                System.out.println("---------------------------------------------");
                                check6 = false;
                            } else if (battingTeam.equals("2")) {
                                i.battingTeam = t2;
                                i.bowlingTeam = t1;
                                System.out.println("---------------------------------------------");
                                System.out.println("Batting Team = " + i.battingTeam.teamName);
                                System.out.println("---------------------------------------------");
                                System.out.println("Bowling Team = " + i.bowlingTeam.teamName);
                                System.out.println("---------------------------------------------");
                                check6 = false;
                            } else {
                                System.out.println("---------------------------------------------");
                                System.out.println("Invalid Input , Please enter 1 or 2 only :");
                                System.out.println("---------------------------------------------");
                            }
                        }
                        check5 = false;
                    } else if (checkTossManual.equalsIgnoreCase("no")) {
                        System.out.println("---------------------------------------------");
                        System.out.println("Okay , performing toss automatically.");
                        System.out.println("---------------------------------------------");
                        i.toss(t1, t2);
                        check5 = false;
                    } else {
                        System.out.println("---------------------------------------------");
                        System.out.println("Invalid input , Please Enter YES or NO only :");
                        System.out.println("---------------------------------------------");
                    }
                }

                System.out.println("\n\t\t\t\t====================== STARTING SCORE INPUT ======================\n");

                i.startInnings();
                i.calculateScore();

                boolean exitDisplayScore = false;
                while (!exitDisplayScore) {
                    System.out.println("Which of the following scores you want , Enter number accordingly." +
                            "\n1 ->> Overall Score\n2 ->> Batting team Score\n3 ->> Bowling team Score\n4 ->> All of the above"
                            +
                            "\n5 ->> end this display function.");
                    String displayScoreChoice = sc.next();
                    switch (displayScoreChoice) {
                        case "1":
                            i.displayOverallScore();
                            break;
                        case "2":
                            i.displayBattingScore();
                            break;
                        case "3":
                            i.displayBowlingScore();
                            break;
                        case "4":
                            i.displayOverallScore();
                            i.displayBattingScore();
                            i.displayBowlingScore();
                            break;
                        case "5":
                            System.out.println("---------------------------------------------");
                            System.out.println("Score display function ends here.");
                            System.out.println("---------------------------------------------");
                            exitDisplayScore = true;
                            break;
                        default:
                            System.out.println("---------------------------------------------");
                            System.out.println("Invalid Input , Please enter again :");
                            System.out.println("---------------------------------------------");
                    }
                }
            }

            else {
                System.out.println("Enter number of overs :");
                int numOvers = 0;
                boolean check7 = true;
                while (check7) {
                    if (sc.hasNextInt()) {
                        numOvers = sc.nextInt();
                        if (numOvers > 0) {
                            check7 = false;
                            System.out.println("---------------------------------------------");
                        } else {
                            System.out.println("---------------------------------------------");
                            System.out.println("Invalid input , Please re-enter number of overs :");
                            System.out.println("---------------------------------------------");
                        }
                    } else {
                        System.out.println("---------------------------------------------");
                        System.out.println("Invalid input , Please re-enter number of overs :");
                        System.out.println("---------------------------------------------");
                        sc.next();
                    }
                }

                System.out.println("Enter number of max overs that can be bowled by a bowler :");
                int numMaxOvers = 0;
                boolean check8 = true;
                while (check8) {
                    if (sc.hasNextInt()) {
                        numMaxOvers = sc.nextInt();
                        if (numMaxOvers > 0) {
                            check8 = false;
                            System.out.println("---------------------------------------------");
                        } else {
                            System.out.println("---------------------------------------------");
                            System.out.println("Invalid input , Please re-enter number of overs :");
                            System.out.println("---------------------------------------------");
                        }
                    } else {
                        System.out.println("---------------------------------------------");
                        System.out.println("Invalid input , Please re-enter number of overs :");
                        System.out.println("---------------------------------------------");
                        sc.next();
                    }
                }

                Innings i1 = new Innings(numOvers, numMaxOvers);
                System.out
                        .println("\n\t\t\t\t====================== STARTING THE INNINGS - 1 ======================\n");
                System.out.println("Do you want to perform toss manually?\nEnter YES or NO :");
                boolean check9 = true;
                while (check9) {
                    String checkTossManual = sc.next();
                    if (checkTossManual.equalsIgnoreCase("yes")) {
                        boolean check10 = true;
                        System.out.println("Enter batting team accordingly :\n" +
                                "1 ->> " + t1.teamName + "\n2 ->> " + t2.teamName);
                        while (check10) {
                            String battingTeam = sc.next();
                            if (battingTeam.equals("1")) {
                                i1.battingTeam = t1;
                                i1.bowlingTeam = t2;
                                System.out.println("---------------------------------------------");
                                System.out.println("Batting Team = " + i1.battingTeam.teamName);
                                System.out.println("---------------------------------------------");
                                System.out.println("Bowling Team = " + i1.bowlingTeam.teamName);
                                System.out.println("---------------------------------------------");
                                check10 = false;
                            } else if (battingTeam.equals("2")) {
                                i1.battingTeam = t2;
                                i1.bowlingTeam = t1;
                                System.out.println("---------------------------------------------");
                                System.out.println("Batting Team = " + i1.battingTeam.teamName);
                                System.out.println("---------------------------------------------");
                                System.out.println("Bowling Team = " + i1.bowlingTeam.teamName);
                                System.out.println("---------------------------------------------");
                                check10 = false;
                            } else {
                                System.out.println("---------------------------------------------");
                                System.out.println("Invalid Input , Please enter 1 or 2 only :");
                                System.out.println("---------------------------------------------");
                            }
                        }
                        check9 = false;
                    } else if (checkTossManual.equalsIgnoreCase("no")) {
                        System.out.println("---------------------------------------------");
                        System.out.println("Okay , performing toss automatically.");
                        System.out.println("---------------------------------------------");
                        i1.toss(t1, t2);
                        check9 = false;
                    } else {
                        System.out.println("---------------------------------------------");
                        System.out.println("Invalid input , Please Enter YES or NO only :");
                        System.out.println("---------------------------------------------");
                    }
                }
                System.out.println("\n\t\t\t\t====================== STARTING SCORE INPUT ======================\n");
                i1.startInnings();
                i1.calculateScore();

                Innings i2 = new Innings(numOvers, numMaxOvers);
                System.out
                        .println("\n\t\t\t\t====================== STARTING THE INNINGS - 2 ======================\n");
                i2.battingTeam = i1.bowlingTeam;
                i2.bowlingTeam = i1.battingTeam;
                System.out.println("---------------------------------------------");
                System.out.println("Batting Team = " + i2.battingTeam.teamName);
                System.out.println("---------------------------------------------");
                System.out.println("---------------------------------------------");
                System.out.println("Bowling Team = " + i2.bowlingTeam.teamName);
                System.out.println("---------------------------------------------");
                System.out.println("\n\t\t\t\t====================== STARTING SCORE INPUT ======================\n");
                i2.startInnings(i1);
                i2.calculateScore();

                boolean exitDisplayScore = false;
                while (!exitDisplayScore) {
                    System.out.println("Enter number for which of the following task you want to perform.\n" +
                            "Enter number accordingly :\n1 -->> Score function for Innings-1\n2 -->> Score function for Innings-2\n"
                            +
                            "3 -->> Winning Team Details\n4 -->> Stop this function.");
                    String inningsChoice = sc.next();
                    switch (inningsChoice) {
                        case "1":
                            System.out.println("---------------------------------------------");
                            boolean exitDisplayScore1 = false;
                            while (!exitDisplayScore1) {
                                System.out.println(
                                        "Which of the following scores you want for Innings-1 , Enter number accordingly."
                                                +
                                                "\n1 ->> Overall Score\n2 ->> Batting team Score\n3 ->> Bowling team Score\n4 ->> All of the above"
                                                +
                                                "\n5 ->> End Innings-1 score display function.");
                                String displayScoreChoice = sc.next();
                                switch (displayScoreChoice) {
                                    case "1":
                                        i1.displayOverallScore();
                                        break;
                                    case "2":
                                        i1.displayBattingScore();
                                        break;
                                    case "3":
                                        i1.displayBowlingScore();
                                        break;
                                    case "4":
                                        i1.displayOverallScore();
                                        i1.displayBattingScore();
                                        i1.displayBowlingScore();
                                        break;
                                    case "5":
                                        System.out.println("---------------------------------------------");
                                        System.out.println("Innings-1 Score display function ends here.");
                                        System.out.println("---------------------------------------------");
                                        exitDisplayScore1 = true;
                                        break;
                                    default:
                                        System.out.println("---------------------------------------------");
                                        System.out.println("Invalid Input , Please enter again :");
                                        System.out.println("---------------------------------------------");
                                }
                            }
                            break;

                        case "2":
                            boolean exitDisplayScore2 = false;
                            while (!exitDisplayScore2) {
                                System.out.println(
                                        "Which of the following scores you want for Innings-2 , Enter number accordingly."
                                                +
                                                "\n1 ->> Overall Score\n2 ->> Batting team Score\n3 ->> Bowling team Score\n4 ->> All of the above"
                                                +
                                                "\n5 ->> End Innings-2 score display function.");
                                String displayScoreChoice = sc.next();
                                switch (displayScoreChoice) {
                                    case "1":
                                        i2.displayOverallScore();
                                        break;
                                    case "2":
                                        i2.displayBattingScore();
                                        break;
                                    case "3":
                                        i2.displayBowlingScore();
                                        break;
                                    case "4":
                                        i2.displayOverallScore();
                                        i2.displayBattingScore();
                                        i2.displayBowlingScore();
                                        break;
                                    case "5":
                                        System.out.println("---------------------------------------------");
                                        System.out.println("Innings-2 Score display function ends here.");
                                        System.out.println("---------------------------------------------");
                                        exitDisplayScore2 = true;
                                        break;
                                    default:
                                        System.out.println("---------------------------------------------");
                                        System.out.println("Invalid Input , Please enter again :");
                                        System.out.println("---------------------------------------------");
                                }
                            }
                            break;

                        case "3":
                            i2.displayWinnerTeam(i1, i2);
                            break;

                        case "4":
                            System.out.println("---------------------------------------------");
                            System.out.println("The main score display function ends here.");
                            System.out.println("---------------------------------------------");
                            exitDisplayScore = true;
                            break;

                        default:
                            System.out.println("---------------------------------------------");
                            System.out.println("Invalid Input , Please enter again :");
                            System.out.println("---------------------------------------------");
                    }
                }
            }

            System.out.println("If you want to reuse this program then Enter 1 or if you want to stop then Enter 2:");

            boolean check11 = true;
            while (check11) {
                String programChoice = sc.next();
                if (programChoice.equals("1")) {
                    System.out.println(
                            "\n\t\t\t\t>>>>>>>>>>>>>>>>>>>>>> WELCOME TO THE  CRICKET SCOREBOARD SYSTEM <<<<<<<<<<<<<<<<<<<<<<\n");
                    check11 = false;
                } else if (programChoice.equals("2")) {
                    System.out.println(
                            "\n\t\t\t\t>>>>>>>>>>>>>>>>>>>>>> THANK YOU FOR USING THE CRICKET SCOREBOARD SYSTEM <<<<<<<<<<<<<<<<<<<<<<\n");
                    check11 = false;
                    checkExitProgram = true;
                } else {
                    System.out.println("---------------------------------------------");
                    System.out.println("Invalid Input , Please re-enter your choice :");
                    System.out.println("---------------------------------------------");
                }
            }
        }
        sc.close();
    }
}