package com.example.chemlabx.data

import com.example.chemlabx.data.model.Experiment
import com.example.chemlabx.data.model.ExperimentStep

object ExperimentData {
    val experiments: List<Experiment> = listOf(
        Experiment(
            id = "exp_acid_base_titration",
            title = "Acid-Base Neutralization Titration",
            category = "Acids & Bases",
            difficulty = "Class 9–10",
            objective = "Demonstrate neutralization of hydrochloric acid with sodium hydroxide using phenolphthalein indicator.",
            materials = listOf("0.1 M Hydrochloric Acid (HCl)", "0.1 M Sodium Hydroxide (NaOH)", "Phenolphthalein Indicator Solution", "Distilled Water"),
            equipmentRequired = listOf("250ml Erlenmeyer Flask", "Graduated Dropper / Burette", "Magnetic Stirrer", "pH Indicator Strip"),
            steps = listOf(
                ExperimentStep(1, "Measure Acid", "Add 50 ml of 0.1 M HCl into the Erlenmeyer flask.", "HCl", "250ml Flask", "Colorless acidic solution in flask (pH ~1.0)."),
                ExperimentStep(2, "Add Indicator", "Add 3 drops of phenolphthalein indicator to the acid solution.", "Phenolphthalein", "Dropper", "Solution remains completely clear and colorless in acid."),
                ExperimentStep(3, "Titrate with Base", "Slowly drop 0.1 M NaOH while swirling continuously.", "NaOH", "Burette / Flask", "Swirls of fleeting pink appear and vanish as base mixes."),
                ExperimentStep(4, "Reach Equivalence Point", "Add the final drop of NaOH until a persistent faint magenta pink color remains.", "NaOH", "Flask", "Permanent faint pink endpoint reached! Neutralization complete at pH 7.0.")
            ),
            balancedEquation = "HCl(aq) + NaOH(aq) → NaCl(aq) + H₂O(l)",
            expectedObservation = "The solution transitions from completely colorless to a sharp, beautiful faint pink when the exact stoichiometric equivalent of base neutralizes all acid protons.",
            scientificExplanation = "Phenolphthalein is a weak organic acid that is colorless in its protonated form (pH < 8.2) and vivid magenta-pink in its deprotonated quinoid conjugated form (pH > 8.2). When all H⁺ ions have combined with OH⁻ to form neutral H₂O, the next drop of base turns the indicator pink.",
            quizQuestion = "What color does phenolphthalein turn in an acidic solution?",
            quizOptions = listOf("Colorless", "Vivid Pink / Magenta", "Deep Blue", "Bright Yellow"),
            correctQuizIndex = 0,
            quizExplanation = "Phenolphthalein is completely colorless in acidic and neutral solutions (pH 0-8.2), and only turns pink in basic alkaline solutions (pH > 8.2)."
        ),
        Experiment(
            id = "exp_golden_rain",
            title = "Golden Rain: Lead Iodide Precipitation",
            category = "Precipitation & Qualitative Analysis",
            difficulty = "Class 11–12",
            objective = "Synthesize shimmering golden spangles of crystalline lead(II) iodide through double displacement and temperature-dependent recrystallization.",
            materials = listOf("0.1 M Lead(II) Nitrate Solution", "0.2 M Potassium Iodide Solution", "Distilled Water"),
            equipmentRequired = listOf("Beaker 250ml", "Bunsen Burner & Wire Gauze", "Glass Stirring Rod", "Ice Bath"),
            steps = listOf(
                ExperimentStep(1, "Prepare Lead Solution", "Pour 40 ml of clear Pb(NO₃)₂ solution into the beaker.", "Pb(NO3)2", "Beaker", "Completely clear, colorless liquid."),
                ExperimentStep(2, "Add Potassium Iodide", "Add 40 ml of clear KI solution into the beaker.", "KI", "Beaker", "Instant formation of an intense, opaque golden-yellow cloud of PbI₂!"),
                ExperimentStep(3, "Heat to Dissolve", "Place beaker over Bunsen burner and heat gently to 85°C with stirring.", "Heat", "Burner", "The yellow precipitate completely dissolves back into a transparent colorless solution!"),
                ExperimentStep(4, "Slow Cooling Spangles", "Remove from heat and allow solution to cool slowly into an ice bath.", "Cooling", "Ice Bath", "Spectacular hexagonal golden crystals precipitate and flutter down like glittering golden rain!")
            ),
            balancedEquation = "Pb(NO₃)₂(aq) + 2 KI(aq) → PbI₂(s)↓ + 2 KNO₃(aq)",
            expectedObservation = "The solution bursts into opaque golden yellow upon mixing, turns crystal-clear when boiled, and precipitates into breathtaking glittering golden spangles upon slow cooling.",
            scientificExplanation = "PbI₂ has a low solubility product at room temperature (Ksp = 9.8 × 10⁻⁹), but its solubility increases dramatically with temperature. Cooling slowly allows supersaturated Pb²⁺ and I⁻ ions to organize into large, reflective, planar hexagonal crystalline sheets that sparkle like gold leaf.",
            quizQuestion = "Why does the yellow precipitate disappear when the mixture is heated to 85°C?",
            quizOptions = listOf("PbI₂ decomposes into lead metal and iodine vapor", "The solubility of PbI₂ increases substantially at elevated temperature", "The water evaporates completely", "Potassium nitrate reacts with the glass beaker"),
            correctQuizIndex = 1,
            quizExplanation = "Lead iodide's dissolution is an endothermic process; according to Le Chatelier's principle, higher temperatures shift the equilibrium toward dissolved ions, increasing solubility by over 10-fold."
        ),
        Experiment(
            id = "exp_metal_displacement",
            title = "Single Displacement: Iron in Copper Sulfate",
            category = "Redox & Metals",
            difficulty = "Middle School",
            objective = "Observe the electrochemical displacement of copper metal from solution by an iron nail.",
            materials = listOf("Copper(II) Sulfate Pentahydrate (CuSO₄·5H₂O)", "Clean Iron Nails / Iron Filings", "Distilled Water"),
            equipmentRequired = listOf("Test Tubes & Rack", "Forceps / Tongs", "Sandpaper"),
            steps = listOf(
                ExperimentStep(1, "Prepare Copper Solution", "Dissolve blue CuSO₄ crystals in water inside a test tube.", "CuSO4", "Test Tube", "Bright royal blue liquid."),
                ExperimentStep(2, "Clean Iron Nail", "Polish iron nail with sandpaper to expose fresh metallic surface.", "Nail", "Sandpaper", "Shiny metallic gray nail."),
                ExperimentStep(3, "Immerse Iron", "Carefully lower the iron nail into the blue copper solution.", "Fe Nail", "Test Tube", "Within 30 seconds, the nail surface begins to darken and turn reddish-brown."),
                ExperimentStep(4, "Examine After 15 Minutes", "Lift the nail out with tongs and observe solution color.", "Observation", "Forceps", "Nail is coated in pure metallic copper; the solution has faded from blue to light pale green (FeSO₄).")
            ),
            balancedEquation = "Fe(s) + CuSO₄(aq) → FeSO₄(aq) + Cu(s)↓",
            expectedObservation = "The blue solution fades to pale green, and a thick reddish-brown coating of pure metallic copper deposits on the iron nail.",
            scientificExplanation = "Iron is more reactive than copper in the reactivity series. Iron atoms readily donate 2 electrons to Cu²⁺ ions in solution. Fe oxidizes to soluble Fe²⁺ (pale green), while Cu²⁺ reduces to insoluble elemental Cu atoms that plate out onto the nail.",
            quizQuestion = "Which element is oxidized during this single displacement reaction?",
            quizOptions = listOf("Copper (Cu²⁺)", "Iron (Fe)", "Sulfur (S)", "Oxygen (O)"),
            correctQuizIndex = 1,
            quizExplanation = "Iron loses two electrons (Fe → Fe²⁺ + 2e⁻). Loss of electrons is oxidation (OIL RIG: Oxidation Is Loss)."
        ),
        Experiment(
            id = "exp_hydrogen_evolution",
            title = "Hydrogen Gas Evolution & Pop Test",
            category = "Gas Evolution & Redox",
            difficulty = "Class 9–10",
            objective = "Generate hydrogen gas from the reaction between zinc and dilute hydrochloric acid, and verify with a squeaky pop acoustic test.",
            materials = listOf("Zinc Granules (Zn)", "2 M Hydrochloric Acid (HCl)", "Wooden Splints"),
            equipmentRequired = listOf("Test Tube with Delivery Tube", "Gas Collection Tube", "Matchbox / Lighter", "Safety Shield"),
            steps = listOf(
                ExperimentStep(1, "Add Zinc", "Place 3-4 granules of zinc metal into the bottom of the test tube.", "Zn Granules", "Test Tube", "Dry silver-gray zinc granules."),
                ExperimentStep(2, "Add Acid", "Pour 10 ml of 2 M hydrochloric acid over the zinc.", "HCl", "Test Tube", "Immediate vigorous bubbling and hissing! Zinc surface covered in gas bubbles."),
                ExperimentStep(3, "Collect Hydrogen Gas", "Invert an empty test tube over the delivery tube to collect gas by upward displacement.", "Gas Tube", "Delivery Tube", "Colorless gas fills the inverted tube, displacing air downward."),
                ExperimentStep(4, "Flame Pop Test", "Bring a lit wooden splint to the mouth of the collected gas tube.", "Lit Splint", "Test Tube Mouth", "A distinctive sharp squeaky 'POP!' sound rings out as the hydrogen combusts with a blue flash!")
            ),
            balancedEquation = "Zn(s) + 2 HCl(aq) → ZnCl₂(aq) + H₂(g)↑",
            expectedObservation = "Rapid effervescence on zinc granules, accompanied by an audible hiss and a classic high-pitched 'pop' when ignited.",
            scientificExplanation = "Zinc reduces hydrogen protons to diatomic H₂ gas. Because hydrogen is much less dense than air, it collects at the top of inverted tubes. When ignited, H₂ reacts explosively with atmospheric oxygen (2 H₂ + O₂ → 2 H₂O), creating a localized shockwave that produces the characteristic pop sound.",
            quizQuestion = "What acoustic sound confirms the presence of hydrogen gas during a flame test?",
            quizOptions = listOf("A loud hiss", "A sharp squeaky pop", "A dull thud", "Complete silence"),
            correctQuizIndex = 1,
            quizExplanation = "Hydrogen burns in air with a characteristic sharp squeaky 'pop' as it rapidly combines with atmospheric oxygen to form water vapor."
        ),
        Experiment(
            id = "exp_catalytic_h2o2",
            title = "Catalytic Decomposition of H₂O₂",
            category = "Catalysis & Thermochemistry",
            difficulty = "Class 9–10",
            objective = "Study how a solid catalyst (MnO₂) accelerates the decomposition of hydrogen peroxide into oxygen gas and water.",
            materials = listOf("6% Hydrogen Peroxide (H₂O₂)", "Manganese Dioxide Powder (MnO₂)", "Wooden Glowing Splint"),
            equipmentRequired = listOf("100ml Graduated Cylinder", "Spatula", "Thermometer", "Safety Goggles"),
            steps = listOf(
                ExperimentStep(1, "Pour Peroxide", "Pour 30 ml of 6% H₂O₂ into the graduated cylinder. Record baseline temperature.", "H₂O₂", "Cylinder", "Clear liquid, room temperature ~22°C. Virtually zero spontaneous bubbles."),
                ExperimentStep(2, "Add Catalyst", "Add a tiny spatula tip of black MnO₂ powder into the cylinder.", "MnO₂", "Spatula", "Instantaneous violent eruption of dense white steam and thousands of rushing bubbles!"),
                ExperimentStep(3, "Measure Heat", "Monitor the thermometer inside the foaming liquid.", "Thermometer", "Cylinder", "Temperature surges rapidly to over 70°C! Strongly exothermic."),
                ExperimentStep(4, "Test with Glowing Splint", "Insert a glowing wooden ember into the top of the cylinder.", "Glowing Splint", "Cylinder Mouth", "The glowing ember immediately reignites into a bright, blazing flame!")
            ),
            balancedEquation = "2 H₂O₂(aq) --[MnO₂]--> 2 H₂O(l) + O₂(g)↑",
            expectedObservation = "Violent foaming eruption with substantial heat release; pure oxygen gas evolved reignites a glowing wooden splint.",
            scientificExplanation = "MnO₂ acts as a heterogeneous catalyst providing an alternative reaction pathway with a dramatically lower activation energy. Oxygen gas produced relights glowing embers because combustion in pure O₂ proceeds far faster than in 21% atmospheric oxygen.",
            quizQuestion = "What happens to the manganese dioxide (MnO₂) catalyst at the conclusion of the reaction?",
            quizOptions = listOf("It is completely consumed and converted into manganese gas", "It remains chemically unchanged and can be recovered and reused", "It dissolves into soluble manganese ions", "It turns bright yellow"),
            correctQuizIndex = 1,
            quizExplanation = "A catalyst speeds up a chemical reaction without being consumed or permanently altered. It can be filtered out, washed, dried, and used again indefinitely."
        ),
        Experiment(
            id = "exp_universal_indicator_rainbow",
            title = "Universal Indicator pH Rainbow Spectrum",
            category = "Acids & Bases",
            difficulty = "Middle School",
            objective = "Observe the full continuous pH color spectrum (pH 1 to 14) using Universal Indicator across laboratory substances.",
            materials = listOf("0.1 M HCl (pH 1)", "Vinegar (pH 3)", "Coffee/Tea (pH 5)", "Distilled Water (pH 7)", "Baking Soda Solution (pH 9)", "Ammonia Solution (pH 11)", "0.1 M NaOH (pH 13)", "Universal Indicator Solution"),
            equipmentRequired = listOf("7 Test Tubes in Rack", "Droppers", "pH Color Chart"),
            steps = listOf(
                ExperimentStep(1, "Line Up Tubes", "Place 7 clean test tubes in a test tube rack and fill each with 10 ml of the designated test solution from pH 1 to 13.", "Test Solutions", "7-Tube Rack", "Seven tubes with clear or pale liquids."),
                ExperimentStep(2, "Add Indicator", "Add 3 drops of Universal Indicator to every tube.", "Universal Indicator", "Dropper", "Spectacular rainbow of colors emerges across the rack!"),
                ExperimentStep(3, "Compare pH Colors", "Match each test tube color against the standard pH scale.", "Observation", "Color Chart", "Tube 1: Red (pH 1), Tube 2: Orange (pH 3), Tube 3: Yellow (pH 5), Tube 4: Green (pH 7), Tube 5: Blue-Green (pH 9), Tube 6: Deep Blue (pH 11), Tube 7: Royal Violet (pH 13-14).")
            ),
            balancedEquation = "HIn + H₂O ⇌ In⁻ + H₃O⁺ (Multi-indicator equilibrium mixture)",
            expectedObservation = "A complete, stunning rainbow of color from fiery red (strong acid), yellow (weak acid), green (neutral), to deep blue and purple/violet (strong base).",
            scientificExplanation = "Universal indicator is a formulated mixture of dyes (including thymol blue, methyl red, bromothymol blue, and phenolphthalein). Each dye undergoes color transitions at different pH ranges, producing a smooth rainbow spectrum from pH 1 to 14.",
            quizQuestion = "What color does Universal Indicator display in pure distilled water at neutral pH 7?",
            quizOptions = listOf("Red", "Green", "Yellow", "Violet"),
            correctQuizIndex = 1,
            quizExplanation = "At neutral pH 7, Universal Indicator displays a distinct clean green color."
        )
    )
}
