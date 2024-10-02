package simply.homework.spacextimer.spacexinfo.data.repo

import android.content.Context
import android.content.Intent
import android.net.Uri
import simply.homework.spacextimer.spacexinfo.domain.repo.LinkRepository

class LinkRepositoryImpl(private val context: Context) : LinkRepository {
    override suspend fun openLinkInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(browserIntent)
    }
}
