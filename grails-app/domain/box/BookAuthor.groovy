package box

class BookAuthor {

    static belongsTo = [book: Book, author: Author, parent:Parent]

    static constraints = {
    }
}
