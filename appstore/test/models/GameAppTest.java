package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

    public class GameAppTest {

        private GameApp edAppBelowBoundary, edAppOnBoundary, edAppAboveBoundary, edAppInvalidData;
        private Developer developerLego = new Developer("MiHoYo", "www.mihoyo.com");
        private Developer developerSphero = new Developer("Sphero", "www.sphero.com");



        @BeforeEach
        void setUp() {
            //Validation: appSize(1-1000), appVersion(>=1.0), ageRating (0-18), appCost(>=0), level(1-10).
            edAppBelowBoundary = new GameApp(developerLego, "genshin impact", 14860, 3.4, 0, true);
            edAppOnBoundary = new GameApp(developerLego, "Muse dash", 2100, 2.0, 0, false);
            edAppAboveBoundary = new GameApp(developerLego, "Collapse III", 7530, 6.3, 0, true);
            edAppInvalidData = new GameApp(developerLego, "Arknights", -1, 0.0, -1.00, false);
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
        }
        @Test
        void getAppName() {
            assertEquals("genshin impact", edAppBelowBoundary.getAppName());
            assertEquals("Muse dash", edAppOnBoundary.getAppName());
            assertEquals("Collapse III", edAppAboveBoundary.getAppName());
            assertEquals("Arknights", edAppInvalidData.getAppName());
        }

        @Test
        void getAppSize() {
            assertEquals(14860, edAppBelowBoundary.getAppSize());
            assertEquals(2100, edAppOnBoundary.getAppSize());
            assertEquals(7530, edAppAboveBoundary.getAppSize());
            assertEquals(0, edAppInvalidData.getAppSize());
        }

        @Test
        void getAppVersion() {
            assertEquals(3.4, edAppBelowBoundary.getAppVersion());
            assertEquals(2.0, edAppOnBoundary.getAppVersion());
            assertEquals(6.3, edAppAboveBoundary.getAppVersion());
            assertEquals(1.0, edAppInvalidData.getAppVersion());
        }

        @Test
        void getAppCost() {
            assertEquals(0, edAppBelowBoundary.getAppCost());
            assertEquals(0, edAppOnBoundary.getAppCost());
            assertEquals(0, edAppAboveBoundary.getAppCost());
            assertEquals(0, edAppInvalidData.getAppCost());
        }

        @Test
        void isMultiplayer(){
            assertEquals(true, edAppBelowBoundary.isMultiplayer());
            assertEquals(false, edAppOnBoundary.isMultiplayer());
            assertEquals(true, edAppAboveBoundary.isMultiplayer());
            assertEquals(false, edAppInvalidData.isMultiplayer());
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
                assertEquals("genshin impact", edAppBelowBoundary.getAppName());
                edAppBelowBoundary.setAppName("Mindstorms");
                assertEquals("Mindstorms", edAppBelowBoundary.getAppName());
            }

            @Test
            void setAppSize() {
                //Validation: appSize(1-1000)

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

                edAppBelowBoundary.setAppCost(1.0);
                assertEquals(1.0, edAppBelowBoundary.getAppCost()); //update

                edAppBelowBoundary.setAppCost(-1);
                assertEquals(1.0, edAppBelowBoundary.getAppCost()); //no update

                edAppBelowBoundary.setAppCost(0.0);
                assertEquals(0.0, edAppBelowBoundary.getAppCost()); //update
            }

            @Test
            void isMultiplayer() {
                //Validation: appCost(>=0)

                edAppBelowBoundary.setMultiplayer(true);
                assertEquals(true, edAppBelowBoundary.getIsMultiplayer()); //update

                edAppBelowBoundary.setMultiplayer(false);
                assertEquals(false, edAppBelowBoundary.getIsMultiplayer()); //no update

                edAppBelowBoundary.setMultiplayer(true);
                assertEquals(true, edAppBelowBoundary.getIsMultiplayer()); //update
            }

        }
        @Nested
        class ObjectStateMethods {

            @Test
            void appSummaryReturnsCorrectString() {
                GameApp edApp = setupEducationAppWithRating(3, 4);
                String stringContents = edApp.appSummary();

                assertTrue(stringContents.contains("isMultiplayer " + edApp.getIsMultiplayer()));
                assertTrue(stringContents.contains(edApp.getAppName() + "(V" + edApp.getAppVersion()));
                assertTrue(stringContents.contains(edApp.getDeveloper().toString()));
                assertTrue(stringContents.contains(",€" + edApp.getAppCost()));
                assertTrue(stringContents.contains(".Rating: " + edApp.calculateRating()));
            }

            @Test
            void toStringReturnsCorrectString() {
              GameApp edApp = setupEducationAppWithRating(3, 4);
                String stringContents = edApp.toString();

                assertTrue(stringContents.contains(edApp.getAppName()));
                assertTrue(stringContents.contains("(Version " + edApp.getAppVersion()));
                assertTrue(stringContents.contains(edApp.getDeveloper().toString()));
                assertTrue(stringContents.contains(edApp.getAppSize() + "MB"));
                assertTrue(stringContents.contains("Cost: " + edApp.getAppCost()));
                assertTrue(stringContents.contains("multiplayer"+edApp.getIsMultiplayer()));
                assertTrue(stringContents.contains("Ratings (" + edApp.calculateRating()));

            }

        }

        @Nested
        class RecommendedApp {

            @Test
            void appIsNotRecommendedWhenInAppCostIs99c() {
                //setting all conditions to true with ratings of 3 and 4 (i.e. 3.5)
                GameApp edApp = setupEducationAppWithRating(3, 4);

                //now setting appCost to 0.99 so app should not be recommended now
                edApp.setAppCost(0.99);
                assertFalse(edApp.isRecommendedApp());
            }

            @Test
            void appIsNotRecommendedWhenRatingIsLessThan3AndAHalf() {
                //setting all conditions to true with ratings of 3 and 3 (i.e. 3.0)
                GameApp edApp = setupEducationAppWithRating(3, 3);
                //verifying recommended app returns false (rating not high enough
                assertFalse(edApp.isRecommendedApp());
            }

            @Test
            void appIsNotRecommendedWhenNoRatingsExist() {
                //setting all conditions to true with no ratings
                GameApp edApp = new GameApp(developerLego,"WeDo", 1,
                        1.0, 1.00,  true);
                //verifying recommended app returns true
                assertFalse(edApp.isRecommendedApp());
            }

            @Test
            void appIsRecommendedWhenAllOfTheConditionsAreTrue() {
                //setting all conditions to true with ratings of 3 and 4 (i.e. 3.5)
               GameApp edApp = setupEducationAppWithRating(3, 4);

                //verifying recommended app returns true
                assertFalse(edApp.isRecommendedApp());
            }

        }

        GameApp setupEducationAppWithRating(int rating1, int rating2) {
            //setting all conditions to true
            GameApp edApp = new GameApp(developerLego, "WeDo", 1,
                    1.0, 1.00,true );
            edApp.addRating(new Rating(rating1, "John Doe", "Very Good"));
            edApp.addRating(new Rating(rating2, "Jane Doe", "Excellent"));

            //verifying all conditions are true for a recommended educational app]
            assertEquals(4, edApp.getRatings().size());  //two ratings are added
            assertEquals(1.0, edApp.getAppCost(), 0.01);
            assertEquals(true, edApp.getIsMultiplayer());

            return edApp;
        }
    }
