package models;

public class ProductivityApp extends App {
    @Override
    public String toString() {
        return this.getAppName()+
                "(Version " + this.getAppVersion()+
                this.getDeveloper().toString()+
                this.getAppSize() + "MB"+
                "Cost: " + this.getAppCost()+
                "Ratings (" +this.calculateRating();
    }

    public ProductivityApp(Developer developer, String appName, double appSize, double appVersion, double appCost) {
        this.developer = developer;
        this.appName = appName;
        this.appSize = appSize;
        this.appVersion = appVersion;
        this.appCost = appCost;
    }
    @Override
    public String appSummary() {

        return this.getAppName() + "(V" + this.getAppVersion()+
                this.getDeveloper().toString()+
                ",€" + this.getAppCost()+
                ".Rating: " + this.calculateRating();
    }
    @Override
    public boolean isRecommendedApp() {
        if (this.getAppCost() >= 1.99 && this.calculateRating() >= 3.0) {
            return true;
        }
        return false;
    }
}
