package simply.homework.spacextimer.spacexinfo.presentation.loading


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import simply.homework.spacextimer.R

@Composable
fun SpaceXLoading() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        val composition =
            rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_anim))
        val progress = animateLottieCompositionAsState(
            composition.value, iterations = LottieConstants.IterateForever
        )

        LottieAnimation(composition = composition.value, progress = {
            progress.value
        })
    }
}

@Preview
@Composable
fun GifLoaderPreview() {
    SpaceXLoading()
}
