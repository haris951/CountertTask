package com.example.speedtrack.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedtrack.R
import com.example.speedtrack.components.IconTextRow
import com.example.speedtrack.components.TextIconSwitch
import com.example.speedtrack.components.TextSetting


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingUI(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedUnit by remember { mutableStateOf("km/h") }
    val options = listOf("km/h", "mph", "knot")
    var selectedPreset by remember { mutableStateOf("Driving") }


    val onConfirm = {
        showDialog = false
    }

    val onDismiss = {
        showDialog = false
    }
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Warning when over",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Row(modifier = Modifier.padding(top = 12.dp)) {
                        val text = remember { mutableStateOf("100") }
                        TextField(
                              value = text.value,
                              onValueChange = {
                                  val newValue = it.toIntOrNull()
                                  if (newValue != null) {
                                      if (newValue in 0..999) {
                                          text.value = it
                                      }
                                  }
                              },
                              modifier = Modifier
                                  .width(100.dp).padding(start = 10.dp),
                            textStyle = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 30.sp,
                            ),
                            shape = RoundedCornerShape(16.dp),
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Black,
                            ),
                        )
                        Text(
                            text = "km/h",
                            color = Color.White,
                            modifier = Modifier.padding(top = 22.dp, start = 5.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }


                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        HorizontalDivider(thickness = 2.dp,modifier = Modifier.width(45.dp).padding(top = 10.dp, end = 5.dp))
                        Text(
                            text = "Or choose from presets",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        HorizontalDivider(thickness = 2.dp, modifier = Modifier.width(45.dp).padding(top = 10.dp, start = 5.dp))

                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    PresetOption("Driving", "100 km/h", selectedPreset == "Driving") {
                        selectedPreset = "Driving"
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    PresetOption("Cycling", "20 km/h", selectedPreset == "Cycling") {
                        selectedPreset = "Cycling"
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    PresetOption("Walking", "10 km/h", selectedPreset == "Walking") {
                        selectedPreset = "Walking"
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {  },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                    ) {
                        Text(
                            text = "Done",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable { navController.popBackStack() },
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "",
                tint = Color.White
            )
            Text(
                text = "Settings",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 15.dp)
                .background(color = Color.DarkGray, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(120.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Text(
                    text = "Speed Unit",
                    color = Color.Cyan,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Row(modifier = Modifier.padding(top = 12.dp, start = 20.dp, end = 20.dp)) {
                Row(
                    modifier = Modifier
                        .width(380.dp)
                        .background(Color(0xFF757575), shape = RoundedCornerShape(10.dp))
                ) {
                    options.forEach { unit ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(
                                    if (unit == selectedUnit) Color(0xFF00E5FF) else Color(
                                        0xFF757575
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable { selectedUnit = unit },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = unit,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (unit == selectedUnit) Color.Black else Color.LightGray,
                                modifier = Modifier.padding(vertical = 10.dp)
                            )
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 15.dp)
                .background(color = Color.DarkGray, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(220.dp),
        ) {
            TextSetting(
                text = "Speed Warning",
                textColor = Color.Cyan,
                switchState = true
            )
            Row(modifier = Modifier
                .padding(start = 15.dp, top = 5.dp)
                .height(30.dp)
                .width(350.dp)
                .clickable { showDialog = true }
            ){
                Text(
                    text = "Warning when over",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(250.dp)
                    )
                    Text(
                        text = "100km/h",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .width(100.dp)
                            .padding(start = 20.dp)
                    )

            }
            TextSetting(
                text = "Sound effect",
                textColor = Color.White
            )
            TextSetting(
                text = "Vibration",
                textColor = Color.White
            )

        }
        Column(
            modifier = Modifier
                .padding(top = 15.dp)
                .background(color = Color.DarkGray, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(480.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = "General Settings",
                    color = Color.Cyan,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            TextIconSwitch(
                text = "Route Tracking on map",
                icon = ImageVector.vectorResource(id = R.drawable.baseline_route_24)
                )
            TextIconSwitch(
                text = "Notification bar",
                icon = ImageVector.vectorResource(id = R.drawable.baseline_notifications_24 )
            )
            TextIconSwitch(
                text = "Keep screen on",
                icon = ImageVector.vectorResource(id = R.drawable.baseline_smartphone_24 )
            )
            TextIconSwitch(
                text = "Keep screen on",
                icon = ImageVector.vectorResource(id = R.drawable.outline_window_24 )
            )
            TextIconSwitch(
                text ="Language",
                icon = ImageVector.vectorResource(id = R.drawable.baseline_language_24 )
            )
            IconTextRow(
                text = "Permissions Setting",
                textColor = Color.White,
                icon = ImageVector.vectorResource(id = R.drawable.baseline_lock_24)
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 15.dp)
                .background(color = Color.DarkGray, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .height(270.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Text(
                    text = "Support us",
                    color = Color.Cyan,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            IconTextRow(
                text = "Rate us",
                textColor = Color.White,
                icon = ImageVector.vectorResource(id = R.drawable.baseline_star_24)
            )
            IconTextRow(
                text = "Feedback",
                textColor = Color.White,
                icon = ImageVector.vectorResource(id = R.drawable.baseline_create_24)
            )
            IconTextRow(
                text = "Share with friends",
                textColor = Color.White,
                icon = ImageVector.vectorResource(id = R.drawable.baseline_share_24)
            )
            IconTextRow(
                text = "Privacy policy",
                textColor = Color.White,
                icon = ImageVector.vectorResource(id = R.drawable.outline_article_24)
            )
        }

    }
}

@Composable
fun PresetOption(name: String, speed: String, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .width(250.dp)
            .height(40.dp)
            .clickable(onClick = onSelect)
            .background(if (isSelected) Color.Cyan else Color.Gray, shape = CircleShape)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = if (isSelected) Color.Black else Color.White)
        Text(text = speed, fontSize = 12.sp, color = if (isSelected) Color.Black else Color.White)
    }
}

@Composable
@Preview
fun SettingUIPreview() {
    SettingUI(rememberNavController())
}