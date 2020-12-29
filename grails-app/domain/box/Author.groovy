package box

class Author {

    String titleTH
    String titleEng
    int age

    static hasMany = [books: BookAuthor, parents:Parent]
    
    static constraints = {
    }
}
