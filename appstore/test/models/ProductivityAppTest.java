package models;

import models.Developer;
import models.EducationApp;
import models.Rating;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductivityAppTest {

    private ProductivityApp edAppBelowBoundary;
    private ProductivityApp edAppOnBoundary;
    private ProductivityApp edAppAboveBoundary;
    private ProductivityApp edAppInvalidData;
    private Developer developerLego = new Developer("Lego", "www.lego.com");
    private Developer developerSphero = new Developer("Sphero", "www.sphero.com");


    @BeforeEach
    void setUp() {
        //Validation: appSize(1-1000), appVersion(>=1.0), ageRating (0-18), appCost(>=0), level(1-10).
        edAppBelowBoundary = new ProductivityApp(developerLego, "WeDo", 1, 1.0, 0);
        edAppOnBoundary = new ProductivityApp(developerLego, "Spike", 1000, 2.0, 1.99);
        edAppAboveBoundary = new ProductivityApp(developerLego, "EV3", 1001, 3.5, 2.99);
        edAppInvalidData = new ProductivityApp(developerLego, "", -1, 0.0, -1.00);
    }

    @AfterEach
    void tearDown() {
        edAppBelowBoundary = edAppOnBoundary = edAppAboveBoundary = edAppInvalidData = null;
        developerLego = developerSphero = null;
    }

    @Nested
    class Getters {

        @Test
        void getDeveloper() {
            assertEquals(developerLego, edAppBelowBoundary.getDeveloper());
            assertEquals(developerLego, edAppOnBoundary.getDeveloper());
            assertEquals(developerLego, edAppAboveBoundary.getDeveloper());
            assertEquals(developerLego, edAppInvalidData.getDeveloper());
        }

        @Test
        void getAppName() {
            assertEquals("WeDo", edAppBelowBoundary.getAppName());
            assertEquals("Spike", edAppOnBoundary.getAppName());
            assertEquals("EV3", edAppAboveBoundary.getAppName());
            assertEquals("", edAppInvalidData.getAppName());
        }

        @Test
        void getAppSize() {
            assertEquals(1, edAppBelowBoundary.getAppSize());
            assertEquals(1000, edAppOnBoundary.getAppSize());
            assertEquals(1001, edAppAboveBoundary.getAppSize());
            assertEquals(0, edAppInvalidData.getAppSize());
        }

        @Test
        void getAppVersion() {
            assertEquals(1.0, edAppBelowBoundary.getAppVersion());
            assertEquals(2.0, edAppOnBoundary.getAppVersion());
            assertEquals(3.5, edAppAboveBoundary.getAppVersion());
            assertEquals(1.0, edAppInvalidData.getAppVersion());
        }

        @Test
        void getAppCost() {
            assertEquals(0, edAppBelowBoundary.getAppCost());
            assertEquals(1.99, edAppOnBoundary.getAppCost());
            assertEquals(2.99, edAppAboveBoundary.getAppCost());
            assertEquals(0, edAppInvalidData.getAppCost());
        }

    }

    @Nested
    class Setters {

        @Test
        void setDeveloper() {
            //no validation in models
            assertEquals(developerLego, edAppBelowBoundary.getDeveloper());
            edAppBelowBoundary.setDeveloper(developerSphero);
            assertEquals(developerSphero, edAppBelowBoundary.getDeveloper());
        }

        @Test
        void setAppName() {
            //no validation in models
            assertEquals("WeDo", edAppBelowBoundary.getAppName());
            edAppBelowBoundary.setAppName("Mindstorms");
            assertEquals("Mindstorms", edAppBelowBoundary.getAppName());
        }

        @Test
        void setAppSize() {
            //Validation: appSize(1-1000)
            assertEquals(1, edAppBelowBoundary.getAppSize());

            edAppBelowBoundary.setAppSize(1000);
            assertEquals(1000, edAppBelowBoundary.getAppSize()); //update

            edAppBelowBoundary.setAppSize(1001);
            assertEquals(1000, edAppBelowBoundary.getAppSize()); //no update

            edAppBelowBoundary.setAppSize(2);
            assertEquals(2, edAppBelowBoundary.getAppSize()); //update

            edAppBelowBoundary.setAppSize(0);
            assertEquals(2, edAppBelowBoundary.getAppSize()); //no update
        }

        @Test
        void setAppVersion() {
            //Validation: appVersion(>=1.0)
            assertEquals(1.0, edAppBelowBoundary.getAppVersion());

            edAppBelowBoundary.setAppVersion(2.0);
            assertEquals(2.0, edAppBelowBoundary.getAppVersion()); //update

            edAppBelowBoundary.setAppVersion(0.0);
            assertEquals(2.0, edAppBelowBoundary.getAppVersion()); //no update

            edAppBelowBoundary.setAppVersion(1.0);
            assertEquals(1.0, edAppBelowBoundary.getAppVersion()); //update
        }

        @Test
        void setAppCost() {
            //Validation: appCost(>=0)
            assertEquals(0.0, edAppBelowBoundary.getAppCost());

            edAppBelowBoundary.setAppCost(1.0);
            assertEquals(1.0, edAppBelowBoundary.getAppCost()); //update

            edAppBelowBoundary.setAppCost(-1);
            assertEquals(1.0, edAppBelowBoundary.getAppCost()); //no update

            edAppBelowBoundary.setAppCost(0.0);
            assertEquals(0.0, edAppBelowBoundary.getAppCost()); //update
        }
    }

    @Nested
    class ObjectStateMethods {

        @Test
        void appSummaryReturnsCorrectString() {
           ProductivityApp edApp = setupEducationAppWithRating(3, 4);
            String stringContents = edApp.appSummary();
            assertTrue(stringContents.contains(edApp.getAppName() + "(V" + edApp.getAppVersion()));
            assertTrue(stringContents.contains(edApp.getDeveloper().toString()));
            assertTrue(stringContents.contains(",€" + edApp.getAppCost()));
            assertTrue(stringContents.contains(".Rating: " + edApp.calculateRating()));
        }

        @Test
        void toStringReturnsCorrectString() {
         ProductivityApp edApp = setupEducationAppWithRating(3, 4);
            String stringContents = edApp.toString();

            assertTrue(stringContents.contains(edApp.getAppName()));
            assertTrue(stringContents.contains("(Version " + edApp.getAppVersion()));
            assertTrue(stringContents.contains(edApp.getDeveloper().toString()));
            assertTrue(stringContents.contains(edApp.getAppSize() + "MB"));
            assertTrue(stringContents.contains("Cost: " + edApp.getAppCost()));
            assertTrue(stringContents.contains("Ratings (" + edApp.calculateRating()));

        }

    }

    @Nested
    class RecommendedApp {

        @Test
        void appIsNotRecommendedWhenInAppCostIs99c() {
            //setting all conditions to true with ratings of 3 and 4 (i.e. 3.5)
           ProductivityApp edApp = setupEducationAppWithRating(3, 4);

            //now setting appCost to 0.99 so app should not be recommended now
            edApp.setAppCost(0.99);
            assertFalse(edApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenRatingIsLessThan3AndAHalf() {
            //setting all conditions to true with ratings of 3 and 3 (i.e. 3.0)
           ProductivityApp edApp = setupEducationAppWithRating(3, 3);
            //verifying recommended app returns false (rating not high enough
            assertFalse(edApp.isRecommendedApp());
        }

        @Test
        void appIsNotRecommendedWhenNoRatingsExist() {
            //setting all conditions to true with no ratings
          ProductivityApp edApp = new ProductivityApp(developerLego, "WeDo", 1,
                    1.0, 1.00);
            //verifying recommended app returns true
            assertFalse(edApp.isRecommendedApp());
        }

        @Test
        void appIsRecommendedWhenAllOfTheConditionsAreTrue() {
            //setting all conditions to true with ratings of 3 and 4 (i.e. 3.5)
            ProductivityApp edApp = setupEducationAppWithRating(3, 4);

            //verifying recommended app returns true
            assertFalse(edApp.isRecommendedApp());
        }

    }

    ProductivityApp setupEducationAppWithRating(int rating1, int rating2) {
        //setting all conditions to true
        ProductivityApp edApp = new ProductivityApp(developerLego, "WeDo", 1,
                1.0, 1.00);
        edApp.addRating(new Rating(rating1, "John Doe", "Very Good"));
        edApp.addRating(new Rating(rating2, "Jane Doe", "Excellent"));

        //verifying all conditions are true for a recommended educational app]
        assertEquals(4, edApp.getRatings().size());  //two ratings are added
        assertEquals(1.0, edApp.getAppCost(), 0.01);


        return edApp;
    }
}
