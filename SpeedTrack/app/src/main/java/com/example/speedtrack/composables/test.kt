package com.example.speedtrack.composables

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.location.LocationManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedtrack.R
import com.example.speedtrack.viewModel.MapViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.maps.android.compose.Circle
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@Preview
@Composable
fun SpeedtrackStats() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
        ) {
            Column(modifier = Modifier.padding(start = 50.dp)) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_timer_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Duration",
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Text(
                    text = "00:00:00",
                    color = Color.Cyan,
                    fontSize = 26.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 70.dp)
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_route_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Distance",
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Text(text = "0 km", color = Color.Cyan, fontSize = 26.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 50.dp)
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_speed_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Avg.Speed",
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Text(text = "0 km/h", color = Color.Cyan, fontSize = 26.sp)
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 70.dp)
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_speed_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Max.Speed",
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Text(text = "0 km/h", color = Color.Cyan, fontSize = 26.sp)
            }
        }
        Button(
            modifier = Modifier
                .width(160.dp)
                .height(75.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 25.dp),
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan
            )
        ) {
            Text(
                text = "Start",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

        ) {
            // Reset Button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(


                    modifier = Modifier

                        .width(80.dp)
                        .height(50.dp)
                        .size(50.dp)
                        ,
                    shape = CircleShape,
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_settings_24) ,// Replace with your reset icon
                        contentDescription = null,
                        tint = Color.White
                    )

                }
                Text(
                    text = "Reset",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Stop Button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(

                    shape = CircleShape,
                    modifier = Modifier
                        .width(160.dp)
                        .height(50.dp)
                        .background(Color(0xFF333333), shape = RoundedCornerShape(35.dp)),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(Color.Cyan, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "STOP",
                            color = Color.Cyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
            }

            // Pause Button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    shape = CircleShape,
                    modifier = Modifier
                        .width(80.dp)
                        .height(50.dp)
                        .size(50.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_play_arrow_24), // Replace with your pause icon
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(140.dp)
                    )
                }
                Text(
                    text = "Pause",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        }
    }





