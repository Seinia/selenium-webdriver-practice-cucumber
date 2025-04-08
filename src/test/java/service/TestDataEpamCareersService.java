package service;

import model.EpamCareersTestData;
import util.TestDataReader;

public class TestDataEpamCareersService implements TestDataService<EpamCareersTestData> {
    @Override
    public EpamCareersTestData getTestDataFromProperties() {
        return new EpamCareersTestData(
                TestDataReader.getTestData("testdata.epamCareersPageText"),
                TestDataReader.getTestData("testdata.epamJobSkill"),
                TestDataReader.getTestData("testdata.epamJobSpecialization"),
                TestDataReader.getTestData("testdata.epamJobLocation")
        );
    }
}
