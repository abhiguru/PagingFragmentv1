package `in`.tutorial.pagingfragmentv1.data.remote.model

interface GoodReceivedResponseMapper<Response, Model> {
    abstract fun mapFromResponse(response: Response):Model
}