package box


class Classroom {

    String className

    static belongsTo = [teacher: Teacher]

    static constraints = {
    }
}
