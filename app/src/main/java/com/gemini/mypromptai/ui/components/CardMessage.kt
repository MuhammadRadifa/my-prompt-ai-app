package com.gemini.mypromptai.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gemini.mypromptai.ui.theme.secondaryBackground
import com.gemini.mypromptai.ui.theme.tertiaryBackground
import com.gemini.mypromptai.ui.theme.textColor

@Composable
fun CardMessage(
    isUser:Boolean = true,
    text:String
) {
    Column(Modifier.fillMaxWidth(),
        horizontalAlignment = if(isUser) Alignment.End else Alignment.Start
    ) {
        Card(
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                contentColor = textColor,
                containerColor = if(isUser) tertiaryBackground else secondaryBackground
            )
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = text
            )
        }
    }

}