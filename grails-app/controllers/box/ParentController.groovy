package box

import grails.rest.*
import grails.converters.*

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import java.time.LocalDate

class ParentController {

	static responseFormats = ['json']
	
    def index() { 
        def result = [valid: false]

        // def qp = params

        def filter = new ParentFilter()
        

        bindData(filter, params)

        filter = filter.properties

        def parentList = Parent.where{}.build {

            if(filter.id){
                eq 'id', filter.id
            }

            if(filter.user){
                eq 'parentUser', filter.user
            }
            
            if(filter.password){
                eq 'parentPassword', filter.password
            }

            if(filter.title != null){
                eq 'parentTitle', filter.title
            }

            if(filter.name){
                gt 'parentName', filter.name
            }
            if(filter.sname){
                gt 'parentSname', filter.sname
            }

            if(filter.tel){
                gt 'parentTel', filter.tel
            }

            if(filter.address){
                gt 'parentAddress', filter.address
            }

            if(filter.teacherId){
                eq 'teacher.id', filter.teacherId
            }
            // if(filter.bookCateId){
            //     eq 'category.id', filter.bookCateId
            // }
            // if(filter.teacheruser){
            //     teacherUser {
            //         teacher {
            //             ilike 'teacherUser', "%${filter.teacherid}%"
            //         }
            //     }
            // }
            
        }

        result.data = parentList.list(offset: params.offset ?: 0, max: params.max ?: 10)
        result.totalCount = parentList.count()
        result.valid = true

        render(view: "index", model: [data: result.data])
    }

    def show(Long id){
        def result = [valid: false]
        def parent = Parent.get(id)

        result.valid = true
        result.data = parent
        
        render(view: "show", model: [data: result.data])
    }

    def save(Parent parent){
        def result = [valid: false]
        try{
            if(parent.hasErrors()){
                respond parent.errors
                return
            }
            parent.save(failOnError: true)
            result.data = parent
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
    def update(Parent parent){
        def result = [valid: false]
        parent.save(failOnError: true)

        result.data = parent
        result.valid = true

        render(result as JSON)
    }

    @Transactional
    def delete(Long id){
        def result = [valid: false]
        try {
            def parent = Parent.get(id)
            if(!parent){
                throw new Exception("not found parent id: ${id}")
            }
            parent.delete()
            result.valid = true

        }catch(error){
            result.reason = error.message
        }
        render(result as JSON)
    }
    //1


}

class ParentFilter {

    Long id
    String user
    String password
    String title
    String name
    String sname
    String tel
    String address
    Long teacherId

 
}
