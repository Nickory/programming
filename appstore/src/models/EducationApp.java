package models;

import java.util.List;

public class EducationApp extends App {

    public int getLevel() {
        if(level>=0&&level<=10) {
            return level;
        }
        return 0;
    }

    public void setLevel(int level) {
        if(level>=0&&level<=10) {
            this.level = level;
        }
    }
int level=0;

    public EducationApp(Developer developer, String appName, double appSize, double appVersion, double appCost, int level) {
        super(developer, appName, appSize, appVersion, appCost);
        this.level = level;
    }

    @Override
    public boolean isRecommendedApp() {
        if (this.getAppCost() >= 0.99 && this.calculateRating() >= 3.5 && this.getLevel() >= 3) {
            return true;
        }
        return false;
    }

    @Override
    public String appSummary() {

        return "level " + this.getLevel()+
         this.getAppName() + "(V" + this.getAppVersion()+
        this.getDeveloper().toString()+
        ",€" + this.getAppCost()+
        ".Rating: " + this.calculateRating();
    }

    @Override
    public String toString() {
        return this.getAppName()+
        "(Version " + this.getAppVersion()+
      this.getDeveloper().toString()+
        this.getAppSize() + "MB"+
        "Cost: " + this.getAppCost()+
        "Level: " +this.getLevel()+
        "Ratings (" +this.calculateRating();



    }
}
