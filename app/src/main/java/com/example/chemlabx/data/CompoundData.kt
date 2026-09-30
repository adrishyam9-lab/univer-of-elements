package com.example.chemlabx.data

import com.example.chemlabx.data.model.Atom3D
import com.example.chemlabx.data.model.BondType
import com.example.chemlabx.data.model.ChemicalBond
import com.example.chemlabx.data.model.Compound

object CompoundData {
    private val baseCompounds: List<Compound> = listOf(
        Compound(
            id = "water",
            name = "Water",
            iupacName = "Oxidane",
            formula = "H₂O",
            category = "Inorganic Oxide",
            molarMass = 18.015,
            geometry = "Bent (Angular)",
            bondAngles = "104.5°",
            polarity = "Polar (Dipole Moment: 1.85 D)",
            functionalGroups = listOf("Hydroxyl-like (O-H bonds)"),
            atoms = listOf(
                Atom3D("O", 0f, 0f, 0f, 0xFFEF4444, 1.2f),
                Atom3D("H", -0.75f, 0.58f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 0.75f, 0.58f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 96),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 96)
            ),
            lewisNotes = "Oxygen central atom with 2 single bonds to H and 2 lone pairs on oxygen, giving a steric number of 4 (sp³ hybridization).",
            description = "The universal solvent of life on Earth. Displays extraordinary hydrogen bonding, high surface tension, and anomalous expansion upon freezing.",
            safetyInfo = "Non-hazardous; essential for biological life.",
            bondExplanation = "Oxygen has an electronegativity of 3.44 while hydrogen is 2.20. The high electronegativity difference (ΔEN = 1.24) pulls electron density strongly toward oxygen, creating a partial negative charge (δ⁻) on oxygen and partial positive charges (δ⁺) on hydrogens, forming strong polar covalent bonds."
        ),
        Compound(
            id = "carbon_dioxide",
            name = "Carbon Dioxide",
            iupacName = "Carbon Dioxide",
            formula = "CO₂",
            category = "Inorganic Oxide",
            molarMass = 44.01,
            geometry = "Linear",
            bondAngles = "180.0°",
            polarity = "Non-polar (Dipoles cancel out)",
            functionalGroups = listOf("Carbonyl (C=O double bonds)"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", -1.16f, 0f, 0f, 0xFFEF4444, 1.15f),
                Atom3D("O", 1.16f, 0f, 0f, 0xFFEF4444, 1.15f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 116),
                ChemicalBond(0, 2, 2, BondType.COVALENT_POLAR, 116)
            ),
            lewisNotes = "Carbon shares two double bonds with both oxygens: O::C::O. No lone pairs on carbon; 2 lone pairs on each oxygen.",
            description = "Crucial greenhouse gas in Earth's atmosphere, essential for photosynthesis in plants and produced during respiration and combustion.",
            safetyInfo = "Colorless, odorless asphyxiant in high concentrations; sublimates to dry ice at -78.5°C.",
            bondExplanation = "Even though each C=O double bond is polar covalent, the molecule is symmetrical and linear (180°). The two equal and opposite bond dipole vectors cancel each other out entirely, resulting in zero net molecular dipole moment."
        ),
        Compound(
            id = "methane",
            name = "Methane",
            iupacName = "Methane",
            formula = "CH₄",
            category = "Organic Hydrocarbon (Alkane)",
            molarMass = 16.04,
            geometry = "Tetrahedral",
            bondAngles = "109.5°",
            polarity = "Non-polar (Symmetrical)",
            functionalGroups = listOf("Alkyl / C-H single bonds"),
            atoms = listOf(
                Atom3D("C", 0f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("H", 0f, 1.09f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 1.03f, -0.36f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -0.51f, -0.36f, 0.89f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -0.51f, -0.36f, -0.89f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(0, 2, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(0, 3, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(0, 4, 1, BondType.COVALENT_NONPOLAR, 109)
            ),
            lewisNotes = "Carbon is central with 4 single covalent bonds to 4 hydrogens. Zero lone pairs on carbon. Octet rule fully satisfied with 8 valence electrons.",
            description = "The simplest alkane and the primary constituent of natural gas. Clean-burning fuel used worldwide for heating and power generation.",
            safetyInfo = "Highly flammable gas; forms explosive air-gas mixtures.",
            bondExplanation = "Carbon forms 4 equivalent sp³ hybridized orbitals directed toward the corners of a regular tetrahedron at 109.5°. The small electronegativity difference between C (2.55) and H (2.20) makes the bonds non-polar."
        ),
        Compound(
            id = "ammonia",
            name = "Ammonia",
            iupacName = "Azane",
            formula = "NH₃",
            category = "Inorganic Base",
            molarMass = 17.031,
            geometry = "Trigonal Pyramidal",
            bondAngles = "107.8°",
            polarity = "Polar (Dipole Moment: 1.42 D)",
            functionalGroups = listOf("Amine (-NH₂)"),
            atoms = listOf(
                Atom3D("N", 0f, 0.2f, 0f, 0xFF3B82F6, 1.1f),
                Atom3D("H", 0.94f, -0.2f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -0.47f, -0.2f, 0.81f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -0.47f, -0.2f, -0.81f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 101),
                ChemicalBond(0, 2, 1, BondType.COVALENT_POLAR, 101),
                ChemicalBond(0, 3, 1, BondType.COVALENT_POLAR, 101)
            ),
            lewisNotes = "Nitrogen has 3 single bonds to hydrogen and 1 lone electron pair on top. The lone pair repels the bonding pairs downward, compressing the angle from 109.5° to 107.8°.",
            description = "Pungent gas vital for worldwide agricultural synthetic fertilizers via the Haber-Bosch process, supporting food production for billions.",
            safetyInfo = "Toxic and corrosive gas; severely irritates respiratory system and eyes.",
            bondExplanation = "Nitrogen is strongly electronegative (3.04). The lone pair of electrons creates a localized region of negative charge at the apex, giving ammonia a strong molecular dipole and basic properties (acting as a Lewis base)."
        ),
        Compound(
            id = "sodium_chloride",
            name = "Sodium Chloride",
            iupacName = "Sodium Chloride",
            formula = "NaCl",
            category = "Ionic Salt",
            molarMass = 58.44,
            geometry = "Face-Centered Cubic Lattice",
            bondAngles = "90.0° / 180.0°",
            polarity = "Ionic (Infinite crystal dipole)",
            functionalGroups = listOf("Ionic Salt"),
            atoms = listOf(
                Atom3D("Na", -0.6f, 0f, 0f, 0xFF8B5CF6, 1.0f),
                Atom3D("Cl", 0.6f, 0f, 0f, 0xFF10B981, 1.3f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 282)
            ),
            lewisNotes = "[Na]⁺ [:Cl:]⁻. Complete electron transfer from sodium 3s¹ valence shell to chlorine 3p⁵ shell, giving both stable noble gas octets.",
            description = "Common table salt. Essential electrolyte for human bodily fluid balance, blood pressure regulation, and nerve impulse conduction.",
            safetyInfo = "Non-toxic food substance; excessive ingestion over time is linked to hypertension.",
            bondExplanation = "Sodium has very low electronegativity (0.93) while chlorine is highly electronegative (3.16). The difference (2.23 > 1.7) causes complete transfer of 1 valence electron from Na to Cl, forming Na⁺ and Cl⁻ ions held together by intense electrostatic Coulomb attraction."
        ),
        Compound(
            id = "hydrochloric_acid",
            name = "Hydrochloric Acid",
            iupacName = "Hydrogen Chloride",
            formula = "HCl",
            category = "Inorganic Strong Acid",
            molarMass = 36.46,
            geometry = "Linear Diatomic",
            bondAngles = "N/A (Diatomic)",
            polarity = "Polar (Dipole Moment: 1.08 D)",
            functionalGroups = listOf("Mineral Acid"),
            atoms = listOf(
                Atom3D("H", -0.64f, 0f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("Cl", 0.64f, 0f, 0f, 0xFF10B981, 1.3f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_POLAR, 127)
            ),
            lewisNotes = "H-Cl with 3 lone electron pairs on chlorine. In water, dissociates 100% into H₃O⁺ and Cl⁻.",
            description = "Strong mineral acid present in human stomach gastric juices (pH 1.5–2) to digest proteins and kill bacteria.",
            safetyInfo = "Corrosive liquid and suffocating acidic vapor. Causes serious skin burns and eye damage.",
            bondExplanation = "Chlorine's high electronegativity draws the shared electron pair toward itself. In aqueous solution, the bond undergoes heterolytic fission as polar water molecules pull H⁺ away, creating hydronium ions."
        ),
        Compound(
            id = "sulfuric_acid",
            name = "Sulfuric Acid",
            iupacName = "Sulfuric Acid",
            formula = "H₂SO₄",
            category = "Inorganic Strong Acid",
            molarMass = 98.079,
            geometry = "Tetrahedral around Sulfur",
            bondAngles = "109.5°",
            polarity = "Polar",
            functionalGroups = listOf("Sulfonate / Sulfate"),
            atoms = listOf(
                Atom3D("S", 0f, 0f, 0f, 0xFFEAB308, 1.15f),
                Atom3D("O", 0f, 1.4f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 0f, -1.4f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.3f, 0f, 0.5f, 0xFFEF4444, 1.1f),
                Atom3D("O", -1.3f, 0f, -0.5f, 0xFFEF4444, 1.1f),
                Atom3D("H", 1.8f, 0.5f, 0.5f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.8f, -0.5f, -0.5f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_POLAR, 142),
                ChemicalBond(0, 2, 2, BondType.COVALENT_POLAR, 142),
                ChemicalBond(0, 3, 1, BondType.COVALENT_POLAR, 157),
                ChemicalBond(0, 4, 1, BondType.COVALENT_POLAR, 157),
                ChemicalBond(3, 5, 1, BondType.COVALENT_POLAR, 97),
                ChemicalBond(4, 6, 1, BondType.COVALENT_POLAR, 97)
            ),
            lewisNotes = "Central sulfur with expanded octet (12 valence electrons) with two S=O double bonds and two S-OH single bonds.",
            description = "The world's highest-volume industrial chemical, nicknamed the 'King of Chemicals'. Used in fertilizers, car lead-acid batteries, and petroleum refining.",
            safetyInfo = "Extremely corrosive; vigorous exothermic reaction with water. Always add acid to water, never water to acid!",
            bondExplanation = "Sulfur uses expanded d-orbitals to form double bonds with two oxygens and single bonds with two hydroxyl oxygens, forming a remarkably stable resonance-stabilized sulfate dianion (SO₄²⁻) upon dissociation."
        ),
        Compound(
            id = "sodium_hydroxide",
            name = "Sodium Hydroxide",
            iupacName = "Sodium Hydroxide",
            formula = "NaOH",
            category = "Inorganic Strong Base",
            molarMass = 39.997,
            geometry = "Ionic Crystal",
            bondAngles = "180.0°",
            polarity = "Ionic",
            functionalGroups = listOf("Hydroxide (OH⁻)"),
            atoms = listOf(
                Atom3D("Na", -1.0f, 0f, 0f, 0xFF8B5CF6, 1.0f),
                Atom3D("O", 0.5f, 0f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", 1.4f, 0f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 240),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 96)
            ),
            lewisNotes = "Na⁺ and [:O-H]⁻. The O-H bond is polar covalent within the hydroxide anion, while the bond between Na⁺ and OH⁻ is purely ionic.",
            description = "Commonly known as caustic soda or lye. Used for soap saponification, paper pulp digestion, and chemical drain cleaners.",
            safetyInfo = "Highly caustic; causes rapid liquefaction necrosis of animal tissue. Severe eye injury risk.",
            bondExplanation = "Contains both ionic bonding (Na⁺ attracting OH⁻) and polar covalent bonding (the oxygen bonded to hydrogen inside OH⁻)."
        ),
        Compound(
            id = "ethanol",
            name = "Ethanol",
            iupacName = "Ethanol",
            formula = "C₂H₅OH",
            category = "Organic Alcohol",
            molarMass = 46.07,
            geometry = "Tetrahedral Carbons, Bent Oxygen",
            bondAngles = "109.5° (C-C-O), 104.5° (C-O-H)",
            polarity = "Polar (Miscible with water)",
            functionalGroups = listOf("Hydroxyl (-OH)"),
            atoms = listOf(
                Atom3D("C", -0.7f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 0.7f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 1.2f, 1.2f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", 2.1f, 1.2f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.1f, 0.9f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.1f, -0.5f, 0.8f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.1f, -0.5f, -0.8f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 0.9f, -0.5f, 0.8f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 0.9f, -0.5f, -0.8f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 154),
                ChemicalBond(1, 2, 1, BondType.COVALENT_POLAR, 143),
                ChemicalBond(2, 3, 1, BondType.COVALENT_POLAR, 96),
                ChemicalBond(0, 4, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(0, 5, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(0, 6, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(1, 7, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(1, 8, 1, BondType.COVALENT_NONPOLAR, 109)
            ),
            lewisNotes = "Two sp³ hybridized carbons connected to an electronegative oxygen bearing a hydroxyl proton and two lone pairs.",
            description = "Common drinking alcohol, hand sanitizer antiseptic, solvent, and renewable biofuel blend (E10/E85).",
            safetyInfo = "Flammable liquid and vapor; intoxicating neurotoxin when consumed in excess.",
            bondExplanation = "The polar -OH hydroxyl head readily forms hydrogen bonds with water molecules, explaining why ethanol dissolves infinitely in water, while its ethyl nonpolar tail dissolves organic oils."
        ),
        Compound(
            id = "benzene",
            name = "Benzene",
            iupacName = "Benzene",
            formula = "C₆H₆",
            category = "Aromatic Hydrocarbon",
            molarMass = 78.11,
            geometry = "Planar Hexagonal Ring",
            bondAngles = "120.0°",
            polarity = "Non-polar",
            functionalGroups = listOf("Aromatic Phenyl Ring"),
            atoms = listOf(
                Atom3D("C", 1.4f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 0.7f, 1.21f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", -0.7f, 1.21f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", -1.4f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", -0.7f, -1.21f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 0.7f, -1.21f, 0f, 0xFF475569, 1.0f),
                Atom3D("H", 2.48f, 0f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 1.24f, 2.15f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.24f, 2.15f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -2.48f, 0f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.24f, -2.15f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", 1.24f, -2.15f, 0f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 2, BondType.COVALENT_NONPOLAR, 140),
                ChemicalBond(1, 2, 1, BondType.COVALENT_NONPOLAR, 140),
                ChemicalBond(2, 3, 2, BondType.COVALENT_NONPOLAR, 140),
                ChemicalBond(3, 4, 1, BondType.COVALENT_NONPOLAR, 140),
                ChemicalBond(4, 5, 2, BondType.COVALENT_NONPOLAR, 140),
                ChemicalBond(5, 0, 1, BondType.COVALENT_NONPOLAR, 140),
                ChemicalBond(0, 6, 1, BondType.COVALENT_NONPOLAR, 108),
                ChemicalBond(1, 7, 1, BondType.COVALENT_NONPOLAR, 108),
                ChemicalBond(2, 8, 1, BondType.COVALENT_NONPOLAR, 108),
                ChemicalBond(3, 9, 1, BondType.COVALENT_NONPOLAR, 108),
                ChemicalBond(4, 10, 1, BondType.COVALENT_NONPOLAR, 108),
                ChemicalBond(5, 11, 1, BondType.COVALENT_NONPOLAR, 108)
            ),
            lewisNotes = "Resonance hybrid between two Kekulé structures with 6 delocalized π-electrons circulating above and below the planar carbon ring.",
            description = "The prototypical aromatic hydrocarbon. Found in crude oil and used as an industrial precursor to synthesize plastics, resins, nylon, and synthetic rubber.",
            safetyInfo = "Carcinogenic and highly flammable. Chronic inhalation causes leukemia and bone marrow suppression.",
            bondExplanation = "All six carbon-carbon bonds have the exact same intermediate length (140 pm)—shorter than a single bond (154 pm) and longer than a double bond (134 pm). The 6 delocalized π electrons confer extraordinary resonance thermodynamic stability (Hückel's 4n+2 rule)."
        ),
        Compound(
            id = "copper_sulfate",
            name = "Copper(II) Sulfate",
            iupacName = "Copper(II) Sulfate",
            formula = "CuSO₄",
            category = "Transition Metal Salt",
            molarMass = 159.609,
            geometry = "Octahedral Coordination in Solution",
            bondAngles = "90.0°",
            polarity = "Ionic",
            functionalGroups = listOf("Cu²⁺ Cation", "Sulfate SO₄²⁻ Anion"),
            atoms = listOf(
                Atom3D("Cu", -1.0f, 0f, 0f, 0xFFF59E0B, 1.2f),
                Atom3D("S", 1.0f, 0f, 0f, 0xFFEAB308, 1.15f),
                Atom3D("O", 1.0f, 1.3f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 1.0f, -1.3f, 0f, 0xFFEF4444, 1.0f),
                Atom3D("O", 2.1f, 0f, 0.7f, 0xFFEF4444, 1.0f),
                Atom3D("O", 2.1f, 0f, -0.7f, 0xFFEF4444, 1.0f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.IONIC, 270),
                ChemicalBond(1, 2, 2, BondType.COVALENT_POLAR, 145),
                ChemicalBond(1, 3, 2, BondType.COVALENT_POLAR, 145),
                ChemicalBond(1, 4, 1, BondType.COVALENT_POLAR, 150),
                ChemicalBond(1, 5, 1, BondType.COVALENT_POLAR, 150)
            ),
            lewisNotes = "[Cu]²⁺ and [SO₄]²⁻. In water, Cu²⁺ coordinates with 4-6 water molecules forming the brilliant azure-blue complex [Cu(H₂O)₆]²⁺.",
            description = "Classic blue vitriol salt widely used in chemistry classrooms for crystallization, electroplating, and as an agricultural fungicide (Bordeaux mixture).",
            safetyInfo = "Harmful if swallowed; highly toxic to aquatic life with long-lasting effects.",
            bondExplanation = "Electrostatic attraction between divalent Cu²⁺ cations and SO₄²⁻ polyatomic anions. The intense blue color arises from d-d electron transitions within the partially filled 3d⁹ shell of the Cu²⁺ ion when split by ligand crystal fields."
        ),
        Compound(
            id = "acetic_acid",
            name = "Acetic Acid",
            iupacName = "Ethanoic Acid",
            formula = "CH₃COOH",
            category = "Organic Weak Acid",
            molarMass = 60.05,
            geometry = "Tetrahedral Methyl, Trigonal Planar Carbonyl",
            bondAngles = "120.0° (C=O)",
            polarity = "Polar",
            functionalGroups = listOf("Carboxylic Acid (-COOH)"),
            atoms = listOf(
                Atom3D("C", -0.8f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("C", 0.7f, 0f, 0f, 0xFF475569, 1.0f),
                Atom3D("O", 1.3f, 1.1f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("O", 1.3f, -1.1f, 0f, 0xFFEF4444, 1.1f),
                Atom3D("H", 2.2f, -1.1f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.2f, 0.9f, 0f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.2f, -0.5f, 0.8f, 0xFFF8FAFC, 0.7f),
                Atom3D("H", -1.2f, -0.5f, -0.8f, 0xFFF8FAFC, 0.7f)
            ),
            bonds = listOf(
                ChemicalBond(0, 1, 1, BondType.COVALENT_NONPOLAR, 150),
                ChemicalBond(1, 2, 2, BondType.COVALENT_POLAR, 122),
                ChemicalBond(1, 3, 1, BondType.COVALENT_POLAR, 131),
                ChemicalBond(3, 4, 1, BondType.COVALENT_POLAR, 97),
                ChemicalBond(0, 5, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(0, 6, 1, BondType.COVALENT_NONPOLAR, 109),
                ChemicalBond(0, 7, 1, BondType.COVALENT_NONPOLAR, 109)
            ),
            lewisNotes = "Carboxyl carbon is sp² hybridized, doubly bonded to carbonyl oxygen and singly bonded to hydroxyl oxygen.",
            description = "The defining component of vinegar (4-8% solution), giving it its sharp, tangy smell and sour taste. Crucial precursor for polyvinyl acetate glue.",
            safetyInfo = "Glacial (pure) acetic acid causes chemical burns. Household vinegar is non-hazardous.",
            bondExplanation = "The strong electron-withdrawing effect of the carbonyl (C=O) double bond weakens the O-H single bond, enabling the proton (H⁺) to dissociate in water and stabilizing the resulting negative acetate anion through resonance delocalization across both oxygens."
        )
    )

    val compounds: List<Compound> by lazy {
        baseCompounds + CompoundsExtendedDataPart1.compounds + CompoundsExtendedDataPart2.compounds
    }
}
