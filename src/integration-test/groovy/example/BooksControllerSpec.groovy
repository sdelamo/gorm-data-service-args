package example

import grails.testing.mixin.integration.Integration
import grails.testing.spock.OnceBefore
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.uri.UriBuilder
import spock.lang.Shared
import spock.lang.Specification
import io.micronaut.http.client.HttpClient

@Integration
class BooksControllerSpec extends Specification {
    @Shared
    HttpClient client

    @OnceBefore
    void init() {
        String baseUrl = "http://localhost:$serverPort"
        this.client = HttpClient.create(baseUrl.toURL())
    }

    BookService bookService

    void "GET /books returns a list of books"() {
        given:
        BlockingHttpClient client = client.toBlocking()
        List<Book> books

        when:
        books = client.retrieve(HttpRequest.GET('/books'), Argument.listOf(Book))

        then:
        books
        books.size() == 14
        books.any { it.title == 'Grails 3 - Step by Step' }
        books.any { it.title == 'Practical Grails 3' }
        books.any { it.title == 'Falando de Grails' }
        books.any { it.title == 'Grails Goodness Notebook' }
        books.any { it.title == 'The Definitive Guide to Grails 2' }
        books.any { it.title == 'Grails in Action' }
        books.any { it.title == 'Grails 2: A Quick-Start Guide' }
        books.any { it.title == 'Programming Grails' }
        books.any { it.title == 'Making Java Groovy' }
        books.any { it.title == 'Groovy in Action, 2nd Edition' }
        books.any { it.title == 'Groovy for Domain-Specific Languages' }
        books.any { it.title == 'Groovy 2 Cookbook' }
        books.any { it.title == 'Programming Groovy 2'  }
        books.any { it.title == 'Building Microservices with Micronaut®' }

        when:
        URI uri = UriBuilder.of('/books')
                .queryParam('offset', 0)
                .queryParam('max', 10)
                .build()
        books = client.retrieve(HttpRequest.GET(uri), Argument.listOf(Book))

        then:
        books
        books.size() == 10

        when:
        books = bookService.listWithQuery([offset: 0, max: 10])

        then:
        books
        books.size() == 10
    }
}