package com.example.speedtrack.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextSetting(
    text: String,
    textColor: Color = Color.Cyan,
    switchState: Boolean = true,
    onSwitchChange: (Boolean) -> Unit = {},

    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .height(40.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 15.sp,
            modifier = Modifier
                .width(320.dp)
                .padding(start = 15.dp),
            fontWeight = FontWeight.Bold
        )
        var checked by remember { mutableStateOf(true) }
        Switch(
            modifier = Modifier
                .size(24.dp)
                .scale(0.6f),
            checked = checked,
            onCheckedChange = {
                checked = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFF00E5FF),
                checkedTrackColor = Color(0xFF005F5F),
                uncheckedThumbColor = Color(0xFFFFFFFF),
                uncheckedTrackColor = Color(0xFFB0BEC5),
            )
        )
    }
}

@Preview
@Composable
fun TextSettingPreview() {
    TextSetting(
        text = "Enter Text",
        textColor = Color.Cyan,
        )
}