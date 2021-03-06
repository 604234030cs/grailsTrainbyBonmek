package box


import grails.rest.*
import grails.converters.*

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import java.time.LocalDate

class ClassroomController {
	static responseFormats = ['json', 'xml']

	
    def index() {
        println(params.offset)
        println(params.max)
        println(params.sortOrder)
        println(params.sortField)
        println(params.__template)

        def result = [valid: false]

        // def qp = params

        def filter = new ClassroomFilter()
        

        bindData(filter, params)

        filter = filter.properties

        def classroomList = Classroom.where{}.build {


            if(filter.classId){
                eq 'id',filter.classId
            }

            if(filter.className){
               ilike 'className', "%${filter.className}%"
            }
        
            //เท่ากับ
            if(filter.teacherId){
                eq 'teacher.id', filter.teacherId
            }
            
            
        }

            result.totalCount = classroomList.count()
            result.valid = true
            result.tmpl = params.__template
            result.sortOrder = (params.sortOrder == '1')? "acs":"desc";
            result.data = classroomList.list(offset: params.offset?:0,max: params.max ?: 12,sort: result.sortField?:'id',order:result.sortOrder)
            render(view: "index", model: [data: result.data,dataCount: result.totalCount,valid: result.valid,message: result.message,dataresource:result.tmpl,totalCount:result.totalCount])


        // if(params.sortOrder == '1'){

        //     if(params.sortField == 'undefined'){
        //         println('sortField undifined')
        //         result.sortField = 'id' 
        //         result.data = classroomList.list(offset: params.offset ?: 0, max: params.max ?: 12,sort: result.sortField?:'id',order:'asc')
        //         result.totalCount = classroomList.count()
        //         result.valid = true
        //         result.tmpl = params.__template
        //         render(view: "index", model: [data: result.data,dataCount: result.totalCount,valid: result.valid,message: result.message,dataresource:result.tmpl,totalCount:result.totalCount])
        //     }else{
        //         result.data = classroomList.list(offset: params.offset ?: 0, max: params.max ?: 12,sort:params.sortField?:'id',order:'asc')
        //         result.totalCount = classroomList.count()
        //         result.valid = true
        //         result.tmpl = params.__template
        //         render(view: "index", model: [data: result.data,dataCount: result.totalCount,valid: result.valid,message: result.message,dataresource:result.tmpl,totalCount:result.totalCount])
        //     }



        // }else if(params.sortOrder == '-1'){

         
        //     if(params.sortField == 'undefined'){
        //         result.sortField = 'id' 
        //         result.data = classroomList.list(offset: params.offset ?: 0, max: params.max ?: 12,sort: result.sortField?:'id',order:'desc')
        //         result.totalCount = classroomList.count()
        //         result.valid = true
        //         result.tmpl = params.__template
        //         render(view: "index", model: [data: result.data,dataCount: result.totalCount,valid: result.valid,message: result.message,dataresource:result.tmpl,totalCount:result.totalCount])
        //     }else{
        //         result.data = classroomList.list(offset: params.offset ?: 0, max: params.max ?: 12,sort:params.sortField?:'id',order:'desc')
        //         result.totalCount = classroomList.count()
        //         result.valid = true
        //         result.tmpl = params.__template
        //         render(view: "index", model: [data: result.data,dataCount: result.totalCount,valid: result.valid,message: result.message,dataresource:result.tmpl,totalCount:result.totalCount])
        //     }
        // }else{
        //     if(params.sortField == 'undefined'){
        //         result.sortField = 'id' 
        //         result.data = classroomList.list(offset: params.offset ?: 0, max: params.max ?: 12,sort: result.sortField?:'id',order:'desc')
        //         result.totalCount = classroomList.count()
        //         result.valid = true
        //         result.tmpl = params.__template
        //         render(view: "index", model: [data: result.data,dataCount: result.totalCount,valid: result.valid,message: result.message,dataresource:result.tmpl,totalCount:result.totalCount])
        //     }else{
        //         result.data = classroomList.list(offset: params.offset ?: 0, max: params.max ?: 12,sort:params.sortField?:'id',order:'desc')
        //         result.totalCount = classroomList.count()
        //         result.valid = true
        //         result.tmpl = params.__template
        //         render(view: "index", model: [data: result.data,dataCount: result.totalCount,valid: result.valid,message: result.message,dataresource:result.tmpl,totalCount:result.totalCount])
        //     }
        // }


     }




//-------------------------------------------show--------------------------




    def show(Long id){
        println("show()")
        def result = [valid: false]
        def classroom = Classroom.get(id)

        result.valid = true
        result.data = classroom
        
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
            result.data = classroom
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
                throw new Exception("not found classroom id: ${id}")
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
