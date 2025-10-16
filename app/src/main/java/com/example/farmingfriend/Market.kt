package com.example.farmingfriend

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Market : Fragment() {

    private lateinit var spinnerState: Spinner
    private lateinit var spinnerCity: Spinner
    private lateinit var spinnerMarket: Spinner
    private lateinit var btnEnter: Button
    private lateinit var tableLayout: TableLayout
    private lateinit var loadingProgressBar: ProgressBar

    data class SeedEntry(val name: String, val price: String, val quality: String)

    // Sample hierarchical data for states => cities => markets
    private val dataMap = mapOf(
        "Gujarat" to mapOf(
            "Ahmedabad" to listOf("Market 1", "Market 2", "Market 5"),
            "Surat" to listOf("Market 3", "Market 4"),
            "Vadodara" to listOf("Market 1", "Market 3", "Market 5"),
            "Rajkot" to listOf("Market 4", "Market 6")
        ),
        "Maharashtra" to mapOf(
            "Mumbai" to listOf("Market 1", "Market 2", "Market 7"),
            "Pune" to listOf("Market 2", "Market 3", "Market 6"),
            "Nagpur" to listOf("Market 4", "Market 5"),
            "Nashik" to listOf("Market 6", "Market 7")
        ),
        "Rajasthan" to mapOf(
            "Jaipur" to listOf("Market 1", "Market 7"),
            "Udaipur" to listOf("Market 3", "Market 4"),
            "Jodhpur" to listOf("Market 4", "Market 5"),
            "Bikaner" to listOf("Market 6")
        ),
        "Punjab" to mapOf(
            "Ludhiana" to listOf("Market 1", "Market 2"),
            "Amritsar" to listOf("Market 3", "Market 4", "Market 5"),
            "Patiala" to listOf("Market 6", "Market 7"),
            "Jalandhar" to listOf("Market 2", "Market 7")
        ),
        "Karnataka" to mapOf(
            "Bangalore" to listOf("Market 1", "Market 3"),
            "Mysore" to listOf("Market 4", "Market 5"),
            "Hubli" to listOf("Market 6"),
            "Belgaum" to listOf("Market 7")
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_market, container, false)

        spinnerState = view.findViewById(R.id.spinner_state)
        spinnerCity = view.findViewById(R.id.spinner_city)
        spinnerMarket = view.findViewById(R.id.spinner_market)
        btnEnter = view.findViewById(R.id.btn)
        tableLayout = view.findViewById(R.id.market_table)
        loadingProgressBar = view.findViewById(R.id.progress_loading)

        setupSpinner(spinnerState, dataMap.keys.toList())
        spinnerState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val selectedState = spinnerState.selectedItem.toString()
                val cities = dataMap[selectedState]?.keys?.toList() ?: emptyList()
                setupSpinner(spinnerCity, cities)
                // Reset markets spinner for the first city or empty
                if (cities.isNotEmpty()) {
                    val markets = dataMap[selectedState]?.get(cities[0]) ?: emptyList()
                    setupSpinner(spinnerMarket, markets)
                } else {
                    setupSpinner(spinnerMarket, emptyList())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val selectedState = spinnerState.selectedItem.toString()
                val selectedCity = spinnerCity.selectedItem.toString()
                val markets = dataMap[selectedState]?.get(selectedCity) ?: emptyList()
                setupSpinner(spinnerMarket, markets)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnEnter.setOnClickListener {
            val selectedState = spinnerState.selectedItem?.toString()
            val selectedCity = spinnerCity.selectedItem?.toString()
            val selectedMarket = spinnerMarket.selectedItem?.toString()

            if (selectedState.isNullOrEmpty() || selectedCity.isNullOrEmpty() || selectedMarket.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Please select State, City, and Market", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Show loading
            loadingProgressBar.visibility = View.VISIBLE
            tableLayout.visibility = View.GONE

            // Simulate data fetching with coroutine
            lifecycleScope.launch {
                val seedData = fetchSeedDataAsync(selectedState, selectedCity, selectedMarket)
                populateSeedTable(seedData)
                loadingProgressBar.visibility = View.GONE
                tableLayout.visibility = View.VISIBLE
            }
        }

        // Initialize UI
        spinnerState.setSelection(0)
        loadingProgressBar.visibility = View.GONE

        setupTableHeader()

        return view
    }

    private fun setupSpinner(spinner: Spinner, data: List<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private suspend fun fetchSeedDataAsync(state: String, city: String, market: String): List<SeedEntry> =
        withContext(Dispatchers.IO) {
            delay(800) // simulate network or database delay
            getSeedData(state, city, market)
        }

    private fun getSeedData(state: String, city: String, market: String): List<SeedEntry> {
        return when (market) {
            "Market 1" -> listOf(
                SeedEntry("Wheat", "₹2100", "High"),
                SeedEntry("Rice", "₹2200", "Medium"),
                SeedEntry("Bajra", "₹1900", "Low"),
                SeedEntry("Mustard", "₹2000", "Medium")
            )
            "Market 2" -> listOf(
                SeedEntry("Corn", "₹2300", "Medium"),
                SeedEntry("Barley", "₹2000", "High"),
                SeedEntry("Soybean", "₹2400", "High"),
                SeedEntry("Sugarcane", "₹1800", "High")
            )
            "Market 3" -> listOf(
                SeedEntry("Maize", "₹1800", "Low"),
                SeedEntry("Gram", "₹2500", "High"),
                SeedEntry("Cotton", "₹2200", "Premium"),
                SeedEntry("Lentil", "₹2100", "Medium")
            )
            "Market 4" -> listOf(
                SeedEntry("Sunflower", "₹2000", "Medium"),
                SeedEntry("Peanut", "₹1950", "Medium"),
                SeedEntry("Chickpea", "₹2050", "High"),
                SeedEntry("Sorghum", "₹1750", "Low")
            )
            "Market 5" -> listOf(
                SeedEntry("Sesame", "₹2300", "High"),
                SeedEntry("Green Peas", "₹2400", "High"),
                SeedEntry("Tobacco", "₹2100", "Medium"),
                SeedEntry("Millets", "₹1800", "Low")
            )
            "Market 6" -> listOf(
                SeedEntry("Flaxseed", "₹2500", "Premium"),
                SeedEntry("Canola", "₹2450", "High"),
                SeedEntry("Coriander", "₹1900", "Medium"),
                SeedEntry("Castor", "₹2200", "Medium")
            )
            "Market 7" -> listOf(
                SeedEntry("Coffee", "₹3000", "Premium"),
                SeedEntry("Tea", "₹2800", "High"),
                SeedEntry("Spices", "₹2600", "High"),
                SeedEntry("Vanilla", "₹4000", "Premium")
            )
            else -> emptyList()
        }
    }

    private fun setupTableHeader() {
        tableLayout.removeAllViews()

        val headerRow = TableRow(requireContext())
        val headers = listOf("Seed Name", "Price", "Quality")
        headers.forEach { headerText ->
            val tv = TextView(requireContext()).apply {
                text = headerText
                setPadding(16, 16, 16, 16)
                setTextColor(Color.WHITE)
                setBackgroundColor(Color.parseColor("#4CAF50")) // Green header color
                gravity = Gravity.CENTER
                textSize = 16f
                setTypeface(typeface, android.graphics.Typeface.BOLD)
            }
            headerRow.addView(tv)
        }
        tableLayout.addView(headerRow)
    }

    private fun populateSeedTable(seedData: List<SeedEntry>) {
        // Clear previous rows except header
        tableLayout.removeViews(1, tableLayout.childCount - 1)

        if (seedData.isEmpty()) {
            val emptyRow = TableRow(requireContext())
            val emptyMsg = TextView(requireContext()).apply {
                text = "No seed data available for selection"
                setPadding(16, 16, 16, 16)
                gravity = Gravity.CENTER
                setTextColor(Color.RED)
            }
            emptyRow.addView(emptyMsg)
            tableLayout.addView(emptyRow)
            return
        }

        seedData.forEachIndexed { index, seed ->
            val row = TableRow(requireContext())

            val bgColor = if (index % 2 == 0) "#E8F5E9" else "#C2CF8F" // alternate colors
            row.setBackgroundColor(Color.parseColor(bgColor))

            val nameView = TextView(requireContext()).apply {
                text = seed.name
                setPadding(16, 16, 16, 16)
                setTextColor(Color.BLACK)
                gravity = Gravity.CENTER
                contentDescription = "Seed name: ${seed.name}"
            }
            val priceView = TextView(requireContext()).apply {
                text = seed.price
                setPadding(16, 16, 16, 16)
                setTextColor(Color.BLACK)
                gravity = Gravity.CENTER
                contentDescription = "Price: ${seed.price}"
            }
            val qualityView = TextView(requireContext()).apply {
                text = seed.quality
                setPadding(16, 16, 16, 16)
                setTextColor(Color.BLACK)
                gravity = Gravity.CENTER
                contentDescription = "Quality: ${seed.quality}"
            }

            row.addView(nameView)
            row.addView(priceView)
            row.addView(qualityView)

            tableLayout.addView(row)
        }
    }
}
