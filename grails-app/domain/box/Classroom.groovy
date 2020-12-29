package box

// import java.time.LocalDate

class Classroom {

    String className

    static belongsTo = [teacher: Teacher]
    

    static constraints = {
    }
}
