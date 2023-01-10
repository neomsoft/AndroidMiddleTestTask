package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.screen.view.VENDORS_SCREEN_SEARCH_BUTTON_TEST_TAG
import com.youarelaunched.challenge.ui.screen.view.VENDORS_SCREEN_SEARCH_FIELD_TEST_TAG
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun SearchBar(
    text: String,
    callBack: SearchBarCallBack,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp),
        elevation = 18.dp,
        color = VendorAppTheme.colors.background,
        shape = RoundedCornerShape(size = 16.dp),
    ) {
        TextField(
            modifier = Modifier
                .testTag(VENDORS_SCREEN_SEARCH_FIELD_TEST_TAG)
                .height(56.dp)
                .fillMaxWidth(),
            value = text,
            onValueChange = { callBack.onTextChange(it) },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_hint),
                    color = VendorAppTheme.colors.text,
                    style = VendorAppTheme.typography.subtitle2
                )
            },
            singleLine = true,
            trailingIcon = {
                IconButton(
                    modifier = Modifier.testTag(VENDORS_SCREEN_SEARCH_BUTTON_TEST_TAG),
                    onClick = { callBack.onSearchClicked(text) },
                ) {
                    Icon(
                        tint = VendorAppTheme.colors.text,
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = stringResource(id = R.string.btn_search_description),
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { callBack.onSearchClicked(text) }
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = VendorAppTheme.colors.text,
                disabledTextColor = Color.Transparent,
                backgroundColor = VendorAppTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

interface SearchBarCallBack {
    fun onTextChange(value: String)
    fun onSearchClicked(value: String)
}