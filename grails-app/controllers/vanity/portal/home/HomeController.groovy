package vanity.portal.home

class HomeController {

    def homeService

    def main() {
        return [viewModel: homeService.buildMainModel()]
    }
}
