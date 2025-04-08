package service;

public class TestDataServiceDecorator<T> implements TestDataService<T> {
    protected final TestDataService<T> decoratedService;

    public TestDataServiceDecorator(TestDataService<T> decoratedService) {
        this.decoratedService = decoratedService;
    }

    @Override
    public T getTestDataFromProperties() {
        return decoratedService.getTestDataFromProperties();
    }
}
