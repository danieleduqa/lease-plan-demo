package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.Matchers;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchStepDefinitions {

    @Given("he calls endpoint {string}")
    public void heCallsEndpoint(String url) {
        SerenityRest.given().get(url);
    }

    @When("he receives the results")
    public void heReceivesTheResults() {
        restAssuredThat(response -> response.statusCode(200));
    }

    @Then("he sees the results displayed for orange")
    public void heSeesTheResultsDisplayedForOrange() {
        List<String> titles = SerenityRest.lastResponse().jsonPath().getList("title");
        assertThat(titles, Matchers.not(Matchers.empty())); // assert that the list of titles is not empty

        for (String title : titles) {
            assertThat(title, Matchers.anyOf(Matchers.containsStringIgnoringCase("orange"),
                    Matchers.containsStringIgnoringCase("sinaas"),
                    Matchers.containsStringIgnoringCase("sinas"))); // assert that each title contains "orange" or "sinaas"/"sinas" from Dutch
        }
    }

    @Then("he sees the results displayed for apple")
    public void heSeesTheResultsDisplayedForApple() {
        List<String> titles = SerenityRest.lastResponse().jsonPath().getList("title");
        assertThat(titles, Matchers.not(Matchers.empty())); // assert that the list of titles is not empty

        for (String title : titles) {
            assertThat(title, Matchers.containsStringIgnoringCase("apple")); // assert that each title contains "apple" (case-insensitive)
        }
    }

    @Then("he does not see the results")
    public void he_Does_Not_See_The_Results() {
        restAssuredThat(response -> response.statusCode(404));
        restAssuredThat(response -> response.body("detail.error", Matchers.equalTo(true)));
    }

    @Then("he sees a 404 Not Found error")
    public void he_sees_a_404_Not_Found_error() {
        restAssuredThat(response -> response.statusCode(404));
        restAssuredThat(response -> response.body("detail", Matchers.equalTo("Not Found")));
    }

    @Then("he checks that all units are displayed in grams")
    public void heChecksThatAllUnitsAreDisplayedInGrams() {
        List<String> units = SerenityRest.lastResponse().jsonPath().getList("unit");
        assertThat(units, Matchers.not(Matchers.empty())); // assert that the list of units is not empty

        for (String unit : units) {
            assertThat(unit, Matchers.matchesRegex("\\b([0-9]|[1-9][0-9]|[1-4][0-9]{2}|500)\\s*g\\b")); // assert that each unit is between 0 and 500 grams
        }
    }
}
