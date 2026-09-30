package com.example.chemlabx.data

import com.example.chemlabx.data.model.LabChallenge
import com.example.chemlabx.data.model.QuizQuestion

object QuizData {
    val quizQuestions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = "q1",
            category = "Periodic Trends",
            difficulty = "Class 9–10",
            question = "Which of the following elements has the highest electronegativity on the Pauling scale?",
            options = listOf("Fluorine (F)", "Oxygen (O)", "Chlorine (Cl)", "Cesium (Cs)"),
            correctIndex = 0,
            explanation = "Fluorine has the highest electronegativity value (3.98) of all elements due to its high effective nuclear charge and very small atomic radius."
        ),
        QuizQuestion(
            id = "q2",
            category = "Periodic Trends",
            difficulty = "Class 11–12",
            question = "Why does atomic radius decrease as you move from left to right across a period?",
            options = listOf(
                "Electrons are added to higher principal energy levels",
                "Increasing effective nuclear charge (Z_eff) pulls electron shells closer to the nucleus",
                "Shielding effect increases drastically across a period",
                "Electrons repel each other more strongly"
            ),
            correctIndex = 1,
            explanation = "Across a period, protons are added to the nucleus while electrons enter the same principal energy shell. The resulting increase in effective nuclear charge pulls the electron cloud tighter toward the nucleus."
        ),
        QuizQuestion(
            id = "q3",
            category = "Chemical Bonding",
            difficulty = "Middle School",
            question = "What type of chemical bond is formed when valence electrons are transferred from a metal to a non-metal?",
            options = listOf("Covalent Bond", "Ionic Bond", "Metallic Bond", "Hydrogen Bond"),
            correctIndex = 1,
            explanation = "Ionic bonds are formed through the electrostatic attraction between oppositely charged ions created when a metal transfers electrons to a non-metal."
        ),
        QuizQuestion(
            id = "q4",
            category = "Chemical Bonding",
            difficulty = "Class 11–12",
            question = "What is the molecular geometry and bond angle of methane (CH₄)?",
            options = listOf("Trigonal Planar (120°)", "Tetrahedral (109.5°)", "Bent (104.5°)", "Trigonal Pyramidal (107°)"),
            correctIndex = 1,
            explanation = "Methane has 4 bonding pairs and 0 lone pairs on carbon (sp³ hybridization). According to VSEPR theory, these 4 electron pairs minimize repulsion by directing toward the vertices of a regular tetrahedron at 109.5°."
        ),
        QuizQuestion(
            id = "q5",
            category = "Reaction Types",
            difficulty = "Class 9–10",
            question = "What type of reaction is: 2 H₂ + O₂ → 2 H₂O?",
            options = listOf("Combination / Synthesis", "Decomposition", "Single Displacement", "Double Displacement"),
            correctIndex = 0,
            explanation = "Two simple reactants (hydrogen and oxygen) combine together to form a single chemical product (water), which defines a synthesis or combination reaction."
        ),
        QuizQuestion(
            id = "q6",
            category = "Acids & Bases",
            difficulty = "Middle School",
            question = "What is the pH value of a completely neutral pure water solution at 25°C?",
            options = listOf("0", "7", "14", "1"),
            correctIndex = 1,
            explanation = "At 25°C, pure water has [H⁺] = [OH⁻] = 1.0 × 10⁻⁷ M, which corresponds to a neutral pH of exactly 7."
        ),
        QuizQuestion(
            id = "q7",
            category = "Redox Chemistry",
            difficulty = "Class 11–12",
            question = "In the reaction Zn + CuSO₄ → ZnSO₄ + Cu, what is the oxidizing agent?",
            options = listOf("Metallic Zinc (Zn)", "Cu²⁺ ions in CuSO₄", "Sulfate ions (SO₄²⁻)", "Metallic Copper (Cu)"),
            correctIndex = 1,
            explanation = "The oxidizing agent is the species that gains electrons and is itself reduced. Cu²⁺ gains 2 electrons to form metallic copper (Cu²⁺ + 2e⁻ → Cu), making it the oxidizing agent."
        ),
        QuizQuestion(
            id = "q8",
            category = "Reaction Types",
            difficulty = "Undergraduate",
            question = "According to Le Chatelier's Principle, how will an exothermic equilibrium reaction (N₂ + 3H₂ ⇌ 2NH₃ + Heat) respond if temperature is increased?",
            options = listOf(
                "Shifts right to produce more ammonia",
                "Shifts left to favor the reactants and absorb added heat",
                "Equilibrium position remains completely unaffected",
                "Equilibrium constant (K) increases"
            ),
            correctIndex = 1,
            explanation = "In an exothermic reaction, heat is a product. Increasing temperature adds thermal stress; the system relieves this stress by shifting in the endothermic direction (to the left), consuming heat and decreasing ammonia yield."
        )
    )

    val labChallenges: List<LabChallenge> = listOf(
        LabChallenge(
            id = "chal_golden_mystery",
            title = "The Golden Precipitate Mystery",
            mysteryDescription = "A student combined two clear, colorless solutions in a test tube. Immediately, a dazzling bright yellow precipitate settled out.",
            clues = listOf(
                "One reactant is a salt of a heavy group 14 toxic metal.",
                "The other reactant is a potassium salt of a halogen with atomic number 53.",
                "Heating the test tube causes the yellow precipitate to dissolve completely into a clear liquid.",
                "Slow cooling yields sparkling golden spangles."
            ),
            possibleAnswers = listOf(
                "Lead(II) Iodide (PbI₂)",
                "Barium Sulfate (BaSO₄)",
                "Copper(II) Hydroxide (Cu(OH)₂)",
                "Silver Chloride (AgCl)"
            ),
            correctAnswerIndex = 0,
            resolutionExplanation = "Correct! The reaction is Pb(NO₃)₂ + 2 KI → PbI₂↓ + 2 KNO₃. Lead(II) iodide is famously known as the 'Golden Rain' precipitate due to its brilliant yellow color and temperature-dependent recrystallization."
        ),
        LabChallenge(
            id = "chal_gas_mystery",
            title = "The Extinguishing Gas Mystery",
            mysteryDescription = "A student dropped a white crystalline powder into a clear liquid. The mixture foamed vigorously with effervescence, cooled noticeably, and the gas produced extinguished a burning match.",
            clues = listOf(
                "The white powder contains sodium, hydrogen, carbon, and oxygen.",
                "The clear liquid is a common household weak acid.",
                "The gas produced turns limewater (calcium hydroxide) milky white.",
                "The process is endothermic."
            ),
            possibleAnswers = listOf(
                "Carbon Dioxide (CO₂) from Sodium Bicarbonate + Vinegar",
                "Hydrogen Gas (H₂) from Zinc + Acid",
                "Oxygen Gas (O₂) from Hydrogen Peroxide",
                "Chlorine Gas (Cl₂) from Bleach"
            ),
            correctAnswerIndex = 0,
            resolutionExplanation = "Spot on! Sodium bicarbonate (NaHCO₃) and acetic acid (vinegar) react to produce carbon dioxide gas (CO₂), which is denser than air and does not support combustion, instantly extinguishing the flame."
        ),
        LabChallenge(
            id = "chal_metal_tree",
            title = "The Transforming Nail",
            mysteryDescription = "A shiny iron nail left submerged in a vibrant royal blue solution turned reddish-brown, while the liquid gradually turned pale yellowish-green.",
            clues = listOf(
                "The blue color was due to hydrated transition metal cations with d⁹ electron configuration.",
                "Iron is higher than copper in the activity series.",
                "Elemental metal plated onto the surface of the nail."
            ),
            possibleAnswers = listOf(
                "Copper (Cu) deposited from Copper Sulfate by Iron displacement",
                "Rust (Fe₂O₃) caused by water and oxygen",
                "Silver (Ag) coating",
                "Gold (Au) plating"
            ),
            correctAnswerIndex = 0,
            resolutionExplanation = "Exactly right! Iron spontaneously displaces copper ions from copper(II) sulfate (Fe + CuSO₄ → FeSO₄ + Cu), depositing reddish-brown metallic copper while forming pale green iron(II) sulfate."
        )
    )
}
