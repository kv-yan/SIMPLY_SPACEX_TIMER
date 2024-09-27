package simply.homework.spacextimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import simply.homework.spacextimer.commonpresentation.ui.theme.SYMPLY_SPACEX_TIMERTheme
import simply.homework.spacextimer.spacexinfo.presentation.screen.SpaceXInfoScreen
import simply.homework.spacextimer.spacexinfo.presentation.viewmodel.SpaceXInfoMVIViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
            SYMPLY_SPACEX_TIMERTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: SpaceXInfoMVIViewModel = koinViewModel()
                    SpaceXInfoScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
