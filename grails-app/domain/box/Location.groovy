package box

class Location {
    String title
    String code
    String address
    static hasMany = [books: Book,parents:Parent]
    
    static constraints = {
    }

    static mapping = {
        address type: "text"
    }
}
