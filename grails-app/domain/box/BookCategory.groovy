package box

class BookCategory {
    String title
    String code
    String description
    int borrowingDays
    double findRatePerDay

    static constraints = {
        title 'blank': false
    }
}
