package com.example.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleCurrencyConverterApp()
        }
    }
}

@Composable
fun SimpleCurrencyConverterApp() {
    // Extended currency list
    val currencies = listOf("USD", "IDR", "EUR", "GBP", "JPY", "SGD", "AUD", "CAD", "CHF", "CNY", "HKD", "NZD", "KRW", "THB", "MYR", "PHP")

    var inputAmount by remember { mutableStateOf("") }
    var fromCurrency by remember { mutableStateOf("USD") }
    var toCurrency by remember { mutableStateOf("IDR") }
    var result by remember { mutableStateOf("Enter amount to convert") }

    // Exchange rates
    val exchangeRates = mapOf(
        "USD" to mapOf(
            "IDR" to 16850.0, "EUR" to 0.805, "GBP" to 0.721, "JPY" to 151.2,
            "SGD" to 1.325, "AUD" to 1.485, "CAD" to 1.352, "CHF" to 0.895,
            "CNY" to 7.245, "HKD" to 7.815, "NZD" to 1.625, "KRW" to 1315.0,
            "THB" to 35.85, "MYR" to 4.685, "PHP" to 56.25
        ),
        "IDR" to mapOf(
            "USD" to 0.0000594, "EUR" to 0.0000478, "GBP" to 0.0000428, "JPY" to 0.00897,
            "SGD" to 0.0000786, "AUD" to 0.0000881, "CAD" to 0.0000803, "CHF" to 0.0000531,
            "CNY" to 0.00043, "HKD" to 0.000464, "NZD" to 0.0000965, "KRW" to 0.078,
            "THB" to 0.00213, "MYR" to 0.000278, "PHP" to 0.00334
        ),
        "EUR" to mapOf(
            "USD" to 1.242, "IDR" to 20925.0, "GBP" to 0.895, "JPY" to 187.8,
            "SGD" to 1.645, "AUD" to 1.845, "CAD" to 1.68, "CHF" to 1.11,
            "CNY" to 9.0, "HKD" to 9.7, "NZD" to 2.02, "KRW" to 1634.0,
            "THB" to 44.5, "MYR" to 5.82, "PHP" to 69.8
        ),
        "GBP" to mapOf(
            "USD" to 1.387, "IDR" to 23350.0, "EUR" to 1.118, "JPY" to 209.6,
            "SGD" to 1.838, "AUD" to 2.06, "CAD" to 1.875, "CHF" to 1.24,
            "CNY" to 10.05, "HKD" to 10.84, "NZD" to 2.255, "KRW" to 1824.0,
            "THB" to 49.7, "MYR" to 6.5, "PHP" to 78.0
        ),
        "JPY" to mapOf(
            "USD" to 0.00662, "IDR" to 111.45, "EUR" to 0.00532, "GBP" to 0.00477,
            "SGD" to 0.00877, "AUD" to 0.00983, "CAD" to 0.00895, "CHF" to 0.00592,
            "CNY" to 0.0479, "HKD" to 0.0517, "NZD" to 0.01075, "KRW" to 8.7,
            "THB" to 0.237, "MYR" to 0.031, "PHP" to 0.372
        ),
        "SGD" to mapOf(
            "USD" to 0.755, "IDR" to 12715.0, "EUR" to 0.608, "GBP" to 0.544,
            "JPY" to 114.1, "AUD" to 1.12, "CAD" to 1.02, "CHF" to 0.676,
            "CNY" to 5.47, "HKD" to 5.9, "NZD" to 1.226, "KRW" to 992.0,
            "THB" to 27.05, "MYR" to 3.54, "PHP" to 42.45
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1B2E))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Header
        Text(
            text = "💱",
            fontSize = 48.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Exchange Master",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE8AA42),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Main Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp)),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF16213E)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Amount Input
                Text(
                    text = "AMOUNT",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF8892B0),
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                TextField(
                    value = inputAmount,
                    onValueChange = { inputAmount = it },
                    placeholder = {
                        Text(
                            "0.00",
                            color = Color(0xFF495670),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF0F1419),
                        unfocusedContainerColor = Color(0xFF0F1419),
                        focusedIndicatorColor = Color(0xFFE8AA42),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Currency Selection
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // From Currency
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "FROM",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF8892B0),
                            letterSpacing = 1.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        SimpleCurrencyDropdown(
                            selectedCurrency = fromCurrency,
                            currencies = currencies,
                            onCurrencySelected = { fromCurrency = it }
                        )
                    }

                    // Swap button (simple text)
                    Text(
                        text = "⇄",
                        fontSize = 32.sp,
                        color = Color(0xFFE8AA42),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                val temp = fromCurrency
                                fromCurrency = toCurrency
                                toCurrency = temp
                            }
                    )

                    // To Currency
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "TO",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF8892B0),
                            letterSpacing = 1.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        SimpleCurrencyDropdown(
                            selectedCurrency = toCurrency,
                            currencies = currencies,
                            onCurrencySelected = { toCurrency = it }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Convert Button
                Button(
                    onClick = {
                        if (inputAmount.isBlank()) {
                            result = "Please enter amount"
                            return@Button
                        }
                        val amountValue = inputAmount.toDoubleOrNull()
                        if (amountValue == null) {
                            result = "Invalid amount"
                            return@Button
                        }

                        if (fromCurrency == toCurrency) {
                            result = "${"%.2f".format(amountValue)} $toCurrency"
                            return@Button
                        }

                        val rate = exchangeRates[fromCurrency]?.get(toCurrency)
                        if (rate == null) {
                            result = "Rate not available"
                            return@Button
                        }

                        val convertedAmount = amountValue * rate
                        result = "${"%.2f".format(convertedAmount)} $toCurrency"
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE8AA42),
                        contentColor = Color(0xFF1A1B2E)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        "CONVERT",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Result Display
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F1419)),
            border = BorderStroke(1.dp, Color(0xFFE8AA42))
        ) {
            Text(
                text = result,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFE8AA42),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
        }
    }
}

@Composable
fun SimpleCurrencyDropdown(
    selectedCurrency: String,
    currencies: List<String>,
    onCurrencySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Card(
            modifier = Modifier
                .clickable { expanded = true },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F1419)),
            border = BorderStroke(1.dp, Color(0xFF495670))
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedCurrency,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE8AA42)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color(0xFF8892B0)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFF16213E))
        ) {
            currencies.forEach { currency ->
                DropdownMenuItem(
                    text = {
                        Text(
                            currency,
                            color = if (currency == selectedCurrency) Color(0xFFE8AA42) else Color.White,
                            fontWeight = if (currency == selectedCurrency) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    onClick = {
                        onCurrencySelected(currency)
                        expanded = false
                    }
                )
            }
        }
    }
}