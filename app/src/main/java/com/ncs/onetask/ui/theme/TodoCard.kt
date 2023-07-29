package com.ncs.onetask.ui.theme

import android.content.Intent
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.ncs.onetask.models.TodoItem

import dagger.hilt.android.qualifiers.ActivityContext

@Composable
fun resbanner(
    items: List<TodoItem>,
    intitalSelectedItem: Int = 0
) {
    var clicked by remember {
        mutableStateOf(intitalSelectedItem)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        items.forEachIndexed { index, bannerContent ->
            resbannerItem(item = bannerContent, state = index == clicked) {
                clicked = index
            }
        }
    }
}

@Composable
fun resbannerItem(
    item: TodoItem,
    state: Boolean = false,
    onItemClick: () -> Unit
) {
    Box(modifier = Modifier
        .height(150.dp)
        .fillMaxWidth(1f)) {
        Box(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .padding(15.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp)
            ) {
                Text(text = item.todo, fontSize = 20.sp)
                if (item.isChecked!="0"){
                    Box {
                        Column {
                            Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = null)
                            Text(text = "Done")
                        }
                    }
                }
            }
        }

    }

}

