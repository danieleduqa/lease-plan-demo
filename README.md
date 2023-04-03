# Lease plan example

## Project installation

For the installation of the project you have to clone the repository's HTTPS or SSH.
```
git clone URL
```

## Running the tests

### Locally

1. Tests can be run from the `post_product.feature` file.
   - Tests can be run individually or all at once by using the specific buttons.
2. The entire suite can be run from the `TestRunner.java` file
   - Simply use the specific button for running the TestRunner.
3. The tests can also be run with `Maven` from the Maven panel or from the command line.
   - From the Maven panel expand the dropdown in order to get to Lifecycle commands. From there you can double-click either `test` or `verify` in order to run the tests.
   - In the command line type:
```
mvn test
```

## What was refactored

### Clean up

- Removed everything connected to `gradle`.
- Removed the `.m2` directory.
- Removed the `main/java` directory.
- Removed the `CarAPI.java` file.

### Changes in the framework

- Updated the annotation to `Given` for the method that calls the endpoint.
- Added a new `When` method for receiving the results with statusCode: 200.
- Update the methods `heSeesTheResultsDisplayedForOrange` and `heSeesTheResultsDisplayedForApple` in order to assert that all the titles contain orange or apple. Had come complications with orange because the Dutch language is also used.
- Added a `Then` method for 404 error response.
- Added the `heChecksThatAllUnitsAreDisplayedInGrams` method that verifies that all the units on `pasta` endpoint are in the range 0-500 grams.
- I split the big scenario into smaller scenarios to have more control.
- Updated the scenarios to match the `Given -> When -> Then` sequence.
- Created two new scenarios tagged with `@Positive` and `@Negative`.

### Other changes

I have added the project on `GitLab` and tried to implement a pipeline, but it kept failing saying that I should verify my identity by adding a credit card. I didn't want to do this at the moment.

I decided to do it as you suggested, so I created another repository on `GitHub`.

After that I had to add an `action/pipeline`, but noticed there is already a workflow `maven.yml` on the project.
I updated it a bit and added some settings, so we won't see all the logs when the `Maven build` was running and also for reporting.

## Conclusion

I tried to clean up the code as much as possible. I hope I didn't miss anything important.

The changes in the framework and the additional scenarios are at a minimal as I decided to keep it simple at a demo level. Of course, I could have done more complex scenarios, but didn't feel it was the purpose for this assignment.

I am sure there are other workarounds for pipelines on `GitLab`, but I think `Actions` on `GitHub` work as well.

I had some issues with the reporting tool with the test results of the pipeline test run.
I tried with surefire at first, as it is the default way of doing it, but had some difficulties because I was getting `Cannot resolve symbol 'report-only'` and `Cannot resolve symbol 'report'`.
After that I wanted to try with another plugin, but in the end I saw we have the `Serenity` report already on the repository and decided to use this one.
