package example
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Book {
    String image
    String title
    String author
    String about
    String href

    static constraints = {
        image nullable: false
        title nullable: false
        author nullable: false
        about nullable: false
        href nullable: false
        about type: 'text'
    }
}
