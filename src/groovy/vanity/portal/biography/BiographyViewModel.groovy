package vanity.portal.biography

import vanity.celebrity.Celebrity

class BiographyViewModel {

    Map<String, Integer> counts

    List<Celebrity> celebrities

    public List<String> getLetters() {
        counts.findAll { it.value != 0 }.collect { it.key }
    }

}
