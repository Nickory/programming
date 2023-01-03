package models;

public class GameApp extends App {
    boolean isMultiplayer;

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }
public boolean getIsMultiplayer(){
        return  isMultiplayer;
}

    @Override
    public String toString() {
        return this.getAppName()+
                "(Version " + this.getAppVersion()+
                this.getDeveloper().toString()+
                this.getAppSize() + "MB"+
                "Cost: " + this.getAppCost()+
                "multiplayer"+this.getIsMultiplayer()+
                "Ratings (" +this.calculateRating();



    }

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public GameApp(Developer developer, String appName, double appSize, double appVersion, double appCost, boolean isMultiplayer) {
        super(developer, appName, appSize, appVersion, appCost);
        this.isMultiplayer = isMultiplayer;
    }

    @Override
    public boolean isRecommendedApp() {
        if ((this.isMultiplayer = true) &&(this.calculateRating() >= 4.0)) {
            return true;
        }
        return false;
    }
    @Override
    public String appSummary() {

        return "isMultiplayer " + this.getIsMultiplayer()+
                this.getAppName() + "(V" + this.getAppVersion()+
                this.getDeveloper().toString()+
                ",€" + this.getAppCost()+
                ".Rating: " + this.calculateRating();
    }
}
