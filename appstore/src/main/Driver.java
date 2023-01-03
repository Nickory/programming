package main;

import controllers.AppStoreAPI;
import controllers.DeveloperAPI;
import models.*;
import utils.ScannerInput;

import java.util.List;

public class Driver {

    //TODO Some skeleton code has been given to you.
    //     Familiarise yourself with the skeleton code...run the menu and then review the skeleton code.
    //     Then start working through the spec.

    private DeveloperAPI developerAPI = new DeveloperAPI();
    private AppStoreAPI appStoreAPI = new AppStoreAPI();

    public static void main(String[] args) {
        new Driver().start();
    }

    public void start() {
        loadAllData();
        runMainMenu();
    }

    private int mainMenu() {
        System.out.println("""
                 -------------App Store------------
                |  1) Developer - Management MENU  |
                |  2) App - Management MENU        |
                |  3) Reports MENU                 |
                |----------------------------------|
                |  4) Search                       |
                |  5) Sort                         |
                |----------------------------------|
                |  6) Recommended Apps             |
                |  7) Random App of the Day        |
                |  8) Simulate ratings             |
                |----------------------------------|
                |  20) Save all                    |
                |  21) Load all                    |
                |----------------------------------|
                |  0) Exit                         |
                 ----------------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runDeveloperMenu();
                case 2 -> runAppMenu();// TODO run the App Store Menu and the associated methods (your design here)
                case 3 -> runReportMenu();// TODO run the Reports Menu and the associated methods (your design here)
                case 4 -> searchAppsBySpecificCriteria();
                case 5 -> runSortMenu();
                case 6 -> runRecommendMenu();// TODO print the recommended apps
                case 7 -> runRandomMenu();// TODO print the random app of the day
                case 8 -> simulateRatings();
                case 20 -> saveAllData();
                case 21 -> loadAllData();
                default -> System.out.println("Invalid option entered: " + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    private void runRecommendMenu() {
        System.out.println(appStoreAPI.listAllRecommendedApps());
    }

    private void runRandomMenu() {
        System.out.println(appStoreAPI.randomApp().appSummary());
    }

    private void runSortMenu() {
        System.out.println("""
                 --------Sort Menu---------
                |   1) sort apps             |
                |   2) swap apps             |            
                |   0) RETURN to main menu   |
                 ----------------------------""");
        int option = ScannerInput.validNextInt("==>> ");
        switch (option) {
            // TODO Search methods below
            case 1 -> appStoreAPI.sortAppsByNameAscending();
            case 2 -> swapApps();
            default -> System.out.println("Invalid option");
        }
    }

    private void swapApps() {
        int indexA = Integer.parseInt(ScannerInput.validNextLine("Please enter one  app's index:"));
        int indexB = Integer.parseInt(ScannerInput.validNextLine("Please enter the other app's index:"));
        appStoreAPI.newSwapApps(indexA, indexB);
    }

    private void runAppMenu() {
        int option = appMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addEducationApp();
                case 2 -> addProductivityApp();
                case 3 -> addGameApp();
                case 4 -> System.out.println(appStoreAPI.listAllApps());
                case 5 -> System.out.println(appStoreAPI.listSummaryOfAllApps());
                case 6 -> updateApp();
                case 7 -> deleteApp();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = appMenu();
        }
    }

    private void deleteApp() {
        String appName = ScannerInput.validNextLine("Please enter the app name: ");
        if (appStoreAPI.removeApp(appName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }


    private void updateApp() {
        System.out.println(appStoreAPI.listAllApps());
        App app = readValidAppByName();
        if (app != null) {
            double appVersion = Double.parseDouble(ScannerInput.validNextLine("Please enter new app version: "));
            if (appStoreAPI.updateAppVersion(app.getAppName(), appVersion)) {
                System.out.println("App Version Updated");
            } else System.out.println("App Version NOT Updated");
        } else System.out.println("App name is NOT valid");
    }

    private void addEducationApp() {
        String appName = ScannerInput.validNextLine("Please enter the app name: ");
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            double appSize = Double.parseDouble(ScannerInput.validNextLine("Please enter the app size: "));
            double appCost = Double.parseDouble(ScannerInput.validNextLine("Please enter the app cost: "));
            double appVersion = Double.parseDouble(ScannerInput.validNextLine("Please enter the app version: "));
            int appLevel = (int) Double.parseDouble(ScannerInput.validNextLine("Please enter the app level: "));
            if (appStoreAPI.addApp(new EducationApp(developer, appName, appSize, appVersion, appCost, appLevel))) {
                System.out.println("Add successful");
            } else {
                System.out.println("Add not successful");
            }
        } else {
            System.out.println("Add the developer first");
        }
    }
    private void addProductivityApp() {
        String appName = ScannerInput.validNextLine("Please enter the app name: ");
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            double appSize = Double.parseDouble(ScannerInput.validNextLine("Please enter the app size: "));
            double appCost = Double.parseDouble(ScannerInput.validNextLine("Please enter the app cost: "));
            double appVersion = Double.parseDouble(ScannerInput.validNextLine("Please enter the app version: "));
            if (appStoreAPI.addApp(new ProductivityApp(developer, appName, appSize, appVersion, appCost))) {
                System.out.println("Add successful");
            } else {
                System.out.println("Add not successful");
            }
        } else {
            System.out.println("Add the developer first");
        }
    }
    private void addGameApp() {
        String appName = ScannerInput.validNextLine("Please enter the app name: ");
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            double appSize = Double.parseDouble(ScannerInput.validNextLine("Please enter the app size: "));
            double appCost = Double.parseDouble(ScannerInput.validNextLine("Please enter the app cost: "));
            double appVersion = Double.parseDouble(ScannerInput.validNextLine("Please enter the app version: "));
            boolean isMultiplayer = true;
            int temp = Integer.parseInt((ScannerInput.validNextLine("If it is a multiplayer game, please enter 1, if not, please enter 0")));
            if (temp == 1) {
                isMultiplayer = true;
            } else if (temp == 0) {
                isMultiplayer = false;
            }
            if (appStoreAPI.addApp(new GameApp(developer, appName, appSize, appVersion, appCost, isMultiplayer))) {
                System.out.println("Add successful");
            } else {
                System.out.println("Add not successful");
            }
        } else {
            System.out.println("Add the developer first");
        }
    }

    private int appMenu() {
        System.out.println("""
                 ----------App Menu----------
                |   1) Add Education app     |
                |   2) Add Productivity app  |
                |   3) Add Game app          |
                |   4) list app              |
                |   5) list app summary      |
                |   6) Update app            |
                |   7) Delete app            |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private void exitApp() {
        saveAllData();
        System.out.println("Exiting....");
        System.exit(0);
    }

    //--------------------------------------------------
    //  Developer Management - Menu Items
    //--------------------------------------------------
    private int developerMenu() {
        System.out.println("""
                 -------Developer Menu-------
                |   1) Add a developer       |
                |   2) List developer        |
                |   3) Update developer      |
                |   4) Delete developer      |
                |   0) RETURN to main menu   |
                 ----------------------------""");
        return ScannerInput.validNextInt("==>> ");
    }

    private int reportMenu() {
        System.out.println("""
                 ----------Report Menu------------
                |   1) Apps overview              |
                |   2) developers overview        |
                |   3) game apps overview         |
                |   4) eduction apps overview     |
                |   5) productivity apps overview |     
                |   0) RETURN to main menu        |
                 --------------------------------- """);
        return ScannerInput.validNextInt("==>> ");
    }

    private void runReportMenu() {
        int option = reportMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(appStoreAPI.listAllApps());
                case 2 -> System.out.println(developerAPI.listDevelopers());
                case 3-> System.out.println(appStoreAPI.listAllGameApps());
                case 4-> System.out.println(appStoreAPI.listAllEducationApps());
                case 5-> System.out.println(appStoreAPI.listAllProductivityApps());
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = reportMenu();
        }
    }

    private void newRunDeveloperMenu() {
        int option = developerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addDeveloper();
                case 2 -> System.out.println(developerAPI.listDevelopers());
                case 3 -> updateDeveloper();
                case 4 -> deleteDeveloper();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            break;
        }
    }

    private void runDeveloperMenu() {
        int option = developerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addDeveloper();
                case 2 -> System.out.println(developerAPI.listDevelopers());
                case 3 -> updateDeveloper();
                case 4 -> deleteDeveloper();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.validNextLine("\n Press the enter key to continue");
            option = developerMenu();
        }
    }

    private void addDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        String developerWebsite = ScannerInput.validNextLine("Please enter the developer website: ");

        if (developerAPI.addDeveloper(new Developer(developerName, developerWebsite))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private void updateDeveloper() {
        System.out.println(developerAPI.listDevelopers());
        Developer developer = readValidDeveloperByName();
        if (developer != null) {
            String developerWebsite = ScannerInput.validNextLine("Please enter new website: ");
            if (developerAPI.updateDeveloperWebsite(developer.getDeveloperName(), developerWebsite))
                System.out.println("Developer Website Updated");
            else System.out.println("Developer Website NOT Updated");
        } else System.out.println("Developer name is NOT valid");
    }

    private void deleteDeveloper() {
        String developerName = ScannerInput.validNextLine("Please enter the developer name: ");
        if (developerAPI.removeDeveloper(developerName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    private Developer readValidDeveloperByName() {
        String developerName = ScannerInput.validNextLine("Please enter the developer's name: ");
        if (developerAPI.isValidDeveloper(developerName)) {
            return developerAPI.getDeveloperByName(developerName);
        } else {
            return null;
        }
    }

    private App readValidAppByName() {
        String appName = ScannerInput.validNextLine("Please enter the app's name: ");
        if (appStoreAPI.isValidAppName(appName)) {
            return appStoreAPI.getAppByName(appName);
        } else {
            return null;
        }
    }

    //--------------------------------------------------
    // TODO UNCOMMENT THIS CODE as you start working through this class
    //--------------------------------------------------
    private void searchAppsBySpecificCriteria() {
        System.out.println("""
                What criteria would you like to search apps by:
                  1) App Name
                  2) Developer Name
                  3) Rating (all apps of that rating or above)
                  4）List all apps about
                  """);

        int option = ScannerInput.validNextInt("==>> ");
        switch (option) {
            // TODO Search methods below
            case 1 -> searchAppsByName();
            case 2 -> searchAppsByDeveloper(readValidDeveloperByName());
            case 3 -> searchAppsEqualOrAboveAStarRating();
            case 4 -> searchAppsAbout();
            default -> System.out.println("Invalid option");
        }
    }

    private void searchAppsAbout() {
        String appName = ScannerInput.validNextLine("Please enter the app name: ");
        System.out.println(appStoreAPI.listAllAppsByName(appName));
    }

    private void searchAppsEqualOrAboveAStarRating() {
        int rating = Integer.parseInt(ScannerInput.validNextLine("Please enter the Rating: "));
        System.out.println(appStoreAPI.listAllAppsAboveOrEqualAGivenStarRating(rating));
    }

    private void searchAppsByDeveloper(Developer readValidDeveloperByName) {
        System.out.println(appStoreAPI.listAllAppsByChosenDeveloper(readValidDeveloperByName));
    }

    private void searchAppsByName() {
        String appName = ScannerInput.validNextLine("Please enter the app name: ");
        System.out.println(appStoreAPI.getAppByName(appName));
    }


    //--------------------------------------------------
    // TODO UNCOMMENT THIS COMPLETED CODE as you start working through this class
    //--------------------------------------------------
    private void simulateRatings() {
        // simulate random ratings for all apps (to give data for recommended apps and reports etc).
        if (appStoreAPI.numberOfApps() > 0) {
            System.out.println("Simulating ratings...");
            appStoreAPI.simulateRatings();
            System.out.println(appStoreAPI.listSummaryOfAllApps());
        } else {
            System.out.println("No apps");
        }
    }


    //--------------------------------------------------
    //  Persistence Menu Items
    //--------------------------------------------------

    private void saveAllData() {
        try {
            System.out.println("Saving to file: " + appStoreAPI.fileName());
            appStoreAPI.save();
            developerAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
        // TODO try-catch to save the developers to XML file
        // TODO try-catch to save the apps in the store to XML file
    }

    private void loadAllData() {
        try {
            System.out.println("Loading from file: " + appStoreAPI.fileName());
            appStoreAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
        // TODO try-catch to load the developers from XML file
        // TODO try-catch to load the apps in the store from XML file
    }

}
