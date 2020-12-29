package box

import java.time.LocalDate

class Teacher {

    String teacherUser
    String teacherPassword
    String teacherTitle
    String teacherName
    String teacherSname
    String teacherAddress
    String teacherTel

    static hasMany = [parents:Parent]

    static constraints = {
        teacherUser blank: false, nullable: false
    }

}

