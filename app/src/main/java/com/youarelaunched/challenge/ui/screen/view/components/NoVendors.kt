package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun NoVendors() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.no_vendors_title),
                textAlign = TextAlign.Center,
                color = VendorAppTheme.colors.largeAccentText,
                style = VendorAppTheme.typography.h2
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.no_vendors_desc),
                textAlign = TextAlign.Center,
                color = VendorAppTheme.colors.textDark,
                style = VendorAppTheme.typography.subtitle2,
            )
        }
    }
}
