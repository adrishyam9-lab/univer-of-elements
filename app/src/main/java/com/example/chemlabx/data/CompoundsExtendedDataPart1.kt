package com.example.chemlabx.data

import com.example.chemlabx.data.model.Atom3D
import com.example.chemlabx.data.model.BondType
import com.example.chemlabx.data.model.ChemicalBond
import com.example.chemlabx.data.model.Compound

object CompoundsExtendedDataPart1 {
    val compounds: List<Compound> = listOf(
        Compound(
            id = "nitric_acid",
            name = "Nitric Acid",
            iupacName = "Nitric Acid",
            formula = "HNO₃",
            category = "Inorganic Strong Acid",
            molarMass = 63.01,
            geometry = "Planar around Nitrogen",
            bondAngles = "130.0° / 115.0°",
            polarity = "Polar (Dipole Moment: 2.17 D)",
            functionalGroups = listOf("Nitro (-NO₂) / Mineral Acid"),
            atoms = listOf(
                Atom3D("N", 0f, 0f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("O", 0f, 1.22f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.05f, -0.61f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", -1.18f, -0.61f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", -1.7f, 0.1f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 120),
                ChemicalBond(0, 2, 1, BondType.COORDINATE, 122),
                ChemicalBond(0, 3, 1, BondType.COVALENT_POLAR, 140),
                ChemicalBond(3, 4, 1, BondType.COVALENT_POLAR, 96)
            ),
            lewisNotes = "Resonance hybrid with formal charge +1 on nitrogen and delocalized negative charge over terminal oxygens.",
            description = "Highly corrosive mineral acid used primarily for production of fertilizers (ammonium nitrate) and explosives (TNT, nitroglycerin).",
            safetyInfo = "Strong oxidizer causing severe chemical burns and toxic brown nitrogen dioxide (NO₂) fumes.",
            bondExplanation = "Planar resonance-stabilized system where N-O bonds exhibit partial double bond character.",
            pubchemCid = 944,
            casNumber = "7697-37-2"
        ),
        Compound(
            id = "phosphoric_acid",
            name = "Phosphoric Acid",
            iupacName = "Orthophosphoric Acid",
            formula = "H₃PO₄",
            category = "Inorganic Mineral Acid",
            molarMass = 97.994,
            geometry = "Tetrahedral around Phosphorus",
            bondAngles = "109.5°",
            polarity = "Polar",
            functionalGroups = listOf("Phosphate / Triprotic Acid"),
            atoms = listOf(
                Atom3D("P", 0f, 0f, 0f, 0xFFF97316, 1.2f),
                Atom3D("O", 0f, 1.5f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.4f, -0.5f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", -0.7f, -0.5f, 1.2f, 0xFFEF4444, 1.1f),
                Atom3D("O", -0.7f, -0.5f, -1.2f, 0xFFEF4444, 1.1f),
                Atom3D("H", 1.8f, 0.2f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.0f, 0.2f, 1.4f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.0f, 0.2f, -1.4f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 152),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 157),
                ChemicalBond(0, 3, 1, BondType.COVALENT_POLAR, 157),
                ChemicalBond(0, 4, 1, BondType.COVALENT_POLAR, 157),
                ChemicalBond(2, 5, 1, BondType.COVALENT_POLAR, 96),
                ChemicalBond(3, 6, 1, BondType.COVALENT_POLAR, 96),
                ChemicalBond(4, 7, 1, BondType.COVALENT_POLAR, 96)
            ),
            lewisNotes = "Triprotic acid containing one P=O double bond and three P-OH single bonds.",
            description = "Common acidulant in carbonated beverages (cola) and precursor to agricultural phosphate fertilizers.",
            safetyInfo = "Causes skin and eye irritation; non-flammable.",
            bondExplanation = "Phosphorus utilizes sp³ hybridization with d-orbital participation to form a coordinate or double bond to oxygen.",
            pubchemCid = 1004,
            casNumber = "7664-38-2"
        ),
        Compound(
            id = "hydrofluoric_acid",
            name = "Hydrogen Fluoride",
            iupacName = "Fluorane",
            formula = "HF",
            category = "Inorganic Acid / Halide",
            molarMass = 20.01,
            geometry = "Linear Diatomic",
            bondAngles = "180.0°",
            polarity = "Extremely Polar (Dipole Moment: 1.82 D)",
            functionalGroups = listOf("Halide"),
            atoms = listOf(
                Atom3D("H", -0.46f, 0f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("F", 0.46f, 0f, 0f, 0xFF10B981, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 92)
            ),
            lewisNotes = "Single covalent bond with 3 lone electron pairs on fluorine.",
            description = "Fuming liquid that uniquely dissolves glass (silicon dioxide). Industrial precursor to Teflon.",
            safetyInfo = "EXTREME TOXICITY HAZARD: Penetrates skin to precipitate calcium ions in bones, causing cardiac arrest. Calcium gluconate antidote required.",
            bondExplanation = "Fluorine is the most electronegative element (3.98), creating an extraordinarily strong polar covalent bond (568 kJ/mol) with extensive hydrogen bonding.",
            pubchemCid = 14917,
            casNumber = "7664-39-3",
            isDangerousSimulationOnly = true
        ),
        Compound(
            id = "carbonic_acid",
            name = "Carbonic Acid",
            iupacName = "Carbonic Acid",
            formula = "H₂CO₃",
            category = "Inorganic Diprotic Acid",
            molarMass = 62.03,
            geometry = "Planar around Carbon",
            bondAngles = "120.0°",
            polarity = "Polar",
            functionalGroups = listOf("Carbonate / Diprotic Acid"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 0f, 1.25f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.15f, -0.6f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", -1.15f, -0.6f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", 1.8f, -0.1f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.8f, -0.1f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 120),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 135),
                ChemicalBond(0, 3, 1, BondType.COVALENT_POLAR, 135),
                ChemicalBond(2, 4, 1, BondType.COVALENT_POLAR, 96),
                ChemicalBond(3, 5, 1, BondType.COVALENT_POLAR, 96)
            ),
            lewisNotes = "Central sp² carbon with one C=O double bond and two C-OH single bonds. Readily decomposes into CO₂ and H₂O.",
            description = "Formed when CO₂ dissolves in water. Key biological buffer system maintaining human blood pH at exactly 7.4.",
            safetyInfo = "Non-hazardous weak acid; responsible for the effervescence in carbonated water.",
            bondExplanation = "Weak diprotic acid in dynamic equilibrium with dissolved carbon dioxide: CO₂ + H₂O ⇌ H₂CO₃.",
            pubchemCid = 767,
            casNumber = "463-79-6"
        ),
        Compound(
            id = "potassium_hydroxide",
            name = "Potassium Hydroxide",
            iupacName = "Potassium Hydroxide",
            formula = "KOH",
            category = "Inorganic Strong Base",
            molarMass = 56.11,
            geometry = "Ionic Crystal",
            bondAngles = "180.0°",
            polarity = "Ionic",
            functionalGroups = listOf("Hydroxide (OH⁻)"),
            atoms = listOf(
                Atom3D("K", -1.2f, 0f, 0f, 0xFF8B5CF6, 1.2f),
                Atom3D("O", 0.6f, 0f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", 1.5f, 0f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 270),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 96)
            ),
            lewisNotes = "K⁺ and [:O-H]⁻ ionic pair with complete 3d⁰/4s⁰ noble gas argon configuration for K⁺.",
            description = "Commonly called caustic potash. Widely used for making soft liquid soaps, alkaline battery electrolytes, and biodiesel.",
            safetyInfo = "Severe caustic; causes rapid, deep chemical burns.",
            bondExplanation = "Potassium has lower electronegativity than sodium (0.82 vs 0.93), making KOH even more basic and nucleophilic than NaOH.",
            pubchemCid = 14797,
            casNumber = "1310-58-3"
        ),
        Compound(
            id = "calcium_hydroxide",
            name = "Calcium Hydroxide",
            iupacName = "Calcium Dihydroxide",
            formula = "Ca(OH)₂",
            category = "Inorganic Base",
            molarMass = 74.09,
            geometry = "Trigonal Layered Crystal",
            bondAngles = "180.0°",
            polarity = "Ionic",
            functionalGroups = listOf("Hydroxide"),
            atoms = listOf(
                Atom3D("Ca", 0f, 0f, 0f, 0xFFF59E0B, 1.2f),
                Atom3D("O", 0f, 1.4f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 0f, -1.4f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", 0f, 2.3f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 0f, -2.3f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 240),
                ChemicalBond(0, 2, 1, BondType.IONIC, 240),
                ChemicalBond(1, 3, 1, BondType.COVALENT_POLAR, 96),
                ChemicalBond(2, 4, 1, BondType.COVALENT_POLAR, 96)
            ),
            lewisNotes = "Ca²⁺ with two [:O-H]⁻ anions. Clear aqueous solution is known as lime water.",
            description = "Known as slaked lime. Used in mortar, plaster, water treatment, and as the classic laboratory lime water test for CO₂.",
            safetyInfo = "Causes skin irritation and serious eye damage.",
            bondExplanation = "Strongly ionic lattice between divalent Ca²⁺ and two univalent OH⁻ ions.",
            pubchemCid = 6093208,
            casNumber = "1305-62-0"
        ),
        Compound(
            id = "calcium_carbonate",
            name = "Calcium Carbonate",
            iupacName = "Calcium Carbonate",
            formula = "CaCO₃",
            category = "Inorganic Salt / Carbonate",
            molarMass = 100.09,
            geometry = "Trigonal Planar Anion in Calcite Lattice",
            bondAngles = "120.0°",
            polarity = "Ionic",
            functionalGroups = listOf("Carbonate (CO₃²⁻)"),
            atoms = listOf(
                Atom3D("Ca", -1.4f, 0f, 0f, 0xFFF59E0B, 1.2f),
                Atom3D("C", 0.7f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 0.7f, 1.28f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 0.7f, -1.28f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.8f, 0f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 250),
                ChemicalBond(1, 2, 2, BondType.COVALENT_POLAR, 128),
                ChemicalBond(1, 3, 1, BondType.COVALENT_POLAR, 128),
                ChemicalBond(1, 4, 1, BondType.COVALENT_POLAR, 128)
            ),
            lewisNotes = "Resonance hybrid over three equivalent C-O bonds in the carbonate dianion with bond order 1.33.",
            description = "Main component of chalk, limestone, marble, and eggshells. Standard antacid and building material.",
            safetyInfo = "Non-hazardous mineral powder.",
            bondExplanation = "High lattice energy accounts for its insolubility in pure water (Ksp = 3.3 × 10⁻⁹).",
            pubchemCid = 10112,
            casNumber = "471-34-1"
        ),
        Compound(
            id = "sodium_bicarbonate",
            name = "Sodium Bicarbonate",
            iupacName = "Sodium Hydrogen Carbonate",
            formula = "NaHCO₃",
            category = "Inorganic Salt / Buffer",
            molarMass = 84.007,
            geometry = "Planar Hydrogen Carbonate Chain",
            bondAngles = "120.0°",
            polarity = "Ionic / Polar",
            functionalGroups = listOf("Bicarbonate (HCO₃⁻)"),
            atoms = listOf(
                Atom3D("Na", -1.4f, 0f, 0f, 0xFF8B5CF6, 1.0f),
                Atom3D("C", 0.6f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 0.6f, 1.25f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 0.6f, -1.25f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.7f, 0f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", 2.4f, 0.4f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 240),
                ChemicalBond(1, 2, 2, BondType.COVALENT_POLAR, 122),
                ChemicalBond(1, 3, 1, BondType.COVALENT_POLAR, 134),
                ChemicalBond(1, 4, 1, BondType.COVALENT_POLAR, 134),
                ChemicalBond(4, 5, 1, BondType.COVALENT_POLAR, 96)
            ),
            lewisNotes = "Na⁺ and [HO-CO₂]⁻. The bicarbonate ion acts as an amphiprotic buffer species.",
            description = "Baking soda. Household leavening agent that releases CO₂ gas upon heating or acid treatment.",
            safetyInfo = "Food grade non-hazardous compound.",
            bondExplanation = "Amphoteric property allows it to neutralize both strong acids and strong bases.",
            pubchemCid = 516892,
            casNumber = "144-55-8"
        ),
        Compound(
            id = "potassium_nitrate",
            name = "Potassium Nitrate",
            iupacName = "Potassium Nitrate",
            formula = "KNO₃",
            category = "Inorganic Salt / Oxidizer",
            molarMass = 101.103,
            geometry = "Orthorhombic Ionic Crystal",
            bondAngles = "120.0°",
            polarity = "Ionic",
            functionalGroups = listOf("Nitrate (NO₃⁻)"),
            atoms = listOf(
                Atom3D("K", -1.4f, 0f, 0f, 0xFF8B5CF6, 1.2f),
                Atom3D("N", 0.6f, 0f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("O", 0.6f, 1.22f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 0.6f, -1.22f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.7f, 0f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 280),
                ChemicalBond(1, 2, 2, BondType.COVALENT_POLAR, 122),
                ChemicalBond(1, 3, 1, BondType.COVALENT_POLAR, 122),
                ChemicalBond(1, 4, 1, BondType.COVALENT_POLAR, 122)
            ),
            lewisNotes = "K⁺ with planar NO₃⁻ resonance hybrid carrying delocalized -1 negative charge.",
            description = "Saltpeter. Essential component of traditional black powder (gunpowder), fireworks, and agricultural fertilizer.",
            safetyInfo = "Strong oxidizer; accelerates combustion of organic materials.",
            bondExplanation = "Electrostatic attraction between K⁺ and symmetrically resonant NO₃⁻ ions.",
            pubchemCid = 24434,
            casNumber = "7757-79-1"
        ),
        Compound(
            id = "potassium_permanganate",
            name = "Potassium Permanganate",
            iupacName = "Potassium Permanganate",
            formula = "KMnO₄",
            category = "Inorganic Strong Oxidizer",
            molarMass = 158.034,
            geometry = "Tetrahedral Anion",
            bondAngles = "109.5°",
            polarity = "Ionic",
            functionalGroups = listOf("Permanganate (MnO₄⁻)"),
            atoms = listOf(
                Atom3D("K", -1.4f, 0f, 0f, 0xFF8B5CF6, 1.2f),
                Atom3D("Mn", 0.7f, 0f, 0f, 0xFF7C3AED, 1.2f),
                Atom3D("O", 0.7f, 1.4f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 0.7f, -1.4f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 1.9f, 0f, 0.7f, 0xFFEF4444, 1.0f),
                Atom3D("O", 1.9f, 0f, -0.7f, 0xFFEF4444, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 260),
                ChemicalBond(1, 2, 2, BondType.COVALENT_POLAR, 163),
                ChemicalBond(1, 3, 2, BondType.COVALENT_POLAR, 163),
                ChemicalBond(1, 4, 2, BondType.COVALENT_POLAR, 163),
                ChemicalBond(1, 5, 1, BondType.COVALENT_POLAR, 163)
            ),
            lewisNotes = "Manganese in +7 maximum oxidation state with tetrahedral oxygen coordination.",
            description = "Deep purple crystalline solid used as a powerful laboratory oxidizing agent, disinfectant, and Baeyer's test reagent.",
            safetyInfo = "Potent oxidizer; stains skin brown with MnO₂ deposits.",
            bondExplanation = "Intense purple color is due to ligand-to-metal charge transfer (LMCT) transitions from oxygen 2p orbitals to manganese 3d orbitals.",
            pubchemCid = 516875,
            casNumber = "7722-64-7"
        ),
        Compound(
            id = "potassium_dichromate",
            name = "Potassium Dichromate",
            iupacName = "Potassium Dichromate",
            formula = "K₂Cr₂O₇",
            category = "Inorganic Strong Oxidizer",
            molarMass = 294.185,
            geometry = "Two Corner-Sharing Tetrahedra",
            bondAngles = "126.0° (Cr-O-Cr bridge)",
            polarity = "Ionic",
            functionalGroups = listOf("Dichromate (Cr₂O₇²⁻)"),
            atoms = listOf(
                Atom3D("Cr", -0.9f, 0f, 0f, 0xFFF97316, 1.2f),
                Atom3D("Cr", 0.9f, 0f, 0f, 0xFFF97316, 1.2f),
                Atom3D("O", 0f, 0.8f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", -1.6f, 0.8f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", -1.6f, -0.8f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 1.6f, 0.8f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 1.6f, -0.8f, 0f, 0xFFEF4444, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 178),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 178),
                ChemicalBond(0, 3, 2, BondType.COVALENT_POLAR, 161),
                ChemicalBond(0, 4, 2, BondType.COVALENT_POLAR, 161),
                ChemicalBond(1, 5, 2, BondType.COVALENT_POLAR, 161),
                ChemicalBond(1, 6, 2, BondType.COVALENT_POLAR, 161)
            ),
            lewisNotes = "Two CrO₄ tetrahedra sharing a central bridging oxygen atom. Both chromium atoms are in +6 oxidation state.",
            description = "Bright orange-red crystalline oxidizer used in classic alcohol breathalyzer tests, leather tanning, and wood staining.",
            safetyInfo = "Carcinogenic, mutagenic, and highly toxic hexavalent chromium (Cr VI).",
            bondExplanation = "Orange color arises from ligand-to-metal charge transfer transitions; turns green upon reduction to Cr³⁺.",
            pubchemCid = 24502,
            casNumber = "7778-50-9",
            isDangerousSimulationOnly = true
        ),
        Compound(
            id = "silver_nitrate",
            name = "Silver Nitrate",
            iupacName = "Silver Nitrate",
            formula = "AgNO₃",
            category = "Inorganic Salt / Silver Salt",
            molarMass = 169.87,
            geometry = "Orthorhombic Ionic Crystal",
            bondAngles = "120.0°",
            polarity = "Ionic",
            functionalGroups = listOf("Ag⁺ Cation", "Nitrate NO₃⁻"),
            atoms = listOf(
                Atom3D("Ag", -1.2f, 0f, 0f, 0xFF94A3B8, 1.2f),
                Atom3D("N", 0.6f, 0f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("O", 0.6f, 1.22f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 0.6f, -1.22f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 1.7f, 0f, 0f, 0xFFEF4444, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 250),
                ChemicalBond(1, 2, 2, BondType.COVALENT_POLAR, 122),
                ChemicalBond(1, 3, 1, BondType.COVALENT_POLAR, 122),
                ChemicalBond(1, 4, 1, BondType.COVALENT_POLAR, 122)
            ),
            lewisNotes = "Ag⁺ and NO₃⁻. Readily precipitates insoluble halides (AgCl white, AgBr pale yellow, AgI bright yellow).",
            description = "Historically known as lunar caustic. Used in photography, silver mirror Tollens' tests, and wound cauterization.",
            safetyInfo = "Causes dark black skin stains upon photochemical reduction to metallic silver.",
            bondExplanation = "Monovalent Ag⁺ has high affinity for halide ions due to strong covalent polarization.",
            pubchemCid = 24470,
            casNumber = "7761-88-8"
        ),
        Compound(
            id = "barium_sulfate",
            name = "Barium Sulfate",
            iupacName = "Barium Sulfate",
            formula = "BaSO₄",
            category = "Inorganic Insoluble Salt",
            molarMass = 233.39,
            geometry = "Orthorhombic Barite Lattice",
            bondAngles = "109.5°",
            polarity = "Ionic",
            functionalGroups = listOf("Ba²⁺ Cation", "Sulfate SO₄²⁻"),
            atoms = listOf(
                Atom3D("Ba", -1.4f, 0f, 0f, 0xFF14B8A6, 1.3f),
                Atom3D("S", 0.8f, 0f, 0f, 0xFFEAB308, 1.15f),
                Atom3D("O", 0.8f, 1.4f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 0.8f, -1.4f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 2.0f, 0f, 0.7f, 0xFFEF4444, 1.0f),
                Atom3D("O", 2.0f, 0f, -0.7f, 0xFFEF4444, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 280),
                ChemicalBond(1, 2, 2, BondType.COVALENT_POLAR, 147),
                ChemicalBond(1, 3, 2, BondType.COVALENT_POLAR, 147),
                ChemicalBond(1, 4, 1, BondType.COVALENT_POLAR, 147),
                ChemicalBond(1, 5, 1, BondType.COVALENT_POLAR, 147)
            ),
            lewisNotes = "Ba²⁺ with tetrahedral SO₄²⁻. Extremely insoluble in water and digestive acids (Ksp = 1.1 × 10⁻¹⁰).",
            description = "Used as an X-ray radiocontrast agent (barium meal) to image the human gastrointestinal tract.",
            safetyInfo = "Safe to swallow for medical imaging due to zero aqueous solubility, despite free Ba²⁺ being toxic.",
            bondExplanation = "Enormous lattice energy prevents dissociation in water.",
            pubchemCid = 24414,
            casNumber = "7727-43-7"
        ),
        Compound(
            id = "ammonium_nitrate",
            name = "Ammonium Nitrate",
            iupacName = "Azanium Nitrate",
            formula = "NH₄NO₃",
            category = "Inorganic Nitrogen Salt",
            molarMass = 80.043,
            geometry = "Ionic Crystal",
            bondAngles = "109.5° (NH₄⁺), 120.0° (NO₃⁻)",
            polarity = "Ionic",
            functionalGroups = listOf("Ammonium (NH₄⁺)", "Nitrate (NO₃⁻)"),
            atoms = listOf(
                Atom3D("N", -1.0f, 0f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("N", 1.0f, 0f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("O", 1.0f, 1.22f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 1.0f, -1.22f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 2.0f, 0f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("H", -1.0f, 1.0f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.9f, -0.3f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -0.5f, -0.3f, 0.8f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -0.5f, -0.3f, -0.8f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 280),
                ChemicalBond(0, 5, 1, BondType.COVALENT_POLAR, 102),
                ChemicalBond(0, 6, 1, BondType.COVALENT_POLAR, 102),
                ChemicalBond(0, 7, 1, BondType.COVALENT_POLAR, 102),
                ChemicalBond(0, 8, 1, BondType.COVALENT_POLAR, 102),
                ChemicalBond(1, 2, 2, BondType.COVALENT_POLAR, 122),
                ChemicalBond(1, 3, 1, BondType.COVALENT_POLAR, 122),
                ChemicalBond(1, 4, 1, BondType.COVALENT_POLAR, 122)
            ),
            lewisNotes = "Composed of tetrahedral NH₄⁺ cation and trigonal planar NO₃⁻ anion. Endothermic dissolution in water.",
            description = "High-nitrogen fertilizer and active ingredient in instant cold packs; can detonate under high heat and confinement.",
            safetyInfo = "Major explosion hazard if contaminated with fuels or subjected to extreme shock and fire.",
            bondExplanation = "Dissolving in water is strongly endothermic (ΔH = +25.7 kJ/mol), making it perfect for sports cold packs.",
            pubchemCid = 22985,
            casNumber = "6484-52-2"
        ),
        Compound(
            id = "calcium_chloride",
            name = "Calcium Chloride",
            iupacName = "Calcium Dichloride",
            formula = "CaCl₂",
            category = "Inorganic Salt / Halide",
            molarMass = 110.98,
            geometry = "Rutile-like Ionic Lattice",
            bondAngles = "90.0° / 180.0°",
            polarity = "Ionic",
            functionalGroups = listOf("Halide Salt"),
            atoms = listOf(
                Atom3D("Ca", 0f, 0f, 0f, 0xFFF59E0B, 1.2f),
                Atom3D("Cl", -1.2f, 0f, 0f, 0xFF10B981, 1.2f),
                Atom3D("Cl", 1.2f, 0f, 0f, 0xFF10B981, 1.2f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 270),
                ChemicalBond(0, 2, 1, BondType.IONIC, 270)
            ),
            lewisNotes = "[Ca]²⁺ and two [:Cl:]⁻ ions. Highly deliquescent salt.",
            description = "Industrial de-icing salt for winter roads and powerful drying agent (desiccant) in laboratories.",
            safetyInfo = "Exothermic dissolution releases substantial heat; causes eye and skin irritation.",
            bondExplanation = "High ionic charge density of Ca²⁺ causes immense hydration energy (-1577 kJ/mol).",
            pubchemCid = 5284359,
            casNumber = "10043-52-4"
        ),
        Compound(
            id = "magnesium_oxide",
            name = "Magnesium Oxide",
            iupacName = "Oxomagnesium",
            formula = "MgO",
            category = "Inorganic Basic Oxide",
            molarMass = 40.304,
            geometry = "Halite (Rock Salt) Lattice",
            bondAngles = "90.0°",
            polarity = "Ionic",
            functionalGroups = listOf("Basic Oxide"),
            atoms = listOf(
                Atom3D("Mg", -0.7f, 0f, 0f, 0xFFF59E0B, 1.1f),
                Atom3D("O", 0.7f, 0f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 212)
            ),
            lewisNotes = "[Mg]²⁺ [O]²⁻. Divalent ions create four times greater electrostatic attraction than monovalent NaCl.",
            description = "Magnesia. Refractory material with an astonishingly high melting point (2852°C) used in furnace linings and antacids.",
            safetyInfo = "Non-toxic white mineral powder; inhalation of fresh fumes causes metal fume fever.",
            bondExplanation = "Colossal lattice energy (3791 kJ/mol) due to doubly charged Mg²⁺ and O²⁻ ions.",
            pubchemCid = 14792,
            casNumber = "1309-48-4"
        ),
        Compound(
            id = "hydrogen_peroxide",
            name = "Hydrogen Peroxide",
            iupacName = "Dioxidane",
            formula = "H₂O₂",
            category = "Inorganic Peroxide / Oxidizer",
            molarMass = 34.015,
            geometry = "Non-planar Open Book",
            bondAngles = "94.8° (H-O-O), 111.5° (Dihedral angle)",
            polarity = "Polar (Dipole Moment: 2.26 D)",
            functionalGroups = listOf("Peroxy (-O-O-)"),
            atoms = listOf(
                Atom3D("O", -0.7f, 0f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 0.7f, 0f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", -1.1f, 0.8f, 0.4f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 1.1f, 0.8f, -0.4f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 146),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 97),
                ChemicalBond(1, 3, 1, BondType.COVALENT_POLAR, 97)
            ),
            lewisNotes = "Oxygen exists in unusual -1 oxidation state with a weak single peroxy bond.",
            description = "Antiseptic disinfectant, bleaching agent, and rocket propellant oxidizer. Catalytically decomposes into H₂O and O₂.",
            safetyInfo = "Concentrated solutions (>30%) are dangerous oxidizers causing skin blistering and boiling explosions.",
            bondExplanation = "The weak O-O single bond (146 kJ/mol) makes hydrogen peroxide thermodynamically unstable and chemically reactive.",
            pubchemCid = 784,
            casNumber = "7722-84-1"
        ),
        Compound(
            id = "ozone",
            name = "Ozone",
            iupacName = "Trioxygen",
            formula = "O₃",
            category = "Inorganic Allotrope / Oxidizer",
            molarMass = 47.998,
            geometry = "Bent",
            bondAngles = "116.8°",
            polarity = "Polar (Dipole Moment: 0.53 D)",
            functionalGroups = listOf("Allotrope of Oxygen"),
            atoms = listOf(
                Atom3D("O", 0f, 0.4f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", -1.1f, -0.4f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.1f, -0.4f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 128),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 128)
            ),
            lewisNotes = "Resonance hybrid of two structures: one double bond and one coordinate dative bond with formal charges.",
            description = "Pale blue gas with a sharp pungent chlorine-like odor. Forms Earth's stratospheric protective UV shield.",
            safetyInfo = "Toxic respiratory irritant at ground level; powerful oxidizing agent.",
            bondExplanation = "Both O-O bonds have identical intermediate length (128 pm) due to delocalized π resonance.",
            pubchemCid = 24823,
            casNumber = "10028-15-6"
        ),
        Compound(
            id = "sulfur_dioxide",
            name = "Sulfur Dioxide",
            iupacName = "Sulfur Dioxide",
            formula = "SO₂",
            category = "Inorganic Acidic Oxide",
            molarMass = 64.066,
            geometry = "Bent",
            bondAngles = "119.5°",
            polarity = "Polar (Dipole Moment: 1.63 D)",
            functionalGroups = listOf("Acidic Gas / Reductant"),
            atoms = listOf(
                Atom3D("S", 0f, 0.3f, 0f, 0xFFEAB308, 1.15f),
                Atom3D("O", -1.25f, -0.4f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.25f, -0.4f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 143),
                ChemicalBond(0, 2, 2, BondType.COVALENT_POLAR, 143)
            ),
            lewisNotes = "Sulfur has a lone electron pair and two resonance-stabilized S=O double bonds (expanded octet).",
            description = "Pungent suffocating gas produced by volcanoes and coal combustion. Major cause of acid rain (forming H₂SO₃).",
            safetyInfo = "Toxic choking gas causing bronchospasm and pulmonary edema.",
            bondExplanation = "Lone pair on sulfur repels bonding electrons, bending the molecule into a 119.5° angle.",
            pubchemCid = 1119,
            casNumber = "7446-09-5"
        ),
        Compound(
            id = "nitrogen_dioxide",
            name = "Nitrogen Dioxide",
            iupacName = "Nitrogen Dioxide",
            formula = "NO₂",
            category = "Inorganic Radical Oxide",
            molarMass = 46.0055,
            geometry = "Bent Radical",
            bondAngles = "134.3°",
            polarity = "Polar (Dipole Moment: 0.32 D)",
            functionalGroups = listOf("Free Radical Gas"),
            atoms = listOf(
                Atom3D("N", 0f, 0.3f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("O", -1.1f, -0.3f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.1f, -0.3f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 120),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 120)
            ),
            lewisNotes = "Odd-electron free radical molecule with one unpaired electron on nitrogen. Readily dimerizes to colorless N₂O₄.",
            description = "Reddish-brown toxic gas with a biting, pungent odor. Key smog component and intermediate in nitric acid manufacture.",
            safetyInfo = "Highly toxic lung irritant and air pollutant.",
            bondExplanation = "A single unpaired electron exerts less repulsion than a lone pair, widening the bond angle to 134.3°.",
            pubchemCid = 3032552,
            casNumber = "10102-44-0"
        ),
        Compound(
            id = "nitrous_oxide",
            name = "Nitrous Oxide",
            iupacName = "Dinitrogen Monoxide",
            formula = "N₂O",
            category = "Inorganic Neutral Oxide",
            molarMass = 44.013,
            geometry = "Linear Asymmetric",
            bondAngles = "180.0°",
            polarity = "Polar (Dipole Moment: 0.166 D)",
            functionalGroups = listOf("Neutral Oxide"),
            atoms = listOf(
                Atom3D("N", -1.12f, 0f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("N", 0f, 0f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("O", 1.19f, 0f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 3, BondType.COVALENT_POLAR, 112),
                ChemicalBond(1, 2, 1, BondType.COORDINATE, 119)
            ),
            lewisNotes = "Resonance hybrid: [:N≡N⁺-O⁻] ↔ [⁻N=N⁺=O]. Linear N-N-O sequence.",
            description = "Laughing gas. Widely used as a surgical and dental analgesic anesthetic, whipped cream aerosol propellant, and racing rocket oxidizer.",
            safetyInfo = "Inhalation causes euphoria and dissociation; asphyxiant in pure form.",
            bondExplanation = "Asymmetric linear molecule possessing a small dipole moment.",
            pubchemCid = 948,
            casNumber = "10024-97-2"
        ),
        Compound(
            id = "carbon_monoxide",
            name = "Carbon Monoxide",
            iupacName = "Carbon Monoxide",
            formula = "CO",
            category = "Inorganic Toxic Gas",
            molarMass = 28.01,
            geometry = "Linear Diatomic",
            bondAngles = "180.0°",
            polarity = "Slightly Polar (Dipole Moment: 0.122 D)",
            functionalGroups = listOf("Carbonyl"),
            atoms = listOf(
                Atom3D("C", -0.56f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 0.56f, 0f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 3, BondType.COORDINATE, 113)
            ),
            lewisNotes = "[:C⁻≡O⁺:]. Triple bond with two covalent bonds and one coordinate dative bond donated by oxygen lone pair.",
            description = "Silent killer. Binds to blood hemoglobin 210 times more tightly than oxygen, preventing oxygen transport.",
            safetyInfo = "LETHAL TOXICITY HAZARD: Colorless, odorless, tasteless gas. Carbon monoxide detectors are essential in homes.",
            bondExplanation = "Extraordinarily strong triple bond (1072 kJ/mol), the strongest chemical bond in neutral diatomic molecules.",
            pubchemCid = 281,
            casNumber = "630-08-0",
            isDangerousSimulationOnly = true
        ),
        Compound(
            id = "hydrogen_sulfide",
            name = "Hydrogen Sulfide",
            iupacName = "Sulfane",
            formula = "H₂S",
            category = "Inorganic Hydride / Toxic Gas",
            molarMass = 34.08,
            geometry = "Bent",
            bondAngles = "92.1°",
            polarity = "Polar (Dipole Moment: 0.97 D)",
            functionalGroups = listOf("Thiol / Sulfhydryl"),
            atoms = listOf(
                Atom3D("S", 0f, 0.2f, 0f, 0xFFEAB308, 1.2f),
                Atom3D("H", -0.96f, -0.6f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 0.96f, -0.6f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 134),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 134)
            ),
            lewisNotes = "Sulfur has two lone pairs and two single bonds to hydrogens.",
            description = "Notorious gas with the foul odor of rotten eggs. Naturally found in volcanic gases, natural gas, and sewage.",
            safetyInfo = "HIGHLY TOXIC: Paralyzes olfactory nerves at high concentrations, removing odor warning before lethal paralysis.",
            bondExplanation = "The 92.1° angle is close to 90°, indicating sulfur bonds primarily using unhybridized p-orbitals.",
            pubchemCid = 402,
            casNumber = "7783-06-4",
            isDangerousSimulationOnly = true
        ),
        Compound(
            id = "silicon_dioxide",
            name = "Silicon Dioxide (Quartz)",
            iupacName = "Dioxosilane",
            formula = "SiO₂",
            category = "Inorganic Covalent Network Solid",
            molarMass = 60.084,
            geometry = "Tetrahedral Network Lattice",
            bondAngles = "109.5° (Si), 144.0° (Si-O-Si bridge)",
            polarity = "Non-polar Network",
            functionalGroups = listOf("Silicate"),
            atoms = listOf(
                Atom3D("Si", 0f, 0f, 0f, 0xFF06B6D4, 1.2f),
                Atom3D("O", 0f, 1.6f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.5f, -0.5f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", -0.7f, -0.5f, 1.3f, 0xFFEF4444, 1.1f),
                Atom3D("O", -0.7f, -0.5f, -1.3f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 161),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 161),
                ChemicalBond(0, 3, 1, BondType.COVALENT_POLAR, 161),
                ChemicalBond(0, 4, 1, BondType.COVALENT_POLAR, 161)
            ),
            lewisNotes = "Giant covalent macromolecular network where each silicon is tetrahedrally bonded to 4 oxygens.",
            description = "Silica. Found naturally as quartz and beach sand; primary constituent of glass and semiconductor microchips.",
            safetyInfo = "Inhalation of fine crystalline silica dust causes irreversible silicosis lung disease.",
            bondExplanation = "Unlike carbon which forms gaseous O=C=O molecules, silicon prefers single bonds to oxygen, forming a diamond-like refractory network.",
            pubchemCid = 24261,
            casNumber = "7631-86-9"
        ),
        Compound(
            id = "iron_oxide_rust",
            name = "Iron(III) Oxide",
            iupacName = "Diiron Trioxide",
            formula = "Fe₂O₃",
            category = "Inorganic Transition Metal Oxide",
            molarMass = 159.69,
            geometry = "Corundum Crystal Lattice",
            bondAngles = "90.0° / 180.0°",
            polarity = "Ionic",
            functionalGroups = listOf("Fe³⁺ Cation", "Oxide O²⁻"),
            atoms = listOf(
                Atom3D("Fe", -0.9f, 0f, 0f, 0xFFD97706, 1.2f),
                Atom3D("Fe", 0.9f, 0f, 0f, 0xFFD97706, 1.2f),
                Atom3D("O", 0f, 1.3f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", -1.4f, -1.0f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.4f, -1.0f, 0f, 0xFFEF4444, 1.1f)
            ),
            bonds = listOf(
                ChemicalBond(0, 2, 1, BondType.IONIC, 195),
                ChemicalBond(1, 2, 1, BondType.IONIC, 195),
                ChemicalBond(0, 3, 1, BondType.IONIC, 195),
                ChemicalBond(1, 4, 1, BondType.IONIC, 195)
            ),
            lewisNotes = "Ionic network of Fe³⁺ cations with high spin 3d⁵ electron configuration and O²⁻ anions.",
            description = "Hematite / Rust. The reddish-brown product of iron corrosion in moist air; primary source of commercial iron ore.",
            safetyInfo = "Non-toxic mineral pigment (ochre, Venetian red).",
            bondExplanation = "Ferric ions possess high charge density, imparting high stability to the corundum crystal lattice.",
            pubchemCid = 518696,
            casNumber = "1309-37-1"
        )
    )
}
