package box

class Student {

    String studentTitle
    String studentName
    String studentSname
    String studentNickname
    String studentSex
    
    static belongsTo = [classroom: Classroom ,parent: Parent]
    
    static constraints = {
        
    }
}
