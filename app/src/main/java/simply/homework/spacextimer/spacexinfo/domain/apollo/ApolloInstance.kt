package simply.homework.spacextimer.spacexinfo.domain.apollo

import com.apollographql.apollo.ApolloClient

object ApolloInstance {
    val apolloClient = ApolloClient.Builder().serverUrl("https://spacex-production.up.railway.app/").build()
}
