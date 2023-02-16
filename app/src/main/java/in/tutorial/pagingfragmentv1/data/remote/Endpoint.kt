package `in`.tutorial.pagingfragmentv1.data.remote

object Endpoint {
    const val GET_ALL_GR = "goodsreceived/light/"
    const val GET_GR_DETAILS = "goodsreceived/{id}/"
    const val POST_AUTH_TOKEN = "http://192.168.0.180/auth/android/"
    const val HEADER_ACCEPT = "Accept: application/json"
    const val HEADER_AUTH = "auth:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7ImlkIjoiNjcyNmIxZjAtMmExMS0xMWVkLWJhMjQtNTI1NDAwMDI0MjcxIiwibmFtZSI6Imdyb290IiwiZGlzcGxheU5hbWUiOiJhYmhpZ3VydTIiLCJpZGVudGl0eSI6IiIsImVtYWlsIjoiYWJoaW5hdmd1cnUyQGdtYWlsLmNvbSIsInN1cGVydmlzb3IiOjEsImFjdGl2ZSI6MSwidHJsIjpbXX0sImlhdCI6MTY3NjU2OTA5MSwiZXhwIjoxNjc5MTYxMDkxfQ.mgRjgblKHAIuuIjfqG7NNCeYaSnfG7WjLUOV79GuHOM"
    var AUTH_TOKEN = ""
    val HEADER_AUTH_MAN = "auth:$AUTH_TOKEN"
}