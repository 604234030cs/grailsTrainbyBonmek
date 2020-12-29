package box


import grails.rest.*
import grails.converters.*

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import java.time.LocalDate

class StudentController {
	static responseFormats = ['json', 'xml']
	
    def index() {
        def result  = [valid: false]
        def filter = new StudentFilter()

        bindData(filter, params)

                filter = filter.properties

        def studentList = Student.where{}.build {

            if(filter.studentId){
                eq 'id',filter.studentId
            }

            if(filter.title){
                ilike 'studentTitle', "%${filter.title}%"
            }
            
            if(filter.name){
                eq 'studentName', filter.name
            }

            if(filter.sname != null){
                eq 'studentSname', filter.sname
            }

            if(filter.nickname){
                gt 'studentNickname', filter.nickname
            }
            if(filter.sex){
                gt 'studentSex', filter.sex
            }

            if(filter.classroom){
                eq 'classroom', filter.classroom
            }
            if(filter.__className){
                eq 'classroom.className', filter.__className
            }

            if(filter.__parentUser){
                parent { 
                     eq 'parentUser', filter.__parentUser
                }
               
            }

            //เท่ากับ
        }

        result.data = studentList.list(offset: params.offset ?: 0, max: params.max ?: 10)
        result.totalCount = studentList.count()
        result.valid = true

        render(view: "index", model: [data: result.data])

     }
     
    def show(Long id){
        def result = [valid: false]
        def student = Student.get(id)

        result.valid = true
        result.data = student
        
        render(view: "show", model: [data: result.data])
    }

    def save(Student student){
        def result = [valid: false]
        try{
            if(student.hasErrors()){
                respond student.errors
                return
            }
            student.save(failOnError: true)
            result.data = student
            result.valid = true
        }catch(ValidationException error){
            respond parent.errors
            return
        }catch(error){
            result.reason = error.message
        }
        render(result as JSON)
    }

    @Transactional
    def update(Student student){
        def result = [valid: false]
        student.save(failOnError: true)

        result.data = student
        result.valid = true

        render(result as JSON)
    }

    @Transactional
    def delete(Long id){
        def result = [valid: false]
        try {
            def student = Student.get(id)
            if(!student){
                throw new Exception("not found parent id: ${id}")
            }
            student.delete()
            result.valid = true

        }catch(error){
            result.reason = error.message
        }
        render(result as JSON)
    }
}

class StudentFilter {
    
    Long id
    String title
    String name
    String sname
    String nickname
    String sex
    Classroom classroom
    String __className
    String __parentUser
}
