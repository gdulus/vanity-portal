package vanity.portal.i18n

class MessageDto {

    final String code

    final String message

    final Long foundDate

    MessageDto(String code, String message) {
        this.code = code
        this.message = message
        this.foundDate = new Date().time
    }
}
