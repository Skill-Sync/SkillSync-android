package com.ss.skillsync.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.components.BrandButton
import com.ss.skillsync.welcome.components.Slogan
import com.ss.skillsync.commonandroid.R as commonRes

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 31/08/2023
 */

interface WelcomeNavigator {
    fun leaveWelcomeScreen()

}
@Destination
@Composable
fun WelcomeScreen(
    navigator: WelcomeNavigator
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Image(
            painter = painterResource(id = commonRes.drawable.logo_w_text),
            contentDescription = "Skill Sync",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(3) { rowIndex ->
                val scrollState = rememberScrollState(rowIndex * 560)
                Image(
                    painter = painterResource(id = R.drawable.skills_row),
                    contentDescription = null,
                    modifier = Modifier
                        .height(48.dp)
                        .horizontalScroll(scrollState, enabled = false)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .paddingFromBaseline(bottom = 12.dp)
                .padding(24.dp)
        ) {
            Slogan(modifier = Modifier.padding(bottom = 16.dp))
            Text(
                text = "Explore all the most exciting skills based on your interest and study major.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.paddingFromBaseline(bottom = 32.dp),
            )
            BrandButton(
                text = stringResource(R.string.find_your_skills),
                onClick = {
                    navigator.leaveWelcomeScreen()
                },
                modifier = Modifier.fillMaxWidth(),
                isUppercase = false
            )
        }
    }
}