package box

class BootStrap {

    def fixtureService
    // FixtureService fixtureService

    def init = { servletContext ->
        fixtureService.createTestData()
    }
    def destroy = {
    }
}
