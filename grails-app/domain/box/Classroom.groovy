package box


class Classroom {

    String className

    static belongsTo = [teacher: Teacher]

        static mapping = {
        id "desc"
    }
}
