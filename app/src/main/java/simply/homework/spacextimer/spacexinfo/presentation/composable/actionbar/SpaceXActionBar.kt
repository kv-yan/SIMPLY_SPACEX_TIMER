package simply.homework.spacextimer.spacexinfo.presentation.composable.actionbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SpaceXActionBar(
    modifier: Modifier = Modifier,
    title: String,
    icon: Int,
    onIconClick: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = 4.dp
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            IconButton(onClick = { onIconClick() }) {
                Icon(painter = painterResource(id = icon), contentDescription = null)
            }

            Spacer(modifier = Modifier.width(4.dp))

            Text(text = title)
        }
    }
}

@Composable
fun SpaceXActionBar(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    onIconClick: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = 4.dp
    ) {
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onIconClick() }) {
                Icon(imageVector = icon, contentDescription = null)
            }

            Spacer(modifier = Modifier.width(4.dp))

            Text(text = title)
        }
    }
}