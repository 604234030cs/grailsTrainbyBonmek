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
            //test
            if(filter.user){
                ilike 'ParentUser', "%${filter.user}%"
            }
            
            if(filter.password){
                gt 'ParentPassword', filter.password
            }

            if(filter.title != null){
                eq 'ParentTitle', filter.title
            }

            if(filter.name){
                gt 'ParentName', filter.name
            }
            if(filter.sname){
                gt 'ParentSname', filter.sname
            }

            if(filter.address){
                gt 'ParentAddress', filter.address
            }

            if(filter.tel){
                gt 'ParentTel', filter.tel
            }

            
        }

        result.data = parentList.list(offset: params.offset ?: 0, max: params.max ?: 2)
        result.totalCount = parentList.count()
        result.message = "world2"
        result.valid = true

        render(view: "index", model: [data: result.data,dataCount: result.totalCount,valid: result.valid,message: result.message])
    }

    def show(Long id){
        def result = [valid: false]
        def parent = Parent.get(id)

        result.valid = true
        result.data =parent
        
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
                throw new Exception("not found Parent id: ${id}")
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

    // String ParenttitleEng__ilike
    // Double price__gt
    // Boolean isRead
    // LocalDate since
    // LocalDate since1
    // LocalDate since2
    // String authorParenttitle
    // Long bookCateId 
}
