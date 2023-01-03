package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.random;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static utils.RatingUtility.generateRandomRating;

public class AppStoreAPI implements ISerializer {
    public List<App> apps = new ArrayList<App>();

    //TODO refer to the spec and add in the required methods here (make note of which methods are given to you first!)

    //---------------------
    // Method to simulate ratings (using the RatingUtility).
    // This will be called from the Driver (see skeleton code)
    //---------------------
    // TODO UNCOMMENT THIS COMPLETED method as you start working through this class
    //---------------------

    public void simulateRatings() {
        for (App app : apps) {
            app.addRating(generateRandomRating());
        }
    }
public int numberOfApps(){
        return apps.size();
}
    //---------------------
    // Validation methods
    //---------------------
    // TODO UNCOMMENT THIS COMPlETED method as you start working through this class
    //---------------------

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < apps.size());
    }

    //---------------------
    // Persistence methods
    //---------------------
    // TODO UNCOMMENT THIS COMPLETED CODE block as you start working through this class
    //---------------------

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{App.class, EducationApp.class, GameApp.class, ProductivityApp.class, Rating.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        apps = (List<App>) in.readObject();
        in.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(apps);
        out.close();
    }

    @Override
    public String fileName() {
        return "apps.xml";
    }

    public boolean addApp(App App) {
        apps.add(App);
        return apps.add(App);
    }

    public App deleteAppByIndex(int index) {
        if ((0 <= index) && (index <= apps.size())) {
            apps.remove(index);

            return apps.get(index);
        }
        return null;

    }

    public App getAppByIndex(int index) {
        for (int i = 0; i < apps.size(); i++) {
            App tempApp = apps.get(i);
            if (tempApp.equals(apps.get(index))) {
                return apps.get(i);
            }
        }
        return null;
    }

    public App getAppByName(String appName) {
        for (int i = 0; i < apps.size(); i++) {
            App tempApp = apps.get(i);
            String tempAppName = tempApp.getAppName();
            if (tempAppName.equals(appName)) {
                return apps.get(i);
            }
        }
        return null;
    }

    public String listAllApps() {
        String tempAllApps = "";
        for (int i = 0; i < apps.size(); i++) {
            App tempApp = apps.get(i);
            tempAllApps +=  +apps.indexOf(tempApp)+".name:"+tempApp.getAppName();
        }
        if (tempAllApps.isEmpty()) {
            return "No apps";
        } else {
            return tempAllApps;
        }
    }

    public String listSummaryOfAllApps() {
        String listSummaryOfAllApps = "";
        for (int i = 0; i < apps.size(); i++) {
            App tempApp = apps.get(i);
            listSummaryOfAllApps = listSummaryOfAllApps + tempApp.toString();
        }
        if (listSummaryOfAllApps.isEmpty()) {
            return "No apps";
        } else {
            return listSummaryOfAllApps;
        }
    }

    public String listAllGameApps() {
        String listAllGameApps = "";
        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getClass() == GameApp.class) {
                App tempApp = apps.get(i);
                listAllGameApps = listAllGameApps + tempApp.getAppCost() + tempApp.getAppVersion() + tempApp.getAppSize() + tempApp.getDeveloper() + tempApp.getAppName() + tempApp.getRatings();
            }
        }
        if (listAllGameApps.isEmpty()) {
            return "No Game apps";
        } else {
            return listAllGameApps;
        }
    }

    public String listAllEducationApps() {
        String listAllEducationApps ="";
        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getClass() == EducationApp.class) {
                App tempApp = apps.get(i);
                listAllEducationApps = listAllEducationApps + tempApp.getAppCost() + tempApp.getAppVersion() + tempApp.getAppSize() + tempApp.getDeveloper() + tempApp.getAppName() + tempApp.getRatings();
            }
        }
        if (listAllEducationApps.isEmpty()) {
            return "No Education apps";
        } else {
            return listAllEducationApps;
        }
    }

    public String listAllProductivityApps() {
        String listAllProductivityApps = "";
        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getClass() == GameApp.class) {
                App tempApp = apps.get(i);
                listAllProductivityApps = listAllProductivityApps + tempApp.getAppCost() + tempApp.getAppVersion() + tempApp.getAppSize() + tempApp.getDeveloper() + tempApp.getAppName() + tempApp.getRatings();
            }

        }
        if (listAllProductivityApps.isEmpty()) {
            return "No apps for name appName exists";
        } else {
            return listAllProductivityApps;
        }
    }

    public String listAllAppsByName(String name) {
        String allByName = "";
        for (int i = 0; i < apps.size(); i++) {
            if ((apps.get(i).getAppName()).contains(name)) {
                App tempApp = apps.get(i);
                allByName = null;
                allByName = allByName + apps.get(i).toString();
            }
        }
        if (allByName.isEmpty()) {
            return "No apps for name appName exists";
        } else {
            return allByName;
        }
    }

    public String listAllAppsAboveOrEqualAGivenStarRating(int rating) {
        String appList = "";
        for (int i = 0; i < apps.size(); i++) {
            for (int j = 0; j < apps.get(i).getRatings().size(); j++)
                if (apps.get(i).getRatings().get(j).getNumberOfStars() >= rating) {
                    App tempApp = apps.get(i);
                    if (!appList.contains(tempApp.getAppName())) {
                        appList = appList + tempApp.getAppName();
                    }
                }
        }
        if (appList.isEmpty()) {
            return "No apps have a rating of " + rating + "or above";
        } else {
            return appList;
        }
    }

    public String listAllRecommendedApps() {
        String allRecommendedApps = "";
        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).isRecommendedApp()) {
                allRecommendedApps = allRecommendedApps + apps.get(i).appSummary();

            }
        }
        if (allRecommendedApps.isEmpty()) {
            return "No recommended apps";
        } else {
            return allRecommendedApps;
        }
    }

    public String listAllAppsByChosenDeveloper(Developer developer) {
        String allDeveloperApps = "";
        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getDeveloper() == developer) {
                allDeveloperApps = allDeveloperApps + apps.get(i);
            }
        }
        if (allDeveloperApps.isEmpty()) {
            return "No apps for developer :" + developer;
        } else {
            return allDeveloperApps;
        }
    }

    public int numberOfAppsByChosenDeveloper(Developer developer) {
        int j = 0;
        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getDeveloper() == developer) {
                j++;
            }
        }
        return j;
    }

    public App randomApp() {
        if (apps.size() > 0) {
            return apps.get((int) (random() * apps.size()));
        }
        return null;
    }

    public boolean isValidAppName(String appName) {
        for (int i = 0; i < apps.size(); i++) {
            if (apps.get(i).getAppName() == appName) {
                return true;
            }
        }
        return false;
    }

    public void sortAppsByNameAscending() {
        for (int i = apps.size() - 1; i >= 0; i--) {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++) {
                if (apps.get(j).getAppName().compareTo(apps.get(highestIndex).getAppName()) > 0) {
                    highestIndex = j;
                }
            }
            swapApps((ArrayList<App>) apps, i, highestIndex);
        }
    }



    public void swapApps(ArrayList<App> apps, int i, int j) {
        App tempA = apps.get(i);
        App tempB = apps.get(j);
        apps.set(i, tempB);
        apps.set(j, tempA);
    }
    public void newSwapApps(int i, int j) {
        App tempA = apps.get(i);
        App tempB = apps.get(j);
        apps.set(i, tempB);
        apps.set(j, tempA);
    }

    public boolean updateAppVersion(String appName, double appVersion){
        if (isValidAppName(appName)){
            App appToUpdate = getAppByName(appName);
           appToUpdate.setAppVersion(appVersion);
            return true;
        }
        return false;
    }
    public App removeApp(String appName){
        int index = retrieveAppIndex(appName);
        if (index != -1) {
            return apps.remove(index);
        }
        return null;
    }
    public int retrieveAppIndex(String appName){
        for (App app: apps){
            if (app.getAppName().equalsIgnoreCase(appName)){
                return apps.indexOf(app);
            }
        }
        return -1;
    }
}






