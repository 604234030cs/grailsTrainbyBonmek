package box


import grails.rest.*
import grails.converters.*

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import java.time.LocalDate

class ClassroomController {
	static responseFormats = ['json', 'xml']
	
    def index() {

        def result = [valid: false]

        // def qp = params

        def filter = new ClassroomFilter()
        

        bindData(filter, params)

        filter = filter.properties

        def classroomList = Classroom.where{}.build {

            if(filter.classId){
                eq 'id', filter.classId
            }

            if(filter.className){
               eq 'className',filter.className
            }
        
            //เท่ากับ
            if(filter.teacherId){
                eq 'teacher.id', filter.teacherId
            }

            
        }

        result.data = classroomList.list(offset: params.offset ?: 0, max: params.max ?: 10)
        result.totalCount = classroomList.count()
        result.valid = true

        render(view: "index", model: [data: result.data])
     }
     def show(Long id){
        def result = [valid: false]
        def classname = Classroom.get(id)

        result.valid = true
        result.data = parent
        
        render(view: "show", model: [data: result.data])
    }

    def save(Classroom classname){
        def result = [valid: false]
        try{
            if(classname.hasErrors()){
                respond classname.errors
                return
            }
            classname.save(failOnError: true)
            result.data = parent
            result.valid = true
        }catch(ValidationException error){
            respond classname.errors
            return
        }catch(error){
            result.reason = error.message
        }
        render(result as JSON)
    }

    @Transactional
    def update(Classroom classname){
        def result = [valid: false]
        classname.save(failOnError: true)

        result.data = classname
        result.valid = true

        render(result as JSON)
    }

    @Transactional
    def delete(Long id){
        def result = [valid: false]
        try {
            def classname = Classroom.get(id)
            if(!classname){
                throw new Exception("not found parent id: ${id}")
            }
            classname.delete()
            result.valid = true

        }catch(error){
            result.reason = error.message
        }
        render(result as JSON)
    }
    //1     
}

class ClassroomFilter {

    Long classId
    String className
    Long teacherId

 
}