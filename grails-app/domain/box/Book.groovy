package box

import java.time.LocalDate

class Book {
    String titleTH
    String titleEng
    String author
    double price
    LocalDate since

    boolean isRead = true

    static belongsTo = [category: BookCategory, location: Location]

    static hasMany = [authors: BookAuthor]

    static constraints = {
        titleTH blank: false, nullable: false
    }
}
