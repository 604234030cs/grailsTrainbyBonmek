package box

import grails.rest.*
import grails.converters.*

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import java.time.LocalDate

class TeacherController {

	static responseFormats = ['json']
	
    def index() { 
        def result = [valid: false]

        // def qp = params

        def filter = new TeacherFilter()
        

        bindData(filter, params)

        filter = filter.properties

        def teacherList = Teacher.where{}.build {

            if(filter.id){
                eq 'id', filter.id
            }
            //test
            if(filter.user){
                ilike 'teacherUser', "%${filter.user}%"
            }
            
            if(filter.password){
                gt 'teacherPassword', filter.password
            }

            if(filter.title != null){
                eq 'teacherTitle', filter.title
            }

            if(filter.name){
                gt 'teacherName', filter.name
            }
            if(filter.sname){
                gt 'teacherSname', filter.sname
            }

            if(filter.address){
                gt 'teacherAddress', filter.address
            }

            if(filter.tel){
                gt 'teacherTel', filter.tel
            }

            
        }

        result.data = teacherList.list(offset: params.offset ?: 0, max: params.max ?: 50)
        result.totalCount = teacherList.count()
        result.valid = true

        render(view: "index", model: [data: result.data])
    }

    def show(Long id){
        def result = [valid: false]
        def teacher = Teacher.get(id)

        result.valid = true
        result.data = teacher
        
        render(view: "show", model: [data: result.data])
    }

    def save(Teacher teacher){
        def result = [valid: false]
        try{
            if(teacher.hasErrors()){
                respond teacher.errors
                return
            }
            teacher.save(failOnError: true)
            result.data = teacher
            result.valid = true
        }catch(ValidationException error){
            respond teacher.errors
            return
        }catch(error){
            result.reason = error.message
        }
        render(result as JSON)
    }

    @Transactional
    def update(Teacher teacher){
        def result = [valid: false]
        teacher.save(failOnError: true)

        result.data = teacher
        result.valid = true

        render(result as JSON)
    }

    @Transactional
    def delete(Long id){
        def result = [valid: false]
        try {
            def teacher = Teacher.get(id)
            if(!teacher){
                throw new Exception("not found teacher id: ${id}")
            }
            teacher.delete()
            result.valid = true

        }catch(error){
            result.reason = error.message
        }
        render(result as JSON)
    }
    //1


}

class TeacherFilter {

    
    Long id
    String user
    String password
    String title
    String name
    String sname
    String tel
    String address

    // String teachertitleEng__ilike
    // Double price__gt
    // Boolean isRead
    // LocalDate since
    // LocalDate since1
    // LocalDate since2
    // String authorteachertitle
    // Long bookCateId 
}
