package service;

import model.LearnCourseTestData;
import util.TestDataReader;

public class TestDataLearnCourseService implements TestDataService<LearnCourseTestData> {
    @Override
    public LearnCourseTestData getTestDataFromProperties() {
        return new LearnCourseTestData(
                TestDataReader.getTestData("testdata.learnHomePageText"),
                TestDataReader.getTestData("testdata.learnCatalogPageText"),
                TestDataReader.getTestData("testdata.learnTechnicalAndTechnologyPageText"),
                TestDataReader.getTestData("testdata.learnTechnicalAndTechnologyPageFilterCount"),
                TestDataReader.getTestData("testdata.learnLanguageText"),
                TestDataReader.getTestData("testdata.learnEstimatedEffortsText")
        );
    }
}