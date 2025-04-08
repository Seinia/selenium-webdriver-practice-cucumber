package service;

public interface TestDataService<T> {
    T getTestDataFromProperties();

    static <T> T getTestDataFromProperties(TestDataService<T> service) {
        return service.getTestDataFromProperties();
    }
}
