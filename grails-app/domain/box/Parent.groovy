package box

import java.time.LocalDate

class Parent {


    String parentUser
    String parentPassword
    String parentTitle
    String parentName
    String parentSName
    String parentTel
    String parentAddress

    static belongsTo = [teacher: Teacher]

    static constraints = {
        parentUser blank: false, nullable: false
    }
}