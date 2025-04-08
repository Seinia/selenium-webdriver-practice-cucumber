package service;

import model.CampusCourseTestData;
import util.TestDataReader;

public class TestDataCampusCourseService implements TestDataService<CampusCourseTestData> {
    @Override
    public CampusCourseTestData getTestDataFromProperties() {
        return new CampusCourseTestData(
                TestDataReader.getTestData("testdata.learnHomePageText"),
                TestDataReader.getTestData("testdata.campusHomePageText"),
                TestDataReader.getTestData("testdata.trainingPageText"),
                TestDataReader.getTestData("testdata.courseLocation"),
                TestDataReader.getTestData("testdata.courseSkill"),
                TestDataReader.getTestData("testdata.courseName"),
                TestDataReader.getTestData("testdata.loginPageText")
        );
    }
}