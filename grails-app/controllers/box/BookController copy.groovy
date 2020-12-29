package box

import grails.validation.ValidationException
import grails.rest.*
import grails.converters.*
import grails.gorm.transactions.Transactional
import java.time.LocalDate

class BookController {
	static responseFormats = ['json']
	
    def index() { 
        def result = [valid: false]

        // def qp = params

        def filter = new BookFilter()

        bindData(filter, params)

        filter = filter.properties

        def booksCriteria = Book.where{}.build {

            if(filter.titleEng__ilike){
                ilike 'titleEng', "%${filter.titleEng__ilike}%"
            }
            
            if(filter.price__gt){
                gt 'price', filter.price__gt
            }

            if(filter.isRead != null){
                eq 'isRead', filter.isRead
            }

            if(filter.since){
                gt 'since', filter.since
            }

            if(filter.since1 && filter.since2){
                between 'since', filter.since1, filter.since2
            }

            if(filter.bookCateId){
                eq 'category.id', filter.bookCateId
            }

            if(filter.authorTitle){
                authors {
                    author {
                        ilike 'titleEng', "%${filter.authorTitle}%"
                    }
                }
            }
            
        }

        result.data = booksCriteria.list(offset: params.offset ?: 0, max: params.max ?: 10)
        result.totalCount = booksCriteria.count()
        result.valid = true

        render(view: "index", model: [data: result.data])
    }

    def show(Long id){
        def result = [valid: false]
        def book = Book.get(id)

        result.valid = true
        result.data = book
        
        render(view: "show", model: [data: result.data])
    }

    def save(Book book){
        def result = [valid: false]
        try{
            if(book.hasErrors()){
                respond book.errors
                return
            }
            book.save(failOnError: true)
            result.data = book
            result.valid = true
        }catch(ValidationException error){
            respond book.errors
            return
        }catch(error){
            result.reason = error.message
        }
        render(result as JSON)
    }

    @Transactional
    def update(Book book){
        def result = [valid: false]
        book.save(failOnError: true)

        result.data = book
        result.valid = true

        render(result as JSON)
    }

    @Transactional
    def delete(Long id){
        def result = [valid: false]
        try {
            def book = Book.get(id)
            if(!book){
                throw new Exception("not found book id: ${id}")
            }
            book.delete()
            result.valid = true

        }catch(error){
            result.reason = error.message
        }
        render(result as JSON)
    }


}

class BookFilter {
    String titleEng__ilike
    Double price__gt
    Boolean isRead
    LocalDate since
    LocalDate since1
    LocalDate since2
    String authorTitle
    Long bookCateId 
}
