package com.pmdm.login.ui.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ForumTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    label: String,
    icon: @Composable () -> Unit,
    errorMessage: String?,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(label) },
        leadingIcon = icon,
        isError = errorMessage != null,
        supportingText = { if (errorMessage != null) Text(errorMessage) },
        modifier = modifier
    )
}