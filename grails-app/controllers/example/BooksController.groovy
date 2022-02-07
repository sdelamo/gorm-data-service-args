package example

import groovy.transform.CompileStatic

@CompileStatic
class BooksController {

    BookService bookService

    def index() {
        Integer max =params.getInt('max')
        Integer offset = params.getInt('offset')
        Map args =  max != null && offset != null ? [offset: offset, max: max] : null
        List<Book> books = args ? bookService.list(args) : bookService.findAll()
        [books: books]
    }
}