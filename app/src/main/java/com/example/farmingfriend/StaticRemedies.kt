package com.example.farmingfriend
import com.example.farmingfriend.Remedy
import kotlin.random.Random

object StaticRemedies {

    // Map of crop_disease -> remedyType -> List<Remedy>
    private val remediesMap: Map<String, Map<String, List<Remedy>>> = mapOf(

        // WHEAT
        "wheat_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chemical Insecticide A", null, "Step 1: Wear protective gear.\nStep 2: Dilute 100ml in 10L water.\nStep 3: Spray every 7 days.\nStep 4: Avoid windy days."),
                Remedy("Chemical Insecticide B", null, "Mix 50ml with 15L water and spray every 10 days."),
                Remedy("Chemical Insecticide C", null, "Spray early morning with 0.5% solution."),
                Remedy("Chemical Insecticide D", null, "Apply only on affected areas, repeat after 14 days.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "Mix 50ml neem oil with 5L water. Add mild soap. Spray every 5 days."),
                Remedy(null, "Cow Urine Spray", "Mix 1L cow urine + 10L water + turmeric. Spray weekly."),
                Remedy(null, "Chili-Garlic Extract", "Soak chili+garlic overnight. Filter and spray every 7 days."),
                Remedy(null, "Buttermilk Spray", "Mix 2L buttermilk with 8L water and spray twice a week.")
            ),
            "organic" to listOf(
                Remedy(null, "Garlic Extract Spray", "Crush 10 garlic cloves overnight in water, dilute to 5L, spray weekly."),
                Remedy(null, "Soap Solution", "Mix 30g mild soap in 10L water, spray on leaves."),
                Remedy(null, "Tobacco Decoction", "Boil tobacco leaves, cool, strain, and spray."),
                Remedy(null, "Marigold Extract", "Blend marigold flowers with water, strain, spray every 5 days.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybugs Release", "Release ladybugs in field, avoid pesticide use."),
                Remedy(null, "Predatory Wasps", "Introduce wasps near infested crops."),
                Remedy(null, "Beauveria Bassiana", "Mix spores with water, apply on plants."),
                Remedy(null, "Nematode Spray", "Apply beneficial nematodes to soil.")
            )
        ),

        "wheat_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Solution", null, "Mix 2% urea solution and spray on leaves every 15 days."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre at the time of sowing."),
                Remedy("Micronutrient Mix", null, "Spray micronutrient mix as per label instructions."),
                Remedy("Zinc Sulphate", null, "Apply 25kg/ha zinc sulphate in soil before sowing.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Prepare jeevamrut with cow dung, cow urine, jaggery and apply weekly."),
                Remedy(null, "Buttermilk Tonic", "Dilute buttermilk 1:10 with water and apply at root zone."),
                Remedy(null, "Neem Cake Powder", "Apply neem cake powder to soil to improve fertility."),
                Remedy(null, "Banana Pseudostem Extract", "Spray extract once every 15 days.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "Apply 2 tons per acre as basal dose."),
                Remedy(null, "Seaweed Extract", "Spray seaweed extract solution on leaves weekly."),
                Remedy(null, "Farm Yard Manure", "Apply FYM before sowing to improve soil structure."),
                Remedy(null, "Green Manuring", "Grow dhaincha or sunhemp and plough into soil.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum Biofertilizer", "Mix with soil and apply near root zone."),
                Remedy(null, "Phosphate Solubilizing Bacteria", "Apply with irrigation water."),
                Remedy(null, "Rhizobium Culture", "Coat seeds before sowing."),
                Remedy(null, "VAM (Vesicular Arbuscular Mycorrhiza)", "Mix in soil to enhance nutrient uptake.")
            )
        ),

        "wheat_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate Spray", null, "Spray 1% solution immediately after hail or frost damage to boost recovery."),
                Remedy("Plant Growth Regulator", null, "Apply as per label to stimulate regrowth after stress."),
                Remedy("Silicon Supplement", null, "Spray 2ml/L solution to strengthen plant cell walls."),
                Remedy("Boron Foliar Spray", null, "Apply 0.5g/L solution to support flowering after stress.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray 3% solution on leaves to enhance immunity."),
                Remedy(null, "Aloe Vera Extract", "Spray diluted aloe vera juice (1:10) to help plant healing."),
                Remedy(null, "Tulsi Extract", "Spray tulsi leaf extract weekly for stress tolerance."),
                Remedy(null, "Giloy (Tinospora) Extract", "Spray diluted extract to improve plant resilience.")
            ),
            "organic" to listOf(
                Remedy(null, "Seaweed Extract", "Spray 2ml/L solution to reduce stress impact."),
                Remedy(null, "Humic Acid Solution", "Apply 1% humic acid to root zone for faster recovery."),
                Remedy(null, "Compost Tea", "Spray compost tea to provide nutrients and beneficial microbes."),
                Remedy(null, "Vermiwash", "Spray 5% solution on leaves every 7 days after damage.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR (Plant Growth Promoting Rhizobacteria)", "Apply near root zone to boost growth recovery."),
                Remedy(null, "Trichoderma Harzianum", "Apply as soil drench to prevent secondary infections."),
                Remedy(null, "Mycorrhizal Fungi", "Mix with soil to improve water and nutrient uptake."),
                Remedy(null, "Pseudomonas Fluorescens", "Spray suspension to prevent opportunistic pathogens.")
            )
        ),

        "wheat_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum (CaSO4)", null, "Apply 1–2 tonnes/ha for sodic soils. Incorporate into topsoil and follow with good irrigation to leach sodium."),
                Remedy("Lime (Calcium Carbonate)", null, "Apply 1–3 tonnes/ha depending on soil pH. Mix into soil before sowing to raise pH and neutralize acidity."),
                Remedy("Balanced NPK (e.g., 20-20-10)", null, "Apply NPK based on soil test recommendations. Split N application (basal + topdressing) for best uptake."),
                Remedy("Zinc Sulphate (ZnSO4)", null, "Apply 25 kg/ha or foliar spray 0.5% at early tillering if zinc deficiency symptoms appear.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut Soil Tonic", "Prepare jeevamrut and apply as soil drench (5–10 L per tree-bed equivalent). Repeat weekly for 3–4 applications to boost microbes."),
                Remedy(null, "Neem Cake Amendment", "Mix neem cake 250–500 kg/ha into soil before sowing to improve organic content and pest resistance."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute panchagavya 1:10 and apply to soil and foliage every 15 days to improve vigor."),
                Remedy(null, "Cow Dung Compost", "Apply well-decomposed cow dung compost 5–10 tonnes/ha as a basal amendment to improve structure and fertility.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost Application", "Apply vermicompost 2–4 tonnes/ha as basal dose to increase microbial activity and nutrient availability."),
                Remedy(null, "Green Manure (Dhaincha/Sunhemp)", "Grow and incorporate green manure crop 20–30 days before sowing to add nitrogen and organic matter."),
                Remedy(null, "Biochar + Compost Mix", "Mix biochar with compost and apply 1–2 tonnes/ha to improve water retention and cation exchange capacity."),
                Remedy(null, "Farm Yard Manure (FYM)", "Apply FYM 10–20 tonnes/ha pre-sowing to improve soil structure and slow-release nutrients.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum (N-fixing bacteria)", "Coat seeds or apply as soil inoculant at sowing to enhance root growth and nitrogen availability."),
                Remedy(null, "Phosphate Solubilizing Bacteria (PSB)", "Apply PSB to soil or with irrigation to increase available phosphorus from insoluble sources."),
                Remedy(null, "VAM (Arbuscular Mycorrhizal Fungi)", "Mix VAM in planting furrow or seedling zone to improve phosphorus and water uptake, especially in poor soils."),
                Remedy(null, "Trichoderma + Organic Matter", "Apply Trichoderma treated compost or soil drench to improve root health and suppress opportunistic pathogens.")
            )
        ),




        "wheat_fungal infection" to mapOf(
            "chemical" to listOf(
                Remedy("Fungicide A", null, "Spray 2g/L solution every 10 days."),
                Remedy("Fungicide B", null, "Apply as per label instructions."),
                Remedy("Fungicide C", null, "Spray in early morning or late evening."),
                Remedy("Fungicide D", null, "Do not spray during rain, repeat after 14 days.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Turmeric Paste", "Apply turmeric paste around stem daily."),
                Remedy(null, "Aloe Vera Extract", "Spray aloe vera extract diluted in water."),
                Remedy(null, "Asafoetida Spray", "Mix asafoetida in water, spray weekly."),
                Remedy(null, "Neem Decoction", "Boil neem leaves, cool, and spray.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost Tea Spray", "Spray compost tea twice a week."),
                Remedy(null, "Sour Milk Spray", "Mix 1L sour milk with 9L water, spray weekly."),
                Remedy(null, "Baking Soda Spray", "Mix 5g baking soda per L of water and spray."),
                Remedy(null, "Wood Ash Dusting", "Dust wood ash near infected area.")
            ),
            "biological" to listOf(
                Remedy(null, "Trichoderma spp.", "Apply Trichoderma spores to soil before planting."),
                Remedy(null, "Pseudomonas Fluorescens", "Mix with water, spray on foliage."),
                Remedy(null, "Bacillus Subtilis", "Apply bacterial suspension on plants."),
                Remedy(null, "Mycorrhizal Fungi", "Mix in soil to boost immunity.")
            )
        ),

        "rice_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chlorpyrifos 20% EC", null, "Apply 1.5 ml/L water, spray at early pest appearance."),
                Remedy("Monocrotophos", null, "Spray as per label, repeat every 10–15 days if needed."),
                Remedy("Cypermethrin 25% EC", null, "Spray 1 ml/L water in morning hours."),
                Remedy("Imidacloprid 17.8% SL", null, "Apply as foliar spray 0.3 ml/L water.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Seed Kernel Extract", "Grind 5kg neem seed, soak overnight, filter, spray."),
                Remedy(null, "Garlic-Chili Extract", "Prepare extract, dilute 1:10, spray weekly."),
                Remedy(null, "Cow Urine Decoction", "Boil cow urine + neem leaves, cool, spray every 7 days."),
                Remedy(null, "Buttermilk Spray", "Spray diluted buttermilk (1:10) to repel pests.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap + Kerosene Emulsion", "Prepare soap-kerosene emulsion, spray on affected areas."),
                Remedy(null, "Marigold Border Crop", "Plant marigold around field to repel pests."),
                Remedy(null, "Tobacco Decoction", "Boil tobacco leaves, filter, spray on affected area."),
                Remedy(null, "Neem Oil + Soap Solution", "Mix 50ml neem oil + 5ml soap per L water, spray weekly.")
            ),
            "biological" to listOf(
                Remedy(null, "Trichogramma Egg Parasitoids", "Release @ 50,000/ha at weekly intervals."),
                Remedy(null, "Metarhizium Anisopliae", "Apply spores in irrigation water."),
                Remedy(null, "Beauveria Bassiana", "Spray suspension on infested crop area."),
                Remedy(null, "Predatory Spiders", "Encourage natural predators by avoiding broad-spectrum insecticides.")
            )
        ),

        "rice_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea", null, "Apply top-dressing urea in splits at active tillering."),
                Remedy("Zinc Sulphate (ZnSO4)", null, "Apply 25kg/ha for zinc-deficient soils."),
                Remedy("DAP (Diammonium Phosphate)", null, "Apply basal dose at transplanting."),
                Remedy("Micronutrient Foliar Spray", null, "Spray Fe, Zn, Mn mix 0.5% solution.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray 3% solution every 15 days to enhance growth."),
                Remedy(null, "Cow Dung Slurry", "Apply slurry to root zone to improve microbial activity."),
                Remedy(null, "Banana Pseudostem Extract", "Foliar spray once in 15 days."),
                Remedy(null, "Jeevamrut", "Apply as drench to soil at weekly intervals.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "Apply 3-5 tonnes/ha as basal dose."),
                Remedy(null, "Green Manuring", "Incorporate dhaincha before transplanting."),
                Remedy(null, "FYM", "Apply 10–15 t/ha during land preparation."),
                Remedy(null, "Compost Tea", "Spray or drench every 15 days for better growth.")
            ),
            "biological" to listOf(
                Remedy(null, "Azolla Biofertilizer", "Grow azolla in fields to fix nitrogen."),
                Remedy(null, "Blue Green Algae (BGA)", "Inoculate standing water with BGA cultures."),
                Remedy(null, "Azospirillum", "Apply 5kg/ha mixed with FYM at transplanting."),
                Remedy(null, "PSB (Phosphate Solubilizing Bacteria)", "Apply with basal fertilizer for better P availability.")
            )
        ),

        "rice_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution after lodging or hail damage."),
                Remedy("Silicon Supplement", null, "Spray 2 ml/L to strengthen stems."),
                Remedy("Boron Foliar Spray", null, "Apply 0.5g/L solution for reproductive recovery."),
                Remedy("Plant Growth Regulator (GA3)", null, "Spray as per label to improve regrowth.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Tulsi Extract", "Spray tulsi extract to improve stress tolerance."),
                Remedy(null, "Aloe Vera Juice", "Apply diluted aloe vera extract 1:10 ratio."),
                Remedy(null, "Giloy Decoction", "Spray weekly to strengthen plant immunity."),
                Remedy(null, "Panchagavya", "Spray 3% every 15 days after stress event.")
            ),
            "organic" to listOf(
                Remedy(null, "Seaweed Extract", "Spray 2ml/L every 10 days post stress."),
                Remedy(null, "Humic Acid", "Apply 1% to root zone via irrigation."),
                Remedy(null, "Compost Tea", "Spray compost tea on foliage after stress."),
                Remedy(null, "Vermiwash", "Spray 5% solution every 7 days to aid recovery.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR Mix", "Apply near roots to stimulate growth recovery."),
                Remedy(null, "Trichoderma Harzianum", "Soil drench to prevent disease post flooding."),
                Remedy(null, "Mycorrhiza", "Apply in root zone to enhance nutrient uptake."),
                Remedy(null, "Pseudomonas Fluorescens", "Spray suspension to protect against secondary infections.")
            )
        ),

        "rice_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "Apply for sodic soils 1–2 t/ha, incorporate into top layer."),
                Remedy("Lime", null, "Apply for acidic soils 1–3 t/ha based on pH."),
                Remedy("Balanced NPK", null, "Apply based on soil test recommendation."),
                Remedy("Zinc Sulphate", null, "Apply 25kg/ha or foliar spray 0.5%.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Apply as soil drench weekly for 3–4 weeks."),
                Remedy(null, "Neem Cake", "Mix 250–500kg/ha into soil before transplanting."),
                Remedy(null, "Panchagavya", "Apply 1:10 dilution to soil."),
                Remedy(null, "Cow Dung Compost", "Apply 5–10 t/ha during puddling.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "Apply 2–4 t/ha as basal amendment."),
                Remedy(null, "Green Manure", "Grow dhaincha, incorporate before transplanting."),
                Remedy(null, "Biochar", "Apply 1–2 t/ha mixed with compost."),
                Remedy(null, "FYM", "Apply 10–20 t/ha to improve soil structure.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply to soil or seed before sowing."),
                Remedy(null, "PSB", "Apply through irrigation to solubilize phosphorus."),
                Remedy(null, "VAM", "Mix in nursery soil or root zone."),
                Remedy(null, "Trichoderma", "Apply through compost or soil drench.")
            )
        ),

        "rice_fungal infection" to mapOf(
            "chemical" to listOf(
                Remedy("Carbendazim 50% WP", null, "Spray 1g/L every 10–12 days."),
                Remedy("Propiconazole 25% EC", null, "Spray as per label at early infection."),
                Remedy("Mancozeb 75% WP", null, "Spray 2g/L at 10-day intervals."),
                Remedy("Hexaconazole 5% EC", null, "Apply as foliar spray after symptoms appear.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Decoction", "Boil neem leaves, cool, spray weekly."),
                Remedy(null, "Asafoetida Spray", "Mix 5g asafoetida in 1L water and spray."),
                Remedy(null, "Turmeric Paste", "Apply around base of affected plants."),
                Remedy(null, "Aloe Vera Extract", "Spray diluted aloe vera juice.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost Tea", "Spray twice a week on infected leaves."),
                Remedy(null, "Sour Milk Spray", "Mix 1L sour milk + 9L water, spray weekly."),
                Remedy(null, "Baking Soda Solution", "Spray 5g/L solution to suppress fungus."),
                Remedy(null, "Wood Ash Dust", "Dust wood ash around plant base.")
            ),
            "biological" to listOf(
                Remedy(null, "Trichoderma Viride", "Mix spores with FYM, apply near roots."),
                Remedy(null, "Pseudomonas Fluorescens", "Spray on leaves to suppress fungus."),
                Remedy(null, "Bacillus Subtilis", "Apply suspension as foliar spray."),
                Remedy(null, "Mycorrhizal Fungi", "Mix with nursery soil to enhance resistance.")
            )
        ),

        "corn_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Lambda-cyhalothrin", null, "Spray 1ml per liter water when pests are visible."),
                Remedy("Chlorpyrifos", null, "Apply 2ml per liter water in evening hours."),
                Remedy("Thiodicarb", null, "Mix 1.5g per liter water, spray thoroughly."),
                Remedy("Spinosad", null, "Spray 0.5ml per liter at 7-day intervals.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Seed Extract", "Mix 5% solution, spray weekly."),
                Remedy(null, "Garlic-Chili Spray", "Prepare extract, spray on leaves."),
                Remedy(null, "Asafoetida Solution", "Mix with water, spray near roots."),
                Remedy(null, "Butter Milk Spray", "Mix with water, spray on leaves.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray 30g soap solution per 10L water."),
                Remedy(null, "Trap Crops", "Plant marigold around field borders."),
                Remedy(null, "Wood Ash", "Sprinkle on leaves to deter pests."),
                Remedy(null, "Cow Dung Slurry", "Apply near roots to repel insects.")
            ),
            "biological" to listOf(
                Remedy(null, "Trichogramma", "Release egg parasitoids in field."),
                Remedy(null, "Beauveria bassiana", "Spray spores suspension."),
                Remedy(null, "Nuclear Polyhedrosis Virus", "Spray on infested crop."),
                Remedy(null, "Ladybird Beetles", "Release near aphid population.")
            )
        ),

        "corn_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea", null, "Apply 75kg per acre during knee-high stage."),
                Remedy("Zinc Sulphate", null, "Spray 0.5% solution for zinc deficiency."),
                Remedy("Micronutrient Mix", null, "Apply as per soil test results."),
                Remedy("NPK Fertilizer", null, "Follow recommended dose for corn.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray 3% solution on leaves."),
                Remedy(null, "Jeevamrut", "Apply at 15-day intervals."),
                Remedy(null, "Banana Extract", "Foliar spray to boost growth."),
                Remedy(null, "Neem Cake", "Apply to soil before irrigation.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "Apply 3 tons per acre."),
                Remedy(null, "Bio-slurry", "Use as basal dose before sowing."),
                Remedy(null, "Compost Tea", "Spray 5% solution on foliage."),
                Remedy(null, "Green Manure", "Incorporate before corn planting.")
            ),
            "biological" to listOf(
                Remedy(null, "Azotobacter", "Mix with seeds before sowing."),
                Remedy(null, "Mycorrhiza", "Apply with irrigation water."),
                Remedy(null, "Phosphate Solubilizing Bacteria", "Mix with FYM and apply."),
                Remedy(null, "Rhizobium", "Apply as seed treatment.")
            )
        ),

        "corn_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Anti-Transpirant Spray", null, "Spray 1% solution to reduce water loss."),
                Remedy("Potassium Nitrate", null, "Spray during drought stress."),
                Remedy("Silicon Spray", null, "Strengthens plant cell walls."),
                Remedy("Fungicide Mix", null, "Spray after hailstorm damage.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Apply to improve soil health post rain."),
                Remedy(null, "Panchagavya", "Spray to recover plant vigor."),
                Remedy(null, "Tulsi Extract", "Spray to reduce stress."),
                Remedy(null, "Aloe Vera Extract", "Spray as natural anti-stress agent.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Apply to improve plant health."),
                Remedy(null, "Biochar", "Mix in soil for better moisture retention."),
                Remedy(null, "Drip Irrigation", "Maintain proper moisture after damage.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply Plant Growth Promoting Rhizobacteria."),
                Remedy(null, "Trichoderma", "Apply to prevent secondary infections."),
                Remedy(null, "Azospirillum", "Improves root strength."),
                Remedy(null, "AM Fungi", "Improves water and nutrient uptake.")
            )
        ),

        "corn_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "Apply 1 ton per acre for sodic soil."),
                Remedy("Lime", null, "Use 0.5 ton per acre for acidic soil."),
                Remedy("Zinc Sulphate", null, "Use 25kg per acre for zinc deficiency."),
                Remedy("Sulphur", null, "Apply 20kg per acre if sulphur deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Cow Urine", "Apply diluted cow urine to soil."),
                Remedy(null, "Neem Cake", "Mix with soil to improve fertility."),
                Remedy(null, "Buttermilk Solution", "Pour around root zone."),
                Remedy(null, "Agniastra", "Apply for soil pest control.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "Add 5 tons per acre."),
                Remedy(null, "Biochar", "Mix to improve soil structure."),
                Remedy(null, "Green Manure", "Incorporate before sowing."),
                Remedy(null, "Vermicompost", "Apply near root zone.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Improves soil nitrogen."),
                Remedy(null, "PSB", "Solubilizes phosphorus."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Improves soil nutrient uptake.")
            )
        ),



    // 🌱 SOYBEAN
    "soybean_pest infestation" to mapOf(
    "chemical" to listOf(
    Remedy("Imidacloprid", null, "Spray 0.3ml per liter water on affected leaves."),
    Remedy("Thiamethoxam", null, "Apply 1g per liter water, repeat after 10 days."),
    Remedy("Chlorpyrifos", null, "Spray 2ml per liter water in evening hours."),
    Remedy("Lambda-cyhalothrin", null, "Use 1ml per liter water, avoid windy days.")
    ),
    "ayurvedic" to listOf(
    Remedy(null, "Neem Oil Spray", "Mix 5% solution, spray weekly."),
    Remedy(null, "Garlic-Chili Extract", "Prepare extract, spray leaves every 7 days."),
    Remedy(null, "Cow Urine Solution", "Dilute 1:10, spray weekly."),
    Remedy(null, "Turmeric Water", "Mix turmeric powder in water, spray on leaves.")
    ),
    "organic" to listOf(
    Remedy(null, "Soap Water Spray", "Mix mild soap in water, spray leaves."),
    Remedy(null, "Marigold Extract", "Blend flowers with water, spray weekly."),
    Remedy(null, "Tobacco Decoction", "Boil tobacco leaves, cool, spray on pests."),
    Remedy(null, "Wood Ash Dust", "Sprinkle near pest-infested areas.")
    ),
    "biological" to listOf(
    Remedy(null, "Ladybird Beetles", "Release near aphid colonies."),
    Remedy(null, "Trichogramma", "Release egg parasitoids in fields."),
    Remedy(null, "Beauveria bassiana", "Spray spore suspension on plants."),
    Remedy(null, "Nematodes", "Apply beneficial nematodes to soil.")
    )
    ),

    "soybean_poor growth" to mapOf(
    "chemical" to listOf(
    Remedy("Urea Spray", null, "Spray 2% solution at vegetative stage."),
    Remedy("DAP Fertilizer", null, "Apply 100kg per acre before sowing."),
    Remedy("NPK Mix", null, "Follow recommended soil test dose."),
    Remedy("Micronutrients", null, "Spray zinc, boron as per deficiency.")
    ),
    "ayurvedic" to listOf(
    Remedy(null, "Panchagavya", "Spray 3% solution every 15 days."),
    Remedy(null, "Jeevamrut", "Apply 10L per acre near roots weekly."),
    Remedy(null, "Banana Extract", "Foliar spray to enhance growth."),
    Remedy(null, "Neem Cake Powder", "Mix into soil before sowing.")
    ),
    "organic" to listOf(
    Remedy(null, "Vermicompost", "Apply 2–3 tons per acre."),
    Remedy(null, "Compost Tea", "Drench soil weekly for nutrients."),
    Remedy(null, "Green Manure", "Incorporate before sowing to enrich soil."),
    Remedy(null, "Farmyard Manure", "Apply 10 tons per acre for better structure.")
    ),
    "biological" to listOf(
    Remedy(null, "Azospirillum", "Coat seeds or soil drench for N-fixation."),
    Remedy(null, "PSB (Phosphate Solubilizing Bacteria)", "Apply to soil for phosphorus availability."),
    Remedy(null, "Rhizobium Culture", "Seed treatment before sowing."),
    Remedy(null, "Trichoderma", "Mix in soil to improve root health.")
    )
    ),

    "soybean_weather damage" to mapOf(
    "chemical" to listOf(
    Remedy("Potassium Nitrate", null, "Spray 1% solution to recover from stress."),
    Remedy("Silicon Spray", null, "Strengthens stems and leaves after damage."),
    Remedy("Anti-Transpirant Spray", null, "Reduce water loss under heat/drought."),
    Remedy("Fungicide Mix", null, "Spray to prevent secondary fungal infections.")
    ),
    "ayurvedic" to listOf(
    Remedy(null, "Panchagavya", "Spray to enhance immunity after stress."),
    Remedy(null, "Jeevamrut", "Apply at root zone post-damage."),
    Remedy(null, "Tulsi Extract", "Spray leaves to reduce stress impact."),
    Remedy(null, "Aloe Vera Extract", "Dilute and spray to aid recovery.")
    ),
    "organic" to listOf(
    Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
    Remedy(null, "Compost Tea", "Drench soil for microbial support."),
    Remedy(null, "Biochar", "Mix with soil to improve moisture retention."),
    Remedy(null, "Drip Irrigation", "Maintain water supply post-damage.")
    ),
    "biological" to listOf(
    Remedy(null, "PGPR", "Apply Plant Growth Promoting Rhizobacteria."),
    Remedy(null, "Trichoderma", "Apply to reduce secondary infections."),
    Remedy(null, "Azospirillum", "Boost root and nutrient uptake."),
    Remedy(null, "AM Fungi", "Enhance water and nutrient absorption.")
    )
    ),

    "soybean_soil issues" to mapOf(
    "chemical" to listOf(
    Remedy("Gypsum", null, "Apply 1 ton/acre for sodic soils."),
    Remedy("Lime", null, "Use 0.5 ton/acre to neutralize acidic soils."),
    Remedy("Zinc Sulphate", null, "Apply 25kg per acre if zinc deficient."),
    Remedy("Sulphur", null, "Apply 20kg per acre for sulphur deficient soil.")
    ),
    "ayurvedic" to listOf(
    Remedy(null, "Neem Cake", "Mix 250kg per acre into soil."),
    Remedy(null, "Jeevamrut Soil Drench", "Apply 10L per acre weekly."),
    Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 and apply fortnightly."),
    Remedy(null, "Cow Dung Compost", "Apply 5–10 tons per acre as basal dose.")
    ),
    "organic" to listOf(
    Remedy(null, "Compost", "Add 5 tons per acre."),
    Remedy(null, "Vermicompost", "Apply 2–3 tons per acre near roots."),
    Remedy(null, "Green Manure", "Grow and plough sunhemp or dhaincha."),
    Remedy(null, "Biochar + Compost", "Mix and apply 1–2 tons per acre.")
    ),
    "biological" to listOf(
    Remedy(null, "Azospirillum", "Apply with irrigation to enrich N content."),
    Remedy(null, "PSB", "Apply to make phosphorus available."),
    Remedy(null, "Trichoderma", "Reduces soil-borne pathogens."),
    Remedy(null, "AM Fungi", "Enhances nutrient uptake and root health.")
    )
    ),
        "barley_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Insecticide X", null, "Spray 1ml per liter water on leaves."),
                Remedy("Insecticide Y", null, "Apply 0.5% solution, repeat after 7 days."),
                Remedy("Insecticide Z", null, "Spray early morning to avoid sunlight degradation."),
                Remedy("Insecticide W", null, "Apply only on affected areas.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil", "5% solution, spray weekly."),
                Remedy(null, "Garlic Extract", "Crush garlic, dilute, spray on leaves."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric powder in water, spray.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Water", "Spray mild soap solution on leaves."),
                Remedy(null, "Marigold Extract", "Blend and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, cool and spray."),
                Remedy(null, "Wood Ash", "Sprinkle near affected areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybugs", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids in field."),
                Remedy(null, "Beauveria bassiana", "Spray spore suspension on plants."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "barley_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray at vegetative stage."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre before sowing."),
                Remedy("NPK Mix", null, "Use recommended doses as per soil test."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray 3% solution every 15 days."),
                Remedy(null, "Jeevamrut", "Apply 10L per acre near roots weekly."),
                Remedy(null, "Neem Cake Powder", "Mix into soil before sowing."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench soil weekly."),
                Remedy(null, "Green Manure", "Incorporate before sowing."),
                Remedy(null, "Farmyard Manure", "10 tons per acre pre-sowing.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Coat seeds or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium Culture", "Seed treatment before sowing."),
                Remedy(null, "Trichoderma", "Mix in soil to improve root health.")
            )
        ),
        "barley_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution to recover."),
                Remedy("Silicon Spray", null, "Strengthens stems and leaves."),
                Remedy("Anti-Transpirant", null, "Reduces water loss under stress."),
                Remedy("Fungicide Mix", null, "Prevents secondary fungal infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray to enhance immunity."),
                Remedy(null, "Jeevamrut", "Apply at root zone post-damage."),
                Remedy(null, "Tulsi Extract", "Spray leaves to reduce stress."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray to aid recovery.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Drench soil for microbial support."),
                Remedy(null, "Biochar", "Mix with soil to improve moisture retention."),
                Remedy(null, "Drip Irrigation", "Maintain water supply post-damage.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply Plant Growth Promoting Rhizobacteria."),
                Remedy(null, "Trichoderma", "Apply to reduce secondary infections."),
                Remedy(null, "Azospirillum", "Boost root and nutrient uptake."),
                Remedy(null, "AM Fungi", "Enhance nutrient and water absorption.")
            )
        ),
        "barley_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "Apply 1 ton/acre for sodic soils."),
                Remedy("Lime", null, "0.5–1 ton/acre for acidic soils."),
                Remedy("Zinc Sulphate", null, "25kg per acre if deficient."),
                Remedy("Sulphur", null, "20kg per acre for sulphur-deficient soil.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg per acre into soil."),
                Remedy(null, "Jeevamrut Soil Drench", "Apply 10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 and apply fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons per acre as basal dose.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "Add 5 tons per acre."),
                Remedy(null, "Vermicompost", "2–3 tons per acre near roots."),
                Remedy(null, "Green Manure", "Grow and plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "Mix and apply 1–2 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Enhances nutrient uptake and root health.")
            )
        ),

        // ------------------- COTTON -------------------
        "cotton_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Imidacloprid", null, "Spray 0.3ml per liter water."),
                Remedy("Thiamethoxam", null, "Apply 1g per liter, repeat after 10 days."),
                Remedy("Chlorpyrifos", null, "Spray 2ml per liter in evening."),
                Remedy("Lambda-cyhalothrin", null, "1ml per liter water, avoid wind.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "5% solution weekly."),
                Remedy(null, "Garlic-Chili Extract", "Prepare extract and spray weekly."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric in water and spray.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray mild soap water."),
                Remedy(null, "Marigold Extract", "Blend and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, spray on plants."),
                Remedy(null, "Wood Ash Dust", "Sprinkle near pest areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybird Beetles", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids."),
                Remedy(null, "Beauveria bassiana", "Spray spores on plants."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "cotton_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray at vegetative stage."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre."),
                Remedy("NPK Mix", null, "Use recommended soil test doses."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray 3% solution."),
                Remedy(null, "Jeevamrut", "Apply 10L per acre weekly."),
                Remedy(null, "Neem Cake Powder", "Mix in soil before sowing."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench weekly."),
                Remedy(null, "Green Manure", "Incorporate before sowing."),
                Remedy(null, "FYM", "10 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Seed coating or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium", "Seed treatment before sowing."),
                Remedy(null, "Trichoderma", "Mix in soil.")
            )
        ),
        "cotton_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution."),
                Remedy("Silicon Spray", null, "Strengthens stems."),
                Remedy("Anti-Transpirant", null, "Reduces water loss."),
                Remedy("Fungicide Mix", null, "Prevents fungal infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray for immunity."),
                Remedy(null, "Jeevamrut", "Apply post-damage."),
                Remedy(null, "Tulsi Extract", "Spray leaves."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch."),
                Remedy(null, "Compost Tea", "Drench soil."),
                Remedy(null, "Biochar", "Mix with soil."),
                Remedy(null, "Drip Irrigation", "Maintain water supply.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply rhizobacteria."),
                Remedy(null, "Trichoderma", "Reduce secondary infections."),
                Remedy(null, "Azospirillum", "Boost root and nutrient uptake."),
                Remedy(null, "AM Fungi", "Enhances nutrient absorption.")
            )
        ),
        "cotton_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "1 ton/acre."),
                Remedy("Lime", null, "0.5–1 ton/acre."),
                Remedy("Zinc Sulphate", null, "25kg/acre."),
                Remedy("Sulphur", null, "20kg/acre.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg/acre."),
                Remedy(null, "Jeevamrut Soil Drench", "10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "1:10 dilution fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons/acre.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "5 tons/acre."),
                Remedy(null, "Vermicompost", "2–3 tons/acre."),
                Remedy(null, "Green Manure", "Plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "1–2 tons/acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduce soil pathogens."),
                Remedy(null, "AM Fungi", "Enhance nutrient uptake.")
            )
        ),
        "sugarcane_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chlorpyrifos", null, "Spray 2ml per liter water on affected areas."),
                Remedy("Imidacloprid", null, "0.3ml per liter water, repeat after 10 days."),
                Remedy("Thiamethoxam", null, "Apply 1g per liter solution in evening."),
                Remedy("Lambda-cyhalothrin", null, "Spray 1ml per liter, avoid windy days.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "5% solution weekly."),
                Remedy(null, "Garlic-Chili Extract", "Prepare extract and spray weekly."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric in water and spray.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray mild soap water."),
                Remedy(null, "Marigold Extract", "Blend and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, spray on plants."),
                Remedy(null, "Wood Ash Dust", "Sprinkle near pest areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybird Beetles", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids."),
                Remedy(null, "Beauveria bassiana", "Spray spores on plants."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "sugarcane_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray at vegetative stage."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre."),
                Remedy("NPK Mix", null, "Use recommended doses as per soil test."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray 3% solution."),
                Remedy(null, "Jeevamrut", "Apply 10L per acre weekly."),
                Remedy(null, "Neem Cake Powder", "Mix in soil before sowing."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench weekly."),
                Remedy(null, "Green Manure", "Incorporate before sowing."),
                Remedy(null, "FYM", "10 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Seed coating or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium", "Seed treatment before sowing."),
                Remedy(null, "Trichoderma", "Mix in soil.")
            )
        ),
        "sugarcane_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution to recover."),
                Remedy("Silicon Spray", null, "Strengthens stems and leaves."),
                Remedy("Anti-Transpirant", null, "Reduces water loss under stress."),
                Remedy("Fungicide Mix", null, "Prevents secondary fungal infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray to enhance immunity."),
                Remedy(null, "Jeevamrut", "Apply at root zone post-damage."),
                Remedy(null, "Tulsi Extract", "Spray leaves to reduce stress."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray to aid recovery.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Drench soil for microbial support."),
                Remedy(null, "Biochar", "Mix with soil to improve moisture retention."),
                Remedy(null, "Drip Irrigation", "Maintain water supply post-damage.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply Plant Growth Promoting Rhizobacteria."),
                Remedy(null, "Trichoderma", "Apply to reduce secondary infections."),
                Remedy(null, "Azospirillum", "Boost root and nutrient uptake."),
                Remedy(null, "AM Fungi", "Enhance nutrient and water absorption.")
            )
        ),
        "sugarcane_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "Apply 1 ton/acre for sodic soils."),
                Remedy("Lime", null, "0.5–1 ton/acre for acidic soils."),
                Remedy("Zinc Sulphate", null, "25kg per acre if deficient."),
                Remedy("Sulphur", null, "20kg per acre for sulphur-deficient soil.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg per acre into soil."),
                Remedy(null, "Jeevamrut Soil Drench", "Apply 10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 and apply fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons per acre as basal dose.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "Add 5 tons per acre."),
                Remedy(null, "Vermicompost", "2–3 tons per acre near roots."),
                Remedy(null, "Green Manure", "Grow and plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "Mix and apply 1–2 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Enhances nutrient uptake and root health.")
            )
        ),


        "tomoato_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chlorothalonil", null, "Spray 2ml per liter water on leaves."),
                Remedy("Imidacloprid", null, "0.3ml per liter water, repeat after 10 days."),
                Remedy("Lambda-cyhalothrin", null, "Spray 1ml per liter, avoid windy days."),
                Remedy("Carbofuran", null, "Apply 2kg per hectare in soil before sowing.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "Spray 5% solution weekly."),
                Remedy(null, "Garlic Extract", "Crush garlic, dilute, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric in water and spray."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10 and spray weekly.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray mild soap water."),
                Remedy(null, "Marigold Extract", "Blend flowers and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, spray."),
                Remedy(null, "Wood Ash Dusting", "Sprinkle near pest areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybugs", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids."),
                Remedy(null, "Beauveria bassiana", "Spray spores."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "tomoato_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray weekly."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre."),
                Remedy("NPK Mix", null, "Use as per soil test."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Apply 10L per acre weekly."),
                Remedy(null, "Panchagavya", "Spray 3% solution."),
                Remedy(null, "Neem Cake Powder", "Mix in soil before planting."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench weekly."),
                Remedy(null, "Green Manure", "Incorporate before planting."),
                Remedy(null, "FYM", "10 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Seed coating or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium", "Seed treatment."),
                Remedy(null, "Trichoderma", "Mix in soil.")
            )
        ),
        "tomoato_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution."),
                Remedy("Silicon Spray", null, "Strengthens stems and leaves."),
                Remedy("Anti-Transpirant", null, "Reduces water loss."),
                Remedy("Fungicide Mix", null, "Prevents secondary infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray leaves post-stress."),
                Remedy(null, "Jeevamrut", "Apply at root zone."),
                Remedy(null, "Tulsi Extract", "Spray weekly for recovery."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray leaves.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Drench soil weekly."),
                Remedy(null, "Biochar", "Mix with soil."),
                Remedy(null, "Drip Irrigation", "Maintain water supply.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply near root zone."),
                Remedy(null, "Trichoderma", "Apply as soil drench."),
                Remedy(null, "Azospirillum", "Boost root growth."),
                Remedy(null, "AM Fungi", "Enhance nutrient absorption.")
            )
        ),
        "tomoato_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "1 ton/acre for sodic soil."),
                Remedy("Lime", null, "0.5–1 ton/acre for acidic soil."),
                Remedy("Zinc Sulphate", null, "25kg per acre."),
                Remedy("Sulphur", null, "20kg per acre if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg/acre into soil."),
                Remedy(null, "Jeevamrut Soil Drench", "10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons per acre.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "5 tons per acre."),
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Green Manure", "Grow and plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "1–2 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Enhances nutrient uptake.")
            )
        ),

        "tomoato_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chlorothalonil", null, "Spray 2ml per liter water on leaves."),
                Remedy("Imidacloprid", null, "0.3ml per liter water, repeat after 10 days."),
                Remedy("Lambda-cyhalothrin", null, "Spray 1ml per liter, avoid windy days."),
                Remedy("Carbofuran", null, "Apply 2kg per hectare in soil before sowing.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "Spray 5% solution weekly."),
                Remedy(null, "Garlic Extract", "Crush garlic, dilute, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric in water and spray."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10 and spray weekly.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray mild soap water."),
                Remedy(null, "Marigold Extract", "Blend flowers and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, spray."),
                Remedy(null, "Wood Ash Dusting", "Sprinkle near pest areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybugs", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids."),
                Remedy(null, "Beauveria bassiana", "Spray spores."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "tomoato_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray weekly."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre."),
                Remedy("NPK Mix", null, "Use as per soil test."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Apply 10L per acre weekly."),
                Remedy(null, "Panchagavya", "Spray 3% solution."),
                Remedy(null, "Neem Cake Powder", "Mix in soil before planting."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench weekly."),
                Remedy(null, "Green Manure", "Incorporate before planting."),
                Remedy(null, "FYM", "10 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Seed coating or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium", "Seed treatment."),
                Remedy(null, "Trichoderma", "Mix in soil.")
            )
        ),
        "tomoato_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution."),
                Remedy("Silicon Spray", null, "Strengthens stems and leaves."),
                Remedy("Anti-Transpirant", null, "Reduces water loss."),
                Remedy("Fungicide Mix", null, "Prevents secondary infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray leaves post-stress."),
                Remedy(null, "Jeevamrut", "Apply at root zone."),
                Remedy(null, "Tulsi Extract", "Spray weekly for recovery."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray leaves.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Drench soil weekly."),
                Remedy(null, "Biochar", "Mix with soil."),
                Remedy(null, "Drip Irrigation", "Maintain water supply.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply near root zone."),
                Remedy(null, "Trichoderma", "Apply as soil drench."),
                Remedy(null, "Azospirillum", "Boost root growth."),
                Remedy(null, "AM Fungi", "Enhance nutrient absorption.")
            )
        ),
        "tomoato_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "1 ton/acre for sodic soil."),
                Remedy("Lime", null, "0.5–1 ton/acre for acidic soil."),
                Remedy("Zinc Sulphate", null, "25kg per acre."),
                Remedy("Sulphur", null, "20kg per acre if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg/acre into soil."),
                Remedy(null, "Jeevamrut Soil Drench", "10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons per acre.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "5 tons per acre."),
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Green Manure", "Grow and plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "1–2 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Enhances nutrient uptake.")
            )
        ),

        "chili_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chlorothalonil", null, "Spray 2ml per liter water on leaves."),
                Remedy("Imidacloprid", null, "0.3ml per liter water, repeat after 10 days."),
                Remedy("Lambda-cyhalothrin", null, "Spray 1ml per liter, avoid windy days."),
                Remedy("Carbofuran", null, "Apply 2kg per hectare in soil before sowing.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "Spray 5% solution weekly."),
                Remedy(null, "Garlic Extract", "Crush garlic, dilute, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric in water and spray."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10 and spray weekly.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray mild soap water."),
                Remedy(null, "Marigold Extract", "Blend flowers and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, spray."),
                Remedy(null, "Wood Ash Dusting", "Sprinkle near pest areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybugs", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids."),
                Remedy(null, "Beauveria bassiana", "Spray spores."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "chili_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray weekly."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre."),
                Remedy("NPK Mix", null, "Use as per soil test."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Apply 10L per acre weekly."),
                Remedy(null, "Panchagavya", "Spray 3% solution."),
                Remedy(null, "Neem Cake Powder", "Mix in soil before planting."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench weekly."),
                Remedy(null, "Green Manure", "Incorporate before planting."),
                Remedy(null, "FYM", "10 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Seed coating or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium", "Seed treatment."),
                Remedy(null, "Trichoderma", "Mix in soil.")
            )
        ),
        "chili_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution."),
                Remedy("Silicon Spray", null, "Strengthens stems and leaves."),
                Remedy("Anti-Transpirant", null, "Reduces water loss."),
                Remedy("Fungicide Mix", null, "Prevents secondary infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray leaves post-stress."),
                Remedy(null, "Jeevamrut", "Apply at root zone."),
                Remedy(null, "Tulsi Extract", "Spray weekly for recovery."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray leaves.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Drench soil weekly."),
                Remedy(null, "Biochar", "Mix with soil."),
                Remedy(null, "Drip Irrigation", "Maintain water supply.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply near root zone."),
                Remedy(null, "Trichoderma", "Apply as soil drench."),
                Remedy(null, "Azospirillum", "Boost root growth."),
                Remedy(null, "AM Fungi", "Enhance nutrient absorption.")
            )
        ),
        "chili_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "1 ton/acre for sodic soil."),
                Remedy("Lime", null, "0.5–1 ton/acre for acidic soil."),
                Remedy("Zinc Sulphate", null, "25kg per acre."),
                Remedy("Sulphur", null, "20kg per acre if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg/acre into soil."),
                Remedy(null, "Jeevamrut Soil Drench", "10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons per acre.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "5 tons per acre."),
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Green Manure", "Grow and plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "1–2 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Enhances nutrient uptake.")
            )
        ),

        "onion_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chlorothalonil", null, "Spray 2ml per liter water on leaves."),
                Remedy("Imidacloprid", null, "0.3ml per liter water, repeat after 10 days."),
                Remedy("Lambda-cyhalothrin", null, "Spray 1ml per liter, avoid windy days."),
                Remedy("Carbofuran", null, "Apply 2kg per hectare in soil before sowing.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "Spray 5% solution weekly."),
                Remedy(null, "Garlic Extract", "Crush garlic, dilute, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric in water and spray."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10 and spray weekly.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray mild soap water."),
                Remedy(null, "Marigold Extract", "Blend flowers and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, spray."),
                Remedy(null, "Wood Ash Dusting", "Sprinkle near pest areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybugs", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids."),
                Remedy(null, "Beauveria bassiana", "Spray spores."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "onion_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray weekly."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre."),
                Remedy("NPK Mix", null, "Use as per soil test."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Apply 10L per acre weekly."),
                Remedy(null, "Panchagavya", "Spray 3% solution."),
                Remedy(null, "Neem Cake Powder", "Mix in soil before planting."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench weekly."),
                Remedy(null, "Green Manure", "Incorporate before planting."),
                Remedy(null, "FYM", "10 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Seed coating or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium", "Seed treatment."),
                Remedy(null, "Trichoderma", "Mix in soil.")
            )
        ),
        "onion_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution."),
                Remedy("Silicon Spray", null, "Strengthens stems and leaves."),
                Remedy("Anti-Transpirant", null, "Reduces water loss."),
                Remedy("Fungicide Mix", null, "Prevents secondary infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray leaves post-stress."),
                Remedy(null, "Jeevamrut", "Apply at root zone."),
                Remedy(null, "Tulsi Extract", "Spray weekly for recovery."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray leaves.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Drench soil weekly."),
                Remedy(null, "Biochar", "Mix with soil."),
                Remedy(null, "Drip Irrigation", "Maintain water supply.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply near root zone."),
                Remedy(null, "Trichoderma", "Apply as soil drench."),
                Remedy(null, "Azospirillum", "Boost root growth."),
                Remedy(null, "AM Fungi", "Enhance nutrient absorption.")
            )
        ),
        "onion_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "1 ton/acre for sodic soil."),
                Remedy("Lime", null, "0.5–1 ton/acre for acidic soil."),
                Remedy("Zinc Sulphate", null, "25kg per acre."),
                Remedy("Sulphur", null, "20kg per acre if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg/acre into soil."),
                Remedy(null, "Jeevamrut Soil Drench", "10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons per acre.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "5 tons per acre."),
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Green Manure", "Grow and plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "1–2 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Enhances nutrient uptake.")
            )
        ),

        "coffee_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chlorothalonil", null, "Spray 2ml per liter water on leaves."),
                Remedy("Imidacloprid", null, "0.3ml per liter water, repeat after 10 days."),
                Remedy("Lambda-cyhalothrin", null, "Spray 1ml per liter, avoid windy days."),
                Remedy("Carbofuran", null, "Apply 2kg per hectare in soil before sowing.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "Spray 5% solution weekly."),
                Remedy(null, "Garlic Extract", "Crush garlic, dilute, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric in water and spray."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10 and spray weekly.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray mild soap water."),
                Remedy(null, "Marigold Extract", "Blend flowers and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, spray."),
                Remedy(null, "Wood Ash Dusting", "Sprinkle near pest areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybugs", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids."),
                Remedy(null, "Beauveria bassiana", "Spray spores."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "coffee_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray weekly."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre."),
                Remedy("NPK Mix", null, "Use as per soil test."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Apply 10L per acre weekly."),
                Remedy(null, "Panchagavya", "Spray 3% solution."),
                Remedy(null, "Neem Cake Powder", "Mix in soil before planting."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench weekly."),
                Remedy(null, "Green Manure", "Incorporate before planting."),
                Remedy(null, "FYM", "10 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Seed coating or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium", "Seed treatment."),
                Remedy(null, "Trichoderma", "Mix in soil.")
            )
        ),
        "coffee_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution."),
                Remedy("Silicon Spray", null, "Strengthens stems and leaves."),
                Remedy("Anti-Transpirant", null, "Reduces water loss."),
                Remedy("Fungicide Mix", null, "Prevents secondary infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray leaves post-stress."),
                Remedy(null, "Jeevamrut", "Apply at root zone."),
                Remedy(null, "Tulsi Extract", "Spray weekly for recovery."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray leaves.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Drench soil weekly."),
                Remedy(null, "Biochar", "Mix with soil."),
                Remedy(null, "Drip Irrigation", "Maintain water supply.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply near root zone."),
                Remedy(null, "Trichoderma", "Apply as soil drench."),
                Remedy(null, "Azospirillum", "Boost root growth."),
                Remedy(null, "AM Fungi", "Enhance nutrient absorption.")
            )
        ),
        "coffee_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "1 ton/acre for sodic soil."),
                Remedy("Lime", null, "0.5–1 ton/acre for acidic soil."),
                Remedy("Zinc Sulphate", null, "25kg per acre."),
                Remedy("Sulphur", null, "20kg per acre if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg/acre into soil."),
                Remedy(null, "Jeevamrut Soil Drench", "10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons per acre.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "5 tons per acre."),
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Green Manure", "Grow and plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "1–2 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Enhances nutrient uptake.")
            )
        ),
        "tea_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chlorothalonil", null, "Spray 2ml per liter water on leaves."),
                Remedy("Imidacloprid", null, "0.3ml per liter water, repeat after 10 days."),
                Remedy("Lambda-cyhalothrin", null, "Spray 1ml per liter, avoid windy days."),
                Remedy("Carbofuran", null, "Apply 2kg per hectare in soil before sowing.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "Spray 5% solution weekly."),
                Remedy(null, "Garlic Extract", "Crush garlic, dilute, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric in water and spray."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10 and spray weekly.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray mild soap water."),
                Remedy(null, "Marigold Extract", "Blend flowers and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, spray."),
                Remedy(null, "Wood Ash Dusting", "Sprinkle near pest areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybugs", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids."),
                Remedy(null, "Beauveria bassiana", "Spray spores."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "tea_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray weekly."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre."),
                Remedy("NPK Mix", null, "Use as per soil test."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Apply 10L per acre weekly."),
                Remedy(null, "Panchagavya", "Spray 3% solution."),
                Remedy(null, "Neem Cake Powder", "Mix in soil before planting."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench weekly."),
                Remedy(null, "Green Manure", "Incorporate before planting."),
                Remedy(null, "FYM", "10 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Seed coating or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium", "Seed treatment."),
                Remedy(null, "Trichoderma", "Mix in soil.")
            )
        ),
        "tea_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution."),
                Remedy("Silicon Spray", null, "Strengthens stems and leaves."),
                Remedy("Anti-Transpirant", null, "Reduces water loss."),
                Remedy("Fungicide Mix", null, "Prevents secondary infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray leaves post-stress."),
                Remedy(null, "Jeevamrut", "Apply at root zone."),
                Remedy(null, "Tulsi Extract", "Spray weekly for recovery."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray leaves.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Drench soil weekly."),
                Remedy(null, "Biochar", "Mix with soil."),
                Remedy(null, "Drip Irrigation", "Maintain water supply.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply near root zone."),
                Remedy(null, "Trichoderma", "Apply as soil drench."),
                Remedy(null, "Azospirillum", "Boost root growth."),
                Remedy(null, "AM Fungi", "Enhance nutrient absorption.")
            )
        ),
        "tea_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "1 ton/acre for sodic soil."),
                Remedy("Lime", null, "0.5–1 ton/acre for acidic soil."),
                Remedy("Zinc Sulphate", null, "25kg per acre."),
                Remedy("Sulphur", null, "20kg per acre if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg/acre into soil."),
                Remedy(null, "Jeevamrut Soil Drench", "10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons per acre.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "5 tons per acre."),
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Green Manure", "Grow and plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "1–2 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Enhances nutrient uptake.")
            )
        ),


        "apple_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chlorothalonil", null, "Spray 2ml per liter water on leaves."),
                Remedy("Imidacloprid", null, "0.3ml per liter water, repeat after 10 days."),
                Remedy("Lambda-cyhalothrin", null, "Spray 1ml per liter, avoid windy days."),
                Remedy("Carbofuran", null, "Apply 2kg per hectare in soil before sowing.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "Spray 5% solution weekly."),
                Remedy(null, "Garlic Extract", "Crush garlic, dilute, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric in water and spray."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10 and spray weekly.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray mild soap water."),
                Remedy(null, "Marigold Extract", "Blend flowers and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, spray."),
                Remedy(null, "Wood Ash Dusting", "Sprinkle near pest areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybugs", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids."),
                Remedy(null, "Beauveria bassiana", "Spray spores."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "apple_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray weekly."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre."),
                Remedy("NPK Mix", null, "Use as per soil test."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Apply 10L per acre weekly."),
                Remedy(null, "Panchagavya", "Spray 3% solution."),
                Remedy(null, "Neem Cake Powder", "Mix in soil before planting."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench weekly."),
                Remedy(null, "Green Manure", "Incorporate before planting."),
                Remedy(null, "FYM", "10 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Seed coating or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium", "Seed treatment."),
                Remedy(null, "Trichoderma", "Mix in soil.")
            )
        ),
        "apple_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution."),
                Remedy("Silicon Spray", null, "Strengthens stems and leaves."),
                Remedy("Anti-Transpirant", null, "Reduces water loss."),
                Remedy("Fungicide Mix", null, "Prevents secondary infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray leaves post-stress."),
                Remedy(null, "Jeevamrut", "Apply at root zone."),
                Remedy(null, "Tulsi Extract", "Spray weekly for recovery."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray leaves.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Drench soil weekly."),
                Remedy(null, "Biochar", "Mix with soil."),
                Remedy(null, "Drip Irrigation", "Maintain water supply.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply near root zone."),
                Remedy(null, "Trichoderma", "Apply as soil drench."),
                Remedy(null, "Azospirillum", "Boost root growth."),
                Remedy(null, "AM Fungi", "Enhance nutrient absorption.")
            )
        ),
        "apple_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "1 ton/acre for sodic soil."),
                Remedy("Lime", null, "0.5–1 ton/acre for acidic soil."),
                Remedy("Zinc Sulphate", null, "25kg per acre."),
                Remedy("Sulphur", null, "20kg per acre if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg/acre into soil."),
                Remedy(null, "Jeevamrut Soil Drench", "10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons per acre.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "5 tons per acre."),
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Green Manure", "Grow and plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "1–2 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Enhances nutrient uptake.")
            )
        ),

        "banana_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chlorothalonil", null, "Spray 2ml per liter water on leaves."),
                Remedy("Imidacloprid", null, "0.3ml per liter water, repeat after 10 days."),
                Remedy("Lambda-cyhalothrin", null, "Spray 1ml per liter, avoid windy days."),
                Remedy("Carbofuran", null, "Apply 2kg per hectare in soil before sowing.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "Spray 5% solution weekly."),
                Remedy(null, "Garlic Extract", "Crush garlic, dilute, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric in water and spray."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10 and spray weekly.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray mild soap water."),
                Remedy(null, "Marigold Extract", "Blend flowers and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, spray."),
                Remedy(null, "Wood Ash Dusting", "Sprinkle near pest areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybugs", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids."),
                Remedy(null, "Beauveria bassiana", "Spray spores."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "banana_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray weekly."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre."),
                Remedy("NPK Mix", null, "Use as per soil test."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Apply 10L per acre weekly."),
                Remedy(null, "Panchagavya", "Spray 3% solution."),
                Remedy(null, "Neem Cake Powder", "Mix in soil before planting."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench weekly."),
                Remedy(null, "Green Manure", "Incorporate before planting."),
                Remedy(null, "FYM", "10 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Seed coating or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium", "Seed treatment."),
                Remedy(null, "Trichoderma", "Mix in soil.")
            )
        ),
        "banana_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution."),
                Remedy("Silicon Spray", null, "Strengthens stems and leaves."),
                Remedy("Anti-Transpirant", null, "Reduces water loss."),
                Remedy("Fungicide Mix", null, "Prevents secondary infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray leaves post-stress."),
                Remedy(null, "Jeevamrut", "Apply at root zone."),
                Remedy(null, "Tulsi Extract", "Spray weekly for recovery."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray leaves.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Drench soil weekly."),
                Remedy(null, "Biochar", "Mix with soil."),
                Remedy(null, "Drip Irrigation", "Maintain water supply.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply near root zone."),
                Remedy(null, "Trichoderma", "Apply as soil drench."),
                Remedy(null, "Azospirillum", "Boost root growth."),
                Remedy(null, "AM Fungi", "Enhance nutrient absorption.")
            )
        ),
        "banana_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "1 ton/acre for sodic soil."),
                Remedy("Lime", null, "0.5–1 ton/acre for acidic soil."),
                Remedy("Zinc Sulphate", null, "25kg per acre."),
                Remedy("Sulphur", null, "20kg per acre if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg/acre into soil."),
                Remedy(null, "Jeevamrut Soil Drench", "10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons per acre.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "5 tons per acre."),
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Green Manure", "Grow and plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "1–2 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Enhances nutrient uptake.")
            )
        ),

        "mango_pest infestation" to mapOf(
            "chemical" to listOf(
                Remedy("Chlorothalonil", null, "Spray 2ml per liter water on leaves."),
                Remedy("Imidacloprid", null, "0.3ml per liter water, repeat after 10 days."),
                Remedy("Lambda-cyhalothrin", null, "Spray 1ml per liter, avoid windy days."),
                Remedy("Carbofuran", null, "Apply 2kg per hectare in soil before sowing.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Oil Spray", "Spray 5% solution weekly."),
                Remedy(null, "Garlic Extract", "Crush garlic, dilute, spray weekly."),
                Remedy(null, "Turmeric Water", "Mix turmeric in water and spray."),
                Remedy(null, "Cow Urine Solution", "Dilute 1:10 and spray weekly.")
            ),
            "organic" to listOf(
                Remedy(null, "Soap Solution", "Spray mild soap water."),
                Remedy(null, "Marigold Extract", "Blend flowers and spray weekly."),
                Remedy(null, "Tobacco Decoction", "Boil leaves, spray."),
                Remedy(null, "Wood Ash Dusting", "Sprinkle near pest areas.")
            ),
            "biological" to listOf(
                Remedy(null, "Ladybugs", "Release near infested areas."),
                Remedy(null, "Trichogramma", "Release egg parasitoids."),
                Remedy(null, "Beauveria bassiana", "Spray spores."),
                Remedy(null, "Beneficial Nematodes", "Apply to soil.")
            )
        ),
        "mango_poor growth" to mapOf(
            "chemical" to listOf(
                Remedy("Urea Spray", null, "2% solution spray weekly."),
                Remedy("DAP Fertilizer", null, "Apply 100kg/acre."),
                Remedy("NPK Mix", null, "Use as per soil test."),
                Remedy("Micronutrients", null, "Spray zinc, boron if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Jeevamrut", "Apply 10L per acre weekly."),
                Remedy(null, "Panchagavya", "Spray 3% solution."),
                Remedy(null, "Neem Cake Powder", "Mix in soil before planting."),
                Remedy(null, "Banana Extract", "Foliar spray to enhance growth.")
            ),
            "organic" to listOf(
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Compost Tea", "Drench weekly."),
                Remedy(null, "Green Manure", "Incorporate before planting."),
                Remedy(null, "FYM", "10 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Seed coating or soil drench."),
                Remedy(null, "PSB", "Apply with irrigation."),
                Remedy(null, "Rhizobium", "Seed treatment."),
                Remedy(null, "Trichoderma", "Mix in soil.")
            )
        ),
        "mango_weather damage" to mapOf(
            "chemical" to listOf(
                Remedy("Potassium Nitrate", null, "Spray 1% solution."),
                Remedy("Silicon Spray", null, "Strengthens stems and leaves."),
                Remedy("Anti-Transpirant", null, "Reduces water loss."),
                Remedy("Fungicide Mix", null, "Prevents secondary infection.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Panchagavya", "Spray leaves post-stress."),
                Remedy(null, "Jeevamrut", "Apply at root zone."),
                Remedy(null, "Tulsi Extract", "Spray weekly for recovery."),
                Remedy(null, "Aloe Vera Extract", "Dilute and spray leaves.")
            ),
            "organic" to listOf(
                Remedy(null, "Mulching", "Apply straw mulch to conserve moisture."),
                Remedy(null, "Compost Tea", "Drench soil weekly."),
                Remedy(null, "Biochar", "Mix with soil."),
                Remedy(null, "Drip Irrigation", "Maintain water supply.")
            ),
            "biological" to listOf(
                Remedy(null, "PGPR", "Apply near root zone."),
                Remedy(null, "Trichoderma", "Apply as soil drench."),
                Remedy(null, "Azospirillum", "Boost root growth."),
                Remedy(null, "AM Fungi", "Enhance nutrient absorption.")
            )
        ),
        "mango_soil issues" to mapOf(
            "chemical" to listOf(
                Remedy("Gypsum", null, "1 ton/acre for sodic soil."),
                Remedy("Lime", null, "0.5–1 ton/acre for acidic soil."),
                Remedy("Zinc Sulphate", null, "25kg per acre."),
                Remedy("Sulphur", null, "20kg per acre if deficient.")
            ),
            "ayurvedic" to listOf(
                Remedy(null, "Neem Cake", "Mix 250kg/acre into soil."),
                Remedy(null, "Jeevamrut Soil Drench", "10L per acre weekly."),
                Remedy(null, "Panchagavya Soil Spray", "Dilute 1:10 fortnightly."),
                Remedy(null, "Cow Dung Compost", "5–10 tons per acre.")
            ),
            "organic" to listOf(
                Remedy(null, "Compost", "5 tons per acre."),
                Remedy(null, "Vermicompost", "2–3 tons per acre."),
                Remedy(null, "Green Manure", "Grow and plough sunhemp/dhaincha."),
                Remedy(null, "Biochar + Compost", "1–2 tons per acre.")
            ),
            "biological" to listOf(
                Remedy(null, "Azospirillum", "Apply with irrigation."),
                Remedy(null, "PSB", "Increase phosphorus availability."),
                Remedy(null, "Trichoderma", "Reduces soil pathogens."),
                Remedy(null, "AM Fungi", "Enhances nutrient uptake.")
            )
        ),









        )

    fun getRemedies(crop: String, disease: String, remedyType: String): List<Remedy> {
        val key = "${crop.lowercase()}_${disease.lowercase()}"
        val remedies = remediesMap[key]?.get(remedyType)

        return if (!remedies.isNullOrEmpty()) {
            // Pick one random remedy from the list
            listOf(remedies.random(Random(System.currentTimeMillis())))
        } else {
            listOf(
                Remedy(
                    null,
                    "No static remedies available",
                    "No preloaded data for this crop-disease combination."
                )
            )
        }
    }

}
