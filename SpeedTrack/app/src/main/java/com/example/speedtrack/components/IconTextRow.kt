package com.example.speedtrack.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedtrack.R

@Composable
fun IconTextRow(
    text: String,
    textColor: Color = Color.Cyan,
    icon: ImageVector? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .height(40.dp)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Text(
            text = text,
            color = textColor,
            fontSize = 15.sp,
            modifier = Modifier
                .width(320.dp)
                .padding(start = 10.dp),
            fontWeight = FontWeight.Bold
        )

    }
}

@Preview
@Composable
fun IconTextRowPreview() {
    IconTextRow(
        text = "Enter Text",
        textColor = Color.Cyan,
        icon = ImageVector.vectorResource(id = R.drawable.baseline_settings_24)
    )
}