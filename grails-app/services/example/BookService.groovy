package example

import grails.gorm.services.Query
import grails.gorm.services.Service

@Service(Book)
interface BookService {
    Book save(String title, String author, String about, String href, String image)

    List<Book> findAll()

    List<Book> list(Map args)

    @Query("from ${Book book}")
    List<Book> listWithQuery(Map args)
}