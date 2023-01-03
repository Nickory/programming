package models;

import java.util.ArrayList;
import java.util.List;

public abstract class App {

    Developer developer;
    String appName = "No app name";

    protected App() {
    }

    public App(Developer developer, String appName, double appSize, double appVersion, double appCost) {
        this.developer = developer;
        this.appName = appName;
        this.appSize = appSize;
        this.appVersion = appVersion;
        this.appCost = appCost;
    }

    public String toString() {
        return "App{" +
                "appName='" + appName + '\'' +
                '}';
    }

    double appSize = 0;
    double appVersion = 1.0;
    double appCost = 0;

    public List<Rating> ratings = new ArrayList<Rating>();

    public App(Developer developer, String appName, double appSize, double appVersion, double appCost, List<Rating> ratings) {
        this.developer = developer;
        this.appName = appName;
        this.appSize = appSize;
        this.appVersion = appVersion;
        this.appCost = appCost;
        this.ratings = ratings;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public double getAppSize() {
        if(appSize>=0) {
            return appSize;
        }
        return 0;
    }

    public void setAppSize(double appSize) {
        if ((1 <= appSize) && appSize <= 1000) {
            this.appSize = appSize;
        }
    }

    public double getAppVersion() {
        if(appVersion>=1.0) {
            return appVersion;
        }
        return 1.0;
    }

    public void setAppVersion(double appVersion) {
        if (appVersion >= 1.0) {
            this.appVersion = appVersion;
        }
    }

    public double getAppCost() {
        if(appCost>=0) {
            return appCost;
        }
        return 0;
    }

    public void setAppCost(double appCost) {
        if (appCost >= 0) {
            this.appCost = appCost;
        }
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public boolean addRating(Rating Rating) {
        ratings.add(Rating);
        return ratings.add(Rating);
    }

    public String appSummary() {
        this.getAppName();
        this.getAppVersion();
        this.getDeveloper();
        this.getAppCost();
        this.getRatings();
        String appSummary = getAppName() + " (V" + getAppVersion() + ") by " + getDeveloper().toString()+ ",€" + getAppCost() + ". Rating " + getRatings();
        return appSummary;
    }

    public String listRatings() {
        String listRating = new String();
        if (ratings.size() == 0) {
            listRating = new String("No ratings added yet");
        } else if (ratings.size() > 0) {
            for (int i = 0; i < ratings.size(); i++) {

                listRating = listRating + ratings.get(i);

            }
        }

        return listRating;
    }

    public int calculateRating() {
        int arrangeNumber = 0;
        int totalNumber = 0;
        if (ratings.size() == 0) {
            arrangeNumber = 0;
        } else if (ratings.size()!=0) {
            for (int i = 0; i < ratings.size(); i++) {
                Rating temp = ratings.get(i);
                int j = 0;
                if (temp.getNumberOfStars() > 0) {
                    j = 0;
                    j++;
                    totalNumber = temp.getNumberOfStars() + totalNumber;
                }
                arrangeNumber = totalNumber / ratings.size();

            }
        }
        return arrangeNumber;
    }

    public abstract boolean isRecommendedApp();
}
